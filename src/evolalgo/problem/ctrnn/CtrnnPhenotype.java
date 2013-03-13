
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
