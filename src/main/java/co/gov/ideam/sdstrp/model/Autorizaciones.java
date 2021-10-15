package co.gov.ideam.sdstrp.model;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

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
@Table(name = "TRPT_AUTORIZACIONES")
public class Autorizaciones implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

// campos de la base de datos 	
//	"AG_ID" NUMBER NOT NULL ENABLE, 
//	"AG_RESOL_LICENCIA" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
//	"AG_FECHA_RESOL" DATE NOT NULL ENABLE, 
//	"AG_AUTORIDAD_AMBIENTAL" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
//	"AG_PERMISO_EMI" VARCHAR2(20 BYTE), 
//	"AG_RESOL_EMISIONES" VARCHAR2(20 BYTE), 
//	"AG_FECHA_INI_EMI" DATE, 
//	"AG_FECHA_FIN_EMI" DATE, 
//	"AG_PERMISO_VERTI" VARCHAR2(20 BYTE), 
//	"AG_RESOL_VERTI" VARCHAR2(20 BYTE), 
//	"AG_FECHA_INI_VERTI" DATE, 
//	"AG_FECHA_FIN_VERTI" DATE, 
//	"AG_GESTOR_ID" NUMBER NOT NULL ENABLE, 
//	"AG_PDF" BLOB, 
	
	@Id
	@GeneratedValue
	@Column(name="ID_AUTORIZACION")
	private int id_autorizacion;
	
	@Column(name="AG_RESOL_LICENCIA")
	private String ag_resol_licencia;
	//no se que campo es
	@Column(name="USR_NOMBRE")
	private Date fecha_resolF;
	
	@Column(name="AG_FECHA_RESOL")
	private String fecha_resol;
	
	//no se que es este campo
	@Column(name="AG_AUTORIDAD_AMBIENTAL1")
	private String auto_ambiental;
	
	@Column(name="AG_AUTORIDAD_AMBIENTAL")
	private int id_auto_ambiental;
	
	@Column(name="AG_PERMISO_EMI")
	private String permiso_emisiones;
	
	@Column(name="AG_RESOL_EMISIONES")
	private String emisiones_resol;
	
	@Column(name="AG_FECHA_INI_EMI")
	private Date fecha_ini_emis;	
	
	@Column(name="AG_FECHA_FIN_EMI")
	private Date ag_fecha_fin_emi;	
	
	
	@Column(name="AG_PERMISO_VERTI")
	private String permiso_vertimi;
	
	@Column(name="AG_RESOL_VERTI")
	private String vertimi_resol;
	
	@Column(name="AG_FECHA_INI_VERTI")
	private Date fecha_ini_verti;
	
	@Column(name="AG_FECHA_FIN_VERTI")
	private Date fecha_fin_verti;
	
//	private Date fecha_fin_vertiF;
	
	@Column(name="AG_GESTOR_ID")
    private int ag_gestor_id;
	
//	no se de q es
//	@Column(name="test")
//    private String test;
	
    @Column(name="AG_PDF")
    private Blob file_pdf;
    


	public int getId_autorizacion() {
		return id_autorizacion;
	}

	public void setId_autorizacion(int id_autorizacion) {
		this.id_autorizacion = id_autorizacion;
	}

	public String getLicencia_plan() {
		return ag_resol_licencia;
	}

	public void setLicencia_plan(String licencia_plan) {
		this.ag_resol_licencia = licencia_plan;
	}

	public Date getFecha_resolF() {
		return fecha_resolF;
	}

	public void setFecha_resolF(Date fecha_resolF) {
		this.fecha_resolF = fecha_resolF;
	}

	public String getFecha_resol() {
		return fecha_resol;
	}

	public void setFecha_resol(String fecha_resol) {
		this.fecha_resol = fecha_resol;
	}

	public String getAuto_ambiental() {
		return auto_ambiental;
	}

	public void setAuto_ambiental(String auto_ambiental) {
		this.auto_ambiental = auto_ambiental;
	}

	public int getId_auto_ambiental() {
		return id_auto_ambiental;
	}

	public void setId_auto_ambiental(int id_auto_ambiental) {
		this.id_auto_ambiental = id_auto_ambiental;
	}

	public String getPermiso_emisiones() {
		return permiso_emisiones;
	}

	public void setPermiso_emisiones(String permiso_emisiones) {
		this.permiso_emisiones = permiso_emisiones;
	}

	public String getEmisiones_resol() {
		return emisiones_resol;
	}

	public void setEmisiones_resol(String emisiones_resol) {
		this.emisiones_resol = emisiones_resol;
	}

	public Date getFecha_ini_emis() {
		return fecha_ini_emis;
	}

	public void setFecha_ini_emis(Date fecha_ini_emis) {
		this.fecha_ini_emis = fecha_ini_emis;
	}

	public Date getFecha_fin_emis() {
		return ag_fecha_fin_emi;
	}

	public void setFecha_fin_emis(Date fecha_fin_emis) {
		this.ag_fecha_fin_emi = fecha_fin_emis;
	}

	public String getPermiso_vertimi() {
		return permiso_vertimi;
	}

	public void setPermiso_vertimi(String permiso_vertimi) {
		this.permiso_vertimi = permiso_vertimi;
	}

	public String getVertimi_resol() {
		return vertimi_resol;
	}

	public void setVertimi_resol(String vertimi_resol) {
		this.vertimi_resol = vertimi_resol;
	}

	public Date getFecha_ini_verti() {
		return fecha_ini_verti;
	}

	public void setFecha_ini_verti(Date fecha_ini_verti) {
		this.fecha_ini_verti = fecha_ini_verti;
	}

	public Date getFecha_fin_verti() {
		return fecha_fin_verti;
	}

	public void setFecha_fin_verti(Date fecha_fin_verti) {
		this.fecha_fin_verti = fecha_fin_verti;
	}

	public int getGestor() {
		return ag_gestor_id;
	}

	public void setGestor(int gestor) {
		this.ag_gestor_id = gestor;
	}

	public Blob getFile_pdf() {
		return file_pdf;
	}

	public void setFile_pdf(Blob file_pdf) {
		this.file_pdf = file_pdf;
	}

	@Override
	public String toString() {
		return id_autorizacion + " : " + ag_resol_licencia + " : " + fecha_resolF + " : " + fecha_resol + " : "
				+ auto_ambiental + " : " + id_auto_ambiental + " : " + permiso_emisiones + " : " + emisiones_resol
				+ " : " + fecha_ini_emis + " : " + ag_fecha_fin_emi + " : " + permiso_vertimi + " : " + vertimi_resol
				+ " : " + fecha_ini_verti + " : " + fecha_fin_verti + " : " + ag_gestor_id + " : " + file_pdf + " : "
				+ autoridad + "]";
	}
    
	
    
    
    
	  @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "ag_autoridad_ambiental", referencedColumnName = "id_aut", insertable=false, updatable=false)
	   private Autoridad autoridad;
	
    
    
    

}
