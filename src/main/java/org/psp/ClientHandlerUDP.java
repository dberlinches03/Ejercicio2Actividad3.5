package org.psp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ClientHandlerUDP extends Thread {
    private DatagramSocket socket;
    private DatagramPacket paquete;

    public ClientHandlerUDP(DatagramSocket socket, DatagramPacket paquete) {
        this.socket = socket;
        this.paquete = paquete;
    }
}
