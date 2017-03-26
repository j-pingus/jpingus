package gee.audio;

import gee.imaging.Ressourcer;

import java.applet.Applet;
import java.applet.AudioClip;
import java.util.Vector;

public class AudioClipLoop {
	AudioClip sound;
	Vector loopers=new Vector();
	public AudioClipLoop(String uri) {
		sound = Applet.newAudioClip(Ressourcer.getResource(uri));
	}
	public void loop(Object obj){
		if(loopers.size()==0){
			sound.loop();
		}
		loopers.addElement(obj);
	}
	public void stop(Object obj){
		if(loopers.contains(obj)){
			loopers.remove(obj);
		}
		if(loopers.size()==0){
			sound.stop();
		}
		
	}

}
