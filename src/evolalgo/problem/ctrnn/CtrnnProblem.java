package evolalgo.problem.ctrnn;

import evolalgo.IIndividual;
import evolalgo.IndividualImpl;
import evolalgo.problem.IProblem;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Odd
 */
public class CtrnnProblem implements IProblem{
    
    private static final int BIT_SIZE = 8;
    private static final int NUM_ATTRIBUTES = 34;

    @Override
    public void developPheno(IIndividual individual) throws Exception {
        
        String gene = (String) individual.getGenes();
        List<Integer> attribs = new ArrayList<Integer>();
        for (int i = 0; i < NUM_ATTRIBUTES; i++){
            int value = Integer.parseInt(gene.substring(i * BIT_SIZE, (i * BIT_SIZE) + BIT_SIZE), 2);
            attribs.add(value);
        }
        individual.setPhenotype(new CtrnnPhenotype(attribs));
        
    }

    @Override
    public void calculateFitness(List<IIndividual> population) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IIndividual> createPopulation(int individuals) {
        Random rand = new Random();
        List<IIndividual> population = new ArrayList<IIndividual>();
        for (int i = 0; i < individuals; i++){
            String genome = "";
            for (int j = 0; j < BIT_SIZE * NUM_ATTRIBUTES; j++){
                genome += rand.nextInt(2);
            }
            population.add(new IndividualImpl(genome));
        }
        return population;
    }
}
