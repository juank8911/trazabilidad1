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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.AssertTrue;
import javax.ws.rs.GET;


import co.gov.ideam.sdstrp.model.Autoridad;
import co.gov.ideam.sdstrp.model.Departamento;
import co.gov.ideam.sdstrp.model.Empresa;
import co.gov.ideam.sdstrp.model.Perfil;
import co.gov.ideam.sdstrp.model.Sede;
import co.gov.ideam.sdstrp.model.Usuario;
import co.gov.ideam.sdstrp.model.Usuario_Perfil;

@Model
@Stateful
public class UserDao {
	
    @Inject
    private Logger log;

    @Inject
    private EntityManager em;
    private Usuario_Perfil newUsuPer;
    private List<Tuple> tuple;
    private Object result;
    private Usuario newUsu;
    private Usuario usuario;
    private int perfil ;
    static List<Perfil> inSelPerfiles;
    static Perfil SelPerfil;
    private Usuario usuLog;
    private Usuario pSUsuario;
    private List<Perfil> listUsuPerfil;
    private List<Usuario> listUsuaAut;
    private List<Usuario> listUsuaIdea;
    @Inject
    private Event<Usuario> usuarioEventSrc;
    @Inject
    private Event<Usuario_Perfil> usuPerEventSrc;
    

    
    public Object getObject()
    {
		return result;
    	
    }

    @Produces
    @Named
    public List<Perfil> getInSelPerfiles()
    {	
    	
    	return inSelPerfiles;
    }
    
    @Produces
    @Named
    public Perfil getSelPerfil()
    {
    	
    	return SelPerfil;
    }
    
    @Produces
    @Named
    public int getPerfil()
    {
    	return perfil;
    }
    
    
    
    @Produces
    @Named
    public Usuario getpSUsuario()
    {
    	
    	return pSUsuario; 
    }
    
    
    
    
    public Usuario ingresar(String usr,String pssw) throws Exception
    {
    	
    		Usuario usuario = new Usuario();
    		log.info("dentro de ingresar");
    		CriteriaBuilder cb = em.getCriteriaBuilder();
    		try {
    			
    	    	//creacion de builder criteria
//        		CriteriaQuery<Usuario> conCons = cb.createQuery(Usuario.class);
        		CriteriaQuery<Usuario> conCons = cb.createQuery(Usuario.class);
                log.info("Clase usuario"+Usuario.class);
                Root<Usuario> raizCons = conCons.from(Usuario.class);
//                Join<Sede,Departamento> join_depto = joinSede.join("departamento", JoinType.INNER);
                log.info(""+usr);
                log.info(""+pssw);
                log.info("Form usuario"+raizCons);
                conCons.where(cb.and(cb.equal(raizCons.get("usr_nombre"),usr),cb.equal(raizCons.get("usr_clave"),pssw),cb.notEqual(raizCons.get("usr_estado"),0)));
                conCons.select(raizCons);
                System.out.println("respuesta de get List ------------");
                System.out.println(em.createQuery(conCons).getResultList());
                usuario =  em.createQuery(conCons).getSingleResult();
               System.out.println("pruebas de datos");
                System.out.println(usuario.toString());
                 	usuario.getSedeUsu().getEmpresaSed();
                 	
                 	for (Usuario_Perfil perfil : usuario.getUsuaPer()) {
                 		perfil.getUsuaPer().getUsr_nom_persona();
    					perfil.getPerUsu().getPerfil();
					}
    			
			} catch (Exception e) {
	            System.out.println("Fallo validacion de usuario: " + e.getMessage());
	            e.fillInStackTrace();
			}
    		
    		 return usuario;

    }
    




