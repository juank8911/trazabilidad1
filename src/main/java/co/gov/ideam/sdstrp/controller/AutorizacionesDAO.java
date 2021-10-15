package co.gov.ideam.sdstrp.controller;

import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import co.gov.ideam.sdstrp.model.Autoridad;
import co.gov.ideam.sdstrp.model.Autorizaciones;

@Model
public class AutorizacionesDAO {
	
    private List<Tuple> listAutorizacion;
    private List<Autorizaciones> autorizaciones;
    private List<Autorizaciones> listaIdAut;
    private Date objDate;
	 
    @Inject
    private Logger log;

    @Inject
    private EntityManager em;
    

    public List<Tuple> listAutorizaciones(int id) throws Exception {
        
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			 CriteriaQuery<Tuple> conRes = cb.createTupleQuery();	
			 Root<Autorizaciones> RootRes = conRes.from(Autorizaciones.class);
			 conRes.where(cb.equal(RootRes.get("ag_gestor_id"), id));
			 Join<Autorizaciones, Autoridad> joinAutoridad = RootRes.join("autoridad", JoinType.INNER);
			 conRes.multiselect(RootRes.get("ag_resol_licencia"),joinAutoridad.get("aut_nombre"));
			 listAutorizacion = em.createQuery(conRes).getResultList();
			 
		} catch (Exception e) {
            System.out.println("Fallo lista de autorizaciones por autoridad: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
        return listAutorizacion;
    }


	public List<Autorizaciones> listAutoridad() {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Autorizaciones> conRes = cb.createQuery(Autorizaciones.class);	
			 Root<Autorizaciones> RootRes = conRes.from(Autorizaciones.class);
//			 conRes.where(cb.equal(RootRes.get("sed_transportador"), "s"));
			 conRes.select(RootRes);
			 autorizaciones = em.createQuery(conRes).getResultList();
		} catch (Exception e) {
            System.out.println("Fallo lista de autorizaciones: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
		return autorizaciones;
	}


	public List<Autorizaciones> listarIdAutGes(int id_Aut) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Autorizaciones> conRes = cb.createQuery(Autorizaciones.class);	
			 Root<Autorizaciones> RootRes = conRes.from(Autorizaciones.class);
			 conRes.where(cb.equal(RootRes.get("id_autoridad"), id_Aut));
			 Join<Autorizaciones, Autoridad> joinAut = RootRes.join("autoridad",JoinType.INNER);
			 conRes.multiselect(RootRes.get("id_autoridad"),RootRes.get("ag_resol_licencia"),RootRes.get("ag_fecha_resol"),RootRes.get("ag_autoridad_ambiental"),
					 RootRes.get("ag_permiso_emi"),RootRes.get("ag_resol_emisiones"),RootRes.get("ag_fecha_ini_emi"),RootRes.get("ag_permiso_verti"),
					 RootRes.get("ag_fecha_fin_emi"),RootRes.get("ag_resol_verti"),RootRes.get("ag_fecha_ini_verti"),RootRes.get("ag_fecha_fin_verti"),
					 joinAut.get("aut_nombre"));
			 autorizaciones = em.createQuery(conRes).getResultList();
		} catch (Exception e) {
            System.out.println("Fallo lista de autorizaciones por id: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
		
		return listaIdAut;
	}





    
    

}
