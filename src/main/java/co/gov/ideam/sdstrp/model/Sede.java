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
@Table(name = "TRPT_SEDE")
public class Sede implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_sede")
	@SequenceGenerator(initialValue = 4,allocationSize=1, name = "sequence_sede", sequenceName = "TRPT_SEDE_SEQ")
	@Column(name="SED_ID")
	private int sed_id;

	
	@Column(name="SED_EMPRESA")
	private int sed_empresa;
	
	@Column(name="SED_MUNICIPIO")
	private int sed_municipio;
	
	@Column(name="SED_LATITUD")
	private String sed_latitud;
	
	@Column(name="SED_LONGITUD")
	private String sed_longitud;
	
	@Column(name="SED_NOMBRE")
	private String sed_nombre;
	
	@Column(name="SED_DIRECCION")
	private String sed_direccion;
	
	@Column(name="SED_DEPARTAMENTO")
	private int sed_departamento;
	
	@Column(name="SED_TELEFONO_NRO")
	private BigInteger sed_telefono_nro;
	
	@Column(name="SED_TELEFONO_EXT")
	private int sed_telefono_ext;
	
	@Column(name="SED_AUTORIDAD")
	private int sed_autoridad;
	
	@Column(name="SED_GENERADOR")
	private String sed_generador;
	
	@Column(name="SED_TRANSPORTADOR")
	private String sed_transportador;
	
	@Column(name="SED_GESTOR")
	private String sed_gestor;
	

	public int getSed_id() {
		return sed_id;
	}

	public void setSed_id(int sed_id) {
		this.sed_id = sed_id;
	}

	public int getSed_empresa() {
		return sed_empresa;
	}

	public void setSed_empresa(int sed_empresa) {
		this.sed_empresa = sed_empresa;
	}

	public int getSed_municipio() {
		return sed_municipio;
	}

	public void setSed_municipio(int sed_municipio) {
		this.sed_municipio = sed_municipio;
	}

	public String getSed_latitud() {
		return sed_latitud;
	}

	public void setSed_latitud(String sed_latitud) {
		this.sed_latitud = sed_latitud;
	}

	public String getSed_longitud() {
		return sed_longitud;
	}

	public void setSed_longitud(String sed_longitud) {
		this.sed_longitud = sed_longitud;
	}

	public String getSed_nombre() {
		return sed_nombre;
	}

	public void setSed_nombre(String sed_nombre) {
		this.sed_nombre = sed_nombre;
	}

	public String getSed_direccion() {
		return sed_direccion;
	}

	public void setSed_direccion(String sed_direccion) {
		this.sed_direccion = sed_direccion;
	}

	public int getSed_departamento() {
		return sed_departamento;
	}

	public void setSed_departamento(int sed_departamento) {
		this.sed_departamento = sed_departamento;
	}

	public BigInteger getSed_telefono_nro() {
		return sed_telefono_nro;
	}

	public void setSed_telefono_nro(BigInteger sed_telefono_nro) {
		this.sed_telefono_nro = sed_telefono_nro;
	}

	public int getSed_telefono_ext() {
		return sed_telefono_ext;
	}

	public void setSed_telefono_ext(int sed_telefono_ext) {
		this.sed_telefono_ext = sed_telefono_ext;
	}

	public int getSed_autoridad() {
		return sed_autoridad;
	}

	public void setSed_autoridad(int sed_autoridad) {
		this.sed_autoridad = sed_autoridad;
	}

	public String getSed_generador() {
		return sed_generador;
	}

	public void setSed_generador(String sed_generador) {
		this.sed_generador = sed_generador;
	}

	public String getSed_transportador() {
		return sed_transportador;
	}

	public void setSed_transportador(String sed_transportador) {
		this.sed_transportador = sed_transportador;
	}

	public String getSed_gestor() {
		return sed_gestor;
	}

	public void setSed_gestor(String sed_gestor) {
		this.sed_gestor = sed_gestor;
	}
	
	
	

	public List<RutaSedeTran> getRutasSedTr() {
		return RutasSedTr;
	}

	public void setRutasSedTr(List<RutaSedeTran> rutasSedTr) {
		RutasSedTr = rutasSedTr;
	}

	public List<Usuario> getSedeUsu() {
		return sedeUsu;
	}

	public void setSedeUsu(List<Usuario> sedeUsu) {
		this.sedeUsu = sedeUsu;
	}

	public List<Programacion> getSedGene() {
		return sedGene;
	}

	public void setSedGene(List<Programacion> sedGene) {
		this.sedGene = sedGene;
	}

	public List<Programacion> getJsedprog() {
		return jsedprog;
	}

	public void setJsedprog(List<Programacion> jsedprog) {
		this.jsedprog = jsedprog;
	}

	public List<Declaracion> getDecSedGes() {
		return decSedGes;
	}

	public void setDecSedGes(List<Declaracion> decSedGes) {
		this.decSedGes = decSedGes;
	}

	public List<Declaracion> getDecSedTran() {
		return decSedTran;
	}

	public void setDecSedTran(List<Declaracion> decSedTran) {
		this.decSedTran = decSedTran;
	}

	public List<Declaracion> getDecSedGen() {
		return decSedGen;
	}

	public void setDecSedGen(List<Declaracion> decSedGen) {
		this.decSedGen = decSedGen;
	}

	public List<Programacion> getListproSede() {
		return listproSede;
	}

	public void setListproSede(List<Programacion> listproSede) {
		this.listproSede = listproSede;
	}

	public List<Residuos> getSedes_res() {
		return sedes_res;
	}

	public void setSedes_res(List<Residuos> sedes_res) {
		this.sedes_res = sedes_res;
	}

	public List<Residuos> getSedeTrans() {
		return sedeTrans;
	}

	public void setSedeTrans(List<Residuos> sedeTrans) {
		this.sedeTrans = sedeTrans;
	}

	public List<Residuos> getSedeGest() {
		return sedeGest;
	}

	public void setSedeGest(List<Residuos> sedeGest) {
		this.sedeGest = sedeGest;
	}

	public List<Vehiculo> getSedes_Vehi() {
		return sedes_Vehi;
	}

	public void setSedes_Vehi(List<Vehiculo> sedes_Vehi) {
		this.sedes_Vehi = sedes_Vehi;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Empresa getEmpresaSed() {
		return empresaSed;
	}

	public void setEmpresaSed(Empresa sedEmpresa) {
		this.empresaSed = sedEmpresa;
	}

	public Municipio getSedMunic() {
		return sedMunic;
	}

	public void setSedMunic(Municipio sedMunic) {
		this.sedMunic = sedMunic;
	}
	
	

	@Override
	public String toString() {
		return sed_id + " : " + sed_empresa + " : " + sed_municipio + " : " + sed_latitud + " : " + sed_longitud + " : "
				+ sed_nombre + " : " + sed_direccion + " : " + sed_departamento + " : " + sed_telefono_nro + " : "
				+ sed_telefono_ext + " : " + sed_autoridad + " : " + sed_generador + " : " + sed_transportador + " : "
				+ sed_gestor ;
	}	
	
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "RutasSedTr")
	 private List<RutaSedeTran> RutasSedTr;
	
	@OneToMany(cascade = {CascadeType.MERGE} ,fetch = FetchType.LAZY, mappedBy = "sedeUsu")
	 private List<Usuario> sedeUsu;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sedGene")
	 private List<Programacion> sedGene;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jsedprog")
	 private List<Programacion> jsedprog;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,
	        orphanRemoval = true, mappedBy = "decSedGes")
	 private List<Declaracion> decSedGes;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,
	        orphanRemoval = true, mappedBy = "decSedTran")
	 private List<Declaracion> decSedTran;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,
	        orphanRemoval = true, mappedBy = "decSedGen")
	 private List<Declaracion> decSedGen;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jsedprog")
	 private List<Programacion> listproSede;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sedeRes")
	 private List<Residuos> sedes_res;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sedeTrans")
	 private List<Residuos> sedeTrans;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sedeGest")
	 private List<Residuos> sedeGest;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vehiSede")
	 private List<Vehiculo> sedes_Vehi;
	
	  @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "SED_DEPARTAMENTO", referencedColumnName = "ID_DEPT", insertable=false, updatable=false)
	   private Departamento departamento;
	  
	  @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "SED_EMPRESA", referencedColumnName = "EMP_ID", insertable=false, updatable=false)
	   private Empresa empresaSed;
	  
	  @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "SED_MUNICIPIO", referencedColumnName = "ID_MUNIC", insertable=false, updatable=false)
	   private Municipio sedMunic;
	  
//	  @ManyToOne(fetch=FetchType.LAZY)
//	   @JoinColumn(name = "pro_transportador", referencedColumnName = "sed_id", insertable=false, updatable=false)
//	   private Sede sedTranspT;

//		
//		
//		@OneToMany(fetch = FetchType.LAZY, mappedBy = "sedGest")
//		 private List<Programacion> listSedeGest;
		
	

}
