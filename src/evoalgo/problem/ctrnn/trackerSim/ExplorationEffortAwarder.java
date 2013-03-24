package evoalgo.problem.ctrnn.trackerSim;

public class ExplorationEffortAwarder implements IEffortAwarder{

	@Override
	public double pointsForEffort(boolean[] sv, int move) {
		
		//Creates an integer from the bitstring representing sensor values.
		
		int sum = binaryToInt(sv);
		
		if( sum == binaryToInt(new boolean[]{true,true,true,true,true})){
			
			return move == 0 ? 1 : 0 ;
			
		}
		
		if( sum == binaryToInt(new boolean[]{!true,!true,!true,!true,!true})){
			
			return move == 0 ? 0 : 1 ; //Go explore!
			
		}
		
		if( !sv[0] && !sv[sv.length-1] && (sv[1] || sv[2] || sv[3]) ){
			
			return move == 0 ? 1 : 0 ; //Stay put!
			
		}
		
		
		
		if( sum == binaryToInt(new boolean[]{!true,!true,!true,!true,true})){
			
			return move > 2 ? 1 : 0 ; //Go right
			
		}
		

		if( sum == binaryToInt(new boolean[]{!true,!true,!true, true,true})){
			
			return move > 1 ? 1 : 0 ; //Go right
			
		}
		
		if( sum == binaryToInt(new boolean[]{!true,!true, true, true,true})){
			
			return move > 0 ? 1 : 0 ; //Go right
			
		}
		
		if( sum == binaryToInt(new boolean[]{!true,true, true, true,true})){
			
			return move > 0 ? 1 : 0 ; //Go right
			
		}
		
		if( sum == binaryToInt(new boolean[]{true,!true,!true,!true,!true})){
			
			return move < -2 ? 1 : 0 ; //Go left!
			
		}
		
		if( sum == binaryToInt(new boolean[]{true,true,!true,!true,!true})){
			
			return move < -1 ? 1 : 0 ; //Go left
			
		}
		
		if( sum == binaryToInt(new boolean[]{true,true,true,!true,!true})){
			
			return move < 0 ? 1 : 0 ; //Go left
			
		}
		
		if( sum == binaryToInt(new boolean[]{true,true,true,true,!true})){
			
			return move < 0 ? 1 : 0 ; //Go left
			
		}
		
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
