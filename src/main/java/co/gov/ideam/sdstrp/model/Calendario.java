package co.gov.ideam.sdstrp.model;

import java.sql.Date;

public class Calendario {
	
//	clase que no se encuentra en la base de datos
	
    private String title;
    private int id;
    private Date start;
    private int id_gestor;
    private int id_transp;
    private String nombreTransp;
    private int idTrans;
    private String nombreGestor;
    private int idGes;
    private int idProg;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public int getId_gestor() {
		return id_gestor;
	}
	public void setId_gestor(int id_gestor) {
		this.id_gestor = id_gestor;
	}
	public int getId_transp() {
		return id_transp;
	}
	public void setId_transp(int id_transp) {
		this.id_transp = id_transp;
	}
	public String getNombreTransp() {
		return nombreTransp;
	}
	public void setNombreTransp(String nombreTransp) {
		this.nombreTransp = nombreTransp;
	}
	public int getIdTrans() {
		return idTrans;
	}
	public void setIdTrans(int idTrans) {
		this.idTrans = idTrans;
	}
	public String getNombreGestor() {
		return nombreGestor;
	}
	public void setNombreGestor(String nombreGestor) {
		this.nombreGestor = nombreGestor;
	}
	public int getIdGes() {
		return idGes;
	}
	public void setIdGes(int idGes) {
		this.idGes = idGes;
	}
	public int getIdProg() {
		return idProg;
	}
	public void setIdProg(int idProg) {
		this.idProg = idProg;
	}
	@Override
	public String toString() {
		return "Calendario [title=" + title + ", id=" + id + ", start=" + start + ", id_gestor=" + id_gestor
				+ ", id_transp=" + id_transp + ", nombreTransp=" + nombreTransp + ", idTrans=" + idTrans
				+ ", nombreGestor=" + nombreGestor + ", idGes=" + idGes + ", idProg=" + idProg + "]";
	}
    
    

}
