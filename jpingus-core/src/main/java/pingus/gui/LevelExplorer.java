package pingus.gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

public class LevelExplorer extends JPanel {

	/**
	 * @param args
	 */
	public DefaultMutableTreeNode buildTree(File currentFile) throws Exception {
		DefaultMutableTreeNode currentNode = null;
		currentNode = new DefaultMutableTreeNode(new LevelTreeNode(currentFile));
		if (currentFile.isDirectory()) {
			File sub[] = currentFile.listFiles();
			boolean empty=true;
			for (int i = 0; i < sub.length; i++) {
				File file = sub[i];
				DefaultMutableTreeNode newNode = buildTree(file);
				
				if (newNode != null) {
					currentNode.add(newNode);
					empty=false;
				}
			}
			if(!empty){
				return currentNode;
			}
		} else if (currentFile.getName().endsWith(".xml")) {
			currentNode.setAllowsChildren(false);
			return currentNode;
		}
		 return null;
	}

	JTree treeview;

	public LevelExplorer() {
		try {

			treeview = new JTree();
			treeview.setCellRenderer(new LevelTreeRenderer());

		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
			treeview = new JTree(new String[] { "Could not access magnolia" });
		}

		// setBackground(Color.LIGHT_GRAY);

		// Put the drawing area in a scroll pane.
		JScrollPane scroller = new JScrollPane(treeview);
		// Layout this demo.
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		//scroller.setSize(100,100);
		add(scroller, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
				GridBagConstraints.PAGE_START, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
	}

	class LevelTreeRenderer extends DefaultTreeCellRenderer {
		ImageIcon xmlIcon = new ImageIcon("images/xml.jpg");

		public LevelTreeRenderer() {
		}

		public Component getTreeCellRendererComponent(JTree tree, Object value,
				boolean sel, boolean expanded, boolean leaf, int row,
				boolean hasFocus) {

			super.getTreeCellRendererComponent(tree, value, sel, expanded,
					leaf, row, hasFocus);
			if (leaf && value.toString().endsWith(".xml")) {
				setIcon(xmlIcon);
				setToolTipText("Drag and drop into public or author");
			}

			return this;
		}
	}

	public void init(File dir) {
		// TODO Auto-generated method stub

		DefaultTreeModel model = (DefaultTreeModel) (treeview.getModel());
		DefaultMutableTreeNode rootnode = (DefaultMutableTreeNode) model
				.getRoot();
		model.removeNodeFromParent(rootnode.getFirstLeaf());

		try {

			model.setRoot(buildTree(dir));

		} catch (Throwable ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
	}

	public JTree getTreeview() {
		return treeview;
	}
}
