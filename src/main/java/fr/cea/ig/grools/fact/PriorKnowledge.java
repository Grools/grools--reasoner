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

/**
 * PriorKnowledge
 */
/*
 * @startuml
 * skinparam defaultFontName  Monospaced
 * interface PriorKnowledge extends Concept{
 *  + getLabel()        : Label
 *  + getPrediction()   : TruthValuePowerSet
 *  + getExpectation()  : TruthValuePowerSet
 *  + getConclusion()   : Conclusion
 *  + getIsDispensable()  : boolean
 *  + getIsSpecific()   : boolean
 *  + setPrediction( TruthValuePowerSet values )
 *  + setExpectation( TruthValuePowerSet values )
 *  + setConclusion( Conclusion conclusion )
 *  + setIsDispensable( boolean value )
 *  + setIsSpecific( boolean value )
 * }
 * @enduml
 */
public interface PriorKnowledge extends Concept{
    Conclusion                      getConclusion();
    void                            setConclusion( Conclusion conclusion );
    TruthValuePowerSet              getPrediction();
    void                            setPrediction( final TruthValuePowerSet values );
    boolean                         getIsSpecific();
    void                            setIsSpecific( boolean isSpecific );
    boolean                         getIsDispensable();
    void                            setIsDispensable( boolean isSpecific );
    TruthValuePowerSet              getExpectation( );
    void                            setExpectation( final TruthValuePowerSet values );

}
