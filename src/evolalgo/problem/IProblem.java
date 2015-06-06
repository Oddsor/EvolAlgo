package evolalgo.problem;

import evolalgo.Individual;
import java.util.List;

/**
 * Interface for development methods. All methods that want to develop a genotype
 * need to implement this method
 * @author Odd
 */
public interface IProblem {
    
    /**
     * Converts the input genotype to a phenotype that suits the problem
     * @param geno Genotype in bit string form. We currently assume that all
     * problems will contain genotypes in bit string form.
     * @return Phenotype in the form of an array of objects. Can be anything, 
     * as the fitness evaluator is the only other class that relies on the phenotype,
     * and this needs to be implemented on a per problem basis anyway.
     * @throws Exception 
     */
    public void developPheno(Individual individual) throws Exception;
    
    public void calculateFitness(List<Individual> population) 
            throws Exception;
    public List<Individual> createPopulation(int individuals);
}
