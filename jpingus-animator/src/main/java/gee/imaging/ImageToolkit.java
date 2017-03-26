package gee.imaging;

import gee.imaging.sprites.Sprite;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageToolkit {
	private static MediaTracker track;

	static Hashtable sprites = new Hashtable();

	static Hashtable images = new Hashtable();

	static Component comp;

	public static void init(Component comp) {
		track = new MediaTracker(comp);
		sprites = new Hashtable();
		images = new Hashtable();
		ImageToolkit.comp = comp;
	}

	public static void waitForImage(Image img) {
		track.addImage(img, 0);
		try {
			track.waitForID(0);
		} catch (InterruptedException e) {
			return;
		}
	}

	public static void main(String[] args) {
		System.out.println(modifierPattern.matcher("RT0").matches());
		System.out.println(modifierPattern.matcher("ROT0").matches());
		System.out.println(modifierPattern.matcher("ROT90").matches());
		System.out.println(modifierPattern.matcher("ROT180").matches());
		System.out.println(modifierPattern.matcher("ROT270").matches());
		Matcher m = modifierPattern.matcher("ROT270");
		System.out.println(m.matches());
		System.out.println(m.groupCount());
		System.out.println(m.group(2));
		System.out.println(modifierPattern.matcher("ROT90FLIP").matches());
		System.out.println(modifierPattern.matcher("ROT180FLIP").matches());
		System.out.println(modifierPattern.matcher("ROT270FLIP").matches());
	}

	static Pattern modifierPattern = Pattern
			.compile("ROT(0|90|180|270)(FLIP)?");

	public static Image getImage(String uri, String modifier) {
		if(modifier==null)modifier="ROT0";
		String key = "[" + modifier + "][" + uri + "]";
		System.out.println(key);
		if (!images.containsKey(key)) {
			Toolkit kit = Toolkit.getDefaultToolkit();
			URL urlImage = Ressourcer.getResource(uri);
			Image img;
			if (urlImage == null) {
				System.err.println(uri + " could not be found");
				return null;

			}
			img = kit.getImage(urlImage);
			if (img == null) {
				System.err.println(uri + " could not be found");
				return null;
			}
			waitForImage(img);

			Matcher m = modifierPattern.matcher(modifier);
			if (!"ROT0".equals(modifier)) {
				if (m.matches()) {
					int angle = Integer.parseInt(m.group(1));
					boolean flip = m.groupCount() >= 2 && m.group(2) != null;
					BufferedImage bimg = null;
					int x = 0, y = 0;
					switch (angle) {
					case 0:
						bimg = new BufferedImage(img.getWidth(null), img
								.getHeight(null), BufferedImage.TYPE_INT_ARGB);
						x = 0;
						y = 0;
						break;
					case 90:
						bimg = new BufferedImage(img.getHeight(null), img
								.getWidth(null), BufferedImage.TYPE_INT_ARGB);
						x = 0;
						y = -img.getHeight(null);
						break;
					case 180:
						bimg = new BufferedImage(img.getWidth(null), img
								.getHeight(null), BufferedImage.TYPE_INT_ARGB);
						x = -img.getWidth(null);
						y = -img.getHeight(null);
						break;
					case 270:
						bimg = new BufferedImage(img.getHeight(null), img
								.getWidth(null), BufferedImage.TYPE_INT_ARGB);

						x = -img.getWidth(null);
						y = 0;
						break;
					}
					Graphics2D g2d = bimg.createGraphics();
					// Debug
					// g2d.setColor(new Color(0,0xf0,0,0x80));
					// g2d.fill(new
					// Rectangle(0,0,bimg.getWidth(),bimg.getHeight()));
					// Debug
					AffineTransform origXform = g2d.getTransform();
					AffineTransform newXform = (AffineTransform) (origXform
							.clone());
					newXform.rotate(Math.toRadians(angle), 0, 0);
					g2d.setTransform(newXform);
					g2d.drawImage(img, x, y, comp);
					g2d.setTransform(origXform);
					if (flip) {
						for (x = 0; x < bimg.getWidth() / 2; x++) {
							for (y = 0; y < bimg.getHeight(); y++) {
								int rgb1 = bimg.getRGB(bimg.getWidth() - x - 1,
										y);
								// rgb1=rgb1 | 0x00FF0000;
								int rgb2 = bimg.getRGB(x, y);
								// rgb2=rgb2 | 0x0000FF00;
								bimg.setRGB(x, y, rgb1);
								bimg.setRGB(bimg.getWidth() - x - 1, y, rgb2);
							}
						}
						// System.out.println(key+": is FLIPPED");
					} else {
						// System.out.println(key+": is NOT FLIPPED");
					}
					waitForImage(bimg);
					img = bimg;
				} else {
					// System.err.println("Modifier unknown:"+modifier);
				}
			} else {
				// System.out.println(key);
			}
			images.put(key, img);

		}
		// System.err.println(key);
		return (Image) images.get(key);
	}

	public static Sprite createSprite(String uri, String modifier, int width,
			int height) {
		Image img = getImage(uri, modifier);
		if (!sprites.containsKey(uri)) {
			sprites.put(uri, new Sprite(img, width, height));
		}
		return (Sprite) sprites.get(uri);
	}

	public static Sprite createSprite(String uri, String modifier) {
		if (!sprites.containsKey(uri)) {
			Image img = getImage(uri, modifier);
			sprites.put(uri, new Sprite(img, 0, 0));
		}
		return (Sprite) sprites.get(uri);
	}

	public static Image createTiledImage(Image srce, int d_width, int d_height) {
		int s_width = srce.getWidth(null);
		int s_height = srce.getHeight(null);
		BufferedImage img = new BufferedImage(d_width, d_height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();
		g.setColor(Color.RED);
		g.fill(new Rectangle(0, 0, d_width, d_height));
		for (int x = 0; x < d_width; x += s_width)
			for (int y = 0; y < d_height; y += s_height) {
				g.drawImage(srce, x, y, s_width, s_height, null);
			}
		waitForImage(img);
		return img;
	}

	public static Image getImagedShape(Shape s) {
		Rectangle b = s.getBounds();
		BufferedImage img = new BufferedImage(b.width, b.height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D gBuffered = img.createGraphics();
		gBuffered.setBackground(new Color(0, 0, 0, 0));
		gBuffered.setColor(new Color(0, 0, 0xff, 0xff));
		gBuffered.fill(s);
		waitForImage(img);
		return img;
	}

	public static Image getNotImagedShape(Shape s) {
		Rectangle b = s.getBounds();
		BufferedImage img = new BufferedImage(b.width, b.height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D gBuffered = img.createGraphics();
		gBuffered.setBackground(new Color(0xff, 0xff, 0xff, 0xff));
		gBuffered.setColor(new Color(0, 0, 0, 0));
		gBuffered.fill(s);
		waitForImage(img);
		return img;
	}

	public static int[] getPixeledShape(Shape s) {
		return getPixels(getImagedShape(s), s.getBounds());
	}

	public static int[] getPixels(Image img, Rectangle r) {
		System.gc();
		int pixels[] = new int[r.width * r.height];

		PixelGrabber pg = new PixelGrabber(img, r.x, r.y, r.width, r.height,
				pixels, 0, r.width);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			pixels = null;
			return pixels;
		}
		pg = null;
		// in nanoseconds long l = System.currentTimeMillis();
		return pixels;

	}
}
