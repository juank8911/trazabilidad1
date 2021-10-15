package co.gov.ideam.sdstrp.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
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



import co.gov.ideam.sdstrp.model.Empresa;
import co.gov.ideam.sdstrp.model.Municipio;
import co.gov.ideam.sdstrp.model.Sede;
import co.gov.ideam.sdstrp.model.Usuario;
import co.gov.ideam.sdstrp.model.Usuario_Perfil;

@Stateful
@Model
public class SedeDAO {
	
	
	 @Inject
	    private Logger log;

	    @Inject
	    private EntityManager em;
	    
	    @Inject
	    private Event<Sede> sedeEventSrc;
	    
	    private List<Tuple> listaSedesAu;
	    private List<Sede> listaSedesAuJs;
	    private List<Sede> listaSedesIdeJs;
	    private Sede idSede;

		private Sede newSed;
		
		private Usuario_Perfil newUsPe;

		private Long sedesCon;
		

		
	    @Produces
	    @Named
	    public Sede getIdSede()
	    {
	    	
	    	return idSede;
	    }
	    
	    @Produces
	    @Named
	    public Long getSedesCon()
	    {
	    	
	    	return sedesCon;
	    }
	    
	    @Produces
	    @Named
	    public List<Tuple> getListaSedesAu()
	    {
	    	SedesAuto();
	    	return listaSedesAu;
	    }
	    
	    @Produces
	    @Named
	    public List<Sede> getListaSedesAuJs()
	    {

	    	return listaSedesAuJs;
	    }
	    
	    private void SedesAuto() {
			// TODO Auto-generated method stub
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> conBd = cb.createTupleQuery();
			Root<Sede> rootSed = conBd.from(Sede.class);
			Join<Sede, Empresa> joinSeEm = rootSed.join("empresaSed",JoinType.INNER);
			Join<Sede, Municipio> joinSeMu = rootSed.join("sedMunic",JoinType.INNER);
			conBd.multiselect(joinSeEm.get("emp_razon_social"),rootSed.get("sed_nombre"),joinSeMu.get("munic_nombre"),rootSed.get("sed_generador"),rootSed.get("sed_transportador"),rootSed.get("sed_gestor"));
			listaSedesAu = em.createQuery(conBd).getResultList();
			listaSedesAu.size();
			for (Tuple tuple : listaSedesAu) {
				log.info(""+tuple.get(0));
				log.info(""+tuple.get(1));
				log.info(""+tuple.get(2));
				log.info(""+tuple.get(3));
				log.info(""+tuple.get(4));
				log.info(""+tuple.get(5));
			}
			
		}
	    
	    public List<Sede> sedesAutoJs(int idAut) {
			// TODO Auto-generated method stub
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Sede> conBd = cb.createQuery(Sede.class);
			Root<Sede> rootSed = conBd.from(Sede.class);
			conBd.where(cb.equal(rootSed.get("sed_autoridad"), idAut));
			conBd.select(rootSed);
			listaSedesAuJs = em.createQuery(conBd).getResultList();
				for (Sede sede : listaSedesAuJs) {
						sede.getEmpresaSed().getEmp_razon_social();
						sede.getDepartamento().getDept_nombre();
						sede.getSedMunic().getMunic_nombre();
						
				}
			return listaSedesAuJs;
			
		}
	    
	    public List<Sede> sedesIdeamJs() {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			
			listaSedesIdeJs=null;
			try {

			CriteriaQuery<Sede> conBd = cb.createQuery(Sede.class);
			Root<Sede> rootSed = conBd.from(Sede.class);
			conBd.select(rootSed);
			listaSedesIdeJs = em.createQuery(conBd).getResultList();
				for (Sede sede : listaSedesIdeJs) {
						sede.getEmpresaSed().getEmp_razon_social();
						sede.getDepartamento().getDept_nombre();
						sede.getSedMunic().getMunic_nombre();
						
				}
			}  catch (Exception e) {
				
				log.info("Fallo lista de Declaraciones: " + e.getMessage());
				listaSedesIdeJs = null;
				
				
			} finally {

				return listaSedesIdeJs;
			}
	
			
		}

	    public void onTiGestionListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Sede sede) {
	    	SedesAuto();
	    }
	    
		public Sede getNewSede() {
			// TODO Auto-generated method stub
			return newSed;
		}

		@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
		public void initNewSede() {
			// TODO Auto-generated method stub
			newSed = new Sede();
		}
		
		public Usuario_Perfil getNewUsuP() {
			// TODO Auto-generated method stub
			return newUsPe;
		}

		@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
		public void initNewUsuP() {
			// TODO Auto-generated method stub
			newUsPe = new Usuario_Perfil();
		}
		

		   public Sede sedeTransEmp(int idTrEm) {
			   CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<Sede> conBd = cb.createQuery(Sede.class);
				Root<Sede> rootSed = conBd.from(Sede.class);
				Join<Sede, Empresa> joinSedEm = rootSed.join("empresaSed",JoinType.INNER);
				conBd.where(cb.equal(joinSedEm.get("emp_id"), idTrEm));
				conBd.select(rootSed);	
				Sede sede = em.createQuery(conBd).setMaxResults(0).getSingleResult();
				log.info(sede.toString());
			return sede;
		}
		   
			public boolean sedeUpd(Sede sed) throws Exception {
				// TODO Auto-generated method stub
		        try {

		            em.merge(sed);
		            sedeEventSrc.fire(sed);
		            initNewSede();
		        } catch (Exception e) {
		        	
		            Throwable t = e;
		            while ((t.getCause()) != null) {
		                t = t.getCause();
		            }
		            log.info("Exception:" + t.getMessage());
		           return false;
		        }
				return true;
			}




		public void registerSede() throws Exception {
			// TODO Auto-generated method stub
	        try {

	            log.info("Registering " + newSed);
	            em.persist(newSed);
	            sedeEventSrc.fire(newSed);
	            initNewSede();
	        } catch (Exception e) {
	            Throwable t = e;
	            while ((t.getCause()) != null) {
	                t = t.getCause();
	            }
	            log.info("Exception:" + t.getMessage());
	            throw ((Exception) t);
	        }
			
		}

		public Long contSedes(int idE) {

				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<Long> conBd = cb.createQuery(Long.class);
				Root<Sede> rootSed = conBd.from(Sede.class);
				conBd.select(cb.count(rootSed));
				conBd.where(cb.equal(rootSed.get("sed_empresa"), idE));
				sedesCon = em.createQuery(conBd).getSingleResult();
				log.info(""+sedesCon);
			return sedesCon;
		}

		public void darSedeId(int idS) {
			// TODO Auto-generated method stub
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Sede> conBd = cb.createQuery(Sede.class);
			Root<Sede> rootSed = conBd.from(Sede.class);
			conBd.select(rootSed);
			conBd.where(cb.equal(rootSed.get("sed_id"), idS));
			idSede = em.createQuery(conBd).getSingleResult();
			idSede.getEmpresaSed().getEmpCii().getCii_nombre();
			
		}






		
		
	    
	    

}
