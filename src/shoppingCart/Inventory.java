package shoppingCart;

import java.math.BigDecimal;

/**
 * A ProductList that manages the Products in the Seller's inventory and the
 * seller's financial state.
 * 
 * @author Seth Moore
 *
 */
@SuppressWarnings("serial")
public class Inventory extends ProductList {
	
	/**
	 * Constructor
	 */
	public Inventory(){
		super();
		costs = new BigDecimal("0.00");
		revenues = new BigDecimal("0.00");
	}
	
	/**
	 * Accessor method for costs
	 * 
	 * @return the seller's costs
	 */
	public BigDecimal getCosts(){
		return costs;
	}
	
	/**
	 * Accessor method for revenues
	 * 
	 * @return the seller's revenues
	 */
	public BigDecimal getRevenues(){
		return revenues;
	}
	
	/**
	 * Calculates and returns the seller's profits, based on revenues - costs
	 * 
	 * @return the seller's profits
	 */
	public BigDecimal getProfits(){
		return revenues.subtract(costs);
	}
	
	/**
	 * Increments, by one, the quantity of a Product that equals the
     * supplied Product, and increases revenues by the Product's sellPrice.
     * 
     * @param product the Product whose matching Product will be incremented.
     * @precondition none
     * @postcondition getMatchingProduct(product).getQuantity() > 0
	 */
	public void decrement(Product product){
		super.decrement(product);
		revenues.add(product.getSellPrice());
	}
	
	/**
	 * Decrements, by one, the quantity of a Product that equals the
     * supplied Product, and decreases revenues by the Product's sellPrice.
     * 
     *  @param the Product whose matching Product will be decremented.
     *  @precondition getMatchingProduct(product) != null
     *  @precondition getMatchingProduct(product).getQuantity() > 0
     *  @postcondition getMatchingProduct(product).getQuantity() >= 0
	 */
	public void increment(Product product){
		super.increment(product);
		revenues.subtract(product.getSellPrice());
	}

	private BigDecimal costs;
	private BigDecimal revenues;
}