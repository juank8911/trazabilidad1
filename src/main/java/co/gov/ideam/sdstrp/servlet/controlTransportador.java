/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.ideam.sdstrp.servlet;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

import com.google.gson.Gson;

import co.gov.ideam.sdstrp.controller.AutorizacionesDAO;
import co.gov.ideam.sdstrp.controller.ConsultaComboDAO;
import co.gov.ideam.sdstrp.controller.DashDao;
import co.gov.ideam.sdstrp.controller.DeclaracionDAO;
import co.gov.ideam.sdstrp.controller.ResiduoDAO;
import co.gov.ideam.sdstrp.controller.RutasDAO;
import co.gov.ideam.sdstrp.controller.SedeDAO;
import co.gov.ideam.sdstrp.controller.UserDao;
import co.gov.ideam.sdstrp.controller.VehiculoDAO;
import co.gov.ideam.sdstrp.model.Autorizaciones;
import co.gov.ideam.sdstrp.model.Declaracion;
import co.gov.ideam.sdstrp.model.DeclaracionResiduo;
import co.gov.ideam.sdstrp.model.Residuos;
import co.gov.ideam.sdstrp.model.RutaSedeTran;
import co.gov.ideam.sdstrp.model.Rutas;
import co.gov.ideam.sdstrp.model.TipoManejo;
import co.gov.ideam.sdstrp.model.TipoVehiculo;
import co.gov.ideam.sdstrp.model.Vehiculo;

/**
 *
 * @author Yorman.B
 */

@WebServlet(name = "controlTrans", urlPatterns = {"/controlTrans"})
public class controlTransportador extends HttpServlet {
      
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private Logger log;
    @Inject
    private EntityManager em;
//	@Inject
//	private ComboServices comboServices;
	@Inject
	private VehiculoDAO vehDAO;
	@Inject
	private DashDao dasDAO;
	
	@Inject
	private ConsultaComboDAO combo;
	
	@Inject
	private UserDao usrDAO;
	
	@Inject
	private ResiduoDAO resDao;
	
	@Inject
	private DeclaracionDAO decDao;
	
	
	@Inject
	private RutasDAO rutDAO;
	@Inject
	private DeclaracionDAO declaDao;
	
	private Rutas rutas;
	private RutaSedeTran rutaSedTran;
	private Declaracion upDecla;
	private DeclaracionResiduo udDeRes;
	
	@Inject
	private SedeDAO sedDAO;
	
	private RequestDispatcher dis;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PrintWriter out = response.getWriter();

        response.setContentType("text/html;charset=UTF-8");
        String ruta = "";
        String action = request.getParameter("action");
		HttpSession sesion = request.getSession();
		
        switch (action) {

            case "transp":
                /* Este es el Menu inicio interno autoridad*/
                ruta = "view/transportador/transportador.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
                    this.transp(request, response, ruta);
    			} else {
    				int idSedeT = (int) sesion.getAttribute("idSede");
    				String perfT = "transportador";
    				dasDAO.declaracionesDeTrn(idSedeT,"transportador");
    				ordenarDash(sesion);	
                    this.transp(request, response, ruta);
    			}
                break;

