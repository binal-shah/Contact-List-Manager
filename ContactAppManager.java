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
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import javax.swing.JOptionPane;
import java.util.List;

import contact_app_manager.ContactManagerTable;
import contact_app_manager.ContactListForm;
import contact_app_manager.ContactConnectDatabase;
import javax.swing.table.AbstractTableModel;

public class ContactAppManager extends JFrame {

	private JPanel contentPane;
	private JTextField lnametextField;
	private JTable table;
	
	private ContactConnectDatabase ContactConnectDatabase;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					ContactAppManager frame = new ContactAppManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ContactAppManager() {
		
		try 
		{
			ContactConnectDatabase = new ContactConnectDatabase();
		}
		catch(Exception exc)
		{
			JOptionPane.showMessageDialog(this, "Error: "+exc,"Error",JOptionPane.ERROR_MESSAGE);
		}
		setTitle("Contact Display Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblEnter = new JLabel("Enter");
		panel.add(lblEnter);
		
		lnametextField = new JTextField();
		lnametextField.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lnametextField);
		lnametextField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
				
				List<ContactListForm> contacts=null;
				String lname = lnametextField.getText();
				if(lname!=null && lname.trim().length()>0)
				{
					contacts = ContactConnectDatabase.searchForContact(lname);
					
				}
				else
				{
					contacts = ContactConnectDatabase.getAllContacts();
					
				}
				
				ContactManagerTable model = new ContactManagerTable(contacts);
				
				table.setModel(model);
				}
				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(ContactAppManager.this,  "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnSearch);
		
		JButton btnViewContact = new JButton("View Contact");
		btnViewContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int row = table.getSelectedRow();
				
				// make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(ContactAppManager.this, "You must select a contact", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				ContactListForm temp = (ContactListForm) table.getValueAt(row, ContactManagerTable.OBJECT_COL);
				ContactAdd dialog = new ContactAdd(ContactAppManager.this, ContactConnectDatabase,temp,false,true);
				dialog.setVisible(true);
				}
				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(ContactAppManager.this, "Error "+exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnViewContact.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(btnViewContact);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnAddContact = new JButton("Add New Contact");
		btnAddContact.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(btnAddContact);
		
		JButton btnUpdateContact = new JButton("Modify Contact");
		btnUpdateContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					int row = table.getSelectedRow();
				
				if (row < 0) {
					JOptionPane.showMessageDialog(ContactAppManager.this, "You must select a contact", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				ContactListForm temp = (ContactListForm) table.getValueAt(row, ContactManagerTable.OBJECT_COL);
				ContactAdd dialog = new ContactAdd(ContactAppManager.this, ContactConnectDatabase, temp,true,false);
				dialog.setVisible(true);
				}
				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(ContactAppManager.this, "Error "+exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	
		panel_1.add(btnUpdateContact);
		
		JButton btnDeleteRow = new JButton("Delete Contact");
		btnDeleteRow.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(btnDeleteRow);
		btnDeleteRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int row = table.getSelectedRow();
				
				// Make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(ContactAppManager.this, "You must select a contact", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				ContactListForm temp = (ContactListForm) table.getValueAt(row, ContactManagerTable.OBJECT_COL);
				ContactConnectDatabase.deleteContact(temp);
				JOptionPane.showMessageDialog(ContactAppManager.this, "Contact deleted successfully", "Contact deleted", JOptionPane.ERROR_MESSAGE);
				RefreshEmployeesView();
				}
				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(ContactAppManager.this, "Error "+exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnAddContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ContactAdd dialog = new ContactAdd(ContactAppManager.this,ContactConnectDatabase);
				dialog.setVisible(true);
				
			}
		});
	}

	public void RefreshEmployeesView() {
		try
		{
			List<ContactListForm> contacts=null;
			contacts=ContactConnectDatabase.getAllContacts();
			ContactManagerTable model= new ContactManagerTable(contacts);
			table.setModel(model);
			}
			catch(Exception exc)
			{
				JOptionPane.showMessageDialog(ContactAppManager.this, "Error "+exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		
	

}

