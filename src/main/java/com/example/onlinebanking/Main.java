package com.example.onlinebanking;

import java.util.Scanner;

import com.example.onlinebanking.dao.AccountantDao;
import com.example.onlinebanking.dao.AccountantDaoImplementation;
import com.example.onlinebanking.entity.Accountant;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean f = true;
        while(f) {
        	System.out.println("---------------WELCOME  TO ONLINE BANKING SYSTEM-----------");
        	System.out.println("-----------------------------------------------------------");
        	System.out.println("1. ADMIN LOGIN PORTAL");
        	System.out.println("2. Customer");
        	
        	System.out.println("Choose your Option ");
        	int choice = sc.nextInt();
        	
        	switch(choice) {
        	
        	case 1 :
        		System.out.println("Admin Login Credentials ---------------------Accountant");
        		System.out.println("Enter Username : ");
        		String username = sc.next();
        		System.out.println("Enter Password :");
        		String pass = sc.next();
        		
        		AccountantDao ad= new AccountantDaoImplementation();
        		
        		try {
        			Accountant a = ad.LoginAccountant(username, pass);
        			if(a == null) {
        				System.out.println("wrong credentials");
        				break;
        			}
        			System.out.println("login successfully!!!");
        			System.out.println("Welcome to : " + a.getAccountantUsername() + "as admin of Online Banking System");
        			
        		}catch(Exception e) {
        			
        		}
        	}
        }
    }
}