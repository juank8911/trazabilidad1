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
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import co.gov.ideam.sdstrp.model.Departamento;
import co.gov.ideam.sdstrp.model.Empresa;
import co.gov.ideam.sdstrp.model.RutaSedeTran;
import co.gov.ideam.sdstrp.model.Rutas;
import co.gov.ideam.sdstrp.model.Sede;
import co.gov.ideam.sdstrp.model.ServicioGestor;
import co.gov.ideam.sdstrp.model.TipoGestion;
import co.gov.ideam.sdstrp.model.TipoManejo;
import co.gov.ideam.sdstrp.model.TipoResiduos;
import co.gov.ideam.sdstrp.model.Municipio;
import co.gov.ideam.sdstrp.model.Programacion;
import co.gov.ideam.sdstrp.model.Residuos;

@Stateful
@Model
public class RutasDAO {
	
    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    private RutaSedeTran newRutaSedTran;
    private Rutas newRuta;
    private List<Tuple> listRutas;
    private List<Tuple> listSedeTr;
    
    @Inject
    private Event<Rutas> rutaEventSrc;
    @Inject
    private Event<RutaSedeTran> rutaSedTEventSrc;
    
    
    @Produces
    @Named
    public List<Tuple> getlistRutas()
    {

    	return listRutas;
    }
    
    @Produces
    @Named
    public List<Tuple> getListSedeTr()
    {
    	return listSedeTr;
    }
    
