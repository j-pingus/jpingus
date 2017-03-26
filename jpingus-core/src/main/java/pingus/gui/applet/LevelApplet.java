package pingus.gui.applet;

import gee.imaging.ImageToolkit;
import gee.imaging.animator.Animator;
import gee.imaging.animator.AppletAnimator;
import gee.imaging.scene.SceneDirector;

import java.applet.Applet;

import pingus.JPingus;
import pingus.behaviours.ThemeDetector;
import pingus.level.Level;
import pingus.level.LevelReader;
import pingus.pieces.gui.WaitingSprite;

public class LevelApplet extends Applet {
	Animator animator = null;

	private static final int SPEED_ANIMATOR = 1000 /50;

	ThemeDetector detector;

	Level current = null;

	String levelName = "themes/tutorial.xml";
	
	public void init() {
		super.init();
		setSize(640,480);
		ImageToolkit.init(this);
		if(getParameter("level")!=null)
			levelName=getParameter("level");
		animator = new AppletAnimator(this, SPEED_ANIMATOR,false);
		detector = new ThemeDetector();
		animator.setDetector(detector);
		detector.init();
		SceneDirector.getSceneDirector().showScene("start",500);
		
		Thread load = new Thread(){
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				animator.setDebug(false);
				WaitingSprite ws=new WaitingSprite(SceneDirector.getSceneDirector().getCurrentScene());
				current = LevelReader.readLevel("/Ressource/data/levels/" + levelName,ws);

				// handleLelevChunks(root.getChildren());
				// handleStartPosition(root.getChild("background"));
				animator.setBackground(current.getBack());
				animator.addSprite(current.getSpots());
				animator.addSprite(current.getTraps());
//				//animator.addSprite(current.getStart());
				animator.addSprite(current.getActions());
				animator.setMusic("/Ressource/data/music/"+current.getMusic());
				animator.setStartPos(current.getStart().getX(), current.getStart()
						.getY());
				new JPingus(current.getBack());
				SceneDirector.getSceneDirector().addScene("select",animator);
				SceneDirector.getSceneDirector().showScene("select",500);
				ws.finish();
			}
		};
		load.start();
	}
}
