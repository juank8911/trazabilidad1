package co.gov.ideam.sdstrp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
@Table(name = "TRPT_TIPO_GESTION")
public class TipoGestion implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
//	"ID_TIP_GESTION" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
//	"NOMBRE_GESTION" VARCHAR2(50 BYTE)
	
	@Id
	@Column(name="ID_TIP_GESTION")
    private String id_tip_gestion;
	
	@Column(name="NOMBRE_GESTION")
    private String nombre_gestion;
	

    public String getId_tip_gestion() {
        return id_tip_gestion;
    }

    public void setId_tip_gestion(String id_tip_gestion) {
        this.id_tip_gestion = id_tip_gestion;
    }

    public String getNombre_gestion() {
        return nombre_gestion;
    }

    public void setNombre_gestion(String nombre_gestion) {
        this.nombre_gestion = nombre_gestion;
    }

	@Override
	public String toString() {
		return id_tip_gestion + " :  " + nombre_gestion;
	}
	
	
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tResiduo")
	 private List<Residuos> residuos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tiGestion")
	 private List<ServicioGestor> serGestor;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoGes")
	 private List<DeclaracionResiduo> tipoGes;
    
    

}
