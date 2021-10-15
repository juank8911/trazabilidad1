package co.gov.ideam.sdstrp.controller;



import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
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
import co.gov.ideam.sdstrp.model.GestionUbicacion;
                           
import co.gov.ideam.sdstrp.model.Residuos;
import co.gov.ideam.sdstrp.model.Sede;
import co.gov.ideam.sdstrp.model.ServicioGestor;
import co.gov.ideam.sdstrp.model.TipoGestion;
import co.gov.ideam.sdstrp.model.TipoManejo;
import co.gov.ideam.sdstrp.model.TipoResiduos;


@Stateful
@Model
public class ResiduoDAO {
	
    @Inject
    private Logger log;
    

    static List<Tuple> listAutorizacion;
    static List<Tuple> listSrevGestorId;
    static List<Tuple> listaResiduosRes;
    static List<Tuple> listaResiduosId;
    static List<Tuple> listaResiduosGenId;
    static List<Residuos> listaResiduos;
    static List<TipoResiduos> listaTresiduos;
    static Residuos acResiduo;
    static String titulo;


    
    private String listKLoc;
    
    @Inject
    private EntityManager em;
    
    @Inject
    private Event<ServicioGestor> serGestEventSrc;
    
    @Inject
    private Event<Residuos> resEventSrc;
    

    
    private ServicioGestor newServG;
    private Residuos newRes;
    
    
     @SessionScoped
	 private List<Tuple> listResiGestor;
     

    
    
    

    
//	-----------------------------------------------------------------------------------------------------	

     

     
     @Produces
	    @Named
	    public List<TipoResiduos> getListaTresiduos()
	    {
    	 	this.verListaTResiduos();
	    	return listaTresiduos;
	    }
	    
	    

		@Produces
	    @Named
	    public List<Tuple> getListResiGestorId()
	    {
	    	return listSrevGestorId;
	    }
	    

		/**
		 * @return the titulo
		 */
	    @Produces
	    @Named
		public String getTitulo() {
			return titulo;
		}


		/**
		 * @param titulo the titulo to set
		 */
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

	    @Produces
	    @Named
		public List<Residuos> getListaResiduos(int idSede) {
			// TODO Auto-generated method stub
			ListaSedesPr();
			listarResiduo(idSede);
			return listaResiduos;
		}
	    
	    @Produces
	    @Named
	    public List<Tuple> getListaResiduosGenId()
	    {
	    	return listaResiduosGenId;
	    }
	    
	    @Produces
	    @Named
	    public Residuos getAcResiduo()
	    {
	    	return acResiduo;
	    }
	    
	    
	    
	    @Produces
	    @Named
	    public List<Tuple> getListaResiduosResult() {
	    	listarResultResiduos();;
	        return listaResiduosRes;
	    }
	    
//	   -----------------------------------------------------------------------------------------------
	    public void onTiGestionListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final TipoResiduos residuos) {
	        listarResultResiduos();
	    }
	    
