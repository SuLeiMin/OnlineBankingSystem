package com.example.onlinebanking;

import java.util.Scanner;

import com.example.onlinebanking.dao.AccountantDao;
import com.example.onlinebanking.dao.AccountantDaoImplementation;
import com.example.onlinebanking.entity.Accountant;
import com.example.onlinebanking.exception.CustomerException;

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
        			
        		
        			boolean y = true;
        			while(y) {
        				System.out.println("------------------------------");
        				System.out.println("1. Add New Customer Account");
        				System.out.println("2. Update Customer Address");
        				System.out.println("3. Remove/Delete the account by Account Number");
        				
        				int x = sc.nextInt();
        				if(x == 1) {
        					System.out.println("----------New Account -------------");
        					System.out.println("Enter CustomerName : ");
        					String a1 =sc.next();
        					
        					System.out.println("Enter Account Opening Balance");
        					int a2 = sc.nextInt();
        					
        					System.out.println("Enter CustomerMail : ");
        					String a3 =sc.next();
        					System.out.println("Enter CustomerPassword : ");
        					String a4 =sc.next();
        					System.out.println("Enter CustomerMobile Number : ");
        					String a5 =sc.next();
        					System.out.println("Enter Customer Address : ");
        					String a6 =sc.next();
        					
        					int s1 = -1;
        					try {
        						s1 = ad.addCustomer(a1, a3, a4, a5, a6);
        						
        						try {
        							//s1=1;
        							ad.addAccount(a2, s1);
        						}catch(CustomerException e) {
        							e.printStackTrace();
        						}
        						
        					}catch(CustomerException e) {
        						System.out.println(e.getMessage());
        					}
        					System.out.println("Customer added successfully");
        					System.out.println("----------------------");
        				}
        				
        				if( x == 2) {
        					System.out.println("Update Customer Address.........");
        					System.out.println("Enter Customer Account Number......");
        					int u = sc.nextInt();
        					System.out.println("Enter New address.....");
        					String u2 = sc.next(); 
        					
        					try {
        						String mes = ad.updateCustomer(u, u2);
        					}catch(CustomerException e) {
        						e.printStackTrace();
        					}
        				}
        				
        				if( x == 3) {
        					System.out.println("----------Remove Account -----------");
        					System.out.println("Enter Account Number : ");
        					int ac = sc.nextInt();
        					String s = null;
        					try {
        						s = ad.deleteCustomer(ac);
        					}catch(CustomerException e){
        						e.printStackTrace();
        					}
        					
        					if( s!=null) {
        						System.out.println(s);
        					}
        				}
        				
        				
        			}
        			
        		}catch(Exception e) {
        			
        		}
        	}
        }
    }
}