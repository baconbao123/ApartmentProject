package table;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

import action.ActionRenter;
import event.EventTableRenterAction;

// chỉnh sửa dữ liệu
public class TableActionCellEditor extends DefaultCellEditor  {
	private EventTableRenterAction event;
	
	public TableActionCellEditor(EventTableRenterAction event) {
		super(new JCheckBox());
		this.event = event;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		ActionRenter action = new ActionRenter();
		action.initEvent(event, row);
		return action;
	}


}
