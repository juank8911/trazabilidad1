package co.gov.ideam.sdstrp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.gov.ideam.sdstrp.controller.ConsultaComboDAO;
import co.gov.ideam.sdstrp.controller.DashDao;
import co.gov.ideam.sdstrp.controller.DeclaracionDAO;
import co.gov.ideam.sdstrp.controller.ProgramacionDao;
import co.gov.ideam.sdstrp.controller.ResiduoDAO;
import co.gov.ideam.sdstrp.controller.SedeDAO;
import co.gov.ideam.sdstrp.controller.UserDao;
import co.gov.ideam.sdstrp.model.Declaracion;
import co.gov.ideam.sdstrp.model.DeclaracionResiduo;
import co.gov.ideam.sdstrp.model.Empresa;
import co.gov.ideam.sdstrp.model.Programacion;
import co.gov.ideam.sdstrp.model.Residuos;
import co.gov.ideam.sdstrp.model.Sede;
import co.gov.ideam.sdstrp.model.TipoGestion;
import co.gov.ideam.sdstrp.model.TipoManejo;
import co.gov.ideam.sdstrp.model.Usuario;

//import co.gov.ideam.sdstrp.model.tipoGestion;
//import co.gov.ideam.sdstrp.model.tipoManejo;
//import co.gov.ideam.sdstrp.model.tipoPeligrosidad;
//import co.gov.ideam.sdstrp.model.tipoResiduo;


@WebServlet(name = "controlGenerador", urlPatterns = {"/controlGenerador"})
public class ControlGenerador extends HttpServlet {
	
    @Inject
    private Logger log;
	@Inject
	 private ResiduoDAO resDAO;
	@Inject
	 private ConsultaComboDAO combo;
	@Inject
	private DeclaracionDAO decDAO;
	
	@Inject
	private DashDao dasDao;
	@Inject
	private SedeDAO sedDAO;
	
	@Inject
	private UserDao usrDAO;

	
	@Inject
	private ProgramacionDao progDao;
	
	@Inject
	private DeclaracionDAO decDao;
		
    @Inject
    private EntityManager em;
    
    
    private Programacion prog;
	private Declaracion decl;
	private DeclaracionResiduo decaRes;
	private Sede sedeTr; 
	private Sede sedeGes;
	private Sede SedeGen;
	private int idGen;
    
