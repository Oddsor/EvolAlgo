package evolalgo.parentselectors;

import evolalgo.Individual;
import java.util.List;

/**
 * All parent selection mechanisms must implement this interface to be accepted
 * by the algorithm.
 * @author Odd
 */
public interface IParentSelection {
    /**
     * Pick one parent out of the entire population
     * @param population List of individuals
     * @return The chosen parent
     * @throws Exception 
     */
    public Individual getParent(List<Individual> population) throws Exception;
}