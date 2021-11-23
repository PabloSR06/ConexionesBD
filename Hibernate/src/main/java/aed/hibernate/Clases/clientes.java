package aed.hibernate.Clases;
import java.io.Serializable;

import javax.persistence.*;
//PABLO SUAREZ ROMERO
@SuppressWarnings("serial")
@Entity
@Table(name = "clientes")
public class clientes implements Serializable {
 
    @Id
    @Column(columnDefinition = "CHAR(9)")
    private String codDNIoNIE;

    @Column(length = 50)
    private String nombre;

    @Column(length = 40)
    private String nacionalidad;

	public String getCodDNIoNIE() {
		return codDNIoNIE;
	}

	public void setCodDNIoNIE(String codDNIoNIE) {
		this.codDNIoNIE = codDNIoNIE;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
    
    
}
