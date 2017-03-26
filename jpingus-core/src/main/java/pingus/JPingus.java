/**
 * 
 */
package pingus;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import pingus.level.Entrance;
import pingus.pieces.ground.Liquid;
import pingus.ressource.Ressource;
import pingus.ressource.RessourceLocator;

import gee.audio.AudioClipLoop;
import gee.imaging.Ressourcer;
import gee.imaging.background.MovingBackground;

import gee.imaging.sprites.animated.AnimatedSprite;
import gee.imaging.sprites.animated.BackgroundImpacter;

/**
 * @author Gérald
 * 
 */
public class JPingus extends BackgroundImpacter {
	public enum internalStates {
		NONE,WALKER, BLOCKER, BRIDGER, BRIDGER_FINISH, DIGGER, FALLER, MINER, BASHER, SAVING, SAVED, BOMBER, DEAD, SWIMMER, FLOATER, FALLED, CLIMBER, JUMPER
	};
	public final static int MIN_OBS_SIZE = 3;

	private static final int SPEED_GRAVITY = 10;

	private static final int SPEED_JUMP_Y = -210;

	private static final int SPEED_JUMP_X = 180;

	public static final int SPEED_UP = -20;

	public static final int SPEED_FALL = 70;

	public static final int SPEED_FLOAT = 20;

	private static final int SPEED_LEFT_WALKER = -25;

	private static final int SPEED_RIGHT_WALKER = 25;

	public static final int SPEED_LEFT_MINER = -2;

	public static final int SPEED_LEFT_BRIDGER = -4;

	public static final int SPEED_RIGHT_BRIDGER = 4;

	public static final int SPEED_RIGHT_MINER = 2;

	public static final int SPEED_LEFT_BASHER = SPEED_LEFT_WALKER;

	public static final int SPEED_RIGHT_BASHER = SPEED_RIGHT_WALKER;


	public static final boolean JUMP_UP = true;

	public static final boolean JUMP_DOWN = false;

	public static final int DEATH_FALL = 1;

	public static final int DEATH_EXPLODE = 2;

	public static final int LEFT = -1;

	public static final int RIGHT = 1;

	public static final int STEP_BRIDGER_HEIGHT = 2;

	public static final int STEP_BRIDGER_WIDTH = 16;

	public static final int BOMBER_RADIUS = 62;

	internalStates state = internalStates.WALKER;

	internalStates nextState=internalStates.NONE;

	boolean jump = false;

	boolean climber = false;

	boolean floater = false;

	boolean verbose = false;

	int count_bash, count_bomb = 0;

	Ressource spriteSwimmer = RessourceLocator.findRessource("datafile",
			"pingus", "Pingus/basher0", "ROT0");

	Ressource spriteMiner = RessourceLocator.findRessource("datafile",
			"pingus", "Pingus/miner0", "ROT0");

	Ressource arrow = RessourceLocator.findRessource("datafile", "pingus",
			"other/arrows", "ROT0");

	int steps_bridger = 0;

	Ressource spriteDigger = RessourceLocator.findRessource("datafile",
			"pingus", "Pingus/digger0", "ROT0");

	Ressource spriteFloaterLayer = RessourceLocator.findRessource("datafile",
			"pingus", "Pingus/floaterlayer", "ROT0");

	Ressource spriteBridger = RessourceLocator.findRessource("datafile",
			"pingus", "Pingus/bridger0", "ROT0");

	Ressource spriteWalker = RessourceLocator.findRessource("datafile",
			"pingus", "Pingus/walker0", "ROT0");

	Ressource spriteBlocker = RessourceLocator.findRessource("datafile",
			"pingus", "Pingus/blocker0", "ROT0");

	Ressource spriteBomber = RessourceLocator.findRessource("datafile",
			"pingus", "Pingus/bomber0", "ROT0");

	Ressource spriteFaller = RessourceLocator.findRessource("datafile",
			"pingus", "Pingus/faller0", "ROT0");

	Ressource spriteJumper = RessourceLocator.findRessource("datafile",
			"pingus", "Pingus/faller0", "ROT0");

