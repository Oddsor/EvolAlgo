/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolalgo.problem.ctrnn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Odd
 */
public class MotorNode implements INode{
    
    private double gain;
    private double timeConstant;
    private double selfWeight;
    
    private Map<INode, Double> connections;
    
    public MotorNode(double gain, double timeConstant, double selfWeight){
        this.gain = gain;
        this.timeConstant = timeConstant;
        this.selfWeight = selfWeight;
        connections = new HashMap<INode, Double>();
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
