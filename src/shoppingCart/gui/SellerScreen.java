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
import java.math.BigDecimal;

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
 *	A JPanel containing all the panels, buttons, listeners, etc. for
 *  the SellerScreen.
 *  
 *  ConcreteClass in Template Method Pattern
 *  
 *  @author Newman Souza
 *  @author Seth Moore
 */ 
@SuppressWarnings("serial")
public class SellerScreen extends AbstractScreen {
	
	/**
	 * Constructs a SellerScreen object.
	 * 
	 * @param ui a reference to the UI that created this screen.
	 */
    public SellerScreen(UI ui) {
    	super(ui);
    }

    /**
     * Creates the header panel for the SellerScreen.
     */
	@Override
    public void createHeaderPanel() {
    	headerPanel.setBorder(new EtchedBorder());
		headerPanel.setLayout(new GridBagLayout());
    	GridBagConstraints headerC = new GridBagConstraints();
		headerC.anchor = GridBagConstraints.LINE_END;
		headerC.weightx = 0.5;
		headerC.gridx = 0;
    	JLabel label = new JLabel("Seller Screen");
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
	 * Creates the side panel for the SellerScreen,
	 * which includes Seller's financial information,
	 * and an Add Product button.
	 */
	@Override
	public void createSidePanel() {
		final JLabel costsLabel = new JLabel("Costs: $" + Inventory.getInstance().getCosts());
		final JLabel revenuesLabel = new JLabel("Revenues: $" + Inventory.getInstance().getRevenues());
		final JLabel profitsLabel = new JLabel("Profits: $" + Inventory.getInstance().getRevenues().subtract(Inventory.getInstance().getCosts()));
		sidePanel = new
				JPanel() {
					public void repaint() {
						costsLabel.setText("Costs: $" + Inventory.getInstance().getCosts());
						revenuesLabel.setText("Revenues: $" + Inventory.getInstance().getRevenues());
						profitsLabel.setText("Profits: $" + Inventory.getInstance().getRevenues().subtract(Inventory.getInstance().getCosts()));
						super.repaint();
					}
			
				};
		sidePanel.setPreferredSize(new Dimension(200, 500));
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		sidePanel.setBorder(new EtchedBorder());
		JLabel titleLabel = new JLabel("Financial Information");
		sidePanel.add(titleLabel);
		JPanel sellerFinancials = new JPanel();
		sellerFinancials.setMaximumSize(new Dimension(200, 100));
		sellerFinancials.setBorder(new EtchedBorder());
		sellerFinancials.setLayout(new GridLayout(3,1));
		sellerFinancials.add(costsLabel);
		sellerFinancials.add(revenuesLabel);
		sellerFinancials.add(profitsLabel);
		sidePanel.add(sellerFinancials);
		JButton checkoutButton = new JButton("Add Product");
		checkoutButton.addActionListener(new
    			ActionListener() {
					public void actionPerformed(ActionEvent e) {
    					displayProductForm();
					}
				}
			);
		sidePanel.add(checkoutButton);
		Inventory.getInstance().addListener(new
				ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent arg0) {
						sidePanel.repaint();
					}
					
		});
		this.add(sidePanel, BorderLayout.EAST);
	}

	/**
	 * Displays a blank Product form, which can be filled in
	 * and saved in order to add new products to Inventory.
	 */
    public void displayProductForm() {
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
    	label = new JLabel("Invoice Price:");
    	c.gridy = 3;
    	productForm.add(label, c);
    	label = new JLabel("Sell Price:");
    	c.gridy = 4;
    	productForm.add(label, c);
    	label = new JLabel("Quantity:");
    	c.gridy = 5;
    	productForm.add(label, c);

    	int newID = Inventory.getInstance().getNewID();

    	label = new JLabel(String.valueOf(newID));
    	c.insets = new Insets(10,20,10,20);
    	c.weightx = 0.5;
    	c.gridx = 2;
    	c.gridy = 0;
    	productForm.add(label, c);
    	JTextField nameTextField = new JTextField(20);
    	nameTextField.requestFocus();
    	c.gridwidth = 3;
    	c.gridy = 1;
    	productForm.add(nameTextField, c);
    	JTextField descriptionTextField = new JTextField(40);
    	c.gridy = 2;
    	productForm.add(descriptionTextField, c);
    	JTextField invoicePriceTextField = new JTextField(20);
    	c.gridy = 3;
    	productForm.add(invoicePriceTextField, c);
    	JTextField sellPriceTextField = new JTextField(20);
    	c.gridy = 4;
    	productForm.add(sellPriceTextField, c);
    	JTextField quantityTextField = new JTextField(5);
    	c.gridy = 5;
    	productForm.add(quantityTextField, c);
    	
    	Object[] options = {"Save", "Cancel"};
    	int button = 2;
    	while (button == 2) {
        	button = JOptionPane.showOptionDialog(ui, productForm, "New Product",
        			JOptionPane.YES_NO_OPTION,
        			JOptionPane.QUESTION_MESSAGE, null, options, null);
        	if (button == 0) {
        		if (validateFields(nameTextField, descriptionTextField, invoicePriceTextField, sellPriceTextField, quantityTextField)) {
            		Product p = new Product(newID, nameTextField.getText(), descriptionTextField.getText(), 
            				new BigDecimal(invoicePriceTextField.getText()), new BigDecimal(sellPriceTextField.getText()), Integer.parseInt(quantityTextField.getText()));
            		Inventory.getInstance().add(p);
            		ui.getCartSystem().saveInventory();
            		ui.displaySellerScreen();
                	ui.validate();
        		} else {
        			button = 2;
        		}
        	}
    	}
    }

    private boolean validateFields(JTextField nameTextField, JTextField descriptionTextField, 
    		JTextField invoicePriceTextField, JTextField sellPriceTextField, JTextField quantityTextField) {
    	if (!nameTextField.getText().matches(".+")) {
    		JOptionPane.showMessageDialog(ui, "Invalid Name:\n\nName cannot be blank.");
    		return false;
    	}
    	if (!descriptionTextField.getText().matches(".+")) {
    		JOptionPane.showMessageDialog(ui, "Invalid Description:\n\nDescription cannot be blank.");
    		return false;
    	}
    	if (!invoicePriceTextField.getText().matches("[0-9]+(?:\\.[0-9]{1,2})?")) {
    		JOptionPane.showMessageDialog(ui, "Invalid Invoice Price:\n\n" + invoicePriceTextField.getText());
    		return false;
    	}
    	if (!sellPriceTextField.getText().matches("[0-9]+(?:\\.[0-9]{1,2})?")) {
    		JOptionPane.showMessageDialog(ui, "Invalid Sell Price:\n\n" + sellPriceTextField.getText());
    		return false;
    	}
    	if (!quantityTextField.getText().matches("[0-9]+")) {
    		JOptionPane.showMessageDialog(ui, "Invalid Quantity:\n\n" + quantityTextField.getText());
    		return false;
    	}
    	return true;
    }
    
    /**
	 * Displays a pre-filled Product form, which can be changed
	 * in order to update a product in Inventory, or the Product
	 * can be deleted from Inventory.
	 * 
	 * @param product the Product that can be updated or deleted.
	 */
    public void displayProductForm(Product product) {
    	JPanel productForm = new JPanel();
    	productForm.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	c.fill = GridBagConstraints.HORIZONTAL;

		JLabel label;
    	label = new JLabel("ID:");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.insets = new Insets(10,20,10,0);
    	c.weightx = 0.5;
    	c.gridx = 0;
    	c.gridy = 0;
    	productForm.add(label, c);
    	label = new JLabel("Name:");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 1;
    	productForm.add(label, c);
    	label = new JLabel("Description: ");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 2;
    	productForm.add(label, c);
    	label = new JLabel("Invoice Price:");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 3;
    	productForm.add(label, c);
    	label = new JLabel("Sell Price:");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 4;
    	productForm.add(label, c);
    	label = new JLabel("Quantity:");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 5;
    	productForm.add(label, c);

    	label = new JLabel(String.valueOf(product.getID()));
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.insets = new Insets(10,20,10,20);
    	c.weightx = 0.5;
    	c.gridx = 2;
    	c.gridy = 0;
    	productForm.add(label, c);
    	JTextField nameTextField = new JTextField(product.getName());
    	nameTextField.setSize(200, (int)nameTextField.getSize().getHeight());
    	nameTextField.requestFocus();
    	c.gridwidth = 3;
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 1;
    	productForm.add(nameTextField, c);
    	JTextField descriptionTextField = new JTextField(product.getDescription());
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 2;
    	productForm.add(descriptionTextField, c);
    	JTextField invoicePriceTextField = new JTextField(String.valueOf(product.getInvoicePrice()));
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 3;
    	productForm.add(invoicePriceTextField, c);
    	JTextField sellPriceTextField = new JTextField(String.valueOf(product.getSellPrice()));
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 4;
    	productForm.add(sellPriceTextField, c);
    	JTextField quantityTextField = new JTextField(String.valueOf(product.getQuantity()));
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 5;
    	productForm.add(quantityTextField, c);
    	
    	Object[] options = {"Update", "Delete", "Cancel"};

    	int button = 3;
    	while (button == 3) {
        	button = JOptionPane.showOptionDialog(ui, productForm, "Update Product",
        			JOptionPane.YES_NO_CANCEL_OPTION,
        			JOptionPane.QUESTION_MESSAGE, null, options, null);
        	if (button == 0) {
        		if (validateFields(nameTextField, descriptionTextField, invoicePriceTextField, sellPriceTextField, quantityTextField)) {
            		Product p = new Product(product.getID(), nameTextField.getText(), descriptionTextField.getText(), 
            				new BigDecimal(invoicePriceTextField.getText()), new BigDecimal(sellPriceTextField.getText()), Integer.parseInt(quantityTextField.getText()));
            		Inventory.getInstance().update(p);
            		ui.getCartSystem().saveInventory();
            	} else {
        			button = 3;
        		}
        	} else if (button == 1) {
            	button = JOptionPane.showConfirmDialog(ui,"Are you sure?", "Confirm Delete", 
            			JOptionPane.YES_NO_OPTION,
            			JOptionPane.QUESTION_MESSAGE, null);
            	if (button == 0) {
            		Inventory.getInstance().remove(product);
            		ui.getCartSystem().saveInventory();
            		ui.displaySellerScreen();
                	ui.validate();
	        	} else {
	    			button = 3;
	    		}
        	}
    	}
    }

    /** 
     * Assembles and returns a JPanel for the supplied Product,
     * with product summary. The Product name is clickable in
     * order to update or delete the Product.
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
    		titleBar.add(new JLabel("ID"));
    		titleBar.add(new JLabel("Name"));
    		titleBar.add(new JLabel("Invoice Price"));
    		titleBar.add(new JLabel("Sell Price"));
    		titleBar.add(new JLabel("Quantity"));
    		titleBar.setLayout(grid);
    		browsePanel.add(titleBar);
    	}
    	final JLabel nameLabel = new JLabel(product.getName());
        final JLabel invoicePriceLabel = new JLabel(product.getInvoicePrice().toString());
    	final JLabel sellPriceLabel = new JLabel(product.getSellPrice().toString());
        final JLabel quantityLabel = new JLabel(String.valueOf(product.getQuantity()));
    	final JPanel line = new
    			JPanel() {
    				public void repaint() {
    					nameLabel.setText(product.getName());
    					invoicePriceLabel.setText(String.valueOf(product.getInvoicePrice()));
    					sellPriceLabel.setText(String.valueOf(product.getSellPrice()));
    					quantityLabel.setText(String.valueOf(product.getQuantity()));
    					super.repaint();
    				}
    			};
    	product.addListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				line.repaint();
			}
		});
    	

//    	GridBagLayout grid = new GridBagLayout();
//		GridBagConstraints c = new GridBagConstraints();    	
//    	line.setLayout(grid);
        JLabel idLabel = new JLabel(String.valueOf(product.getID()));
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 1;
//        c.gridx = 0;
//        c.gridy = 0;
//        line.add(label, c);
        line.add(idLabel);

        nameLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); 
        nameLabel.addMouseListener(new
    			MouseAdapter(){
    				public void mouseClicked(MouseEvent e) {
    					displayProductForm(product);
    				};
    			}
    	);
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 2;
//        c.gridx = 1;
//        c.gridy = 0;
//    	line.add(label, c);
        line.add(nameLabel);

//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 1;
//        c.gridx = 1;
//        c.gridy = 0;
//    	line.add(label, c);
        line.add(invoicePriceLabel);

//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 1;
//        c.gridx = 4;
//        c.gridy = 0;
//    	line.add(label, c);
        line.add(sellPriceLabel);

//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 1;
//        c.gridx = 5;
//        c.gridy = 0;
//    	line.add(label, c);
        line.add(quantityLabel);

    	return line;
    }

}