	private List<Date> dates = new ArrayList<Date>();
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RequestDispatcher dis;
	 
	    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws Exception{
	            PrintWriter out = response.getWriter();
	    		HttpSession sesion = request.getSession();	            
	            response.setContentType("text/html;charset=UTF-8");
	            String ruta = "";
	            String action = request.getParameter("action");
	            switch (action) {

	            case "generador":
	            	ruta = "view/generador/generador.jsp";
	            	if (sesion.getAttribute("perfil") == null) {
	    				ruta = "view/login.jsp";
	                    this.dirigir(request, response, ruta);
	    			} else {
	    				String perf = "generador";
	    				int idSede = (int) sesion.getAttribute("idSede");
	    				dasDao.declaracionesDeGene(idSede,perf);
	    				ordenarDash(sesion);
	                    this.dirigir(request, response, ruta);
	    			}
	                
	                break;
	            case "perfilLeeGenera":
	                ruta = "view/generador/persona/Read.jsp";
	                if (sesion.getAttribute("perfil") == null) {
	    				ruta = "view/login.jsp";
	                    this.dirigir(request, response, ruta);
	    			} else {
	    				sedDAO.darSedeId(Integer.parseInt(String.valueOf(sesion.getAttribute("idSede"))));
	                    this.dirigir(request, response, ruta);
	    			}
	                break;

	            case "perfilActuaGenera":
	                ruta = "view/primero/Update.jsp";
	                if (sesion.getAttribute("perfil") == null) {
	    				ruta = "view/login.jsp";
	                    this.dirigir(request, response, ruta);
	    			} else {
	    				int idUsua = Integer.parseInt(String.valueOf(sesion.getAttribute("idUsuario")));
	    					combo.darListaTipoDocumento();
	    					usrDAO.primerSesUsu(idUsua);
	                    this.dirigir(request, response, ruta);
	    			}
	                break;
	            case "passGenera":
	                ruta = "view/primero/UpdateClv.jsp";
	                if (sesion.getAttribute("perfil") == null) {
	    				ruta = "view/login.jsp";
	                    this.dirigir(request, response, ruta);
	    			} else {
	    				
	    				usrDAO.inPerfilSeleccionado(Integer.parseInt(String.valueOf(sesion.getAttribute("perfil"))), Integer.parseInt(String.valueOf(sesion.getAttribute("idUsuario"))));
	    				int idP = Integer.parseInt(String.valueOf(usrDAO.getSelPerfil().getId_perfil()));
	    				sesion.setAttribute("perfil", idP);
	                    this.dirigir(request, response, ruta);
	    			}
	                break;
	            case "residuoLeeGenera":	
	                ruta="view/generador/residuos/ReadKn.jsp";
	                if (sesion.getAttribute("perfil") == null) {
	    				ruta = "view/login.jsp";
	                    this.dirigir(request, response, ruta);
	    			} else {
	                    this.listarResiduosGene(request, response, ruta);
	    			}
	                break;
	            case "residuoCreaGenera":
	                ruta = "view/generador/residuos/Create.jsp";
	                if (sesion.getAttribute("perfil") == null) {
	    				ruta = "view/login.jsp";
	                    this.dirigir(request, response, ruta);
	    			} else {
	    				int idSed = Integer.parseInt(String.valueOf(sesion.getAttribute("iSede")));
	    				resDAO.listarResiduo(idSed);
	                    this.dirigir(request, response, ruta);
	    			}
	                break;
	            case "residuoActuaGenera":
//	                request.setAttribute("idresiduo", request.getParameter("id"));
	                ruta = "view/generador/residuos/Update.jsp";
//	                resDAO.findResiduo(Integer.parseInt(String.valueOf(request.getParameter("id"))));
	                if (sesion.getAttribute("perfil") == null) {
	    				ruta = "view/login.jsp";
	                    this.dirigir(request, response, ruta);
	    			} else {
	    				
	                    this.dirigir(request, response, ruta);
	    			}
	                break;
	            case "residuoActuaGeneraCarg":
	                request.setAttribute("idresiduo", request.getParameter("id"));
	                ruta = "view/generador/residuos/Update.jsp";
	                Residuos res = resDAO.findResiduo(Integer.parseInt(String.valueOf(request.getParameter("id"))));
	                out.write(res.toString());	
	                break;    
	            case "updateResiduo":
	                request.setAttribute("idresiduo", request.getParameter("id_res"));
	                ruta = "view/generador/residuos/Read.jsp";
	                if (sesion.getAttribute("perfil") == null) {
	    				ruta = "view/login.jsp";
	                    this.dirigir(request, response, ruta);
	    			} else {
	    				
	                    this.updateResiduo(request, response, ruta);
	    			}
	                break;
	            case "eliminarResiGenerador":
	                ruta="view/generador/residuos/Read.jsp";
	                if (sesion.getAttribute("perfil") == null) {
	    				ruta = "view/login.jsp";
	                    this.dirigir(request, response, ruta);
	    			} else {
	    				
		                idGen = Integer.parseInt(String.valueOf(sesion.getAttribute("idSede")));
		                int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
		                Residuos resi = em.find(Residuos.class, id);
		                em.remove(resi);
		                combo.listaResiduo();
		                this.listarResiduosGene(request, response, ruta);
	    			}

	                break;
	            case "declaraResiduoGenera":
	                ruta = "view/generador/declaracion/Create.jsp";
	                if (sesion.getAttribute("perfil") == null) {
	    				ruta = "view/login.jsp";
	                    this.dirigir(request, response, ruta);
	    			} else {
	    				
	    				int idProg = Integer.parseInt(request.getParameter("txtProg"));
		                decDAO.darListaDeclaProg(idProg);
		                this.dirigir(request, response, ruta);
	    			}
	                
	                break;
	                
	            case "Registrar": 
	            	ruta = "controlGenerador?action=residuoLeeGenera";
	                if (sesion.getAttribute("perfil") == null) {
	    				ruta = "view/login.jsp";
	                    this.dirigir(request, response, ruta);
	    			} else {
	    				
	                    this.registraResiduo(request, response, ruta);
	    			}            
	                break;
	            case "residuoListaSTGGenera":
	                System.out.println("en control generador STG");
	                String codSTG = request.getParameter("codiSTG");
	                List<TipoManejo> tmaenjo = combo.listaSubGestionP(codSTG);
	                StringBuilder sb = new StringBuilder("");
	                for (TipoManejo tipoManejo : tmaenjo) {
	                	System.out.println(tipoManejo);
	                	sb.append(tipoManejo.getTma_id()+"-"+tipoManejo.getTma_nombre()+"|");
					}
	                out.write(sb.toString());
	                break;
	                
	            case "listaGestorProg":
	            	int idTra = Integer.parseInt(String.valueOf(request.getParameter("codiTrans")));
	            	 idGen = (int) request.getSession().getAttribute("idUsuario");
	            	List<Sede> sedesP = combo.listaGeneraTrPr(idTra, idGen );
	            	StringBuilder gn = new StringBuilder("");
	            	for (Sede sede : sedesP) {
	            		System.out.println(sede.toString());
	            		System.out.println(sede.getSed_nombre());
	            		gn.append(sede.getSed_id()+"-"+sede.getEmpresaSed().getEmp_nombre_comercial()+ " :: " +sede.getSed_nombre()+" :: "+sede.getSed_direccion() +"|");
					}
	            	out.write(gn.toString());
	            	break;
	            case "listaResiGenTran":
	            	log.info(String.valueOf(request.getParameter("parameter"))); 
	            	log.info(String.valueOf(request.getParameter("idGes")));
	            	log.info(String.valueOf(request.getParameter("idTre")));
	            	int idGes = Integer.parseInt(String.valueOf(request.getParameter("idGes")));
	            	int idTrans = Integer.parseInt(String.valueOf(request.getParameter("idTre")));
	            	List<Residuos> residuos = combo.listaResiduosGenTran(idGes, idTrans);
	            	StringBuilder gs = new StringBuilder("");
	            	for (Residuos resid : residuos) {
	            		System.out.println(resid.toString());
	            		gs.append(resid.getRes_id()+"-"+resid.getRes_nombre()+"|");
					}
	            	out.write(gs.toString());
	            	
	            break;
	            case "sedeListTranspGenera":
	                System.out.println("en control generador STG");
	                String codEm = request.getParameter("codEm");
	                List<Sede> tSede = combo.verListaSedeTrans(codEm);
	                StringBuilder st = new StringBuilder("");	                
	                for (Sede sedes : tSede) {
	                	System.out.println(sedes);
	                	st.append(sedes.getSed_id()+"-"+sedes.getSed_nombre()+"|");
					}
	                out.write(st.toString());
	                break;
	            case "sedeListGestorGenera":
	                System.out.println("en control generador STG");
	                String codEmG = request.getParameter("codEmG");
	                List<Sede> gSede = combo.verListaSedeGes(codEmG);
	                StringBuilder stu = new StringBuilder("");	                
	                for (Sede sedes : gSede) {
	                	System.out.println(sedes);
	                	stu.append(sedes.getSed_id()+"-"+sedes.getSed_nombre()+"|");
					}
	                out.write(stu.toString());
	                break;
	            case "programaListaGenera":
	                ruta = "view/generador/programacion/List.jsp";
	                if (sesion.getAttribute("perfil") == null) {
	    				ruta = "view/login.jsp";
	                    this.dirigir(request, response, ruta);
	    			} else {
	    				
	                    this.programaListaGenera(request, response, ruta);
	    			}
	                break;
	            case "programaCreaGenera":
	                ruta = "view/generador/programacion/Create.jsp";
	                if (sesion.getAttribute("perfil") == null) {
	    				ruta = "view/login.jsp";
	                    this.dirigir(request, response, ruta);
	    			} else {
	    				
		    	        int idSed = (int) request.getSession().getAttribute("idSede");
		    	        System.out.println("generador "+ idSed);
		    	        resDAO.listarResiduoGeneradorId(idSed);
		    	        int idGess = (int) request.getSession().getAttribute("idUsuario");
		    	        combo.sedesTransProg(idGess);
		                this.dirigir(request, response, ruta);
		                
	    			}

	                break;
	            case "programaRGuardaGenera":
	            	ruta = "view/generador/programacion/List.jsp";
	                if (sesion.getAttribute("perfil") == null) {
	    				ruta = "view/login.jsp";
	                    this.dirigir(request, response, ruta);
	    			} else {
	    				
	                    this.creaProgDecDecRes(request, response, ruta);
	    			}	            
	                break;
	            case "enviosBuscarhoy":	                
	                ruta = "view/generador/declaracion/List.jsp";
	                if (sesion.getAttribute("perfil") == null) {
	    				ruta = "view/login.jsp";
	                    this.dirigir(request, response, ruta);
	    			} else {
	    				int idSed = Integer.parseInt(String.valueOf(sesion.getAttribute("idSede")));
	    				decDao.declaCercana(idSed);
	                    this.dirigir(request, response, ruta);
	    			}	                
	                break;
	            case "enviosBuscarFch":	                
	                ruta = "view/generador/declaracion/List.jsp";
	                if (sesion.getAttribute("perfil") == null) {
	    				ruta = "view/login.jsp";
	                    this.dirigir(request, response, ruta);
	    			} else {
	    				int idSed = Integer.parseInt(String.valueOf(sesion.getAttribute("idSede")));
	    				String fIni = request.getParameter("txtFIni");
	    				String fFin = request.getParameter("txtFFin");
	    				decDao.declaEntre(fIni, fFin, idSed);
	                    this.dirigir(request, response, ruta);
	    			}	                
	                break;
	            case "CrearDeclaResiduo":	
	                if (sesion.getAttribute("perfil") == null) {
	    				ruta = "view/login.jsp";
	                    this.dirigir(request, response, ruta);
	    			} else {
	    	        	ruta = "view/generador/declaracion/List.jsp";
	                    this.crearDeclaResiduos(request, response, ruta);
	    			}
	                
	                break;
	            case "reporteMGenera":	
	                if (sesion.getAttribute("perfil") == null) {
	    				ruta = "view/login.jsp";
	                    this.dirigir(request, response, ruta);
	    			} else {
	    	        	ruta = "view/generador/generacionMensual/List.jsp";
	                    this.reporteMGenera(request, response, ruta);
	    			}
	                
	                break;
	            case "historicoListaGenera":
	                ruta = "view/generador/generacion/Read.jsp";
	                resDAO.setTitulo("sdstrp - Declaracion");
//	                int idS = Integer.parseInt(String.valueOf(sesion.getAttribute("idSede")));
//	                int idP = Integer.parseInt(String.valueOf(sesion.getAttribute("perfil")));
//	                decDao.prebaDeclaRes(idS, idP);
	                this.dirigir(request, response, ruta);
	                break;
	            case "salidaListaGenera":
	                ruta = "view/generador/salida/List.jsp";
	                if (sesion.getAttribute("perfil") == null) {
	    				ruta = "view/login.jsp";
	                    this.dirigir(request, response, ruta);
	    			} else {
		                int idSe = Integer.parseInt(String.valueOf(sesion.getAttribute("idSede")));
		                int idPe = Integer.parseInt(String.valueOf(sesion.getAttribute("perfil")));
		                decDao.SelectDeclHistorial(idSe, idPe);
		                this.dirigir(request, response, ruta);
	    			}

	                break;
   
	            
	            }
	            
	            
	            
	    }
	   


