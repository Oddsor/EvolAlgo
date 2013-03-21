package evoalgo.tracker;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

import evolalgo.problem.ctrnn.ITracker;

@SuppressWarnings("serial")
public class SimulationAnimation extends JFrame {


	private JFrame frame;
	private draw dr;
	private char[] board;
	private int[] trackerPos;
	private int[] fallPos;
	private int fallLevel;
	private Container con;
	static final int WIDTH=900;
	static final int HEIGHT=450+60;
	TrackerEnvironment env;
	public SimulationAnimation(ITracker it, IPointAwarder pa) {

		env = new TrackerEnvironment(it, pa);
		env.setFallingObject(new VerticalFallingObject());
		fallLevel = env.getFallingObject().getYPosition();
		updateMatrix();
		frame = new JFrame("StixRPG");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);
		con = frame.getContentPane();
		con.setBackground(Color.black);
		dr = new draw();
		dr.setBackground(Color.black);
		con.add(dr);
		//	        board  = new char[30];
		//	        board[9] = "*".toCharArray()[0];
		//	        board[10] = "*".toCharArray()[0];
		//	        board[11] = "*".toCharArray()[0];
		//	        board[12] = "*".toCharArray()[0];
		//	        board[13] = "*".toCharArray()[0];
		loop();
	}

	private void updateMatrix(){

		trackerPos = env.getTracker().getPosition();
		fallPos = env.getFallingObject().getPosition();
		fallLevel = env.step();
		if(fallLevel < 0) env.setFallingObject((new VerticalFallingObject()));
	}
	
	void loop(){
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			updateMatrix();
			con.repaint();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new SimulationAnimation(new randomTracker(), new HitAwarder());
		
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

