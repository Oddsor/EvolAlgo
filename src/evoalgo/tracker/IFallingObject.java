package evoalgo.tracker;

public interface IFallingObject {

	
	public int getSize();

	public void setSize(int size);
	public int[] getPosition();
	
	public void setPosition(int[] position);
	
	public int getYPosition();
	public void step();
}
