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
@Table(name = "TRPT_RESIDUO")
public class Residuos implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_Residuos")
	@SequenceGenerator(initialValue = 5,allocationSize=1,name = "sequence_Residuos", sequenceName = "TRPT_RESIDUO_SEQ")
	@Column(name="RES_ID")
    private int res_id;
	
	@Column(name="RES_NOMBRE")
    private String res_nombre;
	
	@Column(name="RES_TIPO_RESIDUO" )
    private String res_tipo_residuo;
	
	@Column(name="RES_ESTADO_MATERIA" )
    private String res_estado_materia;
	
	@Column(name="RES_GESTION_UBICA" )
    private String res_gestion_ubica;
	
	@Column(name="RES_TIPO_EMPAQUE")
    private String res_tipo_empaque;
	
	@Column(name="RES_TIPO_EMBALAJE")
    private String res_tipo_embalaje;
	
	@Column(name= "RES_TIPO_GESTION")
    private String res_tipo_gestion;
	
	@Column(name="RES_PELIGROSIDAD")
    private String res_peligrosidad;
	
	@Column(name="RES_SUB_TIP_GESTION")
    private String res_sub_tip_gestion;
	
	@Column(name="RES_TRANSPORTADOR")
    private String res_transportador;
	
	@Column(name="RES_SEDE_TRANSPORTE")
    private int res_sede_transporte;
	
	@Column(name="RES_GESTOR")
    private int res_gestor;
	
	@Column(name="RES_SEDE_GESTOR")
    private int res_sede_gestor;
	
	@Column(name="RES_SEDE_GENERADOR")
    private int res_sede_generador;
	
	
	
	public int getRes_id() {
		return res_id;
	}
	public void setRes_id(int res_id) {
		this.res_id = res_id;
	}
	public String getRes_nombre() {
		return res_nombre;
	}
	public void setRes_nombre(String res_nombre) {
		this.res_nombre = res_nombre;
	}
	public String getRes_tipo_residuo() {
		return res_tipo_residuo;
	}
	public void setRes_tipo_residuo(String res_id_tipo_residuo) {
		this.res_tipo_residuo = res_id_tipo_residuo;
	}
	public String getRes_estado_materia() {
		return res_estado_materia;
	}
	public void setRes_estado_materia(String res_estado_materia) {
		this.res_estado_materia = res_estado_materia;
	}
	public String getRes_gestion_ubica() {
		return res_gestion_ubica;
	}
	public void setRes_gestion_ubica(String res_gestion_ubica) {
		this.res_gestion_ubica = res_gestion_ubica;
	}
	public String getRes_tipo_empaque() {
		return res_tipo_empaque;
	}
	public void setRes_tipo_empaque(String res_tipo_empaque) {
		this.res_tipo_empaque = res_tipo_empaque;
	}
	public String getRes_tipo_embalaje() {
		return res_tipo_embalaje;
	}
	public void setRes_tipo_embalaje(String res_tipo_embalaje) {
		this.res_tipo_embalaje = res_tipo_embalaje;
	}
	public String getRes_tipo_gestion() {
		return res_tipo_gestion;
	}
	public void setRes_tipo_gestion(String res_tipo_gestion) {
		this.res_tipo_gestion = res_tipo_gestion;
	}
	public String getRes_peligrosidad() {
		return res_peligrosidad;
	}
	public void setRes_peligrosidad(String res_peligrosidad) {
		this.res_peligrosidad = res_peligrosidad;
	}
	public String getRes_sub_tip_gestion() {
		return res_sub_tip_gestion;
	}
	public void setRes_sub_tip_gestion(String res_sub_tip_gestion) {
		this.res_sub_tip_gestion = res_sub_tip_gestion;
	}
	public String getRes_transportador() {
		return res_transportador;
	}
	public void setRes_transportador(String res_id_transportador) {
		this.res_transportador = res_id_transportador;
	}
	public int getRes_sede_transporte() {
		return res_sede_transporte;
	}
	public void setRes_sede_transporte(int res_sede_transporte) {
		this.res_sede_transporte = res_sede_transporte;
	}
	public int getRes_id_gestor() {
		return res_gestor;
	}
	public void setRes_id_gestor(int res_id_gestor) {
		this.res_gestor = res_id_gestor;
	}
	public int getRes_sede_gestor() {
		return res_sede_gestor;
	}
	public void setRes_sede_gestor(int res_sede_gestor) {
		this.res_sede_gestor = res_sede_gestor;
	}
	
	public int getRes_sede_generador() {
		return res_sede_generador;
	}
	public void setRes_sede_generador(int res_sede_generador) {
		this.res_sede_generador = res_sede_generador;
	}
	
	
	public int getRes_gestor() {
		return res_gestor;
	}
	public void setRes_gestor(int res_gestor) {
		this.res_gestor = res_gestor;
	}
	public TipoResiduos gettResiduo() {
		return tResiduo;
	}
	public void settResiduo(TipoResiduos tResiduo) {
		this.tResiduo = tResiduo;
	}
	public GestionUbicacion getgUbicacion() {
		return gUbicacion;
	}
	public void setgUbicacion(GestionUbicacion gUbicacion) {
		this.gUbicacion = gUbicacion;
	}
	public TipoGestion gettGestion() {
		return tGestion;
	}
	public void settGestion(TipoGestion tGestion) {
		this.tGestion = tGestion;
	}
	public TipoManejo gettManejo() {
		return tManejo;
	}
	public void settManejo(TipoManejo tManejo) {
		this.tManejo = tManejo;
	}
	public Sede getSedeRes() {
		return sedeRes;
	}
	public void setSedeRes(Sede sedeRes) {
		this.sedeRes = sedeRes;
	}
	public Sede getSedeGest() {
		return sedeGest;
	}
	public void setSedeGest(Sede sedeGest) {
		this.sedeGest = sedeGest;
	}
	public Sede getSedeTrans() {
		return sedeTrans;
	}
	public void setSedeTrans(Sede sedeTrans) {
		this.sedeTrans = sedeTrans;
	}
	public TipoEmpaque getjTipoEmp() {
		return jTipoEmp;
	}
	public void setjTipoEmp(TipoEmpaque jTipoEmp) {
		this.jTipoEmp = jTipoEmp;
	}
	public TipoEmbalaje getjTipoEmb() {
		return jTipoEmb;
	}
	public void setjTipoEmb(TipoEmbalaje jTipoEmb) {
		this.jTipoEmb = jTipoEmb;
	}
	
	
	
	
	
