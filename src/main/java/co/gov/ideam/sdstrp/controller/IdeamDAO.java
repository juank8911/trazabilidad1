package co.gov.ideam.sdstrp.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import co.gov.ideam.sdstrp.model.Departamento;
import co.gov.ideam.sdstrp.model.Empresa;

@Model
@Stateful
public class IdeamDAO  {
	
    @Inject
    private Logger log;

    @Inject
    private EntityManager em;
    
    private List<Tuple> listaEmpresasIdm;
    
	@Produces
	@Named
	public List<Tuple> getListaEmpresasIdm() {
		return listaEmpresasIdm;
	}
    
    
    
    
	public void empresasAutoridad(int idAu) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Tuple> conRes = cb.createTupleQuery();
			Root<Empresa> RootEmp = conRes.from(Empresa.class);
			Join<Empresa, Departamento> joinEmpDe = RootEmp.join("empDept", JoinType.INNER);			
			conRes.multiselect(RootEmp.get("emp_razon_social"), RootEmp.get("emp_numero_documento"),
					joinEmpDe.get("dept_nombre"), RootEmp.get("emp_id"));
			listaEmpresasIdm = em.createQuery(conRes).getResultList();

			for (Tuple tuple : listaEmpresasIdm) {

				log.info("" + tuple.toString());
				log.info("" + tuple.get(0));
				log.info("" + tuple.get(1));
				log.info("" + tuple.get(2));
			}
		} catch (Exception e) {
			System.out.println("Fallo lista de autorizaciones: " + e.getMessage());
			e.fillInStackTrace();
			// TODO: handle exception
		}

	}

}
