package dao;

import java.util.ArrayList;
import java.util.List;

import common.ConnectDB;
import model.Rooms;

public class RoomsDao {

	public List<Rooms> getRooms() {
		List <Rooms> list = new ArrayList<Rooms>();
		try(
				var con  = ConnectDB.getConnect();
				var cs = con.prepareStatement("{call getAllRooms()}");
				var rs = cs.executeQuery();
				){
			while(rs.next()) {
				var room = new Rooms();
				room.setRooms(rs.getInt("id"));
				room.setConvenient(rs.getString("convenient"));
				room.setFloor(rs.getInt("floors"));
				room.setUlt(rs.getString("utilities"));
				list.add(room);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
