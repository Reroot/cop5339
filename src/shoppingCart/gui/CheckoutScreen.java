package shoppingCart.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import shoppingCart.model.Cart;
import shoppingCart.model.Inventory;
import shoppingCart.model.Product;

/**
 * A JPanel containing all the panels, buttons, listeners, etc. for
 * the customer's Checkout Screen.
 * 
 * ConcreteClass in Template Method Pattern
 * 
 *  @author Newman Souza
 *  @author Seth Moore
 */ 
@SuppressWarnings("serial")
public class CheckoutScreen extends AbstractScreen {
	
 	/**
	 * Constructs a CheckoutScreen object.
	 * 
	 * @param ui a reference to the UI that created this screen.
	 */
    public CheckoutScreen(UI ui) {
    	super(ui);
    }

    /**
     * Creates the header panel for the CheckoutScreen.
     */
	public void createHeaderPanel() {
    	headerPanel.setBorder(new EtchedBorder());
		headerPanel.setLayout(new GridBagLayout());
    	GridBagConstraints headerC = new GridBagConstraints();
		headerC.anchor = GridBagConstraints.LINE_END;
		headerC.weightx = 0.5;
		headerC.gridx = 0;
    	JLabel label = new JLabel("Checkout Screen");
		label.setFont(label.getFont().deriveFont(16.0f));
    	headerPanel.add(label, headerC);
		JButton logoutButton = new JButton("Logout");
    	logoutButton.addActionListener(new
    			ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    					Cart.getInstance().clear();
    					ui.displayLoginScreen();
    				}
    			}
    		);
		headerC.gridx = 1;
		headerC.insets = new Insets(3,0,4,4);
		headerC.anchor = GridBagConstraints.LINE_END;
    	headerPanel.add(logoutButton, headerC);
    	this.add(headerPanel, BorderLayout.NORTH);
    }
    
	/**
	 * Creates the side panel for the CheckoutScreen,
	 * which includes a cart summary and payment form.
	 */
	@Override
	public void createSidePanel() {
		final JLabel itemsLabel = new JLabel(String.valueOf(Cart.getInstance().getQuantity()));
		final JLabel totalLabel = new JLabel(String.valueOf(NumberFormat.getCurrencyInstance().format(Cart.getInstance().getTotal())));
		sidePanel = new
				JPanel() {
					public void repaint() {
						itemsLabel.setText(String.valueOf(Cart.getInstance().getQuantity()));
						totalLabel.setText(String.valueOf(NumberFormat.getCurrencyInstance().format(Cart.getInstance().getTotal())));
						super.repaint();
					}
			
				};
		sidePanel.setBorder(new EtchedBorder());
		sidePanel.setPreferredSize(new Dimension(240, 600));
		JPanel panel = new JPanel();
		panel.setSize(sidePanel.getPreferredSize());
		panel.setLayout(new GridBagLayout());
		JLabel titleLabel = new JLabel("Cart Summary");
		titleLabel.setFont(titleLabel.getFont().deriveFont(16.0f));
    	GridBagConstraints panelC = new GridBagConstraints();
		panelC.insets = new Insets(10,10,3,10);
		panelC.weightx = 1;
		panelC.gridx = 0;
		panelC.gridy = 0;
		panel.add(titleLabel, panelC);
		JPanel cartSummary = new JPanel();
		cartSummary.setLayout(new GridBagLayout());
    	GridBagConstraints summaryC = new GridBagConstraints();
		cartSummary.setPreferredSize(new Dimension(210, 100));
		cartSummary.setBorder(new EtchedBorder());

		JLabel label;
		label = new JLabel("Items:");
		label.setFont(label.getFont().deriveFont(16.0f));
		summaryC.anchor = GridBagConstraints.LINE_END;
		summaryC.insets = new Insets(10,0,10,0);
		summaryC.weightx = 0.5;
		summaryC.gridx = 0;
		summaryC.gridy = 0;
		cartSummary.add(label, summaryC);
		label = new JLabel("Total:");
		label.setFont(label.getFont().deriveFont(16.0f));
		summaryC.gridy = 1;
		cartSummary.add(label, summaryC);
		summaryC.insets = new Insets(10,0,10,20);
		summaryC.gridwidth = 2;
		summaryC.gridx = 1;
		summaryC.gridy = 0;
		itemsLabel.setFont(label.getFont().deriveFont(16.0f));
		cartSummary.add(itemsLabel, summaryC);
		summaryC.gridy = 1;
		totalLabel.setFont(label.getFont().deriveFont(16.0f));
		cartSummary.add(totalLabel, summaryC);
		panelC.insets = new Insets(0,10,0,10);
		panelC.gridy = 1;
		panel.add(cartSummary, panelC);
		
		JLabel formLabel = new JLabel("Payment Information");
		formLabel.setFont(titleLabel.getFont().deriveFont(16.0f));
		panelC.insets = new Insets(30,10,3,10);
		panelC.weightx = 1;
		panelC.gridy = 3;
		panel.add(formLabel, panelC);
		JPanel paymentForm = new JPanel();
		paymentForm.setLayout(new GridBagLayout());
    	GridBagConstraints formC = new GridBagConstraints();
		paymentForm.setPreferredSize(new Dimension(210, 260));
		paymentForm.setBorder(new EtchedBorder());
		
		label = new JLabel("Type:");
//		formC.fill = GridBagConstraints.NONE;
		formC.anchor = GridBagConstraints.LINE_START;
		formC.insets = new Insets(5,5,0,5);
		formC.gridwidth = 2;
		formC.weightx = 0.2;
		formC.gridx = 0;
		formC.gridy = 0;
		paymentForm.add(label, formC);
		label = new JLabel("Cardholder Name:");
		formC.gridy = 2;
		paymentForm.add(label, formC);
		label = new JLabel("Card Number:");
		formC.gridy = 4;
		paymentForm.add(label, formC);
		label = new JLabel("Expiration Date:");
		formC.gridwidth = 1;
		formC.gridy = 6;
		paymentForm.add(label, formC);
		label = new JLabel("Security Code:");
		formC.gridy = 7;
		paymentForm.add(label, formC);
		
		JTextField cardTypeTextField = new JTextField();
		cardTypeTextField.setPreferredSize(new Dimension(195, 25));
		cardTypeTextField.setText("MasterCard");
		formC.gridwidth = 2;
		formC.insets = new Insets(5,5,3,5);
		formC.weightx = 1;
		formC.gridy = 1;
		paymentForm.add(cardTypeTextField, formC);
		JTextField cardholderTextField = new JTextField();
		cardholderTextField.setPreferredSize(new Dimension(195, 25));
		cardholderTextField.setText("John Smith");
		formC.gridy = 3;
		paymentForm.add(cardholderTextField, formC);
		final JTextField cardNumberTextField = new JTextField();
		cardNumberTextField.setPreferredSize(new Dimension(195, 25));
		cardNumberTextField.setText("1234-5678-1234-5678");
		formC.gridy = 5;
		paymentForm.add(cardNumberTextField, formC);
		JTextField expirationTextField = new JTextField();
		expirationTextField.setPreferredSize(new Dimension(52, 25));
		expirationTextField.setText("10/2015");
		formC.anchor = GridBagConstraints.LINE_END;
		formC.gridwidth = 1;
		formC.gridx = 1;
		formC.gridy = 6;
		paymentForm.add(expirationTextField, formC);
		JTextField codeTextField = new JTextField();
		codeTextField.setPreferredSize(new Dimension(26, 25));
		codeTextField.setText("123");
		formC.gridy = 7;
		paymentForm.add(codeTextField, formC);
		panelC.insets = new Insets(0,10,10,10);
		panelC.gridy = 4;
		panel.add(paymentForm, panelC);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new
    			ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ui.displayCustomerScreen();
					}
				}
			);
		panelC.anchor = GridBagConstraints.LINE_START;
    	panelC.insets = new Insets(0,40,0,5);
		panelC.gridy = 5;
		panel.add(cancelButton, panelC);
		JButton payButton = new JButton("Pay");
		payButton.addActionListener(new
    			ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boolean result = ui.getCartSystem().pay(cardNumberTextField.getText(), Cart.getInstance().getTotal());
						if (result) {
    						JOptionPane.showMessageDialog(null,
    								"Payment successful.");
        					Cart.getInstance().clear();
        					ui.displayCustomerScreen();
						} else {
							JOptionPane.showMessageDialog(null,
									"Payment failed.");
						}
					}
				}
			);
		panelC.anchor = GridBagConstraints.LINE_END;
    	panelC.insets = new Insets(0,5,0,40);
		panel.add(payButton, panelC);
		Cart.getInstance().addListener(new
				ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent arg0) {
						sidePanel.repaint();
					}
					
		});
		sidePanel.add(panel);
		this.add(sidePanel, BorderLayout.EAST);
	}

   /**
    * Displays a Product's details.
    * 
    * @param product the Product whose details are to be displayed.
    */
    public void displayProductForm(Product product) {
    	JPanel productForm = new JPanel();
    	productForm.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	c.fill = GridBagConstraints.HORIZONTAL;

    	JLabel label;
    	label = new JLabel("ID:");
    	c.insets = new Insets(10,20,10,0);
    	c.weightx = 0.5;
    	c.gridx = 0;
    	c.gridy = 0;
    	productForm.add(label, c);
    	label = new JLabel("Name:");
    	c.gridy = 1;
    	productForm.add(label, c);
    	label = new JLabel("Description: ");
    	c.gridy = 2;
    	productForm.add(label, c);
    	label = new JLabel("Price:");
    	c.gridy = 3;
    	productForm.add(label, c);
    	label = new JLabel("Quantity:");
    	c.gridy = 4;
    	productForm.add(label, c);

    	label = new JLabel(String.valueOf(product.getID()));
    	c.insets = new Insets(10,20,10,20);
    	c.weightx = 0.5;
    	c.gridx = 2;
    	c.gridy = 0;
    	productForm.add(label, c);
    	label = new JLabel(product.getName());
    	c.gridy = 1;
    	productForm.add(label, c);
    	label = new JLabel(product.getDescription());
    	c.gridy = 2;
    	productForm.add(label, c);
    	label = new JLabel(String.valueOf(NumberFormat.getCurrencyInstance().format(product.getSellPrice())));
    	c.gridy = 3;
    	productForm.add(label, c);
    	label = new JLabel(String.valueOf(product.getQuantity()));
    	c.gridy = 4;
    	productForm.add(label, c);
    	JOptionPane.showMessageDialog(ui, productForm, "Product Detail", JOptionPane.INFORMATION_MESSAGE);
    }
	
    /** 
     * Assembles and returns a JPanel for the supplied Product,
     * with product summary and increment/decrement buttons.
     *  
	 *  @param product		The Product represented by the JPanel.
	 *  @return 			a JPanel filled with appropriate labels, buttons, etc.
	 *  @precondition 		product is a valid reference
	 */
    @Override
    public JPanel addLine(final Product product) {
    	GridBagLayout grid = new GridBagLayout();
    	GridBagConstraints c = new GridBagConstraints();
    	if (browsePanel.getComponentCount() == 0) {
    		JPanel titleBar = new JPanel();
    		titleBar.add(new JLabel("Name"));
    		titleBar.add(new JLabel("Price"));
    		titleBar.add(new JLabel("Quantity in cart"));
    		titleBar.setLayout(grid);
    		browsePanel.add(titleBar);
    	}
    	final JButton incrementButton = new JButton("Increment");
    	final JButton decrementButton = new JButton("Decrement");
    	final JLabel quantityLabel = new JLabel(String.valueOf(product.getQuantity()));
    	final JPanel line = new
    			JPanel() {
    				public void repaint() {
    					quantityLabel.setText(String.valueOf(product.getQuantity()));
    					if (product.getQuantity() < 1) {
    						decrementButton.setEnabled(false);
    					}
    					else {
    						decrementButton.setEnabled(true);
    					}
    					if (Inventory.getInstance().getMatchingProduct(product).getQuantity() < 1) {
    						incrementButton.setEnabled(false);
    					}
    					else {
    						incrementButton.setEnabled(true);
    					}
    					super.repaint();
    				}
    			};
    	product.addListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				line.repaint();
			}
		});
    	JLabel label;
    	
    	label = new JLabel(product.getName());
    	label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); 
    	label.addMouseListener(new
    			MouseAdapter(){
    				public void mouseClicked(MouseEvent e) {
    					displayProductForm(product);
    				};
    			}
    	);
    	line.add(label);

    	label = new JLabel("$" + product.getSellPrice().toString());
    	line.add(label);

    	
    	line.add(quantityLabel);

    	incrementButton.addActionListener(new
    			ActionListener(){
    				public void actionPerformed(ActionEvent arg0) {
   						Inventory.getInstance().decrement(product);
   						Cart.getInstance().increment(product);
    				};
    			}
    	);

    	line.add(incrementButton);
    	
    	decrementButton.addActionListener(new
    			ActionListener(){
    				public void actionPerformed(ActionEvent arg0) {
						Inventory.getInstance().increment(product);
						Cart.getInstance().decrement(product);
    				};
    			}
    	);
    	line.add(decrementButton);
    	return line;
    }

}
