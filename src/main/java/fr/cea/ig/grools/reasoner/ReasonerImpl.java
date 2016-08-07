package fr.cea.ig.grools.reasoner;


import fr.cea.ig.grools.Mode;
import fr.cea.ig.grools.Reasoner;
import fr.cea.ig.grools.VariantMode;
import fr.cea.ig.grools.Verbosity;
import fr.cea.ig.grools.fact.*;
import fr.cea.ig.grools.logic.Conclusion;
import fr.cea.ig.grools.logic.TruthValuePowerSet;
import fr.cea.ig.grools.logic.TruthValueSet;
import lombok.NonNull;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static fr.cea.ig.grools.logic.Conclusion.ABSENT;
import static fr.cea.ig.grools.logic.Conclusion.AMBIGUOUS;
import static fr.cea.ig.grools.logic.Conclusion.AMBIGUOUS_ABSENCE;
import static fr.cea.ig.grools.logic.Conclusion.AMBIGUOUS_CONTRADICTORY;
import static fr.cea.ig.grools.logic.Conclusion.AMBIGUOUS_PRESENCE;
import static fr.cea.ig.grools.logic.Conclusion.CONFIRMED_ABSENCE;
import static fr.cea.ig.grools.logic.Conclusion.CONFIRMED_PRESENCE;
import static fr.cea.ig.grools.logic.Conclusion.CONTRADICTORY_ABSENCE;
import static fr.cea.ig.grools.logic.Conclusion.CONTRADICTORY_PRESENCE;
import static fr.cea.ig.grools.logic.Conclusion.MISSING;
import static fr.cea.ig.grools.logic.Conclusion.UNCONFIRMED_ABSENCE;
import static fr.cea.ig.grools.logic.Conclusion.UNCONFIRMED_CONTRADICTORY;
import static fr.cea.ig.grools.logic.Conclusion.UNCONFIRMED_PRESENCE;
import static fr.cea.ig.grools.logic.Conclusion.UNEXPECTED_ABSENCE;
import static fr.cea.ig.grools.logic.Conclusion.UNEXPECTED_PRESENCE;
import static fr.cea.ig.grools.logic.Conclusion.UNEXPLAINED;

/**
 * ReasonerImpl
 */
public class ReasonerImpl implements Reasoner {
    private final ConceptGraph          graph;
    private final Mode                  mode;
    private final Verbosity             verbosity;
    private final Set<PriorKnowledge>[] predictionstoEvaluates;
    private final Set<PriorKnowledge>[] expectationstoEvaluates;
    private int                         currentFrame;
    private int                         nextFrame;
    private boolean                     hasBeenProceesed;
    
    private final static DoubleEntryTable<TruthValueSet,TruthValueSet,Conclusion> conclusions = new DoubleEntryTable<>(
        new TruthValueSet[] { TruthValueSet.T, TruthValueSet.F, TruthValueSet.B, TruthValueSet.N },
        new TruthValueSet[] { TruthValueSet.T, TruthValueSet.F, TruthValueSet.B, TruthValueSet.N },
        new Conclusion[][]{ // PREDICTION
                            // TRUE                  FALSE                BOTH                        UNKNOWN         | EXPECTATION
                            {  CONFIRMED_PRESENCE  , UNEXPECTED_ABSENCE , CONTRADICTORY_ABSENCE     , MISSING  },  // | TRUE
                            {  UNEXPECTED_PRESENCE , CONFIRMED_ABSENCE  , CONTRADICTORY_PRESENCE    , ABSENT   },  // | FALSE
                            {  AMBIGUOUS_PRESENCE  , AMBIGUOUS_ABSENCE  , AMBIGUOUS_CONTRADICTORY   , AMBIGUOUS},  // | BOTH
                            {  UNCONFIRMED_PRESENCE, UNCONFIRMED_ABSENCE, UNCONFIRMED_CONTRADICTORY , UNEXPLAINED} // | UNKNOWN
        }
    );
    
