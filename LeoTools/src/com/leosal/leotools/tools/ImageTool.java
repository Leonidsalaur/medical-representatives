package com.leosal.leotools.tools;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageTool {

	public static BufferedImage scale(BufferedImage image, int imageType, int width, int height, double fWidth, double fHeight){
		BufferedImage result = null;
		if(image!=null){
			result = new BufferedImage(width, height, imageType);
			Graphics2D g = result.createGraphics();
			AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
			g.drawRenderedImage(image, at);
		}
		return result;
	}
}
