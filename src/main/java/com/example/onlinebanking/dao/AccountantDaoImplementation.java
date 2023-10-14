package com.example.onlinebanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.onlinebanking.databaseconnection.DatabaseConnection;
import com.example.onlinebanking.entity.Accountant;
import com.example.onlinebanking.exception.AccountantException;
import com.example.onlinebanking.exception.CustomerException;

public class AccountantDaoImplementation implements AccountantDao{

	@Override
	public Accountant LoginAccountant(String accountantUsername, String accountantPassword) throws AccountantException {
		
		Accountant acc = null;
		try(Connection conn = DatabaseConnection.provideConnection()){
			PreparedStatement ps = conn.prepareStatement("select * from accountant where accountantUsername = ? and "
					+ "accountantPassword = ?");
			
			ps.setString(1,accountantUsername); 
			ps.setString(2, accountantPassword);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String n = rs.getString("accountantUsername");
				String e = rs.getString("accountantEmail");
				String p = rs.getString("accountantPassword");
				
				acc = new Accountant(n,e,p);
			}
			
		}catch(SQLException e) {
			throw new AccountantException("Invalid UserName and Password");
		}
		
		return acc;
	}

	@Override
	public int addCustomer(String customerName, String customerMail, String customerPassword, String customerMobile,
			String customerAddress) throws CustomerException {
		
		int cid = -1;
		try(Connection con = DatabaseConnection.provideConnection() ){
			PreparedStatement ps = con.prepareStatement("insert into customerinformation(customerName,customerMail,customerPassword,"
					+ "customerMobile ,customerAddress) values (?,?,?,?,?)");
			
			ps.setString(1, customerName);
			ps.setString(2, customerMail);
			ps.setString(3, customerPassword);
			ps.setString(4, customerMobile);
			ps.setString(5, customerAddress);
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				PreparedStatement ps2 = con.prepareStatement("select cid from customerinformation where customerMail = ? and customerMobile = ?");
				ps2.setString(1,customerMail);
				ps2.setString(2, customerMobile);
				
				ResultSet rs = ps2.executeQuery();
				
				if(rs.next()) {
					cid=1;
					cid = rs.getInt(cid);
					
					
				}else {
					System.out.println("Inserted Data is Incorrect.Please try again");
					
				}
			}
			
		}catch(Exception e) {
			System.out.println("SQL Query Related Error");
		}
		
		return cid;
	}

	@Override
	public String addAccount(int customerBalance, int cid) throws CustomerException {
		
		String message = null;
		try (Connection conn = DatabaseConnection.provideConnection()){
			PreparedStatement ps = conn.prepareStatement("insert into account(customerBalance,cid) values (?,?)");
			
			ps.setInt(1, customerBalance);
			ps.setInt(2, cid);
			
			int x = ps.executeUpdate();	
			if(x>0) {
				System.out.println("Account Added Successfully");
			}else {
				System.out.println("Inserted Data not added successfully");
			}
			
			
		}catch(SQLException e) {
			System.out.println("SQL Related Exception");
		}
		
		return message;
	}

	@Override
	public String updateCustomer(int customerAccountNumber, String customerAddress) throws CustomerException {
		
		String message =null;
		
		try (Connection con = DatabaseConnection.provideConnection()){
			PreparedStatement ps = con.prepareStatement("update customerinformation i inner join account a on i.cid = a.cid and "
					+ "a.customerAccountNumber = ? set i.customerAddress = ? ");
			ps.setInt(1, customerAccountNumber);
			ps.setString(2, customerAddress);
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				System.out.println("Address Updated Successfully .....");
			}else {
				System.out.println("Customer Updatation is not successfully......");
				System.out.println("------------------------------------------------");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			message = e.getMessage();
		}
		
		
		return message;
	}

	@Override
	public String deleteCustomer(int customerAccountNumber) throws CustomerException {
		String message = null;
		try(Connection con = DatabaseConnection.provideConnection()){
			PreparedStatement ps = con.prepareStatement("delete i from customerinformation i inner join account a on "
					+ "i.cid = a.cid where a.customerAccountNumber = ?");
			
			ps.setInt(1, customerAccountNumber);
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				System.out.println("Account deleted successfully.......");
			}else {
				System.out.println("Delection failed.........Account not found");
				System.out.println("----------------------------------------------");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			message = e.getMessage();
		}
		
		return message;
	}

}
