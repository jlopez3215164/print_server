package com.pos.print.escposjava;

import static com.pos.print.escposjava.print.Commands.BARCODE_CODE39;
import static com.pos.print.escposjava.print.Commands.BARCODE_EAN13;
import static com.pos.print.escposjava.print.Commands.BARCODE_EAN8;
import static com.pos.print.escposjava.print.Commands.BARCODE_FONT_A;
import static com.pos.print.escposjava.print.Commands.BARCODE_FONT_B;
import static com.pos.print.escposjava.print.Commands.BARCODE_HEIGHT;
import static com.pos.print.escposjava.print.Commands.BARCODE_ITF;
import static com.pos.print.escposjava.print.Commands.BARCODE_NW7;
import static com.pos.print.escposjava.print.Commands.BARCODE_TXT_ABV;
import static com.pos.print.escposjava.print.Commands.BARCODE_TXT_BLW;
import static com.pos.print.escposjava.print.Commands.BARCODE_TXT_BTH;
import static com.pos.print.escposjava.print.Commands.BARCODE_TXT_OFF;
import static com.pos.print.escposjava.print.Commands.BARCODE_UPC_A;
import static com.pos.print.escposjava.print.Commands.BARCODE_UPC_E;
import static com.pos.print.escposjava.print.Commands.BARCODE_WIDTH;
import static com.pos.print.escposjava.print.Commands.BEEPER;
import static com.pos.print.escposjava.print.Commands.CD_KICK_2;
import static com.pos.print.escposjava.print.Commands.CD_KICK_5;
import static com.pos.print.escposjava.print.Commands.CHARCODE_GREEK;
import static com.pos.print.escposjava.print.Commands.CHARCODE_HEBREW;
import static com.pos.print.escposjava.print.Commands.CHARCODE_JIS;
import static com.pos.print.escposjava.print.Commands.CHARCODE_PC1252;
import static com.pos.print.escposjava.print.Commands.CHARCODE_PC437;
import static com.pos.print.escposjava.print.Commands.CHARCODE_PC850;
import static com.pos.print.escposjava.print.Commands.CHARCODE_PC852;
import static com.pos.print.escposjava.print.Commands.CHARCODE_PC858;
import static com.pos.print.escposjava.print.Commands.CHARCODE_PC860;
import static com.pos.print.escposjava.print.Commands.CHARCODE_PC863;
import static com.pos.print.escposjava.print.Commands.CHARCODE_PC865;
import static com.pos.print.escposjava.print.Commands.CHARCODE_PC866;
import static com.pos.print.escposjava.print.Commands.CHARCODE_THAI11;
import static com.pos.print.escposjava.print.Commands.CHARCODE_THAI13;
import static com.pos.print.escposjava.print.Commands.CHARCODE_THAI14;
import static com.pos.print.escposjava.print.Commands.CHARCODE_THAI16;
import static com.pos.print.escposjava.print.Commands.CHARCODE_THAI17;
import static com.pos.print.escposjava.print.Commands.CHARCODE_THAI18;
import static com.pos.print.escposjava.print.Commands.CHARCODE_THAI42;
import static com.pos.print.escposjava.print.Commands.CHARCODE_WEU;
import static com.pos.print.escposjava.print.Commands.CTL_LF;
import static com.pos.print.escposjava.print.Commands.HW_INIT;
import static com.pos.print.escposjava.print.Commands.LINE_SPACE_24;
import static com.pos.print.escposjava.print.Commands.PAPER_FULL_CUT;
import static com.pos.print.escposjava.print.Commands.PAPER_PART_CUT;
import static com.pos.print.escposjava.print.Commands.PD_0;
import static com.pos.print.escposjava.print.Commands.PD_N12;
import static com.pos.print.escposjava.print.Commands.PD_N25;
import static com.pos.print.escposjava.print.Commands.PD_N37;
import static com.pos.print.escposjava.print.Commands.PD_N50;
import static com.pos.print.escposjava.print.Commands.PD_P12;
import static com.pos.print.escposjava.print.Commands.PD_P25;
import static com.pos.print.escposjava.print.Commands.PD_P37;
import static com.pos.print.escposjava.print.Commands.PD_P50;
import static com.pos.print.escposjava.print.Commands.SELECT_BIT_IMAGE_MODE;
import static com.pos.print.escposjava.print.Commands.TXT_2HEIGHT;
import static com.pos.print.escposjava.print.Commands.TXT_2WIDTH;
import static com.pos.print.escposjava.print.Commands.TXT_4SQUARE;
import static com.pos.print.escposjava.print.Commands.TXT_ALIGN_CT;
import static com.pos.print.escposjava.print.Commands.TXT_ALIGN_LT;
import static com.pos.print.escposjava.print.Commands.TXT_ALIGN_RT;
import static com.pos.print.escposjava.print.Commands.TXT_BOLD_OFF;
import static com.pos.print.escposjava.print.Commands.TXT_BOLD_ON;
import static com.pos.print.escposjava.print.Commands.TXT_FONT_A;
import static com.pos.print.escposjava.print.Commands.TXT_FONT_B;
import static com.pos.print.escposjava.print.Commands.TXT_NORMAL;
import static com.pos.print.escposjava.print.Commands.TXT_UNDERL2_ON;
import static com.pos.print.escposjava.print.Commands.TXT_UNDERL_OFF;
import static com.pos.print.escposjava.print.Commands.TXT_UNDERL_ON;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.pos.print.escposjava.print.Printer;
import com.pos.print.escposjava.print.exceptions.BarcodeSizeError;
import com.pos.print.escposjava.print.exceptions.QRCodeException;
import com.pos.print.escposjava.print.image.Image;
import com.pos.print.escposjava.print.qrcode.QRCodeGenerator;

