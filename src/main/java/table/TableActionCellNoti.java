package table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import action.ActionNoti;
import action.ActionRenter;


//render action panel - hiển thị
public class TableActionCellNoti extends DefaultTableCellRenderer {
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		ActionNoti action = new ActionNoti();
		
		return action;
	}
}
