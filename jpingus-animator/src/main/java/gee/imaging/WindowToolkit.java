package gee.imaging;

import gee.imaging.animator.ComponentAnimator;

import java.awt.BufferCapabilities;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferStrategy;

public class WindowToolkit {
	boolean fullscreen = false;

	public static void initWindow(Frame win, boolean fullscreen,
			int width, int height) {
		
		GraphicsDevice gd;
		DisplayMode dm = null;
		BufferStrategy strategy = null;
		BufferCapabilities bufCap = null;
		BufferCapabilities.FlipContents flipContents = null;
		if (fullscreen) {
			gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
					.getDefaultScreenDevice();
			DisplayMode[] dmodes = gd.getDisplayModes();
			for (int i = 0; i < dmodes.length; i++) {
				if (dmodes[i].getWidth() == width
						&& dmodes[i].getHeight() == height) {
					dm = dmodes[i];
				}
			} // Create the back buffer
			if (dm != null) {
				win.setUndecorated(true);
				gd.setFullScreenWindow(win);
				GraphicsConfiguration gc = gd.getDefaultConfiguration();
				win.requestFocus();
				gd.setDisplayMode(dm);
				int numBuffers = 2; // Includes front buffer
				win.createBufferStrategy(numBuffers);
				strategy = win.getBufferStrategy();
				bufCap = strategy.getCapabilities();
				flipContents = bufCap.getFlipContents();
			} else {
				win.setSize(width, height);
			}
		}
		if (!fullscreen) {
			win.setUndecorated(false);
			win.setSize(width+8, height+34);
			win.addComponentListener(new ComponentListener(){
				public void componentHidden(ComponentEvent e) {
					// TODO Auto-generated method stub
					
				}

				public void componentMoved(ComponentEvent e) {
					// TODO Auto-generated method stub
					
				}

				public void componentResized(ComponentEvent e) {
					System.out.println(e.getComponent().getSize());
					
				}

				public void componentShown(ComponentEvent e) {
					// TODO Auto-generated method stub
					
				}});
			win.addWindowListener(new WindowListener(){

				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				public void windowClosed(WindowEvent e) {
					System.exit(0);
					
				}

				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					System.exit(0);
				}

				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}});
			
			win.setVisible(true);
		}
		// TODO Auto-generated constructor stub
	}
}
