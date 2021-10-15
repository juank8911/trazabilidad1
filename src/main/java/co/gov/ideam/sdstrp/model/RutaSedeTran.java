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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "TRPT_RUTA_SEDE_TRANS")
public class RutaSedeTran implements Serializable  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	"RUG_ID" NUMBER(10,0) NOT NULL ENABLE, 
//	"RUG_ID_SEDTR" NUMBER(10,0), 
//	"RUG_ID_RUTA" NUMBER(10,0), 
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_RutaST")
	@SequenceGenerator(initialValue = 5,allocationSize=1,name = "sequence_RutaST", sequenceName = "TRPT_UBICACION_SEQ")
	@Column(name="RUG_ID")
	private int rug_id;
	
	
	@Column	(name="RUG_ID_SEDTR")
	private int rug_id_sedtr;
	
	@Column	(name="RUG_ID_RUTA")
	private int rug_id_ruta;
	
	

	
	public int getRug_id() {
		return rug_id;
	}

	public void setRug_id(int rug_id) {
		this.rug_id = rug_id;
	}

	public int getRug_id_sedtr() {
		return rug_id_sedtr;
	}

	public void setRug_id_sedtr(int rug_id_sedtr) {
		this.rug_id_sedtr = rug_id_sedtr;
	}

	public int getRug_id_ruta() {
		return rug_id_ruta;
	}

	public void setRug_id_ruta(int rug_id_ruta) {
		this.rug_id_ruta = rug_id_ruta;
	}

	@Override
	public String toString() {
		return rug_id + " : " + rug_id_sedtr + " : " + rug_id_ruta;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "rug_id_sedtr", referencedColumnName = "SED_ID", insertable=false, updatable=false)
	   private Sede RutasSedTr;
	
	  @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "rug_id_ruta", referencedColumnName = "ID_RUTA", insertable=false, updatable=false)
	   private Rutas RutasRGen;
	  
}
