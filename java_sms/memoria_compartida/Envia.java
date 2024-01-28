import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Envia {

    private static final String ARCHIVO = "memoria_compartida.txt";
    private static final String CARPETA_ESTADISTICAS = "estadisticas";
    private static final String ESTADISTICAS_ARCHIVO = CARPETA_ESTADISTICAS + File.separator + "estadisticas.txt";
    private static final Semaphore mutex = new Semaphore(1, true);

    public static void main(String[] args) {
        if (args.length == 2) {
            String destinatario = args[0];
            String mensaje = args[1];

            try {
                mutex.acquire();

                if (!hayMensajesPendientes(destinatario)) {
                    guardarMensaje(destinatario, mensaje);
                    System.out.println("Mensaje enviado a " + destinatario + ": " + mensaje + ". Estado=0");
                    actualizarEstadisticas(1, 0, 0);
                } else {
                    System.out.println("Ya hay mensajes pendientes para " + destinatario +
                            ". No se puede enviar otro mensaje. Estado=0");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } finally {
                mutex.release();
            }
        } else if (args.length == 1 && args[0].equals("-")) {
            try {
                mostrarEstadisticas();
                liberarRecursos();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Uso: java Envia <destinatario> <mensaje>");
            System.out.println("     java Envia -");
        }
    }

    private static boolean hayMensajesPendientes(String destinatario) throws IOException {
        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo " + ARCHIVO);
                throw e;
            }
        }

        return archivo.exists() && archivo.length() > 0;
    }

    private static void guardarMensaje(String destinatario, String mensaje) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            writer.write(destinatario + ": " + mensaje);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo " + ARCHIVO);
            throw e;
        }
    }

    private static void actualizarEstadisticas(int enviados, int leidos, int noLeidos) throws IOException {
        File carpetaEstadisticas = new File(CARPETA_ESTADISTICAS);
        File estadisticasArchivo = new File(ESTADISTICAS_ARCHIVO);

        if (!carpetaEstadisticas.exists()) {
            carpetaEstadisticas.mkdirs();
        }

        if (!estadisticasArchivo.exists()) {
            try {
                estadisticasArchivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo de estadísticas " + ESTADISTICAS_ARCHIVO);
                throw e;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ESTADISTICAS_ARCHIVO))) {
            writer.write("Mensajes enviados: " + enviados);
            writer.newLine();
            writer.write("Mensajes leídos: " + leidos);
            writer.newLine();
            writer.write("Mensajes no leídos: " + noLeidos);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de estadísticas " + ESTADISTICAS_ARCHIVO);
            throw e;
        }
    }

    private static void mostrarEstadisticas() throws IOException {
        try (Scanner scanner = new Scanner(new File(ESTADISTICAS_ARCHIVO))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de estadísticas " + ESTADISTICAS_ARCHIVO);
            throw e;
        }
    }

    private static void liberarRecursos() throws InterruptedException {
        System.out.println("Recursos liberados.");
    }
}
onf
