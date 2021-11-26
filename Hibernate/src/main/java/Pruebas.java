import org.hibernate.Session;

import aed.hibernate.Clases.HibernateUtil;
import aed.hibernate.Clases.habitaciones;
import aed.hibernate.Main.InitialInsert;
import aed.hibernate.Query.Consultas;

public class Pruebas {

	public static void main(String[] args) {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		InitialInsert.inicio(sesion);
		//sesion.beginTransaction();

		habitaciones hab = new habitaciones();
		hab = (habitaciones) sesion.get(habitaciones.class, 1);

		System.out.println(hab.getActiva());
		
		
		
		for (int i = 0; i < 20; i++) {
			Consultas.consulta_1(sesion);			
		}
	}

}
