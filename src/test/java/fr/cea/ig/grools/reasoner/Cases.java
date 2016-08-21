package fr.cea.ig.grools.reasoner;


import fr.cea.ig.grools.fact.Observation;
import fr.cea.ig.grools.fact.ObservationImpl;
import fr.cea.ig.grools.fact.ObservationType;
import fr.cea.ig.grools.fact.PriorKnowledge;
import fr.cea.ig.grools.fact.PriorKnowledgeImpl;
import fr.cea.ig.grools.logic.TruthValue;
import fr.cea.ig.grools.fact.RelationImpl;
import fr.cea.ig.grools.fact.Relation;
import fr.cea.ig.grools.fact.RelationType;
import lombok.NonNull;

/**
 * Cases
 */
class Cases {

    static void case1( @NonNull final Reasoner reasoner ) throws Exception {
        PriorKnowledge pk1 = PriorKnowledgeImpl.builder( )
                                               .name( "pk1" )
                                               .build( );
        Observation o1 = ObservationImpl.builder( )
                                        .name( "o" )
                                        .type( ObservationType.COMPUTATION )
                                        .truthValue( TruthValue.t )
                                        .build( );
        Relation o1ToPk1 = new RelationImpl( o1, pk1, o1.getType( ) );
        reasoner.insert( pk1, o1, o1ToPk1 );

        reasoner.reasoning( );

    }

    static void case2( @NonNull final Reasoner reasoner ) throws Exception {
        PriorKnowledge pk1 = PriorKnowledgeImpl.builder( )
                                               .name( "pk1" )
                                               .build( );
        Observation o1 = ObservationImpl.builder( )
                                        .name( "o" )
                                        .type( ObservationType.CURATION )
                                        .build( );
        Relation o1ToPk1 = new RelationImpl( o1, pk1, o1.getType( ) );
        reasoner.insert( pk1, o1, o1ToPk1 );

        reasoner.reasoning( );
    }

    static void case3( @NonNull final Reasoner reasoner ) throws Exception {
        PriorKnowledge pk1 = PriorKnowledgeImpl.builder( )
                                               .name( "pk1" )
                                               .build( );
        Observation o1 = ObservationImpl.builder( )
                                        .name( "o1" )
                                        .type( ObservationType.CURATION )
                                        .truthValue( TruthValue.f )
                                        .build( );
        Relation o1ToPk1 = new RelationImpl( o1, pk1, o1.getType( ) );
        reasoner.insert( pk1, o1, o1ToPk1 );

        reasoner.reasoning( );

        Observation o2 = ObservationImpl.builder( )
                                        .name( "o2" )
                                        .type( ObservationType.COMPUTATION )
                                        .build( );
        Relation o2ToPk1 = new RelationImpl( o2, pk1, o2.getType( ) );
        reasoner.insert( o2, o2ToPk1 );

        reasoner.reasoning( );

    }

    static void case4( @NonNull final Reasoner reasoner ) throws Exception {
        PriorKnowledge pk1 = PriorKnowledgeImpl.builder( )
                                               .name( "pk1" )
                                               .build( );
        Observation o1 = ObservationImpl.builder( )
                                        .name( "o1" )
                                        .type( ObservationType.CURATION )
                                        .build( );
        Relation o1ToPk1 = new RelationImpl( o1, pk1, o1.getType( ) );

        Observation o2 = ObservationImpl.builder( )
                                        .name( "o2" )
                                        .type( ObservationType.COMPUTATION )
                                        .build( );
        Relation o2ToPk1 = new RelationImpl( o2, pk1, o2.getType( ) );
        reasoner.insert( pk1, o1, o1ToPk1, o2, o2ToPk1 );

        reasoner.reasoning( );

        reasoner.delete( o1ToPk1 );
        reasoner.reasoning( );
    }

    static void case5( @NonNull final Reasoner reasoner ) throws Exception {
        PriorKnowledge pk1 = PriorKnowledgeImpl.builder( )
                                               .name( "pk1" )
                                               .build( );
        PriorKnowledge pk11 = PriorKnowledgeImpl.builder( )
                                                .name( "pk11" )
                                                .build( );
        Relation r11 = new RelationImpl( pk11, pk1, RelationType.PART );
        Observation o11 = ObservationImpl.builder( )
                                         .name( "o11" )
                                         .type( ObservationType.COMPUTATION )
                                         .truthValue( TruthValue.t )
                                         .build( );
        Relation o11ToPk11 = new RelationImpl( o11, pk11, o11.getType( ) );
        reasoner.insert( pk1, o11, o11ToPk11, pk11, r11 );

        reasoner.reasoning( );
    }

    static void case6( @NonNull final Reasoner reasoner ) throws Exception {
        PriorKnowledge pk1 = PriorKnowledgeImpl.builder( )
                                               .name( "pk1" )
                                               .build( );
        PriorKnowledge pk11 = PriorKnowledgeImpl.builder( )
                                                .name( "pk11" )
                                                .build( );
        PriorKnowledge pk12 = PriorKnowledgeImpl.builder( )
                                                .name( "pk12" )
                                                .build( );
        Relation r11 = new RelationImpl( pk11, pk1, RelationType.PART );
        Relation r12 = new RelationImpl( pk12, pk1, RelationType.PART );
        Observation o11 = ObservationImpl.builder( )
                                         .name( "o11" )
                                         .type( ObservationType.COMPUTATION )
                                         .truthValue( TruthValue.t )
                                         .build( );
        Relation o11ToPk11 = new RelationImpl( o11, pk11, o11.getType( ) );
        reasoner.insert( pk1, o11, o11ToPk11, pk11, r11, pk12, r12 );

        reasoner.reasoning( );
    }

