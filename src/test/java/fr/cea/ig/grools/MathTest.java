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

import fr.cea.ig.grools.logic.Math;
import fr.cea.ig.grools.logic.TruthValue;
import fr.cea.ig.grools.logic.TruthValuePowerSet;
import fr.cea.ig.grools.logic.TruthValueSet;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * MathTest
 */
public class MathTest {
    @Test
    public void TruthValueUnion() throws Exception {
        final TruthValue a = TruthValue.n;
        final TruthValue b = TruthValue.t;
        final TruthValue c = TruthValue.f;
        assertEquals( TruthValueSet.T, TruthValue.union( b, a, b) );
        assertEquals( TruthValueSet.F, TruthValue.union( c ) );
        assertEquals( TruthValueSet.B, TruthValue.union(c, b, c) );
        assertEquals( TruthValueSet.N, TruthValue.union( a ) );
        assertEquals( TruthValueSet.B, TruthValue.union( a, b, c ) );
    }

    @Test
    public void TruthValueSetUnion() throws Exception {
        final TruthValueSet a = TruthValueSet.n;
        final TruthValueSet b = TruthValueSet.N;
        final TruthValueSet c = TruthValueSet.T;
        final TruthValueSet d = TruthValueSet.F;
        final TruthValueSet e = TruthValueSet.B;

        assertEquals( TruthValuePowerSet.T      , TruthValueSet.union( a, c, a) );
        assertEquals( TruthValuePowerSet.F      , TruthValueSet.union(d) );
        assertEquals( TruthValuePowerSet.TF     , TruthValueSet.union(c,d,c) );
        assertEquals( TruthValuePowerSet.B      , TruthValueSet.union(e) );
        assertEquals( TruthValuePowerSet.B      , TruthValueSet.union(a,e) );
        assertEquals( TruthValuePowerSet.TFB    , TruthValueSet.union( e,d,c ) );
        assertEquals( TruthValuePowerSet.TB     , TruthValueSet.union( a,e,c ) );
        assertEquals( TruthValuePowerSet.NTFB   , TruthValueSet.union( d,a,e,c,b ) );
    }

    @Test
    public void merge() throws Exception {
        final TruthValueSet a = TruthValueSet.n;
        final TruthValueSet b = TruthValueSet.N;
        final TruthValueSet c = TruthValueSet.T;
        final TruthValueSet d = TruthValueSet.F;
        final TruthValueSet e = TruthValueSet.B;
        assertEquals( TruthValueSet.T, TruthValueSet.merge(c,a) );
        assertEquals( TruthValueSet.B, TruthValueSet.merge(c,a,d) );
        assertEquals( TruthValueSet.F, TruthValueSet.merge(b,d) );
        assertEquals( TruthValueSet.B, TruthValueSet.merge(b,e) );

    }


    @Test
    public void TruthValueSetIntersection() throws Exception {
        final TruthValueSet a = TruthValueSet.n;
        final TruthValueSet b = TruthValueSet.N;
        final TruthValueSet c = TruthValueSet.T;
        final TruthValueSet d = TruthValueSet.F;
        final TruthValueSet e = TruthValueSet.B;
         assertEquals( TruthValueSet.n, TruthValueSet.intersection(c,a) );
         assertEquals( TruthValueSet.n, TruthValueSet.intersection(c,b) );
         assertEquals( TruthValueSet.T, TruthValueSet.intersection(c,c) );
         assertEquals( TruthValueSet.T, TruthValueSet.intersection(c,e) );
         assertEquals( TruthValueSet.F, TruthValueSet.intersection(d,e) );
    }

