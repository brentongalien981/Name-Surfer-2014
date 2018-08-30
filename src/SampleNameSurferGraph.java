import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class SampleNameSurferGraph extends Applet 
	implements NameSurferConstants, ComponentListener {
	
	private int marginSize;
	private int decadeGap;
	
	public void init() {		
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		
		// sizes initialization in standard position
		marginSize = GRAPH_MARGIN_SIZE;
		decadeGap = APPLICATION_WIDTH / NDECADES; 
		
		addComponentListener(this);		
	}
	
	public void paint(Graphics g) {
		g.drawLine(0, 	marginSize, 	getWidth(), 	marginSize); // northernLine
		g.drawLine(0,	(getHeight() - marginSize),		getWidth(),		(getHeight() - marginSize)); // southernLine
		
		int xPositionOfGapLine = decadeGap;
		for(int x=0; x<10; x++) { // gap lines			
			g.drawLine(xPositionOfGapLine, marginSize, xPositionOfGapLine, (getHeight() - marginSize));			
			xPositionOfGapLine += decadeGap; // move the x-position
		}
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
