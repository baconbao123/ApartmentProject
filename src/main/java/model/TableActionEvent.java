package model;

public interface TableActionEvent {		
	public void edit(int row);
	public void pay(int row);
	public void report(int row);
}
