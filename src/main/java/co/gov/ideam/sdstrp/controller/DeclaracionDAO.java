package co.gov.ideam.sdstrp.controller;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Query;

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
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;

import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import java.util.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.*;



import co.gov.ideam.sdstrp.model.Autoridad;
import co.gov.ideam.sdstrp.model.Autorizaciones;
import co.gov.ideam.sdstrp.model.Declaracion;
import co.gov.ideam.sdstrp.model.DeclaracionResiduo;
import co.gov.ideam.sdstrp.model.Departamento;
import co.gov.ideam.sdstrp.model.Empresa;
import co.gov.ideam.sdstrp.model.EstadoMateria;
import co.gov.ideam.sdstrp.model.GestionUbicacion;
import co.gov.ideam.sdstrp.model.Municipio;
import co.gov.ideam.sdstrp.model.Perfil;
import co.gov.ideam.sdstrp.model.Programacion;
import co.gov.ideam.sdstrp.model.Residuos;
import co.gov.ideam.sdstrp.model.Sede;
import co.gov.ideam.sdstrp.model.TipoEmbalaje;
import co.gov.ideam.sdstrp.model.TipoEmpaque;
import co.gov.ideam.sdstrp.model.TipoGestion;
import co.gov.ideam.sdstrp.model.TipoManejo;
import co.gov.ideam.sdstrp.model.TipoResiduos;
import co.gov.ideam.sdstrp.model.Usuario;


@Stateful
@Model
public class DeclaracionDAO {
	
	
    @Inject
    private Logger log;
    	
    @Inject
    private EntityManager em;

   
    
    @Inject
    private Event<Declaracion> DecEventSrc;
    
    @Inject
    private Event<DeclaracionResiduo> DeReEventSrc;
    
    private List<Tuple> listaDeclApGest;
    private List<TipoEmpaque> tipoEmpa;
    private List<TipoEmbalaje> tipoEmba;
    private List<Tuple> listProgHoy;
    private List<Tuple> listaDeclaracionProg;
    private Date objDate;
    private Declaracion newDec;
    private DeclaracionResiduo newDeRe;
    private List<Tuple> listDeclaAprueba;
    private List<Declaracion> listDeclaGen;
    private List<Declaracion> listDeclaTranp;
    private List<Declaracion> listDeclaHoy;
    private List<Declaracion> listHistoricoDecla; 
    private List<Tuple> listRepoMesGene;
    private List<Declaracion> listDeclaSal;
    private List<Declaracion> listDeclaHisAut;
    private List<Declaracion> listDeclaHiIdeam;
    
    private List<Declaracion> listDeclaGest;
    
    
   
    
    @Produces
    @Named
    public List<Declaracion> getListDeclaGest()
    {
    	return listDeclaGest;
    }
    
    @Produces
    @Named
    public List<Declaracion> getListDeclaHisAut()
    {
    	return listDeclaHisAut;
    }
    
    
    
    @Produces
    @Named
    public List<Declaracion> getListDeclaSal()
    {
    	return listDeclaSal;
    }
    
    
    
    @Produces
    @Named
    public List<Declaracion> getListHistoricoDecla()
    {
    	return listHistoricoDecla;
    }
    
    @Produces
    @Named
    public List<Declaracion> getListDeclaHoy()
    {
    	return listDeclaHoy;
    }
    @Produces
    @Named
    public List<Declaracion> getListDeclaGen()
    {
    	return  listDeclaGen;
    }
 
    @Produces
    @Named
    public List<Tuple> getListRepoMesGene()
    {
    	return listRepoMesGene;
    }
   
    @Produces
    @Named
    public List<Tuple> getListaDeclApGest()
    {
    	return listaDeclApGest;
    }
    
    @Produces
    @Named
    public List<Tuple> getListDeclaAprueba()
    {
    	return listDeclaAprueba;
    }
    
    
    
    
    
    @Produces
    @Named
    public List<Declaracion> getlistDeclaTranp()
    {
    	return listDeclaTranp;
    }
    
    @Produces
    @Named
    public List<Tuple> getListaDeclaracionProg()
    {
    	return listaDeclaracionProg;
    }
    
    @Produces
    @Named
    public List<Tuple> getListProgHoy()
    {
    	return listProgHoy;
    }
    
    public List<Declaracion> SelectDeclHistorial(int idS, int idP)
    {	
    	CriteriaBuilder cDe = em.getCriteriaBuilder();
    	
    	try {
				CriteriaQuery<Declaracion> criDec = cDe.createQuery(Declaracion.class);
				Root<Declaracion> rootDec = criDec.from(Declaracion.class);
				if(idP==3)
				{
					criDec.where(cDe.equal(rootDec.get("dec_generador"), idS));
				}
				else if(idP==4)
				{
					criDec.where(cDe.equal(rootDec.get("dec_transportador"), idS));
				}
				else if(idP==5)
				{
					criDec.where(cDe.equal(rootDec.get("dec_gestor"), idS));
				}
				criDec.select(rootDec);
				listDeclaSal =  em.createQuery(criDec).getResultList();
				log.info(listDeclaSal.size()+"");
				for (Declaracion declaracion : listDeclaSal) {
					log.info(declaracion.getDec_id()+"");
					log.info(declaracion.getDeclaracion_res().size()+"");
		    		log.info(declaracion.getDeclaracion_res()+"");
		    		log.info(declaracion.getDec_gen_fecha_gen()+"");
					log.info(""+declaracion.getDec_generador());
					log.info(declaracion.getDeclaracion_res().size()+" Tamaño del array de Declaracion residuo");
					log.info(""+declaracion.getDecSedGen().getSed_nombre());
					log.info(""+declaracion.getDecSedGen().getSedMunic().getMunic_nombre());
					log.info(""+declaracion.getDecSedGen().getDepartamento().getDept_nombre());
					log.info(""+declaracion.getDecSedGen().getEmpresaSed().getEmp_razon_social());
					log.info(""+declaracion.getDecSedTran().getSed_nombre());
					log.info(""+declaracion.getDecSedTran().getDepartamento().getDept_nombre());
					log.info(""+declaracion.getDecSedTran().getEmpresaSed().getEmp_razon_social());
					log.info(""+declaracion.getDecSedTran().getSedMunic().getMunic_nombre());
					log.info(""+declaracion.getDecSedGes().getSed_nombre());
					log.info(""+declaracion.getDecSedGes().getDepartamento().getDept_nombre());
					log.info(""+declaracion.getDeclaracion_res().toString());
					log.info(""+declaracion.getDecSedGes().getEmpresaSed().getEmp_nombre_comercial());
					log.info(""+declaracion.getDecSedGes().getSedMunic().getMunic_nombre());
		    		List<DeclaracionResiduo> resDec = declaracion.getDeclaracion_res();
		    		for (DeclaracionResiduo decla1 : resDec) {
						log.info(""+decla1.getDer_gen_peso_residuo());
					}
				} 
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Fallo lista historico de declaraciones: " + e.getMessage());
	        e.fillInStackTrace();
		}
    	return listDeclaSal;
    }
    
