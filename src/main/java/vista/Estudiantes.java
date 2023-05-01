package vista;

import java.util.Scanner;
import static vista.Menues.cLea;
import static vista.Menues.nLea;
import static vista.Menues.opc;
import static vista.Menues.opx;

public class Estudiantes {
    
    public void estudiante(){
        boolean salir = false;
        while (!salir) {
            nLea = new Scanner(System.in);
            cLea = new Scanner(System.in);
            System.out.println("\t\t\tRegistro Academico");
            System.out.println("Crear Estudiantes");
            System.out.println("""
                               1. Ingresar Nombre de Estudiante
                               2. Ingresar ID de Estudiante
                               3. Ingresar Carrera de Estudiante
                               4. Ingresar Numero de Contacto del Estudiante
                               5. Volver al menu de Gestion de Estudiantes
                               6. Volver al menu dprincipal
                               7. Salir""");
            System.out.println("Digite Opcion:");
            opc = nLea.nextInt();
            
            switch(opc){
                case 1 -> System.out.println("Opcion 1");
                case 2 -> System.out.println("Opcion 2");
                case 5 -> salir = true;
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
