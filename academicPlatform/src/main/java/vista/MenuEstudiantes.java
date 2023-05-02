package vista;

import java.util.Scanner;
import static vista.Menues.cLea;
import static vista.Menues.nLea;
import static vista.Menues.opc;
import static vista.Menues.opx;

/**
 *
 * @author Asus
 */
public class MenuEstudiantes {
        public static void MenuEstudiantes() {
        Estudiantes llamada=new Estudiantes();
        boolean salir = false;
        while (!salir) {
            nLea = new Scanner(System.in);
            cLea = new Scanner(System.in);
            System.out.println("\t\t\tRegistro Academico");
            System.out.println("Menu de Gestion de Estudiantes");
            System.out.println("""
                               1. Creacion de Estudiante
                               2. Consulta de Estudiante
                               3. Actualizacion de Estudiante
                               4. Eliminacion de Estudiante
                               5. Listado de Estudiantes
                               6. Volver al menu principal
                               7. Salir""");
            System.out.println("Digite Opcion:");
            opc = nLea.nextInt();
            
            switch(opc){
                case 1 -> llamada.estudiante();
                case 2 -> System.out.println("Opcion 2");
                case 6 -> salir = true;
                case 7 -> {
                    System.out.println("Confirmar salida\n S=Salir/N=Cancelar");
                    opx = cLea.next().charAt(0);
                    if (opx == 'S' || opx=='s') {
                        System.out.println("Ha salido del sistema . . .");
                        System.exit(0);
                        salir = true;
                    }
                }
                default -> System.out.println("Opcion incorrecta . . .");
            }
        }
    }

}
