package evolalgo.problem.ctrnn;

import evoalgo.problem.ctrnn.trackerSim.HitAndAvoidAwarder;
import evoalgo.problem.ctrnn.trackerSim.IPointAwarder;
import evoalgo.problem.ctrnn.trackerSim.Simulation;
import evoalgo.problem.ctrnn.trackerSim.SimulationAnimation;
import evolalgo.Evolution;
import evolalgo.IIndividual;
import evolalgo.IReproduction;
import evolalgo.IndividualImpl;
import evolalgo.BinaryStrings;
import evolalgo.adultselectors.GenerationalMixing;
import evolalgo.adultselectors.IAdultSelection;
import evolalgo.parentselectors.IParentSelection;
import evolalgo.parentselectors.SigmaScaling;
import evolalgo.parentselectors.Tournament;
import evolalgo.problem.IProblem;
import java.awt.Color;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.math.plot.Plot2DPanel;

/**
 *
 * @author Odd
 */
public class CtrnnProblem implements IProblem{
    
    public static final int BIT_SIZE = 8;
    
    public static final int MOTOR_TUGOFWAR = 0;
    public static final int MOTOR_MOSTEAGER_BIDIRECTIONAL = 1;
    public static final int MOTOR_MOSTEAGER_SEPARATE = 2;
    
    private int motorType;
    private Simulation sim = new Simulation();
    private IPointAwarder awarder;
    private int trackerWidth;
    private int hiddenNeurons;
    
    public CtrnnProblem(IPointAwarder awarder, int hiddenNeurons, int trackerWidth, int motorType){
        this.awarder = awarder;
        this.trackerWidth = trackerWidth;
        this.hiddenNeurons = hiddenNeurons;
        
        this.motorType = motorType;
    }

    /**
     * Calculate how many attributes we need to form all weights, gains, biases
     * and time constants. For example: a tracker width of 5 and 2 hidden neurons
     * will output 34.
     * @return 
     */
    private int numAttributes(){
        int numAttributes = trackerWidth * hiddenNeurons; //connections from input layer to hidden layer
        numAttributes += ((hiddenNeurons + 2) * 4); //Bias, gain, time constant, selfweight for all neurons
        numAttributes += (hiddenNeurons * (hiddenNeurons - 1)); //Connections between hidden neurons
        numAttributes += (hiddenNeurons * 2); //Connections from hidden neurons to motors
        numAttributes += 2; //Two connections between motor neurons.
        return numAttributes;
    }
    
    @Override
    public void developPheno(IIndividual individual) throws Exception {
        
        String gene = (String) individual.getGenes();
        List<Integer> attribs = new ArrayList<Integer>();
        for (int i = 0; i < numAttributes(); i++){
            int value = Integer.parseInt(gene.substring(i * BIT_SIZE, (i * BIT_SIZE) + BIT_SIZE), 2);
            attribs.add(value);
        }
        individual.setPhenotype(new CtrnnPhenotype(attribs, hiddenNeurons, trackerWidth, motorType));
        
    }

    @Override
    public void calculateFitness(List<IIndividual> population) throws Exception {
        int NUM_RUNS = 3;
        for (IIndividual iIndividual : population) {
            double score = 0.0;
                for(int i = 0; i < NUM_RUNS; i++){
                    score += (double) sim.simulate((ITracker)iIndividual.phenotype(), awarder);
                }
        	iIndividual.setFitness(score/(double) NUM_RUNS);
		}
    }

    @Override
    public List<IIndividual> createPopulation(int individuals) {
        Random rand = new Random();
        List<IIndividual> population = new ArrayList<IIndividual>();
        for (int i = 0; i < individuals; i++){
            String genome = "";
            for (int j = 0; j < BIT_SIZE * numAttributes(); j++){
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
                    long start = System.currentTimeMillis();
                    Date d = new Date(start);
                    System.out.println("Started run at " + d.toString());
                    //IReproduction rep = new BinaryStrings(0.15, 0.8, 2, 1);
                    IReproduction rep = new BinaryCTRNNStrings(0.15, 0.8, 1);
                    IAdultSelection adSel = new GenerationalMixing(10);
                    //IParentSelection parSel = new FitnessProportionate();
                    IParentSelection parSel = new SigmaScaling();
                    //IParentSelection parSel = new Tournament(10, 0.3);
                    IPointAwarder rewarder = new HitAndAvoidAwarder();
                    IProblem problem = new CtrnnProblem(rewarder, 2, 5, MOTOR_TUGOFWAR);

                    int POPULATION = 100;
                    int GENERATIONS = 200;
                    Evolution evo = new Evolution(POPULATION, rep, adSel, parSel, problem);
                    Plot2DPanel plot = new Plot2DPanel();
                    double[] Y = new double[1];
                    
                    plot.addLinePlot("Fitness of best individual", Color.BLUE, Y);
                    //plot.addScatterPlot("", scale);
                    //plot.addScatterPlot("", scale2);
                    javax.swing.JFrame frame = new javax.swing.JFrame("Best of generation");
                    frame.setContentPane(plot);
                    frame.setSize(500, 400);
                    frame.setVisible(true);
                    List<IIndividual> pop = problem.createPopulation(POPULATION);
                    for (int j = 0; j < GENERATIONS; j++){
                        double[] newY = new double[j + 1];
                        for(int k = 0; k < Y.length; k++){
                            newY[k] = Y[k];
                        }
                        Y = newY;
                        try{
                            pop = evo.runGeneration(pop);
                            Map m = evo.getStatistics().get(evo.getStatistics().size() - 1);
                            Y[j] = Double.parseDouble(m.get("maxFitness").toString());
                            System.out.println(j + ", " + Y[j]);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        plot.removeAllPlots();
                        plot.addLinePlot("Fitness of best individual", Color.BLUE, Y);
                    }
                    long end = System.currentTimeMillis();
                    long time = end - start;
                    int seconds = (int) time % 1000;
                    int minutes = seconds % 60;
                    System.out.println("Time spent: " + minutes + " minutes, " + seconds + "seconds");
                    List<Map> stats = evo.getStatistics();
                    evo.drawBestFitnessPlot();
                    IIndividual ind = (IIndividual) stats.get(stats.size()-1).get("bestIndividual");
                    ITracker tr = (ITracker) ind.phenotype();
                    System.out.println("Final tracker: " + ind.toString());
                    SimulationAnimation simAn = new SimulationAnimation(tr, rewarder);
                }
            };
            evoT.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
