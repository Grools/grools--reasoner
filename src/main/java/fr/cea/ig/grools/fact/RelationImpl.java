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
import lombok.Getter;
import lombok.NonNull;

/**
 * RelationImpl
 */
public final class RelationImpl implements Relation {

    private static final long serialVersionUID = 857073565638296917L;

    @Getter
    private final Concept source;

    @Getter
    private final Concept target;

    @Getter
    private final Enum<?> type;

    @java.beans.ConstructorProperties( {"source", "target", "type"} )
    public RelationImpl( @NonNull final Concept source, @NonNull final Concept target, @NonNull final Enum<?> type){
        this.source = source;
        this.target = target;
        this.type   = type;
    }

    @java.beans.ConstructorProperties( {"source", "target"} )
    public RelationImpl( @NonNull final Observation source, @NonNull final PriorKnowledge target){
        this.source = source;
        this.target = target;
        this.type   = source.getType();
    }


    @Override
    public String toString(){
        final String s1 = (source != null )? source.getName() : "∅";
        final String s2 = (target != null )? target.getName() : "∅";
        return "Relation(\n" +
                       "    source   = " + s1      + '\n' +
                       "    target   = " + s2      + '\n' +
                       "    type     = " + type    + '\n' +
                       ")";
    }

    @Override
    public Object clone(){
        return new RelationImpl( source, target, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Relation relation = (Relation) o;

        if (!source.equals(relation.getSource())) return false;
        if (!target.equals(relation.getTarget())) return false;
        return type.equals(relation.getType());

    }

    @Override
    public int hashCode() {
        int result = source.hashCode();
        result = 31 * result + target.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
