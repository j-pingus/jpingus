/**
 * 
 */
package gee.imaging.background;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.text.Format;
import java.util.Iterator;
import java.util.Vector;

import gee.imaging.Renderable;
import gee.imaging.SpriteContainer;
import gee.imaging.sprites.Sprite;
import gee.imaging.sprites.animated.MovingSprite;

/**
 * @author Gérald
 * 
 */
public class StaticSpriteBackground extends DummyBackground implements
		SpriteContainer, MovingBackground {
	Vector groundPieces = new Vector();
	public String toString() {
		// TODO Auto-generated method stub
		return "groundpieces:"+groundPieces.size();
	}
	boolean ground = true;

	boolean background = true;

	boolean spriteBack = true;

	boolean spriteFront = true;

	boolean redrawAlways = false;

	BufferedImage back;

	Graphics2D gBuffered;

	Color backColor;

	boolean rendered;

	int width, height;
	protected StaticSpriteBackground() {
		// TODO Auto-generated constructor stub
	}
	public  StaticSpriteBackground( int width, int height) {
		init(null,width,height);
	}
	public void init(Sprite img, int width, int height) {
		this.width = width;
		this.height = height;
		back = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		gBuffered = back.createGraphics();
		backColor = new Color(0, 0, 0, 0);
		gBuffered.setBackground(backColor);
	}

	public void addSprite(Renderable sp) {
		// TODO Auto-generated method stub
		for (int i = 0; i < groundPieces.size(); i++) {
			if (((Renderable) groundPieces.elementAt(i)).getZ() > sp.getZ()) {
				groundPieces.insertElementAt(sp, i);
				// System.out.println(sprites);
				return;
			}
		}
		groundPieces.addElement(sp);
	}

	public boolean update(long elapsed) {
		// TODO Auto-generated method stub
		if (redrawAlways) {
			back = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			gBuffered = back.createGraphics();
			backColor = new Color(0, 0, 0, 0);
			gBuffered.setBackground(backColor);
		}
		if (!rendered || redrawAlways) {
			for (int i = 0; i < groundPieces.size(); i++) {
				Renderable sp = (Renderable) groundPieces.elementAt(i);
					sp.setUseOffset(false);
					sp.update(elapsed);
					sp.render(gBuffered);
			}
//			getPixels(new Rectangle(0, 0, width, height));
			rendered = true;
		}
		return rendered;
	}

	public void getPixels(Rectangle r) {
	}

	public float renderMap(Graphics2D g, int x, int y, int width, int height) {
		Composite c = g.getComposite(); // backup the old composite
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				0.75f));
		float w = back.getWidth();
		float h = back.getHeight();

		float wr = w / (float) width;
		float hr = h / (float) height;
		float x2;
		float y2;
		float rate;
		if (hr < wr) {
			rate = wr;
		} else {
			rate = hr;
		}

		w = w / rate;
		h = h / rate;
		x2 = (width - w) / 2.0f;
		y2 = (height - h) / 2.0f;
		g.drawImage(back, x, y, (int) w, (int) h, Color.BLUE, null);
		g.setComposite(c);
		return rate;

	}

	public void render(Graphics2D g) {
		if(back!=null)
		g.drawImage(back, useOffset ? offsetX : 0,
				useOffset ? offsetY : 0, null);
//		boolean drawn = false;
//		for (int i = 0; i < groundPieces.size(); i++) {
//			MovingSprite sp = (MovingSprite) groundPieces.elementAt(i);
//			if (sp.getZ() < 0) {
//				if (spriteBack)
//					sp.render(g);
//			} else {
//				if (!drawn) {
//					if (ground)
//					drawn = true;
//				}
//			}
//		}
		// renderSmall(g);
	}


	public void removeShape(Shape s) {
		Composite old = gBuffered.getComposite();
		AlphaComposite rep = AlphaComposite.getInstance(AlphaComposite.CLEAR);
		gBuffered.setComposite(rep);
		gBuffered.setColor(backColor);
		gBuffered.fill(s);
		gBuffered.setComposite(old);
	}

	public void addShape(Shape s, Color c) {
		gBuffered.setColor(c);
		gBuffered.fill(s);
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public boolean isGround() {
		return ground;
	}

	public void setGround(boolean ground) {
		this.ground = ground;
	}

	public boolean isSpriteBack() {
		return spriteBack;
	}

	public void setSpriteBack(boolean spriteBack) {
		this.spriteBack = spriteBack;
	}

	public boolean isSpriteFront() {
		return spriteFront;
	}

	public void setSpriteFront(boolean spriteFront) {
		this.spriteFront = spriteFront;
	}

	public void reset() {
		// TODO Auto-generated method stub
		rendered = false;
		gBuffered.dispose();
		back = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		gBuffered = back.createGraphics();
		backColor = new Color(0, 0, 0, 0);
		gBuffered.setBackground(backColor);
	}

	public Renderable mouseOver(int x, int y) {
		for (Iterator iter = groundPieces.iterator(); iter.hasNext();) {
			Renderable sp = (Renderable) iter.next();
			if (sp.getZ() == 0 && !background)
				continue;
			if (sp.getZ() > 0 && !spriteFront)
				continue;
			if (sp.getZ() < 0 && !spriteBack)
				continue;
			if (sp.contained(x, y))
				return sp;
		}
		return null;
	}

	public boolean isRedrawAlways() {
		return redrawAlways;
	}

	public void setRedrawAlways(boolean redrawAlways) {
		this.redrawAlways = redrawAlways;
	}

	public void initOffset(int offsetx, int offsety) {
		// TODO Auto-generated method stub
		super.initOffset(offsetx, offsety);
		for (int i = 0; i < groundPieces.size(); i++) {
			Renderable sp = (Renderable) groundPieces.elementAt(i);
			sp.setUseOffset(true);
			sp.initOffset(offsetx, offsety);
		}
	}
	public int floorDistance(MovingSprite s) {
		Rectangle r = s.getFloorBounds(false);
		for (int y = 0; y < r.height; y++) {
			for (int x = 0; x < r.width; x++) {
				if (x + r.x >= width || y + r.y >= height)
					return r.height - 1;
				if (x + r.x < 0 || y + r.y < 0)
					return r.height - 1;
				int c = back.getRGB(r.x + x, r.y + y) & 0xff000000 >> 24;
				if (c != 0)
					return y;
			}
		}
		return r.height - 1;
	}

	public int obstacleSize(MovingSprite s) {
		Rectangle r = s.getObstacleBounds(false);
		int ret = r.height;
		int ret2 = r.height;
		int x = 0;
		String msg = "";
		for (int y = r.height - 1; y >= 0; y--) {
			if (x + r.x >= width || y + r.y >= height)
				return 0;
			if (x + r.x < 0 || y + r.y < 0)
				return 0;
			int c = (back.getRGB(r.x + x, r.y + y) & 0xff000000) ;
			msg+= "-"+Integer.toHexString(c);
		}
		System.out.println(msg);
		for (int y = r.height - 1; y >= 0; y--) {
			if (x + r.x >= width || y + r.y >= height)
				return 0;
			if (x + r.x < 0 || y + r.y < 0)
				return 0;
			int c = back.getRGB(r.x + x, r.y + y) & 0xff000000 >> 24;
			if (c == 0) {
				ret = r.height - (y + 1);
				break;
			}
		}
		for (int y = 0; y < r.height; y++) {
			if (x + r.x >= width || y + r.y >= height)
				return 0;
			if (x + r.x < 0 || y + r.y < 0)
				return 0;
			int c = back.getRGB(r.x + x, r.y + y) & 0xff000000 >> 24;
			if (c == 0) {
				ret2 = y;
				break;
			}
		}
		if (ret2 > ret)
			return ret2;
		return ret;
	}

	public Vector getSprites() {
		return groundPieces;
	}

	public void addImage(Image img, int x, int y) {
		gBuffered.drawImage(img,x,y,null);
		
	}

	public void free() {
		// TODO Auto-generated method stub
		
	}

	public void renderMap(Graphics2D g, float rate, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void removeSprite(Renderable sp) {
		groundPieces.removeElement(sp);
	}

	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void init(int x, int y, int z) {
		// TODO Auto-generated method stub
		
	}
}