package pingus.gui.editor;

import gee.imaging.ImageToolkit;
import gee.imaging.animator.Animator;
import gee.imaging.animator.ComponentAnimator;

import java.awt.AWTEvent;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import pingus.JPingus;
import pingus.jPingusProject;
import pingus.behaviours.ViewerDetector;
import pingus.gui.LevelExplorer;
import pingus.gui.LevelTreeNode;
import pingus.gui.ObjectEditor;
import pingus.gui.RessourceSelector;
import pingus.level.Background;
import pingus.level.Level;
import pingus.level.LevelReader;
import pingus.level.LevelWriter;
import pingus.pieces.ground.Ground;
import pingus.pieces.gui.LevelSprite;
import pingus.pieces.gui.ThemeSprite;
import pingus.pieces.gui.TimerSprite;
import pingus.ressource.Ressource;
import pingus.ressource.RessourceLocator;

public class FrameLevelEditor extends Frame implements ActionListener,
		TreeSelectionListener {
	private static final int SPEED_ANIMATOR = 1000 / 24;

	/**
	 * 
	 */
	private static final long serialVersionUID = 58L;

	private static final String FREE_LEVEL = "freeLevel";

	private static final String BENCHMARK = "bench";

	private static final String TOGGLE_DEBUG = "toggleDebug";

	private static final String NEW_LEVEL = "newLevel";

	private static final String TOGGLE_GROUND = "toggleGround";

	private static final String TOGGLE_SPRITES_BACK = "toggleSpritesBack";

	private static final String TOGGLE_SPRITES_FRONT = "toggleSpritesFront";

	private static final String TOGGLE_BACK = "toggleBack";

	private static final String ADD_PINGUS = "addPingus";

	private static final String STOP_ANIMATOR = "stopAnimator";

	private static final String START_ANIMATOR = "startAnimator";

	private static final String EDIT_LEVEL = "editLevel";

	private static final String ADD_LEVEL_SELECTOR = "addLevelSelector";
	private static final String ADD_THEME_SELECTOR = "addThemeSelector";

	private static final String SAVE_LEVEL = "saveLevel";

	private static final String ADD_GROUND = "addGround";
	private static final String DO_STEP = "doStep";


	// private static final String NEXT_FILE = "nextFile";

	static File defaultParent;

	private static String DEFAULT_DIR = FrameLevelEditor.class.getName()
			+ ".DefaultDir";

	private static String PROPERTIES_FILE = FrameLevelEditor.class.getName()
			+ ".properties";

	private Thread bench = null;

	private boolean do_bench;
	static Properties props;

	static {
		props = new Properties();
		defaultParent = new File(".");
		try {
			props.load(new FileInputStream(new File(".", PROPERTIES_FILE)));
			if (props.containsKey(DEFAULT_DIR)) {
				defaultParent = new File(props.getProperty(DEFAULT_DIR));
			} else {
				defaultParent = new File(".");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	Animator animator = null;

	Level current = null;
	int entrance=0;
	Label fileName = new Label();

	Panel panelLevel = new Panel();

	LevelExplorer le = new LevelExplorer();

	public FrameLevelEditor() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			GridBagLayout layout = new GridBagLayout();
			setLayout(layout);
			panelLevel.setBackground(Color.GREEN);
			Panel left = new Panel(new GridBagLayout());
			left.setBackground(Color.WHITE);
			int p = 0;
			le.init(new File("Ressources/Ressource/data/levels"));
			le.getTreeview().addTreeSelectionListener(this);
			left.add(le, new GridBagConstraints(0, p++, 1, 1, 0.2, 1.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(10, 0, 0, 0), 0, 0));
			Button b = new Button("New Level !");
			b.setActionCommand(NEW_LEVEL);
			b.addActionListener(this);
			left.add(b, new GridBagConstraints(0, p++, 1, 1, 0.0, 0.0,
					GridBagConstraints.PAGE_START, GridBagConstraints.BOTH,
					new Insets(10, 0, 0, 0), 0, 0));
			b = new Button("drop me in !");
			b.setActionCommand(ADD_PINGUS);
			b.addActionListener(this);
			left.add(b, new GridBagConstraints(0, p++, 1, 1, 0.0, 0.0,
					GridBagConstraints.PAGE_START, GridBagConstraints.BOTH,
					new Insets(10, 0, 0, 0), 0, 0));
			b = new Button("toggle background");
			b.setActionCommand(TOGGLE_BACK);
			b.addActionListener(this);
			left.add(b, new GridBagConstraints(0, p++, 1, 1, 0.0, 0.0,
					GridBagConstraints.PAGE_START, GridBagConstraints.BOTH,
					new Insets(10, 0, 0, 0), 0, 0));
			b = new Button("toggle sprites back");
			b.setActionCommand(TOGGLE_SPRITES_BACK);
			b.addActionListener(this);
			left.add(b, new GridBagConstraints(0, p++, 1, 1, 0.0, 0.0,
					GridBagConstraints.PAGE_START, GridBagConstraints.BOTH,
					new Insets(10, 0, 0, 0), 0, 0));
			b = new Button("toggle sprites front");
			b.setActionCommand(TOGGLE_SPRITES_FRONT);
			b.addActionListener(this);
			left.add(b, new GridBagConstraints(0, p++, 1, 1, 0.0, 0.0,
					GridBagConstraints.PAGE_START, GridBagConstraints.BOTH,
					new Insets(10, 0, 0, 0), 0, 0));

			b = new Button("toggle ground");
			b.setActionCommand(TOGGLE_GROUND);
			b.addActionListener(this);
			left.add(b, new GridBagConstraints(0, p++, 1, 1, 0.0, 0.0,
					GridBagConstraints.PAGE_START, GridBagConstraints.BOTH,
					new Insets(10, 0, 0, 0), 0, 0));
			b = new Button("debug info");
			b.setActionCommand(TOGGLE_DEBUG);
			b.addActionListener(this);
			left.add(b, new GridBagConstraints(0, p++, 1, 1, 0.0, 0.0,
					GridBagConstraints.PAGE_START, GridBagConstraints.BOTH,
					new Insets(10, 0, 0, 0), 0, 0));

			b = new Button("free memory");
			b.setActionCommand(FREE_LEVEL);
			b.addActionListener(this);
			left.add(b, new GridBagConstraints(0, p++, 1, 1, 0.0, 0.0,
					GridBagConstraints.PAGE_START, GridBagConstraints.BOTH,
					new Insets(10, 0, 0, 0), 0, 0));

			Panel bottom = new Panel();
			// bottom.setBackground(Color.BLUE);

			add(left, new GridBagConstraints(0, 0, 1, 2, 0.1, 0.0,
					GridBagConstraints.PAGE_START, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
			add(panelLevel, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
					GridBagConstraints.PAGE_START, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
			add(bottom, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.2,
					GridBagConstraints.PAGE_START, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
			setSize(new Dimension(800, 623));

			Button next = new Button("Toggle Benchmark");
			bottom.add(fileName);
			bottom.add(next);
			next.setActionCommand(BENCHMARK);
			next.addActionListener(this);
			b = new Button("Stop");
			b.setActionCommand(STOP_ANIMATOR);
			b.addActionListener(this);
			bottom.add(b);
			b = new Button("Start");
			b.setActionCommand(START_ANIMATOR);
			b.addActionListener(this);
			bottom.add(b);

			b = new Button("Edit Level Object");
			b.setActionCommand(EDIT_LEVEL);
			b.addActionListener(this);
			bottom.add(b);

			b = new Button("Add level selector");
			b.setActionCommand(ADD_LEVEL_SELECTOR);
			b.addActionListener(this);
			bottom.add(b);

			b = new Button("Add theme selector");
			b.setActionCommand(ADD_THEME_SELECTOR);
			b.addActionListener(this);
			bottom.add(b);

			b = new Button("Save this level");
			b.setActionCommand(SAVE_LEVEL);
			b.addActionListener(this);
			bottom.add(b);

			b = new Button("Add Ground");
			b.setActionCommand(ADD_GROUND);
			b.addActionListener(this);
			bottom.add(b);

			b = new Button("50 Steps [SPACE for 1 step]");
			b.setActionCommand(DO_STEP);
			b.addActionListener(this);
			bottom.add(b);

			this.setTitle("Loading");
//			animator = new ComponentAnimator(panelLevel, SPEED_ANIMATOR);
//			animator.setDebug(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initLevel(File level) {
		try {
			ImageToolkit.init(this);
//			System.out.println(level);
			if (animator != null)
				animator.stop();
			else
				animator = new ComponentAnimator(panelLevel, SPEED_ANIMATOR,false);

//			animator.setDebug(true);
			repaint();

			current = LevelReader.readLevel(level,null);
			entrance=0;
			// handleLelevChunks(root.getChildren());
			// handleStartPosition(root.getChild("background"));
			animator.setBackground(current.getBack());
			animator.addSprite(current.getSpots());
			animator.addSprite(current.getStart());
			animator.addSprite(current.getActions());
			animator.addSprite(current.getTraps());
//			animator.addSprite(new TimerSprite());
			animator.setMusic("/Ressource/data/music/" + current.getMusic());
			animator.setDetector(new ViewerDetector(this,panelLevel, current));
			
			animator.setStartPos(current.getStart().getX(), current.getStart()
					.getY());
			animator.start();
			animator.addSprite(new TimerSprite(animator));
			//panelLevel.enableInputMethods(true);
			// System.out.println(background);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// Overridden so we can exit on System Close
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}
	}

	public void paint(Graphics g) {
		if (animator != null)
			if (animator.getImage() != null)
				g.drawImage(animator.getImage(), 0, 0, this);
	}

	public void update(Graphics g) {
		if (animator != null)
			if (animator.getImage() != null)
				g.drawImage(animator.getImage(), 0, 0, this);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			// if (e.getActionCommand().equals(NEW_LEVEL)) {
			// selectNextLevel();
			// }
			// if (e.getActionCommand().equals(NEXT_FILE)) {
			// if (animator != null) {
			// animator.free();
			// animator = null;
			// }
			// System.gc();
			// selectNextLevel();
			// }
			if (e.getActionCommand().equals(STOP_ANIMATOR)) {
				animator.stop();
			}
			if (e.getActionCommand().equals(START_ANIMATOR)) {
				animator.start();
			}
			if (e.getActionCommand().equals(EDIT_LEVEL)) {
				new ObjectEditor(this, current);
			}
			if (e.getActionCommand().equals(ADD_LEVEL_SELECTOR)) {
				RessourceSelector rs = new RessourceSelector(this, null);
				if (rs.getRessource() != null) {
					LevelSprite ls = new LevelSprite("choose a level.xml","jpingus.behariours.GameDetector");
					ls.setSprite(rs.getRessource());
					ls.freeze();
					current.addSpot(ls);
					animator.addSprite(ls);
				}
			}
			if (e.getActionCommand().equals(ADD_THEME_SELECTOR)) {
				RessourceSelector rs = new RessourceSelector(this, null);
				if (rs.getRessource() != null) {
					ThemeSprite ts = new ThemeSprite("choose a theme.xml","jpingus.behariours.ThemeDetector",false);
					ts.setSprite(rs.getRessource());
					ts.freeze();
					current.addSpot(ts);
					animator.addSprite(ts);
				}
			}
			if (e.getActionCommand().equals(SAVE_LEVEL)) {
				LevelWriter.write(current);
			}
			if (e.getActionCommand().equals(ADD_GROUND)) {
				RessourceSelector rs = new RessourceSelector(this, null);
				if (rs.getRessource() != null) {
					Ground g = new Ground(rs.getName());
					g.setSprite(rs.getRessource());
					g.freeze();
					current.getBack().addSprite(g);
					current.getBack().reset();
				}
			}
			if (e.getActionCommand().equals(DO_STEP)) {
				animator.stop();
				animator.step(50);
				animator.start();
			}
			if (e.getActionCommand().equals(NEW_LEVEL)) {

				RessourceSelector rs = new RessourceSelector(this, null);
				if (rs.getRessource() != null) {
					Level l = new Level(new File(jPingusProject
							.getRessourceDir(), "data/levels/themes/new.xml"));
					Background back = new Background();
					back.init(rs.getRessource(), 800, 600);
					l.setBack(back);
					LevelWriter.write(l);
				}

			}
			if (e.getActionCommand().equals(ADD_PINGUS)) {
				JPingus pingus = new JPingus(current.getBack());
				pingus.init(current.getEntrance(entrance++));
				animator.addSprite(pingus);
			}
			if (e.getActionCommand().equals(TOGGLE_BACK)) {
//				current.getBack().setBackground(
//						!current.getBack().isBackground());
			}
			if (e.getActionCommand().equals(TOGGLE_GROUND)) {
				current.getBack().setGround(!current.getBack().isGround());
			}
			if (e.getActionCommand().equals(TOGGLE_SPRITES_BACK)) {
				current.getBack().setSpriteBack(
						!current.getBack().isSpriteBack());
			}
			if (e.getActionCommand().equals(TOGGLE_SPRITES_FRONT)) {
				current.getBack().setSpriteFront(
						!current.getBack().isSpriteFront());
			}
			if (e.getActionCommand().equals(TOGGLE_DEBUG)) {
				animator.setDebug(!animator.isDebug());
			}
			if (e.getActionCommand().equals(BENCHMARK)) {
				if (bench == null) {
					bench = new Thread() {
						public void run() {
							try {
								for (int i = 0; do_bench && i < 1000; i++) {
									JPingus pingus = new JPingus(current
											.getBack());
									pingus.init(current.getEntrance(i));
									animator.addSprite(pingus);
									Thread.sleep(60000/current.getEntrance(i).getRate());
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					};
					do_bench = true;
					bench.start();
				} else {
					do_bench = false;
					bench = null;
				}
			}
			if (e.getActionCommand().equals(FREE_LEVEL)) {
				animator.free();
				animator = null;
				System.gc();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode dmt = (DefaultMutableTreeNode) e.getPath()
				.getLastPathComponent();
		LevelTreeNode ltn = (LevelTreeNode) dmt.getUserObject();
//		System.out.println(ltn.getFile());
		if (animator != null) {
			animator.free();
			animator = null;
			System.gc();
		}
		initLevel(ltn.getFile());
	}

}
