package shoppingCart;

<<<<<<< HEAD

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
=======
import java.awt.BorderLayout;
import java.awt.Dimension;
>>>>>>> daf9ae94c3714f8630d79f653ccca67b60b6b855
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
<<<<<<< HEAD
    public AbstractScreen(UI ui) {
    	super();
    	headerPanel = new JPanel();
    	browsePanel = new JPanel();
    	sidePanel = new JPanel();
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
	
=======
    public AbstractScreen() {
    	// TODO
    	// super();
    }

    public void createScreen() {
		JPanel customerScreen = new JPanel();
    	customerScreen.setLayout(new BorderLayout());
    	
    	customerBrowsePanel = new CustomerScreen();
//        	customerBrowsePanel.setPreferredSize(new Dimension(300, 350));
    	customerBrowsePanel.setBorder(new EtchedBorder());
    	JScrollPane scrollPane = new JScrollPane(customerBrowsePanel);
//        	customerScreen.add(customerBrowsePanel, BorderLayout.CENTER);
    	customerScreen.add(scrollPane, BorderLayout.CENTER);
    	
    	JPanel sidePanel = new JPanel();
    	sidePanel.setPreferredSize(new Dimension(200, 500));
    	sidePanel.setBorder(new EtchedBorder());
    	customerScreen.add(sidePanel, BorderLayout.LINE_END);
    	JLabel costsLabel = new JLabel("Costs:" + inventory.getCosts());
    	JLabel revenuesLabel = new JLabel("Revenues:" + inventory.getRevenues());
    	sidePanel.add(costsLabel);
    	sidePanel.add(revenuesLabel);
    	JButton checkoutButton = new JButton("Checkout");
    	checkoutButton.addMouseListener(new
    			MouseAdapter(){
    				public void mouseClicked(MouseEvent e){
    					displayCheckoutScreen();
    				}
    			}
    		);
    	sidePanel.add(checkoutButton);
		return customerScreen;

		this.add(createHeaderPanel());
		
    	   // add header
    	// create sidepanel
    	   // add sidepanel
    	// create browsepanel
    	  // populate
    }
    
>>>>>>> daf9ae94c3714f8630d79f653ccca67b60b6b855
    /** Populates a list of products.
	 *  @param iterator		An Product iterator
	 *  @precondition 		iterator is a valid reference
	 *  @postcondition  	Product list populated
	 */
    public void populate(Iterator<Product> iterator) {
<<<<<<< HEAD
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

=======
    	removeAll();
    	while (iterator.hasNext()) {
        	Product product = iterator.next();
        	this.addLine(product);
    	}
    }
    
>>>>>>> daf9ae94c3714f8630d79f653ccca67b60b6b855
    /** Assembles a line for each Product in the Inventory.
	 *  @param product		The Product to be displayed in the line
	 *  @precondition 		product is a valid reference
	 *  @postcondition  	Line assembled
	 */
<<<<<<< HEAD
    public abstract JPanel addLine(Product product);

    protected JPanel headerPanel;
    protected JPanel browsePanel;
    protected JPanel sidePanel;
    protected UI ui;
=======
    public abstract void addLine(Product product);
    
    
    public void createPanel() {
    	createHeaderPanel("Customer");
    }
    
    public abstract JPanel createHeaderPanel();
    public abstract JPanel createBrowsePanel();
    public abstract JPanel createSidePanel();
    
//    /** The panel that shows a list of products for Seller, Customer and Checkout screens. */
//    private JPanel browsePanel;
    
>>>>>>> daf9ae94c3714f8630d79f653ccca67b60b6b855
}
