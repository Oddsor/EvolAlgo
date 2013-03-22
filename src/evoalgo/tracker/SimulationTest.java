package evoalgo.tracker;

import static org.junit.Assert.*;

import org.junit.Test;

import evolalgo.problem.ctrnn.ITracker;

public class SimulationTest {

	@Test
	public void test() {
		
		ITracker it = new passiveTracker();
		Simulation sim = new Simulation();
		
		System.out.println("Score: "+sim.simulate(it, new HitAwarder()));
		
		ITracker it2 = new randomTracker();
		Simulation sim2 = new Simulation();
		
		System.out.println("Score: "+sim2.simulate(it2, new HitAwarder()));
		
	}

}
