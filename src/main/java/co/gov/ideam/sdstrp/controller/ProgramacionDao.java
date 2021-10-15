package co.gov.ideam.sdstrp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.event.Event;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import co.gov.ideam.sdstrp.model.Declaracion;
import co.gov.ideam.sdstrp.model.DeclaracionResiduo;
import co.gov.ideam.sdstrp.model.Programacion;
import co.gov.ideam.sdstrp.model.Residuos;
import co.gov.ideam.sdstrp.model.Sede;
import co.gov.ideam.sdstrp.model.ServicioGestor;
import co.gov.ideam.sdstrp.model.TipoResiduos;


@Stateful

@Model
public class ProgramacionDao {
	
    @Inject
    private Logger log;

    @Inject
    private EntityManager em;
    
    @Inject
    private Event<Programacion> ProgEventSrc;
    

    
    private List<Tuple> listaProgramacionC;
    

    private Programacion newProg;
    
    @Produces
    @Named
    public List<Tuple> getListaProgramacionC()
    {
    	return listaProgramacionC;
    }
    
    //Agregar id de generador para mostar sus programaciones
    public void programacionResiduios(int idS)
    {
//    						 select pro_id , pro_fecha_inicial , res_nombre, a.sed_id as idTrans, a.sed_nombre as transportador , b.sed_id as idGes, b.sed_nombre as gestor, res_id from trpt_programacion\n" +
//    	"                    inner join trpt_programacion_residuo on (pro_id = pre_programacion)\n" +
//    	"                    inner join trpt_residuo on(res_id= pre_residuo)\n" +
//    	"                    inner join trpt_sede a on (trpt_residuo.res_transportador = a.sed_id)\n" +
//    	"                    inner join trpt_sede b on (trpt_residuo.res_gestor = b.sed_id)\n" +
//    	"                    where pro_generador = "+id
    	
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Tuple> conRes = cb.createTupleQuery();	
			 Root<Declaracion> rootRes = conRes.from(Declaracion.class);
//			 Join<ServicioGestor, TipoResiduos> jointTRE = RootRes.join("tiResiduos", JoinType.INNER);
			 Join<Declaracion,Programacion> joinPrPr = rootRes.join("prog_dec",JoinType.INNER);
			 Join<Declaracion, DeclaracionResiduo> joinDecs = rootRes.join("declaracion_res",JoinType.INNER);
			 Join<DeclaracionResiduo, Residuos> joinPrRs = joinDecs.join("residuosDecl",JoinType.INNER);
			 conRes.where(cb.equal(joinPrPr.get("pro_generador"),idS));
			 conRes.multiselect(joinPrPr.get("pro_id"),joinPrPr.get("pro_fecha_inicial"),joinPrRs.get("res_nombre"),joinPrRs.get("res_id"));
			 listaProgramacionC = em.createQuery(conRes).getResultList();
		} catch (Exception e) {
            System.out.println("Fallo lista de autorizaciones: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}	
    }

	public Programacion getNewProg() {
		// TODO Auto-generated method stub
		return newProg;
	}
	
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void initNewProg() {
    	newProg = new Programacion();
        log.info("@PostConstruct:initNewProgramacion called");
    }



    
    public void registerProgramacion() throws Exception {

        try {

            log.info("Registering " + newProg);
            em.persist(newProg);
            ProgEventSrc.fire(newProg);
            initNewProg();
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
