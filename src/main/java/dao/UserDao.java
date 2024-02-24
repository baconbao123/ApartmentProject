package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import common.ConnectDB;
import component.Login;
import entity.UsersOtherId;
import entity.Users;

public class UserDao {
	private int userId;
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void insertRenter(Users user) {
		try (
				var con = ConnectDB.getConnect();
				var cs = con.prepareCall("{call addRenter(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			) 
		{
			cs.setString(1, user.getAvatar());
			cs.setString(2, user.getName());
			cs.setString(3, user.getGender());
			cs.setString(4, user.getPhone());
			cs.setDate(5, (Date) user.getDob());
			cs.setString(6, user.getAddress());
			cs.setString(7, user.getNic());
			cs.setString(8, user.getiAuthority());
			cs.setString(9, user.getImgIAuthority());
			cs.setString(10, user.getEmail()); // email
			cs.setString(11, "123"); // password
			cs.setBoolean(12, false); // is_active
			cs.setTimestamp(13, new java.sql.Timestamp(System.currentTimeMillis()));
			cs.executeUpdate();
			System.out.println("insert success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateUser(Users user) {
		try 
		( 
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call upUser(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		)
		{
			cs.setInt(1, user.getId());
			cs.setString(2, user.getAvatar());
			cs.setString(3, user.getName());
			cs.setString(4, user.getEmail());
			cs.setString(5, user.getGender());
			cs.setString(6, user.getPhone());
			cs.setDate(7, (Date) user.getDob());
			cs.setString(8, user.getAddress());
			cs.setString(9, user.getNic());
			cs.setString(10, user.getiAuthority());
			cs.setString(11, user.getImgIAuthority());
			cs.executeUpdate();
			System.out.println("Update renter success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Users> selectRenders(int pageNumber, int rowOfPage) {
		List<Users> list = new ArrayList<>();
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call selRenter(?, ?)}");
			
		) 
		{
			cs.setInt(1, pageNumber);
			cs.setInt(2, rowOfPage);
			var rs = cs.executeQuery();
			while(rs.next()) {
				var renter = new Users();
				renter.setId(rs.getInt("id"));
				renter.setAvatar(rs.getString("avatar"));
				renter.setName(rs.getString("name"));
				renter.setEmail(rs.getString("email"));
				renter.setGender(rs.getString("gender"));
				renter.setDob(rs.getDate("dob"));
				renter.setPhone(rs.getString("phone"));
				renter.setAddress(rs.getString("address"));
				renter.setNic(rs.getString("nic"));
				renter.setiAuthority(rs.getString("iAuthority"));
				renter.setImgIAuthority(rs.getString("imgIAuthority"));
				list.add(renter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}

	
	public List<Object> selRenterName() {
		List<Object> list = new ArrayList<>();
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call selectRenterName()}");
		)
		{
			
			var rs = cs.executeQuery();
			while(rs.next()) {
				var renter = new Users();
				renter.setId(rs.getInt("id"));
				renter.setName(rs.getString("name"));
				renter.setPhone(rs.getString("phone"));
				renter.setDob(rs.getDate("dob"));
				renter.setNic(rs.getString("nic"));
				list.add(renter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Users> selRenterNameInfor() {
		List<Users> list = new ArrayList<>();
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call selectRenterName()}");
		)
		{
			
			var rs = cs.executeQuery();
			while(rs.next()) {
				var renter = new Users();
				renter.setId(rs.getInt("id"));
				renter.setName(rs.getString("name"));
				renter.setPhone(rs.getString("phone"));
				renter.setDob(rs.getDate("dob"));
				renter.setNic(rs.getString("nic"));
				list.add(renter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Users> selRoomateInFor(List<Integer> idList) {
		List<Users> list = new ArrayList<>();
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call selectRenterName()}");
		)
		{
			
			var rs = cs.executeQuery();
			while(rs.next()) {
				int roomateId = rs.getInt("id");
				if(idList.contains(roomateId)) {
					var renter = new Users();
					renter.setId(rs.getInt("id"));
					renter.setName(rs.getString("name"));
					renter.setPhone(rs.getString("phone"));
					renter.setDob(rs.getDate("dob"));
					renter.setNic(rs.getString("nic"));
					list.add(renter);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public int countRenter() {
		int count = 0;
		
		try
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call countRenter()}");
			var rs = cs.executeQuery();
		)
		{
			while (rs.next()) {
				count = rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	// Call info user
	public Users selUser(int id) {
		Users user = null;
	    try (var con = ConnectDB.getConnect();
	         var cs = con.prepareCall("{call selInforUser(?)}");
	    ) {
	        cs.setInt(1, id);
	        var rs = cs.executeQuery();
	        if (rs.next()) {
	            user = new Users();
	            user.setAvatar(rs.getString("avatar"));
	            user.setName(rs.getString("name"));
	            user.setGender(rs.getString("gender"));
	            user.setDob(rs.getDate("dob"));
	            user.setPhone(rs.getString("phone"));
	            user.setAddress(rs.getString("address"));
	            user.setNic(rs.getString("nic"));
	            user.setiAuthority(rs.getString("iAuthority"));
	            user.setImgIAuthority(rs.getString("imgIAuthority"));
	            user.setEmail(rs.getString("email"));
	            user.setPw(rs.getString("password"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return user;
	}
	// update info user
	public Users updateInforUser(int id, String address, String phone, String gender, String avatar, String name, String pwd, String email, String imgIAuthority, String iAuthority, String nic,Date dob) {
		Users user = null;
	    try (
	        Connection con = ConnectDB.getConnect();
	        CallableStatement cs = con.prepareCall("{call updateInforUser(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
	    ) {
	        cs.setInt(1, id);
	        cs.setString(2, address);
	        cs.setString(3, phone);
	        cs.setString(4, gender);
	        cs.setString(5, avatar);
	        cs.setString(6, name);
	        cs.setString(7, pwd);
	        cs.setString(8, email);
	        cs.setString(9, imgIAuthority);
	        cs.setString(10, iAuthority);
	        cs.setString(11, nic);
	        cs.setDate(12, dob);

	        int rowsAffected = cs.executeUpdate();
	        if (rowsAffected > 0) {
	            user = new Users();
	            user.setId(id); // Assuming you have a method to set the user's ID
	            user.setAddress(address);
	            user.setPhone(phone);
	            user.setGender(gender);
	            user.setAvatar(avatar);
	            user.setName(name);
	            user.setPw(pwd);
	            user.setEmail(email);
	            user.setImgIAuthority(imgIAuthority);
	            user.setiAuthority(iAuthority);
	            user.setNic(nic);
	            user.setDob(dob);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return user;
		
	}
	
	// selAllUser
	public List<Users> selAllUser() {
		List<Users> listUser = new ArrayList<>();
		try {
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call selAllUser}");
			var rs = cs.executeQuery();
			{
				while (rs.next()) {
					var user = new Users();
					user.setName(rs.getString("name"));
					user.setEmail(rs.getString("email"));
					user.setPw(rs.getString("password"));
					listUser.add(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listUser;
	}
	// selAllUserOtherID
	public List<UsersOtherId> selAllUserOtherId(int id) {
		List<UsersOtherId> listUser = new ArrayList<>();
		try {
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call selAllUserOtherId(?)}");{
				 cs.setInt(1, id);
			};
			var rs = cs.executeQuery();
			{
				while (rs.next()) {
					var user = new UsersOtherId();
					user.setReceiver_email(rs.getString("receiver_email"));
					user.setName(rs.getString("name"));
					user.setNumRoom(rs.getInt("rooms"));
					user.setId(rs.getInt("id"));
					listUser.add(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listUser;
	}
	public List<Users> selectRendersFilteredData(String name, String phone, String nic) {
		List<Users> list = new ArrayList<>();
		try
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call filterDataTableRenter(?, ?, ?)}");
		) 
		{
			cs.setString(1, name);
			cs.setString(2, phone);
			cs.setString(3, nic);
			var rs = cs.executeQuery();
			while(rs.next()) {
				var user = new Users();
				user.setAvatar(rs.getString("avatar"));
				user.setName(rs.getString("name"));
				user.setGender(rs.getString("gender"));
				user.setDob(rs.getDate("dob"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
				user.setNic(rs.getString("nic"));
				user.setiAuthority(rs.getString("iAuthority"));
				user.setImgIAuthority(rs.getString("imgIAuthority"));
				list.add(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
	
	public List<Object> login(String name, String pass) {
		var list = new ArrayList<>();
		try(
				var con = ConnectDB.getConnect();
				var cs = con.prepareCall("{call login(?, ?)}");) 
			{
			cs.setString(1, name);
			cs.setString(2, pass);
			var rs = cs.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt("exist"));
				list.add(rs.getBoolean("is_admin"));
//				this.userId = rs.getInt("id");
				Login.setId(rs.getInt("id"));
				JOptionPane.showMessageDialog(null, list.toString());
			}
		return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Object> emailExist(String email) {
		var list = new ArrayList<>();
		try (var con = ConnectDB.getConnect(); var cs = con.prepareCall("{call emailExist(?)}");) {
			cs.setString(1, email);
			var rs = cs.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt("exist"));
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	
}
