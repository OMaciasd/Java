package vista;

import java.util.Scanner;

/**
 * @author Dionny V, Julio A, Fabian R, Juan P, Dayana.
 */

public class Menues {
    
    public static int opc;
    public static char opx;
    public static Scanner nLea, cLea;

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            nLea = new Scanner(System.in);
            cLea = new Scanner(System.in);
            System.out.println("\t\t\tRegistro Academico");
            System.out.println("Menu de Principal");
            System.out.println("""
                               1. Gestion de Estudiantes
                               2. Gestion de Materias
                               3. Gestion de Carreras
                               4. Gestion de Profesores
                               5. Gestion de Registro
                               6. Salir""");
            System.out.println("Digite Opcion:");
            opc = nLea.nextInt();
            
            switch(opc){
                case 1 -> vista.MenuEstudiantes.MenuEstudiantes();
                case 2 -> vista.MenuMaterias.MenuMaterias();
                case 3 -> vista.MenuCarreras.MenuCarreras();
                case 4 -> vista.MenuProfesores.MenuProfesores();
                case 5 -> vista.MenuRegistros.MenuRegistros();
                case 6 -> {
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
