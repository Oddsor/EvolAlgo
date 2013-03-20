/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolalgo.problem.ctrnn;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Odd
 */
public class HiddenNode implements INode{
    private double gain;
    private double timeConstant;
    private double selfWeight;
    
    private Map<INode, Double> connections;
    private double[] sensorWeights;
    
    public HiddenNode(double gain, double timeConstant, double selfWeight, double... sensorWeights){
        this.gain = gain;
        this.timeConstant = timeConstant;
        this.selfWeight = selfWeight;
        connections = new HashMap<INode, Double>();
        
        if(sensorWeights.length != 5) try {
            throw new Exception("Wrong amount of weights passed to constructor!");
        } catch (Exception ex) {
            Logger.getLogger(HiddenNode.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        this.sensorWeights = sensorWeights;
    }

    @Override
    public double getOutput() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addArc(INode node, double weight) {
        connections.put(node, weight);
    }
    
}
