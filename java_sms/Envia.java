import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Envia {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java Envia <destinatario> <mensaje>");
        } else {
            String destinatario = args[0];
            String mensaje = args[1];
            enviarMensaje(destinatario, mensaje);
        }
    }

    private static void enviarMensaje(String destinatario, String mensaje) {
        try {
            // Cargar los mensajes existentes desde el archivo
            Map<String, String[]> mensajes = cargarMensajes();

            // Agregar el nuevo mensaje al destinatario
            if (!mensajes.containsKey(destinatario)) {
                mensajes.put(destinatario, new String[]{mensaje});
            } else {
                String[] mensajesAnteriores = mensajes.get(destinatario);
                String[] nuevosMensajes = new String[mensajesAnteriores.length + 1];
                System.arraycopy(mensajesAnteriores, 0, nuevosMensajes, 0, mensajesAnteriores.length);
                nuevosMensajes[mensajesAnteriores.length] = mensaje;
                mensajes.put(destinatario, nuevosMensajes);
            }

            // Guardar los mensajes actualizados en el archivo
            guardarMensajes(mensajes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String[]> cargarMensajes() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("mensajes.txt"))) {
            Map<String, String[]> mensajes = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String destinatario = parts[0];
                String[] mensajesArray = parts[1].split(",");
                mensajes.put(destinatario, mensajesArray);
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
                writer.write(destinatario + ":");
                writer.write(String.join(",", mensajesArray));
                writer.newLine();
            }
        }
    }
}
