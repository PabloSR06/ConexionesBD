package aed.hibernate.Clases;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
//PABLO SUAREZ ROMERO
@SuppressWarnings("serial")
@Entity
@Table(name="habitacionesobservaciones")
public class habitacionesobservaciones implements Serializable {

	@Id
	@GeneratedValue(generator = "fknumhabitacion")
	@GenericGenerator(name = "fknumhabitacion", strategy = "foreign", parameters = {
			@org.hibernate.annotations.Parameter(name = "property", value = "codhabitacionX") })
	@Column(columnDefinition = "int(11)")
	private int codhabitacion;
    
	@OneToOne(cascade = { CascadeType.ALL })
	@PrimaryKeyJoinColumn
	private habitaciones codhabitacionX;
    
    @Column(length=200)
    private String observaciones;


    public habitacionesobservaciones(){

    }


	public int getCodhabitacion() {
		return codhabitacion;
	}


	public void setCodhabitacion(int codhabitacion) {
		this.codhabitacion = codhabitacion;
	}


	public habitaciones getCodhabitacionX() {
		return codhabitacionX;
	}


	public void setCodhabitacionX(habitaciones codhabitacionX) {
		this.codhabitacionX = codhabitacionX;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


}
