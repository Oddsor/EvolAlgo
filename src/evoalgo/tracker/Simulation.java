package evoalgo.tracker;

import evolalgo.problem.ctrnn.ITracker;

public class Simulation implements ISimulation

{

	@Override
	public int simulate(ITracker it) {
		
		TrackerEnvironment env = new TrackerEnvironment(it, new HitAndAvoidAwarder());
		
		int score = 0;
		
		for (int i = 0; i < 40; i++) {
			
			env.setFallingObject(new VerticalFallingObject());
			score += env.run();
			
		}		
		
		return score;
	}
	
}
