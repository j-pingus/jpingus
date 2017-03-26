package pingus.behaviours;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import pingus.Actions;
import pingus.JPingus;
import pingus.JPingus.internalStates;
import pingus.gui.MiniMap;
import pingus.level.Level;
import pingus.pieces.Exit;
import pingus.pieces.Trap;
import pingus.pieces.ground.Liquid;
import pingus.pieces.ground.Solid;
import pingus.pieces.gui.ActionSprite;
import pingus.pieces.gui.LevelSprite;

import gee.imaging.Magnifyer;
import gee.imaging.Renderable;
import gee.imaging.animator.Animator;
import gee.imaging.animator.AnimatorDetector;
import gee.imaging.sprites.animated.MovingSprite;

public class LevelDetector implements AnimatorDetector {
	private Animator animator;

//	private boolean loading = false;

	Renderable current;

	ActionSprite currentAction = null;

	Level current_level;
	LevelSprite currentLevel;


	public LevelDetector(Level level) {
		current_level = level;
	}

	public void collisionDetected(MovingSprite s1, MovingSprite s2) {
		// System.out.println(s1.getClass() + " collides " + s2.getClass());
		if (s1.getClass() == JPingus.class && s2.getClass() == Trap.class) {
			JPingus pingus = (JPingus) s1;
			Trap trap = (Trap) s2;
			if (pingus.x() >= trap.x()) {
				trap.initTrap();
				pingus.die(JPingus.DEATH_FALL);
			}

		}
		if (s1.getClass() == JPingus.class && s2.getClass() == JPingus.class) {
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
		if (s1.getClass() == JPingus.class && s2.getClass() == Liquid.class) {
			if (s1.getY() >= s2.getY()) {
				((JPingus) s1).initSwimmer((Liquid) s2);
				// System.out.println("Pingus swimming " + s1+ " in "+s2);
				s1.land(0);
				if (s1.getY() >= s2.getY() + 22) {
					s1.setSpeedY(-1);
				} else if (s1.getY() <= s2.getY() + 12) {
					s1.setSpeedY(1);
				}

			}

		}
		if (s1.getClass() == JPingus.class && s2.getClass() == Solid.class) {
			JPingus _s1 = (JPingus) s1;
			if(_s1.getState()==JPingus.internalStates.DIGGER){
				_s1.initWalker(_s1.getSpeedX());
			}
			int delta = 32 - (s2.getY() - s1.getY());
			if (delta > JPingus.MIN_OBS_SIZE)
				((JPingus) s1).turnBack(s2);

		}
		if (s1.getClass() == JPingus.class && s2.getClass() == Exit.class) {
			JPingus _s1 = (JPingus) s1;

			_s1.initSaved();
		}

	}


	public void outOfSight(MovingSprite s1, Rectangle sight) {
		// System.out.println(s1 + " is out of sight!");
		animator.removeSprite(s1);
	}

	public void gettingOutOfSight(MovingSprite s1, Rectangle sight) {
		// System.out.println(s1 + " is getting out of sight!");
		if(s1.getClass()==JPingus.class){
			((JPingus)s1).die(JPingus.DEATH_FALL);
		}else{
			s1.terminated();
		}
		// anim.removeSprite(s1);
	}

	public void setAnimator(Animator anim) {
		this.animator = anim;
	}

	public void obstacleDetected(MovingSprite sprite, int size) {
		// TODO Auto-generated method stub
		sprite.obstacleDetected(size);
	}

	boolean pressed;

	public void pressed(MouseEvent e) {
		if (current != null && current.getClass() == ActionSprite.class) {
			return;
		}
		if (current != null && current.getClass() == JPingus.class) {
			return;
		}
		// pressed = true;
		// ((Background) anim.getBackground()).setRedrawAlways(true);
		// if (current == null)
		// return;
		// System.out.println(current + " pressed");
	}

	public void dragged(MouseEvent e) {
		if (current != null && current.getClass() == ActionSprite.class) {
			return;
		}
		if (current != null && current.getClass() == JPingus.class) {
			return;
		}
		if (current == null)
			return;
		// System.out.println(current + " dragged");
		// current.init(e.getX() + anim.getOffsetX(),
		// e.getY() + anim.getOffsetY(), current.getZ());
	}

	public void released(MouseEvent e) {
		if (current != null && current.getClass() == ActionSprite.class) {
			return;
		}
		if (current != null && current.getClass() == JPingus.class) {
			return;
		}
		// pressed = false;
		// ((Background) anim.getBackground()).setRedrawAlways(false);
		// if (current == null)
		// return;
		// System.out.println(current + " released");

	}

	public void clicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {

			Thread Application = new Thread(new Magnifyer(animator, e.getX(), e
					.getY()));
			Application.start();
			return;
		}
		if (current == null)
			return;
		if (current.getClass() == ActionSprite.class) {
			if (currentAction != null) {
				currentAction.unselect();
			}
			currentAction = (ActionSprite) current;
			currentAction.select();
		}
		if (current.getClass() == JPingus.class) {
			JPingus pingus = (JPingus) current;
			if (currentAction != null) {
				
				if (currentAction.getOne()) {
					currentAction.getAction().doAction(pingus);
				}
			}
		}

	}

	public void mouseOver(Renderable s) {
		if (pressed)
			return;
		if (current != null && current.getClass() != ActionSprite.class)
			((MovingSprite) current).unselect();
		if (s == null) {
			current = null;
			return;
		}
		// System.out.println("over "+s);
		current = s;
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// System.err.println("1"+e);
		if (e.getKeyChar() == 'D' || e.getKeyChar() == 'd') {
			JPingus pingus = new JPingus(current_level.getBack());
			pingus.init(current_level.getEntrance(0));
			animator.addSprite(pingus);

		}
		if (e.getKeyChar() == 'M' || e.getKeyChar() == 'm') {
			Thread Application = new Thread(new MiniMap(animator));
			Application.start();
		}
		if (e.getKeyChar() == '+') {
			animator.accelerate();
			
		}
		if (e.getKeyChar() == '-') {
			animator.decelerate();
			
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		// System.err.println("2"+e);
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		// System.err.println("3"+e);
	}

	public void renderDebug(Graphics2D g) {
		Enumeration sprites = animator.getSprites();
		int pingus = 0, saved = 0;
		while (sprites.hasMoreElements()) {
			Renderable sp = (Renderable) sprites.nextElement();
			if (sp.getClass() == JPingus.class) {
				if (((JPingus) sp).getState() == JPingus.internalStates.SAVED)
					saved++;
				pingus++;
			}

		}
		g.setColor(Color.WHITE);
		g.drawString("pingus/saved:" + pingus + "/" + saved, 500, 24);
		// TODO Auto-generated method stub

	}

	public Animator getAnimator() {
		return animator;
	}


}
