import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NameSurferPrototype extends JApplet
	implements NameSurferConstants {
	
	private static HashMap<String, String> namesHashMap;
	private NameSurferGraphPrototype2 graph;
	private NameSurferController controlBar;
	
	// Initialize the applet.
	public void init() {
		try {
			SwingUtilities.invokeAndWait(new Runnable () {
				public void run() {
					initMainProgram();
				}
			});
		}
		catch(Exception exc) { System.out.println("Can't create because of "+ exc); }
	}
	
	private void initMainProgram() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		setLayout(new BorderLayout());
		
		namesHashMap = new HashMap<String, String>();
		loadnamesHashMap();
		
		// create an instance and initialize constructors
		graph = new NameSurferGraphPrototype2();
		controlBar = new NameSurferController(graph, namesHashMap); // move creation of statusLabel to NameSurferController 
		
		add(graph, BorderLayout.CENTER);		
		add(controlBar, BorderLayout.SOUTH);
	}	
	
	// put all the the data of the names.txt to namesHashMap
	private void loadnamesHashMap() {
		try {
			BufferedReader rd = new BufferedReader(
					// search on how to make accessing directories shorter
					new FileReader("C:\\Users\\Chust\\Documents\\Eclipse\\CS 106A Assignment6 (Name Surfer)\\src\\names-data.txt"));
		
			int nameEnd;
			while (true) {				
				String line = rd.readLine();
				if (line == null) break;
				nameEnd = line.indexOf(" ");
				namesHashMap.put(line.substring(0, nameEnd), line); // key = name of that person ||||| value = rankings in every decade
			}
			
			rd.close();
		}
		catch(IOException ex) { System.out.print("error on: " + ex); }
	}
}
