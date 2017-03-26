package gee.imaging.background;

import gee.imaging.Renderable;
import gee.imaging.sprites.Sprite;

import java.awt.*;

public class StaticBackground implements Renderable {
	Sprite sprite;

	int width, height;

	int offsetX, offsetY;

	boolean useOffset = true;

	public void init(Sprite sp, int width, int height) {
		this.width = width;
		this.height = height;
		setSprite(sp);
	}

	public void render(Graphics2D g) {
		sprite.paint(g,useOffset ? offsetX : 0,
				useOffset ? offsetY : 0);
	}

	public void setUseOffset(boolean useOffset) {
		this.useOffset = useOffset;
	}

	public void initOffset(int offsetx, int offsety) {
		offsetX = offsetx;
		offsetY = offsety;
	}

	public Image getCurrentImage() {
		return null;
	}

	public void renderMap(Graphics2D g, float rate, int x, int y) {
		// TODO Auto-generated method stub
		return;
	}

	private void setSprite(Sprite sp) {
		this.sprite = sp;
		this.sprite.setTiled(width, height, true);
	}

	public boolean update(long elapsed) {
		// TODO Auto-generated method stub
		return sprite.update(elapsed);
	}

	public int getType() {
		// TODO Auto-generated method stub
		return STATIC;
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

	public void free() {
		// TODO Auto-generated method stub
		
	}

}