package aed.hibernate.Query;

import java.util.Iterator;
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
		try {
			String sql = ("from habitaciones");

			Query q = sesion.createQuery(sql);

			List<habitaciones> habitaciones = q.getResultList();

			System.out.format("| %-13s | %-10s | %-13s | %-8s | %-9s | %-6s | %-20s |%n", "CodHabitacion", "CodHotel",
					"NumHabitacion", "Capacidad", "PrecioDia", "Activa", "Observaciones");
			for (habitaciones c : habitaciones) {
				try {
					if (c.getHabitacionesobservaciones() == null) {
						System.out.format("| %-13s | %-10s | %-13s | %-9s | %-9s | %-6s | %-20s |%n",
								c.getCodhabitacion(), c.getCodHotel().getCodHotel(), c.getNumHabitacion(),
								c.getCapacidad(), c.getPreciodia(), c.getActiva(), c.getHabitacionesobservaciones());
					} else {
						System.out.format("| %-13s | %-10s | %-13s | %-9s | %-9s | %-6s | %-20s |%n",
								c.getCodhabitacion(), c.getCodHotel().getCodHotel(), c.getNumHabitacion(),
								c.getCapacidad(), c.getPreciodia(), c.getActiva(),
								c.getHabitacionesobservaciones().getObservaciones());
					}

				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
			System.out.println("//Salida//");
		} catch (Exception e) {
			System.err.println("No se ha podido hacer la consulta 1");
		}

	}

	public static void consulta_2(Session sesion) {
		try {
			String sql = ("from habitaciones");
			String sql1 = ("from estancias");

			Query q = sesion.createQuery(sql);
			Query q1 = sesion.createQuery(sql1);

			List<habitaciones> habit = q.getResultList();
			List<estancias> esta = q1.getResultList();

			if (habit.isEmpty()) {
				System.out.println("No hay habitaciones");
			} else {
				for (habitaciones c : habit) {
					System.out.println();
					System.out.println("\n//Habitacion\\\n");
					System.out.format("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |%n", "CodHabitacion",
							"numHabitacion", "CodHotel", "nomHotel", "PrecioDia", "Activa", "Capacidad");

					System.out.format("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |%n", c.getCodhabitacion(),
							c.getNumHabitacion(), c.getCodHotel().getCodHotel(), c.getCodHotel().getNomHotel(),
							c.getPreciodia(), c.getActiva(), c.getCapacidad());
					System.out.println();

					for (estancias c1 : esta) {
						if (c1.getCodhabitacion().getCodhabitacion() == c.getCodhabitacion()) {

							System.out.println("\n//Regimen//\n");
							System.out.format(
									"| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |%n",
									"CodHabitacion", "FechaFin", "FechaInicio", "Ocupantes", "Pagado", "PrecioEstancia",
									"CodDNIoNIE", "Nombre", "CodRegimen");

							System.out.format(
									"| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |%n",
									c1.getCodhabitacion().getCodhabitacion(), c1.getFechaFIN(), c1.getFechaInicio(),
									c1.getOcupantes(), c1.getPagado(), c1.getPrecioestancia(),
									c1.getCodDNIoNIE().getCodDNIoNIE(), c1.getCodDNIoNIE().getNombre(),
									c1.getCodregimen().getCodregimen());

						}
					}
				}
			}

			System.out.println("//Salida//");

		} catch (Exception e) {
			System.err.println("No se ha podido hacer la consulta 2");
		}
	}

	public static void consulta_3(Session sesion) {
		try {
			String sql = ("from habitaciones");
			String sql1 = ("from estancias");

			Query q = sesion.createQuery(sql);
			Query q1 = sesion.createQuery(sql1);

			List<habitaciones> habit = q.getResultList();
			List<estancias> esta = q1.getResultList();

			if (habit.isEmpty()) {
				System.out.println("No hay habitaciones");
			} else {
				for (estancias c : esta) {
					System.out.println();

					System.out.println("\n Habitacion: \n");
					System.out.format("| %-20s| %-20s | %-20s | %-20s | %-20s |%n", "CodHabitacion", "numHabitacion",
							"PrecioDia", "Activa", "Capacidad");

					System.out.format("| %-20s| %-20s | %-20s | %-20s | %-20s |%n",
							c.getCodhabitacion().getCodhabitacion(), c.getCodhabitacion().getNumHabitacion(),
							c.getCodhabitacion().getPreciodia(), c.getCodhabitacion().getActiva(),
							c.getCodhabitacion().getCapacidad());
					System.out.println();

					System.out.println("\n Hotel: \n");
					System.out.format("| %-15s | %-15s |%n", "CodHotel", "NomHotel");

					System.out.format("| %-15s | %-15s |%n", c.getCodhabitacion().getCodHotel().getCodHotel(),
							c.getCodhabitacion().getCodHotel().getNomHotel());
					System.out.println();

					

					System.out.println("\n Regimen: \n");
					System.out.format("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |%n",
							"CodHabitacion", "FechaFin", "FechaInicio", "Ocupantes", "Pagado", "PrecioEstancia",
							"CodRegimen");

					System.out.format("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |%n",
							c.getCodhabitacion().getCodhabitacion(), c.getFechaFIN(), c.getFechaInicio(),
							c.getOcupantes(), c.getPagado(), c.getPrecioestancia(), c.getCodregimen().getCodregimen());
					
					
					System.out.println("\n Cliente: \n");
					System.out.format("| %-15s | %-15s |%n", "CodDNIoNIE", "Nombre");
					
					System.out.format("| %-15s | %-15s |%n", c.getCodDNIoNIE().getCodDNIoNIE(),
							c.getCodDNIoNIE().getNombre());
					System.out.println();
					
					System.out.println("----------------------------------------------------------");

				}
			}

			System.out.println("//Salida//");

		} catch (Exception e) {
			System.err.println("No se ha podido hacer la consulta 2");
		}
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
			String sql = ("from habitacionesobservaciones WHERE codhabitacion = :codhabitacion");

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
