package table;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CheckBoxRenderer extends JLabel implements TableCellRenderer {
	public CheckBoxRenderer() {
        setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setSelected(value != null && (Boolean) value);
        return this;
    }

	private void setSelected(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
