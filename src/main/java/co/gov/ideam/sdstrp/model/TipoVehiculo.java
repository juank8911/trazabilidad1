/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.ideam.sdstrp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yorman B.
 */

@Entity
@XmlRootElement
@Table(name = "TRPT_TIPO_VEHICULO")
public class TipoVehiculo implements Serializable {
    

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TVE_ID")
   private String tve_id;
	
	@Column(name="TVE_NOMBRE")
   private String tve_nombre;
	
	@Column(name="TVE_PADRE")
   private String tve_padre;

	public String getTve_id() {
		return tve_id;
	}

	public void setTve_id(String tve_id) {
		this.tve_id = tve_id;
	}

	public String getTve_nombre() {
		return tve_nombre;
	}

	public void setTve_nombre(String tve_nombre) {
		this.tve_nombre = tve_nombre;
	}

	public String getTve_padre() {
		return tve_padre;
	}

	public void setTve_padre(String tve_padre) {
		this.tve_padre = tve_padre;
	}

	@Override
	public String toString() {
		return tve_id + " : " + tve_nombre + " : " + tve_padre;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tvehiculo")
	 private List<Vehiculo> vehi_tvehi;
	
	

   
    
    
}
