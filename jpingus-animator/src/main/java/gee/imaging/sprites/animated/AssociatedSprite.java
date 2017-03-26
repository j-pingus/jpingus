/**
 * 
 */
package gee.imaging.sprites.animated;

import java.awt.Graphics2D;
import java.awt.Image;

/**
 * @author Gérald
 * 
 */
public class AssociatedSprite extends AnimatedSprite {
	
	int deltaX;

	int deltaY;

	public AssociatedSprite(AnimatedSprite refered, int deltaX, int deltaY) {
		super("Associated");
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	public void setX(int x) {
		// TODO Auto-generated method stub
		super.setX(x + deltaX);
	}

	public void setY(int y) {
		// TODO Auto-generated method stub
		super.setY(y + deltaY);
	}

	public Image getImage() {
		// TODO Auto-generated method stub
		Image img = super.getImage();
		return img;
	}

	public void render(Graphics2D g) {
		if (sprite != null) {
			g.drawImage(getImage(), x(), y(), null);
		}
	}

	public int getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}

	public int getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	}

}
