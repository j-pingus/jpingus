package pingus.level;

import java.io.File;
import java.util.Vector;

import pingus.PingusSprite;
import pingus.pieces.gui.ActionSprite;
import pingus.pieces.gui.StartPosSprite;

public class Level {
	private String title = "title here", description = "desciption here",
			author = "gev,fka", comment = "no comments yet";
	private File xmlFile;
	public Level() {
		setStart(new StartPosSprite());
	}
	public Level(File level) {
		xmlFile=level;
		setStart(new StartPosSprite());
	}

	String music;

	private int width = 800;

	private int height = 600;

	private int nbOfPingus = 10;

	private int nbToSave = 5;

	private int time = -1;

	private 	int difficulty = 0;

	private boolean playable = true;

	private Background back = new Background();

	private Vector entrances = new Vector();

	private PingusSprite start;

	private Vector<ActionSprite> actions = new Vector<ActionSprite>();

	Vector<PingusSprite> spotSprites = new Vector<PingusSprite>();

	public void addAction(ActionSprite s) {
		s.setUseOffset(false);
		actions.addElement(s);
	}

	public void addSpot(PingusSprite s) {
		s.setUseOffset(true);
		spotSprites.addElement(s);
	}
	public void removeSpot(PingusSprite s) {
		spotSprites.removeElement(s);
	}

	public Vector<PingusSprite> getSpots() {
		return spotSprites;
	}

	Vector<PingusSprite> trapSprites = new Vector<PingusSprite>();

	public void addTrap(PingusSprite s) {
		s.setUseOffset(true);
		trapSprites.addElement(s);
	}

	public void removeTrap(PingusSprite s) {
		trapSprites.removeElement(s);
	}

	public Vector<PingusSprite> getTraps() {
		return trapSprites;
	}

