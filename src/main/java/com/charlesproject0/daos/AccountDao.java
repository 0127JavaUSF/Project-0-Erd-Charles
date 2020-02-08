package com.charlesproject0.daos;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.charlesproject0.models.Account;
import com.charlesproject0.utils.ConnectionUtil;



/**
 * DAO - Data Access Object
 * DAOs are a particular kind of abstraction that define an object whose responsibility
 * is the retrieval and management of data persistence. 
 *
 */
public class AccountDao {
	
	private Account extractAccount(ResultSet result) throws SQLException {
		int id = result.getInt("id");
		String accountName = result.getString("account_name");
		String password = result.getString("password");
		return new Account(id, accountName, password);
	}
	
	public Account getAccount(int id) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			Statement statement = connection.createStatement();
			String sql = "SELECT id, build_name, apartment_number FROM accounts " +
					"WHERE id = " + id;
			ResultSet result = statement.executeQuery(sql);
			
			if(result.next()) {
				String buildName = result.getString("build_name");
				String apartmentNumber = result.getString("apartment_number");
				return new Account(id, buildName, apartmentNumber);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}

	public Account createAccount(Account account) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO accounts (account_name, password) " +
					" VALUES (?, ?) RETURNING *";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, account.getAccountName());
			statement.setString(2, account.getPassword());
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return extractAccount(result);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
