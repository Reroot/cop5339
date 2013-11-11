package shoppingCart;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class Inventory extends ProductList {
	
	public Inventory(){
		super();
		costs = new BigDecimal("0.00");
		revenues = new BigDecimal("0.00");
	}
	
	public BigDecimal getCosts(){
		return costs;
	}
	
	public BigDecimal getRevenues(){
		return revenues;
	}
	
	public BigDecimal getProfits(){
		return revenues.subtract(costs);
	}
	
	public void decrement(Product product){
		super.decrement(product);
		revenues.add(product.getSellPrice());
	}
	
	public void increment(Product product){
		super.increment(product);
		revenues.subtract(product.getSellPrice());
	}

	private BigDecimal costs;
	private BigDecimal revenues;
}
