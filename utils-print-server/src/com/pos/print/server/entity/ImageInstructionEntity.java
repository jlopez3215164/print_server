package com.pos.print.server.entity;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pos.print.escposjava.PrinterService;
import com.pos.print.escposjava.print.ByteArrayPrinter;

public class ImageInstructionEntity extends PrinterInstructionEntity {

	@JsonProperty("base64Image")
	private String base64Image;
	@JsonProperty("pixelWidth")
	private int pixelWidth = 350;

	public ImageInstructionEntity() {

	}

	public ImageInstructionEntity(String base64Image, int pixelWidth) {
		super();
		this.base64Image = base64Image;
		this.pixelWidth = pixelWidth;
	}

	private static BufferedImage fromBase64(String base64Text) throws IOException {
		try {
			byte[] content = Base64.getDecoder().decode(base64Text);
			ByteArrayInputStream bais = new ByteArrayInputStream(content);
			BufferedImage res = ImageIO.read(bais);
			return res;
		} catch (IOException e) {
			System.err.println("Error creando la imagen");
			e.printStackTrace();
			throw e;
		}
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public int getPixelWidth() {
		return pixelWidth;
	}

	public void setPixelWidth(int pixelWidth) {
		this.pixelWidth = pixelWidth;
	}

	@Override
	public void sendPrint(PrinterService service) throws Throwable {
		BufferedImage image = fromBase64(this.base64Image);
		double scale = scaleTo(image, this.pixelWidth);
		image = scaleImage(image, scale, scale);
		//-----------------------------------------------------
		service.setTextAlignCenter();
		service.printImage(image);
		service.setTextAlignLeft();
		service.printLn("");
	}

	/**
	 * Escalar una imagen por los factores especificados
	 * 
	 * @param image
	 *            La imagen
	 * @param scalex
	 *            Factor de escalamiento horizontal
	 * @param scaley
	 *            Factor de escalamiento vertical
	 * @return La imagen transformada
	 */
	private static BufferedImage scaleImage(BufferedImage image, double scalex, double scaley) {
		try {
			BufferedImage res = new BufferedImage((int) (image.getWidth() * scalex), (int) (image.getHeight() * scaley),
					BufferedImage.TYPE_4BYTE_ABGR);
			Graphics2D graphics = res.createGraphics();

			AffineTransform at = AffineTransform.getScaleInstance(scalex, scaley);
			graphics.drawRenderedImage(image, at);
			return res;
		} catch (Exception e) {
			System.err.println("Error procesando la imagen");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 *
	 * @param image
	 *            La imagen
	 * @param target
	 *            Pixels horizontal que se desean de la imagen
	 * @return Scale necesario para que la imagen llegue al target
	 */
	private static double scaleTo(BufferedImage image, int target) {
		return ((double) target) / image.getWidth();
	}

}