//	   ----------------------------------------------------------------------------------------------
	    

	    public void findResiduoId(int id)
	    {
	    	acResiduo = em.find(Residuos.class, id);
	    }
	    
	    public Residuos findResiduo(int id)
	    {
	    
	    	acResiduo = em.find(Residuos.class, id);
	    	acResiduo.gettGestion().getId_tip_gestion();
	    	acResiduo.gettManejo().getTma_id();
	    	acResiduo.gettResiduo().getTre_id();
	    	acResiduo.getEstadoM().getEma_id();
	    	acResiduo.gettPeligro().getTpe_id();
	    	acResiduo.getgUbicacion().getId_gestion_ubi();
	    	acResiduo.getjTipoEmb().getTem_id();
	    	acResiduo.getjTipoEmp().getTep_id();
	    	acResiduo.getSedeGest().getEmpresaSed().getEmp_nombre_comercial();
	    	acResiduo.getSedeTrans().getEmpresaSed().getEmp_nombre_comercial();
	    	
	    	return acResiduo;
	    }
	    
	    private void verListaTResiduos() {
			// TODO Auto-generated method stub
	    	CriteriaBuilder cb = em.getCriteriaBuilder();
	    	try {
				CriteriaQuery<TipoResiduos> conTRes = cb.createQuery(TipoResiduos.class);
				Root<TipoResiduos> rootTres = conTRes.from(TipoResiduos.class);
				conTRes.select(rootTres);
				listaTresiduos = em.createQuery(conTRes).getResultList();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	    
		public void listarResiduo(int idSede) {

			CriteriaBuilder cb = em.getCriteriaBuilder();

			try {
				
				CriteriaQuery<Residuos> conResi = cb.createQuery(Residuos.class);	
				 Root<Residuos> RootRes = conResi.from(Residuos.class);
				 conResi.where(cb.equal(RootRes.get("res_sede_generador"), idSede));
				 conResi.select(RootRes);
				 listaResiduos = em.createQuery(conResi).getResultList();
				for (Residuos resi : listaResiduos) {
					resi.gettGestion().getNombre_gestion();
					resi.gettManejo().getTma_nombre();
					resi.getgUbicacion().getNombre_gestion_ubi();
					resi.gettGestion().getNombre_gestion();
					resi.gettResiduo().getTre_nombre();
					resi.getEstadoM().getEma_nombre();
					
				}
				 		 
			} catch (Exception e) {
			
				log.info("Fallo lista de Residuios: " + e.getMessage());
				
				
			} 

		}
		
		
		public void ListaSedesPr()
		{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			try {
				CriteriaQuery<Sede> ConSed = cb.createQuery(Sede.class);
				Root<Sede> rootSed = ConSed.from(Sede.class);
				ConSed.select(rootSed);
				List<Sede> sed = em.createQuery(ConSed).getResultList();
				log.info(sed+"");
				
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Fallo lista de sedes: " + e.getMessage());
	            e.fillInStackTrace();
				// TODO: handle exception
			}
		}
	    
	    
	    
	    /*
	     * Lista servicios del gestor por su id 
	     */
	public void listarResiduoGestor(int vsG) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			
			 CriteriaQuery<Tuple> conRes = cb.createTupleQuery();	
			 Root<ServicioGestor> RootRes = conRes.from(ServicioGestor.class);
			 conRes.where(cb.equal(RootRes.get("sg_gestor"), vsG));
			 Join<ServicioGestor, TipoResiduos> jointTRE = RootRes.join("tiResiduos", JoinType.INNER);
			 Join<ServicioGestor, TipoGestion> jointTGE = RootRes.join("tiGestion", JoinType.INNER);
			 Join<ServicioGestor, TipoManejo> jointMNJ = RootRes.join("tiManejo", JoinType.INNER);
			 conRes.multiselect(RootRes.get("sg_id"),RootRes.get("sg_gestion"),RootRes.get("sg_sub_gestion"), jointTRE.get("tre_nombre"),jointTRE.get("tre_id"),jointTGE.get("nombre_gestion"),jointMNJ.get("tma_nombre"));
			 listSrevGestorId = em.createQuery(conRes).getResultList();
			 		 
		} catch (Exception e) {
            System.out.println("Fallo lista de residuos gestor por id: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}

	}
	
	
	
	
    public void register() throws Exception {

        try {

            log.info("Registering " + newServG);
            em.persist(newServG);
            serGestEventSrc.fire(newServG);
            initNewServicioGestor();
            listarResiduoGestor(newServG.getSg_gestor());
        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }
            log.info("Exception:" + t.getMessage());
            throw ((Exception) t);
        }

    }
    
    public void registerResiduo() throws Exception {
    	
        try {

            log.info("Registering " + newRes);
            em.persist(newRes);
            resEventSrc.fire(newRes);
            initNewResiduo();
        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }
            log.info("Exception:" + t.getMessage());
            throw ((Exception) t);
        }
    	
    }
	
	
	
//	Matodos para inicializar nuevo servicio y agregarlo
	
    @Produces
    @Named
    public ServicioGestor getNewServGest() {

        log.info("getNewMember: called" + newServG);
        return newServG;
    }
	
	public Residuos getNewResiGest() {
		
		return newRes;
	}
	
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void initNewServicioGestor() {
    	newServG = new ServicioGestor();
        log.info("@PostConstruct:initNewServicioGestor called");
    }

    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void initNewResiduo() {
    	newRes = new Residuos();
        log.info("@PostConstruct:initNewResiduo called");
    }


