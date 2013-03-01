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
public class SpikeTimeDistance extends AbstractSpikePenalty implements ISDM {

		
	private double threshold = 35;
    @Override
    public double calculateDistance(double[] target, double[] spiketrain) {
        double d;
        final double P = 2.0;
        List<Integer> targetSpikes = new ArrayList<Integer>();
        List<Integer> spiketrainSpikes = new ArrayList<Integer>();
       
        for (int i = 0; i < target.length; i++){
            if(spiketrain[i] >= threshold) spiketrainSpikes.add(i);
            if(target[i] >= threshold) targetSpikes.add(i);
        }
        int N = (targetSpikes.size() < spiketrainSpikes.size()) ? targetSpikes.size() : spiketrainSpikes.size();
        double sum = 0.0;
        for (int i = 0; i < N; i++){
            sum += Math.pow(Math.abs(targetSpikes.get(i) - spiketrainSpikes.get(i)), P);
        }
        System.out.println("Penalty: "+calculatePenalty(targetSpikes.size(), spiketrainSpikes.size(), target.length));
        sum += calculatePenalty(targetSpikes.size(), spiketrainSpikes.size(), target.length);
        d = Math.pow(sum, 1.0/P) / (double) N;
        return d;
    }
	public double getThreshold() {
		return threshold;
	}
	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}
	@Override
	public double convertToFitness(double distance) {
		// TODO Auto-generated method stub
		return 0;
	}
    
}