public class PrinterService implements AutoCloseable {
   private static final String CARRIAGE_RETURN = System.getProperty("line.separator");

   public final int MIN_BEEP_LEN = 1;
   public final int MAX_BEEP_LEN = 9;

   private final Printer printer;

   public PrinterService(Printer printer){
      this.printer = printer;
      open();
   }

   public void print(String text) {
      write(text.getBytes());
   }

   public void printLn(String text) {
      print(text + CARRIAGE_RETURN);
   }

   public void lineBreak() {
      lineBreak(1);
   }

   public void lineBreak(int nbLine) {
      for (int i=0;i<nbLine;i++) {
         write(CTL_LF);
      }
   }

   public void printQRCode(String value) throws QRCodeException {
      printQRCode(value, 150);
   }

   public void printQRCode(String value, int size) throws QRCodeException {
      QRCodeGenerator q = new QRCodeGenerator();
      printImage(q.generate(value, size));
   }

   public void setTextSizeNormal(){
      setTextSize(1,1);
   }

   public void setTextSize2H(){
      setTextSize(1,2);
   }

   public void setTextSize2W(){
      setTextSize(2,1);
   }

   public void setText4Square(){
      setTextSize(2,2);
   }

    private void setTextSize(int width, int height) {
        if (height == 2 && width == 2) {
            write(TXT_NORMAL);
            write(TXT_4SQUARE);
        }
        else if (height == 2) {
            write(TXT_NORMAL);
            write(TXT_2HEIGHT);
        }
        else if (width == 2) {
            write(TXT_NORMAL);
            write(TXT_2WIDTH);
        }
        else {
            write(TXT_NORMAL);
        }
    }

   public void setTextTypeBold(){
      setTextType("B");
   }

   public void setTextTypeUnderline(){
      setTextType("U");
   }

   public void setTextType2Underline(){
      setTextType("U2");
   }

   public void setTextTypeBoldUnderline(){
      setTextType("BU");
   }

   public void setTextTypeBold2Underline(){
      setTextType("BU2");
   }

   public void setTextTypeNormal(){
      setTextType("NORMAL");
   }

   private void setTextType(String type){
      if (type.equalsIgnoreCase("B")){
         write(TXT_BOLD_ON);
         write(TXT_UNDERL_OFF);
      }else if(type.equalsIgnoreCase("U")){
         write(TXT_BOLD_OFF);
         write(TXT_UNDERL_ON);
      }else if(type.equalsIgnoreCase("U2")){
         write(TXT_BOLD_OFF);
         write(TXT_UNDERL2_ON);
      }else if(type.equalsIgnoreCase("BU")){
         write(TXT_BOLD_ON);
         write(TXT_UNDERL_ON);
      }else if(type.equalsIgnoreCase("BU2")){
         write(TXT_BOLD_ON);
         write(TXT_UNDERL2_ON);
      }else if(type.equalsIgnoreCase("NORMAL")){
         write(TXT_BOLD_OFF);
         write(TXT_UNDERL_OFF);
      }
   }

