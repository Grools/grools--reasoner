package fr.cea.ig.grools.reasoner;


import fr.cea.ig.grools.Mode;
import fr.cea.ig.grools.Reasoner;
import fr.cea.ig.grools.Verbosity;
import fr.cea.ig.grools.fact.PriorKnowledge;
import fr.cea.ig.grools.logic.TruthValuePowerSet;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * NormalSpecificTest
 */
public class NormalSpecificTest {
    private Reasoner reasoner;

    @Before
    public void setUp( ) {
        reasoner = new ReasonerImpl( Mode.NORMAL_SPECIFIC, Verbosity.HIGHT );
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

        assertTrue( pkC.getIsSpecific( ) );
        assertTrue( pkB.getIsSpecific( ) );
        assertEquals( TruthValuePowerSet.T, pkC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.F, pkB.getPrediction( ) );
        assertEquals( TruthValuePowerSet.TF, pkA.getPrediction( ) );
        reasoner.close( );
    }

    @Test
    public void case20( ) throws Exception {
        Cases.case20( reasoner );
        final PriorKnowledge pkA = reasoner.getPriorKnowledge( "A" );
        final PriorKnowledge pkB = reasoner.getPriorKnowledge( "B" );
        final PriorKnowledge pkC = reasoner.getPriorKnowledge( "C" );
        final PriorKnowledge pkD = reasoner.getPriorKnowledge( "D" );
        final PriorKnowledge pkE = reasoner.getPriorKnowledge( "E" );
        final PriorKnowledge pkF = reasoner.getPriorKnowledge( "F" );
        final PriorKnowledge pkG = reasoner.getPriorKnowledge( "G" );
        final PriorKnowledge pkH = reasoner.getPriorKnowledge( "H" );
        final PriorKnowledge pkI = reasoner.getPriorKnowledge( "I" );
        final PriorKnowledge pkJ = reasoner.getPriorKnowledge( "J" );
        final PriorKnowledge pkK = reasoner.getPriorKnowledge( "K" );
        final PriorKnowledge pkL = reasoner.getPriorKnowledge( "L" );

        assertTrue( pkJ.getIsSpecific( ) );
        assertEquals( TruthValuePowerSet.N, pkL.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkK.getPrediction( ) );
        assertEquals( TruthValuePowerSet.N, pkJ.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkI.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkH.getPrediction( ) );
        assertEquals( TruthValuePowerSet.N, pkG.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkF.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkE.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkD.getPrediction( ) );
        assertEquals( TruthValuePowerSet.NT, pkC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkB.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkA.getPrediction( ) );
        reasoner.close( );
    }

    @Test
    public void case24( ) throws Exception {
        Cases.case24( reasoner );
        final PriorKnowledge pkA = reasoner.getPriorKnowledge( "A" );
        final PriorKnowledge pkB = reasoner.getPriorKnowledge( "B" );
        final PriorKnowledge pkC = reasoner.getPriorKnowledge( "C" );
        final PriorKnowledge pkD = reasoner.getPriorKnowledge( "D" );
        assertTrue( pkD.getIsSpecific( ) );
        assertEquals( TruthValuePowerSet.N, pkD.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkA.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkB.getPrediction( ) );
        reasoner.close( );
    }

    @Test
    public void case25( ) throws Exception {
        Cases.case25( reasoner );
        final PriorKnowledge pkA = reasoner.getPriorKnowledge( "A" );
        final PriorKnowledge pkB = reasoner.getPriorKnowledge( "B" );
        final PriorKnowledge pkC = reasoner.getPriorKnowledge( "C" );
        final PriorKnowledge pkD = reasoner.getPriorKnowledge( "D" );
        final PriorKnowledge pkE = reasoner.getPriorKnowledge( "E" );
        final PriorKnowledge pkF = reasoner.getPriorKnowledge( "F" );
        final PriorKnowledge pkG = reasoner.getPriorKnowledge( "G" );
        final PriorKnowledge pkH = reasoner.getPriorKnowledge( "H" );
        final PriorKnowledge pkI = reasoner.getPriorKnowledge( "I" );
        final PriorKnowledge pkJ = reasoner.getPriorKnowledge( "J" );
        final PriorKnowledge pkK = reasoner.getPriorKnowledge( "K" );
        assertTrue( pkG.getIsSpecific( ) );
        assertTrue( pkK.getIsSpecific( ) );
        assertEquals( TruthValuePowerSet.N, pkK.getPrediction( ) );
        assertEquals( TruthValuePowerSet.N, pkJ.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkI.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkH.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkG.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkF.getPrediction( ) );
        assertEquals( TruthValuePowerSet.N, pkE.getPrediction( ) );
        assertEquals( TruthValuePowerSet.NT, pkD.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkC.getPrediction( ) );
        assertEquals( TruthValuePowerSet.NT, pkB.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkA.getPrediction( ) );
        reasoner.close( );
    }

    @Test
    public void case26( ) throws Exception {
        Cases.case26( reasoner );
        final PriorKnowledge pkA = reasoner.getPriorKnowledge( "A" );
        final PriorKnowledge pkB = reasoner.getPriorKnowledge( "B" );
        final PriorKnowledge pkC = reasoner.getPriorKnowledge( "C" );
        final PriorKnowledge pkD = reasoner.getPriorKnowledge( "D" );
        final PriorKnowledge pkE = reasoner.getPriorKnowledge( "E" );
        final PriorKnowledge pkE1 = reasoner.getPriorKnowledge( "E1" );
        final PriorKnowledge pkE2 = reasoner.getPriorKnowledge( "E2" );
        final PriorKnowledge pkE3 = reasoner.getPriorKnowledge( "E3" );
        final PriorKnowledge pkE4 = reasoner.getPriorKnowledge( "E4" );
        final PriorKnowledge pkE5 = reasoner.getPriorKnowledge( "E5" );
        final PriorKnowledge pkE6 = reasoner.getPriorKnowledge( "E6" );
        final PriorKnowledge pkF = reasoner.getPriorKnowledge( "F" );
        final PriorKnowledge pkF1 = reasoner.getPriorKnowledge( "F1" );
        final PriorKnowledge pkF1a = reasoner.getPriorKnowledge( "F1a" );
        final PriorKnowledge pkF2 = reasoner.getPriorKnowledge( "F2" );
        final PriorKnowledge pkF2a = reasoner.getPriorKnowledge( "F2a" );
        final PriorKnowledge pkF3 = reasoner.getPriorKnowledge( "F3" );
        final PriorKnowledge pkF4 = reasoner.getPriorKnowledge( "F4" );
        final PriorKnowledge pkF5 = reasoner.getPriorKnowledge( "F5" );
        assertTrue( pkF1.getIsSpecific( ) );
        assertTrue( pkF2.getIsSpecific( ) );
        assertEquals( TruthValuePowerSet.T, pkF1.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkF2.getPrediction( ) );
        assertEquals( TruthValuePowerSet.N, pkF3.getPrediction( ) );
        assertEquals( TruthValuePowerSet.N, pkF4.getPrediction( ) );
        assertEquals( TruthValuePowerSet.N, pkF5.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkF.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkD.getPrediction( ) );
        assertEquals( TruthValuePowerSet.N, pkE.getPrediction( ) );
        assertEquals( TruthValuePowerSet.T, pkA.getPrediction( ) );
        assertEquals( TruthValuePowerSet.N, pkE.getExpectation( ) );
        assertEquals( TruthValuePowerSet.B, pkA.getExpectation( ) );
        reasoner.close( );
    }
}