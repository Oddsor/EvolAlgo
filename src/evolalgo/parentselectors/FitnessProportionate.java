/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolalgo.parentselectors;

import evolalgo.IIndividual;
import java.util.List;

/**
 * Selects parent by spinning a wheel of size 1.0 where all adults are weighted
 * by their fitness value
 * @author Odd
 */
public class FitnessProportionate extends ParentSelection 
        implements IParentSelection{
    @Override
    public IIndividual getParent(List<IIndividual> population) 
            throws Exception{
        //Sum up total fitness in the population
        double totalFitness = 0.0;
        for(IIndividual i: population){
            totalFitness += i.fitness();
        }
        double[] weights = new double[population.size()];
        
        double fitnessCount = 0.0;
        
        for(int i = 0; i < population.size(); i++){
            fitnessCount += population.get(i).fitness() / totalFitness;
            weights[i] = fitnessCount;
        }
        return super.spinWheel(weights, population);
    }
}
