
import java.awt.*;
import java.util.ArrayList;

public class Asteroids {
    public int size = (int) (Math.random() * 20 + 20);
    public int[] positionX;
    public int[] positionY;
    public ArrayList<Double> thetaList = new ArrayList<Double>();
    public double x, y, xMove, yMove;
    public boolean golden = false;
    public Asteroids(boolean unused) {
        if(Math.random()>.98) // Change percentage of asteroids that are golden
            golden = true;
        for (Double i = (double) 0; i < 2 * Math.PI; i += Math.random() * Math.PI / 4) {
            thetaList.add(i);
        }

        positionX = new int[thetaList.size()];
        positionY = new int[thetaList.size()];

        if (Math.random() < .5) {
            x = Math.random() * 100;
        } else {
            x = Math.random() * 100 + Driving.play.getSize().width - 100;
        }
        if (Math.random() < .5) {
            y = Math.random() * 100;
        } else {
            y = Math.random() * 100 + Driving.play.getSize().height - 100;
        }
        if (Math.random() < .5) {
            xMove = .1 + Math.random();
        } else {
            xMove = -.1 - Math.random();
        }
        if (Math.random() < .5) {
            yMove = .1 + Math.random();
        } else {
            yMove = -.1 - Math.random();
        }

        for (int i = 0; i < thetaList.size(); i++) {
            positionX[i] = (int) (size * Math.cos(thetaList.get(i)) + x);
            positionY[i] = (int) (size * Math.sin(thetaList.get(i)) + y);

        }

    }

    public Asteroids() {
        if(Math.random() > .98) // change how many beginning gold asteroids
            golden = true;
        for (Double i = (double) 0; i < 2 * Math.PI; i += Math.random() * Math.PI / 4) {
            thetaList.add(i);
        }

        positionX = new int[thetaList.size()];
        positionY = new int[thetaList.size()];

        if (Math.random() < .5) {
            x = Math.random() * 100;
        } else {
            x = Math.random() * 100 + 1300;
        }
        if (Math.random() < .5) {
            y = Math.random() * 100;
        } else {
            y = Math.random() * 100 + 1300;
        }
        if (Math.random() < .5) {
            xMove = .1 + Math.random();
        } else {
            xMove = -.1 - Math.random();
        }
        if (Math.random() < .5) {
            yMove = .1 + Math.random();
        } else {
            yMove = -.1 - Math.random();
        }

        for (int i = 0; i < thetaList.size(); i++) {
            positionX[i] = (int) (size * Math.cos(thetaList.get(i)) + x);
            positionY[i] = (int) (size * Math.sin(thetaList.get(i)) + y);

        }

    }

    public void doAll(Graphics g) {
        moveAsteroids();
        wrapBorder();
        drawAsteroids(g);

    }

    public boolean checkLazerCol(Projectile p) {
        if (Math.sqrt(Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2)) <= size) {
            return true;
        }
        return false;
    }

    public boolean checkCollision(Spaceship s) {
        double[][] vertexes = s.getVertices();
        if (s.spawnImmunity <= 0) {
            if (Math.sqrt(Math.pow(vertexes[0][0] - x, 2) + Math.pow(vertexes[0][1] - y, 2)) <= size) {

                return true;
            }
            if (Math.sqrt(Math.pow(vertexes[1][0] - x, 2) + Math.pow(vertexes[1][1] - y, 2)) <= size) {

                return true;
            }
            if (Math.sqrt(Math.pow(vertexes[2][0] - x, 2) + Math.pow(vertexes[2][1] - y, 2)) <= size) {

                return true;
            }
        }
        return false;
    }

    public void moveAsteroids() {
        x = x + xMove;
        y = y + yMove;
        for (int i = 0; i < thetaList.size(); i++) {
            positionX[i] = (int) (size * Math.cos(thetaList.get(i)) + x);
            positionY[i] = (int) (size * Math.sin(thetaList.get(i)) + y);

        }
    }

    public void wrapBorder() {
        if (x - size <= -40) {
            x = Driving.play.getSize().width + 39 - size;
        }
        if (x + size >= Driving.play.getSize().width + 40) {
            x = -39 + size;
        }
        if (y - size <= -40) {
            y = Driving.play.getSize().height + 39 - size;
        }
        if (y + size >= Driving.play.getSize().height + 40) {
            y = -39 + size;
        }
    }

    public void drawAsteroids(Graphics g) {
        g.setColor(Color.white);
        if(golden)
        	g.setColor(Color.YELLOW);
        g.drawPolygon(positionX, positionY, positionX.length);
        g.setColor(Color.gray);
    }

}
