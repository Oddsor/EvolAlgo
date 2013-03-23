/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolalgo.problem.ctrnn;

/**
 *
 * @author Odd
 */
public interface INode {
    public double getOutput();
    
    /**
     * See if there is a new value of y in storage
     */
    public void updateY();
    
    /**
     * Calculate the new value for y and store internally in the node.
     * @param shadowSensors 
     */
    public void calculateNextY(boolean[] shadowSensors);
    
    public void addArc(INode node, double weight);
}