public List<DeclaracionResiduo> getResiduosDecl() {
		return residuosDecl;
	}
	public void setResiduosDecl(List<DeclaracionResiduo> residuosDecl) {
		this.residuosDecl = residuosDecl;
	}
	
	
	public EstadoMateria getEstadoM() {
		return estadoM;
	}
	public void setEstadoM(EstadoMateria estadoM) {
		this.estadoM = estadoM;
	}
	
	
	public TipoPeligrosidad gettPeligro() {
		return tPeligro;
	}
	public void settPeligro(TipoPeligrosidad tPeligro) {
		this.tPeligro = tPeligro;
	}
	//	public List<DeclaracionResiduo> getResiduosDecl() {
//		return residuosDecl;
//	}
//	public void setResiduosDecl(List<DeclaracionResiduo> residuosDecl) {
//		this.residuosDecl = residuosDecl;
//	}
	
	
	@Override
	public String toString() {
		return res_id + " : " + res_nombre + " : " + res_tipo_residuo + " : " + res_estado_materia + " : "
				+ res_gestion_ubica + " : " + res_tipo_empaque + " : " + res_tipo_embalaje + " : " + res_tipo_gestion
				+ " : " + res_peligrosidad + " : " + res_sub_tip_gestion + " : " + res_transportador + " : "
				+ res_sede_transporte + " : " + res_gestor + " : " + res_sede_gestor + " : " + res_sede_generador;
	}
	
	
	

	   @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "res_tipo_residuo", referencedColumnName = "tre_id", insertable=false, updatable=false)
	   private TipoResiduos tResiduo;
	   
	   @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "res_gestion_ubica", referencedColumnName = "id_gestion_ubi", insertable=false, updatable=false)
	   private GestionUbicacion gUbicacion;
	   
	   @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "res_tipo_gestion", referencedColumnName = "id_tip_gestion", insertable=false, updatable=false)
	   private TipoGestion tGestion;
	   
	   @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "res_sub_tip_gestion", referencedColumnName = "tma_id", insertable=false, updatable=false)
	   private TipoManejo tManejo;
	   
	   @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "res_sede_generador", referencedColumnName = "sed_id", insertable=false, updatable=false)
	   private Sede sedeRes;
	   
	   @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "res_sede_gestor", referencedColumnName = "sed_id", insertable=false, updatable=false)
	   private Sede sedeGest;
	   
	   @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "res_sede_transporte", referencedColumnName = "sed_id", insertable=false, updatable=false)
	   private Sede sedeTrans;
	   
	   @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "res_tipo_empaque", referencedColumnName = "tep_id", insertable=false, updatable=false)
	   private TipoEmpaque jTipoEmp;
	   
	   @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "res_tipo_embalaje", referencedColumnName = "tem_id", insertable=false, updatable=false)
	   private TipoEmbalaje jTipoEmb;
	   
	   @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "res_estado_materia", referencedColumnName = "ema_id", insertable=false, updatable=false)
	   private EstadoMateria estadoM;
	   
	   @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "res_peligrosidad", referencedColumnName = "TPE_ID", insertable=false, updatable=false)
	   private TipoPeligrosidad tPeligro;
	   
	 	@OneToMany(cascade = CascadeType.ALL , mappedBy = "residuosDecl")
	 	private List<DeclaracionResiduo> residuosDecl;
	   
	   
	
	

}
