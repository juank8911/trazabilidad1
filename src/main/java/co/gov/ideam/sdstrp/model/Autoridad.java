package co.gov.ideam.sdstrp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "TRPT_AUTORIDAD")
public class Autoridad implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="ID_AUT" )
	private int id_aut;
	
	@Column(name="AUT_NOMBRE")
    private String aut_nombre;
	
	@Column(name="AUT_NUMERO1" )
    private int aut_numero1;
	
	@Column(name="AUT_NUMERO2")
    private int aut_numero2;
	@Column(name="AUT_DEPARTAMENTO")
	private int aut_depertamento;
	@Column(name="AUT_MUNICIPIO")
	private int aut_municipio;
	@Column(name="AUT_DIRECCION")
	private String aut_direccion;
	
	public int getId_aut() {
		return id_aut;
	}
	public void setId_aut(int id_aut) {
		this.id_aut = id_aut;
	}
	public String getAut_nombre() {
		return aut_nombre;
	}
	public void setAut_nombre(String aut_nombre) {
		this.aut_nombre = aut_nombre;
	}
	public int getAut_numero1() {
		return aut_numero1;
	}
	public void setAut_numero1(int aut_numero1) {
		this.aut_numero1 = aut_numero1;
	}
	public int getAut_numero2() {
		return aut_numero2;
	}
	public void setAut_numero2(int aut_numero2) {
		this.aut_numero2 = aut_numero2;
	}
	public int getAut_depertamento() {
		return aut_depertamento;
	}
	public void setAut_depertamento(int aut_depertamento) {
		this.aut_depertamento = aut_depertamento;
	}
	public int getAut_municipio() {
		return aut_municipio;
	}
	public void setAut_municipio(int aut_municipio) {
		this.aut_municipio = aut_municipio;
	}
	public String getAut_direccion() {
		return aut_direccion;
	}
	public void setAut_direccion(String aut_direccion) {
		this.aut_direccion = aut_direccion;
	}
	
	@Override
	public String toString() {
		return "Autoridad [id_aut=" + id_aut + ", aut_nombre=" + aut_nombre + ", aut_numero1=" + aut_numero1
				+ ", aut_numero2=" + aut_numero2 + ", aut_depertamento=" + aut_depertamento + ", aut_municipio="
				+ aut_municipio + ", aut_direccion=" + aut_direccion + "]";
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "autoridad")
	 private List<Autorizaciones> autorizaciones;
	

}
