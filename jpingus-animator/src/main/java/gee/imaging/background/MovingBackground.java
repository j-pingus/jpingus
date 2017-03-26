package gee.imaging.background;

import gee.imaging.Renderable;
import gee.imaging.Ressourcer;
import gee.imaging.sprites.Sprite;
import gee.imaging.sprites.animated.AlphaSprite;
import gee.imaging.sprites.animated.MovingSprite;

import java.awt.*;

public interface MovingBackground extends Renderable {
	public void init(Sprite img, int width, int height);

	public void reset();

	public int floorDistance(MovingSprite s);

	public int obstacleSize(MovingSprite s);

	public void removeShape(Shape s);

	public void addShape(Shape s, Color c);
	
	public void addImage(Image r,int x,int y);
	
	public int getWidth();

	public int getHeight();

	public void free();

	public Renderable mouseOver(int x, int y);

	public float renderMap(Graphics2D g,int x, int y, int width, int height) ;
}