            case "perfilLeeTransp":
                /* Este es el menu establecimiento */
                ruta = "view/transportador/persona/Read.jsp";
       			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
                    this.transp(request, response, ruta);
    			} else {
    				sedDAO.darSedeId(Integer.parseInt(String.valueOf(sesion.getAttribute("idSede"))));
                    this.transp(request, response, ruta);
    			}
       			break;
            case "perfilActuaGenera":
                ruta = "view/primero/Update.jsp";
                if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
                    this.transp(request, response, ruta);
    			} else {
    				int idUsua = Integer.parseInt(String.valueOf(sesion.getAttribute("idUsuario")));
    					combo.darListaTipoDocumento();
    					usrDAO.primerSesUsu(idUsua);
                    this.transp(request, response, ruta);
    			}
                break;
                
            case "claveTransp":
                /* Este es el menu establecimiento */
            	ruta = "view/primero/UpdateClv.jsp";
       			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
                    this.transp(request, response, ruta);
    			} else {
                    this.claveTransp(request, response, ruta);
    			}

                break;
            case "vehiculoTransp":
                /* Lista de Vehiculos creados para el transportador */
       			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
                    this.transp(request, response, ruta);
    			} else {
                    int idU = (int) request.getSession().getAttribute("idSede"); // llamar la variable de session
                    log.info(idU+"");
                    int vst = idU;
                    System.out.println("variable del transportador "+vst);
                    vehDAO.listarVehiculosUs(vst); // enviar la variable al me
                    request.getRequestDispatcher("view/transportador/vehiculos/Read.jsp").forward(request, response);
    			}

                
                break;              
            case "vehiGuardaTransp":
                /* Crear Vehiculo */
       			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
                    this.transp(request, response, ruta);
    			} else {
                	StringBuilder errorMessage = new StringBuilder();
                    Vehiculo veh;
                    ruta = "controlTrans?action=vehiculoTransp";
                 try {
    	        	while ((veh = vehDAO.getNewVehic())==null) 
    	        	{
    	    			vehDAO.initNewVehi();
    	    			
    	    		}
    	        	veh.setVeh_id(request.getParameter("txtPlaca"));
    	        	log.info("id "+ veh.getVeh_id());
    	        	veh.setVeh_tipo_vehiculo(request.getParameter("tipoVeh"));
    	        	log.info("tipo "+ veh.getVeh_tipo_vehiculo());
    	        	veh.setVeh_id_desig(request.getParameter("tipDesig"));
    	        	log.info("des "+ veh.getVeh_id_desig());	        	
    	        	veh.setVeh_placa(request.getParameter("txtPlaca"));
    	        	log.info("placa "+ veh.getVeh_placa());
    	        	veh.setVeh_placa_remolque(request.getParameter("txtPlacaSemi"));
    	        	log.info("placa semi "+ veh.getVeh_placa_remolque());
    	        	veh.setVeh_chasis(request.getParameter("txtChasis"));
    	        	log.info("chasi "+ veh.getVeh_chasis());
    	        	veh.setVeh_capacidad(request.getParameter("txtCapacidad"));
    	        	log.info("cap "+ veh.getVeh_capacidad());
    	        	veh.setVeh_marca(request.getParameter("txtMarca"));
    	        	log.info("marca "+ veh.getVeh_marca());
    	        	veh.setVeh_linea(request.getParameter("txtLinea"));
    	        	log.info("linea "+ veh.getVeh_linea());
    	        	veh.setVeh_modelo(request.getParameter("txtModelo"));
    	        	log.info("modelo "+ veh.getVeh_modelo());
    	        	veh.setVeh_tipo_peligrosidad(request.getParameter("txtPeligrosidad[]"));
    	        	log.info("peligro "+ veh.getVeh_tipo_peligrosidad());
    	        	veh.setVeh_pro_tipo_documento(request.getParameter("txtTipDocu"));
    	        	log.info("tipo_Doc "+ veh.getVeh_pro_tipo_documento());
    	        	veh.setVeh_pro_numero_documento(request.getParameter("txtNdocumento"));
    	        	log.info("num_doc "+ veh.getVeh_pro_numero_documento());
    	        	veh.setVeh_pro_nombre(request.getParameter("txtNomPro"));
    	        	log.info("nom_pro "+ veh.getVeh_pro_nombre());
                    System.out.println("peligrosidad "+ veh.getVeh_tipo_peligrosidad());
                    veh.setVeh_transportador(String.valueOf(request.getSession().getAttribute("idUsuario")));
                    veh.setVeh_id_sede(Integer.parseInt(String.valueOf(request.getSession().getAttribute("idSede"))));
                    log("*****************Try Registration of Residuio = " + veh);
                    	vehDAO.registerVehi();
                    request.getRequestDispatcher("controlTrans?action=vehiculoTransp").forward(request, response);
                    request.setAttribute("infoMessage", request.getParameter("txtPlaca")+ " Registrado correctamente!");
    		} catch (Exception e) {
    			// TODO: handle exception
                Throwable t = e;
                while ((t.getCause()) != null) {
                    t = t.getCause();
                }
    		} finally {
    			
                request.setAttribute("errorMessage", errorMessage.toString());
                dis = request.getRequestDispatcher(ruta);
                dis.forward(request, response);		
    		}
    			}

                
                break;
                
            case "vehiCreaTransp":
                /* Crear Vehiculo */
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.transp(request, response, ruta);
    			} else {
                    ruta = "view/transportador/vehiculos/Create.jsp";                
                    this.vehiCreaTransp(request, response, ruta);
    			}
