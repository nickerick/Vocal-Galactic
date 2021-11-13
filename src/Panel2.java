import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Panel2 extends JPanel {

    private static final long serialVersionUID = 1L;
    private JButton startButton = new JButton("START");
    private JButton tutorialButton = new JButton("Tutorial");
    private JButton settingsButton = new JButton("Settings");
    private JLabel title = new JLabel("Vocal Galactic");

    public Panel2() {
        Dimension dimension = new Dimension(1280, 720);
        setLayout(null);
        setPreferredSize(dimension);
        setSize(dimension);
        setBackground(Color.black);

        setFocusable(true);
        requestFocus();
        requestFocusInWindow();

        title.setBounds(325, 120,800, 200);
        title.setOpaque(false);
        title.setForeground(Color.white);
        title.setFont(new Font("Impact",1,100));
        add(title);

        startButton.setBounds(464, 400, 350, 100);
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setBorderPainted(false);
        startButton.setFont(new Font("PLAIN",1,80));
        startButton.setContentAreaFilled(false);
        startButton.addActionListener(new MyButtonListener());
        add(startButton);

        tutorialButton.setBounds(0, 550, 200, 80);
        tutorialButton.setBackground(Color.black);
        tutorialButton.setForeground(Color.white);
        tutorialButton.setBorderPainted(false);
        tutorialButton.setFont(new Font("PLAIN",1,30));
        tutorialButton.setContentAreaFilled(false);
        tutorialButton.addActionListener(new MyButtonListener1());
        add(tutorialButton);
        
        settingsButton.setBounds(0, 600, 200, 80);
        settingsButton.setBackground(Color.black);
        settingsButton.setForeground(Color.white);
        settingsButton.setBorderPainted(false);
        settingsButton.setFont(new Font("PLAIN",1,30));
        settingsButton.setContentAreaFilled(false);
        settingsButton.addActionListener(new MyButtonListener2());
        add(settingsButton);
        

    }

    public class MyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            RunGame.switchToGame();
        }
    }

    public class MyButtonListener1 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            RunGame.switchToTutorial();
        }
    }
    
    public class MyButtonListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            RunGame.switchToSettings();
        }
    }
    

}