    public List<DeclaracionResiduo> DarDeclracionResiduoid(int idDe)
    {
    	CriteriaBuilder cd = em.getCriteriaBuilder();
    	List<DeclaracionResiduo> lDer = null;
    	try {
    		
    		CriteriaQuery<DeclaracionResiduo> conDeR= cd.createQuery(DeclaracionResiduo.class);
    		Root<DeclaracionResiduo> rootDer = conDeR.from(DeclaracionResiduo.class);
    		conDeR.where(cd.equal(rootDer.get("der_declaracion"), idDe));
			conDeR.select(rootDer);
			lDer = em.createQuery(conDeR).getResultList(); 
		} catch (Exception e) {
			// TODO: handle exception
		}

		return lDer;
    	
    }
    
    
    public void prebaDeclaRes(int id, int idP)
    {
    		
    	try {
			
    	CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery<Declaracion> conDes = cb.createQuery(Declaracion.class);
    	Root<Declaracion> rootDeRe = conDes.from(Declaracion.class);
    	Join<Declaracion, DeclaracionResiduo> joinDe = rootDeRe.join("declaracion_res",JoinType.INNER);
    	if(idP==3)
		{
    		conDes.where(cb.equal(rootDeRe.get("dec_generador"), id));
		}
		else if(idP==4)
		{
			conDes.where(cb.equal(rootDeRe.get("dec_transportador"), id));
		}
		else if(idP==5)
		{
			conDes.where(cb.equal(rootDeRe.get("dec_gestor"), id));
		}
    	log.info(idP+" Id de perfi");
    	
    	conDes.select(rootDeRe);
    	listHistoricoDecla = em.createQuery(conDes).getResultList();
    	log.info(listHistoricoDecla.size()+"");
    	for (Declaracion tuple : listHistoricoDecla) {
			log.info(tuple.getDec_id()+" / ID DE DECLARACIONES");
		}
    	for (Declaracion declaracion : listHistoricoDecla) {
    		log.info(declaracion.getDeclaracion_res().size()+"");
    		log.info(declaracion.getDeclaracion_res()+"");
    		log.info(declaracion.getDec_gen_fecha_gen()+"");
			log.info(""+declaracion.getDec_generador());
			log.info(declaracion.getDeclaracion_res().size()+" Tamaño del array de Declaracion residuo");
			log.info(""+declaracion.getDecSedGen().getSed_nombre());
			log.info(""+declaracion.getDecSedGen().getSedMunic().getMunic_nombre());
			log.info(""+declaracion.getDecSedGen().getDepartamento().getDept_nombre());
			log.info(""+declaracion.getDecSedGen().getEmpresaSed().getEmp_razon_social());
			log.info(""+declaracion.getDecSedTran().getSed_nombre());
			log.info(""+declaracion.getDecSedTran().getDepartamento().getDept_nombre());
			log.info(""+declaracion.getDecSedTran().getEmpresaSed().getEmp_razon_social());
			log.info(""+declaracion.getDecSedGes().getSed_nombre());
			log.info(""+declaracion.getDecSedGes().getDepartamento().getDept_nombre());
			log.info(""+declaracion.getDeclaracion_res().toString());
    		List<DeclaracionResiduo> resDec = declaracion.getDeclaracion_res();
    		for (DeclaracionResiduo decla1 : resDec) {
				log.info(""+decla1.getDer_gen_peso_residuo());
			}
		}
    	} catch (Exception e) {
			// TODO: handle exception
			log.info("Fallo lista historico de declaraciones: " + e.getMessage());
	        e.fillInStackTrace();
		}
    }

//public void HistoricoDeclas(int id,int perf) {
//	// TODO Auto-generated method stub
//	CriteriaBuilder cb = em.getCriteriaBuilder();
//	try {
//		log.info(id+"");
//		log.info(perf+"");
//		CriteriaQuery<Declaracion> conDecla = null;
//		conDecla = cb.createQuery(Declaracion.class);	
//		 Root<Declaracion> RootRes = conDecla.from(Declaracion.class);
////		 conRes.where(cb.equal(RootRes.get("sed_transportador"), "s"));
//			if(perf==3)
//			{
//				conDecla.where(cb.equal(RootRes.get("dec_generador"), id));
//			}
//			else if(perf==4)
//			{
//				conDecla.where(cb.equal(RootRes.get("dec_transportador"), id));
//			}
//			else if(perf==5)
//			{
//				conDecla.where(cb.equal(RootRes.get("dec_gestor"), id));
//			}
//		 conDecla.select(RootRes);
//		 listHistoricoDecla = em.createQuery(conDecla).getResultList();
//		 log.info(listHistoricoDecla.size()+" Tmaño de el arra de declaraciones");
//		 for (Declaracion dec : listHistoricoDecla) {
//			 log.info(dec.getDec_gen_fecha_gen()+"");
//			 log.info(""+dec.getDec_generador());
//			 log.info(dec.getDeclaracion_res().size()+" Tamaño del array de Declaracion residuo");
//			log.info(""+dec.getDecSedGen().getSed_nombre());
//			log.info(""+dec.getDecSedGen().getSedMunic().getMunic_nombre());
//			log.info(""+dec.getDecSedGen().getDepartamento().getDept_nombre());
//			log.info(""+dec.getDecSedGen().getEmpresaSed().getEmp_razon_social());
//			log.info(""+dec.getDecSedTran().getSed_nombre());
//			log.info(""+dec.getDecSedTran().getDepartamento().getDept_nombre());
//			log.info(""+dec.getDecSedTran().getEmpresaSed().getEmp_razon_social());
//			log.info(""+dec.getDecSedGes().getSed_nombre());
//			log.info(""+dec.getDecSedGes().getDepartamento().getDept_nombre());
//			log.info(""+dec.getDeclaracion_res().toString());
//			
//			
//		}
//	} catch (Exception e) {
//        log.info("Fallo lista historico de declaraciones: " + e.getMessage());
//        e.fillInStackTrace();
//		// TODO: handle exception
//	}
//
//}
    
