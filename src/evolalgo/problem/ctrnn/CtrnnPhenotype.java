
package evolalgo.problem.ctrnn;

import evolalgo.IPhenotype;

/**
 *
 * @author Odd
 */
public class CtrnnPhenotype implements IPhenotype{
   private static final double WEIGHTS_MIN = -5.0;
   private static final double WEIGHTS_MAX = 5.0;
   private double weights;
   
   private static final double BIASES_MIN = -10.0;
   private static final double BIASES_MAX = 0.0;
   private double biases;
   
   private static final double GAINS_MIN = 1.0;
   private static final double GAINS_MAX = 5.0;
   private double gains;
   
   private static final double TIMECONSTANTS_MIN = 1.0;
   private static final double TIMECONSTANTS_MAX = 2.0;
   private double timeconstants;

    public CtrnnPhenotype(double... attributes) {
        weights = WEIGHTS_MIN;
        biases = BIASES_MIN;
        gains = GAINS_MIN;
        timeconstants = TIMECONSTANTS_MIN;
        
        try{
            weights = attributes[0];
            biases = attributes[1];
            gains = attributes[2];
            timeconstants = attributes[3];
        }catch(Exception e){
            //No input available! using minimum values
        }
    }
    public CtrnnPhenotype(double weights, double biases, double gains,
            double timeconstants) {
            this.weights = weights;
            this.biases = biases;
            this.gains = gains;
            this.timeconstants = timeconstants;
    }
    
    public CtrnnPhenotype(int... attributes){
        weights = WEIGHTS_MIN;
        biases = BIASES_MIN;
        gains = GAINS_MIN;
        timeconstants = TIMECONSTANTS_MIN;
        
        try{
            double weights_val = (double) attributes[0] / (WEIGHTS_MAX - WEIGHTS_MIN);
            weights = WEIGHTS_MIN + weights_val;
            double biases_val = (double) attributes[1] / (BIASES_MAX - BIASES_MIN);
            biases = BIASES_MIN + biases_val;
            double gains_val = (double) attributes[2] / (GAINS_MAX - GAINS_MIN);
            gains = GAINS_MIN + gains_val;
            double timeconstants_val = (double) attributes[3] / 
                    (TIMECONSTANTS_MAX - TIMECONSTANTS_MIN);
            timeconstants = TIMECONSTANTS_MIN + timeconstants_val;
        }catch(Exception e){
            //Missing inputs, oh no!
        }
    }

    public double getWeights() {
        return weights;
    }

    public void setWeights(double weights) {
        this.weights = weights;
    }

    public double getBiases() {
        return biases;
    }

    public void setBiases(double biases) {
        this.biases = biases;
    }

    public double getGains() {
        return gains;
    }

    public void setGains(double gains) {
        this.gains = gains;
    }

    public double getTimeconstants() {
        return timeconstants;
    }

    public void setTimeconstants(double timeconstants) {
        this.timeconstants = timeconstants;
    }
    
    
}
