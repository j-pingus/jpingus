package gee.imaging.animator;

import gee.imaging.Renderable;
import gee.imaging.Ressourcer;
import gee.imaging.SpriteContainer;
import gee.imaging.background.DummyBackground;
import gee.imaging.background.MovingBackground;
import gee.imaging.sprites.animated.MovingSprite;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.Timer;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.Player;

public abstract class Animator implements MouseListener, MouseMotionListener,
		SpriteContainer, ComponentListener, KeyListener {
	boolean debug = true;

	boolean renderUpdate = false;

	boolean actorCollides = false;

	// MouseEvent clicked, dragged, released, pressed;

	AnimatorDetector detector = new DummyDetector();

	BufferedImage image;

	Graphics2D gBuffer;

	Graphics g;

	Component window;

	Timer timer;

	Thread run;

	Vector sprites;

	MovingBackground background;

	Rectangle sight;

	private int height;

	private int width;

	int offsetX, offsetY;

	int mouseX, mouseY;

	private static final int SCROLLING_ZONE = 35;

	private static final int SCROLLING_SPEED = 13;

	Vector laterRemove = new Vector();

	boolean continueLoop = false;

	String music;

	Player player;

	long speed = 0;

	long animatorTime = 0;

	public long getAnimatorTime() {
		return animatorTime;
	}

	public long getSpeed() {
		return speed;
	}

	public void resetSpeed() {
		speed = 0;
	}

	public void accelerate() {
		speed += 10;
	}

	public void decelerate() {
		speed -= 10;
	}

	AudioDevice dev;

	/* Very helpfull for debugging */
	boolean stepMode = true;

	public void clearAnimation() {
		stop();
		sprites.clear();
	}

	public Enumeration getSprites() {
		return sprites.elements();
	}

	public void setMusic(String uri) {
		music = uri;
		try {
			if (dev == null)
				dev = FactoryRegistry.systemRegistry().createAudioDevice();
			if (uri.endsWith("mp3")) {
				Thread mp = new Thread() {
					public void run() {
						// TODO Auto-generated method stub
						super.run();
						if (player != null) {
							player.close();
						}
						try {
							player = new Player(Ressourcer
									.getResourceAsStream(music), dev);
							player.play();
						} catch (JavaLayerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};

				mp.start();
			}
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void free() {
		stop();
		if (background != null)
			background.free();
		background = null;
		if (gBuffer != null)
			gBuffer.dispose();
		gBuffer = null;
		if (sprites != null) {
			for (int i = 0; i < sprites.size(); i++) {
				Renderable sprite = (Renderable) sprites.elementAt(i);
				sprite.free();
			}
			sprites.removeAllElements();
			// sprites = null;
		}
	}

	public void addSprite(Vector v) {
		for (Iterator iter = v.iterator(); iter.hasNext();) {
			Renderable sp = (Renderable) iter.next();
			addSprite(sp);
		}
	}

	public void addSprite(Renderable sp) {
		// TODO Auto-generated method stub
		// for (int i = 0; i < sprites.size(); i++) {
		for (int i = sprites.size() - 1; i >= 0; i--) {
			if (((Renderable) sprites.elementAt(i)).getZ() >= sp.getZ()) {
				sprites.insertElementAt(sp, i);
				// System.out.println(sprites);
				return;
			}
		}
		sprites.addElement(sp);

	}

	// TODO Auto-generated method stub

	public void removeSprite(Renderable sp) {
		// TODO Auto-generated method stub
		laterRemove.addElement(sp);
	}

	public void doLater() {
		for (int i = 0; i < laterRemove.size(); i++) {
			Renderable sprite = (Renderable) laterRemove.elementAt(i);
			sprites.removeElement(sprite);
		}
		laterRemove.clear();
	}

	public void renderDebug() {
		if (debug) {
			Runtime rt = Runtime.getRuntime();
			int usedMemory = (int) (100 - ((rt.freeMemory() * 100) / rt
					.totalMemory()));
			int usedTime = getPercentTimeUsed();
			gBuffer.setColor(Color.WHITE);
			gBuffer.drawString("Sprites left:" + sprites.size(), 10, 35);
			gBuffer.drawString("mouse(" + mouseX + "," + mouseY + ") offset("
					+ offsetX + "," + offsetY + ")", 10, 50);
			gBuffer.drawString("x:" + offsetX + " y:" + offsetY, 10, 65);
			gBuffer.setColor(Color.WHITE);
			gBuffer.fillRect(10, 10, 50, 5);
			gBuffer.setColor(Color.BLUE);
			gBuffer.fillRect(11, 11, (int) (48 * usedMemory / 100), 3);
			gBuffer.setColor(Color.WHITE);
			gBuffer.fillRect(500, 10, 50, 5);
			if (usedTime < 95) {
				gBuffer.setColor(Color.GREEN);
			} else {
				gBuffer.setColor(Color.RED);
			}
			gBuffer.fillRect(501, 11, (int) (48 * usedTime / 100), 3);
			gBuffer.setColor(Color.WHITE);
			gBuffer
					.drawString(
							getEllapsedTime()
									+ " ms,"
									+ ((getEllapsedTime() > 0) ? ((1000 / getEllapsedTime()) + " fps")
											: ""), 555, 10);
			detector.renderDebug(gBuffer);
		}

	}

	public void step(int steps) {
		if (!stepMode)
			stepMode = true;
		for (int i = 0; i < steps; i++)
			update(50, false);
	}

	public void stepBack(int steps) {
		if (!stepMode)
			stepMode = true;
		for (int i = 0; i < steps; i++)
			update(-50, false);
	}

	public void render(Graphics g, int x, int y) {
		if (getImage() != null && g != null) {

			g.drawImage(getImage(), x, y, window);

		}

	}

	public void render() {
		render(window.getGraphics(), 0, 0);
	}

	public abstract long getEllapsedTime();

	public abstract long getCurrentTime();

	public abstract void setEllapsedTime(long ellapsed);

	public abstract int getSleepTime();

	public abstract int getPercentTimeUsed();

	public void start() {
		if (continueLoop)
			return;
		continueLoop = true;
		// System.out.println(this + " starting
		// "+window.getKeyListeners().length);
		window.addMouseListener(this);
		window.addMouseMotionListener(this);
		window.addKeyListener(this);
		window.addComponentListener(this);
		window.setFocusable(true);
		window.requestFocus();
		// System.out.println(this + " starting "+window.getKeyListeners()[0]);

		if (background != null) {
			this.setWidth(background.getWidth());
			this.setHeight(background.getHeight());
		} else {
			this.setWidth(window.getWidth());
			this.setHeight(window.getHeight());
		}
		sight = new Rectangle(0, 0, getWidth(), getHeight());
		System.out.println("size animated:" + window.getWidth() + "/"
				+ window.getHeight());
		setImage(new BufferedImage(window.getWidth(), window.getHeight(),
				BufferedImage.TYPE_INT_RGB));

		run = new Thread() {
			public void run() {
				try {
					int noDelays = 0;
					long startTime = getCurrentTime();
					long currTime = startTime;

					while (continueLoop) {
						long elapsedTime = getCurrentTime() - currTime;
						// System.out.println("Ellapsed:"+elapsedTime);
						currTime += elapsedTime;
						animatorTime += elapsedTime;
						setEllapsedTime(elapsedTime);
						detectScrolling();
						// doMouseActions();
						update(elapsedTime + speed, stepMode);
						mouseOver();
						renderDebug();
						if (renderUpdate) {
							window.update(window.getGraphics());
						} else {
							render();
						}
						int sleep = getSleepTime();
						// System.out.println(sleep);
						if (sleep > 0) {
							Thread.sleep(sleep);
						}
						if (++noDelays >= 100) {
							// System.out.println("yield");
							// System.out.println("yield
							// "+window.getKeyListeners().length);
							// System.out.println("yield "+window.isEnabled());
							// System.out.println("yield "+window.isValid());
							// System.out.println("yield
							// "+window.isFocusOwner());

							Thread.yield(); // give another thread a
							// chance to run
							noDelays = 0;
						}

					}
				} catch (InterruptedException e) {
					e.printStackTrace();
					// TODO: handle exception
				}

			}

		};
		run.start();
	}

	public void stop() {
		// System.out.println(this + " stopped");
		window.removeMouseListener(this);
		window.removeMouseMotionListener(this);
		window.removeKeyListener(this);
		window.removeComponentListener(this);
		if (player != null) {
			player.close();
			player = null;
		}

		continueLoop = false;
	}

	public Image getImage() {
		return image;
	}

	public Animator(Component comp, boolean step) {
		this.window = comp;
		this.stepMode = step;
		window.setIgnoreRepaint(true);
		background = new DummyBackground(window, Color.YELLOW);

		sprites = new Vector();
		addSprite(background);
	}

	public void update(long elapsed, boolean step) {
		doLater();
		if (gBuffer != null) {
			if (sprites != null) {
				for (int i = 0; i < sprites.size(); i++) {

					Renderable renderme = (Renderable) sprites.elementAt(i);
					MovingSprite sprite;
					// System.out.print("painting "+i+" of
					// "+sprites[i].getClass());
					if (renderme != null) {
						renderme.initOffset(offsetX, offsetY);
						if (!step)
							renderme.update(elapsed);

						if (renderme.getType() == Renderable.ANIMATED) {
							sprite = (MovingSprite) renderme;
							if (sprite.isTerminated()) {
								removeSprite(sprite);
								continue;
							}
							if (!sprite.isActor()) {
								sprite.render(gBuffer);
								continue;
							}
							if (sprite.contained(mouseX - offsetX, mouseY
									- offsetY)) {
								detector.mouseOver(sprite);
							}
							int distance = background.floorDistance(sprite);
							// System.out.println("floor:"+distance);
							if (!step) {
								sprite.floorDecteted(distance);
								sprite.obstacleDetected(background.obstacleSize(sprite));
							}
							if (!sight.contains(sprite.getBounds(false))) {
								if (sight.intersects(sprite.getBounds(false))) {
									detector.gettingOutOfSight(sprite, sight);
								} else {
									detector.outOfSight(sprite, sight);
								}
							}
							for (int j = 0; j < sprites.size(); j++) {
								if (j != i) {
									if (((Renderable) sprites.elementAt(j))
											.getType() == Renderable.ANIMATED) {
										MovingSprite s2 = (MovingSprite) sprites
												.elementAt(j);
										// For "lemmings" like games where a lot
										// of
										// actors
										// are in a same place, this features
										// speeds
										// up
										// a little
										if (!isActorCollides() && s2.isActor())
											continue;

										if (sprite.collides(s2))
											if (!step)
												detector.collisionDetected(
														sprite, s2);
									}
								}
							}
						}
						// sprite.render(gBuffer);

						renderme.render(gBuffer);

					}
				}
			}

		}
	}

	public long max = 0;

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (continueLoop) {

		}
		mouseX = (window.getWidth() / 2);
		mouseY = (window.getHeight() / 2);
	}

	public void mouseOver() {
		// TODO Auto-generated method stub
		for (int i = sprites.size() - 1; i >= 0; i--) {
			Renderable sprite = (Renderable) sprites.elementAt(i);
			if (sprite.getType() == Renderable.ANIMATED)
				if (((MovingSprite) sprite).contained(mouseX, mouseY)) {
					detector.mouseOver(((MovingSprite) sprite));
					return;
				}
		}
		Renderable selectedRenderable = null;
		if (background != null)
			selectedRenderable = background.mouseOver(mouseX, mouseY);
		detector.mouseOver(selectedRenderable);
	}

	public MovingBackground getBackground() {
		return background;
	}

	public void setBackground(MovingBackground background) {
		if (this.background != null)
			removeSprite(this.background);
		this.background = background;
		if (background != null)
			addSprite(background);
	}

	public AnimatorDetector getDetector() {
		return detector;
	}

	public void setDetector(AnimatorDetector detector) {
		this.detector = detector;
		this.detector.setAnimator(this);
		// start();
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if (continueLoop) {

		}
		mouseX = e.getX();
		mouseY = e.getY();

	}

	public void setStartPos(int x, int y) {
		mouseX = (window.getWidth() / 2);
		mouseY = (window.getHeight() / 2);
		offsetX = (window.getWidth() / 2) - x;
		if (offsetX >= 0)
			offsetX = 0;
		if (offsetX <= window.getWidth() - getWidth())
			offsetX = window.getWidth() - getWidth();
		offsetY = y - (window.getHeight() / 2);
		if (offsetY < 0)
			offsetY = 0;
		if (offsetY <= window.getHeight() - getHeight())
			offsetY = window.getHeight() - getHeight();
		if (offsetY >= 0)
			offsetY = 0;
		if (window.getWidth() - getWidth() >= 0)
			offsetX = 0;
		if (window.getHeight() - getHeight() >= 0)
			offsetY = 0;

	}

	public void scrollLeft() {
		offsetX += SCROLLING_SPEED;
		if (offsetX >= 0)
			offsetX = 0;
	}

	public void scrollRight() {
		offsetX -= SCROLLING_SPEED;
		if (offsetX <= window.getWidth() - getWidth())
			offsetX = window.getWidth() - getWidth();

	}

	public void scrollDown() {
		offsetY += SCROLLING_SPEED;
		if (offsetY >= 0)
			offsetY = 0;

	}

	public void scrollUp() {
		offsetY -= SCROLLING_SPEED;
		if (offsetY <= window.getHeight() - getHeight())
			offsetY = window.getHeight() - getHeight();

	}

	public void detectScrolling() {
		// TODO Auto-generated method stub
		if (window.getWidth() - getWidth() < 0) {
			if (mouseX < SCROLLING_ZONE) {
				scrollLeft();
			}
			if (mouseX > window.getWidth() - SCROLLING_ZONE) {
				scrollRight();
			}
		} else {
			offsetX = 0;
		}
		if (window.getHeight() - getHeight() < 0) {
			if (mouseY < SCROLLING_ZONE) {
				scrollDown();
			}
			if (mouseY > window.getHeight() - SCROLLING_ZONE) {
				scrollUp();
			}
		} else {
			offsetY = 0;
		}
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// System.out.println(e);
		detector.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		// System.out.println(e);
		detector.keyReleased(e);

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		// System.out.println(e);
		detector.keyTyped(e);

	}

	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	public void componentResized(ComponentEvent e) {
		System.out.println("size animated:" + window.getWidth() + "/"
				+ window.getHeight());
		image = new BufferedImage(window.getWidth(), window.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		gBuffer = image.createGraphics();
	}

	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	public int getOffsetX() {
		return offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	// mouse events section
	public void mouseClicked(MouseEvent e) {
		// clicked = e;
		if (continueLoop) {
			detector.clicked(e);
			e.consume();
		}
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		// dragged = e;
		if (continueLoop) {
			mouseX = e.getX();
			mouseY = e.getY();
			detector.dragged(e);
			e.consume();
		}
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		// released = e;
		if (continueLoop) {
			detector.released(e);
			e.consume();
		}
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		// pressed = e;
		if (continueLoop) {
			detector.pressed(e);
			e.consume();
		}
	}

	public void renderMap(Graphics2D g, int x, int y, int width, int height) {
		float rate = getBackground().renderMap(g, x, y, width, height);
		if (sprites != null) {
			for (int i = 0; i < sprites.size(); i++) {

				Renderable sprite = (Renderable) sprites.elementAt(i);
				if (sprite != null) {
					sprite.renderMap(g, rate, x, y);

					// if (!sprite.isActor())
					// continue;
				}
			}
		}

	}

	public boolean isActorCollides() {
		return actorCollides;
	}

	public void setActorCollides(boolean actorCollides) {
		this.actorCollides = actorCollides;
	}

	public Component getWindow() {
		return window;
	}

	public boolean isStepMode() {
		return stepMode;
	}

	public void setStepMode(boolean stepMode) {
		this.stepMode = stepMode;
	}

	/**
	 * @param width
	 *            The width to set.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return Returns the width.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param height
	 *            The height to set.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return Returns the height.
	 */
	public int getHeight() {
		return height;
	}

	public void setGBuffer(Graphics2D buffer) {
		gBuffer = buffer;
	}

	public Rectangle getSight() {
		return sight;
	}

	public void setSight(Rectangle sight) {
		this.sight = sight;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		gBuffer = image.createGraphics();
	}

	public boolean isRenderUpdate() {
		return renderUpdate;
	}

	public void setRenderUpdate(boolean renderUpdate) {
		this.renderUpdate = renderUpdate;
	}

}
