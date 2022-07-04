package pkg2dfourier;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author arthu
 */
public class Wheel {

    private double x, y;
    private double radius;
    private double frequency;

    public Wheel(double newX, double newY, double newRad) {
        x = newX;
        y = newY;
        radius = newRad;
        frequency = 0;
    }

    public Wheel() {
        this(0, 0, 0);
    }

    public void setRadius(double newVal) {
        radius = newVal;
    }

    public void setFrequency(double newVal) {
        frequency = newVal;
    }

    public void paint(Graphics g) {
        int xLeft = (int) (x - radius);
        int yTop = (int) (y - radius);
        int appWidth = (int) (2 * radius);
        g.setColor(Color.black);
//        g.fillOval(xLeft, yTop, appWidth, appWidth);
//        g.setColor(Color.red);
        g.drawOval(xLeft, yTop, appWidth, appWidth);
    }

    public Point getEndPosition(double time) {
        int endX = (int) (x + radius * Math.cos(time));
        int endY = (int) (y + radius * Math.sin(time));
        System.out.println("GetPos(" + time + ": {" + endX + ", " + endY + "};");
        return new Point(endX, endY);
    }

    public void setPosition(Point newPos) {
        x = newPos.x;
        y = newPos.y;
    }
}
