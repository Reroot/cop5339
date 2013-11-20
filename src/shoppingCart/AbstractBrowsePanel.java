package shoppingCart;

import java.util.Iterator;

import javax.swing.JPanel;

/** A class that assembles Jpanels for the UI.
 *  @author Newman Souza
 *  @author Seth Moore
 */ 
@SuppressWarnings("serial")
public abstract class AbstractBrowsePanel extends JPanel {
	
    /** Constructs a AbstractBrowsePanel object.
     *  @precondition 		none
     *  @postcondition 		object created
     */
    public AbstractBrowsePanel() {
    	// TODO
    }

    /** Populates a list of products.
	 *  @param iterator		An Product iterator
	 *  @precondition 		iterator is a valid reference
	 *  @postcondition  	Product list populated
	 */
    public void populate(Iterator<Product> iterator) {
    	// TODO
    }
    
    /** Assembles a line for each Product in the Inventory.
	 *  @param product		The Product to be displayed in the line
	 *  @precondition 		product is a valid reference
	 *  @postcondition  	Line assembled
	 */
    public abstract void addLine(Product product);
    
}
