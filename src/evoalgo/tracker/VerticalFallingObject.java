package evoalgo.tracker;

public class VerticalFallingObject {
	int size;
	private int yPosition=15;
	int[] xPosition;

	public VerticalFallingObject(){
		
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
		yPosition--;
	}
	
}
