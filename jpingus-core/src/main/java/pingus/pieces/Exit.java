package pingus.pieces;

import java.awt.Rectangle;

import pingus.PingusSprite;
import pingus.ressource.Ressource;
import pingus.ressource.RessourceLocator;

import gee.imaging.sprites.Sprite;
import gee.imaging.sprites.animated.AnimatedSprite;

public class Exit extends PingusSprite {
	Ressource spriteFlag = RessourceLocator.findRessource("datafile", "core",
			"misc/flag0", "ROT0");

	public Exit(String name) {
		super(name,"exit");

	}

	public void setSprite(Sprite sprite) {
		// TODO Auto-generated method stub
		super.setSprite(sprite);
		AnimatedSprite s = associate(sprite.getWidth() - spriteFlag.getWidth(),
				sprite.getHeight() - spriteFlag.getHeight());
		s.setSprite(spriteFlag);
		s.freeze();

	}

	public Rectangle getBounds(boolean offset) {
		return new Rectangle(offset ? x() : getX() - 1 + getWidth() / 2,
				offset ? y() : getY(), 2, getHeight());
	}

	public String getXml() {
		return getXml("exit", "exit", "<type>exit</type>");
	}
}
