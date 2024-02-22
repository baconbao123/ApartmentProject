package table;

import javax.swing.table.DefaultTableModel;

public class CustomTable extends DefaultTableModel {
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) { // index of checkbox column
            return Boolean.class; // set the column class to Boolean (for checkboxes)
        }
        return super.getColumnClass(columnIndex);
    }
}
