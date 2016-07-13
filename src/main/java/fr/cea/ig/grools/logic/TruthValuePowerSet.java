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
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TruthValuePowerSet
 */
public enum TruthValuePowerSet implements Set<TruthValueSet> {

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
    public int size() {
        return truthValuePowerSet.size();
    }

    @Override
    public boolean isEmpty() {
        return truthValuePowerSet.isEmpty();
    }

    @Override
    public boolean contains( final Object o ) {
        return truthValuePowerSet.contains( o );
    }

    @Override
    public Iterator<TruthValueSet> iterator() {
        return truthValuePowerSet.iterator();
    }

    @Override
    public Object[] toArray() {
        return truthValuePowerSet.toArray(  );
    }

    @Override
    public <T> T[] toArray( final T[] a ) {
        return truthValuePowerSet.toArray( a );
    }

    @Override
    public boolean add( final TruthValueSet truthValues ) {
        return false;
    }

    @Override
    public boolean remove( final Object o ) {
        return false;
    }

    @Override
    public boolean containsAll( final Collection<?> c ) {
        return truthValuePowerSet.containsAll( c );
    }

    @Override
    public boolean addAll( final Collection<? extends TruthValueSet> c ) {
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
