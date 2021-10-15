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
@Table(name = "TRPT_PROGRAMACION")
public class Programacion implements Serializable {
//	"PRO_ID" NUMBER NOT NULL ENABLE, 
//	"PRO_FECHA_INICIAL" DATE, 
//	"PRO_FECHA_FINAL" DATE, 
//	"PRO_PERIODICIDAD" NUMBER(4,0), 
//	"PRO_CANTIDAD" NUMBER(4,0), 
//	"PRO_DIAS" VARCHAR2(7 BYTE), 
//	"PRO_GENERADOR" NUMBER, 
//	"PRO_TRANSPORTADOR" NUMBER, 
//	"PRO_GESTOR" NUMBER, 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_pr")
	@SequenceGenerator(initialValue = 4,allocationSize=1,name = "sequence_pr", sequenceName = "TRPT_PROGRAMACION_SEQ")
	@Column(name="PRO_ID")
    private int pro_id;
	
	@Column(name="PRO_FECHA_INICIAL", columnDefinition = "Date")
    private Date pro_fecha_inicial;

	@Column(name="PRO_FECHA_FINAL", columnDefinition = "Date")
    private Date pro_fecha_final;
	
	@Column(name="PRO_PERIODICIDAD")
    private int pro_periodicidad;
	
	@Column(name="PRO_CANTIDAD")
    private int pro_cantidad;
	
	@Column(name="PRO_DIAS")
    private String pro_dias;
	
	@Column(name="PRO_GENERADOR")
    private int pro_generador;
	
	@Column(name="PRO_TRANSPORTADOR")
    private int pro_transportador;
	
	@Column(name="PRO_GESTOR")
    private int pro_gestor;

	public int getPro_id() {
		return pro_id;
	}

	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}

	public Date getPro_fecha_inicial() {
		return pro_fecha_inicial;
	}

	public void setPro_fecha_inicial(Date pro_fecha_inicial) {
		this.pro_fecha_inicial = pro_fecha_inicial;
	}

	public Date getPro_fecha_final() {
		return pro_fecha_final;
	}

	public void setPro_fecha_final(Date pro_fecha_final) {
		this.pro_fecha_final = pro_fecha_final;
	}

	public int getPro_periodicidad() {
		return pro_periodicidad;
	}

	public void setPro_periodicidad(int pro_periodicidad) {
		this.pro_periodicidad = pro_periodicidad;
	}

	public int getPro_cantidad() {
		return pro_cantidad;
	}

	public void setPro_cantidad(int pro_cantidad) {
		this.pro_cantidad = pro_cantidad;
	}

	public String getPro_dias() {
		return pro_dias;
	}

	public void setPro_dias(String pro_dias) {
		this.pro_dias = pro_dias;
	}

	public int getPro_generador() {
		return pro_generador;
	}

	public void setPro_generador(int pro_generador) {
		this.pro_generador = pro_generador;
	}

	public int getPro_transportador() {
		return pro_transportador;
	}

	public void setPro_transportador(int pro_transportador) {
		this.pro_transportador = pro_transportador;
	}

	public int getPro_gestor() {
		return pro_gestor;
	}

	public void setPro_gestor(int pro_gestor) {
		this.pro_gestor = pro_gestor;
	}

	@Override
	public String toString() {
		return pro_id + " : " + pro_fecha_inicial + " : " + pro_fecha_final + " : " + pro_periodicidad + " : "
				+ pro_cantidad + " : " + pro_dias + " : " + pro_generador + " : " + pro_transportador + " : "
				+ pro_gestor;
	}
	
	

	 
	 @OneToOne(cascade = CascadeType.ALL, mappedBy = "prog_dec")
	 private Declaracion prog_dec;
	 
	  
	  @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "pro_generador", referencedColumnName = "sed_id", insertable=false, updatable=false)
	   private Sede sedGene;
	  
	 
	  @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "pro_transportador", referencedColumnName = "sed_id", insertable=false, updatable=false)
	   private Sede jsedprog;
	 


	  
	

	
	


}
