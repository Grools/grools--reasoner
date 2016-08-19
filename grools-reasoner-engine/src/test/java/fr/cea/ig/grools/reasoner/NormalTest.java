package fr.cea.ig.grools.reasoner;

import fr.cea.ig.grools.Mode;
import fr.cea.ig.grools.Reasoner;
import fr.cea.ig.grools.Verbosity;
import fr.cea.ig.grools.fact.Observation;
import fr.cea.ig.grools.fact.ObservationImpl;
import fr.cea.ig.grools.fact.ObservationType;
import fr.cea.ig.grools.fact.PriorKnowledge;
import fr.cea.ig.grools.fact.Relation;
import fr.cea.ig.grools.fact.RelationImpl;
import fr.cea.ig.grools.logic.TruthValue;
import fr.cea.ig.grools.logic.TruthValuePowerSet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * NormalTest
 */
public class NormalTest {
    private Reasoner reasoner;

    @Before
    public void setUp( ) {
        reasoner = new ReasonerImpl( Mode.NORMAL, Verbosity.HIGHT );
    }

    @Test
    public void case1( ) throws Exception {
        Cases.case1( reasoner );
        final PriorKnowledge pk1 = reasoner.getPriorKnowledge( "pk1" );

        assertEquals( TruthValuePowerSet.T, pk1.getPrediction( ) );
        reasoner.close( );
    }

    @Test
    public void case2( ) throws Exception {
        Cases.case2( reasoner );
        final PriorKnowledge pk1 = reasoner.getPriorKnowledge( "pk1" );

        assertEquals( TruthValuePowerSet.T, pk1.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pk1.getExpectation( ) );
        reasoner.close( );
    }

    @Test
    public void case3( ) throws Exception {
        Cases.case3( reasoner );
        final PriorKnowledge pk1 = reasoner.getPriorKnowledge( "pk1" );


        assertEquals( TruthValuePowerSet.B, pk1.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, pk1.getExpectation( ) );
        reasoner.close( );
    }

    @Test
    public void case4( ) throws Exception {
        Cases.case4( reasoner );
        final PriorKnowledge pk1 = reasoner.getPriorKnowledge( "pk1" );
        assertEquals( TruthValuePowerSet.T, pk1.getPrediction( ) );
        assertEquals( TruthValuePowerSet.N, pk1.getExpectation( ) );
        reasoner.close( );
    }

    @Test
    public void case5( ) throws Exception {
        Cases.case5( reasoner );
        final PriorKnowledge pk1 = reasoner.getPriorKnowledge( "pk1" );
        final PriorKnowledge pk11 = reasoner.getPriorKnowledge( "pk11" );

        assertEquals( TruthValuePowerSet.T, pk11.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pk1.getPrediction( ) );
        reasoner.close( );
    }

    @Test
    public void case6( ) throws Exception {
        Cases.case6( reasoner );
        final PriorKnowledge pk1 = reasoner.getPriorKnowledge( "pk1" );
        final PriorKnowledge pk11 = reasoner.getPriorKnowledge( "pk11" );
        final PriorKnowledge pk12 = reasoner.getPriorKnowledge( "pk12" );

        assertEquals( TruthValuePowerSet.T, pk11.getPrediction( ) );
        assertEquals( TruthValuePowerSet.N, pk12.getPrediction( ) );
        assertEquals( TruthValuePowerSet.NT, pk1.getPrediction( ) );
        reasoner.close( );
    }

    @Test
    public void case7( ) throws Exception {
        Cases.case7( reasoner );
        final PriorKnowledge pk1 = reasoner.getPriorKnowledge( "pk1" );

        assertEquals( TruthValuePowerSet.B, pk1.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pk1.getExpectation( ) );
        reasoner.close( );
    }

