
public class Table {
	/**
	 * variable declaration
	 */
	private int tableNumber;
	private int numberOfSeats;
	public Table next=null;
	public BookingList book = new BookingList();
	
	/**
	 * constructor for the table
	 * @param tableNumber
	 * @param numberOfSeats
	 */
	public Table(int tableNumber,int numberOfSeats) {
		if(tableNumber<=30) {
			this.tableNumber = tableNumber;
		}else {
			System.out.println("please enter valid table number 1-30");
		}
		
		if(numberOfSeats<=30) {
			this.numberOfSeats = numberOfSeats;
		}else {
			System.out.println("there is no table for more than 30 people.sorry");
		}
		
	}
	
	/**
	 * getters and setters 
	 * @return
	 */
	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	
	
		
	

}