   public void cutPart(){
      cut("PART");
   }

   public void cutFull(){
      cut("FULL");
   }

   private void cut(String mode){
      for (int i = 0; i < 5; i++){
         write(CTL_LF);
      }
      if (mode.toUpperCase().equals("PART")){
         write(PAPER_PART_CUT);
      }else{
         write(PAPER_FULL_CUT);
      }
   }

   public void printBarcode(String code, String bc, int width, int height, String pos, String font) throws BarcodeSizeError {
      // Align Bar Code()
      write(TXT_ALIGN_CT);
      // Height
      if (height >=2 || height <=6) {
         write(BARCODE_HEIGHT);
      } else {
         throw new BarcodeSizeError("Incorrect Height");
      }
      //Width
      if (width >= 1 || width <=255) {
         write(BARCODE_WIDTH);
      } else {
         throw new BarcodeSizeError("Incorrect Width");
      }
      //Font
      if (font.equalsIgnoreCase("B")) {
         write(BARCODE_FONT_B);
      } else {
         write(BARCODE_FONT_A);
      }
      //Position
      if (pos.equalsIgnoreCase("OFF")) {
         write(BARCODE_TXT_OFF);
      } else if (pos.equalsIgnoreCase("BOTH")) {
         write(BARCODE_TXT_BTH);
      } else if (pos.equalsIgnoreCase("ABOVE")) {
         write(BARCODE_TXT_ABV);
      } else {
         write(BARCODE_TXT_BLW);
      }
      //Type
      switch(bc.toUpperCase()){
         case "UPC-A":
            write(BARCODE_UPC_A);
            break;
         case "UPC-E":
            write(BARCODE_UPC_E);
            break;
         default: case "EAN13":
            write(BARCODE_EAN13);
            break;
         case "EAN8":
            write(BARCODE_EAN8);
            break;
         case "CODE39":
            write(BARCODE_CODE39);
            break;
         case "ITF":
            write(BARCODE_ITF);
            break;
         case "NW7":
            write(BARCODE_NW7);
            break;
      }
      //Print Code
      if (!code.equals("")) {
         write(code.getBytes());
         write(CTL_LF);
      } else {
         throw new BarcodeSizeError("Incorrect Value");
      }
   }

   public void setTextFontA(){
      setTextFont("A");
   }

   public void setTextFontB(){
      setTextFont("B");
   }

   private void setTextFont(String font){
      if (font.equalsIgnoreCase("B")){
         write(TXT_FONT_B);
      }else{
         write(TXT_FONT_A);
      }
   }

   public void setTextAlignCenter(){
      setTextAlign("CENTER");
   }

   public void setTextAlignRight(){
      setTextAlign("RIGHT");
   }

   public void setTextAlignLeft(){
      setTextAlign("LEFT");
   }

   private void setTextAlign(String align){
      if (align.equalsIgnoreCase("CENTER")){
         write(TXT_ALIGN_CT);
      }else if( align.equalsIgnoreCase("RIGHT")){
         write(TXT_ALIGN_RT);
      }else{
         write(TXT_ALIGN_LT);
      }
   }

   public void setTextDensity(int density){
      switch (density){
         case 0:
            write(PD_N50);
            break;
         case 1:
            write(PD_N37);
            break;
         case 2:
            write(PD_N25);
            break;
         case 3:
            write(PD_N12);
            break;
         case 4:
            write(PD_0);
            break;
         case 5:
            write(PD_P12);
            break;
         case 6:
            write(PD_P25);
            break;
         case 7:
            write(PD_P37);
            break;
         case 8:
            write(PD_P50);
            break;
      }
   }

   public void setTextNormal(){
      setTextProperties("LEFT", "A", "NORMAL", 1,1,9);
   }

   public void setTextProperties(String align, String font, String type, int width, int height, int density){
      setTextAlign(align);
      setTextFont(font);
      setTextType(type);
      setTextSize(width, height);
      setTextDensity(density);
   }

   public void printImage(String filePath) throws IOException {
      File img = new File(filePath);
      printImage(ImageIO.read(img));
   }

   public void printImage(String path, double scalex, double scaley) throws IOException {
       printImage(ImageIO.read(new File(path)), scalex, scaley);
   }

   public void printImage(String path, double scale) throws IOException {
       printImage(ImageIO.read(new File(path)), scale);
   }

