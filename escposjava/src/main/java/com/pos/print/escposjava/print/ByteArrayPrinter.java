package com.pos.print.escposjava.print;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * {@link Printer} que guarda en memoria lo impreso y da acceso a los bytes
 * mediante {@link #getSoFar()}
 */
public class ByteArrayPrinter implements Printer {
    private final int BUFFER_SIZE = 128000;

    private ByteArrayOutputStream buffer;

    @Override
    public void open() {
        buffer = new ByteArrayOutputStream(BUFFER_SIZE);
    }

    @Override
    public void write(byte[] command) {
        try {
            buffer.write(command);
        }
        catch (IOException e) {
            throw new RuntimeException("Error escribiendo al buffer");
        }
    }

    @Override
    public void close() {
        try {
            buffer.close();
        }
        catch (IOException e) {
            // Nada
        }
    }


    /**
     * @return Bytes escritos a este buffer hasta ahora
     */
    public byte[] getSoFar() {
        return buffer.toByteArray();
    }
}
