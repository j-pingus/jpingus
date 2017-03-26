package gee.imaging.background;

import gee.imaging.Renderable;
import gee.imaging.sprites.Sprite;
import gee.imaging.sprites.animated.MovingSprite;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;

public class DummyBackground implements MovingBackground {
	Color c;

	Component window;

	int offsetX, offsetY;

	boolean useOffset = true;

	protected DummyBackground() {

	}

	public DummyBackground(Component window, Color c) {
		// TODO Auto-generated constructor stub
		this.window = window;
		this.c = c;
	}

	public void init(Sprite img, int width, int height) {
		// TODO Auto-generated method stub

	}

	public void reset() {
		// TODO Auto-generated method stub

	}

	public int floorDistance(MovingSprite s) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int obstacleSize(MovingSprite s) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void removeShape(Shape s) {
		// TODO Auto-generated method stub

	}

	public void addImage(Image img, int x, int y) {
		// TODO Auto-generated method stub

	}

	public void addShape(Shape s, Color c) {
		// TODO Auto-generated method stub

	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void free() {
		// TODO Auto-generated method stub

	}

	public Renderable mouseOver(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	public float renderMap(Graphics2D g, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void preRender(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	public void render(Graphics2D g) {
		g.setColor(c);
		g.fillRect(0, 0, window.getWidth(), window.getHeight());
	}

	public void renderMap(Graphics2D g, float rate, int x, int y) {
		// TODO Auto-generated method stub

	}

	public void postRender(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.drawRect(1,3,window.getWidth()-4,window.getHeight()-4);
		g.setColor(Color.GRAY);
		g.drawRect(2,2,window.getWidth()-4,window.getHeight()-4);
}

	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	public Image getCurrentImage() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setUseOffset(boolean useOffset) {
		this.useOffset = useOffset;
	}

	public void initOffset(int offsetx, int offsety) {
		offsetX = offsetx;
		offsetY = offsety;
	}

	public int getType() {
		// TODO Auto-generated method stub
		return GROUND;
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
		return -100;
	}

	public void init(int x, int y, int z) {
		// TODO Auto-generated method stub

	}

	public boolean contained(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(long elapsed) {
		// TODO Auto-generated method stub
		return false;
	}

}
