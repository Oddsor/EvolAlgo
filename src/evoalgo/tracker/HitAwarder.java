package evoalgo.tracker;

public class HitAwarder implements IPointAwarder {

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

	@Override
	public double pointsForEffort(boolean[] sv, double move) {
		
		//Creates an integer from the bitstring representing sensor values.
		int sum = binaryToInt(sv);
		
		if( sum == binaryToInt(new boolean[]{true,true,true,true,true})){
			
			return move == 0 ? 1 : 0 ;
			
		}
		
//		switch (sum) {
//		case 0:	//[0 0 0 0 0] No shadow above, gets credit for 'exploring';
//			if(move!=0) return 1;
//			break;
//		case 1: //[0 0 0 0 1] Shadow on the rights, get under it.
//			if(move>0) return 1;
//			break;
//		
//		case 16: // [1 0 0 0 0] Shadow on the left, get under it.
//			if(move<0) return 1;
//			break;
//		
//		case 15: // [0 1 1 1 1]
//			if(move == -1.0) return 1;
//			break;
//		case 14: //[0 1 1 1 0] Freeze!
//			if(move ==0.0) return 1;
//			break;
//		case 6: // [0 0 1 1 0]
//			if(move ==0.0) return 1;
//			break;
//		case 31: // [1 1 1 1 1] Stay put!
//			if(move<1 && move >-1) return 1;
//		default:
//			return 0;
//		}
		
		return 0;
	}

	private int binaryToInt(boolean[] sv){
		int sum=0;
		for (int i = sv.length-1; i >=0 ; i--) {
			
			if(sv[i]) sum += Math.pow(2, Math.abs(i-(sv.length-1)));
			
		}
		return sum;
	}
	
}