		private void ordenarDash(HttpSession sesion) {
			// TODO Auto-generated method stub
			List<Tuple> dshi = dasDao.getConDash();
			log(dshi.size()+"");
			log(dshi+"");
			if(dshi.toString().equals("[]"))
			{
				sesion.setAttribute("dashEnviadas", 	0);
				sesion.setAttribute("dashNoEnviadas", 	0);
				sesion.setAttribute("dashAprovadas", 	0);
				sesion.setAttribute("dashRechasadas", 	0);
				sesion.setAttribute("dashFinalizadas",  0);
				sesion.setAttribute("dashRechaGes", 	0);
				sesion.setAttribute("dashPrograma", 	0);
				
			}
			else
			{
				for (Tuple dsh : dshi) {
					sesion.setAttribute("dashEnviadas", dsh.get(0)==null ? 0 : dsh.get(0));
					sesion.setAttribute("dashNoEnviadas", dsh.get(1)==null ? 0 : dsh.get(1));
					sesion.setAttribute("dashAprovadas", dsh.get(2)==null ? 0 : dsh.get(2));
					sesion.setAttribute("dashRechasadas", dsh.get(3)==null ? 0 : dsh.get(3));
					sesion.setAttribute("dashFinalizadas", dsh.get(4)==null ? 0 : dsh.get(4));
					sesion.setAttribute("dashRechaGes", dsh.get(5)==null ? 0 : dsh.get(5));
					sesion.setAttribute("dashPrograma", dsh.get(6)==null ? 0 : dsh.get(6));			
				}

			}
			
		}



