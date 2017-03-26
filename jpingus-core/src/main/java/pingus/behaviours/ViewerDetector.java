package pingus.behaviours;

import gee.imaging.Renderable;
import gee.imaging.animator.Animator;
import gee.imaging.animator.ComponentAnimator;
import gee.imaging.sprites.animated.AnimatedSprite;
import gee.imaging.sprites.animated.MovingSprite;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import pingus.jPingusProject;
import pingus.gui.ObjectEditor;
import pingus.level.Level;
import pingus.level.LevelWriter;
import pingus.pieces.gui.ActionSprite;

public class ViewerDetector extends LevelDetector {
	Renderable current_selected;

	Component animated;

	Frame frame;

	public ViewerDetector(Frame frame, Component animated, Level level) {
		super(level);
		this.frame = frame;
		this.animated = animated;
	}

	public void clicked(MouseEvent e) {
		// TODO Auto-generated method stub
		super.clicked(e);
		current_selected = current;
		animated.setFocusable(true);
		animated.requestFocus();
		// System.out.println(current_selected);
	}

	public void renderDebug(Graphics2D g) {
		// TODO Auto-generated method stub
		super.renderDebug(g);
		Composite c = g.getComposite(); // backup the old composite

		if (current_selected != null) {
		}
		g.setComposite(c);
		if (current != null) {
			if (current.getType() != Renderable.ANIMATED)
				return;
			Image i = ((MovingSprite) current).getCurrentImage();
			if (i != null) {
				g.drawImage(i, 500, 30, null);
			}
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					0.45f));
			g.setColor(Color.WHITE);
			g.fillRect(500, 30, 300, 24);
			g.setColor(Color.BLACK);
			g.setComposite(c);
			g.drawString("x:" + current.getX() + ",y:" + current.getY(), 502,
					40);
			g.drawString("n:" + ((MovingSprite) current).getName(), 502, 52);

		}
		if (current_selected != null) {
			Image i = ((MovingSprite) current_selected).getCurrentImage();

			if (i != null) {
				g.drawImage(i, ((AnimatedSprite) current_selected).x(),
						((AnimatedSprite) current_selected).y(), null);
			}
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					0.45f));
			g.setColor(Color.RED);
			g.fill(((MovingSprite) current_selected).getBounds(true));
			// g.setColor(new Color(0xff,0x00,0x00,0x8f));
			// g.fill(current.getBounds(true));
		}
		g.setComposite(c);
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		super.keyPressed(e);
		// System.out.println(e);
		if (current_selected != null) {
			int moveSpeed=1;
			if((e.getModifiers() & KeyEvent.CTRL_MASK)!=0){
				moveSpeed=10;
			}
			if (e.getKeyCode() == 37) {
				// Left
				current_selected.init(current_selected.getX() - moveSpeed,
						current_selected.getY(), current_selected.getZ());
			} else if (e.getKeyCode() == 38) {
				// Up
				current_selected.init(current_selected.getX(), current_selected
						.getY() - moveSpeed, current_selected.getZ());
			} else if (e.getKeyCode() == 39) {
				// Right
				current_selected.init(current_selected.getX() + moveSpeed,
						current_selected.getY(), current_selected.getZ());

			} else if (e.getKeyCode() == 40) {
				// Down
				current_selected.init(current_selected.getX(), current_selected
						.getY() + moveSpeed, current_selected.getZ());
			} else if (e.getKeyCode() == 10) {
				current_selected = null;
				Animator animator = getAnimator();
				Component window = animator.getWindow();
				// animator.free();
				animator.stop();
				animator = new ComponentAnimator(window, 1000 / 24, false);
				animator.setDebug(true);
				current_level.getBack().reset();
				// handleLelevChunks(root.getChildren());
				// handleStartPosition(root.getChild("background"));
				animator.setBackground(current_level.getBack());
				animator.addSprite(current_level.getSpots());
				animator.addSprite(current_level.getStart());
				animator.addSprite(current_level.getActions());
				animator.addSprite(current_level.getTraps());
				// animator.setMusic("/Ressource/data/music/"+current.getMusic());

				animator.setDetector(this);
				animator.setStartPos(current_level.getStart().getX(),
						current_level.getStart().getY());

			} else if (e.getKeyChar() == 'E' || e.getKeyChar() == 'e') {
				if (current_selected != null) {
					try {
						new ObjectEditor(frame,
								(AnimatedSprite) current_selected);
					} catch (ClassCastException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		} else if (e.getKeyCode() == 32) {

			getAnimator().step(1);

		} else if (e.getKeyCode() == 8) {

			getAnimator().stepBack(1);

		} else if (e.getKeyChar() == 'S' || e.getKeyChar() == 's') {
			if (current_level != null) {
				LevelWriter.write(current_level);
			}
		} else {
			System.out.println(e);
		}
	}

	public void mouseOver(Renderable s) {
		// TODO Auto-generated method stub
		super.mouseOver(s);
		if (s != null && s.getClass() != ActionSprite.class)
			((MovingSprite) current).select();
	}
}
