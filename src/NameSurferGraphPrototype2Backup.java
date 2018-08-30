import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class NameSurferGraphPrototype2Backup extends Panel
	implements NameSurferConstants, ComponentListener {
	
	private int marginSize; // size of top and bottom margins
	private int decadeGap; // width that separates the decades
	private int rank[];
	private int graphWidth;
	private int graphHeight;
	private int drawableGraphHeight;
	
	// FOR THE LINE RANKINGS POINT
	private int x1, y1, x2, y2;	
	private String name;
	private HashMap<String, int[]> searchedNamesHashMap;
	
	public NameSurferGraphPrototype2Backup() {		
		setBackground(new Color(255, 255, 240));
		
		// these are just the starting-point sizes
		// these will change as the frame updates
		marginSize = GRAPH_MARGIN_SIZE;
		decadeGap = APPLICATION_WIDTH / NDECADES; 
		graphWidth = getWidth();
		graphHeight = getHeight();
		
		rank = new int[11];
		rank[0] = -1;
		
		addComponentListener(this);		
		
		searchedNamesHashMap = new HashMap<>();
	}
	
	public void addEntry(NameSurferEntryPrototype entry) {
		// You fill this in //
	}
	
	private void drawBackgroundGraph(Graphics g) {
		// northernLine
		g.drawLine(0, 	marginSize, 	graphWidth, 	marginSize); 
		// southernLine
		g.drawLine(0,	(graphHeight - marginSize),		graphWidth,		(graphHeight - marginSize)); 
		
		// vertical decade lines
		int xthDecadeLine = 0;
		for(int x=0; x<NDECADES; x++) {
			g.drawLine(xthDecadeLine, 	0, 	xthDecadeLine, 		graphHeight);
			xthDecadeLine += decadeGap; // move to next decade
		}
	}
	
	private void plotNameRankings(Graphics g) {
		// if a legitimate name entry has been searched
		if(rank[0] != -1) {
			
			int xCoordinateOfLastPoint = decadeGap; // basically, this is the x-coordinate from which the next point will connect to
			
			for(int x=0; x<10; x++) { 
				// get the first point (rank during the decade)
				x1 = xCoordinateOfLastPoint - decadeGap;	
				y1 = ((drawableGraphHeight * rank[x]) / MAX_RANK) + marginSize; // calculate the ratio of the -- ACTUAL RANK : RANK ON THE SCREEN
				g.drawString("" + rank[x], x1, y1);
			
				// get the second point (rank during the decade)
				x2 = xCoordinateOfLastPoint;
				y2 = ((drawableGraphHeight * rank[x+1]) / MAX_RANK) + marginSize;	
				
				// connect the 2 points
				g.drawLine(x1, y1, x2, y2);
				g.drawString("" + rank[x+1], x2, y2);
				
				// move the x-position
				xCoordinateOfLastPoint += decadeGap; 
			}
		}
	}
	
	public void paint(Graphics g) {
		drawBackgroundGraph(g);
		plotNameRankings(g);
		
	}
	
	// change this method's name
	public void collectDataOfPerson(String name, int rankings[]) {
		this.name = name;
		rank = rankings;
		update();
	}
	
	private void update() {
		decadeGap =  getWidth() / NDECADES;		
		marginSize = (getHeight() * GRAPH_MARGIN_SIZE) / APPLICATION_HEIGHT;
		graphWidth = getWidth();
		graphHeight = getHeight();
		drawableGraphHeight = getHeight() - (marginSize * 2);
		repaint();
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
