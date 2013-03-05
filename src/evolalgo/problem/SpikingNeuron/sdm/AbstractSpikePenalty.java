package evolalgo.problem.SpikingNeuron.sdm;

/**
 * 
 * @author Andreas
 *
 */
public abstract class AbstractSpikePenalty {

	public double calculatePenalty(int N, int M, int L){
		double n = (double)Math.max(N, M);
		double m = (double)Math.min(N, M);
		double l = (double)L;
		
		return ((n-m)*l)/(2*Math.max(1, m));
		
	}
	
}
