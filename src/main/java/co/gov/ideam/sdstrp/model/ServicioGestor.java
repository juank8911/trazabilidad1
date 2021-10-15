package co.gov.ideam.sdstrp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "TRPT_SERVICIOS_GESTOR")
public class ServicioGestor implements Serializable {
	
//	"SG_ID" NUMBER NOT NULL ENABLE, 
//	"SG_CORRIENTE_RESIDUO" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
//	"SG_GESTION" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
//	"SG_SUB_GESTION" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
//	"SG_GESTOR" NUMBER NOT NULL ENABLE,
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="SG_ID")
	private int sg_id;
	
	@Column(name="SG_CORRIENTE_RESIDUO")
	private String sg_corriente_residuo;
	
	@Column(name="SG_GESTION")
	private String sg_gestion;
	
	@Column(name="SG_SUB_GESTION")
	private String sg_sub_gestion;
	
	@Column(name="SG_GESTOR")
	private int sg_gestor;

	public int getSg_id() {
		return sg_id;
	}

	public void setSg_id(int sg_id) {
		this.sg_id = sg_id;
	}

	public String getSg_corriente_residuo() {
		return sg_corriente_residuo;
	}

	public void setSg_corriente_residuo(String sg_corriente_residuo) {
		this.sg_corriente_residuo = sg_corriente_residuo;
	}

	public String getSg_gestion() {
		return sg_gestion;
	}

	public void setSg_gestion(String sg_gestion) {
		this.sg_gestion = sg_gestion;
	}

	public String getSg_sub_gestion() {
		return sg_sub_gestion;
	}

	public void setSg_sub_gestion(String sg_sub_gestion) {
		this.sg_sub_gestion = sg_sub_gestion;
	}

	public int getSg_gestor() {
		return sg_gestor;
	}

	public void setSg_gestor(int sg_gestor) {
		this.sg_gestor = sg_gestor;
	}

	@Override
	public String toString() {
		return "ServicioGestor [sg_id=" + sg_id + ", sg_corriente_residuo=" + sg_corriente_residuo + ", sg_gestion="
				+ sg_gestion + ", sg_sub_gestion=" + sg_sub_gestion + ", sg_gestor=" + sg_gestor + "]";
	} 
	
	  @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "sg_corriente_residuo", referencedColumnName = "tre_id", insertable=false, updatable=false)
	   private TipoResiduos tiResiduos;
	  
	  @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "sg_gestion", referencedColumnName = "id_tip_gestion", insertable=false, updatable=false)
	   private TipoGestion tiGestion;
	  
	  @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "sg_sub_gestion", referencedColumnName = "tma_id", insertable=false, updatable=false)
	   private TipoManejo tiManejo;
	

}
