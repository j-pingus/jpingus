package pingus.behaviours;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import pingus.JPingus;
import pingus.level.Level;
import pingus.level.LevelReader;
import pingus.pieces.gui.LevelSprite;
import pingus.pieces.gui.TextScroller;
import pingus.pieces.gui.ThemeSprite;
import pingus.pieces.gui.WaitingSprite;

import gee.imaging.ImageToolkit;
import gee.imaging.Renderable;
import gee.imaging.animator.Animator;
import gee.imaging.animator.AnimatorDetector;
import gee.imaging.animator.ComponentAnimator;
import gee.imaging.scene.SceneDirector;
import gee.imaging.sprites.animated.AnimatedSprite;
import gee.imaging.sprites.animated.MovingSprite;

public class ThemeDetector implements AnimatorDetector {
	Level currentLevel; 

	Animator animator;

	Renderable currentSprite;
	
	LevelSprite currentLevelSprite;

	ThemeSprite currentThemeSprite;

	public static final int MODE_APPLET = 1, MODE_COMPONENT = 2;

	ThemeDetector theme = this;
	boolean loading=false;
	class LevelLoadingThread extends Thread {

		ThemeDetector detector=null; 

		LevelLoadingThread(ThemeDetector detector, LevelSprite current) {
			this.detector = detector;
			currentLevelSprite=current;
		}

		public void run() {
			// TODO Auto-generated method stub
			super.run();
			WaitingSprite ws = new WaitingSprite(SceneDirector
					.getSceneDirector().getCurrentScene());

			Animator level_a = new ComponentAnimator(animator.getWindow(),
					1000 / 24,false);

			level_a.setDebug(false);
			SceneDirector.getSceneDirector().addScene("level", level_a);
			Level current = LevelReader.readLevel("/Ressource/data/levels/"
					+ currentLevelSprite.getName(), ws);
			if(current==null){
				System.err.println("check "+currentLevelSprite.getName());
				return;
			}
			// handleLelevChunks(root.getChildren());
			// handleStartPosition(root.getChild("background"));
			level_a.setBackground(current.getBack());
			level_a.addSprite(current.getSpots());
			level_a.addSprite(current.getTraps());
			// //animator.addSprite(current.getStart());
			level_a.addSprite(current.getActions());
			level_a.setMusic("/Ressource/data/music/" + current.getMusic());
			ws.setMessage("Music started");
			GameDetector gdetector = new GameDetector(current, detector); 
			level_a.setStartPos(current.getStart().getX(), current.getStart()
					.getY());
			new JPingus(current.getBack());
			level_a.setDetector(gdetector);
			SceneDirector.getSceneDirector().showScene("level", 500);
			ws.finish();
			gdetector.startDrop();
			loading = false;
		}
	}
	class ThemeLoadingThread extends Thread {
	
		ThemeDetector detector=null; 
	
		ThemeLoadingThread(ThemeDetector detector, ThemeSprite current) {
			this.detector = detector;
			currentThemeSprite=current;
		}
	
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			ThemeDetector gdetector = new ThemeDetector();
	
			Animator level_a = new ComponentAnimator(animator.getWindow(),
					1000 / 24,false);
	
			level_a.setDebug(false);
			SceneDirector.getSceneDirector().removeScene("select");
			SceneDirector.getSceneDirector().addScene("select", level_a);
			WaitingSprite ws = new WaitingSprite(SceneDirector
					.getSceneDirector().getCurrentScene());
			Level current = LevelReader.readLevel("/Ressource/data/levels/"
					+ currentThemeSprite.getName(), ws);
			gdetector.setCurrentLevel(current);
			if(current==null)return;
			// handleLelevChunks(root.getChildren());
			// handleStartPosition(root.getChild("background"));
			level_a.setBackground(current.getBack());
			level_a.addSprite(current.getSpots());
			level_a.addSprite(current.getTraps());
			// //animator.addSprite(current.getStart());
			level_a.addSprite(current.getActions());
			level_a.setMusic("/Ressource/data/music/" + current.getMusic());
			ws.setMessage("Music started");
			level_a.setStartPos(current.getStart().getX(), current.getStart()
					.getY());
			new JPingus(current.getBack());
			level_a.setDetector(gdetector);
			SceneDirector.getSceneDirector().showScene("select", 500);
			ws.finish();
			
