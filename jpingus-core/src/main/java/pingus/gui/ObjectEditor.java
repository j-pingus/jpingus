package pingus.gui;

import gee.imaging.sprites.Sprite;

import java.awt.AWTEvent;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.TreeSet;

import pingus.level.Background;
import pingus.pieces.ground.Liquid;
import pingus.ressource.Ressource;

public class ObjectEditor extends Dialog implements ActionListener {
	Panel editor = new Panel();

	ScrollPane scrollEditor = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);

	Object editee;
	Dialog me;
	class Argument implements Comparable, TextListener, ItemListener,
			ActionListener {
		String name;

		Method getter;

		Method setter;

		Dialog editorDialog;

		public String toString() {
			// TODO Auto-generated method stub
			return name;

		}

		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			return name.compareTo(o.toString());
		}

		public void textValueChanged(TextEvent e) {
			// TODO Auto-generated method stub
			TextField tf = (TextField) e.getSource();
			Class type = setter.getParameterTypes()[0];
			try {
				if (type == int.class) {
					Integer val = new Integer(tf.getText());
					setter.invoke(editee, new Object[] { val });
				} else if (type == String.class) {
					setter.invoke(editee, new Object[] { tf.getText() });
				}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			try {
				Boolean state = new Boolean(((Checkbox) e.getSource())
						.getState());
				setter.invoke(editee, new Object[] { state });
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Class c = getter.getReturnType();
			if (c == Background.class ) {
				try {
					Object o = getter.invoke(editee, null);
					new ObjectEditor(me,o);
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if (c == Ressource.class || c == Sprite.class ) {
				try {
					Object o = getter.invoke(editee, null);

					Ressource res = (Ressource) o;

					RessourceSelector selector = new RessourceSelector(
							editorDialog, res);
					setter.invoke(editee, new Object[] { selector
							.getRessource() });
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}

	public Argument getArgument(Hashtable args, String name) {
		if (!args.containsKey(name)) {
			Argument a = new Argument();
			a.name = name;
			args.put(name, a);
		}
		return (Argument) args.get(name);
	}

	public Argument[] analyse(Object editee) {
		Hashtable args = new Hashtable();
		Class c = editee.getClass();
		Method m[] = c.getMethods();
		for (int i = 0; i < m.length; i++) {
			Method method = m[i];
			if (method.getParameterTypes().length < 2) {
				String name = method.getName();
				if (name.startsWith("get")
						&& method.getParameterTypes().length == 0) {
					getArgument(args, name.substring(3)).getter = method;
					getArgument(args, name.substring(3)).editorDialog = this;
				}
				if (name.startsWith("is")
						&& method.getParameterTypes().length == 0) {
					getArgument(args, name.substring(2)).getter = method;
					getArgument(args, name.substring(3)).editorDialog = this;
				}
				if (name.startsWith("set")
						&& method.getParameterTypes().length == 1) {
					getArgument(args, name.substring(3)).setter = method;
					getArgument(args, name.substring(3)).editorDialog = this;
				}
			}
		}
		Argument[] ret = (Argument[]) args.values().toArray(new Argument[] {});
		Arrays.sort(ret);
		return ret;
	}

	Component getEditor(Argument a) {
		Component c = null;
		if (a.getter == null || a.setter == null)
			return c;
		try {
			if (a.setter == null) {
				TextField tf = new TextField(""
						+ a.getter.invoke(editee, new Object[] {}));
				tf.setEditable(false);
				c = tf;
			} else {
				Class type = a.getter.getReturnType();
				if (type == String.class) {
					TextField tf = new TextField((String) a.getter.invoke(
							editee, new Object[] {}));
					tf.addTextListener(a);
					c = tf;
				} else if (type == Ressource.class || type == Sprite.class) {
					Panel p = new Panel();
					TextField tf = new TextField(""
							+ a.getter.invoke(editee, new Object[] {}));
					tf.setEnabled(false);
					Button b = new Button("...");
					b.setActionCommand("Ressource");
					p.add(tf);
					p.add(b);
					b.addActionListener(a);
					c = p;
				} else if (type == Background.class){
					Panel p = new Panel();
					TextField tf = new TextField(""
							+ a.getter.invoke(editee, new Object[] {}));
					tf.setEnabled(false);
					Button b = new Button("...");
					b.setActionCommand("Object");
					p.add(tf);
					p.add(b);
					b.addActionListener(a);
					c = p;
				} else if (type == int.class) {
					TextField tf = new TextField(""
							+ a.getter.invoke(editee, new Object[] {}));
					tf.addTextListener(a);
					c = tf;
				} else if (type == boolean.class) {
					Object ret = a.getter.invoke(editee, new Object[] {});
					Checkbox cb = new Checkbox(a.name, ((Boolean) ret)
							.booleanValue());
					cb.addItemListener(a);
					c = cb;
					// }else if(type==String.class){
					//					
					// }else if(type==String.class){
					//					
					// }else if(type==String.class){
					//					
				}
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	void init(){
		me=this;
		setSize(500, 500);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);

		setLayout(new GridBagLayout());
		add(scrollEditor, new GridBagConstraints(0, 0, 1, 1, 1.0, 9.8,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		scrollEditor.add(editor);
		editor.setLayout(new GridBagLayout());
		Panel buttons = new Panel();
		GridBagConstraints gbc = new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
				GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 00);

		add(buttons, gbc);
		Button done = new Button("Done");
		done.setActionCommand("Done");
		done.addActionListener(this);
		buttons.add(done);
		Argument a[] = analyse(editee);
		for (int i = 0; i < a.length; i++) {
			Argument argument = a[i];
			Component tf = getEditor(argument);
			if (tf != null) {
				editor.add(new Label(argument.name), new GridBagConstraints(0,
						i, 1, 1, 1.0, 1.0, GridBagConstraints.EAST,
						GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
				editor.add(tf, new GridBagConstraints(1, i, 1, 1, 1.0, 1.0,
						GridBagConstraints.WEST, GridBagConstraints.NONE,
						new Insets(2, 2, 2, 2), 0, 0));
			}
		}

		setVisible(true);
		// TODO Auto-generated constructor stub
	}
	public ObjectEditor(Frame owner, Object anim) {
		super(owner, true);
		this.editee = anim;
		setTitle("Editing :" + editee.toString());
		init();
	}
	public ObjectEditor(Dialog owner, Object anim) {
		super(owner,"Editing :" + anim.toString(), true);
		this.editee = anim;
		init();
	}

	public static void main(String[] args) {
		new ObjectEditor(new Frame(), new Liquid("water"));
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Done")) {
			dispose();
		}
	}
}
