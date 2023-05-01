package vista;

import java.util.Scanner;
import static vista.Menues.cLea;
import static vista.Menues.nLea;
import static vista.Menues.opc;

/**
 *
 * @author Asus
 */
public class MenuCarreras {
        public static void MenuCarreras() {
        boolean salir = false;
        while (!salir) {
            nLea = new Scanner(System.in);
            cLea = new Scanner(System.in);
            System.out.println("\t\t\tRegistro Academico");
            System.out.println("Menu de Gestion de Carreras");
            System.out.println("""                               
                               1. Creacion de Carrera
                               2. Consulta de Carrera
                               3. Actualizacion de Carrera
                               4. Eliminacion de Carrera
                               5. Listado de Carrera
                               6. Volver al menu principal""");
            System.out.println("Digite Opcion:");
            opc = nLea.nextInt();
            
            switch(opc){
                case 1 -> System.out.println("Opcion 1");
                case 2 -> System.out.println("Opcion 2");
                case 6 -> salir = true;
                default -> System.out.println("Opcion incorrecta . . .");
            }
        }
    }
    
}
