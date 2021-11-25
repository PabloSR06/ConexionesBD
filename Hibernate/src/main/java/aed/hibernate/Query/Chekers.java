package aed.hibernate.Query;

import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.persistence.Query;
import org.hibernate.Session;
import aed.hibernate.Clases.*;

public class Chekers {
	

	public static boolean hay_habitacion(Session sesion, int codhabitacion) {
		try {
			String sql = ("from habitaciones hab WHERE hab.codhabitacion = :codhabitacion");
			Boolean salida = false;

			Query q = sesion.createQuery(sql);

			q.setParameter("codhabitacion", codhabitacion);

			List<habitaciones> habit = q.getResultList();

			if (habit.size() == 0) {
				salida = false;
			} else {
				salida = true;
			}
			return salida;
		} catch (Exception e) {
			System.err.println("No se ha podido comprobar si hay habitaciones");
			return false;
		}
	}

	public static boolean hay_hotel(Session sesion, String codhotel) {
		try {
			String sql = ("from hoteles  WHERE codhotel = :codhotel");
			Boolean salida = false;

			Query q = sesion.createQuery(sql);

			q.setParameter("codhotel", codhotel);

			List<hoteles> hotel = q.getResultList();

			if (hotel.size() == 0) {
				salida = false;
			} else {
				salida = true;
			}
			return salida;
		} catch (Exception e) {
			System.err.println("No se ha podido comprobar si hay hoteles");
			return false;
		}
	}

	public static boolean hay_cliente(Session sesion, String codDNIoNIE) {
		try {
			String sql = ("from clientes clie WHERE clie.codDNIoNIE = :codDNIoNIE");
			Boolean salida = false;

			Query q = sesion.createQuery(sql);

			q.setParameter("codDNIoNIE", codDNIoNIE);

			List<clientes> cliente = q.getResultList();

			if (cliente.size() == 0) {
				salida = false;
			} else {
				salida = true;
			}
			return salida;
		} catch (Exception e) {
			System.err.println("No se ha podido comprobar si hay clientes");
			return false;
		}
	}
	
	public static boolean hay_regimen(Session sesion, int codregimen) {
		try {
			String sql = ("from regimenes reg WHERE reg.codregimen = :codregimen");
			Boolean salida = false;

			Query q = sesion.createQuery(sql);

			q.setParameter("codregimen", codregimen);

			List<regimenes> regimen = q.getResultList();

			if (regimen.size() == 0) {
				salida = false;
			} else {
				salida = true;
			}
			return salida;
		} catch (Exception e) {
			System.err.println("No se ha podido comprobar si hay regimen");
			return false;
		}
	}
	
	public static boolean hay_estancias(Session sesion, int codhabitacion) {
		try {
			String sql = ("from estancias est WHERE est.codhabitacion = :codhabitacion");
			Boolean salida = false;

			Query q = sesion.createQuery(sql);
			
			habitaciones hab = new habitaciones();
			hab = (habitaciones) sesion.get(habitaciones.class, codhabitacion);
			
			q.setParameter("codhabitacion", hab);

			List<estancias> estancia = q.getResultList();

			if (estancia.size() == 0) {
				salida = false;
			} else {
				salida = true;
			}
			return salida;
		} catch (Exception e) {
			System.err.println("No se ha podido comprobar si hay estancias");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	public static boolean hay_observacion(Session sesion, int codHabitacion) {
		try {
			String sql = ("from habitacionesobservaciones obs WHERE codhabitacionX = :codhabitacion");
			Boolean salida = false;

			Query q = sesion.createQuery(sql);
			
			habitaciones hab = new habitaciones();
			hab = (habitaciones) sesion.get(habitaciones.class, codHabitacion);
			

			q.setParameter("codhabitacion", hab	);

			List<habitacionesobservaciones> observacion = q.getResultList();

			if (observacion.size() == 0) {
				salida = false;
			} else {
				salida = true;
			}
			return salida;
		} catch (Exception e) {
			System.err.println("No se ha podido comprobar si hay observaciones");
			return false;
		}
	}

	public static boolean checkActiva(int activa) {
		if (activa == 1 || activa == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static void espera(int cantidad) {
		try {
			System.out.println("Cargando...");
			TimeUnit.SECONDS.sleep(cantidad);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
