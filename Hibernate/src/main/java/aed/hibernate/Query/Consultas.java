package aed.hibernate.Query;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import aed.hibernate.Clases.*;

//PABLO SUAREZ ROMERO
public class Consultas {
	public static void main(String[] args) {

		Session sesion = HibernateUtil.getSessionFactory().openSession();
		sesion.beginTransaction();

		consulta_1(sesion);

	}

	public static void consulta_1(Session sesion) {
		//try {
			String sql = ("from habitaciones");

			Query q = sesion.createQuery(sql);

			List<habitaciones> habitaciones = q.getResultList();

			System.out.format("| %-13s | %-10s | %-13s | %-8s | %-9s | %-6s | %-20s |%n", "CodHabitacion", "CodHotel",
					"NumHabitacion", "Capacidad", "PrecioDia", "Activa", "Observaciones");
//			for (habitaciones c : habitaciones) {
//				try {
//					if (c.getHabitacionesobservaciones().getObservaciones() == null) {
//
//					} else {
//						System.out.format("| %-13s | %-10s | %-13s | %-9s | %-9s | %-6s | %-20s |%n",
//								c.getCodhabitacion(), c.getCodHotel().getCodHotel(), c.getNumHabitacion(),
//								c.getCapacidad(), c.getPreciodia(), c.getActiva(),
//								c.getHabitacionesobservaciones().getObservaciones());
//					}
//
//				} catch (Exception e) {
//					System.out.format("| %-13s | %-10s | %-13s | %-9s | %-9s | %-6s | %-20s |%n", c.getCodhabitacion(),
//							c.getCodHotel().getCodHotel(), c.getNumHabitacion(), c.getCapacidad(), c.getPreciodia(),
//							c.getActiva(), "Sin Observaciones");
//				}
//			}
//			System.out.println("//Salida//");
//		} catch (Exception e) {
//			System.err.println("No se ha podido hacer la consulta 1");
//		}
			for (habitaciones c : habitaciones) {
                try {
                    if (c.getHabitacionesobservaciones().getObservaciones() == null) {

                    } else {
                        System.out.println("Cod Habitacion: " + c.getCodhabitacion() + "  " + "Num Habitacion: "
                                + c.getNumHabitacion() + "  " + "CodHotel: " + c.getCodHotel().getCodHotel() + "  "
                                + "Capacidad: " + c.getCapacidad() + "  " + " Precio dia: " + c.getPreciodia()
                                + "  " + "Activa: " + c.getActiva() + "  " + " Observacion: "
                                + c.getHabitacionesobservaciones().getObservaciones());
                    }
                } catch (Exception e) {
                    System.out.println("Cod Habitacion: " + c.getCodhabitacion() + "  " + "Num Habitacion: "
                            + c.getNumHabitacion() + "  " + "CodHotel: " + c.getCodHotel().getCodHotel() + "  "
                            + "Capacidad: " + c.getCapacidad() + "  " + " Precio dia: " + c.getPreciodia() + " || "
                            + "Activa: " + c.getActiva());
                }
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
			}
		
	}

	public static void consulta_2(Session sesion) {

	}

	public static void consulta_todas_habitaciones(Session sesion) {
		try {
			String sql = ("from habitaciones");

			Query q = sesion.createQuery(sql);

			List<habitaciones> habit = q.getResultList();
			System.out.format("| %-13s | %-10s | %-13s | %-8s | %-9s | %-6s |%n", "CodHabitacion", "CodHotel",
					"NumHabitacion", "Capacidad", "PrecioDia", "Activa");

			for (habitaciones c : habit) {

				System.out.format("| %-13s | %-10s | %-13s | %-9s | %-9s | %-6s |%n", c.getCodhabitacion(),
						c.getCodHotel().getCodHotel(), c.getNumHabitacion(), c.getCapacidad(), c.getPreciodia(),
						c.getActiva());
			}
			System.out.println("//Salida//");

		} catch (Exception e) {
			System.err.println("No se ha podido hacer una consulta de habitaciones");
		}
	}

	public static void consulta_habitacion(Session sesion, int codhabitacion) {
		try {
			String sql = ("from habitaciones hab WHERE hab.codhabitacion = :codhabitacion");

			Query q = sesion.createQuery(sql);

			q.setParameter("codhabitacion", codhabitacion);

			List<habitaciones> habit = q.getResultList();
			System.out.format("| %-13s | %-10s | %-13s | %-8s | %-9s | %-6s |%n", "CodHabitacion", "CodHotel",
					"NumHabitacion", "Capacidad", "PrecioDia", "Activa");

			for (habitaciones c : habit) {

				System.out.format("| %-13s | %-10s | %-13s | %-9s | %-9s | %-6s |%n", c.getCodhabitacion(),
						c.getCodHotel().getCodHotel(), c.getNumHabitacion(), c.getCapacidad(), c.getPreciodia(),
						c.getActiva());
			}
			System.out.println("//Salida//");

		} catch (Exception e) {
			System.err.println("No se ha podido hacer una consulta simple de habitaciones");
		}
	}

