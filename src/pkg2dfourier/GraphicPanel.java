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
public class GraphicPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

    private WheelChain chain;

    private double x0, y0, zoom;

    private boolean isScrolling;
    private int prevMouseX, prevMouseY;

    public GraphicPanel() {
        super();
        setPreferredSize(new Dimension(600, 400));
        isScrolling = false;
        setVisible(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        prevMouseX = -1;
        prevMouseY = -1;
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("Panel painting");
        g.setColor(Color.white);
        g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
        chain.paint(g);
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
            System.out.println("Scroll " + e.getX() + ", " + e.getY());
            int dx = e.getX() - prevMouseX;
            int dy = e.getY() - prevMouseY;

            // TODO scroll
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
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
    }
}
