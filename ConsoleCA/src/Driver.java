
import java.util.Scanner;


public class Driver {
	
	/**
	 * objects creation
	 */
	public static TableList Tlist = new TableList();
	public static menuList mList = new menuList();
	public static Scanner scan = new Scanner(System.in);
	
	/**
	 * Driver initialisation
	 */
	public Driver() {
		runMenu();
	}
	
	public static  void search() {
	System.out.print("search by number of seats : " );
	int seats = scan.nextInt();
	Tlist.searchTable(seats);
	}
	
	/**
	 * the methods that allows you to add purchase
	 */
	public static void addPurchase() {
		System.out.println("please enter your booking id");
		int id = scan.nextInt();
		// found the booking
		Booking booking=Tlist.getBookingByIdSearchAllTables(id);
		
		if(booking!=null) {
			System.out.println("please enter the item you want");
			scan.nextLine();
			String item = scan.nextLine();
			
			System.out.println("please enter the quantity you want");
			int Quantity = scan.nextInt();
			
			booking.pList.addPurchase(item,Quantity);
			System.out.println("Purchase added.");
			
		}
		
	}
	
	/**
	 * the methods that list the purchases added
	 */
	public static void listPurchase() {
		System.out.println("please enter your booking id");
		int id = scan.nextInt();
		// found the booking
		Booking booking=Tlist.getBookingByIdSearchAllTables(id);
		
		if(booking!=null) {
			booking.pList.viewPurchaseList();
		}
		else {
			System.out.println("Booking not found.");
		}
		
	}
	
	/**
	 * the method deletes the purchase
	 */
	public static void deletePurchase() {
		System.out.println("please enter your booking id");
		int id = scan.nextInt();
		
		// found the booking
		Booking booking=Tlist.getBookingByIdSearchAllTables(id);
		if(booking!=null) {
			System.out.println("please select the item you want to delete");
			String item = scan.next();
			booking.pList.deletePurchase(item);
		}else {
			System.out.println("Sorry there are no purchases on this booking");
		}
	}
	
	
	
	/**
	 * this a method to delete a purchase
	 */
	public static void deleteAllPurchases() {
		System.out.println("please the booking id to delete");
		int id = scan.nextInt();
		Booking booking=Tlist.getBookingByIdSearchAllTables(id);
		if(booking != null) {
			booking.pList.deleteAllPurchases();
			booking.pList.viewPurchaseList();
		}else {
			System.out.println("Sorry there are no purchases on this booking");
		}
		
	}
	
	/**
	 * this a method to checkout and get your recipt as well
	 */
	public static void reciept() {
		System.out.println("Thank you for eating with us");
		System.out.println("below is your receipt");
		System.out.println("--------------------------------------------");
		System.out.println("please enter your booking id to pay and check Out");
		int id = scan.nextInt();
		Booking booking = Tlist.getBookingByIdSearchAllTables(id);
		if(booking != null) {
			booking.pList.viewPurchaseList();
			int total = booking.pList.subTotalForPurchase();
			System.out.println("The Total for your purchase is €:" +total);
			System.out.println("----------------------------------------");
			System.out.println("Thank you..See you soon");
		}
		
	}
	
	
//	public static void timeCheck(int time) {
//		i
//		
//	}
	
	/**
	 * the method allows you to add a booking to the system
	 */
	public static  void addBooking() {
		System.out.println("Booking details");
		
		System.out.print("\t" + "Please enter your booking ID (100-500) : ");
		int bookingId = scan.nextInt();
		
		System.out.print("\t" + "Name (20 character max) : ");
		String nameOfPerson = scan.next();
		
		System.out.print("\t" + "Total number of people (maxmum 30) : ");
		int totalNumberOfPeople = scan.nextInt();
		
		System.out.print("\t" + "Table number booked(between 1-30) : ");
		int tableNumberBooked = scan.nextInt();
		
		System.out.print("\t" + "What time is your check in (between 13-23 hours) : ");
	
		int time = scan.nextInt();

		System.out.print("\t" + "Hours booked for the table (between 1-2 hours) : ");
		int numberOfHoursBooked = scan.nextInt();
		
		Booking book = new Booking(bookingId,nameOfPerson, totalNumberOfPeople ,tableNumberBooked,time,numberOfHoursBooked);
		
		Table temp=Driver.Tlist.head;
		
		while(temp!=null && temp.getTableNumber()!=tableNumberBooked) 
			temp=temp.next;
		
		if(temp!=null) { //found table
			book.next=temp.book.head;
			temp.book.head=book;
			
			System.out.println("Booking confirmed.");
		}
			else System.out.println("Error making booking.");
	}
	
