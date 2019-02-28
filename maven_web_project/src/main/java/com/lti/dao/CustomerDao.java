package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.lti.model.Customer;

//Data Access Object
public class CustomerDao {
	public void addCustomer(Customer customer) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			String sql = "insert into customer_o values (?,?,?)";
			
			Properties dbProps=new Properties();
			dbProps.load(this.getClass().getClassLoader().getResourceAsStream("prod-db.properties"));
			Class.forName(dbProps.getProperty("driverClassName"));
			System.out.println("driver loaded");
			conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps.getProperty("username"), dbProps.getProperty("password"));
			System.out.println("connection established");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, customer.getId());
			stmt.setString(2, customer.getName());
			stmt.setString(3, customer.getEmail());
			stmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}  finally {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (Exception e) {

			}
		}

	}
}
