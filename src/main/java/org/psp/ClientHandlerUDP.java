package org.psp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ClientHandlerUDP extends Thread {
    private DatagramSocket socket;
    private DatagramPacket paquete;

    // Constructor del hilo
    public ClientHandlerUDP(DatagramSocket socket, DatagramPacket paquete) {
        this.socket = socket;
        this.paquete = paquete;
    }

    // Metodo run que se ejecuta al iniciar el hilo
    @Override
    public void run() {
        try {
            // Se extra el mensaje recibido del paquete UDP
            String mensaje = new String(paquete.getData(), 0, paquete.getLength());
            int numero = Integer.parseInt(mensaje); // convierte el mensaje en entero

            String resultado = calcularPrimos(numero); // utiliza la funcion de calcular primos para calcular el resultado hasta el numero indicado

            // Se convierten los datos a bytes para enviarlos
            byte[] datos = resultado.getBytes();
            // Se crea el paquete de respuesta al cliente
            DatagramPacket respuesta = new DatagramPacket(
                    datos, datos.length,
                    paquete.getAddress(),
                    paquete.getPort()
            );

            socket.send(respuesta); // envia la respuesta al cliente

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Funcion para calcular los primos
    private String calcularPrimos(int n) {
        String resultado = "";


        // Recorre todos los números desde 2 hasta n
        for (int i = 2; i <= n; i++) {
            boolean primo = true;

            // Comprueba si i es divisible por algún número menor
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    primo = false;
                    break;
                }
            }

            // si es primo, se añade al resultado
            if (primo) {
                resultado += i + ",";
            }
        }

        return resultado;
    }
}