    static void case7( @NonNull final Reasoner reasoner ) throws Exception {
        PriorKnowledge pk1 = PriorKnowledgeImpl.builder( )
                                               .name( "pk1" )
                                               .build( );
        Observation o1 = ObservationImpl.builder( )
                                        .name( "o1" )
                                        .type( ObservationType.CURATION )
                                        .build( );
        Relation o1ToPk1 = new RelationImpl( o1, pk1, o1.getType( ) );

        Observation o2 = ObservationImpl.builder( )
                                        .name( "o2" )
                                        .truthValue( TruthValue.f )
                                        .type( ObservationType.COMPUTATION )
                                        .build( );
        Relation o2ToPk1 = new RelationImpl( o2, pk1, o2.getType( ) );
        reasoner.insert( pk1, o1, o1ToPk1, o2, o2ToPk1 );

        reasoner.reasoning( );
    }

    static void case8( @NonNull final Reasoner reasoner ) {

        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final Relation relationBA = new RelationImpl( nB, nA, RelationType.PART );
        final Relation relationCA = new RelationImpl( nC, nA, RelationType.PART );
        reasoner.insert( nA, nB, nC, relationBA, relationCA );

        final Observation oB = ObservationImpl.builder( )
                                              .type( ObservationType.COMPUTATION )
                                              .name( "oB" )
                                              .truthValue( TruthValue.t )
                                              .build( );
        final Relation oToB = new RelationImpl( oB, nB, oB.getType( ) );

        final Observation oC = ObservationImpl.builder( )
                                              .type( ObservationType.COMPUTATION )
                                              .name( "oC" )
                                              .truthValue( TruthValue.t )
                                              .build( );
        final Relation oToC = new RelationImpl( oC, nC, oC.getType( ) );

        reasoner.insert( oB, oC, oToB, oToC );

        reasoner.reasoning( );
    }

    static void case9( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final Relation relationBA = new RelationImpl( nB, nA, RelationType.PART );
        final Relation relationCA = new RelationImpl( nC, nA, RelationType.PART );
        reasoner.insert( nA, nB, nC, relationBA, relationCA );

        final Observation oB = ObservationImpl.builder( )
                                              .type( ObservationType.COMPUTATION )
                                              .name( "oB" )
                                              .truthValue( TruthValue.f )
                                              .build( );
        final Relation oToB = new RelationImpl( oB, nB, oB.getType( ) );

        final Observation oC = ObservationImpl.builder( )
                                              .type( ObservationType.COMPUTATION )
                                              .name( "oC" )
                                              .truthValue( TruthValue.f )
                                              .build( );
        final Relation oToC = new RelationImpl( oC, nC, oC.getType( ) );

        reasoner.insert( oB, oC, oToB, oToC );

        reasoner.reasoning( );
    }

    static void case10( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final Relation relationBA = new RelationImpl( nB, nA, RelationType.PART );
        final Relation relationCA = new RelationImpl( nC, nA, RelationType.PART );
        reasoner.insert( nA, nB, nC, relationBA, relationCA );

        Observation oB = ObservationImpl.builder( ).type( ObservationType.COMPUTATION )
                                        .name( "oB" )
                                        .truthValue( TruthValue.t )
                                        .build( );

        Observation oC = ObservationImpl.builder( ).type( ObservationType.COMPUTATION )
                                        .name( "oC" )
                                        .truthValue( TruthValue.f )
                                        .build( );
        final Relation oToB = new RelationImpl( oB, nB, oB.getType( ) );
        final Relation oToC = new RelationImpl( oC, nC, oC.getType( ) );

        reasoner.insert( oB, oC, oToB, oToC );

        reasoner.reasoning( );
    }

    static void case11( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final Relation relationBA = new RelationImpl( nB, nA, RelationType.PART );
        final Relation relationCA = new RelationImpl( nC, nA, RelationType.PART );
        reasoner.insert( nA, nB, nC, relationBA, relationCA );

        final Observation oB1 = ObservationImpl.builder( ).type( ObservationType.COMPUTATION )
                                               .name( "oB1" )
                                               .truthValue( TruthValue.t )
                                               .build( );

        final Observation oB2 = ObservationImpl.builder( ).type( ObservationType.COMPUTATION )
                                               .name( "oB2" )
                                               .truthValue( TruthValue.f )
                                               .build( );

        final Observation oC = ObservationImpl.builder( ).type( ObservationType.COMPUTATION )
                                              .name( "oC" )
                                              .truthValue( TruthValue.f )
                                              .build( );
        final Relation oB1ToB = new RelationImpl( oB1, nB, oB1.getType( ) );
        final Relation oB2ToB = new RelationImpl( oB2, nB, oB2.getType( ) );
        final Relation oCToC = new RelationImpl( oC, nC, oC.getType( ) );
        reasoner.insert( oB1, oB2, oC, oB1ToB, oB2ToB, oCToC );

        final Relation obsB1RelConB = new RelationImpl( oB1, nB, RelationType.PREDICTION );
        final Relation obsB2RelConB = new RelationImpl( oB2, nB, RelationType.PREDICTION );
        final Relation obsCRelConC = new RelationImpl( oC, nC, RelationType.PREDICTION );

        reasoner.insert( oB1, oB2, oC, obsB1RelConB, obsB2RelConB, obsCRelConC );

        reasoner.reasoning( );

    }

