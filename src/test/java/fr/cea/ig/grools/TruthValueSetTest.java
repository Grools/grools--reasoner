/*
 *
 * Copyright LABGeM 2015
 *
 * author: Jonathan MERCIER
 *
 * This software is a computer program whose purpose is to annotate a complete genome.
 *
 * This software is governed by the CeCILL  license under French law and
 * abiding by the rules of distribution of free software.  You can  use,
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info".
 *
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability.
 *
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or
 * data to be ensured and,  more generally, to use and operate it in the
 * same conditions as regards security.
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 *
 */

package fr.cea.ig.grools;

import fr.cea.ig.grools.logic.TruthValue;
import fr.cea.ig.grools.logic.TruthValueSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.EnumSet;
import java.util.Set;

/**
 * TruthValueSetTest
 */
public class TruthValueSetTest {

    @Test
    public void content_n(){
        Set<TruthValue> content = EnumSet.noneOf( TruthValue.class );
        TruthValueSet value = TruthValueSet.getByContent( content );
        assertEquals( TruthValueSet.n, value);
    }

    @Test
    public void content_N(){
        Set<TruthValue> content = EnumSet.of( TruthValue.n );
        TruthValueSet value = TruthValueSet.getByContent( content );
        assertEquals( TruthValueSet.N, value);
    }

    @Test
    public void content_T(){
        Set<TruthValue> content = EnumSet.of( TruthValue.t );
        TruthValueSet value = TruthValueSet.getByContent( content );
        assertEquals( TruthValueSet.T, value);
    }

    @Test
    public void content_F(){
        Set<TruthValue> content = EnumSet.of( TruthValue.f );
        TruthValueSet value = TruthValueSet.getByContent( content );
        assertEquals( TruthValueSet.F, value);
    }

    @Test
    public void content_B(){
        Set<TruthValue> content = EnumSet.of( TruthValue.t, TruthValue.f);
        TruthValueSet value = TruthValueSet.getByContent( content );
        assertEquals( TruthValueSet.B, value);
    }

    @Test
    public void degree_n(){
        assertEquals( 0, TruthValueSet.n.getTruth(), 1e-15);
        assertEquals( 0, TruthValueSet.n.getFalsehood(), 1e-15);
        assertEquals( 0, TruthValueSet.n.size() );
    }

    @Test
    public void degree_T(){
        assertEquals( 1.0, TruthValueSet.T.getTruth(), 1e-15);
        assertEquals( 0.0, TruthValueSet.T.getFalsehood(), 1e-15);
        assertEquals( 1, TruthValueSet.T.size() );
    }

    @Test
    public void degree_F(){
        assertEquals( 0.0, TruthValueSet.F.getTruth(), 1e-15);
        assertEquals( 1.0, TruthValueSet.F.getFalsehood(), 1e-15);
        assertEquals( 1, TruthValueSet.F.size());
    }

    @Test
    public void degree_B(){
        assertEquals( 0.5, TruthValueSet.B.getTruth(), 1e-15);
        assertEquals( 0.5, TruthValueSet.B.getFalsehood(), 1e-15);
        assertEquals( 2, TruthValueSet.B.size());
    }

    @Test
    public void composition(){
        assertTrue( TruthValueSet.T.contains( TruthValue.t ) );
        assertTrue( TruthValueSet.F.contains( TruthValue.f ) );
        assertTrue( TruthValueSet.B.contains( TruthValue.f ) );
        assertTrue( TruthValueSet.B.contains( TruthValue.t ) );
        assertFalse( TruthValueSet.B.contains( TruthValue.n ) );
    }



}
