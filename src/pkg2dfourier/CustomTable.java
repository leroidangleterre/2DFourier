package pkg2dfourier;

import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author arthu
 */
public class CustomTable extends JTable {

    private ArrayList<Subscriber> listeners;

    public CustomTable(String[][] rowData, String[] header) {
        super(rowData, header);
        listeners = new ArrayList<>();
    }

    // Add a new listener
    public void addListener(Subscriber s) {
        if (!listeners.contains(s)) {
            listeners.add(s);
        }
    }

    private void notifyListeners(String string) {
        for (Subscriber s : listeners) {
            s.update(string);
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return (col != 0);
    }

    @Override
    public void setValueAt(Object newValue, int rowIndex, int colIndex) {

        double newDoubleVal = -42;

        try {
            newDoubleVal = Double.parseDouble((String) newValue);
            super.setValueAt(newValue, rowIndex, colIndex);

        } catch (NumberFormatException e) {
            System.out.println("Wrong format for number input: " + e);
        }

        String notification = "" + rowIndex + " ";
        if (colIndex == 1) {
            notification += "length " + newDoubleVal;
        } else if (colIndex == 2) {
            notification += "phase " + newDoubleVal;
        }
        notifyListeners(notification);

    }
}