    static void case12( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final PriorKnowledge nD = PriorKnowledgeImpl.builder( ).name( "D" ).build( );
        final PriorKnowledge nE = PriorKnowledgeImpl.builder( ).name( "E" ).build( );
        final PriorKnowledge nF = PriorKnowledgeImpl.builder( ).name( "F" ).build( );

        final Relation relationBA = new RelationImpl( nB, nA, RelationType.SUBTYPE );
        final Relation relationCA = new RelationImpl( nC, nA, RelationType.SUBTYPE );
        final Relation relationDB = new RelationImpl( nD, nB, RelationType.PART );
        final Relation relationEB = new RelationImpl( nE, nB, RelationType.PART );
        final Relation relationEC = new RelationImpl( nE, nC, RelationType.PART );
        final Relation relationFC = new RelationImpl( nF, nC, RelationType.PART );


        reasoner.insert( nA, nB, nC, nD, nE, nF, relationBA, relationCA, relationDB, relationEB, relationEC, relationFC );

        final Observation oB1 = ObservationImpl.builder( )
                                               .type( ObservationType.COMPUTATION )
                                               .name( "oB1" )
                                               .truthValue( TruthValue.t )
                                               .build( );

        final Observation oD1 = ObservationImpl.builder( )
                                               .type( ObservationType.COMPUTATION )
                                               .name( "oD1" )
                                               .truthValue( TruthValue.f )
                                               .build( );

        final Observation oE1 = ObservationImpl.builder( )
                                               .type( ObservationType.COMPUTATION )
                                               .name( "oE" )
                                               .truthValue( TruthValue.f )
                                               .build( );

        final Observation oF1 = ObservationImpl.builder( )
                                               .type( ObservationType.COMPUTATION )
                                               .name( "oF" )
                                               .truthValue( TruthValue.t )
                                               .build( );
        final Relation oB1ToB = new RelationImpl( oB1, nB, oB1.getType( ) );
        final Relation oD1ToD = new RelationImpl( oD1, nD, oD1.getType( ) );
        final Relation oE1ToE = new RelationImpl( oE1, nE, oE1.getType( ) );
        final Relation oF1ToF = new RelationImpl( oF1, nF, oF1.getType( ) );
        reasoner.insert( oB1, oD1, oE1, oF1, oB1ToB, oD1ToD, oE1ToE, oF1ToF );

        reasoner.reasoning( );

    }

    static void case13( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final PriorKnowledge nD = PriorKnowledgeImpl.builder( ).name( "D" ).build( );
        final PriorKnowledge nE = PriorKnowledgeImpl.builder( ).name( "E" ).build( );
        final PriorKnowledge nF = PriorKnowledgeImpl.builder( ).name( "F" ).build( );

        final Relation relationBA = new RelationImpl( nB, nA, RelationType.SUBTYPE );
        final Relation relationCA = new RelationImpl( nC, nA, RelationType.SUBTYPE );
        final Relation relationDB = new RelationImpl( nD, nB, RelationType.PART );
        final Relation relationEB = new RelationImpl( nE, nB, RelationType.PART );
        final Relation relationEC = new RelationImpl( nE, nC, RelationType.PART );
        final Relation relationFC = new RelationImpl( nF, nC, RelationType.PART );


        reasoner.insert( nA, nB, nC, nD, nE, nF, relationBA, relationCA, relationDB, relationEB, relationEC, relationFC );

        final Observation oD1 = ObservationImpl.builder( )
                                               .type( ObservationType.COMPUTATION )
                                               .name( "oD1" )
                                               .truthValue( TruthValue.f )
                                               .build( );

        final Observation oE1 = ObservationImpl.builder( )
                                               .type( ObservationType.COMPUTATION )
                                               .name( "oE" )
                                               .truthValue( TruthValue.f )
                                               .build( );

        final Observation oF1 = ObservationImpl.builder( )
                                               .type( ObservationType.COMPUTATION )
                                               .name( "oF" )
                                               .truthValue( TruthValue.t )
                                               .build( );
        final Relation oD1ToD = new RelationImpl( oD1, nD );
        final Relation oE1ToE = new RelationImpl( oE1, nE );
        final Relation oF1ToF = new RelationImpl( oF1, nF );
        reasoner.insert( oD1, oE1, oF1, oD1ToD, oE1ToE, oF1ToF );

        reasoner.insert( oD1, oE1, oF1 );

        reasoner.reasoning( );

    }

    static void case14( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final PriorKnowledge nD = PriorKnowledgeImpl.builder( ).name( "D" ).build( );
        final PriorKnowledge nE = PriorKnowledgeImpl.builder( ).name( "E" ).build( );
        final PriorKnowledge nF = PriorKnowledgeImpl.builder( ).name( "F" ).build( );

        final Relation relationBA = new RelationImpl( nB, nA, RelationType.SUBTYPE );
        final Relation relationCA = new RelationImpl( nC, nA, RelationType.SUBTYPE );
        final Relation relationDB = new RelationImpl( nD, nB, RelationType.PART );
        final Relation relationEB = new RelationImpl( nE, nB, RelationType.PART );
        final Relation relationEC = new RelationImpl( nE, nC, RelationType.PART );
        final Relation relationFC = new RelationImpl( nF, nC, RelationType.PART );


        reasoner.insert( nA, nB, nC, nD, nE, nF, relationBA, relationCA, relationDB, relationEB, relationEC, relationFC );

        Observation opD1 = ObservationImpl.builder( )
                                          .type( ObservationType.COMPUTATION )
                                          .name( "opD1" )
                                          .truthValue( TruthValue.f )
                                          .build( );

        Observation opE1 = ObservationImpl.builder( )
                                          .type( ObservationType.COMPUTATION )
                                          .name( "opE" )
                                          .truthValue( TruthValue.f )
                                          .build( );

        Observation opF1 = ObservationImpl.builder( )
                                          .type( ObservationType.COMPUTATION )
                                          .name( "opF" )
                                          .truthValue( TruthValue.t )
                                          .build( );


        Observation oeA1 = ObservationImpl.builder( )
                                          .type( ObservationType.EXPERIMENTATION )
                                          .name( "oeA1" )
                                          .truthValue( TruthValue.f )
                                          .build( );
        final Relation oD1ToD = new RelationImpl( opD1, nD );
        final Relation oE1ToE = new RelationImpl( opE1, nE );
        final Relation oF1ToF = new RelationImpl( opF1, nF );
        final Relation oeA1ToA = new RelationImpl( oeA1, nA );
        reasoner.insert( opD1, opE1, opF1, oeA1, oD1ToD, oE1ToE, oF1ToF, oeA1ToA );


        reasoner.reasoning( );

    }

