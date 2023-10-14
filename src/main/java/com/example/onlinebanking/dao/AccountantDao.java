package com.example.onlinebanking.dao;

import com.example.onlinebanking.entity.Accountant;
import com.example.onlinebanking.exception.AccountantException;
import com.example.onlinebanking.exception.CustomerException;

public interface AccountantDao {

	public Accountant LoginAccountant(String accountantUsername,String accountantPassword) throws AccountantException;
	
	public int addCustomer(String customerName,String customerMail,String customerPassword,
			String customerMobile,String customerAddress) throws CustomerException;
	
	public String addAccount(int customerBalance,int cid) throws CustomerException;
	
	public String updateCustomer(int customerAccountNumber,String customerAddress) throws CustomerException;
	
	public String deleteCustomer(int customerAccountNumber) throws CustomerException;
 }
