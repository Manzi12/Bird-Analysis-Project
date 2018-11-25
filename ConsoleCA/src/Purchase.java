

public class Purchase {
	
	/**
	 * variable declaration
	 */
	private int quantity;
	private Menu item;
	public Purchase next = null;
	
	/**
	 * purchase constructor
	 * @param item
	 * @param quantity
	 */
	public Purchase(Menu item,int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	/**
	 * Getters and setters for item and quantity
	 * @param item
	 */
	
	public void setItem(Menu item) {
		this.item = item;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Menu getItem() {
		return item;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
}
