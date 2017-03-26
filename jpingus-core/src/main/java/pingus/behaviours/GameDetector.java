package pingus.behaviours;

import gee.imaging.ImageToolkit;
import gee.imaging.Renderable;
import gee.imaging.sprites.animated.AnimatedSprite;
import gee.imaging.sprites.animated.MovingSprite;

import java.awt.event.KeyEvent;
import java.util.Vector;

import pingus.Actions;
import pingus.JPingus;
import pingus.JPingus.internalStates;
import pingus.level.Level;
import pingus.macro.ActionsRecorder;
import pingus.macro.PingusIdentifier;
import pingus.pieces.gui.ActionSprite;
import pingus.pieces.gui.ScoreSprite;

public class GameDetector extends LevelDetector implements PingusIdentifier {
	Vector<JPingus> vpingus;

	boolean win = false;

	boolean dropper;
	ThemeDetector theme;
	public GameDetector(Level level,ThemeDetector theme) {
		super(level); 
		this.theme=theme;
	}

	public Vector<JPingus> getVpingus() {
		return vpingus;
	}

	public void setVpingus(Vector<JPingus> vpingus) {
		this.vpingus = vpingus;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {

		this.win = win;
		if (win) {

		}
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==27)
			loop=false;
		super.keyPressed(e);
		if (e.getKeyChar() == 'A' || e.getKeyChar() == 'a') {
			dropper = false;
			for (int i = 0; i < vpingus.size(); i++) {
				((JPingus) vpingus.elementAt(i)).initBomber();
				try {
					Thread.sleep(125);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
		if (e.getKeyChar() == 'B' || e.getKeyChar() == 'b') {
			new PingusManager(1000, false, 500);
		}
		if (e.getKeyCode() >= 49 && e.getKeyCode() <= 57) {
			int act = e.getKeyCode() - 49;
			if (currentAction != null)
				currentAction.unselect();
			if (act < current_level.getActions().size())
				currentAction = (ActionSprite) current_level.getActions()
						.elementAt((int) e.getKeyChar() - '1');
			currentAction.select();
		} else if (e.getKeyCode() == 37) {
			getAnimator().scrollLeft();
		} else if (e.getKeyCode() == 38) {
			getAnimator().scrollUp();
		} else if (e.getKeyCode() == 39) {
			getAnimator().scrollRight();
		} else if (e.getKeyCode() == 40) {
			getAnimator().scrollDown();
		}

	}

	public void startDrop() {
		Actions.setRecorder(new ActionsRecorder(getAnimator(),this));
		new PingusManager(current_level.getNbOfPingus(), true, 1500);
	}
	ScoreSprite score;
	boolean loop = true;

	class PingusManager extends Thread {
		int how_many, sleep;
		boolean count;
		
		public PingusManager(int how_many, boolean count, int sleep) {
			setVpingus(new Vector<JPingus>());
			this.how_many = how_many;
			this.count = count;
			this.sleep = sleep;
			score=new ScoreSprite(50,12);
			getAnimator().addSprite(score);
			start();
		}


		public void run() {
			dropper = true;
			int added = 0;
			int saved = 0;
			int killed = 0;
			
			while (loop) {
				saved = 0;
				killed = 0;
				if (added < how_many && dropper) {
					JPingus pingus = new JPingus(current_level.getBack());
					pingus.init(current_level.getEntrance(added));
					sleep=60000/current_level.getEntrance(added).getRate();
					
					if (count)
						getVpingus().addElement(pingus);
					added++;
					getAnimator().addSprite(pingus);
				}
				for (int i = 0; i < getVpingus().size(); i++) {
					JPingus pingus = (JPingus) getVpingus().elementAt(i);
					if (pingus.getState() == JPingus.internalStates.SAVED)
						saved++;
					if (pingus.getState() == JPingus.internalStates.DEAD)
						killed++;
				}
				score.setScore(how_many,getVpingus().size(),current_level.getNbToSave(),saved,killed);
				if (saved + killed == getVpingus().size()) {
					loop = false;
				}
				if (saved >= current_level.getNbToSave()) {
					if (!isWin()) {
						setWin(true);
						AnimatedSprite winner = new AnimatedSprite("Win");
						winner.init(60, 20, 1000);
						winner.freeze();
						winner.setActor(false);
						winner.setUseOffset(false);
						winner
								.setSprite(ImageToolkit
										.createSprite("/Ressource/data/images/gui/Win.png","ROT0"));
						getAnimator().addSprite(winner);
						winner = new AnimatedSprite("Super");
						winner.init(60, 24, 2000);
						winner.freeze();
						winner.setActor(false);
						winner.setUseOffset(false);
						winner.setSprite(ImageToolkit.createSprite(
								"/Ressource/data/images/pingus/superman.png","ROT0",
								30, 30));
						getAnimator().addSprite(winner);

					}
				}
				try {
						Thread.sleep(sleep-getAnimator().getSpeed());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			if (saved >= current_level.getNbToSave()) {
				if(theme!=null)
					theme.won(saved);
			} else {
				if(theme!=null)
					theme.lost();
				// lost
			}

		}
	}
	public void mouseOver(Renderable s) {
		// TODO Auto-generated method stub
		super.mouseOver(s);
		if (s != null && s.getClass() == JPingus.class)
			((MovingSprite) current).select();
	}

	public int getId(JPingus pingus) {
		// TODO Auto-generated method stub
		return vpingus.indexOf(pingus);
	}

	public JPingus getPingus(int id) {
		// TODO Auto-generated method stub
		return vpingus.elementAt(id);
	}
}