    static void case15( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final PriorKnowledge nD = PriorKnowledgeImpl.builder( ).name( "D" ).build( );
        final PriorKnowledge nE = PriorKnowledgeImpl.builder( ).name( "E" ).build( );
        final PriorKnowledge nF = PriorKnowledgeImpl.builder( ).name( "F" ).build( );

        final Relation relationBA = new RelationImpl( nB, nA, RelationType.SUBTYPE );
        final Relation relationCA = new RelationImpl( nC, nA, RelationType.SUBTYPE );
        final Relation relationDB = new RelationImpl( nD, nB, RelationType.PART );
        final Relation relationEB = new RelationImpl( nE, nB, RelationType.PART );
        final Relation relationEC = new RelationImpl( nE, nC, RelationType.PART );
        final Relation relationFC = new RelationImpl( nF, nC, RelationType.PART );


        reasoner.insert( nA, nB, nC, nD, nE, nF, relationBA, relationCA, relationDB, relationEB, relationEC, relationFC );

        Observation opD1 = ObservationImpl.builder( )
                                          .type( ObservationType.COMPUTATION )
                                          .name( "opD1" )
                                          .truthValue( TruthValue.f )
                                          .build( );

        Observation opE1 = ObservationImpl.builder( )
                                          .type( ObservationType.COMPUTATION )
                                          .name( "opE" )
                                          .truthValue( TruthValue.t )
                                          .build( );

        Observation opF1 = ObservationImpl.builder( )
                                          .type( ObservationType.COMPUTATION )
                                          .name( "opF" )
                                          .truthValue( TruthValue.t )
                                          .build( );


        Observation oeA1 = ObservationImpl.builder( )
                                          .type( ObservationType.EXPERIMENTATION )
                                          .name( "oeA1" )
                                          .truthValue( TruthValue.f )
                                          .build( );
        final Relation oD1ToD = new RelationImpl( opD1, nD );
        final Relation oE1ToE = new RelationImpl( opE1, nE );
        final Relation oF1ToF = new RelationImpl( opF1, nF );
        final Relation oeA1ToA = new RelationImpl( oeA1, nA );
        reasoner.insert( opD1, opE1, opF1, oeA1, oD1ToD, oE1ToE, oF1ToF, oeA1ToA );

        reasoner.reasoning( );

    }

    static void case16( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final PriorKnowledge nD = PriorKnowledgeImpl.builder( ).name( "D" ).build( );
        final PriorKnowledge nE = PriorKnowledgeImpl.builder( ).name( "E" ).build( );
        final PriorKnowledge nF = PriorKnowledgeImpl.builder( ).name( "F" ).build( );

        final Relation relationBA = new RelationImpl( nB, nA, RelationType.SUBTYPE );
        final Relation relationCA = new RelationImpl( nC, nA, RelationType.SUBTYPE );
        final Relation relationDB = new RelationImpl( nD, nB, RelationType.PART );
        final Relation relationEB = new RelationImpl( nE, nB, RelationType.PART );
        final Relation relationEC = new RelationImpl( nE, nC, RelationType.PART );
        final Relation relationFC = new RelationImpl( nF, nC, RelationType.PART );


        reasoner.insert( nA, nB, nC, nD, nE, nF, relationBA, relationCA, relationDB, relationEB, relationEC, relationFC );

        Observation opD1 = ObservationImpl.builder( )
                                          .type( ObservationType.COMPUTATION )
                                          .name( "opD1" )
                                          .truthValue( TruthValue.f )
                                          .build( );

        Observation opF1 = ObservationImpl.builder( )
                                          .type( ObservationType.COMPUTATION )
                                          .name( "opF" )
                                          .truthValue( TruthValue.t )
                                          .build( );


        Observation oeA1 = ObservationImpl.builder( )
                                          .type( ObservationType.EXPERIMENTATION )
                                          .name( "oeA1" )
                                          .truthValue( TruthValue.f )
                                          .build( );
        final Relation oD1ToD = new RelationImpl( opD1, nD );
        final Relation oF1ToF = new RelationImpl( opF1, nF );
        final Relation oeA1ToA = new RelationImpl( oeA1, nA );
        reasoner.insert( opD1, opF1, oeA1, oD1ToD, oF1ToF, oeA1ToA );


        reasoner.reasoning( );

    }

    static void case17( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final PriorKnowledge nD = PriorKnowledgeImpl.builder( ).name( "D" ).build( );
        final PriorKnowledge nE = PriorKnowledgeImpl.builder( ).name( "E" ).build( );
        final PriorKnowledge nF = PriorKnowledgeImpl.builder( ).name( "F" ).build( );

        final Relation relationBA = new RelationImpl( nB, nA, RelationType.SUBTYPE );
        final Relation relationCA = new RelationImpl( nC, nA, RelationType.SUBTYPE );
        final Relation relationDB = new RelationImpl( nD, nB, RelationType.PART );
        final Relation relationEB = new RelationImpl( nE, nB, RelationType.PART );
        final Relation relationEC = new RelationImpl( nE, nC, RelationType.PART );
        final Relation relationFC = new RelationImpl( nF, nC, RelationType.PART );


        reasoner.insert( nA, nB, nC, nD, nE, nF, relationBA, relationCA, relationDB, relationEB, relationEC, relationFC );

        Observation opD1 = ObservationImpl.builder( )
                                          .type( ObservationType.COMPUTATION )
                                          .name( "opD1" )
                                          .truthValue( TruthValue.f )
                                          .build( );

        Observation opF1 = ObservationImpl.builder( )
                                          .type( ObservationType.COMPUTATION )
                                          .name( "opF" )
                                          .truthValue( TruthValue.t )
                                          .build( );


        Observation oeA1 = ObservationImpl.builder( )
                                          .type( ObservationType.EXPERIMENTATION )
                                          .name( "oeA1" )
                                          .truthValue( TruthValue.t )
                                          .build( );
        final Relation oD1ToD = new RelationImpl( opD1, nD );
        final Relation oF1ToF = new RelationImpl( opF1, nF );
        final Relation oeA1ToA = new RelationImpl( oeA1, nA );
        reasoner.insert( opD1, opF1, oeA1, oD1ToD, oF1ToF, oeA1ToA );


        reasoner.reasoning( );

    }

