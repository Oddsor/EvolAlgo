package evoalgo.tracker;

import evolalgo.problem.ctrnn.ITracker;

public class Tracker {

	int xPosition[];
	private int length=5;
	private ITracker it;
	public Tracker(ITracker it){
		
		this.it = it;
		xPosition = new int[length];
		int start = (int) (Math.random()*31);
		for (int i = 0; i < xPosition.length; i++) {
			xPosition[i] =(start+i)%30;
		}
		
	}
	public int[] getPosition() {
		return xPosition;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setPosition(int[] pos){
		xPosition = pos;
	}
	
	public void updatePosition(boolean[] shadowSensors ){
		
		int dx = it.getMovement(shadowSensors);
		for (int i = 0; i < xPosition.length; i++) {
			xPosition[i] =(xPosition[i]+dx)%30;
		}
	}
	
}