    private static TruthValueSet predictionToTruthValueSet( @NonNull final TruthValuePowerSet tvps ){
        TruthValueSet result;
        switch ( tvps ){
            case T:
                result = TruthValueSet.T;
                break;
            case F:
            case FB:
            case TF:
            case NTFB:
            case NTF:
            case NFB:
            case NF:
            case TFB:
                result = TruthValueSet.F;
                break;
            case B:
            case TB:
            case NTB:
            case NB:
                result = TruthValueSet.B;
                break;
            case N:
            case NT:
            case n:
                result = TruthValueSet.N;
                break;
            default:
                result = TruthValueSet.N;
                break;
        }
        return result;
    }
    
    private static TruthValueSet expectationToTruthValueSet( @NonNull final TruthValuePowerSet tvps ){
        TruthValueSet result;
        switch ( tvps ){
            case T:
            case NT:
                result = TruthValueSet.T;
                break;
            case F:
            case FB:
            case TF:
            case NTFB:
            case NTF:
            case NFB:
            case NF:
            case TFB:
                result = TruthValueSet.F;
                break;
            case B:
            case TB:
            case NTB:
            case NB:
                result = TruthValueSet.B;
                break;
            case N:
            case n:
                result = TruthValueSet.N;
                break;
            default:
                result = TruthValueSet.N;
                break;
        }
        return result;
    }

    
    private void markSpecific(){
        graph.getOutgoing()
             .entrySet()
             .stream()
             .filter(entry -> entry.getKey() instanceof PriorKnowledge)
             .forEach(entry -> {
                PriorKnowledge pk = (PriorKnowledge) entry.getKey();
                if (entry.getValue().size() == 1)
                    pk.setIsSpecific(true);
                else
                    pk.setIsSpecific(false);
        });
    }
    
    public ReasonerImpl(  ){
        this( new ConceptGraph(), Mode.NORMAL, Verbosity.QUIET );
    }
    
    
    public ReasonerImpl( @NonNull final ConceptGraph graph ){
        this( graph, Mode.NORMAL, Verbosity.QUIET );
    }

    public ReasonerImpl( @NonNull final Mode mode, @NonNull final Verbosity verbosity ){
        this( new ConceptGraph(), mode, verbosity );
    }

    public ReasonerImpl( @NonNull final ConceptGraph graph, @NonNull final Mode mode, @NonNull final Verbosity verbosity ){
        this.graph                  = graph;
        this.mode                   = mode;
        this.verbosity              = verbosity;
        this.predictionstoEvaluates = new Set[2];
        this.expectationstoEvaluates= new Set[2];
        this.currentFrame           = 0;
        this.nextFrame              = 1;
        this.hasBeenProceesed       = false;
        
        predictionstoEvaluates[currentFrame]    = new HashSet<>();
        predictionstoEvaluates[nextFrame]       = new HashSet<>();
        expectationstoEvaluates[currentFrame]   = new HashSet<>();
        expectationstoEvaluates[nextFrame]      = new HashSet<>();
    }

    @Override
    public void addVariantMode( VariantMode... variants ) {
        mode.setVariants( Arrays.stream(variants)
                                .collect( Collectors.toCollection( () -> EnumSet.noneOf(VariantMode.class ) ) ) );
    }

    @Override
    public void removeVariantMode( VariantMode... variants ) {
        Set<VariantMode> toRemove =  Arrays.stream(variants)
                                           .collect( Collectors.toCollection( () -> EnumSet.noneOf(VariantMode.class ) ) );
        Set<VariantMode> current  = mode.getVariants();
        // reference object
        current.removeAll(toRemove);
    }

    @Override
    public void insert( @NonNull final Object... data ) {
        Arrays.stream(data)
              .forEach( this::insert );
    }

    @Override
    public void insert( @NonNull final Collection< ? > data ) {
        data.forEach( this::insert );
    }

