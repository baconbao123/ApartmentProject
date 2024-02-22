package entity;

public class UsersOtherId {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	private String receiver_email;
	private String name;
	private int numRoom;
	public String getReceiver_email() {
		return receiver_email;
	}
	public void setReceiver_email(String receiver_email) {
		this.receiver_email = receiver_email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumRoom() {
		return numRoom;
	}
	public void setNumRoom(int numRoom) {
		this.numRoom = numRoom;
	}
	public UsersOtherId(String receiver_email, String name, int numRoom) {
		super();
		this.receiver_email = receiver_email;
		this.name = name;
		this.numRoom = numRoom;
	}
	@Override
	public String toString() {
		return "[Id="+id+"receiver_email=" + receiver_email + ", name=" + name + ", numRoom=" + numRoom + "]";
	}
	
	public UsersOtherId() {};
}
