package co.gov.ideam.sdstrp.servlet;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import co.gov.ideam.sdstrp.controller.AutorizacionesDAO;
import co.gov.ideam.sdstrp.controller.ConsultaComboDAO;
import co.gov.ideam.sdstrp.controller.DashDao;
import co.gov.ideam.sdstrp.controller.DeclaracionDAO;
import co.gov.ideam.sdstrp.controller.ResiduoDAO;
import co.gov.ideam.sdstrp.controller.UserDao;
import co.gov.ideam.sdstrp.model.Autorizaciones;
import co.gov.ideam.sdstrp.model.Declaracion;
import co.gov.ideam.sdstrp.model.DeclaracionResiduo;
import co.gov.ideam.sdstrp.model.Residuos;
import co.gov.ideam.sdstrp.model.ServicioGestor;
import co.gov.ideam.sdstrp.model.TipoEmbalaje;
import co.gov.ideam.sdstrp.model.TipoEmpaque;
import co.gov.ideam.sdstrp.model.TipoGestion;
import co.gov.ideam.sdstrp.model.TipoResiduos;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Islena
 */
@WebServlet(name = "ControlGestor", urlPatterns = {"/ControlGestor"})
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class ControlGestor extends HttpServlet {
    
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Inject
    private Logger log;
//	@Inject
//	private ComboServices comboServices;
    @Inject
    private EntityManager em;
    
    @Inject
    private UserDao usrDao;
	@Inject
	private AutorizacionesDAO autoDAO;
	@Inject
	private ConsultaComboDAO comboDAO;
	
	@Inject
	private DashDao dasDao;
	
	@Inject
	 private DeclaracionDAO declaDAO;
	@Inject
	 private ResiduoDAO resDAO; 
	
	
	 private Autorizaciones auto;
	 private Residuos resi;
	 private Declaracion finDecla; 
	 private List<Tuple> listResiGestor;

	 
//    declaracion decla = new declaracion();
//    declaracionDAO declaDAO = new declaracionDAO();
//    //LLAMA INFORMACION PARA LLENAR LOS COMBO SELECT SEGUN PAGINA QUE SE REQUIERA
//    comboServices comboServices = new comboServices();
//    consultaComboDAO comboDAO = new consultaComboDAO();
//    residuo resi = new residuo();
//    residuoDAO resDAO = new residuoDAO();
//    autorizacionesDAO autoDAO = new autorizacionesDAO();
//    autorizaciones auto = new autorizaciones();
    
   
    
    private RequestDispatcher dis;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");

        String ruta = "";
        String action = request.getParameter("action");
        log.info(action);
		HttpSession sesion = request.getSession();
        switch (action) {

            case "gestor":
                /* Este es el Menu inicio interno Gestor*/
                ruta = "view/gestor/gestor.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
    				int idSedeT = (int) sesion.getAttribute("idSede");
    				String perfT = "gestor";
    				dasDao.declaracionesDeTrn(idSedeT,"transportador");
    				this.ordenarDash(sesion);	
    	               this.gestor(request, response, ruta);
    			}
 
                break;
            case "empresaGestor":
                /* Este es el Menu Lista Empresas asociadas */
                ruta = "view/gestor/empresa/List.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
    	               this.gestor(request, response, ruta);
    			}
                break;
            case "sedeGestor":
                /* Este es el Menu Lista Establecimientos Asociados*/
                ruta = "view/gestor/sede/List.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
    	               this.gestor(request, response, ruta);
    			}
                break;
            case "perfilCreaGestor":
                /* Este es el Menu Crea Perfil*/
                ruta = "view/gestor/persona/Create.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
    	               this.gestor(request, response, ruta);
    			}
                break;

            case "perfilLeerGestor":
                /* Este es el Menu Lee Perfil*/
                ruta = "view/gestor/persona/Read.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
    	               this.gestor(request, response, ruta);
    			}
                break;
            case "perfilUpdateGestor":
                /* Este es el Menu Actualiza Perfil*/
                ruta = "view/gestor/persona/Update.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
    	               this.gestor(request, response, ruta);
    			}
                break;
            case "servicioListaGestor":
                /* Este es el Menu Lista de servicio*/
                ruta = "view/gestor/serviciosPrestados/List.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
                    this.servicioListaGestor(request, response, ruta);
    			}

                break;
            case "servicioCreaGestor":
                /* Este es el Menu Crea Servicio*/
                ruta = "view/gestor/serviciosPrestados/Create.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
                    this.servicioCreaGestor(request, response, ruta);
    			}

                break;
            case "servicioActuaGestor":
                /* Este es el Menu Actualiza servicio*/
                ruta = "view/gestor/serviciosPrestados/Update.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
                    this.servicioActuaGestor(request, response, ruta);
    			}
                break;
            case "servicioEnvActuaGestor":
                /* Este es el Menu Actualiza servicio*/
                ruta = "ControlGestor?action=servicioListaGestor";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
                    this.servicioEnvActuaGestor(request, response, ruta);
    			}

                break;
            case "creaServicioGestor":
                /* Este es el Menu Actualiza servicio*/
                ruta = "ControlGestor?action=servicioListaGestor";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
                    this.creaServicioGestor(request, response, ruta);
    			}

                break;
            case "eliminaServicioGestor":
                /* Este es el Menu Actualiza servicio*/
                ruta = "ControlGestor?action=servicioListaGestor";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
                    this.eliminaServicioGestor(request, response, ruta);
    			}

                break;
                
            case "autorizaEliminaGestor":
                /* Este es el Menu Actualiza servicio*/
                ruta = "ControlGestor?action=autorizaListaGestor";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
                    this.autorizaEliminaGestor(request, response, ruta);
    			}

                break;
                
                
            case "guardaAutorizaGestor":
                /* Este es el Menu Lista de autorizacion*/
                ruta = "ControlGestor?action=autorizaListaGestor";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
    				this.guardaAutorizaGestor(request, response, ruta);
    			}
                
                break;
            case "autorizaCreaGestor":
                /* Este es el Menu Crea autorizacion*/
                ruta = "view/gestor/autorizaciones/Create.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
    				this.crearAutorizaGestor(request, response, ruta);
    			}
                
                break;
            case "declaGESAprobar":
            	ruta = "view/gestor/autorizaciones/List.jsp";
            	String[] check = request.getParameterValues("prCheck[]");
            	String[] tipoEmbalaje = request.getParameterValues("tipEmbalaje[]");
            	String[] idsDecla = request.getParameterValues("idDecla[]");
            	String[] idsDeclaRes = request.getParameterValues("idDeclaRes[]");
            	String[] cantEmb = request.getParameterValues("txtCantEmb[]"); 
            	String[] tipEmpa = request.getParameterValues("tipEmpaque[]");
            	String[] cantEmpq = request.getParameterValues("txtCantEmpq[]");
            	String[] cantPeso = request.getParameterValues("txtCantPeso[]");
            	SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
            	// el que formatea
            	SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
            	String fechaRes = request.getParameter("txtReco");
            	String fechEntr = request.getParameter("txtEntr");
            	Date fechRec = parseador.parse(fechaRes);
            	Date fechEnt = parseador.parse(fechEntr);
            	
            	log.info(tipoEmbalaje.length+" / "+idsDeclaRes.length);
            		int s=0;
            	for (int i=0; i<check.length;i++)
            	{
            		long cDeclas =  declaDAO.countDeclaRes(Integer.valueOf(String.valueOf(idsDecla[i])));
            		log.info(cDeclas+"");
        			log.info(check[i]+"");
        			log.info(i+"");
        			Boolean ban  = Boolean.valueOf(check[i]) ;
        			if(ban == true)
            			{
                        	for (int j=0; j<cDeclas;j++)
                        	{	
                        	
                        		DeclaracionResiduo decRes = em.find(DeclaracionResiduo.class, Integer.valueOf(String.valueOf(idsDeclaRes[s])));
                        		decRes.setDer_ges_tipo_embalaje2(String.valueOf(tipoEmbalaje[s]));
                        		decRes.setDer_ges_numero_embalajes2(Integer.parseInt(cantEmb[s]));
                        		decRes.setDer_ges_tipo_empaque2(tipEmpa[s]);
                        		
                        		decRes.setDer_ges_numero_empaques2(Integer.parseInt(cantEmpq[s]));
                        		decRes.setDer_ges_peso_residuo2(Integer.parseInt(cantPeso[s]));
                        		log.info(decRes.getDer_declaracion()+"");
                        		Declaracion dcla = em.find(Declaracion.class, Integer.valueOf(String.valueOf(decRes.getDer_declaracion())));
                        		dcla.setDec_trn_fecha_trn(fechRec);
                        		dcla.setDec_trn_fecha_ent(fechEnt);
                        		dcla.setDec_trn_aprobada("A");
                        		declaDAO.updateDecla_DeclaRes(dcla,decRes);
                        		s++;
                        	}
            			}
            			else {
            				s+=cDeclas;
            			}
        				
  
            		
            	}                
                dis = request.getRequestDispatcher(ruta);
                dis.forward(request, response);
            break;
            case "autorizaListaGestor":
                /* Este es el Menu Lista de autorizacion*/
                ruta = "view/gestor/autorizaciones/List.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
    				this.autorizaListaGestor(request, response, ruta);
    			}
                
                break;
                
            case "autorizaVerGestor":
                /* Este es el Menu Lista de autorizacion*/
                ruta = "view/gestor/autorizaciones/View.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
    				this.autorizaVerGestor(request, response, ruta);
    			}
                
                break;
            
            case "autorizaActuaGestor":
                /* Este es el Menu Actualiza autorizacion*/
                ruta = "view/gestor/autorizaciones/Update.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
                    this.autorizaActuaGestor(request, response, ruta);
    			}

                break;
   
            case "editaAutorizaGestor":
                /* despues de editar la autorizacion regresa a la lista*/
                ruta = "ControlGestor?action=autorizaListaGestor";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
    				this.editaAutorizaGestor(request, response, ruta);
    			}
                
                break;
            
            case "finalizaListaGestor":
                /* Este es el Menu lista las declaraciones pendientes*/
                ruta = "view/gestor/finalizacion/List.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
    				this.finalizaListaGestor(request, response, ruta);
    			}
                
                break;
            case "finalizaDeGestor":
                /* Este es el Menu Actualiza autorizacion*/
                ruta = "view/gestor/finalizacion/List.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
    				this.finalizaDeclaGestor(request, response, ruta);
    			}
                
                break;
                
            case "finalizaRechazaGestor":
                /* Este es el Menu Actualiza autorizacion*/
                ruta = "ControlGestor?action=finalizaListaGestor";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
    				this.finalizaRechazaGestor(request, response, ruta);
    			}
                
                break;
            case "declaraListaGestor":
                /* Este es el Menu Lista Historico*/
                ruta = "view/gestor/declaracion/Read.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
	                int idS = Integer.parseInt(String.valueOf(sesion.getAttribute("idSede")));
	                int idP = Integer.parseInt(String.valueOf(sesion.getAttribute("perfil")));
