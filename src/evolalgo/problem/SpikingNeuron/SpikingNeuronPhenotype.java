package evolalgo.problem.SpikingNeuron;

import evolalgo.IPhenotype;

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
        c = C_MIN + C_SCALER * (attribs[2]);
        d = D_MIN + (D_SCALER * (attribs[3]));
        k = K_MIN + (K_SCALER * (attribs[4]));
    }

    @Override
    public String toString() {
        return "A: " + a + ", B: " + b + ", C: " + c + ", D: " + d + ", K: " + k;
        /*String valueString = "";
        if(spiketrain == null){
            System.out.println("Distance, spiketrain is null?!: " + distance);
        }
        for (int i = 0; i < spiketrain.length; i++){
            valueString += spiketrain[i] + " ";
        }
        return valueString;*/
    }
}