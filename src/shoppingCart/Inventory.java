package shoppingCart;

import java.math.BigDecimal;
import java.util.Iterator;

/**
 * A ProductList that manages the Products in the Seller's inventory and the
 * seller's financial state.
 * 
 * @author Seth Moore
 * @author Newman Souza
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
	
	/**
	 * The accessor method for obtaining the single (there can
	 * only be one) instance of Inventory. 
	 * 
	 * @return the Inventory instance
	 */
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

	// TODO DELETE
	public void setCosts(BigDecimal costs){
		this.costs = costs;
	}
	
	/**
	 * Accessor method for revenues
	 * 
	 * @return the seller's revenues
	 */
	public BigDecimal getRevenues(){
		return revenues;
	}

	// TODO DELETE
	public void setRevenues(BigDecimal revenues){
		this.revenues = revenues;
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
	 * Decrements, by one, the quantity of a Product that equals the
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
	 * Increments, by one, the quantity of a Product that equals the
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
     * Creates and returns a new integer that is equal to p.getID()+1,
     * where p is the Product in Inventory with greatest ID.
     * 
     * @return the largest ID in the inventory plus 1.
     */
    public int getNewID() {
    	int newID = 0;
    	Iterator<Product> iterator = iterator();
		while (iterator.hasNext()) {
			Product p = (Product) iterator.next();
			if (p.getID() > newID) {
				newID = p.getID();
			}
		}
		return (newID + 1);
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
		costs = costs.add(product.getInvoicePrice().multiply(BigDecimal.valueOf(product.getQuantity())));
		super.add(product);
	}
	
	/**
	 * Updates all the fields of the product in Inventory whose ID matches
	 * the ID of product to match the fields in product. If product's quantity
	 * is greater than the quantity of the match, costs is increased by the
	 * difference times the InvoicePrice of product.
	 * 
	 * @param product the Product whose match will be found and updated
	 * @precondition getMatchingProduct(product) != null
	 */
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
	
	/**
	 * Removes all the Products in Inventory, and sets costs and revenues to 0.00
	 * 
	 * @postcondition iterater().hasNext == false
	 * @postcondition getCosts().equals(new BigDecimal("0.00")
	 * @postcondition getRevenues().equals(new BigDecimal("0.00")
	 */
	public void clear() {
		super.clear();
		costs = new BigDecimal("0.00");
		revenues = new BigDecimal("0.00");
	}
	
	/**
	 * This method makes the Singleton Pattern play nicely with
	 * deserialization in our application. Since deserialization creates
	 * a new object, we clear the old instance, and add all the Products
	 * from the deserialized object (this) to the instance, then return
	 * the instance, rather than this.
	 * 
	 * @return the Inventory instance
	 */
	private Object readResolve(){
		if (instance == null){
			instance = this;
		}
		else{
			instance.clear();
			for (Product p : this) {
				instance.add(p);
			}
			instance.costs = this.costs;
			instance.revenues = this.revenues;
		}
		instance.notifyListeners();
		return instance;
	}
	
	private BigDecimal costs;
	private BigDecimal revenues;
	private static Inventory instance = null;
}
