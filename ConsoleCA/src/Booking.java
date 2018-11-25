
public class Booking {
	
	/**
	 * variables declaration
	 */
	//private static int bid=1;
	Booking next;
	String nameOfPerson;
	int totalNumberOfPeople;
	int tableNumberBooked;
	int time ;
	int numberOfHoursBooked;
	int bookingId;
	
	/**
	 * object for the purchase list
	 */
	PurchaseList pList = new PurchaseList(this);
	
	/**
	 * constructor for booking
	 * @param bookingId
	 * @param nameOfPerson
	 * @param totalNumberOfPeople
	 * @param tableNumberBooked
	 * @param time
	 * @param numberOfHoursBooked
	 */
	public Booking(int bookingId,String nameOfPerson,int totalNumberOfPeople,int tableNumberBooked,int time,int numberOfHoursBooked) {
//		bookingId=bid;
//		bid++;
		
		/**
		 * validation for the booking id
		 */
		if((bookingId >= 100) && (bookingId<=500)) {
		this.bookingId = bookingId;
		}else {
			System.out.println("invalid Booking Id,100-500 booking id.");
		}
		
		/**
		 * validation for the name of the person
		 */
		if(nameOfPerson.length()<=20) {
			this.nameOfPerson = nameOfPerson;
		}else {
			nameOfPerson.substring(0, 20);
		}
		
		
		/**
		 * validation for the total number of people in the table
		 */
		if(totalNumberOfPeople<=30) {
			this.totalNumberOfPeople = totalNumberOfPeople;
		}else {
			System.out.println("can't be more than 30 people");
		}
		
		/**
		 * validation on the table number booked
		 */
		if(tableNumberBooked<=30) {
			this.tableNumberBooked = tableNumberBooked;
		}else {
			System.out.println("table selected does not exist, 1-30 tables");
		}
		
		
		/**
		 * validation on time 
		 */
		if((time>=13) && (time<=23)) {
			this.time = time;
		}else {
			System.out.println("Invalid time, 13-23 normal time.");
		}
		
		
		/**
		 * validation on the number of hours the table has been booked
		 */
		if(numberOfHoursBooked<=2) {
		this.numberOfHoursBooked = numberOfHoursBooked;
		}else {
			System.out.println("sorry you can't spend more than two hours on table");
		}
			
	}
	
	/**
	 * getters and setters
	 * @param bookingId
	 */
	public void setBookingId(int bookingId) {
		if((bookingId >= 100) && (bookingId<=500)) {
			this.bookingId = bookingId;
			}else {
				System.out.println("invalid Booking Id,100-500 booking id.");
			}
	}
	
	public int getBookingId() {
		return bookingId;
	}
	
	/**
	 * validation on the name of the person booking
	 * @param nameOfPerson
	 */
	public void setNameOfPerson(String nameOfPerson) {
		if(nameOfPerson.length()<=20) {
			this.nameOfPerson = nameOfPerson;
		}else {
			nameOfPerson.substring(0, 20);
		}
	}
	
	public String getNameOfPerson() {
		return nameOfPerson;
	}

	public int getTotalNumberOfPeople() {
		return totalNumberOfPeople;
	}

	/**
	 * validation on total number of people on the table
	 * @param totalNumberOfPeople
	 */
	public void setTotalNumberOfPeople(int totalNumberOfPeople) {
		if(totalNumberOfPeople<=30) {
			this.totalNumberOfPeople = totalNumberOfPeople;
		}else {
			System.out.println("can't be more than 30 people");
		}
	}

	public int getTableNumberBooked() {
		return tableNumberBooked;
	}
/**
 * validation of the table number booked 
 * @param tableNumberBooked
 */
	public void setTableNumberBooked(int tableNumberBooked) {
		if(tableNumberBooked<=30) {
			this.tableNumberBooked = tableNumberBooked;
		}else {
			System.out.println("table selected does not exist, 1-30 tables");
		}
	}

	public int getTime() {
		return time;
	}
/**
 * validation on time when setting it
 * @param time
 */
	public void setTime(int time) {
		if(time==13 || time>=23) {
			this.time = time;
		}else {
			System.out.println("Invalid time.we open at 13 pm to 23");
		}
	}

	public int getNumberOfHoursBooked() {
		return numberOfHoursBooked;
	}

	/**
	 * validation of number of hours booked
	 * @param numberOfHoursBooked
	 */
	public void setNumberOfHoursBooked(int numberOfHoursBooked) {
		if(numberOfHoursBooked<=2) {
			this.numberOfHoursBooked = numberOfHoursBooked;
			}else {
				System.out.println("sorry you can't spend more than two hours on table");
			}
	}
	
	
	/**
	 * the to string to display everything superb
	 */
	public String toString() {
		return "Table booked by" + nameOfPerson +
				"Number of people on the table" + totalNumberOfPeople +
				"Number of table Booked" + tableNumberBooked +
				"Time Booled" + time +
				"Number of hour the table has been booked" + numberOfHoursBooked;
	}

}
