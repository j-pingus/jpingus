package pingus.pieces.gui;

import pingus.PingusSprite;

public class LevelSprite extends PingusSprite {
	String behavior;

	public LevelSprite(String level, String behavior) {
		super(level,"level");
		this.behavior = behavior;
	}

	public String getXml() {
		return getXml("level", "level", "<ref>" + getName() + "</ref>"
				+ "<behavior>" + behavior + "</behavior>");
	}

	public String getBehavior() {
		return behavior;
	}

	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}
}
