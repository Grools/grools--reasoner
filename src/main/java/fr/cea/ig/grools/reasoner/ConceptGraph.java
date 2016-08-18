package fr.cea.ig.grools.reasoner;

import fr.cea.ig.grools.fact.Concept;
import fr.cea.ig.grools.fact.Relation;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ConceptGraph
 */
public final class ConceptGraph implements Serializable {
    @Getter
    @NonNull
    private final Map< Concept, Set< Relation > > outgoing;
    @Getter
    @NonNull
    private final Map< Concept, Set< Relation > > incoming;
    @Getter
    @NonNull
    private final Map< String, Concept > nameToConcept;
    @Getter
    @NonNull
    private final Set< Relation > relations;
    @Getter
    @NonNull
    private final Set< Concept > concepts;

    public ConceptGraph( ) {
        this.outgoing       = new HashMap<>( );
        this.incoming       = new HashMap<>( );
        this.nameToConcept  = new HashMap<>( );
        this.relations      = new HashSet<>( );
        this.concepts       = new HashSet<>( );
    }


    public ConceptGraph( @NonNull final Map< Concept, Set< Relation > > outgoing, @NonNull final Map< Concept, Set< Relation > > incoming,
                         @NonNull final Map< String, Concept > nameToConcept, @NonNull final Set< Relation > relations,
                         @NonNull final Set< Concept > concepts ) {
        this.outgoing = outgoing;
        this.incoming = incoming;
        this.nameToConcept = nameToConcept;
        this.relations = relations;
        this.concepts = concepts;
    }

    public void addConcept( @NonNull final Concept concept ) {
        concepts.add( concept );
        nameToConcept.put( concept.getName( ), concept );
    }

    public void deleteConcept( @NonNull final Concept concept ) {
        concepts.remove( concept );
        final Set< Relation > outgoingSet = outgoing.get( concept );
        final Set< Relation > incomingSet = incoming.get( concept );
        outgoingSet.forEach( outgoing::remove );
        incomingSet.forEach( incoming::remove );
        relations.removeAll( outgoingSet );
        relations.removeAll( incomingSet );
    }

    public void deleteRelation( @NonNull final Relation relation ) {
        relations.remove( relation );
        final Concept source = relation.getSource( );
        final Concept target = relation.getTarget( );
        incoming.get( target ).remove( relation );
        outgoing.get( source ).remove( relation );
    }

    public void addRelation( @NonNull final Relation relation ) {
        relations.add( relation );
        addConcept( relation.getSource( ) );
        addConcept( relation.getTarget( ) );
        final Set< Relation > outgoingSet = outgoing.getOrDefault( relation.getSource( ), new HashSet<>( ) );
        final Set< Relation > incomingSet = incoming.getOrDefault( relation.getTarget( ), new HashSet<>( ) );
        outgoingSet.add( relation );
        incomingSet.add( relation );
        outgoing.put( relation.getSource( ), outgoingSet );
        incoming.put( relation.getTarget( ), incomingSet );

    }

    public void addRelations( @NonNull final Relation... relation ) {
        addRelations( Arrays.stream( relation ).collect( Collectors.toSet( ) ) );
    }

    public void addRelations( @NonNull final Collection< Relation > relationsToAdd ) {
        relationsToAdd.forEach( this::addRelation );
    }

    public Concept getConcept( @NonNull final String name ) {
        return nameToConcept.get( name );
    }

    boolean hasIncomingRelation( @NonNull final Concept concept ) {
        return incoming.containsKey( concept );
    }

    boolean hasIncomingRelation( @NonNull final Concept concept, Class type ) {
        Set< Relation > relations = incoming.getOrDefault( concept, new HashSet<>( ) );
        return relations.stream( )
                        .filter( relation -> type.isInstance( relation.getSource( ) ) )
                        .findFirst( )
                        .isPresent( );
    }

    boolean hasOutGoingRelation( @NonNull final Concept concept ) {
        return outgoing.containsKey( concept );
    }

    boolean hasOutGoingRelation( @NonNull final Concept concept, Class type ) {
        Set< Relation > relations = outgoing.getOrDefault( concept, new HashSet<>( ) );
        return relations.stream( )
                        .filter( relation -> type.isInstance( relation.getTarget( ) ) )
                        .findFirst( )
                        .isPresent( );
    }

    public Set< Relation > getOutgoing( @NonNull final Concept concept ) {
        return outgoing.getOrDefault( concept, new HashSet<>( ) );
    }

    public Set< Relation > getIncoming( @NonNull final Concept concept ) {
        return incoming.getOrDefault( concept, new HashSet<>( ) );
    }
}
