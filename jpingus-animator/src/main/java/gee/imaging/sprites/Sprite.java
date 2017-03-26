package gee.imaging.sprites;

import gee.imaging.ImageToolkit;
import gee.imaging.Renderable;

import java.awt.*;
import java.awt.image.*;

//All managing things for a sprite.
public class Sprite implements Renderable {
	long animTime = 0;

	int z;

	int animSpeed = 70;

	Image source[][];

	Image currentImage;

	boolean tiled;

	protected int width, height;

	protected int i_width, i_height;

	int idx = 0, idy = 0;

	public void free() {
		for (int x = 0; x < source.length; x++) {
			for (int y = 0; y < source[x].length; y++) {
				try {
					source[x][y].getGraphics().dispose();
				} catch (Throwable e) {
					// TODO: handle exception
				}
				source[x][y] = null;
			}

		}
	}

	public int getSpriteLength() {
		return source[0].length;
	}

	public Sprite(Image img) {
		this(img, 0, 0);
	}

	public void setTiled(int w, int h, boolean tiled) {
		this.width = w;
		this.height = h;
		this.tiled = (width > i_width || height > i_height) && tiled;

	}

	public Sprite(Image img, int w, int h) {
		width = w;
		height = h;
		i_width = img.getWidth(null);
		i_height = img.getHeight(null);
		idx = 0;

		if (w == 0) {
			width = i_width;
		}
		if (h == 0) {
			height = i_height;
		}
		tiled = width > i_width || height > i_height;
		if (!tiled) {
			int nbWitdh = i_width / width;
			int nbHeight = i_height / height;

			source = new Image[nbHeight][nbWitdh];
			if (nbHeight == 1 && nbWitdh == 1) {
				source[0][0] = img;
				return;
			}
			ImageProducer sourceI = img.getSource();
			for (int x = 0; x < nbWitdh; x++) {
				for (int y = 0; y < nbHeight; y++) {
					ImageFilter extractFilter = new CropImageFilter(
							(x * width), (y * height), width, height);
					ImageProducer producer = new FilteredImageSource(sourceI,
							extractFilter);
					source[y][x] = Toolkit.getDefaultToolkit().createImage(
							producer);
					ImageToolkit.waitForImage(source[y][x]);
					producer = null;
					extractFilter = null;
				}
			}
		} else {
			source = new Image[1][1];
			source[0][0] = img;

		}
		if (w != 0) {
			i_width = width;
		}
		if (h != 0) {
			i_height = height;

		}

	}

	public Image getImage(int idImg) {
		if (idImg < 0)
			return null;
		if (source.length == 1) {
			return source[0][idImg % source[0].length];
		} else if (source[0].length == 1) {
			return source[idImg % source.length][0];
		} else {
			throw new Error("Please specify y");
		}
	}

	public Image getImage(int idImgX, int idImgY) {
		if (idImgX < 0)
			return null;
		if (idImgY < 0)
			return null;
		return source[idImgY % source.length][idImgX % source[0].length];
	}

	public boolean update(long elapsed) {
		boolean ret = false;
		if (source[0].length != 1) {
			animTime += elapsed;
			idx += animTime / animSpeed;
			ret = idx > source[0].length;
			idx = idx % source[0].length;
			if (idx < 0)
				idx = -idx;
			animTime = animTime % animSpeed;
		}

		currentImage = source[idy][idx];
		return ret;
		// idx_source++;
		// idx_source = idx_source % source[0].length;
	}

	public void paint(Graphics g, int _x, int _y) {
		if (!tiled) {
			g.drawImage(currentImage, _x, _y, width, height, null);
		} else {
			for (int x = 0; x + _x <= width; x += i_width) {
				for (int y = 0; y <= height; y += i_height) {
					if (x == 0) {
						g.drawImage(currentImage, x + _x, y + _y, null);
					} else {
						g.drawImage(source[idy][idx], x + _x, y + _y, null);
					}
				}
			}
		}
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public Image getCurrentImage() {
		return currentImage;
	}

	public void render(Graphics2D g) {
		g.drawImage(currentImage, 0, 0, null);
	}

	public void renderMap(Graphics2D g, float rate, int x, int y) {
		float _w = width * rate;
		float _h = height * rate;
		g.drawImage(currentImage, x, y, (int) _w, (int) _h, null);
	}

	public int getType() {
		return SPRITE;
	}

	public void initOffset(int offsetx, int offsety) {
		// TODO Auto-generated method stub

	}

	public void setUseOffset(boolean useOffset) {
		// TODO Auto-generated method stub

	}

	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getZ() {
		// TODO Auto-generated method stub
		return z;
	}

	public void init(int x, int y, int z) {
		// TODO Auto-generated method stub
		this.z = z;
	}

	public boolean contained(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx % source[0].length;
	}

	public int getIdy() {
		return idy;
	}

	public void setIdy(int idy) {
		this.idy = idy % source.length;

	}

}
