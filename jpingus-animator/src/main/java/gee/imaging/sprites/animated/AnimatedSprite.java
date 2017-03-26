package gee.imaging.sprites.animated;

import gee.imaging.sprites.Sprite;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

public class AnimatedSprite implements MovingSprite {
	protected boolean showDetectors = false;

	private int initFallSpeedX;

	private int initFallSpeedY;

	private int initFallY;

	Sprite selectedSprite;

	Vector associated = null;

	// int x, y, z;
	float x;

	float y;

	int z;

	float speedX = 1;

	float speedY = 1;

	int width, height;

	int offsetX, offsetY;

	boolean useOffset = false;

	String name;

	Sprite sprite;

	boolean actor = false;

	boolean terminated;

	public boolean isActor() {
		return actor;
	}

	public void setActor(boolean actor) {
		this.actor = actor;
	}

	public int x() {
		return x(useOffset);
	}

	public int y() {
		return y(useOffset);
	}

	public int x(boolean useOffset) {
		return useOffset ? getX() + offsetX : getX();
	}

	public int y(boolean useOffset) {
		return useOffset ? getY() + offsetY : getY();
	}

	public boolean collides(MovingSprite sprite) {
		Rectangle b1 = getBounds(false), b2 = sprite.getBounds(false);
		return b1.intersects(b2) || b1.contains(b2) || b2.contains(b1);
	}

	public Rectangle getRealBounds(boolean offset) {
		return new Rectangle(x(offset), y(offset), width, height);
	}

	public Rectangle getBounds(boolean offset) {
		return new Rectangle(x(offset) + width / 6, y(offset), width * 2 / 3,
				height);
	}

	public Rectangle getFloorBounds(boolean offset) {
		return new Rectangle(x(offset) + (width / 4), y(offset) + height - 1,
				width / 2, 8);
	}

	public Rectangle getObstacleBounds(boolean offset) {
		if (speedX > 0)
			return new Rectangle(x(offset) + width - 2, y(offset) + 5, 1,
					height - 5);
		else
			return new Rectangle(x(offset) + 2, y(offset) + 5, 1, height - 5);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int witdth) {
		this.width = witdth;
	}

	public int getX() {
		return (int) x;

	}

	public int getY() {
		return (int) y;
	}

	public void init(int x, int y, int z) {
		setX(x);
		setY(y);
		setZ(z);
		if (associated != null)
			for (Iterator iter = associated.iterator(); iter.hasNext();) {
				AnimatedSprite sp = (AnimatedSprite) iter.next();
				sp.setX(x);
				sp.setY(y);
				sp.setZ(z);
			}

	}

	public boolean contained(int x, int y) {
		return getBounds(useOffset).contains(x, y);
	}

	public String toString() {
		// TODO Auto-generated method stub
		return this.getClass() + "(" + name + "@" + x + ":" + y + ")";
	}

