package co.gov.ideam.sdstrp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "TRPT_GESTION_UBICACION")
public class GestionUbicacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_GESTION_UBI")
	private String id_gestion_ubi;
	@Column(name = "NOMBRE_GESTION_UBI")
	private String nombre_gestion_ubi;

	public String getId_gestion_ubi() {
		return id_gestion_ubi;
	}

	public void setId_gestion_ubi(String id_gestion_ubi) {
		this.id_gestion_ubi = id_gestion_ubi;
	}

	public String getNombre_gestion_ubi() {
		return nombre_gestion_ubi;
	}

	public void setNombre_gestion_ubi(String nombre_gestion_ubi) {
		this.nombre_gestion_ubi = nombre_gestion_ubi;
	}

	@Override
	public String toString() {
		return "GestionUbicacion [id_gestion_ubi=" + id_gestion_ubi + ", nombre_gestion_ubi=" + nombre_gestion_ubi
				+ "]";
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gUbicacion")
	private List<Residuos> Residuos;

}
