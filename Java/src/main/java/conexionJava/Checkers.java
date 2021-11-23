package conexionJava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

public class Checkers {

	private static Connection db = ConectionDB.conectionDB();

	public static void main(String[] args) {

		System.out.println(checkCodHotel("3"));
	}

	public static boolean checkNomHotel(String nomHotel) {
		try {
			int retorno = 0;

			PreparedStatement sql = db.prepareStatement("SELECT nomHotel FROM hoteles WHERE nomHotel = ?");
			sql.setString(1, nomHotel);

			ResultSet resultado = sql.executeQuery();
			while (resultado.next()) {
				// String nomHotel1 = resultado.getString("nomHotel1");
				retorno++;
			}

			if (retorno > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			return false;
		}
	}

	public static boolean checkCodHotel(String codHotel) {
		try {
			int retorno = 0;

			PreparedStatement sql = db.prepareStatement("SELECT codHotel FROM hoteles WHERE codHotel = ?");
			sql.setString(1, codHotel);

			ResultSet resultado = sql.executeQuery();
			while (resultado.next()) {
//				String codHotel1 = resultado.getString("codHotel");
//				String numHabitacion1 = resultado.getString("numHabitacion");
//				System.out.println(codHotel1);
//				System.out.println(numHabitacion1);
				retorno++;
			}

			if (retorno > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			return false;
		}

	}

	public static boolean checkNumHabitacion(String numHabitacion) {
		try {

			int retorno = 0;

			PreparedStatement sql = db.prepareStatement(
					"SELECT numHabitacion FROM habitaciones WHERE numHabitacion = ?");
			//sql.setString(1, codHotel);
			sql.setString(1, numHabitacion);

			ResultSet resultado = sql.executeQuery();
			while (resultado.next()) {
				// String codHotel1 = resultado.getString("codHotel");
				retorno++;
			}

			if (retorno > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
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

	public static boolean checkHabitacionEstancia(String codHotel, String numHabitacion) {
		try {

			ResultSet resultado;
			PreparedStatement sql = db.prepareStatement(
					"SELECT codHotel, numHabitacion FROM estancias WHERE codHotel = ? AND numHabitacion = ?");

			sql.setString(1, codHotel);
			sql.setString(2, numHabitacion);

			resultado = sql.executeQuery();

			int retorno = 0;
			while (resultado.next()) {
//				String codHotel1 = resultado.getString("codHotel");
//				String numHabitacion1 = resultado.getString("numHabitacion");
				retorno++;
			}

			if (retorno > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			return false;
		}

	}

	public static boolean checkCodDni(String coddni) {
		try {
			ResultSet resultado;
			PreparedStatement sql = db.prepareStatement("SELECT coddnionie FROM clientes WHERE coddnionie = ?");

			sql.setString(1, coddni);

			resultado = sql.executeQuery();

			int retorno = 0;
			while (resultado.next()) {
//				String coddni1 = resultado.getString("coddnionie");

				retorno++;
			}

			if (retorno > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
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
