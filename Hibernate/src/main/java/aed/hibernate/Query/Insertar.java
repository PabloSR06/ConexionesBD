package aed.hibernate.Query;

import java.util.Scanner;
import org.hibernate.Session;
import aed.hibernate.Clases.*;
import aed.hibernate.Main.Menu;

public class Insertar {

	private static Scanner in = new Scanner(System.in);

	public static void insertar_habitaciones_entrada(Session sesion) {
		int error = 0;
		System.out.println("\n// Pablo Suarez Romero //");
		System.out.println("// Habitaciones //");

		System.out.println("CodHotel: ");
		String CodHotel = in.next();

		System.out.println("numHabitacion: ");
		String numHabitacion = in.next();

		System.out.println("capacidad: ");
		int capacidad = in.nextInt();

		System.out.println("preciodia: ");
		int preciodia = in.nextInt();

		System.out.println("activa: ");
		int activa = in.nextInt();

		if (Chekers.hay_hotel(sesion, CodHotel) == false) {
			System.err.println("El hotel no existe");
			error++;
		}
		if (Chekers.checkActiva(activa) == false) {
			System.err.println("Activa solo puede ser 0 o 1");
			error++;
		}

		if (error == 0) {
			insertar_habitaciones(sesion, numHabitacion, capacidad, preciodia, activa, CodHotel);
		} else {
			Chekers.espera(5);
			Menu.Menu_entrada(sesion);
		}

	}

	public static void insertar_habitaciones(Session sesion, String numHabitacion, int capacidad, int preciodia,
			int activa, String CodHotel) {
		try {
			sesion.beginTransaction();

			hoteles claveHotel = sesion.get(hoteles.class, CodHotel);

			habitaciones p1 = new habitaciones();

			p1.setCodHotel(claveHotel);
			p1.setNumHabitacion(numHabitacion);
			p1.setCapacidad(capacidad);
			p1.setPreciodia(preciodia);
			p1.setActiva(activa);

			sesion.save(p1);
			sesion.getTransaction().commit();
			System.out.println("Insertado!");
		} catch (Exception e) {
			System.err.println("No se ha podido insertar la habitacion");
		}
		Chekers.espera(4);
		Menu.Menu_entrada(sesion);

	}

	public static void insertar_hoteles_entrada(Session sesion) {
		int error = 0;

		System.out.println("\n// Hoteles //");

		System.out.println("CodHotel: ");
		String CodHotel = in.next();

		System.out.println("nomHotel: ");
		String nomHotel = in.next();

		if (Chekers.hay_hotel(sesion, CodHotel) == true) {
			System.err.println("Ya hay un codHotel");
			error++;
		}

		if (error == 0) {
			insertar_hoteles(sesion, CodHotel, nomHotel);
		} else {
			Chekers.espera(3);
			Menu.Menu_entrada(sesion);
		}

	}

	public static void insertar_hoteles(Session sesion, String CodHotel, String nomHotel) {

		try {
			sesion.beginTransaction();

			hoteles p1 = new hoteles();
			p1.setCodHotel(CodHotel);
			p1.setNomHotel(nomHotel);

			sesion.save(p1);
			sesion.getTransaction().commit();

			Consultas.consulta_hoteles(sesion, CodHotel);

		} catch (Exception e) {
			System.err.println("No se ha podido insertar el hotel");
		}

		Chekers.espera(2);
		Menu.Menu_entrada(sesion);
	}

	public static void insertar_estancias_entrada(Session sesion) {
		int error = 0;
		System.out.println("\n// Estancias //");

		System.out.println("codestancia: ");
		int codestancia = in.nextInt();

		System.out.println("fechaFin: (AAAA-MM-DD)");
		String fechaFin = in.next();

		System.out.println("fechaInicio: (AAAA-MM-DD)");
		String fechaInicio = in.next();

		System.out.println("ocupantes: ");
		int ocupantes = in.nextInt();

		System.out.println("pagado: ");
		int pagado = in.nextInt();

		System.out.println("precioestancia: ");
		int precioestancia = in.nextInt();

		System.out.println("codDNIoNIE: ");
		String codDNIoNIE = in.next();

		System.out.println("codhabitacion: ");
		int codhabitacion = in.nextInt();

		System.out.println("codregimen: ");
		int codregimen = in.nextInt();

		// TODO COMPROBAR DATOS
		if (Chekers.hay_cliente(sesion, codDNIoNIE) == false) {
			System.err.println("No existe el cliente");
			error++;
		}
		if (Chekers.hay_habitacion(sesion, codhabitacion) == false) {
			System.err.println("No existe habitacion");
			error++;
		}

		if (error == 0) {
			insertar_estancias(sesion, codestancia, fechaFin, fechaInicio, ocupantes, pagado, precioestancia,
					codDNIoNIE, codhabitacion, codregimen);
		} else {
			Chekers.espera(3);
			Menu.Menu_entrada(sesion);
		}

	}

