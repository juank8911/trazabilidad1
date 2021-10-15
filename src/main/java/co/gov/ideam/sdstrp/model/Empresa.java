package co.gov.ideam.sdstrp.model;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "TRPT_EMPRESA")
public class Empresa implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	"EMP_ID" NUMBER, 
//	"EMP_RAZON_SOCIAL" VARCHAR2(200 BYTE), 
//	"EMP_NOMBRE_COMERCIAL" VARCHAR2(200 BYTE), 
//	"EMP_NUMERO_DOCUMENTO" NUMBER, 
//	"EMP_CIIU4" VARCHAR2(4 BYTE), 
//	"EMP_CC_REPRESEN" NUMBER, 
//	"EMP_REP_EMAIL" VARCHAR2(200 BYTE), 
//	"EMP_REP_NOMBRE" VARCHAR2(200 BYTE), 
//	"EMP_TIP_DOCU" NUMBER DEFAULT 3, 
//	"EMP_ID_UBICA" NUMBER, 
//	"EMP_TELEFONO" NUMBER, 
//	"EMP_EXT" NUMBER
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_Empresa")
	@SequenceGenerator(initialValue = 8,allocationSize=1, name = "sequence_Empresa", sequenceName = "TRPT_EMPRESA_SEQ")
	@Column(name="EMP_ID")
	private int emp_id;
	
	@Column(name="EMP_RAZON_SOCIAL")
	private String emp_razon_social;
	
	@Column(name="EMP_NOMBRE_COMERCIAL")
	private String emp_nombre_comercial;
	
	@Column(name="EMP_NUMERO_DOCUMENTO")
	private BigInteger emp_numero_documento;
	
	@Column(name="EMP_CIIU4")
	private String emp_ciiu4;
	
	@Column(name="EMP_CC_REPRESEN")
	private int emp_cc_represen;
	
	@Column(name="EMP_REP_EMAIL")
	private String emp_rep_email;
	
	@Column(name="EMP_REP_NOMBRE")
	private String emp_rep_nombre;
	
	@Column(name="EMP_TIP_DOCU")
	private int emp_tip_docu;
	
	@Column(name="EMP_ID_UBICA")
	private int emp_id_ubica;
	
	@Column(name="EMP_TELEFONO")
	private BigInteger emp_telefono;
	
	@Column(name="EMP_EXT")
	private int emp_ext;
	
	@Column(name="EMP_ID_AUT")
	private int emp_id_aut;

	
	@Column(name="EMP_MUNICIPIO")
	private int emp_municipio;
	
	@Column(name="EMP_DIRECCION")
	private String emp_direccion;
	

	
	
	/**
	 * @return the emp_direccion
	 */
	public String getEmp_direccion() {
		return emp_direccion;
	}

	/**
	 * @param emp_direccion the emp_direccion to set
	 */
	public void setEmp_direccion(String emp_direccion) {
		this.emp_direccion = emp_direccion;
	}

	/**
	 * @return the emp_municipio
	 */
	public int getEmp_municipio() {
		return emp_municipio;
	}

	/**
	 * @param emp_municipio the emp_municipio to set
	 */
	public void setEmp_municipio(int emp_municipio) {
		this.emp_municipio = emp_municipio;
	}

	public int getEmp_id_aut() {
		return emp_id_aut;
	}

	public void setEmp_id_aut(int emp_id_aut) {
		this.emp_id_aut = emp_id_aut;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_razon_social() {
		return emp_razon_social;
	}

	public void setEmp_razon_social(String emp_razon_social) {
		this.emp_razon_social = emp_razon_social;
	}

	public String getEmp_nombre_comercial() {
		return emp_nombre_comercial;
	}

	public void setEmp_nombre_comercial(String emp_nombre_comercial) {
		this.emp_nombre_comercial = emp_nombre_comercial;
	}

	public BigInteger getEmp_numero_documento() {
		return emp_numero_documento;
	}

	public void setEmp_numero_documento(BigInteger emp_numero_documento) {
		this.emp_numero_documento = emp_numero_documento;
	}

	public String getEmp_ciiu4() {
		return emp_ciiu4;
	}

	public void setEmp_ciiu4(String emp_ciiu4) {
		this.emp_ciiu4 = emp_ciiu4;
	}

	public int getEmp_cc_represen() {
		return emp_cc_represen;
	}

	public void setEmp_cc_represen(int emp_cc_represen) {
		this.emp_cc_represen = emp_cc_represen;
	}

	public String getEmp_rep_email() {
		return emp_rep_email;
	}

	public void setEmp_rep_email(String emp_rep_email) {
		this.emp_rep_email = emp_rep_email;
	}

	public String getEmp_rep_nombre() {
		return emp_rep_nombre;
	}

	public void setEmp_rep_nombre(String emp_rep_nombre) {
		this.emp_rep_nombre = emp_rep_nombre;
	}

	public int getEmp_tip_docu() {
		return emp_tip_docu;
	}

	public void setEmp_tip_docu(int emp_tip_docu) {
		this.emp_tip_docu = emp_tip_docu;
	}

	public int getEmp_id_ubica() {
		return emp_id_ubica;
	}

	public void setEmp_id_ubica(int emp_id_ubica) {
		this.emp_id_ubica = emp_id_ubica;
	}

	public BigInteger getEmp_telefono() {
		return emp_telefono;
	}

	public void setEmp_telefono(BigInteger emp_telefono) {
		this.emp_telefono = emp_telefono;
	}

	public int getEmp_ext() {
		return emp_ext;
	}

	public void setEmp_ext(int emp_ext) {
		this.emp_ext = emp_ext;
	}

	@Override
	public String toString() {
		return emp_id + " : " + emp_razon_social + " : " + emp_nombre_comercial + " : " + emp_numero_documento + " : "
				+ emp_ciiu4 + " : " + emp_cc_represen + " : " + emp_rep_email + " : " + emp_rep_nombre + " : "
				+ emp_tip_docu + " : " + emp_id_ubica + " : " + emp_telefono + " : " + emp_ext + " : " + emp_id_aut;
	}
	
	
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ubiEmpresa")
//	 private List<Ubicacion> ubicaciones;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "empresaSed")
	 private List<Sede> empresaSed;
	
	  @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "emp_id_ubica", referencedColumnName = "id_dept", insertable=false, updatable=false)
	   private Departamento empDept;
	  
	  @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "emp_ciiu4", referencedColumnName = "cii_id", insertable=false, updatable=false)
	   private Ciiu4 empCii;
	  
	  @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "emp_municipio", referencedColumnName = "id_munic", insertable=false, updatable=false)
	   private Municipio emp_mun;

	  
	  
	  
	/**
	 * @return the emp_mun
	 */
	public Municipio getEmp_mun() {
		return emp_mun;
	}

	/**
	 * @param emp_mun the emp_mun to set
	 */
	public void setEmp_mun(Municipio emp_mun) {
		this.emp_mun = emp_mun;
	}

	public List<Sede> getEmpresaSed() {
		return empresaSed;
	}

	public void setEmpresaSed(List<Sede> empresaSed) {
		this.empresaSed = empresaSed;
	}

	public Ciiu4 getEmpCii() {
		return empCii;
	}

	public void setEmpCii(Ciiu4 empCii) {
		this.empCii = empCii;
	}

	public Departamento getEmpDept() {
		return empDept;
	}

	public void setEmpDept(Departamento empDept) {
		this.empDept = empDept;
	}
	
	
}
