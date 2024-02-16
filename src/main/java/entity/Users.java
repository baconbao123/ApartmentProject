package entity;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Users {
	private int id;
	private String avatar;
	private String name;
	private String gender;
	private String phone;
	private Date dob;
	private String address;
	private String nic;
	private String iAuthority;
	private String imgIAuthority;
	private String email;
	private String pw;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getiAuthority() {
		return iAuthority;
	}

	public void setiAuthority(String iAuthority) {
		this.iAuthority = iAuthority;
	}

	public String getImgIAuthority() {
		return imgIAuthority;
	}

	public void setImgIAuthority(String imgIAuthority) {
		this.imgIAuthority = imgIAuthority;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public Users() {
	};

	public Users(int id, String avatar, String name, String gender, String phone, Date dob, String address, String nic,
			String iAuthority, String imgIAuthority, String email, String pw) {
		this.id = id;
		this.avatar = avatar;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.dob = dob;
		this.address = address;
		this.nic = nic;
		this.iAuthority = iAuthority;
		this.imgIAuthority = imgIAuthority;
		this.email = email;
		this.pw = pw;
	}
	@Override
	public String toString() {
		return "Users [avatar=" + avatar + ", name=" + name + ", gender=" + gender + ", phone=" + phone + ", dob=" + dob
				+ ", address=" + address + ", nic=" + nic + ", iAuthority=" + iAuthority + ", imgIAuthority="
				+ imgIAuthority + ", email=" + email + ", pw=" + pw + "]";
	}
	
	
	

	

}
