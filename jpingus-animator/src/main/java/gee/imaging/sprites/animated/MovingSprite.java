package gee.imaging.sprites.animated;

import gee.imaging.Renderable;
import gee.imaging.sprites.Sprite;

import java.awt.*;
public interface MovingSprite extends Renderable 
{ 
   public void initSize(int width, int height);
   public void initSprite(int x, int y);
   public void setSprite(Sprite sprite);
   public void select();
   public void unselect();
   public Image getSelectedImage(); 
   public boolean collides(MovingSprite sprite);
   public Rectangle getBounds(boolean offset);
   public Rectangle getFloorBounds(boolean offset);
   public Rectangle getObstacleBounds(boolean offset);
   public String getName();
   public void setName(String name);
	public int getSpeedX() ;
	public void setSpeedX(int speedx);
	public int getSpeedY() ;
	public void setSpeedY(int speedy);
	public int getHeight() ;
	public int getWidth() ;
	public void fall(int speed);
	public int land(int distance);
	public void turnBack();
	public void turnBack(MovingSprite s);
	public void freeze();
	public void unfreeze();
	public boolean isActor() ;
	public void setActor(boolean actor) ;
	public void terminated();
	public boolean isTerminated();
	public Image getCurrentImage() ;
	public void obstacleDetected(int size);
	public void noFloorDecteted();
	public void floorDecteted(int distance);
}
