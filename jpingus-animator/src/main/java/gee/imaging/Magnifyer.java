package gee.imaging;

import gee.imaging.animator.Animator;

import java.awt.AWTEvent;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;

import javax.swing.JFrame;

public class Magnifyer extends Frame implements Runnable {
	// Overridden so we can exit on System Close
	private int mode;

	private static final int MODE_SCREENSHOT = 1;

	private static final int MODE_ANIMATOR = 2;

	private Animator animator;

	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			runMe = false;
			setVisible(false);
		}
	}

	/**
	 * Données membres
	 */
	private Image image = null;

	private int x, y;

	/** constructeur de la classe Loupe */
	public Magnifyer(int x, int y) {
		super("Loupe");
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		this.x = x;
		this.y = y;
		// setResizable(false);
		setBounds(50, 50, 250, 250);
		mode = MODE_SCREENSHOT;
		setVisible(true);
	}

	/** constructeur de la classe Loupe */
	public Magnifyer(Animator anim, int x, int y) {
		this(x, y);
		animator = anim;
		mode = MODE_ANIMATOR;
	}

	/**
	 * faire un imprime écran une zone rectangulaire de centre la pointe du
	 * curseur
	 */
	public void getScreenShot() throws AWTException {
		switch (mode) {
		case MODE_ANIMATOR:
			getScreenShot(animator, x, y);

			break;

		default:
			getScreenShot(x, y);
			break;
		}
		return;
	}

	public void getScreenShot(Animator anim, int x, int y) {
		int sourceWidth = getWidth() / 2;
		int sourceHeight = getHeight() / 2;
		ImageProducer sourceI;
		ImageFilter extractFilter;
		ImageProducer producer;
		sourceI = anim.getImage().getSource();
		extractFilter = new CropImageFilter(x - (sourceWidth >> 1), y
				- (sourceHeight >> 1), sourceWidth, sourceHeight);
		producer = new FilteredImageSource(sourceI, extractFilter);
		image =  Toolkit.getDefaultToolkit().createImage(
				producer);

	}

	/**
	 * faire un imprime écran d'une zone rectangulaire de centre la pointe du
	 * curseur avec un facteur d'aggrandissment x2
	 */
	public void getScreenShot(int x, int y) throws AWTException {
		int sourceWidth = getWidth() / 2;
		int sourceHeight = getHeight() / 2;

		Robot robot;
		robot = new Robot();

		Rectangle rectangle;
		rectangle = new Rectangle(x - (sourceWidth >> 1), y
				- (sourceHeight >> 1), sourceWidth, sourceHeight);

		image = robot.createScreenCapture(rectangle);

		return;
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

				Graphics g;
				g = getGraphics();

				// faire un imprime écran
				try {
					getScreenShot();
				} catch (AWTException exception) {
				}
				// afficher l'imprime écran sur le fenêtre
				g.setColor(Color.CYAN);

				if (image != null)
					g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
				else
					g.fillRect(2, 2, getWidth() - 4, getHeight() - 4);

				g.drawString("x:" + x + " y:" + y, 10, 10);
				// libérer le contexte d'affichage
				g.dispose();
				// faire un rafraîchissement tout les 50 millisecondes
				Thread.sleep(150);
			} catch (InterruptedException exception) {
			}
		}
	}

	/**
	 * @param args :
	 *            ligne de commande
	 */
	public static void main(String[] args) {
		// code de l'application
	}

}