			loading = false;
		}
	}
	public Animator getSplash(String uri) {
		Animator anim = new ComponentAnimator(animator.getWindow(), 1000 / 24,false);
		anim.setDebug(false);
		AnimatedSprite splash = new AnimatedSprite("Starter");
		splash.init(0, 0, 1000);
		splash.freeze();
		splash.setActor(false);
		splash.setUseOffset(false);
		splash.setSprite(ImageToolkit.createSprite(uri,"ROT0"));
		anim.addSprite(splash);
		return anim;
	}
	Properties levelsWon;
	public ThemeDetector() {
		// TODO Auto-generated constructor stub
		loadProperties();
	}

	public void init() {
		SceneDirector.getSceneDirector().addScene("loose",
				getSplash("/Ressource/data/images/splash/GameOver.jpg"));
		SceneDirector.getSceneDirector().addScene("win",
				getSplash("/Ressource/data/images/splash/LevelComplete.jpg"));
		SceneDirector.getSceneDirector().addScene("start",
				getSplash("/Ressource/data/images/splash/Starter.jpg"));
		SceneDirector.getSceneDirector().showScene("start", 0);

	}

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
//		System.out.println(s1);
		if(s1.getClass()==TextScroller.class){
			System.exit(0);
		}

	}

	public void gettingOutOfSight(MovingSprite s1, Rectangle sight) {
		// TODO Auto-generated method stub
//		System.out.println(s1);
	}

	public void setAnimator(Animator anim) {
		// TODO Auto-generated method stub
		animator = anim;
		checkFinished();
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

	public void mouseOver(Renderable s) {
		// TODO Auto-generated method stub
		
		if (currentSprite != null) {
			currentSprite.init(currentSprite.getX(), currentSprite.getY() + 2, currentSprite.getZ());
			currentSprite = null;
		}
		if (s != null) {
			if(s.getClass()==TextScroller.class)return;
			currentSprite = s;
			currentSprite.init(currentSprite.getX(), currentSprite.getY() - 2, currentSprite.getZ());
		}
	}

	boolean scrollTextStarted = false;
	public void loadProperties(){
		levelsWon=new Properties();
		File f=new File("Theme.properties");
		if(f.exists()){
			try {
				levelsWon.load(new FileInputStream(f));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void saveProperties(){
		try {
			levelsWon.store(new FileOutputStream(new File("Theme.properties")),"Will be better later");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 27) {
			if(scrollTextStarted)
				System.exit(0);
			saveProperties();
			TextScroller scroll = new TextScroller(animator.getWindow(),
					new String[] {
				"The T E A M",
				"",
				"",
				"",
				"Inspiration : Pingus game",
				"visit : http://pingus.seul.org/",
				"",
				"",
				"Additional (3D) Graphism : Fabrice Kauffmann",
				"visit : http://scifi.skynetblogs.be",
				"",
				"",
				"Sound Tutorial: St Jean",
				"visit: http://www.jamendo.com/fr/artist/bouvier/",
				"",
				"Sound Egypt: Ehma : Les murailles d'Ismar",
				"La danse du Superlatif",
				"",
				"Coder : Papa Pingu",
				"visit : http://jpingus.skynetblogs.be",
				"",
				"",
				"(biggest) Supporter : Anne Toussaint"
			}, null, 35);
			animator.addSprite(scroll);
			scrollTextStarted=true;
		}

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

	public void clicked(MouseEvent e) {
		if (currentSprite.getClass() == LevelSprite.class && !loading) {
			loading = true;
			// System.out.println("Click : ");
			LevelLoadingThread loadLevel = new LevelLoadingThread(this, (LevelSprite) currentSprite);
			currentSprite.init(currentSprite.getX(), currentSprite.getY() + 2, currentSprite.getZ());
			currentSprite = null;
			loadLevel.start();
			return;
		}
		if (currentSprite.getClass() == ThemeSprite.class && !loading) {
			loading = true;
			// System.out.println("Click : ");
			ThemeLoadingThread loadLevel = new ThemeLoadingThread(this, (ThemeSprite) currentSprite);
			currentSprite.init(currentSprite.getX(), currentSprite.getY() + 2, currentSprite.getZ());
			currentSprite = null;
			loadLevel.start();

		}
	}
	boolean added=false;
	public void won(int pingus) {
		currentLevelSprite.initSprite(0, 1);
		levelsWon.setProperty(currentLevelSprite.getName(),""+pingus);
		saveProperties();
		SceneDirector.getSceneDirector().showScene("win", 500);
		SceneDirector.getSceneDirector().removeScene("level");
		SceneDirector.getSceneDirector().showScene("select", 3000);
		if(!added){
			Vector v = currentLevel.getSpots();
			
			for(int i=v.size()-1;i>=0;i--){
				Object o = v.elementAt(i);
				if(o instanceof LevelSprite){
					LevelSprite ls=(LevelSprite)o;
					if(!levelsWon.containsKey(ls.getName())){
						return;
					}
				}
			}
			animator.addSprite(themes);
			added=true;
		}
	}

	public void lost() {
		// currentLevel.initSprite(0, 0);
		SceneDirector.getSceneDirector().showScene("loose", 500);
		SceneDirector.getSceneDirector().removeScene("level");
		SceneDirector.getSceneDirector().showScene("select", 3000);
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}
	Vector themes=new Vector();
	public void checkFinished(){
		boolean full=true;
		if(currentLevel==null )return;
		Vector v = currentLevel.getSpots();
		for(int i=v.size()-1;i>=0;i--){
			Object o = v.elementAt(i);
			if(o instanceof LevelSprite){
				LevelSprite ls=(LevelSprite)o;
				if(levelsWon.containsKey(ls.getName())){
					ls.initSprite(0,1);
				}else{
					ls.initSprite(0,0);
					full=false;
				}
			}
			if(o instanceof ThemeSprite){
				if(((ThemeSprite)o).isHidden()){
					themes.addElement(o);
					v.remove(o);
				}
			}
		}
		if(full && animator != null){
			animator.addSprite(themes);
		}
	}
	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
		checkFinished();
	}

}
