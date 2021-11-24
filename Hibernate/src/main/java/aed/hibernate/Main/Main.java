package aed.hibernate.Main;



import java.util.Iterator;

import org.hibernate.*;

import aed.hibernate.Clases.*;
import aed.hibernate.Query.Consultas;
import aed.hibernate.Query.Eliminar;
import aed.hibernate.Query.Insertar;
//PABLO SUAREZ ROMERO

public class Main {
    public static void main(String[] args) {
        // Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();
        //Insertar.insertar_hoteles(sesion, "5", "hotel5");
        //Insertar.insertar_habitaciones(sesion, "100", 1 ,1, 1, "5");
        //Insertar.insertar_observaciones(sesion, 4, "prueba");
        //Insertar.insertar_clientes(sesion, "0005A", "espa", "nombre5");
        //Insertar.insertar_estancias(sesion, 5, "2021-11-11", "2021-11-12", 1, 1, 1, "0005A", 4, 1);
//        Eliminar.eliminar_estancias(sesion, 4);
        
        hoteles h1 = new hoteles();
        h1.setCodHotel("1");
        h1.setNomHotel("hotel1");
        sesion.save(h1);
        hoteles h2 = new hoteles();
        h2.setCodHotel("2");
        h2.setNomHotel("hotel2");
        sesion.save(h2);
        sesion.getTransaction().commit();
        
        hoteles hot1 = sesion.get(hoteles.class, "1");
        for (int i = 0; i < 4; i++) {
        	sesion.beginTransaction();
        	habitaciones hab1 = new habitaciones();
            hab1.setCodHotel(hot1);
            hab1.setCapacidad(1);
            hab1.setActiva(1);
            hab1.setNumHabitacion("1"+i);
            hab1.setPreciodia(1);
            sesion.save(hab1);
            sesion.getTransaction().commit();
		}
        
        
        for (int i = 0; i < 2; i++) {
        	sesion.beginTransaction();
            habitacionesobservaciones ob1 = new habitacionesobservaciones();
            habitaciones hab2 = sesion.get(habitaciones.class, i+1);
            ob1.setCodHabitacionX(hab2);
            ob1.setCodhabitacion(i+1);
            ob1.setObservaciones("texto " + i);
            
            sesion.save(ob1);
            sesion.getTransaction().commit();
		}
        
        for (int i = 0; i < 5; i++) {
        	sesion.beginTransaction();
            clientes clie = new clientes();
            clie.setCodDNIoNIE("00"+i+"A");
            clie.setNacionalidad("nacionalidad "+i);
            clie.setNombre("nombre "+i);
    
            sesion.save(clie);
            sesion.getTransaction().commit();
		}
        
        for (int i = 1; i < 4; i++) {
        	sesion.beginTransaction();
            regimenes reg = new regimenes();
            reg.setCodregimen(i);
            reg.setPreciodia(12);
            reg.setTipo("DY");
            reg.setCodHotel(hot1);
    
            sesion.save(reg);
            sesion.getTransaction().commit();
		}
        
        for (int i = 1; i < 4; i++) {
        	sesion.beginTransaction();
            estancias est = new estancias();
            clientes cli1 = sesion.get(clientes.class, "00"+i+"A");
            habitaciones hab2 = sesion.get(habitaciones.class, i);
            est.setCodestancia(i);
            est.setFechaFIN("2021-11-12");
            est.setFechaInicio("2021-11-12");
            est.setCodhabitacion(hab2);
            est.setOcupantes(10);
            est.setPagado(1);
            est.setPrecioestancia(20);
            est.setCodDNIoNIE(cli1);
    
            sesion.save(est);
            sesion.getTransaction().commit();
		}
        
        Consultas.consulta_observacion(sesion, 1);
        Consultas.consulta_1(sesion);
        sesion.close();
        
        
       
    }

    
}