    @Test
    public void case8( ) throws Exception {
        Cases.case8( reasoner );
        final PriorKnowledge cA = reasoner.getPriorKnowledge( "A" );
        final PriorKnowledge cB = reasoner.getPriorKnowledge( "B" );
        final PriorKnowledge cC = reasoner.getPriorKnowledge( "C" );
        assertEquals( TruthValuePowerSet.T, cB.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, cC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, cA.getPrediction( ) );
        reasoner.close( );
    }

    @Test
    public void case9( ) throws Exception {
        Cases.case9( reasoner );
        final PriorKnowledge cA = reasoner.getPriorKnowledge( "A" );
        final PriorKnowledge cB = reasoner.getPriorKnowledge( "B" );
        final PriorKnowledge cC = reasoner.getPriorKnowledge( "C" );
        assertEquals( TruthValuePowerSet.F, cB.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, cC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, cA.getPrediction( ) );
        reasoner.close( );
    }

    @Test
    public void case10( ) throws Exception {
        Cases.case10( reasoner );
        final PriorKnowledge cA = reasoner.getPriorKnowledge( "A" );
        final PriorKnowledge cB = reasoner.getPriorKnowledge( "B" );
        final PriorKnowledge cC = reasoner.getPriorKnowledge( "C" );
        assertEquals( TruthValuePowerSet.T, cB.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, cC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.TF, cA.getPrediction( ) );
        reasoner.close( );
    }

    @Test
    public void case11( ) throws Exception {
        Cases.case11( reasoner );
        final PriorKnowledge cA = reasoner.getPriorKnowledge( "A" );
        final PriorKnowledge cB = reasoner.getPriorKnowledge( "B" );
        final PriorKnowledge cC = reasoner.getPriorKnowledge( "C" );
        assertEquals( TruthValuePowerSet.B, cB.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, cC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.FB, cA.getPrediction( ) );
        reasoner.close( );
    }

    @Test
    public void case12( ) throws Exception {
        Cases.case12( reasoner );
        final PriorKnowledge cA = reasoner.getPriorKnowledge( "A" );
        final PriorKnowledge cB = reasoner.getPriorKnowledge( "B" );
        final PriorKnowledge cC = reasoner.getPriorKnowledge( "C" );
        final PriorKnowledge cD = reasoner.getPriorKnowledge( "D" );
        final PriorKnowledge cE = reasoner.getPriorKnowledge( "E" );
        final PriorKnowledge cF = reasoner.getPriorKnowledge( "F" );
        assertEquals( TruthValuePowerSet.T, cF.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, cE.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, cD.getPrediction( ) );
        assertEquals( TruthValuePowerSet.TF, cC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.TF, cB.getPrediction( ) );
        assertEquals( TruthValuePowerSet.TF, cA.getPrediction( ) );
        reasoner.close( );
    }


    @Test
    public void case13( ) throws Exception {
        Cases.case13( reasoner );
        final PriorKnowledge cA = reasoner.getPriorKnowledge( "A" );
        final PriorKnowledge cB = reasoner.getPriorKnowledge( "B" );
        final PriorKnowledge cC = reasoner.getPriorKnowledge( "C" );
        final PriorKnowledge cD = reasoner.getPriorKnowledge( "D" );
        final PriorKnowledge cE = reasoner.getPriorKnowledge( "E" );
        final PriorKnowledge cF = reasoner.getPriorKnowledge( "F" );

        assertEquals( TruthValuePowerSet.T, cF.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, cE.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, cD.getPrediction( ) );
        assertEquals( TruthValuePowerSet.TF, cC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, cB.getPrediction( ) );
        assertEquals( TruthValuePowerSet.TF, cA.getPrediction( ) );
        reasoner.close( );
    }


