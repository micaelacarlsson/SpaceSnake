package view.Figures;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 * The main class for figures in the game view. Should be subclassed for all different kinds of figures.
 * Is an observer for events like a new figure added.
 * Is an ActionListener to be able to listen to itself.
 *  
 * @author Gustav
 * @version 2016-02-05
 */

@SuppressWarnings("serial")
public class GameFigure 
extends JComponent
implements Observer, ActionListener
{

	protected double size;
	protected double x,y;

	protected Color color;
	
	//Extra buffer when determining bounds
	int extra=1;

	//Only for testing:
	Timer t;
	
	//Needed to repaint the containing panel and to delete itself
	JComponent parent;
	
	
	/**
	 * Constructor that takes a position (x,y) and a size and generates a GameFigure.
	 * 
	 * @param x		The x-position
	 * @param y		The y-position
	 * @param size	The size of the GameFigure
	 * @param parent	The containing GameView
	 */
    public GameFigure(double x, double y, double size, JComponent parent)
    {
    	
        this.x=x;
        this.y=y;
        this.size=size;
        this.parent=parent;
        
        this.setBounds((int)(x-size/2-extra), (int)(y-size/2-extra), (int)size+2*extra, (int)size+2*extra);
        this.color = new Color(255,0,0);
        
        //For testing:
        t = new Timer(1000/30,this);
        t.start();
    }
    
        
    /**
     * For testing only.
     */
    @Override
	public void actionPerformed(ActionEvent e) {
		x += 0.2*(Math.round(Math.random())*2-1);
		y += 0.2*(Math.round(Math.random())*2-1);
		this.setBounds((int)(x-size/2-extra), (int)(y-size/2-extra), (int)size+2*extra, (int)size+2*extra);
        parent.repaint();
	}
	
    
    /**
     * Move the figure to a new position
     * @param new_x	The new x-position
     * @param new_y	Thenew y-position
     */
    private void move (double new_x, double new_y) {
    	x = new_x;
    	y = new_y;
    	this.setBounds((int)(x-size/2-extra), (int)(y-size/2-extra), (int)size+2*extra, (int)size+2*extra);
        parent.repaint();
	}
    
    
    /**
     * Set a new size for the figure
     * @param new_size	The new size
     */
    private void resize (double new_size) {
    	size = new_size;
    	this.setBounds((int)(x-size/2), (int)(y-size/2), (int)size+extra, (int)size+extra);
    	parent.repaint();
	}
    
    /**
     * Returns the color of itself to be used in the map.
     */
    public Color getColor() {
    	return color;
    }
    public void setColor(Color c) {
    	color = c;
    }
    
    /**
     * Paints itself.
     * @param g
     */
    @Override
    public void paintComponent(Graphics g_in) {
		//super.paintComponent(g);
		
    	Graphics2D g = (Graphics2D)g_in;

    	g.setRenderingHint( RenderingHints.KEY_ANTIALIASING, 
    						RenderingHints.VALUE_ANTIALIAS_ON);
    	
    	g.setColor(Color.WHITE);
        g.fillOval(0, 0, (int)size, (int)size);
        g.setColor(Color.BLACK);
        g.drawOval(0+extra, 0+extra, (int)size, (int)size);
        
        g.setColor(Color.RED);
        String text="?";
        g.setFont(g.getFont().deriveFont((float)(size*0.75)));
        Rectangle2D r = g.getFont().getStringBounds(text, g.getFontRenderContext());
        g.drawString(text, (int)(size/2-r.getCenterX()),(int)(size/2-r.getCenterY()));
        
        //g.drawLine(x + 20, y + 10, x + 20, y + 20);
        //g.drawLine(x + 30, y + 10, x + 30, y + 20);
        
        //g.drawArc(x + 15, y + 15, 20, 20, 180, 180);
        //Graphics2D g2 = (Graphics2D)g;

        //Line2D line = new Line2D.Double(10, 10, 40, 40);
        //g2.setColor(Color.blue);
        //g2.setStroke(new BasicStroke(10));
        //g2.draw(line);
    }
    
    
    /**
     * Used when "the model" sends notifyObservers(arg1).
     */
	@Override //Movement (or something)
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub	
		// if (died) parent.removeItem(this);
		// if (moved) move(new_x, new_y);
		// if (resized) resize(new_size);
	}
	
}