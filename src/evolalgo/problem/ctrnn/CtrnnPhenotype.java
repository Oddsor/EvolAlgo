
package evolalgo.problem.ctrnn;

import evolalgo.IPhenotype;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
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
   
   private List<INode> motorLayer;
   private List<INode> hiddenLayer;
   private Collection<INode> neurons;
   private INode biasNode;

    public CtrnnPhenotype(List<Integer> attributes){
        try {
            if (attributes.size() != 34) throw new Exception("Need 34 attributes!");
        } catch (Exception ex) {
            Logger.getLogger(CtrnnPhenotype.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        Iterator<Integer> attributeIt = attributes.iterator();
        
        biasNode = new BiasNode();
        hiddenLayer = new ArrayList<INode>();
        motorLayer = new ArrayList<INode>();
        
        
        for(int i = 0; i < 4; i++){
            INode newNode;
            if (i < 2){
                double[] sensorWeights = new double[5];
                for (int j = 0; j < 5; j++){
                    sensorWeights[j] = convertWeight(attributeIt.next());
                }
                newNode = new HiddenNode(convertGain(attributeIt.next()), 
                        convertTimeConstant(attributeIt.next()), 
                        convertWeight(attributeIt.next()),sensorWeights);
                hiddenLayer.add(newNode);
            }
            else {
                newNode = new MotorNode(convertGain(attributeIt.next()), 
                        convertTimeConstant(attributeIt.next()),
                        convertWeight(attributeIt.next()));
                motorLayer.add(newNode);
            }
            newNode.addArc(biasNode, convertBias(attributeIt.next()));
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
        neurons = new ArrayList<INode>(motorLayer);
        neurons.addAll(hiddenLayer);
    }

    @Override
    public int getMovement(boolean[] shadowSensors) {
        for(INode node: neurons){
            node.updateY(shadowSensors);
        }
        double left = motorLayer.get(0).getOutput();
        double right = motorLayer.get(1).getOutput();
        double total = -left + right;
        total = total * 4.0; //Maks 4
        int rounded = (int) Math.round(total);
        if(rounded != 0){
            System.out.println("TOTAL MOVEMENT: " + total);
            System.out.println("Rounded: " + rounded);
        }
        return rounded;
    }
    
    private double convertWeight(int genomeValue){
        return WEIGHTS_MIN + ((double) genomeValue / (WEIGHTS_MAX - WEIGHTS_MIN));
    }
    
    private double convertBias(int genomeValue){
        return BIASES_MIN + ((double) genomeValue / (BIASES_MAX - BIASES_MIN));
    }
    
    private double convertGain(int genomeValue){
        return GAINS_MIN + ((double) genomeValue / (GAINS_MAX - GAINS_MIN));
    }
    
    private double convertTimeConstant(int genomeValue){
        return TIMECONSTANTS_MIN + ((double) genomeValue / 
                (TIMECONSTANTS_MAX - TIMECONSTANTS_MIN));
    }
}