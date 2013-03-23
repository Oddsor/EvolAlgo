package evoalgo.tracker;

public class HitAndAvoidAwarder implements IPointAwarder {

	@Override
	public int awardPoints(boolean[] sv,int objectSize) {

		if(objectSize>sv.length){ 
			for (boolean b : sv) {
				if(!b) return 0; //If any part is free of the object
			}
			return 1;
		}
		else{ //Tracker should catch it
			int c = 0;
			for (boolean b : sv) {
				if(b) c++;
			}

			return c == objectSize ? 1 : 0;

		}

	}
}
