package evoalgo.tracker;

import evolalgo.problem.ctrnn.ITracker;

public class TrackerEnvironment {

	Tracker tracker;
	private IFallingObject VFO;  //Only one falling object exists at any given moment.
	IPointAwarder pa;
	public TrackerEnvironment(ITracker it,IPointAwarder pa){
		tracker = new Tracker(it);		
		this.pa = pa;
	}

	boolean[] getShadowVector(){

		int[] trackerPos = tracker.getPosition();
		int[] fallingObjectPos = VFO.getPosition();
		boolean[] shadowVector = new boolean[tracker.getLength()];
		for (int i = 0; i < trackerPos.length; i++) {
			for (int j = 0; j < fallingObjectPos.length; j++) {
				if(trackerPos[i] == fallingObjectPos[j]) shadowVector[i] = true;
			}
		}
		return shadowVector;

	}

	private int awardPoints(){
		
		boolean[] sv = getShadowVector();
		
		return	pa.awardPoints(sv,VFO.getSize());
	
//		if(VFO.getSize()>4){ //Item should be avoided
//			for (boolean b : sv) {
//				if(b) return 0; //If any part is on it, it has not avoided
//			}
//			return 1;
//		}
//		else{ //Tracker should catch it
//			int c = 0;
//			for (boolean b : sv) {
//				if(b) c++;
//			}
//
//			return c == VFO.getSize() ? 1 : 0;
//
//		}
	}
	
	public int step(){
		tracker.updatePosition(getShadowVector());
		VFO.step();
		int y = VFO.getYPosition();
		if(y==0) System.out.println("Score: "+awardPoints());
		
		return y;
	}

	public double run() {
		
		double pointsForEffort=0;
		double rounds = VFO.getYPosition();
		while(VFO.getYPosition()>0){
			
			boolean[] sv = getShadowVector();
			double move = tracker.updatePosition(sv);
			tracker.updatePosition(getShadowVector());
			
			pointsForEffort += pa.pointsForEffort(sv,move);
			
			VFO.step();
		}
		boolean[] sv = getShadowVector();
		int hitPoints = pa.awardPoints(sv, VFO.getSize());
		return (pointsForEffort/rounds)+hitPoints;
	}

	Tracker getTracker(){
		return tracker;
	}

	void setTracker(ITracker it){
		tracker = new Tracker(it);
	}

	IFallingObject getFallingObject(){
		return VFO;
	}

	void setFallingObject(IFallingObject obj){
		this.VFO = obj;
	}	

}
