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

package fr.cea.ig.grools.logic;

import lombok.Getter;
import lombok.NonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TruthValuePowerSet
 */
public enum TruthValuePowerSet {

    n(  ),
    N   ( TruthValueSet.N ),
    T   ( TruthValueSet.T ),
    F   ( TruthValueSet.F ),
    B   ( TruthValueSet.B ),
    NT  ( TruthValueSet.N, TruthValueSet.T ),
    NF  ( TruthValueSet.N, TruthValueSet.F ),
    NB  ( TruthValueSet.N, TruthValueSet.B ),
    TF  ( TruthValueSet.T, TruthValueSet.F ),
    TB  ( TruthValueSet.T, TruthValueSet.B ),
    FB  ( TruthValueSet.F, TruthValueSet.B ),
    NTF ( TruthValueSet.N, TruthValueSet.T, TruthValueSet.F ),
    NTB ( TruthValueSet.N, TruthValueSet.T, TruthValueSet.B ),
    NFB ( TruthValueSet.N, TruthValueSet.F, TruthValueSet.B ),
    TFB ( TruthValueSet.T, TruthValueSet.F, TruthValueSet.B ),
    NTFB( TruthValueSet.N, TruthValueSet.T, TruthValueSet.F, TruthValueSet.B );

    public static TruthValuePowerSet getByContent( @NonNull final TruthValueSet... set ){
        return getByContent( Arrays.asList( set )
                                   .stream()
                                   .collect(Collectors.toCollection(() -> EnumSet.noneOf(TruthValueSet.class))) );
    }

    public static TruthValuePowerSet getByContent( @NonNull final Collection<TruthValueSet> set ){
        final EnumSet<TruthValuePowerSet>   tvset   = EnumSet.allOf( TruthValuePowerSet.class );
        final Iterator<TruthValuePowerSet>  it      = tvset.iterator();
        TruthValuePowerSet                  result  = TruthValuePowerSet.n;
        if( set.size() > 1 ) set.remove(TruthValueSet.n);
        boolean isSearching = true;
        while( isSearching ){
            if( it.hasNext() ){
                final TruthValuePowerSet tester = it.next();
                if( tester.truthValuePowerSet.equals( set ) ) {
                    result      = tester;
                    isSearching = false;
                }
            }
            else
                isSearching = false;
        }
        return result;
    }

    @NonNull
    private final String name;

    @Getter
    @NonNull
    private final Set<TruthValueSet> truthValuePowerSet;

    @Getter
    private final float truth;

    @Getter
    private final float falsehood;


    TruthValuePowerSet( @NonNull final Collection<TruthValueSet> tvList ){
        truthValuePowerSet = Collections.synchronizedSet( EnumSet.copyOf( tvList) );
        truth       = ( truthValuePowerSet.size() == 0) ? 0: ( float ) ( truthValuePowerSet.stream().mapToDouble( i -> i.getTruth() ).sum() ) / truthValuePowerSet.size() ;
        falsehood   = ( truthValuePowerSet.size() == 0) ? 0: ( float ) ( truthValuePowerSet.stream().mapToDouble( i -> i.getFalsehood() ).sum() ) / truthValuePowerSet.size();
        name        = '{' + truthValuePowerSet.stream()
                                              .map( TruthValueSet::toString )
                                              .collect( Collectors.joining( ",")) + '}';
    }

    TruthValuePowerSet( @NonNull final TruthValueSet... tvSet){
        this( Arrays.asList(tvSet) );
    }

    TruthValuePowerSet( ){
        truthValuePowerSet = Collections.synchronizedSet( EnumSet.noneOf( TruthValueSet.class ) );
        truth       = 0;
        falsehood   = 0;
        name        = "∅";
    }


    @Override
    public String toString(){
        return name;
    }

    public int size(){
        return truthValuePowerSet.size();
    }

    public static TruthValuePowerSet merge( @NonNull final TruthValuePowerSet... values){
        return merge( Arrays.asList( values )
                                   .stream()
                                   .collect(Collectors.toCollection(() -> EnumSet.noneOf(TruthValuePowerSet.class))) );
    }


    public static TruthValuePowerSet merge( @NonNull final Collection<TruthValuePowerSet> values){
        final Set<TruthValueSet> tvSet = values.stream(  )
                                               .map( i-> i.getTruthValuePowerSet() )
                                               .reduce( EnumSet.noneOf(TruthValueSet.class), (x,y) -> {EnumSet<TruthValueSet> z = EnumSet.noneOf(TruthValueSet.class); z.addAll( x ); z.addAll( y ); return z;} );
        if( tvSet.size() > 1 ) tvSet.remove( TruthValueSet.n );
        return TruthValuePowerSet.getByContent( tvSet );
    }

    public static TruthValuePowerSet fill( @NonNull final TruthValuePowerSet... values){
        return fill( Arrays.asList( values )
                                   .stream()
                                   .collect(Collectors.toCollection(() -> EnumSet.noneOf(TruthValuePowerSet.class)))  );
    }


