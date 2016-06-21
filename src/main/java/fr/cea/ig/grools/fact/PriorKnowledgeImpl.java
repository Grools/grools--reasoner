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

import fr.cea.ig.grools.logic.Conclusion;
import fr.cea.ig.grools.logic.TruthValuePowerSet;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * PriorKnowledgeImpl
 */
public final class PriorKnowledgeImpl implements PriorKnowledge {

    private static final long serialVersionUID = 5468848075108935740L;

    @Getter
    private final String name;

    @Getter
    private final String source;

    @Getter
    private final String label;

    @Getter
    private final String description;

    @Getter @Setter
    private TruthValuePowerSet prediction;

    @Getter @Setter
    private TruthValuePowerSet expectation;

    @Getter @Setter
    private Conclusion conclusion;

    private boolean isDispensable;

    private boolean isSpecific;

    private final int hash;

    private static int hashCalculation( @NonNull final String name, @NonNull final String source,
                                        @NonNull final String label, @NonNull final String description ){
        int result = name.hashCode();
        result = 31 * result + source.hashCode();
        result = 31 * result + label.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Builder
    @java.beans.ConstructorProperties({"name", "source", "label", "description", "prediction", "expectation", "conclusion", "isDispensable", "isSpecific"})
    PriorKnowledgeImpl( @NonNull final String name, final String source, final String label, final String description, final TruthValuePowerSet prediction, final TruthValuePowerSet expectation, final Conclusion conclusion, final Boolean isDispensable, final Boolean isSpecific) {
        this.name           = name;
        this.source         = ( source          == null ) ? "unknown"               : source;
        this.label          = ( label           == null ) ? ""                      : label;
        this.description    = ( description     == null ) ? ""                      : description;
        this.prediction     = ( prediction      == null ) ? TruthValuePowerSet.n    : prediction;
        this.expectation    = ( expectation     == null ) ? TruthValuePowerSet.n    : expectation;
        this.conclusion     = ( conclusion      == null ) ? Conclusion.UNEXPLAINED  : conclusion;
        this.isDispensable  = ( isDispensable   == null ) ? false                   : isDispensable;
        this.isSpecific     = ( isSpecific      == null ) ? false                   : isSpecific;
        this.hash           = hashCalculation( this.name, this.source, this.label, this.description);
    }

    @Override
    public boolean getIsDispensable() {
        return isDispensable;
    }

    @Override
    public void setIsDispensable( boolean value ) {
        isDispensable = value;
    }

    @Override
    public boolean getIsSpecific() {
        return isSpecific;
    }

    @Override
    public void setIsSpecific( boolean value ) {
        isSpecific = value;
    }

    @Override
    public String toString(){
        return "PriorKnowledge(" + '\n' +
                       "    name                       = " + name           + '\n' +
                       "    source                     = " + source         + '\n' +
                       "    prediction                 = " + prediction     + '\n' +
                       "    expectation                = " + expectation    + '\n' +
                       "    conclusion                 = " + conclusion     + '\n' +
                       "    isDispensable              = " + isDispensable  + '\n' +
                       "    isSpecific                 = " + isSpecific     + '\n' +
                       ")";
    }

    @Override
    public Object clone(){
        return builder().name( name )
                        .source( source )
                        .prediction( prediction )
                        .expectation( expectation )
                        .conclusion( conclusion )
                        .isDispensable( isDispensable )
                        .isSpecific( isSpecific )
                        .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ! (o instanceof PriorKnowledge)) return false;

        PriorKnowledge that = (PriorKnowledge) o;

        if (isDispensable   !=      that.getIsDispensable() ) return false;
        if (isSpecific      !=      that.getIsSpecific()    ) return false;
        if (! name         .equals( that.getName()        ) ) return false;
        if (! source       .equals( that.getSource()      ) ) return false;
        if (! label        .equals( that.getLabel()       ) ) return false;
        if (! description  .equals( that.getDescription() ) ) return false;
        if (prediction      !=      that.getPrediction()    ) return false;
        if (expectation     !=      that.getExpectation()   ) return false;
        return conclusion == that.getConclusion();

    }

    @Override
    public int hashCode() {
        return hash;
    }
}
