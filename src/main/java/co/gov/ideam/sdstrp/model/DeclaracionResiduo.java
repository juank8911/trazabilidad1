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
@Table(name = "TRPT_DECLARACION_RESIDUO")
public class DeclaracionResiduo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
			
	
//	"DER_ID" NUMBER(10,0) NOT NULL ENABLE, 
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_DeclaracionRes")
	@SequenceGenerator(initialValue = 2,allocationSize=1,name = "sequence_DeclaracionRes", sequenceName = "TRPT_DECLARACION_RESIDUO_SEQ")
	@Column(name="DER_ID")
    private int der_id;
//	"DER_DECLARACION" NUMBER,
	@Column(name="DER_DECLARACION")
	private int der_declaracion;
// DER_RES_ID
	@Column(name="DER_RES_ID")
	private int der_res_id;
//	"DER_GEN_NOMBRE" VARCHAR2(255 CHAR),
	@Column(name="DER_GEN_NOMBRE")
	private String der_gen_nombre; 
//	"DER_GEN_TIPO_RESIDUO" NUMBER, 
	@Column(name="DER_GEN_TIPO_RESIDUO")
	private String der_gen_tipo_residuo; 	
//	"DER_GEN_ESTADO_MATERIA" VARCHAR2(255 CHAR),
	@Column(name="DER_GEN_ESTADO_MATERIA")
	private String der_gen_estado_materia; 
//	"DER_GEN_TIPO_EMPAQUE" NUMBER, 
	@Column(name="DER_GEN_TIPO_EMPAQUE")
	private String der_gen_tipo_empaque;
//	"DER_GEN_NUMERO_EMPAQUES" NUMBER(10,0),
	@Column(name="DER_GEN_NUMERO_EMPAQUES")
	private int der_gen_numero_empaques;
//	"DER_GEN_TIPO_EMBALAJE" VARCHAR, 
	@Column(name="DER_GEN_TIPO_EMBALAJE")
	private String der_gen_tipo_embalaje; 
//	"DER_GEN_NUMERO_EMBALAJES" NUMBER(10,0),
	@Column(name="DER_GEN_NUMERO_EMBALAJES")
	private int der_gen_numero_embalajes;
//	"DER_GEN_TIPO_PELIGROSIDAD" VARCHAR,
	@Column(name="DER_GEN_TIPO_PELIGROSIDAD")
	private String der_gen_tipo_peligrosidad; 
//	"DER_GEN_TIPO_MANEJO" VARCHAR,
	@Column(name="DER_GEN_TIPO_MANEJO")
	private String der_gen_tipo_manejo;
//	"DER_GEN_PESO_RESIDUO" NUMBER(10,0),
	@Column(name="DER_GEN_PESO_RESIDUO")
	private int der_gen_peso_residuo;
//	"DER_TRN_TIPO_EMPAQUE" VARCHAR2(255 CHAR),
	@Column(name="DER_TRN_TIPO_EMPAQUE")
	private String der_trn_tipo_empaque; 
//	"DER_TRN_NUMERO_EMPAQUES" NUMBER(10,0), 
	@Column(name="DER_TRN_NUMERO_EMPAQUES")
	private int der_trn_numero_empaques; 
//	"DER_TRN_TIPO_EMBALAJE" VARCHAR, 
	@Column(name="DER_TRN_TIPO_EMBALAJE")
	private String der_trn_tipo_embalaje; 
//	"DER_GES_TIPO_EMBALAJE2" VARCHAR2(255 CHAR),
	@Column(name="DER_TRN_NUMERO_EMBALAJES")
	private int der_trn_numero_embalajes;
	@Column(name="DER_TRN_TIPO_EMBALAJE2")
	private String der_trn_tipo_embalaje2;
//	"DER_TRN_TIPO_PELIGROSIDAD2" VARCHAR2(255 CHAR),
	@Column(name="DER_TRN_TIPO_PELIGROSIDAD2")
	private String der_trn_tipo_peligrosidad2; 
//	"DER_GES_PESO_RESIDUO2" VARCHAR2(255 CHAR),
	@Column(name="DER_TRN_PESO_RESIDUO2")
	private int der_trn_peso_residuo2;
//	"DER_GES_TIPO_EMPAQUE2" VARCHAR2(255 CHAR), 
	@Column(name="DER_GES_TIPO_EMPAQUE2")
	private String der_ges_tipo_empaque2; 
	@Column(name="DER_GES_NUMERO_EMPAQUES2")
	private int der_ges_numero_empaques2;
