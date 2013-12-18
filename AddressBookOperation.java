package addressbook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.Observable;
import java.util.Vector;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class AddressBookOperation extends Observable  implements Serializable, ListSelectionListener, ActionListener, Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Thread thread;
    int next;
    Vector<Contacts> collection = new Vector<Contacts>();
    String columnNames[] = { "First Name", "Last Name", "Address",  "City", "State", "Zip Code", "Phone No."  };
    Object data[][]= new Object[200][7];
    //gui part
    //frame
	JFrame newFrame;
	//textfields
    JTextField tFname, tLname, tAddress, tCity, tState, tZip, tPhone, tSearch;
    //labels
    JLabel lFname, lLname, lAddress, lCity, lState, lZip, lPhone;
    //buttons
    JButton bAdd, bSave, bFind, bCancel, bDelete;
    @SuppressWarnings("unused")
	private AbstractButton txt;
    //tables
    JTable searchTable, printTable;
    //list
    JList list;
    DefaultListModel listModel;
    ListSelectionModel listSelectionModel;
    
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
    
    @SuppressWarnings("unchecked")
    AddressBookOperation(){
    	try {
    		FileInputStream fileReader = new FileInputStream("file.txt");
    		ObjectInputStream  objectReader = new ObjectInputStream(fileReader);
    		collection = (Vector<Contacts>) objectReader.readObject();
    		objectReader.close();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
 	public void run(){
 		try {
 			FileOutputStream fileWriter = new FileOutputStream("file.txt");
 			ObjectOutputStream objectWriter = new ObjectOutputStream(fileWriter);
 			objectWriter.writeObject(collection);
 			objectWriter.flush();
 			objectWriter.close();
 		}
 		catch(Exception e) {
 			JOptionPane.showMessageDialog(newFrame, 
 					"Error Opening Data File: Could Not Save Contents.", "Error Opening Data File", JOptionPane.INFORMATION_MESSAGE);
 		}
 	}

    @SuppressWarnings({ "deprecation" })
	public void AddNew() {
    	
    	newFrame = new JFrame("Add New Contact");
    	newFrame.setSize(250,250);
    	newFrame.setResizable(false);

    	JPanel centerPane = new JPanel();
    	lFname = new JLabel("First Name: ");
    	tFname = new JTextField(30);
    	centerPane.add(lFname);
    	centerPane.add(tFname);
            
    	lLname = new JLabel("Last Name: ");
    	tLname = new JTextField(30);
    	centerPane.add(lLname);
    	centerPane.add(tLname);
            
    	lAddress = new JLabel("Address: ");
    	tAddress = new JTextField(30);
    	centerPane.add(lAddress);
    	centerPane.add(tAddress);
            
    	lCity = new JLabel("City: ");
    	tCity = new JTextField(30);
    	centerPane.add(lCity);
        centerPane.add(tCity);
            
        lState = new JLabel("State: ");
        tState = new JTextField(30);
        centerPane.add(lState);
        centerPane.add(tState);
            
        lZip = new JLabel("Zip Code: ");
        tZip = new JTextField(30);
        centerPane.add(lZip);
        centerPane.add(tZip);
            
        lPhone = new JLabel("Phone No.: ");
        tPhone = new JTextField(30);
        centerPane.add(lPhone);
        centerPane.add(tPhone);
            
        JPanel bottomPane = new JPanel();
        bAdd = new JButton("Add");
        bAdd.addActionListener(this);
        bottomPane.add(bAdd);
            
        bSave = new JButton("Save!");
        bSave.addActionListener(this);
        bSave.setEnabled(false);
        bottomPane.add(bSave);
            
        centerPane.setLayout(new GridLayout(0,2));
        newFrame.getContentPane().add(centerPane,BorderLayout.CENTER);
        newFrame.getContentPane().add(bottomPane,BorderLayout.SOUTH);
        newFrame.setLocation(screenWidth/4, screenHeight/4);
        newFrame.show();
    }
    /**
     * search contact 
     * */
    @SuppressWarnings({ "deprecation"})
	public void SearchContacts() {
    	
    	newFrame = new JFrame("Search Contacts");
        newFrame.setSize(720,230);
        newFrame.setLocation(screenWidth/4, screenHeight/4); 
        newFrame.setResizable(false);

        JPanel centerPane = new JPanel();
        JLabel label2 = new JLabel("Enter info:");
        centerPane.add(label2);
            
        tSearch = new JTextField(20);
        centerPane.add(tSearch);
            
        bFind = new JButton("Find");
        bFind.addActionListener(this);
        centerPane.add(bFind);
            
        bCancel = new JButton("Cancel");
        bCancel.addActionListener(this);
        centerPane.add(bCancel);
        //table for displaying search result
        searchTable = new JTable(data, columnNames);
        searchTable.setPreferredScrollableViewportSize(new Dimension(500, 130));
        JScrollPane scrollPane = new JScrollPane(searchTable);

        newFrame.getContentPane().add(centerPane,BorderLayout.CENTER);
        newFrame.getContentPane().add(scrollPane,BorderLayout.SOUTH);
        newFrame.show();
    }
    
    /**
     * delete contact/s
     * @param firstname first name of contact
     * @param lastname last name of the contact
     * */
    @SuppressWarnings({ "unused", "deprecation" })
	public void DeleteContact() {
    	
    	String firstname;
    	String lastname;
        Contacts contact = new Contacts();
        newFrame = new JFrame("Delete Contact");
        newFrame.setSize(300,300);
        newFrame.setLocation(screenWidth/4, screenHeight/4);
            
        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("Current Contacts in My Address Book");
        topPanel.add(label);
            
        JPanel centerPane = new JPanel();
        listModel = new DefaultListModel();
        
        for(int list = 0; list<collection.size(); list++) {
          	contact = collection.elementAt(list);
           	firstname = contact.getFirstName();
           	lastname = contact.getLastName();
           	listModel.addElement(firstname + " " + lastname);
        }
        
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel = list.getSelectionModel();
        listSelectionModel.addListSelectionListener(this);
        JScrollPane listScrollPane = new JScrollPane(list);

        JPanel bottomPane = new JPanel();
        bDelete = new JButton("Delete this");
        bottomPane.add(bDelete);
        bDelete.addActionListener(this);

        bCancel = new JButton("Cancel");
        bottomPane.add(bCancel);
        bCancel.addActionListener(this);

        newFrame.getContentPane().add(topPanel, BorderLayout.NORTH);
        newFrame.getContentPane().add(listScrollPane, BorderLayout.CENTER);
        newFrame.getContentPane().add(bottomPane, BorderLayout.SOUTH);
        newFrame.show();
    }

    /** Sort the collection by name
     * @param firstname first name of contact
     * @param lastname last name of contact
     * 
     */
    @SuppressWarnings("deprecation")
	public void sortByName() {

        //sorts collection by name
        Collections.sort(collection, new Contacts.CompareByName());
        notifyObservers();
    	String firstname;
    	String lastname;
        Contacts contact = new Contacts();
        
        newFrame = new JFrame("Sort Contacts by Name");
        newFrame.setSize(300,300);
        newFrame.setLocation(screenWidth/4, screenHeight/4);
            
        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("Current Contacts in My Address Book");
        topPanel.add(label);
            
        listModel = new DefaultListModel();
        
        for(int list = 0; list<collection.size(); list++) {
          	contact = collection.elementAt(list);
           	firstname = contact.getFirstName();
           	lastname = contact.getLastName();
           	listModel.addElement(firstname + " " + lastname);
        }
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel = list.getSelectionModel();
        listSelectionModel.addListSelectionListener(this);
        JScrollPane listScrollPane = new JScrollPane(list);
       
        newFrame.getContentPane().add(topPanel, BorderLayout.NORTH);
        newFrame.getContentPane().add(listScrollPane, BorderLayout.CENTER);
        newFrame.show();
    }
    
    /** Sort the collection by ZIP
     */
    @SuppressWarnings("deprecation")
	public void sortByZip()
    {
    	//sorts contacts by zip code
        Collections.sort(collection, new Contacts.CompareByZip());
        notifyObservers(); 
        
    	String firstname;
    	String lastname;
        Contacts contact = new Contacts();
        newFrame = new JFrame("Sort Contacts by Name");
        newFrame.setSize(300,300);
        newFrame.setLocation(screenWidth/4, screenHeight/4);
            
        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("Current Contacts in My Address Book");
        topPanel.add(label);
            
        listModel = new DefaultListModel();
        
        for(int list = 0; list<collection.size(); list++) {
          	contact = collection.elementAt(list);
           	firstname = contact.getFirstName();
           	lastname = contact.getLastName();
           	listModel.addElement(firstname + " " + lastname);
        }
        
        
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel = list.getSelectionModel();
        listSelectionModel.addListSelectionListener(this);
        JScrollPane listScrollPane = new JScrollPane(list);
       
        newFrame.getContentPane().add(topPanel, BorderLayout.NORTH);
        newFrame.getContentPane().add(listScrollPane, BorderLayout.CENTER);
        newFrame.show();
    }
    
    /**
     * Print all contacts
     * */
    @SuppressWarnings("deprecation")
	public void Print() {
    	Contacts contact = new Contacts();
        newFrame = new JFrame("Address Book");
        newFrame.setSize(700,300);
                
        JPanel topPane = new JPanel();
        JLabel label = new JLabel("Current Contacts in my Address Book");
        topPane.add(label);
        Object data[][]= new Object[collection.size()][7];
                 
        for(int index = 0; index<collection.size(); index++) {
        	contact = collection.elementAt(next);
     	    data[index][0] = contact.getFirstName();
            data[index][1] = contact.getLastName();
            data[index][2] = contact.getAddress();
            data[index][3] = contact.getCity();
            data[index][4] = contact.getState();
            data[index][5] = contact.getZip();
            data[index][6] = contact.getPhone();
            next++;
       }
        next=0;
        
        printTable = new JTable(data, columnNames);
        printTable.setPreferredScrollableViewportSize(new Dimension(400, 130));
        JScrollPane scrollPane = new JScrollPane(printTable);
       
        newFrame.getContentPane().add(topPane,BorderLayout.NORTH);
        newFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        newFrame.setLocation(screenWidth/4, screenHeight/4);
        newFrame.show();
    }
    /**
     * action event
     * */
    @SuppressWarnings({ "deprecation" })
	public void actionPerformed(ActionEvent event) {
 	   if(event.getActionCommand() == "Add") {
 		   if(tFname.getText().equals("") && 
 				   tLname.getText().equals("") && tAddress.getText().equals("") &&
					   tCity.getText().equals("") && tState.getText().equals("") &&
					   tZip.getText().equals("") && tPhone.getText().equals("")) {
        	            	JOptionPane.showMessageDialog(newFrame,
        	            			"Error! Entries are empty! Fill in all entries to save contact in addressbook.", "Entries Empty", JOptionPane.INFORMATION_MESSAGE);
 		   }
 		   else {
 			   	Contacts contact = new Contacts();
 			   	contact.initEntry(tFname.getText(),tLname.getText(),tAddress.getText(),tCity.getText(),tState.getText(),tZip.getText(),tPhone.getText());
 			   	collection.addElement(contact);
 			   	tFname.setText("");
 			   	tLname.setText("");
 			   	tAddress.setText("");
 			   	tCity.setText("");
 			   	tState.setText("");
 			   	tZip.setText("");
 			   	tPhone.setText("");
 			   	if(bSave.isEnabled() == false)
 			   		bSave.setEnabled(true);
 		   }
 	   }
 	   else if(event.getActionCommand() == "Edit") {
 		   
 	   }
 	   else if(event.getActionCommand() == "Save!") {
 		   saveCollection();
 		   newFrame.setVisible(false);
 	   }
 	   else if(event.getActionCommand() == "Delete this") {
 		   int index;
 		   
 		   try {
 			   index = list.getSelectedIndex();
 			   if(index == -1) {
 				   JOptionPane.showMessageDialog(newFrame, "Select a Contact first to delete it.", "Select a Contact First", JOptionPane.INFORMATION_MESSAGE);
 			   }
 			   else {
 				   int n = JOptionPane.showConfirmDialog(newFrame, "Are you sure you want to delete this?", "Are you sure?", JOptionPane.YES_NO_OPTION);                                    
 				   
 				   if (n == JOptionPane.YES_OPTION) {
 					   listModel.remove(index);
 					   collection.removeElementAt(index);
 					   saveCollection();
 					   newFrame.show();
 				   }
 				   else if (n == JOptionPane.NO_OPTION) {
 					   //do nothing;
 				   }
 			   }
 		   }
 		   catch(Exception e) {
 			   e.printStackTrace();
 		   }
 	   }
 	   else if(event.getActionCommand() == "Ok") {
 		   newFrame.setVisible(false);
 	   }
 	   else if(event.getActionCommand() == "Cancel") {
 		   newFrame.setVisible(false);
 	   }
        else if(event.getActionCommand() == "Find") {
     	   String search = tSearch.getText();
     	   boolean found=false;
     	   Contacts contact = new Contacts();
     	   int currentIndex = 0;
     	   
     	   for(int thread=0;thread<collection.size();thread++) {
     		   contact = collection.elementAt(thread);
     		   if(search.equalsIgnoreCase(contact.getFirstName()) || search.equalsIgnoreCase(contact.getAddress()) ||
     				   search.equalsIgnoreCase(contact.getCity()) || search.equalsIgnoreCase(contact.getState())   ||
                       search.equalsIgnoreCase(contact.getZip())  || search.equalsIgnoreCase(contact.getPhone())   ||
                       search.equalsIgnoreCase(contact.getFirstName() + " " + contact.getLastName())) {
     			   found=true;
     			   data[currentIndex][0] = contact.getFirstName();
     			   data[currentIndex][1] = contact.getLastName();
                   data[currentIndex][2] = contact.getAddress();
                   data[currentIndex][3] = contact.getCity();
                   data[currentIndex][4] = contact.getState();
                   data[currentIndex][5] = contact.getZip();
                   data[currentIndex][6] = contact.getPhone();
                   searchTable = new JTable(data,columnNames);
                   newFrame.setSize(730,220);
                   newFrame.setSize(730,220);
                   if(currentIndex<100)
                 	  currentIndex++;
     		   }
     	   }
     	   if(found) {
     		   //display result
     	   }
     	   else {
     		   JOptionPane.showMessageDialog(newFrame, "No Such Contact Found!", "Error Message", JOptionPane.INFORMATION_MESSAGE);
     	   }
        }
    }
    /**
     * save the thread that has been implemented
     * */
    public void saveCollection() {
 	  thread = new Thread(this, "Save collection Thread");
 	   thread.start();
    }
    
    public void valueChanged(ListSelectionEvent lse) {
    	//do nothing
    }
	}
