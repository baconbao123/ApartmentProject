package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.ConnectDB;
import entity.Apartment;
import entity.Contract;
import formAdmin.Bill;
import model.Fees;

public class ApartmentDao {
	private String module = "Management Apartment";
	
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
	
	public void insertApart(Apartment apart) {
		
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call addApart(?, ?, ?, ?, ?, ?, ?, ?)}");
		)
		{
			cs.setInt(1, apart.getId());
			cs.setInt(2, apart.getRoomNumber());
			cs.setString(3, apart.getConvenient());
			cs.setInt(4, apart.getPeople());
			cs.setInt(5, apart.getPeopleMaximun());
			cs.setString(6, apart.getUtilities());
			cs.setString(7, apart.getType());
			cs.setInt(8, apart.getFloor());
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
				//apart.setStatus(rs.getBoolean("status"));
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
	
	public List<Apartment> selectApartNum() {
		List<Apartment> list = new ArrayList<>();
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call selApartInfor()}");
		)
		{
			var rs = cs.executeQuery();
			while (rs.next()) {
				var apart = new Apartment();
				apart.setId(rs.getInt("id"));
				apart.setRoomNumber(rs.getInt("rooms"));		
				
				list.add(apart);
//				System.out.println("list ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list; 
	}
	
	// select money all
	public List<Fees> selectMoneyByAll() {
		List<Fees> list = new ArrayList<>();
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call selectAllMoney()}");
		)
		{
			var rs = cs.executeQuery();
			while (rs.next()) {
				var fee = new Fees();
				fee.setTotal(rs.getFloat("total_money"));				
				fee.setRoom(rs.getInt("rooms"));
				fee.setIdFeeAll(rs.getString("id_fee_all"));
				list.add(fee);
//				System.out.println("list fee"+list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	// update id fee all
	public void updateFeeAll(Fees fee) {
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call upFeeStatus(?, ?)}");
		)
		{
			cs.setInt(1,Integer.parseInt(fee.getIdFeeAll()));
			cs.setBoolean(2, fee.getStatus());
			cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateFeeByMoth(Fees fee) {
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call upFeeStatus(?, ?)}");
		)
		{
			cs.setInt(1, fee.getId());
			cs.setBoolean(2, fee.getStatus());
			cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// select money by month
	public List<Fees> selectMonthByMoth() {
		List<Fees> list = new ArrayList<>();
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call selectMoneyByMonth()}");
		)
		{
			var rs = cs.executeQuery();
			while(rs.next()) {
				var fee = new Fees();
				fee.setRoom(rs.getInt("rooms"));
				fee.setTotal(rs.getFloat("total_money"));
				fee.setTime(rs.getDate("time").toLocalDate());
				fee.setId(rs.getInt("id"));
				list.add(fee);
				System.out.println("list month: " + list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Apartment> selectApartRoomate() {
		List<Apartment> list = new ArrayList<>();
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call countPeople()}");
		)
		{
			var rs = cs.executeQuery();
			while (rs.next()) {
				var apart = new Apartment();
				apart.setRoomNumber(rs.getInt("rooms"));
				apart.setPeople(rs.getInt("total_roommates"));
				list.add(apart);
//				System.out.println("list"+list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return list;
	}
	
	public List<Contract> selInfoRoomate() {
		List<Contract> list = new ArrayList<>();
		try 
		(
			var con = ConnectDB.getConnect();
			var cs = con.prepareCall("{call selInfoPeopleInRoom()}");
		)
		{
			var rs = cs.executeQuery();
			while (rs.next()) {
				var contract = new Contract();
				contract.setId(rs.getInt("id"));
				contract.setApartNum(rs.getInt("rooms"));
				contract.setRoomates(rs.getString("all_user_id"));
				contract.setImgContracs(rs.getString("pic"));
				contract.setStatus(rs.getBoolean("status"));
				contract.setFormDate(rs.getDate("fromDate"));
				contract.setToDate(rs.getDate("toDate"));
				list.add(contract);
//				System.out.println("list hahah"+list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
}
