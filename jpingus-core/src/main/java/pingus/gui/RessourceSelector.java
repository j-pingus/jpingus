package pingus.gui;

import gee.imaging.ImageToolkit;
import gee.imaging.animator.Animator;
import gee.imaging.animator.ComponentAnimator;
import gee.imaging.background.DummyBackground;
import gee.imaging.sprites.animated.AnimatedSprite;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.List;
import java.awt.Panel;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.dnd.InvalidDnDOperationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import pingus.ressource.Ressource;
import pingus.ressource.RessourceEntry;
import pingus.ressource.RessourceFile;

public class RessourceSelector extends Dialog implements ActionListener,
		ComponentListener, KeyListener {
	File dir = new File("Ressources\\Ressource\\data\\data");

	RessourceFile resFile;

	Dialog me = this;

	Ressource ressource;

	List ressources, entries;

	AnimatedSprite spritePreview = new AnimatedSprite("preview");

	Animator anim;

	Panel selection = new Panel();

	Panel preview = new Panel();

	Component owner;

	public RessourceSelector(Dialog owner, Ressource r) {
		super(owner);
		this.owner = owner;
		init(r);

		// TODO Auto-generated constructor stub
	}

	public RessourceSelector(Frame owner, Ressource r) {
		super(owner);
		this.owner = owner;
		init(r);

		// TODO Auto-generated constructor stub
	}

	public List getListRessources() {
		List ret = new List(3, false);
		File l[] = dir.listFiles();
		for (int i = 0; i < l.length; i++) {
			File file = l[i];
			if (file.getName().endsWith(".res")) {
				ret.add(file.getName());
			}
		}
		return ret;
	}

	public void init(Ressource r) {
		setModal(true);
		preview.setBackground(Color.GREEN);
		selection.setBounds(0, 0, 50, 80);
		setLayout(new GridBagLayout());
		selection.setLayout(new GridBagLayout());
		setSize(800, 600);
		add(selection, new GridBagConstraints(0, 0, 1, 1, 0.1, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
				new Insets(2, 2, 2, 2), 0, 0));
		add(preview, new GridBagConstraints(1, 0, 1, 1, 0.9, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		ressources = getListRessources();
		ressources.addActionListener(this);
		selection.add(ressources, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.5,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2,
						2, 2, 2), 0, 0));
		entries = new List();
		entries.addActionListener(this);
		selection.add(entries, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.5,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2,
						2, 2, 2), 0, 0));
		entries.addKeyListener(this);
		new DropTarget(entries, new PictureDropTargetListener());
		new DropTarget(preview, new PictureDropTargetListener());

		Button done = new Button("Done");
		done.setActionCommand("Done");
		done.addActionListener(this);
		done.setBounds(0, 0, 60, 10);
		selection.add(done, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						2, 2, 2, 2), 0, 0));
		setRessource(r);
		this.addComponentListener(this);
		setVisible(true);
	}

	public static void main(String[] args) {
		new RessourceSelector(new Dialog(new Frame("H")), null);
	}

	public void selectRessourceFile(String name) {
		try {
			for (int i = 0; i < ressources.countItems(); i++) {
				String string = ressources.getItem(i);
				if (ressources.getItem(i).equals(name)) {
					ressources.select(i);
					break;
				}
			}
			if (ressources.getSelectedIndex() >= 0
					&& !ressources.getItem(ressources.getSelectedIndex())
							.equals(name)) {
				return;
			}
			File res = new File(dir, name);
			if (!res.exists())
				res = new File(dir, name + ".res");
			resFile = RessourceFile
					.read(res.getName().replaceFirst(".res", ""),
							new FileReader(res));
			RessourceEntry re[] = resFile.getEntries();
			entries.removeAll();
			for (int i = 0; i < re.length; i++) {
				RessourceEntry entry = re[i];
				entries.add(entry.getSection() + "/" + entry.getName());
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void selectRessource(String name) {
		if (resFile != null) {
			try {
				RessourceEntry res = resFile.find(name);
//				System.out.println("Found:" + res);
				String imageLocation = "/Ressource/data"
						+ res.getFile().replaceFirst("..", "");
//				System.out.println(imageLocation);
				if (anim != null) {
					anim.stop();
					anim.free();
				}
				ImageToolkit.init(owner);
				Image im = ImageToolkit.getImage(imageLocation,"ROT0");
				Ressource sp = new Ressource(im, res.getWidth(), res
						.getHeight(), "datafile", resFile.getName(),
						name, "R0TO");
//				System.out.println(sp);
				anim = new ComponentAnimator(preview, 1000 / 20,false);
				anim.setBackground(new DummyBackground(preview,
						Color.LIGHT_GRAY));
				anim.setStartPos(10, 10);
				anim.setDebug(false);
				spritePreview = new AnimatedSprite("Preview");
				spritePreview.freeze();
				spritePreview.setUseOffset(false);
				// spritePreview.initOffset(preview.getX(),preview.getY());
				spritePreview.setSpeedX(0);
				spritePreview.setSpeedY(0);
				spritePreview.setSprite(sp);
				spritePreview.initSprite(0, 0);
				spritePreview.init((preview.getWidth() - sp.getWidth()) / 2,
						(preview.getHeight() - sp.getHeight()) / 2, 0);
				ressource = sp;
				anim.addSprite(spritePreview);
//				System.out.println(spritePreview);
				anim.start();
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Done")) {
			dispose();
		}
		if (e.getSource() == ressources) {
			selectRessourceFile(e.getActionCommand());

		}
		if (e.getSource() == entries) {
			selectRessource(e.getActionCommand());
		}
	}

	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
		if (ressource != null) {
//			System.out.println(ressource);
		}
	}

	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	public void componentShown(ComponentEvent e) {
		if (ressource != null) {
			selectRessourceFile(ressource.getFile());
			selectRessource(ressource.getIdent());
		}
	}

	class PictureDropTargetListener implements DropTargetListener {

		public void dragEnter(DropTargetDragEvent dropTargetDragEvent) {
			// TODO Auto-generated method stub
			dropTargetDragEvent.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
		}

		public void dragOver(DropTargetDragEvent dtde) {
			// TODO Auto-generated method stub

		}

		public void dropActionChanged(DropTargetDragEvent dtde) {
			// TODO Auto-generated method stub

		}

		public void dragExit(DropTargetEvent dte) {
			// TODO Auto-generated method stub

		}

		public void drop(DropTargetDropEvent dropTargetDropEvent) {
			try {
				Transferable tr = dropTargetDropEvent.getTransferable();
				if (tr.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
					dropTargetDropEvent
							.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
					java.util.List fileList = (java.util.List) tr
							.getTransferData(DataFlavor.javaFileListFlavor);
					Iterator iterator = fileList.iterator();
					while (iterator.hasNext()) {
						File file = (File) iterator.next();
//						System.out.println(file);
						RessourceEntry r = new RessourceEntry();
						File dest = new File("web/Ressource/data/images/"
								+ resFile.getName() + "/" + file.getName());
						if (!dest.equals(file)) {
							if (!dest.getParentFile().exists()) {
								dest.getParentFile().mkdirs();
							}
							copy(file, dest, true);
							file = dest;
						}
						r.setFile(dest);
						r.setName(file.getName());
						dest = new File(dest, r.getFile().replaceAll("../", ""));
						r.setHeight(0);
						r.setWidth(0);
						r.setSection("Default");
						new ObjectEditor(me, r);
						entries.add(r.getSection() + "/" + r.getName());
						resFile.addEntry(r);
						if (resFile.getName().indexOf(".jpingus.") > -1) {
							resFile.save(new FileWriter(
									"web/Ressource/data/data/"
											+ resFile.getName() + ".res"));
						} else {
							resFile.save(new FileWriter(
									"web/Ressource/data/data/"
											+ resFile.getName()
											+ ".jpingus.res"));
						}
					}
					dropTargetDropEvent.getDropTargetContext().dropComplete(
							true);
				} else {
//					System.err.println("Rejected");
					dropTargetDropEvent.rejectDrop();
				}
			} catch (InvalidDnDOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedFlavorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void copy(File from, File to) throws IOException {
		copy(from, to, false);
	}

	public static synchronized void copy(File from, File to, boolean overwrite)
			throws IOException {
		if (!from.exists()) {
			throw new IOException(
					"Impossible de copier le fichier parce que la source n'existe plus ["
							+ from.getAbsolutePath() + "]");
		}

		if (to.exists()) {
			if (overwrite) {
				if (!to.delete()) {
					throw new IOException(
							"Impossible de supprimer le fichier de destination ["
									+ to.getAbsolutePath() + "]");
				}
			} else {
				throw new IOException(
						"Le fichier de destintation existe dij` ["
								+ to.getAbsolutePath() + "]");
			}
		}

		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(from);
			out = new FileOutputStream(to);
			copy(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} finally {
			if (in != null) {
				in.close();
				in = null;
			}
			if (out != null) {
				out.flush();
				out.close();
				out = null;
			}
		}
	}

	/**
	 * Buffer size when reading from input stream.
	 * 
	 * @since ostermillerutils 1.00.00
	 */
	private final static int BUFFER_SIZE = 500 * 1024;

	/**
	 * Copy the data from the input stream to the output stream.
	 * 
	 * @param in
	 *            data source
	 * @param out
	 *            data destination
	 * @throws IOException
	 *             in an input or output error occurs
	 * 
	 * @since ostermillerutils 1.00.00
	 */
	public static void copy(InputStream in, OutputStream out)
			throws IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(entries)) {
			String ressource = entries.getSelectedItem();
			RessourceEntry r = resFile.find(ressource);
			entries.replaceItem(r.getSection() + "/" + r.getName(), entries
					.getSelectedIndex());
			new ObjectEditor(me, r);
			try {
				if (resFile.getName().indexOf(".jpingus.") > -1) {
					resFile.save(new FileWriter("web/Ressource/data/data/"
							+ resFile.getName() + ".res"));
				} else {
					resFile.save(new FileWriter("web/Ressource/data/data/"
							+ resFile.getName() + ".jpingus.res"));
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
