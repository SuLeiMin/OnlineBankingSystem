package com.example.onlinebanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.onlinebanking.databaseconnection.DatabaseConnection;
import com.example.onlinebanking.entity.Accountant;
import com.example.onlinebanking.exception.AccountantException;

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

}
