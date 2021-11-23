package aed.hibernate.Clases;
import java.io.Serializable;
import javax.persistence.*;
//PABLO SUAREZ ROMERO
@SuppressWarnings("serial")
@Entity
@Table(name="hoteles")
public class hoteles implements Serializable{

    @Id
    @Column(columnDefinition = "CHAR(6)")
    private String codHotel;

    @Column(length = 60)
    private String nomHotel;

    public hoteles(){

    }

    public String getCodHotel() {
        return codHotel;
    }

    public void setCodHotel(String codHotel) {
        this.codHotel = codHotel;
    }

    public String getNomHotel() {
        return nomHotel;
    }

    public void setNomHotel(String nomHotel) {
        this.nomHotel = nomHotel;
    }

    
    
}
