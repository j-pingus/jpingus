//Title:      Game Tookit
//Version:
//Copyright:  Copyright (c) 1999
//Author:     G�rald Even
//Company:    GEE
//Description:Should help me in the creation of my (1st) real game.
package gee.Testing;

import gee.imaging.ImageToolkit;
import gee.imaging.animator.Animator;
import gee.imaging.animator.ComponentAnimator;
import gee.imaging.background.StaticBackground;
import gee.imaging.background.StaticSpriteBackground;
import gee.imaging.sprites.Sprite;
import gee.imaging.sprites.animated.AnimatedSprite;
import pingus.JPingus;
import pingus.gui.applet.SampleCollisionDetector;
import pingus.pieces.gui.TextScroller;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class Frame1 extends Frame implements ComponentListener {
    private static final int LEVEL_HEIGHT = 800;

    private static final int LEVEL_WIDTH = 850;

    private static final int SCREEN_WIDTH = 800;

    private static final int SCREEN_HEIGHT = 600;

    BorderLayout borderLayout1 = new BorderLayout();
    Image iBackground;
    Sprite spriteSelected;
    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice device = env.getDefaultScreenDevice();
    DisplayMode oldDisplayMode = device.getDisplayMode();
    SampleCollisionDetector detector = new SampleCollisionDetector();
    StaticSpriteBackground back;
    Animator anim;
    boolean direction = true, stop = false;
    int speed = 1;

    // Construct the frame
    public Frame1(boolean fullscreen) {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit(fullscreen);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void startFullScreen() {
        try {
            this.setUndecorated(true);
            if (device.isFullScreenSupported()) {
                DisplayMode m[] = device.getDisplayModes();
                DisplayMode modechosen = null;
                for (int i = 0; i < m.length; i++) {
                    DisplayMode mode = m[i];
                    if (mode.getHeight() == SCREEN_HEIGHT
                            && mode.getWidth() == SCREEN_WIDTH
                            && mode.getBitDepth() == 16
                            && mode.getRefreshRate() == 60

                            ) {
//						System.out.println(i + ":" + mode.getBitDepth() + "/"
//								+ mode.getRefreshRate());
                        modechosen = mode;
                    }
                }

//				System.out.println("fullscreen");
                device.setFullScreenWindow(this);
                if (modechosen != null)
                    device.setDisplayMode(modechosen);

            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    void stopFullScreen() {
        device.setDisplayMode(oldDisplayMode);
        device.setFullScreenWindow(null);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        stopFullScreen();
    }

    // Component initialization
    private void jbInit(boolean fullscreen) throws Exception {
        // this.setLayout(borderLayout1);
        // this.setSize(new Dimension(800, 600));
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setLocation(0, 0);
        if (fullscreen)
            startFullScreen();
        // this.setTitle("Frame Title");
        ImageToolkit.init(this);
        iBackground = ImageToolkit.getImage("/gee/images/background.jpg", "ROT0");

        spriteSelected = ImageToolkit.createSprite("/gee/images/selected.png", "ROT0",
                34, 34);
        Sprite spriteFloor = ImageToolkit.createSprite("/gee/images/floor.jpg", "ROT0",
                0, 0);
        Sprite spriteFloorHole = ImageToolkit.createSprite(
                "/gee/images/floor.png", "ROT0", 0, 0);
        Sprite block = ImageToolkit.createSprite("/gee/images/blk.png", "ROT0", 0, 0);
        StaticBackground back2 = new StaticBackground();
        back2.init(new Sprite(iBackground), LEVEL_WIDTH, LEVEL_HEIGHT);
        back2.init(0, 0, -150);

        back = new StaticSpriteBackground(LEVEL_WIDTH, LEVEL_HEIGHT);

        AnimatedSprite theFloor;
        JPingus thePingus;

        theFloor = new AnimatedSprite("floor");
        theFloor.setSprite(spriteFloorHole);
        theFloor.init(0, 216, 0);
        back.addSprite(theFloor);
        theFloor = new AnimatedSprite("floor");
        theFloor.setSprite(spriteFloor);
        theFloor.init(0, 316, 0);
        back.addSprite(theFloor);
        theFloor = new AnimatedSprite("floor");
        theFloor.setSprite(spriteFloor);
        theFloor.init(200, 386, 0);
        back.addSprite(theFloor);

        theFloor = new AnimatedSprite("block");
        theFloor.setSprite(block);
        theFloor.init(540, 244, 0);
        back.addSprite(theFloor);

        theFloor = new AnimatedSprite("floor");
        theFloor.setSprite(spriteFloor);
        theFloor.init(500, 500, 0);
        theFloor.setSpeedX(0);
        theFloor.setSpeedY(0);
        back.addSprite(theFloor);
        anim = new ComponentAnimator(this, 25, false);
        anim.setBackground(back);
        anim.setDetector(detector);
        anim.addSprite(back2);
        thePingus = new JPingus(back);
        thePingus.init(384, 284, 5);
        thePingus.initBlocker();
        anim.addSprite(thePingus);
        thePingus = new JPingus(back);
        thePingus.init(84, 284, 5);
        thePingus.initBlocker();
        anim.addSprite(thePingus);
        TextScroller scroll = new TextScroller(this,
                new String[]{
                        "Hello le monde",
                        "Hello pingus le monde",
                        "Hello le pingus pingus monde",
                        "Hello le monde pingus pingus ",
                        "pingus Hello le monde",
                },
                null,
                30
        );
        anim.addSprite(scroll);

        this.setSize(new java.awt.Dimension(495, 309));
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                JPingus thePingus = null;
                if (detector.getCurrent() != null) {
                    thePingus = (JPingus) detector.getCurrent();
                }
                detector.setKeyCode(e.getKeyCode());
                // ((Moving) anim.selectedMovingSprite).setSpeedY(5);
                switch (detector.getKeyCode()) {
                    case 107: // +
                        anim.addSprite(new JPingus(back));
                        break;
                    case 27:// Esc
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Stroke:" + e.getKeyCode());

                        break;
                }
            }

        });
        this.addComponentListener(this);
    }

    public void start() {
        anim.start();
    }

    // Overridden so we can exit on System Close
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        }
    }

    public void componentResized(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    public void componentMoved(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    public void componentShown(ComponentEvent e) {
        // TODO Auto-generated method stub
        start();

    }

    public void componentHidden(ComponentEvent e) {
        // TODO Auto-generated method stub

    }


}  //  @jve:decl-index=0:visual-constraint="10,10"
