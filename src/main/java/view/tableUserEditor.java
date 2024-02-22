package view;

import java.awt.Checkbox;
import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import event.EventShowFeeUser;
import view.ActionFees;

public class tableUserEditor extends DefaultCellEditor{
	
	private EventShowFeeUser event;
	public tableUserEditor(EventShowFeeUser event) {
		super(new JCheckBox());
		this.event = event;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		var action = new ActionFeesUser();
		action.initEvent(event,row);
		return action;
	}
}