	Ressource spriteSaving = RessourceLocator.findRessource("datafile",
			"pingus", "Pingus/exit0", "ROT0");

	Ressource spriteFloater = RessourceLocator.findRessource("datafile",
			"pingus", "Pingus/floater0", "ROT0");

	Ressource spriteSplat = RessourceLocator.findRessource("datafile",
			"pingus", "Pingus/splat0", "ROT0");

	Ressource spriteClimber = RessourceLocator.findRessource("datafile",
			"pingus", "Pingus/climber0", "ROT0");

	Ressource spriteSplash = RessourceLocator.findRessource("datafile",
			"pingus", "Pingus/splash0", "ROT0");

	Ressource spriteSplashDebirs = RessourceLocator.findRessource("datafile",
			"pingus", "Pingus/splash_debris0", "ROT0");

	Ressource spriteBasher = RessourceLocator.findRessource("datafile",
			"pingus", "Pingus/basher0", "ROT0");

	Ressource spriteBrickLeft = RessourceLocator.findRessource("datafile",
			"pingus", "Other/brick_left", "ROT0");
	
	Ressource spriteBrickRight = RessourceLocator.findRessource("datafile",
			"pingus", "Other/brick_right", "ROT0");
	
	static AudioClip ohnoSound = Applet.newAudioClip(Ressourcer
			.getResource("/Ressource/data/sounds/ohno.wav"));

	static AudioClipLoop diggerSound = new AudioClipLoop(
			"/Ressource/data/sounds/digger.wav");

	public void initWalker() {
		initWalker(40, -20, SPEED_RIGHT_WALKER);
	}

	public void initWalker(int direction) {
		initWalker(getX(), getY(), direction);
	}

	private void initWalker(int x, int y, int direction) {
		if (verbose)
			System.out.println("initWalker(" + x + "," + y + "," + direction
					+ ")");
		setState(internalStates.WALKER);
		setSprite(spriteWalker);

		init(x, y, 5);
		setSpeedX(direction);
		setSpeedY(0);
		setDelay(0);
		unfreeze();
		setActor(true);
	}

	public void initBlocker() {
		initBlocker(getX(), getY());
	}

	private void initBlocker(int x, int y) {
		if (verbose)
			System.out.println("initBlocker(" + x + "," + y + ")");
		setState(internalStates.BLOCKER);
		setSprite(spriteBlocker);
		init(x, y, 5);
		setDelay(0);
		freeze();
		setActor(false);
	}

	public void initBomber() {
		count_bomb = 6000;
	}

	public void die(int cause) {
		switch (cause) {
		case DEATH_EXPLODE:
			setState(internalStates.BOMBER);
			setSprite(spriteBomber);
			init(getX(), getY(), 5);
			initSprite(0, 0);
			freeze();
			setDelay(15 * 70);
			setActor(false);
			break;
		case DEATH_FALL:
			setState(internalStates.FALLED);
			setSprite(spriteSplat);
			initSprite(0, 0);
			setDelay(15 * 70);
			freeze();
			setActor(false);

			AnimatedSprite s = associate(0, 0);
			s.setSprite(spriteSplashDebirs);
			// s.initSprite(0, 0);
			s.freeze();

			break;

		default:
			break;
		}
	}

	public void initJumper() {
		setState(internalStates.JUMPER);
		setDelay(0);
		setSprite(spriteJumper);
		jump = JUMP_UP;
		setSpeedX(getSpeedX());
		setSpeedY(SPEED_JUMP_Y);
		// freeze();
		setActor(true);
	}

	public void initFaller() {
		initFaller(0);
	}

	public void initFaller(int speedX) {
		if (verbose)
			System.out.println("initFaller(" + speedX + ")");
		diggerSound.stop(this);
		if (state != internalStates.FALLER)
			ohnoSound.play();
		if (state == internalStates.FLOATER) {
			return;
		}
		if (floater) {
			doFloat();
			return;
		}
		setState(internalStates.FALLER);
		setSprite(spriteFaller);
		fall(SPEED_FALL, speedX);
		setDelay(0);
		unfreeze();
		unselect();
		setActor(true);
	}

