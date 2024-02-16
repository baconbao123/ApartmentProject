package model;

import java.time.LocalDate;

public class Fees {
	private Integer id;
	private Integer room;
	private LocalDate time;
	private String note;
	private Boolean status;
	private float water;
	private float electric;
	private float rent;
	private float other;
	private float total;
	private int water_id;
	private int electric_id;
	private int rent_id;
	private int other_id;
	private int total_int;
	public Fees(Integer id, Integer room, LocalDate time, String note, Boolean status, float water, float electric,
			float rent, float other, float total, int water_id, int electric_id, int rent_id, int other_id,
			int total_int) {
		this.id = id;
		this.room = room;
		this.time = time;
		this.note = note;
		this.status = status;
		this.water = water;
		this.electric = electric;
		this.rent = rent;
		this.other = other;
		this.total = total;
		this.water_id = water_id;
		this.electric_id = electric_id;
		this.rent_id = rent_id;
		this.other_id = other_id;
		this.total_int = total_int;
	}
	public Fees() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoom() {
		return room;
	}
	public void setRoom(Integer room) {
		this.room = room;
	}
	public LocalDate getTime() {
		return time;
	}
	public void setTime(LocalDate time) {
		this.time = time;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public float getWater() {
		return water;
	}
	public void setWater(float water) {
		this.water = water;
	}
	public float getElectric() {
		return electric;
	}
	public void setElectric(float electric) {
		this.electric = electric;
	}
	public float getRent() {
		return rent;
	}
	public void setRent(float rent) {
		this.rent = rent;
	}
	public float getOther() {
		return other;
	}
	public void setOther(float other) {
		this.other = other;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public int getWater_id() {
		return water_id;
	}
	public void setWater_id(int water_id) {
		this.water_id = water_id;
	}
	public int getElectric_id() {
		return electric_id;
	}
	public void setElectric_id(int electric_id) {
		this.electric_id = electric_id;
	}
	public int getRent_id() {
		return rent_id;
	}
	public void setRent_id(int rent_id) {
		this.rent_id = rent_id;
	}
	public int getOther_id() {
		return other_id;
	}
	public void setOther_id(int other_id) {
		this.other_id = other_id;
	}
	public int getTotal_int() {
		return total_int;
	}
	public void setTotal_int(int total_int) {
		this.total_int = total_int;
	}
	@Override
	public String toString() {
		return "Fees [id=" + id + ", room=" + room + ", time=" + time + ", note=" + note + ", status=" + status
				+ ", water=" + water + ", electric=" + electric + ", rent=" + rent + ", other=" + other + ", total="
				+ total + ", water_id=" + water_id + ", electric_id=" + electric_id + ", rent_id=" + rent_id
				+ ", other_id=" + other_id + ", total_int=" + total_int + "]";
	}
	
}
