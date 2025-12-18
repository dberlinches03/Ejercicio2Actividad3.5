package org.psp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {
    public static void main(String[] args) {
        // Puerto en el que el servidor UDP escucha peticiones
        int puerto = 6000;
        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            System.out.println("Servidor UDP iniciado en el puerto " + puerto);

            // El servidor permanece a la espera de peticiones
            while (true) {
                // Buffer para recibir los datos del cliente
                byte[] buffer = new byte[1024];
                // Paquete UDP que contendrá los datos recibidos
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                // recieve se bloque hasta recibir un paquete
                socket.receive(paquete);

                // Se crea un hilo para atender la petición recibida
                new ClientHandlerUDP(socket, paquete).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}