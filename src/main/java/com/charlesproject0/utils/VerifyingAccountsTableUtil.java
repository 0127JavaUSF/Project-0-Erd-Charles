package com.charlesproject0.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.charlesproject0.utils.ConnectionUtil;

public class VerifyingAccountsTableUtil {
	public static ArrayList<String> returnVisibleAccounts(String ... strings ) {//verifies that the account exists in db
		ArrayList<String> results = new ArrayList<String>();
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql = "Select account_name from accounts";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			ArrayList<String> dbrsList = new ArrayList<String>();
			while (rs.next()) {
				dbrsList.add(rs.getString("account_name"));
				
				
			}
			for (String entry:strings){
				if (dbrsList.contains(entry)) {
					results.add(entry);
				}
				
				
			}
			System.out.println("placeholder");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}
}