	public void initBridger() {
		setState(internalStates.BRIDGER);
		setSprite(spriteBridger);
		setSpeedX(getSpeedX());
		setDelay(14 * 70);
		freeze();
		setActor(true);
	}

	private void finishBridger() {
		setState(internalStates.BRIDGER_FINISH);
		setSpeedX(getSpeedX());
		setSprite(spriteBlocker);
		setDelay(5 * 70);
		freeze();
		setActor(true);
	}

	public void initDigger() {
		setState(internalStates.DIGGER);
		setSprite(spriteDigger);
		setSpeedX(getSpeedX());
		setDelay(3 * 70);
		freeze();
		setActor(true);
		diggerSound.loop(this);
	}

	public void initMiner() {
		setState(internalStates.MINER);
		setSprite(spriteMiner);
		setSpeedX(getSpeedX());
		setDelay(5 * 70);
		freeze();
		setActor(true);
	}

	public void initSaved() {
		if (state != internalStates.SAVED)
			setState(internalStates.SAVING);
		nextState = internalStates.SAVED;
		setSprite(spriteSaving);
		setSpeedX(getSpeedX());
		setDelay(6 * 70);
		freeze();
		setActor(false);
	}

	Liquid water;

	public void initSwimmer(Liquid liquid) {
		setState(internalStates.SWIMMER);
		unfreeze();

		setSprite(spriteSwimmer);
		this.water = liquid;
		diggerSound.stop(this);
	}

	public boolean inWater() {
		if (water == null)
			return false;
		if (water.getBounds(false).intersects(getBounds(false)))
			return true;
		if (water.getBounds(false).contains(getBounds(false)))
			return true;
		return false;
	}

	public void init(Entrance e) {
		init(e.getX(), e.getY(), e.getZ());
		initWalker(e.goRight() ? RIGHT : LEFT);
	}

	public void initBasher() {
		nextState = internalStates.BASHER;
		setSprite(spriteBasher);
		count_bash = 12;
	}

	public void doBash() {
		boolean isBashing = state == internalStates.BASHER;
		state = internalStates.BASHER;
		nextState = internalStates.WALKER;
		nextState = internalStates.NONE;
		setSprite(spriteBasher);
		if (!isBashing)
			setSpeedX(getSpeedX());
		// freeze();
		setActor(true);
	}

	public void initFloater() {
		if (verbose)
			System.out.println("initFloater");
		floater = true;
	}

	public void doFloat() {
		setState(internalStates.FLOATER);
		setSprite(spriteFloater);
		fall(SPEED_FLOAT);
	}

	public void initClimber() {
		climber = true;
	}

	public void doClimb() {
		if (state == internalStates.CLIMBER)
			return;
		if (verbose)
			System.out.println("doClimb");
		setState(internalStates.CLIMBER);
		setSprite(spriteClimber);
		setSpeedY(SPEED_UP);
		setSpeedX(getSpeedX());
	}

	public JPingus(MovingBackground background) {
		super("jPingus", background);
		// showDetectors = true;
		// verbose=true;
		setUseOffset(true);
		// setSelectedSprite(spriteSelected);
		setSpeedY(0);
		switch (state) {
		case WALKER:
			initWalker();
			break;
		default:
			break;
		}
	}

	public void setSpeedX(int speedx) {
		if (verbose)
			System.out.println("Set Speedx:" + speedx);
		if (speedx > 0) {
			arrow.setIdy(0);
		} else {
			arrow.setIdy(1);
		}
		// TODO Auto-generated method stub
		switch (state) {
		case WALKER:
			setSpeedXWalker(speedx);
			break;

		case BRIDGER:
			setSpeedXBridger(speedx);
			break;
		case MINER:
			setSpeedXMiner(speedx);
			break;

		case BASHER:
			setSpeedXBasher(speedx);
			break;

		case CLIMBER:
			setSpeedXClimber(speedx);
			break;
		case JUMPER:
			setSpeedXJumper(speedx);
			break;

		default:
			super.setSpeedX(speedx);
			break;
		}
	}

