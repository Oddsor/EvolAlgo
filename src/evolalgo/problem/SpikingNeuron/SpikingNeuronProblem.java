package evolalgo.problem.SpikingNeuron;

import evolalgo.problem.SpikingNeuron.sdm.ISDM;
import evolalgo.Individual;
import evolalgo.IndividualImpl;
import evolalgo.problem.IProblem;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This problem attempts to find the parameters that were used to draw a
 * plot, a type of <i>curve fitting</i>. Our randomly generated individuals
 * contain a genome that is translated into five parameter values, and then
 * these variables are used to form a plot.
 * 
 * @param file Training data, a value from 1 to 4 representing four files
 * @param sdm The spiking distance metric that we choose use.
 * @param logarithmic We can calculate fitness logarithmically or regularly.
 * Logarithmic calculation gives us a wider spread of fitness in the best individuals
 * while the bad individuals get grouped together near the bottom. In other words
 * bad individuals don't push good ones too closely together.
 * 
 * @author Odd Andreas Sørsæther
 * @author Andreas Hagen
 */
public class SpikingNeuronProblem implements IProblem{
    final double T = 10.0;
    final double I = 10.0;
    double longestDistance = 0.0; //Is it a bad idea to compare fitness values to the longest EVER detected distance metric?
    /**
     * This is the plot of the target neuron we evaluate.
     */
    public double[] target;
    ISDM sdm;
    private boolean logarithmic;
    /**
     * The length of the bit describing each variable in the problem. This is hardcoded
     * because I don't see much use in letting it be changed once I feel I've struck
     * a good balance: For instance the variable "k" goes from 0.01-1.0 and a bit
     * length of 7 would describe 128 possible values (2^7, [0, 127]).
     */
    public static final int BIT_LENGTH = 15;

    public SpikingNeuronProblem(int file, ISDM sdm, boolean logarithmic){
        
        this.sdm = sdm;
        this.logarithmic = logarithmic;
        Path path = FileSystems.getDefault().getPath("libs/trainingdata", 
                "izzy-train" + file + ".dat");
        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            String readerString = reader.readLine();
            String[] splitReader = readerString.split(" ");
            target = new double[splitReader.length];
            for (int i = 0; i < target.length; i++){
                target[i] = Double.parseDouble(splitReader[i].trim());
            }
        } catch (IOException ex) {
            Logger.getLogger(SpikingNeuronProblem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void developPheno(Individual individual) throws Exception {
        
        String gene = (String) individual.getGenes();
        double[] attribs = new double[5];
        for (int i = 0; i < 5; i++){
            String sub = gene.substring((i * BIT_LENGTH), (i * BIT_LENGTH) + BIT_LENGTH);
            attribs[i] = (double) Integer.parseInt(sub, 2);
        }
        individual.setPhenotype(new SpikingNeuronPhenotype(attribs));
        
    }
    
    @Override
    public void calculateFitness(List<Individual> population) throws Exception {
        
        for (int i = 0; i < population.size(); i++){
            SpikingNeuronPhenotype pheno = (SpikingNeuronPhenotype) population.get(i).phenotype();
            calculateSpikeTrain(pheno);
            double percentDistance;
            if(logarithmic) percentDistance = 1.0 - (Math.log(pheno.distance + 1.0) / Math.log(longestDistance + 1.0));
            else percentDistance = 1.0 - pheno.distance / longestDistance;
            population.get(i).setFitness(percentDistance);
        }
        
    }

    private void calculateSpikeTrain(SpikingNeuronPhenotype pheno){
        
    	double v = -60.0;
        double u = 0.0;
        double[] voltageArray = new double[target.length];
        voltageArray[0] = v;
        for (int j = 1; j < target.length; j++){
            if (v >= 35.0){
                v = pheno.c;
                u += pheno.d;
            }
            double vd = ((pheno.k * Math.pow(v, 2.0)) + 5.0*v + 140.0 - u + I) / T;
            double ud = (pheno.a / T) * (pheno.b * v - u);
            u += ud;
            v += vd;
            if (v >= 35.0) voltageArray[j] = 35.0;
            else voltageArray[j] = v;
        }
        pheno.spiketrain = voltageArray;
        pheno.distance = sdm.calculateDistance(target, voltageArray);
        if (pheno.distance < 0) System.out.println("ERROR, distance negative");
        if (pheno.distance > longestDistance) longestDistance = pheno.distance;
        
    }
    
    @Override
    public List<Individual> createPopulation(int individuals) {
        
        List<Individual> population = new ArrayList<Individual>();
        Random rand = new Random();
        while(population.size() < individuals){
            String gene = "";
            while (gene.length() < BIT_LENGTH * 5){
                gene += rand.nextInt(2);
            }
            population.add(new IndividualImpl(gene));
        }
        return population;
        
    }
}
