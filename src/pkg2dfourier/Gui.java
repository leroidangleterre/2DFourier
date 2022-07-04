package pkg2dfourier;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

/**
 *
 * @author arthu
 */
public class Gui extends JFrame {

    private JSplitPane split;
    private GraphicPanel panel;
    private CustomTable table;
    private JPanel tablePanel;

    private int nbLinesInit = 10;

    private WheelChain chain;

    public Gui() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new GraphicPanel();
        initTable();
        tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(10, 3));
        JScrollPane scroll = new JScrollPane(table);
        tablePanel.add(scroll);
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, tablePanel);
        this.add(split);
        this.setVisible(true);
        this.pack();
    }

    private void initTable() {
        String[][] rowData = new String[nbLinesInit][];
        for (int row = 0; row < nbLinesInit; row++) {
            rowData[row] = new String[]{row + "", "0", "0"};
        }

        String[] header = {"Frequency", "length", "phase"};
        table = new CustomTable(rowData, header);
    }

    public void setChain(WheelChain newChain) {
        chain = newChain;
        panel.setChain(newChain);
        table.addListener(chain);
    }
}
