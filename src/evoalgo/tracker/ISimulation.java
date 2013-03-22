package evoalgo.tracker;

import evolalgo.problem.ctrnn.ITracker;

public interface ISimulation {

	public double simulate(ITracker it, IPointAwarder awarder);
	
}
