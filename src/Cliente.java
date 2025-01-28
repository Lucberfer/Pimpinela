import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        String HOST = "127.0.0.1"; // Dirección local
        int PUERTO = 5000; // Puerto del servidor

        DataInputStream in;
        DataOutputStream out;

        Scanner scanner = new Scanner(System.in);

        try {

            // Connectar al servidor
            Socket socket = new Socket(HOST, PUERTO);

            // Flujo de entrada y salida
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            boolean continuar = true;

            while (continuar){

                // Leer mensaje desde la consola
                System.out.println("Cliente: ");
                String mensajeCliente = scanner.nextLine();
                out.writeUTF(mensajeCliente); // Enviar mensaje al servidor

                // Leer respuesta del servidor
                String respuestaServidor = in.readUTF();
                System.out.println("Servidor: " + respuestaServidor);

                // Verificar si termina la conversación
                if("Por eso vete, olvida mi nombre, mi cara, mi casa y pega la vuelta".equals(respuestaServidor)){

                    continuar = false; // Terminar conversación

                }
            }

            // Cerrar conexión
            System.out.println("Conexíon cerrada");
            socket.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
