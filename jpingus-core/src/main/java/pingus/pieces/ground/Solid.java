package pingus.pieces.ground;

import pingus.PingusSprite;
import gee.imaging.sprites.animated.AnimatedSprite;

public class Solid  extends PingusSprite {
	public Solid(String name) {
		super(name,"worldobj");
	}
	public void init(int x, int y, int z) {
		// TODO Auto-generated method stub
		super.init(x, y, z);
	}
	public String getXml() {
		// TODO Auto-generated method stub
		return getXml("worldobj","solid","<type>solid</type>");
	}

}
