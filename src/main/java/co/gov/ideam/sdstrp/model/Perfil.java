package co.gov.ideam.sdstrp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "trpt_perfil")
public class Perfil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID_PERFIL")
	private int id_perfil;

	@Column(name = "PERFIL")
	private String perfil;

	public int getId_perfil() {
		return id_perfil;
	}

	public void setId_perfil(int id_perfil) {
		this.id_perfil = id_perfil;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}


	public List<Usuario_Perfil> getPerUsu() {
		return perUsu;
	}

	public void setPerUsu(List<Usuario_Perfil> perUsu) {
		this.perUsu = perUsu;
	}

	@Override
	public String toString() {
		return id_perfil + " : " + perfil;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "perUsu")
	private List<Usuario_Perfil> perUsu;

}
