package co.gov.ideam.sdstrp.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.management.Query;
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
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.Subquery;


import co.gov.ideam.sdstrp.model.Declaracion;
import co.gov.ideam.sdstrp.model.DeclaracionResiduo;
import co.gov.ideam.sdstrp.model.Empresa;
import co.gov.ideam.sdstrp.model.Programacion;
import co.gov.ideam.sdstrp.model.Sede;
import co.gov.ideam.sdstrp.model.Usuario;
import co.gov.ideam.sdstrp.model.dashboard;

@Model
public class DashDao {
	
    @Inject
    private Logger log;
    
    @Inject
    EntityManager em;


    private List<Tuple> tuple;
    private Object result;
    private List<Tuple> declaraciones;
    private List<Tuple> DeclarGen;
    private Tuple DeclaTrn;
    private List<Tuple> conDash;
    private List<Tuple> conDashTrn;
    static Tuple dashIdeam;

	private Tuple DeclaAut;
	

    
    @Produces
    @Named
    public Tuple getDeclarIdeam()
    {
    	return dashIdeam;
    }

    @Produces
    @Named
    public Tuple getDeclarAut()
    {
    	return DeclaAut;
    }
	
    @Produces
    @Named
    public List<Tuple> getDeclarGen()
    {
    	return DeclarGen;
    }
    

    @Produces
    @Named
    public List<Tuple> getConDashTrn()
    {
    	return conDashTrn;
    }
    
    
    @Produces
    @Named
    public List<Tuple> getConDash()
    {
    	return conDash;
    }
    
    
    @Produces
    @Named
    public Tuple getDeclartrn()
    {
    	return DeclaTrn;
    }
    

    

