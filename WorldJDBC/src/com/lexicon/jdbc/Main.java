package com.lexicon.jdbc;

public class Main {

	public static void main(String[] args) throws Exception{
		
		WorldDAO worldDAO = new WorldDAO();
		System.out.println(worldDAO.searchAllCities("B"));
		System.out.println(worldDAO.getAllCities());

	}

}