	public void impactBackgroundBridger(MovingBackground background) {
		if (steps_bridger < 15) {
			if (getSpeedX() == SPEED_LEFT_BRIDGER){
				background.addImage(spriteBrickRight.getImage(0, 0), getX(), getY() + getHeight() - 3);
			}else{
				background.addImage(spriteBrickLeft.getImage(0, 0), getX() + getWidth() - STEP_BRIDGER_WIDTH,
						getY() + getHeight() - 3);
			}			
			steps_bridger++;
			init(getX() + getSpeedX(), getY() - 2, getZ());
		} else {
			finishBridger();
		}

	}

	public void impactBackground(MovingBackground background) {
		// TODO Auto-generated method stub
		// System.out.println("impact:"+type);
		switch (state) {
		case BRIDGER:
			impactBackgroundBridger(background);
			break;
		case BRIDGER_FINISH:
			steps_bridger = 0;
			initWalker(getSpeedX());
			break;
		case DIGGER:
			impactBackgroundDigger(background);
			break;
		case MINER:
			impactBackgroundMiner(background);
			break;
		case BASHER:
			impactBackgroundBasher(background);
			break;
		case BOMBER:
			impactBackgroundBomber(background);
			setState(internalStates.DEAD);
			terminated();
			break;
		case FALLED:
			setState(internalStates.DEAD);
			terminated();
			break;
		case SAVING:
			setState(internalStates.SAVED);
			// System.out.println("One saved");
			setActor(false);
			terminated();
			break;
		default:
			break;
		}

	}

	public boolean contained(int x, int y) {
		if (state == internalStates.SAVED)
			return false;
		if (state == internalStates.DEAD)
			return false;
		return super.contained(x, y);
	}

	public boolean update(long elapsed) {
		// TODO Auto-generated method stub
		arrow.update(elapsed);
		if (nextState == internalStates.BASHER) {
			if (count_bash-- < 0)
				initWalker(getSpeedX());

			return super.update(elapsed);
		}
		switch (state) {

		case JUMPER:
			// System.out.println(getSpeedY());
			if (jump == JUMP_UP && getSpeedY() < 0) {
				setSpeedY(getSpeedY() + SPEED_GRAVITY);
			} else {
				jump = JUMP_DOWN;
				// System.out.println(getSpeedY());
				if (getSpeedY() < -SPEED_JUMP_Y) {
					setSpeedY(getSpeedY() + SPEED_GRAVITY);
				} else {
					if (verbose)
						System.out.println("Finished the jump");
					setSpeedY(0);
					initWalker(getSpeedX());
				}
			}
			super.update(elapsed);
			break;
		case BASHER:
			impactBackgroundBasher(background);
		default:
			if (state != internalStates.FALLER && count_bomb > 0) {
				count_bomb -= elapsed;
				if (count_bomb <= 0) {
					die(DEATH_EXPLODE);
					count_bomb = 0;
				}
			}
			return super.update(elapsed);
		}
		return false;
	}

	public void render(Graphics2D g) {
		if (state == internalStates.SAVED)
			return;
		if (state == internalStates.DEAD)
			return;
		switch (state) {
		// case BRIDGER:
		// renderBridger(g);
		// break;
		default:
			if (state != internalStates.FALLER)
				if (count_bomb > 0) {
					g.setColor(Color.BLACK);
					g.drawString("" + (count_bomb / 100), x() - 1, y() - 11);
					g.setColor(Color.YELLOW);
					g.drawString("" + (count_bomb / 100), x(), y() - 10);

				}
			super.render(g);
			if (state == internalStates.WALKER && floater) {
				g.drawImage(spriteFloaterLayer.getImage(getSprite().getIdx(),
						getSprite().getIdy()), x(), y(), null);
			}
			if (selected)
				g.drawImage(arrow.getCurrentImage(), x() + 6, y() - 4, null);
			break;
		}
	}