	public void declaracionesDeTrn(int idSede, String perf) {
		// TODO Auto-generated method stub
		conDashTrn = null;
		
		CriteriaBuilder cbDb = em.getCriteriaBuilder();

try {

	CriteriaQuery<Tuple> conRes = cbDb.createTupleQuery();		
	Root<Declaracion> rootDeRe = conRes.from(Declaracion.class);
	conRes.where(cbDb.and(cbDb.equal(rootDeRe.get("dec_"+perf),idSede),cbDb.equal(rootDeRe.get("dec_gen_aprobada"),"A")));
	conRes.groupBy(rootDeRe.get("dec_"+perf));
	
//	
	Subquery<Long> sbq = conRes.subquery(Long.class);
	Root<Declaracion> rootsqDe = sbq.from(Declaracion.class);
	sbq.where(cbDb.and(cbDb.equal(rootsqDe.get("dec_"+perf),idSede),cbDb.or(cbDb.equal(rootsqDe.get("dec_gen_aprobada"),"R"),cbDb.equal(rootsqDe.get("dec_gen_aprobada"),"N"))));
	sbq.groupBy(rootsqDe.get("dec_"+perf));
	rootsqDe.alias("noEnv");
	sbq.select(cbDb.count(rootsqDe.get("dec_"+perf))); 
	Expression<Long> rGen = sbq.getSelection();
//	
	Subquery<Long> sq1 = conRes.subquery(Long.class);
	Root<Declaracion> rootsqDe2 = sq1.from(Declaracion.class);
	sq1.where(cbDb.and(cbDb.equal(rootsqDe2.get("dec_"+perf),idSede),cbDb.equal(rootsqDe2.get("dec_trn_aprobada"),"A")));
	sq1.groupBy(rootsqDe2.get("dec_"+perf));
	rootsqDe2.alias("aprobada_trn");
	sq1.select(cbDb.count(rootsqDe2.get("dec_"+perf))); 
	Expression<Long> apTrn = sq1.getSelection();
////	
	Subquery<Long> sq2 = conRes.subquery(Long.class);
	Root<Declaracion> rootsqDe3 = sq2.from(Declaracion.class);
//	sq2.where(cb.and(cb.equal(rootsqDe3.get("dec_generador"),idS),cb.equal(rootsqDe3.get("dec_trn_aprobada"),"A")));
	sq2.where(cbDb.and(cbDb.equal(rootsqDe3.get("dec_"+perf),idSede),cbDb.equal(rootsqDe3.get("dec_trn_aprobada"),"R")));
	sq2.groupBy(rootsqDe3.get("dec_"+perf));
	rootsqDe3.alias("Rechasada");
	sq2.select(cbDb.count(rootsqDe3.get("dec_"+perf))); 
	Expression<Long> reTrn = sq2.getSelection();
////	
	Subquery<Long> sq3 = conRes.subquery(Long.class);
	Root<Declaracion> rootsqDe4 = sq3.from(Declaracion.class);
	sq3.where(cbDb.and(cbDb.equal(rootsqDe4.get("dec_"+perf),idSede),cbDb.equal(rootsqDe4.get("dec_ges_aprobada"),"A")));
	sq3.groupBy(rootsqDe4.get("dec_"+perf));
	rootsqDe4.alias("finalizada");
	sq3.select(cbDb.count(rootsqDe4.get("dec_"+perf))); 
	Expression<Long> apGes = sq3.getSelection();
////	
	Subquery<Long> sq5 = conRes.subquery(Long.class);
	Root<Declaracion> rootsqDe5 = sq5.from(Declaracion.class);
//	sq5.where(cb.and(cb.equal(rootsqDe5.get("dec_generador"),idS),cb.equal(rootsqDe5.get("dec_ges_aprobada"),"A")));
	sq5.where(cbDb.and(cbDb.equal(rootsqDe5.get("dec_"+perf),idSede),cbDb.equal(rootsqDe5.get("dec_ges_aprobada"),"R")));
	sq5.groupBy(rootsqDe5.get("dec_"+perf));
	rootsqDe5.alias("rechasada_ges");
	sq5.select(cbDb.count(rootsqDe5.get("dec_"+perf))); 
	Expression<Long> reGes = sq5.getSelection();
////	
	Subquery<Long> sq4 = conRes.subquery(Long.class);
	Root<Programacion> rootSPr = sq4.from(Programacion.class);
	sq4.where(cbDb.equal(rootSPr.get("pro_"+perf), idSede));
	sq4.groupBy(rootSPr.get("pro_"+perf));
	sq4.select(cbDb.count(rootSPr.get("pro_id")));
	Expression<Long> progGen = sq4.getSelection();
	
//	Path<Long> agegadas = (Path<Long>) cb.count(rootDeRe); 
//	Expression<ResultType> expression = cb.coalesce(sbq, cb.literal(null) literal((ResultType) defaultResult);

	conRes.multiselect(cbDb.count(rootDeRe.get("dec_id"))
			,rGen
			,apTrn
			,reTrn
			,apGes
			,reGes
			,progGen
			);
//	log.info(em.createQuery(conRes)+"");
	
	List<Tuple> res = em.createQuery(conRes).getResultList();
	log.info(res.toString());
	conDashTrn = res;

}catch (Exception e) {

	System.out.println("Fallo reporte dashboard transportador por:  " + e.getMessage());
    e.fillInStackTrace();
    
	
	
}
	}
	
	
	public void declaracionesDeGene(int idS,String perf)
	{

				conDash = null;
				CriteriaBuilder cbDb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Tuple> conRes = cbDb.createTupleQuery();		
			Root<Declaracion> rootDeRe = conRes.from(Declaracion.class);
			conRes.where(cbDb.and(cbDb.equal(rootDeRe.get("dec_"+perf),idS),cbDb.equal(rootDeRe.get("dec_gen_aprobada"),"A")));
			conRes.groupBy(rootDeRe.get("dec_"+perf));
			
//			
			Subquery<Long> sbq = conRes.subquery(Long.class);
			Root<Declaracion> rootsqDe = sbq.from(Declaracion.class);
			sbq.where(cbDb.and(cbDb.equal(rootsqDe.get("dec_"+perf),idS),cbDb.or(cbDb.equal(rootsqDe.get("dec_gen_aprobada"),"R"),cbDb.equal(rootsqDe.get("dec_gen_aprobada"),"N"))));
			sbq.groupBy(rootsqDe.get("dec_"+perf));
			rootsqDe.alias("noEnv");
			sbq.select(cbDb.count(rootsqDe.get("dec_"+perf))); 
			Expression<Long> rGen = sbq.getSelection();
//			
			Subquery<Long> sq1 = conRes.subquery(Long.class);
			Root<Declaracion> rootsqDe2 = sq1.from(Declaracion.class);
			sq1.where(cbDb.and(cbDb.equal(rootsqDe2.get("dec_"+perf),idS),cbDb.equal(rootsqDe2.get("dec_trn_aprobada"),"A")));
			sq1.groupBy(rootsqDe2.get("dec_"+perf));
			rootsqDe2.alias("aprobada_trn");
			sq1.select(cbDb.count(rootsqDe2.get("dec_"+perf))); 
			Expression<Long> apTrn = sq1.getSelection();
////			
			Subquery<Long> sq2 = conRes.subquery(Long.class);
			Root<Declaracion> rootsqDe3 = sq2.from(Declaracion.class);
//			sq2.where(cb.and(cb.equal(rootsqDe3.get("dec_generador"),idS),cb.equal(rootsqDe3.get("dec_trn_aprobada"),"A")));
			sq2.where(cbDb.and(cbDb.equal(rootsqDe3.get("dec_"+perf),idS),cbDb.equal(rootsqDe3.get("dec_trn_aprobada"),"R")));
			sq2.groupBy(rootsqDe3.get("dec_"+perf));
			rootsqDe3.alias("Rechasada");
			sq2.select(cbDb.count(rootsqDe3.get("dec_"+perf))); 
			Expression<Long> reTrn = sq2.getSelection();
////			
			Subquery<Long> sq3 = conRes.subquery(Long.class);
			Root<Declaracion> rootsqDe4 = sq3.from(Declaracion.class);
			sq3.where(cbDb.and(cbDb.equal(rootsqDe4.get("dec_"+perf),idS),cbDb.equal(rootsqDe4.get("dec_ges_aprobada"),"A")));
			sq3.groupBy(rootsqDe4.get("dec_"+perf));
			rootsqDe4.alias("finalizada");
			sq3.select(cbDb.count(rootsqDe4.get("dec_"+perf))); 
			Expression<Long> apGes = sq3.getSelection();
////			
			Subquery<Long> sq5 = conRes.subquery(Long.class);
			Root<Declaracion> rootsqDe5 = sq5.from(Declaracion.class);
//			sq5.where(cb.and(cb.equal(rootsqDe5.get("dec_generador"),idS),cb.equal(rootsqDe5.get("dec_ges_aprobada"),"A")));
			sq5.where(cbDb.and(cbDb.equal(rootsqDe5.get("dec_"+perf),idS),cbDb.equal(rootsqDe5.get("dec_ges_aprobada"),"R")));
			sq5.groupBy(rootsqDe5.get("dec_"+perf));
			rootsqDe5.alias("rechasada_ges");
			sq5.select(cbDb.count(rootsqDe5.get("dec_"+perf))); 
			Expression<Long> reGes = sq5.getSelection();
////			
			Subquery<Long> sq4 = conRes.subquery(Long.class);
			Root<Programacion> rootSPr = sq4.from(Programacion.class);
			sq4.where(cbDb.equal(rootSPr.get("pro_"+perf), idS));
			sq4.groupBy(rootSPr.get("pro_"+perf));
			sq4.select(cbDb.count(rootSPr.get("pro_id")));
			Expression<Long> progGen = sq4.getSelection();
			
//			Path<Long> agegadas = (Path<Long>) cb.count(rootDeRe); 
//			Expression<ResultType> expression = cb.coalesce(sbq, cb.literal(null) literal((ResultType) defaultResult);

			conRes.multiselect(cbDb.count(rootDeRe.get("dec_id"))
					,rGen
					,apTrn
					,reTrn
					,apGes
					,reGes
					,progGen
					);
//			log.info(em.createQuery(conRes)+"");
			
			List<Tuple> res = em.createQuery(conRes).getResultList();
			log.info(res.toString());
			
				conDash = res;	
			
			

		}
		catch (Exception e) {
			System.out.println("Fallo reporte dashboard generador por:  " + e.getMessage());
            e.fillInStackTrace();
            
			
			
		}}
		


	public void declaracionDeTrn(int idS) {
		// TODO Auto-generated method stub
		

		CriteriaBuilder cb = em.getCriteriaBuilder();

		try {

			CriteriaQuery<Tuple> conRes = cb.createTupleQuery();		
			Root<Declaracion> rootDeRe = conRes.from(Declaracion.class);
			conRes.where(cb.and(cb.equal(rootDeRe.get("dec_transportador"),idS),cb.equal(rootDeRe.get("dec_gen_aprobada"),"A")));
//			conRes.groupBy(rootDeRe.get("dec_transportador"));
			
			
			Subquery<Long> sbq = conRes.subquery(Long.class);
			Root<Declaracion> rootsqDe = sbq.from(Declaracion.class);
			sbq.where(cb.and(cb.equal(rootsqDe.get("dec_transportador"),idS),cb.equal(rootsqDe.get("dec_gen_aprobada"),"N")));
			sbq.groupBy(rootsqDe.get("dec_transportador"));
			rootsqDe.alias("prueba");
			sbq.select(cb.count(rootsqDe.get("dec_transportador"))); 
			Expression<Long> nEGen = sbq.getSelection();
			
			Subquery<Long> sq1 = conRes.subquery(Long.class);
			Root<Declaracion> rootsqDe2 = sq1.from(Declaracion.class);
			sq1.where(cb.and(cb.equal(rootsqDe2.get("dec_transportador"),idS),cb.equal(rootsqDe2.get("dec_trn_aprobada"),"A")));
			sq1.groupBy(rootsqDe2.get("dec_transportador"));
			rootsqDe2.alias("prueba");
			sq1.select(cb.count(rootsqDe2.get("dec_transportador"))); 
			Expression<Long> apTrn = sq1.getSelection();
			
			Subquery<Long> sq2 = conRes.subquery(Long.class);
			Root<Declaracion> rootsqDe3 = sq2.from(Declaracion.class);
//			sq2.where(cb.and(cb.equal(rootsqDe3.get("dec_generador"),idS),cb.equal(rootsqDe3.get("dec_trn_aprobada"),"A")));
			sq2.where(cb.and(cb.equal(rootsqDe3.get("dec_transportador"),idS),cb.equal(rootsqDe3.get("dec_trn_aprobada"),"R")));
			sq2.groupBy(rootsqDe3.get("dec_transportador"));
			rootsqDe3.alias("prueba");
			sq2.select(cb.count(rootsqDe3.get("dec_transportador"))); 
			Expression<Long> reTrn = sq2.getSelection();
			
			Subquery<Long> sq3 = conRes.subquery(Long.class);
			Root<Declaracion> rootsqDe4 = sq3.from(Declaracion.class);
			sq3.where(cb.and(cb.equal(rootsqDe4.get("dec_transportador"),idS),cb.equal(rootsqDe4.get("dec_ges_aprobada"),"A")));
			sq3.groupBy(rootsqDe4.get("dec_transportador"));
			rootsqDe4.alias("prueba");
			sq3.select(cb.count(rootsqDe4.get("dec_transportador"))); 
			Expression<Long> apGes = sq3.getSelection();
			
			Subquery<Long> sq5 = conRes.subquery(Long.class);
			Root<Declaracion> rootsqDe5 = sq5.from(Declaracion.class);
//			sq5.where(cb.and(cb.equal(rootsqDe5.get("dec_generador"),idS),cb.equal(rootsqDe5.get("dec_ges_aprobada"),"A")));
			sq2.where(cb.and(cb.equal(rootsqDe5.get("dec_transportador"),idS),cb.equal(rootsqDe5.get("dec_ges_aprobada"),"R")));
			sq5.groupBy(rootsqDe5.get("dec_transportador"));
			rootsqDe5.alias("prueba");
			sq5.select(cb.count(rootsqDe5.get("dec_transportador"))); 
			Expression<Long> reGes = sq5.getSelection();
			
			Subquery<Long> sq4 = conRes.subquery(Long.class);
			Root<Programacion> rootSPr = sq4.from(Programacion.class);
			sq4.where(cb.equal(rootSPr.get("pro_transportador"), idS));
			sq4.groupBy(rootSPr.get("pro_transportador"));
			sq4.select(cb.count(rootSPr.get("pro_id")));
			Expression<Long> progGen = sq4.getSelection();
			
//			Path<Long> agegadas = (Path<Long>) cb.count(rootDeRe); 
//			Expression<ResultType> expression = cb.coalesce(sbq, cb.literal(null) literal((ResultType) defaultResult);

			conRes.multiselect(cb.count(rootDeRe.get("dec_id")),nEGen,apTrn,reTrn,apGes,reGes,progGen);
			log.info(em.createQuery(conRes)+"");
			DeclaTrn = em.createQuery(conRes).getSingleResult();

			
		} catch (Exception e) {
		
			System.out.println("Fallo validacion de conte declaraciones generador:  " + e.getMessage());
            e.fillInStackTrace();
            DeclaTrn = null;
			
			
		}
		
		
		
	}
	
	public void declaracionDeAut(int idS) {
		// TODO Auto-generated method stub
		
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			
			CriteriaQuery<Tuple> conRes = cb.createTupleQuery();		
			Root<Sede> rootDeRe = conRes.from(Sede.class);
			Join<Sede,Declaracion> joinTrn = rootDeRe.join("decSedTran",JoinType.INNER);
			conRes.where(cb.and(cb.equal(rootDeRe.get("sed_autoridad"),idS),cb.equal(joinTrn.get("dec_gen_aprobada"),"A")));
			conRes.groupBy(rootDeRe.get("sed_autoridad"));
			
			Subquery<Long> sbq = conRes.subquery(Long.class);
			Root<Sede> rootsqDe = sbq.from(Sede.class);
			Join<Sede,Declaracion> joinTrn1 = rootsqDe.join("decSedTran",JoinType.INNER);
			sbq.where(cb.and(cb.equal(rootsqDe.get("sed_autoridad"),idS),cb.equal(joinTrn1.get("dec_gen_aprobada"),"N")));
			sbq.groupBy(rootsqDe.get("sed_autoridad"));
			sbq.select(cb.count(joinTrn1.get("dec_id"))); 
			Expression<Long> nEGen = sbq.getSelection();
			
			Subquery<Long> sq1 = conRes.subquery(Long.class);
			Root<Sede> rootsqDe2 = sq1.from(Sede.class);
			Join<Sede,Declaracion> joinTrn2 = rootsqDe2.join("decSedTran",JoinType.INNER);
			sq1.where(cb.and(cb.equal(rootsqDe2.get("sed_autoridad"),idS),cb.equal(joinTrn2.get("dec_trn_aprobada"),"A")));
			sq1.groupBy(rootsqDe2.get("sed_autoridad"));
			sq1.select(cb.count(joinTrn2.get("dec_id"))); 
			Expression<Long> apTrn = sq1.getSelection();		
			Subquery<Long> sq2 = conRes.subquery(Long.class);
			Root<Sede> rootsqDe3 = sq2.from(Sede.class);
			Join<Sede,Declaracion> joinTrn3 = rootsqDe3.join("decSedTran",JoinType.INNER);
			sq2.where(cb.and(cb.equal(rootsqDe3.get("sed_autoridad"),idS),cb.equal(joinTrn3.get("dec_trn_aprobada"),"R")));
			sq2.groupBy(rootsqDe3.get("sed_autoridad"));
			sq2.select(cb.count(joinTrn3.get("dec_id"))); 
			Expression<Long> reTrn = sq2.getSelection();
			
			Subquery<Long> sq3 = conRes.subquery(Long.class);
			Root<Sede> rootsqDe4 = sq3.from(Sede.class);
			Join<Sede,Declaracion> joinTrn4 = rootsqDe4.join("decSedTran",JoinType.INNER);
			sq3.where(cb.and(cb.equal(rootsqDe4.get("sed_autoridad"),idS),cb.equal(joinTrn4.get("dec_ges_aprobada"),"A")));
			sq3.groupBy(rootsqDe4.get("sed_autoridad"));
			sq3.select(cb.count(joinTrn4.get("dec_id"))); 
			Expression<Long> apGes = sq3.getSelection();
			
			Subquery<Long> sq5 = conRes.subquery(Long.class);
			Root<Sede> rootsqDe5 = sq5.from(Sede.class);
			Join<Sede,Declaracion> joinTrn5 = rootsqDe5.join("decSedTran",JoinType.INNER);
			sq5.where(cb.and(cb.equal(rootsqDe5.get("sed_autoridad"),idS),cb.equal(joinTrn5.get("dec_ges_aprobada"),"R")));
			sq5.groupBy(rootsqDe5.get("sed_autoridad"));			
			sq5.select(cb.count(joinTrn5.get("dec_id"))); 
			Expression<Long> reGes = sq5.getSelection();
			
			Subquery<Long> sq6 = conRes.subquery(Long.class);
			Root<Sede> rootsqDe6 = sq6.from(Sede.class);
			sq6.where(cb.and(cb.equal(rootsqDe6.get("sed_autoridad"),idS),cb.equal(rootsqDe6.get("sed_generador"), "S")));
			sq6.groupBy(rootsqDe6.get("sed_autoridad"));			
			sq6.select(cb.count(rootsqDe6.get("sed_id"))); 
			Expression<Long> genSed = sq6.getSelection();
			
			Subquery<Long> sq7 = conRes.subquery(Long.class);
			Root<Sede> rootsqDe7 = sq7.from(Sede.class);
			sq7.where(cb.and(cb.equal(rootsqDe7.get("sed_autoridad"),idS),cb.equal(rootsqDe7.get("sed_transportador"), "S")));
			sq7.groupBy(rootsqDe7.get("sed_autoridad"));			
			sq7.select(cb.count(rootsqDe7.get("sed_id"))); 
			Expression<Long> trnSed = sq7.getSelection();
			
			Subquery<Long> sq8 = conRes.subquery(Long.class);
			Root<Sede> rootsqDe8 = sq8.from(Sede.class);
			sq8.where(cb.and(cb.equal(rootsqDe8.get("sed_autoridad"),idS),cb.equal(rootsqDe8.get("sed_generador"), "S")));
			sq8.groupBy(rootsqDe8.get("sed_autoridad"));			
			sq8.select(cb.count(rootsqDe8.get("sed_id"))); 
			Expression<Long> gesSed = sq8.getSelection();
			
			Subquery<Long> sq9 = conRes.subquery(Long.class);
			Root<Sede> rootsqDe9 = sq9.from(Sede.class);
			sq8.where(cb.and(cb.equal(rootsqDe8.get("sed_autoridad"),idS),cb.equal(rootsqDe8.get("sed_generador"), "S")));
			sq9.groupBy(rootsqDe9.get("sed_autoridad"));			
			sq9.select(cb.count(rootsqDe9.get("sed_id"))); 
			Expression<Long> conSed = sq9.getSelection();
			

			conRes.multiselect(cb.count(joinTrn.get("dec_id"))
					,nEGen
					,apTrn
					,reTrn
					,apGes
					,reGes
					,genSed
					,trnSed
					,gesSed
					,conSed
					);
			log.info(em.createQuery(conRes)+"");
			DeclaAut = em.createQuery(conRes).getSingleResult();
			
		}
		catch (Exception e) {
		
			System.out.println("Fallo validacion de conte declaraciones generador:  " + e.getMessage());
            e.fillInStackTrace();
            DeclaAut = null;
			
			
		}
		
		
		
	}
	
	public void declasIdeam()
	{
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
try {
			
			CriteriaQuery<Tuple> conRes = cb.createTupleQuery();		
			Root<Sede> rootDeRe = conRes.from(Sede.class);
			Join<Sede,Declaracion> joinTrn = rootDeRe.join("decSedGen",JoinType.INNER);
			conRes.where(cb.and(cb.equal(joinTrn.get("dec_gen_aprobada"),"A"),cb.equal(joinTrn.get("dec_trn_aprobada"),"N")));
			conRes.groupBy(joinTrn.get("dec_gen_aprobada"));

			
			Subquery<Long> sbq = conRes.subquery(Long.class);
			Root<Sede> rootsqDe = sbq.from(Sede.class);
			Join<Sede,Declaracion> joinTrn1 = rootsqDe.join("decSedTran",JoinType.INNER);
			sbq.where(cb.equal(joinTrn1.get("dec_gen_aprobada"),"N"));
			sbq.groupBy(joinTrn1.get("dec_gen_aprobada"));
			sbq.select(cb.count(joinTrn1.get("dec_id"))); 
			Expression<Long> nEGen = sbq.getSelection();
			
			Subquery<Long> sq1 = conRes.subquery(Long.class);
			Root<Sede> rootsqDe2 = sq1.from(Sede.class);
			Join<Sede,Declaracion> joinTrn2 = rootsqDe2.join("decSedTran",JoinType.INNER);
			sq1.where(cb.and(cb.equal(joinTrn2.get("dec_trn_aprobada"),"A"),cb.equal(joinTrn2.get("dec_ges_aprobada"),"N"))); //agreagr gen N
			sq1.groupBy(joinTrn2.get("dec_trn_aprobada"));
			sq1.select(cb.count(joinTrn2.get("dec_id"))); 
			Expression<Long> apTrn = sq1.getSelection();
			
			Subquery<Long> sq2 = conRes.subquery(Long.class);
			Root<Sede> rootsqDe3 = sq2.from(Sede.class);
			Join<Sede,Declaracion> joinTrn3 = rootsqDe3.join("decSedTran",JoinType.INNER);
			sq2.where(cb.equal(joinTrn3.get("dec_trn_aprobada"),"R"));
			sq2.groupBy(joinTrn3.get("dec_trn_aprobada"));
			sq2.select(cb.count(joinTrn3.get("dec_id"))); 
			Expression<Long> reTrn = sq2.getSelection();
			
			Subquery<Long> sq3 = conRes.subquery(Long.class);
			Root<Sede> rootsqDe4 = sq3.from(Sede.class);
			Join<Sede,Declaracion> joinTrn4 = rootsqDe4.join("decSedTran",JoinType.INNER);
			sq3.where(cb.equal(joinTrn4.get("dec_ges_aprobada"),"A"));
			sq3.groupBy(joinTrn4.get("dec_ges_aprobada"));
			sq3.select(cb.count(joinTrn4.get("dec_id"))); 
			Expression<Long> apGes = sq3.getSelection();
			
			Subquery<Long> sq5 = conRes.subquery(Long.class);
			Root<Sede> rootsqDe5 = sq5.from(Sede.class);
			Join<Sede,Declaracion> joinTrn5 = rootsqDe5.join("decSedTran",JoinType.INNER);
			sq5.where(cb.equal(joinTrn5.get("dec_ges_aprobada"),"R"));
			sq5.groupBy(joinTrn5.get("dec_ges_aprobada"));
			sq5.select(cb.count(joinTrn5.get("dec_id"))); 
			Expression<Long> reGes = sq5.getSelection();
			
			Subquery<Long> sq6 = conRes.subquery(Long.class);
			Root<Sede> rootsqDe6 = sq6.from(Sede.class);
			sq6.where(cb.equal(rootsqDe6.get("sed_generador"), "S"));	
			sq6.groupBy(rootsqDe6.get("sed_generador"));
			sq6.select(cb.count(rootsqDe6.get("sed_id"))); 
			Expression<Long> genSed = sq6.getSelection();
			
			Subquery<Long> sq7 = conRes.subquery(Long.class);
			Root<Sede> rootsqDe7 = sq7.from(Sede.class);
			sq7.where(cb.equal(rootsqDe7.get("sed_transportador"), "S"));			
			sq7.select(cb.count(rootsqDe7.get("sed_id"))); 
			Expression<Long> trnSed = sq7.getSelection();
			
			Subquery<Long> sq8 = conRes.subquery(Long.class);
			Root<Sede> rootsqDe8 = sq8.from(Sede.class);
			sq8.where(cb.equal(rootsqDe8.get("sed_gestor"), "S"));			
			sq8.select(cb.count(rootsqDe8.get("sed_id"))); 
			Expression<Long> gesSed = sq8.getSelection();
			
			Subquery<Long> sq9 = conRes.subquery(Long.class);
			Root<Sede> rootsqDe9 = sq9.from(Sede.class);			
			sq9.select(cb.count(rootsqDe9.get("sed_id"))); 
			Expression<Long> conSed = sq9.getSelection();
			


			conRes.multiselect(cb.count(joinTrn.get("dec_id"))
					,nEGen
					,apTrn
					,reTrn
					,apGes
					,reGes
					,genSed
					,trnSed
					,gesSed
					,conSed
					);
			log.info(em.createQuery(conRes)+"");
			dashIdeam = em.createQuery(conRes).getSingleResult();
			
			log.info(""+dashIdeam);
			log.info(dashIdeam.get(6)+"");
			log.info(dashIdeam.get(7)+"");
			log.info(dashIdeam.get(8)+"");
			log.info(dashIdeam.get(9)+"");
			
			
		} 
			catch (Exception e) {
			log.info("Fallo lista de Declaraciones: " + e.getMessage());
			dashIdeam = null;
			
			
		}
		
	}
}
