package co.gov.ideam.sdstrp.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import co.gov.ideam.sdstrp.model.Ciiu4;
import co.gov.ideam.sdstrp.model.Declaracion;
import co.gov.ideam.sdstrp.model.Departamento;
import co.gov.ideam.sdstrp.model.Empresa;
import co.gov.ideam.sdstrp.model.EstadoMateria;
import co.gov.ideam.sdstrp.model.GestionUbicacion;
import co.gov.ideam.sdstrp.model.Municipio;
import co.gov.ideam.sdstrp.model.Programacion;
import co.gov.ideam.sdstrp.model.Residuos;
import co.gov.ideam.sdstrp.model.Sede;
import co.gov.ideam.sdstrp.model.TipoDocumento;
import co.gov.ideam.sdstrp.model.TipoEmbalaje;
import co.gov.ideam.sdstrp.model.TipoEmpaque;
import co.gov.ideam.sdstrp.model.TipoGestion;
import co.gov.ideam.sdstrp.model.TipoManejo;
import co.gov.ideam.sdstrp.model.TipoPeligrosidad;
import co.gov.ideam.sdstrp.model.TipoResiduos;
import co.gov.ideam.sdstrp.model.TipoVehiculo;
import co.gov.ideam.sdstrp.model.Ubicacion;
import co.gov.ideam.sdstrp.model.Usuario;



@Stateful
@Model
public class ConsultaComboDAO {
	
    @Inject
    private Logger log;

    @Inject
    private EntityManager em;
    
    static List<TipoResiduos> listTiResiduos;
    
    static List<EstadoMateria> listaEstadoM;;
    static List<GestionUbicacion> listaGestionUb;
    static List<TipoEmpaque> listaTipoEmpaque;
    static List<TipoEmbalaje> listaTipoEmbalaje;
    static List<TipoGestion> listaTipoGestion;
    static List<TipoPeligrosidad> listaTipoPeligrosidad;
    static List<TipoResiduos> listaTipoResiduos;
    static List<TipoManejo> ListaTipoManejo;
    static List<Departamento> listaDepar;
    static List<Municipio> listMuni;
    static List<Ciiu4> listCiiu;
    private List<Sede> sede;
    static List<Ubicacion> ubicaciones;
    private List<Sede> gestor;
    private List<TipoManejo> listaTipoManejoPad;
    private List<Sede> listSedeTransp;
    private List<Empresa> listaEmpTrans;
    private List<Sede> listaSedeGen;
    static List<TipoVehiculo> listaTipoVeh;
    private List<TipoVehiculo> listaVehDes;
    private List<Empresa> listaEmpresasGestor;
    private List<Sede> listaSedeGestor;
    private List<Municipio> listaMuniDepa;
    private List<Empresa> listEmprProgTrans;
    private List<Empresa> listEmprProgGes;
    private List<Sede> listaSedeProgTrans;
    private List<Sede> listaSedeProgGen;
    private List<TipoDocumento> listTiDocumento;
    private Sede sedetrs;
    private Sede sedeGene;
//    private List<TipoGestion> tipoGestion;
//    private List<TipoGestion> tipoGestion;
    
    
    
	
	
//	Metodos que retornan las listas de todos los combos

    @Produces
    @Named
    public List<TipoResiduos> getListTiResiduos() {
    	listaResiduo();
        return listTiResiduos;
    }
    
    @Produces
    @Named
    public List<TipoDocumento> getListTipoDocumento() {
    	listaResiduo();
        return listTiDocumento;
    }

    @Produces
    @Named
    public List<Sede> getListaSedeProgTrans() {
        return listaSedeProgTrans;
    } 
    
    
    @Produces
    @Named
    public List<Empresa> getListEmprProgTrans() {

        return listEmprProgTrans;
    }
    
    @Produces
    @Named
    public List<Municipio> getListaMunicipioDepa() {
    	
        return listaMuniDepa;
    }
    
    @Produces
    @Named
    public List<Ciiu4> getListCiiu() {
    	listaCiiu();
        return listCiiu;
    }


