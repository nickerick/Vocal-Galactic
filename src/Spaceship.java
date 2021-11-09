
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.io.IOException;
import java.awt.*;
import java.util.*;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;

//import org.apache.hc.core5.http.ParseException; // REMOVE THIS

public class Spaceship {
	private static double y, x, angle, speed, turnSpeed;
	public static double justFired;
	public double spawnImmunity;
	private static double L1 = 20;
	private static double L2 = 10;
	private static boolean left, right, forward, backward, shot;
	private static ArrayList<Projectile> projes = new ArrayList<Projectile>();
	private static int forceField, ffChange;
	// private static VoiceToText text;
	public static String powerUps = ""; // STRING = ALL POWERUPS THAT CAN BE ACTIVATED

	public Spaceship(double xStart, double yStart) {
		x = xStart;
		y = yStart;
		angle = 1.5 * Math.PI;
		speed = 1;
		turnSpeed = .02;
		spawnImmunity = 400;
		ffChange = 1;
		forceField = 130;
		//text = new VoiceToText();
	}

	public void drawSpaceship(Graphics g/*, boolean b*/) {
		if (spawnImmunity > 0 && spawnImmunity % 100 >= 50)
			g.setColor(Color.gray);
		else
			g.setColor(Color.white);
		if (Panel1.active3)
			g.setColor(Color.YELLOW);
		  
		 
		int[] xVal = { (int) x, (int) (x + L2 * Math.cos(angle + (12. / 18) * Math.PI)),
				(int) (x + L1 * Math.cos(angle)), (int) (x + L2 * Math.cos(angle - (12. / 18) * Math.PI)) };
		int[] yVal = { (int) y, (int) (y + L2 * Math.sin(angle + (12. / 18) * Math.PI)),
				(int) (y + L1 * Math.sin(angle)), (int) (y + L2 * Math.sin(angle - (12. / 18) * Math.PI)) };
		g.drawPolygon(xVal, yVal, 4);
		spawnImmunity--;
		testMovement();
		justFired--;
		for (int i = 0; i < projes.size(); i++) {
			if (projes.get(i).draw(g)) {
				projes.remove(i);
				i--;
			}
		}	
		if(Panel1.active2){
			if(forceField>240 || forceField<120) {
			  ffChange *= -1;
			  }
			  forceField += ffChange;
			  g.setColor(new Color(40, 40, forceField, forceField));
			  g.drawOval((int)(x-25+Math.cos(angle)*4),(int)(y-25+Math.sin(angle)*4),50,50);
			  }
		
	}

	// Reads key inputs
	public static class MyKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				left = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				right = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				forward = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				backward = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				shot = true;
			}
			// Add pressing buttons for powerups here. Just add to the powerUps string of the character that represents that power up
//			if (e.getKeyCode() == KeyEvent.VK_SHIFT) { // REMOVE THIS
//				try {
//					recordVoice();
//				} catch (ParseException | IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				left = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				right = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				forward = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				backward = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				shot = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_1) {
				powerUps = "s";
			}
			if (e.getKeyCode() == KeyEvent.VK_2) {
				powerUps = "d";
			}
			if (e.getKeyCode() == KeyEvent.VK_3) {
				powerUps = "b";
			}
		}

		// don't remove - necessary for key listener interface
		@Override
		public void keyTyped(KeyEvent e) {

		}

	}

	public void testMovement() {
		left();
		right();
		backward();
		forward();
		wrapBorder();
		checkShot();
	}

	public void checkShot() {
		if (justFired <= 0 && shot) {
			justFired = 50;
			projes.add(new Projectile((int) (x + L1 * 1.1 * Math.cos(angle)), (int) (y + L1 * 1.1 * Math.sin(angle)),
					angle));
			if (Panel1.active1) {
				projes.add(new Projectile((int) (x + L1 * 1.1 * Math.cos(angle)),
						(int) (y + L1 * 1.1 * Math.sin(angle)), angle + .6));
				projes.add(new Projectile((int) (x + L1 * 1.1 * Math.cos(angle)),
						(int) (y + L1 * 1.1 * Math.sin(angle)), angle - .6));
			}
		}
	}

	public void left() {
		if (left) {
			angle = angle - turnSpeed;
		}
	}

	public void right() {
		if (right) {
			angle = angle + turnSpeed;
		}
	}

	public void backward() {
		if (backward) {
			y = y - speed * Math.sin(angle);
			x = x - speed * Math.cos(angle);
		}
	}

	public void forward() {
		if (forward) {
			x = x + speed * Math.cos(angle);
			y = y + speed * Math.sin(angle);
		}
	}

	public void wrapBorder() {
		if (x <= -10) {
			x = Driving.play.getSize().width + 5;
		}
		if (x >= Driving.play.getSize().width + 10) {
			x = -5;
		}
		if (y <= -10) {
			y = Driving.play.getSize().height + 5;
		}
		if (y >= Driving.play.getSize().height + 10) {
			y = -5;
		}
	}

	public static ArrayList<Projectile> getProj() {
		return projes;
	}

	public double[][] getVertices() {
		double[] vertex1 = { x + L2 * Math.cos(angle + (12. / 18) * Math.PI),
				y + L2 * Math.sin(angle + (12. / 18) * Math.PI) };
		double[] vertex2 = { x + L1 * Math.cos(angle), y + L1 * Math.sin(angle) };
		double[] vertex3 = { x + L2 * Math.cos(angle - (12. / 18) * Math.PI),
				y + L2 * Math.sin(angle - (12. / 18) * Math.PI) };
		double[][] vertex = { vertex1, vertex2, vertex3 };
		return vertex;
	}

//	public static void recordVoice() throws ParseException, IOException { // REMOVE THIS
//		ExecutorService service = Executors.newFixedThreadPool(4);
//		service.submit(new Runnable() {
//			public void run() {
//				try {
//					powerUps = text.voiceToText();
//					System.out.println(powerUps);
//				} catch (ParseException | IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
//	}

}
