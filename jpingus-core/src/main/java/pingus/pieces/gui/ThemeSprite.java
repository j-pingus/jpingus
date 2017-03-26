package pingus.pieces.gui;

import pingus.PingusSprite;

public class ThemeSprite extends PingusSprite {
	String behavior;
	boolean hidden=false;
	public ThemeSprite(String level, String behavior,boolean hidden) {
		super(level,"theme");
		this.behavior = behavior;
		this.hidden=hidden;
	}

	public String getXml() {
		return getXml("theme", "theme", "<ref>" + getName() + "</ref>"
				+ "<behavior>" + behavior + "</behavior>"+(hidden?"<hidden/>":""));
	}

	public String getBehavior() {
		return behavior;
	}

	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
}
