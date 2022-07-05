package pkg2dfourier;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;

/**
 *
 * @author arthu
 */
public class GraphicPanel extends JPanel implements Subscriber, MouseListener, MouseMotionListener, MouseWheelListener {

    private WheelChain chain;

    private int x0, y0;
    private double zoom;

    private boolean isScrolling;
    private int prevMouseX, prevMouseY;

    public GraphicPanel() {
        super();
        setPreferredSize(new Dimension(600, 400));
        isScrolling = false;
        setVisible(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
        prevMouseX = -1;
        prevMouseY = -1;
        x0 = 0;
        y0 = 0;
        zoom = 1;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
        chain.paint(g, (int) x0, (int) y0, zoom);
        paintOrigin(g);
    }

    public void setChain(WheelChain newChain) {
        chain = newChain;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 2) {
            isScrolling = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 2) {
            isScrolling = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isScrolling) {
            int dx = e.getX() - prevMouseX;
            int dy = e.getY() - prevMouseY;

            prevMouseX = e.getX();
            prevMouseY = e.getY();

            // TODO scroll
            x0 += dx;
            y0 -= dy;
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        prevMouseX = e.getX();
        prevMouseY = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        double fact;
        if (e.getWheelRotation() < 0) {
            fact = 1.1;
        } else {
            fact = 1 / 1.1;
        }

        int h = this.getHeight();

        x0 = (int) (fact * (x0 - e.getX()) + e.getX());
        y0 = (int) (fact * (y0 - (h - e.getY())) + (h - e.getY()));

        zoom *= fact;
        repaint();
    }

    @Override
    public void update(String message) {
        repaint();
    }

    private void paintOrigin(Graphics g) {
        g.setColor(Color.black);
        int h = g.getClipBounds().height;
        g.drawLine(x0, h - y0, (int) (x0 + zoom), (int) (h - (y0 + zoom)));
    }
}