    @Test
    public void TruthValuePowerSetIntersection() throws Exception {
        final TruthValuePowerSet a = TruthValuePowerSet.n;
        final TruthValuePowerSet b = TruthValuePowerSet.N;
        final TruthValuePowerSet c = TruthValuePowerSet.T;
        final TruthValuePowerSet d = TruthValuePowerSet.F;
        final TruthValuePowerSet e = TruthValuePowerSet.TF;
        final TruthValuePowerSet f = TruthValuePowerSet.NTF;
        final TruthValuePowerSet g = TruthValuePowerSet.NTFB;
        final TruthValuePowerSet h = TruthValuePowerSet.B;
         assertEquals( TruthValuePowerSet.n, TruthValuePowerSet.intersection(c,a) );
         assertEquals( TruthValuePowerSet.n, TruthValuePowerSet.intersection(c,b) );
         assertEquals( TruthValuePowerSet.T, TruthValuePowerSet.intersection(c,c) );
         assertEquals( TruthValuePowerSet.T, TruthValuePowerSet.intersection(c,e) );
         assertEquals( TruthValuePowerSet.F, TruthValuePowerSet.intersection(d,e) );
         assertEquals( TruthValuePowerSet.NTF, TruthValuePowerSet.intersection(f,g) );
         assertEquals( TruthValuePowerSet.n, TruthValuePowerSet.intersection(c,h) );
    }

    @Test
    public void TruthValueSetComplement(){
        final TruthValueSet a = TruthValueSet.T;
        final TruthValueSet b = TruthValueSet.F;
        final TruthValueSet c = TruthValueSet.B;
         assertEquals( TruthValueSet.F, TruthValueSet.complement(a,c) );
         assertEquals( TruthValueSet.T, TruthValueSet.complement(b,c) );
         assertEquals( TruthValueSet.F, TruthValueSet.complement(a,b) );
    }

    @Test
    public void TruthValueSetDifference(){
        final TruthValueSet a = TruthValueSet.T;
        final TruthValueSet b = TruthValueSet.F;
        final TruthValueSet c = TruthValueSet.B;
         assertEquals( TruthValueSet.n, TruthValueSet.difference(a,c) );
         assertEquals( TruthValueSet.F, TruthValueSet.difference(c,a) );
         assertEquals( TruthValueSet.n, TruthValueSet.difference(b,c) );
         assertEquals( TruthValueSet.T, TruthValueSet.difference(c,b) );
         assertEquals( TruthValueSet.T, TruthValueSet.difference(a,b) );
    }

    @Test
    public void TruthValuePowerSetComplement(){
        final TruthValuePowerSet a = TruthValuePowerSet.T;
        final TruthValuePowerSet b = TruthValuePowerSet.F;
        final TruthValuePowerSet c = TruthValuePowerSet.B;
        final TruthValuePowerSet d = TruthValuePowerSet.TF;
         assertEquals( TruthValuePowerSet.B, TruthValuePowerSet.complement(a,c) );
         assertEquals( TruthValuePowerSet.B, TruthValuePowerSet.complement(b,c) );
         assertEquals( TruthValuePowerSet.F, TruthValuePowerSet.complement(a,b) );
         assertEquals( TruthValuePowerSet.F, TruthValuePowerSet.complement(a,d) );
    }

    @Test
    public void TruthValuePowerSetDifference(){
        final TruthValuePowerSet a = TruthValuePowerSet.T;
        final TruthValuePowerSet b = TruthValuePowerSet.F;
        final TruthValuePowerSet c = TruthValuePowerSet.B;
         assertEquals( TruthValuePowerSet.T, TruthValuePowerSet.difference(a,c) );
         assertEquals( TruthValuePowerSet.F, TruthValuePowerSet.difference(b,c) );
         assertEquals( TruthValuePowerSet.T, TruthValuePowerSet.difference(a,b) );
    }

    @Test
    public void counter() throws Exception {
        final List<TruthValueSet> l = Arrays.asList(
                                                        TruthValueSet.T, TruthValueSet.B, TruthValueSet.F,
                                                        TruthValueSet.T, TruthValueSet.F, TruthValueSet.T );
         assertEquals( 3, Math.counter(l, TruthValueSet.T) );
         assertEquals( 1, Math.counter(l, TruthValueSet.B) );
    }

}
