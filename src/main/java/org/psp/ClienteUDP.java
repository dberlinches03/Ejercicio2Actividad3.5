package org.psp;

import java.net.*;
import java.util.Scanner;

public class ClienteUDP {

    public static void main(String[] args) {
        int puerto = 6000; // Puerto del servidor
        String servidor = "localhost"; // Direccion del servidor

        try (
                // Socket UDP del cliente
                DatagramSocket socket = new DatagramSocket();
                // Scanner para leer el número desde teclado
                Scanner teclado = new Scanner(System.in)
        ) {

            // Se solicita el número al usuario
            System.out.print("Introduce un número: ");
            String numero = teclado.nextLine();

            // Se convierten los datos a bytes
            byte[] datos = numero.getBytes();

            // Direccion IP del servidor
            InetAddress direccion = InetAddress.getByName(servidor);

            // Se crea el paquete que se enviará al servidor
            DatagramPacket paquete = new DatagramPacket(
                    datos, datos.length, direccion, puerto);
            // Se envía el paquete UDP al servidor
            socket.send(paquete);

            // Buffer para recibir la respuesta
            byte[] buffer = new byte[2048];

            // Paquete para recibir los datos del servidor
            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
            // Se espera la respuesta del servidor
            socket.receive(respuesta);

            // Se convierte la respuesta a String
            String resultado = new String(
                    respuesta.getData(), 0, respuesta.getLength());

            System.out.println("Primos: " + resultado); // Se muestra el resultado por pantalla

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}