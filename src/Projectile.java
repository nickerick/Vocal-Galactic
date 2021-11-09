
import java.awt.*;
import javax.swing.*;

public class Projectile {

    private double speed, direction, xSto, ySto;
    private int x, y, framesLeft;

    public Projectile(double xStart, double yStart, double theta) {
        xSto = xStart;
        ySto = yStart;
        x = (int) xStart;
        y = (int) yStart;
        direction = theta;
        framesLeft = 600;
        speed = 1.5;
    }

    public boolean draw(Graphics g) {
        wrapBorder(Driving.play);
        move();
        x = (int) xSto;
        y = (int) ySto;
        g.setColor(Color.white);
        g.fillOval(x - 5, y - 5, 10, 10);
        return framesLeft <= 0;
    }

    public void move() {
        xSto += speed * Math.cos(direction);
        ySto += speed * Math.sin(direction);
        framesLeft--;
    }

    public void wrapBorder(JPanel p) {
        if (xSto <= -10) {
            xSto = p.getSize().width + 5;
        }
        if (xSto >= p.getSize().width + 10) {
            xSto = -05;
        }
        if (ySto <= -10) {
            ySto = p.getSize().height + 5;
        }
        if (ySto >= p.getSize().height + 10) {
            ySto = -05;
        }
    }

    public double getX() {
        return xSto;
    }

    public double getY() {
        return ySto;
    }

}
