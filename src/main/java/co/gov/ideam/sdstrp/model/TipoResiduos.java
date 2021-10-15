package co.gov.ideam.sdstrp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "TRPT_TIPO_RESIDUO")
public class TipoResiduos implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TRE_ID")
	private String tre_id;
	
	@Column(name="TRE_NOMBRE")
	private String tre_nombre ;
	
	@Column(name="TRE_PADRE")
	private String tre_padre ;
	
	
	
	public List<Residuos> getResiduos() {
		return Residuos;
	}

	public void setResiduos(List<Residuos> residuos) {
		Residuos = residuos;
	}

	public List<ServicioGestor> getSerGestor() {
		return serGestor;
	}

	public void setSerGestor(List<ServicioGestor> serGestor) {
		this.serGestor = serGestor;
	}

	public List<DeclaracionResiduo> getTipoResDec() {
		return tipoResDec;
	}

	public void setTipoResDec(List<DeclaracionResiduo> tipoResDec) {
		this.tipoResDec = tipoResDec;
	}


	public String getTre_id() {
		return tre_id;
	}

	public void setTre_id(String tre_id) {
		this.tre_id = tre_id;
	}

	public String getTre_nombre() {
		return tre_nombre;
	}

	public void setTre_nombre(String tre_nombre) {
		this.tre_nombre = tre_nombre;
	}

	public String getTre_padre() {
		return tre_padre;
	}

	public void setTre_padre(String tre_padre) {
		this.tre_padre = tre_padre;
	}
	
	

	@Override
	public String toString() {
		return tre_id + " : " + tre_nombre + " : " + tre_padre;
	}
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tResiduo")
	 private List<Residuos> Residuos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tiResiduos")
	 private List<ServicioGestor> serGestor;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoResDec")
	 private List<DeclaracionResiduo> tipoResDec;
	
	
}
