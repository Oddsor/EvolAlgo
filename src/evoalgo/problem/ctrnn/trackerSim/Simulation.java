package evoalgo.problem.ctrnn.trackerSim;

import evolalgo.problem.ctrnn.ITracker;

public class Simulation implements ISimulation

{
	 public static final int OBJECT_TYPE_VERTICAL = 0;
	 public static final int OBJECT_TYPE_SIDEWAYS = 1;
	@Override
	public double simulate(ITracker it, IPointAwarder awarder, int objectType) {
		
//		TrackerEnvironment env = new TrackerEnvironment(it, awarder,new ExplorationEffortAwarder());
		TrackerEnvironment env = new TrackerEnvironment(it, awarder);

		double score = 0;
		
		for (int i = 0; i < 40; i++) {
			
			switch (objectType) {
			case OBJECT_TYPE_VERTICAL:
				env.setFallingObject(new VerticalFallingObject());
				break;

			case OBJECT_TYPE_SIDEWAYS:
				env.setFallingObject(new SidewaysFallingObject());
				break;
			}
						
			score += env.run();
			
		}
		if(env.getEffortAwarder()==null) return score/40.0; 
		return score/80.0;
	}
	
}
