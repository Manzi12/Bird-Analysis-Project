import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class menuList {
	
	public Menu head=null;
	//Menu menu = new Menu(menuItem, itemPrice);
	
	/**
	 * method to add the item and its price to the menu
	 * @param menuItem
	 * @param itemPrice
	 */
	public void insert(String menuItem,int itemPrice) {
		Menu menu = new Menu(menuItem, itemPrice);
		menu.next=head;
		head=menu;
	}
	
	
	/**
	 * Searching item by name
	 * @param name
	 * @return
	 */
	public Menu getMenuItemByName(String name) {
		System.out.println("Looking for: "+name);
		Menu temp=head;
		while(temp!=null && !temp.getMenuItem().equals(name)) temp=temp.next;
		
		if(temp!=null) return temp; //found it return
		return null; //wasn't found return null
	}
	
	
	/**
	 * this one takes in the item and deletes it
	 * @param item
	 */
	public void deleteMenuItem(String item) {
		Menu temp = head;
		Menu temp2=null;
		System.out.println("Deleting menu item "+item+"...");
		while(temp != null && temp.getMenuItem().equals(item)) {
			if(temp.getMenuItem()==item) { 
				temp2=temp;
				temp=temp.next; 
				}
			else 
			{	//found item to delete	
 				if(temp2!=null) 
 					temp2.next=temp.next;	
 				else 
 					head=temp.next;			
 				
 				System.out.println("item found and deleted");
 				return;
			}
		}
		System.out.println("item not found");
	}
	
	/**
	 * this menthod list all the items in the menu
	 */
	public void listMenuItem() {
		Menu menu = head;
		while(menu != null) {
			System.out.println("Item in the menu:" + menu.getMenuItem() + " " +  " the price €" + menu.getItemPrice());
			menu = menu.next;
		}
	}
	
	@SuppressWarnings("unchecked")
    public  void load()
    		throws Exception{
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("menu.xml"));
        head = (Menu) is.readObject();
        is.close();
    }
    
	/**
	 * this fuction saves the program data to xml file 
	 * @throws Exception
	 */
    public void save() 
    		throws Exception{
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("menu.xml"));
        out.writeObject(head);
        out.close();    
    }
	
	
}




	