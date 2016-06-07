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

import fr.cea.ig.grools.fact.Observation;
import lombok.NonNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Math
 */
public class Math {

    public static TruthValueSet union( @NonNull final TruthValue... values ){
        final Set<TruthValue> tvSet = Arrays.stream( values )
                                            .collect( Collectors.toCollection( () -> EnumSet.noneOf(TruthValue.class) ) );
        if( tvSet.size() > 1 ) tvSet.remove( TruthValue.n );
        return TruthValueSet.getByContent( tvSet );
    }

    public static TruthValueSet union( final boolean... values ){
        final Set<TruthValue> tvSet = IntStream.range( 0, values.length )
                                               .mapToObj( i -> TruthValue.valueOf( values[ i ]? "t" : "f" ) )
                                               .collect( Collectors.toCollection( () -> EnumSet.noneOf(TruthValue.class) ) );
        return TruthValueSet.getByContent( tvSet );
    }

    public static TruthValuePowerSet union( @NonNull final TruthValueSet... values){
        final Set<TruthValueSet> tvSet = Arrays.stream( values )
                                               .collect( Collectors.toCollection( () -> EnumSet.noneOf(TruthValueSet.class) ) );
        if( tvSet.size() > 1 ) tvSet.remove( TruthValueSet.n );
        return TruthValuePowerSet.getByContent( tvSet );
    }

    public static TruthValueSet union( @NonNull final Observation... observations ){
        final Set<TruthValue> tvSet = Arrays.stream( observations )
                                            .map( Observation::getTruthValue )
                                            .collect( Collectors.toCollection( () -> EnumSet.noneOf(TruthValue.class) ) );
        if( tvSet.size() > 1 ) tvSet.remove( TruthValue.n );
        return TruthValueSet.getByContent( tvSet );
    }

    // this function is designed to took truthValueSet from observation. Do not use it for another purpose
    public static TruthValuePowerSet merge( @NonNull final Set<Observation> observations, @NonNull final Set<TruthValueSet> sets ){
        final TruthValueSet truthValueSet = TruthValueSet.getByContent( observations.stream()
                                                                                    .map( Observation::getTruthValue )
                                                                                    .collect( Collectors.toCollection( () -> EnumSet.of(TruthValue.n) ) )
                                                                      );
        final TruthValuePowerSet truthValuePowerSet = TruthValuePowerSet.getByContent( sets );
        TruthValuePowerSet result;
        if( truthValueSet == TruthValueSet.N ){
            if( truthValuePowerSet == TruthValuePowerSet.n )
                result = TruthValuePowerSet.N;
            else
                result = truthValuePowerSet;
        }
        else if( truthValuePowerSet == TruthValuePowerSet.n )
            result = TruthValuePowerSet.getByContent( EnumSet.of( truthValueSet) );
        else{
            final EnumSet<TruthValueSet> tmp = EnumSet.of( truthValueSet );
            tmp.addAll( truthValuePowerSet.getTruthValuePowerSet() );
            result = TruthValuePowerSet.getByContent( tmp );
        }
        return result;
    }

    public static TruthValueSet merge( @NonNull final TruthValueSet... values ){
        final Set<TruthValue> tvSet = Arrays.stream( values )
                                            .filter( i -> i != TruthValueSet.n && i != TruthValueSet.N )
                                            .map( TruthValueSet::getTruthValueSet )
                                            .reduce( EnumSet.noneOf(TruthValue.class), (x,y) -> {EnumSet<TruthValue> z = EnumSet.noneOf(TruthValue.class); z.addAll( x ); z.addAll( y ); return z;} );

        return TruthValueSet.getByContent( tvSet );
    }

    public static TruthValuePowerSet merge( @NonNull final TruthValuePowerSet... values ){
        final Set<TruthValueSet> tvSet = Arrays.stream( values )
                                               .filter( i -> i != TruthValuePowerSet.n && i != TruthValuePowerSet.N )
                                               .map( TruthValuePowerSet::getTruthValuePowerSet )
                                               .reduce( EnumSet.noneOf(TruthValueSet.class), (x,y) -> {Set<TruthValueSet> z = EnumSet.noneOf(TruthValueSet.class); z.addAll( x ); z.addAll( y ); return  z;} );
        return TruthValuePowerSet.getByContent( tvSet );
    }



    public static TruthValueSet intersection( @NonNull final TruthValueSet... values ){
        return Arrays.asList( values )
                     .stream()
                     .reduce( (x,y) -> { final Set<TruthValue> tmp = EnumSet.noneOf(TruthValue.class); tmp.addAll( x.getTruthValueSet() ); tmp.retainAll( y.getTruthValueSet() ); return TruthValueSet.getByContent( tmp ) ; } )
                     .orElse( TruthValueSet.n );
    }




    public static TruthValuePowerSet intersection( @NonNull final TruthValuePowerSet... values ){
        return Arrays.asList( values )
                     .stream()
                     .reduce( (x,y) -> { final Set<TruthValueSet> tmp = EnumSet.noneOf(TruthValueSet.class); tmp.addAll( x.getTruthValuePowerSet() ); tmp.retainAll( y.getTruthValuePowerSet() ); return TruthValuePowerSet.getByContent( tmp ) ; } )
                     .orElse( TruthValuePowerSet.n );
    }


    // Complement: Set of members that belong to set B "and not" set A.
    public static TruthValueSet complement( @NonNull final TruthValueSet a , @NonNull final TruthValueSet b ){
        return difference(b,a);
    }


    // Complement: Set of members that belong to set B "and not" set A.
    public static TruthValuePowerSet complement( @NonNull final TruthValuePowerSet a , @NonNull final TruthValuePowerSet b ){
        return difference(b,a);
    }


    // Difference: Set of members that belong to set A "and not" set B.
    public static TruthValueSet difference( @NonNull final TruthValueSet a , @NonNull final TruthValueSet b ){
        Set<TruthValue> result = EnumSet.noneOf(TruthValue.class);
        result.addAll( a.getTruthValueSet() );
        result.removeAll( b );
        return TruthValueSet.getByContent( result );
    }


    // Difference: Set of members that belong to set A "and not" set B.
    public static TruthValuePowerSet difference( @NonNull final TruthValuePowerSet a , @NonNull final TruthValuePowerSet b ){
        Set<TruthValueSet> result = EnumSet.noneOf(TruthValueSet.class);
        result.addAll( a.getTruthValuePowerSet() );
        result.removeAll( b );
        return TruthValuePowerSet.getByContent( result );
    }

    public static TruthValuePowerSet remove( @NonNull final TruthValueSet truthValueSet, @NonNull final TruthValuePowerSet truthValuePowerSet ){
        Set<TruthValueSet> tmp = truthValuePowerSet.getTruthValuePowerSet();
        tmp.remove( truthValueSet );
        return TruthValuePowerSet.getByContent( tmp );
    }

    public static long counter( @NonNull final Collection<?> items, @NonNull final Object value ){
        return items.stream()
                    .filter( (i) -> i == value )
                    .count();
    }

    public static TruthValueSet observedTruthValues( @NonNull final Set<Observation> observations ){
        final Set<TruthValue> set = EnumSet.noneOf( TruthValue.class);
        for( final Observation observation : observations)
            set.add( observation.getTruthValue() );
        set.remove(TruthValue.n);
        return TruthValueSet.getByContent( set );
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
            result = ( tvps1.size() < tvps2.size()) ? tvps1 : tvps2;
        return result;
    }

}
