
/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.graphics.GLabel;
import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {
	/*
	 * private instance variables:
	 */

	private JTextField tf;
	private NameSurferGraph nsg = new NameSurferGraph();
	private NameSurferDataBase nsdb;

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base and
	 * initializing the interactors at the bottom of the window.
	 */
	public void init() {
		add(nsg);
		addButtons();
		nsdb = new NameSurferDataBase(NAMES_DATA_FILE); // reads the file,
														// builds the database
	}

	// adds buttons and sets appropriate commands

	private void addButtons() {
		JLabel label = new JLabel("name");
		add(label, SOUTH);
		tf = new JTextField(20);
		add(tf, SOUTH);
		tf.setActionCommand("Graph");
		tf.addActionListener(this);
		JButton graph = new JButton("Graph");
		add(graph, SOUTH);
		JButton clear = new JButton("Clear");
		add(clear, SOUTH);
		addActionListeners();
	}

	/* Method: actionPerformed(e) */
	/*
	 * This class is responsible for detecting when the buttons are clicked, so
	 * you will have to define a method to respond to button actions.
	 */

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Clear")) {
			nsg.clear();
		} else if (cmd.equals("Graph")) { // if the user presses the graph
											// button or
			// interacts with the text field
			getTheName();
		}
	}

	private void getTheName() {
		String name = tf.getText();
		// makes sure the name is in the correct form
		name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		if (isValid(name)) { // checks name in the list
			if (!wasEntered(name)) { // checks if the user already
												// entered this name
				nsg.addEntry(nsdb.findEntry(name)); // adds the name into
													// entries
				nsg.update(); // updates the graph
			} 
			
		}
		tf.setText("");
	}

	private boolean wasEntered(String name) {
		if (nsg.entries.contains(nsdb.findEntry(name))) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isValid(String name) {
		if (nsdb.findEntry(name) != null) {
			return true;
		} else {
			return false;
		}

	}

}
