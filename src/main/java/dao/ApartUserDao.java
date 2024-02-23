package dao;

import java.util.ArrayList;
import java.util.List;

import common.ConnectDB;
import entity.Apartment;
import entity.Contract;
import entity.Users;

// user
public class ApartUserDao {
	public List<Apartment> selApartOfUser(int userID) {
		List<Apartment> list = new ArrayList<>();
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call selUserAparts(?)}")
		)
		{
			cs.setInt(1, userID);
			var rs = cs.executeQuery();
			while(rs.next()) {
				var apart = new Apartment();
				apart.setRoomNumber(rs.getInt("rooms"));
				list.add(apart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