//	"DER_TRN_TIPO_EMBALAJE2" NUMBER(10,0), 
	@Column(name="DER_GES_TIPO_EMBALAJE2")
	private String der_ges_tipo_embalaje2;
//	"DER_GES_NUMERO_EMBALAJES2" NUMBER(10,0),
	@Column(name="DER_GES_NUMERO_EMBALAJES2")
	private int der_ges_numero_embalajes2;
//	"DER_GES_TIPO_PELIGROSIDAD3" VARCHAR2(255 CHAR),
	@Column(name="DER_GES_TIPO_PELIGROSIDAD3")
	private String der_ges_tipo_peligrosidad3; 
//	"DER_GES_TIPO_MANEJO2" VARCHAR2(255 CHAR), 
	@Column(name="DER_GES_TIPO_MANEJO2")
	private String der_ges_tipo_manejo2;
	
	@Column(name= "DER_GES_TI_GESTION")
    private String der_ges_ti_gestion;
	
	@Column(name="DER_GES_SUBTI_GESTION")
    private String der_ges_subti_gestion;
	
//	"DER_TRN_PESO_RESIDUO2" NUMBER(10,0), 
	@Column(name="DER_GES_PESO_RESIDUO2")
	private int der_ges_peso_residuo2;
	/**
	 * @return the der_id
	 */
	public int getDer_id() {
		return der_id;
	}
	/**
	 * @param der_id the der_id to set
	 */
	public void setDer_id(int der_id) {
		this.der_id = der_id;
	}
	/**
	 * @return the der_declaracion
	 */
	public int getDer_declaracion() {
		return der_declaracion;
	}
	/**
	 * @param der_declaracion the der_declaracion to set
	 */
	public void setDer_declaracion(int der_declaracion) {
		this.der_declaracion = der_declaracion;
	}
	/**
	 * @return the der_gen_nombre
	 */
	public String getDer_gen_nombre() {
		return der_gen_nombre;
	}
	/**
	 * @param der_gen_nombre the der_gen_nombre to set
	 */
	public void setDer_gen_nombre(String der_gen_nombre) {
		this.der_gen_nombre = der_gen_nombre;
	}
	
	public int getDer_trn_numero_embalajes() {
		return der_trn_numero_embalajes;
	}
	public void setDer_trn_numero_embalajes(int der_trn_numero_embalajes) {
		this.der_trn_numero_embalajes = der_trn_numero_embalajes;
	}
	/**
	 * @return the der_gen_tipo_residuo
	 */
	public String getDer_gen_tipo_residuo() {
		return der_gen_tipo_residuo;
	}
	/**
	 * @param der_gen_tipo_residuo the der_gen_tipo_residuo to set
	 */
	public void setDer_gen_tipo_residuo(String der_gen_tipo_residuo) {
		this.der_gen_tipo_residuo = der_gen_tipo_residuo;
	}
	/**
	 * @return the der_gen_estado_materia
	 */
	public String getDer_gen_estado_materia() {
		return der_gen_estado_materia;
	}
	/**
	 * @param der_gen_estado_materia the der_gen_estado_materia to set
	 */
	public void setDer_gen_estado_materia(String der_gen_estado_materia) {
		this.der_gen_estado_materia = der_gen_estado_materia;
	}
	/**
	 * @return the der_gen_tipo_empaque
	 */
	public String getDer_gen_tipo_empaque() {
		return der_gen_tipo_empaque;
	}
	/**
	 * @param der_gen_tipo_empaque the der_gen_tipo_empaque to set
	 */
	public void setDer_gen_tipo_empaque(String der_gen_tipo_empaque) {
		this.der_gen_tipo_empaque = der_gen_tipo_empaque;
	}
	/**
	 * @return the der_gen_numero_empaques
	 */
	public int getDer_gen_numero_empaques() {
		return der_gen_numero_empaques;
	}
	/**
	 * @param der_gen_numero_empaques the der_gen_numero_empaques to set
	 */
	public void setDer_gen_numero_empaques(int der_gen_numero_empaques) {
		this.der_gen_numero_empaques = der_gen_numero_empaques;
	}
	/**
	 * @return the der_gen_tipo_embalaje
	 */
	public String getDer_gen_tipo_embalaje() {
		return der_gen_tipo_embalaje;
	}
	/**
	 * @param der_gen_tipo_embalaje the der_gen_tipo_embalaje to set
	 */
	public void setDer_gen_tipo_embalaje(String der_gen_tipo_embalaje) {
		this.der_gen_tipo_embalaje = der_gen_tipo_embalaje;
	}
	/**
	 * @return the der_gen_numero_embalajes
	 */
	public int getDer_gen_numero_embalajes() {
		return der_gen_numero_embalajes;
	}
	/**
	 * @param der_gen_numero_embalajes the der_gen_numero_embalajes to set
	 */
	public void setDer_gen_numero_embalajes(int der_gen_numero_embalajes) {
		this.der_gen_numero_embalajes = der_gen_numero_embalajes;
	}
	/**
	 * @return the der_gen_tipo_peligrosidad
	 */
	public String getDer_gen_tipo_peligrosidad() {
		return der_gen_tipo_peligrosidad;
	}
	/**
	 * @param der_gen_tipo_peligrosidad the der_gen_tipo_peligrosidad to set
	 */
	public void setDer_gen_tipo_peligrosidad(String der_gen_tipo_peligrosidad) {
		this.der_gen_tipo_peligrosidad = der_gen_tipo_peligrosidad;
	}
	/**
	 * @return the der_gen_tipo_manejo
	 */
	public String getDer_gen_tipo_manejo() {
		return der_gen_tipo_manejo;
	}
	/**
	 * @param der_gen_tipo_manejo the der_gen_tipo_manejo to set
	 */
	public void setDer_gen_tipo_manejo(String der_gen_tipo_manejo) {
		this.der_gen_tipo_manejo = der_gen_tipo_manejo;
	}
	/**
	 * @return the der_gen_peso_residuo
	 */
	public int getDer_gen_peso_residuo() {
		return der_gen_peso_residuo;
	}
	/**
	 * @param der_gen_peso_residuo the der_gen_peso_residuo to set
	 */
	public void setDer_gen_peso_residuo(int der_gen_peso_residuo) {
		this.der_gen_peso_residuo = der_gen_peso_residuo;
	}
	/**
	 * @return the der_trn_tipo_empaque
	 */
	public String getDer_trn_tipo_empaque() {
		return der_trn_tipo_empaque;
	}
	/**
	 * @param der_trn_tipo_empaque the der_trn_tipo_empaque to set
	 */
	public void setDer_trn_tipo_empaque(String der_trn_tipo_empaque) {
		this.der_trn_tipo_empaque = der_trn_tipo_empaque;
	}
	/**
	 * @return the der_trn_numero_empaques
	 */
	public int getDer_trn_numero_empaques() {
		return der_trn_numero_empaques;
	}
	/**
	 * @param der_trn_numero_empaques the der_trn_numero_empaques to set
	 */
	public void setDer_trn_numero_empaques(int der_trn_numero_empaques) {
		this.der_trn_numero_empaques = der_trn_numero_empaques;
	}
	/**
	 * @return the der_trn_tipo_embalaje
	 */
	public String getDer_trn_tipo_embalaje() {
		return der_trn_tipo_embalaje;
	}
	/**
	 * @param der_trn_tipo_embalaje the der_trn_tipo_embalaje to set
	 */
	public void setDer_trn_tipo_embalaje(String der_trn_tipo_embalaje) {
		this.der_trn_tipo_embalaje = der_trn_tipo_embalaje;
	}
	/**
	 * @return the der_trn_tipo_embalaje2
	 */
	public String getDer_trn_tipo_embalaje2() {
		return der_trn_tipo_embalaje2;
	}
	/**
	 * @param der_trn_tipo_embalaje2 the der_trn_tipo_embalaje2 to set
	 */
	public void setDer_trn_tipo_embalaje2(String der_trn_tipo_embalaje2) {
		this.der_trn_tipo_embalaje2 = der_trn_tipo_embalaje2;
	}
	/**
	 * @return the der_trn_tipo_peligrosidad2
	 */
	public String getDer_trn_tipo_peligrosidad2() {
		return der_trn_tipo_peligrosidad2;
	}
	/**
	 * @param der_trn_tipo_peligrosidad2 the der_trn_tipo_peligrosidad2 to set
	 */
	public void setDer_trn_tipo_peligrosidad2(String der_trn_tipo_peligrosidad2) {
		this.der_trn_tipo_peligrosidad2 = der_trn_tipo_peligrosidad2;
	}
	/**
	 * @return the der_trn_peso_residuo2
	 */
	public int getDer_trn_peso_residuo2() {
		return der_trn_peso_residuo2;
	}
	/**
	 * @param der_trn_peso_residuo2 the der_trn_peso_residuo2 to set
	 */
	public void setDer_trn_peso_residuo2(int der_trn_peso_residuo2) {
		this.der_trn_peso_residuo2 = der_trn_peso_residuo2;
	}
	/**
	 * @return the der_ges_tipo_empaque2
	 */
	public String getDer_ges_tipo_empaque2() {
		return der_ges_tipo_empaque2;
	}
	/**
	 * @param der_ges_tipo_empaque2 the der_ges_tipo_empaque2 to set
	 */
	public void setDer_ges_tipo_empaque2(String der_ges_tipo_empaque2) {
		this.der_ges_tipo_empaque2 = der_ges_tipo_empaque2;
	}
	/**
	 * @return the der_ges_numero_empaques2
	 */
	public int getDer_ges_numero_empaques2() {
		return der_ges_numero_empaques2;
	}
	/**
	 * @param der_ges_numero_empaques2 the der_ges_numero_empaques2 to set
	 */
	public void setDer_ges_numero_empaques2(int der_ges_numero_empaques2) {
		this.der_ges_numero_empaques2 = der_ges_numero_empaques2;
	}
	/**
	 * @return the der_ges_tipo_embalaje2
	 */
	public String getDer_ges_tipo_embalaje2() {
		return der_ges_tipo_embalaje2;
	}
	/**
	 * @param der_ges_tipo_embalaje2 the der_ges_tipo_embalaje2 to set
	 */
	public void setDer_ges_tipo_embalaje2(String der_ges_tipo_embalaje2) {
		this.der_ges_tipo_embalaje2 = der_ges_tipo_embalaje2;
	}
	/**
	 * @return the der_ges_numero_embalajes2
	 */
	public int getDer_ges_numero_embalajes2() {
		return der_ges_numero_embalajes2;
	}
	/**
	 * @param der_ges_numero_embalajes2 the der_ges_numero_embalajes2 to set
	 */
	public void setDer_ges_numero_embalajes2(int der_ges_numero_embalajes2) {
		this.der_ges_numero_embalajes2 = der_ges_numero_embalajes2;
	}
	/**
	 * @return the der_ges_tipo_peligrosidad3
	 */
	public String getDer_ges_tipo_peligrosidad3() {
		return der_ges_tipo_peligrosidad3;
	}
	/**
	 * @param der_ges_tipo_peligrosidad3 the der_ges_tipo_peligrosidad3 to set
	 */
	public void setDer_ges_tipo_peligrosidad3(String der_ges_tipo_peligrosidad3) {
		this.der_ges_tipo_peligrosidad3 = der_ges_tipo_peligrosidad3;
	}
	/**
	 * @return the der_ges_tipo_manejo2
	 */
	public String getDer_ges_tipo_manejo2() {
		return der_ges_tipo_manejo2;
	}
	/**
	 * @param der_ges_tipo_manejo2 the der_ges_tipo_manejo2 to set
	 */
	public void setDer_ges_tipo_manejo2(String der_ges_tipo_manejo2) {
		this.der_ges_tipo_manejo2 = der_ges_tipo_manejo2;
	}
	/**
	 * @return the der_ges_peso_residuo2
	 */
	public int getDer_ges_peso_residuo2() {
		return der_ges_peso_residuo2;
	}
	/**
	 * @param der_ges_peso_residuo2 the der_ges_peso_residuo2 to set
	 */
	public void setDer_ges_peso_residuo2(int der_ges_peso_residuo2) {
		this.der_ges_peso_residuo2 = der_ges_peso_residuo2;
	}
	
	
	
	public int getDer_res_id() {
		return der_res_id;
	}
	public void setDer_res_id(int der_res_id) {
		this.der_res_id = der_res_id;
	}
	public Declaracion getDeclaracion_res() {
		return declaracion_res;
	}
	public void setDeclaracion_res(Declaracion declaracion_res) {
		this.declaracion_res = declaracion_res;
	}

 	public TipoResiduos getTipoResDec() {
	return tipoResDec;
}
public void setTipoResDec(TipoResiduos tipoResDec) {
	this.tipoResDec = tipoResDec;
}
public Residuos getResiduosDecl() {
	return residuosDecl;
}
public void setResiduosDecl(Residuos residuosDecl) {
	this.residuosDecl = residuosDecl;
}