    public void insert( @NonNull final Object data ) {
        if( data instanceof Concept )
            graph.addConcept( (Concept)data );
        if( data instanceof Relation ) {
            final Relation relation = (Relation) data;
            graph.addRelation(relation);
            if( hasBeenProceesed ){
                if( relation.getSource() instanceof Observation) {
                    final Observation observation = (Observation) relation.getSource();
                    assert relation.getTarget() instanceof PriorKnowledge;
                    final PriorKnowledge target = (PriorKnowledge) relation.getTarget();
                    target.setConclusion( UNEXPLAINED );
                    switch (observation.getType()) {
                        case CURATION:
                            target.setPrediction( TruthValuePowerSet.n );
                            predictionstoEvaluates[currentFrame].add(target);
                        case EXPERIMENTATION:
                            target.setExpectation( TruthValuePowerSet.n );
                            expectationstoEvaluates[currentFrame].add(target);
                            break;
                        case COMPUTATION:
                            target.setPrediction( TruthValuePowerSet.n );
                            predictionstoEvaluates[currentFrame].add(target);
                            break;
                    }
                }
            }
        }
    }

    @Override
    public void delete( @NonNull final Object... data ) {
        Arrays.stream(data)
              .forEach( this::delete );
    }

    @Override
    public void delete( @NonNull final Collection< ? > data ) {
        data.forEach( this::delete );
    }

    public void delete( @NonNull final Object data ) {
        if( data instanceof Concept )
            graph.deleteConcept( (Concept)data );
        if( data instanceof Relation ) {
            final Relation relation = (Relation) data;
            graph.deleteRelation( relation );
            if( hasBeenProceesed ){
                if( relation.getSource() instanceof Observation) {
                    final Observation observation = (Observation) relation.getSource();
                    assert relation.getTarget() instanceof PriorKnowledge;
                    final PriorKnowledge target = (PriorKnowledge) relation.getTarget();
                    target.setConclusion( UNEXPLAINED );
                    switch (observation.getType()) {
                        case CURATION:
                            target.setPrediction( TruthValuePowerSet.n );
                            predictionstoEvaluates[currentFrame].add(target);
                        case EXPERIMENTATION:
                            target.setExpectation( TruthValuePowerSet.n );
                            expectationstoEvaluates[currentFrame].add(target);
                            break;
                        case COMPUTATION:
                            target.setPrediction( TruthValuePowerSet.n );
                            predictionstoEvaluates[currentFrame].add(target);
                            break;
                    }
                }
            }
        }
    }

    @Override
    public Reasoner copy( ) {
        return new ReasonerImpl( graph, mode, verbosity );
    }

    @Override
    public void save( File file ) throws IOException {

    }

    @Override
    public Mode getMode( ) {
        return mode;
    }

    @Override
    public Set< Concept > getConcepts( ) {
        return graph.getConcepts();
    }

    @Override
    public Concept getConcept( final String name ) {
        return graph.getConcept( name );
    }

    @Override
    public PriorKnowledge getPriorKnowledge( final String name ) {
        PriorKnowledge result = null;
        final Concept concept = graph.getConcept( name );
        if( concept instanceof PriorKnowledge )
            result = (PriorKnowledge ) concept;
        return result;
    }

    @Override
    public Set< PriorKnowledge > getPriorKnowledges( ) {
        return getConcepts().stream()
                            .filter(    obj -> obj instanceof PriorKnowledge )
                            .map(       obj -> (PriorKnowledge)obj )
                            .collect( Collectors.toSet( ));
    }

    @Override
    public Set< PriorKnowledge > getLeavesPriorKnowledges( ) {
        return getConcepts().stream()
                            .filter(    concept -> concept instanceof PriorKnowledge )
                            .filter(    concept -> ! graph.hasIncomingRelation( concept, PriorKnowledge.class ) )
                            .map(       concept -> (PriorKnowledge)concept )
                            .collect( Collectors.toSet() );
    }

