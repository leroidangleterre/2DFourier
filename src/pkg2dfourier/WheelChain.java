package pkg2dfourier;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author arthu
 */
public class WheelChain implements Subscriber {

    private ArrayList<Wheel> list;
    int nbWheelsMax = 10;

    private int time;

    public WheelChain() {
        list = new ArrayList<>();
        for (int rank = 0; rank < nbWheelsMax; rank++) {
            list.add(new Wheel());
        }
    }

    public void setTime(int newTime) {
        time = newTime;
    }

    @Override
    public void update(String message) {
        String[] splitted = message.split(" ");
        for (String s : splitted) {
            System.out.println("    <" + s + ">");
        }
        System.out.println("---- end split");
        int index = Integer.valueOf(splitted[0]);
        String param = splitted[1];
        double value = Double.valueOf(splitted[2]);

        final Wheel currentWheel = list.get(index);

        if (param.equals("length")) {
            currentWheel.setRadius(value);
            System.out.println("Wheel " + index + " setting length to " + value);
        } else if (param.equals("freq")) {
            currentWheel.setFrequency(value);
            System.out.println("Wheel " + index + " setting phase to " + value);
        }
        computePositions();
    }

    public void paint(Graphics g) {
        for (Wheel w : list) {
            w.paint(g);
        }
    }

    /**
     * Compute the position of each wheel, given that of its parent in the
     * chain. The first wheel remains at the origin.
     *
     */
    private void computePositions() {
        Wheel prev = null;
        for (Wheel w : list) {
            if (prev != null) {
                w.setPosition(prev.getEndPosition(time));
            }
            prev = w;
        }
    }
}