    @Test
    public void case14( ) throws Exception {
        Cases.case14( reasoner );
        final PriorKnowledge cA = reasoner.getPriorKnowledge( "A" );
        final PriorKnowledge cB = reasoner.getPriorKnowledge( "B" );
        final PriorKnowledge cC = reasoner.getPriorKnowledge( "C" );
        final PriorKnowledge cD = reasoner.getPriorKnowledge( "D" );
        final PriorKnowledge cE = reasoner.getPriorKnowledge( "E" );
        final PriorKnowledge cF = reasoner.getPriorKnowledge( "F" );

        assertEquals( TruthValuePowerSet.F, cE.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, cF.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, cD.getPrediction( ) );
        assertEquals( TruthValuePowerSet.TF, cC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, cB.getPrediction( ) );
        assertEquals( TruthValuePowerSet.TF, cA.getPrediction( ) );

        assertEquals( TruthValuePowerSet.F, cA.getExpectation( ) );
        assertEquals( TruthValuePowerSet.F, cB.getExpectation( ) );
        assertEquals( TruthValuePowerSet.F, cC.getExpectation( ) );
        assertEquals( TruthValuePowerSet.F, cD.getExpectation( ) );
        assertEquals( TruthValuePowerSet.F, cE.getExpectation( ) );
        assertEquals( TruthValuePowerSet.F, cF.getExpectation( ) );

        Observation o = ObservationImpl.builder( )
                                       .name( "oD2" )
                                       .truthValue( TruthValue.t )
                                       .type( ObservationType.EXPERIMENTATION )
                                       .build( );

        Relation oD2TocD = new RelationImpl( o, cD, o.getType( ) );

        reasoner.insert( o, oD2TocD );
        reasoner.reasoning( );
        assertEquals( TruthValuePowerSet.TF, cD.getExpectation( ) );


        reasoner.close( );
    }


    @Test
    public void case15( ) throws Exception {
        Cases.case15( reasoner );
        final PriorKnowledge cA = reasoner.getPriorKnowledge( "A" );
        final PriorKnowledge cB = reasoner.getPriorKnowledge( "B" );
        final PriorKnowledge cC = reasoner.getPriorKnowledge( "C" );
        final PriorKnowledge cD = reasoner.getPriorKnowledge( "D" );
        final PriorKnowledge cE = reasoner.getPriorKnowledge( "E" );
        final PriorKnowledge cF = reasoner.getPriorKnowledge( "F" );

        assertEquals( TruthValuePowerSet.T, cF.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, cE.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, cD.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, cC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.TF, cB.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, cA.getPrediction( ) );

        assertEquals( TruthValuePowerSet.F, cA.getExpectation( ) );
        assertEquals( TruthValuePowerSet.F, cB.getExpectation( ) );
        assertEquals( TruthValuePowerSet.F, cC.getExpectation( ) );
        assertEquals( TruthValuePowerSet.F, cD.getExpectation( ) );
        assertEquals( TruthValuePowerSet.F, cE.getExpectation( ) );
        assertEquals( TruthValuePowerSet.F, cF.getExpectation( ) );

        Observation o = ObservationImpl.builder( )
                                       .name( "oD2" )
                                       .truthValue( TruthValue.t )
                                       .type( ObservationType.EXPERIMENTATION )
                                       .build( );
        Relation oToD = new RelationImpl( o, cD, o.getType( ) );
        reasoner.insert( o, oToD );
        reasoner.reasoning( );
        assertEquals( TruthValuePowerSet.F, cD.getPrediction( ) );
        assertEquals( TruthValuePowerSet.TF, cD.getExpectation( ) );


        reasoner.close( );
    }