	public void declaCercana(int idSed) throws ParseException
	{
		
		log.info("antes");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		Calendar cini = Calendar.getInstance();
		Calendar cinf = Calendar.getInstance();
		Date fecha = new Date();
		DateFormat fin = new SimpleDateFormat("yyyy-MM-dd");
		String fi = formatter.format(fecha);
		String ff = formatter.format(fecha);
		cinf.setTime(formatter.parse(ff));
		cini.setTime(formatter.parse(fi));
		cini.add(Calendar.DATE, -2);
		cinf.add(Calendar.DATE, 2);
		
		log.info("suma "+cinf+" / "+cinf);
		try {
		
			CriteriaQuery<Declaracion> conRes = cb.createQuery(Declaracion.class);	
			 Root<Declaracion> RootDecR = conRes.from(Declaracion.class);
			 Join<Declaracion, Programacion> joinPrrPr = RootDecR.join("prog_dec",JoinType.INNER);
//			 conRes.where(cb.notEqual(joinDeRe.get("dec_gen_aprobada"),"A"));
			 conRes.where(cb.and(cb.equal(RootDecR.get("dec_generador"), idSed),cb.notEqual(RootDecR.get("dec_gen_aprobada"),"A"),
			 cb.between(joinPrrPr.<Date>get("pro_fecha_inicial"),cini.getTime() ,cinf.getTime())));
			 conRes.select(RootDecR);
			 conRes.orderBy(cb.asc(RootDecR.get("dec_id")));
			 listDeclaHoy = em.createQuery(conRes).getResultList();
			 for (Declaracion tuple : listDeclaHoy) {
				 
				log.info(tuple.toString());
				log.info(tuple.getProg_dec().getPro_fecha_inicial()+"");
				log.info(tuple.getDecSedTran().getEmpresaSed().getEmp_razon_social());
				log.info(tuple.getDecSedGes().getEmpresaSed().getEmp_razon_social());
				String em = tuple.getDecSedGes().getEmpresaSed().getEmp_nombre_comercial();
				em = tuple.getDecSedTran().getEmpresaSed().getEmp_nombre_comercial();
				String dep = tuple.getDecSedGes().getDepartamento().getDept_nombre();
				dep = tuple.getDecSedGes().getDepartamento().getDept_nombre();
				dep = tuple.getDecSedTran().getDepartamento().getDept_nombre();
				dep = tuple.getDecSedGes().getSedMunic().getMunic_nombre();
				dep = tuple.getDecSedTran().getSedMunic().getMunic_nombre();
				List<DeclaracionResiduo> dec = tuple.getDeclaracion_res();
				for (DeclaracionResiduo declaRes : dec) {
					log.info(declaRes.getDer_gen_nombre()+"");
					declaRes.getTipoResDec().getTre_nombre();
					declaRes.getTipoEmbDec().getTem_nombre();
					declaRes.getTipoEmpDec().getTep_nombre();
				}
			}
			
		} catch (Exception e) {
            System.out.println("Fallo lista Declaraciones por: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
	}
	
	public void declaEntre(String fIni, String fFin, int idSed) throws ParseException {
		// TODO Auto-generated method stub
		log.info("antes");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		Calendar cini = Calendar.getInstance();
		Calendar cinf = Calendar.getInstance();
//		Date fecha = new Date();
//		DateFormat fin = new SimpleDateFormat("yyyy-MM-dd");
//		String fi = formatter.format(fecha);
//		String ff = formatter.format(fecha);
		cinf.setTime(formatter.parse(fFin));
		cini.setTime(formatter.parse(fIni));
		
		log.info("suma "+cinf+" / "+cinf);
		try {
		
			CriteriaQuery<Declaracion> conRes = cb.createQuery(Declaracion.class);	
			 Root<Declaracion> RootDecR = conRes.from(Declaracion.class);
			 Join<Declaracion, Programacion> joinPrrPr = RootDecR.join("prog_dec",JoinType.INNER);
//			 conRes.where(cb.notEqual(joinDeRe.get("dec_gen_aprobada"),"A"));
			 conRes.where(cb.and(cb.equal(RootDecR.get("dec_generador"), idSed),cb.notEqual(RootDecR.get("dec_gen_aprobada"),"A"),cb.between(joinPrrPr.<Date>get("pro_fecha_inicial"),cini.getTime() ,cinf.getTime())));
			 conRes.select(RootDecR);
			 conRes.orderBy(cb.asc(RootDecR.get("dec_id")));
			 listDeclaHoy = em.createQuery(conRes).getResultList();
			 for (Declaracion tuple : listDeclaHoy) {
				 
				log.info(tuple.toString());
				log.info(tuple.getProg_dec().getPro_fecha_inicial()+"");
				log.info(tuple.getDecSedTran().getEmpresaSed().getEmp_razon_social());
				log.info(tuple.getDecSedGes().getEmpresaSed().getEmp_razon_social());
				String em = tuple.getDecSedGes().getEmpresaSed().getEmp_nombre_comercial();
				em = tuple.getDecSedTran().getEmpresaSed().getEmp_nombre_comercial();
				String dep = tuple.getDecSedGes().getDepartamento().getDept_nombre();
				dep = tuple.getDecSedGes().getDepartamento().getDept_nombre();
				dep = tuple.getDecSedTran().getDepartamento().getDept_nombre();
				dep = tuple.getDecSedGes().getSedMunic().getMunic_nombre();
				dep = tuple.getDecSedTran().getSedMunic().getMunic_nombre();
				List<DeclaracionResiduo> dec = tuple.getDeclaracion_res();
				for (DeclaracionResiduo declaRes : dec) {
					log.info(declaRes.getDer_gen_nombre()+"");
					declaRes.getTipoResDec().getTre_nombre();
					declaRes.getTipoEmbDec().getTem_nombre();
					declaRes.getTipoEmpDec().getTep_nombre();
				}
			}
			
		} catch (Exception e) {
            System.out.println("Fallo lista Declaraciones por: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
		
	}
    
    
    public void declaraAprovGes(int idS)
    {
    	
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Tuple> conRes = cb.createTupleQuery();	
			Root<DeclaracionResiduo> rootDeRe = conRes.from(DeclaracionResiduo.class);
			Join<DeclaracionResiduo, Declaracion> joinDec = rootDeRe.join("declaracion_res",JoinType.INNER);
			Join<Declaracion, Sede> joinDeSedGen = joinDec.join("decSedGen",JoinType.INNER);
			Join<Declaracion, Sede> joinDeSedTrn = joinDec.join("decSedTran",JoinType.INNER);
//			Join<DeclaracionResiduo, Residuos> joinRes = rootDeRe.join(null)S; 
			Join<DeclaracionResiduo, Residuos> joinres = rootDeRe.join("residuosDecl",JoinType.INNER);
			Join<Residuos, TipoEmpaque> JoinEmp = joinres.join("jTipoEmp",JoinType.INNER); 
			Join<Residuos, TipoEmbalaje> JoinEmb = joinres.join("jTipoEmb",JoinType.INNER);
			conRes.where(cb.and(cb.equal(joinDec.get("dec_trn_aprobada"),"A"),cb.equal(joinDec.get("dec_id"),idS)));
			conRes.multiselect(rootDeRe.get("der_gen_tipo_residuo"),joinDec.get("dec_id"),joinDec.get("dec_gen_fecha_gen"),rootDeRe.get("der_id"),
					joinDeSedGen.get("sed_nombre"),joinDeSedTrn.get("sed_nombre"),rootDeRe.get("der_gen_nombre"),
					JoinEmp.get("tep_id"),JoinEmp.get("tep_nombre"),JoinEmb.get("tem_id"),JoinEmb.get("tem_nombre"));
			listaDeclApGest = em.createQuery(conRes).getResultList();
			for (Tuple tuple : listaDeclApGest) {
				log.info(" 0 "+tuple.get(0));
				log.info(" 1 "+tuple.get(1));
				log.info(" 2 "+tuple.get(2));
				log.info(" 3 "+tuple.get(3));
				log.info(" 4 "+tuple.get(4));
				log.info(" 5 "+tuple.get(5));
				log.info(" 6 "+tuple.get(6));
			}
		
			
		} catch (Exception e) {
            System.out.println("Fallo lista de Declaraciones: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
    	
    }
    
    
    public void declaracioTrans(int idD)
    {
//    	"select dec_id, der_gen_nombre, der_gen_tipo_residuo, dec_gen_fecha_gen from trpt_declaracion\n"
//                + "                inner join trpt_declaracion_residuo on(dec_id = der_declaracion)\n"
//                + "                where dec_gen_aprobada = 'A' and dec_id =" + cod_decla;
    	
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Tuple> conRes = cb.createTupleQuery();	
			Root<DeclaracionResiduo> rootDeRe = conRes.from(DeclaracionResiduo.class);
			Join<DeclaracionResiduo, Declaracion> joinDec = rootDeRe.join("declaracion_res",JoinType.INNER);
			 Join<DeclaracionResiduo, Residuos> joinres = rootDeRe.join("prog_res",JoinType.INNER);
			Join<Declaracion, Sede> joinDeSedGen = joinDec.join("decSedGen",JoinType.INNER);
			Join<Declaracion, Sede> joinDeSedTrn = joinDec.join("decSedTran",JoinType.INNER);
			Join<Residuos, TipoEmpaque> JoinEmp = joinres.join("jTipoEmp",JoinType.INNER); 
			 Join<Residuos, TipoEmbalaje> JoinEmb = joinres.join("jTipoEmb",JoinType.INNER);

			conRes.where(cb.and(cb.equal(joinDec.get("dec_gen_aprobada"),"A"),cb.equal(joinDec.get("dec_id"),idD)));
			conRes.multiselect(rootDeRe.get("der_gen_tipo_residuo"),joinDec.get("dec_id"),joinDec.get("dec_gen_fecha_gen"),rootDeRe.get("der_id"),
					joinDeSedGen.get("sed_nombre"),joinDeSedTrn.get("sed_nombre"),rootDeRe.get("der_gen_nombre"),
					JoinEmp.get("tep_id"),JoinEmp.get("tep_nombre"),JoinEmb.get("tem_id"),JoinEmb.get("tem_nombre"),joinres.get("res_nombre"));
			listDeclaAprueba = em.createQuery(conRes).getResultList();
		
			
		} catch (Exception e) {
            System.out.println("Fallo lista de Declaraciones: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
    	
    }
    
    public void getListaDeclaracionTranpor(int idS)
    {


    	CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Declaracion> conRes = cb.createQuery(Declaracion.class);	
			Root<Declaracion> rootDeR = conRes.from(Declaracion.class);
			conRes.where(cb.and(cb.equal(rootDeR.get("dec_transportador"), idS),cb.equal(rootDeR.get("dec_gen_aprobada"), "A"),cb.notEqual(rootDeR.get("dec_trn_aprobada"), "A"),cb.notEqual(rootDeR.get("dec_trn_aprobada"), "R")));
			conRes.select(rootDeR);
			log.info("-------------------------------Generando consulta");
			listDeclaTranp = em.createQuery(conRes).getResultList();
			
			for (Declaracion tuple : listDeclaTranp) {
				log.info("////////////////////////////////////////////////////////");
				log.info(tuple.getDecSedGes().getDepartamento()+"");
				log.info(tuple.getDecSedGes().getSedMunic()+"");
				log.info(tuple.getDecSedGes().getSed_nombre()+"");
				log.info(tuple.getDecSedGen().getSed_nombre()+"");
				String em = tuple.getDecSedGes().getEmpresaSed().getEmp_nombre_comercial();
				em = tuple.getDecSedGen().getEmpresaSed().getEmp_nombre_comercial();
				String dep = tuple.getDecSedGes().getDepartamento().getDept_nombre();
				dep = tuple.getDecSedGes().getDepartamento().getDept_nombre();
				dep = tuple.getDecSedGen().getDepartamento().getDept_nombre();
				dep = tuple.getDecSedGes().getSedMunic().getMunic_nombre();
				dep = tuple.getDecSedGen().getSedMunic().getMunic_nombre();
				List<DeclaracionResiduo> decares = tuple.getDeclaracion_res();
				for (DeclaracionResiduo declnRid : decares) {
					log.info(declnRid.getDer_gen_nombre());
					log.info(declnRid.getTipoEmbDec().getTem_nombre()+"");
					log.info(declnRid.getTipoEmpDec().getTep_nombre()+"");
				}
				
			}
			
		} catch (Exception e) {
            log.info("Fallo lista de autorizaciones: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
    	
    }
    
    
    public void getListaDeclaracionGes(int idS)
    {


    	CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Declaracion> conDec = cb.createQuery(Declaracion.class);	
			Root<Declaracion> rootDec = conDec.from(Declaracion.class);
			conDec.where(cb.and(cb.equal(rootDec.get("dec_gestor"), idS),cb.equal(rootDec.get("dec_trn_aprobada"), "A"),cb.notEqual(rootDec.get("dec_ges_aprobada"), "A"),cb.notEqual(rootDec.get("dec_ges_aprobada"), "R")));
			conDec.select(rootDec);
			log.info("-------------------------------Generando consulta");
			listDeclaGest = em.createQuery(conDec).getResultList();
			log.info(listDeclaGest.size()+"");
			
			
			for (Declaracion tuple : listDeclaGest) {
				log.info("////////////////////////////////////////////////////////");
				log.info(tuple.getDecSedGen().getDepartamento()+"");
				log.info(tuple.getDecSedGen().getSedMunic()+"");
				log.info(tuple.getDecSedTran().getSed_nombre()+"");
				log.info(tuple.getDecSedGen().getSed_nombre()+"");
				String em = tuple.getDecSedTran().getEmpresaSed().getEmp_nombre_comercial();
				em = tuple.getDecSedGen().getEmpresaSed().getEmp_nombre_comercial();
				String dep = tuple.getDecSedTran().getDepartamento().getDept_nombre();
				dep = tuple.getDecSedGen().getDepartamento().getDept_nombre();
				dep = tuple.getDecSedGen().getSedMunic().getMunic_nombre();
				dep = tuple.getDecSedTran().getSedMunic().getMunic_nombre();
				List<DeclaracionResiduo> decares = tuple.getDeclaracion_res();
				for (DeclaracionResiduo declnRid : decares) {
					log.info(declnRid.getDer_gen_nombre());
					log.info(declnRid.getTipoEmbDec().getTem_nombre()+"");
					log.info(declnRid.getTipoEmpDec().getTep_nombre()+"");
					log.info(declnRid.getResiduosDecl().gettResiduo().getTre_nombre()+"");
					log.info(declnRid.getResiduosDecl().gettGestion().getNombre_gestion()+"");
					log.info(declnRid.getResiduosDecl().gettManejo().getTma_nombre()+"");
					log.info(declnRid.getResiduosDecl().getgUbicacion().getNombre_gestion_ubi()+"");
					
				}
				
			}
			
		} catch (Exception e) {
            System.out.println("Fallo lista de autorizaciones: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
    	
    }
    
    public List<Declaracion> getListaDeclaracionTranporJs(int idS)
    {


    	CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Declaracion> conRes = cb.createQuery(Declaracion.class);	
			Root<Declaracion> rootDeR = conRes.from(Declaracion.class);
			conRes.where(cb.and(cb.equal(rootDeR.get("dec_transportador"), idS),cb.equal(rootDeR.get("dec_gen_aprobada"), "A")));
			conRes.select(rootDeR);
			log.info("-------------------------------Generando consulta");
			listDeclaTranp = em.createQuery(conRes).getResultList();
			
			for (Declaracion tuple : listDeclaTranp) {
				log.info("////////////////////////////////////////////////////////");
				log.info(tuple.getDecSedGes().getDepartamento()+"");
				log.info(tuple.getDecSedGes().getSedMunic()+"");
				log.info(tuple.getDecSedTran().getSed_nombre()+"");
				log.info(tuple.getDecSedGen().getSed_nombre()+"");
				List<DeclaracionResiduo> decares = tuple.getDeclaracion_res();
				for (DeclaracionResiduo declnRid : decares) {
					log.info(declnRid.getDer_gen_nombre());
					log.info(declnRid.getTipoEmbDec().getTem_nombre()+"");
					log.info(declnRid.getTipoEmpDec().getTep_nombre()+"");
				}
				
			}
			
		} catch (Exception e) {
            System.out.println("Fallo lista de autorizaciones: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
		
		return listDeclaTranp;
    	
    }
    
    
    
    
    


//	public void listarDeclaResiGestor(int cod_decla) {
//		// TODO Auto-generated method stub
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		try {
//			CriteriaQuery<Declaracion> conRes = cb.createQuery(Declaracion.class);	
//			 Root<Declaracion> RootRes = conRes.from(Declaracion.class);
//			 Join<Declaracion, DeclaracionResiduo> joinResi = RootRes.join("declaracion_res",JoinType.INNER);
//			 Join<Declaracion,Usuario> joinUsu = RootRes.join("usuarioR",JoinType.INNER);
//			 Join<Usuario, Sede> joinSede = joinUsu.join("sedeRes",JoinType.INNER);
////			 conRes.where(cb.equal(RootRes.get("sed_transportador"), "s"));
//			 conRes.multiselect(RootRes.get("dec_id"),RootRes.get("dec_gen_fecha_gen"),joinResi.get("der_gen_nombre"),joinResi.get("der_gen_tipo_residuo"),
//					 joinSede.get("sed_nombre"));
//			 listaDeclaGes = em.createQuery(conRes).getResultList();
//		} catch (Exception e) {
//            System.out.println("Fallo lista de autorizaciones: " + e.getMessage());
//            e.fillInStackTrace();
//			// TODO: handle exception
//		}
//	}

	public List<TipoEmpaque> empaque() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<TipoEmpaque> conRes = cb.createQuery(TipoEmpaque.class);	
			 Root<TipoEmpaque> RootRes = conRes.from(TipoEmpaque.class);
//			 conRes.where(cb.equal(RootRes.get("sed_transportador"), "s"));
			 conRes.select(RootRes);
			 tipoEmpa = em.createQuery(conRes).getResultList();
		} catch (Exception e) {
            System.out.println("Fallo lista de autorizaciones: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return tipoEmpa;
	}

	public List<TipoEmbalaje> embalaje() {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<TipoEmbalaje> conRes = cb.createQuery(TipoEmbalaje.class);	
			 Root<TipoEmbalaje> RootRes = conRes.from(TipoEmbalaje.class);
//			 conRes.where(cb.equal(RootRes.get("sed_transportador"), "s"));
			 conRes.select(RootRes);
			 tipoEmba = em.createQuery(conRes).getResultList();
		} catch (Exception e) {
            System.out.println("Fallo lista de autorizaciones: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
		return tipoEmba;
	}

	public long countDeclaRes(int idDr) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		long des = 0;
		try {
			CriteriaQuery<Long> conRes = cb.createQuery(Long.class);	
			 Root<DeclaracionResiduo> RootRes = conRes.from(DeclaracionResiduo.class);
//			 conRes.where(cb.equal(RootRes.get("sed_transportador"), "s"));
			 conRes.where(cb.equal(RootRes.get("der_declaracion"), idDr));
			 conRes.select(cb.count(RootRes));
			 des = em.createQuery(conRes).getSingleResult();
		} catch (Exception e) {
            System.out.println("Fallo lista de autorizaciones: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
		return des;
	}
	
	
//	public void ProgramacionHoy()
//	{
//		// TODO Auto-generated method stub
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		Calendar cini = Calendar.getInstance();
//		Calendar cinf = Calendar.getInstance();
//		cini.add(Calendar.DATE, -2);
//		cinf.add(Calendar.DATE, 2);
//		log.info(""+cini);
//		log.info(""+cinf);
//		
//		try {
//			CriteriaQuery<Tuple> conRes = cb.createTupleQuery();	
//			 Root<Prog_Residuos> RootRes = conRes.from(Prog_Residuos.class);
//			 Join<Prog_Residuos,Residuos> joinPrrRe = RootRes.join("prog_res",JoinType.INNER);
//			 Join<Prog_Residuos, Programacion> joinPrrPr = RootRes.join("prog_prog",JoinType.INNER);
//			 Join<Prog_Residuos,Declaracion> joinPrDe = RootRes.join("dec_Prog",JoinType.INNER);
//			 Join<DeclaracionResiduo,Declaracion> joip = joinPrDe.join("declaracion_res",JoinType.INNER);
////			 Join<Declaracion, DeclaracionResiduo> joinDDRe = joinPrDe.join("declaracion_res",JoinType.INNER);
//			 conRes.where(cb.and(cb.notEqual(joinPrDe.get("dec_gen_aprobada"),"A"),cb.between(joinPrrPr.get("pro_fecha_inicial"),cini ,cinf)));
////			 conRes.where(cb.equal(RootRes.get("sed_transportador"), "s"));
////			 ,joinDDRe.get("der_id")
////			 conRes.multiselect(joinPrrRe.get("res_id"),joinPrrzRe.get("res_nombre"),RootRes.get("pr_pro_id"),joip.get("der_id"));
//			 listProgHoy = em.createQuery(conRes).getResultList();
//			 for (Tuple tupe : listProgHoy) {
//				 log.info(tupe.get(0)+"");
//				 log.info(tupe.get(1)+"");
//				 log.info(tupe.get(2)+"");
//				
//			}
//		} catch (Exception e) {
//            System.out.println("Fallo lista de autorizaciones: " + e.getMessage());
//            e.fillInStackTrace();
//			// TODO: handle exception
//		}
//		
//	}
	
	
	public void ProgramacionHoy1() throws ParseException
	{
		// TODO Auto-generated method stub
		log.info("antes");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		Calendar cini = Calendar.getInstance();
		Calendar cinf = Calendar.getInstance();
		Date fecha = new Date();
		DateFormat fin = new SimpleDateFormat("yyyy-MM-dd");
		String fi = formatter.format(fecha);
		String ff = formatter.format(fecha);
		cinf.setTime(formatter.parse(ff));
		cini.setTime(formatter.parse(fi));
		cini.add(Calendar.DATE, -2);
		cinf.add(Calendar.DATE, 2);
		
		log.info("suma "+cinf+" / "+cinf);
		try {
			CriteriaQuery<Tuple> conRes = cb.createTupleQuery();	
			 Root<DeclaracionResiduo> RootDecR = conRes.from(DeclaracionResiduo.class);
			 Join<DeclaracionResiduo, Declaracion> joinDeRe = RootDecR.join("declaracion_res",JoinType.LEFT);
			 Join<Declaracion, Programacion> joinPrrPr = joinDeRe.join("prog_dec",JoinType.INNER);
//			 conRes.where(cb.notEqual(joinDeRe.get("dec_gen_aprobada"),"A"));
			 conRes.where(cb.and(cb.notEqual(joinDeRe.get("dec_gen_aprobada"),"A"),cb.between(joinPrrPr.<Date>get("pro_fecha_inicial"),cini.getTime() ,cinf.getTime())));
			 conRes.multiselect(RootDecR);
			 listProgHoy = em.createQuery(conRes).getResultList();
			 for (Tuple tuple : listDeclaAprueba) {
				log.info(tuple.toString());
			}
			
		} catch (Exception e) {
            System.out.println("Fallo lista de autorizaciones: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
		
	}

	
	
	public void pruebaProg(int idSed) throws ParseException
	{
		log.info("antes");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		Calendar cini = Calendar.getInstance();
		Calendar cinf = Calendar.getInstance();
		Date fecha = new Date();
		DateFormat fin = new SimpleDateFormat("yyyy-MM-dd");
		String fi = formatter.format(fecha);
		String ff = formatter.format(fecha);
		cinf.setTime(formatter.parse(ff));
		cini.setTime(formatter.parse(fi));
		cini.add(Calendar.DATE, -2);
		cinf.add(Calendar.DATE, 2);
		
		log.info("suma "+cinf+" / "+cinf);
		try {
			CriteriaQuery<Declaracion> conRes = cb.createQuery(Declaracion.class);
			Root<Declaracion> rootDec = conRes.from(Declaracion.class);
			Join<Declaracion, DeclaracionResiduo> joinDeDeR = rootDec.join("declaracion_res",JoinType.LEFT);
			conRes.where(cb.equal(rootDec.get("dec_generador"), idSed));
			conRes.select(rootDec);
			List<Declaracion> dcla = em.createQuery(conRes).getResultList();
			for (Declaracion declaracion : dcla) {
				log.info(declaracion.toString());
				log.info(declaracion.getDeclaracion_res()+"");
//				log.info(declaracion.getDec+"");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Fallo lista de autorizaciones: " + e.getMessage());
            e.fillInStackTrace();
		}

		
	}



	public void darListaDeclaProg(int idProg) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			 CriteriaQuery<Tuple> conRes = cb.createTupleQuery();	
//			 Root<Residuos> rootRes = conRes.from(Residuos.class);
			 Root<DeclaracionResiduo> rootDec = conRes.from(DeclaracionResiduo.class);
			 Join<DeclaracionResiduo,Declaracion> joinPrDec = rootDec.join("declaracion_res",JoinType.INNER);
			 Join<DeclaracionResiduo, Residuos> joinRes = joinPrDec.join("residuosDecl",JoinType.INNER);
//			 Join<Residuos, Prog_Residuos> joinResPr = rootRes.join("progRes", JoinType.INNER);
			 Join<Declaracion, Programacion> joinPrP = joinPrDec.join("prog_prog", JoinType.INNER);
			 Join<Residuos, TipoEmpaque> JoinEmp = joinRes.join("jTipoEmp",JoinType.INNER); 
			 Join<Residuos, TipoEmbalaje> JoinEmb = joinRes.join("jTipoEmb",JoinType.INNER);
			Join<Declaracion, Sede> joinDeSeT = joinPrDec.join("decSedTran",JoinType.INNER);
			Join<Declaracion, Sede> joinDeSeGes = joinPrDec.join("decSedGen",JoinType.INNER);
//			 Join<Prog_Residuos, Declaracion> joinDec = joinResPr.join("dec_Prog",JoinType.INNER); 
			
			 Join<Residuos,Sede> joinTrSe = joinRes.join("sedeTrans", JoinType.INNER);
			 Join<Residuos, Sede> joinGeSe = joinRes.join("sedeGest", JoinType.INNER);
			conRes.where(cb.equal(joinPrDec.get("dec_prog_id"), idProg));
			 conRes.multiselect(joinRes.get("res_id"),joinRes.get("res_nombre"),joinRes.get("res_tipo_residuo"),joinPrP.get("pro_id"),
					 joinTrSe.get("sed_id"),joinTrSe.get("sed_direccion"),joinGeSe.get("sed_id"),joinGeSe.get("sed_nombre"),joinGeSe.get("sed_direccion"),joinGeSe.get("sed_nombre")
					 ,rootDec.get("der_id"),JoinEmp.get("tep_id"),JoinEmp.get("tep_nombre"),JoinEmb.get("tem_id"),JoinEmb.get("tem_nombre"),joinDeSeGes.get("sed_nombre"),joinDeSeGes.get("sed_direccion"),joinDeSeT.get("sed_nombre"),joinDeSeT.get("sed_direccion"));
			 listaDeclaracionProg = em.createQuery(conRes).getResultList();
			 
			 
		} catch (Exception e) {
            System.out.println("Fallo lista de autorizaciones por autoridad: " + e.getMessage());
            e.fillInStackTrace();
			// TODO: handle exception
		}
		
		
	}

	public Declaracion getNewDecla() {
		// TODO Auto-generated method stub
		return newDec;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void initNewDecla() {
		// TODO Auto-generated method stub
		newDec = new Declaracion();
		
	}
	
	public DeclaracionResiduo getNewDeRes() {
		// TODO Auto-generated method stub
		return newDeRe;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void initNewDecRes() {
		// TODO Auto-generated method stub
		newDeRe = new DeclaracionResiduo();
	}

	public void registrarDeclaracion() throws Exception {
		// TODO Auto-generated method stub
        try {

            log.info("Registering " + newDec);
            em.persist(newDec);
            DecEventSrc.fire(newDec);
            initNewDecla();
        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }
            log.info("Exception:" + t.getMessage());
            throw ((Exception) t);
        }
		
	}

public void registrarDeclaracioRes() throws Exception
{
    try {

        log.info("Registering " + newDeRe);
        em.persist(newDeRe);
        DeReEventSrc.fire(newDeRe);
        initNewDecRes();
    } catch (Exception e) {
        Throwable t = e;
        System.out.println("Fallo registro de declaracion reciduo: " + e.getMessage());
        while ((t.getCause()) != null) {
            t = t.getCause();
        }
        log.info("Exception:" + t.getMessage());
        throw ((Exception) t);
    }
	
}

public void updateDecla_DeclaRes(Declaracion dcla, DeclaracionResiduo decRes) throws Exception {

    try {

    	em.merge(decRes);
    	em.merge(dcla);
        DeReEventSrc.fire(decRes);
        DecEventSrc.fire(dcla);
    } catch (Exception e) {
        Throwable t = e;
        log.info("Exception:" + t);
        while ((t.getCause()) != null) {
            t = t.getCause();
        }
        log.info("Exception:" + t.getMessage());
        throw ((Exception) t);
    }
	// TODO Auto-generated method stub
	
}

public void updateDeclaRe(Declaracion decla) throws Exception {
    try {

    	em.merge(decla);
        DecEventSrc.fire(decla);
    } catch (Exception e) {
        Throwable t = e;
        while ((t.getCause()) != null) {
            t = t.getCause();
        }
        log.info("Exception:" + t.getMessage());
        throw ((Exception) t);
    }
	// TODO Auto-generated method stub
	
}


public Declaracion findDdecla(int idDecla) {
	// TODO Auto-generated method stub
	Declaracion decla = em.find(Declaracion.class, idDecla);
	return decla;
}


public DeclaracionResiduo findDeRes(int idDres) {
	// TODO Auto-generated method stub
	DeclaracionResiduo deRes = em.find(DeclaracionResiduo.class, idDres);
	return deRes;
}

/*
 * REPORTES DE DELCLARCIONES
 */

public List<Declaracion> listDeclaAuto(int idAu, int take, int skip) {
	// TODO Auto-generated method stub
//	prubeaPag();
//	em.getTransaction().begin();
	CriteriaBuilder cb = em.getCriteriaBuilder();

	try {
		CriteriaQuery<Declaracion> criDec = cb.createQuery(Declaracion.class);
		Root<Declaracion> rootDec = criDec.from(Declaracion.class);
		Join<Declaracion, Sede> joinGen = rootDec.join("decSedGen",JoinType.INNER);
		Join<Declaracion, Sede> joinTrn = rootDec.join("decSedTran",JoinType.INNER);
		Join<Declaracion, Sede> joinGes = rootDec.join("decSedGes",JoinType.INNER);
		criDec.where(cb.or(cb.equal(joinGen.get("sed_autoridad"), idAu),cb.equal(joinTrn.get("sed_autoridad"), idAu),cb.equal(joinGes.get("sed_autoridad"), idAu)));
		criDec.orderBy(cb.asc(rootDec.get("dec_id")));
		TypedQuery<Declaracion> typedQuery1 = em.createQuery(criDec);
		listDeclaHisAut = typedQuery1.getResultList();
//		listDeclaHisAut = em.createQuery(criDec).setMaxResults(take).getResultList();
		for (Declaracion declaracion : listDeclaHisAut) {
			declaracion.getDec_id();
			log.info(declaracion.getDeclaracion_res().size()+"");
    		declaracion.getDeclaracion_res();
    		declaracion.getDec_gen_fecha_gen();
			declaracion.getDec_generador();
			declaracion.getDeclaracion_res().size();
			declaracion.getDecSedGen().getSed_nombre();
			declaracion.getDecSedGen().getSedMunic().getMunic_nombre();
			declaracion.getDecSedGen().getDepartamento().getDept_nombre();
			declaracion.getDecSedGen().getEmpresaSed().getEmp_razon_social();
			declaracion.getDecSedTran().getSed_nombre();
			declaracion.getDecSedTran().getDepartamento().getDept_nombre();
			declaracion.getDecSedTran().getEmpresaSed().getEmp_razon_social();
			declaracion.getDecSedTran().getSedMunic().getMunic_nombre();
			declaracion.getDecSedGes().getSed_nombre();
			declaracion.getDecSedGes().getDepartamento().getDept_nombre();
			declaracion.getDeclaracion_res().toString();
			declaracion.getDecSedGes().getEmpresaSed().getEmp_nombre_comercial();
			declaracion.getDecSedGes().getSedMunic().getMunic_nombre();
    		List<DeclaracionResiduo> resDec = declaracion.getDeclaracion_res();
    		for (DeclaracionResiduo decla1 : resDec) {
				decla1.getDer_gen_peso_residuo();
			}
    		
		}
		em.getTransaction().commit();
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("Fallo lista de Declaraciones: " + e.getMessage());
		log.info("Fallo lista de Declaraciones autoridad: " + e.getMessage());
		e.fillInStackTrace();
		
	}finally {
		return listDeclaHisAut;
	}
	
}



public List<Declaracion> listDeclaIdeam() {
	// TODO Auto-generated method stub
//	prubeaPag();
//	em.getTransaction().begin();
	CriteriaBuilder cb = em.getCriteriaBuilder();

	try {
		CriteriaQuery<Declaracion> criDec = cb.createQuery(Declaracion.class);
		Root<Declaracion> rootDec = criDec.from(Declaracion.class);
		criDec.orderBy(cb.asc(rootDec.get("dec_id")));
		TypedQuery<Declaracion> typedQuery1 = em.createQuery(criDec);
		listDeclaHisAut = typedQuery1.getResultList();
//		listDeclaHisAut = em.createQuery(criDec).setMaxResults(take).getResultList();
		for (Declaracion declaracion : listDeclaHisAut) {
			declaracion.getDec_id();
			log.info(declaracion.getDeclaracion_res().size()+"");
    		declaracion.getDeclaracion_res();
    		declaracion.getDec_gen_fecha_gen();
			declaracion.getDec_generador();
			declaracion.getDeclaracion_res().size();
			declaracion.getDecSedGen().getSed_nombre();
			declaracion.getDecSedGen().getSedMunic().getMunic_nombre();
			declaracion.getDecSedGen().getDepartamento().getDept_nombre();
			declaracion.getDecSedGen().getEmpresaSed().getEmp_razon_social();
			declaracion.getDecSedTran().getSed_nombre();
			declaracion.getDecSedTran().getDepartamento().getDept_nombre();
			declaracion.getDecSedTran().getEmpresaSed().getEmp_razon_social();
			declaracion.getDecSedTran().getSedMunic().getMunic_nombre();
			declaracion.getDecSedGes().getSed_nombre();
			declaracion.getDecSedGes().getDepartamento().getDept_nombre();
			declaracion.getDeclaracion_res().toString();
			declaracion.getDecSedGes().getEmpresaSed().getEmp_nombre_comercial();
			declaracion.getDecSedGes().getSedMunic().getMunic_nombre();
    		List<DeclaracionResiduo> resDec = declaracion.getDeclaracion_res();
    		for (DeclaracionResiduo decla1 : resDec) {
				decla1.getDer_gen_peso_residuo();
			}
    		
		}
		em.getTransaction().commit();
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("Fallo lista de Declaraciones: " + e.getMessage());
		log.info("Fallo lista de Declaraciones autoridad: " + e.getMessage());
		e.fillInStackTrace();
		
	}finally {
		return listDeclaHisAut;
	}
	
}




public List<Declaracion> verDeclaracionid(int id, int idDec) {
	// TODO Auto-generated method stub
CriteriaBuilder cb = em.getCriteriaBuilder();

List<Declaracion> lDecid = null;
try {
	CriteriaQuery<Declaracion> criB = em.getCriteriaBuilder().createQuery(Declaracion.class);
	Root<Declaracion> rootDec = criB.from(Declaracion.class);
	criB.where(cb.equal(rootDec.get("dec_id"), idDec));
	criB.select(rootDec);
	lDecid = em.createQuery(criB).getResultList();
	for (Declaracion declaracion : lDecid) {
		declaracion.getDec_id();
		log.info(declaracion.getDeclaracion_res().size()+"");
		declaracion.getDeclaracion_res();
		declaracion.getDec_gen_fecha_gen();
		declaracion.getDec_generador();
		declaracion.getDeclaracion_res().size();
		declaracion.getDecSedGen().getSed_nombre();
		declaracion.getDecSedGen().getSedMunic().getMunic_nombre();
		declaracion.getDecSedGen().getDepartamento().getDept_nombre();
		declaracion.getDecSedGen().getEmpresaSed().getEmp_razon_social();
		declaracion.getDecSedTran().getSed_nombre();
		declaracion.getDecSedTran().getDepartamento().getDept_nombre();
		declaracion.getDecSedTran().getEmpresaSed().getEmp_razon_social();
		declaracion.getDecSedTran().getSedMunic().getMunic_nombre();
		declaracion.getDecSedGes().getSed_nombre();
		declaracion.getDecSedGes().getDepartamento().getDept_nombre();
		declaracion.getDeclaracion_res().toString();
		declaracion.getDecSedGes().getEmpresaSed().getEmp_nombre_comercial();
		declaracion.getDecSedGes().getSedMunic().getMunic_nombre();
		List<DeclaracionResiduo> resDec = declaracion.getDeclaracion_res();
		for (DeclaracionResiduo decla1 : resDec) {
			decla1.getDer_gen_peso_residuo();
		}
	}
} catch (Exception e) {
	log.info("Fallo lista de Declaraciones: " + e.getMessage());
	lDecid = null;

}

return lDecid;
}


public void prubeaPag()
{

    Query query1 = em.createQuery("SELECT d FROM Declaracion d ORDER BY d.dec_id ", Declaracion.class);
    query1.setMaxResults(3);
    Query query = em.createNativeQuery("SELECT a FROM Declaracion a ORDER a.dec_id LIMIT 5 OFFSET 5", Declaracion.class);
    listDeclaHisAut = query1.getResultList();
;
}


public void reporteMes(int idSed) {
	// TODO Auto-generated method stub



	CriteriaBuilder cb = em.getCriteriaBuilder();

try {
CriteriaQuery<Tuple> conRes = cb.createTupleQuery();	
Root<DeclaracionResiduo> rootDecR = conRes.from(DeclaracionResiduo.class);
Join<DeclaracionResiduo, Declaracion> joinDec = rootDecR.join("declaracion_res",JoinType.INNER);
Join<Declaracion, Programacion> joinPr = joinDec.join("prog_dec",JoinType.INNER);
Join<DeclaracionResiduo, EstadoMateria> joinEs = rootDecR.join("deReEstado",JoinType.INNER);
conRes.where(cb.equal(joinDec.get("dec_generador"), idSed));
conRes.groupBy(rootDecR.get("der_res_id"),joinEs.get("ema_nombre"), rootDecR.get("der_gen_tipo_residuo"),cb.function("month", Integer.class, joinPr.get("pro_fecha_inicial")));
conRes.multiselect(rootDecR.get("der_res_id"),cb.sum(rootDecR.<Number>get("der_gen_peso_residuo")),rootDecR.get("der_gen_tipo_residuo"),cb.function("month", Integer.class, joinPr.get("pro_fecha_inicial")),joinEs.get("ema_nombre"));
conRes.having(cb.notEqual(cb.sum(rootDecR.<Number>get("der_gen_peso_residuo")), 0));
listRepoMesGene = em.createQuery(conRes).getResultList();
log.info(listRepoMesGene.size()+"");
for (Tuple tuple : listRepoMesGene) {
	
	log.info(tuple.get(0)+"/0");
	log.info(tuple.get(1)+"/1");
	log.info(tuple.get(2)+"/2");
	log.info(tuple.get(3)+"/3");
	log.info(tuple.get(4)+"/4");
}

} catch (Exception e) {
	System.out.println("Fallo lista de Declaraciones: " + e.getMessage());
	log.info("Fallo lista de Declaraciones: " + e.getMessage());
	e.fillInStackTrace();
	listRepoMesGene = null;
}

	
}















}
