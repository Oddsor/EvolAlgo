package evoalgo.problem.ctrnn.trackerSim;

import evolalgo.problem.ctrnn.ITracker;
/**
 * Class handling the location and attributes of a tracker in the tracker environment.
 * @author Andreas
 *
 */
public class Tracker {

	int xPosition[];
	private int length=5;
	private ITracker it;
	public Tracker(ITracker it){

		this.it = it;
		xPosition = new int[length];
		int start = (int) (Math.random()*31);
		for (int i = 0; i < xPosition.length; i++) {
			xPosition[i] =(start+i)%30;
		}

	}
	public int[] getPosition() {
		return xPosition;
	}

	public int getLength() {
		return length;
	}

	public void setPosition(int[] pos){
		xPosition = pos;
	}
	/**
	 * Updates the tracker position based on the sensory input
	 * @param shadowVector
	 * @return
	 */
	public int updatePosition(boolean[] shadowVector ){

		int dx = it.getMovement(shadowVector);

		int left = xPosition[0];
		left = left+dx;
		if(left<0) left = 30+left;

		for (int i = 0; i < xPosition.length; i++) {
			xPosition[i] = (left+i)%30;
		}
		return dx;
	}

}
