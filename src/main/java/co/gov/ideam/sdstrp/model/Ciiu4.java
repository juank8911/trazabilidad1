package co.gov.ideam.sdstrp.model;

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
@Table(name = "TRPT_CIIU4")
public class Ciiu4 {
//	
//	TRPT_CIIU4" 
//	   (	"CII_ID" VARCHAR2(4 BYTE), 
//		"CII_NOMBRE" VARCHAR2(200 BYTE)
	
	@Id
	@Column(name = "CII_ID")
	private String cii_id;
	
	@Column(name = "CII_NOMBRE")
	private String cii_nombre;

	public String getCii_id() {
		return cii_id;
	}

	public void setCii_id(String cii_id) {
		this.cii_id = cii_id;
	}

	public String getCii_nombre() {
		return cii_nombre;
	}

	public void setCii_nombre(String cii_nombre) {
		this.cii_nombre = cii_nombre;
	}

	@Override
	public String toString() {
		return cii_id + " : " + cii_nombre;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "empCii")
	 private List<Empresa> empCii;

}