	public void addBackground(PingusSprite s) {
		s.setUseOffset(true);
		back.addSprite(s);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	public Background getBack() {
		return back;
	}

	public void setBack(Background back) {
		back.setUseOffset(true);
		this.back = back;
	}

	public PingusSprite getStart() {
		return start;
	}

	public void setStart(PingusSprite start) {
		this.start = start;
	}

	public Vector getEntrances() {
		return entrances;
	}

	public Entrance getEntrance(int i) {
		return (Entrance) entrances.elementAt(i % entrances.size());
	}

	public void addEntrance(Entrance entrance) {
		entrances.addElement(entrance);
		// this.entrance = entrance;
	}

	public Vector getActions() {
		return actions;
	}

	public int getNbOfPingus() {
		return nbOfPingus;
	}

	public int getNbToSave() {
		return nbToSave;
	}

	public String getMusic() {
		return music;
	}

	public String getXml() {
		String ret = "<?xml version=\"1.0\"  encoding=\"ISO-8859-1\"?>\n";
		ret += "<pingus-level>\n";
		ret += "<global>\n";
		ret += "<levelname lang=\"en\">" + title + "</levelname>\n";
		ret += "<description lang=\"en\">" + description + "</description>\n";
		ret += "<author>" + author + "</author>\n";
		ret += "<number-of-pingus>" + nbOfPingus + "</number-of-pingus>\n";
		ret += "<number-to-save>" + nbToSave + "</number-to-save>\n";
		ret += "<time>" + time + "</time>\n";
		ret += "<width>" + width + "</width>\n";
		ret += "<height>" + height + "</height>\n";
		ret += "<difficulty>" + difficulty + "</difficulty>\n";
		ret += "<playable>" + (playable ? 1 : 0) + "</playable>\n";
		ret += "<comment>" + comment + "</comment>\n";
		ret += "<music>" + music + "</music>\n";
		ret += "</global>\n";
		ret += "<action-list>\n";
		for (int i = 0; i < actions.size(); i++) {
			PingusSprite sp = (PingusSprite) actions.elementAt(i);
			ret += sp.getXml();
		}
		ret += "</action-list>\n";
		for (int i = 0; i < entrances.size(); i++) {
			Entrance e = (Entrance) entrances.elementAt(i);
			ret += e.getXml();
		}
		ret += back.getXml();
		ret += "<start-position>\n";
		ret += "<position>\n";
		ret += "<x-pos>" + getStart().getX() + "</x-pos>\n";
		ret += "<y-pos>" + getStart().getY() + "</y-pos>\n";
		ret += "<z-pos>" + getStart().getZ() + "</z-pos>\n";
		ret += "</position>\n";
		ret += "</start-position>\n";
		for (int i = 0; i < spotSprites.size(); i++) {
			PingusSprite sp = (PingusSprite) spotSprites.elementAt(i);
			ret += sp.getXml();
		}
		ret += "</pingus-level>";
		return ret;
		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * <exit use-old-pos-handling="0"> <position> <x-pos>1152</x-pos>
		 * <y-pos>845</y-pos> <z-pos>0</z-pos> </position> <surface><resource
		 * type="datafile"> <resource-datafile>exits</resource-datafile>
		 * <resource-ident>exits/ice2</resource-ident> <modifier>ROT0</modifier>
		 * </resource></surface> <owner-id>0</owner-id></exit> <background
		 * type="surface"> <surface><resource type="datafile">
		 * <resource-datafile>textures</resource-datafile>
		 * <resource-ident>textures/happyclouds</resource-ident> <modifier>ROT0</modifier>
		 * </resource></surface> <color> <red>0</red> <green>0</green>
		 * <blue>0</blue> <alpha>0.5</alpha> </color> <scroll-x>0.5</scroll-x>
		 * <scroll-y>0</scroll-y> <para-x>0.5</para-x> <para-y>0.5</para-y>
		 * <stretch-x>0</stretch-x> <stretch-y>1</stretch-y> <keep-aspect>1</keep-aspect>
		 * <position> <x-pos>-206</x-pos> <y-pos>-56</y-pos> <z-pos>-150</z-pos>
		 * </position> </background>
		 * 
		 * <hotspot> <surface><resource type="datafile">
		 * <resource-datafile>entrances</resource-datafile>
		 * <resource-ident>entrances/snow_back</resource-ident> <modifier>ROT0</modifier>
		 * </resource></surface> <position> <x-pos>157.777</x-pos>
		 * <y-pos>213.585</y-pos> <z-pos>-50</z-pos> </position> <speed>-1</speed>
		 * <parallax>1</parallax> </hotspot>
		 * 
		 * <worldobj type="groundpiece"> <type>ground</type> <surface><resource
		 * type="datafile"> <resource-datafile>groundpieces-ground</resource-datafile>
		 * <resource-ident>snow/way</resource-ident> <modifier>ROT0</modifier>
		 * </resource></surface> <position> <x-pos>331</x-pos> <y-pos>809</y-pos>
		 * <z-pos>0</z-pos> </position> </worldobj>
		 * 
		 * <entrance> <position> <x-pos>202</x-pos> <y-pos>246</y-pos>
		 * <z-pos>0</z-pos> </position> <type>generic</type> <direction>right</direction>
		 * <release-rate>150</release-rate> <owner-id>0</owner-id> </entrance>
		 * 
		 * 
		 * <start-position> <position> <x-pos>470</x-pos> <y-pos>358</y-pos>
		 * <z-pos>1000</z-pos> </position> </start-position>
		 * 
		 * </pingus-level>
		 * 
		 */
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setMusic(String music) {
		this.music = music;
	}

	public void setNbOfPingus(int nbOfPingus) {
		this.nbOfPingus = nbOfPingus;
	}

	public void setNbToSave(int nbToSave) {
		this.nbToSave = nbToSave;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isPlayable() {
		return playable;
	}

	public void setPlayable(boolean playable) {
		this.playable = playable;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return "actions:" + actions.size() + ",entrances:" + entrances.size()
				+ ",spotSprites:" + spotSprites.size() + ",trapSprites:"
				+ trapSprites.size() + ",\nback:" + back;
	}

	public File getXmlFile() {
		return xmlFile;
	}

	public void setXmlFile(File xmlFile) {
		this.xmlFile = xmlFile;
	}
}
