package table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import action.ActionRenter;


//render action panel - hiển thị
public class TableActionCellRender extends DefaultTableCellRenderer {
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		ActionRenter action = new ActionRenter();
		if(isSelected==false && row%2==0) {
			action.setBackground(com.getBackground());
		} else {
			action.setBackground(com.getBackground());
		}
		return action;
	}
}
