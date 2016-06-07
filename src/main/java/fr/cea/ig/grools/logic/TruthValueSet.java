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

import java.lang.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * TruthValueSet
 */
public enum TruthValueSet implements Set<TruthValue> {

    n(  ),
    N( TruthValue.n ),
    T( true ),
    F( false ),
    B( true, false );

    public static TruthValueSet getByContent( @NonNull final Set<TruthValue> set ){
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


    TruthValueSet( @NonNull final List<TruthValue> tvList ){
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

    public TruthValueSet merge( @NonNull final TruthValueSet tvSet ){
        return Math.merge( this, tvSet );
    }

    public TruthValueSet intersection( @NonNull final TruthValueSet tvSet ){
        return Math.intersection( this, tvSet );
    }

    @Override
    public int size() {
        return truthValueSet.size();
    }

    @Override
    public boolean isEmpty() {
        return truthValueSet.isEmpty();
    }

    @Override
    public boolean contains( final Object o ) {
        return truthValueSet.contains( o );
    }

    @Override
    public Iterator<TruthValue> iterator() {
        return truthValueSet.iterator();
    }

    @Override
    public Object[] toArray() {
        return truthValueSet.toArray();
    }

    @Override
    public <T> T[] toArray( final T[] a ) {
        return truthValueSet.toArray( a );
    }

    @Override
    public boolean add( final TruthValue aTruthValue ) {
        return false;
    }

    @Override
    public boolean remove( final Object o ) {
        return false;
    }

    @Override
    public boolean containsAll( final Collection<?> c ) {
        return truthValueSet.containsAll( c );
    }

    @Override
    public boolean addAll( final Collection<? extends TruthValue> c ) {
        return false;
    }

    @Override
    public boolean retainAll( final Collection<?> c ) {
        return false;
    }

    @Override
    public boolean removeAll( final Collection<?> c ) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public String toString(){
        return name;
    }


}