//                List<vehiculo>datosP=vehDAO.listaVeh(); //obtener los datos
//                request.setAttribute("datosP", datosP);

                break;
            case "residuoListaGesGenera":
                System.out.println("en control generador STG");
                String codTtrn = request.getParameter("codGes");
                log.info(request.getParameter("codGes"));
                log.info(codTtrn);
                List<TipoVehiculo> tmaenjo = combo.listaVehDesginacion(codTtrn);
                StringBuilder sb = new StringBuilder("");
                for (TipoVehiculo tipoVeh : tmaenjo) {
                	System.out.println(tmaenjo);
                	
                	log.info("ID: "+tipoVeh.getTve_id()+" - "+"nombre "+tipoVeh.getTve_nombre()+"|");
                	
                	sb.append(tipoVeh.getTve_id()+"-"+tipoVeh.getTve_nombre()+"|");
					}
                	out.write(sb.toString());
            
            break;
            case "vehiActualizaTransp":
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.transp(request, response, ruta);
    			} else {
                    request.getRequestDispatcher("controlTrans?action=vehiculoTransp").forward(request, response);
    			}

                /* actualizar de pantalla vehiculo */
                //ruta = "view/transportador/vehiculos/List.jsp";
                //this.vehiListaTransp(request, response, ruta);
                break;
            case "vehiListaTipoV":
                /* llamar lista tipo designacion */
//                String codesig = request.getParameter("codigoveh"); 
//                List<tipoVehDesig> ltv = vehDAO.listaVehTipo(codesig);
//                StringBuilder sb = new StringBuilder("");
////                               
//                for (int i = 0; i < ltv.size(); i++) {
//                    sb.append(ltv.get(i).getTv_id()+"-"+ltv.get(i).getTv_nombre()+"|"); // ojo el ciclo es solo para esta linea
//                }
//                out.write(sb.toString());
                
                break;
            case "vehiActuaTransp":
                /* trae el id del vehiculo a actualizar */
