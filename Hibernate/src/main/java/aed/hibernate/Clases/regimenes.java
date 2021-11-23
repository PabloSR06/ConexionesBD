package aed.hibernate.Clases;
import java.io.Serializable;
import javax.persistence.*;
//PABLO SUAREZ ROMERO
@SuppressWarnings("serial")
@Entity
@Table(name="regimenes")
public class regimenes implements Serializable{
    
    @Id
    private int codregimen;

    @ManyToOne
    @JoinColumn(name="codHotel")
    private hoteles codHotel;

    @Column(columnDefinition = "CHAR(2)")
    private String tipo;

    @Column
    private int preciodia;

    public regimenes(){

    }

    public int getCodregimen() {
        return codregimen;
    }

    public void setCodregimen(int codregimen) {
        this.codregimen = codregimen;
    }

    public hoteles getCodHotel() {
        return codHotel;
    }

    public void setCodHotel(hoteles codHotel) {
        this.codHotel = codHotel;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPreciodia() {
        return preciodia;
    }

    public void setPreciodia(int preciodia) {
        this.preciodia = preciodia;
    }

    
    

}
