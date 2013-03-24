package evoalgo.problem.ctrnn.trackerSim;

public class SidewaysFallingObject implements IFallingObject{
	int size;
	private int yPosition=15;
	int[] xPosition;
	int dx;

	public SidewaysFallingObject(){
		
		dx = Math.random() < 0.5 ? 1 : -1;
		size = 1+(int)(Math.random()*6);
		
		xPosition = new int[size];
		int start = (int) (Math.random()*31);
		for (int i = 0; i < xPosition.length; i++) {
			xPosition[i] =(start+i)%30;
		}
		
	}
	public int getSize() {
		return xPosition.length;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int[] getPosition() {
		return xPosition;
	}
	
	public void setPosition(int[] position) {
		xPosition = position;
		this.size=xPosition.length;
	}
	
	public int getYPosition(){
		return yPosition;
	}
	
	public void step(){
		
		int left = xPosition[0];
		left = left+dx;
		if(left<0) left = 30+left;
		
		for (int i = 0; i < xPosition.length; i++) {
			xPosition[i] = (left+i)%30;
		}
		yPosition--;
	}
	
}
