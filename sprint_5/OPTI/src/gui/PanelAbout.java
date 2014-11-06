package gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Creates the panel which displays informations about this application.
 * @author S3G1B2
 *
 */
@SuppressWarnings("serial")
public class PanelAbout extends JPanel {
	private JTextArea textArea;
	private String text;
	
	/**
	 * Creates a PanelButtons instance.
	 */
	public PanelAbout() {
		super();
		this.setLayout(new BorderLayout());
		this.textArea = new JTextArea();
		this.text = createText();
		this.textArea.setText(this.text);
		this.textArea.setFont(new Font("Tahoma",Font.BOLD,12));
		this.textArea.setOpaque(false);
		this.textArea.setFocusable(false);
		this.textArea.setEditable(false);
		this.add(this.textArea, BorderLayout.CENTER);		
	}
	
	/**
	 * Creates the text of the About panel.
	 * @return The text of the About panel.
	 */
	private String createText() {
		String text = "\n"
				+ "  Group 1B2\n"
				+ "\n"
				+ "  Alex Jacquot-Fernandez\n"
				+ "  Romain Noto\n"
				+ "  Théo Piboubès\n"
				+ "  Hanae Rhayour\n"
				+ "  Aurélien Scuotto\n"
				+ "  Damien Wojtowicz\n"
				+ "\n"
				+ "\n"
				+ "  Project OPTI\n"
				+ "  Sprint 5\n"
				+ "\n"
				+ "  DUT INFO S3/Module MPA\n"
				+ "  Université Toulouse II Jean Jaurès\n"
				+ "  IUT Blagnac\n";
		return text;
	}
	
}