//-----------------------------------------------------------------------------------------------------------
    //************************************GESTOR************************************************************
//-----------------------------------------------------------------------------------------------------------


	
    
	public void listarResiduoGeneradorId(int rs) {
		// TODO Auto-generated method stub
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			 CriteriaQuery<Tuple> conRes = cb.createTupleQuery();	
			 Root<Residuos> rootRes = conRes.from(Residuos.class);
			 Join<Residuos, TipoResiduos> jointTRE = rootRes.join("tResiduo", JoinType.INNER);
			 Join<Residuos, GestionUbicacion> joinGEU = rootRes.join("gUbicacion", JoinType.INNER);
			 Join<Residuos, TipoManejo> jointMNJ = rootRes.join("tManejo", JoinType.INNER);
			 Join<Residuos, TipoGestion> jointTGE = rootRes.join("tGestion", JoinType.INNER);
			 Join<Residuos, Sede> joinTrn = rootRes.join("sedeTrans", JoinType.INNER);
			 Join<Residuos, Sede> joinGes = rootRes.join("sedeGest", JoinType.INNER);
			 Join<Sede, Empresa> joinEmpGes = joinGes.join("empresaSed",JoinType.INNER);
			 Join<Sede, Empresa> joinEmptrn = joinTrn.join("empresaSed",JoinType.INNER);
			 conRes.where(cb.equal(rootRes.get("res_sede_generador"), rs));
			 conRes.multiselect(rootRes.get("res_id"),rootRes.get("res_nombre"),rootRes.get("res_tipo_residuo"),
					 jointTRE.get("tre_nombre"),joinGEU.get("nombre_gestion_ubi"),jointTGE.get("nombre_gestion"),jointMNJ.get("tma_nombre"),
					 joinEmptrn.get("emp_nombre_comercial"),joinEmpGes.get("emp_nombre_comercial"));
			 listaResiduosGenId = em.createQuery(conRes).getResultList();
			 log.info(listaResiduosGenId.size()+"");
			 for (Tuple tuple : listaResiduosGenId) {
				 tuple.get(0);
				 tuple.get(1);
				 tuple.get(2);
				 tuple.get(3);	
				 tuple.get(7);
			}
			 
		} catch (Exception e) {
            System.out.println("Fallo lista de generador por id: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
		
	}

	public void listarResultResiduos() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			 CriteriaQuery<Tuple> conRes = cb.createTupleQuery();	
			 Root<Residuos> rootRes = conRes.from(Residuos.class);
			 Join<Residuos, TipoResiduos> jointTRE = rootRes.join("tResiduo", JoinType.INNER);
			 Join<Residuos, GestionUbicacion> joinGEU = rootRes.join("gUbicacion", JoinType.INNER);
			 Join<Residuos, TipoManejo> jointMNJ = rootRes.join("tManejo", JoinType.INNER);
			 Join<Residuos, TipoGestion> jointTGE = rootRes.join("tGestion", JoinType.INNER);
			 conRes.multiselect(rootRes.get("res_id"),rootRes.get("res_nombre"),rootRes.get("res_tipo_residuo"),
			 jointTRE.get("tre_nombre"),joinGEU.get("nombre_gestion_ubi"),jointTGE.get("nombre_gestion"),jointMNJ.get("tma_nombre"));
			 listaResiduosRes = em.createQuery(conRes).getResultList();
			 
			 
		} catch (Exception e) {
            System.out.println("Fallo lista de autorizaciones por autoridad: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
				
	}

	public void UpdateResiduo(Residuos resiUp) throws Exception {
		// TODO Auto-generated method stub
        try {

            log.info("Registering " + resiUp);
            em.merge(resiUp);
            resEventSrc.fire(resiUp);
        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }
            log.info("Exception:" + t.getMessage());
            throw ((Exception) t);
        }
		
		
	}


	
	

	
	
	

	

}
