import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

public class NameSurferController extends Panel
	implements NameSurferConstants, ActionListener {
	
	private NameSurferGraphPrototype2 graph;
	private HashMap<String, String> namesHashMap;
	private JLabel nameLabel, statusLabel;
	private JTextField nameField;
	private JButton graphButton, removeButton, clearButton;

    public NameSurferController(NameSurferGraphPrototype2 graph, HashMap<String, String> namesHashMap) {
    	this.graph = graph;
    	this.namesHashMap = namesHashMap;
    	
    	setLayout(new FlowLayout());
    	setBackground(new Color(139, 119, 101));
    	
    	nameLabel = new JLabel("Name");
    	statusLabel = new JLabel("Status:");    	
    	nameField = new JTextField(15);
    	graphButton = new JButton("Graph");
    	removeButton = new JButton("Remove");
    	clearButton = new JButton("Clear");
    	
    	nameField.addActionListener(this);
    	graphButton.addActionListener(this);
    	removeButton.addActionListener(this);
    	clearButton.addActionListener(this);
    	
    	add(nameLabel);
    	add(nameField);
    	add(graphButton);
    	add(removeButton);
    	add(clearButton);
    	add(statusLabel);
    }
    
    public void actionPerformed(ActionEvent ae) {
    	if(ae.getSource() == graphButton || ae.getSource() == nameField) {
    		String nameBeingSearched = nameField.getText();
    		String nameLine = namesHashMap.get(nameBeingSearched);
    		
    		if(nameLine != null) { // the HashMap will return null if that name is not in the names.txt
    			graph.addEntry(new NameSurferEntryPrototype(nameLine));
    			
        		statusLabel.setText("Status: Plotted " + nameBeingSearched); // temporary	
        		//System.out.println(nameEntry.toString()); // temporary
    		}    		    		
    	}
    	else if(ae.getSource() == removeButton) {
    		String nameBeingRemoved = nameField.getText();
    		if(graph.removeEntry(nameBeingRemoved)) {
    			statusLabel.setText("Status: Removed " + nameBeingRemoved);
    		}
    		else {
    			statusLabel.setText("Status: Name Invalid");
    		}
    	}
    	else if(ae.getSource() == clearButton) {
    		
    	}
    }
}

