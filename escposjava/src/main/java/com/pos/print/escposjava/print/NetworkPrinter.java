package com.pos.print.escposjava.print;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class NetworkPrinter implements Printer {
    private static Logger log = Logger.getLogger(NetworkPrinter.class.getName());

    private Socket socket = null;
    private OutputStream printer = null;
    private final String address;
    private final int    port;

    public NetworkPrinter(String address, int port) {
        this.address = address;
        this.port = port;
    }

    @Override
    public void open() {
        try {
            socket = new Socket(this.address, this.port);
            socket.setSoTimeout(1000);
            printer = socket.getOutputStream();
        }
        catch (IOException e) {
            log.warning("Error abriendo impresora: " + e.getMessage());
        }
    }

    @Override
    public void write(byte[] command) {
        try {
            printer.write(command);
        }
        catch (IOException e) {
            log.warning("Error escribiendo a la impresora: " + e.getMessage());
        }
    }

    @Override
    public void close() {
        try { printer.close(); } catch (IOException e) { log.warning("Error cerrando OutputStream de la impresora"); }
        try { socket.close(); } catch (IOException e) { log.warning("Error cerrando el socket de la impresora"); }
    }
}
