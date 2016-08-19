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
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * ObservationImpl
 */
public final class ObservationImpl implements Observation {

    @Getter
    private final String name;
    @Getter
    private final String source;
    @Getter
    private final String label;
    @Getter
    private final String description;
    @Getter
    private final ObservationType type;
    @Getter
    private final TruthValue truthValue;

    private final int hash;

    private static int hashCalculation( @NonNull final String name, @NonNull final String source,
                                        @NonNull final String label, @NonNull final String description,
                                        @NonNull final ObservationType type, @NonNull final TruthValue truthValue){

        int result = name.hashCode();
        result = 31 * result + source.hashCode();
        result = 31 * result + label.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + truthValue.hashCode();
        return result;
    }

    @Builder
    @java.beans.ConstructorProperties({"name", "source", "label", "description", "types", "prediction"})
    public ObservationImpl( @NonNull final String name, final String source,
                            final String label, final String description,
                            @NonNull final ObservationType type, final TruthValue truthValue ) {
        this.name       = name;
        this.source     = (source == null) ? "unknown" : source;
        this.label      = (label == null) ? "" : label;
        this.description= (description == null) ? "" : description;
        this.type       = type;
        this.truthValue = (truthValue == null)? TruthValue.t : truthValue;
        this.hash       = hashCalculation( this.name, this.source, this.label, this.description, this.type, this.truthValue);
    }

    @Override
    public String toString(){
        return "Observation(" + '\n' +
                       "    name        = " + name         + '\n' +
                       "    source      = " + source       + '\n' +
                       "    type        = " + type         + '\n' +
                       "    value       = " + truthValue   + '\n' +
                       ")";
    }

    @Override
    public Object clone(){
        return builder().name( name )
                        .source( source )
                        .type( type )
                        .truthValue( truthValue )
                        .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Observation that = (Observation) o;

        if (!name.equals(that.getName())) return false;
        if (!source.equals(that.getSource())) return false;
        if (!label.equals(that.getLabel())) return false;
        if (!description.equals(that.getDescription())) return false;
        if (type != that.getType()) return false;
        return truthValue == that.getTruthValue();
    }

    @Override
    public int hashCode() {
        return hash;
    }

}