   public void printImage(BufferedImage image) {
      Image img = new Image();
      int[][] pixels = img.getPixelsSlow(image);
      for (int y = 0; y < pixels.length; y += 24) {
         write(LINE_SPACE_24);
         write(SELECT_BIT_IMAGE_MODE);
         write(new byte[]{(byte)(0x00ff & pixels[y].length), (byte)((0xff00 & pixels[y].length) >> 8)});
         for (int x = 0; x < pixels[y].length; x++) {
            write(img.recollectSlice(y, x, pixels));
         }

         write(CTL_LF);
      }
//        bus.write(CTL_LF);
//        bus.write(LINE_SPACE_30);
   }

   public void printImage(BufferedImage image, double scalex, double scaley) {
       printImage(scaleImage(image, scalex, scaley));
   }

   public void printImage(BufferedImage image, double scale) {
       printImage(scaleImage(image, scale, scale));
   }

   /**
    * Escalar una imagen por los factores especificados
    * @param image  La imagen
    * @param scalex Factor de escalamiento horizontal
    * @param scaley Factor de escalamiento vertical
    * @return La imagen transformada
    */
   private static BufferedImage scaleImage(BufferedImage image, double scalex, double scaley) {
        try {
            BufferedImage res = new BufferedImage((int)(image.getWidth() * scalex),
                                                  (int)(image.getHeight() * scaley),
                                                  BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics = res.createGraphics();

            AffineTransform at = AffineTransform.getScaleInstance(scalex, scaley);
            graphics.drawRenderedImage(image, at);
            return res;
        }
        catch (Exception e) {
            System.err.println("Error procesando la imagen");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

   public void setCharCode(String code)  {
      switch (code){
         case "USA":
            write(CHARCODE_PC437);
            break;
         case "JIS":
            write(CHARCODE_JIS);
            break;
         case "MULTILINGUAL":
            write(CHARCODE_PC850);
            break;
         case "PORTUGUESE":
            write(CHARCODE_PC860);
            break;
         case "CA_FRENCH":
            write(CHARCODE_PC863);
            break;
         default: case "NORDIC":
            write(CHARCODE_PC865);
            break;
         case "WEST_EUROPE":
            write(CHARCODE_WEU);
            break;
         case "GREEK":
            write(CHARCODE_GREEK);
            break;
         case "HEBREW":
            write(CHARCODE_HEBREW);
            break;
         case "WPC1252":
            write(CHARCODE_PC1252);
            break;
         case "CIRILLIC2":
            write(CHARCODE_PC866);
            break;
         case "LATIN2":
            write(CHARCODE_PC852);
            break;
         case "EURO":
            write(CHARCODE_PC858);
            break;
         case "THAI42":
            write(CHARCODE_THAI42);
            break;
         case "THAI11":
            write(CHARCODE_THAI11);
            break;
         case "THAI13":
            write(CHARCODE_THAI13);
            break;
         case "THAI14":
            write(CHARCODE_THAI14);
            break;
         case "THAI16":
            write(CHARCODE_THAI16);
            break;
         case "THAI17":
            write(CHARCODE_THAI17);
            break;
         case "THAI18":
            write(CHARCODE_THAI18);
            break;
      }
   }

   public void init(){
      write(HW_INIT);
   }

   public void openCashDrawerPin2() {
      write(CD_KICK_2);
   }

   public void openCashDrawerPin5() {
      write(CD_KICK_5);
   }

   public void open(){
      printer.open();
   }

   @Override
   public void close() {
      printer.close();
   }

   public void beep(){
      write(BEEPER);
   }

   /**
    * Beep un cierto n&uacute;mero de veces en la impresora, si la misma lo
    * soporta
    * @param times N&uacute;mero de veces a sonar
    * @param len   Longitud del sonido. La longitud va desde {@link #MIN_BEEP_LEN}
    * hasta {@link #MAX_BEEP_LEN} y depende de la impresora.
    */
   public void beep(int times, int len) {
       if (times > 0) {
           if (len < MIN_BEEP_LEN)      len = MIN_BEEP_LEN;
           else if (len > MAX_BEEP_LEN) len = MAX_BEEP_LEN;
           byte[] command = { 0x1b, 0x42, (byte)times, (byte)len };
           write(command);
       }
   }

   public void write(byte[] command){
      printer.write(command);
   }

}
