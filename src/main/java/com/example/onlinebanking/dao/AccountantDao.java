package com.example.onlinebanking.dao;

import com.example.onlinebanking.entity.Accountant;
import com.example.onlinebanking.exception.AccountantException;

public interface AccountantDao {

	public Accountant LoginAccountant(String accountantUsername,String accountantPassword) throws AccountantException;
}
