import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String [] args){

        ServerSocket servidor = null;
        Socket socketCliente = null;

        DataInputStream in;
        DataOutputStream out;

        int PUERTO = 5000;

        try {

            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado");

            // Mantener el servidor escuchando
            while (true){

                socketCliente = servidor.accept(); // Esperar la conexión del cliente
                System.out.println("Cliente conectado");

                // Flujo de entrada y salida
                in = new DataInputStream(socketCliente.getInputStream());
                out = new DataOutputStream(socketCliente.getOutputStream());

                boolean continuar = true;

                while (continuar){

                    String mensajeCliente = in.readUTF(); // Leer mensaje del cliente

                    if (mensajeCliente.equals("Quien es?")){
                        out.writeUTF("Soy yo");
                    } else if (mensajeCliente.equals("Que vienes a buscar?")) {
                        out.writeUTF("A ti");
                    } else if (mensajeCliente.equals("Ya es tarde")) {
                        out.writeUTF("Por que?");
                    } else if (mensajeCliente.equals("Porque ahora soy yo la que quiere estar sin ti")) {
                        out.writeUTF("Por eso vete, olvida mi nombre, mi cara, mi casa y pega la vuelta");
                        continuar = false; // Finaliza la conversacion
                    }else {
                        out.writeUTF("Error");
                    }

                }

                System.out.println("Cliente desconectado");
                socketCliente.close();

            }

        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }

    }
}