//                List<vehiculo>datosU=vehDAO.listaVeh(); //obtener los datos
//                request.setAttribute("datosP", datosU); // envia la lista de tipo de vehiculos
//                
//                String idVeh = request.getParameter("txtId");
//                vistaVehiculo vvu=vehDAO.listarId(idVeh);
//                System.out.println("Controlador vehUpdate "+idVeh);
//                request.setAttribute("vVehiculo", vvu);
            	combo.getListaTipoVeh();
                request.getRequestDispatcher("view/transportador/vehiculos/Update.jsp").forward(request, response);

                break;
                
            case "vehiEliminaTransp":
                // boton eliminar vehiculo
                ruta = "controlTrans?action=vehiculoTransp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.transp(request, response, ruta);
    			} else {
                    this.vehiEliminaTransp(request, response, ruta);
    			}


                break;
            case "rutaAgregar": // en funcionamiento
                /* Agregar Ruta */
                String nRuta = request.getParameter("nombreRuta");
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.transp(request, response, ruta);
    			} else {
                    log.info(nRuta);
                    int idTransp = Integer.parseInt(request.getParameter("idTransp"));
                    log.info(idTransp+" Transportador");
                    String[] nSede = request.getParameterValues("sedeG");
                    log.info(nSede+"");


                    for (String string : nSede) {
                    	log.info(string);
                    	int idS = Integer.parseInt(string);
                    	while ((rutas = rutDAO.getNewRuta())==null) 
                    	{
                			rutDAO.initNewRuta();
                		}
                    	rutas.setRut_nombre(nRuta);
                    	rutas.setRut_sed_gene(idS);
                    	rutDAO.registerRuta();
                    	log.info(""+rutas.getId_ruta());
                    	while ((rutaSedTran = rutDAO.getNewRutaSed())==null) 
                    	{
                			rutDAO.initNewRutaSed();
                		}
                    	rutaSedTran.setRug_id_ruta(rutas.getId_ruta());
                    	rutaSedTran.setRug_id_sedtr(idTransp);
                    	log.info(rutaSedTran.toString());
                    	rutDAO.RegisterRutaSedTr();
    				}
                	
//                    rutas agreRut = new rutas();
//                    agreRut.setId_transp(idTransp);
//                    agreRut.setNombre_ruta(nRuta);
//                    agreRut.setTb_id_sede(nSede);
//                    ruDAO.insertarRuta(agreRut);

                    ruta = "controlTrans?action=rutaListaTransp";
                    String idS = String.valueOf(request.getSession().getAttribute("idSede"));
                    int id = Integer.parseInt(idS);
                    rutDAO.listaRutas(id);
                    this.rutaListaTransp(request, response, ruta);
    			}

                break;
            case "rutaCreaTransp": // en funcionamiento
                
                ruta = "view/transportador/rutas/Create.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.transp(request, response, ruta);
    			} else {
    				this.rutaCreaTransp(request, response, ruta);
    			}
                
                break;
            case "rutaDeleTransp": // en funcionamiento
                
                ruta = "controlTrans?action=rutaListaTransp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.transp(request, response, ruta);
    			} else {
                    this.rutaDeleTransp(request, response, ruta);
    			}

                break;
            case "rutaListaTransp": // en funcionamiento
                
                ruta = "view/transportador/rutas/List.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.transp(request, response, ruta);
    			} else {
                    this.rutaListaTransp(request, response, ruta);
    			}

                break;
            case "rutaActuaTransp":
                /* Este es el menu establecimiento */
                ruta = "view/transportador/rutas/Update.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.transp(request, response, ruta);
    			} else {
    				this.rutaActuaTransp(request, response, ruta);
    			}
                
                break;
            case "apruebaTransp":
                /* lista declaraciones al Transportador */
                ruta = "view/transportador/aprobacion/List.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.transp(request, response, ruta);
    			} else {
    				this.apruebaTransp(request, response, ruta);
    			}
                
                break;
            	case "declaraApruebaTranspor":
            		int coTransP = (int)request.getSession().getAttribute("idSede"); // llamar la variable de session
            		List<Declaracion> lsDecTrasnn = declaDao. getListaDeclaracionTranporJs(coTransP);
            		log.info(lsDecTrasnn.toString());
                    StringBuilder db = new StringBuilder("");
                    	
                        for ( Declaracion dec: lsDecTrasnn) {
                        	System.out.println(dec);
                        	log.info("{'id': "+dec.getDec_id()+" , "+"'name' : '"+dec.getDec_gen_fecha_gen()+"', 'price' :'"+dec.getDec_ges_aprobada()+"'}");
                        	db.append(dec.getDec_id()+" ? "+dec.getDec_gen_fecha_gen()+"?"+dec.getDec_ges_aprobada()+"|");
        					}
                       
                        log.info(db.toString());
                        out.write(db.toString());
