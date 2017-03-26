package pingus.pieces.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;

import gee.imaging.sprites.animated.AnimatedSprite;

public class TextScroller extends AnimatedSprite {

	String text[];
	int deltas[];

	Font font;

	Component comp;

	int lineHeight;

	int width=1;

	int height=1;

	/**
	 * Default bottom to up
	 * 
	 * @param text
	 *            the text to show
	 * @param speed
	 *            in pixels per second
	 */
	public TextScroller(Component comp, String text[], Font font, int speed) {
		super("TextScroller");
		setSpeedX(0);
		setSpeedY(-speed);
		this.text = text;
		if (font != null) {
			this.font = font;
		} 
		this.comp = comp;
		setX(1);
		setY(1);
		setZ(1500);
		setActor(true);
	}

	public Rectangle getBounds(boolean offset) {
		// TODO Auto-generated method stub
		return new Rectangle(x(offset), y(offset), width, height);
	}
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		try {
			if(deltas==null)calcDimensions(g);
			for (int i = 0; i < text.length; i++) {
				String _tx = text[i];
				g.setColor(Color.BLACK);
				g.drawString(_tx,x()+deltas[i]+1,(y()+i*lineHeight)+1);
				g.setColor(Color.WHITE);
				g.drawString(_tx,x()+deltas[i],y()+i*lineHeight);
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void calcDimensions(Graphics g) {
		if(font==null)font=comp.getFont();
		FontMetrics fm = comp.getFontMetrics(font);
		if(g==null)return;
		for (int i = 0; i < text.length; i++) {
			String _tx = text[i];
			Rectangle2D r = fm.getStringBounds(_tx, comp.getGraphics());
			if (r.getWidth() > width)
				width = (int) r.getWidth();
			if (r.getHeight() > lineHeight)
				lineHeight = (int) r.getHeight();

		}
		height = lineHeight * text.length;
		deltas = new int[text.length];
		for (int i = 0; i < text.length; i++) {
			String _tx = text[i];
			Rectangle2D r = fm.getStringBounds(_tx, comp.getGraphics());
			deltas[i]=(int)(width-r.getWidth())/2;
		}
		setX((comp.getWidth()-width)/2);
		setY(comp.getHeight()-1);
		
	}

}
