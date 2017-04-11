package com.lexicon.jdbc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CitySearchApp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable CityTable;
	private JTextField CityToSearch;
	private WorldDAO daoOBJ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CitySearchApp frame = new CitySearchApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CitySearchApp() {
		setTitle("City Search App");
		try{
			daoOBJ = new WorldDAO();
			
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
		}
		setTitle("City Search App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);	
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblFirstFewLetters = new JLabel("First few letter/s of city:");
		lblFirstFewLetters.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblFirstFewLetters);
		
		CityToSearch = new JTextField();
		panel.add(CityToSearch);
		CityToSearch.setColumns(10);
		
		JButton SearchButton = new JButton("Search");
		SearchButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				try{
				String cityNameToSearch = CityToSearch.getText();
				List<City> result = null;
				if(!cityNameToSearch.equals(null) && cityNameToSearch.trim().length() > 0){
					result = daoOBJ.searchAllCities(cityNameToSearch);
					
				}
				else
					result = daoOBJ.getAllCities();
			
				// create the model and update the "table"
				CityTableModel model = new CityTableModel(result);
				CityTable.setModel(model);
				
				for(City temp : result){
					System.out.println(temp);
				}
					
				System.out.println(result);
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(CitySearchApp.this, "Error: "+ e);
				}
						
			}
		});
		panel.add(SearchButton);
		
		JScrollPane scrollPane1 = new JScrollPane();
		contentPane.add(scrollPane1, BorderLayout.CENTER);
		
		CityTable = new JTable();
		scrollPane1.setViewportView(CityTable);
	}

}
