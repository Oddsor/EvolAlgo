package evoalgo.tracker;

import static org.junit.Assert.*;
import org.junit.Test;
import evolalgo.problem.ctrnn.ITracker;

public class TrackerEnvironmentTest {

	@Test
	public void test() {
		ITracker it = new passiveTracker();
		TrackerEnvironment env = new TrackerEnvironment(it, new HitAndAvoidAwarder());
		
		int[] pos = {1,2,3,4,5};
		env.getTracker().setPosition(pos);
		
		//Suitable object hits tracker
		env.setFallingObject(new VerticalFallingObject());
		int[] oPos = {2,3,4};
		env.getFallingObject().setPosition(oPos);
		
		boolean[] b1 = {false,true,true,true,false};
		boolean[] sv  = env.getShadowVector();
		
		for (int i = 0; i < sv.length; i++) {
			assertTrue(sv[i]==b1[i]);
		}
		
		env.setFallingObject(new VerticalFallingObject());
		int[] oPos1 = {6,7,8,9};
		env.getFallingObject().setPosition(oPos1);
		
		boolean[] b2 = {false,false,false,false,false};
		sv  = env.getShadowVector();
		
		for (int i = 0; i < sv.length; i++) {
			assertTrue(sv[i]==b2[i]);
		}
		
		env.setFallingObject(new VerticalFallingObject());
		int[] oPos11 = {2,3,4};
		env.getFallingObject().setPosition(oPos11);
		assertEquals(1, env.run());
		
		//Suitable object misses tracker
		env.setFallingObject(new VerticalFallingObject());
		int[] oPos3 = {4,5,6};
		env.getFallingObject().setPosition(oPos3);
		assertEquals(0, env.run());
		
		//unsuitable object hits tracker
		env.setFallingObject(new VerticalFallingObject());
		int[] oPos111 = {1,2,3,4,5};
		env.getFallingObject().setPosition(oPos111);
		assertEquals(0, env.run());
		
		//unsuitable object misses tracker
		env.setFallingObject(new VerticalFallingObject());
		int[] oPos2 = {6,7,8,9,10};
		env.getFallingObject().setPosition(oPos2);
		assertEquals(1, env.run());
		
	}

}
