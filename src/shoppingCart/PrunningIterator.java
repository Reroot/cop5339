package shoppingCart;

import java.util.Iterator;

public class PrunningIterator implements Iterator<Product> {

	public PrunningIterator(Iterator<Product> iter){
		this.iter = iter;
	}
	
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

	@Override
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	Iterator<Product> iter;
	Product nextProduct = null;
}
