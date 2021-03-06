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
import java.util.EnumSet;
import java.util.stream.Collectors;

/**
 * TruthValue
 */

public enum TruthValue{
    n( "∅", 0, 0),
    t( "t", true, 1, 0 ),
    f( "f", false, 0, 1 );

    @NonNull
    @Getter
    private final String name;
    @Getter
    private final Boolean value;
    @Getter
    private final float truth;
    @Getter
    private final float falsehood;

    TruthValue(@NonNull final String name, final Boolean value, float truth, float falsehood){
        this.name       = name;
        this.value      = value;
        this.truth      = truth;
        this.falsehood  = falsehood;
    }
    TruthValue( @NonNull final String name, float truth, float falsehood){
        this.name       = name;
        this.value      = null;
        this.truth      = truth;
        this.falsehood  = falsehood;
    }

    @Override
    public String toString(){
        return name;
    }

    public static TruthValueSet union( @NonNull final TruthValue... values){
        return union( Arrays.asList( values )
                            .stream()
                            .collect(Collectors.toCollection(() -> EnumSet.noneOf(TruthValue.class))));
    }

    public static TruthValueSet union(@NonNull final Collection<TruthValue> values){
        if( values.isEmpty() )
            values.add( TruthValue.n );
        else if( values.size() > 1 ) values.remove( TruthValue.n );
        return TruthValueSet.getByContent( values );
    }
}
