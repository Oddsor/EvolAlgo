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

    @Override
    public double calculateDistance(double[] target, double[] spiketrain) {
        double d;
        final double P = 2.0;
        List<Integer> targetSpikes = new ArrayList<Integer>();
        List<Integer> spiketrainSpikes = new ArrayList<Integer>();
        for (int i = 0; i < target.length; i++){
            if(spiketrain[i] >= 35) spiketrainSpikes.add(i);
            if(spiketrain[i] >= 35) targetSpikes.add(i);
        }
        int N = (targetSpikes.size() < spiketrainSpikes.size()) ? targetSpikes.size() : spiketrainSpikes.size();
        double sum = 0.0;
        for (int i = 0; i < N; i++){
            sum += Math.pow(Math.abs(targetSpikes.get(i) - spiketrainSpikes.get(i)), P);
        }
        d = Math.pow(sum, 1.0/P) / (double) N;
        return d;
    }
    
}
