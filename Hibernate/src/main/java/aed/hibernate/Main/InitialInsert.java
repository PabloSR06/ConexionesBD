package aed.hibernate.Main;

import org.hibernate.*;

import aed.hibernate.Clases.*;

//PABLO SUAREZ ROMERO

public class InitialInsert {
    public static void inicio(Session sesion) {

        sesion.beginTransaction();
     
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
        
        
        for (int i = 1; i < 3; i++) {
        	sesion.beginTransaction();
            habitacionesobservaciones ob1 = new habitacionesobservaciones();
            habitaciones hab2 = sesion.get(habitaciones.class, i);
            ob1.setCodhabitacionX(hab2);
            //ob1.setCodhabitacion(i);
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
            regimenes reg = sesion.get(regimenes.class, i);
            est.setCodestancia(i);
            est.setFechaFIN("2021-11-12");
            est.setFechaInicio("2021-11-12");
            est.setCodhabitacion(hab2);
            est.setOcupantes(10);
            est.setPagado(1);
            est.setCodregimen(reg);
            est.setPrecioestancia(20);
            est.setCodDNIoNIE(cli1);
    
            sesion.save(est);
            sesion.getTransaction().commit();
		}
        
        sesion.beginTransaction();
        estancias est = new estancias();
        estancias est1 = new estancias();

        clientes cli1 = sesion.get(clientes.class, "00"+1+"A");
        habitaciones hab2 = sesion.get(habitaciones.class, 1);
        regimenes reg = sesion.get(regimenes.class, 1);
        est.setCodestancia(4);
        est.setFechaFIN("2021-11-12");
        est.setFechaInicio("2021-11-12");
        est.setCodhabitacion(hab2);
        est.setOcupantes(10);
        est.setPagado(1);
        est.setCodregimen(reg);
        est.setPrecioestancia(20);
        est.setCodDNIoNIE(cli1);
        
        est1.setCodestancia(5);
        est1.setFechaFIN("2021-11-12");
        est1.setFechaInicio("2021-11-12");
        est1.setCodhabitacion(hab2);
        est1.setOcupantes(10);
        est1.setPagado(1);
        est1.setCodregimen(reg);
        est1.setPrecioestancia(20);
        est1.setCodDNIoNIE(cli1);

        sesion.save(est);
        sesion.save(est1);
        sesion.getTransaction().commit();
            
    }

    
}