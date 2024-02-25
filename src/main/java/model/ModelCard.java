package model;

public class ModelCard {
	private String text;
	private String data;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public ModelCard() {};
	public ModelCard(String text, String data) {
		super();
		this.text = text;
		this.data = data;
	}
	
	
}
