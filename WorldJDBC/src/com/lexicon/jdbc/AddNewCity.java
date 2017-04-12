package com.lexicon.jdbc;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddNewCity extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField CityName;
	private JTextField Code;
	private JTextField District;
	private JTextField population;
	private WorldDAO daoObject;
	private CitySearchApp parentWindow;
	private CityTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddNewCity dialog = new AddNewCity();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddNewCity(CitySearchApp theWindow,WorldDAO theObj){
		
		daoObject = theObj;
		theWindow = parentWindow;
		
		
		
	}
	
	
	public AddNewCity() {
		setTitle("Add a new city");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblCityName = new JLabel("City Name");
			contentPanel.add(lblCityName, "2, 2, right, default");
		}
		{
			CityName = new JTextField();
			contentPanel.add(CityName, "4, 2, fill, default");
			CityName.setColumns(10);
		}
		{
			JLabel lblCountryCode = new JLabel("Country Code");
			contentPanel.add(lblCountryCode, "2, 4, right, default");
		}
		{
			Code = new JTextField();
			contentPanel.add(Code, "4, 4, fill, default");
			Code.setColumns(10);
		}
		{
			JLabel lblDistrict = new JLabel("District");
			contentPanel.add(lblDistrict, "2, 6, right, default");
		}
		{
			District = new JTextField();
			contentPanel.add(District, "4, 6, fill, default");
			District.setColumns(10);
		}
		{
			JLabel lblPopulation = new JLabel("Population");
			contentPanel.add(lblPopulation, "2, 8, right, default");
		}
		{
			population = new JTextField();
			contentPanel.add(population, "4, 8, fill, default");
			population.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
																	
						this.saveNewCity();
						
					}

					private void saveNewCity(){
				
						try{
						int iD = daoObject.CityCount() + 1;
						String theName = CityName.getText();
						String theCode = Code.getText();
						String theDistrict = District.getText();
						Long thePop = Long.parseLong(population.getText());
						
						City cityToAdd = new City(iD,theName,theCode,theDistrict,thePop);
						daoObject.addANewCity(cityToAdd);
						setVisible(false);
						dispose();
						
						//Refresh gui list
						//TODO : I don't understand
						parentWindow.refreshCityView();
						
						}
						catch(Exception excep){
							System.out.println("Not able to save new entry");
						}
														
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
