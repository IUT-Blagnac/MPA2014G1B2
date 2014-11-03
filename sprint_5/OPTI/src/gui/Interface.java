package gui;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
/**
 * Builds OPTI's main frame.
 * @author S3G1B2
 *
 */
@SuppressWarnings("serial")
public class Interface extends JFrame {
	private PanelTab panelTab;
	
	/**
	 * Builds the main frame of OPTI's interface.
	 * 
	 */
	public Interface() {
		super("OPTI");
		this.setSize(550, 600);	//550, 575
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent arg0) {
	        	closeInterface();
	        }
	    });
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		this.panelTab = new PanelTab(this);
					
		this.add(new PanelMenu(this), BorderLayout.NORTH);
		this.add(this.panelTab, BorderLayout.CENTER);
		this.add(new PanelButtons(this), BorderLayout.SOUTH);
	}
	
	/**
	 * Close the main frame of OPTI's interface.
	 */
	public void closeInterface() {
		this.dispose();
	}
	
	/**
	 * returns the tabbed panel attached to the main frame.
	 * @return The tabbed panel attached to the main frame.
	 */
	public PanelTab getPanelTab() {
		return this.panelTab;
	}
	
}
