package pingus.gui.applet;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import pingus.JPingus;

import gee.imaging.Renderable;
import gee.imaging.animator.Animator;
import gee.imaging.animator.AnimatorDetector;
import gee.imaging.sprites.animated.AnimatedSprite;
import gee.imaging.sprites.animated.MovingSprite;

public class SampleCollisionDetector implements AnimatorDetector {
	private Animator anim;

	public SampleCollisionDetector() {

	}

	public void collisionDetected(MovingSprite s1, MovingSprite s2) {
		JPingus _s1 = (JPingus) s1;
		JPingus _s2 = (JPingus) s2;

		// System.err.println(s1+" collides "+s2);
		if (_s1.getState() == JPingus.internalStates.WALKER) {
			if (_s2.getState() == JPingus.internalStates.BLOCKER)
				s1.turnBack(s2);
		} else if (_s2.getState() == JPingus.internalStates.WALKER) {
			if (_s1.getState() == JPingus.internalStates.BLOCKER)
				s2.turnBack(s1);
		}

	}

	public void noFloorDecteted(MovingSprite s1) {
		((JPingus) s1).initFaller();
		if (s1.equals(current))
			current = null;
		// anim.selectedMovingSprite=null;
		// TODO Auto-generated method stub

	}

	public void floorDecteted(MovingSprite s1, int distance) {
		int fall = s1.land(distance);
		AnimatedSprite theMover = null;
		if (fall > 0) {
//			System.out.println(s1 + " falled of " + fall);
			((JPingus) s1).initWalker(s1.getSpeedX());
		}

	}

	public void outOfSight(MovingSprite s1, Rectangle sight) {
//		System.out.println(s1 + " is out of sight!");
		anim.removeSprite(s1);
	}

	public void gettingOutOfSight(MovingSprite s1, Rectangle sight) {
//		System.out.println(s1 + " is getting out of sight!");
		// anim.removeSprite(s1);
	}

	public void setAnimator(Animator anim) {
		this.anim = anim;
	}

	public void obstacleDetected(MovingSprite sprite, int size) {
		// TODO Auto-generated method stub
		int sx = sprite.getSpeedX();
		if (size > sprite.getHeight() / 2.5) {
			if (sx > 0) {
				sprite.initSprite(0, 0);
				sprite.setSpeedX(-sx);
			} else {
				sprite.initSprite(0, 1);
				sprite.setSpeedX(-sx);
			}
		} else if (size > 0) {
			sprite.init(sprite.getX(), sprite.getY() - size, 5);
		}

	}

	public void pressed(MouseEvent e) {
//		System.out.println("pressed : " + current);
	}

	public void dragged(MouseEvent e) {
//		System.out.println("dragged : " + current);

	}

	public void action() {
		if (current != null) {
			JPingus thePingus = (JPingus) current;
			switch (keyCode) {
			case 37: // left
				thePingus.initWalker(JPingus.LEFT);
				break;
			case 39: // RIGHT
				if (thePingus != null)
					thePingus.initWalker(JPingus.RIGHT);
				break;
			case 83: // s
				if (thePingus != null)
					thePingus.initBlocker();
				break;
			case 66:// Bridge
				thePingus.initBridger();
				break;
			case 68:// Dig
				thePingus.initDigger();
				break;
			case 77:// Mine
				thePingus.initMiner();
				break;
			default:
				break;
			}
		}
	}

	public void released(MouseEvent e) {
//		System.out.println("released : " + current);

	}

	public int getKeyCode() {
		return keyCode;
	}

	public Renderable getCurrent() {
		return current;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

	int keyCode = 0;

	public void clicked(MouseEvent e) {
//		System.out.println("clicked : " + current);
		action();
	}

	Renderable current;

	public void mouseOver(Renderable s) {
		// TODO Auto-generated method stub
		if (current != null)
			((MovingSprite)current).unselect();
		current = s;
		if (current != null)
			((MovingSprite)current).select();
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
