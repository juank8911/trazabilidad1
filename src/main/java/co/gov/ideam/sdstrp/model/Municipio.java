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

@Entity
@XmlRootElement
@Table(name = "TRPT_MUNICIPIO")
public class Municipio {
	
//	"ID_MUNIC" NUMBER, 
//	"ID_MUNIC_DEPT" NUMBER, 
//	"MUNIC_NOMBRE" VARCHAR2(100 BYTE)
	
	
	@Id
	@GeneratedValue
	@Column(name="ID_MUNIC")
	private int id_munic;
	
	@Column(name="ID_MUNIC_DEPT")
	private int id_munic_dept;
	
	@Column(name="MUNIC_NOMBRE")
	private String munic_nombre;

	public int getId_munic() {
		return id_munic;
	}

	public void setId_munic(int id_munic) {
		this.id_munic = id_munic;
	}

	public int getId_munic_dept() {
		return id_munic_dept;
	}

	public void setId_munic_dept(int id_munic_dept) {
		this.id_munic_dept = id_munic_dept;
	}

	public String getMunic_nombre() {
		return munic_nombre;
	}

	public void setMunic_nombre(String munic_nombre) {
		this.munic_nombre = munic_nombre;
	}
	
	
	

	/**
	 * @param sedMunic the sedMunic to set
	 */
	public void setSedMunic(List<Sede> sedMunic) {
		this.sedMunic = sedMunic;
	}

	@Override
	public String toString() {
		return id_munic + " : " + id_munic_dept + " : " + munic_nombre;
	}
	
	public List<Sede> getSedMunic() {
		return sedMunic;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sedMunic")
	 private List<Sede> sedMunic;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "emp_mun")
	 private List<Empresa> emp_mun;
	
	

}
