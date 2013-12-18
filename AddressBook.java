package addressbook;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class AddressBook implements ActionListener, Serializable{

          /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		JPanel topPanel,bottomPanel;
    	  JScrollPane scrollPane;
    	  JFrame frame;

    	  JMenuBar menubar = new JMenuBar(); ;
       	  JMenu menu = new JMenu();
       	  JMenuItem menuItem;
       	  private final AddressBookOperation abo = new AddressBookOperation();

       	public static void main(String args[]) {
       		@SuppressWarnings("unused")
			AddressBook addressbook = new AddressBook();

        }
    	  @SuppressWarnings("deprecation")
		public AddressBook() {
            frame = new JFrame("");
            frame.setSize(600,200);
                    
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            addWidgets();
            frame.show();
    	  }
    	  //mainframe design
         public void addWidgets() {
	         menu = new JMenu("File");
       	     menubar.add(menu);
       	            
       	     menuItem = new JMenuItem("Save");
       	     menu.add(menuItem);
       	     menuItem.addActionListener(this);
       	     
       	     menuItem = new JMenuItem("Open");
    	     menu.add(menuItem);
    	     menuItem.addActionListener(this);
       	            
       	     menu = new JMenu("Sort");
       	     menubar.add(menu);
       	     menuItem = new JMenuItem("By Name");
       	     menu.add(menuItem);
       	     menuItem.addActionListener(this);
       	               	            
       	     menuItem = new JMenuItem("By Zip");
       	     menu.add(menuItem);
    	     menuItem.addActionListener(this);
       	            
       	     frame.setJMenuBar(menubar);
       	            
       	     JPanel topPanel = new JPanel();
       	     JLabel label = new JLabel("Welcome to my AddressBook ");
       	     topPanel.add(label);
       	     
             JPanel bottomPanel = new JPanel();
             //Add Buttons To Bottom Panel
             JButton AddNew = new JButton("Add New");
             AddNew.addActionListener(this);
             bottomPanel.add(AddNew);
             
             JButton Edit = new JButton("Edit");
             Edit.addActionListener(this);
             bottomPanel.add(Edit);
             
             JButton Delete = new JButton("Delete");
             Delete.addActionListener(this);
             bottomPanel.add(Delete);
             
             JButton Search = new JButton("Search");
             Search.addActionListener(this);
             bottomPanel.add(Search);
             
             JButton Print = new JButton("Print");
             Print.addActionListener(this);
             bottomPanel.add(Print);
             
             frame.getContentPane().add(topPanel, BorderLayout.NORTH);
             frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
             frame.setResizable(false);
            }
    	 public void actionPerformed(ActionEvent ae){
    		 if(ae.getActionCommand() == "Add New") {
    			 abo.AddNew();
    		 }
    	     else  if(ae.getActionCommand() == "Search") {
    	         abo.SearchContacts();
    	     }
    	     else  if(ae.getActionCommand() == "Delete") {
    	         abo.DeleteContact();
    	     }
    	     else if(ae.getActionCommand() == "By Name") {
    	       	 abo.sortByName();
    	     }
    	     else if(ae.getActionCommand() == "By Zip") {
    	      	 abo.sortByZip();
    	     }
    	     else  if(ae.getActionCommand() == "Print") {
    	         abo.Print();
    	     }
    	     else  if(ae.getActionCommand() == "Save") {
    	    	 JFileChooser chooser = new JFileChooser();
	     	        chooser.setCurrentDirectory(new File("."));
	     	        chooser.setMultiSelectionEnabled(false);
	     	        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	     	        chooser.showSaveDialog(frame);
	     	        FileInputStream reader = null;
	     	        FileOutputStream  writer = null;
	     	        String file=null;

	     	        int index;

	     	        try {
	     	             file = chooser.getSelectedFile().getPath();
	     	         }
	     	        catch(Exception e) {
	     	        	e.printStackTrace();
	     	         }

	     	        try {
	     	              writer = new FileOutputStream(file + "file.txt");
	     	           }
	     	        catch(Exception e) {
	     	        	e.printStackTrace();
	     	           }
	     	        try {
	     	             reader = new FileInputStream("file.txt");
	     	          }
	     	        catch(Exception e) {
	     	        	e.printStackTrace();
	     	          }

	     	        try {
	     	           	   do {  index = reader.read();
	     	           	        if(index!=-1)
	     	           	            writer.write(index);
	     	           	     }while(index!=-1);
	     	           	}
	     	         catch(Exception e) {
	     	        	 e.printStackTrace();
	     	            }
    	     }
    	 }
    }
