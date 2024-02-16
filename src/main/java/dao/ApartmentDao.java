package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.ConnectDB;
import entity.Apartment;

public class ApartmentDao {
	public void insertApart(Apartment apart) {
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call addApart(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		)
		{
			cs.setInt(1, apart.getId());
			cs.setInt(2, apart.getRoomNumber());
			cs.setString(3, apart.getConvenient());
			cs.setInt(4, apart.getPeople());
			cs.setInt(5, apart.getPeopleMaximun());
			cs.setBoolean(6, apart.isStatus());
			cs.setString(7, apart.getUtilities());
			cs.setString(8, apart.getType());
			cs.setInt(9, apart.getFloor());
			cs.executeUpdate();
			System.out.println("insert apartment success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Apartment> selectApartment() {
		List<Apartment> list = new ArrayList<>();
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call selApartment()}");
		)
		{
			var rs = cs.executeQuery();
			while (rs.next()) {
				var apart = new Apartment();
				apart.setRoomNumber(rs.getInt("rooms"));
				apart.setType(rs.getString("type"));
				apart.setStatus(rs.getBoolean("status"));
				apart.setPeople(rs.getInt("people"));
				apart.setPeopleMaximun(rs.getInt("peopleMaximum"));
				apart.setConvenient(rs.getString("convenient"));
				apart.setUtilities(rs.getString("utilities"));
				apart.setFloor(rs.getInt("floors"));
				
				list.add(apart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Integer> selectApartNum() {
		List<Integer> numbers = new ArrayList<>();
		List<Integer> idRoom = new ArrayList<>();
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call selApartment()}");
		)
		{
			var rs = cs.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int roomNumber = rs.getInt("rooms");
				numbers.add(roomNumber);
				idRoom.add(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return numbers;
	}
	
	
}
