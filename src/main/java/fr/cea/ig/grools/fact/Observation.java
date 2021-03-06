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

package fr.cea.ig.grools.fact;

import fr.cea.ig.grools.logic.TruthValue;
import fr.cea.ig.grools.logic.TruthValueSet;
import lombok.NonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Observation
 */
/*
 * @startuml
 * skinparam defaultFontName  Monospaced
 * interface Observation extends Concept{
 *  + getType()        : ObservationType
 *  + getPrediction()  : TruthValue
 * }
 * @enduml
 */
public interface Observation extends Concept {
    ObservationType     getType();
    TruthValue          getTruthValue();

    static TruthValueSet union(@NonNull final Observation... observations){
         return union(Arrays.asList(observations)
                                   .stream()
                                   .collect( Collectors.toSet() ));
    }

    static TruthValueSet union(@NonNull final Collection<Observation> observations){
        Set<TruthValue> truthValueSet = observations.stream()
                                                    .map( Observation::getTruthValue )
                                                    .collect( Collectors.toCollection(() -> EnumSet.noneOf(TruthValue.class)) );

        return TruthValue.union(truthValueSet);
    }
}
