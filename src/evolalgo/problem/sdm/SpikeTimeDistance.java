/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolalgo.problem.sdm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Odd
 */
public class SpikeTimeDistance implements ISDM{

		
	private double threshold = 35;
    @Override
    public double calculateDistance(double[] target, double[] spiketrain) {
        double d;
        final double P = 2.0;
        List<Integer> targetSpikes = new ArrayList<Integer>();
        List<Integer> spiketrainSpikes = new ArrayList<Integer>();
       
        for (int i = 0; i < target.length; i++){
<<<<<<< HEAD
            if(spiketrain[i] >= threshold) spiketrainSpikes.add(i);
            if(spiketrain[i] >= threshold) targetSpikes.add(i);
=======

            if(spiketrain[i] >= threshold) spiketrainSpikes.add(i);
            if(spiketrain[i] >= threshold) targetSpikes.add(i);
>>>>>>> a3ded647bc05db49ca074cb8408200103b9d473d
        }
        int N = (targetSpikes.size() < spiketrainSpikes.size()) ? targetSpikes.size() : spiketrainSpikes.size();
        double sum = 0.0;
        for (int i = 0; i < N; i++){
            sum += Math.pow(Math.abs(targetSpikes.get(i) - spiketrainSpikes.get(i)), P);
        }
        d = Math.pow(sum, 1.0/P) / (double) N;
        return d;
    }
	public double getThreshold() {
		return threshold;
	}
	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}
    
}