	public static void consulta_hoteles(Session sesion, String codHotel) {
		try {
			String sql = ("from hoteles hot WHERE hot.codHotel = :codHotel");

			Query q = sesion.createQuery(sql);

			q.setParameter("codHotel", codHotel);

			List<hoteles> hotel = q.getResultList();
			System.out.format("| %-10s | %-10s |%n", "CodHotel", "NomHotel");

			for (hoteles c : hotel) {
				System.out.format("| %-10s | %-10s |%n", c.getCodHotel(), c.getNomHotel());
			}

			System.out.println("//Salida//");

		} catch (Exception e) {
			System.err.println("No se ha podido hacer una consulta de hoteles");
		}
	}

	public static void consulta_estancias(Session sesion, int codestancia) {
		try {
			String sql = ("from estancias est WHERE est.codestancia = :codestancia");

			Query q = sesion.createQuery(sql);

			q.setParameter("codestancia", codestancia);

			List<estancias> est = q.getResultList();

			System.out.format("| %-11s | %-10s | %-11s | %-9s | %-6s | %-14s | %-10s | %-13s  | %-10s |%n",
					"Codestancia", "FechaFin", "FechaInicio", "Ocupantes", "Pagado", "PrecioEstancia", "CodDNIoNIE",
					"CodHabitacion", "CodRegimen");

			for (estancias c : est) {

				System.out.format("| %-11s | %-10s | %-11s | %-9s | %-6s | %-14s | %-10s | %-13s  | %-10s |%n",
						c.getCodestancia(), c.getFechaFIN(), c.getFechaInicio(), c.getOcupantes(), c.getPagado(),
						c.getPrecioestancia(), c.getCodDNIoNIE(), c.getCodhabitacion().getCodhabitacion(),
						c.getCodregimen());

			}
			System.out.println("//Salida//");

		} catch (Exception e) {
			System.err.println("No se ha podido hacer una consulta de estancias");
		}
	}

	public static void consulta_cliente(Session sesion, String CodDNIoNIE) {
		try {
			String sql = ("from clientes WHERE CodDNIoNIE = :CodDNIoNIE");

			Query q = sesion.createQuery(sql);

			q.setParameter("CodDNIoNIE", CodDNIoNIE);

			List<clientes> client = q.getResultList();
			System.out.format("| %-18s | %-18s | %-18s |%n", "CodDNIoNIE: ", "Nombre: ", "Nacionalidad: ",
					"Preciodia: ");
			for (clientes c : client) {

				System.out.format("| %-18s | %-18s | %-18s |%n", c.getCodDNIoNIE(), c.getNombre(), c.getNacionalidad());

			}

			System.out.println("//Salida//");

		} catch (Exception e) {
			System.err.println("No se ha podido hacer una consulta de clientes");
		}
	}

	public static void consulta_regimenes(Session sesion, int codregimen) {
		try {
			String sql = ("from regimenes regi WHERE regi.codregimen = :codregimen");

			Query q = sesion.createQuery(sql);

			q.setParameter("codregimen", codregimen);

			List<regimenes> regimen = q.getResultList();
			System.out.format("| %-15s | %-15s | %-15s | %-15s |%n", "Codregimen: ", "CodHotel: ", "Tipo: ",
					"Preciodia: ");

			for (regimenes c : regimen) {

				System.out.format("| %-15d | %-15s | %-15s | %-15s |%n", c.getCodregimen(),
						c.getCodHotel().getCodHotel(), c.getTipo(), c.getPreciodia());

			}
			System.out.println("//Salida//");

		} catch (Exception e) {
			System.err.println("No se ha podido hacer una consulta de regimen");
		}
	}

	public static void consulta_observacion(Session sesion, int codHabitacion) {
		try {
			String sql = ("from habitacionesobservaciones obs WHERE obs.codhabitacion = :codhabitacion");

			Query q = sesion.createQuery(sql);

			q.setParameter("codhabitacion", codHabitacion);

			List<habitacionesobservaciones> observacion = q.getResultList();
			System.out.format("| %-15s | %-15s |%n", "CodHabitacion", "Observacion");

			for (habitacionesobservaciones c : observacion) {

				System.out.format("| %-15d | %-15s |%n", c.getCodhabitacion(), c.getObservaciones());

			}
			System.out.println("//Salida//");

		} catch (Exception e) {
			System.err.println("No se ha podido hacer una consulta de observaciones");
		}
	}

}
