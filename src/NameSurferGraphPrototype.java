import java.awt.*;
import java.awt.event.*;

public class NameSurferGraphPrototype extends Panel
	implements NameSurferConstants, ComponentListener {
	
	private int marginSize;
	private int decadeGap;
	private int rank[];
	
	// FOR THE LINE RANKINGS POINT
	private int x1, y1, x2, y2;
	
	public NameSurferGraphPrototype() {		
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		setBackground(Color.white);
		
		// sizes initialization in standard position
		marginSize = GRAPH_MARGIN_SIZE;
		decadeGap = APPLICATION_WIDTH / NDECADES; 
		
		x1 = y1 = x2 = y2 = 0;
		
		addComponentListener(this);		
	}
	
	public void paint(Graphics g) {
		g.drawLine(0, 	marginSize, 	getWidth(), 	marginSize); // northernLine
		g.drawLine(0,	(getHeight() - marginSize),		getWidth(),		(getHeight() - marginSize)); // southernLine
		
		int xPositionOfGapLine = decadeGap;
		for(int x=0; x<10; x++) { // gap lines		
			// get the first point of the line
			Point p1 = new Point((xPositionOfGapLine - decadeGap), (getHeight() * rank[x]) / MAX_RANK);
			x1 = (int) p1.getX();
			y1 = (int) p1.getY();

			// get the second point of the line, then draw it(graph)
			Point p2 = new Point(xPositionOfGapLine, (getHeight() * rank[x + 1]) / MAX_RANK);
			x2 = (int) p2.getX();
			y2 = (int) p2.getY();
			g.drawLine(x1, y1, x2, y2);
			
			g.drawLine(xPositionOfGapLine, marginSize, xPositionOfGapLine, (getHeight() - marginSize));			
			xPositionOfGapLine += decadeGap; // move the x-position
			

		}
		
		for(;;) {
			g.drawLine(0, 	(getHeight() * rank[0]) / MAX_RANK, 	x2, 	y2);
		}
	}
	
	public void drawGraph(int rank[]) {
		this.rank = rank;
		update();
	}
	
	private void update() {
		marginSize = (getHeight() * GRAPH_MARGIN_SIZE) / APPLICATION_HEIGHT;
		decadeGap =  getWidth() / NDECADES;
		
		repaint();
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
