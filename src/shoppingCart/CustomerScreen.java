package shoppingCart;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.sound.sampled.Line;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** A class that assembles the Customer JPanel for the UI.
 *  @author Newman Souza
 *  @author Seth Moore
 */ 
@SuppressWarnings("serial")
public class CustomerScreen extends AbstractScreen {
	
    /** Constructs a CustomerBrowsePanel object.
     *  @precondition 		none
     *  @postcondition 		object created
     */
    public CustomerScreen() {
    	super();
    	GridLayout grid = new GridLayout();
    	grid.setColumns(1);
    	grid.setRows(50);
    	this.setLayout(grid);
    }

    public JPanel createHeaderPanel() {
    	JPanel screenNamePanel = new JPanel();
//    	screenNamePanel.setPreferredSize(new Dimension(600, 30));
    	screenNamePanel.setBorder(new EtchedBorder());
    	screenNamePanel.add(new JLabel("Customer Screen"));
    	screenNamePanel.add(getLogoutButton());
    	return screenNamePanel;
    }
    
    /** Assembles a line for each Product in the Inventory.
	 *  @param product		The Product to be displayed in the line
	 *  @precondition 		product is a valid reference
	 *  @postcondition  	Line assembled
	 */
    @Override
    public void addLine(final Product product) {
    	final JPanel line = new JPanel();
    	product.addListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				line.repaint();
			}
		});
    	line.setLayout(new BoxLayout(line, BoxLayout.LINE_AXIS));
    	JLabel label;
    	
    	label = new JLabel(product.getName());
    	label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); 
    	label.addMouseListener(new
    			MouseAdapter(){
    				public void mouseClicked(MouseEvent e) {
    						JOptionPane.showMessageDialog(null,
    								"Testing listener for\nProduct detail.");
    				};
    			}
    	);
    	line.add(label);

    	label = new JLabel(product.getSellPrice().toString());
    	line.add(label);

    	label = new JLabel(String.valueOf(product.getQuantity()));
    	line.add(label);

    	JButton addButton = new JButton("Add to Cart");
    	addButton.addMouseListener(new
    			MouseAdapter(){
    				public void mouseClicked(MouseEvent e) {
//    						JOptionPane.showMessageDialog(line,
//    								"Testing listener for\nAdd To Cart button.");
						Inventory.getInstance().decrement(product);
						Cart.getInstance().increment(product);
    				};
    			}
    	);
    	line.add(addButton);
    	this.add(line);
    }

	private JButton getLogoutButton() {
		JButton logoutButton = new JButton("Logout");
    	logoutButton.addMouseListener(new
    			MouseAdapter(){
    				public void mouseClicked(MouseEvent e){
    					Cart.getInstance().clear();
    					ui..displayLoginScreen();
    				}
    			}
    		);
		return logoutButton;
	}

}
