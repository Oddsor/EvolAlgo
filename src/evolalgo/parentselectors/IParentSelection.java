
package evolalgo.parentselectors;

import evolalgo.IIndividual;
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
    public IIndividual getParent(List<IIndividual> population) throws Exception;
}