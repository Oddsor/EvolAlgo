package evolalgo.problem.sdm;

public class WaveformDistance implements ISDM {

	/**
	 * Class for computing the Waveform Distance Spike Train Distance Metrics
	 * @author Andreas
	 */
	double p = 2.0;
	@Override
	public double calculateDistance(double[] target, double[] spiketrain) {
		
		int smallest = target.length < spiketrain.length ? target.length : spiketrain.length;
		double sum = 0;
		for (int i = 0; i < smallest; i++) {
			
			sum += Math.pow(Math.abs(spiketrain[i]-target[i]), p);
			
		}
		
		return Math.pow(sum,1.0/p)/smallest;
	}
	public double getP() {
		return p;
	}
	public void setP(double p) {
		this.p = p;
	}

}
