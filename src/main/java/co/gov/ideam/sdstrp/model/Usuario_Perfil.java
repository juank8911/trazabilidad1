package co.gov.ideam.sdstrp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "TRPT_USUARIO_PERFIL")
public class Usuario_Perfil implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_UsuPer")
	@SequenceGenerator(initialValue = 5,allocationSize=1,name = "sequence_UsuPer", sequenceName = "TRPT_USUARIO_PERFIL_SEQ")
	@Column(name="ID_USU_PER")
	private int id_usu_per;
	
	@Column(name="ID_USR")
	private int id_usr;
	
	@Column(name="ID_PERFIL")
	private int id_perfil;

	public int getId_usu_per() {
		return id_usu_per;
	}

	public int getId_usr() {
		return id_usr;
	}

	public void setId_usr(int id_usr) {
		this.id_usr = id_usr;
	}

	public int getId_perfil() {
		return id_perfil;
	}

	public void setId_perfil(int id_perfil) {
		this.id_perfil = id_perfil;
	}
	
	

	public Perfil getPerUsu() {
		return perUsu;
	}

	public void setPerUsu(Perfil perUsu) {
		this.perUsu = perUsu;
	}

	public Usuario getUsuaPer() {
		return usuaPer;
	}

	public void setUsuaPer(Usuario usuaPer) {
		this.usuaPer = usuaPer;
	}

	public void setId_usu_per(int id_usu_per) {
		this.id_usu_per = id_usu_per;
	}

	@Override
	public String toString() {
		return id_usu_per + " : " + id_usr + " : " + id_perfil;
	}
	

	  

	  
	  @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID_PERFIL", insertable=false, updatable=false)
	   private Perfil perUsu;
	  
	  @ManyToOne(cascade = {CascadeType.MERGE} , fetch=FetchType.LAZY)
	   @JoinColumn(name = "ID_USR", referencedColumnName = "ID_USR", insertable=false, updatable=false)
	   private Usuario usuaPer;
	

}
