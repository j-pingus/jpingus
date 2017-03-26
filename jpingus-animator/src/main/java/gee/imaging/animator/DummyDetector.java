package gee.imaging.animator;

import gee.imaging.Renderable;
import gee.imaging.sprites.animated.MovingSprite;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class DummyDetector implements AnimatorDetector {

	public void collisionDetected(MovingSprite sprite,
			MovingSprite anotherSprite) {
		// TODO Auto-generated method stub

	}

	public void obstacleDetected(MovingSprite sprite, int size) {
		// TODO Auto-generated method stub

	}

	public void noFloorDecteted(MovingSprite sprite) {
		// TODO Auto-generated method stub

	}

	public void floorDecteted(MovingSprite s1, int distance) {
		// TODO Auto-generated method stub

	}

	public void outOfSight(MovingSprite s1, Rectangle sight) {
		// TODO Auto-generated method stub

	}

	public void gettingOutOfSight(MovingSprite s1, Rectangle sight) {
		// TODO Auto-generated method stub

	}

	public void setAnimator(Animator anim) {
		// TODO Auto-generated method stub

	}

	public void pressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void dragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void released(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void clicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseOver(Renderable s) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void renderDebug(Graphics2D g) {
		// TODO Auto-generated method stub

	}

}