	public static void insertar_estancias(Session sesion, int codestancia, String fechaFin, String fechaInicio,
			int ocupantes, int pagado, int precioestancia, String codDNIoNIE, int codhabitacion, int codregimen) {

		try {
			sesion.beginTransaction();

			estancias p1 = new estancias();
			p1.setCodestancia(codestancia);
			p1.setFechaFIN(fechaFin);
			p1.setFechaInicio(fechaInicio);
			p1.setOcupantes(ocupantes);
			p1.setPrecioestancia(precioestancia);

			clientes cliente = sesion.get(clientes.class, codDNIoNIE);
			p1.setCodDNIoNIE(cliente);
			habitaciones habitacion = sesion.get(habitaciones.class, codhabitacion);
			p1.setCodhabitacion(habitacion);
			regimenes regimen = sesion.get(regimenes.class, codregimen);
			p1.setCodregimen(regimen);

			sesion.save(p1);
			sesion.getTransaction().commit();

			Consultas.consulta_estancias(sesion, codestancia);

		} catch (Exception e) {
			System.err.println("No se ha podido insertar el estancias");
		}

		Chekers.espera(5);
		Menu.Menu_entrada(sesion);
	}

	public static void insertar_clientes_entrada(Session sesion) {

		int error = 0;
		System.out.println("\n// Clientes //");

		System.out.println("codDNIoNIE: ");
		String codDNIoNIE = in.next();

		System.out.println("nacionalidad: ");
		String nacionalidad = in.next();

		System.out.println("nombre: ");
		String nombre = in.next();

		if (Chekers.hay_cliente(sesion, codDNIoNIE) == true) {
			System.err.println("codDNIoNIE ya existe");
			error++;
		}

		if (error == 0) {
			insertar_clientes(sesion, codDNIoNIE, nacionalidad, nombre);
		} else {
			Chekers.espera(3);
			Menu.Menu_entrada(sesion);
		}

	}

	public static void insertar_clientes(Session sesion, String codDNIoNIE, String nacionalidad, String nombre) {

		try {
			sesion.beginTransaction();

			clientes p1 = new clientes();
			p1.setCodDNIoNIE(codDNIoNIE);
			p1.setNacionalidad(nacionalidad);
			p1.setNombre(nombre);

			sesion.save(p1);
			sesion.getTransaction().commit();

			Consultas.consulta_cliente(sesion, codDNIoNIE);

		} catch (Exception e) {
			System.err.println("No se ha podido insertar el clientes");
		}

		Chekers.espera(3);
		Menu.Menu_entrada(sesion);
	}

	public static void insertar_regimenes_entrada(Session sesion) {

		int error = 0;
		System.out.println("\n// Estancias //");

		System.out.println("codregimen: ");
		int codregimen = in.nextInt();

		System.out.println("codHotel: ");
		String codHotel = in.next();

		System.out.println("tipo: ");
		String tipo = in.next();

		System.out.println("preciodia: ");
		int preciodia = in.nextInt();

		if (Chekers.hay_regimen(sesion, codregimen) == true) {
			System.err.println("Ya existe ese codregimen");
			error++;
		}
		if (Chekers.hay_hotel(sesion, codHotel) == false) {
			System.err.println("No existe ese hotel");
			error++;
		}

		if (error == 0) {
			insertar_regimenes(sesion, codregimen, codHotel, tipo, preciodia);
		} else {
			Chekers.espera(3);
			Menu.Menu_entrada(sesion);
		}

	}

	public static void insertar_regimenes(Session sesion, int codregimen, String codHotel, String tipo, int preciodia) {

		try {
			sesion.beginTransaction();

			regimenes p1 = new regimenes();
			p1.setCodregimen(codregimen);
			hoteles hotel = sesion.get(hoteles.class, codHotel);
			p1.setCodHotel(hotel);
			p1.setTipo(tipo);
			p1.setPreciodia(preciodia);

			sesion.save(p1);
			sesion.getTransaction().commit();

			Consultas.consulta_regimenes(sesion, codregimen);

		} catch (Exception e) {
			System.err.println("No se ha podido insertar el regimen");
		}

		Chekers.espera(3);
		Menu.Menu_entrada(sesion);
	}

	public static void insertar_observaciones_entrada(Session sesion) {
		int error = 0;

		System.out.println("\n// Hoteles //");

		System.out.println("CodHabitacion: ");
		int codHabitacion = in.nextInt();

		System.out.println("observacion: ");
		String observacion = in.next();

		if (Chekers.hay_habitacion(sesion, codHabitacion) == false) {
			System.err.println("No hay habitacion");
			error++;
		}
		if (Chekers.hay_observacion(sesion, codHabitacion) == true) {
			System.err.println("Ya hay observaciones");
			error++;
		}

		if (error == 0) {
			insertar_observaciones(sesion, codHabitacion, observacion);
		} else {
			Chekers.espera(3);
			Menu.Menu_entrada(sesion);
		}

	}

	public static void insertar_observaciones(Session sesion,int codHabitacion, String observacion) {

		try {
			sesion.beginTransaction();

			habitacionesobservaciones p1 = new habitacionesobservaciones();

			habitaciones claveHotel = sesion.get(habitaciones.class, codHabitacion);

			p1.setCodHabitacionX(claveHotel);
			p1.setObservaciones(observacion);
			sesion.save(p1);
			sesion.getTransaction().commit();

			Consultas.consulta_observacion(sesion, codHabitacion);

		} catch (Exception e) {
			System.err.println("No se ha podido insertar la observacion");
		}

		Chekers.espera(2);
		Menu.Menu_entrada(sesion);
	}

}
