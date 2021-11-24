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
    @GeneratedValue(generator = "myForeign")
	@GenericGenerator( name = "myForeign", strategy = "foreign",
	parameters = {@org.hibernate.annotations.Parameter(name =
	"property", value = "codHabitacionX")})
    private int codhabitacion;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @PrimaryKeyJoinColumn
    private habitaciones codHabitacionX;
    
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


	public habitaciones getCodHabitacionX() {
		return codHabitacionX;
	}


	public void setCodHabitacionX(habitaciones codHabitacionX) {
		this.codHabitacionX = codHabitacionX;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


    




    
}