    public void listaRutas(int idS) 
    {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			
//			select  rug_ruta, rut_nombre, count(*) as cantidad from trpt_ruta_generador\n"
//                    + "inner join trpt_ruta on (rut_id = rug_ruta)\n"
//                    + "where rut_transportador = "+codTransp+" group by rut_nombre , rug_ruta order by rut_nombre";
			
			 CriteriaQuery<Tuple> conRes = cb.createTupleQuery();	
			 Root<RutaSedeTran> RootRut = conRes.from(RutaSedeTran.class);
			 Join<RutaSedeTran, Rutas> joinRuG = RootRut.join("RutasRGen",JoinType.INNER);
			 conRes.where(cb.equal(RootRut.get("rug_id_sedtr"), idS));
//			 conRes.groupBy(RootRut.get("rut_nombre"));
			 conRes.groupBy(joinRuG.get("rut_nombre"));
			 conRes.multiselect(cb.count(RootRut),joinRuG.get("rut_nombre"));
			 listRutas = em.createQuery(conRes).getResultList();
			 for (Tuple tuple : listRutas) {
				tuple.get(0);
				tuple.get(1);
			}

			 		 
		} catch (Exception e) {
            System.out.println("Fallo lista de autorizaciones por autoridad: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
    }
    
    
    public void listaSedeGen()
    {
    	
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			
//					"select   sed_id , emp_razon_social ,sed_nombre, dept_nombre,   munic_nombre, sed_direccion from trpt_sede a\n" +
//                    "inner join trpt_empresa d on (a.sed_id = d.emp_id)\n" +
//                    "inner join trpt_municipio b on(a.sed_municipio = b.id_munic)\n" +
//                    "inner join trpt_departamento c on(a.sed_departamento = c.id_dept)\n" +
//                    "where sed_generador = 'S'\n" +
//                    "order by emp_razon_social";
			
			 CriteriaQuery<Tuple> conRes = cb.createTupleQuery();	
			 Root<Sede> RootSed = conRes.from(Sede.class);
			 Join<Sede, Empresa> joinEmp = RootSed.join("sedEmpresa",JoinType.INNER);
			 Join<Sede, Municipio> joinMun = RootSed.join("sedMunic",JoinType.INNER);
			 Join<Sede,Departamento> joinDep = RootSed.join("departamento",JoinType.INNER);
//			 Join<Sede,Programacion> jonProg = RootSed.join("sedProgramacion",JoinType.INNER);
//			 conRes.where(cb.equal(joinRuG.get("rut_transportador"), idS));
//			 conRes.groupBy(RootRut.get("rut_nombre"));
//			 conRes.where( cb.and(cb.equal(RootSed.get("sed_generador"),"S"),cb.equal(jonProg.get("pro_transportador"),2)));
			 conRes.multiselect(RootSed.get("sed_id"),joinEmp.get("emp_razon_social"),RootSed.get("sed_direccion"),joinDep.get("dept_nombre"),
					 joinMun.get("munic_nombre"));
			 listSedeTr = em.createQuery(conRes).getResultList();
			 for (Tuple tuple : listSedeTr) {
				tuple.get(0);
			}

			 		 
		} catch (Exception e) {
            System.out.println("Fallo lista de Sedes Prgrmacion reciduo: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
    	
    }
    
    
    public void pruebaSeT(int idS)
    {
    	CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			 CriteriaQuery<Tuple> conRes = cb.createTupleQuery();	
			 Root<Programacion> rootRes = conRes.from(Programacion.class);
			 Join<Programacion, Sede> joinProgSed = rootRes.join("sedGene",JoinType.INNER);
			 Join<Sede, Empresa> joinEmp = joinProgSed.join("sedEmpresa",JoinType.INNER);
			 Join<Sede, Municipio> joinMun = joinProgSed.join("sedMunic",JoinType.INNER);
			 Join<Sede,Departamento> joinDep = joinProgSed.join("departamento",JoinType.INNER);
			 conRes.where(cb.equal(rootRes.get("jsedprog"),idS));
//			 Join<Sede, Residuos> joinSedRes = RootSed.join("sedeRes",JoinType.INNER);
//			 Join<Sede, Programacion> joinSedProg = RootSed.join("jsedprog",JoinType.INNER); 
			 conRes.multiselect(joinProgSed.get("sed_id"),joinEmp.get("emp_razon_social"),joinProgSed.get("sed_direccion"),joinDep.get("dept_nombre"),
					 joinMun.get("munic_nombre"),joinProgSed.get("sed_nombre"));
			 log.info(""+em.createQuery(conRes).getResultList());
			 listSedeTr = em.createQuery(conRes).getResultList();
		 		 
		} catch (Exception e) {
            System.out.println("Fallo lista de Sedes Prgrmacion reciduo: de prueba" + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
    	
    }


	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void initNewRuta() {
		
		newRuta = new Rutas();
		// TODO Auto-generated method stub
		
	}

	public Rutas getNewRuta() {
		// TODO Auto-generated method stub
		return newRuta;
	}
	
	public RutaSedeTran getNewRutaSed() {
		// TODO Auto-generated method stub
		return newRutaSedTran;
	}
	
	public void initNewRutaSed() {
		// TODO Auto-generated method stub
		newRutaSedTran = new RutaSedeTran();		
	}

	public void registerRuta() throws Exception {

        try {

            log.info("Registering " + newRuta);
            em.persist(newRuta);
            rutaEventSrc.fire(newRuta);
            initNewRutaSed();
        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }
            log.info("Exception:" + t.getMessage());
            throw ((Exception) t);
        }
	}

	public void RegisterRutaSedT() throws Exception {

        try {
            log.info("Registering " + newRutaSedTran);
            em.persist(newRutaSedTran);
            rutaSedTEventSrc.fire(newRutaSedTran);
            initNewRutaSed();
        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }
            log.info("Exception:" + t.getMessage());
            throw ((Exception) t);
        }
		
	}
	
	
	public void RegisterRutaSedTr() throws Exception
	{
		try {
			log.info("Id Sede "+ newRutaSedTran.getRug_id_sedtr()+ " / "+ "Id Ruta "+newRutaSedTran.getRug_id_ruta());
			em.persist(newRutaSedTran);
			rutaSedTEventSrc.fire(newRutaSedTran);
			initNewRutaSed();
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
