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
    
    public void updateY(boolean[] sensorInputs);
    
    public void addArc(INode node, double weight);
}
