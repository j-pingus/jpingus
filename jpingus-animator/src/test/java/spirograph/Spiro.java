package spirograph;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;

public class Spiro extends Frame{
	public static void main(String[] args) {
		Spiro spiro=new Spiro();
		spiro.setVisible(true);
	}
	public Spiro() {
		super();
		initialize();
	}
	private void initialize(){
		this.setSize(800, 600);
		this.setTitle("Spiro");
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
//		g.setColor(Color.GREEN);
//		trochoid(g,100,50);
//		g.setColor(Color.RED);
//		trochoid(g,100,100);
//		g.setColor(Color.BLUE);
//		trochoid(g,100,150);
		g.setColor(Color.GREEN);
		epicicloid(g,1, 100, 50, 10);
		g.setColor(Color.RED);
		epicicloid(g,1, 100, 10, 50);
		
		g.setColor(Color.BLUE);
		epicicloid(g,10, 110,-65,37);
	}
	void trochoid(Graphics g, double radius,double distance){
		Point cur=null,prev=null;
		for(double phi=0;phi<10;phi+=0.1){
			prev=cur;
			cur=trochoid(phi, radius, distance);
			if(prev!=null){
				g.drawLine(prev.x+400, prev.y+300, cur.x+400, cur.y+300);
			}
		}
		
	}
	void epicicloid(Graphics g, double tours,double radius,double radius2,double distance){
		Point cur=null,prev=null;
		for(double phi=0;phi<6.3*tours;phi+=0.0001){
			prev=cur;
			cur=epicicloid(phi, radius, radius2, distance);
			if(prev!=null){
				g.drawLine(prev.x+400, prev.y+300, cur.x+400, cur.y+300);
			}
		}
		
	}
	Point epicicloid(double phi,double R, double r, double p){
		Point ret = new Point();
		ret.x=(int)(((R+r)*Math.cos(phi))+(p*Math.cos((R+r)*phi/r)));
		ret.y=(int)(((R+r)*Math.sin(phi))+(p*Math.sin((R+r)*phi/r)));
		return ret;
	}
	Point trochoid(double phi,double R,double D){
		double sin=Math.sin(phi);
		double cos=Math.cos(phi);
		Point ret = new Point();
		ret.x=(int)(R*phi-D*sin);
		ret.y=(int)(R-D*cos);
		return ret;
	}
}