    @Test
    public void case16( ) throws Exception {
        Cases.case16( reasoner );
        final PriorKnowledge cA = reasoner.getPriorKnowledge( "A" );
        final PriorKnowledge cB = reasoner.getPriorKnowledge( "B" );
        final PriorKnowledge cC = reasoner.getPriorKnowledge( "C" );
        final PriorKnowledge cD = reasoner.getPriorKnowledge( "D" );
        final PriorKnowledge cE = reasoner.getPriorKnowledge( "E" );
        final PriorKnowledge cF = reasoner.getPriorKnowledge( "F" );

        assertEquals( TruthValuePowerSet.T, cF.getPrediction( ) );
        assertEquals( TruthValuePowerSet.N, cE.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, cD.getPrediction( ) );
        assertEquals( TruthValuePowerSet.NT, cC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.NF, cB.getPrediction( ) );
        assertEquals( TruthValuePowerSet.NT, cA.getPrediction( ) );

        assertEquals( TruthValuePowerSet.F, cA.getExpectation( ) );
        assertEquals( TruthValuePowerSet.F, cB.getExpectation( ) );
        assertEquals( TruthValuePowerSet.F, cC.getExpectation( ) );
        assertEquals( TruthValuePowerSet.F, cD.getExpectation( ) );
        assertEquals( TruthValuePowerSet.F, cE.getExpectation( ) );
        assertEquals( TruthValuePowerSet.F, cF.getExpectation( ) );

        Observation o = ObservationImpl.builder( )
                                       .name( "oE2" )
                                       .truthValue( TruthValue.t )
                                       .type( ObservationType.COMPUTATION )
                                       .build( );
        Relation oToE = new RelationImpl( o, cE, o.getType( ) );
        reasoner.insert( o, oToE );
        reasoner.reasoning( );
        assertEquals( TruthValuePowerSet.T, cE.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, cC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, cA.getPrediction( ) );


        reasoner.close( );
    }


    @Test
    public void case17( ) throws Exception {
        Cases.case17( reasoner );
        final PriorKnowledge cA = reasoner.getPriorKnowledge( "A" );
        final PriorKnowledge cB = reasoner.getPriorKnowledge( "B" );
        final PriorKnowledge cC = reasoner.getPriorKnowledge( "C" );
        final PriorKnowledge cD = reasoner.getPriorKnowledge( "D" );
        final PriorKnowledge cE = reasoner.getPriorKnowledge( "E" );
        final PriorKnowledge cF = reasoner.getPriorKnowledge( "F" );

        assertEquals( TruthValuePowerSet.T, cF.getPrediction( ) );
        assertEquals( TruthValuePowerSet.N, cE.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, cD.getPrediction( ) );
        assertEquals( TruthValuePowerSet.NT, cC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.NF, cB.getPrediction( ) );
        assertEquals( TruthValuePowerSet.NT, cA.getPrediction( ) );

        assertEquals( TruthValuePowerSet.T, cA.getExpectation( ) );
        assertEquals( TruthValuePowerSet.N, cB.getExpectation( ) );
        assertEquals( TruthValuePowerSet.T, cC.getExpectation( ) );
        assertEquals( TruthValuePowerSet.N, cD.getExpectation( ) );
        assertEquals( TruthValuePowerSet.NT, cE.getExpectation( ) );
        assertEquals( TruthValuePowerSet.T, cF.getExpectation( ) );

        Observation o = ObservationImpl.builder( )
                                       .name( "oE2" )
                                       .truthValue( TruthValue.t )
                                       .type( ObservationType.CURATION )
                                       .build( );
        Relation oToE = new RelationImpl( o, cE, o.getType( ) );
        reasoner.insert( o, oToE );
        reasoner.reasoning( );
        assertEquals( TruthValuePowerSet.T, cE.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, cC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, cA.getPrediction( ) );

        reasoner.close( );
    }

