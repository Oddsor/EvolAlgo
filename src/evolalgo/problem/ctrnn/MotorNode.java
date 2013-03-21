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
    
    private List<Object[]> connections;
    
    public MotorNode(double gain, double timeConstant, double selfWeight){
        this.gain = gain;
        this.timeConstant = timeConstant;
        this.selfWeight = selfWeight;
        connections = new ArrayList<Object[]>();
    }

    @Override
    public double getOutput() {
        //TODO: Currently looking into this!
        double s = 0.0;
        for(Object[] ob:connections){
            
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addArc(INode node, double weight) {
        Object[] ob = {node, weight};
        connections.add(ob);
    }
    
}
