package co.gov.ideam.sdstrp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
@Table(name = "TRPT_ESTADO_MATERIA")
public class EstadoMateria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="EMA_ID")
    private String ema_id;
	@Column(name="EMA_NOMBRE")
    private String ema_nombre;
	
	public String getEma_id() {
		return ema_id;
	}
	public void setEma_id(String ema_id) {
		this.ema_id = ema_id;
	}
	public String getEma_nombre() {
		return ema_nombre;
	}
	public void setEma_nombre(String ema_nombre) {
		this.ema_nombre = ema_nombre;
	}
	
	
	
	
	public List<Residuos> getEstadoM() {
		return estadoM;
	}
	public void setEstadoM(List<Residuos> estadoM) {
		this.estadoM = estadoM;
	}
	@Override
	public String toString() {
		return ema_id + " : " + ema_nombre + "]";
	}
	
 	@OneToMany(cascade = CascadeType.ALL , mappedBy = "estadoM")
 	private List<Residuos> estadoM;
 	
 	@OneToMany(cascade = CascadeType.ALL , mappedBy = "deReEstado")
 	private List<DeclaracionResiduo> deReEstado;
 	
	
	
	
}
