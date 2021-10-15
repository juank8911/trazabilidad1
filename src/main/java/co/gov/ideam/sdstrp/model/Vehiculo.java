package co.gov.ideam.sdstrp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "TRPT_VEHICULO")
public class Vehiculo implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="VEH_ID")
	private String veh_id;
	@Column(name="VEH_CAPACIDAD")
	private String veh_capacidad;
	@Column(name="VEH_CHASIS")
	private String veh_chasis;
	@Column(name="VEH_DESIG_PADRE")
	private String veh_desig_padre;
	@Column(name="VEH_DESIGNACION")
	private String veh_designacion;
	@Column(name="VEH_ID_DESIG")
	private String veh_id_desig;
	@Column(name="VEH_ID_SEDE")
	private int veh_id_sede;
	@Column(name="VEH_LINEA")
	private String veh_linea;
	@Column(name="VEH_MARCA")
	private String veh_marca;
	@Column(name="VEH_MODELO")
	private String veh_modelo;
	@Column(name="VEH_PLACA")
	private String veh_placa;
	@Column(name="VEH_PLACA_REMOLQUE")
	private String veh_placa_remolque;
	@Column(name="VEH_PRO_NOMBRE")
	private String veh_pro_nombre;
	@Column(name="VEH_PRO_NUMERO_DOCUMENTO")
	private String veh_pro_numero_documento;
	@Column(name="VEH_PRO_TIPO_DOCUMENTO")
	private String veh_pro_tipo_documento;
	@Column(name="VEH_TIPO_PELIGROSIDAD")
	private String veh_tipo_peligrosidad;
	@Column(name="VEH_TIPO_VEHICULO")
	private String veh_tipo_vehiculo;
	@Column(name="VEH_TRANSPORTADOR")
	private String veh_transportador;
	
	
	  public String getVeh_pro_tipo_documento() {
		return veh_pro_tipo_documento;
	}

	public void setVeh_pro_tipo_documento(String veh_pro_tipo_documento) {
		this.veh_pro_tipo_documento = veh_pro_tipo_documento;
	}

	public String getVeh_id() {
		return veh_id;
	}

	public void setVeh_id(String veh_id) {
		this.veh_id = veh_id;
	}

	public String getVeh_capacidad() {
		return veh_capacidad;
	}

	public void setVeh_capacidad(String veh_capacidad) {
		this.veh_capacidad = veh_capacidad;
	}

	public String getVeh_chasis() {
		return veh_chasis;
	}

	public void setVeh_chasis(String veh_chasis) {
		this.veh_chasis = veh_chasis;
	}

	public String getVeh_desig_padre() {
		return veh_desig_padre;
	}

	public void setVeh_desig_padre(String veh_desig_padre) {
		this.veh_desig_padre = veh_desig_padre;
	}

	public String getVeh_designacion() {
		return veh_designacion;
	}

	public void setVeh_designacion(String veh_designacion) {
		this.veh_designacion = veh_designacion;
	}

	public String getVeh_id_desig() {
		return veh_id_desig;
	}

	public void setVeh_id_desig(String veh_id_desig) {
		this.veh_id_desig = veh_id_desig;
	}

	public int getVeh_id_sede() {
		return veh_id_sede;
	}

	public void setVeh_id_sede(int veh_id_sede) {
		this.veh_id_sede = veh_id_sede;
	}

	public String getVeh_linea() {
		return veh_linea;
	}

	public void setVeh_linea(String veh_linea) {
		this.veh_linea = veh_linea;
	}

	public String getVeh_modelo() {
		return veh_modelo;
	}

	public void setVeh_modelo(String veh_modelo) {
		this.veh_modelo = veh_modelo;
	}

	public String getVeh_placa() {
		return veh_placa;
	}

	public void setVeh_placa(String veh_placa) {
		this.veh_placa = veh_placa;
	}

	public String getVeh_placa_remolque() {
		return veh_placa_remolque;
	}

	public void setVeh_placa_remolque(String veh_placa_remolque) {
		this.veh_placa_remolque = veh_placa_remolque;
	}

	public String getVeh_pro_nombre() {
		return veh_pro_nombre;
	}

	public void setVeh_pro_nombre(String veh_pro_nombre) {
		this.veh_pro_nombre = veh_pro_nombre;
	}

	public String getVeh_pro_numero_documento() {
		return veh_pro_numero_documento;
	}

	public void setVeh_pro_numero_documento(String veh_pro_numero_documento) {
		this.veh_pro_numero_documento = veh_pro_numero_documento;
	}

	public String getVeh_tipo_peligrosidad() {
		return veh_tipo_peligrosidad;
	}

	public void setVeh_tipo_peligrosidad(String veh_tipo_peligrosidad) {
		this.veh_tipo_peligrosidad = veh_tipo_peligrosidad;
	}

	public String getVeh_tipo_vehiculo() {
		return veh_tipo_vehiculo;
	}

	public void setVeh_tipo_vehiculo(String veh_tipo_vehiculo) {
		this.veh_tipo_vehiculo = veh_tipo_vehiculo;
	}

	public String getVeh_transportador() {
		return veh_transportador;
	}

	public void setVeh_transportador(String veh_transportador) {
		this.veh_transportador = veh_transportador;
	}
	

	public String getVeh_marca() {
		return veh_marca;
	}

	public void setVeh_marca(String veh_marca) {
		this.veh_marca = veh_marca;
	}

	
	@Override
	public String toString() {
		return veh_id + " : " + veh_capacidad + " : " + veh_chasis + " : " + veh_desig_padre + " : " + veh_designacion
				+ " : " + veh_id_desig + " : " + veh_id_sede + " : " + veh_linea + " : " + veh_marca + " : "
				+ veh_modelo + " : " + veh_placa + " : " + veh_placa_remolque + " : " + veh_pro_nombre + " : "
				+ veh_pro_numero_documento + " : " + veh_pro_tipo_documento + " : " + veh_tipo_peligrosidad + " : "
				+ veh_tipo_vehiculo + " : " + veh_transportador;
	}



	@ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "veh_tipo_vehiculo", referencedColumnName = "tve_id", insertable=false, updatable=false)
	   private TipoVehiculo tvehiculo;
	
	  @ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name = "veh_id_sede", referencedColumnName = "sed_id", insertable=false, updatable=false)
	   private Sede vehiSede;
	  
	  
	
}
