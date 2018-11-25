public class BookingList {
	
	Booking head=null;
	
	
	/**
	 * this method will provide a view for the booking after booking a table
	 */
	public void viewBookingList() {
		Booking temp=head;
		while(temp!=null) {
			System.out.println("Booking details: Table "+ " " + temp.tableNumberBooked+ "\n" + 
														"\t" + "Booking ID :" + " " + temp.bookingId + "\n" +
														"\t" + "Name:" + " " + temp.nameOfPerson + "\n" +
														"\t" +"Namber of people:" +" " + temp.totalNumberOfPeople + "\n" +
														"\t" +"Time:" +" " + temp.time +"\n" +
														"\t" +"Number of hours booked:" +" " + temp.numberOfHoursBooked);
			
			temp.pList.viewPurchaseList();
			temp=temp.next;
			
		}
	}
	
	
	/**
	 * this method will allow you to delete a booking
	 * @param id
	 */
	public void deleteBooking(int id) {
		Booking temp = head,temp2=null;
		System.out.println("Deleting Booking "+id+"...");
		while(temp != null) {
			if(temp.getBookingId() != id) { 
				temp2=temp;
				temp=temp.next; 
				}
			else 
			{	//found item to delete	
 				if(temp2!=null) 
 					temp2.next=temp.next;	
 				else 
 					head=temp.next;			
 				
 				System.out.println("Booking found and deleted");
 				return;
			}
		}
		System.out.println("Booking not found");
	}
	
	
	/**
	 * this method will help you search for the booking
	 * @param id
	 * @return
	 */
	public Booking getBookingById(int id) {
		Booking temp = head;
		while(temp!=null && temp.getBookingId()!=id)
			temp=temp.next;
		if(temp != null) return temp;   //found it return
		return null; 	//wasn't found return null
	}
	
	
	/**
	 * this method will help solve time problem
	 */
	public void timeCheck() {
		Booking temp = head;
		int time = 0;
		while(temp != null) {
			if(temp.getTime()==23) {
				temp.setNumberOfHoursBooked(1);
			}else {
				time = temp.getTime()+temp.getNumberOfHoursBooked();
				temp=temp.next;
			}
		}
	}
	
		
	}


