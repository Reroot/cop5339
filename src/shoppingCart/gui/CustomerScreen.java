package shoppingCart.gui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import shoppingCart.model.Cart;
import shoppingCart.model.Inventory;
import shoppingCart.model.Product;

/** 
 *	A JPanel containing all the panels, buttons, listeners, etc. for
 *  the CustomerScreen.
 *  
 *  ConcreteClass in Template Method Pattern
 *  
 *  @author Newman Souza
 *  @author Seth Moore
 */ 
@SuppressWarnings("serial")
public class CustomerScreen extends AbstractScreen {
	
	/**
	 * Constructs a CustomerScreen object.
	 * 
	 * @param ui a reference to the UI that created this screen.
	 */
    public CustomerScreen(UI ui) {
    	super(ui);
    }

    /**
     * Creates the header panel for the CustomerScreen.
     */
	@Override
    public void createHeaderPanel() {
    	headerPanel.setBorder(new EtchedBorder());
		headerPanel.setLayout(new GridBagLayout());
    	GridBagConstraints headerC = new GridBagConstraints();
		headerC.anchor = GridBagConstraints.LINE_END;
		headerC.weightx = 0.5;
		headerC.gridx = 0;
    	JLabel label = new JLabel("Customer Screen");
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
	 * Creates the side panel for the CustomerScreen,
	 * which includes a cart summary.
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
//		sidePanel.setPreferredSize(new Dimension(200, 500));
		sidePanel.setBorder(new EtchedBorder());
		sidePanel.setLayout(new GridBagLayout());
    	GridBagConstraints panelC = new GridBagConstraints();
		JLabel titleLabel = new JLabel("Cart Summary");
		titleLabel.setFont(titleLabel.getFont().deriveFont(16.0f));
		panelC.insets = new Insets(10,10,10,10);
		panelC.weightx = 0.5;
		panelC.gridx = 0;
		panelC.gridy = 0;
		sidePanel.add(titleLabel, panelC);
		JPanel cartSummary = new JPanel();
		cartSummary.setLayout(new GridBagLayout());
    	GridBagConstraints summaryC = new GridBagConstraints();
//		summaryC.fill = GridBagConstraints.HORIZONTAL;
//		cartSummary.setMinimumSize(new Dimension(200, 100));
		cartSummary.setPreferredSize(new Dimension(200, 100));
		cartSummary.setBorder(new EtchedBorder());
//		cartSummary.setLayout(new GridLayout(3,1));

		JLabel label;
		label = new JLabel("Items:");
		label.setFont(label.getFont().deriveFont(16.0f));
		summaryC.anchor = GridBagConstraints.LINE_END;
		summaryC.insets = new Insets(10,0,0,0);
		summaryC.weightx = 0.5;
		summaryC.gridx = 0;
		summaryC.gridy = 0;
		cartSummary.add(label, summaryC);
		label = new JLabel("Total:");
		label.setFont(label.getFont().deriveFont(16.0f));
		summaryC.gridy = 1;
		cartSummary.add(label, summaryC);
		summaryC.insets = new Insets(10,0,0,20);
		summaryC.gridwidth = 2;
		summaryC.gridx = 1;
		summaryC.gridy = 0;
		itemsLabel.setFont(label.getFont().deriveFont(16.0f));
		cartSummary.add(itemsLabel, summaryC);
		summaryC.gridy = 1;
		totalLabel.setFont(label.getFont().deriveFont(16.0f));
		cartSummary.add(totalLabel, summaryC);
		panelC.gridy = 1;
		sidePanel.add(cartSummary, panelC);
		JButton checkoutButton = new JButton("Checkout");
		checkoutButton.addActionListener(new
    			ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ui.displayCheckoutScreen();
					}
				}
			);
		panelC.gridy = 2;
		sidePanel.add(checkoutButton, panelC);
		Cart.getInstance().addListener(new
				ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent arg0) {
						sidePanel.repaint();
					}
					
		});
		this.add(sidePanel, BorderLayout.EAST);
	}

	/**
	 * Displays a Product's details.
	 * 
	 * @param product
	 *            the Product whose details are to be displayed.
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
     * with product summary and Add button.
     *  
	 *  @param product		The Product represented by the JPanel.
	 *  @return 			a JPanel filled with appropriate labels, buttons, etc.
	 *  @precondition 		product is a valid reference
	 */
    @Override
    public JPanel addLine(final Product product) {
    	GridBagLayout grid = new GridBagLayout();
    	GridBagConstraints lineC = new GridBagConstraints();
    	if (browsePanel.getComponentCount() == 0) {
    		JPanel headerLine = new JPanel();
        	headerLine.setLayout(grid);
    		JLabel nameHeaderLabel = new JLabel("Name");
        	nameHeaderLabel.setPreferredSize(new Dimension(100, 25));
        	lineC.fill = GridBagConstraints.HORIZONTAL;
        	lineC.anchor = GridBagConstraints.CENTER;
        	lineC.gridwidth = 4;
        	lineC.insets = new Insets(10,5,0,5);
        	lineC.weightx = 0.4;
        	lineC.gridx = 0;
        	lineC.gridy = 0;
        	headerLine.add(nameHeaderLabel, lineC);
    		JLabel priceHeaderLabel = new JLabel("Price");
        	priceHeaderLabel.setPreferredSize(new Dimension(70, 25));
        	lineC.anchor = GridBagConstraints.LINE_END;
        	lineC.fill = GridBagConstraints.NONE;
        	lineC.gridwidth = 1;
        	lineC.weightx = 0.2;
        	lineC.gridx = 4;
        	headerLine.add(priceHeaderLabel, lineC);
        	lineC.anchor = GridBagConstraints.CENTER;
        	lineC.gridx = 5;
    		JLabel quantityHeaderLabel = new JLabel("Quantity");
    		quantityHeaderLabel.setPreferredSize(new Dimension(50, 25));
        	headerLine.add(quantityHeaderLabel, lineC);
        	lineC.gridx = 6;
    		JLabel buttonHeaderLabel = new JLabel();
    		buttonHeaderLabel.setPreferredSize(new Dimension(30, 25));
        	headerLine.add(buttonHeaderLabel, lineC);
    		browsePanel.add(headerLine);
    	}
    	final JButton addButton = new JButton("Add to Cart");
    	final JLabel quantityLabel = new JLabel(String.valueOf(product.getQuantity()));
    	final JPanel line = new
    			JPanel() {
    				public void repaint() {
    					quantityLabel.setText(String.valueOf(product.getQuantity()));
    					if (product.getQuantity() < 1) {
    						addButton.setEnabled(false);
    					}
    					super.repaint();
    				}
    			};
    	line.setLayout(grid);
    	
    	JLabel nameLabel = new JLabel(product.getName());
    	nameLabel.setPreferredSize(new Dimension(100, 25));
    	nameLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); 
    	nameLabel.addMouseListener(new
    			MouseAdapter(){
    				public void mouseClicked(MouseEvent e) {
    					displayProductForm(product);
    				};
    			}
    	);
    	lineC.fill = GridBagConstraints.HORIZONTAL;
    	lineC.anchor = GridBagConstraints.CENTER;
    	lineC.gridwidth = 4;
    	lineC.insets = new Insets(10,5,0,5);
    	lineC.weightx = 0.4;
    	lineC.gridx = 0;
    	lineC.gridy = 0;
    	line.add(nameLabel, lineC);

    	JLabel priceLabel = new JLabel(String.valueOf(NumberFormat.getCurrencyInstance().format(product.getSellPrice())));
//    	priceLabel = new JLabel("$" + product.getSellPrice().toString());
    	priceLabel.setPreferredSize(new Dimension(70, 25));
    	priceLabel.setHorizontalAlignment(JLabel.RIGHT);
    	lineC.anchor = GridBagConstraints.LINE_END;
    	lineC.fill = GridBagConstraints.NONE;
    	lineC.gridwidth = 1;
    	lineC.weightx = 0.2;
    	lineC.gridx = 4;
    	line.add(priceLabel, lineC);
    	
    	lineC.gridx = 5;
    	quantityLabel.setPreferredSize(new Dimension(50, 25));
    	quantityLabel.setHorizontalAlignment(JLabel.RIGHT);
    	line.add(quantityLabel, lineC);
    	
    	addButton.addActionListener(new
    			ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Inventory.getInstance().decrement(product);
						Cart.getInstance().increment(product);
						
					};
    			}
    	);
		addButton.setFont(addButton.getFont().deriveFont(10.0f));
    	addButton.setMargin(new Insets(1,1,1,1));
    	lineC.gridx = 6;
    	line.add(addButton, lineC);
    	
    	product.addListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				line.repaint();
			}
		});
    	return line;
    }

}
