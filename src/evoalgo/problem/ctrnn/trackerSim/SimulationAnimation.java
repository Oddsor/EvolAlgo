package evoalgo.problem.ctrnn.trackerSim;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

import evolalgo.problem.ctrnn.CtrnnProblem;
import evolalgo.problem.ctrnn.ITracker;
/**
 * Animates a simulation.
 * @author Andreas
 *
 */
@SuppressWarnings("serial")
public class SimulationAnimation extends JFrame {

	private JFrame frame;
	private draw dr;
	private int[] trackerPos;
	private int[] fallPos;
	private int fallLevel;
	private int objectType;
	private Container con;
	static final int WIDTH=900;
	static final int HEIGHT=450+60;
	TrackerEnvironment env;
	/**
	 * 
	 * @param it
	 * @param pa
	 */
	public SimulationAnimation(ITracker it, IPointAwarder pa, int objectType) {

		env = new TrackerEnvironment(it, pa);
		this.objectType = objectType;
		if(objectType == CtrnnProblem.OBJECT_TYPE_VERTICAL) env.setFallingObject(new VerticalFallingObject());
		if(objectType == CtrnnProblem.OBJECT_TYPE_SIDEWAYS) env.setFallingObject(new SidewaysFallingObject());
		
		fallLevel = env.getFallingObject().getYPosition();
		updateMatrix();
		frame = new JFrame("Tracker Animation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);
		con = frame.getContentPane();
		con.setBackground(Color.black);
		dr = new draw();
		dr.setBackground(Color.black);
		con.add(dr);
		
		loop();
	}

	private void updateMatrix(){

		trackerPos = env.getTracker().getPosition();
		fallPos = env.getFallingObject().getPosition();
		fallLevel = env.step();
		
		if(fallLevel<0){
		if(objectType == CtrnnProblem.OBJECT_TYPE_VERTICAL) env.setFallingObject(new VerticalFallingObject());
		
		if(objectType == CtrnnProblem.OBJECT_TYPE_SIDEWAYS) env.setFallingObject(new SidewaysFallingObject());
	}
	}
	void loop(){
		while(true){
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			updateMatrix();
			con.repaint();
		}
	}
	public static void main(String[] args) {
		new SimulationAnimation(new randomTracker(), new HitAndAvoidAwarder(), 0);
	}
	class draw extends JPanel { 
		public draw() {
		}
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g.setColor(Color.BLACK);
	        g.fillRect(0, 0, getWidth(), getHeight());
			super.paintComponents(g);
			g2.setPaint(Color.red); 
			for (int i = 0; i < trackerPos.length; i++) {

				double y = SimulationAnimation.HEIGHT-60.0;
				g2.fill(new Rectangle2D.Double(trackerPos[i]*30.0, y ,30, 30));
								
			}
			g2.setPaint(Color.BLUE);
			double fy = ((15.0-fallLevel)*30);
			for (int i = 0; i < fallPos.length; i++) {
				g2.fill(new Rectangle2D.Double(fallPos[i]*30.0, fy ,30, 30));
			
		}   
	}   
}
}

