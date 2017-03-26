package pingus.pieces.ground;

import pingus.PingusSprite;
import gee.imaging.sprites.animated.AnimatedSprite;

public class Ground  extends PingusSprite {
	public Ground(String name) {
		super(name,"worldobj");
	}

	public String getXml() {
		// TODO Auto-generated method stub
		return getXml("worldobj","groundpiece","<type>ground</type>");
	}

}
