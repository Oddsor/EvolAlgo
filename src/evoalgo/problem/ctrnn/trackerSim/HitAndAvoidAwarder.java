package evoalgo.problem.ctrnn.trackerSim;

public class HitAndAvoidAwarder implements IPointAwarder {

	private int thresh=4;
	@Override
	public int awardPoints(boolean[] sv,int objectSize) {

		if(objectSize>thresh){ 
			for (boolean b : sv) {
				if(b) return 0; //If any part is touching
			}
			return 1;
		}
		else{ //Tracker should catch it
			int c = 0;
			for (boolean b : sv) {
				if(b) c++;
			}

			return c == objectSize ? 1 : 0;

		}

	}
	public int getThresh() {
		return thresh;
	}
	public void setThresh(int thresh) {
		this.thresh = thresh;
	}
}
