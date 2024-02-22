package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Noti {
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int user_id;
	private int to_user;
	private String mess;
	private boolean status;
	private LocalDateTime created_at;
	private String sender_email;
	private String sender_name;
	private String receiver_email;
	private String receiver_name;
	private String sender_room_number;
	private Boolean sender_role;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getTo_user() {
		return to_user;
	}

	public void setTo_user(int to_user) {
		this.to_user = to_user;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public String getSender_email() {
		return sender_email;
	}

	public void setSender_email(String sender_email) {
		this.sender_email = sender_email;
	}

	public String getSender_name() {
		return sender_name;
	}

	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}

	public String getReceiver_email() {
		return receiver_email;
	}

	public void setReceiver_email(String receiver_email) {
		this.receiver_email = receiver_email;
	}

	public String getReceiver_name() {
		return receiver_name;
	}

	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}

	public String getSender_room_number() {
		return sender_room_number;
	}

	public void setSender_room_number(String sender_room_number) {
		this.sender_room_number = sender_room_number;
	}

	public Boolean getSender_role() {
		return sender_role;
	}

	public void setSender_role(Boolean sender_role) {
		this.sender_role = sender_role;
	}



	public Noti(int id, int user_id, int to_user, String mess, boolean status, LocalDateTime created_at,
			String sender_email, String sender_name, String receiver_email, String receiver_name,
			String sender_room_number, Boolean sender_role) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.to_user = to_user;
		this.mess = mess;
		this.status = status;
		this.created_at = created_at;
		this.sender_email = sender_email;
		this.sender_name = sender_name;
		this.receiver_email = receiver_email;
		this.receiver_name = receiver_name;
		this.sender_room_number = sender_room_number;
		this.sender_role = sender_role;
	}

	public void setCreated_at(String created_at) {

		this.created_at = convertStringToLocalDateTime(created_at);
	}

	private LocalDateTime convertStringToLocalDateTime(String created_at) {
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	    try {
	        
	    	  return LocalDateTime.parse(created_at, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	    } catch (DateTimeParseException e) {
	        
	        System.err.println("Unable to parse the input string: " + e.getMessage());
	        return null; 
	    }
	}

	public Noti() {
	};

	@Override
	public String toString() {
		return "Noti [id="+id+",user_id=" + user_id + ", to_user=" + to_user + ", mess=" + mess + ", status=" + status
				+ ", created_at=" + created_at + ", sender_email=" + sender_email + ", sender_name=" + sender_name
				+ ", receiver_email=" + receiver_email + ", receiver_name=" + receiver_name + ", sender_room_number="
				+ sender_room_number + ", sender_role=" + sender_role + "]";
	}

}
