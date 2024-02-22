package table;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

import action.ActionNoti;
import action.ActionRenter;
import event.EventTableNotiAction;

// chỉnh sửa dữ liệu
public class TableActionCellEditorNoti extends DefaultCellEditor  {
	private EventTableNotiAction event;
	
	public TableActionCellEditorNoti(EventTableNotiAction event) {
		super(new JCheckBox());
		this.event = event;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		ActionNoti action = new ActionNoti();
		action.initEvent(event, row);
		return action;
	}


}
