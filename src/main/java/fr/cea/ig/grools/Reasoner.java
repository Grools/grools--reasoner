package fr.cea.ig.grools;

import fr.cea.ig.grools.fact.Concept;
import fr.cea.ig.grools.fact.Observation;
import fr.cea.ig.grools.fact.PriorKnowledge;
import fr.cea.ig.grools.fact.Relation;
import fr.cea.ig.grools.fact.RelationType;

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
    void                addVariantMode( final VariantMode... variants );

    void                removeVariantMode( final VariantMode... variants );

    void                insert( final Object... data);

    void                insert( final Collection<?> data);

    void                delete( final Object... data);

    void                delete( final Collection<?> data);

    Reasoner            copy();

    Mode                getMode();

    Set<Concept>        getConcepts();

    Concept             getConcept( final String name );

    PriorKnowledge      getPriorKnowledge( final String name );

    Set<PriorKnowledge> getPriorKnowledges();

    Set<Relation>       getRelations();

    Set<Relation>       getRelations( final Concept source, final Concept target );

    Set<Relation>       getRelationsWithSource( Concept source );

    Set<Relation>       getRelationsWithTarget( Concept target );

    Relation            getRelation( final Concept source, final Concept target, final RelationType type );

    Set<Observation>    getObservations();

    Set<Observation>    getObservationsUsingConceptRelation( final String conceptId);

    Observation         getObservation( final String name);

    void               reasoning();
}
