package fr.cea.ig.grools.reasoner;

import fr.cea.ig.grools.Mode;
import fr.cea.ig.grools.Reasoner;
import fr.cea.ig.grools.Verbosity;
import fr.cea.ig.grools.fact.PriorKnowledge;
import fr.cea.ig.grools.logic.TruthValuePowerSet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * DispensableTest
 */
public class DispensableTest {
    private Reasoner reasoner;

    @Before
    public void setUp( ) {
        reasoner = new ReasonerImpl( Mode.DISPENSABLE, Verbosity.HIGHT );
    }

    @Test
    public void case1( ) throws Exception {
        Cases.case1( reasoner );
        final PriorKnowledge pk1 = reasoner.getPriorKnowledge( "pk1" );

        assertEquals( TruthValuePowerSet.T, pk1.getPrediction( ) );
        reasoner.close( );
    }

    @Test
    public void case18( ) throws Exception {
        Cases.case18( reasoner );
        final PriorKnowledge pkA = reasoner.getPriorKnowledge( "A" );
        final PriorKnowledge pkB = reasoner.getPriorKnowledge( "B" );
        final PriorKnowledge pkC = reasoner.getPriorKnowledge( "C" );

        assertEquals( TruthValuePowerSet.T, pkC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, pkB.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkA.getPrediction( ) );
        reasoner.close( );
    }

    @Test
    public void case19( ) throws Exception {
        Cases.case19( reasoner );
        final PriorKnowledge pkA = reasoner.getPriorKnowledge( "A" );
        final PriorKnowledge pkB = reasoner.getPriorKnowledge( "B" );
        final PriorKnowledge pkC = reasoner.getPriorKnowledge( "C" );
        final PriorKnowledge pkD = reasoner.getPriorKnowledge( "D" );
        final PriorKnowledge pkE = reasoner.getPriorKnowledge( "E" );
        final PriorKnowledge pkF = reasoner.getPriorKnowledge( "F" );

        assertEquals( TruthValuePowerSet.T, pkF.getPrediction( ) );
        assertEquals( TruthValuePowerSet.N, pkE.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, pkD.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, pkB.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkA.getPrediction( ) );

        assertEquals( TruthValuePowerSet.T, pkF.getExpectation( ) );
        assertEquals( TruthValuePowerSet.N, pkE.getExpectation( ) );
        assertEquals( TruthValuePowerSet.N, pkD.getExpectation( ) );
        assertEquals( TruthValuePowerSet.T, pkC.getExpectation( ) );
        assertEquals( TruthValuePowerSet.N, pkB.getExpectation( ) );
        assertEquals( TruthValuePowerSet.T, pkA.getExpectation( ) );
        reasoner.close( );
    }
}