		private void reporteMGenera(HttpServletRequest request, HttpServletResponse response, String ruta) throws ServletException, IOException {
			// TODO Auto-generated method stub
			HttpSession sesion = request.getSession();
//			int idSed = Integer.parseInt(String.valueOf(sesion.getAttribute("idSede")));
//			decDao.reporteMes(idSed);
			dis = request.getRequestDispatcher(ruta);
	        dis.forward(request, response);
			
		}

		private void crearDeclaResiduos(HttpServletRequest request, HttpServletResponse response, String ruta) throws Exception {
			
			// TODO Auto-generated method stub
			HttpSession sesion = request.getSession();
			try {
				
			
			
        	String[] tipoEmbalaje = request.getParameterValues("tipEmbalaje[]");
        	String[] idsDeclaRes = request.getParameterValues("idDeclaRes[]");
        	String[] cantEmb = request.getParameterValues("txtCantEmb[]"); 
        	String[] tipEmpa = request.getParameterValues("tipEmpaque[]");
        	String[] cantEmpq = request.getParameterValues("txtCantEmpq[]");
        	String[] cantPeso = request.getParameterValues("txtCantPeso[]");
        	
        	
        	
        	log.info(tipoEmbalaje.length+" / "+idsDeclaRes.length);
        	
        	
        	
        	for (int i=0; i<idsDeclaRes.length;i++)
        	{
        		log.info(i+"");
        		log.info(idsDeclaRes[i]);
        		log.info(tipoEmbalaje[i]);
        		log.info(cantEmb[i]);
        		log.info(tipEmpa[i]);
        		log.info(cantEmpq[i]);
        		log.info(cantPeso[i]);
        		DeclaracionResiduo decRes = em.find(DeclaracionResiduo.class, Integer.parseInt(idsDeclaRes[i].trim()));
        		decRes.setDer_gen_tipo_embalaje(String.valueOf(tipoEmbalaje[i]));
        		decRes.setDer_gen_numero_embalajes(Integer.parseInt(cantEmb[i]));
        		decRes.setDer_gen_tipo_empaque(tipEmpa[i]);
        		decRes.setDer_gen_numero_empaques(Integer.parseInt(cantEmpq[i]));
        		decRes.setDer_gen_peso_residuo(Integer.parseInt(cantPeso[i]));
        		log.info(decRes.getDer_declaracion()+"");
        		Declaracion dcla = em.find(Declaracion.class, Integer.valueOf(decRes.getDer_declaracion()));
        		dcla.setDec_gen_fecha_trn(new Date());
        		dcla.setDec_gen_aprobada("A");
        		decDao.updateDecla_DeclaRes(dcla,decRes);
        		
        	}
			} catch (Exception e) {
				// TODO: handle exception
				 System.out.println("Fallo lista Declaraciones por: " + e.getMessage());
		            e.fillInStackTrace();
			}
			ruta = "view/generador/generador.jsp";
        	int idSed = Integer.parseInt(String.valueOf(sesion.getAttribute("idSede")));
        	decDao.declaCercana(idSed);
            this.dirigir(request, response, ruta);
			
		}
		
