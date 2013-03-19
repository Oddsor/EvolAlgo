package evolalgo.problem.SpikingNeuron;

import evolalgo.IPhenotype;
import java.text.DecimalFormat;

/**
 * 
 * @author Odd Andreas Sørsæther
 */
public class SpikingNeuronPhenotype implements IPhenotype{
    /**
     * How many values do we get from the bit length? For instance a length of
     * 7 gives you 128 values.
     */
    public static final double BIT_VALUES = Math.pow(2.0, (double) SpikingNeuronProblem.BIT_LENGTH);
    /**
     * The parameter a has a valid range of [0.001, 0.2]
     */
    public double a;
    public static final double A_MIN = 0.001;
    public static final double A_MAX = 0.02;
    private static final double A_SCALER = (A_MAX - A_MIN) / BIT_VALUES;
    /**
     * The parameter b has a valid range of [0.01, 0.3]
     */
    public double b;
    public static final double B_MIN = 0.01;
    public static final double B_MAX = 0.3;
    private static final double B_SCALER = (B_MAX - B_MIN) / BIT_VALUES;
    /**
     * The parameter c has a valid range of [-80, -30]
     */
    public double c;
    public static final double C_MIN = -80.0;
    public static final double C_MAX = -30.0;
    private static final double C_SCALER = Math.abs((C_MAX - C_MIN) / BIT_VALUES);
    /**
     * The parameter d has a valid range of [0.1, 10]
     */
    public double d;
    public static final double D_MIN = 0.1;
    public static final double D_MAX = 10.0;
    private static final double D_SCALER = (D_MAX - D_MIN) / BIT_VALUES;
    /**
     * The parameter k has a valid range of [0.01, 1.0]
     */
    public double k;
    public static final double K_MIN = 0.01;
    public static final double K_MAX = 1.0;
    private static final double K_SCALER = (K_MAX - K_MIN) / BIT_VALUES;
    
    public double[] spiketrain;
    
    public double distance;

    public SpikingNeuronPhenotype(double a, double b, double c, double d, double k) {
        
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.k = k;
        
    }
    public SpikingNeuronPhenotype(double[] attribs){
        
        a = A_MIN + (A_SCALER * (attribs[0]));
        b = B_MIN + (B_SCALER * (attribs[1]));
        c = C_MIN + (C_SCALER * (attribs[2]));
        d = D_MIN + (D_SCALER * (attribs[3]));
        k = K_MIN + (K_SCALER * (attribs[4]));
        
    }

    @Override
    public String toString() {
        
        DecimalFormat df = new DecimalFormat("#.###");
        df.setMinimumFractionDigits(3);
        return "A: " + df.format(a) + ", B: " + df.format(b) + ", C: " + 
                df.format(c) + ", D: " + df.format(d) + ", K: " + df.format(k);
        
    }
}