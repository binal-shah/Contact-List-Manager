/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contact_app_manager;
/**
 *
 * @author Binal Shah
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;

import contact_app_manager.ContactListForm;
public class ContactAdd extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField fNametextField;
	private ContactConnectDatabase ContactConnectDatabase;
	private ContactAppManager ContactAppManager;
	private JTextField mnameTextField;
	private JTextField lnameTextField;
	private JTextField homeZipcodeTextField;
	private JTextField homeCitytextField;
	private JTextField dformatTextField;
	private JTextField homeStateTextField;
	private JTextField bdayTextField;
	private JTextField homephoneTextField;
	private JTextField homeareaTextField;
	
	
	
	private ContactListForm prevContact=null;
	private boolean updateMode=false;
	private boolean viewMode=false;
	private JTextField homeStreettextField;
	private JLabel lblWorkStreetCity;
	private JLabel lblStateZip_1;
	private JLabel lblWorkPhone;
	private JTextField workareatextField;
	private JTextField workphonetextField;
	private JLabel lblCellPhoneArea;
	private JTextField workStreetTextField;
	private JTextField workCitytextField;
	private JTextField workStatetextField;
	private JTextField cellareaTextField;
	private JTextField workZipcodetextField;
	private JTextField cellphoneTextField;
	
	
	
	public ContactAdd(ContactAppManager theContactAppManager,ContactConnectDatabase theContactConnectDatabase,ContactListForm theContact,boolean theUpdateMode,boolean theViewMode) throws Exception
	{
		this();
		ContactConnectDatabase = theContactConnectDatabase;
		ContactAppManager = theContactAppManager;
		prevContact=theContact;
		updateMode=theUpdateMode;
		viewMode=theViewMode;
		if(updateMode) {
			setTitle("Modify Contact");
			int id=prevContact.getID();
			ContactListForm temp= theContactConnectDatabase.getSpecificContact(id);
			populateGui(temp);
			
		}
		if(viewMode) {
			setTitle("View Contact");
			int id=prevContact.getID();
			ContactListForm temp= theContactConnectDatabase.getSpecificContact(id);
			populateGui(temp);
			
		}
	}
	private void populateGui(ContactListForm temp) {

		fNametextField.setText(temp.getFirstName());
		mnameTextField.setText(temp.getMiddleName());
		lnameTextField.setText(temp.getLastName());
		homeStreettextField.setText(temp.getHomeStreet());
		homeCitytextField.setText(temp.getHomeCity());
		homeStateTextField.setText(temp.getHomeState());
		homeZipcodeTextField.setText(temp.getHomeZIP());
		workStreetTextField.setText(temp.getWorkStreet());
		workCitytextField.setText(temp.getWorkCity());
		workStatetextField.setText(temp.getWorkState());
		workZipcodetextField.setText(temp.getWorkZIP());
		cellareaTextField.setText(temp.getCellAreaCode());
		cellphoneTextField.setText(temp.getCellPhone());
		homeareaTextField.setText(temp.getHomeAreaCode());
		homephoneTextField.setText(temp.getHomePhone());
		workareatextField.setText(temp.getWorkAreaCode());
		workphonetextField.setText(temp.getWorkPhone());
		dformatTextField.setText(temp.getFormat());
		bdayTextField.setText(temp.getBD());
	}
	

	
	public ContactAdd(ContactAppManager theContactAppManager,ContactConnectDatabase theContactConnectDatabase)
	{
		this();
		ContactConnectDatabase = theContactConnectDatabase;
		ContactAppManager = theContactAppManager;
	}

	/**
	 * Create the dialog.
	 */
	public ContactAdd() {
		setTitle("Add New Contact");
		setBounds(100, 100, 565, 497);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblFirstName = new JLabel("First Name");
			GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
			gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
			gbc_lblFirstName.gridx = 0;
			gbc_lblFirstName.gridy = 0;
			contentPanel.add(lblFirstName, gbc_lblFirstName);
		}
		{
			fNametextField = new JTextField();
			GridBagConstraints gbc_fNametextField = new GridBagConstraints();
			gbc_fNametextField.gridwidth = 3;
			gbc_fNametextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_fNametextField.insets = new Insets(0, 0, 5, 5);
			gbc_fNametextField.gridx = 1;
			gbc_fNametextField.gridy = 0;
			contentPanel.add(fNametextField, gbc_fNametextField);
			fNametextField.setColumns(10);
		}
		{
			JLabel lblMiddleName = new JLabel("Middle Name");
			GridBagConstraints gbc_lblMiddleName = new GridBagConstraints();
			gbc_lblMiddleName.insets = new Insets(0, 0, 5, 5);
			gbc_lblMiddleName.gridx = 0;
			gbc_lblMiddleName.gridy = 1;
			contentPanel.add(lblMiddleName, gbc_lblMiddleName);
		}
		{
			mnameTextField = new JTextField();
			GridBagConstraints gbc_mnameTextField = new GridBagConstraints();
			gbc_mnameTextField.gridwidth = 3;
			gbc_mnameTextField.insets = new Insets(0, 0, 5, 5);
			gbc_mnameTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_mnameTextField.gridx = 1;
			gbc_mnameTextField.gridy = 1;
			contentPanel.add(mnameTextField, gbc_mnameTextField);
			mnameTextField.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("Last Name");
			GridBagConstraints gbc_lblLastName = new GridBagConstraints();
			gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
			gbc_lblLastName.gridx = 0;
			gbc_lblLastName.gridy = 2;
			contentPanel.add(lblLastName, gbc_lblLastName);
		}
		{
			lnameTextField = new JTextField();
			GridBagConstraints gbc_lnameTextField = new GridBagConstraints();
			gbc_lnameTextField.gridwidth = 3;
			gbc_lnameTextField.insets = new Insets(0, 0, 5, 5);
			gbc_lnameTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_lnameTextField.gridx = 1;
			gbc_lnameTextField.gridy = 2;
			contentPanel.add(lnameTextField, gbc_lnameTextField);
			lnameTextField.setColumns(10);
		}
		{
			JLabel lblStreetCity = new JLabel("Home Address: Street, City");
			GridBagConstraints gbc_lblStreetCity = new GridBagConstraints();
			gbc_lblStreetCity.insets = new Insets(0, 0, 5, 5);
			gbc_lblStreetCity.gridx = 0;
			gbc_lblStreetCity.gridy = 3;
			contentPanel.add(lblStreetCity, gbc_lblStreetCity);
		}
		{
			homeStreettextField = new JTextField();
			GridBagConstraints gbc_homeStreettextField = new GridBagConstraints();
			gbc_homeStreettextField.gridwidth = 3;
			gbc_homeStreettextField.insets = new Insets(0, 0, 5, 5);
			gbc_homeStreettextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_homeStreettextField.gridx = 1;
			gbc_homeStreettextField.gridy = 3;
			contentPanel.add(homeStreettextField, gbc_homeStreettextField);
			homeStreettextField.setColumns(10);
		}
		{
			homeCitytextField = new JTextField();
			GridBagConstraints gbc_homeCitytextField = new GridBagConstraints();
			gbc_homeCitytextField.gridwidth = 2;
			gbc_homeCitytextField.insets = new Insets(0, 0, 5, 0);
			gbc_homeCitytextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_homeCitytextField.gridx = 4;
			gbc_homeCitytextField.gridy = 3;
			contentPanel.add(homeCitytextField, gbc_homeCitytextField);
			homeCitytextField.setColumns(10);
		}
		{
			JLabel lblStateZip = new JLabel("Home Address: State, Zip");
			GridBagConstraints gbc_lblStateZip = new GridBagConstraints();
			gbc_lblStateZip.insets = new Insets(0, 0, 5, 5);
			gbc_lblStateZip.gridx = 0;
			gbc_lblStateZip.gridy = 4;
			contentPanel.add(lblStateZip, gbc_lblStateZip);
		}
		{
			homeStateTextField = new JTextField();
			GridBagConstraints gbc_homeStateTextField = new GridBagConstraints();
			gbc_homeStateTextField.gridwidth = 2;
			gbc_homeStateTextField.insets = new Insets(0, 0, 5, 5);
			gbc_homeStateTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_homeStateTextField.gridx = 1;
			gbc_homeStateTextField.gridy = 4;
			contentPanel.add(homeStateTextField, gbc_homeStateTextField);
			homeStateTextField.setColumns(10);
		}
		{
			homeZipcodeTextField = new JTextField();
			GridBagConstraints gbc_homeZipcodeTextField = new GridBagConstraints();
			gbc_homeZipcodeTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_homeZipcodeTextField.insets = new Insets(0, 0, 5, 5);
			gbc_homeZipcodeTextField.gridx = 3;
			gbc_homeZipcodeTextField.gridy = 4;
			contentPanel.add(homeZipcodeTextField, gbc_homeZipcodeTextField);
			homeZipcodeTextField.setColumns(10);
		}
		{
			lblWorkStreetCity = new JLabel("Work Address: Street, City");
			GridBagConstraints gbc_lblWorkStreetCity = new GridBagConstraints();
			gbc_lblWorkStreetCity.insets = new Insets(0, 0, 5, 5);
			gbc_lblWorkStreetCity.gridx = 0;
			gbc_lblWorkStreetCity.gridy = 5;
			contentPanel.add(lblWorkStreetCity, gbc_lblWorkStreetCity);
		}
		{
			workStreetTextField = new JTextField();
			GridBagConstraints gbc_workStreetTextField = new GridBagConstraints();
			gbc_workStreetTextField.gridwidth = 3;
			gbc_workStreetTextField.insets = new Insets(0, 0, 5, 5);
			gbc_workStreetTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_workStreetTextField.gridx = 1;
			gbc_workStreetTextField.gridy = 5;
			contentPanel.add(workStreetTextField, gbc_workStreetTextField);
			workStreetTextField.setColumns(10);
		}
		{
			workCitytextField = new JTextField();
			GridBagConstraints gbc_workCitytextField = new GridBagConstraints();
			gbc_workCitytextField.gridwidth = 2;
			gbc_workCitytextField.insets = new Insets(0, 0, 5, 0);
			gbc_workCitytextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_workCitytextField.gridx = 4;
			gbc_workCitytextField.gridy = 5;
			contentPanel.add(workCitytextField, gbc_workCitytextField);
			workCitytextField.setColumns(10);
		}
		{
			lblStateZip_1 = new JLabel("Work Address: State, Zip");
			GridBagConstraints gbc_lblStateZip_1 = new GridBagConstraints();
			gbc_lblStateZip_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblStateZip_1.gridx = 0;
			gbc_lblStateZip_1.gridy = 6;
			contentPanel.add(lblStateZip_1, gbc_lblStateZip_1);
		}
		{
			workStatetextField = new JTextField();
			GridBagConstraints gbc_workStatetextField = new GridBagConstraints();
			gbc_workStatetextField.gridwidth = 2;
			gbc_workStatetextField.insets = new Insets(0, 0, 5, 5);
			gbc_workStatetextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_workStatetextField.gridx = 1;
			gbc_workStatetextField.gridy = 6;
			contentPanel.add(workStatetextField, gbc_workStatetextField);
			workStatetextField.setColumns(10);
		}
		{
			workZipcodetextField = new JTextField();
			GridBagConstraints gbc_workZipcodetextField = new GridBagConstraints();
			gbc_workZipcodetextField.insets = new Insets(0, 0, 5, 5);
			gbc_workZipcodetextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_workZipcodetextField.gridx = 3;
			gbc_workZipcodetextField.gridy = 6;
			contentPanel.add(workZipcodetextField, gbc_workZipcodetextField);
			workZipcodetextField.setColumns(10);
		}
		{
			lblCellPhoneArea = new JLabel("Cell Phone: Area Code, Number");
			GridBagConstraints gbc_lblCellPhoneArea = new GridBagConstraints();
			gbc_lblCellPhoneArea.fill = GridBagConstraints.VERTICAL;
			gbc_lblCellPhoneArea.insets = new Insets(0, 0, 5, 5);
			gbc_lblCellPhoneArea.gridx = 0;
			gbc_lblCellPhoneArea.gridy = 7;
			contentPanel.add(lblCellPhoneArea, gbc_lblCellPhoneArea);
		}
		{
			cellareaTextField = new JTextField();
			GridBagConstraints gbc_cellareaTextField = new GridBagConstraints();
			gbc_cellareaTextField.gridwidth = 2;
			gbc_cellareaTextField.insets = new Insets(0, 0, 5, 5);
			gbc_cellareaTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_cellareaTextField.gridx = 1;
			gbc_cellareaTextField.gridy = 7;
			contentPanel.add(cellareaTextField, gbc_cellareaTextField);
			cellareaTextField.setColumns(10);
		}
		{
			cellphoneTextField = new JTextField();
			GridBagConstraints gbc_cellphoneTextField = new GridBagConstraints();
			gbc_cellphoneTextField.gridwidth = 2;
			gbc_cellphoneTextField.insets = new Insets(0, 0, 5, 0);
			gbc_cellphoneTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_cellphoneTextField.gridx = 3;
			gbc_cellphoneTextField.gridy = 7;
			contentPanel.add(cellphoneTextField, gbc_cellphoneTextField);
			cellphoneTextField.setColumns(10);
		}
		{
			JLabel lblArea = new JLabel("Home Phone: Area Code, Number");
			GridBagConstraints gbc_lblArea = new GridBagConstraints();
			gbc_lblArea.insets = new Insets(0, 0, 5, 5);
			gbc_lblArea.anchor = GridBagConstraints.EAST;
			gbc_lblArea.gridx = 0;
			gbc_lblArea.gridy = 8;
			contentPanel.add(lblArea, gbc_lblArea);
		}
		{
			homeareaTextField = new JTextField();
			GridBagConstraints gbc_homeareaTextField = new GridBagConstraints();
			gbc_homeareaTextField.gridwidth = 2;
			gbc_homeareaTextField.insets = new Insets(0, 0, 5, 5);
			gbc_homeareaTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_homeareaTextField.gridx = 1;
			gbc_homeareaTextField.gridy = 8;
			contentPanel.add(homeareaTextField, gbc_homeareaTextField);
			homeareaTextField.setColumns(10);
		}
		{
			homephoneTextField = new JTextField();
			GridBagConstraints gbc_homephoneTextField = new GridBagConstraints();
			gbc_homephoneTextField.gridwidth = 2;
			gbc_homephoneTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_homephoneTextField.insets = new Insets(0, 0, 5, 0);
			gbc_homephoneTextField.gridx = 3;
			gbc_homephoneTextField.gridy = 8;
			contentPanel.add(homephoneTextField, gbc_homephoneTextField);
			homephoneTextField.setColumns(10);
		}
		{
			lblWorkPhone = new JLabel("Work Phone : Area Code, Number");
			GridBagConstraints gbc_lblWorkPhone = new GridBagConstraints();
			gbc_lblWorkPhone.anchor = GridBagConstraints.EAST;
			gbc_lblWorkPhone.insets = new Insets(0, 0, 5, 5);
			gbc_lblWorkPhone.gridx = 0;
			gbc_lblWorkPhone.gridy = 9;
			contentPanel.add(lblWorkPhone, gbc_lblWorkPhone);
		}
		{
			workareatextField = new JTextField();
			GridBagConstraints gbc_workareatextField = new GridBagConstraints();
			gbc_workareatextField.gridwidth = 2;
			gbc_workareatextField.insets = new Insets(0, 0, 5, 5);
			gbc_workareatextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_workareatextField.gridx = 1;
			gbc_workareatextField.gridy = 9;
			contentPanel.add(workareatextField, gbc_workareatextField);
			workareatextField.setColumns(10);
		}
		{
			workphonetextField = new JTextField();
			GridBagConstraints gbc_workphonetextField = new GridBagConstraints();
			gbc_workphonetextField.gridwidth = 2;
			gbc_workphonetextField.insets = new Insets(0, 0, 5, 0);
			gbc_workphonetextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_workphonetextField.gridx = 3;
			gbc_workphonetextField.gridy = 9;
			contentPanel.add(workphonetextField, gbc_workphonetextField);
			workphonetextField.setColumns(10);
		}
		{
			JLabel lblBirthday = new JLabel("Date Format,Type of Date:");
			GridBagConstraints gbc_lblBirthday = new GridBagConstraints();
			gbc_lblBirthday.insets = new Insets(0, 0, 5, 5);
			gbc_lblBirthday.gridx = 0;
			gbc_lblBirthday.gridy = 10;
			contentPanel.add(lblBirthday, gbc_lblBirthday);
		}
		{
			dformatTextField = new JTextField();
			GridBagConstraints gbc_dformatTextField = new GridBagConstraints();
			gbc_dformatTextField.gridwidth = 2;
			gbc_dformatTextField.insets = new Insets(0, 0, 5, 5);
			gbc_dformatTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_dformatTextField.gridx = 1;
			gbc_dformatTextField.gridy = 10;
			contentPanel.add(dformatTextField, gbc_dformatTextField);
			dformatTextField.setColumns(10);
		}
		{
			bdayTextField = new JTextField();
			GridBagConstraints gbc_bdayTextField = new GridBagConstraints();
			gbc_bdayTextField.insets = new Insets(0, 0, 5, 0);
			gbc_bdayTextField.gridwidth = 2;
			gbc_bdayTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_bdayTextField.gridx = 3;
			gbc_bdayTextField.gridy = 10;
			contentPanel.add(bdayTextField, gbc_bdayTextField);
			bdayTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveContact();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						}
					
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	protected void saveContact()
	{
		String f=fNametextField.getText();
		String m=mnameTextField.getText();
		String l=lnameTextField.getText();
		String homeStreet=homeStreettextField.getText();
		String homeCity=homeCitytextField.getText();
		String homeState=homeStateTextField.getText();
		String homeZipcode=homeZipcodeTextField.getText();
		String workStreet=workStreetTextField.getText();
		String workCity=workCitytextField.getText();
		String workState=workStatetextField.getText();
		String workZipcode=workZipcodetextField.getText();
		String cell_area_code=cellareaTextField.getText();
		String cellNumber=cellphoneTextField.getText();
		String home_area_code=homeareaTextField.getText();
		String homeNumber=homephoneTextField.getText();
		String work_area_code=workareatextField.getText();
		String workNumber=workphonetextField.getText();

		String format=dformatTextField.getText();
		String bday=bdayTextField.getText();
		ContactListForm temp;
		try
		
		{
		if(updateMode)
			{
			temp= prevContact;
			temp.setFirstName(f);
			temp.setMiddleName(m);
			temp.setLastName(l);
			temp.setHomeStreet(homeStreet);
			temp.setHomeCity(homeCity);
			temp.setHomeState(homeState);
			temp.setHomeZIP(homeZipcode);
			temp.setWorkStreet(workStreet);
			temp.setWorkCity(workCity);
			temp.setWorkState(workState);
			temp.setWorkZIP(workZipcode);
			temp.setCellAreaCode(cell_area_code);
			temp.setCellPhone(cellNumber);
			temp.setHomeAreaCode(home_area_code);
			temp.setHomePhone(homeNumber);
			temp.setWorkAreaCode(work_area_code);
			temp.setWorkPhone(workNumber);
			temp.setFormat(format);
			temp.setBD(bday);
			}
			
		else
		{
			temp = new ContactListForm(f,m,l,homeStreet,homeCity,homeState,homeZipcode,workStreet,workCity,workState,workZipcode,cell_area_code,cellNumber,home_area_code,homeNumber,work_area_code,workNumber,format,bday);
		}
		
		if(updateMode)
			{
				ContactConnectDatabase.modifyContact(temp);
				setVisible(false);
				dispose();
				ContactAppManager.RefreshEmployeesView();
				JOptionPane.showMessageDialog(ContactAppManager,"Contact modified succesfully!","Contact Modified",JOptionPane.INFORMATION_MESSAGE);
			}
		else if(viewMode)
		{
			setVisible(false);
			dispose();
		}
			else
			{
				
			ContactConnectDatabase.addContact(temp);
			setVisible(false);
			dispose();
			
			ContactAppManager.RefreshEmployeesView();
			JOptionPane.showMessageDialog(ContactAppManager,"Contact added succesfully!","Contact Added",JOptionPane.INFORMATION_MESSAGE);
			}
	}
		catch(Exception exc)
		{
			JOptionPane.showMessageDialog(ContactAppManager, "Error: "+exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}

