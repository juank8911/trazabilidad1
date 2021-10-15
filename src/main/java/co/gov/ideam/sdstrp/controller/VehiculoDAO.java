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

import co.gov.ideam.sdstrp.model.Sede;
import co.gov.ideam.sdstrp.model.ServicioGestor;
import co.gov.ideam.sdstrp.model.TipoGestion;
import co.gov.ideam.sdstrp.model.TipoManejo;
import co.gov.ideam.sdstrp.model.TipoResiduos;
import co.gov.ideam.sdstrp.model.TipoVehiculo;
import co.gov.ideam.sdstrp.model.Usuario;
import co.gov.ideam.sdstrp.model.Vehiculo;


@Stateful
@Model
public class VehiculoDAO {
	
	
    @Inject
    private Logger log;

    @Inject
    private EntityManager em;
    
    @Inject
    private Event<Vehiculo> vehiEventSrc;
    
    private Vehiculo newVehi;
    
    private List<Tuple> listaVehiculoUsu;
    
    private List<Vehiculo> listaVehiculoTrans;
    
    
    
    @Produces
    @Named
    public List<Vehiculo> getListaVehiculoTrans()
    {
    	return listaVehiculoTrans;
    }
    
    @Produces
    @Named
    public List<Tuple> getListaVehiculoUsu()
    {
    	return listaVehiculoUsu;
    }
    
    
    /*
     * Lista vehiculos del transportador por su id 
     */
public void listarVehiculosUs(int idS) {
	
	CriteriaBuilder cb = em.getCriteriaBuilder();
	try {
//			   "select  c.tve_nombre as designacion,
//               from trpt_vehiculo a\n"
//               inner join trpt_tipo_vehiculo b on (b.tve_id = a.veh_tipo_vehiculo)\n"
//               inner join trpt_tipo_vehiculo c on (c.tve_id = a.veh_designacion)\n"
//               inner join trpt_sede d on (d.sed_id = usr_id_sede)\n"
//               where trpt_usuario.id_usr = "+vst+"";
		
		 CriteriaQuery<Tuple> conRes = cb.createTupleQuery();	
		 Root<Vehiculo> RootRes = conRes.from(Vehiculo.class);
//		 conRes.where(cb.equal(RootRes.get("veh_transportador"), vsG));
//		 INNER join trpt_usuario on (usr_id_sede = a.veh_id_sede)
		 Join<Vehiculo, Sede> joinVehSed = RootRes.join("vehiSede",JoinType.INNER);
		 Join<Vehiculo, TipoVehiculo> joinVeTve = RootRes.join("tvehiculo",JoinType.INNER);
//		 Join<Sede,Usuario> joinSedUs = joinVehSed.join("sedeUsu",JoinType.INNER);
		 conRes.where(cb.equal(RootRes.get("veh_id_sede"), idS));
		 conRes.multiselect(RootRes.get("veh_id"),RootRes.get("veh_tipo_peligrosidad"),RootRes.get("veh_pro_nombre"),RootRes.get("veh_linea"),RootRes.get("veh_placa"),RootRes.get("veh_placa_remolque"),RootRes.get("veh_marca"),joinVeTve.get("tve_nombre"),joinVehSed.get("sed_nombre"),RootRes.get("veh_id_desig"));
		 listaVehiculoUsu = em.createQuery(conRes).getResultList();
		 		 
	} catch (Exception e) {
        System.out.println("Fallo lista de autorizaciones por autoridad: " + e.getMessage());
        e.fillInStackTrace();
		// TODO: handle exception
	}

}

public void listarVehiculosTrans(int idS) {
	
	CriteriaBuilder cb = em.getCriteriaBuilder();
	try {
//			   "select  c.tve_nombre as designacion,
//               from trpt_vehiculo a\n"
//               inner join trpt_tipo_vehiculo b on (b.tve_id = a.veh_tipo_vehiculo)\n"
//               inner join trpt_tipo_vehiculo c on (c.tve_id = a.veh_designacion)\n"
//               inner join trpt_sede d on (d.sed_id = usr_id_sede)\n"
//               where trpt_usuario.id_usr = "+vst+"";
		
		 CriteriaQuery<Vehiculo> conRes = cb.createQuery(Vehiculo.class);	
		 Root<Vehiculo> RootRes = conRes.from(Vehiculo.class);
		 conRes.where(cb.equal(RootRes.get("veh_id_sede"), idS));
		 conRes.select(RootRes);
		 listaVehiculoTrans = em.createQuery(conRes).getResultList();
		 log.info(listaVehiculoTrans.size()+"");
		 		 
	} catch (Exception e) {
        System.out.println("Fallo lista de autorizaciones por autoridad: " + e.getMessage());
        e.fillInStackTrace();
		// TODO: handle exception
	}

}




public Vehiculo getNewVehic() {
	// TODO Auto-generated method stub
	return newVehi;
}


@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public void initNewVehi() {
	// TODO Auto-generated method stub
	newVehi =new Vehiculo();
	
}


public void registerVehi() throws Exception {
    try {

        log.info("Registering " + newVehi);
        em.persist(newVehi);
        vehiEventSrc.fire(newVehi);
        initNewVehi();
    } catch (Exception e) {
        Throwable t = e;
        while ((t.getCause()) != null) {
            t = t.getCause();
            log.info(""+t);
        }
        log.info("Exception:" + t.getMessage());
        throw ((Exception) t);
    }
	
}


public void eliminarVehiTrans(String id) throws Exception {
	// TODO Auto-generated method stub
	try {
		Vehiculo veh = em.find(Vehiculo.class, id);
		em.remove(veh);
        vehiEventSrc.fire(veh);
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
