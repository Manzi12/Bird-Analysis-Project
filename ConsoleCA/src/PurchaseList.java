public class PurchaseList {
	Purchase head;
	Booking theBooking;
	
	/**
	 * constructor for purchaseList which takes in the booking as its variable
	 * @param theBooking
	 */
	public PurchaseList(Booking theBooking) {
		this.theBooking=theBooking;
	}
	
	
	/**
	 * function to add a purchase 
	 * @param item
	 * @param quantity
	 */
	public void addPurchase(String item,int quantity) {
		Menu theItem=Driver.mList.getMenuItemByName(item);
		if(theItem!=null) {
		Purchase purchase = new Purchase(theItem,quantity);
		purchase.next=head;
		head=purchase;
		
		}
		else {
			System.out.println("No such menu item.");
		}
	}
	
	
	/**
	 * function to calculate the sub total of all the purchases
	 * @return
	 */
	public int subTotalForPurchase() {
		Purchase purchase = head;
		int subTotal = 0;
		
		while(purchase != null) {
			int price = purchase.getItem().getItemPrice();
			int quantity = purchase.getQuantity();
			subTotal += price*quantity;
			purchase=purchase.next;
		
		}
		return subTotal;
	}
	
	
	
	/**
	 * Function to delete one single purchases
	 * @param item
	 */
	public void deletePurchase(String item) {
		Purchase temp = head,temp2=null;
		while(temp != null && temp.getItem().equals(item) ) {
			if(temp.getItem().getMenuItem() != item) { 
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
	 * Function to delete all Purchases
	 */
	public void deleteAllPurchases() {
		Purchase temp = head;
		head=null;
			System.out.println("successfully deleted all  purchases");
	}
	
	
	/**
	 * function to view the Purchase list
	 */
	public void viewPurchaseList() {
		Purchase temp=head;
		while(temp!=null) {
			System.out.println("Purchase details:" + "\n" +
					"\t" + "The item purchased  :" + temp.getItem().getMenuItem() + "\n" +
					"\t" + "The price of Item purchased:€ " + temp.getItem().getItemPrice() + "\n" +
 						"\t" + "You bought such quantity:"  + " "  + temp.getQuantity());
			temp=temp.next;
		}
	}

}
