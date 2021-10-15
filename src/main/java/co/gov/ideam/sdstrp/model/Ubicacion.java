package co.gov.ideam.sdstrp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@XmlRootElement
@Table(name = "TRPT_UBICACION")
public class Ubicacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_Ubica")
	@SequenceGenerator(initialValue = 5,allocationSize=1,name = "sequence_Ubica", sequenceName = "TRPT_UBICACION_SEQ")
	@Column(name="ID_UBC")
    private String id_ubc;
	@Column(name="ID_UBC_DEPT")
    private String id_ubc_dept;
	@Column(name="ID_UBC_MUNIC")
    private String id_ubc_munic;
	@Column(name="UBC_DIRECCION")
    private String ubc_direccion;
	@Column(name="UBC_LATITUD")
    private String ubc_latitud;
	@Column(name="UBC_LONGITUD")
    private String ubc_longitud;
	@Column(name="UBC_EXT")
    private String ubc_ext;
	@Column(name="UBC_TELE")
    private String ubc_tele;

    
    
    public String getId_ubc() {
        return id_ubc;
    }

    public void setId_ubc(String id_ubc) {
        this.id_ubc = id_ubc;
    }

    public String getId_ubc_dept() {
        return id_ubc_dept;
    }

    public void setId_ubc_dept(String id_ubc_dept) {
        this.id_ubc_dept = id_ubc_dept;
    }

    public String getId_ubc_munic() {
        return id_ubc_munic;
    }

    public void setId_ubc_munic(String id_ubc_munic) {
        this.id_ubc_munic = id_ubc_munic;
    }

    public String getUbc_direccion() {
        return ubc_direccion;
    }

    public void setUbc_direccion(String ubc_direccion) {
        this.ubc_direccion = ubc_direccion;
    }

    public String getUbc_latitud() {
        return ubc_latitud;
    }

    public void setUbc_latitud(String ubc_latitud) {
        this.ubc_latitud = ubc_latitud;
    }

    public String getUbc_longitud() {
        return ubc_longitud;
    }

    public void setUbc_longitud(String ubc_longitud) {
        this.ubc_longitud = ubc_longitud;
    }

    public String getUbc_ext() {
        return ubc_ext;
    }

    public void setUbc_ext(String ubc_ext) {
        this.ubc_ext = ubc_ext;
    }

    public String getUbc_tele() {
        return ubc_tele;
    }

    public void setUbc_tele(String ubc_tele) {
        this.ubc_tele = ubc_tele;
    }

	@Override
	public String toString() {
		return "ubicacion [id_ubc=" + id_ubc + ", id_ubc_dept=" + id_ubc_dept + ", id_ubc_munic=" + id_ubc_munic
				+ ", ubc_direccion=" + ubc_direccion + ", ubc_latitud=" + ubc_latitud + ", ubc_longitud=" + ubc_longitud
				+ ", ubc_ext=" + ubc_ext + ", ubc_tele=" + ubc_tele + "]";
	}
	
//	  @ManyToOne(fetch=FetchType.LAZY)
//	   @JoinColumn(name = "ID_UBC", referencedColumnName = "EMP_ID_UBICA", insertable=false, updatable=false)
//	   private Empresa ubiEmpresa;
    
    

}
