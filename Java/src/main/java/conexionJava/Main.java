package conexionJava;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		elegirServidor(args);
	}

	public static void menu(String[] args) {
		System.out.println("\nPablo Suarez Romero");
		System.out.println("Menu\n");

		Scanner in = new Scanner(System.in);

		int entrada = 0;
		System.out.println("Escribe uno de los siguientes numeros");
		System.out.println("////////////////");
		System.out.println("#Consulta: 1");
		System.out.println("#Modificar: 2");
		System.out.println("#Insertar: 3");
		System.out.println("#Eliminar: 4");
		System.out.println("#Procedimientos: 5");
		System.out.println("#Cambiar Conexion: 6");
		System.out.println("#Salir: 7");
		System.out.println("////////////////");

		System.out.print("Numero: ");
		entrada = in.nextInt();

		switch (entrada) {
		case 1:
			Consulta.main(args);
			in.close();
			break;
		case 2:
			Modificar.modificarEntradaHotel(args);
			in.close();
			break;
		case 3:
			Insertar.main(args);
			in.close();
			break;
		case 4:
			Eliminar.main(args);
			in.close();
			break;
		case 5:
			if (ConectionDB.leerFichero()!=3) {
				procedimientosMenu(args);
				in.close();
			}else {
				System.out.println("No puedes utilizar funciones con Access");
				Checkers.espera(3);
				goMenu(1, args);
			}
			
			break;
		case 6:
			elegirServidor(args);
			break;
		case 7:
			System.out.println("Saliendo");
			break;

		default:
			break;
		}
	}

	public static void goMenu(int entrada, String[] args) {
		if (entrada == 1) {
			Main.menu(args);
		} else {

		}
	}

	public static void elegirServidor(String[] args) {
		System.out.println("\nPablo Suarez Romero");
		System.out.println("Menu\n");

		Scanner in = new Scanner(System.in);

		int entrada = 0;
		System.out.println("Escribe uno de los siguientes numeros");
		System.out.println("////////////////");
		System.out.println("#Mysql: 1");
		System.out.println("#SQLServer: 2");
		System.out.println("#Access: 3");
		System.out.println("#Salir: 4");
		System.out.println("////////////////");

		System.out.print("Numero: ");
		entrada = in.nextInt();

		switch (entrada) {
		case 1:
			ConectionDB.escribirFichero(1);
			break;
		case 2:
			ConectionDB.escribirFichero(2);
			break;
		case 3:
			ConectionDB.escribirFichero(3);
			break;
		case 4:
			break;

		default:
			break;
		}
		menu(args);
	}

	public static void procedimientosMenu(String[] args) {
		System.out.println("\nPablo Suarez Romero");
		System.out.println("Menu\n");

		Scanner in = new Scanner(System.in);

		int entrada = 0;
		System.out.println("Escribe uno de los siguientes numeros");
		System.out.println("////////////////");
		System.out.println("#Procedimiento 1: 1");
		System.out.println("#Procedimiento 2: 2");
		System.out.println("#Procedimiento 3: 3");
		System.out.println("#Funcion 1: 4");
		System.out.println("#Menu 5: 5");
		System.out.println("////////////////");

		System.out.print("Numero: ");
		entrada = in.nextInt();

		switch (entrada) {
		case 1:
			Procedimientos.procedimiento1Entrada(args);
			in.close();
			break;
		case 2:
			Procedimientos.procedimiento2Entrada(args);
			in.close();
			break;
		case 3:
			Procedimientos.procedimiento3Entrada(args);
			in.close();
			break;
		case 4:
			Procedimientos.funcion1Entrada(args);
			in.close();
			break;
		case 5:
			goMenu(1, args);
			in.close();
			break;

		default:
			break;
		}
	}

}
