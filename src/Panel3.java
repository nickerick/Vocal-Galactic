
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel3 extends JPanel {

    private static final long serialVersionUID = 1L;
    private JButton menuButton = new JButton("Menu");
    private JLabel title = new JLabel("Tutorial");
    private JLabel tutorial = new JLabel("Use arrow keys to move. Space bar to shoot.");
    private JLabel tutorial1 = new JLabel("Press shift to scan for voice command.");

    public Panel3() {

        Dimension dimension = new Dimension(1280, 720);
        setLayout(null);
        setPreferredSize(dimension);
        setSize(dimension);
        setBackground(Color.black);

        setFocusable(true);
        requestFocus();
        requestFocusInWindow();

        title.setBounds(500, 0,300, 130);
        title.setOpaque(false);
        title.setForeground(Color.white);
        title.setFont(new Font("SERIF",1,80));
        add(title);

        menuButton.setBounds(0, 600, 170, 100);
        menuButton.setBackground(Color.black);
        menuButton.setForeground(Color.white);
        menuButton.setBorderPainted(false);
        menuButton.setFont(new Font("PLAIN",1,30));
        menuButton.setContentAreaFilled(false);
        menuButton.addActionListener(new Panel3.MyButtonListener());
        add(menuButton);

        tutorial.setBounds(350, 100, 700, 500);
        tutorial.setBackground(Color.black);
        tutorial.setForeground(Color.white);
        tutorial.setFont(new Font("PLAIN",1,30));
        add(tutorial);

        tutorial1.setBounds(400, 150, 600, 500);
        tutorial1.setBackground(Color.black);
        tutorial1.setForeground(Color.white);
        tutorial1.setFont(new Font("PLAIN",1,30));
        add(tutorial1);

    }


    public static class MyButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            RunGame.switchToTitle();
        }
    }

}
