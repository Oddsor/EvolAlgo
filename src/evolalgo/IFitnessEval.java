
package evolalgo;

import java.util.List;

/**
 * The fitness evaluation interface. All fitness evaluators must implement these
 * methods, as they are expected by the algorithm.
 * @author Odd
 */
public interface IFitnessEval {
    
    /**
     * Calculates fitness for the population. Need the entire population as 
     * parameter because the evaluation may depend on other individuals.
     * @param population
     * @return 
     * @throws Exception 
     */
    public List<IIndividual> calculateFitness(List<IIndividual> population) throws Exception;
}
