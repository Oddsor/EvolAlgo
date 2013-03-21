package evolalgo.problem.ctrnn;

import evoalgo.tracker.Simulation;
import evolalgo.Evolution;
import evolalgo.IIndividual;
import evolalgo.IReproduction;
import evolalgo.IndividualImpl;
import evolalgo.ReproductionImpl;
import evolalgo.adultselectors.FullGenReplacement;
import evolalgo.adultselectors.IAdultSelection;
import evolalgo.parentselectors.FitnessProportionate;
import evolalgo.parentselectors.IParentSelection;
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
    private Simulation sim = new Simulation();

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
        for (IIndividual iIndividual : population) {
			double score = (double) sim.simulate((ITracker)iIndividual.phenotype());
        	iIndividual.setFitness(score/40.0);
		}
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
    
    public static void main(String[] args){
        IReproduction rep = new ReproductionImpl(0.05, 0.8, 1, 1);
        IAdultSelection adSel = new FullGenReplacement();
        IParentSelection parSel = new FitnessProportionate();
        IProblem problem = new CtrnnProblem();
        Evolution evo = new Evolution(30, rep, adSel, parSel, problem);
        try{
            evo.loop(10, true);
            evo.drawBestFitnessPlot();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
