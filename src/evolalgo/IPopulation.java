
package evolalgo;

import java.util.List;

/**
 * Any class that wants to create a starter population must implement this interface.
 * @author Odd
 */
public interface IPopulation {
    /**
     * Creates a list of N genotypes of M size, where N and M are
     * parameters
     * @param individuals number of individuals in the population
     * @param size size of the randomly generated bit string that forms a genotype
     * @return List of genotypes.
     */
    public List<Object> createPopulation(int individuals, int size);
}
