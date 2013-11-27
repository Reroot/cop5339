package shoppingCart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A class that manages Product state.
 * 
 * Subject in Observer Pattern
 * 
 * @author Newman Souza
 * @author Seth Moore
 */
@SuppressWarnings("serial")
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
			BigDecimal invoicePrice, BigDecimal sellPrice, int quantity) {

		this.ID = ID;
		this.name = name;
		this.description = description;
		this.sellPrice = sellPrice;
		this.invoicePrice = invoicePrice;
		this.quantity = quantity;
		listeners = new ArrayList<>();

	}
	
	/**
	 * Updates the Product.
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
			BigDecimal invoicePrice, BigDecimal sellPrice, int quantity) {
		
		this.ID = ID;
		this.name = name;
		this.description = description;
		this.sellPrice = sellPrice;
		this.invoicePrice = invoicePrice;
		this.quantity = quantity;
		notifyListeners();
	}
	
	/**
	 * Increments the Product's quantity by 1.
	 * 
	 * @precondition none
	 * @postcondition quantity > 0
	 */
	public void increment(){
		quantity++;
		notifyListeners();
	}
	
	/**
	 * Decrements the Product's quantity by 1.
	 * 
	 * @precondition quantity > 0
	 * @postcondition quantity >= 0
	 */
	public void decrement(){
		quantity--;
		notifyListeners();
	}
	
	/**
	 * Getter for ID
	 * 
	 * @return the ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Getter for name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for description
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Getter for sellPrice
	 * 
	 * @return the sellPrice
	 */
	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	/**
	 * Getter for invoicePrice
	 * 
	 * @return the invoicePrice
	 */
	public BigDecimal getInvoicePrice() {
		return invoicePrice;
	}

	/**
	 * Getter for quantity
	 * 
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
	@Override
	public Object clone(){
		Product cloned = null;
		try {
			cloned = (Product)super.clone();
			cloned.quantity = 0;
			cloned.listeners = new ArrayList<>();
		} catch (CloneNotSupportedException e) {
		}
		return cloned;
	}
	
	/**
	 * Adds a ChangeListener to the ChangeListeners that will be notified
	 * whenever this Product changes state.
	 * 
	 * @param listener the ChangeListener to add
	 */
	public void addListener(ChangeListener listener){
		listeners.add(listener);
	}
	
	/**
	 * Calculates and returns the Product's hash code.
	 * 
	 * @return the Product's hash code.
	 */
	@Override
	public int hashCode() {
		int hash = 1;
		int prime = 31;
		return (hash * prime + ID);
	}

	/**
	 * Checks for equality between this Product and the obj parameter, and
	 * returns the result. Equality is based on ID.
	 * 
	 * @param obj the Object that is being compared to this Product.
	 * @return true if obj and this are equivalent (does not fields other
	 * than ID into account), otherwise false
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
		return true;
	}
	
	/**
	 * Notifies the listeners that the state has changed.
	 * 
	 * @precondition none
	 * @postcondition all ChangeListeners in listeners have been notified
	 * that the state has changed.
	 */
	private void notifyListeners(){
		for (ChangeListener listener : listeners){
			listener.stateChanged(new ChangeEvent(this));
		}
	}

	/**
	 * Removes all the ChangeListeners.
	 * 
	 * @postcondition no change listeners that had been added to this
	 * Product will receive notifications of this Product's state changes.
	 */
	public void removeListeners() {
		listeners.clear();
	}

	private int ID;
	private String name;
	private String description;
	private BigDecimal sellPrice;
	private BigDecimal invoicePrice;
	private int quantity;
	private transient ArrayList<ChangeListener> listeners;

}
