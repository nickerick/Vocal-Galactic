import javax.swing.*;

public class PowerUp {
    private int type;
    private JLabel tripleShot = new JLabel("Triple_shot.png");
    private JLabel shield = new JLabel("Shield.png");
    private JLabel doublePoints = new JLabel("X2.png");

    public PowerUp(int t) {
        type = t;
    }

    public JLabel powerLabel() {
        if(type == 1) {
            return tripleShot;
        }
        if(type == 2) {
            return shield;
        }
        return doublePoints;
    }

}
