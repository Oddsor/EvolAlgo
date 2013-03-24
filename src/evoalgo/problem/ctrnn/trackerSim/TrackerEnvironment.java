package evoalgo.problem.ctrnn.trackerSim;

import evolalgo.problem.ctrnn.ITracker;
/**
 * Environment for running simulations of dropping objects on the tracker.
 * @author Andreas
 *
 */
public class TrackerEnvironment {

	Tracker tracker;
	private IFallingObject VFO;  //Only one falling object exists at any given moment.
	IPointAwarder pa;
	IEffortAwarder ea;
	public TrackerEnvironment(ITracker it,IPointAwarder pa){
		tracker = new Tracker(it);		
		this.pa = pa;
	}
	
	public TrackerEnvironment(ITracker it,IPointAwarder pa, IEffortAwarder ea){
		tracker = new Tracker(it);		
		this.pa = pa;
		this.ea = ea;
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
	
	public int step(){
		tracker.updatePosition(getShadowVector());
		VFO.step();
		int y = VFO.getYPosition();
		if(y==0) System.out.println("Score: "+pa.awardPoints(getShadowVector(),VFO.getSize()));
		
		return y;
	}

	public double run() {
		
		double pointsForEffort=0;
		double rounds = VFO.getYPosition();
	
		while(VFO.getYPosition()>0){
			
			boolean[] sv = getShadowVector();
			int move = tracker.updatePosition(sv);
			tracker.updatePosition(getShadowVector());
			
			if(ea != null) pointsForEffort += ea.pointsForEffort(sv,move);
			VFO.step();
		}
		
		boolean[] sv = getShadowVector();
		int hitPoint = pa.awardPoints(sv, VFO.getSize());
		return ((double)pointsForEffort/rounds)+hitPoint;
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

	IEffortAwarder getEffortAwarder(){
		return ea;
	}
}
