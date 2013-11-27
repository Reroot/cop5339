package shoppingCart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** A class that manages a list of products.
 * 
 * Subject in Observer Pattern
 * 
 *  @author Newman Souza
 *  @author Seth Moore
 */ 
@SuppressWarnings("serial")
public abstract class ProductList implements Iterable<Product>, Serializable{
	
    /** Constructs a ProductList object.
     *  @precondition none
     *  @postcondition object created
     */
    public ProductList() {
    	products = new ArrayList<>();
    	listeners = new ArrayList<>();
    }

    /** 
     * Removes all the Products from this ProductList.
     * 
     *  @precondition none
     *  @postcondition iterator().hasNext() == false
     */
    public void clear() {

    	products.clear();
    	notifyListeners();
    }

    /** 
     * Increments, by one, the quantity of a Product that equals the
     * supplied Product.
     * 
     *  @param the Product whose matching Product will be incremented.
     *  @precondition none
     *  @postcondition getMatchingProduct(product).getQuantity() > 0
     */
    public void increment(Product product) {

    	for (Product p : products) {
			if (p.equals(product)){
				p.increment();
		    	notifyListeners();
				return;
			}
		}
    	// If product wasn't already in this ProductList, create a clone
    	// (which will have quantity == 0), increment the clone, and add
    	// to this ProductList.
    	Product p = (Product)product.clone();
    	p.increment();
    	add(p);
    	notifyListeners();
    }

    /** 
     * Decrements, by one, the quantity of a Product that equals the
     * supplied Product.
     * 
     *  @param the Product whose matching Product will be decremented.
     *  @precondition getMatchingProduct(product) != null
     *  @precondition getMatchingProduct(product).getQuantity() > 0
     *  @postcondition getMatchingProduct(product).getQuantity() >= 0
     */
    public void decrement(Product product) {

    	for (Product p : products) {
			if (p.equals(product)){
				p.decrement();
				break;
			}
		}
    	notifyListeners();
    }
    
    /**
     * Gets the Product in this ProductList that equals the supplied Product,
     * if it exists, else returns null.
     * 
     * @param product the Product whose matching product will be retrieved
     * @return the matching Product, or null if matching product not found
     */
    public Product getMatchingProduct(Product product){
    	for (Product p : products) {
			if (p.equals(product)){
				return p;
			}
		}
		return null;
    }

    /** 
     * Adds a copy (including quantity) of the supplied Product to the
     * ProductList.
     *  
     *  @param product the Product whose copy will be added to this ProductList
     *  
     *  @precondition getMatchingProduct(product) == null
     *  @postcondition getMatchingProduct(product).equals(product) == true
     *  @postcondition getMatchingProduct(product).getQuantity() == product.getQuantity()
     */
    public void add(Product product) {

    	Product p = new Product(product.getID(),
    							product.getName(),
    							product.getDescription(),
    							product.getInvoicePrice(),
    							product.getSellPrice(),
    							product.getQuantity());
    	products.add(p);
    	notifyListeners();
    }

    /** 
     * Removes a Product that matches supplied Product from the ProductList
     * 
     *  @param product whose match will be removed from this ProductList
     *  @precondition getMatchingProduct(product) != null 
     *  @postcondition getMatchingProduct(product) == null
     */
    public void remove(Product product) {

    	Iterator<Product> it = products.iterator();
    	while (it.hasNext()) {
			Product p = (Product) it.next();
			if (p.equals(product)){
				it.remove();
			}
		}
    	notifyListeners();
    }
    
    /**
     * Gets an iterator over the Products in this ProductList.
     * 
     * @return an iterator over the Products in an umodifiableList
     * created from the Products in this ProductList
     */
    public Iterator<Product> iterator() {
		return Collections.unmodifiableList(products).iterator();
    }
    
    /**
	 * Adds a ChangeListener to the ChangeListeners that will be notified
	 * whenever this ProductList changes state.
	 * 
	 * @param listener the ChangeListener to add
	 */
	public void addListener(ChangeListener listener){
		listeners.add(listener);
	}
	
	/**
	 * Notifies the listeners that the state has changed.
	 * 
	 * @precondition none
	 * @postcondition all ChangeListeners in listeners have been notified
	 * that the state has changed.
	 */
	protected void notifyListeners(){
		for (ChangeListener listener : listeners){
			listener.stateChanged(new ChangeEvent(this));
		}
	}

    private ArrayList<Product> products;
    private transient ArrayList<ChangeListener> listeners;
}
