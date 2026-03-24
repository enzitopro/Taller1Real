package Taller1;

// Integrante 1: Elliot Bravo de Rodt - enzitopro
// Integrante 2: Enzo Salvatore Cornieles Medina - justamago
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
        int opcion1 = 0;
        
        do {
            try {
                System.out.println("\n=== Menú Principal ===");
                System.out.println("1) Menú de Usuarios");
                System.out.println("2) Menú de Análisis");
                System.out.println("3) Salir");
                System.out.print("Seleccione una opción: ");
                
                opcion1 = Integer.valueOf(lector.nextLine());
                
                switch(opcion1) {
                    case 1:
                        menuUsuarios(lector);
                        break;
                    case 2:
                        menuAnalisis(lector);
                        break;
                    case 3:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Número invalido, intentelo nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("Lo que se ingreso no fue un número, intentelo nuevamente");
                opcion1 = 0;
            }
        } while (opcion1 != 3);
        
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
		if (totalUsuarios == 0) {
			System.out.println("No hay usuarios registrados.");
			return;
		}

        System.out.print("Usuario: ");
        String usuario = lector.nextLine();
        int validador = -1;
        
        for (int i = 0; i < totalUsuarios; i++) {
            if (idUsuarios[i].equals(usuario)) {
                validador = i;
                break;
            }
        }
        if (validador == -1) {
            System.out.println("Usuario no encontrado");
            return;
        }
        
        System.out.print("Contraseña: ");
        String contrasena = lector.nextLine();
        
        if (!passUsuarios[validador].equals(contrasena)) {
            System.out.println("Contraseña incorrecta");
            return;
        }
        
        System.out.println("Acceso correcto!");
        System.out.println("Bienvenido " + usuario + "!");
        
        subMenuActividades(lector, validador);
    }

    public static void subMenuActividades(Scanner lector, int indiceUser) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- Que deseas realizar? ---");
            System.out.println("1) Registrar actividad");
            System.out.println("2) Modificar actividad");
            System.out.println("3) Eliminar actividad");
            System.out.println("4) Cambiar contraseña");
            System.out.println("5) Salir.");
            System.out.print("> ");
            
            try {
                int opcion = Integer.valueOf(lector.nextLine());
                switch (opcion) {
                    case 1:
                        registrarActividad(lector, indiceUser);
                        break;
                    case 2:
                        modificarActividad(lector, indiceUser);
                        break;
                    case 3:
                        eliminarActividad(lector, indiceUser);
                        break;
                    case 4:
                        cambiarPassword(lector, indiceUser);
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion inválida");
                }
            } catch (Exception e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }
}
