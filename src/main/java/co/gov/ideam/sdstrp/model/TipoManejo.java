package co.gov.ideam.sdstrp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * SE UTILIZA HASTA ACLARAR PARA TRAER DATOS ASOCIADOS AL SUB TIPO DE GESTION
 *
 */

@Entity
@XmlRootElement
@Table(name = "TRPT_TIPO_MANEJO")
public class TipoManejo {

	

	@Id
	@Column(name="TMA_ID")
    private String tma_id;
	@Column(name="TMA_NOMBRE")
    private String tma_nombre;
	@Column(name="TMA_PADRE")
    private String tma_padre;

    public String getTma_id() {
        return tma_id;
    }

    public void setTma_id(String tma_id) {
        this.tma_id = tma_id;
    }

    public String getTma_nombre() {
        return tma_nombre;
    }

    public void setTma_nombre(String tma_nombre) {
        this.tma_nombre = tma_nombre;
    }

    public String getTma_padre() {
        return tma_padre;
    }

    public void setTma_padre(String tma_padre) {
        this.tma_padre = tma_padre;
    }

	@Override
	public String toString() {
		return tma_id + " :  " + tma_nombre + " :  " + tma_padre;
	}
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tManejo")
	 private List<Residuos> Residuos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tiManejo")
	 private List<ServicioGestor> serGestor;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subtipoGes")
	 private List<DeclaracionResiduo> subtipoGes;
    
    
}


