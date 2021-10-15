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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
@Table(name = "TRPT_RUTA")
public class Rutas implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_Ruta")
	@SequenceGenerator(initialValue = 5,allocationSize=1,name = "sequence_Ruta", sequenceName = "TRPT_RUTA_SEQ")
	@Column(name="ID_RUTA")
	private int id_ruta;
	@Column(name="RUT_NOMBRE")
	private String rut_nombre;
	@Column(name="RUT_SED_GENE")
	private int rut_sed_gene;

	public int getId_ruta() {
		return id_ruta;
	}
	public void setId_ruta(int id_ruta) {
		this.id_ruta = id_ruta;
	}
	public String getRut_nombre() {
		return rut_nombre;
	}
	public void setRut_nombre(String rut_nombre) {
		this.rut_nombre = rut_nombre;
	}

	
	public int getRut_sed_gene() {
		return rut_sed_gene;
	}
	public void setRut_sed_gene(int rut_sed_gene) {
		this.rut_sed_gene = rut_sed_gene;
	}
	@Override
	public String toString() {
		return id_ruta + " : " + rut_nombre + " : " + rut_sed_gene;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "RutasRGen")
	 private List<RutaSedeTran> listRutasRGen;
	
	

}
