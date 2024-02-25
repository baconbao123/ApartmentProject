package model;

import java.awt.Checkbox;
import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import view.ActionFees;

public class tableBillEditor extends DefaultCellEditor{
	private TableActionEvent event;
	private Boolean status;
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public tableBillEditor(TableActionEvent event) {
		super(new JCheckBox());
		this.event = event;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		ActionFees action = new ActionFees(status);
		action.initEvent(event,row);
		return action;
	}
}
