package pingus.pieces.gui;

import gee.imaging.ImageToolkit;
import gee.imaging.sprites.Sprite;
import gee.imaging.sprites.animated.AnimatedSprite;

import java.awt.Image;

import pingus.PingusSprite;

public class StartPosSprite extends PingusSprite{
	Image i = ImageToolkit
	.getImage("/Ressource/data/images/core/editor/start_pos.png","ROT0");
	public StartPosSprite() {
		super("StartPos","start-position");
		setSprite(new Sprite(i));
		init(100,100,1000);
		setSpeedX(0);
		setSpeedY(0);
		setUseOffset(true);
		// TODO Auto-generated constructor stub
	}
	public void setX(int x) {
		// TODO Auto-generated method stub
		super.setX(x-(getWidth()/2));
	}
	public void setY(int y) {
		// TODO Auto-generated method stub
		super.setY(y-(getHeight()/2));
	}
	public String getXml() {
		// TODO Auto-generated method stub
		return null;
	}
}
