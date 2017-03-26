package gee.imaging.sprites.animated;

import gee.imaging.ImageToolkit;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;

public class AlphaSprite {
	public static final int MODE_AND = 1;

	public static final int MODE_OR = 0;

	int mode, width, height;

	Shape shape;

	int pixels[];

	public AlphaSprite(Shape s, boolean negative, int mode) {
		this.mode = mode;
		this.shape = s;
		Rectangle bounds = s.getBounds();
		this.width = bounds.width;
		this.height = bounds.height;
		Image img;
		if (negative) {
			img = ImageToolkit.getNotImagedShape(s);
		} else {
			img = ImageToolkit.getImagedShape(s);
		}
		pixels = ImageToolkit.getPixels(img, bounds);
		img = null;
	}

	public void render(int picture[], int pictureX, int pictureY,
			int pictureWidth) {
		for (int alphaX = 0; alphaX < width; alphaX++) {
			for (int alphaY = 0; alphaY < height; alphaY++) {
				int idPicture = ((pictureY + alphaY) * pictureWidth) + pictureX
						+ alphaX;
				int idAlpha = ((alphaY) * width) + alphaX;
				int pixelPicture = picture[idPicture];
				int pixelAlpha = pixels[idAlpha];
				switch (mode) {
				// or
				case MODE_OR:
					picture[idPicture] = pixelPicture | pixelAlpha;
					break;

				// and
				case MODE_AND:
					picture[idPicture] = pixelPicture & pixelAlpha;
					break;

				default:
					throw new Error("Please select valid mode");
				}
			}
		}
	}

	public int getHeight() {
		return height;
	}

	public int[] getPixels() {
		return pixels;
	}

	public Shape getShape() {
		return shape;
	}

	public int getWidth() {
		return width;
	}
}
