
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class TableList {
	
	public Table head;
	
	/**
	 * this function adds the table and its number of seats
	 * @param tableNumber
	 * @param numberOfSeats
	 */
	public void insert(int tableNumber,int numberOfSeats) {
		Table table = new Table(tableNumber, numberOfSeats);
		if(head == null) {
			head = table;
		}else {
			Table t = head;
			while(t.next != null) {
				t = t.next;
			}
			t.next = table;
		}
	}
	
	
	/**
	 * this list all the available tables
	 */
	public void showTable() {
		Table table = head;
		while(table != null) {
			System.out.println("Table No." + table.getTableNumber() + " " + table.getNumberOfSeats() + " " + " seats" );
			table.book.viewBookingList();
			table = table.next;
		}
	}
	
	/**
	 * takes in id and uses the id to look up the table
	 * @param id
	 * @return
	 */
	public Booking getBookingByIdSearchAllTables(int id) {
		Table temp=head;
		while(temp!=null && temp.book.getBookingById(id)==null) 
		temp=temp.next;
		
		if(temp!=null)
		return temp.book.getBookingById(id); //found it return
		return null; //wasn't found return null
	}
	
	
	/**
	 * well this function searches for the table using the number of seats
	 * @param seats
	 * @return
	 */
	public Table searchTable(int seats) {
		Table temp = head;
		while(temp!=null) {
			if(temp.getNumberOfSeats()==seats) {
				System.out.println("table with that suits you is available");
				temp=temp.next;
			}else {
				System.out.println("Suitable table is number"+temp.getTableNumber());
				
			}
		
		}
		
		return temp;
	}

			
		
		
	
				
	/**
	 * this code takes in the number of table and if the table is on the list.will delete	
	 * @param number
	 */
	public void deleteTable(int number) {
		Table temp = head,temp2=null;
		System.out.println("Deleting table "+number+"...");
		while(temp != null) {
			if(temp.getTableNumber() != number) { 
				temp2=temp;
				temp=temp.next; 
				}
			else 
			{	//found item to delete	
 				if(temp2!=null) 
 					temp2.next=temp.next;	
 				else 
 					head=temp.next;			
 				
 				System.out.println("Table found and deleted");
 				return;
			}
		}
		System.out.println("Table not found");
	}
	
	/**
	 * this function deletes all the tables
	 * @return
	 */
	public void deleteAllTables() {
		Table temp = head;
		head=null;
			System.out.println("reset successfully ");
	}
	
	/**
	 * this fuction loads the table to the xml file table.xml
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
    public  void load()
    		throws Exception{
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("table.xml"));
        head = (Table) is.readObject();
        is.close();
    }
    
	/**
	 * this fuction saves the program data to xml file 
	 * @throws Exception
	 */
    public void save() 
    		throws Exception{
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("table.xml"));
        out.writeObject(head);
        out.close();    
    }
}
