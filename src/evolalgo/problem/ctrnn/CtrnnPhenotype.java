
package evolalgo.problem.ctrnn;

import evolalgo.IPhenotype;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The CTRNN phenotype stores a neural network in order to
 * fulfill its role as a Tracker in a simulated environment.
 * 
 * The phenotype is developed by accepting a list of integers. These integers are
 * of the same length, and thus we can simply iteratively use the attributes to
 * create the neural network.
 * 
 * 
 * @author Odd
 */
public class CtrnnPhenotype implements IPhenotype, ITracker{
   private static final double WEIGHTS_MIN = -5.0;
   private static final double WEIGHTS_MAX = 5.0;
   
   private static final double BIASES_MIN = -10.0;
   private static final double BIASES_MAX = 0.0;
   
   private static final double GAINS_MIN = 1.0;
   private static final double GAINS_MAX = 5.0;
   
   private static final double TIMECONSTANTS_MIN = 1.0;
   private static final double TIMECONSTANTS_MAX = 2.0;
   
   private final int width;
   
   private List<INeuron> motorLayer;
   private List<INeuron> hiddenLayer;
   private Collection<INeuron> neurons;

    public CtrnnPhenotype(List<Integer> attributes, int width){
        this.width = width;
        try {
            if (attributes.size() != 34) throw new Exception("Need 34 attributes!");
        } catch (Exception ex) {
            Logger.getLogger(CtrnnPhenotype.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        Iterator<Integer> attributeIt = attributes.iterator();
        
        hiddenLayer = new ArrayList<INeuron>();
        motorLayer = new ArrayList<INeuron>();
        
        
        for(int i = 0; i < 4; i++){
            INeuron newNode; 
            if (i < 2){
                double[] sensorWeights = new double[5];
                for (int j = 0; j < 5; j++){
                    sensorWeights[j] = convertWeight(attributeIt.next());
                }
                newNode = new HiddenNeuron(convertGain(attributeIt.next()), 
                        convertTimeConstant(attributeIt.next()), 
                        convertBias(attributeIt.next()),
                        convertWeight(attributeIt.next()),sensorWeights);
                hiddenLayer.add(newNode);
            }
            else {
                newNode = new MotorNeuron(convertGain(attributeIt.next()), 
                        convertTimeConstant(attributeIt.next()), 
                        convertBias(attributeIt.next()),
                        convertWeight(attributeIt.next()));
                motorLayer.add(newNode);
            }
        }
        //===ALL SENSOR WEIGHTS ADDED=== 10 connections from sensory input to hidden layer
        //===ALL BIASES ADDED=== 4 connections from bias node to hidden and motor layer
        //===ALL LOOPS ADDED=== (as selfweight-parameter in constructor) 4 loops
        
        hiddenLayer.get(0).addArc(hiddenLayer.get(1), convertWeight(attributeIt.next()));
        hiddenLayer.get(1).addArc(hiddenLayer.get(0), convertWeight(attributeIt.next()));
        
        motorLayer.get(0).addArc(hiddenLayer.get(0), convertWeight(attributeIt.next()));
        motorLayer.get(0).addArc(hiddenLayer.get(1), convertWeight(attributeIt.next()));
        motorLayer.get(1).addArc(hiddenLayer.get(0), convertWeight(attributeIt.next()));
        motorLayer.get(1).addArc(hiddenLayer.get(1), convertWeight(attributeIt.next()));
        
        motorLayer.get(0).addArc(motorLayer.get(1), convertWeight(attributeIt.next()));
        motorLayer.get(1).addArc(motorLayer.get(0), convertWeight(attributeIt.next()));
        //===ALL HIDDEN AND MOTOR CONNECTIONS ADDED=== 8 connections
        //===TOTAL CONNECTIONS: 10 + 4 + 4 + 8 = 26
        //===TOTAL PARAMETERS IN LAYER NODES: 2 * 4 = 8
        //===TOTAL ATTRIBUTES: 8 + 26 = 34
        neurons = new ArrayList<INeuron>(motorLayer);
        neurons.addAll(hiddenLayer);
    }

    /**
     * Based on the input of the sensors in this object we return an integer
     * representing the agent's next move in the environment.
     * @param shadowSensorInput
     * @return 
     */
    @Override
    public int getMovement(boolean[] shadowSensorInput) {
        /*for(INode node: neurons){
            node.updateY(shadowSensors);
        }*/
        for (INeuron hiddenNode: hiddenLayer){
            hiddenNode.calculateNextY(shadowSensorInput);
        }
        for (INeuron hiddenNode: hiddenLayer){
            hiddenNode.updateY();
        }
        for (INeuron motorNode: motorLayer){
            motorNode.calculateNextY(shadowSensorInput);
        }
        for (INeuron motorNode: motorLayer){
            motorNode.updateY();
        }
        double left = motorLayer.get(0).getOutput();
        double right = motorLayer.get(1).getOutput();
        if(left > right) return (int) -Math.round(left);
        else return (int) Math.round(right);
    }
    
    /**
     * Convert a genome value into a weight.
     * @param genomeValue
     * @return 
     */
    private double convertWeight(int genomeValue){
        return WEIGHTS_MIN + ((double) genomeValue / (WEIGHTS_MAX - WEIGHTS_MIN));
    }
    
    /**
     * Convert a genome value into a bias, which is essentially the weight of a
     * connection to the bias node.
     * @param genomeValue
     * @return 
     */
    private double convertBias(int genomeValue){
        return BIASES_MIN + ((double) genomeValue / (BIASES_MAX - BIASES_MIN));
    }
    
    /**
     * Convert a genome value into a gain value, used in conjunction with
     * the time constant and an internal state-value in the neuron to calculate
     * the neuron's output.
     * @param genomeValue
     * @return 
     */
    private double convertGain(int genomeValue){
        return GAINS_MIN + ((double) genomeValue / (GAINS_MAX - GAINS_MIN));
    }
    
    /**
     * Convert a genome value into a time constant value, used in conjunction with
     * the gain and an internal state-value in the neuron to calculate the neuron's
     * output.
     * @param genomeValue
     * @return 
     */
    private double convertTimeConstant(int genomeValue){
        return TIMECONSTANTS_MIN + ((double) genomeValue / 
                (TIMECONSTANTS_MAX - TIMECONSTANTS_MIN));
    }

    @Override
    public int getWidth() {
        return 5;
    }
}