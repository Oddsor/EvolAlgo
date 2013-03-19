package evoalgo.tracker;

import evolalgo.problem.ctrnn.ITracker;

public class passiveTracker implements ITracker {

	@Override
	public int getMovement(boolean[] shadowSensors) {
		// Nobody move, or the bunny gets it!
		return 0;
	}

}
