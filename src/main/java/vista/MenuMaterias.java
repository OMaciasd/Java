/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.util.Scanner;
import static vista.Menues.cLea;
import static vista.Menues.nLea;
import static vista.Menues.opc;

/**
 *
 * @author Asus
 */
public class MenuMaterias {
        public static void MenuMaterias() {
        boolean salir = false;
        while (!salir) {
            nLea = new Scanner(System.in);
            cLea = new Scanner(System.in);
            System.out.println("\t\t\tRegistro Academico");
            System.out.println("Menu de Gestion de Materias");
            System.out.println("""                         
                               1. Creacion de Materia
                               2. Consulta de Materia
                               3. Actualizacion de Materia
                               4. Eliminacion de Materia
                               5. Listado de Materias
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