	public void setSprite(Sprite sprite) {
		setWidth(sprite.getWidth());
		setHeight(sprite.getHeight());
		this.sprite = sprite;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void turnBack() {
		setSpeedX(-getSpeedX());
	}

	public int getZ() {

		return z;
	}

	public void setZ(int z) {
		this.z = z;

	}

	public boolean isUseOffset() {
		return useOffset;
	}

	public void setUseOffset(boolean useOffset) {
		this.useOffset = useOffset;
		if (associated != null)
			for (Iterator iter = associated.iterator(); iter.hasNext();) {
				AnimatedSprite sp = (AnimatedSprite) iter.next();
				sp.setUseOffset(useOffset);
			}
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void terminated() {
		// TODO Auto-generated method stub
		terminated = true;
		if (associated != null)
			for (Iterator iter = associated.iterator(); iter.hasNext();) {
				AnimatedSprite sp = (AnimatedSprite) iter.next();
				sp.terminated();
			}
	}

	public boolean isTerminated() {
		return terminated;
	}

	public void initOffset(int offsetx, int offsety) {
		this.offsetX = offsetx;
		this.offsetY = offsety;
		if (associated != null)
			for (Iterator iter = associated.iterator(); iter.hasNext();) {
				AnimatedSprite sp = (AnimatedSprite) iter.next();
				sp.initOffset(offsetx, offsety);
			}
	}

	public void setY(int y) {
		this.y = y;
		if (associated != null)
			for (Iterator iter = associated.iterator(); iter.hasNext();) {
				AnimatedSprite sp = (AnimatedSprite) iter.next();
				sp.setY(y);
			}
	}

	public AnimatedSprite associate(int deltaX, int deltaY) {
		AssociatedSprite m = new AssociatedSprite(this, deltaX, deltaY);
		if (associated == null) {
			associated = new Vector();
		}
		associated.addElement(m);
		m.setUseOffset(isUseOffset());
		m.init(getX(), getY(), getZ());
		return m;
	}

	public void setX(int x) {

		this.x = x;
		if (associated != null)
			for (Iterator iter = associated.iterator(); iter.hasNext();) {
				AnimatedSprite sp = (AnimatedSprite) iter.next();
				sp.setX(x);
			}
	}

	public void free() {
		if (selectedSprite != null)
			selectedSprite.free();
		selectedSprite = null;
	}

	boolean falling = false;

	protected boolean selected = false;

	Image current;

	boolean frozen = false;

	public AnimatedSprite(String name) {
		this.name = name;
	}

	public void setSelectedSprite(Sprite sprite) {
		this.selectedSprite = sprite;
	}

	boolean add = false;

	public Image getCurrentImage() {
		return sprite.getCurrentImage();
	}

	public void showDone() {

	}

	public Image getImage() {
		return sprite.getCurrentImage();
	}

	public void initSprite(int x, int y) {
		if (sprite != null) {
			sprite.setIdx(x);
			sprite.setIdy(y);
		}
		if (selectedSprite != null) {
			selectedSprite.setIdx(x);
			selectedSprite.setIdy(y);
		}
	}

	public Image getSelectedImage() {
		// TODO Auto-generated method stub
		if (selected && selectedSprite != null)
			return selectedSprite.getCurrentImage();
		return null;
	}

	public void select() {
		selected = true;
	}

	public void unselect() {
		selected = false;
	}

	public boolean update(long elapsed) {
		boolean ret = false;
		if (selectedSprite != null)
			selectedSprite.update(elapsed);
		if (sprite != null)
			if (ret = sprite.update(elapsed))
				showDone();
		if (!frozen) {
			x += (elapsed * speedX);
			y += (elapsed * speedY);
		}
		return ret;
	}

	public void renderMap(Graphics2D g, float rate, int x, int y) {
		// TODO Auto-generated method stub
		if (getCurrentImage() != null)
			g.drawImage(getCurrentImage(), (int) (getX() / rate) + x,
					(int) (getY() / rate) + y, (int) (getWidth() / rate),
					(int) (getHeight() / rate), null);
	}

	public void render(Graphics2D g, int x, int y) {
		// TODO Auto-generated method stub

	}

	public void render(Graphics2D g) {
		if (g == null)
			return;
		if (getImage() != null) {
			g.drawImage(getImage(), x(), y(), null);
			if (associated != null)
				for (Iterator iter = associated.iterator(); iter.hasNext();) {
					AssociatedSprite as = (AssociatedSprite) iter.next();
					g.drawImage(as.getSprite().getImage(getSprite().getIdx(),
							getSprite().getIdy()), x() + as.getDeltaX(), y()
							+ as.getDeltaY(), null);
					
				}
		} else {
			g.setColor(Color.RED);
			g.draw3DRect(x(), y(), getWidth(), getHeight(), true);
		}
		if (selected && selectedSprite != null) {
			g.drawImage(selectedSprite.getImage(0, 0), x() - 1, y() - 1, null);
		} else if (selected) {
			g.setColor(Color.CYAN);
			g.drawRoundRect(x() - 1, y() - 1, getWidth() + 2, getHeight() + 2,
					3, 3);
		}
		if (showDetectors) {
			g.setColor(Color.CYAN);
			g.draw(getFloorBounds(true));
			g.setColor(Color.RED);
			g.draw(getObstacleBounds(true));
			// g.setColor(Color.green);
			// g.draw(getBounds(true));
		}
	}

	/**
	 * returns the amount of pixels per second
	 */
	public int getSpeedY() {
		return (int) (1000f * speedY);
	}

	/**
	 * returns the amount of pixels per second
	 */
	public int getSpeedX() {
		return (int) (1000f * speedX);
	}

	/**
	 * The speed is given in pixels per second
	 * 
	 * @param speedy
	 *            pixels per second
	 */
	public void setSpeedY(int speedy) {
		this.speedY = speedy / 1000f;
	}

	/**
	 * The speed is given in pixels per second
	 * 
	 * @param speedx
	 *            pixels per second
	 */
	public void setSpeedX(int speedx) {
		this.speedX = speedx / 1000f;
		if (associated != null)
			for (Iterator iter = associated.iterator(); iter.hasNext();) {
				AnimatedSprite sp = (AnimatedSprite) iter.next();
				sp.setSpeedX(speedx);
			}

	}

	public void fall(int speedy, int speedx) {
		if (!falling) {
			// System.out.println(getName() + " falls");
			initFallY = getY();
			initFallSpeedX = getSpeedX();
			initFallSpeedY = getSpeedY();
			setSpeedX(speedx);
			falling = true;
			setSpeedY(speedy);
		}

	}

	public int land(int distance) {
		setY(getY() + distance);
		if (falling) {
			setSpeedX(initFallSpeedX);
			// System.out.println("initial fall speed:"+initFallSpeedX);
			setSpeedY(initFallSpeedY);
			falling = false;
			return getY() - initFallY;
		}
		return 0;

	}

	public void freeze() {
		frozen = true;
	}

	public void unfreeze() {
		frozen = false;
	}

	public void initSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	public Sprite getSelectedSprite() {
		return selectedSprite;
	}

	public void turnBack(MovingSprite s) {
		if (getSpeedX() > 0 && getX() < s.getX()) {
			turnBack();
		}
		if (getSpeedX() < 0 && getX() > s.getX()) {
			turnBack();
		}
	}

	public void fall(int speed) {
		// TODO Auto-generated method stub
		fall(speed, 0);
	}

	public int getType() {
		// TODO Auto-generated method stub
		return ANIMATED;
	}

	public void obstacleDetected(int size) {
		// TODO Auto-generated method stub

	}

	public void noFloorDecteted() {
		// TODO Auto-generated method stub

	}

	public void floorDecteted(int distance) {
		// TODO Auto-generated method stub
		if (distance > 7) {
			noFloorDecteted();
			return;
		}
	}

}
