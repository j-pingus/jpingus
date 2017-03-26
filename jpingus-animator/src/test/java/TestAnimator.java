import gee.gui.AnimatedButton;
import gee.imaging.ImageToolkit;
import gee.imaging.sprites.animated.AnimatedSprite;

import java.awt.Frame;
import java.awt.Button;
import java.awt.Choice;

public class TestAnimator extends Frame {

	private AnimatedButton button = null;
	private Choice choice = null;

	/**
	 * This is the default constructor
	 */
	public TestAnimator() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setTitle("Frame");
		this.add(getButton(), java.awt.BorderLayout.CENTER);
		this.add(getChoice(), java.awt.BorderLayout.NORTH);
	}

	/**
	 * This method initializes button	
	 * 	
	 * @return java.awt.Button	
	 */
	private Button getButton() {
		if (button == null) {
			button = new AnimatedButton();
			button.setLabel("Test Animated Button");
			button.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
					button.start();
				}
			});
			AnimatedSprite sprite = new AnimatedSprite("Test");
			sprite.setSprite(ImageToolkit.createSprite("/walker.png","ROT0", 32, 32));
			sprite.getSprite().setIdy(1);
			sprite.init(0,0,0);
			sprite.freeze();
			button.addSprite(sprite);
		}
		return button;
	}

	/**
	 * This method initializes choice	
	 * 	
	 * @return java.awt.Choice	
	 */
	private Choice getChoice() {
		if (choice == null) {
			choice = new Choice();
			choice.add("a");
			choice.add("a");
			choice.add("abraracourcix");
			choice.add("a");
			choice.add("a");
		}
		return choice;
	}

}
