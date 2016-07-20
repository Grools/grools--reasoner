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

import fr.cea.ig.grools.logic.TruthValuePowerSet;
import fr.cea.ig.grools.logic.TruthValueSet;
import org.junit.Test;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * TruthValuePowerSetTest
 */
public class TruthValuePowerSetTest {
    private static final Map<TruthValuePowerSet, List<Double>> expectation = Stream.of(
                                                                                            new Object[]{TruthValuePowerSet.n   , Arrays.asList(    0.0     ,0.0    ,0.0)},
                                                                                            new Object[]{TruthValuePowerSet.N   , Arrays.asList(    0.0     ,0.0    ,1.0)},
                                                                                            new Object[]{TruthValuePowerSet.T   , Arrays.asList(    1.0     ,0.0    ,1.0)},
                                                                                            new Object[]{TruthValuePowerSet.F   , Arrays.asList(    0.0     ,1.0    ,1.0)},
                                                                                            new Object[]{TruthValuePowerSet.B   , Arrays.asList(    0.5     ,0.5    ,1.0)},
                                                                                            new Object[]{TruthValuePowerSet.NF  , Arrays.asList(    0.0     ,0.5    ,2.0)},
                                                                                            new Object[]{TruthValuePowerSet.NT  , Arrays.asList(    0.5     ,0.0    ,2.0)},
                                                                                            new Object[]{TruthValuePowerSet.NB  , Arrays.asList(    0.25    ,0.25   ,2.0)},
                                                                                            new Object[]{TruthValuePowerSet.TF  , Arrays.asList(    0.5     ,0.5    ,2.0)},
                                                                                            new Object[]{TruthValuePowerSet.FB  , Arrays.asList(    0.25    ,0.75   ,2.0)},
                                                                                            new Object[]{TruthValuePowerSet.TB  , Arrays.asList(    0.75    ,0.25   ,2.0)},
                                                                                            new Object[]{TruthValuePowerSet.TFB , Arrays.asList(    0.5     ,0.5    ,3.0)},
                                                                                            new Object[]{TruthValuePowerSet.NTF , Arrays.asList(    1/3.0   ,1/3.0  ,3.0)},
                                                                                            new Object[]{TruthValuePowerSet.NFB , Arrays.asList(    1/6.0   ,0.5    ,3.0)},
                                                                                            new Object[]{TruthValuePowerSet.NTB , Arrays.asList(    0.5     ,1/6.0  ,3.0)},
                                                                                            new Object[]{TruthValuePowerSet.NTFB, Arrays.asList(    0.375   ,0.375  ,4.0)}
                                                                                    )
                                                                                   .collect( Collectors.toMap( s -> (TruthValuePowerSet) s[0], s -> (List) s[1]) );

    @Test
    public void content_n(){
        Set<TruthValueSet> content = EnumSet.noneOf( TruthValueSet.class );
        TruthValuePowerSet value = TruthValuePowerSet.getByContent( content );
        assertEquals( TruthValuePowerSet.n, value);
    }

    @Test
    public void content_N(){
        Set<TruthValueSet> content = EnumSet.of( TruthValueSet.N );
        TruthValuePowerSet value = TruthValuePowerSet.getByContent( content );
        assertEquals( TruthValuePowerSet.N, value);
    }

    @Test
    public void content_T(){
        Set<TruthValueSet> content = EnumSet.of( TruthValueSet.T );
        TruthValuePowerSet value = TruthValuePowerSet.getByContent( content );
        assertEquals( TruthValuePowerSet.T, value);
    }

    @Test
    public void content_F(){
        Set<TruthValueSet> content = EnumSet.of( TruthValueSet.F );
        TruthValuePowerSet value = TruthValuePowerSet.getByContent( content );
        assertEquals( TruthValuePowerSet.F, value);
    }

    @Test
    public void content_B(){
        Set<TruthValueSet> content = EnumSet.of( TruthValueSet.B);
        TruthValuePowerSet value = TruthValuePowerSet.getByContent( content );
        assertEquals( TruthValuePowerSet.B, value);
    }

