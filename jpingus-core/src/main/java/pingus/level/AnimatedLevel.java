package pingus.level;

import java.awt.Frame;
import java.io.File;
import java.util.Vector;

import pingus.PingusBackground;
import pingus.PingusSprite;
import pingus.behaviours.ViewerDetector;
import pingus.pieces.gui.ActionSprite;
import gee.imaging.animator.Animator;

public class AnimatedLevel {
	private Animator animator = null;
	private Level level = null;
	public AnimatedLevel(Animator anim){
		this.animator=anim;
	}
	public Level getLevel() {
		
		return level;
	}
	public void setLevel(Level current,Frame frame) {
		animator.clearAnimation();
		animator.setBackground(current.getBack());
		animator.addSprite(current.getSpots());
		animator.addSprite(current.getStart());
		animator.addSprite(current.getActions());
		animator.addSprite(current.getTraps());
//		animator.addSprite(new TimerSprite());
		animator.setMusic("/Ressource/data/music/" + current.getMusic());		
		animator.setStartPos(current.getStart().getX(), current.getStart()
				.getY());
		animator.setDetector(new ViewerDetector(frame,animator.getWindow(), current));
		animator.start();
		this.level = current;
	}
	public void addAction(ActionSprite s) {
		level.addAction(s);
		animator.addSprite(s);
	}
	public void addBackground(PingusSprite s) {
		level.addBackground(s);
	}
	public void addEntrance(Entrance entrance) {
		level.addEntrance(entrance);
	}
	public void addSpot(PingusSprite s) {
		level.addSpot(s);
		animator.addSprite(s);
	}
	public void addTrap(PingusSprite s) {
		level.addTrap(s);
		animator.addSprite(s);
	}
	public Vector getActions() {
		return level.getActions();
	}
	public String getAuthor() {
		return level.getAuthor();
	}
	public Background getBack() {
		return level.getBack();
	}
	public String getComment() {
		return level.getComment();
	}
	public String getDescription() {
		return level.getDescription();
	}
	public int getDifficulty() {
		return level.getDifficulty();
	}
	public Entrance getEntrance(int i) {
		return level.getEntrance(i);
	}
	public Vector getEntrances() {
		return level.getEntrances();
	}
	public int getHeight() {
		return level.getHeight();
	}
	public String getMusic() {
		return level.getMusic();
	}
	public int getNbOfPingus() {
		return level.getNbOfPingus();
	}
	public int getNbToSave() {
		return level.getNbToSave();
	}
	public Vector getSpots() {
		return level.getSpots();
	}
	public PingusSprite getStart() {
		return level.getStart();
	}
	public int getTime() {
		return level.getTime();
	}
	public String getTitle() {
		return level.getTitle();
	}
	public Vector getTraps() {
		return level.getTraps();
	}
	public int getWidth() {
		return level.getWidth();
	}
	public String getXml() {
		return level.getXml();
	}
	public File getXmlFile() {
		return level.getXmlFile();
	}
	public int hashCode() {
		return level.hashCode();
	}
	public boolean isPlayable() {
		return level.isPlayable();
	}
	public void removeTrap(PingusSprite s) {
		level.removeTrap(s);
	}
	public void setAuthor(String author) {
		level.setAuthor(author);
	}
	PingusBackground back;
	public void setBackground(PingusBackground back) {
		removeSpot(this.back);
		this.back=back;
		addSpot(back);
	}
	public void setComment(String comment) {
		level.setComment(comment);
	}
	public void setDescription(String description) {
		level.setDescription(description);
	}
	public void setDifficulty(int difficulty) {
		level.setDifficulty(difficulty);
	}
	public void setHeight(int height) {
		level.setHeight(height);
	}
	public void setMusic(String music) {
		level.setMusic(music);
	}
	public void setNbOfPingus(int nbOfPingus) {
		level.setNbOfPingus(nbOfPingus);
	}
	public void setNbToSave(int nbToSave) {
		level.setNbToSave(nbToSave);
	}
	public void setPlayable(boolean playable) {
		level.setPlayable(playable);
	}
	public void setStart(PingusSprite start) {
		level.setStart(start);
	}
	public void setTime(int time) {
		level.setTime(time);
	}
	public void setTitle(String title) {
		level.setTitle(title);
	}
	public void setWidth(int width) {
		level.setWidth(width);
	}
	public void setXmlFile(File xmlFile) {
		level.setXmlFile(xmlFile);
	}
	public String toString() {
		return level.toString();
	}
	public void removeSpot(PingusSprite s) {
		level.removeSpot(s);
		animator.removeSprite(s);
	}
}