    @Test
    public void case21( ) throws Exception {
        Cases.case21( reasoner );
        final PriorKnowledge cA = reasoner.getPriorKnowledge( "A" );
        final PriorKnowledge cB = reasoner.getPriorKnowledge( "B" );
        final PriorKnowledge cC = reasoner.getPriorKnowledge( "C" );
        final PriorKnowledge cD = reasoner.getPriorKnowledge( "D" );
        final PriorKnowledge cE = reasoner.getPriorKnowledge( "E" );
        final PriorKnowledge cF = reasoner.getPriorKnowledge( "F" );

        assertEquals( TruthValuePowerSet.T, cF.getPrediction( ) );
        assertEquals( TruthValuePowerSet.N, cE.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, cD.getPrediction( ) );
        assertEquals( TruthValuePowerSet.NT, cC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.NF, cB.getPrediction( ) );
        assertEquals( TruthValuePowerSet.NT, cA.getPrediction( ) );

        assertEquals( TruthValuePowerSet.N, cA.getExpectation( ) );
        assertEquals( TruthValuePowerSet.N, cB.getExpectation( ) );
        assertEquals( TruthValuePowerSet.T, cC.getExpectation( ) );
        assertEquals( TruthValuePowerSet.N, cD.getExpectation( ) );
        assertEquals( TruthValuePowerSet.NT, cE.getExpectation( ) );
        assertEquals( TruthValuePowerSet.T, cF.getExpectation( ) );

        reasoner.close( );
    }

    @Test
    public void case22( ) throws Exception {
        Cases.case22( reasoner );
        final PriorKnowledge cA = reasoner.getPriorKnowledge( "A" );
        final PriorKnowledge cB = reasoner.getPriorKnowledge( "B" );
        final PriorKnowledge cC = reasoner.getPriorKnowledge( "C" );
        final PriorKnowledge cD = reasoner.getPriorKnowledge( "D" );
        final PriorKnowledge cE = reasoner.getPriorKnowledge( "E" );
        final PriorKnowledge cF = reasoner.getPriorKnowledge( "F" );

        assertEquals( TruthValuePowerSet.T, cF.getPrediction( ) );
        assertEquals( TruthValuePowerSet.N, cE.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, cD.getPrediction( ) );
        assertEquals( TruthValuePowerSet.NT, cC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.NF, cB.getPrediction( ) );
        assertEquals( TruthValuePowerSet.NT, cA.getPrediction( ) );

        assertEquals( TruthValuePowerSet.N, cA.getExpectation( ) );
        assertEquals( TruthValuePowerSet.F, cB.getExpectation( ) );
        assertEquals( TruthValuePowerSet.N, cC.getExpectation( ) );
        assertEquals( TruthValuePowerSet.F, cD.getExpectation( ) );
        assertEquals( TruthValuePowerSet.NF, cE.getExpectation( ) );
        assertEquals( TruthValuePowerSet.N, cF.getExpectation( ) );

        reasoner.close( );
    }

    @Test
    public void case23( ) throws Exception {
        Cases.case23( reasoner );
        final PriorKnowledge cA = reasoner.getPriorKnowledge( "A" );
        final PriorKnowledge cB = reasoner.getPriorKnowledge( "B" );
        final PriorKnowledge cC = reasoner.getPriorKnowledge( "C" );

        assertEquals( TruthValuePowerSet.T, cC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, cB.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, cA.getPrediction( ) );

        assertEquals( TruthValuePowerSet.N, cA.getExpectation( ) );
        assertEquals( TruthValuePowerSet.T, cB.getExpectation( ) );
        assertEquals( TruthValuePowerSet.NT, cC.getExpectation( ) );
        reasoner.close( );
    }

    @Test
    public void case29( ) throws Exception {
        Cases.case29( reasoner );
        final PriorKnowledge cA = reasoner.getPriorKnowledge( "A" );
        final PriorKnowledge cB = reasoner.getPriorKnowledge( "B" );
        final PriorKnowledge cC = reasoner.getPriorKnowledge( "C" );

        assertEquals( TruthValuePowerSet.N, cC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.N, cB.getPrediction( ) );
        assertEquals( TruthValuePowerSet.N, cA.getPrediction( ) );

        assertEquals( TruthValuePowerSet.N, cA.getExpectation( ) );
        assertEquals( TruthValuePowerSet.N, cB.getExpectation( ) );
        assertEquals( TruthValuePowerSet.N, cC.getExpectation( ) );
        reasoner.close( );
    }
}