    @Override
    public Set< PriorKnowledge > getTopsPriorKnowledges( ) {
        return getConcepts().stream()
                            .filter(    concept -> concept instanceof PriorKnowledge )
                            .filter(    concept -> ! graph.hasOutGoingRelation( concept, PriorKnowledge.class  ) )
                            .map(       concept -> (PriorKnowledge)concept )
                            .collect( Collectors.toSet() );
    }
    
    public Set<PriorKnowledge> getParentsPriorKnowledge( @NonNull final PriorKnowledge priorKnowledge ){
        return graph.getOutgoing( priorKnowledge )
                    .stream()
                    .filter( relation -> relation.getTarget() instanceof PriorKnowledge )
                    .map( relation -> (PriorKnowledge)relation.getTarget() )
                    .collect( Collectors.toSet() );
    }
    
    public Set<PriorKnowledge> getChildrensPriorKnowledge( @NonNull final PriorKnowledge priorKnowledge ){
        return graph.getIncoming( priorKnowledge )
                    .stream()
                    .filter( relation -> relation.getSource() instanceof PriorKnowledge )
                    .map( relation -> (PriorKnowledge)relation.getSource() )
                    .collect( Collectors.toSet() );
    }

    @Override
    public Set< Relation > getRelations( ) {
        return graph.getRelations();
    }

    @Override
    public Set< Relation > getRelations( @NonNull final Concept source, @NonNull final Concept target ) {
        return graph.getOutgoing( source )
                    .stream()
                    .filter( relation -> relation.getTarget() == target )
                    .collect( Collectors.toSet() );
    }

    @Override
    public Set< Relation > getRelationsWithSource( @NonNull final Concept source ) {
        return graph.getOutgoing( source )
                    .stream()
                    .collect( Collectors.toSet() );
    }

    @Override
    public Set< Relation > getRelationsWithTarget( @NonNull final Concept target ) {
        return graph.getIncoming( target )
                    .stream()
                    .collect( Collectors.toSet() );
    }

    @Override
    public Relation getRelation( @NonNull final Concept source, @NonNull final Concept target, @NonNull final Enum< ? > type ) {
        return graph.getOutgoing( source )
                    .stream()
                    .filter( relation -> relation.getTarget() == target )
                    .filter( relation -> relation.getType()   == type )
                    .findFirst()
                    .orElse(null);
    }
    
    public Set<PriorKnowledge> getPartOf( @NonNull final PriorKnowledge priorKnowledge ){
        return graph.getIncoming( priorKnowledge )
                    .stream()
                    .filter( relation -> relation.getType()   == RelationType.PART )
                    .filter( relation -> relation.getSource() instanceof PriorKnowledge )
                    .map(    relation -> (PriorKnowledge) relation.getSource() )
                    .collect( Collectors.toSet() );
    }
    
    public Set<PriorKnowledge> getHasPart( @NonNull final PriorKnowledge priorKnowledge ){
        return graph.getOutgoing( priorKnowledge )
                    .stream()
                    .filter( relation -> relation.getType()   == RelationType.PART )
                    .filter( relation -> relation.getTarget() instanceof PriorKnowledge )
                    .map(    relation -> (PriorKnowledge) relation.getTarget() )
                    .collect( Collectors.toSet() );
    }
    
    public Set<PriorKnowledge> getSubtypeOf( @NonNull final PriorKnowledge priorKnowledge ){
        return graph.getIncoming( priorKnowledge )
                    .stream()
                    .filter( relation -> relation.getType()   == RelationType.SUBTYPE )
                    .filter( relation -> relation.getSource() instanceof PriorKnowledge )
                    .map(    relation -> (PriorKnowledge) relation.getSource() )
                    .collect( Collectors.toSet() );
    }
    
