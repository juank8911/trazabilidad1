package co.gov.ideam.sdstrp.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;



import co.gov.ideam.sdstrp.model.Departamento;
import co.gov.ideam.sdstrp.model.Empresa;
import co.gov.ideam.sdstrp.model.Sede;


@Stateful
@Model
public class EmpresasDao {

	@Inject
	private Logger log;

	
	 

	@Inject
	private Event<Empresa> EmpEventSrc;

	private List<Tuple> listaEmpresasAu;
	private List<Empresa> listaEmpresasAuJson;
	private List<Empresa> listaEmpresasIdeJson;

	private Empresa newEmp;
	
	@Inject
	EntityManager em;
	

	@Produces
	@Named
	public List<Tuple> getListaEmpresasAu() {
		return listaEmpresasAu;
	}
	@Produces
	@Named
	public List<Empresa> getListaEmpresasAuJson() {
		return listaEmpresasAuJson;
	}

	public void empresasAutoridad(int idAu) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			log.info(idAu+"");
			CriteriaQuery<Tuple> conRes = cb.createTupleQuery();
			Root<Empresa> RootEmp = conRes.from(Empresa.class);
			Join<Empresa, Departamento> joinEmpDe = RootEmp.join("empDept", JoinType.INNER);
			conRes.where(cb.equal(RootEmp.get("emp_id_aut"), idAu));
			conRes.multiselect(RootEmp.get("emp_razon_social"), RootEmp.get("emp_numero_documento"),
					joinEmpDe.get("dept_nombre"), RootEmp.get("emp_id"));
			listaEmpresasAu = em.createQuery(conRes).getResultList();

			for (Tuple tuple : listaEmpresasAu) {

				log.info("" + tuple.toString());
				log.info("" + tuple.get(0));
				log.info("" + tuple.get(1));
				log.info("" + tuple.get(2));
			}
		} catch (Exception e) {

			log.info("Fallo lista de Declaraciones: " + e.getMessage());
			listaEmpresasIdeJson = null;
			
			
		} 

	}
	
	public List<Empresa> empresasAutoridadJson(int idAu) {
	
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		try {
			log.info(idAu+"");
			CriteriaQuery<Empresa> conRes = cb.createQuery(Empresa.class);
			Root<Empresa> RootEmp = conRes.from(Empresa.class);
			conRes.where(cb.equal(RootEmp.get("emp_id_aut"), idAu));
			conRes.select(RootEmp);
			listaEmpresasAuJson = em.createQuery(conRes).getResultList();
			for (Empresa emp : listaEmpresasAuJson) {
				log.info(emp.getEmpDept().getDept_nombre());
				log.info(emp.getEmp_id()+"");
				emp.getEmpCii().getCii_nombre();
				emp.getEmp_mun().getMunic_nombre();
				
				for (Sede sed : emp.getEmpresaSed()) {
					log.info(sed.getSed_nombre());
					sed.getDepartamento().getDept_nombre();
					sed.getSedMunic().getMunic_nombre();
					
				}
			}
			

		} catch (Exception e) {
		
			log.info("Fallo lista de Declaraciones: " + e.getMessage());
			listaEmpresasIdeJson = null;
			
			
		} finally {
			
			return listaEmpresasAuJson;
		}
		
		

	}




	public Empresa getNewEmpr() {
		// TODO Auto-generated method stub
		return newEmp;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void initNewEmpr() {
		// TODO Auto-generated method stub
		newEmp = new Empresa();
	}
	
	public List<Empresa> listEmpIdeam() {
		// TODO Auto-generated method stub
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		
		try {
			CriteriaQuery<Empresa> conRes = cb.createQuery(Empresa.class);
			Root<Empresa> RootEmp = conRes.from(Empresa.class);
			conRes.select(RootEmp);
			listaEmpresasIdeJson = em.createQuery(conRes).getResultList();
			for (Empresa emp : listaEmpresasIdeJson) {
				log.info(emp.getEmpDept().getDept_nombre());
				emp.getEmpCii().getCii_nombre();
				emp.getEmp_mun().getMunic_nombre();
				
				for (Sede sed : emp.getEmpresaSed()) {
					log.info(sed.getSed_nombre());
					sed.getDepartamento().getDept_nombre();
					sed.getSedMunic().getMunic_nombre();
					
				}
			}
		} catch (Exception e) {
		
			log.info("Fallo lista de Declaraciones: " + e.getMessage());
			listaEmpresasIdeJson = null;
			
			
		} finally {
			return listaEmpresasIdeJson;
		}
	}

	public void registerEmp() throws Exception {
		// TODO Auto-generated method stub
	
	
		try {

			log.info("Registering " + newEmp);
			em.persist(newEmp);
			EmpEventSrc.fire(newEmp);
			initNewEmpr();
		} catch (Exception e) {
			em.getTransaction().rollback();
			Throwable t = e;
			while ((t.getCause()) != null) {
				t = t.getCause();
			}
			log.info("Exception:" + t.getMessage());
			throw ((Exception) t);
		}
		

	}
	public void updEmp(Empresa empUp) throws Exception {
		// TODO Auto-generated method stub
	
		try {
			em.merge(empUp);
			EmpEventSrc.fire(empUp);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			Throwable t = e;
			while ((t.getCause()) != null) {
				t = t.getCause();
			}
			log.info("Exception:" + t.getMessage());
			throw ((Exception) t);
		}
		
	}


}
