package model;

import java.awt.Checkbox;
import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import view.ActionFees;

public class tableBillEditor extends DefaultCellEditor{
	private TableActionEvent event;
	public tableBillEditor(TableActionEvent event) {
		super(new JCheckBox());
		this.event = event;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		ActionFees action = new ActionFees();
		action.initEvent(event,row);
		return action;
	}
}
