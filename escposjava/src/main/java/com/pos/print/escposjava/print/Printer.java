package com.pos.print.escposjava.print;

/**
 * Comportamiento esperado de las impresoras
 */
public interface Printer extends AutoCloseable {
   void open();

   void write(byte[] command);

   @Override
   void close();
}
