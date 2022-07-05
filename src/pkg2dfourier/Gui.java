package pkg2dfourier;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setPreferredSize(new Dimension(1200, 800));
        panel = new GraphicPanel();
        initTable();
        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        addTableInitButton();
        JScrollPane scroll = new JScrollPane(table);
        tablePanel.add(scroll, BorderLayout.CENTER);
        scroll.setPreferredSize(new Dimension(400, 800));
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
        table.addListener(panel);
    }

    public void setChain(WheelChain newChain) {
        chain = newChain;
        panel.setChain(newChain);
        table.addListener(chain);
    }

    private void addTableInitButton() {
        JButton b = new JButton("init table");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.setValueAt("100", 0, 1);
                table.setValueAt("0.1", 0, 2);

                table.setValueAt("70", 1, 1);
                table.setValueAt("0.2", 1, 2);

                table.setValueAt("30", 2, 1);
                table.setValueAt("0.2", 2, 2);

                table.setValueAt("10", 3, 1);
                table.setValueAt("-0.2", 3, 2);
            }
        });
        tablePanel.add(b, BorderLayout.NORTH);
    }
}
