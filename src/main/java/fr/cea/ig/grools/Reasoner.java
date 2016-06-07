package fr.cea.ig.grools;

import fr.cea.ig.grools.fact.Concept;
import fr.cea.ig.grools.fact.Observation;
import fr.cea.ig.grools.fact.PriorKnowledge;
import fr.cea.ig.grools.fact.Relation;

import java.io.Serializable;
import java.util.List;

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
 *  + getConcepts()                                                         : List<Concept>
 *  + getRelations()                                                        : List<Relation>
 *  + getObservations()                                                     : List<Observation>
 *  + getObservationsUsingConceptRelation( final String conceptId)  : List<Observation>
 *  + getObservation( final String name)                              : Observation
 *  + reasoning()                                                           : void
 * }
 * @enduml
 */
public interface Reasoner extends Serializable, AutoCloseable {
    void                addVariantMode( final VariantMode... variants );

    void                removeVariantMode( final VariantMode... variants );

    void                insert( final Object... data);

    void                delete( final Object... data);

    Reasoner            copy();

    Mode                getMode();

    List<Concept>       getConcepts();

    Concept             getConcept( final String name );

    PriorKnowledge      getPriorKnowledge( final String name );

    List<Relation>      getRelations();

    Relation            getRelation( final Concept source, final Concept target );

    List<Observation>   getObservations();

    List<Observation>   getObservationsUsingConceptRelation( final String conceptId);

    Observation         getObservation( final String name);

    void reasoning();
}
