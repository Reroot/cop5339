package shoppingCart.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import shoppingCart.model.Cart;
import shoppingCart.model.Inventory;
import shoppingCart.model.Product;
import shoppingCart.model.PrunningIterator;
import shoppingCart.system.CartSystem;

/** A class that manages interaction with user, receives input and displays screens.
 *  @author Newman Souza
 *  @author Seth Moore
 */ 
@SuppressWarnings("serial")
public class UI extends JFrame{
	
	final static String LOGINPANEL = "LoginScreen";
	final static String CUSTOMERPANEL = "CustomerScreen";
	final static String SELLERPANEL = "SellerScreen";
	final static String CHECKOUTPANEL = "CheckoutScreen";
	
    /** Constructs a UI object (as well as all its screens),
     *  and sets itself visible.
     *  
     *  @precondition none
     *  @postcondition LoginScreen is displayed
     */
    public UI(CartSystem cartSystem) {
    	super("Shopping Cart");
    	this.cartSystem = cartSystem;
    	inventory = Inventory.getInstance();
    	cart = Cart.getInstance();
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setPreferredSize(new Dimension(900, 600));
    	screenCards = new JPanel(new CardLayout());
    	
    	JPanel loginScreen = createLoginScreen();
    	screenCards.add(loginScreen, LOGINPANEL);

    	customerScreen = new CustomerScreen(this);
    	customerScreen.createScreen();
    	screenCards.add(customerScreen, CUSTOMERPANEL);
    	
    	sellerScreen = new SellerScreen(this);
    	sellerScreen.createScreen();
    	screenCards.add(sellerScreen, SELLERPANEL);
    	
    	checkoutScreen = new CheckoutScreen(this);
    	checkoutScreen.createScreen();
    	screenCards.add(checkoutScreen, CHECKOUTPANEL);
    	
    	add(screenCards);
    	   	
    	pack();
    	setLocationRelativeTo(null);
    	setVisible(true);
    	
    	displayLoginScreen();
    }

    /**
     * Switches to the LoginScreen.
     */
    public void displayLoginScreen() {
    	((CardLayout)screenCards.getLayout()).show(screenCards, LOGINPANEL);
    }

    /**
     * Constructs the LoginScreen.
     * 
     * @return the LoginScreen.
     */
    private JPanel createLoginScreen() {
       	JPanel loginScreen = new JPanel();
		loginScreen.setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
    	JLabel label = new JLabel("Login Screen");
		label.setFont(label.getFont().deriveFont(16.0f));
		topPanel.add(label);
		topPanel.setBorder(new EtchedBorder());
		loginScreen.add(topPanel, BorderLayout.NORTH);

		final JTextField usernameField = new JTextField(10);
		final JPasswordField passwordField = new JPasswordField(10);
    	JButton loginButton = new JButton("Login");
    	loginButton.addActionListener(new
    			ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    					String userType = cartSystem.login(usernameField.getText(), String.valueOf(passwordField.getPassword()));
    					if (userType == null){
    						JOptionPane.showMessageDialog(screenCards, "Invalid username / password.\n\nPlease try again.\n");
    						usernameField.setText("");
    						passwordField.setText("");
    					}
    					else if (userType.equals("Customer")) {
    						cart.clear();
    						displayCustomerScreen();
    					}
    					else if (userType.equals("Seller")){
    						displaySellerScreen();
    					}
    					
    				};
    			}
   		);
    	
       	JPanel centerPanel = new JPanel();
       	centerPanel.setMaximumSize(new Dimension(200, 300));
		centerPanel.setLayout(new GridBagLayout());
    	GridBagConstraints loginC = new GridBagConstraints();
    	label = new JLabel();
    	label.setText("Username:");
    	loginC.anchor = GridBagConstraints.LINE_END;
		loginC.insets = new Insets(10,10,10,10);
		loginC.weightx = 0.5;
		loginC.gridx = 0;
		loginC.gridy = 0;
		centerPanel.add(label, loginC);
    	label = new JLabel();
    	label.setText("Password:");
		loginC.gridy = 1;
		centerPanel.add(label, loginC);
		usernameField.setFont(usernameField.getFont().deriveFont(16.0f));
    	loginC.anchor = GridBagConstraints.LINE_START;
		loginC.gridx = 1;
		loginC.gridy = 0;
		centerPanel.add(usernameField, loginC);
		passwordField.setFont(passwordField.getFont().deriveFont(16.0f));
		loginC.gridy = 1;
		centerPanel.add(passwordField, loginC);
		loginC.gridx = 1;
		loginC.gridy = 2;
    	centerPanel.add(loginButton, loginC);

    	loginScreen.add(centerPanel, BorderLayout.CENTER);
		return loginScreen;
	}

    /**
     * Passes iterator over entire Inventory to SellerScreen to populate
     * itself, and switches to the SellerScreen.
     */
    public void displaySellerScreen() {
    	Iterator<Product> iter = inventory.iterator();
    	sellerScreen.populate(iter);
		GridLayout grid = (GridLayout)sellerScreen.browsePanel.getLayout();
    	if (sellerScreen.browsePanel.getComponentCount() < 14) {
    		grid.setRows(14);
    	} else {
    		grid.setRows(0);
    	}
    	((CardLayout)(screenCards.getLayout())).show(screenCards, SELLERPANEL);
    }

    /**
     * Passes iterator over Products in Inventory with quantity > 0 (using PrunningIterator decorator)
     * to CustomerScreen to populate itself, and switches to the CustomerScreen.
     */
    public void displayCustomerScreen() {
    	PrunningIterator pIter = new PrunningIterator(inventory.iterator());
    	customerScreen.populate(pIter);
		GridLayout grid = (GridLayout)customerScreen.browsePanel.getLayout();
    	if (customerScreen.browsePanel.getComponentCount() < 14) {
    		grid.setRows(14);
    	} else {
    		grid.setRows(0);
    	}
    	((CardLayout)(screenCards.getLayout())).show(screenCards, CUSTOMERPANEL);
    }

    /**
     * Passes iterator over Products in Cart with quantity > 0 (using PrunningIterator decorator)
     * to CheckoutScreen to populate itself, and switches to the CheckoutScreen.
     */
    public void displayCheckoutScreen() {
    	PrunningIterator pIter = new PrunningIterator(cart.iterator());
    	if (!pIter.hasNext()) {
			JOptionPane.showMessageDialog(screenCards, "Cart is empty.");
    	} else {
        	checkoutScreen.populate(pIter);
    		GridLayout grid = (GridLayout)checkoutScreen.browsePanel.getLayout();
        	if (checkoutScreen.browsePanel.getComponentCount() < 9) {
        		grid.setRows(9);
        	} else {
        		grid.setRows(0);
        	}
        	((CardLayout)(screenCards.getLayout())).show(screenCards, CHECKOUTPANEL);
    	}
    }
    
    /**
     * Gets reference to the CartSystem that created this UI.
     * 
     * @return the CartSystem that created this UI
     */
	public CartSystem getCartSystem() {
		return cartSystem;
	}

    private CartSystem cartSystem;
    private Inventory inventory;
    private Cart cart;
    private AbstractScreen customerScreen;
    private AbstractScreen sellerScreen;
    private AbstractScreen checkoutScreen;
    JPanel screenCards;

}
