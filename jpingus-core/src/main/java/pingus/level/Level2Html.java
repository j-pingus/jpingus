package pingus.level;

import gee.imaging.ImageToolkit;

import java.awt.Frame;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import pingus.jPingusProject;

public class Level2Html {
	String web;

	public String doLevel2Html(File level, File html) {
		Level read = LevelReader.readLevel(level,null);
		try {
			PrintWriter b = new PrintWriter(new FileWriter(html));
			b.println("<html>");
			b.println("<head><title>" + read.getTitle() + "</title></head>");
			b.println("<body>");
			b.println("<p>Pingus dropped:" + read.getNbOfPingus() + ", to be saved:"
					+ read.getNbToSave());
			if (read.getTime() != -1) {
				b.println(" in " + read.getTime() + " seconds");
			} else {
				b.println(" in no specific timeframe");
			}
			b.println("</p>");
			b.println("<p>" + read.getDescription() + "</p>");
			b
					.println("<applet archive=\"jpingus.jar,data.jar,"
							+ level.getParentFile().getName()
							+ ".jar,pingus.jar,jaxen-core.jar,jaxen-jdom.jar,jdom-1.0.jar,saxpath.jar\" codebase=\"..\"");
			b.println("code=\"pingus.gui.applet.LevelApplet\"");
			b.println("width=\"640\"");
			b.println("height=\"480\">");
			b.println("<param name=\"level\"");
			b.println("value=\"" + level.getParentFile().getName() + "/"
					+ level.getName() + "\"/>");
			b.println("<hr>");
			b
					.println("If you were using a Java-enabled browser such as HotJava,");
			b.println("you would see dancing text instead of this paragraph.");
			b.println("<hr>");
			b.println("</applet><hr>");
			b.println("Hints: <ul><li>right click to zoom in</li>");
			b.println("<li>press M for minimap</li>");
			b.println("<!-- <li>press + for speedup</li>");
			b.println("<li>press - to slow down</li>-->");
			b.println("<li>press 1-9(<b>not</b> the numpad) to select action (color will change in left upper boxes) then apply on pingus with a click</li>");
			b.println("<li>press direction keys for scrolling</li>");
			b.println("<li>press A For armageddon</li>");
			b.println("<li>press D to drop one more</li>");
			b.println("</ul>");
			b.println("</body>");
			b.println("</html>");
			b.flush();
			b.close();
			return "<a href=\"" + web + html.getParentFile().getName() + "/"
					+ html.getName() + "\">" + read.getTitle() + "</a>";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getHtmlName(File level, String webRoot) {
		String name = level.getName();
		if (name.indexOf('-') != -1) {
			return name.substring(0, name.indexOf('-')) + ".html";
		}
		return name.replaceAll(".xml", ".html");
	}

	File dest;

	public Level2Html(File to, String webroot) {
		dest = to;
		web = webroot;

	}

	public void doLevel2Html(File in) {
		// TODO Auto-generated constructor stub
		File out = new File(dest, in.getName());
		StringBuffer buf = new StringBuffer();
		out.mkdirs();
		File levels[] = in.listFiles();
		try {
			File outfile = new File(dest, in.getName() + ".html");
			PrintWriter b = new PrintWriter(new FileWriter(outfile));
			b.println("<html>");
			b.println("<head><title>" + in.getName()
					+ "</title></head>");
			b.println("<body><ul>");
			for (int i = 0; i < levels.length; i++) {
				ImageToolkit.init(new Frame());
				File file = levels[i];
				if (file.getName().endsWith(".xml")) {
					b.println("<li>"
							+ doLevel2Html(file, new File(out, getHtmlName(
									file, ""))) + "</li>");
					b.flush();
				}
			}
			b.println("</ul><body>");
			b.println("<html>");
			b.close();
			System.out.println(outfile + " generated");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String subdirs[] = new String[] { "tutorial", "playable" };
		Level2Html l2h = new Level2Html(new File(
				"J:\\workspace_3.1\\jPingus\\web"), "http://jpingus.50webs.com/");
		for (int j = 0; j < subdirs.length; j++) {
			String subdir = subdirs[j];
			File in = new File(
					jPingusProject.getRessourceDir(),"\\data\\levels\\"+
					subdir);
			l2h.doLevel2Html(in);
		}
	}
}
