package shoppingCart;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

/** A class that assembles Jpanels for the UI.
 *  @author Newman Souza
 *  @author Seth Moore
 */ 
@SuppressWarnings("serial")
public abstract class AbstractScreen extends JPanel {
	
    /** Constructs a AbstractBrowsePanel object.
     *  @precondition 		none
     *  @postcondition 		object created
     */
    public AbstractScreen(UI ui) {
    	super();
    	headerPanel = new JPanel();
    	browsePanel = new JPanel();
    	//sidePanel = new JPanel(); // Moved creation into createSidePanel().
    	this.ui = ui; 
    }

    public void createScreen() {
    	
    	this.setLayout(new BorderLayout());
    	createHeaderPanel();
    	createBrowsePanel();
    	createSidePanel();
		
    }

	public void createBrowsePanel() {
//    	browsePanel.setPreferredSize(new Dimension(400, 500));
    	browsePanel.setBorder(new EtchedBorder());
		GridLayout grid = new GridLayout();
		grid.setColumns(1); 
		// TODO change 50 to something else
		grid.setRows(50);
		browsePanel.setLayout(grid);
		JScrollPane scrollPane = new JScrollPane(browsePanel);
		this.add(scrollPane, BorderLayout.CENTER);
	}
	
    /** Populates a list of products.
	 *  @param iterator		An Product iterator
	 *  @precondition 		iterator is a valid reference
	 *  @postcondition  	Product list populated
	 */
    public void populate(Iterator<Product> iterator) {
    	browsePanel.removeAll();
    	while (iterator.hasNext()) {
        	Product product = iterator.next();
        	browsePanel.add(addLine(product));
    	}
    }
    
    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displayProductForm() {
    	// TODO
    }

    public abstract void createHeaderPanel();
    public abstract void createSidePanel();

    /** Assembles a line for each Product in the Inventory.
	 *  @param product		The Product to be displayed in the line
	 *  @precondition 		product is a valid reference
	 *  @postcondition  	Line assembled
	 */
    public abstract JPanel addLine(Product product);

    protected JPanel headerPanel;
    protected JPanel browsePanel;
    protected JPanel sidePanel;
    protected UI ui;
}
