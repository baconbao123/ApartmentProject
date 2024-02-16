package entity;

import java.time.LocalDate;

public class History {
	private String module; 
	private String operation;
	private LocalDate time;
	private String updated_by;
	
	public String getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public LocalDate getTime() {
		return time;
	}
	public void setTime(LocalDate time) {
		this.time = time;
	}
	public History(String module, String operation, LocalDate time) {
		this.module = module;
		this.operation = operation;
		this.time = time;
	}
	public History() {
	}
	@Override
	public String toString() {
		return "History [module=" + module + ", operation=" + operation + ", time=" + time + ", updated_by="
				+ updated_by + "]";
	}
	
}
