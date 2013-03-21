package evoalgo.tracker;

import evolalgo.problem.ctrnn.ITracker;

public class randomTracker implements ITracker {

	@Override
	public int getMovement(boolean[] shadowSensors) {
		// Nobody move, or the bunny gets it!
		return (int) (Math.random()*8)-4;
	}

}
