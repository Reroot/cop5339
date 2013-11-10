package shoppingCart;

import java.util.ArrayList;
import java.util.Iterator;

/** A class that manages a list of products.
 *  @author Newman Souza
 *  @author Seth Moore
 */ 
public abstract class ProductList {
	
    /** Constructs a ProductList object.
     *  @precondition none
     *  @postcondition object created
     */
    public ProductList() {
    	products = new ArrayList<>();
    }

    /** Clears the state of the cart.
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void clear() {

    	// code
    	
    }

    /** Increments the quantity of a Product in the cart.
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void increment(Product product) {

    	// code
    	
    }

    /** Decrements the quantity of a Product in the cart.
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void decrement(Product product) {

    	// code
    	
    }

    /** Adds a Product to the ProductList.
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void add(Product product) {

    	// code
    	
    }

    /** Removes a Product from the ProductList
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void remove(Product product) {

    	// code
    	
    }
    
    /**
     * 
     * @return
     */
    public Iterator<Product> iterator(){
    	return products.iterator();
    }

    private ArrayList<Product> products;
}
