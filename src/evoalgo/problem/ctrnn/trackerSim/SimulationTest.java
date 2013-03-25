package evoalgo.problem.ctrnn.trackerSim;

import org.junit.Test;

import evolalgo.problem.ctrnn.ITracker;

public class SimulationTest {

	@Test
	public void test() {
		
		ITracker it = new passiveTracker();
		Simulation sim = new Simulation();
		
		
		ITracker it2 = new randomTracker();
		Simulation sim2 = new Simulation();
		
		
	}

}
