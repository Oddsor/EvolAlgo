/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessevaluators;

import evolalgo.IFitnessEval;
import evolalgo.IIndividual;
import java.util.List;

/**
 *
 * @author Odd
 */
public class BitFitness implements IFitnessEval{
    
    private Object[] targetString;
    
    public BitFitness(Object[] targetString){
        this.targetString = targetString;
    }

    @Override
    public List<IIndividual> calculateFitness(List<IIndividual> population) throws Exception{
        for(int i = 0; i < population.size(); i++){
            int fitnessFactor = 0;
            Object[] phenotype = population.get(i).phenotype();
            //Blow up targetString and compare each bit
            if(targetString.length != phenotype.length){
                throw new Exception("Phenotype and bit string not of equal length!");
            }

            for(int j = 0; j < phenotype.length; j++){
                if(phenotype[j].toString().equals(targetString[j].toString())){
                    fitnessFactor++;
                }
            }population.get(i).setFitness((double) fitnessFactor/ (double) phenotype.length);
        }
        
        

        return population;
    }
    
}
