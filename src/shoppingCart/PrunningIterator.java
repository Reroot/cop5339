package shoppingCart;

import java.util.Iterator;

/**
 * A decorator for a Iterator<Product>, which weeds out Products
 * that have quantity < 1.
 * 
 * @author Seth Moore
 * @author Newman Souza
 */
public class PrunningIterator implements Iterator<Product> {

	/**
	 * PrunningIterator constructor.
	 * 
	 * @param iter the Iterator<Product> being decorated
	 */
	public PrunningIterator(Iterator<Product> iter){
		this.iter = iter;
	}
	
	/**
	 * Checks the decorated iterator for the next Product with
	 * quantity > 0, and returns true if one is found, otherwise
	 * returns false.
	 * 
	 * @return answer to whether there is another Product with quantity > 0
	 */
	@Override
	public boolean hasNext() {
		if (nextProduct != null){
			return true;
		}
		while (iter.hasNext()){
			Product p = iter.next();
			if (p.getQuantity() > 0){
				nextProduct = p;
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the next Product whose quantity > 0 from the
	 * decorated iterator.
	 * 
	 * @return the next Product whose quantity > 0
	 * @precondition hasNext() == true
	 */
	@Override
	public Product next() {
		if (nextProduct != null){
			Product temp = nextProduct;
			nextProduct = null;
			return temp;
		}
		else if (hasNext()){
			Product temp = nextProduct;
			nextProduct = null;
			return temp;
		}
		else{
			return null;
		}
	}

	/**
	 * remove() is not supported.
	 */
	@Override
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	Iterator<Product> iter;
	Product nextProduct = null;
}
