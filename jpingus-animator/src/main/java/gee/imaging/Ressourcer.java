package gee.imaging;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Hashtable;
import java.util.regex.Matcher;

public class Ressourcer {
	static Hashtable ressources = new Hashtable();

	public static InputStream getResourceAsStream(String name) {
		if (name.endsWith(".res")) {
			StringBuffer buf = new StringBuffer();
			if (!ressources.containsKey(name)) {
//				System.out.println("Will download(as stream):" + name);
				InputStream i = Ressourcer.class.getResourceAsStream(name);
				if (i != null) {
					try {
						BufferedReader read = new BufferedReader(
								new InputStreamReader(i));
						String line;
						while ((line = read.readLine()) != null) {
							buf.append(line);
							buf.append("\n");
						}
						read.close();
						ressources.put(name, buf);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
				}
			}
			buf = (StringBuffer) ressources.get(name);
			return new BufferedInputStream(new ByteArrayInputStream(buf
					.toString().getBytes()));
		}
//		System.out.println("Will download(as stream):" + name);
//		System.out.println("Will download(as stream):" + name);
		return Ressourcer.class.getResourceAsStream(name);
	}

	public static URL getResource(String name) {
//		System.out.println("Will download:" + name);
		return Ressourcer.class.getResource(name);

	}
}