	public Usuario ingresar1(String user, String pass) throws Exception {
		// TODO Auto-generated method stub		Usuario usuario = new Usuario();
		log.info("dentro de ingresar");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			
	    	//creacion de builder criteria
//    		CriteriaQuery<Usuario> conCons = cb.createQuery(Usuario.class);
    		CriteriaQuery<Usuario> conCons = cb.createQuery(Usuario.class);
            Root<Usuario> raizCons = conCons.from(Usuario.class);
            conCons.where(cb.and(cb.equal(raizCons.get("usr_nombre"),user),cb.equal(raizCons.get("usr_clave"),pass)));
            conCons.select (raizCons);
            usuLog =  em.createQuery(conCons).getSingleResult();
            log.info(usuLog.getUsuaPer().get(0).getId_usr()+""); 
		} catch (Exception e) {
            System.out.println("Fallo validacion de usuario: " + e.getMessage());
            e.fillInStackTrace();
		}
		return usuLog;
	}
	
	public void primerSesUsu(int IdUsu) throws Exception {
		// TODO Auto-generated method stub		Usuario usuario = new Usuario();
		
		
		try {
			
			pSUsuario = em.find(Usuario.class, IdUsu);
			log.info(pSUsuario.getUsr_apellido()+"");
			log.info(pSUsuario.gettDocumento().getTdo_nombre()+"");
			perfil = pSUsuario.getUsuaPer().get(0).getId_perfil();
			for (Usuario_Perfil perfil : pSUsuario.getUsuaPer()) {
				
				log.info(perfil.getId_usr()+"");
				log.info(perfil.getId_perfil()+"");
			}
			
		
		} catch (Exception e) {
            log.info("Fallo busqueda usuario primera sesion: " + e.getMessage());
            e.fillInStackTrace();
		}
	}

	



	public Autoridad datosAutoridad(int idAut) {
		// TODO Auto-generated method stub
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		Autoridad autori = null;
		try {
			
	    	//creacion de builder criteria
//    		CriteriaQuery<Usuario> conCons = cb.createQuery(Usuario.class);
    		CriteriaQuery<Autoridad> conCons = cb.createQuery(Autoridad.class);
            Root<Autoridad> rootAut = conCons.from(Autoridad.class);
            conCons.where(cb.equal(rootAut.get("id_aut"),idAut));
            conCons.select(rootAut);
             autori =  em.createQuery(conCons).getSingleResult();
		} catch (Exception e) {
            System.out.println("Fallo validacion de usuario: " + e.getMessage());
            e.fillInStackTrace();
		}
		
		return autori;
		
	}




	public Usuario LoginUsu(String user, String pass) {
		Usuario usuario = new Usuario();
		log.info("dentro de ingresar");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			
	    	//creacion de builder criteria
//    		CriteriaQuery<Usuario> conCons = cb.createQuery(Usuario.class);
    		CriteriaQuery<Usuario> conCons = cb.createQuery(Usuario.class);
            log.info("Clase usuario"+Usuario.class);
            Root<Usuario> raizCons = conCons.from(Usuario.class);
            conCons.where(cb.and(cb.equal(raizCons.get("usr_nombre"),user),cb.equal(raizCons.get("usr_clave"),pass)));
            conCons.select(raizCons);
            log.info("respuesta de get List ------------");
            log.info(em.createQuery(conCons).getSingleResult()+"");
            usuario =  em.createQuery(conCons).getSingleResult();
            log.info("pruebas de datos");
            log.info(usuario.getUsr_nom_persona()+"");
            	usuario.getSedeUsu().getEmpresaSed().getEmp_cc_represen();
            	for (Usuario_Perfil perfil : usuario.getUsuaPer()) {
					perfil.getUsuaPer().getUsr_nom_persona();
					perfil.getPerUsu().getPerfil();
				}
			
		} catch (Exception e) {
            System.out.println("Fallo validacion de usuario: " + e.getMessage());
            e.fillInStackTrace();
		}
		
		 return usuario;
	}
	
	
	public void inPerfilSeleccionado(int idPer, int idUsu)
	{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaBuilder cb1 = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Perfil> conPe = cb.createQuery(Perfil.class);
			CriteriaQuery<Perfil> conPe1 = cb1.createQuery(Perfil.class);
			Root<Perfil> rootPe  = conPe.from(Perfil.class);
			Join<Perfil, Usuario_Perfil> joinPer = rootPe.join("perUsu",JoinType.INNER);
			Root<Perfil> rootPe1  = conPe1.from(Perfil.class);
			conPe.where(cb.and(cb.equal(joinPer.get("id_usr"), idUsu),cb.notEqual(joinPer.get("id_perfil"), idPer)));
//			conPe.where(cb.and(cb.notEqual(rootPe.get("id_perfil"), idPer),cb.equal(joinPer.get("id_usr"), idUsu )));
			conPe1.where(cb1.equal(rootPe1.get("id_perfil"), idPer));
			conPe.select(rootPe);
			conPe1.select(rootPe1);
			inSelPerfiles = em.createQuery(conPe).getResultList();			
			SelPerfil = em.createQuery(conPe1).getSingleResult();
			log.info(SelPerfil.toString());
			log.info(inSelPerfiles.toString());
			log.info(inSelPerfiles.size()+"");
			for (Perfil perfil : inSelPerfiles) {
				log.info(perfil.toString());
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public List<Usuario> usuIdeam() {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();

		listUsuaIdea = null;
		try {
			CriteriaQuery<Usuario> conUsu = cb.createQuery(Usuario.class);
			Root<Usuario> rootUs = conUsu.from(Usuario.class);
			listUsuaIdea = em.createQuery(conUsu).getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			 System.out.println("Fallo validacion de usuario para Ideam: " + e.getMessage());
	            e.fillInStackTrace();
		}finally {
			return listUsuaIdea;
		}
		
	}

	
	public List<Usuario> usuAutoridad(int idAut) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Usuario> conUsu = cb.createQuery(Usuario.class);
			Root<Usuario> rootUs = conUsu.from(Usuario.class);
			conUsu.where(cb.equal(rootUs.get("id_usr_aut"), idAut));
			listUsuaAut = em.createQuery(conUsu).getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			 System.out.println("Fallo validacion de usuario por Aut: " + e.getMessage());
	            e.fillInStackTrace();
		}
		return listUsuaAut;
		
	}



	public List<Usuario_Perfil> darPerfiles(int idUsu) {
		List<Usuario_Perfil> lPerf = null;
		log.info("dentro de ingresar");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			
	    	//creacion de builder criteria
//    		CriteriaQuery<Usuario> conCons = cb.createQuery(Usuario.class);
    		CriteriaQuery<Usuario_Perfil> conCons = cb.createQuery(Usuario_Perfil.class);
            Root<Usuario_Perfil> rootUsuP = conCons.from(Usuario_Perfil.class); 
//            Join<Sede,Departamento> join_depto = joinSede.join("departamento", JoinType.INNER);
            conCons.where(cb.equal(rootUsuP.get("id_usr"), idUsu));
            lPerf =  em.createQuery(conCons).getResultList();
			log.info(lPerf+"");
			Usuario_Perfil per = lPerf.get(0);
			perfil = per.getId_perfil();
			log.info(perfil+"");
		} catch (Exception e) {
            System.out.println("Fallo validacion de usuario: " + e.getMessage());
            e.fillInStackTrace();
		}
		
		 return lPerf;
	}




	public Usuario getNewUsu() {
		// TODO Auto-generated method stub
		return newUsu;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void initNewUsu() {
		newUsu = new Usuario();	
	}
	
	public Usuario_Perfil getNewUsuPer() {
		// TODO Auto-generated method stub
		return newUsuPer;
	}



	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void initNewUsuPer() {
		newUsuPer = new Usuario_Perfil();	
	}




	public void registerUsuario() throws Exception {
		// TODO Auto-generated method stub
        try {

            log.info("Registering " + newUsu);
            em.persist(newUsu);
            usuarioEventSrc.fire(newUsu);
            initNewUsu();
        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }
            log.info("Exception:" + t.getMessage());
            throw ((Exception) t);
        }
	}




	public void registerUsuPer() throws Exception {
		// TODO Auto-generated method stub
		try {

            log.info("Registering " + newUsuPer);
            em.persist(newUsuPer);
            usuPerEventSrc.fire(newUsuPer);
            initNewUsuPer();
        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }
            log.info("Exception:" + t.getMessage());
            throw ((Exception) t);
        }
		
	}


	public void updtUsuario(Usuario usuUp) throws Exception {
		// TODO Auto-generated method stub
		try {

            log.info("Actualizando " + usuUp);
            em.merge(usuUp);
            usuarioEventSrc.fire(usuUp);
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

