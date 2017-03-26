package pingus.ressource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pingus.jPingusProject;

public class RessourceFile {
	private RessourceEntry entries[];

	private static Hashtable files = new Hashtable();

	// section Exits {
	Pattern sectionPattern = Pattern.compile("(?i)\\s*section\\s+(\\S+)");

	// desert_tut = ../images/exits/desert_tut.png (type=surface, x=0, y=0,
	// width=122, height=115, tcol=0);
	Pattern ressourcePattern1 = Pattern
			.compile("(?i)\\s*(\\S+)\\s+=\\s+(\\S+)\\s+");

	Pattern heightPattern = Pattern.compile("height=(\\d+)");

	Pattern widthPattern = Pattern.compile("width=(\\d+)");
	String name;
	public RessourceEntry find(String ident) {
		for (int i = 0; i < entries.length; i++) {
			RessourceEntry entry = entries[i];
			if (entry.toString().equalsIgnoreCase(ident))
				return entry;
		}
//		System.out.println("ident:" + ident + " not found");
		return null;
	}

	public RessourceFile(String name) {
		// TODO Auto-generated constructor stub
		this.name=name;
	}

	public static RessourceFile read(String name, Reader in) {
		try {
			if (!files.containsKey(name)) {
				RessourceFile ret = new RessourceFile(name);
				BufferedReader r = new BufferedReader(in);
				ret.read(r, null, null);
//				System.out.println(name + " is read");
				in.close();
				files.put(name, ret);
			}
			return (RessourceFile) files.get(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private void read(BufferedReader in, String section, Vector p_v)
			throws IOException {
		Vector ret;
		if (p_v == null) {
			ret = new Vector();
		} else {
			ret = p_v;
		}
		String found;
		while ((found = in.readLine()) != null) {
			if (found.startsWith("//"))
				continue;
			Matcher m = sectionPattern.matcher(found);
			if (m.find()) {
				if (section == null) {
					read(in, m.group(1), ret);
				} else {
					read(in, section + "/" + m.group(1), ret);
				}
			}
			if (found.indexOf("}") != -1)
				return;
			m = ressourcePattern1.matcher(found);
			if (m.find()) {
				RessourceEntry e = new RessourceEntry();
				e.section = section;
				e.name = m.group(1);
				e.file = m.group(2);
				found = found.substring(m.end());
				m = widthPattern.matcher(found);
				if (m.find()) {
					e.width = Integer.parseInt(m.group(1));
				}
				m = heightPattern.matcher(found);
				if (m.find()) {
					e.height = Integer.parseInt(m.group(1));
				}
				ret.addElement(e);
			}

		}
//		System.out.println("read");
		entries = (RessourceEntry[]) ret.toArray(new RessourceEntry[] {});
//		System.out.println("ordered");
		Arrays.sort(entries);
	}

	public static void main(String[] args) throws IOException {
		File dir = new File(jPingusProject.getRessourceDir(),"data\\data");
		File res[] = dir.listFiles();
		for (int i = 0; i < res.length; i++) {
			File file = res[i];
			if (file.getName().endsWith("levels.res")) {
				read(file.getName(), new FileReader(file));
			}
		}
	}

	public RessourceEntry[] getEntries() {
		return entries;
	}

	public void addEntry(RessourceEntry e) {
		RessourceEntry tmp[] = new RessourceEntry[entries.length + 1];
		System.arraycopy(entries, 0, tmp, 0, entries.length);
		tmp[entries.length] = e;
		entries = tmp;
	}

	public void save(Writer w) {
		PrintWriter out = new PrintWriter(w);
		Arrays.sort(entries);
		String section = "";
		int depth =0;
		for (int i = 0; i < entries.length; i++) {
			RessourceEntry entry = entries[i];
			
			int depth2 = entry.section.split("/").length;
			if (!section.equals(entry.section)) {
				section = entry.section;
				if (depth2 <= depth) {
					for(int j=0;j<=depth-depth2;j++){
						out.println("}");
					}
				}
				out.println("section "
						+ section.substring(section.indexOf("/") + 1));
				out.println( "{");

			}
			section = entry.section;
			depth = section.split("/").length;
			entry.save(out);
//			out.println(entry);

		}
		out.println("}");
		out.close();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
