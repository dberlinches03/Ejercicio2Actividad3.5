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
            // Crea el mensaje
            String mensaje = new String(paquete.getData(), 0, paquete.getLength());
            int numero = Integer.parseInt(mensaje); // convierte el mensaje en entero

            String resultado = calcularPrimos(numero); // utiliza la funcion de calcular primos para calcular el resultado

            byte[] datos = resultado.getBytes();
            DatagramPacket respuesta = new DatagramPacket(
                    datos, datos.length,
                    paquete.getAddress(),
                    paquete.getPort()
            );

            socket.send(respuesta); // envia la respuesta

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Funcion para calcular los primos
    private String calcularPrimos(int n) {
        String resultado = "";


        for (int i = 2; i <= n; i++) {
            boolean primo = true;

            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    primo = false;
                    break;
                }
            }

            // si es primo sumale i y ,
            if (primo) {
                resultado += i + ",";
            }
        }

        return resultado; // devuelve un entero
    }
}
