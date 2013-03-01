package evolalgo.problem;

import evolalgo.IIndividual;
import evolalgo.IPhenotype;
import evolalgo.IndividualImpl;
import evolalgo.problem.sdm.ISDM;
import evolalgo.problem.sdm.WaveformDistance;
import java.awt.Color;
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
import org.math.plot.Plot2DPanel;

/**
 *
 * @author Odd
 */
public class SpikingNeuron implements IProblem{
    
    public double[] target;
    ISDM sdm;
    /**
     * The length of the bit describing each variable in the problem. This is hardcoded
     * because I don't see much use in letting it be changed once I feel I've struck
     * a good balance: For instance the variable "k" goes from 0.01-1.0 and a bit
     * length of 7 would describe 128 possible values (2^7, [0, 127]).
     */
    public static final int BIT_LENGTH = 15;
    
    /**
     * 
     * @param file Training data 1 to 4
     */
    public SpikingNeuron(int file, ISDM sdm){
        this.sdm = sdm;
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
            Logger.getLogger(SpikingNeuron.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void developPheno(IIndividual individual) throws Exception {
        String gene = (String) individual.getGenes();
        double[] attribs = new double[5];
        for (int i = 0; i < 5; i++){
            String sub = gene.substring((i * BIT_LENGTH), (i * BIT_LENGTH) + BIT_LENGTH);
            attribs[i] = (double) Integer.parseInt(sub, 2);
        }
        individual.setPhenotype(new SNPhenotype(attribs));
    }
    @Override
    public void calculateFitness(List<IIndividual> population) throws Exception {
        final double T = 10.0;
        final double I = 10.0;
        double longestDistance = 0.0;
        for (int i = 0; i < population.size(); i++){
            SNPhenotype pheno = (SNPhenotype) population.get(i).phenotype();
            try{
                population.get(i).fitness();
            }catch (Exception e){
                double v = -60.0;
                double u = 0.0;
                double[] valueArray = new double[target.length];
                valueArray[0] = v;
                for (int j = 1; j < target.length; j++){
                    if (v >= 35.0){
                        v = pheno.c;
                        u += pheno.d;
                    }
                    double vd = ((pheno.k * Math.pow(v, 2.0)) + 5.0*v + 140.0 - u + I) / T;
                    double ud = (pheno.a / T) * (pheno.b * v - u);
                    u += ud;
                    v += vd;
                    if (v >= 35.0) valueArray[j] = 35.0;
                    else valueArray[j] = v;
                }
                pheno.spiketrain = valueArray;
                pheno.distance = sdm.calculateDistance(target, valueArray);
                if (pheno.distance < 0) System.out.println("ERROR, distance negative");
                if (pheno.distance > longestDistance) longestDistance = pheno.distance;
            }
        }
        //TODO: Fullfør fitnesskalkulering, denne kan være merkelig!
        for (int i = 0; i < population.size(); i++){
            try{
                population.get(i).fitness();
            }catch(Exception e){
                SNPhenotype pheno = (SNPhenotype) population.get(i).phenotype();
                double percentDistance = 1.0 - (pheno.distance / longestDistance);
                population.get(i).setFitness(percentDistance);
            }
        }
    }

    @Override
    public List<IIndividual> createPopulation(int individuals) {
        List<IIndividual> population = new ArrayList<IIndividual>();
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
    
    public static void main(String[] args){
//        ISDM sdm = new SpikeTimeDistance();
    	ISDM sdm = new WaveformDistance();
        SpikingNeuron sp = new SpikingNeuron(1, sdm);
        SpikingNeuron sp1 = new SpikingNeuron(2, sdm);
        SpikingNeuron sp2 = new SpikingNeuron(3, sdm);
        SpikingNeuron sp3 = new SpikingNeuron(4, sdm);
        
        List<IIndividual> pop = sp.createPopulation(1);
        try{
            sp.developPheno(pop.get(0));
            sp.calculateFitness(pop);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        SNPhenotype sn = (SNPhenotype) pop.get(0).phenotype();
        System.out.println(sn);
        System.out.println(sn.spiketrain[0]);
        Plot2DPanel plot = new Plot2DPanel();
        plot.addLinePlot("Spike train", Color.BLUE, sn.spiketrain);
        plot.addLegend("SOUTH");
        plot.addLinePlot("Target 1", Color.RED, sp.target);
        plot.addLinePlot("Target 2", Color.YELLOW, sp1.target);
        plot.addLinePlot("Target 3", Color.GREEN, sp2.target);
        plot.addLinePlot("Target 4", Color.BLACK, sp3.target);
        plot.addLegend("SOUTH");
        javax.swing.JFrame frame = new javax.swing.JFrame(pop.get(0).phenotype().toString());
        frame.setContentPane(plot);
        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        System.out.println(sdm.calculateDistance(sp.target, sp3.target));
    }
}

class SNPhenotype implements IPhenotype{
    /**
     * How many values do we get from the bit length? For instance a length of
     * 7 gives you 128 values.
     */
    public static final double BIT_VALUES = Math.pow(2.0, (double) SpikingNeuron.BIT_LENGTH);
    /**
     * The parameter a has a valid range of [0.001, 0.2]
     */
    public double a;
    private static final double A_SCALER = (0.2 - 0.001) / BIT_VALUES;
    private static final double A_MIN = 0.001;
    /**
     * The parameter b has a valid range of [0.01, 0.3]
     */
    public double b;
    private static final double B_SCALER = (0.3 - 0.01) / BIT_VALUES;
    private static final double B_MIN = 0.01;
    /**
     * The parameter c has a valid range of [-80, -30]
     */
    public double c;
    private static final double C_SCALER = (-30.0 - -80.0) / BIT_VALUES;
    private static final double C_MIN = -80.0;
    /**
     * The parameter d has a valid range of [0.1, 10]
     */
    public double d;
    private static final double D_SCALER = (10.0 - 0.1) / BIT_VALUES;
    private static final double D_MIN = 0.1;
    /**
     * The parameter k has a valid range of [0.01, 1.0]
     */
    public double k;
    private static final double K_SCALER = (1.0 - 0.01) / BIT_VALUES;
    private static final double K_MIN = 0.01;
    
    public double[] spiketrain;
    
    public double distance;

    public SNPhenotype(double a, double b, double c, double d, double k) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.k = k;
    }
    public SNPhenotype(double[] attribs){
        a = A_MIN + (A_SCALER * (attribs[0]));
        b = B_MIN + (B_SCALER * (attribs[1]));
        c = C_MIN + C_SCALER * (attribs[2]);
        d = D_MIN + (D_SCALER * (attribs[3]));
        k = K_MIN + (K_SCALER * (attribs[4]));
    }

    @Override
    public String toString() {
        //return "A: " + a + ", B: " + b + ", C: " + c + ", D: " + d + ", K: " + k;
        String valueString = "";
        if(spiketrain == null){
            System.out.println("Distance, spiketrain is null?!: " + distance);
        }
        for (int i = 0; i < spiketrain.length; i++){
            valueString += spiketrain[i] + " ";
        }
        return valueString;
    }
}