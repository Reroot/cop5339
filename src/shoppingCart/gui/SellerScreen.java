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
import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
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
		final JLabel revenuesLabel = new JLabel(String.valueOf(NumberFormat.getCurrencyInstance().format(Inventory.getInstance().getRevenues())));
		final JLabel costsLabel = new JLabel(String.valueOf(NumberFormat.getCurrencyInstance().format(Inventory.getInstance().getCosts())));
		final JLabel profitsLabel = new JLabel(String.valueOf(NumberFormat.getCurrencyInstance().format(
				Inventory.getInstance().getRevenues().subtract(Inventory.getInstance().getCosts()))));
		sidePanel = new
				JPanel() {
					public void repaint() {
						costsLabel.setText(String.valueOf(NumberFormat.getCurrencyInstance().format(Inventory.getInstance().getCosts())));
						revenuesLabel.setText(String.valueOf(NumberFormat.getCurrencyInstance().format(Inventory.getInstance().getRevenues())));
						profitsLabel.setText(String.valueOf(NumberFormat.getCurrencyInstance().format(
								Inventory.getInstance().getRevenues().subtract(Inventory.getInstance().getCosts()))));
						super.repaint();
					}
				};
		sidePanel.setBorder(new EtchedBorder());
		sidePanel.setPreferredSize(new Dimension(240, 600));
		JPanel panel = new JPanel();
		panel.setSize(sidePanel.getPreferredSize());
		panel.setLayout(new GridBagLayout());
		JLabel titleLabel = new JLabel("Financial Information");
		titleLabel.setFont(titleLabel.getFont().deriveFont(16.0f));
    	GridBagConstraints panelC = new GridBagConstraints();
		panelC.insets = new Insets(10,10,10,10);
		panelC.weightx = 1;
		panelC.gridx = 0;
		panelC.gridy = 0;
		panel.add(titleLabel, panelC);
		JPanel sellerFinancials = new JPanel();
		sellerFinancials.setLayout(new GridBagLayout());
    	GridBagConstraints financialsC = new GridBagConstraints();
    	sellerFinancials.setPreferredSize(new Dimension(210, 140));
    	sellerFinancials.setBorder(new EtchedBorder());

		JLabel label;
		label = new JLabel("Revenues:");
		label.setFont(label.getFont().deriveFont(15.0f));
		financialsC.anchor = GridBagConstraints.LINE_END;
		financialsC.insets = new Insets(10,0,10,0);
		financialsC.weightx = 0.5;
		financialsC.gridx = 0;
		financialsC.gridy = 0;
		sellerFinancials.add(label, financialsC);
		label = new JLabel("Costs:");
		label.setFont(label.getFont().deriveFont(15.0f));
		financialsC.gridy = 1;
		sellerFinancials.add(label, financialsC);
		label = new JLabel("Profit:");
		label.setFont(label.getFont().deriveFont(15.0f));
		financialsC.gridy = 2;
		sellerFinancials.add(label, financialsC);
		financialsC.insets = new Insets(10,0,10,20);
		financialsC.gridwidth = 2;
		financialsC.gridx = 1;
		financialsC.gridy = 0;
		revenuesLabel.setFont(label.getFont().deriveFont(15.0f));
		sellerFinancials.add(revenuesLabel, financialsC);
		financialsC.gridy = 1;
		costsLabel.setFont(label.getFont().deriveFont(15.0f));
		sellerFinancials.add(costsLabel, financialsC);
		financialsC.gridy = 2;
		profitsLabel.setFont(label.getFont().deriveFont(15.0f));
		sellerFinancials.add(profitsLabel, financialsC);
		panelC.gridy = 1;
		panel.add(sellerFinancials, panelC);
		JButton addProductButton = new JButton("Add Product");
		addProductButton.addActionListener(new
    			ActionListener() {
					public void actionPerformed(ActionEvent e) {
    					displayProductForm();
					}
				}
			);
		panelC.gridy = 2;
		panel.add(addProductButton, panelC);
		Inventory.getInstance().addListener(new
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
    	c.fill = GridBagConstraints.NONE;
    	c.anchor = GridBagConstraints.LINE_START;
    	c.insets = new Insets(10,20,10,20);
    	c.weightx = 0.5;
    	c.gridx = 2;
    	c.gridy = 0;
    	productForm.add(label, c);
    	JTextField nameTextField = new JTextField();
    	nameTextField.setPreferredSize(new Dimension(200, 25));
    	nameTextField.requestFocus();
    	c.gridwidth = 3;
    	c.gridy = 1;
    	productForm.add(nameTextField, c);
    	JTextField descriptionTextField = new JTextField();
    	descriptionTextField.setPreferredSize(new Dimension(300, 25));
    	c.gridy = 2;
    	productForm.add(descriptionTextField, c);
    	JTextField invoicePriceTextField = new JTextField();
    	invoicePriceTextField.setPreferredSize(new Dimension(70, 25));
    	c.gridy = 3;
    	productForm.add(invoicePriceTextField, c);
    	JTextField sellPriceTextField = new JTextField();
    	sellPriceTextField.setPreferredSize(new Dimension(70, 25));
    	c.gridy = 4;
    	productForm.add(sellPriceTextField, c);
    	JTextField quantityTextField = new JTextField();
    	quantityTextField.setPreferredSize(new Dimension(50, 25));
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

    	label = new JLabel(String.valueOf(product.getID()));
    	c.fill = GridBagConstraints.NONE;
    	c.anchor = GridBagConstraints.LINE_START;
    	c.insets = new Insets(10,20,10,20);
    	c.weightx = 0.5;
    	c.gridx = 2;
    	c.gridy = 0;
    	productForm.add(label, c);
    	JTextField nameTextField = new JTextField(product.getName());
    	nameTextField.setPreferredSize(new Dimension(200, 25));
    	nameTextField.requestFocus();
    	c.gridwidth = 3;
    	c.gridy = 1;
    	productForm.add(nameTextField, c);
    	JTextField descriptionTextField = new JTextField(product.getDescription());
    	descriptionTextField.setPreferredSize(new Dimension(300, 25));
    	c.gridy = 2;
    	productForm.add(descriptionTextField, c);
    	JTextField invoicePriceTextField = new JTextField(String.valueOf(product.getInvoicePrice()));
    	invoicePriceTextField.setPreferredSize(new Dimension(70, 25));
    	c.gridy = 3;
    	productForm.add(invoicePriceTextField, c);
    	JTextField sellPriceTextField = new JTextField(String.valueOf(product.getSellPrice()));
    	sellPriceTextField.setPreferredSize(new Dimension(70, 25));
    	c.gridy = 4;
    	productForm.add(sellPriceTextField, c);
    	JTextField quantityTextField = new JTextField(String.valueOf(product.getQuantity()));
    	quantityTextField.setPreferredSize(new Dimension(50, 25));
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
    	GridBagConstraints headerC = new GridBagConstraints();
    	if (browsePanel.getComponentCount() == 0) {
    		JPanel headerLine = new JPanel();
        	headerLine.setLayout(grid);
    		JLabel idHeaderLabel = new JLabel("ID");
        	idHeaderLabel.setPreferredSize(new Dimension(10, 25));
        	headerC.fill = GridBagConstraints.HORIZONTAL;
        	headerC.anchor = GridBagConstraints.CENTER;
        	headerC.insets = new Insets(10,5,0,5);
        	headerC.weightx = 0.1;
        	headerC.gridx = 0;
        	headerC.gridy = 0;
        	headerLine.add(idHeaderLabel, headerC);
    		JLabel nameHeaderLabel = new JLabel("Name");
        	nameHeaderLabel.setPreferredSize(new Dimension(120, 25));
        	headerC.gridwidth = 4;
        	headerC.weightx = 0.3;
        	headerC.gridx = 1;
        	headerLine.add(nameHeaderLabel, headerC);
    		JLabel invoicePriceHeaderLabel = new JLabel("Invoice Price");
        	invoicePriceHeaderLabel.setPreferredSize(new Dimension(74, 25));
        	invoicePriceHeaderLabel.setHorizontalAlignment(JLabel.RIGHT);
        	headerC.anchor = GridBagConstraints.LINE_END;
        	headerC.fill = GridBagConstraints.NONE;
        	headerC.gridwidth = 1;
        	headerC.weightx = 0.2;
        	headerC.gridx = 5;
        	headerLine.add(invoicePriceHeaderLabel, headerC);
    		JLabel sellPriceHeaderLabel = new JLabel("Sell Price");
        	sellPriceHeaderLabel.setPreferredSize(new Dimension(70, 25));
        	sellPriceHeaderLabel.setHorizontalAlignment(JLabel.RIGHT);
        	headerC.anchor = GridBagConstraints.LINE_END;
        	headerC.fill = GridBagConstraints.NONE;
        	headerC.gridx = 6;
        	headerLine.add(sellPriceHeaderLabel, headerC);
        	headerC.anchor = GridBagConstraints.CENTER;
        	headerC.gridx = 7;
    		JLabel quantityHeaderLabel = new JLabel("Quantity");
    		quantityHeaderLabel.setPreferredSize(new Dimension(50, 25));
        	quantityHeaderLabel.setHorizontalAlignment(JLabel.RIGHT);
        	headerLine.add(quantityHeaderLabel, headerC);
    		browsePanel.add(headerLine);
    	}
    	final JLabel nameLabel = new JLabel(product.getName());
        final JLabel invoicePriceLabel = new JLabel(String.valueOf(NumberFormat.getCurrencyInstance().format(product.getInvoicePrice())));
    	final JLabel sellPriceLabel = new JLabel(String.valueOf(NumberFormat.getCurrencyInstance().format(product.getSellPrice())));
        final JLabel quantityLabel = new JLabel(String.valueOf(product.getQuantity()));
    	final JPanel line = new
    			JPanel() {
    				public void repaint() {
    					nameLabel.setText(product.getName());
    					invoicePriceLabel.setText(String.valueOf(NumberFormat.getCurrencyInstance().format(product.getInvoicePrice())));
    					sellPriceLabel.setText(String.valueOf(NumberFormat.getCurrencyInstance().format(product.getSellPrice())));
    					quantityLabel.setText(String.valueOf(product.getQuantity()));
    					super.repaint();
    				}
    			};
    	line.setLayout(grid);

        JLabel idLabel = new JLabel(String.valueOf(product.getID()));
    	idLabel.setPreferredSize(new Dimension(10, 25));
    	GridBagConstraints lineC = new GridBagConstraints();
    	lineC.fill = GridBagConstraints.HORIZONTAL;
    	lineC.insets = new Insets(10,5,0,5);
    	lineC.gridwidth = 1;
    	lineC.weightx = 0.1;
    	lineC.gridx = 0;
    	lineC.gridy = 0;
    	line.add(idLabel, lineC);

        nameLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); 
        nameLabel.addMouseListener(new
    			MouseAdapter(){
    				public void mouseClicked(MouseEvent e) {
    					displayProductForm(product);
    				};
    			}
    	);
    	nameLabel.setPreferredSize(new Dimension(120, 25));
    	lineC.gridwidth = 4;
    	lineC.weightx = 0.3;
    	lineC.gridx = 1;
        line.add(nameLabel, lineC);

    	invoicePriceLabel.setPreferredSize(new Dimension(70, 25));
    	invoicePriceLabel.setHorizontalAlignment(JLabel.RIGHT);
    	lineC.anchor = GridBagConstraints.LINE_END;
    	lineC.fill = GridBagConstraints.NONE;
    	lineC.gridwidth = 1;
    	lineC.weightx = 0.2;
    	lineC.gridx = 5;
        line.add(invoicePriceLabel, lineC);

    	sellPriceLabel.setPreferredSize(new Dimension(74, 25));
    	sellPriceLabel.setHorizontalAlignment(JLabel.RIGHT);
    	lineC.gridx = 6;
        line.add(sellPriceLabel, lineC);

		quantityLabel.setPreferredSize(new Dimension(50, 25));
    	quantityLabel.setHorizontalAlignment(JLabel.RIGHT);
    	lineC.anchor = GridBagConstraints.CENTER;
    	lineC.gridx = 7;
        line.add(quantityLabel, lineC);

    	product.addListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				line.repaint();
			}
		});
    	return line;
    }

}