    public static TruthValuePowerSet fill( @NonNull final Collection<TruthValuePowerSet> values){
        if ( values.isEmpty() )
            values.add(TruthValuePowerSet.n);
        else if( values.size() > 1 ) values.remove(TruthValuePowerSet.N);
        final Set<TruthValueSet> tvSet = merge( values ).getTruthValuePowerSet();
        return TruthValuePowerSet.getByContent( tvSet );
    }

    // Difference: Set of members that belong to set A "and not" set B.
    public static TruthValuePowerSet difference( @NonNull final TruthValuePowerSet a , @NonNull final TruthValuePowerSet b ){
        Set<TruthValueSet> result = EnumSet.noneOf(TruthValueSet.class);
        result.addAll( a.getTruthValuePowerSet() );
        result.removeAll( b.getTruthValuePowerSet() );
        return TruthValuePowerSet.getByContent( result );
    }

    // Complement: Set of members that belong to set B "and not" set A.
    public static TruthValuePowerSet complement( @NonNull final TruthValuePowerSet a , @NonNull final TruthValuePowerSet b ){
        return difference(b,a);
    }

    public static TruthValuePowerSet intersection( @NonNull final TruthValuePowerSet... values ){
        return intersection( Arrays.asList( values)
                                   .stream()
                                   .collect(Collectors.toCollection(() -> EnumSet.noneOf(TruthValuePowerSet.class)))  );
    }

    public static TruthValuePowerSet intersection( @NonNull final Collection<TruthValuePowerSet> values ){
        return values.stream()
                     .reduce( (x,y) -> { final Set<TruthValueSet> tmp = EnumSet.noneOf(TruthValueSet.class); tmp.addAll( x.getTruthValuePowerSet() ); tmp.retainAll( y.getTruthValuePowerSet() ); return TruthValuePowerSet.getByContent( tmp ) ; } )
                     .orElse( TruthValuePowerSet.n );
    }

    public static TruthValuePowerSet add( @NonNull final TruthValuePowerSet truthValueSet, @NonNull final TruthValueSet... truthValues ){
        return add( truthValueSet, Arrays.asList(truthValues)
                                         .stream()
                                         .collect(Collectors.toCollection(() -> EnumSet.noneOf(TruthValueSet.class)))  );
    }

    public static TruthValuePowerSet add( @NonNull final TruthValuePowerSet truthValuePowerSet, @NonNull final Collection<TruthValueSet> truthValues ){
        final Set<TruthValueSet> truthValueSets = truthValues.stream()
                                                    .filter( i -> i != TruthValueSet.n && i != TruthValueSet.N )
                                                    .collect( Collectors.toSet( ) );
        if( truthValuePowerSet != TruthValuePowerSet.N &&  truthValuePowerSet != TruthValuePowerSet.n )
            truthValueSets.addAll( truthValuePowerSet.getTruthValuePowerSet() );

        return (truthValueSets.size() == 0 )? TruthValuePowerSet.N : TruthValuePowerSet.getByContent( truthValueSets );
    }

    public static TruthValuePowerSet remove( @NonNull final TruthValuePowerSet truthValueSet, @NonNull final TruthValueSet... truthValues ){
        return remove( truthValueSet, Arrays.asList(truthValues) );
    }

    public static TruthValuePowerSet remove( @NonNull final TruthValuePowerSet truthValueSet, @NonNull final Collection<TruthValueSet> truthValues ){
        final Set<TruthValueSet> tvSet = new HashSet<>( truthValueSet.getTruthValuePowerSet().size() );
        tvSet.addAll( truthValueSet.getTruthValuePowerSet() );
        tvSet.removeAll(truthValues);
        return TruthValuePowerSet.getByContent( tvSet );
    }

    public static TruthValuePowerSet choice(@NonNull final TruthValuePowerSet tvps1, @NonNull final TruthValuePowerSet tvps2){
        TruthValuePowerSet result;
        final double tmp1       = tvps1.getTruth() - tvps1.getFalsehood();
        final double tmp2       = tvps2.getTruth() - tvps2.getFalsehood();
        final double tmpAbs1    = java.lang.Math.abs( tmp1 );
        final double tmpAbs2    = java.lang.Math.abs( tmp2 );

        if( tvps1 == tvps2 )
            result = tvps1;
        else if( tvps1.getTruth() !=  tvps2.getTruth()) // the most true
            result = ( tvps1.getTruth() > tvps2.getTruth()) ? tvps1 : tvps2;
        else if( tvps1.getFalsehood() !=  tvps2.getFalsehood()) // the less false
            result = ( tvps1.getFalsehood() < tvps2.getFalsehood()) ? tvps1 : tvps2;
        else // the most precise
            result = ( tvps1.getTruthValuePowerSet().size() < tvps2.getTruthValuePowerSet().size()) ? tvps1 : tvps2;
        return result;
    }

    public static TruthValuePowerSet choice(@NonNull final Set<TruthValuePowerSet> set ){
        return set.stream().reduce( TruthValuePowerSet.F, (a,b) -> choice(a,b) );
    }
}