//	                declaDAO.prebaDeclaRes(idS, idP);
	                this.gestor(request, response, ruta);
    			}
                break;
            case "declaraLeerGestor":
                /* Este es el Menu Lista Historico*/
                ruta = "view/gestor/declaracion/Read.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
    	               this.gestor(request, response, ruta);
    			}
                break;
            case "indiListaGestor":
                /* Este es el Menu Lista Inficadores*/
                ruta = "view/gestor/indicador/List.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
    	               this.gestor(request, response, ruta);
    			}
                break;
            case "indiCreaGestor":
                /* Este es el Menu Lista Inficadores*/
                ruta = "view/gestor/indicador/Create.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
    				this.gestorCrea(request, response, ruta);
    			}
                
                break;
            case "salidaListaGestor":
                /* Este es el Menu Actualiza autorizacion*/
                ruta = "view/gestor/salida/List.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.gestor(request, response, ruta);
    			} else {
    	               this.gestor(request, response, ruta);
    			}
                break;

        }

    }

    //Pantalla de Principal Gestor
    protected void gestor(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException, SQLException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    
    
    //Pantalla de Principal Gestor
    protected void finalizaRechazaGestor(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws Exception {
        
    	log.info(request.getParameterNames()+"");
    	int idD= Integer.parseInt(String.valueOf(request.getParameter("idDec")));
    	log.info(idD+"");
    	Declaracion decla =	em.find(Declaracion.class, idD);
    	decla.setDec_ges_aprobada("R");
    	java.util.Calendar fecha = java.util.Calendar.getInstance();
    	decla.setDec_ges_fecha_ent(fecha.getTime());
        declaDAO.updateDeclaRe(decla);
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    protected void autorizaActuaGestor(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException, SQLException {
        
        List<Autorizaciones> autoridadGes = autoDAO.listAutoridad();
        request.setAttribute("autoridad" , autoridadGes);
        
        int id_Aut = Integer.parseInt(request.getParameter("txtId")); // recibir el parametro
        List<Autorizaciones> aut = autoDAO.listarIdAutGes(id_Aut); // utilizar la clase y crear objeto, llamar al metodo
        request.setAttribute("autEditar", aut); // enviar lo parametros
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    
    //Pantalla de Principal Gestor
    protected void editaAutorizaGestor(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws Exception  {
        
        String resol = request.getParameter("txtResol");
        String fResol =  request.getParameter("txtFresol");
        int autoridad = Integer.parseInt(request.getParameter("txtAutoridad"));
        String permisoEmis = request.getParameter("txtEmis");
        if (permisoEmis==null) {
            permisoEmis="NO";
        }

        String resolEmis = request.getParameter("txtResolEmis");
        String fechaIniEmis = request.getParameter("txtFiniEmis");
        String fechaFinEmis = request.getParameter("txtFfinEmis");
        String permisoVert = request.getParameter("txtVert");
        if (permisoVert==null) {
            permisoVert="NO";
        }
        String resolVert = request.getParameter("txtResolVert");
        String fechaIniVert = request.getParameter("txtFiniVert");
        String fechaFinVert = request.getParameter("txtFfinVert");
        int id_aut = Integer.parseInt(request.getParameter("txtId"));
        
        System.out.println("resol "+resol+" fec resol "+fResol+" auto "+autoridad+" emisiones "+permisoEmis+"resol emi "+resolEmis+"f i emi "+fechaIniEmis+" f f emi"+fechaFinEmis+"verti "+permisoVert+"resol vert "+resolVert+"f i vert "+fechaIniVert+"f f vert "+fechaFinVert+"gestor");
        auto.setId_autorizacion(id_aut);
        auto.setLicencia_plan(resol);
        auto.setFecha_resol(fResol);
        auto.setId_auto_ambiental(autoridad);
        auto.setPermiso_emisiones(permisoEmis);
        auto.setEmisiones_resol(resolEmis);
//        auto.setFecha_ini_emis(fechaIniEmis);
//        auto.setFecha_fin_emis(fechaFinEmis);
        auto.setPermiso_vertimi(permisoVert);
        auto.setVertimi_resol(resolVert);
//        auto.setFecha_ini_verti(fechaIniVert);
//        auto.setFecha_fin_verti(fechaFinVert);
//        autoDAO.actualizarAutorizacion(auto);
//        
//        
//        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    
     //Pantalla de Principal Gestor
    protected void autorizaVerGestor(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException, SQLException {
        
        List<Autorizaciones> autoridadGes = autoDAO.listAutoridad();
        request.setAttribute("autoridad" , autoridadGes);
        
        int id_Aut = Integer.parseInt(request.getParameter("txtId")); // recibir el parametro
        List<Autorizaciones> aut = autoDAO.listarIdAutGes(id_Aut); // utilizar la clase y crear objeto, llamar al metodo
        request.setAttribute("autVer", aut); // enviar lo parametros
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    
    //Pantalla de Principal Gestor
    protected void crearAutorizaGestor(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException, SQLException {
        
        List<Autorizaciones> autoridadGes = autoDAO.listAutoridad();
        request.setAttribute("autoridad" , autoridadGes);
        
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    
    //Pantalla de Principal Gestor
    protected void guardaAutorizaGestor(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException, SQLException {
        
        String resol = request.getParameter("txtResol");
        String fResol =  request.getParameter("txtFresol");
        String autoridad = request.getParameter("txtAutoridad");
        String permisoEmis = request.getParameter("txtEmis");
        if (permisoEmis==null) {
            permisoEmis="NO";
        }

        String resolEmis = request.getParameter("txtResolEmis");
        String fechaIniEmis = request.getParameter("txtFiniEmis");
        String fechaFinEmis = request.getParameter("txtFfinEmis");
        String permisoVert = request.getParameter("txtVert");
        if (permisoVert==null) {
            permisoVert="NO";
        }
        String resolVert = request.getParameter("txtResolVert");
        String fechaIniVert = request.getParameter("txtFiniVert");
        String fechaFinVert = request.getParameter("txtFfinVert");
        int vsG = Integer.parseInt(String.valueOf(request.getSession().getAttribute("idUsuario"))); // llamar la variable de session
        //System.out.println("resol "+resol+" fec resol "+fResol+" auto "+autoridad+" emisiones "+permisoEmis+"resol emi "+resolEmis+"f i emi "+fechaIniEmis+" f f emi"+fechaFinEmis+"verti "+permisoVert+"resol vert "+resolVert+"f i vert "+fechaIniVert+"f f vert "+fechaFinVert+"gestor "+vsG);
        
//        InputStream inputArc = null;
//        Part pdfPart = request.getPart("txtPdf");
//        int pdfSize =(int)pdfPart.getSize();
//        byte[] arcPdf=null;
//        if (pdfSize>0) {
//            
//            arcPdf= new byte[pdfSize];
//            try(DataInputStream dis = new DataInputStream(pdfPart.getInputStream())){
//                dis.readFully(arcPdf);
//            }
//            
//        }
//       
//        if (pdfSize>0) {
//            auto.setFile_pdf2(arcPdf);
//        }
        
//        auto.setFile_pdf(inputArc);
        auto.setLicencia_plan(resol);
        auto.setFecha_resol(fResol);
        auto.setAuto_ambiental(autoridad);
        auto.setPermiso_emisiones(permisoEmis);
        auto.setEmisiones_resol(resolEmis);
//        auto.setFecha_ini_emis(fechaIniEmis);
//        auto.setFecha_fin_emis(fechaFinEmis);
        auto.setPermiso_vertimi(permisoVert);
        auto.setVertimi_resol(resolVert);
//        auto.setFecha_ini_verti(fechaIniVert);
//        auto.setFecha_fin_verti(fechaFinVert);
        auto.setGestor(vsG);
//        autoDAO.insertarAutorizado(auto);
        

        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    
    //Pantalla de Principal Gestor
    protected void autorizaListaGestor(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws Exception {
    	int FiGesAp = (int)request.getSession().getAttribute("idSede");
    	declaDAO.getListaDeclaracionGes(FiGesAp);
//        autoDAO.listAutorizaciones(FiGesAp);
        
        
        
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    
    //Pantalla de Principal Gestor
    protected void servicioEnvActuaGestor(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        
        String idCorrienteR = request.getParameter("tipResiduo");
        String tipGestion = request.getParameter("tipGestion");
        String subTipoGestion = request.getParameter("subTipGest");
        String idRes = request.getParameter("txtId");
//        resi.setRes_id(idRes);
        resi.setRes_tipo_residuo(idCorrienteR);
        resi.setRes_tipo_gestion(tipGestion);
        resi.setRes_sub_tip_gestion(subTipoGestion);
//        resDAO.actualizarServiciosGestor(resi);
        

        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    
        //Pantalla de Principal Gestor
    protected void servicioActuaGestor(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException, SQLException {
        
        int id_resA = Integer.parseInt(request.getParameter("txtId")); // recibir el parametro
//        updateResiduo ursa = resDAO.listarIdResiA(id_resA); // utilizar la clase y crear objeto, llamar al metodo
//        request.setAttribute("resiEditar", ursa); // enviar lo parametros
//        
//        List<Residuos> corriente = resDAO.corrienteResiduo();
//        request.setAttribute("corriente_res", corriente);
//        
//        List<Residuos> tipoGestion = resDAO.tipoGestion();
//        request.setAttribute("tipo_gestion", tipoGestion);
        
        
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    
     //Pantalla de Principal Gestor
    protected void autorizaEliminaGestor(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException, SQLException {
        
        int id = Integer.parseInt(request.getParameter("txtId"));
//        autoDAO.eliminarAutorizacionG(id);
        
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    
    //Pantalla de Principal Gestor
    protected void eliminaServicioGestor(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        
        int idResA = Integer.parseInt(request.getParameter("txtId"));
//        resDAO.eliminarResAutorizado(idResA);
        
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    //Pantalla de Principal Gestor
    protected void creaServicioGestor(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException, SQLException {
        
    	StringBuilder errorMessage = new StringBuilder();
    	try {
    		
        	String value;
        	ServicioGestor servicio;
        	while ((servicio = resDAO.getNewServGest())==null) 
        	{
    			resDAO.initNewServicioGestor();
    			
    		}
        	servicio.setSg_corriente_residuo(request.getParameter("tipResiduo"));
        	servicio.setSg_gestion(request.getParameter("tipGestion"));
        	servicio.setSg_gestor(Integer.parseInt(String.valueOf(request.getSession().getAttribute("idUsuario"))));
        	servicio.setSg_sub_gestion(request.getParameter("subTipGest"));
            log("\n*****************Try Registration of Member=" + servicio);
            resDAO.register();
            int vsG = Integer.parseInt(String.valueOf(request.getSession().getAttribute("idUsuario"))); // llamar la variable de session
            log.info("Servicio listar gestor: "+ vsG);
            resDAO.listarResiduoGestor(vsG);
            request.setAttribute("infoMessage", servicio.getSg_corriente_residuo()+ " Registrado correctamente!");
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
    //Pantalla de Principal Gestor
    protected void servicioCreaGestor(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws Exception {  
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    
    //Pantalla de Principal Gestor
    protected void servicioListaGestor(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        
        int vsG = Integer.parseInt(String.valueOf(request.getSession().getAttribute("idUsuario"))); // llamar la variable de session
        log.info("Servicio listar gestor: "+ vsG);
        resDAO.listarResiduoGestor(vsG);
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    
    private void ordenarDash(HttpSession sesion) {
		// TODO Auto-generated method stub
		List<Tuple> dshi = dasDao.getConDashTrn();
		if(!dshi.isEmpty())
		{
		for (Tuple dsh : dshi) {
			sesion.setAttribute("dashEnviadasGes", dsh.get(0)==null ? 0 : dsh.get(0));
		  sesion.setAttribute("dashNoEnviadasGes", dsh.get(1)==null ? 0 : dsh.get(1));
		   sesion.setAttribute("dashAprovadasGes", dsh.get(2)==null ? 0 : dsh.get(2));
		  sesion.setAttribute("dashRechasadasGes", dsh.get(3)==null ? 0 : dsh.get(3));
		 sesion.setAttribute("dashFinalizadasGes", dsh.get(4)==null ? 0 : dsh.get(4));
			sesion.setAttribute("dashRechaGesGes", dsh.get(5)==null ? 0 : dsh.get(5));
			sesion.setAttribute("dashProgramaGes", dsh.get(6)==null ? 0 : dsh.get(6));			
		}	
		}
		else
		{
			sesion.setAttribute("dashEnviadasTrn", 0);
			sesion.setAttribute("dashNoEnviadasTrn", 0);
			sesion.setAttribute("dashAprovadasTrn", 0);
			sesion.setAttribute("dashRechasadasTrn", 0);
			sesion.setAttribute("dashFinalizadasTrn", 0);
			sesion.setAttribute("dashRechaGesTrn", 0);
			sesion.setAttribute("dashProgramaTrn", 0);			
		}
		
		
	}
    
    

   
    
    //Pantalla de Gestor Finaliza Declaracion.
    protected void finalizaDeclaGestor(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws Exception {
        
    	String[] check = request.getParameterValues("prCheck[]");
    	String[] tipoEmbalaje = request.getParameterValues("tipEmbalaje[]");
    	String[] idsDecla = request.getParameterValues("idDecla[]");
    	String[] idsDeclaRes = request.getParameterValues("idDeclaRes[]");
    	String[] cantEmb = request.getParameterValues("txtCantEmb[]"); 
    	String[] tipEmpa = request.getParameterValues("tipEmpaque[]");
    	String[] cantEmpq = request.getParameterValues("txtCantEmpq[]");
    	String[] cantPeso = request.getParameterValues("txtCantPeso[]");
    	String[] tGes = request.getParameterValues("tipGestion[]");
    	String[] tMan = request.getParameterValues("tipMan[]");
    	String[] observ = request.getParameterValues("txtObser[]");
    	SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
    	log.info(check.toString());
    	log.info(tipoEmbalaje.toString());
    	log.info(idsDecla.toString());
    	log.info(idsDeclaRes.toString());
    	log.info(cantEmb.toString());
    	log.info(tipEmpa.toString());
    	log.info(cantEmpq.toString());
    	log.info(cantPeso.toString());
    	log.info(observ.toString());
    	// el que formatea
    	SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
    	String fechaRes = request.getParameter("txtReco");
    	String fechEntr = request.getParameter("txtEntr");
    	Date fechRec = parseador.parse(fechaRes);
    	Date fechEnt = parseador.parse(fechEntr);
    	
    	log.info(tipoEmbalaje.length+" / "+idsDeclaRes.length);
    		int s=0;
    	for (int i=0; i<check.length;i++)
    	{
    		long cDeclas = declaDAO.countDeclaRes(Integer.valueOf(String.valueOf(idsDecla[i])));
    		log.info(cDeclas+"");
			log.info(check[i]+"");
			log.info(i+"");
			Boolean ban  = Boolean.valueOf(check[i]) ;
    		if(ban == true) 
			{
            	for (int j=0; j<cDeclas;j++)
            	{
                		DeclaracionResiduo decRes = em.find(DeclaracionResiduo.class, Integer.valueOf(String.valueOf(idsDeclaRes[s])));
                		decRes.setDer_ges_tipo_embalaje2(String.valueOf(tipoEmbalaje[s]));
                		decRes.setDer_ges_numero_embalajes2(Integer.parseInt(cantEmb[s]));
                		decRes.setDer_ges_tipo_empaque2(tipEmpa[s]);
                		decRes.setDer_ges_numero_empaques2(Integer.parseInt(cantEmpq[s]));
                		decRes.setDer_ges_peso_residuo2(Integer.parseInt(cantPeso[s]));
                		decRes.setDec_ges_ti_gestion(tGes[s]);
                		decRes.setDec_ges_subti_gestion(tMan[s]);
                		log.info(decRes.getDer_declaracion()+"");
                		Declaracion dcla = em.find(Declaracion.class, Integer.valueOf(String.valueOf(decRes.getDer_declaracion())));
                		dcla.setDec_ges_fecha_ent(fechEnt);
                		dcla.setDec_ges_fecha_ges(fechRec);
                		if(observ!=null)
                		{
                    	dcla.setDec_ges_observacion(observ[i]);                			
                		}
                		dcla.setDec_ges_aprobada("A");
                		declaDAO.updateDecla_DeclaRes(dcla,decRes);
                		s++;
                	}
    			}
    			else {
    				s+=cDeclas;
    			}
				
			
    		
    	}

		int coTransP = (int)request.getSession().getAttribute("idSede"); // llamar la variable de session
		declaDAO.getListaDeclaracionGes(coTransP);

        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    // pantalla Gestor Lista declaraciones
    protected void finalizaListaGestor(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException, SQLException {
        
    	log.info(request.getSession().getAttribute("idSede")+"");
    	int coTransP = (int)request.getSession().getAttribute("idSede");
//        log.info(idGes+"----------------------------------------------------");
//        int idG = Integer.parseInt(coTransP);
        //Crear ql sql no esta finalizado
        declaDAO.getListaDeclaracionGes(coTransP);   
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    
    protected void finalizaAprobGestor(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws Exception {
        
        int idDecla = Integer.parseInt(request.getParameter("idDecla"));
        int idDeRes = Integer.parseInt(request.getParameter("idDecRes"));
        Declaracion decla = declaDAO.findDdecla(idDecla);
        DeclaracionResiduo decRes = declaDAO.findDeRes(idDeRes);
        String tipEmbalaje = request.getParameter("tipEmbalaje");
        int numEmbalaje = Integer.parseInt(request.getParameter("txtCantEmb"));
        String tipEmpaque = request.getParameter("tipEmpaque");
        
        int numEmpaque = Integer.parseInt(request.getParameter("CantEmpq"));
        int cantPeso = Integer.parseInt(request.getParameter("txtCantPeso"));
        String fechaRec = request.getParameter("txtFRecibe");
        SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaR = parseador.parse(fechaRec);
        
        
        String vst = String.valueOf(request.getSession().getAttribute("idUsuario")); // llamar la variable de session
        decla.setDec_ges_aprobada("A");
        decla.setDec_ges_fecha_ent(fechaR);
        decla.setDec_ges_fecha_ges(new Date());
        decRes.setDer_ges_tipo_embalaje2(tipEmbalaje);
        decRes.setDer_ges_numero_embalajes2(numEmbalaje);
        decRes.setDer_ges_peso_residuo2(cantPeso);
        decRes.setDer_ges_tipo_empaque2(tipEmpaque);
        decRes.setDer_ges_numero_empaques2(numEmpaque);
        declaDAO.updateDecla_DeclaRes(decla, decRes);
    	log.info(request.getSession().getAttribute("idSede")+"");
    	int coTransP = (int)request.getSession().getAttribute("idSede");
//        log.info(idGes+"----------------------------------------------------");
//        int idG = Integer.parseInt(coTransP);
        //Crear ql sql no esta finalizado
        declaDAO.getListaDeclaracionGes(coTransP);
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de Principal Gestor
    protected void gestorCrea(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
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
