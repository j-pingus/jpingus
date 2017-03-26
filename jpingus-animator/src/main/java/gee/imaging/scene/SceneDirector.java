package gee.imaging.scene;

import gee.imaging.animator.Animator;

import java.awt.Component;
import java.util.Hashtable;

public class SceneDirector {
	Hashtable scenes = new Hashtable();

	Animator currentScene;
	private static SceneDirector director=new SceneDirector();
	private SceneDirector() {
	}
	public static SceneDirector getSceneDirector(){
		return director;
	} 
	public void addScene(String name, Animator anim) {
		scenes.put(name, anim);
	}

	private Animator getAnimator(String name) {
		if (scenes.containsKey(name)) {
			return (Animator) scenes.get(name);
		}
		return null;
	}

	public void showScene(String name,int delay) {
		Animator a = getAnimator(name);
		if (currentScene != null){
			try {
				Thread.currentThread().sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentScene.stop();
		}
		if (a != null){
			a.start();
			currentScene=a;
		}
	}

	public void removeScene(String name) {
		Animator a = getAnimator(name);
		if (a == null)
			return;
		a.stop();
		a.free();
		scenes.remove(name);
	}

	public Animator getCurrentScene() {
		return currentScene;
	}
}
