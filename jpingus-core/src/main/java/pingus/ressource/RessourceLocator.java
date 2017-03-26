package pingus.ressource;

import java.awt.Frame;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gee.imaging.ImageToolkit;
import gee.imaging.Ressourcer;

public class RessourceLocator {
	private static String findRessource(BufferedReader in, String section,
			String search) throws Exception {
		String found;
		String key = "[" + section + "/" + search + "]";
		// System.out.println("["+section+"]");
		while ((found = in.readLine()) != null) {
			if (found.startsWith("//"))
				continue;
			int i = found.indexOf("section");
			if (i > -1) {
				String thisSection = found.substring(i + 8);

				i = thisSection.indexOf("{");
				if (i > -1) {
					thisSection = thisSection.substring(0, i);
				}
				thisSection = thisSection.trim();
				String ret = findRessource(in, section + thisSection + "/",
						search);
				if (ret != null)
					return ret;
			}
			i = found.indexOf("}");
			if (i > -1) {
				// System.out.println(key+":not found 1.1");
				return null;
			}
			i = found.indexOf("=");
			int i2 = found.indexOf("//");
			if (i > -1 && i2 == -1) {
				String part = found.substring(0, i).trim();
				String match = section + part;

				if (match.equalsIgnoreCase(search))
					return found.substring(i + 1).trim();
			}
		}
//		System.out.println(key + ":not found 2.1");
		return null;
	}

	static Pattern match = Pattern.compile("(\\d+).*");

	static Hashtable sprites = new Hashtable();

	private static Image findImage(String type, String file, String ident,
			String modifier) throws IOException {
		Ressource ret = findRessource(type, file, ident, modifier);
		if (ret != null)
			return ret.getImage(0, 0);
//		System.out.println("Could not find image : " + file + "/" + type + "/"
//				+ ident);
		return null;
	}

	public static Ressource findRessource(String type, String file,
			String ident, String modifier) {
		String key = "[" + type + "," + file + "," + ident + "," + modifier
				+ "]";
//		System.out.println("key:"+key);
		if (!sprites.containsKey(key)) {
			InputStream input = Ressourcer
					.getResourceAsStream("/Ressource/data/data/" + file
							+ ".res");
			if (input == null) {
//				System.out.println(key + ":not found 1");
				return null;
			}
			RessourceFile resFile = RessourceFile.read(file,new InputStreamReader(
					input));
			
			RessourceEntry res = resFile.find(ident);
			String imageLocation = "/Ressource/data" + res.file.replaceFirst("..","");
//			System.out.println(imageLocation);
			Image im = ImageToolkit.getImage(imageLocation,modifier);
			Ressource sp = new Ressource(im, res.width, res.height, type, file,
					ident, modifier);
			sprites.put(key, sp);
			return sp;
		}

		try {
			return (Ressource)((Ressource) sprites.get(key)).clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	// public static Image findRessource(String type, String file,String ident,
	// String modifier)throws IOException{
	//
	// String dir = "/Ressource/";
	// if(type.equals("datafile")){
	// dir+="data/";
	// if(file.equals("textures")||file.equals("liquids")||file.equals("entrances")||file.equals("exits")){
	// dir+="images/";
	// dir+= file;
	// String dest=dir+ident.substring(ident.indexOf("/"))+".png";
	// URL test = Ressourcer.getResource(dest);
	// if(test!=null)return ImageToolkit.getImage(dest);
	// dest=dir+ident.substring(ident.indexOf("/"))+".jpg";
	// test = Ressourcer.getResource(dest);
	// if(test!=null)return ImageToolkit.getImage(dest);;
	//				
	// }else if(file.equals("hotspots")){
	// dir+="images/";
	// dir+= file+"/";
	// URL test = Ressourcer.getResource(dir+"/"+ident+".png");
	// if(test!=null)
	// return ImageToolkit.getImage(dir+"/"+ident+".png");
	// test = Ressourcer.getResource(dir+"/"+ident+".jpg");
	// if(test!=null)
	// return ImageToolkit.getImage(dir+"/"+ident+".jpg");
	//				
	// }else if(file.startsWith("groundpieces")){
	// dir+="images/";
	// dir+= file.replace('-','/')+"/";
	// URL test = Ressourcer.getResource(dir+"/"+ident+".png");
	// if(test!=null)
	// return ImageToolkit.getImage(dir+"/"+ident+".png");
	// test = Ressourcer.getResource(dir+"/"+ident+".jpg");
	// if(test!=null)
	// return ImageToolkit.getImage(dir+"/"+ident+".jpg");
	//				
	// }
	// }
	// return null;
	// }
	public static void main(String[] args) throws IOException {
		/*
		 * <surface><resource type="datafile"> <resource-datafile>textures</resource-datafile>
		 * <resource-ident>textures/clouds2</resource-ident> <modifier>ROT0</modifier>
		 * </resource></surface>
		 */
		ImageToolkit.init(new Frame("test"));
//		System.out.println(findImage("datafile", "textures",
//				"textures/clouds2", "ROT0"));
	}
}
