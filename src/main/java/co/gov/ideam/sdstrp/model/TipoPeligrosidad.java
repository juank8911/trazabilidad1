package co.gov.ideam.sdstrp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "TRPT_TIPO_PELIGROSIDAD")
public class TipoPeligrosidad implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="TPE_ID")
    private String tpe_id;
	@Column(name="TPE_NOMBRE")
    private String tpe_nombre;
	

    public String getTpe_id() {
        return tpe_id;
    }

    public void setTpe_id(String tpe_id) {
        this.tpe_id = tpe_id;
    }

    public String getTpe_nombre() {
        return tpe_nombre;
    }

    public void setTpe_nombre(String tpe_nombre) {
        this.tpe_nombre = tpe_nombre;
    }
    
    

	public List<Residuos> gettPeligro() {
		return tPeligro;
	}

	public void settPeligro(List<Residuos> tPeligro) {
		this.tPeligro = tPeligro;
	}

	@Override
	public String toString() {
		return "tipoPeligrosidad [tpe_id=" + tpe_id + ", tpe_nombre=" + tpe_nombre + "]";
	}
	
 	@OneToMany(cascade = CascadeType.ALL , mappedBy = "tPeligro")
 	private List<Residuos> tPeligro;
 	
    
    

}
