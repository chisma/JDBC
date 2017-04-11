package com.lexicon.jdbc;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WorldDAO {

	private Connection myCon;
	private static Properties prop = new Properties();
	private String user;
	private String password;
	private String dbURL;
	
	
	public WorldDAO() throws Exception{
		
		prop.load(new FileInputStream("world.properties"));
		user = prop.getProperty("user");
		password = prop.getProperty("password");
		dbURL = prop.getProperty("dbURL");
		
		myCon = DriverManager.getConnection(dbURL, user, password);
		System.out.println("The connection is successful to the World DB at: "+ dbURL);
				
		
	}
	
		
	public List<City> getAllCities() throws Exception {
		
		List<City> cityList = new ArrayList<>();
		
		Statement myStat = null;
		ResultSet myRes = null;
		try{
		
		myStat = myCon.createStatement();
		myRes = myStat.executeQuery("SELECT * FROM city");
		
		while(myRes.next()){
			City myCity = convertRowToCityObject(myRes);
			cityList.add(myCity);
		}
		}
		finally {
			close(myStat,myRes);
		}
		return cityList;
	}

	public List<City> searchAllCities(String cityName) throws Exception {
		
		List<City> cityList = new ArrayList<>();
		cityName+= "%";
		PreparedStatement myStat = null;
		ResultSet myRes = null;
		try{
		
		myStat = myCon.prepareStatement("SELECT * FROM city WHERE Name LIKE ?");
		myStat.setString(1, cityName);
		myRes = myStat.executeQuery();
		
		while(myRes.next()){
			City myCity = convertRowToCityObject(myRes);
			cityList.add(myCity);
		}
		}
		finally {
			close(myStat,myRes);
		}
		return cityList;
	}
	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}
	
		
	private void close(Statement myStat, ResultSet myRes) throws SQLException  {
		{
			close(null, myStat, myRes);		
		}

		
	}

	private City convertRowToCityObject(ResultSet myRes) throws Exception {
		
		
		int iD = myRes.getInt(1);
		String Name = myRes.getString(2);
		String countryCode = myRes.getString(3);
		String district = myRes.getString(4);
		long population =myRes.getLong(5);
		
		City newCity = new City(iD,Name,countryCode,district,population);
		return newCity;
		
	}



}