	/**
	 * this is a restaurant list of function to select from
	 * @return
	 */
	private int RestaurantMenu() {
		System.out.println("-------WELCOME TO MAGIC RESTAURANT-----");
		System.out.println(" 1)  Add Table");
		System.out.println(" 2)  List all Tables");
		System.out.println(" 3)  Search the table that suits");
		System.out.println("-----------------------------");
		System.out.println(" 4)  Add a Booking (to Table number)");
		System.out.println(" 5)  Add to the menu" );
		System.out.println(" 6)  The Menu list");
		System.out.println(" 7)  Add puchase to the your table ");
		System.out.println(" 8)  List Purchase for your table");
		System.out.println(" 9)  Delete a Table");
		System.out.println("-------------------------------------");
		System.out.println(" 10)  Delete a Booking");
		System.out.println(" 11)  Delete a Menu Item");
		System.out.println(" 12)  Delete purchase");
		System.out.println(" 13)  Reset");
		System.out.println(" 14)  Delete all purchases");
		System.out.println("-------------------------------------");
		System.out.println(" 15) Check out facility");
		System.out.println("------------------------------");
		System.out.println(" 16)  Save to XML");
		System.out.println(" 17)  load from XNL");
		int option = validNextInt("==>>");
		return option;
	}

	/**
	 * here follows the function of the list of functions above
	 */
	private void runMenu() {
		int option = RestaurantMenu();
		while(option !=0) {
			switch (option) {
			case 1: 	
						System.out.println("Please enter the table number");
						int tn = scan.nextInt();
						System.out.println("please enter number of seats");
						int ns = scan.nextInt();
						Tlist.insert(tn,ns);
						System.out.println("Table Added");	
							break;
			case 2:		Tlist.showTable();
							break;
			case 3:  	System.out.println("Please enter the total number of seat needed");
						int seats = scan.nextInt();
						Tlist.searchTable(seats);
							break;
			case 4:  	addBooking();
							break;
			case 5:  	System.out.println("Please enter the item you want:");
						scan.nextLine();
						
						String menuItem = scan.nextLine();
						try {
						System.out.println("please enter price of the item: € ");
						int itemPrice = scan.nextInt();
						mList.insert(menuItem, itemPrice);
						
						System.out.print("Item "+menuItem+" added");
						}catch(Exception e) { System.out.print("Error. Purchase not added");}
							break;
							
			case 6:      mList.listMenuItem();
						break;
						
			case 7:  	System.out.println("You have successfully check in");
						System.out.println("You hcan now press your order");
						addPurchase();
						break;
						
			case 8:     listPurchase();
						break;
						
			case 9:	System.out.println("Please the table number to delete");
						int tableNumber = scan.nextInt();
						Tlist.deleteTable(tableNumber);
						break;
						
			case 10: 	System.out.println("Please the id of booking to delete");
						int id = scan.nextInt();
						Tlist.head.book.deleteBooking(id);
						break;
						
			case 11: 	System.out.println("Please select the item to delete");
						String item = scan.next();
						mList.deleteMenuItem(item);
						break;
						
			case 12: 	deletePurchase();
						break;
						
			
						
			case 13:	Tlist.deleteAllTables();
						Tlist.showTable();
						break;
							
			case 14:	deleteAllPurchases();
						break;
			
						
			case 15:	reciept();
						break;
						
			case 16:	try {
							Tlist.save();
						} catch (Exception e) {
							System.out.println("Error Reading from file: " + e);
						}
							System.out.println("Saving booking details....");
							System.out.println("Save Complete");
							break;
			case 17:	try {
							Tlist.load();
						} catch (Exception e) {
						System.out.println("Error Reading from file: " + e);
						}
						System.out.println("Loading booking details....");
						System.out.println("Load Complete");
						break;
			
			}
			System.out.println("\n Press any key to continue ...");
			option = RestaurantMenu();
			}
	}

	/**
	 * thats the driver where the program start to run
	 * @param args
	 */
	public static void main(String[] args) {
		Driver dr = new Driver();
		dr.runMenu();

	}
	
	
	private int validNextInt(String prompt) {
		do {
			try {
				System.out.print(prompt);
				return scan.nextInt();
			}
			catch (Exception e) { 
				scan.nextLine(); //swallows the buffer contents
				System.err.print("\tEnter a number please.");
			}
		}  while (true);

	}

}