    public Set<PriorKnowledge> getHasSubtype( @NonNull final PriorKnowledge priorKnowledge ){
        return graph.getOutgoing( priorKnowledge )
                    .stream()
                    .filter( relation -> relation.getType()   == RelationType.SUBTYPE )
                    .filter( relation -> relation.getTarget() instanceof PriorKnowledge )
                    .map(    relation -> (PriorKnowledge) relation.getTarget() )
                    .collect( Collectors.toSet() );
    }

    @Override
    public Set< Observation > getObservations( ) {
        return getConcepts().stream()
                            .filter(    concept -> concept instanceof Observation)
                            .map(       concept -> (Observation)concept )
                            .collect( Collectors.toSet() );
    }

    @Override
    public Set< Observation > getObservationsUsingConceptRelation( @NonNull final String conceptName ) {
        Set< Observation > observations = null;
        final Concept target = graph.getConcept( conceptName );
        if( target != null ){
            observations = graph.getIncoming( target )
                                .stream()
                                .filter(    concept -> concept instanceof Observation)
                                .map(       concept -> (Observation)concept)
                                .collect( Collectors.toSet() );
        }
        return observations;
    }
    
    public Set< Observation > getObservationsRelatedToConcept( @NonNull final Concept target ) {
        final Set< Observation > observations = new HashSet<>();
        if( target != null ){
            graph.getIncoming( target )
                 .stream()
                 .filter(   concept -> concept instanceof Observation)
                 .map(      concept -> (Observation)concept)
                 .collect( Collectors.toCollection( () -> observations ) );
        }
        return observations;
    }
    
    public Set< Observation > getPredictionsRelatedToConcept( @NonNull final Concept target ) {
        final Set< Observation > observations = new HashSet<>();
        if( target != null ){
            graph.getIncoming( target )
                 .stream()
                 .map(      relation    -> relation.getSource() )
                 .filter(   concept     -> concept instanceof Observation)
                 .map(      concept     -> (Observation)concept)
                 .filter(   observation -> observation.getType().equals( ObservationType.COMPUTATION ) || observation.getType().equals( ObservationType.CURATION ) )
                 .collect( Collectors.toCollection( () -> observations ) );
        }
        return observations;
    }
    
    public Set< Observation > getExpectationsRelatedToConcept( @NonNull final Concept target ) {
        final Set< Observation > observations = new HashSet<>();
        if( target != null ){
            graph.getIncoming( target )
                 .stream()
                 .map(      relation    -> relation.getSource() )
                 .filter(   concept -> concept instanceof Observation)
                 .map(      concept -> (Observation)concept)
                 .filter(   observation -> observation.getType().equals( ObservationType.EXPERIMENTATION ) || observation.getType().equals( ObservationType.CURATION ) )
                 .collect( Collectors.toCollection( () -> observations ) );
        }
        return observations;
    }

    @Override
    public Observation getObservation( @NonNull final String name ) {
        Observation result = null;
        final Concept concept = graph.getConcept( name );
        if( concept instanceof Observation )
            result = (Observation ) concept;
        return result;
    }

    @Override
    public Set< Relation > getSubGraph( @NonNull final Concept concept ) {
        Set< Relation > subGraph = getRelationsWithTarget( concept );
        for( final Relation relation : subGraph )
            subGraph.addAll( getSubGraph( relation.getSource() ) );
        return subGraph;
    }

