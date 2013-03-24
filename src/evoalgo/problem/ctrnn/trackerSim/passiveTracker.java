package evoalgo.problem.ctrnn.trackerSim;

import evolalgo.problem.ctrnn.ITracker;

public class passiveTracker implements ITracker {

	@Override
	public int getMovement(boolean[] shadowSensors) {
		// Nobody move, or the bunny gets it!
		return 0;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

}
