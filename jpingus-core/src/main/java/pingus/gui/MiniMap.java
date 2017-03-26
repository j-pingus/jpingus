package pingus.gui;

import gee.imaging.animator.Animator;

import java.awt.AWTEvent;
import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class MiniMap extends Frame implements Runnable,ComponentListener {
	private Animator animator;
	Graphics2D gBuffer;
	BufferedImage image;
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			runMe = false;
			setVisible(false);
		}
	}


	/** constructeur de la classe Loupe */
	public MiniMap(Animator anim) {
		super("MiniMap");
		
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		animator = anim;
		addComponentListener(this);
		setBounds(150, 150, 250, 250);
		image = new BufferedImage(getWidth(), getHeight(),
				BufferedImage.TYPE_INT_RGB);
		gBuffer = image.createGraphics();
		setVisible(true);
		
	}



	/**
	 * @param point :
	 *            (x, y) renvoie : true si ce point est à l'intérieur de la
	 *            fenêtre false sinon
	 */
	boolean isPointInRect(Point point) {
		int frameX = getX();
		int frameY = getY();
		int frameWidth = getWidth();
		int frameHeight = getHeight();

		return ((point.x >= frameX) & (point.x <= frameX + frameWidth))
				& (point.y >= frameY) & (point.y <= frameY + frameHeight);
	}

	boolean runMe = true;

	/**
	 * 
	 */
	public void run() {
		while (runMe) {
			try {
				// récupérer les coordonnées de la pointe de la souris
				// Point point;
				// point = MouseInfo.getPointerInfo ().getLocation ();

				// if (isPointInRect (point))
				// continue;


				// faire un imprime écran
				Composite c = gBuffer.getComposite(); // backup the old composite
				gBuffer.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
						0.75f));
				gBuffer.setColor(Color.WHITE);
				gBuffer.fillRect(0,0,getWidth(),getHeight());
				gBuffer.setComposite(c);
				animator.renderMap(gBuffer,5,5,getWidth()-10,getHeight()-10);
				
				Graphics g;
				g = getGraphics();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
				g.dispose();
				// faire un rafraîchissement tout les 50 millisecondes
				Thread.sleep(150);
			} catch (InterruptedException exception) {
			}
		}
	}



	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}



	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}



	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		image = new BufferedImage(getWidth(), getHeight(),
				BufferedImage.TYPE_INT_RGB);
		gBuffer = image.createGraphics();
		
	}



	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
