package pkg2dfourier;

import javax.swing.JFrame;

/**
 *
 * @author arthu
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WheelChain chain = new WheelChain();
        Gui theGui = new Gui();
        theGui.setChain(chain);
    }

}