//                    	response.getWriter().write(json);
////                    	out.write(json);
                  
            		
            	break;
            case "declaraListaTransp":
                /* Lista declaracion Transportador */
                ruta = "view/transportador/declaracion/Read.jsp";
                try {
                	log.info(""+sesion.getAttribute("perfil"));
                	if (sesion.getAttribute("perfil") == null) {
        				ruta = "view/login.jsp";
        	               this.transp(request, response, ruta);
        			} else {
        				this.declaraListaTranspo(request, response, ruta);
        			}
					
				} catch (Exception e) {
					// TODO: handle exception
					log.info("Fallo lista Declaraciones por: " + e.getMessage());
		            e.fillInStackTrace();
					// TODO: handle exception
				}
    			
    			 break;
            case "declaTRNVer":
                /*  declaracion Transportador Aprobacion*/
                ruta = "view/transportador/aprobacion/Create.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.transp(request, response, ruta);
    			} else {
    				this.declaTRNVer(request, response, ruta);
    			}
                
                break;
                
            case "declaTRNAprobar":
                /*  declaracion Transportador Aprobacion*/
            	ruta = "view/transportador/aprobacion/List.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.transp(request, response, ruta);
    			} else {
    				this.declaTRNAprobar(request, response, ruta);
    			}
                
                break;
            case "declaTRNRechazar":
                /*  declaracion Transportador Rechazar*/               
                ruta = "view/transportador/aprobacion/List.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.transp(request, response, ruta);
    			} else {
    				this.declaTRNRechazar(request, response, ruta);
    			}
                
                break;
            case "indiListaTransp":
                /* Este es el menu establecimiento */
                ruta = "view/transportador/indicador/List.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.transp(request, response, ruta);
    			} else {
    				this.indiListaTransp(request, response, ruta);
    			}
                
                break;
            case "salidaListTransp":
                /* Este es el menu establecimiento */
                ruta = "view/transportador/salida/List.jsp";
    			if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.transp(request, response, ruta);
    			} else {
    		    	int idSe = Integer.parseInt(String.valueOf(sesion.getAttribute("idSede")));
    		        int idPe = Integer.parseInt(String.valueOf(sesion.getAttribute("perfil")));
    		        decDao.SelectDeclHistorial(idSe, idPe);
    				this.salidaListTransp(request, response, ruta);
    			}
                
                break;

        }

    }
		

    private void ordenarDash(HttpSession sesion) {
		// TODO Auto-generated method stub
		List<Tuple> dshi = dasDAO.getConDashTrn();
		if(!dshi.isEmpty())
		{
		for (Tuple dsh : dshi) {
			sesion.setAttribute("dashEnviadasTrn", dsh.get(0)==null ? 0 : dsh.get(0));
			sesion.setAttribute("dashNoEnviadasTrn", dsh.get(1)==null ? 0 : dsh.get(1));
			sesion.setAttribute("dashAprovadasTrn", dsh.get(2)==null ? 0 : dsh.get(2));
			sesion.setAttribute("dashRechasadasTrn", dsh.get(3)==null ? 0 : dsh.get(3));
			sesion.setAttribute("dashFinalizadasTrn", dsh.get(4)==null ? 0 : dsh.get(4));
			sesion.setAttribute("dashRechaGesTrn", dsh.get(5)==null ? 0 : dsh.get(5));
			sesion.setAttribute("dashProgramaTrn", dsh.get(6)==null ? 0 : dsh.get(6));			
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


	//Pantalla de Inicio Transportador
    protected void transp(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de Transportador Empresa lista
    protected void empresaLeerTransp(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de Transportador Establecimiento lista
    protected void sedeLeeTransp(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }


    //Pantalla de Transportador Cambio clave
    protected void claveTransp(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de Transportador Vehiculo
    protected void vehiculoTransp(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de Transportador Crea Vehiculo
    protected void vehiCreaTransp(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
    	
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de Transportador Lista Vehiculo
    protected void vehiListaTransp(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de Transportador Actualiza Vehiculo
    protected void vehiActuaTransp(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    
     //Pantalla de Transportador Actualiza Vehiculo
    protected void vehiEliminaTransp(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws Exception {    
        String id = request.getParameter("txtId");
        vehDAO.eliminarVehiTrans(id);     
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    
    //Pantalla de Transportador ruta
    protected void rutaCreaTransp(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException, SQLException {
        /* Pantalla del selector de rutas Generadoras  */
//                List<rutas>rutaG=ruDAO.listaSedeGen();
//                request.setAttribute("rutaG", rutaG);
    	int coTransP = (int)request.getSession().getAttribute("idSede"); // llamar la variable de session
    	rutDAO.pruebaSeT(coTransP);
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    
    protected void rutaDeleTransp(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException, SQLException {
        /* Pantalla del selector de rutas Generadoras  */
                
//                String idrut = request.getParameter("txtId");
//                System.out.println("en controlador delete"+idrut);
//                ruDAO.eliminarRuta(idrut);
                
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    
    //Pantalla de Transportador ruta Lista
    protected void rutaListaTransp(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException, SQLException {
        /* aqui se genera la lista de las rutas por transportador usuario */
                int coTransP = (int)request.getSession().getAttribute("idSede"); // llamar la variable de session
                log.info(coTransP+"");
                rutDAO.listaRutas(coTransP);
//                rutDAO.listaRutas(coTransP);
//                int convId = Integer.parseInt(coTransP);
//                List<rutas> lisRuta = ruDAO.listaRutas(convId);
//                request.setAttribute("listaRutas", lisRuta);

        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de Transportador ruta
    protected void rutaActuaTransp(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException, SQLException {
        
//        String idRu = request.getParameter("txtId");
//        
//        List<rutas> rutaG = ruDAO.listarIdR(idRu);
//        request.setAttribute("rutaG", rutaG);

        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de Declaracion Transportador
    protected void apruebaTransp(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException, SQLException {
    		int coTransP = (int)request.getSession().getAttribute("idSede"); // llamar la variable de session
    		declaDao.getListaDeclaracionTranpor(coTransP);
    		vehDAO.listarVehiculosTrans(coTransP);
        int idTrn = Integer.parseInt((String.valueOf(request.getSession().getAttribute("idUsuario"))));
//        List<Declaracion> listDeclaTrn = declaDAO.listDeclaTransp(idTrn);
//        request.setAttribute("listDeclaTrn", listDeclaTrn);
        

        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de Transportador Vehiculo
    protected void declaraListaTranspo(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
    	HttpSession sesion = request.getSession();
    	resDao.setTitulo("sdstrp - Declaracion");
        int idS = Integer.parseInt(String.valueOf(sesion.getAttribute("idSede")));
        int idP = Integer.parseInt(String.valueOf(sesion.getAttribute("perfil")));
//        decDao.prebaDeclaRes(idS,idP);
//        decDao.HistoricoDeclas(idS, idP);
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    
    //Pantalla de Transportador Vehiculo
    protected void declaTRNVer(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException, SQLException {
        
        int cod_decla = Integer.parseInt(request.getParameter("id_decla"));
        int idS = (int) request.getSession().getAttribute("idSede");
        log.info(""+cod_decla);
        declaDao.declaracioTrans(cod_decla);
        vehDAO.listarVehiculosUs(idS);
//        List<declaracion>listaADecla=declaDAO.listarDeclaResi(cod_decla);
//        request.setAttribute("declaAprueba", listaADecla);
//        
//        List<declaracion>empaq=declaDAO.empaque();
//        request.setAttribute("listEmpaque", empaq);
//        
//        List<declaracion>embalaj=declaDAO.embalaje();
//        request.setAttribute("listEmbal", embalaj);
//        
//        String vst = (String) request.getSession().getAttribute("idUsuario"); // llamar la variable de session
//
//        List<vistaVehiculo> visVeh = vehDAO.listaVehCrea(vst); // enviar la variable al me
//        request.setAttribute("listaVeh", visVeh);
        
        
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    
    //Pantalla de Transportador Vehiculo
    protected void declaTRNRechazar(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws Exception {
        
//        int id = Integer.parseInt(request.getParameter("id_decla"));
//        declaDAO.rechazaDeclaTrans(id);
    	log.info(request.getParameterNames()+"");
    	int idD= Integer.parseInt(String.valueOf(request.getParameter("idDec")));
    	log.info(idD+"");
    	Declaracion decla =	em.find(Declaracion.class, idD);
    	decla.setDec_trn_aprobada("R");
    	java.util.Calendar fecha = java.util.Calendar.getInstance();
    	decla.setDec_trn_fecha_ent(fecha.getTime());
        declaDao.updateDeclaRe(decla);
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    //Pantalla de Transportador Vehiculo
    protected void declaTRNAprobar(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws Exception {
    	
     	String[] check = request.getParameterValues("prCheck[]");
    	String[] tipoEmbalaje = request.getParameterValues("tipEmbalaje[]");
    	String[] idsDecla = request.getParameterValues("idDecla[]");
    	String[] idsDeclaRes = request.getParameterValues("idDeclaRes[]");
    	String[] cantEmb = request.getParameterValues("txtCantEmb[]"); 
    	String[] tipEmpa = request.getParameterValues("tipEmpaque[]");
    	String[] cantEmpq = request.getParameterValues("txtCantEmpq[]");
    	String[] cantPeso = request.getParameterValues("txtCantPeso[]");
    	String vhei = request.getParameter("txtVhei");
    	SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
    	log.info(check+"");
    	log.info(tipoEmbalaje.toString());
    	log.info(idsDecla.toString());
    	log.info(idsDeclaRes.toString());
    	log.info(cantEmb.toString());
    	log.info(tipEmpa.toString());
    	log.info(cantEmpq.toString());
    	log.info(cantPeso.toString());
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
    		long cDeclas = declaDao.countDeclaRes(Integer.valueOf(String.valueOf(idsDecla[i])));
    		log.info(cDeclas+"");
    			log.info(check[i]+"");
    			log.info(i+"");
    			Boolean ban  = Boolean.valueOf(check[i]) ;
    			if(ban == true) 
    			{
                	for (int j=0; j<cDeclas;j++)
                	{	
                		DeclaracionResiduo decRes = em.find(DeclaracionResiduo.class, Integer.valueOf(String.valueOf(idsDeclaRes[s])));
                		decRes.setDer_trn_tipo_embalaje(String.valueOf(tipoEmbalaje[s]));
                		decRes.setDer_trn_numero_embalajes(Integer.parseInt(cantEmb[s]));
                		decRes.setDer_trn_tipo_empaque(tipEmpa[s]);
                		decRes.setDer_trn_numero_empaques(Integer.parseInt(cantEmpq[s]));
                		decRes.setDer_trn_peso_residuo2(Integer.parseInt(cantPeso[s]));
                		log.info(decRes.getDer_declaracion()+"");
                		Declaracion dcla = em.find(Declaracion.class, Integer.valueOf(String.valueOf(decRes.getDer_declaracion())));
                		dcla.setDec_trn_fecha_trn(fechRec);
                		dcla.setDec_trn_fecha_ent(fechEnt);
                		dcla.setDec_trn_aprobada("A");
                		dcla.setDec_trn_vehiculo(vhei);
                		declaDao.updateDecla_DeclaRes(dcla,decRes);
                		s++;
                	}
    			}
    			else {
    				s+=cDeclas;
    			}
				
			
    		
    	}

		int coTransP = (int)request.getSession().getAttribute("idSede"); // llamar la variable de session
		declaDao.getListaDeclaracionTranpor(coTransP);
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }
    

    //Pantalla de Transportador Vehiculo
    protected void indiListaTransp(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de Transportador Vehiculo
    protected void salidaListTransp(HttpServletRequest request, HttpServletResponse response, String ruta)
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
            Logger.getLogger(controlTransportador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(controlTransportador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}