public TipoEmbalaje getTipoEmbDec() {
	return tipoEmbDec;
}
public void setTipoEmbDec(TipoEmbalaje tipoEmbDec) {
	this.tipoEmbDec = tipoEmbDec;
}
public TipoEmpaque getTipoEmpDec() {
	return tipoEmpDec;
}
public void setTipoEmpDec(TipoEmpaque tipoEmpDec) {
	this.tipoEmpDec = tipoEmpDec;
}


	public String getDec_ges_ti_gestion() {
	return der_ges_ti_gestion;
}
public void setDec_ges_ti_gestion(String dec_ges_ti_gestion) {
	this.der_ges_ti_gestion = dec_ges_ti_gestion;
}
public String getDec_ges_subti_gestion() {
	return der_ges_subti_gestion;
}
public void setDec_ges_subti_gestion(String dec_ges_subti_gestion) {
	this.der_ges_subti_gestion = dec_ges_subti_gestion;
}
public TipoGestion getTipoGes() {
	return tipoGes;
}
public void setTipoGes(TipoGestion tipoGes) {
	this.tipoGes = tipoGes;
}
public TipoManejo getSubtipoGes() {
	return subtipoGes;
}
public void setSubtipoGes(TipoManejo subtipoGes) {
	this.subtipoGes = subtipoGes;
}
	@Override
	public String toString() {
		return der_id + " : " + der_declaracion + " : " + der_gen_nombre + " : " + der_gen_tipo_residuo + " : "
				+ der_gen_estado_materia + " : " + der_gen_tipo_empaque + " : " + der_gen_numero_empaques + " : "
				+ der_gen_tipo_embalaje + " : " + der_gen_numero_embalajes + " : " + der_gen_tipo_peligrosidad + " : "
				+ der_gen_tipo_manejo + " : " + der_gen_peso_residuo + " : " + der_trn_tipo_empaque + " : "
				+ der_trn_numero_empaques + " : " + der_trn_tipo_embalaje + " : " + der_trn_numero_embalajes + " : "
				+ der_trn_tipo_embalaje2 + " : " + der_trn_tipo_peligrosidad2 + " : " + der_trn_peso_residuo2 + " : "
				+ der_ges_tipo_empaque2 + " : " + der_ges_numero_empaques2 + " : " + der_ges_tipo_embalaje2 + " : "
				+ der_ges_numero_embalajes2 + " : " + der_ges_tipo_peligrosidad3 + " : " + der_ges_tipo_manejo2 + " : "
				+ der_ges_peso_residuo2;
	}
	
	



		@ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "DER_DECLARACION", referencedColumnName = "DEC_ID", insertable=false, updatable=false)
	   private Declaracion declaracion_res;
	 	
	 	@ManyToOne(fetch=FetchType.LAZY)
	 	@JoinColumn(name = "DER_GEN_TIPO_RESIDUO", referencedColumnName = "TRE_ID", insertable=false, updatable=false)
	 	private TipoResiduos tipoResDec;
	 	
	 	@ManyToOne(fetch=FetchType.LAZY)
	 	@JoinColumn(name = "DER_GEN_TIPO_EMBALAJE", referencedColumnName = "TEM_ID", insertable=false, updatable=false)
	 	private TipoEmbalaje tipoEmbDec;
	 	
	 	@ManyToOne(fetch=FetchType.LAZY)
	 	@JoinColumn(name = "DER_GEN_TIPO_EMPAQUE", referencedColumnName = "TEP_ID", insertable=false, updatable=false)
	 	private TipoEmpaque tipoEmpDec;
	 	
	 	
	 	@ManyToOne(fetch=FetchType.LAZY)
	 	@JoinColumn(name = "DER_RES_ID", referencedColumnName = "RES_ID", insertable=false, updatable=false)
	 	private Residuos residuosDecl;
	 	
		  @ManyToOne(fetch=FetchType.LAZY)
		   @JoinColumn(name = "dec_ges_ti_gestion", referencedColumnName = "id_tip_gestion", insertable=false, updatable=false)
		   private TipoGestion tipoGes;
		  
		  @ManyToOne(fetch=FetchType.LAZY)
		   @JoinColumn(name = "dec_ges_subti_gestion", referencedColumnName = "tma_id", insertable=false, updatable=false)
		   private TipoManejo subtipoGes;
	 	
		  @ManyToOne(fetch=FetchType.LAZY)
		   @JoinColumn(name = "der_gen_estado_materia", referencedColumnName = "ema_id", insertable=false, updatable=false)
		   private EstadoMateria deReEstado;		  
	 	
	 	
		

}
