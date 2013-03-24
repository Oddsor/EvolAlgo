
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
   
   
   private List<INeuron> motorLayer;
   private List<INeuron> hiddenLayer;

    public CtrnnPhenotype(List<Integer> attributes, int numHiddenNeurons, int sensors){
        Iterator<Integer> attributeIt = attributes.iterator();
        
        hiddenLayer = new ArrayList<INeuron>();
        motorLayer = new ArrayList<INeuron>();
        
        
        for(int i = 0; i < numHiddenNeurons; i++){
            double[] sensorWeights = new double[sensors];
            for (int j = 0; j < sensors; j++){
                sensorWeights[j] = convertWeight(attributeIt.next());
            }
            HiddenNeuron hidden = new HiddenNeuron(convertGain(attributeIt.next()), 
                    convertTimeConstant(attributeIt.next()), 
                    convertBias(attributeIt.next()),
                    convertWeight(attributeIt.next()),sensorWeights);
            hiddenLayer.add(hidden);
        }
        for(int i = 0; i < 2; i++){
            MotorNeuron motor = new MotorNeuron(convertGain(attributeIt.next()), 
                    convertTimeConstant(attributeIt.next()), 
                    convertBias(attributeIt.next()),
                    convertWeight(attributeIt.next()));
            motorLayer.add(motor);
        }
        //===ALL SENSOR WEIGHTS ADDED=== 10 connections from sensory input to hidden layer
        //===ALL BIASES ADDED=== 4 connections from bias node to hidden and motor layer
        //===ALL LOOPS ADDED=== (as selfweight-parameter in constructor) 4 loops
        
        for(INeuron hiddenNeuron: hiddenLayer){
            ArrayList<INeuron> otherHiddens = new ArrayList<INeuron>(hiddenLayer);
            otherHiddens.remove(hiddenNeuron);
            for(INeuron otherNeuron: otherHiddens){
                hiddenNeuron.addArc(otherNeuron, convertWeight(attributeIt.next()));
            }
        }
        
        for(int i = 0; i < 2; i++){
            for (int j = 0; j < numHiddenNeurons; j++){
                motorLayer.get(i).addArc(hiddenLayer.get(j), convertWeight(attributeIt.next()));
            }
        }
        
        motorLayer.get(0).addArc(motorLayer.get(1), convertWeight(attributeIt.next()));
        motorLayer.get(1).addArc(motorLayer.get(0), convertWeight(attributeIt.next()));
        //===ALL HIDDEN AND MOTOR CONNECTIONS ADDED=== 8 connections
        //===TOTAL CONNECTIONS: 10 + 4 + 4 + 8 = 26
        //===TOTAL PARAMETERS IN LAYER NODES: 2 * 4 = 8
        //===TOTAL ATTRIBUTES: 8 + 26 = 34
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

        /*double left = motorLayer.get(0).getOutput();
        double right = motorLayer.get(1).getOutput();
        if(left > right) return (int) (-left * 4);
        else return (int) (right * 4);*/
        if(Math.abs(motorLayer.get(0).getOutput() - 0.5) > 
                Math.abs(motorLayer.get(1).getOutput() - 0.5)){
            return (int) ((motorLayer.get(0).getOutput() - 0.5) * 8);
        }else return (int) ((motorLayer.get(1).getOutput() - 0.5) * 8);
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