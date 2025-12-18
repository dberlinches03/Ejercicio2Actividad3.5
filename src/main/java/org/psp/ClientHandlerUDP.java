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

    @Override
    public void run() {
        try {
            String mensaje = new String(paquete.getData(), 0, paquete.getLength());
            int numero = Integer.parseInt(mensaje);

            String resultado = calcularPrimos(numero);

            byte[] datos = resultado.getBytes();
            DatagramPacket respuesta = new DatagramPacket(
                    datos, datos.length,
                    paquete.getAddress(),
                    paquete.getPort()
            );

            socket.send(respuesta);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
