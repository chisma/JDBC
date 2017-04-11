package com.lexicon.jdbc;

import java.util.List;

import javax.swing.table.AbstractTableModel;

class CityTableModel extends AbstractTableModel {

	private static final int Id_COL = 0;
	private static final int NAME_COL = 1;
	private static final int COUNTRYCODE_COL = 2;
	private static final int DISTRICT_COL = 3;
	private static final int POPULATION_COL = 3;
	
	private String[] columnNames = { "Id", "Name", "countryCode",
			"District","Population" };
	private List<City> cities;

	public CityTableModel(List<City> thecities) {
		cities = thecities;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return cities.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		City tempCity = cities.get(row);

		switch (col) {
		case Id_COL:
			return tempCity.getiD();
		case NAME_COL:
			return tempCity.getName();
		case COUNTRYCODE_COL:
			return tempCity.getCountryCode();
		case DISTRICT_COL:
			return tempCity.getDistrict();
		default:
			return tempCity.getPopulation();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
