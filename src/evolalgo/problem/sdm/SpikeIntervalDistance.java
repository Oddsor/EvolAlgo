package evolalgo.problem.sdm;

import java.util.ArrayList;
import java.util.List;

public class SpikeIntervalDistance implements ISDM {

	/**
	 * Class for computing the Spike Interval Distance
	 * @author Andreas
	 */
	double p = 2.0;
	private double threshold = 35;
	@Override
	public double calculateDistance(double[] target, double[] spiketrain) {
		double d;
		List<Integer> targetSpikes = new ArrayList<Integer>();
        List<Integer> spiketrainSpikes = new ArrayList<Integer>();
       
        for (int i = 0; i < target.length; i++){
            if(spiketrain[i] >= threshold) spiketrainSpikes.add(i);
            if(spiketrain[i] >= threshold) targetSpikes.add(i);
        }
        int N = (targetSpikes.size() < spiketrainSpikes.size()) ? targetSpikes.size() : spiketrainSpikes.size();
        double sum = 0.0;
        for (int i = 0; i < N; i++){
            sum += Math.pow(Math.abs(targetSpikes.get(i) - spiketrainSpikes.get(i)), p);
        }
        d = Math.pow(sum, 1.0/p) / (double) N;
        return d;
	}

}
