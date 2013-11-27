package shoppingCart;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

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
    	setPreferredSize(new Dimension(800, 600));
    	screenCards = new JPanel(new CardLayout());
    	
    	//setLayout(new BorderLayout());
    	
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
		topPanel.add(new JLabel("Login Screen"));
		topPanel.setBorder(new EtchedBorder());
		loginScreen.add(topPanel, BorderLayout.PAGE_START);
		
		JPanel centerPanel = new JPanel();
		loginScreen.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		
		final JTextField usernameField = new JTextField(20);
		usernameField.setMaximumSize( usernameField.getPreferredSize() );
		final JTextField passwordField = new JTextField(20);
		passwordField.setMaximumSize( passwordField.getPreferredSize() );
    	JButton loginButton = new JButton("Login");
    	loginButton.addActionListener(new
    			ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    					String userType = cartSystem.login(usernameField.getText(), passwordField.getText());
    					if (userType == null){
    						JOptionPane.showMessageDialog(screenCards, "Invalid username/password pair.\nPlease try again.");
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
    	JButton newmanButton = new JButton("Newman");
    	newmanButton.addMouseListener(new
    			MouseAdapter() {
    				public void mouseClicked(MouseEvent e) {
    					String userType = cartSystem.login("Newman", "newman");
    					displaySellerScreen();
    				};
    			}
    	);
    	JButton sethButton = new JButton("Seth");
       	sethButton.addMouseListener(new
    			MouseAdapter() {
    				public void mouseClicked(MouseEvent e) {
    					String userType = cartSystem.login("Seth", "seth");
    					displayCustomerScreen();
    				};
    			}
    	);
    	centerPanel.add(usernameField);
    	centerPanel.add(passwordField);
    	centerPanel.add(loginButton);
    	centerPanel.add(newmanButton);
    	centerPanel.add(sethButton);
		return loginScreen;
	}

    /**
     * Passes iterator over entire Inventory to SellerScreen to populate
     * itself, and switches to the SellerScreen.
     */
    public void displaySellerScreen() {
    	Iterator<Product> iter = inventory.iterator();
    	sellerScreen.populate(iter);
    	((CardLayout)(screenCards.getLayout())).show(screenCards, SELLERPANEL);
    }

    /**
     * Passes iterator over Products in Inventory with quantity > 0 (using PrunningIterator decorator)
     * to CustomerScreen to populate itself, and switches to the CustomerScreen.
     */
    public void displayCustomerScreen() {
    	PrunningIterator pIter = new PrunningIterator(inventory.iterator());
    	customerScreen.populate(pIter);
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