	@Produces
    @Named
    public List<Municipio> getlistMuni() 
    {	listaMunicipios();
        return listMuni;
    }
    
    
    @Produces
    @Named
    public List<Departamento> getListaDepar() {
    	listaDepartamentos();
        return listaDepar;
    }


	@Produces
    @Named
    public List<Empresa> getListaEmpresaGestor() {
    	listaEmpresasGestor();
        return listaEmpresasGestor;
    }
    
    @Produces
    @Named
    public List<Sede> getListaSedeGestor() {
        return listaSedeGestor;
    }
    

    
	@Produces
    @Named
    public List<Empresa> getListaEmpTrans() {
    	EmpresasTransComboPr();
        return listaEmpTrans;
    }
    
    @Produces
    @Named
    public List<TipoVehiculo> getListaTipoVeh() {
    	log.info("Dentro del get de vehiculos");
    	listaTipoVehiulo();
    	log.info("vehiculos "+listaTipoVeh);
        return listaTipoVeh;
    }
    
    @Produces
    @Named
    public List<TipoGestion> getListaTipoGestion() {
    	listaTipGestion();
        return listaTipoGestion;
    }
    
    @Produces
    @Named
    public List<TipoManejo> getListaTipoManejo() {
    	listaSubGestion();
        return ListaTipoManejo;
    }
    
    @Produces
    @Named
    public List<EstadoMateria> getListaEstadoMateria() {
    	listaEstadoMateria();
        return listaEstadoM;
    }
    
    @Produces
    @Named
    public List<GestionUbicacion> getListaGestionUbicacion() {
    	listaGestionUbica();
        return listaGestionUb;
    }
    
    @Produces
    @Named
    public List<TipoPeligrosidad> getListaTipoPeligrosidad() {
    	listaPeligro();
        return listaTipoPeligrosidad;
    }
    
    @Produces
    @Named
    public List<TipoEmpaque> getListaTipoEmpaque() {
    	listaEmpaque();
        return listaTipoEmpaque;
    }
    
    @Produces
    @Named
    public List<TipoEmbalaje> getListaTipoEmbalaje() {
    	listaEmbalaje();
        return listaTipoEmbalaje;
    }
    
    
    @Produces
    @Named
    public List<TipoManejo> getListaTipoManejoPad() {
        return listaTipoManejoPad;
    }
    
    @Produces
    @Named
    public List<Sede> getListaSedeTransp() {
    	
        return listSedeTransp;
    }
    