		private void creaProgDecDecRes(HttpServletRequest request, HttpServletResponse response, String ruta) throws ServletException, IOException {
			// TODO Auto-generated method stub
			SimpleDateFormat formato = new SimpleDateFormat("dd-mm-yy");
    		StringBuilder errorMessage = new StringBuilder();
    		Date fecha;
        	try {
        		
            	String value;
            	log.info(request.getParameter("txtPrueba[]"));
            	String[] items = request.getParameterValues("txtPrueba[]");
            	log.info(items.length+"");
//            	int residuoId = Integer.parseInt(request.getParameter("txtResi"));
//            	log.info(residuoId+"");
            	int idG = Integer.parseInt(String.valueOf(request.getSession().getAttribute("idUsuario")));
            	int idS =  (int) request.getSession().getAttribute("idSede");
//        		Residuos res = em.find(Residuos.class,Integer.valueOf(residuoId));	                    	
//            	Trabajando las fechas
				// el que parsea
            	SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
            	// el que formatea
            	SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cini = Calendar.getInstance();
                Calendar cinf = Calendar.getInstance();
            	switch (request.getParameter("txtRep")) {
				case "1":
					log.info("repeticion = 1");
                	log.info("FECHA ACTUAL");
                	log.info(request.getParameter("txtFactual"));
                	String fecha1 = request.getParameter("txtFactual");
                	fecha = parseador.parse(fecha1);
                	log.info(""+fecha);
                	log.info(formateador.format(fecha));
                	fecha = parseador.parse(fecha1);
                	log.info(""+fecha);
                	dates.add(fecha);
					break;
				case "2":
					log.info("repeticion = 2");
					cini.setTime(formateador.parse(request.getParameter("txtFactual")));
					cinf.setTime(formateador.parse(request.getParameter("txtFfinal")));
					log.info(""+cini);
					log.info(""+cinf);
					int i =0;
					 while (cinf.after(cini) || cinf.equals(cini))
					 {
						 log.info(""+cini.getTime());
						 dates.add(cini.getTime());
						 log.info(dates.get(i)+" Array");
						 cini.add(Calendar.DATE, 1);
						 log.info(""+cini.getTime());
						 i++;
					 }
					break;
				case "3":
					
					log.info("repeticion = 3");
					log.info("repeticion = 2");
					cini.setTime(formateador.parse(request.getParameter("txtFactual")));
					cinf.setTime(formateador.parse(request.getParameter("txtFfinal")));
					log.info(""+cini);
					log.info(""+cinf);
					 while (cinf.after(cini) || cinf.equals(cini))
					 {
// 				-----------------------------------------------------------	
						 log.info(""+cini.get(Calendar.DAY_OF_WEEK));
						 log.info(request.getParameter("btnLunes"));
						 log.info(cini.get(Calendar.DAY_OF_WEEK)+"   Dia de la semana de fecha incial");
						 log.info(Calendar.MONDAY+"   Dia lunes");
						 String lunes = request.getParameter("btnLunes");
						 log.info(lunes);
						 if(lunes!=null)
							{
							 log.info("hoy es lunes");
							}
			                if (cini.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY && request.getParameter("btnLunes")!=null) {
			                    log.info("---------->" + cini.getTime().toString() + " es Lunes ");
			                    dates.add(cini.getTime());
			                }
			                else if(cini.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY && request.getParameter("btnMartes")!=null)
			                {
			                	log.info("---------->" + cini.getTime().toString() + " es Martes "); 
			                	dates.add(cini.getTime());
			                }
			                else if(cini.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY && request.getParameter("btnMiercoles")!=null)
			                {
			                	log.info("---------->" + cini.getTime().toString() + " es Miercoles ");
			  
			                }
			                else if(cini.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY && request.getParameter("btnJueves")!=null)
			                {
			                	log.info("---------->" + cini.getTime().toString() + " es Jueves ");
			                	dates.add(cini.getTime());
			                }
			                else if(cini.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && request.getParameter("btnViernes")!=null)
			                {
			                	log.info("---------->" + cini.getTime().toString() + " es Viernes ");
			                	
			                	dates.add(cini.getTime());
			                }
			                else if(cini.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY && request.getParameter("btnSabado")!=null)
			                {
			                	log.info("---------->" + cini.getTime().toString() + " es Sabado ");
			                	dates.add(cini.getTime());
			                }
			                else if(cini.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && request.getParameter("btnDomingo")!=null)
			                {
			                	log.info("---------->" + cini.getTime().toString() + " es domingo ");
			                	dates.add(cini.getTime());
			                }
			                cini.add(Calendar.DATE, 1);
						 
//					------------------------------------------------------------
					 }
					break;
				case "4":
					cini.setTime(formateador.parse(request.getParameter("txtFactual")));
					cinf.setTime(formateador.parse(request.getParameter("txtFfinal")));
					log.info(""+cini);
					log.info(""+cinf);
					int mesi  = cini.get(Calendar.MONTH);
					int mesf = cinf.get(Calendar.MONTH);
					int j =0;
					 while ((cinf.after(cini) || cinf.equals(cini))&& mesi<=mesf)
					 {
						 log.info(""+cini.getTime());
						 dates.add(cini.getTime());
						 log.info(dates.get(j)+" Array");
						 cini.add(Calendar.MONTH, 1);
						 mesi = cini.get(Calendar.MONTH);
						 log.info(""+mesi);
						 log.info(""+cini.getTime());
						 j++;
					 }
					break;
				case "5":
					log.info("repeticion = 5");
					cini.setTime(formateador.parse(request.getParameter("txtFactual")));
					cinf.setTime(formateador.parse(request.getParameter("txtFfinal")));
					log.info(""+cini);
					log.info(""+cinf);
					int ani  = cini.get(Calendar.YEAR);
					int anf = cinf.get(Calendar.YEAR);
					int k =0;
					 while ((cinf.after(cini) || cinf.equals(cini))&& ani<=anf)
					 {
						 log.info(""+cini.getTime());
						 dates.add(cini.getTime());
						 log.info(dates.get(k)+" Array");
						 cini.add(Calendar.YEAR, 1);
						 mesi = cini.get(Calendar.YEAR);
						 log.info(""+mesi);
						 log.info(""+cini.getTime());
						 k++;
					 }
					break;
				}
            	log.info(""+dates.size());
            	Residuos ResUni = null;
            	int idTrEm = 0;
            	int idGenEmp = 0;
            	for (Date fech : dates) {
            		log.info(dates.size()+"");
            		log.info(fech+"");
                	while ((prog = progDao.getNewProg())==null) 
                	{
            			progDao.initNewProg();
            		}
                	prog.setPro_generador(idS);
                	log.info(""+fech);
                	log.info(""+request.getParameter("txtFfinal"));
                	prog.setPro_fecha_inicial(fech);
                	if(!request.getParameter("txtFfinal").equals(""))
                	{
                    	prog.setPro_fecha_final(formateador.parse(request.getParameter("txtFfinal")));
                	}
                	log.info("s"+prog.getPro_fecha_inicial());
                	//campo transportador
                	log.info(request.getParameter("localiza"));
                	log.info(request.getParameter("btnInterna"));
                	String p = request.getParameter("btnInterna");
                	if(request.getParameter("localiza")!="btnResi")
                	{
                		ResUni = em.find(Residuos.class, Integer.parseInt(String.valueOf(items[0])));
                		prog.setPro_transportador(ResUni.getRes_sede_transporte());
                		prog.setPro_gestor(ResUni.getRes_sede_gestor());
                		
//                		prog.setPro_transportador(res);
                	}
                	else
                	{
                		idTrEm = Integer.valueOf(String.valueOf(request.getParameter("chekTrans")));
                		prog.setPro_transportador(idTrEm);
                		idGenEmp = Integer.valueOf(String.valueOf(request.getParameter("txtGes")));
                    	prog.setPro_gestor(idGenEmp);
                	}
                			                  
                	
                	//Campo gestor
                               	
                    log.info("\n*****************Try Registration of programa=" + prog.getPro_fecha_final());
                    log.info(prog.toString());
                    progDao.registerProgramacion();
                    log.info("//////////////////////////////PROGRAMA agregado Correctamente");
                    log.info("////// "+ prog.getPro_fecha_inicial());		  
                    
                	while ((decl = decDao.getNewDecla())==null) 
                	{
            			decDao.initNewDecla();
            		}
                	
                	if(request.getParameter("btnInterna")!=null)
                	{
                		decl.setDec_gestor(ResUni.getRes_sede_gestor());
                		decl.setDec_transportador(ResUni.getRes_sede_transporte());
                	}
                	else
                	{

                    		decl.setDec_transportador(ResUni.getRes_sede_transporte());
                    		decl.setDec_gestor(ResUni.getRes_sede_gestor());
                    		
//                    		prog.setPro_transportador(res);

                		

                	}
                	decl.setDec_gen_fecha_gen(fech);
                	log.info("Fecha de gnenracion de la declaracion "+decl.getDec_gen_fecha_gen());
                	decl.setDec_generador(idS);
                	
//                	log.info(sedeTr.getSed_id()+"");
                	
                	decl.setDec_prog_id(prog.getPro_id());
                	decl.setDec_generador(idS);
                	decl.setDec_trn_aprobada("N");
                	decl.setDec_ges_aprobada("N");
//                	decl.setDec_generador(idS);
                	decl.setDec_gen_aprobada("N");
                	log.info(decl.toString());
                	decDao.registrarDeclaracion();
                	
                	for (String item : items) {
						log.info(item);
					Residuos res = em.find(Residuos.class, Integer.parseInt(item));
                	decaRes = new DeclaracionResiduo();
                	while ((decaRes = decDao.getNewDeRes())==null) 
                	{
            			decDao.initNewDecRes();
            		}
                	decaRes.setDer_declaracion(decl.getDec_id());
                	
                	decaRes.setDer_gen_estado_materia(res.getRes_estado_materia());
                	decaRes.setDer_gen_nombre(res.getRes_nombre());
                	decaRes.setDer_res_id(res.getRes_id());
                	decaRes.setDer_gen_tipo_embalaje(res.getRes_tipo_embalaje());
                	decaRes.setDer_gen_tipo_empaque(res.getRes_tipo_empaque());
                	decaRes.setDer_gen_tipo_manejo(res.getRes_sub_tip_gestion());
                	decaRes.setDer_gen_tipo_peligrosidad(res.getRes_peligrosidad());
                	decaRes.setDer_gen_tipo_residuo(res.getRes_tipo_residuo());
//                	decaRes.setDer_id(0);
                	log.info(""+decaRes.toString());
                	decDao.registrarDeclaracioRes();
                	request.setAttribute("infoMessage ", res.getRes_nombre() + " Programado correctamente!");
    		    	String idGs = String.valueOf(request.getSession().getAttribute("idUsuario"));
    		        int idGen = Integer.parseInt(idGs); // variable de usuario session
    		        System.out.println("generador "+ idGen);
    		        int idSed = (int) request.getSession().getAttribute("idSede");
    		        System.out.println("generador "+ idSed);
    		        resDAO.listarResiduoGeneradorId(idSed);
    		        combo.sedesTransProg(idSed);
    		        progDao.programacionResiduios(idSed);
				}
            	}
            		dates.removeAll(dates);
    		} catch (Exception e) {
    			// TODO: handle exception
    			System.out.println("Fallo lista Programacion: " + e.getMessage());
	            e.fillInStackTrace();
    			dates.removeAll(dates);
                Throwable t = e;
                while ((t.getCause()) != null) {
                    t = t.getCause();
                    log.info(""+t);
                }
    		} finally {
    			ruta = "controlGenerador?action=programaListaGenera";
    			log.info(errorMessage.toString());
                request.setAttribute("errorMessage", errorMessage.toString());
                dis = request.getRequestDispatcher(ruta);
    	        dis.forward(request, response);		
    		}

			
		}






