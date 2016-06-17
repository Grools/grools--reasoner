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

import fr.cea.ig.grools.fact.Observation;
import fr.cea.ig.grools.fact.ObservationImpl;
import fr.cea.ig.grools.fact.ObservationType;
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
        assertEquals( TruthValueSet.T, Math.union( b, a, b) );
        assertEquals( TruthValueSet.F, Math.union(c) );
        assertEquals( TruthValueSet.B, Math.union(c,b,c) );
        assertEquals( TruthValueSet.N, Math.union(a) );
        assertEquals( TruthValueSet.B, Math.union( a,b,c ) );
        assertEquals( TruthValueSet.T, Math.union( true ) );
        assertEquals( TruthValueSet.F, Math.union( false ) );
        assertEquals( TruthValueSet.B, Math.union( true, false ) );
    }

    @Test
    public void TruthValueSetUnion() throws Exception {
        final TruthValueSet a = TruthValueSet.n;
        final TruthValueSet b = TruthValueSet.N;
        final TruthValueSet c = TruthValueSet.T;
        final TruthValueSet d = TruthValueSet.F;
        final TruthValueSet e = TruthValueSet.B;

        assertEquals( TruthValuePowerSet.T      , Math.union( a, c, a) );
        assertEquals( TruthValuePowerSet.F      , Math.union(d) );
        assertEquals( TruthValuePowerSet.TF     , Math.union(c,d,c) );
        assertEquals( TruthValuePowerSet.B      , Math.union(e) );
        assertEquals( TruthValuePowerSet.B      , Math.union(a,e) );
        assertEquals( TruthValuePowerSet.TFB    , Math.union( e,d,c ) );
        assertEquals( TruthValuePowerSet.TB     , Math.union( a,e,c ) );
        assertEquals( TruthValuePowerSet.NTFB   , Math.union( d,a,e,c,b ) );
    }

    @Test
    public void merge() throws Exception {
        final TruthValueSet a = TruthValueSet.n;
        final TruthValueSet b = TruthValueSet.N;
        final TruthValueSet c = TruthValueSet.T;
        final TruthValueSet d = TruthValueSet.F;
        final TruthValueSet e = TruthValueSet.B;
        assertEquals( TruthValueSet.T, Math.merge(c,a) );
        assertEquals( TruthValueSet.B, Math.merge(c,a,d) );
        assertEquals( TruthValueSet.F, Math.merge(b,d) );
        assertEquals( TruthValueSet.B, Math.merge(b,e) );

    }

    @Test
    public void mergeObservationWithSetOfTruthValueSet(){
        final Observation observation = new ObservationImpl( "Exp_Test",
                                                             "Experimentation LABGeM" ,
                                                             "",
                                                             "",
                                                             ObservationType.EXPERIMENTATION,
                                                             TruthValue.t);
        final Set<Observation> set = new HashSet<>(  );
        set.add( observation );
        EnumSet<TruthValueSet> ets = EnumSet.of( TruthValueSet.n );
        TruthValuePowerSet result = Math.merge( set, ets );
        assertEquals( TruthValuePowerSet.T, result);
    }

    @Test
    public void TruthValueSetIntersection() throws Exception {
        final TruthValueSet a = TruthValueSet.n;
        final TruthValueSet b = TruthValueSet.N;
        final TruthValueSet c = TruthValueSet.T;
        final TruthValueSet d = TruthValueSet.F;
        final TruthValueSet e = TruthValueSet.B;
         assertEquals( TruthValueSet.n, Math.intersection(c,a) );
         assertEquals( TruthValueSet.n, Math.intersection(c,b) );
         assertEquals( TruthValueSet.T, Math.intersection(c,c) );
         assertEquals( TruthValueSet.T, Math.intersection(c,e) );
         assertEquals( TruthValueSet.F, Math.intersection(d,e) );
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
         assertEquals( TruthValuePowerSet.n, Math.intersection(c,a) );
         assertEquals( TruthValuePowerSet.n, Math.intersection(c,b) );
         assertEquals( TruthValuePowerSet.T, Math.intersection(c,c) );
         assertEquals( TruthValuePowerSet.T, Math.intersection(c,e) );
         assertEquals( TruthValuePowerSet.F, Math.intersection(d,e) );
         assertEquals( TruthValuePowerSet.NTF, Math.intersection(f,g) );
         assertEquals( TruthValuePowerSet.n, Math.intersection(c,h) );
    }

    @Test
    public void TruthValueSetComplement(){
        final TruthValueSet a = TruthValueSet.T;
        final TruthValueSet b = TruthValueSet.F;
        final TruthValueSet c = TruthValueSet.B;
         assertEquals( TruthValueSet.F, Math.complement(a,c) );
         assertEquals( TruthValueSet.T, Math.complement(b,c) );
         assertEquals( TruthValueSet.F, Math.complement(a,b) );
    }

    @Test
    public void TruthValueSetDifference(){
        final TruthValueSet a = TruthValueSet.T;
        final TruthValueSet b = TruthValueSet.F;
        final TruthValueSet c = TruthValueSet.B;
         assertEquals( TruthValueSet.n, Math.difference(a,c) );
         assertEquals( TruthValueSet.F, Math.difference(c,a) );
         assertEquals( TruthValueSet.n, Math.difference(b,c) );
         assertEquals( TruthValueSet.T, Math.difference(c,b) );
         assertEquals( TruthValueSet.T, Math.difference(a,b) );
    }

    @Test
    public void TruthValuePowerSetComplement(){
        final TruthValuePowerSet a = TruthValuePowerSet.T;
        final TruthValuePowerSet b = TruthValuePowerSet.F;
        final TruthValuePowerSet c = TruthValuePowerSet.B;
        final TruthValuePowerSet d = TruthValuePowerSet.TF;
         assertEquals( TruthValuePowerSet.B, Math.complement(a,c) );
         assertEquals( TruthValuePowerSet.B, Math.complement(b,c) );
         assertEquals( TruthValuePowerSet.F, Math.complement(a,b) );
         assertEquals( TruthValuePowerSet.F, Math.complement(a,d) );
    }

    @Test
    public void TruthValuePowerSetDifference(){
        final TruthValuePowerSet a = TruthValuePowerSet.T;
        final TruthValuePowerSet b = TruthValuePowerSet.F;
        final TruthValuePowerSet c = TruthValuePowerSet.B;
         assertEquals( TruthValuePowerSet.T, Math.difference(a,c) );
         assertEquals( TruthValuePowerSet.F, Math.difference(b,c) );
         assertEquals( TruthValuePowerSet.T, Math.difference(a,b) );
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
