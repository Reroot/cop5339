package shoppingCart;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.swing.event.ChangeListener;

/**
 * A class that manages Product state.
 * 
 * @author Seth Moore and Newman Souza
 */
public class Product implements Cloneable, Serializable{

	/**
	 * Constructs a Product object.
	 * 
	 * @param ID
	 *            Product's ID
	 * @param name
	 *            Product's name
	 * @param description
	 *            Product's description
	 * @param sellPrice
	 *            Product's selling price
	 * @param invoicePrice
	 *            Product's invoice price
	 * @param quantity
	 *            Product's quantity
	 * @precondition none
	 * @postcondition object created
	 */
	public Product(int ID, String name, String description,
			BigDecimal sellPrice, BigDecimal invoicePrice, int quantity) {

		this.ID = ID;
		this.name = name;
		this.description = description;
		this.sellPrice = sellPrice;
		this.invoicePrice = invoicePrice;
		this.quantity = quantity;

	}
	
	/**
	 * Updates the product.
	 * 
	 * @param ID
	 *            Product's ID
	 * @param name
	 *            Product's name
	 * @param description
	 *            Product's description
	 * @param sellPrice
	 *            Product's selling price
	 * @param invoicePrice
	 *            Product's invoice price
	 * @param quantity
	 *            Product's quantity
	 * @precondition none
	 * @postcondition 	All the Product's fields have been
	 * 					updated to the supplied parameters. 
	 */
	public void update(int ID, String name, String description,
			BigDecimal sellPrice, BigDecimal invoicePrice, int quantity) {
		
		this.ID = ID;
		this.name = name;
		this.description = description;
		this.sellPrice = sellPrice;
		this.invoicePrice = invoicePrice;
		this.quantity = quantity;
		
		// TODO: Notify change listeners
	}
	
	/**
	 * Increments the Product's quantity by 1.
	 * 
	 * @precondition none
	 * @postcondition quantity > 0
	 */
	public void increment(){
		quantity++;
	}
	
	/**
	 * Decrements the Product's quantity by 1.
	 * 
	 * @precondition quantity > 0
	 * @postcondition quantity >= 0
	 */
	public void decrement(){
		quantity--;
	}
	
	/**
	 * @return the ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the sellPrice
	 */
	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	/**
	 * @return the invoicePrice
	 */
	public BigDecimal getInvoicePrice() {
		return invoicePrice;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Creates and returns an Object whose fields match the fields of this
	 * Product, but with quantity set to 0.
	 * 
	 * @return the clone of this object.
	 * @precondition none
	 */
	public Object clone(){
		Product cloned = null;
		try {
			cloned = (Product)super.clone();
//			cloned.ID = ID;
//			cloned.name = name;
//			cloned.description = description;
//			cloned.sellPrice = sellPrice;
//			cloned.invoicePrice = invoicePrice;
			cloned.quantity = 0;
		} catch (CloneNotSupportedException e) {
		}
		return cloned;
	}
	
	/**
	 * 
	 * @param listener
	 */
	public void addListener(ChangeListener listener){
		// TODO: Implement addListener.
		throw new UnsupportedOperationException("addListener is not yet supported");
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((invoicePrice == null) ? 0 : invoicePrice.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((sellPrice == null) ? 0 : sellPrice.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Product)) {
			return false;
		}
		Product other = (Product) obj;
		if (ID != other.ID) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (invoicePrice == null) {
			if (other.invoicePrice != null) {
				return false;
			}
		} else if (!invoicePrice.equals(other.invoicePrice)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (sellPrice == null) {
			if (other.sellPrice != null) {
				return false;
			}
		} else if (!sellPrice.equals(other.sellPrice)) {
			return false;
		}
		return true;
	}

	private int ID;
	private String name;
	private String description;
	private BigDecimal sellPrice;
	private BigDecimal invoicePrice;
	private int quantity;

}
