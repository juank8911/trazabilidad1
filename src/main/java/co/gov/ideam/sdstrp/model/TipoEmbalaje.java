
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
@Table(name = "TRPT_TIPO_EMBALAJE")
public class TipoEmbalaje implements Serializable {
    
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="TEM_ID")
    private String tem_id;
	
	@Column(name="TEM_NOMBRE")
    private String tem_nombre;

    public String getTem_id() {
        return tem_id;
    }

    public void setTem_id(String tem_id) {
        this.tem_id = tem_id;
    }

    public String getTem_nombre() {
        return tem_nombre;
    }

    public void setTem_nombre(String tem_nombre) {
        this.tem_nombre = tem_nombre;
    }

	@Override
	public String toString() {
		return tem_id + " : " + tem_nombre;
	}
	
	public List<Residuos> getjTipoEmb() {
		return jTipoEmb;
	}

	public void setjTipoEmb(List<Residuos> jTipoEmb) {
		this.jTipoEmb = jTipoEmb;
	}

	public List<DeclaracionResiduo> getTipoEmbDec() {
		return tipoEmbDec;
	}

	public void setTipoEmbDec(List<DeclaracionResiduo> tipoEmbDec) {
		this.tipoEmbDec = tipoEmbDec;
	}
	
	
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jTipoEmb")
    private List<Residuos> jTipoEmb;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoEmbDec")
	 private List<DeclaracionResiduo> tipoEmbDec;


   
    
    
    
    
    
}
