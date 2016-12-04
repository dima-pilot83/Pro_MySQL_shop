package ru.mail.dimapilot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Action {

	protected static void addClient(String base, String user, String password, String name, String city) {
		try {
			// connect to base
			Connection conn = DriverManager.getConnection(base, user, password);

			// prepare and make query
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Clients (client_name,client_city)VALUES(?,?)");
			stmt.setString(1, name);
			stmt.setString(2, city);
			stmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("Got error: " + ex.getMessage());
		}

	}

	protected static void addGoods(String base, String user, String password, String name, double price) {

		try {

			Connection conn = DriverManager.getConnection(base, user, password);

			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Goods (goods_name,goods_price)VALUES(?,?)");
			stmt.setString(1, name);
			stmt.setDouble(2, price);
			stmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("Got error: " + ex.getMessage());
		}
	}

	protected static void makeOrder(String base, String user, String password, String a, String b) {
		try {

			Connection conn = DriverManager.getConnection(base, user, password);

			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO Orders (client,good)SELECT Clients.idclients,Goods.idgoods FROM Clients,Goods WHERE client_name = ? AND goods_name=?");
			stmt.setString(1, a);
			stmt.setString(2, b);

			stmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("Got error: " + ex.getMessage());
		}
	}
}
