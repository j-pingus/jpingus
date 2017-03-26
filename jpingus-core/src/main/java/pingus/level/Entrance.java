/**
 * 
 */
package pingus.level;

public class Entrance {
	private int x, y, z,rate;
	private boolean right=true;
	public boolean goRight(){
		return right;
	}
	public boolean goLeft(){
		return !right;
	}
	public void setDirection(String direction){
		if(direction==null)return;
		right=direction.equalsIgnoreCase("right");
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public String getXml(){
		return "<entrance><position>"+
		"<x-pos>"+x+"</x-pos>"+
		"<y-pos>"+y+"</y-pos>"+
		"<z-pos>"+z+"</z-pos>"+
		"</position>"+
		"<type>generic</type>"+
		"<direction>"+(right?"right":"left")+"</direction>"+
		"<release-rate>"+rate+"</release-rate>"+
		"<owner-id>0</owner-id>"+
		"</entrance>";
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
}