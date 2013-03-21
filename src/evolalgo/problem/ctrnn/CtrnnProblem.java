package evolalgo.problem.ctrnn;

import evoalgo.tracker.HitAndAvoidAwarder;
import evoalgo.tracker.Simulation;
import evoalgo.tracker.SimulationAnimation;
import evolalgo.Evolution;
import evolalgo.IIndividual;
import evolalgo.IReproduction;
import evolalgo.IndividualImpl;
import evolalgo.ReproductionImpl;
import evolalgo.adultselectors.GenerationalMixing;
import evolalgo.adultselectors.IAdultSelection;
import evolalgo.parentselectors.FitnessProportionate;
import evolalgo.parentselectors.IParentSelection;
import evolalgo.problem.IProblem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.math.plot.Plot2DPanel;

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
        try{
            Thread evoT = new Thread(){

                @Override
                public void run() {
                    IReproduction rep = new ReproductionImpl(0.11, 0.8, 1, 5);
                    IAdultSelection adSel = new GenerationalMixing(10);
                    IParentSelection parSel = new FitnessProportionate();
                    IProblem problem = new CtrnnProblem();
                    Evolution evo = new Evolution(20, rep, adSel, parSel, problem);
                    Plot2DPanel plot = new Plot2DPanel();
                    double[] Y = new double[15];
                    for (int i = 0; i < 15; i++){
                        Y[i] = 0;
                    }
                    double[] scale = {1.0};
                    double[] scale2 = {0.0};
                    plot.addLinePlot("Fitness of best individual", Color.BLUE, Y);
                    plot.addScatterPlot("", scale);
                    plot.addScatterPlot("", scale2);
                    plot.addLegend("SOUTH");
                    javax.swing.JFrame frame = new javax.swing.JFrame("Best of generation");
                    frame.setContentPane(plot);
                    frame.setSize(500, 400);
                    frame.setVisible(true);
                    List<IIndividual> pop = problem.createPopulation(50);
                    for (int j = 0; j < 15; j++){
                        try{
                            pop = evo.runGeneration(pop);
                            Map m = evo.getStatistics().get(evo.getStatistics().size() - 1);
                            Y[j] = Double.parseDouble(m.get("maxFitness").toString());
                            System.out.println(j + ", " + Y[j]);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        plot.removeAllPlots();
                        plot.addScatterPlot("", scale);
                        plot.addScatterPlot("", scale2);
                        plot.addLinePlot("Fitness of best individual", Color.BLUE, Y);
                    }
                    List<Map> stats = evo.getStatistics();
                    evo.drawBestFitnessPlot();
                    IIndividual ind = (IIndividual) stats.get(stats.size()-1).get("bestIndividual");
                    ITracker tr = (ITracker) ind.phenotype();
                    SimulationAnimation simAn = new SimulationAnimation(tr, new HitAndAvoidAwarder());
                }
            };
            evoT.start();   
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
