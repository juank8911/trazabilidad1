package co.gov.ideam.sdstrp.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "TRPT_DECLARACION")
public class Declaracion implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_Declaracion")
	@SequenceGenerator(initialValue = 3,allocationSize=1,name = "sequence_Declaracion", sequenceName = "TRPT_DECLARACION_SEQ")
	@Column(name="DEC_ID")
	private int dec_id;
	
	@Column(name="DEC_GENERADOR")
	private int dec_generador;
	
	@Column(name="DEC_GEN_FECHA_GEN")
	private Date dec_gen_fecha_gen; 
	
	@Column(name="DEC_GEN_FECHA_TRN")
	private Date dec_gen_fecha_trn;
	
	@Column(name="DEC_GEN_APROBADA", columnDefinition = "varchar(3) default 'N'")
	private String dec_gen_aprobada;
	
	@Column(name="DEC_TRANSPORTADOR")
	private int dec_transportador; 
	
	@Column(name="DEC_TRN_VEHICULO")
	private String dec_trn_vehiculo;
	

	@Column(name="DEC_TRN_FECHA_TRN", columnDefinition = "Date")
	private Date dec_trn_fecha_trn; 
	
	@Column(name="DEC_TRN_FECHA_ENT", columnDefinition = "Date")
	private Date dec_trn_fecha_ent; 
	
	@Column(name="DEC_TRN_APROBADA" , columnDefinition = "varchar(3) default 'N'")
	private String dec_trn_aprobada; 
	
	@Column(name="DEC_GESTOR")
	private int dec_gestor;
	
	@Column(name="DEC_GES_FECHA_ENT", columnDefinition = "Date")
	private Date dec_ges_fecha_ent;
	
	@Column(name="DEC_GES_FECHA_GES", columnDefinition = "Date")
	private Date dec_ges_fecha_ges; 
	
	@Column(name="DEC_GES_APROBADA" , columnDefinition = "varchar(3) default 'N'")
	private String dec_ges_aprobada;
	
	
	@Column(name="DEC_GES_OBSERVACION", columnDefinition = "varchar(255) default 'SIN OBSERVACIONES'")
	private String dec_ges_observacion; 
	
	@Column(name="DEC_PROG_ID")
	private int dec_prog_id;
	
	
	
	

	public String getDec_ges_observacion() {
		return dec_ges_observacion;
	}

	public void setDec_ges_observacion(String dec_ges_observacion) {
		this.dec_ges_observacion = dec_ges_observacion;
	}

	/**
	 * @return the dec_id
	 */
	public int getDec_id() {
		return dec_id;
	}

	/**
	 * @param dec_id the dec_id to set
	 */
	public void setDec_id(int dec_id) {
		this.dec_id = dec_id;
	}

	/**
	 * @return the dec_generador
	 */
	public int getDec_generador() {
		return dec_generador;
	}

	/**
	 * @param dec_generador the dec_generador to set
	 */
	public void setDec_generador(int dec_generador) {
		this.dec_generador = dec_generador;
	}

	/**
	 * @return the dec_gen_fecha_gen
	 */
	public Date getDec_gen_fecha_gen() {
		return dec_gen_fecha_gen;
	}

	/**
	 * @param dec_gen_fecha_gen the dec_gen_fecha_gen to set
	 */
	public void setDec_gen_fecha_gen(Date dec_gen_fecha_gen) {
		this.dec_gen_fecha_gen = dec_gen_fecha_gen;
	}

	/**
	 * @return the dec_gen_fecha_trn
	 */
	public Date getDec_gen_fecha_trn() {
		return dec_gen_fecha_trn;
	}

	/**
	 * @param dec_gen_fecha_trn the dec_gen_fecha_trn to set
	 */
	public void setDec_gen_fecha_trn(Date dec_gen_fecha_trn) {
		this.dec_gen_fecha_trn = dec_gen_fecha_trn;
	}

	/**
	 * @return the dec_gen_aprobada
	 */
	public String getDec_gen_aprobada() {
		return dec_gen_aprobada;
	}

	/**
	 * @param dec_gen_aprobada the dec_gen_aprobada to set
	 */
	public void setDec_gen_aprobada(String dec_gen_aprobada) {
		this.dec_gen_aprobada = dec_gen_aprobada;
	}

	/**
	 * @return the dec_transportador
	 */
	public int getDec_transportador() {
		return dec_transportador;
	}

	/**
	 * @param dec_transportador the dec_transportador to set
	 */
	public void setDec_transportador(int dec_transportador) {
		this.dec_transportador = dec_transportador;
	}

	/**
	 * @return the dec_trn_vehiculo
	 */
	public String getDec_trn_vehiculo() {
		return dec_trn_vehiculo;
	}

	/**
	 * @param idVeh the dec_trn_vehiculo to set
	 */
	public void setDec_trn_vehiculo(String idVeh) {
		this.dec_trn_vehiculo = idVeh;
	}

	/**
	 * @return the dec_trn_fecha_trn
	 */
	public Date getDec_trn_fecha_trn() {
		return dec_trn_fecha_trn;
	}

	/**
	 * @param dec_trn_fecha_trn the dec_trn_fecha_trn to set
	 */
	public void setDec_trn_fecha_trn(Date dec_trn_fecha_trn) {
		this.dec_trn_fecha_trn = dec_trn_fecha_trn;
	}

	/**
	 * @return the dec_trn_fecha_ent
	 */
	public Date getDec_trn_fecha_ent() {
		return dec_trn_fecha_ent;
	}

	/**
	 * @param dec_trn_fecha_ent the dec_trn_fecha_ent to set
	 */
	public void setDec_trn_fecha_ent(Date dec_trn_fecha_ent) {
		this.dec_trn_fecha_ent = dec_trn_fecha_ent;
	}

	/**
	 * @return the dec_trn_aprobada
	 */
	public String getDec_trn_aprobada() {
		return dec_trn_aprobada;
	}

	/**
	 * @param dec_trn_aprobada the dec_trn_aprobada to set
	 */
	public void setDec_trn_aprobada(String dec_trn_aprobada) {
		this.dec_trn_aprobada = dec_trn_aprobada;
	}

	/**
	 * @return the dec_gestor
	 */
	public int getDec_gestor() {
		return dec_gestor;
	}

	/**
	 * @param dec_gestor the dec_gestor to set
	 */
	public void setDec_gestor(int dec_gestor) {
		this.dec_gestor = dec_gestor;
	}

	/**
	 * @return the dec_ges_fecha_ent
	 */
	public Date getDec_ges_fecha_ent() {
		return dec_ges_fecha_ent;
	}

	/**
	 * @param dec_ges_fecha_ent the dec_ges_fecha_ent to set
	 */
	public void setDec_ges_fecha_ent(Date dec_ges_fecha_ent) {
		this.dec_ges_fecha_ent = dec_ges_fecha_ent;
	}

	/**
	 * @return the dec_ges_fecha_ges
	 */
	public Date getDec_ges_fecha_ges() {
		return dec_ges_fecha_ges;
	}

	/**
	 * @param dec_ges_fecha_ges the dec_ges_fecha_ges to set
	 */
	public void setDec_ges_fecha_ges(Date dec_ges_fecha_ges) {
		this.dec_ges_fecha_ges = dec_ges_fecha_ges;
	}

	/**
	 * @return the dec_ges_aprobada
	 */
	public String getDec_ges_aprobada() {
		return dec_ges_aprobada;
	}

	/**
	 * @param dec_ges_aprobada the dec_ges_aprobada to set
	 */
	public void setDec_ges_aprobada(String dec_ges_aprobada) {
		this.dec_ges_aprobada = dec_ges_aprobada;
	}
	
	
	
	public List<DeclaracionResiduo> getDeclaracion_res() {
		return declaracion_res;
	}

	public void setDeclaracion_res(List<DeclaracionResiduo> declaracion_res) {
		this.declaracion_res = declaracion_res;
	}
	
	

	public Sede getDecSedGes() {
		return decSedGes;
	}

	public void setDecSedGes(Sede decSedGes) {
		this.decSedGes = decSedGes;
	}

	public Sede getDecSedGen() {
		return decSedGen;
	}

	public void setDecSedGen(Sede decSedGen) {
		this.decSedGen = decSedGen;
	}

	public Sede getDecSedTran() {
		return decSedTran;
	}

	public void setDecSedTran(Sede decSedTran) {
		this.decSedTran = decSedTran;
	}
	
	
	public int getDec_prog_id() {
		return dec_prog_id;
	}

	public void setDec_prog_id(int dec_prog_id) {
		this.dec_prog_id = dec_prog_id;
	}

	public Programacion getProg_dec() {
		return prog_dec;
	}

	public void setProg_dec(Programacion prog_dec) {
		this.prog_dec = prog_dec;
	}

	@Override
	public String toString() {
		return dec_id + " : " + dec_generador + " : " + dec_gen_fecha_gen + " : " + dec_gen_fecha_trn + " : "
				+ dec_gen_aprobada + " : " + dec_transportador + " : " + dec_trn_vehiculo + " : " + dec_trn_fecha_trn
				+ " : " + dec_trn_fecha_ent + " : " + dec_trn_aprobada + " : " + dec_gestor + " : " + dec_ges_fecha_ent
				+ " : " + dec_ges_fecha_ges + " : " + dec_ges_aprobada;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "declaracion_res")
	 private List<DeclaracionResiduo> declaracion_res;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	   @JoinColumn(name = "dec_generador", referencedColumnName = "sed_id", insertable=false, updatable=false)
	   private Sede decSedGen;
	
	  @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "dec_gestor", referencedColumnName = "sed_id", insertable=false, updatable=false)
	   private Sede decSedGes;
	  
	  @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "dec_transportador", referencedColumnName = "sed_id", insertable=false, updatable=false)
	   private Sede decSedTran;
	  
	   @OneToOne
	   @JoinColumn(name = "dec_prog_id", referencedColumnName = "pro_id", insertable=false, updatable=false)
	   private Programacion prog_dec;
	  
	  
	  
	  
	  

	  

	 	
	
	

}
