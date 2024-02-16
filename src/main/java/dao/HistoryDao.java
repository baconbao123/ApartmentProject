package dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import common.ConnectDB;
import entity.History;

public class HistoryDao {
	
	public List<History> selHistory(Integer pageNumber,Integer rowOfPage, String module, java.util.Date from, java.util.Date to,String name, Boolean filter) {
		var list = new ArrayList<History>();
		
		try (
				var con  = ConnectDB.getConnect();
				var cs = con.prepareStatement("{call paginateHistory(?,?,?,?,?,?)}");)
		{
			if(!filter) {
				cs.setInt(1, pageNumber);
				cs.setInt(2, rowOfPage);
				cs.setString(3, module);
				cs.setNull(4, java.sql.Types.DATE);
				cs.setNull(5, java.sql.Types.DATE);
				if(name == null || name.isEmpty()) {
					cs.setNull(6, java.sql.Types.VARCHAR);
				}
				else {
					cs.setString(6, name);
				}
			}
			else  {
				cs.setInt(1, pageNumber);
				cs.setInt(2, rowOfPage);
				cs.setString(3, module);
				
				if(to == null) {
					cs.setNull(4, java.sql.Types.DATE);
				}
				else {
					var fromTime  =  new java.sql.Date(to.getTime());
					cs.setDate(4, fromTime);
				}
				
				if(from == null) {
					cs.setNull(5, java.sql.Types.DATE);
				}
				else {
					var fromTime  =  new java.sql.Date(from.getTime());
					cs.setDate(5, fromTime);
				}
				
				if(name == null || name.isEmpty()) {
					cs.setString(6, null);
				}
				else {
					cs.setString(6, name);
				}
			}
			var rs = cs.executeQuery();
			while(rs.next()) {
				var history = new History();
				history.setModule(rs.getString("module"));
				history.setOperation(rs.getString("operation"));
				history.setTime(rs.getDate("time").toLocalDate());
				history.setUpdated_by(rs.getString("name"));
				list.add(history);
			}
		} catch (Exception e) {
			
		}
		System.out.println(list.toString());
		return list;
	}
	
	public Integer total(String module, Boolean filter, String name, java.util.Date from, java.util.Date to) {
		var total = 0;
		try (
				var con  = ConnectDB.getConnect();
				var cs = con.prepareStatement("{call totalHistory(?,?,?,?)}");
				) {
			if(!filter) {
				cs.setString(1, module);
				cs.setNull(2, java.sql.Types.DATE);
				cs.setNull(3, java.sql.Types.DATE);
				if(name == null || name.isEmpty()) {
					cs.setString(4, null);
				}
				else {
					cs.setString(4, name);
				}
			}
			else  {
			cs.setString(1, module);
				
				if(to == null) {
					cs.setNull(2, java.sql.Types.DATE);
				}
				else {
					var fromTime  =  new java.sql.Date(to.getTime());
					cs.setDate(2, fromTime);
				}
				
				if(from == null) {
					cs.setNull(3, java.sql.Types.DATE);
				}
				else {
					var fromTime  =  new java.sql.Date(from.getTime());
					cs.setDate(3, fromTime);
				}
				
				if(name == null || name.isEmpty()) {
					cs.setString(4, null);
				}
				else {
					cs.setString(4, name);
				}
			}
			var rs = cs.executeQuery();
			while(rs.next()){
				total = rs.getInt("total");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public void insertHistory(String module, String operation, Integer id) {
		try(
				var con  = ConnectDB.getConnect();
				var cs = con.prepareStatement("{call insertHistory(?,?,?)}");
				)
		{
			cs.setString(1, module);
			cs.setInt(2, id);
			cs.setString(3, operation);
			cs.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
