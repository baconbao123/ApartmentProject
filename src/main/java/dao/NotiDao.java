package dao;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import common.ConnectDB;
import entity.Noti;
import entity.Users;
import entity.UsersOtherId;

public class NotiDao {

	public List<Noti> selAllNoti(int id) {
		List<Noti> list = new ArrayList<>();
		try (var con = ConnectDB.getConnect(); var cs = con.prepareCall("{call selAllNoti(?)}");) {
			cs.setInt(1, id);
			var rs = cs.executeQuery();
			while (rs.next()) {
				Noti noti = new Noti();
				noti.setId(rs.getInt("id"));
				noti.setSender_name(rs.getString("sender_name"));
				noti.setSender_email(rs.getString("sender_email"));
				noti.setReceiver_email(rs.getString("receiver_name"));
				noti.setReceiver_name(rs.getString("receiver_email"));
				noti.setSender_room_number(rs.getString("sender_room_number"));
				noti.setSender_role(rs.getBoolean("sender_role"));
				noti.setMess(rs.getString("message"));
				noti.setStatus(rs.getBoolean("status"));
				// Convert java.util.Date to java.time.LocalDateTime
				java.sql.Timestamp timestamp = rs.getTimestamp("created_at");
				LocalDateTime localDateTime = timestamp.toLocalDateTime();
				noti.setCreated_at(localDateTime);
				list.add(noti);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// delete noti
	public void deleteNoti(int id) {
		try (var con = ConnectDB.getConnect(); var cs = con.prepareCall("{call deleteNoti(?)}");) {
			cs.setInt(1, id); // Set the parameter for the stored procedure
			int rowsDeleted = cs.executeUpdate();

			if (rowsDeleted > 0) {
				JOptionPane.showMessageDialog(null, "Delete successful");
			} else {
				JOptionPane.showMessageDialog(null, "No records were deleted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// add Noti
	public void insertRenter(Noti noti) {
		try (
				var con = ConnectDB.getConnect();
				var cs = con.prepareCall("{call InsertNoti(?,?,?,?)}");
			) 
		{
			cs.setInt(1, noti.getUser_id());
			cs.setInt(2, noti.getTo_user());
			cs.setString(3, noti.getMess());
			cs.setBoolean(4, noti.isStatus());
			cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
