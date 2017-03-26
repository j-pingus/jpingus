package gee.imaging.animator;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import gee.imaging.Renderable;
import gee.imaging.sprites.animated.MovingSprite;

public interface AnimatorDetector {
	public void collisionDetected(MovingSprite sprite,
			MovingSprite anotherSprite);

	public void outOfSight(MovingSprite s1, Rectangle sight);

	public void gettingOutOfSight(MovingSprite s1, Rectangle sight);

	public void setAnimator(Animator anim);

	public void pressed(MouseEvent e);

	public void dragged(MouseEvent e);

	public void released(MouseEvent e);

	public void clicked(MouseEvent e);

	public void mouseOver(Renderable s);

	public void keyPressed(KeyEvent e);

	public void keyReleased(KeyEvent e);

	public void keyTyped(KeyEvent e);

	public void renderDebug(Graphics2D g);
}
