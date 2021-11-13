import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Panel1 extends JPanel {

	private static final long serialVersionUID = 1L;
	private Spaceship spaceship = new Spaceship(400, 700);
	private ArrayList<Asteroids> asteroid = new ArrayList<Asteroids>();
	public static int lives = 5, asteroidNum = 5;
	private static int score = 0;
	private JLabel lifeCounter = new JLabel(lives+" Lives");
	private JButton returnTitle = new JButton();
	private JLabel scoreCounter = new JLabel("Score: " + score);
	private JLabel finalScore = new JLabel("You scored " + score + " points!");
	private JLabel tripleShot = new JLabel("Triple Shot");
	private JLabel shield = new JLabel("Shield");
	private JLabel doublePoints = new JLabel("x2");
	public static int power1, power2, power3, counter1, counter2, counter3;
	public static boolean active1, active2, active3;
	

    public Panel1() {
        Dimension dimension = new Dimension(1280, 720);
        setPreferredSize(dimension);
        setSize(dimension);
        setLayout(null);
        setBackground(Color.black);

        // makes key listener work - DO NOT MESS
        setFocusable(true);
        requestFocus();
        requestFocusInWindow();

        // calling key listener and constantly scanning input
        Spaceship.MyKeyListener listener = new Spaceship.MyKeyListener();
        addKeyListener(listener);

        for (int i = 0; i < asteroidNum; i++) {
            asteroid.add(new Asteroids());
        }

        tripleShot.setForeground(Color.white);
        shield.setForeground(Color.white);
        doublePoints.setForeground(Color.white);
        lifeCounter.setForeground(Color.white);
        scoreCounter.setForeground(Color.white);  
        
        add(lifeCounter);
        lifeCounter.setBounds(40, 20, 100, 50);
        add(scoreCounter);
        scoreCounter.setBounds(40, 40, 100, 50);

        returnTitle.setBounds(450, 450, 400, 100);
        returnTitle.setBackground(Color.gray);
        returnTitle.setFont(new Font("PLAIN", 1, 30));
        returnTitle.addActionListener(new MyButtonListener());
        
        add(tripleShot);
		add(shield);
		add(doublePoints);
		tripleShot.setBounds(40, 100, 100, 50);
		shield.setBounds(40, 120, 100, 50);
		doublePoints.setBounds(40, 140, 120, 50);
		
		Panel1.power1 = 0;
		Panel1.power2 = 0;
		Panel1.power3 = 0;

		counter1 = 0;
		counter2 = 0;
		counter3 = 0;

    }

    @Override
    public void paintComponent(Graphics g) { //overriding paint component

    	//boolean words1 = false, words2 = false, words3 = false; //used for voice to text
        super.paintComponent(g);

        spaceship.drawSpaceship(g/*,Immunity*/);
        tripleShot.setText("[1] Triple Shot: " + power1);
		shield.setText("[2] Shield: " + power2);
		doublePoints.setText("[3] Double Points: " + power3);

        ArrayList<Projectile> lazers = Spaceship.getProj();
		for (int i = 0; i < asteroid.size(); i++) {
			asteroid.get(i).doAll(g);
			for (int j = 0; j < lazers.size(); j++) {
				if (asteroid.get(i).checkLazerCol(lazers.get(j))) {
					if (asteroid.get(i).golden) {
						if (Math.random() > .6666) {
							power1++;
						} else if (Math.random() > .5) {
							power2++;
						} else {
							power3++;
						}
					}
					
					Panel1.counter1--;
					Panel1.counter2--;
					Panel1.counter3--;

					if(counter1 <= 0) {
						Panel1.active1 = false;
					}
					if(counter2 <= 0) {
						Panel1.active2 = false;
					}
					if(counter3 <= 0) {
						Panel1.active3 = false;
					}
					
					// Ignore yellow squiggles if you have em,
					// otherwise this is how you would do power ups.
					// Just add to the powerups string of what button they press and this will check if they can use it, and if so it will
					// To add a new power up just make another if statement. NOT AN IF ELSE!!!!
					if(spaceship.powerUps.indexOf("s") >= 0 && Panel1.power1 > 0) { // Triple Shot
						Panel1.active1 = true;
						Panel1.counter1 = 100; // How long powerup lasts
						Panel1.power1--;
					}
					if(spaceship.powerUps.indexOf("d") >= 0 && Panel1.power2 > 0) { //shield
						Panel1.active2 = true;
						Panel1.counter2 = 100; // How long powerup lasts
						Panel1.power2--;
					}
					if(spaceship.powerUps.indexOf("b") >= 0 && Panel1.power3 > 0) { // Bonus points
						Panel1.active3 = true;
						Panel1.counter3 = 100; // How long powerup lasts
						Panel1.power3--;
					}
					spaceship.powerUps = "";
					asteroid.remove(i);
					if (i > 0) {
						i--;
					}
					lazers.remove(j);
					j--;
					asteroid.add(new Asteroids(true));
					score++;
					if(active3) {
						score++;
					}
					scoreCounter.setText("Score: " + score);
				}
			}
		}

        for (int i = 0; i < asteroidNum; i++) {
            asteroid.get(i).doAll(g);
            if (asteroid.get(i).checkCollision(spaceship) && !active2) {
                lives--;
                lifeCounter.setText(lives + " Lives");
                if (lives <= 0) {
                    returnTitle.setText("Return To Menu");
                    add(returnTitle);
                    finalScore.setText("You scored " + score + " points!");
                    finalScore.setBounds(475, 200, 450, 180);
                    finalScore.setOpaque(false);
                    finalScore.setForeground(Color.white);
                    finalScore.setFont(new Font("SERIF", 1, 40));
                    add(finalScore);
                    spaceship = new Spaceship(23123, 23123);
                    lives = 5;
                    score = 0;
                } else {
                    spaceship = new Spaceship(600, 400);
                }

            }
        }

        try {
            Thread.sleep(2);// change this number to increase/decrease animation speed
        } catch (Exception x) {
        }

        repaint();
    }

    public class MyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	RunGame.switchToTitle();
        }
    }

}
