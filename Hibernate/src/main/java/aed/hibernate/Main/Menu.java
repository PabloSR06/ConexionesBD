package aed.hibernate.Main;
import java.util.Scanner;
import org.hibernate.Session;
import aed.hibernate.Clases.HibernateUtil;
import aed.hibernate.Query.*;


//PABLO SUAREZ ROMERO

public class Menu {
	public static void main(String[] args) {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		InitialInsert.inicio(sesion);
		Menu_entrada(sesion);
	}

	public static void Menu_entrada(Session sesion) {

		Scanner in = new Scanner(System.in);

		int entrada = 0;

		System.out.println("\n// Pablo Suarez Romero //");
		System.out.println("// Menu //");

		System.out.println("\nEscribe uno de los siguientes numeros");
		System.out.println("////////////////");
		System.out.println("#Consulta: 1");
		System.out.println("#Insertar: 2");
		System.out.println("#Eliminar: 3");
		System.out.println("#Actualizar: 4");
		System.out.println("#Salir: 5");
		System.out.println("////////////////");

		System.out.print("Numero: ");
		entrada = in.nextInt();

		switch (entrada) {
		case 1:
			Menu_consulta(sesion);
			break;
		case 2:
			Menu_insertar();

			break;
		case 3:
			Eliminar.eliminar_entrada(sesion);

			break;
		case 4:
			Update.update_habitacion_entrada(sesion);
			break;
		case 5:
			System.out.println("Saliendo");

			cerrar_session(sesion);
			break;

		default:
			in.close();
			break;
		}

	}

	public static void Menu_consulta(Session sesion) {

		Scanner in = new Scanner(System.in);
		int entrada = 0;

		System.out.println("\n// Pablo Suarez Romero //");
		System.out.println("// Menu //");

		System.out.println("\nEscribe uno de los siguientes numeros");
		System.out.println("////////////////");
		System.out.println("#Consulta 1: 1");
		System.out.println("#Consulta 2: 2");
		System.out.println("#Salir: 3");
		System.out.println("////////////////");

		System.out.print("Numero: ");
		entrada = in.nextInt();

		switch (entrada) {
		case 1:
			Consultas.consulta_1(sesion);
			Menu_entrada(sesion);
			break;
		case 2:
			Consultas.consulta_2(sesion);
			Menu_entrada(sesion);
			break;
		
		case 3:
			System.out.println("Saliendo");
			Menu_entrada(sesion);
			break;

		default:
			in.close();
			break;
		}

	}
	
	

	public static void Menu_insertar() {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Scanner in = new Scanner(System.in);

		int entrada = 0;

		System.out.println("\n// Pablo Suarez Romero //");
		System.out.println("// Menu Insertar //");

		System.out.println("\nEscribe uno de los siguientes numeros");
		System.out.println("////////////////");
		System.out.println("#Insertar Habitacion: 1");
		System.out.println("#Insertar observaciones: 2");
		System.out.println("#Insertar hotel: 3");
		System.out.println("#Insertar estancia: 4");
		System.out.println("#Insertar cliente: 5");
		System.out.println("#Insertar regimen: 65");
		System.out.println("#Volver: 7");
		System.out.println("////////////////");

		System.out.print("Numero: ");
		entrada = in.nextInt();

		switch (entrada) {
		case 1:
			Insertar.insertar_habitaciones_entrada(sesion);
			in.close();
			break;
		case 2:
			Insertar.insertar_observaciones_entrada(sesion);
			in.close();
			break;
		case 3:
			Insertar.insertar_hoteles_entrada(sesion);
			in.close();
			break;
		case 4:
			Insertar.insertar_estancias_entrada(sesion);
			in.close();
			break;
		case 5:
			Insertar.insertar_clientes_entrada(sesion);
			in.close();
			break;
		case 6:
			Insertar.insertar_regimenes_entrada(sesion);
			in.close();
			break;
		case 7:
			System.out.println("Saliendo");
			Menu_entrada(sesion);
			break;

		default:
			break;
		}

	}

	public static void cerrar_session(Session sesion) {
		sesion.close();
	}

}