    static void case18( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).isDispensable( true ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final Relation relationBA = new RelationImpl( nB, nA, RelationType.PART );
        final Relation relationCA = new RelationImpl( nC, nA, RelationType.PART );
        final Observation opB1 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opB1" )
                                                .truthValue( TruthValue.f )
                                                .build( );
        final Observation opC1 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opC1" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Relation opB1ToB = new RelationImpl( opB1, nB );
        final Relation opC1ToC = new RelationImpl( opC1, nC );
        reasoner.insert( nA, nB, nC, relationBA, relationCA, opB1, opC1, opB1ToB, opC1ToC );

        reasoner.reasoning( );

    }

    static void case19( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final PriorKnowledge nD = PriorKnowledgeImpl.builder( ).name( "D" ).build( );
        final PriorKnowledge nE = PriorKnowledgeImpl.builder( ).name( "E" ).isDispensable( true ).build( );
        final PriorKnowledge nF = PriorKnowledgeImpl.builder( ).name( "F" ).build( );

        final Relation relationBA = new RelationImpl( nB, nA, RelationType.SUBTYPE );
        final Relation relationCA = new RelationImpl( nC, nA, RelationType.SUBTYPE );
        final Relation relationDB = new RelationImpl( nD, nB, RelationType.PART );
        final Relation relationEB = new RelationImpl( nE, nB, RelationType.PART );
        final Relation relationEC = new RelationImpl( nE, nC, RelationType.PART );
        final Relation relationFC = new RelationImpl( nF, nC, RelationType.PART );


        reasoner.insert( nA, nB, nC, nD, nE, nF, relationBA, relationCA, relationDB, relationEB, relationEC, relationFC );

        final Observation opD1 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opD1" )
                                                .truthValue( TruthValue.f )
                                                .build( );

        final Observation opF1 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opF" )
                                                .truthValue( TruthValue.t )
                                                .build( );


        final Observation oeA1 = ObservationImpl.builder( )
                                                .type( ObservationType.EXPERIMENTATION )
                                                .name( "oeA1" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Relation oD1ToD = new RelationImpl( opD1, nD );
        final Relation oF1ToF = new RelationImpl( opF1, nF );
        final Relation oeA1ToA = new RelationImpl( oeA1, nA );
        reasoner.insert( opD1, opF1, oeA1, oD1ToD, oF1ToF, oeA1ToA );


        reasoner.reasoning( );

    }


    static void case20( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final PriorKnowledge nD = PriorKnowledgeImpl.builder( ).name( "D" ).build( );
        final PriorKnowledge nE = PriorKnowledgeImpl.builder( ).name( "E" ).build( );
        final PriorKnowledge nF = PriorKnowledgeImpl.builder( ).name( "F" ).build( );
        final PriorKnowledge nG = PriorKnowledgeImpl.builder( ).name( "G" ).build( );
        final PriorKnowledge nH = PriorKnowledgeImpl.builder( ).name( "H" ).build( );
        final PriorKnowledge nI = PriorKnowledgeImpl.builder( ).name( "I" ).build( );
        final PriorKnowledge nJ = PriorKnowledgeImpl.builder( ).name( "J" ).build( );
        final PriorKnowledge nK = PriorKnowledgeImpl.builder( ).name( "K" ).build( );
        final PriorKnowledge nL = PriorKnowledgeImpl.builder( ).name( "L" ).build( );

        reasoner.insert( nA, nB, nC, nD, nE, nF, nG, nH, nI, nJ, nK, nL );

        final Relation relationBA = new RelationImpl( nB, nA, RelationType.SUBTYPE );
        final Relation relationCA = new RelationImpl( nC, nA, RelationType.SUBTYPE );

        reasoner.insert( relationBA, relationCA );

        final Relation relationDB = new RelationImpl( nD, nB, RelationType.PART );
        final Relation relationEB = new RelationImpl( nE, nB, RelationType.PART );
        final Relation relationFB = new RelationImpl( nF, nB, RelationType.PART );

        reasoner.insert( relationDB, relationEB, relationFB );

        final Relation relationDC = new RelationImpl( nD, nC, RelationType.PART );
        final Relation relationFC = new RelationImpl( nF, nC, RelationType.PART );
        final Relation relationGC = new RelationImpl( nG, nC, RelationType.PART );

        reasoner.insert( relationDC, relationFC, relationGC );


        final Relation relationHD = new RelationImpl( nH, nD, RelationType.PART );
        final Relation relationIE = new RelationImpl( nI, nE, RelationType.PART );
        final Relation relationJE = new RelationImpl( nJ, nE, RelationType.PART );
        final Relation relationKF = new RelationImpl( nK, nF, RelationType.PART );
        final Relation relationLG = new RelationImpl( nL, nG, RelationType.PART );


        reasoner.insert( relationHD, relationIE, relationJE, relationKF, relationLG );


        final Observation opH1 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opH1" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Observation opI1 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opI1" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Observation opK1 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opK1" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Observation oeA1 = ObservationImpl.builder( )
                                                .type( ObservationType.EXPERIMENTATION )
                                                .name( "oeA1" )
                                                .truthValue( TruthValue.t )
                                                .build( );

        reasoner.insert( opH1, opI1, opK1, oeA1 );

        final Relation opH1ToH = new RelationImpl( opH1, nH );
        final Relation opI1ToI = new RelationImpl( opI1, nI );
        final Relation opK1ToK = new RelationImpl( opK1, nK );
        final Relation oeA1ToA = new RelationImpl( oeA1, nA );

        reasoner.insert( opH1ToH, opI1ToI, opK1ToK, oeA1ToA );


        reasoner.reasoning( );
    }

    static void case21( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final PriorKnowledge nD = PriorKnowledgeImpl.builder( ).name( "D" ).build( );
        final PriorKnowledge nE = PriorKnowledgeImpl.builder( ).name( "E" ).build( );
        final PriorKnowledge nF = PriorKnowledgeImpl.builder( ).name( "F" ).build( );

        final Relation relationBA = new RelationImpl( nB, nA, RelationType.SUBTYPE );
        final Relation relationCA = new RelationImpl( nC, nA, RelationType.SUBTYPE );
        final Relation relationDB = new RelationImpl( nD, nB, RelationType.PART );
        final Relation relationEB = new RelationImpl( nE, nB, RelationType.PART );
        final Relation relationEC = new RelationImpl( nE, nC, RelationType.PART );
        final Relation relationFC = new RelationImpl( nF, nC, RelationType.PART );


        reasoner.insert( nA, nB, nC, nD, nE, nF, relationBA, relationCA, relationDB, relationEB, relationEC, relationFC );

        Observation opD1 = ObservationImpl.builder( )
                                          .type( ObservationType.COMPUTATION )
                                          .name( "opD1" )
                                          .truthValue( TruthValue.f )
                                          .build( );

        Observation opF1 = ObservationImpl.builder( )
                                          .type( ObservationType.COMPUTATION )
                                          .name( "opF" )
                                          .truthValue( TruthValue.t )
                                          .build( );


        Observation oeC1 = ObservationImpl.builder( )
                                          .type( ObservationType.EXPERIMENTATION )
                                          .name( "oeC1" )
                                          .truthValue( TruthValue.t )
                                          .build( );
        final Relation oD1ToD = new RelationImpl( opD1, nD );
        final Relation oF1ToF = new RelationImpl( opF1, nF );
        final Relation oeC1ToC = new RelationImpl( oeC1, nC );
        reasoner.insert( opD1, opF1, oeC1, oD1ToD, oF1ToF, oeC1ToC );


        reasoner.reasoning( );

    }

    static void case22( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final PriorKnowledge nD = PriorKnowledgeImpl.builder( ).name( "D" ).build( );
        final PriorKnowledge nE = PriorKnowledgeImpl.builder( ).name( "E" ).build( );
        final PriorKnowledge nF = PriorKnowledgeImpl.builder( ).name( "F" ).build( );

        final Relation relationBA = new RelationImpl( nB, nA, RelationType.SUBTYPE );
        final Relation relationCA = new RelationImpl( nC, nA, RelationType.SUBTYPE );
        final Relation relationDB = new RelationImpl( nD, nB, RelationType.PART );
        final Relation relationEB = new RelationImpl( nE, nB, RelationType.PART );
        final Relation relationEC = new RelationImpl( nE, nC, RelationType.PART );
        final Relation relationFC = new RelationImpl( nF, nC, RelationType.PART );


        reasoner.insert( nA, nB, nC, nD, nE, nF, relationBA, relationCA, relationDB, relationEB, relationEC, relationFC );

        Observation opD1 = ObservationImpl.builder( )
                                          .type( ObservationType.COMPUTATION )
                                          .name( "opD1" )
                                          .truthValue( TruthValue.f )
                                          .build( );

        Observation opF1 = ObservationImpl.builder( )
                                          .type( ObservationType.COMPUTATION )
                                          .name( "opF" )
                                          .truthValue( TruthValue.t )
                                          .build( );


        Observation oeB1 = ObservationImpl.builder( )
                                          .type( ObservationType.EXPERIMENTATION )
                                          .name( "oeB1" )
                                          .truthValue( TruthValue.f )
                                          .build( );
        final Relation oD1ToD = new RelationImpl( opD1, nD );
        final Relation oF1ToF = new RelationImpl( opF1, nF );
        final Relation oeB1ToB = new RelationImpl( oeB1, nB );
        reasoner.insert( opD1, opF1, oeB1, oD1ToD, oF1ToF, oeB1ToB );


        reasoner.reasoning( );

    }

    static void case23( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final Relation relationCA = new RelationImpl( nC, nA, RelationType.SUBTYPE );
        final Relation relationCB = new RelationImpl( nC, nB, RelationType.SUBTYPE );

        final Observation opC1 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opC" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Observation oeB1 = ObservationImpl.builder( )
                                                .type( ObservationType.EXPERIMENTATION )
                                                .name( "oeB" )
                                                .truthValue( TruthValue.t )
                                                .build( );

        final Relation opC1ToC = new RelationImpl( opC1, nC );
        final Relation oeB1ToB = new RelationImpl( oeB1, nB );
        reasoner.insert( nA, nB, nC, relationCA, relationCB, opC1, oeB1, opC1ToC, oeB1ToB );


        reasoner.reasoning( );
    }

    static void case24( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final PriorKnowledge nD = PriorKnowledgeImpl.builder( ).name( "D" ).build( );
        final Relation relationCA = new RelationImpl( nC, nA, RelationType.PART );
        final Relation relationCB = new RelationImpl( nC, nB, RelationType.PART );
        final Relation relationDB = new RelationImpl( nD, nB, RelationType.PART );
        final Observation opB1 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opB1" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Observation opB2 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opB2" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Observation opC1 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opC1" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Observation opC2 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opC2" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Observation opC3 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opC3" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Observation oeA1 = ObservationImpl.builder( )
                                                .type( ObservationType.EXPERIMENTATION )
                                                .name( "oeA" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Relation opB1ToB = new RelationImpl( opB1, nB );
        final Relation opB2ToB = new RelationImpl( opB2, nB );
        final Relation opC1ToC = new RelationImpl( opC1, nC );
        final Relation opC2ToC = new RelationImpl( opC2, nC );
        final Relation opC3ToC = new RelationImpl( opC3, nC );
        final Relation oeA1ToA = new RelationImpl( oeA1, nA );
        reasoner.insert( nA, nB, nC, nD, relationCA, relationCB, relationDB, opB1, opB1ToB, opB2, opB2ToB, opC1, opC1ToC, opC2, opC2ToC, opC3, opC3ToC, oeA1, oeA1ToA );
        reasoner.reasoning( );
    }

    static void case25( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final PriorKnowledge nD = PriorKnowledgeImpl.builder( ).name( "D" ).build( );
        final Relation relationCA = new RelationImpl( nB, nA, RelationType.SUBTYPE );
        final Relation relationCB = new RelationImpl( nC, nA, RelationType.SUBTYPE );
        final Relation relationDB = new RelationImpl( nD, nA, RelationType.SUBTYPE );
        reasoner.insert( nA, nB, nC, nD, relationCA, relationCB, relationDB );
        final PriorKnowledge nE = PriorKnowledgeImpl.builder( ).name( "E" ).build( );
        final PriorKnowledge nF = PriorKnowledgeImpl.builder( ).name( "F" ).build( );
        final PriorKnowledge nG = PriorKnowledgeImpl.builder( ).name( "G" ).build( );
        final PriorKnowledge nH = PriorKnowledgeImpl.builder( ).name( "H" ).build( );
        final PriorKnowledge nI = PriorKnowledgeImpl.builder( ).name( "I" ).build( );
        final PriorKnowledge nJ = PriorKnowledgeImpl.builder( ).name( "J" ).build( );
        final PriorKnowledge nK = PriorKnowledgeImpl.builder( ).name( "K" ).build( );
        reasoner.insert( nE, nF, nG, nH, nI, nJ, nK );
        final Relation relationEB = new RelationImpl( nE, nB, RelationType.PART );
        final Relation relationFB = new RelationImpl( nF, nB, RelationType.PART );
        final Relation relationFC = new RelationImpl( nF, nC, RelationType.PART );
        final Relation relationFD = new RelationImpl( nF, nD, RelationType.PART );
        final Relation relationGC = new RelationImpl( nG, nC, RelationType.PART );
        final Relation relationHB = new RelationImpl( nH, nB, RelationType.PART );
        final Relation relationHC = new RelationImpl( nH, nC, RelationType.PART );
        final Relation relationHD = new RelationImpl( nH, nD, RelationType.PART );
        final Relation relationIB = new RelationImpl( nI, nB, RelationType.PART );
        final Relation relationIC = new RelationImpl( nI, nC, RelationType.PART );
        final Relation relationID = new RelationImpl( nI, nD, RelationType.PART );
        final Relation relationJB = new RelationImpl( nJ, nB, RelationType.PART );
        final Relation relationJC = new RelationImpl( nJ, nC, RelationType.PART );
        final Relation relationJD = new RelationImpl( nJ, nD, RelationType.PART );
        final Relation relationKD = new RelationImpl( nK, nD, RelationType.PART );
        reasoner.insert( relationEB, relationFB, relationFC, relationFD, relationGC, relationHB, relationHC, relationHD,
                         relationIB, relationIC, relationID, relationJB, relationJC, relationJD, relationKD );


        final Observation oeA1 = ObservationImpl.builder( )
                                                .type( ObservationType.EXPERIMENTATION )
                                                .name( "opA" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Relation oeA1ToA = new RelationImpl( oeA1, nA );
        reasoner.insert( oeA1, oeA1ToA );
        final Observation opF1 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opF" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Relation opF1ToF = new RelationImpl( opF1, nF );
        reasoner.insert( opF1, opF1ToF );
        final Observation opG1 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opG" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Relation opG1ToG = new RelationImpl( opG1, nG );
        reasoner.insert( opG1, opG1ToG );
        final Observation opH1 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opH" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Relation opH1ToH = new RelationImpl( opH1, nH );
        reasoner.insert( opH1, opH1ToH );
        final Observation opI1 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opI" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Relation opI1ToI = new RelationImpl( opI1, nI );
        reasoner.insert( opI1, opI1ToI );

        reasoner.reasoning( );
    }

    static void case26( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        reasoner.insert( nA );

        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final PriorKnowledge nD = PriorKnowledgeImpl.builder( ).name( "D" ).build( );
        reasoner.insert( nB, nC, nD );

        final Relation bToA = new RelationImpl( nB, nA, RelationType.SUBTYPE );
        final Relation cToA = new RelationImpl( nC, nA, RelationType.SUBTYPE );
        final Relation dToA = new RelationImpl( nD, nA, RelationType.SUBTYPE );
        reasoner.insert( bToA, cToA, dToA );

        final PriorKnowledge nE = PriorKnowledgeImpl.builder( ).name( "E" ).build( );
        final PriorKnowledge nF = PriorKnowledgeImpl.builder( ).name( "F" ).build( );
        reasoner.insert( nE, nF );

        final Relation eToC = new RelationImpl( nE, nC, RelationType.PART );
        final Relation fToD = new RelationImpl( nF, nD, RelationType.PART );
        reasoner.insert( eToC, fToD );

        final PriorKnowledge nE1 = PriorKnowledgeImpl.builder( ).name( "E1" ).build( );
        final PriorKnowledge nE2 = PriorKnowledgeImpl.builder( ).name( "E2" ).build( );
        final PriorKnowledge nE3 = PriorKnowledgeImpl.builder( ).name( "E3" ).build( );
        final PriorKnowledge nE4 = PriorKnowledgeImpl.builder( ).name( "E4" ).build( );
        final PriorKnowledge nE5 = PriorKnowledgeImpl.builder( ).name( "E5" ).build( );
        final PriorKnowledge nE6 = PriorKnowledgeImpl.builder( ).name( "E6" ).build( );
        reasoner.insert( nE1, nE2, nE3, nE4, nE5, nE6 );

        final Relation e1ToE = new RelationImpl( nE1, nE, RelationType.PART );
        final Relation e2ToE = new RelationImpl( nE2, nE, RelationType.PART );
        final Relation e3ToE = new RelationImpl( nE3, nE, RelationType.PART );
        final Relation e4ToE = new RelationImpl( nE4, nE, RelationType.PART );
        final Relation e5ToE = new RelationImpl( nE5, nE, RelationType.PART );
        final Relation e6ToE = new RelationImpl( nE6, nE, RelationType.PART );
        reasoner.insert( e1ToE, e2ToE, e3ToE, e4ToE, e5ToE, e6ToE );

        final PriorKnowledge nF1 = PriorKnowledgeImpl.builder( ).name( "F1" ).build( );
        final PriorKnowledge nF2 = PriorKnowledgeImpl.builder( ).name( "F2" ).build( );
        final PriorKnowledge nF3 = PriorKnowledgeImpl.builder( ).name( "F3" ).build( );
        final PriorKnowledge nF4 = PriorKnowledgeImpl.builder( ).name( "F4" ).build( );
        final PriorKnowledge nF5 = PriorKnowledgeImpl.builder( ).name( "F5" ).build( );
        reasoner.insert( nF1, nF2, nF3, nF4, nF5 );

        final Relation f1ToF = new RelationImpl( nF1, nF, RelationType.PART );
        final Relation f2ToF = new RelationImpl( nF2, nF, RelationType.PART );
        final Relation f3ToF = new RelationImpl( nF3, nF, RelationType.PART );
        final Relation f4ToF = new RelationImpl( nF4, nF, RelationType.PART );
        final Relation f5ToF = new RelationImpl( nF5, nF, RelationType.PART );
        reasoner.insert( f1ToF, f2ToF, f3ToF, f4ToF, f5ToF );

        final PriorKnowledge nF1a = PriorKnowledgeImpl.builder( ).name( "F1a" ).build( );
        final PriorKnowledge nF2a = PriorKnowledgeImpl.builder( ).name( "F2a" ).build( );
        reasoner.insert( nF1a, nF2a );

        final Relation f1aToF1 = new RelationImpl( nF1a, nF1, RelationType.PART );
        final Relation f2aToF2 = new RelationImpl( nF2a, nF2, RelationType.PART );
        reasoner.insert( f1aToF1, f2aToF2 );


        final Observation oeA1 = ObservationImpl.builder( )
                                                .type( ObservationType.EXPERIMENTATION )
                                                .name( "oeA1" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Observation oeA2 = ObservationImpl.builder( )
                                                .type( ObservationType.EXPERIMENTATION )
                                                .name( "oeA2" )
                                                .truthValue( TruthValue.f )
                                                .build( );
        final Relation oeA1ToA = new RelationImpl( oeA1, nA );
        final Relation oeA2ToA = new RelationImpl( oeA2, nA );
        reasoner.insert( oeA1, oeA2, oeA1ToA, oeA2ToA );

        final Observation opF1 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opF1" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Observation opF2 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opF2" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Relation opF1ToF = new RelationImpl( opF1, nF1 );
        final Relation opF2ToF = new RelationImpl( opF2, nF2 );
        reasoner.insert( opF1, opF2, opF1ToF, opF2ToF );

        reasoner.reasoning( );

    }

    static void case27( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final PriorKnowledge nD = PriorKnowledgeImpl.builder( ).name( "D" ).build( );
        reasoner.insert( nA, nB, nC, nD );
        final Relation dToA = new RelationImpl( nD, nA, RelationType.SUBTYPE );
        final Relation dToB = new RelationImpl( nD, nB, RelationType.SUBTYPE );
        final Relation dToC = new RelationImpl( nD, nC, RelationType.SUBTYPE );
        reasoner.insert( dToA, dToB, dToC );
        final Relation cToB = new RelationImpl( nC, nB, RelationType.SUBTYPE );
        reasoner.insert( cToB );
        final Observation oeC1 = ObservationImpl.builder( )
                                                .type( ObservationType.EXPERIMENTATION )
                                                .name( "oeC1" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Observation opD1 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opD1" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        reasoner.insert( oeC1, opD1 );

        final Relation oeC1ToC = new RelationImpl( oeC1, nC );
        final Relation opD1ToD = new RelationImpl( opD1, nD );
        reasoner.insert( oeC1ToC, opD1ToD );
        reasoner.reasoning( );
    }

    static void case28( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        final PriorKnowledge nD = PriorKnowledgeImpl.builder( ).name( "D" ).build( );
        final PriorKnowledge nE = PriorKnowledgeImpl.builder( ).name( "E" ).build( );
        reasoner.insert( nA, nB, nC, nD, nE );
        final Relation dToA = new RelationImpl( nD, nA, RelationType.SUBTYPE );
        final Relation dToB = new RelationImpl( nD, nB, RelationType.SUBTYPE );
        final Relation dToC = new RelationImpl( nD, nC, RelationType.SUBTYPE );
        final Relation bToE = new RelationImpl( nB, nE, RelationType.SUBTYPE );
        final Relation cToB = new RelationImpl( nC, nB, RelationType.SUBTYPE );
        reasoner.insert( dToA, dToB, dToC, bToE, cToB );
        final Observation oeC1 = ObservationImpl.builder( )
                                                .type( ObservationType.EXPERIMENTATION )
                                                .name( "oeC1" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Observation opD1 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opD1" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        final Observation opD2 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opD2" )
                                                .truthValue( TruthValue.f )
                                                .build( );
        final Observation opE1 = ObservationImpl.builder( )
                                                .type( ObservationType.COMPUTATION )
                                                .name( "opE1" )
                                                .truthValue( TruthValue.t )
                                                .build( );
        reasoner.insert( oeC1, opD1, opD2, opE1 );

        final Relation oeC1ToC = new RelationImpl( oeC1, nC );
        final Relation opD1ToD = new RelationImpl( opD1, nD );
        final Relation opD2ToD = new RelationImpl( opD2, nD );
        final Relation opE1ToE = new RelationImpl( opE1, nE );
        reasoner.insert( oeC1ToC, opD1ToD, opD2ToD, opE1ToE );
        reasoner.reasoning( );
    }

    static void case29( @NonNull final Reasoner reasoner ) {
        final PriorKnowledge nA = PriorKnowledgeImpl.builder( ).name( "A" ).build( );
        final PriorKnowledge nB = PriorKnowledgeImpl.builder( ).name( "B" ).build( );
        final PriorKnowledge nC = PriorKnowledgeImpl.builder( ).name( "C" ).build( );
        reasoner.insert( nA, nB, nC );
        final Relation cToA = new RelationImpl( nC, nA, RelationType.SUBTYPE );
        final Relation cToB = new RelationImpl( nC, nB, RelationType.SUBTYPE );
        reasoner.insert( cToA,cToB );
        reasoner.reasoning( );
    }
}