package aed.hibernate.Clases;
import java.io.Serializable;
import javax.persistence.*;
//PABLO SUAREZ ROMERO
@SuppressWarnings("serial")
@Entity
@Table(name = "estancias")
public class estancias implements Serializable{
    
    @Id
    private int codestancia;

    @ManyToOne
    @JoinColumn(name="codDNIoNIE")
    private clientes codDNIoNIE;

    @ManyToOne
    @JoinColumn(name="codhabitacion")
    private habitaciones codhabitacion;
    

    @Column(columnDefinition = "DATE")
    private String fechaInicio;

    @Column(columnDefinition = "DATE")
    private String fechaFIN;

    @ManyToOne
    @JoinColumn(name="codregimen")
    private regimenes codregimen;

    @Column(columnDefinition = "SMALLINT")
    private int ocupantes;

    @Column
    private int precioestancia;

    @Column(columnDefinition = "TINYINT(1)")
    private int pagado;

    public estancias(){

    }

    public int getCodestancia() {
        return codestancia;
    }

    public void setCodestancia(int codestancia) {
        this.codestancia = codestancia;
    }

    public clientes getCodDNIoNIE() {
        return codDNIoNIE;
    }

    public void setCodDNIoNIE(clientes codDNIoNIE) {
        this.codDNIoNIE = codDNIoNIE;
    }

    public habitaciones getCodhabitacion() {
        return codhabitacion;
    }

    public void setCodhabitacion(habitaciones codhabitacion) {
        this.codhabitacion = codhabitacion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFIN() {
        return fechaFIN;
    }

    public void setFechaFIN(String fechaFIN) {
        this.fechaFIN = fechaFIN;
    }

    public regimenes getCodregimen() {
        return codregimen;
    }

    public void setCodregimen(regimenes codregimen) {
        this.codregimen = codregimen;
    }

    public int getOcupantes() {
        return ocupantes;
    }

    public void setOcupantes(int ocupantes) {
        this.ocupantes = ocupantes;
    }

    public int getPrecioestancia() {
        return precioestancia;
    }

    public void setPrecioestancia(int precioestancia) {
        this.precioestancia = precioestancia;
    }

    public int getPagado() {
        return pagado;
    }

    public void setPagado(int pagado) {
        this.pagado = pagado;
    }

    

    
}
