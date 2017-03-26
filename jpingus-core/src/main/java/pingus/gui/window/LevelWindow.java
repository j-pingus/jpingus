package pingus.gui.window;

import gee.imaging.ImageToolkit;
import gee.imaging.WindowToolkit;
import gee.imaging.animator.Animator;
import gee.imaging.animator.ComponentAnimator;
import gee.imaging.scene.SceneDirector;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Panel;

import pingus.behaviours.ThemeDetector;
import pingus.level.Level;
import pingus.level.LevelReader;
import pingus.pieces.gui.TimerSprite;
import pingus.pieces.gui.WaitingSprite;

public class LevelWindow extends Frame {
	String levelName;

	Animator animator;

	ThemeDetector detector;
	int screen_width = 800;
	int screen_height = 600;

	private static final int SPEED_ANIMATOR = 1000 / 50;

	Level current;

	private void init(boolean fullScreen) {
		ImageToolkit.init(this); 
		Panel aPanel = new Panel();
		this.setLayout(new BorderLayout());
		this.add(aPanel,BorderLayout.CENTER);
		
		WindowToolkit.initWindow(this, fullScreen, screen_width, screen_height);
		animator = new ComponentAnimator(aPanel,SPEED_ANIMATOR,false);
		detector = new ThemeDetector();
		animator.setDetector(detector);
		this.setVisible(true);
		detector.init();
		SceneDirector.getSceneDirector().showScene("start", 500);

		Thread load = new Thread() {
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				WaitingSprite ws = new WaitingSprite(SceneDirector.getSceneDirector().getCurrentScene());
				animator.setDebug(false);
				current = LevelReader.readLevel("/Ressource/data/levels/"
						+ levelName,ws);
				LevelWindow.this.setTitle(levelName.replaceAll("\\....", ""));
				detector.setCurrentLevel(current);
				// handleLelevChunks(root.getChildren());
				// handleStartPosition(root.getChild("background"));
				animator.setBackground(current.getBack());
				animator.addSprite(current.getSpots());
				animator.addSprite(current.getTraps());
				// //animator.addSprite(current.getStart());
				animator.addSprite(current.getActions());
				animator
						.setMusic("/Ressource/data/music/" + current.getMusic());
				animator.setStartPos(current.getStart().getX(), current
						.getStart().getY());
				animator.addSprite(new TimerSprite(animator));
//				new JPingus(current.getBack());
				SceneDirector.getSceneDirector().addScene("select", animator);
				SceneDirector.getSceneDirector().showScene("select", 500);
				ws.finish();
			}
		};
		load.start();
	}
	public LevelWindow(boolean fullScreen,String level,int width,int height) {
		this.levelName=level;
		this.screen_height=height;
		this.screen_width=width;
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			init(fullScreen);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
//		System.setOut(null);
//		System.setErr(null);
		new LevelWindow(args.length>0&&args[0].equals("fullscreen"),"themes/tutorial.xml",640,480);
	}
}