		private void listarResiduosGene(HttpServletRequest request, HttpServletResponse response, String ruta) throws ServletException, IOException {
			// TODO Auto-generated method stub
			resDAO.setTitulo("sdstrp - Residuo");
			log.info("Sede es ="+request.getSession().getAttribute("idSede"));
	        int rs =  (int) request.getSession().getAttribute("idSede"); // llamar la variable de session de Sede
	        log.info("Servicio listar gestor: "+ rs);
	        resDAO.listarResiduoGeneradorId(rs);
	        dis = request.getRequestDispatcher(ruta);
	        dis.forward(request, response);
			
		}
		

		private void updateResiduo(HttpServletRequest request, HttpServletResponse response, String ruta) throws Exception {
			// TODO Auto-generated method stub
		Residuos resiUp = em.find(Residuos.class, Integer.parseInt(String.valueOf(request.getParameter("id_res"))));
			
		resiUp.setRes_nombre(request.getParameter("txtDescript"));
		resiUp.setRes_tipo_residuo(request.getParameter("tipResiduo"));
		resiUp.setRes_estado_materia(request.getParameter("tipMateria"));
		resiUp.setRes_gestion_ubica(request.getParameter("gestionUbica"));
		resiUp.setRes_tipo_gestion(request.getParameter("tipGestion"));
    	System.out.println(request.getParameter("txtDescript"));
    	resiUp.setRes_nombre(request.getParameter("txtDescript"));
    	System.out.println(request.getParameter("tipResiduo"));
    	resiUp.setRes_tipo_residuo(request.getParameter("tipResiduo"));
    	System.out.println(request.getParameter("tipMateria"));
    	resiUp.setRes_estado_materia(request.getParameter("tipMateria"));
    	System.out.println(request.getParameter("gestionUbica"));
    	resiUp.setRes_gestion_ubica(request.getParameter("gestionUbica"));
    	System.out.println(request.getParameter("tipEmpa"));
    	resiUp.setRes_tipo_empaque(request.getParameter("tipEmpa"));
    	System.out.println(request.getParameter("tipEmbalaje"));
    	resiUp.setRes_tipo_embalaje(request.getParameter("tipEmbalaje"));
    	System.out.println(request.getParameter("tipGestion"));
    	resiUp.setRes_tipo_gestion(request.getParameter("tipGestion"));
    	System.out.println(request.getParameter("tipPeligro"));
    	resiUp.setRes_peligrosidad(request.getParameter("tipPeligro"));
    	System.out.println(request.getParameter("subTipGest"));
    	resiUp.setRes_sub_tip_gestion(request.getParameter("subTipGest"));
    	System.out.println("sede Transportador"+request.getParameter("sedeTransp"));   	
    	resiUp.setRes_sede_transporte(Integer.valueOf(request.getParameter("sedeTransp")));
    	System.out.println("Sede del gestor"+request.getParameter("sedeGestor"));
    	resiUp.setRes_sede_gestor(Integer.valueOf(request.getParameter("sedeGestor")));
    	int rs =  (int) request.getSession().getAttribute("idSede");
    	resiUp.setRes_sede_generador(rs);
			resDAO.UpdateResiduo(resiUp);
        	int sed =  (int) request.getSession().getAttribute("idSede");
            resDAO.listarResiduoGeneradorId(sed);
            RequestDispatcher resultView = request.getRequestDispatcher(ruta);
            resultView.forward(request, response);
		}