    @Test
    public void content_NT(){
        Set<TruthValueSet> content = EnumSet.of( TruthValueSet.N, TruthValueSet.T);
        TruthValuePowerSet value = TruthValuePowerSet.getByContent( content );
        assertEquals( TruthValuePowerSet.NT, value);
    }


    @Test
    public void content_NF(){
        Set<TruthValueSet> content = EnumSet.of( TruthValueSet.N, TruthValueSet.F);
        TruthValuePowerSet value = TruthValuePowerSet.getByContent( content );
        assertEquals( TruthValuePowerSet.NF, value);
    }


    @Test
    public void content_NB(){
        Set<TruthValueSet> content = EnumSet.of( TruthValueSet.N, TruthValueSet.B);
        TruthValuePowerSet value = TruthValuePowerSet.getByContent( content );
        assertEquals( TruthValuePowerSet.NB, value);
    }

    @Test
    public void content_TF(){
        Set<TruthValueSet> content = EnumSet.of( TruthValueSet.T, TruthValueSet.F);
        TruthValuePowerSet value = TruthValuePowerSet.getByContent( content );
        assertEquals( TruthValuePowerSet.TF, value);
    }

    @Test
    public void content_TB(){
        Set<TruthValueSet> content = EnumSet.of( TruthValueSet.T, TruthValueSet.B);
        TruthValuePowerSet value = TruthValuePowerSet.getByContent( content );
        assertEquals( TruthValuePowerSet.TB, value);
    }

    @Test
    public void content_FB(){
        Set<TruthValueSet> content = EnumSet.of( TruthValueSet.F, TruthValueSet.B);
        TruthValuePowerSet value = TruthValuePowerSet.getByContent( content );
        assertEquals( TruthValuePowerSet.FB, value);
    }

    @Test
    public void content_NTF(){
        Set<TruthValueSet> content = EnumSet.of( TruthValueSet.N, TruthValueSet.T, TruthValueSet.F);
        TruthValuePowerSet value = TruthValuePowerSet.getByContent( content );
        assertEquals( TruthValuePowerSet.NTF, value);
    }

    @Test
    public void content_NTB(){
        Set<TruthValueSet> content = EnumSet.of( TruthValueSet.N, TruthValueSet.T, TruthValueSet.B);
        TruthValuePowerSet value = TruthValuePowerSet.getByContent( content );
        assertEquals( TruthValuePowerSet.NTB, value);
    }

    @Test
    public void content_NFB(){
        Set<TruthValueSet> content = EnumSet.of( TruthValueSet.N, TruthValueSet.F, TruthValueSet.B);
        TruthValuePowerSet value = TruthValuePowerSet.getByContent( content );
        assertEquals( TruthValuePowerSet.NFB, value);
    }

    @Test
    public void content_TFB(){
        Set<TruthValueSet> content = EnumSet.of( TruthValueSet.T, TruthValueSet.F, TruthValueSet.B);
        TruthValuePowerSet value = TruthValuePowerSet.getByContent( content );
        assertEquals( TruthValuePowerSet.TFB, value);
    }

    @Test
    public void content_NTFB(){
        Set<TruthValueSet> content = EnumSet.of( TruthValueSet.N, TruthValueSet.T, TruthValueSet.F, TruthValueSet.B);
        TruthValuePowerSet value = TruthValuePowerSet.getByContent( content );
        assertEquals( TruthValuePowerSet.NTFB, value);
    }

    @Test
    public void degree(){
        final EnumSet<TruthValuePowerSet> truthValuePowerSets = EnumSet.allOf( TruthValuePowerSet.class );
        for( final TruthValuePowerSet tvpset : truthValuePowerSets){
            final List<Double> values = expectation.get( tvpset );
            assertEquals( values.get( 0 ), tvpset.getTruth(), 1e-3 );
            assertEquals( values.get( 1 ), tvpset.getFalsehood(), 1e-3 );
            assertEquals( values.get( 2 ).intValue(), tvpset.size() );
        }
    }

}
