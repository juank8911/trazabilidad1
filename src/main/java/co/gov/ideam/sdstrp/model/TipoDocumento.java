package co.gov.ideam.sdstrp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "TRPT_TIPO_DOCUMENTO")
public class TipoDocumento implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	"TDO_ID" NUMBER, 
//	"TDO_NOMBRE" VARCHAR2(50 BYTE)
	
	@Id
	@GeneratedValue
	@Column(name="TDO_ID")
	private int tdo_id;
	
	@Column(name="TDO_NOMBRE")
	private String tdo_nombre;

	public int getTdo_id() {
		return tdo_id;
	}

	public void setTdo_id(int tdo_id) {
		this.tdo_id = tdo_id;
	}

	public String getTdo_nombre() {
		return tdo_nombre;
	}

	public void setTdo_nombre(String tdo_nombre) {
		this.tdo_nombre = tdo_nombre;
	}

	@Override
	public String toString() {
		return "TipoDocumento [tdo_id=" + tdo_id + ", tdo_nombre=" + tdo_nombre + "]";
	} 
	

	@OneToMany(cascade = {CascadeType.MERGE} ,fetch = FetchType.LAZY, mappedBy = "tDocumento")
	 private List<Usuario> tDocumento;

}
