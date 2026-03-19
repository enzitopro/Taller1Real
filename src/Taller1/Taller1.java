package Taller1;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Taller1 {
        // para cargar usuarios
	static String[] idUsuarios = new String[300];
	static String[] passUsuarios = new String[300];
	static int totalUsuarios = 	0;
	
	// para cargar registros
	static String[] regUsuarios = new String[300];
	static String[] regFechas = new String[300];
	static int[] regHoras = new int[300];
	static String[] regActividades = new String[300];
	static int totalRegistros = 0;

    public static void main(String[] args) {
		cargarUsuarios();
		cargarRegistros();
		
		Scanner lector = new Scanner(System.in);
		boolean salir = false;
		
		while (!salir) {
			System.out.println("1) Menu de Usuarios");
			System.out.println("2) Menu de Analisis");
			System.out.println("3) Menu de Salir");
			System.out.println("> ");
			
			try {
				int opcion = Integer.valueOf(lector.nextLine());
				
				switch (opcion) {
				case 1:
					menuUsuarios(lector);
					break;
				case 2:
					menuAnalisis(lector);
					break;
				case 3:
					salir = true;
					System.out.println("Fin del programa");
					break;
				default:
					System.out.println("Opcion inválida");
				}
			} catch (NumberFormatException e) {
				System.out.println("Ingrese un número válido");
			}
		}
		lector.close();
	}
}
