package model;

public class Rooms {
	private int rooms;

	private String convenient;
	private String ult;
	private int status;
	private String type;
	private String address;
	private int floor;


	public String getConvenient() {
		return convenient;
	}
	public void setConvenient(String convenient) {
		this.convenient = convenient;
	}
	public String getUlt() {
		return ult;
	}
	public void setUlt(String ult) {
		this.ult = ult;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public Rooms(int rooms,  String convenient, String ult, int status, String type, String address,
			int floor) {
		this.rooms = rooms;
		this.convenient = convenient;
		this.ult = ult;
		this.status = status;
		this.type = type;
		this.address = address;
		this.floor = floor;
	}
	public int getRooms() {
		return rooms;
	}
	public void setRooms(int rooms) {
		this.rooms = rooms;
	}
	public Rooms() {
	}
	@Override
	public String toString() {
		return "Rooms [rooms=" + rooms + ", convenient=" + convenient + ", ult=" + ult + ", status="
				+ status + ", type=" + type + ", address=" + address + ", floor=" + floor + "]";
	}
	
	
}
