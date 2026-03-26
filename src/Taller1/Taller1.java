package Taller1;

// Integrante 1: Elliot Bravo de Rodt - enzitopro
// Integrante 2: Enzo Salvatore Cornieles Medina - justamago
// Link repositorio: https://github.com/enzitopro/Taller1Real

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;

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
                    	guardarUsuarios();
                    	guardarRegistros();
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
    
    public static void registrarActividad(Scanner lector, int indiceUser) {
        System.out.println("\n--- Registrar nueva actividad ---");
        if (totalRegistros >= 300) {
        	System.out.println("Se ha alcanzado el límite de actividades a registrar.");
        	System.out.println("No se pueden agregar más actividades");
        	return;
        }
        
        System.out.println("Ingrese la fecha de la actividad (ej. 04/04/2026): ");
        String fecha = lector.nextLine();
        
        int horas = 0;
        boolean horaValida = false;
        while (!horaValida) {
        	System.out.println("Ingrese por cuanto tiempo se realizó la actividad: ");
        	try {
        		horas = Integer.valueOf(lector.nextLine());
        		if (horas > 0) {
        			horaValida = true;
        		} else {
        			System.out.println("Las horas deben ser mayores a 0.");
        		}
        	} catch (NumberFormatException e) {
        		System.out.println("Por favor, ingrese un número entero.");
        	}
        }
        
        System.out.println("Ingrese el nombre de la actividad (ej. ver steel ball run): ");
        String actividad = lector.nextLine();
        
        String nombreUsuario = idUsuarios[indiceUser];
        regUsuarios[totalRegistros] = nombreUsuario;
        regFechas[totalRegistros] = fecha;
        regHoras[totalRegistros] = horas;
        regActividades[totalRegistros] = actividad;
        
        totalRegistros++;
        System.out.println("Actividad registrada con éxito!");
    }
    
    public static void modificarActividad(Scanner lector, int indiceUser) {
        System.out.println("\n--- Modificar actividad ---");
        String nombreUsuario = idUsuarios[indiceUser];
        boolean tieneActividades = false;
        
        for (int i=0; i<totalRegistros; i++) {
        	if (regUsuarios[i].equals(nombreUsuario)) {
        		System.out.println("ID:"+i+" | "+regFechas[i]+" | "+regHoras[i]+"h | "+regActividades[i]);
        		tieneActividades = true;
        	}
        }
        if (!tieneActividades) {
        	System.out.println("No tienes actividades registradas");
        }
    }
    
    public static void eliminarActividad(Scanner lector, int indiceUser) {
        System.out.println("Función en construcción...");
    }
    
    public static void cambiarPassword(Scanner lector, int indiceUser) {
        System.out.println("Función en construcción...");
    }
    
    public static void menuAnalisis(Scanner lector) {
        int opcion2 = 0;
        do {
            try {
                System.out.println("\n--- Bienvenido al Menú de Análisis! ---");
                System.out.println("Que deseas realizar?");
                System.out.println("1) Actividad más realizada");
                System.out.println("2) Actividad más realizada por cada usuario");
                System.out.println("3) Usuario con mayor procastinacion");
                System.out.println("4) Ver todas las actividades");
                System.out.println("5) Salir");
                System.out.print("Seleccione una opción: ");
                opcion2 = Integer.valueOf(lector.nextLine());
                
                switch(opcion2) {
                    case 1:
                        actividadMasRealizada(lector);
                        break;
                    case 2:
                        actividadMasRealizadaPorUsuario(lector);
                        break;
                    case 3:
                        mayorProcrastinacion(lector);
                        break;
                    case 4:
                        todasLasActividades(lector);
                        break;
                    case 5:
                        System.out.println("Saliendo del sistema de Analisis...");
                        break;
                    default:
                        System.out.println("Número invalido, intentelo nuevamente.");
                }
            } catch(Exception e) {
                System.out.println("Lo que se ingreso no fue un número, intentelo nuevamente");
                opcion2 = 0;
            }
        } while (opcion2 != 5);
    }
    
    public static void actividadMasRealizada(Scanner lector) {
        if (totalRegistros == 0) {
            System.out.println("No se encontraron registros.");
            return;
        }
        String maxUsuario = "";
        String maxActividad = "";
        int maxHoras = 0;
        
        for (int i = 0; i < totalRegistros; i++) {
            String usuario = regUsuarios[i];
            String actividad = regActividades[i];
            int sumaHoras = 0;
            
            for (int j = 0; j < totalRegistros; j++) {
                if (regActividades[j].equals(actividad)) {
                    sumaHoras += regHoras[j];
                }
            }
            if (sumaHoras > maxHoras) {
                maxUsuario = usuario;
                maxHoras = sumaHoras;
                maxActividad = actividad;
            }
        }
        System.out.println("La actividad más repetida es de: " + maxUsuario + ", haciendo la actividad de: " +  maxActividad + " en un total de " + maxHoras + " horas.");
    }
    public static void actividadMasRealizadaPorUsuario(Scanner lector) {
        if (totalRegistros == 0) {
            System.out.println("No se encontraron registros.");
            return;
        }
        String usuario1 = "Martin";
        String usuario2 = "Catalina";
        String usuario3 = "Estefania";
        String maxActividad1 = "";
        String maxActividad2 = "";
        String maxActividad3 = "";
        int maxHoras1 = 0;
        int maxHoras2 = 0;
        int maxHoras3 = 0;
        
        for (int i = 0; i < totalRegistros; i++) {
            if (regUsuarios[i].equals("Martin")) {
                String actividad1 = regActividades[i];
                int sumaHoras1 = 0;
                for (int j = 0; j < totalRegistros; j++) {
                    if (regUsuarios[j].equals("Martin") && regActividades[j].equals(actividad1)) {
                        sumaHoras1 += regHoras[j];
                    }
                }
                if (sumaHoras1 > maxHoras1) {
                    maxHoras1 = sumaHoras1;
                    maxActividad1 = actividad1;
                }
            }
            if (regUsuarios[i].equals("Catalina")) {
                String actividad2 = regActividades[i];
                int sumaHoras2 = 0;
                for (int j = 0; j < totalRegistros; j++) {
                    if (regUsuarios[j].equals("Catalina") && regActividades[j].equals(actividad2)) {
                        sumaHoras2 += regHoras[j];
                    }
                }
                if (sumaHoras2 > maxHoras2) {
                    maxHoras2 = sumaHoras2;
                    maxActividad2 = actividad2;
                }
            }
             if (regUsuarios[i].equals("Estefania")) {
                String actividad3 = regActividades[i];
                int sumaHoras3 = 0;
                for (int j = 0; j < totalRegistros; j++) {
                    if (regUsuarios[j].equals("Estefania") && regActividades[j].equals(actividad3)) {
                        sumaHoras3 += regHoras[j];
                    }
                }
                if (sumaHoras3 > maxHoras3) {
                    maxHoras3 = sumaHoras3;
                    maxActividad3 = actividad3;
                }
            }
        } 
        System.out.println("\nActividades mas realizadas por cada usuario:");
        System.out.println("* " + usuario1 + " -> " + maxActividad1 + " -> con " + maxHoras1 + " horas registradas.");
        System.out.println("* " + usuario2 + " -> " + maxActividad2 + " -> con " + maxHoras2 + " horas registradas.");
        System.out.println("* " + usuario3 + " -> " + maxActividad3 + " -> con " + maxHoras3 + " horas registradas.");
    }
    public static void mayorProcrastinacion(Scanner lector) {
        if (totalRegistros == 0) {
            System.out.println("No se encontraron registros.");
            return;
        }
        
        String maxUsuario = "";
        int maxHorasGlobal = 0;
        
        for (int u = 0; u < totalUsuarios; u++) {
            String usuarioActual = idUsuarios[u];
            int horasDelUsuario = 0;
            
            for (int i = 0; i < totalRegistros; i++) {
                if (regUsuarios[i].equals(usuarioActual)) {
                    horasDelUsuario += regHoras[i];
                }
            }
            
            if (horasDelUsuario > maxHorasGlobal) {
                maxHorasGlobal = horasDelUsuario;
                maxUsuario = usuarioActual;
            }
        }
        
        System.out.println("\nEl usuario con mayor procrastinación fue " + maxUsuario + " con " + maxHorasGlobal + " horas.");
    }
    public static void todasLasActividades(Scanner lector) {
        if (totalRegistros == 0) {
            System.out.println("No se encontraron registros.");
            return;
        }
        System.out.println("\nSe encontraron " + totalRegistros + " actividades:");
        for (int i = 0; i < totalRegistros; i++) {
            System.out.println(regUsuarios[i] + " | " + regFechas[i] + " | " + regHoras[i] + " horas | " + regActividades[i]);
        }
    }
    public static void guardarUsuarios() {
    	try {
    		FileWriter escritorUser = new FileWriter("Usuarios.txt");
    		for (int i=0; i<totalUsuarios; i++) {
    			escritorUser.write(idUsuarios[i] + ";" + passUsuarios[i] + "\n");
    		}
    		escritorUser.close();
    		System.out.println("Archivo de usuarios actualizado correctamente.");
    	} catch (Exception e) {
    		System.out.println("Error al intentar guardar los usuarios.");
    	}
    }
    public static void guardarRegistros() {
    	try {
    		FileWriter escritorReg = new FileWriter("Registros.txt");
    		for (int i=0; i<totalRegistros;i++) {
    			escritorReg.write(regUsuarios[i]+";"+regFechas[i]+";"+regHoras[i]+";"+regActividades[i]+"\n");
    		}
    		escritorReg.close();
        	System.out.println("Archivo de registros actualizado correctamente.");
    	} catch (Exception e) {
        	System.out.println("Error al intentar guardar los registros.");
        } 	
    } 
}