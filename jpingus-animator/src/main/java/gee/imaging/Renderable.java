package gee.imaging;

import java.awt.Graphics2D;
import java.awt.Image;

public interface Renderable {
	public static final int GROUND = 0;

	public static final int STATIC = 1;

	public static final int ANIMATED = 2;

	public static final int SPRITE = 3;

	public boolean update(long elapsed);

	public void render(Graphics2D g);

	public void renderMap(Graphics2D g, float rate, int x, int y);

	public int getType();

	public void initOffset(int offsetx, int offsety);

	public void setUseOffset(boolean useOffset);

	public void free();

	public int getX();

	public int getY();

	public int getZ();

	public void init(int x, int y, int z);

	public boolean contained(int x, int y);
}
