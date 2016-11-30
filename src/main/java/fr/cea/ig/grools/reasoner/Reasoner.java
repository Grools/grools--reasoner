package fr.cea.ig.grools.reasoner;

import ch.qos.logback.classic.Logger;
import fr.cea.ig.grools.fact.Concept;
import fr.cea.ig.grools.fact.Observation;
import fr.cea.ig.grools.fact.PriorKnowledge;
import fr.cea.ig.grools.fact.Relation;
import fr.cea.ig.grools.logic.Conclusion;
import fr.cea.ig.grools.logic.TruthValuePowerSet;
import fr.cea.ig.grools.logic.TruthValueSet;
import lombok.NonNull;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

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
 * Reasoner
 */
/*
 * @startuml
 * skinparam defaultFontName  Monospaced
 * interface Reasoner extends Serializable, AutoCloseable{
 *  + addVariantMode( final VariantMode... variants )               : void
 *  + removeVariantMode( final VariantMode... variants )            : void
 *  + insert( final Object... data)                                 : void
 *  + copy()                                                                : Reasoner
 *  + getMode()                                                             : Mode
 *  + find(  final String name )                                      : Fact
 *  + getConcepts()                                                         : Set<Concept>
 *  + getRelations()                                                        : Set<Relation>
 *  + getObservations()                                                     : Set<Observation>
 *  + getObservationsUsingConceptRelation( final String conceptId)  : Set<Observation>
 *  + getObservation( final String name)                              : Observation
 *  + reasoning()                                                           : void
 * }
 * @enduml
 */
public interface Reasoner extends Serializable, AutoCloseable {
    Logger LOGGER = ( Logger ) LoggerFactory.getLogger( Reasoner.class );
    
    DoubleEntryTable< TruthValueSet, TruthValueSet, Conclusion> conclusions = new DoubleEntryTable<>(
            new TruthValueSet[]{ TruthValueSet.T, TruthValueSet.F, TruthValueSet.B, TruthValueSet.N },
            new TruthValueSet[]{ TruthValueSet.T, TruthValueSet.F, TruthValueSet.B, TruthValueSet.N },
            new Conclusion[][]{ // PREDICTION
                                // TRUE                    FALSE                   BOTH                          NONE          | EXPECTATION
                                { CONFIRMED_PRESENCE    , UNEXPECTED_ABSENCE    , CONTRADICTORY_ABSENCE     , MISSING     },// | TRUE
                                { UNEXPECTED_PRESENCE   , CONFIRMED_ABSENCE     , CONTRADICTORY_PRESENCE    , ABSENT      },// | FALSE
                                { AMBIGUOUS_PRESENCE    , AMBIGUOUS_ABSENCE     , AMBIGUOUS_CONTRADICTORY   , AMBIGUOUS   },// | BOTH
                                { UNCONFIRMED_PRESENCE  , UNCONFIRMED_ABSENCE   , UNCONFIRMED_CONTRADICTORY , UNEXPLAINED } // | NONE
            }
    );
    
    static Reasoner load( @NonNull final File file ) {
        Reasoner              reasoner = null;
        final FileInputStream fis;
        try {
            fis = new FileInputStream( file );
            final ObjectInputStream ois              = new ObjectInputStream( fis );
            final boolean           hasBeenProceesed = ois.readBoolean( );
            final Mode              mode             = ( Mode ) ois.readObject( );
            final Verbosity         verbosity        = ( Verbosity ) ois.readObject( );
            final ConceptGraph      graph            = ( ConceptGraph ) ois.readObject( );
            reasoner = new ReasonerImpl( graph, mode, verbosity, hasBeenProceesed );
        }
        catch ( ClassNotFoundException e ) {
            LOGGER.error( e.getMessage( ) );
            System.exit( 1 );
        }
        catch ( FileNotFoundException e ) {
            LOGGER.error( "File: " + file.toString( ) + "not found" );
            System.exit( 1 );
        }
        catch ( IOException e ) {
            LOGGER.error( "Can not read/write into " + file.toString( ) );
            System.exit( 1 );
        }
        return reasoner;
}

    static TruthValueSet predictionToTruthValueSet( @NonNull final TruthValuePowerSet tvps ) {
        TruthValueSet result;
        switch ( tvps ) {
            case T:
                result = TruthValueSet.T;
                break;
            case F:
                result = TruthValueSet.F;
                break;
            case NB:
            case NTB:
            case TB:
            case TF:
            case TFB:
            case NTF:
            case NTFB:
            case FB:
            case NFB:
            case B:
                result = TruthValueSet.B;
                break;
            case n:
            case NT:
            case NF:
            case N:
            default:
                result = TruthValueSet.N;
                break;
        }
        return result;
    }

    static TruthValueSet expectationToTruthValueSet( @NonNull final TruthValuePowerSet tvps ) {
        TruthValueSet result;
        switch ( tvps ) {
            case NT:
            case T:
                result = TruthValueSet.T;
                break;
            case NF:
            case F:
                result = TruthValueSet.F;
                break;
            case NB:
            case NTB:
            case TB:
            case TF:
            case TFB:
            case NTF:
            case NTFB:
            case FB:
            case NFB:
            case B:
                result = TruthValueSet.B;
                break;
            case n:
            case N:
            default:
                result = TruthValueSet.N;
                break;
        }
        return result;
    }

    void                addVariantMode( final VariantMode... variants );

    void                removeVariantMode( final VariantMode... variants );

    void                insert( final Object... data);

    void                insert( final Collection<?> data);

    void                delete( final Object... data);

    void                delete( final Collection<?> data);

    Reasoner            copy();

    void                save( final File file ) throws IOException;

    Mode                getMode();

    Set<Concept>        getConcepts();

    Concept             getConcept( final String name );

    PriorKnowledge      getPriorKnowledge( final String name );

    Set<PriorKnowledge> getPriorKnowledges();

    Set<PriorKnowledge> getLeavesPriorKnowledges();

    Set<PriorKnowledge> getTopsPriorKnowledges();

    Set<Relation>       getRelations();

    Set<Relation>       getRelations( final Concept source, final Concept target );

    Set<Relation>       getRelationsWithSource( Concept source );

    Set<Relation>       getRelationsWithTarget( Concept target );

    Relation            getRelation( final Concept source, final Concept target, final Enum<?> type );

    Set<Observation>    getObservations();

    Set<Observation>    getObservationsUsingConceptRelation( final String conceptId);

    Observation         getObservation( final String name);

    Set<Relation>       getSubGraph( final Concept concept );

    void               reasoning();
}
