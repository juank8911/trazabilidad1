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
@Table(name = "TRPT_USUARIO")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
	@SequenceGenerator(initialValue = 5,allocationSize=1,name = "sequence", sequenceName = "TRPT_USUARIO_SEQ")
	@Column(name = "ID_USR")
	private int id_usr;

	@Column(name = "USR_NOMBRE")
	private String usr_nombre;

	@Column(name = "USR_CLAVE")
	private String usr_clave;

	@Column(name = "USR_ESTADO")
	private int usr_estado;


	@Column(name = "ID_USR_AUT")
	private int id_usr_aut;

	@Column(name = "USR_TIP_IDENT")
	private int usr_tip_ident;

	@Column(name = "USR_DOCUMENTO")
	private int usr_documento;

	@Column(name = "USR_ID_SEDE")
	private int usr_id_sede;

	@Column(name = "NUMERO_SESION")
	private int numero_sesion;

	@Column(name = "USR_NOM_PERSONA")
	private String usr_nom_persona;

	@Column(name = "USR_APELLIDO")
	private String usr_apellido;

	@Column(name = "USR_TELEFONO")
	private String usr_telefono;
	
	@Column(name = "USR_CARGO")
	private String usr_cargo;
	

	@Column(name = "USR_EMAIL")
	private String usr_email;

	@ManyToOne(cascade = {CascadeType.MERGE} , fetch = FetchType.LAZY)
	@JoinColumn(name = "USR_ID_SEDE", referencedColumnName = "SED_ID", insertable = false, updatable = false)
	private Sede sedeUsu;

	
	@ManyToOne(cascade = {CascadeType.MERGE} ,fetch = FetchType.LAZY)
	@JoinColumn(name = "USR_TIP_IDENT", referencedColumnName = "TDO_ID", insertable = false, updatable = false)
	private TipoDocumento tDocumento;

	
	@OneToMany(cascade = {CascadeType.MERGE} , fetch = FetchType.LAZY, mappedBy = "usuaPer")
	private List<Usuario_Perfil> usuaPer;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarioR")
//	 private List<Declaracion> declaraciones;

//   @ManyToOne(fetch=FetchType.LAZY)
//   @JoinColumn(name = "ID_USR_PERFIL", referencedColumnName = "DEC_GENERADOR", insertable=false, updatable=false)
//   private Declaracion declaracion;

	public int getId_usr() {
		return id_usr;
	}

	public void setId_usr(int id_usr) {
		this.id_usr = id_usr;
	}

	public String getUsr_nombre() {
		return usr_nombre;
	}

	public void setUsr_nombre(String usr_nombre) {
		this.usr_nombre = usr_nombre;
	}

	public String getUsr_clave() {
		return usr_clave;
	}

	public void setUsr_clave(String usr_clave) {
		this.usr_clave = usr_clave;
	}

	public int getUsr_estado() {
		return usr_estado;
	}

	public void setUsr_estado(int usr_estado) {
		this.usr_estado = usr_estado;
	}

	public int getId_usr_aut() {
		return id_usr_aut;
	}

	public void setId_usr_aut(int id_usr_aut) {
		this.id_usr_aut = id_usr_aut;
	}

	public int getUsr_tip_ident() {
		return usr_tip_ident;
	}

	public void setUsr_tip_ident(int usr_tip_ident) {
		this.usr_tip_ident = usr_tip_ident;
	}

	public int getUsr_documento() {
		return usr_documento;
	}

	public void setUsr_documento(int usr_documento) {
		this.usr_documento = usr_documento;
	}

	public int getUsr_id_sede() {
		return usr_id_sede;
	}

	public void setUsr_id_sede(int usr_id_sede) {
		this.usr_id_sede = usr_id_sede;
	}

	public int getNumero_sesion() {
		return numero_sesion;
	}

	public void setNumero_sesion(int numero_sesion) {
		this.numero_sesion = numero_sesion;
	}

	public String getUsr_nom_persona() {
		return usr_nom_persona;
	}

	public void setUsr_nom_persona(String usr_nom_persona) {
		this.usr_nom_persona = usr_nom_persona;
	}

	public String getUsr_apellido() {
		return usr_apellido;
	}

	public void setUsr_apellido(String usr_apellido) {
		this.usr_apellido = usr_apellido;
	}

	public String getUsr_telefono() {
		return usr_telefono;
	}

	public void setUsr_telefono(String usr_telefono) {
		this.usr_telefono = usr_telefono;
	}

	public String getUsr_email() {
		return usr_email;
	}

	public void setUsr_email(String usr_email) {
		this.usr_email = usr_email;
	}
	
	
	public Sede getSedeUsu() {
		return sedeUsu;
	}

	public void setSedeUsu(Sede sedeUsu) {
		this.sedeUsu = sedeUsu;
	}

	public TipoDocumento gettDocumento() {
		return tDocumento;
	}

	public void settDocumento(TipoDocumento tDocumento) {
		this.tDocumento = tDocumento;
	}

	public List<Usuario_Perfil> getUsuaPer() {
		return usuaPer;
	}

	public void setUsuaPer(List<Usuario_Perfil> usuaPer) {
		this.usuaPer = usuaPer;
	}
	
	

	public String getUsr_cargo() {
		return usr_cargo;
	}

	public void setUsr_cargo(String usr_cargo) {
		this.usr_cargo = usr_cargo;
	}

	@Override
	public String toString() {
		return id_usr + " : " + usr_nombre + " : " + usr_clave + " : " + usr_estado + " : "
				+ id_usr_aut + " : " + usr_tip_ident + " : " + usr_documento + " : " + usr_id_sede + " : "
				+ numero_sesion + " : " + usr_nom_persona + " : " + usr_apellido + " : " + usr_telefono + " : "
				+ usr_email;
	}
	
}