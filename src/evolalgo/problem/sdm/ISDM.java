/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolalgo.problem.sdm;

/**
 *
 * @author Odd
 */
public interface ISDM {
    public double calculateDistance(double[] target, double[] spiketrain);
    public double convertToFitness(double distance);
}



