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
	private Inventory(){
		super();
		costs = new BigDecimal("0.00");
		revenues = new BigDecimal("0.00");
	}
	
	public static Inventory getInstance(){
		if (instance == null){
			instance = new Inventory();
		}
		return instance;
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
		revenues = revenues.add(product.getSellPrice());
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
		revenues = revenues.subtract(product.getSellPrice());
	}
	
	/**
	 * Adds a copy (including quantity) of the supplied Product to the
     * ProductList, and increases costs by the invoicePrice times quantity
     * of the Product.
     *  
     *  @param product the Product whose copy will be added to this ProductList
     *  
     *  @precondition getMatchingProduct(product) == null
     *  @postcondition getMatchingProduct(product).equals(product) == true
     *  @postcondition getMatchingProduct(product).getQuantity() == product.getQuantity()
	 */
	@Override
	public void add(Product product){
		super.add(product);
		costs = costs.add(product.getInvoicePrice().multiply(BigDecimal.valueOf(product.getQuantity())));
	}
	
	public void update(Product product){
		Product p = getMatchingProduct(product);
		int oldQuantity = p.getQuantity();
		int newQuantity = product.getQuantity();
		if (oldQuantity < newQuantity){
			costs = costs.add(product.getInvoicePrice().multiply(BigDecimal.valueOf(newQuantity - oldQuantity)));
		}
		p.update(product.getID(), product.getName(), product.getDescription(), product.getSellPrice(), product.getInvoicePrice(), product.getQuantity());
		notifyListeners();
	}
	
		private BigDecimal costs;
	private BigDecimal revenues;
	private static Inventory instance = null;
}