	public internalStates getState() {
		return state;
	}

	private Shape getDiggingShapeDigger() {
		return new Rectangle(getX(), getY() + getHeight() - 5, getWidth(), 7);
	}

	public void impactBackgroundDigger(MovingBackground background) {
		background.removeShape(getDiggingShapeDigger()
		// ,Color.MAGENTA
				);
	}

	public void impactBackgroundBomber(MovingBackground background) {
		background.removeShape(getDiggingShapeBomber());
	}

	public void setSpeedXWalker(int speedx) {
		if (speedx < 0) {
			super.setSpeedX(SPEED_LEFT_WALKER);
			initSprite(0, 0);
		} else {
			super.setSpeedX(SPEED_RIGHT_WALKER);
			initSprite(0, 1);

		}
	}

	public void setSpeedXJumper(int speedx) {
		if (speedx < 0) {
			super.setSpeedX(-SPEED_JUMP_X);
			initSprite(0, 0);
		} else {
			super.setSpeedX(SPEED_JUMP_X);
			initSprite(0, 1);

		}

	}

	public void setSpeedXClimber(int speedx) {
		if (verbose)
			System.out.println("SetSpeedxClimber:" + speedx);
		if (speedx < 0) {
			super.setSpeedX(0);
			init(getX() + 10, getY(), getZ());
			spriteClimber.setIdy(0);
		} else {
			super.setSpeedX(0);
			init(getX() - 10, getY(), getZ());
			spriteClimber.setIdy(1);
		}

	}

	public void setSpeedXBridger(int speedx) {
		if (speedx < 0) {
			super.setSpeedX(SPEED_LEFT_BRIDGER);
			initSprite(0, 0);
		} else {
			// System.out.println("Bridger init speed:"+speedx);
			super.setSpeedX(SPEED_RIGHT_BRIDGER);
			initSprite(0, 1);

		}

	}

	public void setSpeedXMiner(int speedx) {
		if (speedx < 0) {
			super.setSpeedX(SPEED_LEFT_MINER);
			initSprite(0, 0);
		} else {
			super.setSpeedX(SPEED_RIGHT_MINER);
			initSprite(0, 1);

		}
	}

	public void setSpeedXBasher(int speedx) {
		if (speedx < 0) {
			super.setSpeedX(SPEED_LEFT_BASHER);

			initSprite(0, 0);
		} else {
			super.setSpeedX(SPEED_RIGHT_BASHER);
			initSprite(0, 1);

		}
	}

	private Shape getDiggingShapeMiner() {
		return new Ellipse2D.Double(getX() + getSpeedX(), getY() + 4,
				getWidth(), getHeight());
	}

	private Shape getDiggingShapeBasher() {
		return new Ellipse2D.Double(getX(), getY() - 1, getWidth(), getHeight());
	}

	private Shape getDiggingShapeBomber() {
		return new Ellipse2D.Double(getX() - ((BOMBER_RADIUS - 32) / 2), getY()
				- ((BOMBER_RADIUS - 32) / 2), BOMBER_RADIUS, BOMBER_RADIUS);
	}

	public void impactBackgroundBasher(MovingBackground background) {
		int s = background.obstacleSize(this);
		background.removeShape(getDiggingShapeBasher());
		// System.out.println("basher obs size=" + s);
		if (s <= 1) {
			initBasher();
		}
		// setX(getX() + getSpeedX());
	}

	public void impactBackgroundMiner(MovingBackground background) {
		background.removeShape(getDiggingShapeMiner());
		setX(getX() + getSpeedX());
	}

	public void renderMap(Graphics2D g, float rate, int x, int y) {
		// TODO Auto-generated method stub
		g.setColor(Color.YELLOW);
		g.fillRect((int) (getX() / rate) + x, (int) (getY() / rate) + y,
				(int) (getWidth() / rate), (int) (getHeight() / rate));
		super.renderMap(g, rate, x, y);
	}

	public internalStates getNextState() {
		return nextState;
	}

