package pingus.macro;

import pingus.JPingus;

public interface PingusIdentifier {
	public int getId(JPingus pingus);
	public JPingus getPingus(int id);
}
