package evoalgo.tracker;

import static org.junit.Assert.*;

import org.junit.Test;

import evolalgo.problem.ctrnn.ITracker;

public class SimulationTest {

	@Test
	public void test() {
		
		ITracker it = new passiveTracker();
		Simulation sim = new Simulation();
		
		System.out.println("Score: "+sim.simulate(it));
		
		
	}

}
