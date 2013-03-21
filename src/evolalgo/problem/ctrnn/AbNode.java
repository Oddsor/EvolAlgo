/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolalgo.problem.ctrnn;

/**
 *
 * @author Odd
 */
abstract class AbNode {
    private double gain;
    private double timeConstant;
    
    private double bias;
    
    private double dy(){
        return (1 / timeConstant) * (-y + s + bias);
    }
    
    private double output(double y){
        return 1 / (1 + Math.pow(Math.E, -gain * y));
    }
}
