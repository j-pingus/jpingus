package gee.imaging.background;

import gee.imaging.Renderable;
import gee.imaging.sprites.Sprite;
import gee.imaging.sprites.animated.AlphaSprite;
import gee.imaging.sprites.animated.MovingSprite;

import java.awt.*;
import java.awt.image.*;

public class ScrollingBackground implements Renderable {
	public static final int LEFT = 1, RIGHT = 2, UP = 4, DOWN = 8;

	Sprite img;

	int offsetX, offsetY;

	boolean useOffset = true;

	BufferedImage back;

	Graphics2D gBuffered;

	public void free() {
		gBuffered.dispose();
		gBuffered = null;
		back = null;
	}

	int x, y, width, height, speed = 1, direction = LEFT;

	public void init(Sprite img, int width, int height) {
		this.img = img;
		this.x = 0;
		this.y = 0;
		this.width = width;
		this.height = height;

		back = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		gBuffered = back.createGraphics();

	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public Image getImage() {
		return back;
	}

	public void render(Graphics2D g) {
		img.paint(g, useOffset ? offsetX : 0, useOffset ? offsetY : 0);
		// g
		// .drawImage(img, , null);
	}

	public MovingSprite obstacleToSprite(MovingSprite s) {
		// TODO Auto-generated method stub
		return null;
	}

	public int floorDistance(MovingSprite s) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int obstacleSize(MovingSprite s) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void addImage(Image img, int x, int y) {
		// TODO Auto-generated method stub

	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setUseOffset(boolean useOffset) {
		// TODO Auto-generated method stub
		this.useOffset = useOffset;
	}

	public void initOffset(int offsetx, int offsety) {
		// TODO Auto-generated method stub
		offsetX = offsetx;
		offsetY = offsety;
	}

	public Image getCurrentImage() {
		return back;
	}

	public float renderMap(Graphics2D g, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		render(g);
		return 1.0f;
	}

	public void renderMap(Graphics2D g, float rate, int x, int y) {
		// TODO Auto-generated method stub
		render(g);
	}

	public boolean update(long elapsed) {
		// TODO Auto-generated method stub
		img.paint(gBuffered, 0, 0);
		gBuffered.drawImage(img.getCurrentImage(), 0, 0, x, y, width - x,
				height - y, width, height, null);
		gBuffered.drawImage(img.getCurrentImage(), 0, y, x, height, width - x,
				0, width, height - y, null);

		gBuffered.drawImage(img.getCurrentImage(), x, 0, width, y, 0, height
				- y, width - x, height, null);
		gBuffered.drawImage(img.getCurrentImage(), x, y, width, height, 0, 0,
				width - x, height - y, null);

		if ((direction & LEFT) == LEFT) {
			x -= speed;
			if (x < 0)
				x = width - 1;
		}

		if ((direction & RIGHT) == RIGHT) {
			x += speed;
			if (x > width)
				x = 1;
		}

		if ((direction & UP) == UP) {
			y += speed;
			if (y > height)
				y = 1;
		}
		if ((direction & DOWN) == DOWN) {
			y -= speed;
			if (y < 0)
				y = height - 1;
		}
		return false;
	}

	public int getType() {
		// TODO Auto-generated method stub
		return ANIMATED;
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
		return -150;
	}

	public void init(int x, int y, int z) {
		// TODO Auto-generated method stub

	}

	public boolean contained(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

}