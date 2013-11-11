package shoppingCart;

import java.math.BigDecimal;

/**
 * A ProductList that manages the Products in a customer's shopping cart.
 * 
 * @author Seth Moore
 *
 */
@SuppressWarnings("serial")
public class Cart extends ProductList {
	
	/**
	 * Gets the Cart's total, which is the sum of the sell price times quantity
	 * of each Product in the cart.
	 * 
	 * @return the Cart's total
	 */
	public BigDecimal getTotal(){
		BigDecimal total = new BigDecimal("0.00");
		for (Product p : this) {
			total = total.add(p.getSellPrice().multiply(BigDecimal.valueOf(p.getQuantity())));
		}
		return total;
	}
	
	/**
	 * Gets the sum of each Product's quantity.
	 * 
	 * @return the number of items in the Cart.
	 */
	public int getQuantity(){
		int quantity = 0;
		for (Product p : this) {
			quantity += p.getQuantity();
		}
		return quantity;
	}

}