    @Override
    public void reasoning( ) {
        // deep search and mark could be done once other time just trigger and set expected value inside insert/delete method
        // if( !hasBeenProceesed )
            markSpecific();
        hasBeenProceesed = true;
        boolean                             isReasoning = true;
        final Set<PriorKnowledge>           tops        = getTopsPriorKnowledges();
        final Set<PriorKnowledge>           leaves      = getLeavesPriorKnowledges();
        
        
        for( final  PriorKnowledge leaf : leaves ){
            final Set<Observation>      predictions     = getPredictionsRelatedToConcept( leaf );
            final TruthValueSet         truthValueSet   = Observation.union( predictions );
            final TruthValuePowerSet    result          = TruthValueSet.union( truthValueSet );
            if( leaf.getPrediction() != result ) {
                leaf.setPrediction( result );
                predictionstoEvaluates[currentFrame].addAll(getParentsPriorKnowledge(leaf));
            }
        }
    
        Iterator<PriorKnowledge> it = predictionstoEvaluates[currentFrame].iterator();
        
        
        // prediction inference
        while ( isReasoning ) {
            if ( it.hasNext() ) {
                final PriorKnowledge   pk          = it.next();
                final Set<Observation> predictions = getPredictionsRelatedToConcept(pk);
                TruthValuePowerSet     result      = null;
        
                if (predictions.isEmpty()) {
                    final Set<PriorKnowledge>       partOf              = getPartOf( pk );
                    final Set<PriorKnowledge>       subtypeOf           = getSubtypeOf( pk );
                    if( mode.getVariants().contains(VariantMode.DISPENSABLE) ){
                        final Set<PriorKnowledge> toRemovePartPk = partOf.stream()
                                                                         .filter( priorknowledge -> priorknowledge.getIsDispensable() )
                                                                         .collect(Collectors.toSet());
                        partOf.removeAll(toRemovePartPk);
                        final Set<PriorKnowledge> toRemoveSubtypePK = subtypeOf.stream()
                                                                               .filter( priorknowledge -> priorknowledge.getIsDispensable() )
                                                                               .collect(Collectors.toSet());
                        subtypeOf.removeAll(toRemoveSubtypePK);
                    }
                    final Set<TruthValuePowerSet>   predictionsPart     = partOf.stream()
                                                                                .map( PriorKnowledge::getPrediction )
                                                                                .collect( Collectors.toSet() );
                    final Set<TruthValuePowerSet>   predictionsSubtype  = subtypeOf.stream()
                                                                                   .map( PriorKnowledge::getPrediction )
                                                                                   .collect( Collectors.toSet() );
                    if( mode.getVariants().contains(VariantMode.SPECIFIC) ){
                        boolean enableSpecificRule = partOf.stream()
                                                           .filter( priorknowledge -> priorknowledge.getIsSpecific() )
                                                           .filter( priorknowledge -> priorknowledge.getPrediction() == TruthValuePowerSet.T )
                                                           .findFirst()
                                                           .isPresent();
                        if( enableSpecificRule )
                            predictionsPart.remove( TruthValuePowerSet.N );
                    }
                    final TruthValuePowerSet        tmp                 = TruthValuePowerSet.merge( predictionsPart );
                    predictionsSubtype.add( tmp );
                    result = TruthValuePowerSet.choice( predictionsSubtype );
                }
                else {
                    final TruthValueSet truthValueSet = Observation.union( predictions );
                    result = TruthValueSet.union(truthValueSet);
                }
                if( pk.getPrediction() != result ) {
                    pk.setPrediction( result );
                    predictionstoEvaluates[nextFrame].addAll( getParentsPriorKnowledge( pk ) );
                }
            }
            else if( predictionstoEvaluates[nextFrame].isEmpty() )
                isReasoning = false;
            else{
                currentFrame            = ( currentFrame    == 0 ) ? 1 : 0;
                nextFrame               = ( nextFrame       == 0 ) ? 1 : 0;
                it                      = predictionstoEvaluates[currentFrame].iterator();
                predictionstoEvaluates[nextFrame] = new HashSet<>();
            }
            
        }
        
        currentFrame= 0;
        nextFrame   = 1;
        isReasoning = true;
    
        predictionstoEvaluates[currentFrame]   = new HashSet<>();
        predictionstoEvaluates[nextFrame]      = new HashSet<>();
        
        for( final  PriorKnowledge top : tops ){
            final Set<Observation>      expectations    = getExpectationsRelatedToConcept( top );
            final TruthValueSet         truthValueSet   = Observation.union( expectations );
            final TruthValuePowerSet    result          = TruthValueSet.union( truthValueSet );
            if( top.getExpectation() != result ){
                top.setExpectation( result );
                expectationstoEvaluates[currentFrame].addAll( getChildrensPriorKnowledge( top ) );
                try{
                    Conclusion conclusion = conclusions.get(    expectationToTruthValueSet( top.getPrediction()  ),
                                                                predictionToTruthValueSet(  top.getExpectation() ) );
                    top.setConclusion( conclusion );
                }
                catch ( Exception e ){
                    e.printStackTrace(); // should never come
                }
            }
        }
        
        it = expectationstoEvaluates[currentFrame].iterator();
        
        
        //expectation inference
        while ( isReasoning ) {
            if ( it.hasNext() ) {
                final PriorKnowledge   pk          = it.next();
                final Set<Observation> expectations= getExpectationsRelatedToConcept(pk);
                TruthValuePowerSet     result      = null;
        
                if (expectations.isEmpty()) {
                    if( mode.getVariants().contains(VariantMode.DISPENSABLE) && pk.getIsDispensable() ){
                        result = TruthValuePowerSet.N;
                    }
                    else {
                        final Set<PriorKnowledge> hasPart    = getHasPart(pk);
                        final Set<PriorKnowledge> hasSubtype = getHasSubtype(pk);
                        final Set<TruthValuePowerSet> expectationPart = hasPart.stream()
                                                                               .map(PriorKnowledge::getExpectation)
                                                                               .collect(Collectors.toSet());
                        final Set<TruthValuePowerSet> expectationSubtype = hasSubtype.stream()
                                                                                     .map(PriorKnowledge::getExpectation)
                                                                                     .collect(Collectors.toSet());
                        if (expectationSubtype.contains(TruthValuePowerSet.F))
                            result = TruthValuePowerSet.F;
                        else {
                            for (final PriorKnowledge parent : hasSubtype) {
                                final Set<PriorKnowledge> brothers = getChildrensPriorKnowledge(parent);
            
                                final Set<TruthValuePowerSet> brothersPredictions = brothers.stream()
                                                                                            .map(PriorKnowledge::getPrediction)
                                                                                            .collect(Collectors.toSet());
                                if (pk.getPrediction() == TruthValuePowerSet.choice(brothersPredictions))
                                    expectationPart.add(parent.getExpectation());
                            }
                            result = TruthValuePowerSet.merge(expectationPart);
                            if (result == TruthValuePowerSet.n)
                                result = TruthValuePowerSet.N;
                        }
                    }
                }
                else {
                    final TruthValueSet truthValueSet = Observation.union( expectations );
                    result = TruthValueSet.union(truthValueSet);
                }
                if( pk.getExpectation() != result ) {
                    pk.setExpectation( result );
                    Conclusion conclusion = null;
                    try{
                        conclusion = conclusions.get( expectationToTruthValueSet(  pk.getPrediction()  ),
                                                      predictionToTruthValueSet( pk.getExpectation() ) );
                        expectationstoEvaluates[nextFrame].addAll( getChildrensPriorKnowledge( pk ) );
                    }
                    catch ( Exception e ){
                        e.printStackTrace(); // should never come
                    }
                    pk.setConclusion( conclusion );
                }
            }
            else if( expectationstoEvaluates[nextFrame].isEmpty() )
                isReasoning = false;
            else{
                currentFrame    = ( currentFrame    == 0 ) ? 1 : 0;
                nextFrame       = ( nextFrame       == 0 ) ? 1 : 0;
                it              = expectationstoEvaluates[currentFrame].iterator();
                expectationstoEvaluates[nextFrame] = new HashSet<>();
            }
        }
        expectationstoEvaluates[currentFrame]   = new HashSet<>();
        expectationstoEvaluates[nextFrame]      = new HashSet<>();
    }

    @Override
    public void close( ) throws Exception {

    }
}
