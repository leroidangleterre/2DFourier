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
    private double endX, endY;
    private double frequency;
    private double time;
    private double phase;

    public Wheel(double newX, double newY, double newRad) {
        x = newX;
        y = newY;
        radius = newRad;
        frequency = 0;
        phase = 0;
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

    public void setPhase(double newPhase) {
        phase = newPhase;
    }

    public void paint(Graphics g, int x0, int y0, double zoom) {

        // Draw the circle
        int h = g.getClipBounds().height;

        int xLeft = (int) ((x - radius) * zoom + x0);
        int yTop = (int) (h - ((y + radius) * zoom + y0));
        int appWidth = (int) (2 * radius * zoom);
        g.setColor(Color.black);
        g.drawOval(xLeft, yTop, appWidth, appWidth);

        // Draw the main radius
        // TODO: fix the coordinates of the tip.
//        g.drawLine((int) (x + x0), h - (int) (y + y0), (int) (x + x0 + endX), h - (int) (y + y0 + endY));
    }

    public Point getEndPosition(double newTime) {
        time = newTime;
        endX = (int) (x + radius * Math.cos(newTime + phase));
        endY = (int) (y + radius * Math.sin(newTime + phase));
        return new Point((int) endX, (int) endY);
    }

    public void setPosition(Point newPos) {
        x = newPos.x;
        y = newPos.y;
    }
}
