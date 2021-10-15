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

@Entity
@XmlRootElement
@Table(name = "TRPT_DEPARTAMENTO")
public class Departamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="ID_DEPT")
	private int id_dept;
	
	@Column(name="DEPT_NOMBRE")
	private String dept_nombre;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "departamento")
	 private List<Sede> departamentos;

	public int getId_dept() {
		return id_dept;
	}

	public void setId_dept(int id_dept) {
		this.id_dept = id_dept;
	}

	public String getDept_nombre() {
		return dept_nombre;
	}

	public void setDept_nombre(String dept_nombre) {
		this.dept_nombre = dept_nombre;
	}
	
	

	public List<Sede> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Sede> departamentos) {
		this.departamentos = departamentos;
	}

	public List<Empresa> getEmpDept() {
		return empDept;
	}

	public void setEmpDept(List<Empresa> empDept) {
		this.empDept = empDept;
	}

	@Override
	public String toString() {
		return id_dept + " : " + dept_nombre;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empDept")
	 private List<Empresa> empDept;
	

}
