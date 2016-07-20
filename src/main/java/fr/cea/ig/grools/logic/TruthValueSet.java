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
import java.util.stream.IntStream;

/**
 * TruthValueSet
 */
public enum TruthValueSet {

    n(  ),
    N( TruthValue.n ),
    T( true ),
    F( false ),
    B( true, false );

    public static TruthValueSet getByContent( @NonNull final TruthValue... set ){
        return getByContent( Arrays.asList( set )
                                   .stream()
                                   .collect(Collectors.toCollection(() -> EnumSet.noneOf(TruthValue.class))) );
    }


    public static TruthValueSet getByContent( @NonNull final Collection<TruthValue> set ){
        final EnumSet<TruthValueSet> tvset = EnumSet.allOf( TruthValueSet.class );
        final Iterator<TruthValueSet>   it      = tvset.iterator();
        TruthValueSet                   result  = TruthValueSet.n;

        if( set.size() > 1 ) set.remove(TruthValue.n);

        boolean isSearching = true;
        while( isSearching ){
            if( it.hasNext() ){
                final TruthValueSet tester = it.next();
                if( tester.truthValueSet.equals( set ) ) {
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

    @Getter @NonNull
    private final Set<TruthValue> truthValueSet;

    @Getter
    private final float truth;

    @Getter
    private final float falsehood;


    TruthValueSet( @NonNull final Collection<TruthValue> tvList ){
        truthValueSet = Collections.synchronizedSet( EnumSet.copyOf( tvList) );
        truth       = ( truthValueSet.size() == 0) ? 0: Math.counter( truthValueSet, TruthValue.t ) / (float)truthValueSet.size();
        falsehood   = ( truthValueSet.size() == 0) ? 0: Math.counter( truthValueSet, TruthValue.f ) / (float)truthValueSet.size();
        name        = '{' + truthValueSet.stream()
                                         .map( TruthValue::toString )
                                         .collect(Collectors.joining(",")) + '}';
    }

    TruthValueSet( @NonNull final TruthValue... tv){
        this( Arrays.asList(tv) );
    }

    TruthValueSet( final boolean... values){
        this( IntStream.range( 0, values.length )
                       .mapToObj( i -> TruthValue.valueOf( values[ i ]? "t" : "f" ) )
                       .collect( Collectors.toList() ));
    }

    TruthValueSet( ){
        truthValueSet = Collections.synchronizedSet( EnumSet.noneOf( TruthValue.class )  );
        truth       = 0;
        falsehood   = 0;
        name        = "∅";
    }

    @Override
    public String toString(){
        return name;
    }

    public int size(){
        return truthValueSet.size();
    }

    public static TruthValuePowerSet union( @NonNull final TruthValueSet... values){
        return union( Arrays.asList( values )
                            .stream()
                            .collect(Collectors.toCollection(() -> EnumSet.noneOf(TruthValueSet.class))) );
    }

    public static TruthValuePowerSet union( @NonNull final Collection<TruthValueSet> values){
        if( values.size() > 1 ) values.remove( TruthValueSet.n );
        return TruthValuePowerSet.getByContent( values );
    }

    public static TruthValueSet merge( @NonNull final TruthValueSet... values){
        return merge( Arrays.asList( values )
                            .stream()
                            .collect(Collectors.toCollection(() -> EnumSet.noneOf(TruthValueSet.class))) );
    }

    public static TruthValueSet merge( @NonNull final Collection<TruthValueSet> values){
        final Set<TruthValue> tvSet = values.stream(  )
                                               .map( i-> i.getTruthValueSet() )
                                               .reduce( EnumSet.noneOf(TruthValue.class), (x,y) -> {EnumSet<TruthValue> z = EnumSet.noneOf(TruthValue.class); z.addAll( x ); z.addAll( y ); return z;} );
        if( tvSet.size() > 1 ) tvSet.remove( TruthValueSet.n );
        return TruthValueSet.getByContent( tvSet );
    }


    // Difference: Set of members that belong to set A "and not" set B.
    public static TruthValueSet difference( @NonNull final TruthValueSet a , @NonNull final TruthValueSet b ){
        Set<TruthValue> result = EnumSet.noneOf(TruthValue.class);
        result.addAll( a.getTruthValueSet() );
        result.removeAll( b.getTruthValueSet() );
        return TruthValueSet.getByContent( result );
    }

    // Complement: Set of members that belong to set B "and not" set A.
    public static TruthValueSet complement( @NonNull final TruthValueSet a , @NonNull final TruthValueSet b ){
        return difference(b,a);
    }

    public static TruthValueSet intersection( @NonNull final TruthValueSet... values ){
        return intersection( Arrays.asList( values )
                                   .stream()
                                   .collect(Collectors.toCollection(() -> EnumSet.noneOf(TruthValueSet.class))) );
    }


    public static TruthValueSet intersection( @NonNull final Collection<TruthValueSet> values ){
        return values.stream()
                     .reduce( (x,y) -> { final Set<TruthValue> tmp = EnumSet.noneOf(TruthValue.class); tmp.addAll( x.getTruthValueSet() ); tmp.retainAll( y.getTruthValueSet() ); return TruthValueSet.getByContent( tmp ) ; } )
                     .orElse( TruthValueSet.n );
    }

    public static TruthValueSet add( @NonNull final TruthValueSet truthValueSet, @NonNull final TruthValue... truthValues ){
        return add( truthValueSet, Arrays.asList(truthValues)
                                         .stream()
                                         .collect(Collectors.toCollection(() -> EnumSet.noneOf(TruthValue.class)))  );
    }

    public static TruthValueSet add( @NonNull final TruthValueSet truthValueSet, @NonNull final Collection<TruthValue> truthValues ){
        final Set<TruthValue> tvSet = new HashSet<>( truthValues.size() + truthValueSet.getTruthValueSet().size() );
        tvSet.addAll(truthValues);
        tvSet.addAll( truthValueSet.getTruthValueSet() );
        return TruthValueSet.getByContent( tvSet );
    }

    public static TruthValueSet remove( @NonNull final TruthValueSet truthValueSet, @NonNull final Collection<TruthValue> truthValues ){
        final Set<TruthValue> tvSet = new HashSet<>( truthValueSet.getTruthValueSet().size() );
        tvSet.addAll( truthValueSet.getTruthValueSet() );
        tvSet.removeAll(truthValues);
        return TruthValueSet.getByContent( tvSet );
    }

}
