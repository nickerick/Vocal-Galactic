import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class Panel4 extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton menuButton = new JButton("Menu");
	private JLabel title = new JLabel("Settings");
	private JLabel asteroidsSliderLabel = new JLabel("Use Bar to set Number of Asteroids.");
	private JLabel livesSliderLabel = new JLabel("Use Bar to set Number of Lives.");
	private static JSlider lives = new JSlider(1, 10);
	private static JSlider asteroids = new JSlider(3, 20);

	public Panel4() {
		Dimension dimension = new Dimension(1280, 720);
		setLayout(null);
		setPreferredSize(dimension);
		setSize(dimension);
		setBackground(Color.black);

		setFocusable(true);
		requestFocus();
		requestFocusInWindow();

		title.setBounds(500, 0, 300, 130);
		title.setOpaque(false);
		title.setForeground(Color.white);
		title.setFont(new Font("SERIF", 1, 80));
		add(title);

		menuButton.setBounds(0, 600, 170, 100);
		menuButton.setBackground(Color.black);
		menuButton.setForeground(Color.white);
		menuButton.setBorderPainted(false);
		menuButton.setFont(new Font("PLAIN", 1, 30));
		menuButton.setContentAreaFilled(false);
		menuButton.addActionListener(new Panel4.MyButtonListener());
		add(menuButton);

		asteroidsSliderLabel.setBounds(385, 200, 700, 100);
		asteroidsSliderLabel.setBackground(Color.black);
		asteroidsSliderLabel.setForeground(Color.white);
		asteroidsSliderLabel.setFont(new Font("PLAIN", 1, 30));
		add(asteroidsSliderLabel);
		asteroids.addChangeListener(new MySlider1Listener());

		asteroids.setMajorTickSpacing(1);
		asteroids.setPaintTicks(true);
		asteroids.setPaintLabels(true);
		asteroids.setBounds(350, 300, 600, 50);
		asteroids.setValue(5);
		asteroids.setBackground(Color.BLACK);
		asteroids.setForeground(Color.WHITE);
		add(asteroids);

		livesSliderLabel.setBounds(425, 375, 700, 100);
		livesSliderLabel.setBackground(Color.black);
		livesSliderLabel.setForeground(Color.white);
		livesSliderLabel.setFont(new Font("PLAIN", 1, 30));
		add(livesSliderLabel);
		lives.addChangeListener(new Panel4.MySlider2Listener());

		lives.setMajorTickSpacing(1);
		lives.setPaintTicks(true);
		lives.setPaintLabels(true);
		lives.setBounds(350, 475, 600, 50);
		lives.setBackground(Color.BLACK);
		lives.setForeground(Color.WHITE);
		add(lives);
	}

	public static class MyButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			RunGame.switchToTitle();
		}
	}

	public static class MySlider1Listener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			Panel1.asteroidNum = asteroids.getValue();
		}
	}

	public static class MySlider2Listener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			Panel1.lives = lives.getValue();
		}
	}

}