    @Produces
    @Named
    public List<Sede> getListaSedeGen() {
    	listaSedeGene();
        return listaSedeGen;
    }
    
    
    public void onTiGestionListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final TipoResiduos listResiduos) {
        listaResiduo();
    }
    
    public void onResiduosListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final TipoGestion tipoGestion) {
        listaTipGestion();
    }
    
    public void onTipoManejoListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final TipoManejo listaTipoManejo) {
        listaSubGestion();
    }
    
    public void onTipoEstadoMatListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final EstadoMateria listaEstadoM) {
    	listaEstadoMateria();
    }
    
    public void onGestionUbicacionListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final GestionUbicacion listaGestionUb) {
    	listaGestionUbica();
    }
    
    public void onGestionUbicacionListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final TipoPeligrosidad listaTipoPeligrosidad) {
    	listaPeligro();
    }
    public void onGestionUbicacionListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final TipoEmpaque listaTipoEmpaque) {
    	listaEmpaque();
    }
    
    public void onGestionUbicacionListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final TipoEmbalaje listaTipoEmbalaje) {
    	listaEmbalaje();
    }
    
    public void onTipoVehiculoListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final TipoVehiculo listaTipoVeh) {
    	listaTipoVehiulo();
    }
    
    public void onSedeTranspListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Sede listaSede) {
    	listaSedeGene();
    }
	
    
	public void listaResiduo(){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<TipoResiduos> conRes = cb.createQuery(TipoResiduos.class);	
			 Root<TipoResiduos> RootRes = conRes.from(TipoResiduos.class);
			 conRes.select(RootRes);
			 listTiResiduos = em.createQuery(conRes).getResultList();
		        log.info(" "+ listTiResiduos);
		
	}
	
    
    private void listaCiiu() {
    	CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Ciiu4> conRes = cb.createQuery(Ciiu4.class);	
		 Root<Ciiu4> RootRes = conRes.from(Ciiu4.class);
		 conRes.select(RootRes);
		 listCiiu = em.createQuery(conRes).getResultList();
		
	}
    
    public void darListaTipoDocumento() {
    	CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TipoDocumento> conRes = cb.createQuery(TipoDocumento.class);	
		 Root<TipoDocumento> RootRes = conRes.from(TipoDocumento.class);
		 conRes.select(RootRes);
		 listTiDocumento = em.createQuery(conRes).getResultList();
		
	}
	
    private void listaMunicipios() {
		// TODO Auto-generated method stub
    	CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Municipio> conRes = cb.createQuery(Municipio.class);	
		 Root<Municipio> RootDep = conRes.from(Municipio.class);
		 conRes.select(RootDep);
		 listMuni = em.createQuery(conRes).getResultList();
		
	}    
    
    

    private void listaDepartamentos() {
		// TODO Auto-generated method stub
    	CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Departamento> conRes = cb.createQuery(Departamento.class);	
		 Root<Departamento> RootDep = conRes.from(Departamento.class);
		 conRes.select(RootDep);
		 listaDepar = em.createQuery(conRes).getResultList();
		
	}
	
    private void listaEmpresasGestor() {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Empresa> conBd = cb.createQuery(Empresa.class);
		Root<Sede> rootEmp = conBd.from(Sede.class);
		Join<Sede,Empresa> joinSede = rootEmp.join("empresaSed",JoinType.INNER);
		conBd.where(cb.equal(rootEmp.get("sed_generador"),"S"));
		conBd.groupBy(joinSede.get("emp_id"),               
				joinSede.get("emp_ciiu4"),          
				joinSede.get("emp_id_ubica"),       
				joinSede.get("emp_cc_represen"),     
				joinSede.get("emp_ciiu4"),          
				joinSede.get("emp_ext"),             
				joinSede.get("emp_id_aut"),          
				joinSede.get("emp_id_ubica"),        
				joinSede.get("emp_nombre_comercial"),
				joinSede.get("emp_numero_documento"),
				joinSede.get("emp_razon_social"),    
				joinSede.get("emp_rep_email"),       
				joinSede.get("emp_rep_nombre"),      
		        joinSede.get("emp_telefono"),        
		        joinSede.get("emp_tip_docu"));
		conBd.select(joinSede);
		listaEmpresasGestor = em.createQuery(conBd).getResultList();
		
	}
	
	public void EmpresasTransCombo()
	{
		try {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Empresa> conBd = cb.createQuery(Empresa.class);
		Root<Sede> rootEmp = conBd.from(Sede.class);
		Join<Sede,Empresa> joinSede = rootEmp.join("empresaSed",JoinType.INNER);
		conBd.where(cb.equal(rootEmp.get("sed_transportador"),"S"));
		conBd.groupBy(rootEmp.get("sed_id"));
		conBd.select(joinSede);
		listaEmpTrans = em.createQuery(conBd).getResultList();
		for (Empresa emp : listaEmpTrans) {
			log.info(emp.toString());
		}
		} catch (Exception e) {
			// TODO: handle exception
			 	System.out.println("Fallo lista empresas trnasporte: " + e.getMessage());
	            e.fillInStackTrace();
		}
	}
	
	public void EmpresasTransComboPr()
	{
		try {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Empresa> conBd = cb.createQuery(Empresa.class);
		Root<Empresa> rootEmp = conBd.from(Empresa.class);
		Join<Empresa,Sede> joinSede = rootEmp.join("empresaSed",JoinType.INNER);
		conBd.where(cb.equal(joinSede.get("sed_transportador"),"S"));
		conBd.groupBy(rootEmp.get("emp_id"),               
				rootEmp.get("emp_ciiu4"),          
				rootEmp.get("emp_id_ubica"),       
				rootEmp.get("emp_cc_represen"),     
				rootEmp.get("emp_ciiu4"),          
				rootEmp.get("emp_ext"),             
				rootEmp.get("emp_id_aut"),          
				rootEmp.get("emp_id_ubica"),        
				rootEmp.get("emp_nombre_comercial"),
				rootEmp.get("emp_numero_documento"),
				rootEmp.get("emp_razon_social"),    
				rootEmp.get("emp_rep_email"),       
				rootEmp.get("emp_rep_nombre"),      
		        rootEmp.get("emp_telefono"),        
		        rootEmp.get("emp_tip_docu"));         
				conBd.select(rootEmp);
		listaEmpTrans = em.createQuery(conBd).getResultList();
		for (Empresa emp : listaEmpTrans) {
			log.info(emp.toString());
		}
		} catch (Exception e) {
			// TODO: handle exception
			 	System.out.println("Fallo lista empresas trnasporte: " + e.getMessage());
	            e.fillInStackTrace();
		}
	}
	
	public void listaTipoVehiulo() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TipoVehiculo> conRes = cb.createQuery(TipoVehiculo.class);	
		 Root<TipoVehiculo> RootRes = conRes.from(TipoVehiculo.class);
		 conRes.select(RootRes);
		 listaTipoVeh = em.createQuery(conRes).getResultList();
		 log.info("resultado "+ em.createQuery(conRes).getResultList());
	        log.info(" "+ listaTipoVeh);
	}
	
	


	public void listaTipGestion() {
		CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<TipoGestion> conRes = cb.createQuery(TipoGestion.class);	
			 Root<TipoGestion> RootRes = conRes.from(TipoGestion.class);
			 conRes.select(RootRes);
			 listaTipoGestion = em.createQuery(conRes).getResultList();
			 log.info(" "+ listaTipoGestion);
				}





	public void listaEstadoMateria() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<EstadoMateria> conRes = cb.createQuery(EstadoMateria.class);	
			 Root<EstadoMateria> RootRes = conRes.from(EstadoMateria.class);
			 conRes.select(RootRes);
			 listaEstadoM = em.createQuery(conRes).getResultList();
			 
			
		} catch (Exception e) {
			// TODO: handle exception
            System.out.println("Fallo lista de estado: " + e.getMessage());
            e.fillInStackTrace();
		}

		
	}


	public void listaGestionUbica() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<GestionUbicacion> conRes = cb.createQuery(GestionUbicacion.class);	
			 Root<GestionUbicacion> RootRes = conRes.from(GestionUbicacion.class);
			 conRes.select(RootRes);
			 listaGestionUb = em.createQuery(conRes).getResultList();	
		} catch (Exception e) {
			// TODO: handle exception
            System.out.println("Fallo lista de de gestion de ubicacion: " + e.getMessage());
            e.fillInStackTrace();
		}

	}




	public void listaEmpaque() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<TipoEmpaque> conRes = cb.createQuery(TipoEmpaque.class);	
			 Root<TipoEmpaque> RootRes = conRes.from(TipoEmpaque.class);
			 conRes.select(RootRes);
			 listaTipoEmpaque = em.createQuery(conRes).getResultList();
			 
			
		} catch (Exception e) {
			// TODO: handle exception
            System.out.println("Fallo lista de empaques: " + e.getMessage());
            e.fillInStackTrace();
		}
	}




	public void listaEmbalaje() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<TipoEmbalaje> conRes = cb.createQuery(TipoEmbalaje.class);	
			 Root<TipoEmbalaje> RootRes = conRes.from(TipoEmbalaje.class);
			 conRes.select(RootRes);
			 listaTipoEmbalaje = em.createQuery(conRes).getResultList();
			 
			
		} catch (Exception e) {
            System.out.println("Fallo lista de usuario: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
	}





public Sede sedeTrans(int idEmp)
{
	CriteriaBuilder cb = em.getCriteriaBuilder();
	try {
		CriteriaQuery<Sede> conRes =  cb.createQuery(Sede.class);	
		
		 Root<Sede> RootRes = conRes.from(Sede.class);
		 conRes.where(cb.and(cb.equal(RootRes.get("sed_empresa"), idEmp),cb.equal(RootRes.get("sed_transportador"), "S")));
		 conRes.select(RootRes);
		 TypedQuery<Sede> query =  em.createQuery(conRes); 
		 sedetrs = query.setMaxResults(1).getSingleResult();
		log.info(sedetrs.toString());

	} catch (Exception e) {
        System.out.println("Fallo lista de tipo de gestion: " + e.getMessage());
        e.fillInStackTrace();
		// TODO: handle exception
	}
	return sedetrs;
	
	
}


	public void listaPeligro() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<TipoPeligrosidad> conRes = cb.createQuery(TipoPeligrosidad.class);	
			 Root<TipoPeligrosidad> RootRes = conRes.from(TipoPeligrosidad.class);
			 conRes.select(RootRes);
			 listaTipoPeligrosidad = em.createQuery(conRes).getResultList();
			 
			
		} catch (Exception e) {
            System.out.println("Fallo lista de tipo de gestion: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
	}
	
	
	public List<TipoManejo> listaSubGestionP(String padre)
	{
		log.info("tipo de gestion consulta");
		TipoGestion gestion1 = em.find(TipoGestion.class, padre);
		log.info(""+padre);
		log.info(""+gestion1);
		log.info(""+gestion1.getId_tip_gestion());
		String pd = gestion1.getId_tip_gestion();
		int pad = Integer.parseInt(pd);
		String tma_pad = null;
		if(pad==1){tma_pad = "ALMC";}
		else if(pad==2){tma_pad = "APRV";}
		else if(pad==3){tma_pad = "TRTM";}
		else if(pad==4){tma_pad = "DISP";}
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<TipoManejo> conRes = cb.createQuery(TipoManejo.class);	
			 Root<TipoManejo> RootRes = conRes.from(TipoManejo.class);
			 conRes.where(cb.equal(RootRes.get("tma_padre"), tma_pad));
			 conRes.select(RootRes);
			 listaTipoManejoPad = em.createQuery(conRes).getResultList();
			 
			
		} catch (Exception e) {
            System.out.println("Fallo lista de tipo de gestion: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
		
		return  listaTipoManejoPad;
	}




	public void listaSubGestion() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<TipoManejo> conRes = cb.createQuery(TipoManejo.class);	
			 Root<TipoManejo> RootRes = conRes.from(TipoManejo.class);
		
			 conRes.select(RootRes);
			 ListaTipoManejo = em.createQuery(conRes).getResultList();
		} catch (Exception e) {
            System.out.println("Fallo lista de tipo de manejo: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
	}




	public List<Sede> listaTransp() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Sede> conRes = cb.createQuery(Sede.class);	
			 Root<Sede> RootRes = conRes.from(Sede.class);
			 conRes.where(cb.equal(RootRes.get("sed_transportador"), "s"));
			 conRes.select(RootRes);
			 sede = em.createQuery(conRes).getResultList();
		} catch (Exception e) {
            System.out.println("Fallo lista de tipo de manejo: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
		return sede;
	}




	// Esta consulta no me convense del todo, no entiendo funcionamiento, preguntar.
//	public void listaSedeTrans() {
//		// TODO Auto-generated method stub
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		try {
//			CriteriaQuery<Sede> conRes = cb.createQuery(Sede.class);	
//			 Root<Sede> RootRes = conRes.from(Sede.class);
//			 conRes.where(cb.equal(RootRes.get("sed_transportador"), "S"));
//			 conRes.select(RootRes);
//			 listSedeTransp = em.createQuery(conRes).getResultList();
//		} catch (Exception e) {
//            System.out.println("Fallo lista de tipo de manejo: " + e.getMessage());
//            e.fillInStackTrace();
//		}
//	}
	
	// Esta consulta no me convense del todo, no entiendo funcionamiento, preguntar.
	public void listaSedeGene() {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Sede> conRes = cb.createQuery(Sede.class);	
			 Root<Sede> RootRes = conRes.from(Sede.class);
			 conRes.where(cb.equal(RootRes.get("sed_generador"), "S"));
			 conRes.select(RootRes);
			 listaSedeGen = em.createQuery(conRes).getResultList();
		} catch (Exception e) {
            System.out.println("Fallo lista de tipo de manejo: " + e.getMessage());
            e.fillInStackTrace();
		}
	}




	public List<Sede> listaGestor() {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Sede> conRes = cb.createQuery(Sede.class);	
			 Root<Sede> RootRes = conRes.from(Sede.class);
			 conRes.where(cb.equal(RootRes.get("sed_gestor"), "s"));
			 conRes.select(RootRes);
			 sede = em.createQuery(conRes).getResultList();
		} catch (Exception e) {
            System.out.println("Fallo lista de tipo de manejo: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
		return gestor;
	}

	public List<TipoVehiculo> listaVehDesginacion(String codTtrn) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			log.info("//////////////////////////////////////////////////////////////");
			log.info(codTtrn);
			CriteriaQuery<TipoVehiculo> conRes = cb.createQuery(TipoVehiculo.class);	
			 Root<TipoVehiculo> RootVh = conRes.from(TipoVehiculo.class);
			 conRes.where(cb.equal(RootVh.get("tve_padre"),codTtrn));
			 conRes.select(RootVh);
			 listaVehDes = em.createQuery(conRes).getResultList();
		} catch (Exception e) {
            System.out.println("Fallo lista de tipo de manejo: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}		
		for (TipoVehiculo tipoVehiculo : listaVehDes) {
			
			log.info(tipoVehiculo.getTve_id()+"");
			log.info(tipoVehiculo.getTve_nombre());
			log.info(tipoVehiculo.getTve_padre());
		}
		return listaVehDes;
	}

	public List<Sede> verListaSedeTrans(String codEm) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			log.info("//////////////////////////////////////////////////////////////");
			log.info(codEm);
			CriteriaQuery<Sede> conRes = cb.createQuery(Sede.class);	
			 Root<Sede> RootVh = conRes.from(Sede.class);
			 conRes.where(cb.and(cb.equal(RootVh.get("sed_empresa"),codEm),cb.equal(RootVh.get("sed_transportador"), "S")));
			 conRes.select(RootVh);
			 listSedeTransp = em.createQuery(conRes).getResultList();
		} catch (Exception e) {
            System.out.println("Fallo lista de tipo de manejo: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
		
		return listSedeTransp;
	}


	public List<Sede> verListaSedeGes(String codEmG) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			log.info("//////////////////////////////////////////////////////////////");
			log.info(codEmG);
			CriteriaQuery<Sede> conRes = cb.createQuery(Sede.class);	
			 Root<Sede> RootVh = conRes.from(Sede.class);
			 conRes.where(cb.and(cb.equal(RootVh.get("sed_empresa"),codEmG),cb.equal(RootVh.get("sed_gestor"), "S")));
			 conRes.select(RootVh);
			 listaSedeGestor = em.createQuery(conRes).getResultList();
		} catch (Exception e) {
            System.out.println("Fallo lista de tipo de manejo: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
		
		return listaSedeGestor;
	}

	public List<Municipio> verListaMuniDepa(int idDepa) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			log.info("//////////////////////////////////////////////////////////////");
			log.info(idDepa+"");
			CriteriaQuery<Municipio> conRes = cb.createQuery(Municipio.class);	
			 Root<Municipio> RootVh = conRes.from(Municipio.class);
			 conRes.where(cb.equal(RootVh.get("id_munic_dept"), idDepa));
			 conRes.select(RootVh);
			 listaMuniDepa = em.createQuery(conRes).getResultList();
			 log.info(listaMuniDepa.size()+"");
		} catch (Exception e) {
            System.out.println("Fallo lista de tipo de manejo: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
		
		return listaMuniDepa;

	}
	
	
	public void sedesTransProg(int idGen)
	{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {

			CriteriaQuery<Sede> conRes = cb.createQuery(Sede.class);		
			Root<Sede> rootEmp  = conRes.from(Sede.class);
	
			Subquery<Long> sbq = conRes.subquery(Long.class);
			Root<Residuos> rootsqDe = sbq.from(Residuos.class);
			sbq.where(cb.equal(rootsqDe.get("res_sede_generador"),idGen));
			sbq.groupBy(rootsqDe.get("res_sede_transporte"));
			sbq.select(rootsqDe.get("res_sede_transporte")); 
			Expression<Long> rGen = sbq.getSelection();


			
//			Path<Long> agegadas = (Path<Long>) cb.count(rootDeRe); 
//			Expression<ResultType> expression = cb.coalesce(sbq, cb.literal(null) literal((ResultType) defaultResult);

			conRes.select(rootEmp);
			conRes.where(cb.in(rootEmp.get("sed_id")).value(rGen));
			log.info(em.createQuery(conRes)+"");
			listaSedeProgTrans = em.createQuery(conRes).getResultList();
			for (Sede sede : listaSedeProgTrans) {
				log.info(sede.getSed_nombre());
				log.info(sede.getEmpresaSed().getEmp_nombre_comercial());
			}
			
			
		} catch (Exception e) {
            System.out.println("Fallo validacion de sedes transportador:  " + e.getMessage());
            e.fillInStackTrace();
		}
		
	}

	public List<Sede> listaGeneraTrPr(int idTra, int idGen) 
	{
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try 
		{
			CriteriaQuery<Sede> conRes = cb.createQuery(Sede.class);		
			Root<Sede> rootEmp  = conRes.from(Sede.class);
			
			
			
			Subquery<Long> sbq = conRes.subquery(Long.class);
			Root<Residuos> rootsqDe = sbq.from(Residuos.class);
			sbq.where(cb.and(cb.equal(rootsqDe.get("res_sede_generador"),idGen),cb.equal(rootsqDe.get("res_sede_transporte"),idTra)));
			sbq.groupBy(rootsqDe.get("res_sede_gestor"));
			sbq.select(rootsqDe.get("res_sede_gestor")); 
			Expression<Long> rGen = sbq.getSelection();


			conRes.select(rootEmp);
			conRes.where(cb.in(rootEmp.get("sed_id")).value(rGen));
			log.info(em.createQuery(conRes)+"");
			listaSedeProgGen = em.createQuery(conRes).getResultList();
			for (Sede sede : listaSedeProgGen) {
				log.info(sede.getEmpresaSed().getEmp_id()+"");
			}
			
			
		} catch (Exception e) {
            System.out.println("Fallo validacion de usuario:  " + e.getMessage());
            e.fillInStackTrace();
		}
		
		return listaSedeProgGen;
	}

	public Sede SedeGes(int idGenEmp) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Sede> conRes = cb.createQuery(Sede.class);	
			 Root<Sede> RootRes = conRes.from(Sede.class);
			 Join<Sede, Empresa> joinSeEm = RootRes.join("empresaSed",JoinType.INNER);

			 conRes.where(cb.and(cb.equal(joinSeEm.get("emp_id"), idGenEmp),cb.equal(RootRes.get("sed_gestor"), "S")));
			 conRes.select(RootRes);
			 sedeGene = em.createQuery(conRes).setMaxResults(1).getSingleResult();
			 log.info(sedeGene.toString());

		} catch (Exception e) {
	        System.out.println("Fallo lista de tipo de gestion: " + e.getMessage());
	        e.fillInStackTrace();
			// TODO: handle exception
		}
		return sedeGene;

	}

	public List<Residuos> listaResiduosGenTran(int idGes, int idTrans) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		List<Residuos> resi = null;
		try {
			CriteriaQuery<Residuos> conRes = cb.createQuery(Residuos.class);	
			 Root<Residuos> RootRes = conRes.from(Residuos.class);
			 Join<Residuos, Sede> joinSeTR = RootRes.join("sedeTrans",JoinType.INNER);
			 Join<Residuos, Sede> joinSeGs = RootRes.join("sedeGest",JoinType.INNER);
			 conRes.where(cb.and(cb.equal(RootRes.get("res_sede_transporte"), idTrans),cb.equal(RootRes.get("res_sede_gestor"), idGes)));
			 conRes.select(RootRes);
			 resi = em.createQuery(conRes).getResultList();

		} catch (Exception e) {
	        System.out.println("Fallo lista de tipo de gestion: " + e.getMessage());
	        e.fillInStackTrace();
			// TODO: handle exception
		}
		return resi;
	}




		
	
}
