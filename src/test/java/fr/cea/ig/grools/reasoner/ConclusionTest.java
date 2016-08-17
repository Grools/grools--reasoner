package fr.cea.ig.grools.reasoner;

import fr.cea.ig.grools.logic.Conclusion;
import fr.cea.ig.grools.logic.TruthValueSet;
import org.junit.Test;

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
import static org.junit.Assert.assertEquals;

/**
 * ConclusionTest
 */
public class ConclusionTest {
    private final static DoubleEntryTable< TruthValueSet, TruthValueSet, Conclusion > conclusions = new DoubleEntryTable<>(
            new TruthValueSet[]{ TruthValueSet.T, TruthValueSet.F, TruthValueSet.B, TruthValueSet.N },
            new TruthValueSet[]{ TruthValueSet.T, TruthValueSet.F, TruthValueSet.B, TruthValueSet.N },
            new Conclusion[][]{ // PREDICTION
                                // TRUE                  FALSE                BOTH                        UNKNOWN         | EXPECTATION
                                { CONFIRMED_PRESENCE, UNEXPECTED_ABSENCE, CONTRADICTORY_ABSENCE, MISSING },  // | TRUE
                                { UNEXPECTED_PRESENCE, CONFIRMED_ABSENCE, CONTRADICTORY_PRESENCE, ABSENT },  // | FALSE
                                { AMBIGUOUS_PRESENCE, AMBIGUOUS_ABSENCE, AMBIGUOUS_CONTRADICTORY, AMBIGUOUS },  // | BOTH
                                { UNCONFIRMED_PRESENCE, UNCONFIRMED_ABSENCE, UNCONFIRMED_CONTRADICTORY, UNEXPLAINED } // | UNKNOWN
            }
    );

    @Test
    public void isMissing( ) throws Exception {
        Conclusion test = conclusions.get( TruthValueSet.T, TruthValueSet.N );
        assertEquals( MISSING, test );
    }
}
