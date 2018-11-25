

public class Menu {
	
	/**
	 * variable declaration
	 */
	String menuItem;
	int itemPrice;
	public Menu next = null;
	
	/**
	 * Constructor for the menu
	 * @param menuItem
	 * @param itemPrice
	 */
	public Menu(String menuItem,int itemPrice) {
		this.menuItem = menuItem;
		this.itemPrice = itemPrice;
	}

	/**
	 * getters and setters
	 * @return
	 */
	public String getMenuItem() {
		return menuItem;
	}


	public void setMenuItem(String menuItem) {
		this.menuItem = menuItem;
	}


	public int getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	

   
}
