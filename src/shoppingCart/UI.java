package shoppingCart;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

/** A class that manages interaction with user, receives input and display screens.
 *  @author Seth Moore and Newman Souza
 */ 
public class UI extends JFrame{
	
	final static String LOGINPANEL = "LoginScreen";
	final static String USINGPANEL = "UsingScreen";
	
    /** Constructs a UI object.
     *  @precondition none
     *  @postcondition object created
     */
    public UI() {
    	super("Shopping Cart");
    	inventory = Inventory.getInstance();
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    	setPreferredSize(new Dimension(400, 400));
    	screenCards = new JPanel(new CardLayout());
    	
    	//setLayout(new BorderLayout());
    	
    	JPanel loginScreen = createLoginScreen();
    	screenCards.add(loginScreen, LOGINPANEL);
    	
    	JPanel usingScreen = new JPanel();
    	usingScreen.setLayout(new BorderLayout());
    	screenCards.add(usingScreen, USINGPANEL);
    	
    	JPanel screenNamePanel = new JPanel();
    	screenNamePanel.setPreferredSize(new Dimension(400, 50));
    	screenNamePanel.setBorder(new EtchedBorder());
    	usingScreen.add(screenNamePanel, BorderLayout.PAGE_START);
    	
    	JPanel browsePanel = new JPanel();
    	browsePanel.setPreferredSize(new Dimension(300, 350));
    	browsePanel.setBorder(new EtchedBorder());
    	usingScreen.add(browsePanel, BorderLayout.CENTER);
    	
    	JPanel sidePanel = new JPanel();
    	sidePanel.setPreferredSize(new Dimension(100, 350));
    	sidePanel.setBorder(new EtchedBorder());
    	usingScreen.add(sidePanel, BorderLayout.LINE_END);
    	
    	add(screenCards, BorderLayout.CENTER);
    	
    	((CardLayout)screenCards.getLayout()).show(screenCards, LOGINPANEL);
    	
    	pack();
    	setVisible(true);
    	
    	displayLoginScreen();
    }

	

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displayLoginScreen() {

    	// code
    }

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displayProductForm() {

    	// code
    	
    }

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displayProductForm(Product product) {

    	// code
    	
    }

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displaySellerScreen() {

    	// code
    	
    }

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displaySellerScreen(Inventory inventory) {

    	this.inventory = inventory;
    	
    }

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displayCustomerScreen() {
    	PrunningIterator PIter = new PrunningIterator(inventory.iterator());
//    	customerBrowsePanel.populate(PIter);
    	((CardLayout)(screenCards.getLayout())).show(screenCards, USINGPANEL);
    }

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displayCustomerScreen(Inventory inventory) {

    	this.inventory = inventory;
    	
    }

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displayCheckoutScreen() {

    	// code
    	
    }
    
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
		
		final JTextField usernameField = new JTextField();
		final JTextField passwordField = new JTextField();
    	JButton loginButton = new JButton("Login");
    	loginButton.addMouseListener(new
    			MouseAdapter(){
    				public void mouseClicked(MouseEvent e) {
    					// cartSystem.login(usernameField.getText(), passwordField.getText())
    					//displayCustomerScreen();
//    					String userType = cartSystem.login(usernameField.getText(), passwordField.getText());
    			    	String userType = cartSystem.login("Newman", "newman");
//    					String userType = "customer";
    					if (userType.equals("Customer")){
    						displayCustomerScreen();
    					}
    					else if (userType.equals("Seller")){
    						displaySellerScreen();
    					}
    					else {
    						JOptionPane.showMessageDialog(screenCards, "Invalid username/password pair.\nPlease try again.");
    						usernameField.setText("");
    						passwordField.setText("");
    						
    					}
    					
    				};
    			}
    		);
    	centerPanel.add(usernameField);
    	centerPanel.add(passwordField);
    	centerPanel.add(loginButton);
		return loginScreen;
	}

    private CartSystem cartSystem;
    private Inventory inventory;
    private Cart cart;
//    private AbstractBrowsePanel customerBrowsePanel;
    JPanel screenCards;

}
