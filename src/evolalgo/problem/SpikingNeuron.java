package evolalgo.problem;

import evolalgo.IIndividual;
import evolalgo.IPhenotype;
import evolalgo.IndividualImpl;
import evolalgo.problem.sdm.ISDM;
import evolalgo.problem.sdm.SpikeTimeDistance;
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
 * The spiking neuron problem simulates a single artificial neuron by using a
 * formula consisting of 5 parameters. The calculations simulates a "spiking"
 * pattern.
 * @author Odd
 */
public class SpikingNeuron implements IProblem{
    
    double[] target;
    ISDM sdm;
    
    /**
     * For this problem we need to input a Spike-time Distance Metric to compare
     * the traits found by evolution against some target data. These methods
     * must use the ISDM-interface. <br/>
     * 
     * The target data is provided in the assignment through four files.
     * @param file Training data, files [1, 4].
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
    
    /**
     * The length of the bit describing each variable in the problem. This is hardcoded
     * because I don't see much use in letting it be changed once I feel I've struck
     * a good balance: For instance the variable "k" goes from 0.01-1.0 and a bit
     * length of 7 describes 128 possible values (2^7, [0, 127]).
     */
    public static final int BIT_LENGTH = 7;
    
    @Override
    public void developPheno(IIndividual individual) throws Exception {
        String gene = (String) individual.getGenes();
        double[] attribs = new double[5];
        for (int i = 0; i < 5; i++){
            String sub = gene.substring((i * BIT_LENGTH), (i * BIT_LENGTH) + BIT_LENGTH);
            attribs[i] = (double) Integer.parseInt(sub, 2) + 1.0;
            System.out.println(sub + " = " + (Integer.parseInt(sub, 2) + 1.0));
        }
        individual.setPhenotype(new SNPhenotype(attribs));
    }

    @Override
    public void calculateFitness(List<IIndividual> population) throws Exception {
        final double T = 10.0;
        final double I = 10.0;
        
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
                    double vd = (1.0 / T) * (pheno.k * Math.pow(v, 2.0) + 5.0*v + 140.0 - u + I);
                    double ud = (pheno.a / T) * (pheno.b * v - u);
                    v += vd;
                    u += ud;
                    if(v >= 35.0) valueArray[j] = 35.0;
                    //else if(v <= -60.0) valueArray[j] = -80.0;
                    else valueArray[j] = v;
                }
                pheno.spiketrain = valueArray;
                //TODO fitness may need to be scaled somehow.
                //population.get(i).setFitness(sdm.calculateDistance(target, valueArray));
                System.out.println("Distance-calc: ");
                System.out.println(sdm.calculateDistance(target, valueArray));
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
        ISDM sdm = new SpikeTimeDistance();
        SpikingNeuron sp = new SpikingNeuron(1, sdm);
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
        javax.swing.JFrame frame = new javax.swing.JFrame("a plot panel");
        frame.setContentPane(plot);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

/**
 * The phenotype for this problem is in 
 * @author Odd
 */
class SNPhenotype implements IPhenotype{
    /**
     * How many values do we get from the bit length? For instance a length of
     * 7 gives you 128 values.
     */
    public static final double BIT_VALUES = Math.pow(2.0, (double) SpikingNeuron.BIT_LENGTH);
    /**
     * The parameter a has a valid range of [0.001, 0.2]
     */
    public final double a;
    private static final double A_SCALER = (0.2 - 0.001) / BIT_VALUES;
    /**
     * The parameter b has a valid range of [0.01, 0.3]
     */
    public final double b;
    private static final double B_SCALER = (0.3 - 0.01) / BIT_VALUES;
    /**
     * The parameter c has a valid range of [-80, -30]
     */
    public final double c;
    private static final double C_SCALER = (-30 - -80) / BIT_VALUES;
    private static final double C_MIN = -80;
    /**
     * The parameter d has a valid range of [0.1, 10]
     */
    public final double d;
    private static final double D_SCALER = (10 - 0.1) / BIT_VALUES;
    /**
     * The parameter k has a valid range of [0.01, 1.0]
     */
    public final double k;
    private static final double K_SCALER = (1.0 - 0.01) / BIT_VALUES;
    
    public double[] spiketrain;

    public SNPhenotype(double a, double b, double c, double d, double k) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.k = k;
    }
    public SNPhenotype(double[] attribs){
        a = A_SCALER * (attribs[0]);
        b = B_SCALER * (attribs[1]);
        c = C_MIN + C_SCALER * (attribs[2]);
        d = D_SCALER * (attribs[3]);
        k = K_SCALER * (attribs[4]);
    }

    @Override
    public String toString() {
        for(int i = 0; i < spiketrain.length; i++){
            System.out.print(spiketrain[i] + " ");
        }
        System.out.println("");
        return "A: " + a + ", B: " + b + ", C: " + c + ", D: " + d + ", K: " + k;
    }
}
