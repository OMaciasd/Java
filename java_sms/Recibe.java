import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Recibe {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java Recibe <destinatario>");
        } else {
            String destinatario = args[0];
            recibirMensajes(destinatario);
        }
    }

    private static void recibirMensajes(String destinatario) {
        try {
            // Cargar los mensajes existentes desde el archivo
            Map<String, String[]> mensajes = cargarMensajes();

            if (mensajes.containsKey(destinatario)) {
                // Mostrar los mensajes pendientes para el destinatario
                for (String mensaje : mensajes.get(destinatario)) {
                    System.out.println(mensaje);
                }

                // Eliminar los mensajes para que no se lean la próxima vez
                mensajes.remove(destinatario);

                // Guardar los mensajes actualizados en el archivo
                guardarMensajes(mensajes);
            } else {
                System.out.println("No hay mensajes para " + destinatario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String[]> cargarMensajes() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("mensajes.txt"))) {
            Map<String, String[]> mensajes = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                String destinatario = parts[0];
                String mensaje = parts[1];
                mensajes.putIfAbsent(destinatario, new String[]{mensaje});
            }
            return mensajes;
        } catch (FileNotFoundException e) {
            return new HashMap<>();
        }
    }

    private static void guardarMensajes(Map<String, String[]> mensajes) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("mensajes.txt"))) {
            for (Map.Entry<String, String[]> entry : mensajes.entrySet()) {
                String destinatario = entry.getKey();
                String[] mensajesArray = entry.getValue();
                for (String mensaje : mensajesArray) {
                    writer.write(destinatario + ":" + mensaje);
                    writer.newLine();
                }
            }
        }
    }
}
