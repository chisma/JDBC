package com.lexicon.jdbc;
import java.util.ArrayList;
import java.util.List;

public class City {
	private int iD;
	private String Name;
	private String CountryCode;
	private String District;
	private long population;
	
	
	public int getiD() {
		return iD;
	}


	public String getName() {
		return Name;
	}


	public String getCountryCode() {
		return CountryCode;
	}


	public String getDistrict() {
		return District;
	}


	public long getPopulation() {
		return population;
	}


	public City(int iD, String Name, String CountrCode, String District, long population){
		
		this.iD = iD;
		this.Name = Name;
		this.CountryCode = CountrCode;
		this.population = population;
		this.District = District;		
	}


	@Override
	public String toString() {
		return "CityiD= " + this.iD + ";"+ "Name= " + this.Name + ";"+ "CountryCode=" + this.CountryCode +";" + " District=" + this.District
				+ ";" + "population= " + this.population + "\n";
	}
	
}