	public Rectangle getFloorBounds(boolean offset) {
		// TODO Auto-generated method stub
		if (state == internalStates.CLIMBER) {
			if (getSprite().getIdy() == 1) {
				return new Rectangle((offset ? x() : getX()) + 32,
						(offset ? y() : getY()) + 30, 3, 8);
			} else {
				return new Rectangle((offset ? x() : getX()) - 4, (offset ? y()
						: getY()) + 30, 3, 8);
			}
		}
		return getPingusFloorBounds(offset);
	}

	public Rectangle getObstacleBounds(boolean offset) {
		// TODO Auto-generated method stub
		if (state == internalStates.CLIMBER) {
			return new Rectangle((offset ? x() : getX()), (offset ? y()
					: getY()), 32, 3);
		}
		return getPingusObstacleBounds(offset);
	}

	public Rectangle getPingusFloorBounds(boolean offset) {
		return new Rectangle((offset ? x() : getX()) + 7, (offset ? y()
				: getY()) + 32 - 1, 18, 8);
	}

	public Rectangle getPingusObstacleBounds(boolean offset) {
		if (getSpeedX() > 0)
			return new Rectangle((offset ? x() : getX()) + 24, (offset ? y()
					: getY()) + 5, 1, 26);
		else
			return new Rectangle((offset ? x() : getX()) + 7, (offset ? y()
					: getY()) + 5, 1, 26);
	}

	public void obstacleDetected(int size) {
		if (size > 0) {
			if (verbose)
				System.out.println("obstacleDetected(" + size + ")");
			if (nextState == internalStates.BASHER && size > 1) {
				doBash();
				return;
			}
			if (climber && size > MIN_OBS_SIZE) {
				doClimb();
				return;
			}
			if (state == internalStates.BASHER) {
				if (size <= MIN_OBS_SIZE) {
					initBasher();
				}
				return;
			}
			if (state == internalStates.BRIDGER) {
				setSpeedX(-getSpeedX());
				return;
			}
			// System.out.println("obstacle size:" + size);
			int sx = getSpeedX();
			if (size > MIN_OBS_SIZE) {
				if (sx > 0) {
					initSprite(0, 0);
					setSpeedX(-sx);
				} else {
					initSprite(0, 1);
					setSpeedX(-sx);
				}
			} else if (size > 0) {
				init(getX(), getY() - (size * 2), getZ());
			}
		}
	}

	public void noFloorDecteted() {
		if (verbose)
			System.out.println("noFloorDecteted()");
		// TODO Auto-generated method stub
		if (state == internalStates.SWIMMER && inWater())
			return;
		if (state == internalStates.JUMPER) {
			return;
		}
		if (state == internalStates.CLIMBER) {
			if (verbose)
				System.out.println("Walk now climber !!");
			initWalker(getSpeedX());
			return;
		}

		initFaller();
	}

	public void floorDecteted(int distance) {
		if (distance == 0)
			return;
		if (verbose)
			System.out.println("floorDecteted(" + distance + ")");
		if (distance >= 7) {
			noFloorDecteted();
			return;
		}
		if (getState() == internalStates.JUMPER) {
			if (jump == JUMP_UP) {
				return;
			}
			initWalker(getSpeedX());
		}

		if (getState() == internalStates.CLIMBER && distance == 1) {
			// System.out.println("Walk now climber !!"+distance);
			if (getSprite().getIdy() == 1) {
				init(getX() + 10, getY(), getZ());
				initWalker(RIGHT);
			} else {
				init(getX() - 10, getY(), getZ());
				initWalker(LEFT);
			}

		}
		int fall = land(distance);
		if (fall != 0) {
			if (fall < 176 || state == internalStates.FLOATER) {
				// System.out.println(pingus + " falled of " + fall);
				initWalker(getSpeedX());
			} else {
				die(DEATH_FALL);
				if (verbose)
					System.out.println("Death by falling :" + fall);
			}
		}
	}

	private void setState(internalStates state) {
		this.state = state;
		nextState = internalStates.NONE;
	}
}
