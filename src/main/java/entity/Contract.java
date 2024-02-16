package entity;

import java.util.Date;
import java.util.List;

public class Contract {
	private int id;
	private int apartNum;
	private int ownerID;
	private String ownerName;
	private String imgContracs;
	private boolean status;
	private Date formDate;
	private Date toDate;
	private String roomates;
	private int idRoom;
	
	public Contract() {}
	
	public int getId() {
		return id;
	}

	public int getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public int getApartNum() {
		return apartNum;
	}

	public void setApartNum(int apartNum) {
		this.apartNum = apartNum;
	}

	public int getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}

	public String getImgContracs() {
		return imgContracs;
	}

	public void setImgContracs(String imgContracs) {
		this.imgContracs = imgContracs;
	}

	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getFormDate() {
		return formDate;
	}

	public void setFormDate(Date formDate) {
		this.formDate = formDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getRoomates() {
		return roomates;
	}

	public void setRoomates(String roomates) {
		this.roomates = roomates;
	}

	public Contract(int id, int apartNum, int ownerID, String ownerName, String imgContracs, boolean status,
			Date formDate, Date toDate, String roomates, int idRoom) {
		super();
		this.id = id;
		this.apartNum = apartNum;
		this.ownerID = ownerID;
		this.ownerName = ownerName;
		this.imgContracs = imgContracs;
		this.status = status;
		this.formDate = formDate;
		this.toDate = toDate;
		this.roomates = roomates;
		this.idRoom = idRoom;
	}

	

	
	
	

	



	



	

	



	
	
	
	
	
}
