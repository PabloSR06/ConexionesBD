package aed.hibernate.Clases;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.*;
//PABLO SUAREZ ROMERO


@SuppressWarnings("serial")
@Entity
@Table(name = "habitaciones")
public class habitaciones implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codhabitacion;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@PrimaryKeyJoinColumn
	private habitacionesobservaciones habitacionesobservaciones;
	

	@ManyToOne
	@JoinColumn(name = "codHotel")
	private hoteles codHotel;

	@Column(columnDefinition = "CHAR(4)")
	private String numHabitacion;

	@Column(columnDefinition = "SMALLINT(6)")
	private int capacidad;

	@Column
	private int preciodia;

	@Column(columnDefinition = "TINYINT(1)")
	private int activa;

	public habitaciones() {

	}

	public int getCodhabitacion() {
		return codhabitacion;
	}

	public void setCodhabitacion(int codhabitacion) {
		this.codhabitacion = codhabitacion;
	}

	public hoteles getCodHotel() {
		return codHotel;
	}

	public void setCodHotel(hoteles codHotel) {
		this.codHotel = codHotel;
	}

	public String getNumHabitacion() {
		return numHabitacion;
	}

	public void setNumHabitacion(String numHabitacion) {
		this.numHabitacion = numHabitacion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getPreciodia() {
		return preciodia;
	}

	public void setPreciodia(int preciodia) {
		this.preciodia = preciodia;
	}

	public int getActiva() {
		return activa;
	}

	public void setActiva(int activa) {
		this.activa = activa;
	}

	public habitacionesobservaciones getHabitacionesobservaciones() {
		return habitacionesobservaciones;
	}

	public void setHabitacionesobservaciones(habitacionesobservaciones habitacionesobservaciones) {
		this.habitacionesobservaciones = habitacionesobservaciones;
	}
	
}