		private void registraResiduo(HttpServletRequest request, HttpServletResponse response, String ruta) throws ServletException, IOException {
			// TODO Auto-generated method stub
	    	StringBuilder errorMessage = new StringBuilder();
	    	try {
	    		
	        	String value;
	        	Residuos residuo;
	        	while ((residuo = resDAO.getNewResiGest())==null) 
	        	{
	    			resDAO.initNewResiduo();
	    			
	    		}
	        	System.out.println(request.getParameter("txtDescript"));
	        	residuo.setRes_nombre(request.getParameter("txtDescript"));
	        	System.out.println(request.getParameter("tipResiduo"));
	        	residuo.setRes_tipo_residuo(request.getParameter("tipResiduo"));
	        	System.out.println(request.getParameter("tipMateria"));
	        	residuo.setRes_estado_materia(request.getParameter("tipMateria"));
	        	System.out.println(request.getParameter("gestionUbica"));
	        	residuo.setRes_gestion_ubica(request.getParameter("gestionUbica"));
	        	System.out.println(request.getParameter("tipEmpa"));
	        	residuo.setRes_tipo_empaque(request.getParameter("tipEmpa"));
	        	System.out.println(request.getParameter("tipEmbalaje"));
	        	residuo.setRes_tipo_embalaje(request.getParameter("tipEmbalaje"));
	        	System.out.println(request.getParameter("tipGestion"));
	        	residuo.setRes_tipo_gestion(request.getParameter("tipGestion"));
	        	System.out.println(request.getParameter("tipPeligro"));
	        	residuo.setRes_peligrosidad(request.getParameter("tipPeligro"));
	        	System.out.println(request.getParameter("subTipGest"));
	        	residuo.setRes_sub_tip_gestion(request.getParameter("subTipGest"));
	        	System.out.println("sede Transportador"+request.getParameter("sedeTransp"));   	
	        	residuo.setRes_sede_transporte(Integer.valueOf(request.getParameter("sedeTransp")));
	        	System.out.println("Sede del gestor"+request.getParameter("sedeGestor"));
	        	residuo.setRes_sede_gestor(Integer.valueOf(request.getParameter("sedeGestor")));
	        	int rs =  (int) request.getSession().getAttribute("idSede");
	        	residuo.setRes_sede_generador(rs);
	            log("\n*****************Try Registration of Residuio = " + residuo);
	            resDAO.registerResiduo();
	            resDAO.listarResiduoGeneradorId(rs);
	            request.setAttribute("infoMessage", residuo.getRes_id()+ " Registrado correctamente!");

			
			} catch (Exception e) {
				// TODO: handle exception
	            Throwable t = e;
	            while ((t.getCause()) != null) {
	                t = t.getCause();
	            }
			} finally {
				
	            request.setAttribute("errorMessage", errorMessage.toString());
	            RequestDispatcher resultView = request.getRequestDispatcher(ruta);
	            resultView.forward(request, response);		
			}
			
		}


