package fr.cea.ig.grools;

import fr.cea.ig.grools.fact.Concept;
import fr.cea.ig.grools.fact.Observation;
import fr.cea.ig.grools.fact.PriorKnowledge;
import fr.cea.ig.grools.fact.Relation;
import fr.cea.ig.grools.logic.TruthValuePowerSet;
import fr.cea.ig.grools.logic.TruthValueSet;
import lombok.NonNull;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

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


    static TruthValueSet predictionToTruthValueSet( @NonNull final TruthValuePowerSet tvps ) {
        TruthValueSet result;
        switch ( tvps ) {
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

    static TruthValueSet expectationToTruthValueSet( @NonNull final TruthValuePowerSet tvps ) {
        TruthValueSet result;
        switch ( tvps ) {
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
