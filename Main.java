package ru.mail.dimapilot;

import java.sql.Connection;

public class Main {

	static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/shop";
	static final String DB_USER = "root";
	static final String DB_PASSWORD = "1111";


	static Connection conn;
	
	
	public static void main(String[] args) {
		
		try {
			
		Action.addClient(DB_CONNECTION, DB_USER, DB_PASSWORD, "Andrey", "Kharkov");
		Action.addGoods(DB_CONNECTION, DB_USER, DB_PASSWORD, "Milk", 15.25);
		
		Action.makeOrder(DB_CONNECTION, DB_USER, DB_PASSWORD,"Andrey","Milk");
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