		//Dirije a la pantalla solicitada por el ususario
	    protected void dirigir(HttpServletRequest request, HttpServletResponse response, String ruta)
	            throws ServletException, IOException {
	        dis = request.getRequestDispatcher(ruta);
	        dis.forward(request, response);
	    }
	    
	    //Pantalla de Lista de Programaciones
	    protected void programaListaGenera(HttpServletRequest request, HttpServletResponse response, String ruta)
	            throws ServletException, IOException{
	        
	    	String idG = String.valueOf(request.getSession().getAttribute("idUsuario"));
	        int idGen = Integer.parseInt(idG); // variable de usuario session
	        System.out.println("generador "+ idGen);
	        int idSed = (int) request.getSession().getAttribute("idSede");
	        System.out.println("generador "+ idSed);
	        resDAO.listarResiduoGeneradorId(idSed);
	        combo.sedesTransProg(idSed);
	        progDao.programacionResiduios(idSed);

	        dis = request.getRequestDispatcher(ruta);
	        dis.forward(request, response);
	    }
	    
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
	        
	        try {
	            processRequest(request, response);
	        } catch (Exception ex) {
	            Logger.getLogger(control.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        
	    }
	    
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	              
	        try {
	            processRequest(request, response);
	        } catch (Exception ex) {
	            Logger.getLogger(control.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
}
