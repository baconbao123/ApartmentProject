package dao;


import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import javax.swing.JOptionPane;

import common.ConnectDB;
import model.Fees;
import model.Rooms;
import component.Login;


public class FeesDao {
	private HistoryDao daoHistory = new HistoryDao();
	private String module = "Management Payment";
	
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public List<Fees> getFees(Integer pageNumber,Integer rowOfPage,String room,String status,java.util.Date date, java.util.Date date2, Boolean filter, Integer id ) {
		List<Fees> list = new ArrayList<>();
		try(
				var con  = ConnectDB.getConnect();
				var cs = con.prepareStatement("{call paginateFees(?,?,?,?,?,?,?)}");
				)
		{
			
			if(filter) { 
				cs.setInt(1,pageNumber);
				cs.setInt(2,rowOfPage);
				if(room == null) {
					cs.setNull(3, java.sql.Types.INTEGER);
				}
				else {
					cs.setInt(3, Integer.parseInt(room));
				}
				
				cs.setInt(4, Integer.parseInt(status));
				if(date == null) {
					cs.setNull(5, java.sql.Types.DATE);
				}
				else {
					var fromTime  =  new java.sql.Date(date.getTime());
					cs.setDate(5, fromTime);
				}
				if(date2 ==null ) {
					cs.setNull(6, java.sql.Types.DATE);
				}
				else {
					var toTime  =  new java.sql.Date(date2.getTime());
					
					cs.setDate(6, toTime);
				}
				
				if(id ==null) {
					cs.setNull(7, java.sql.Types.INTEGER);
				}
				else {
					cs.setInt(7, id);
				}
			}
			else {
				cs.setInt(1,pageNumber);
				cs.setInt(2,rowOfPage);
				cs.setNull(3, java.sql.Types.INTEGER);
				cs.setInt(4, 0);
				cs.setNull(5, java.sql.Types.DATE);
				cs.setNull(6, java.sql.Types.DATE);
				cs.setNull(7, java.sql.Types.INTEGER);
			}
			var rs = cs.executeQuery();
			while(rs.next()) {		
				System.out.println("sucess");
				var fee = new Fees();
				fee.setRoom(rs.getInt("room"));
				fee.setNote(rs.getString("note"));
				fee.setStatus(rs.getBoolean("status"));
				fee.setElectric(rs.getFloat("electric"));
				fee.setWater(rs.getFloat("water"));
				fee.setOther(rs.getFloat("other"));
				fee.setRent(rs.getFloat("rent"));
				fee.setTime(rs.getDate("time").toLocalDate());
				fee.setId(rs.getInt("id"));
				fee.setTotal(fee.getElectric()+fee.getOther()+fee.getRent()+fee.getWater());
				list.add(fee);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int total(String room, String status, java.util.Date date, java.util.Date date2, Boolean filter) {
		int count = 0;
		
		try(
				var con  = ConnectDB.getConnect();
				var cs = con.prepareStatement("{call countFees(?,?,?,?)}");
				
				)
		
		{
			if(filter) {
				if(room == null) {
					cs.setNull(1, java.sql.Types.INTEGER);
				}
				else {
					cs.setInt(1, Integer.parseInt(room));
				}
				
				cs.setInt(2, Integer.parseInt(status));
				if(date == null) {
					cs.setNull(3, java.sql.Types.DATE);
				}
				else {
					var fromTime  =  new java.sql.Date(date.getTime());
					cs.setDate(3, fromTime);
				}
				if(date2 == null ) {
					cs.setNull(4, java.sql.Types.DATE);
				}
				else {
					var toTime  =  new java.sql.Date(date2.getTime());
					cs.setDate(4, toTime);
				}
			}
			else  {
				cs.setNull(1, java.sql.Types.INTEGER);
				cs.setInt(2, Integer.parseInt(status));
				cs.setNull(3, java.sql.Types.DATE);
				cs.setNull(4, java.sql.Types.DATE);
			}
			var rs = cs.executeQuery();
			while(rs.next()) {
				count = rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int changeStatus(int id, boolean status) {
		int result =0;
		try(
				var con  = ConnectDB.getConnect();
				var cs = con.prepareStatement("{call changeStatus(?,?)}");
				) {	
			cs.setBoolean(1, status);
			cs.setInt(2, id);
			var rs = cs.executeUpdate();
			
			if(rs>0) {
				daoHistory.insertHistory(module, "Change status of id "+id+" from "+!status+" to "+ status, Login.getId());
			}
			return rs;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Fees showEditItem(int id) {
		var fee = new Fees();
		try(
				var con  = ConnectDB.getConnect();
				var cs = con.prepareStatement("{call showItemEdit(?)}");
				) {
			cs.setInt(1, id);
			var rs = cs.executeQuery();
			while(rs.next()) {
				fee.setRoom(rs.getInt("room"));
				fee.setNote(rs.getString("note"));
				fee.setStatus(rs.getBoolean("status"));
				fee.setElectric(rs.getFloat("electric"));
				fee.setWater(rs.getFloat("water"));
				fee.setOther(rs.getFloat("other"));
				fee.setRent(rs.getFloat("rent"));
				fee.setTime(rs.getDate("time").toLocalDate());
				fee.setId(rs.getInt("id"));
				fee.setWater_id(rs.getInt("water_id"));
				fee.setElectric_id(rs.getInt("elec_id"));
				fee.setOther_id(rs.getInt("other_id"));
				fee.setRent_id(rs.getInt("rental_id"));
				fee.setTotal(fee.getElectric()+fee.getOther()+fee.getRent()+fee.getWater());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fee;
	}
	
	public int updateMoney(int id,float money ) {
		try(
				var con  = ConnectDB.getConnect();
				var cs = con.prepareStatement("{call updateMoney(?,?)}");
				) {
			cs.setInt(1, id);
			cs.setFloat(2, money);
			var rs = cs.executeUpdate();
			return rs;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public HistoryDao getDaoHistory() {
		return daoHistory;
	}

	public void setDaoHistory(HistoryDao daoHistory) {
		this.daoHistory = daoHistory;
	}

	public int updateFees(int id, String note) {
		try(
				var con  = ConnectDB.getConnect();
				var cs = con.prepareStatement("{call updateFees(?,?)}");
				) {
			cs.setInt(1, id);
			cs.setString(2, note);
			var rs = cs.executeUpdate();
			return rs;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void syncNewFeeMonth(LocalDate time) {
		var dao = new RoomsDao();
		var listRoom = dao.getRooms();
	
		listRoom.stream().forEach(item-> {
			try(
					var con  = ConnectDB.getConnect();
					var cs = con.prepareStatement("{call insertNewMonth(?,?)}");
					)
			{
				cs.setInt(1,item.getRooms() );
				cs.setDate(2, Date.valueOf(time));
				cs.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	public Fees selFees(int id) {
		try(
				var con  = ConnectDB.getConnect();
				var cs = con.prepareStatement("{call selFee(?)}");
				) {
			cs.setInt(1, id);
			
			var rs = cs.executeQuery();
			var fee = new Fees();
			while(rs.next()) {
				fee.setRoom(rs.getInt("room"));
				fee.setNote(rs.getString("note"));
				fee.setStatus(rs.getBoolean("status"));
				fee.setElectric(rs.getFloat("electric"));
				fee.setWater(rs.getFloat("water"));
				fee.setOther(rs.getFloat("other"));
				fee.setRent(rs.getFloat("rent"));
				fee.setTime(rs.getDate("time").toLocalDate());
				fee.setId(rs.getInt("id"));
				fee.setTotal(fee.getElectric()+fee.getOther()+fee.getRent()+fee.getWater());
			}
			return fee;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Fees> getFeesUser(Integer pageNumber,Integer rowOfPage,String status,java.util.Date date, java.util.Date date2, Boolean filter, Integer id, String room ) {
		List<Fees> list = new ArrayList<>();
		try(
				var con  = ConnectDB.getConnect();
				var cs = con.prepareStatement("{call feesPer(?,?,?,?,?,?,?)}");
				)
		{
			if(filter) {
				cs.setInt(1,pageNumber);
				cs.setInt(2,rowOfPage);
				cs.setInt(3, Integer.parseInt(status));
				if(date == null) {
					cs.setNull(4, java.sql.Types.DATE);
				}
				else {
					var fromTime  =  new java.sql.Date(date.getTime());
					cs.setDate(4, fromTime);
				}
				if(date2 ==null ) {
					cs.setNull(5, java.sql.Types.DATE);
				}
				else {
					var toTime  =  new java.sql.Date(date2.getTime());
					
					cs.setDate(5, toTime);
				}
					cs.setInt(6, id);
				if(room.isEmpty()) {
						cs.setNull(7, java.sql.Types.INTEGER);
					}
					else {
						cs.setInt(7, Integer.parseInt(room));
					}
			}
			else {
				cs.setInt(1,pageNumber);
				cs.setInt(2,rowOfPage);
				cs.setInt(3, 0);
				cs.setNull(4, java.sql.Types.DATE);
				cs.setNull(5, java.sql.Types.DATE);
				cs.setInt(6, id);
				cs.setNull(7, java.sql.Types.INTEGER);
			}
			var rs = cs.executeQuery();
			while(rs.next()) {		
				System.out.println("sucess");
				var fee = new Fees();
				fee.setRoom(rs.getInt("room_number"));
				fee.setNote(rs.getString("note"));
				fee.setStatus(rs.getBoolean("status"));
				fee.setElectric(rs.getFloat("electric"));
				fee.setWater(rs.getFloat("water"));
				fee.setOther(rs.getFloat("other"));
				fee.setRent(rs.getFloat("rent"));
				fee.setTime(rs.getDate("time").toLocalDate());
				fee.setId(rs.getInt("id"));
				fee.setTotal(fee.getElectric()+fee.getOther()+fee.getRent()+fee.getWater());
				list.add(fee);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int totalUserFees (Integer id, String status, java.util.Date date, java.util.Date date2, Boolean filter, String room) {
		int count = 0;
		
		try(
				var con  = ConnectDB.getConnect();
				var cs = con.prepareStatement("{call countFeesUser(?,?,?,?,?)}");
				
				)
		
		{
			if(filter) {
					cs.setInt(1, (id));
				
				cs.setInt(2, Integer.parseInt(status));
				if(date == null) {
					cs.setNull(3, java.sql.Types.DATE);
				}
				else {
					var fromTime  =  new java.sql.Date(date.getTime());
					cs.setDate(3, fromTime);
				}
				if(date2 == null ) {
					cs.setNull(4, java.sql.Types.DATE);
				}
				else {
					var toTime  =  new java.sql.Date(date2.getTime());
					cs.setDate(4, toTime);
				}
				if(room.isEmpty()) {
					cs.setNull(5, java.sql.Types.INTEGER);
				}
				else {
					cs.setInt(5, Integer.parseInt(room));
				}
			}
			else  {
				cs.setInt(1, id);
				cs.setInt(2, Integer.parseInt(status));
				cs.setNull(3, java.sql.Types.DATE);
				cs.setNull(4, java.sql.Types.DATE);
				cs.setNull(5,java.sql.Types.INTEGER);
			}
			var rs = cs.executeQuery();
			while(rs.next()) {
				count = rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
