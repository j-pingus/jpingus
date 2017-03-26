package pingus.level;

import gee.imaging.Ressourcer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import pingus.Actions;
import pingus.PingusBackground;
import pingus.PingusSprite;
import pingus.Transparent;
import pingus.pieces.Exit;
import pingus.pieces.Trap;
import pingus.pieces.ground.Ground;
import pingus.pieces.ground.Liquid;
import pingus.pieces.ground.Solid;
import pingus.pieces.gui.ActionSprite;
import pingus.pieces.gui.LevelSprite;
import pingus.pieces.gui.StartPosSprite;
import pingus.pieces.gui.ThemeSprite;
import pingus.pieces.gui.WaitingSprite;
import pingus.ressource.Ressource;
import pingus.ressource.RessourceLocator;

public class LevelReader {
	static SAXBuilder builder = new SAXBuilder();

	public LevelReader() {
	}

	public static Level readLevel(String level, WaitingSprite ws) {
		// System.out.println(level);
		return readLevel(new Level(null),
				Ressourcer.getResourceAsStream(level), ws);
	}

	public static Level readLevel(File level, WaitingSprite ws) {

		try {
			// System.out.println(level);
			return readLevel(new Level(level), new FileInputStream(level), ws);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static Level readLevel(Level ret, InputStream level,
			WaitingSprite ws) {
		try {
			if (level == null) {
				System.err.println("Level is not found");
				return null;
			}
			Document doc = builder.build(level);
			Element root = doc.getRootElement();

			handleGlobals(ret, root.getChild("global"));
			if (ws != null)
				ws.setMessage("Globals loaded");
			handleActionList(ret, root.getChild("action-list"));
			if (ws != null)
				ws.setMessage("actions loaded");
			handleBackground(ret, root.getChild("background"));
			if (ws != null)
				ws.setMessage("Background loaded");
			XPath entrance = XPath.newInstance("//entrance");
			handleEntrance(ret, entrance.selectNodes(root));
			if (ws != null)
				ws.setMessage("Entrance loaded");
			handleStartPosition(ret, root.getChild("start-position"));
			if (ws != null)
				ws.setMessage("Start loaded");
			handleElements(ret, root);
			if (ws != null)
				ws.setMessage("Elements loaded");
			System.out.println(ret);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void handleGlobals(Level current, Element globals) {
		/*
		 * <number-of-pingus>10</number-of-pingus> <number-to-save>5</number-to-save>
		 * <time>-1</time> <width>1400</width> <height>700</height>
		 * <difficulty>0</difficulty> <playable>1</playable>
		 */
		current.setTitle(globals.getChildText("levelname"));
		current.setDescription(globals.getChildText("description"));
		current.setNbOfPingus( Integer.parseInt(globals
				.getChildText("number-of-pingus")));
		current.setTime(Integer.parseInt(globals.getChildText("time"))) ;
		current.setNbToSave(Integer.parseInt(globals
				.getChildTextTrim("number-to-save"))) ;
		current.setWidth(Integer.parseInt(globals.getChildText("width"))) ;
		current.setHeight(Integer.parseInt(globals.getChildText("height"))) ;
		// setSize(new Dimension(current.width, current.height));
		if (globals.getChild("difficulty") != null)
			current.setDifficulty(Integer.parseInt(globals
					.getChildText("difficulty"))) ;
		if (globals.getChild("playable") != null)
			current.setPlayable(Integer.parseInt(globals
					.getChildText("playable")) == 1) ;
		if (globals.getChild("music") != null) {
			current.setMusic(globals.getChildText("music")) ;
		}
	}
	public static int getActionCount(Element action){
		int actionCount =Integer.parseInt(action.getAttributeValue("count", "0"));
		if(actionCount==0){
			actionCount=Integer.parseInt(action.getTextTrim());
		}
		
		return actionCount;
	}
	public static void handleActionList(Level current, Element actions) {
		if (actions == null)
			return;
		// System.out.println(actions.getChildren());
		ActionSprite.reinit();
		for(Actions a:Actions.values()){
			Element action = actions.getChild(a.getTagname());
			if(action!= null){
				current.addAction(new ActionSprite(a,getActionCount(action)));
			}
		}
	}

	public static void handleBackground(Level current, Element back) {

		if (back == null)
			return;
		if (back.getChild("position") != null) {
			current.getBack().setX((int) Float.parseFloat(back.getChild("position")
					.getChildText("x-pos")));
			current.getBack().setY((int) Float.parseFloat(back.getChild("position")
					.getChildText("y-pos")));
			current.getBack().setZ((int) Float.parseFloat(back.getChild("position")
					.getChildText("z-pos")));
		}
		if (back.getAttributeValue("type", "surface").equals("surface")) {
			Element ressource = back.getChild("surface").getChild("resource");
			try {
				Ressource r = RessourceLocator.findRessource(ressource
						.getAttributeValue("type"), ressource
						.getChildText("resource-datafile"), ressource
						.getChildText("resource-ident"), ressource
						.getChildText("modifier"));
				if (r != null) {
					// Image backImg = ImageToolkit.createTiledImage(
					// r.getImage(0), current.width, current.height);

					current.getBack().init(null, current.getWidth(), current.getHeight());
					current.addSpot(new PingusBackground(r, current.getWidth(),
							current.getHeight()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void handleEntrance(Level current, List entrances) {
		for (int i = 0; i < entrances.size(); i++) {
			Element entrance = (Element) entrances.get(i);
			if (entrance != null) {
				Entrance e = new Entrance();
				e.setX((int) Float.parseFloat(entrance.getChild("position")
						.getChildText("x-pos")));
				e.setY((int) Float.parseFloat(entrance.getChild("position")
						.getChildText("y-pos")));
				e.setZ((int) Float.parseFloat(entrance.getChild("position")
						.getChildText("z-pos")));
				e.setRate((int) Float.parseFloat(entrance.getChildText("release-rate")));
				e.setDirection(entrance.getChildText("direction"));
				current.addEntrance(e);
			}
		}
	}
	public static void handleExits(Level current, List exits) {
		for (int i = 0; i < exits.size(); i++) {
			Element exit = (Element) exits.get(i);
			if (exit != null) {
				Entrance e = new Entrance();
				e.setX((int) Float.parseFloat(exit.getChild("position")
						.getChildText("x-pos")));
				e.setY((int) Float.parseFloat(exit.getChild("position")
						.getChildText("y-pos")));
				e.setZ((int) Float.parseFloat(exit.getChild("position")
						.getChildText("z-pos")));
				e.setRate((int) Float.parseFloat(exit.getChildText("release-rate")));
				e.setDirection(exit.getChildText("direction"));
				current.addEntrance(e);
			}
		}
	}

	public static void handleGroups(Level current, Element root) {
		List childs = root.getChildren("group");
		for (Iterator iter = childs.iterator(); iter.hasNext();) {
			Element group = (Element) iter.next();
			handleElements(current, group);
		}
	}

	public static void handleElements(Level current, Element root) {
		handleGroups(current, root);
		handleTraps(current, root);
		List childs = root.getChildren();
		for (Iterator iter = childs.iterator(); iter.hasNext();) {
			Element levelChunk = (Element) iter.next();
			String element_name = levelChunk.getName();
			if ("background".equals(element_name))
				continue;
			String type = levelChunk.getAttributeValue("type");
			String subType = levelChunk.getChildText("type");
			if (subType == null)
				subType = "none";
			PingusSprite s = null;
			if (levelChunk.getChild("surface") == null) {
				// System.out.println(element_name + "[" + type + "]/" + subType
				// + " has no surface");
				continue;
			}
			Element ressource = levelChunk.getChild("surface").getChild(
					"resource");

			Ressource sp = null;
			try {
				sp = findSprite(ressource);
			} catch (Throwable e) {
				System.err.println(element_name + "[" + type + "]/" + subType
						+ " nok:" + e);
				// e.printStackTrace(System.out);
				continue;
			}
			if (sp == null) {
				System.err.println(element_name + "[" + type + "]/" + subType
						+ " nok");
				System.err.println("\tno image for "
						+ ressource.getChildText("resource-datafile"));
				continue;
			}
			int x = (int) Float.parseFloat(levelChunk.getChild("position")
					.getChildText("x-pos"));
			int y = (int) Float.parseFloat(levelChunk.getChild("position")
					.getChildText("y-pos"));
			int z = (int) Float.parseFloat(levelChunk.getChild("position")
					.getChildText("z-pos"));
			int paralax = 0;
			if (levelChunk.getChild("parallax") != null) {
				paralax = (int) Float.parseFloat(levelChunk
						.getChildText("parallax"));
			}
			if ("level".equals(element_name)) {
				s = new LevelSprite(levelChunk.getChildText("ref"), levelChunk
						.getChildText("behavior"));
				s.init(x, y, z);
				s.setSprite(sp);
				s.setActor(false);
				s.setSpeedX(0);
				s.setSpeedY(0);
				current.addSpot(s);

			} else if ("theme".equals(element_name)) {
				s = new ThemeSprite(levelChunk.getChildText("ref"), levelChunk
						.getChildText("behavior"), levelChunk
						.getChild("hidden") != null);
				s.init(x, y, z);
				s.setSprite(sp);
				s.setActor(false);
				s.setSpeedX(0);
				s.setSpeedY(0);
				current.addSpot(s);

			} else if ("worldobj".equals(element_name)
					|| "groundpiece".equals(element_name)) {
				if ("ground".equals(subType)) {
					s = new Ground(ressource.getChildText("resource-ident"));
					s.init(x, y, 0);

				} else if ("solid".equals(subType)) {
					s = new Solid(ressource.getChildText("resource-ident"));

					s.init(x, y, 0);
				} else if ("transparent".equals(type) || "transparent".equals(subType)) {
					s = new Transparent(ressource
							.getChildText("resource-ident"), 0.8f);
					s.init(x, y, z-1);

				} else if ("remove".equals(type)|| "remove".equals(subType)) {
					continue;
				} else {
					System.err.println("Unknown type?"+ressource.getChildText("resource-ident")+":" + type+"/"+subType); 
					s = new PingusSprite(ressource
							.getChildText("resource-ident"),"Unknown");
					s.init(x, y, 0);
				}
				//
				s.setSprite(sp);
				s.setActor(false);
				s.setSpeedX(0);
				s.setSpeedY(0);
				if (s.getClass() == Solid.class) {
					current.addSpot(s);
				}
				if (s.getClass() == Transparent.class) {
					current.addSpot(s);
				}else{
					current.addBackground(s);
				}

			} else if ("liquid".equals(element_name)) {
				// System.out.println(type);
				int x3 = 0;
				s = new Liquid(ressource.getChildText("resource-ident"));
				((Liquid) s).setSprite(sp, current.getWidth());
				s.setActor(false);
				s.init(x, y, z);
				// s.initSprite(x3++, 0);
				s.setSpeedX(0);
				s.setSpeedY(0);
//				if (z < 0) {
					current.addSpot(s);
//				} else {
//					current.addBackground(s);
//				}
			} else if ("exit".equals(element_name)) {
				// System.out.println(type);
				boolean oldPosHandling = levelChunk
						.getAttribute("use-old-pos-handling") == null;
				s = new Exit(ressource.getChildText("resource-ident"));
				s.setActor(false);
				s.setSprite(sp);
				if (oldPosHandling) {
					s.init(x, y, -1);
				} else {
					s.init(x - (sp.getWidth() / 2), y - sp.getHeight(), -1);
				}
				s.setSpeedX(0);
				s.setSpeedY(0);
				current.addSpot(s);
				// } else {
				// System.err.println(element_name);
				// s = new
				// AnimatedSprite(ressource.getChildText("resource-ident"));
				// s.setActor(false);
				// s.setSprite(sp);
				// s.init(x, y, z - paralax);
				// s.setSpeedX(0);
				// s.setSpeedY(0);
				// if (z - paralax == 0) {
				// current.addBackground(s);
				// } else {
				// current.addSpot(s);
				// }
			} else if ("hotspot".equals(element_name)) {
				s = new PingusSprite(ressource.getChildText("resource-ident"),"hotspot");
				s.setActor(false);
				s.setSprite(sp);
				s.init(x, y, -1);
				s.setSpeedX(0);
				s.setSpeedY(0);
				// if (z == 0) {
				// current.addBackground(s);
				// } else {
				current.addSpot(s);
				// }

			} else if ("background".equals(element_name)) {
			} else {
				System.err.println(element_name + "[" + type + "]/" + subType
						+ " not treated");

			}
		}
	}

	public static void handleStartPosition(Level current, Element estart) {
		if (estart == null)
			return;
		StartPosSprite start = new StartPosSprite();
		start.setUseOffset(true);
		start.init((int) Float.parseFloat(estart.getChild("position")
				.getChildText("x-pos"))
				, (int) Float.parseFloat(estart
				.getChild("position").getChildText("y-pos"))
				, (int) Float.parseFloat(estart
				.getChild("position").getChildText("z-pos")));
		start.freeze();
		start.setSpeedX(0);
		start.setSpeedY(0);
		current.setStart(start) ;

	}

	public static void handleTraps(Level current, Element root) {

		List childs = root.getChildren("trap");
		for (Iterator iter = childs.iterator(); iter.hasNext();) {
			try {
				Element aTrap = (Element) iter.next();
				String type = aTrap.getChildText("type");
				Ressource sp = RessourceLocator.findRessource("data", "traps",
						"Traps/" + type, "RT0");
				if (sp == null) {
					System.err.println("trap : " + type + " not found");
					continue;
				}
				int x = (int) Float.parseFloat(aTrap.getChild("position")
						.getChildText("x-pos"));
				int y = (int) Float.parseFloat(aTrap.getChild("position")
						.getChildText("y-pos"));
				int z = (int) Float.parseFloat(aTrap.getChild("position")
						.getChildText("z-pos"));
				Trap m = new Trap("Traps/" + type);
				m.setSprite(sp);
				m.init(x, y, z);
				m.setSpeedX(0);
				m.setSpeedY(0);
				m.freeze();
				current.addTrap(m);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static Ressource findSprite(Element ressource) throws IOException {
		Ressource r=  RessourceLocator.findRessource(ressource
				.getAttributeValue("type"), ressource
				.getChildText("resource-datafile"), ressource
				.getChildText("resource-ident"), ressource
				.getChildText("modifier"));
//		System.out.println("findsprite for "+ressource+"="+r);
		return r;
	}

}
