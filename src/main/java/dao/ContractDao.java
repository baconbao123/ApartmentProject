package dao;

import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import common.ConnectDB;
import entity.Contract;
import entity.Users;


public class ContractDao {
	public void insertContract(Contract contract) {
		try 
		(	var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call addContract(?, ?, ?, ?, ?, ?, ?, ?)}");
		)	
		{
			cs.setInt(1, contract.getApartNum());
			cs.setInt(2, contract.getOwnerID());
			cs.setString(3, contract.getImgContracs());
			cs.setBoolean(4, contract.isStatus());
			cs.setDate(5, (Date) contract.getFormDate());
			cs.setDate(6, (Date) contract.getToDate());
			cs.setString(7, contract.getRoomates());
			cs.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));
			cs.executeUpdate();
			System.out.println("contract insert success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	public void updateContract(Contract contract) {
		try 
		(	var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call upContract(?, ?, ?, ?, ?, ?, ?)}");
		)	
		{
			cs.setInt(1, contract.getId());
			cs.setInt(2, contract.getOwnerID());
			cs.setString(3, contract.getImgContracs());
			cs.setBoolean(4, contract.isStatus());
			cs.setDate(5, (Date) contract.getFormDate());
			cs.setDate(6, (Date) contract.getToDate());
			cs.setString(7, contract.getRoomates());
			cs.executeUpdate();
			System.out.println("contract update success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateConStatus(Contract contract) {
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call upConStatus(?, ?)}")
		)
		{
			cs.setInt(1, contract.getId());
			cs.setBoolean(2, contract.isStatus());
			cs.executeUpdate();
			System.out.println("contract status update success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	public List<Contract> selContract(int pageNumber, int rowOfPage) {
		List<Contract> list = new ArrayList<>();
		try 
		( 
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call selContract(?, ?)}");
			
		)
		{
			cs.setInt(1, pageNumber);
			cs.setInt(2, rowOfPage);
			var rs = cs.executeQuery();
			while(rs.next()) {
				var contract = new Contract();
				contract.setId(rs.getInt("id"));
				contract.setIdRoom(rs.getInt("room_id"));
				contract.setApartNum(rs.getInt("roomNumber"));
				contract.setOwnerName(rs.getString("ownerName"));
				contract.setImgContracs(rs.getString("pic"));
				contract.setStatus(rs.getBoolean("status"));
				contract.setFormDate(rs.getDate("fromDate"));
				contract.setToDate(rs.getDate("toDate"));
				contract.setRoomates(rs.getString("all_user_id"));
				contract.setOwnerID(rs.getInt("user_id"));
				
				
				list.add(contract);
				System.out.println(contract);
			}
			cs.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Contract> selAllContract() {
		List<Contract> list = new ArrayList<>();
		try 
		( 
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call selectContractInfo()}");
			
		)
		{
			var rs = cs.executeQuery();
			while(rs.next()) {
				var contract = new Contract();
				contract.setId(rs.getInt("id"));
				contract.setApartNum(rs.getInt("room_id"));
				contract.setOwnerName(rs.getString("ownerName"));
				contract.setImgContracs(rs.getString("pic"));
				contract.setStatus(rs.getBoolean("status"));
				contract.setFormDate(rs.getDate("fromDate"));
				contract.setToDate(rs.getDate("toDate"));
				contract.setRoomates(rs.getString("all_user_id"));
				list.add(contract);
			}
//			cs.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<String> getRoommateNamesByIds(String[] roomateIds) {
	    List<String> roomateNames = new ArrayList<>();
	    for (String roomId : roomateIds) {
	        int roomID = Integer.parseInt(roomId.trim());
	        String roomateName = fetchRoommateNameById(roomID); 
	        roomateNames.add(roomateName);
	        
	        
	        System.out.println(roomateNames);
	    }
	    return roomateNames;
	}
	
	private String fetchRoommateNameById(int roomId) {
		String roomatesName = null;
		try 
		( 
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call selNameByID(?)}");
		)
		{
			cs.setInt(1, roomId);
			var rs = cs.executeQuery();
			while(rs.next()) {
				roomatesName = rs.getString("name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomatesName;
	}	
	
	//select infor roomate in contract	
	public List<Contract> selectIDRoomate() {
		List<Contract> list = new ArrayList<>();
		try 
		( 
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call selectIDRommate()}");
			
		)
		{
			var rs = cs.executeQuery();
			while(rs.next()) {
				var contract = new Contract();
				contract.setId(rs.getInt("id"));
				list.add(contract);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<String> getRoommateInforsByIds(String[] roomateIds) {
	    List<String> roomateDetails = new ArrayList<>();
	    for (String roomId : roomateIds) {
	        int roomID = Integer.parseInt(roomId.trim());
	        String roomateDetail = fetchRoommateDetailById(roomID);
	        roomateDetails.add(roomateDetail);
	    }
	    return roomateDetails;
	}
	
	private String fetchRoommateDetailById(int roomId) {
		String roomateDetail = null;
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call selInfosRoomates(?)}");
		)
		{
			cs.setInt(1, roomId);
			var rs = cs.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
	            String phone = rs.getString("phone");
	            String nic = rs.getString("nic");
	            roomateDetail = name + " - " + phone + " - " + nic;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return roomateDetail;
	}

	public int countContract() {
		int count = 0;
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call countContract()}");
			var rs = cs.executeQuery();
		)
		{
			while(rs.next()) {
				count = rs.getInt("totalContract");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public List<Contract> selContractFilter(String owner, Integer room, Boolean status) {
		List<Contract> list = new ArrayList<>();
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call filterDataTableContract(?, ?, ?)}")
		)
		{
			cs.setString(1, owner);
			 if (room != null) {
		            cs.setInt(2, room);
		        } else {
		            cs.setNull(2, Types.INTEGER);
		        }
			 if(status!=null) {
				 cs.setBoolean(3, status);
			 } else {
				 cs.setNull(3, Types.BOOLEAN);
			 }
			
			var rs = cs.executeQuery();
			while(rs.next()) {
				var contract = new Contract();
				contract.setId(rs.getInt("id"));
				contract.setApartNum(rs.getInt("rooms"));
				contract.setOwnerName(rs.getString("ownerName"));
				contract.setImgContracs(rs.getString("pic"));
				contract.setStatus(rs.getBoolean("status"));
				contract.setFormDate(rs.getDate("fromDate"));
				contract.setToDate(rs.getDate("toDate"));
				contract.setRoomates(rs.getString("all_user_id"));
				list.add(contract);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// select owner contract and show apart
	public List<Contract> selecOwnerApart() {
		List<Contract> list = new ArrayList<>();
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call selOwner()}")
		)
		{
			var rs = cs.executeQuery();
			while(rs.next()) {
				var contract = new Contract();
				contract.setApartNum(rs.getInt("rooms"));
				contract.setOwnerName(rs.getString("ownerName"));
				contract.setRoomates(rs.getString("total_roommates"));
				list.add(contract);
				System.out.println("list coasda" + list);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
