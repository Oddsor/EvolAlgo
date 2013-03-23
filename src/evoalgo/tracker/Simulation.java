package evoalgo.tracker;

import evolalgo.problem.ctrnn.ITracker;

public class Simulation implements ISimulation

{

	@Override
	public double simulate(ITracker it, IPointAwarder awarder) {
		
		TrackerEnvironment env = new TrackerEnvironment(it, awarder,new ExplorationEffortAwarder());
		
		double score = 0;
		
		for (int i = 0; i < 40; i++) {
			
			env.setFallingObject(new VerticalFallingObject());
			score += env.run();
			
		}
		if(env.getEffortAwarder()==null) return score/40.0; 
		return score/80.0;
	}
	
}
