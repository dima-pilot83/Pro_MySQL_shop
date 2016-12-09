package ru.mail.dimapilot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

	protected static void makeOrder(String base, String user, String password, String name, String good) {
		try {

			Connection conn = DriverManager.getConnection(base, user, password);

			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO Orders (client,good)SELECT Clients.idclients,Goods.idgoods FROM Clients,Goods WHERE client_name = ? AND goods_name=?");
			stmt.setString(1, name);
			stmt.setString(2, good);

			stmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("Got error: " + ex.getMessage());
		}
	}

	protected static void makeOrderFull(String base, String user, String password) {
		try {

			// connect to base
			Connection conn = DriverManager.getConnection(base, user, password);

			// prepare and make query
			PreparedStatement stmt = conn.prepareStatement("SELECT client_name,client_city,goods_name,goods_price FROM Clients JOIN Orders ON idclients=client JOIN Goods ON idgoods=good ");
			ResultSet rs = stmt.executeQuery();

			// output data
			int columns = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				for (int i = 1; i <= columns; ++i) {
					System.out.print(rs.getString(i) + "\t");
				}
				System.out.println("");
			}

		} catch (Exception ex) {
			System.out.println("Got error: " + ex.getMessage());
		}
	}

}
