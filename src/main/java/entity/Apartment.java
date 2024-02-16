package entity;

public class Apartment {
	private int id;
	private int roomNumber;
	private String type;
	private boolean status;
	private int people;
	private int peopleMaximun;
	private String convenient;
	private String utilities;
	private int floor;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public int getPeopleMaximun() {
		return peopleMaximun;
	}

	public void setPeopleMaximun(int peopleMaximun) {
		this.peopleMaximun = peopleMaximun;
	}

	public String getConvenient() {
		return convenient;
	}

	public void setConvenient(String convenient) {
		this.convenient = convenient;
	}

	public String getUtilities() {
		return utilities;
	}

	public void setUtilities(String utilities) {
		this.utilities = utilities;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public Apartment() {};
	public Apartment(int id, int roomNumber, String type, boolean status, int people, int peopleMaximun,
			String convenient, String utilities, int floor) {
		this.id = id;
		this.roomNumber = roomNumber;
		this.type = type;
		this.status = status;
		this.people = people;
		this.peopleMaximun = peopleMaximun;
		this.convenient = convenient;
		this.utilities = utilities;
		this.floor = floor;
	}

	
}
