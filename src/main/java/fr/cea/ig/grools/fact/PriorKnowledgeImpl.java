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

    @Getter @Setter
    private TruthValuePowerSet prediction;
    @Getter @Setter
    private TruthValuePowerSet expectation;

    @Getter @Setter
    private Conclusion conclusion;

    private boolean isDispensable;

    private boolean isSpecific;

    @java.beans.ConstructorProperties({"name", "source", "prediction", "expectation", "conclusion", "isDispensable", "isSpecific"})
    PriorKnowledgeImpl( @NonNull final String name, @NonNull final String source, final TruthValuePowerSet prediction, final TruthValuePowerSet expectation, final Conclusion conclusion, boolean isDispensable, boolean isSpecific) {
        this.name           = name;
        this.source         = source;
        this.prediction     = prediction;
        this.expectation    = expectation;
        this.conclusion     = conclusion;
        this.isDispensable  = isDispensable;
        this.isSpecific     = isSpecific;
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

    public static PriorKnowledgeImplBuilder builder() {
        return new PriorKnowledgeImplBuilder();
    }

    public static class PriorKnowledgeImplBuilder {
        private String              name;
        private String              source;
        private TruthValuePowerSet  prediction;
        private TruthValuePowerSet  expectation;
        private Conclusion          conclusion;
        private boolean             isDispensable   = false;
        private boolean             isSpecific      = false;

        PriorKnowledgeImplBuilder() {
        }

        public PriorKnowledgeImplBuilder name(@NonNull final String name) {
            this.name = name;
            return this;
        }

        public PriorKnowledgeImplBuilder source(@NonNull final String source) {
            this.source = source;
            return this;
        }

        public PriorKnowledgeImplBuilder prediction(@NonNull final TruthValuePowerSet values) {
            this.prediction = values;
            return this;
        }

        public PriorKnowledgeImplBuilder expectation(@NonNull final TruthValuePowerSet values) {
            this.expectation = values;
            return this;
        }

        public PriorKnowledgeImplBuilder conclusion(@NonNull final Conclusion conclusion) {
            this.conclusion = conclusion;
            return this;
        }

        public PriorKnowledgeImplBuilder isDispensable(boolean isDispensable) {
            this.isDispensable = isDispensable;
            return this;
        }

        public PriorKnowledgeImplBuilder isSpecific(boolean isSpecific) {
            this.isSpecific = isSpecific;
            return this;
        }

        public PriorKnowledgeImpl build() {
            if( source == null )
                source = "Unknown";
            if( prediction == null )
                prediction = TruthValuePowerSet.n;
            if( expectation == null )
                expectation = TruthValuePowerSet.n;
            if( conclusion == null )
                conclusion = Conclusion.UNEXPLAINED;
            return new PriorKnowledgeImpl( name, source, prediction, expectation, conclusion, isDispensable, isSpecific);
        }

        public String toString() {
            return "PriorKnowledgeBuilder(name=" + this.name + ", source=" + this.source + ", prediction=" + this.prediction + ", expectation=" + this.expectation + ", conclusion=" + this.conclusion + ", isDispensable=" + this.isDispensable + ", isSpecific=" + this.isSpecific + ")";
        }
    }
}
