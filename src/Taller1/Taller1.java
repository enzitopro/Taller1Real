package Taller1;

// Integrante 1: Elliot Bravo de Rodt - enzitopro
// Integrante 2: Enzo Salvatore Cornieles Medina -
// Link repositorio: https://github.com/enzitopro/Taller1Real

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
    public static void cargarUsuarios() {
		try {
			File archivo = new File("Usuarios.txt");
			Scanner lectorArchivo = new Scanner(archivo);
			
			while (lectorArchivo.hasNextLine()) {
				String linea = lectorArchivo.nextLine();
				String[] partes = linea.split(";");
				
				idUsuarios[totalUsuarios] = partes[0];
				passUsuarios[totalUsuarios] = partes[1];
				
				totalUsuarios++;
			}
			lectorArchivo.close();
			// System.out.println(totalUsuarios + " usuarios cargados");
		} catch (FileNotFoundException e) {
			System.out.println("Archivo de usuarios no encontrado.");
		}
	}
    public static void cargarRegistros() {
		try {
			File archivoReg = new File("Registros.txt");
			Scanner lectorArchivoReg = new Scanner(archivoReg);
			
			while (lectorArchivoReg.hasNextLine()) {
				String linea = lectorArchivoReg.nextLine();
				String[] partes = linea.split(";");
				
				regUsuarios[totalRegistros] = partes[0];
				regFechas[totalRegistros] = partes[1];
				regHoras[totalRegistros] = Integer.valueOf(partes[2]);
				regActividades[totalRegistros] = partes[3];
				
				totalRegistros++;
			}
			lectorArchivoReg.close();
		} catch (FileNotFoundException e) {
			System.out.println("Archivo de registros no encontrado.");
		}
	}
    
    public static void menuUsuarios(Scanner lector) {
		System.out.print("Usuario: ");
		String usuarioIngresado = lector.nextLine();
		
		System.out.print("Contraseña: ");
		String passIngresada = lector.nextLine();
		
		int indiceUser = -1;
		
		for (int i = 0; i < totalUsuarios; i++) {
			if (idUsuarios[i].equals(usuarioIngresado)) {
				indiceUser = i;
				break;
			}
		}
		if (indiceUser == -1) {
			System.out.println("Usuario no encontrado.");
		} else {
			if (passUsuarios[indiceUser].equals(passIngresada)) {
				System.out.println("Acceso correcto.");
				
				subMenuActividades(lector, indiceUser);
			} else {
				System.out.println("Contraseña incorrecta.");
			}
		}
	}
}
