/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    private int BIT_SIZE = 8;

    @Override
    public void developPheno(IIndividual individual) throws Exception {
        
        String gene = (String) individual.getGenes();
        int[] attributes = new int[4];
        for (int i = 0; i < attributes.length; i++){
            int value = Integer.parseInt(gene.substring(i, i + BIT_SIZE), 2);
            attributes[i] = value;
        }
        individual.setPhenotype(new CtrnnPhenotype(attributes));
        
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
            for (int j = 0; j < BIT_SIZE * 4; j++){
                genome += rand.nextInt(2);
            }
            population.add(new IndividualImpl(genome));
        }
        return population;
    }
}
