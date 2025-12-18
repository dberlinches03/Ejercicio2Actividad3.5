package org.psp;

import java.net.*;
import java.util.Scanner;

public class ClienteUDP {

    public static void main(String[] args) {
        int puerto = 6000;
        String servidor = "localhost";

        try (
                DatagramSocket socket = new DatagramSocket();
                Scanner teclado = new Scanner(System.in)
        ) {
            System.out.print("Introduce un número: ");
            String numero = teclado.nextLine();

            byte[] datos = numero.getBytes();
            InetAddress direccion = InetAddress.getByName(servidor);

            DatagramPacket paquete = new DatagramPacket(
                    datos, datos.length, direccion, puerto);
            socket.send(paquete);

            byte[] buffer = new byte[2048];
            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
            socket.receive(respuesta);

            String resultado = new String(
                    respuesta.getData(), 0, respuesta.getLength());

            System.out.println("Primos: " + resultado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}