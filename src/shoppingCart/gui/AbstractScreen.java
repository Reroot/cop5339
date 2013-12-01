package shoppingCart.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import shoppingCart.model.Product;

/** 
 * A class that assembles Jpanels for the UI.
 * AbstractClass in Template Method Pattern
 * 
 * @author Newman Souza
 * @author Seth Moore
 */ 
@SuppressWarnings("serial")
public abstract class AbstractScreen extends JPanel {
	
    /**
	 * Constructs a AbstractScreen object.
	 * 
	 * @param ui 			a reference to the UI that created this screen.
	 * @precondition 		none
     * @postcondition 		object created
	 */
    public AbstractScreen(UI ui) {
    	super();
    	headerPanel = new JPanel();
    	browsePanel = new JPanel();
    	//sidePanel = new JPanel(); // Moved creation into createSidePanel().
    	this.ui = ui; 
    }

    /**
     * Calls methods that fill Header, Browse, and Side panels with components and listeners.
     * This is a Template Method in the Template Method Pattern, 
     * with createheaderPanel() and createSidePanel() being abstract primitive methods.
     */
    public void createScreen() {
    	
    	this.setLayout(new BorderLayout());
    	createHeaderPanel();
    	createBrowsePanel();
    	createSidePanel();
		
    }

    /**
     * Sets up the BrowsePanel, in preparation for being populated
     * with lines of Product information.
     */
	public void createBrowsePanel() {
		GridLayout grid = new GridLayout();
		grid.setColumns(1); 
		browsePanel.setLayout(grid);
		JScrollPane scrollPane = new JScrollPane(browsePanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(12);
		this.add(scrollPane, BorderLayout.CENTER);
	}
	
    /** Populates a browsePanel with a list of products.
     * This is another Template Method, with addLine() being
     * the abstract primitive method.
     * 
	 *  @param iterator		An Product iterator
	 *  @precondition 		iterator is a valid reference
	 *  @postcondition  	browsePanel is populated with lines of Products
	 */
    public void populate(Iterator<Product> iterator) {
    	browsePanel.removeAll();
    	while (iterator.hasNext()) {
        	Product product = iterator.next();
        	product.removeListeners();
        	browsePanel.add(addLine(product));
    	}
    }
    
    /**
     * Shows the user a pre-populated product form, which is
     * editable for a seller.
     * 
     * @param product 		the Product whose information will be
     * 						displayed in the product form.
     */
    public abstract void displayProductForm(Product product);
    
    /**
     * Primitive method called by createScreen() to create the appropriate header
     * for the sub-class
     */
    public abstract void createHeaderPanel();
    
    /**
     * Primitive method called by createScreen() to create the appropriate side panel
     * for the sub-class
     */
    public abstract void createSidePanel();

    /** Primitive method called by populate(). Assembles a line for
     * 	each Product in the Inventory.
     * 
	 *  @param product		The Product to be displayed in the line
	 *  @return 			a JPanel containing the appropriate components/listeners
	 *  @precondition 		product is a valid reference
	 *  @postcondition  	Line assembled
	 */
    public abstract JPanel addLine(Product product);

    protected JPanel headerPanel;
    protected JPanel browsePanel;
    protected JPanel sidePanel;
    protected UI ui;
}
