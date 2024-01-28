import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Recibe {

    private static final String ARCHIVO = "memoria_compartida.txt";
    private static final Semaphore semaforo = new Semaphore(1);

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java Recibe <destinatario>");
            System.exit(1);
        }

        String destinatario = args[0];

        try {
            semaforo.acquire();

            String mensajesPendientes = obtenerMensajesPendientes(destinatario);

            if (mensajesPendientes.isEmpty()) {
                System.out.println("No hay mensajes pendientes para " + destinatario + ".");
            } else {
                System.out.println("Mensajes pendientes para " + destinatario + ":");
                System.out.println(mensajesPendientes);

                // Eliminar mensajes pendientes después de leerlos
                limpiarMensajesPendientes(destinatario);
                System.out.println("Mensajes leídos y archivo actualizado. Estado=1");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al trabajar con el archivo " + ARCHIVO);
            e.printStackTrace();
        } finally {
            semaforo.release();
        }
    }

    private static String obtenerMensajesPendientes(String destinatario) throws IOException {
        StringBuilder mensajes = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith(destinatario + ": ")) {
                    mensajes.append(line).append("\n");
                }
            }
        }

        return mensajes.toString();
    }

    private static void limpiarMensajesPendientes(String destinatario) throws IOException {
        File archivoOriginal = new File(ARCHIVO);

        File archivoTemporal = new File(ARCHIVO + ".temp");

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTemporal))) {

            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(destinatario + ": ")) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }

        if (archivoOriginal.delete()) {
            if (archivoTemporal.renameTo(archivoOriginal)) {
                System.out.println("Mensajes leídos y archivo actualizado. Estado=1");
            } else {
                System.out.println("Error al renombrar el archivo temporal.");
            }
        } else {
            System.out.println("Error al eliminar el archivo original.");
        }
    }
}
