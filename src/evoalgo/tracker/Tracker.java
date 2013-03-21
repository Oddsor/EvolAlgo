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
		
		int left = xPosition[0];
		left = left+dx;
		if(left<0) left = 30+left;
		
		for (int i = 0; i < xPosition.length; i++) {
			xPosition[i] = (left+i)%30;
		}
		
//		for (int i = 0; i < xPosition.length; i++) {
//			int newX = (xPosition[i]+dx);
//			if(newX<0) newX = 30-newX;
//			xPosition[i] = newX%30;
//		}
//		System.out.println("Tracker position: ");
//
//		for (int b : xPosition) {
//			System.out.print(b+", ");
//		}
	}
	
}
