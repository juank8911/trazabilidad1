package co.gov.ideam.sdstrp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.SessionBean;
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



import co.gov.ideam.sdstrp.controller.UserDao;
import co.gov.ideam.sdstrp.controller.ConsultaComboDAO;
import co.gov.ideam.sdstrp.controller.DashDao;
import co.gov.ideam.sdstrp.controller.EmpresasDao;
import co.gov.ideam.sdstrp.controller.SedeDAO;
import co.gov.ideam.sdstrp.controller.dashboardDAO;
import co.gov.ideam.sdstrp.controller.passwordDAO;
import co.gov.ideam.sdstrp.controller.perfilDAO;
import co.gov.ideam.sdstrp.model.Autoridad;
import co.gov.ideam.sdstrp.model.Departamento;
import co.gov.ideam.sdstrp.model.Empresa;
import co.gov.ideam.sdstrp.model.Municipio;
import co.gov.ideam.sdstrp.model.Perfil;
import co.gov.ideam.sdstrp.model.Sede;
import co.gov.ideam.sdstrp.model.ServicioGestor;
import co.gov.ideam.sdstrp.model.Usuario;
import co.gov.ideam.sdstrp.model.Usuario_Perfil;

;


@WebServlet(name = "control", urlPatterns = { "/control" })
public class control extends HttpServlet {

	@Inject
	private Logger log;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// userDAO usuarioDAO;
	passwordDAO passDAO;

	@Inject
	private EntityManager em;
	@Inject
	private ConsultaComboDAO combo;
	private perfilDAO perDAO;
	@Inject
	private UserDao userDao;
	@Inject
	private DashDao dashDAO;

	@Inject
	private EmpresasDao empDao;

	@Inject
	private SedeDAO sedeDAO;

	@Inject
	private UserDao usrDAO;


	private Usuario usuario;
	private Sede sede;
	private Departamento departamento;
	private Empresa empre;
	private List<Usuario_Perfil> lPerfilLog;
	public int idUser;
	
	

	public control() {

		super();

		// usuarioDAO = new userDAO();
		passDAO = new passwordDAO();
		perDAO = new perfilDAO();

	}

	private RequestDispatcher dis;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String ruta = "";
		HttpSession sesion = request.getSession();
		String action = request.getParameter("action");

		switch (action) {

		case "login":
			ruta = "view/login.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.ingresar(request, response, ruta);
			}
			break;
		// listo juank
		case "ingresar":
			log.info("enviando a ingresar");
			this.ingresar(request, response, ruta);
			break;
		// listo juank
		case "mesDatos":
			ruta = "view/primero/mensajeDatos.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}

			break;
		case "asociDatos":
			ruta = "view/primero/asociarDatos.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}
			break;
		case "mesClave":
			ruta = "view/primero/mensajeCambioClave.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}
			break;
		case "claveInicio2":
			ruta = "view/primero/ClaveInicio.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}
			break;
		case "passActualiza":
			ruta = "view/generador/password/Update.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}
			break;
		case "autoridad":
			/* Este es el Menu inicio interno autoridad */
			ruta = "view/Autoridad/autoridad.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}
			break;
		case "autoridadLeer":
			/* Este es el Menu Historico */
			ruta = "view/Autoridad/autoridad/Read.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}
			break;
		case "perfilLee":
			/* Este es el Menu Perfil */
			ruta = "view/Autoridad/persona/Read.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}
			break;
		case "perfilCrea":
			/* Este es el Menu perfil */
			ruta = "view/Autoridad/persona/Create.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}
			break;
		case "perfilActualiza":
            if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
                this.dirigir(request, response, ruta);
			} else {
				
                this.UpdtPerfil(request, response);
			}
            
			break;
		case "perfilActualizaContra":
            if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
                this.dirigir(request, response, ruta);
			} else {
				
                this.UpdtContra(request, response);
			}
            
			break;
			
		case "cambioClave":
			/* Este es el Menu Actualiza Clave */
			ruta = "view/Autoridad/password/Update.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}

			break;
		case "empresaRegistro":
			ruta = "view/Autoridad/empresa/Read.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.crearEmpresa(request, response, ruta);
			}
			break;
		case "empresaCrear":
			ruta = "view/Autoridad/empresa/Create.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}
			break;
		case "empresaListar":
			ruta = "view/Autoridad/empresa/Read.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}
			break;
		case "empresaLeer":
			ruta = "view/Autoridad/empresa/List.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}
			break;
		case "empresaActualiza":
			ruta = "view/Autoridad/empresa/Update.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}
			break;
		case "sedeCrea":
			/* Este es el menu establecimiento */
			ruta = "view/Autoridad/sede/Create.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				
				int id = Integer.parseInt(String.valueOf(sesion.getAttribute("idUsuario")));
				empDao.empresasAutoridad(id);
				sedeDAO.contSedes(id);
				this.dirigir(request, response, ruta);
			}
			break;
		case "nuevoUsu":
			/* Este es el menu establecimiento */
			StringBuilder usu = new StringBuilder("");
			int empId = Integer.parseInt(String.valueOf(request.getParameter("idEmp")));
			Empresa emp =  em.find(Empresa.class, empId);
			long cSede = sedeDAO.contSedes(empId);
			cSede++;
				usu.append(emp.getEmp_numero_documento()+"_00"+cSede);
				out.write(usu.toString());
			break;
		case "sedeRegista":
			/* Este es el menu establecimiento */
			ruta = "view/Autoridad/sede/List.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.registraSede(request, response, ruta);
			}

			break;
		case "sedeLee":
			/* Este es el menu establecimiento */
			ruta = "view/Autoridad/sede/List.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}
			break;
		case "sedeActualiza":
			/* Este es el menu establecimiento */
			ruta = "view/Autoridad/sede/Update.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}
			break;
		case "usuarioLista":
			ruta = "view/Autoridad/usuario/List.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}
			break;
		case "declaraLista":
			/* Este es el Menu Historico */
			ruta = "view/Autoridad/declaracion/List.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}
			break;
		case "cargaPerfil":
			/* Este es el Menu Historico */;
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				int idp = Integer.valueOf(String.valueOf(request.getParameter("perfilSel")));
				int idUsu = Integer.parseInt(String.valueOf(sesion.getAttribute("idUsuario")));
				userDao.inPerfilSeleccionado(idp,idUsu);
				log.info(idp+"");
				sesion.setAttribute("perfil", Integer.valueOf(String.valueOf(userDao.getSelPerfil().getId_perfil())));
				sesion.setAttribute("perfiles", userDao.getInSelPerfiles());
				this.redirigir(request, response);
			}
			break;
			
		case "declaraLeer":
			/* Este es el Menu Historico */
			ruta = "view/Autoridad/declaracion/Read.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}
			break;
		case "indiLista":
			/* Este es el Menu Indicador */
			ruta = "view/Autoridad/indicador/List.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}
			break;
		case "indiLee":
			/* Este es el Menu Indicador */
			ruta = "view/Autoridad/indicador/Create.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}
			break;
		case "salidaList":
			/* Este es el Menu Indicador */
			ruta = "view/Autoridad/salida/List.jsp";
			if (sesion.getAttribute("perfil") == null) {
				ruta = "view/login.jsp";
				this.dirigir(request, response, ruta);
			} else {
				this.dirigir(request, response, ruta);
			}
			break;
		case "listaMunip":
			/* Este es el Menu Indicador */
			int idDepa = Integer.parseInt(request.getParameter("idDepa"));
			List<Municipio> lMuni = combo.verListaMuniDepa(idDepa);
			StringBuilder stu = new StringBuilder("");
			log.info(lMuni.size()+"");
			for (Municipio muni : lMuni) {
				log.info(muni.getId_munic() + "/" + muni.getMunic_nombre());
				stu.append(muni.getId_munic() + "-" + muni.getMunic_nombre() + "|");
			}
			log.info(stu.toString());
			out.write(stu.toString());
			break;
			
			

		case "cerrar":
			ruta = "view/login.jsp";
			sesion.removeAttribute("perfil");
			sesion.invalidate();
//			response.sendRedirect("view/login.jsp");
//			this.processRequest(request, response);
			this.dirigir(request, response, ruta);
			break;

		}

	}
	
	private void UpdtPerfil(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String ruta;
		HttpSession sesion = request.getSession();
		Usuario usuUp = em.find(Usuario.class, Integer.parseInt(String.valueOf(request.getParameter("txtIdusu"))));
		usuUp.setUsr_documento(Integer.parseInt(String.valueOf(request.getParameter("txtNumDoc"))));
		usuUp.setUsr_nom_persona(String.valueOf(request.getParameter("nomUsu")));
		usuUp.setUsr_apellido(String.valueOf(request.getParameter("apeUsu")));
		usuUp.setUsr_tip_ident(Integer.parseInt(String.valueOf(request.getParameter("slTiDoc"))));
		usuUp.setUsr_cargo(String.valueOf(request.getParameter("txtCargo")));
		usuUp.setUsr_telefono(String.valueOf(request.getParameter("nroTel")));
		usuUp.setUsr_email(String.valueOf(request.getParameter("txtEmail")));
		usuUp.setUsr_nombre(String.valueOf(request.getParameter("txtUsu")));
		usrDAO.updtUsuario(usuUp);
		if(Integer.parseInt(String.valueOf(sesion.getAttribute("nSesion")))==0)
		{
			ruta = "view/primero/UpdateClv.jsp";
		}
		else
		{
			ruta = "control?action=ingresar";
		}
		dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);			
	}
	
	private void UpdtContra(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String ruta;
		HttpSession sesion = request.getSession();
		Usuario usuUp = em.find(Usuario.class, Integer.parseInt(String.valueOf(request.getParameter("txtUsuario"))));
		log.info(String.valueOf(request.getParameter("txtPassActu")));
		if(String.valueOf( usuUp.getUsr_clave()).equals(String.valueOf(request.getParameter("txtPassActu"))))
		{
			usuUp.setUsr_clave(String.valueOf(request.getParameter("txtPassNuev1")));
			usuUp.setNumero_sesion(1);
			usrDAO.updtUsuario(usuUp);
			sesion.setAttribute("nSesion", 1);
			ruta = "control?action=ingresar";
		}
		else
		{
			ruta = "view/primero/UpdateClv.jsp";
			request.setAttribute("actual", "true");
			
		}

		dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);			
	}


	private void registraSede(HttpServletRequest request, HttpServletResponse response, String ruta)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		StringBuilder errorMessage = new StringBuilder();
		HttpSession sesion = request.getSession();
		Sede sede;
		Usuario nUsu;
		List<Integer> cods = new ArrayList<Integer>();
		while ((sede = sedeDAO.getNewSede()) == null) {
			sedeDAO.initNewSede();

		}
		sede.setSed_nombre(request.getParameter("txtNombre"));
		sede.setSed_empresa(Integer.parseInt(String.valueOf(request.getParameter("txtEmpr"))));
		sede.setSed_departamento(Integer.parseInt(request.getParameter("txtDepar")));
		sede.setSed_municipio(Integer.parseInt(request.getParameter("txtMun")));
		sede.setSed_direccion(request.getParameter("txtDirec"));
		sede.setSed_telefono_nro(new BigInteger(request.getParameter("txtTel")));
		sede.setSed_telefono_ext(Integer.parseInt(request.getParameter("txtExt")));
		sede.setSed_generador(request.getParameter("txtEmpr"));
		String idAu = sesion.getAttribute("idUsuario")+"";
		int idUsu = Integer.parseInt(idAu);
		sede.setSed_autoridad(idUsu);
		log.info(request.getParameter("btnGene"));
		log.info(request.getParameter("btnTrans"));
		log.info(request.getParameter("btnGes"));
		if (request.getParameter("btnGene") != null) {

			log.info("S btnGene");
			sede.setSed_generador("S");
			cods.add(3);

		} else {
			log.info("N btnGene");
			sede.setSed_generador("N");
		}
		if (request.getParameter("btnTrans") != null) {
			log.info("S btnTrans");
			sede.setSed_transportador("S");
			cods.add(4);
		} else {
			log.info("N btnTrans");
			sede.setSed_transportador("N");
		}
		if (request.getParameter("btnGes") != null) {
			log.info("S btnGes");
			sede.setSed_gestor("S");
			cods.add(5);
		} else {
			log.info("N btnGes");
			sede.setSed_gestor("N");
		}

		try {

			log("\n*****************Try Registration of Empresa=" + sede.getSed_nombre());
			sedeDAO.registerSede();
			int idE = Integer.parseInt(request.getParameter("txtEmpr"));
			Long sedes = sedeDAO.contSedes(idE);
			int cSedes = sedes.intValue();
			while ((nUsu = usrDAO.getNewUsu()) == null) {
				usrDAO.initNewUsu();

			}
			int idAut = (int) sesion.getAttribute("idUsuario");
			nUsu.setId_usr_aut(idAut);
			Empresa empresa = em.find(Empresa.class, Integer.parseInt(String.valueOf(sede.getSed_empresa())));
			nUsu.setUsr_nombre(empresa.getEmp_numero_documento() + "_00" + cSedes);
			nUsu.setUsr_clave(empresa.getEmp_numero_documento() + "_00" + cSedes);
			nUsu.setUsr_id_sede(sede.getSed_id());
			nUsu.setUsr_apellido(String.valueOf(empresa.getEmp_numero_documento()));
			nUsu.setUsr_nom_persona(empresa.getEmp_numero_documento() + "_00" + cSedes);
			nUsu.setUsr_documento(00+cSedes);
			nUsu.setUsr_email(empresa.getEmp_razon_social()+"@"+empresa.getEmp_nombre_comercial()+".com");
			nUsu.setUsr_telefono(String.valueOf(empresa.getEmp_telefono()));
			nUsu.setUsr_tip_ident(3);
			nUsu.setUsr_estado(1);
			nUsu.setNumero_sesion(0);
			usrDAO.registerUsuario();
			Usuario_Perfil usuP;

			for (Integer inte : cods) {
				usuP = null;
				while ((usuP = usrDAO.getNewUsuPer()) == null) {
					userDao.initNewUsuPer();
				}
				log.info(inte + "- perfil /" + nUsu.getId_usr() + "- Usuario ");
				usuP.setId_usu_per(0);
				usuP.setId_perfil(inte);
				usuP.setId_usr(nUsu.getId_usr());
				usrDAO.registerUsuPer();

			}

			request.setAttribute("infoMessage", sede.getSed_nombre() + " Registrado correctamente!");

			sede = null;
		} catch (Exception e) {
			// TODO: handle exception
			Throwable t = e;
			while ((t.getCause()) != null) {
				t = t.getCause();
				log.info(t + "");
			}
		} finally {

			request.setAttribute("errorMessage", errorMessage.toString());
			RequestDispatcher resultView = request.getRequestDispatcher(ruta);
			resultView.forward(request, response);
		}
	}

	private void crearEmpresa(HttpServletRequest request, HttpServletResponse response, String ruta)
			throws ServletException, IOException, SQLException {
		StringBuilder errorMessage = new StringBuilder();
		HttpSession sesion = request.getSession();
		Empresa empresa;

		while ((empresa = empDao.getNewEmpr()) == null) {
			empDao.initNewEmpr();

		}
		try {

			empresa.setEmp_razon_social(request.getParameter("txtRaSoc"));
			empresa.setEmp_nombre_comercial(request.getParameter("txtNomComer"));
			String nit = request.getParameter("txtNit");
			BigInteger nitn = new BigInteger(nit);
			empresa.setEmp_numero_documento(nitn);
			empresa.setEmp_direccion(request.getParameter("txtDirecc"));
			empresa.setEmp_ciiu4(request.getParameter("txtCiiu"));
			empresa.setEmp_id_aut(Integer.parseInt(sesion.getAttribute("idUsuario")+""));
			empresa.setEmp_id_ubica(Integer.parseInt(request.getParameter("txtDepar")));
			empresa.setEmp_municipio(Integer.parseInt(request.getParameter("txtMun")));
			empresa.setEmp_ciiu4(request.getParameter("txtCiiu"));
			empresa.setEmp_telefono(new BigInteger(request.getParameter("txtTel")));
			empresa.setEmp_ext(Integer.parseInt(request.getParameter("txtExt")));
			empresa.setEmp_rep_nombre(request.getParameter("txtRepr"));
			empresa.setEmp_rep_email(request.getParameter("txtEmail"));
			empresa.setEmp_cc_represen(Integer.parseInt((request.getParameter("txtReprCed"))));
			log("\n*****************Try Registration of Empresa=" + empresa);
			empDao.registerEmp();
			request.setAttribute("infoMessage", empresa.getEmp_razon_social() + " Registrado correctamente!");

		} catch (Exception e) {
			// TODO: handle exception
			Throwable t = e;
			while ((t.getCause()) != null) {
				t = t.getCause();
				log.info(t + "");
			}
		} finally {

			request.setAttribute("errorMessage", errorMessage.toString());
			RequestDispatcher resultView = request.getRequestDispatcher(ruta);
			resultView.forward(request, response);
		}

	}

	// Pantalla de Login
	protected void dirigir(HttpServletRequest request, HttpServletResponse response, String ruta)
			throws ServletException, IOException {

		dis = request.getRequestDispatcher(ruta);
		dis.forward(request, response);
	}

	private void redirigir(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {

		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession();

		log.info(sesion.getAttribute("perfil") + "Perfil Activo");
		String pr = String.valueOf(sesion.getAttribute("perfil")); 
		int nSesion = Integer.parseInt(String.valueOf(sesion.getAttribute("nSesion")));
		int idUsu = Integer.parseInt(String.valueOf(sesion.getAttribute("idUsuario")));
		if(nSesion == 0)
		{
			combo.darListaTipoDocumento();
			userDao.primerSesUsu(idUsu);
			request.getRequestDispatcher("view/primero/Update.jsp").forward(request, response);			
		}
		else if(nSesion == 1)
		{
			switch (pr) {

			case "1":
				dashDAO.declaracionDeAut(idUsu);
				Tuple declas =dashDAO.getDeclarAut();
				this.ordenar(declas, sesion);
				request.getRequestDispatcher("view/Autoridad/autoridad.jsp").forward(request, response);
				break;

			case "2":
				dashDAO.declasIdeam();
				request.getRequestDispatcher("controlIDEAM?action=ideam").forward(request, response);

				break;

			case "3":

				System.out.println("entrando al controlador de generador");
				request.getRequestDispatcher("controlGenerador?action=generador").forward(request, response);

				break;

			case "4":
				
				request.getRequestDispatcher("controlTrans?action=transp").forward(request, response);

				break;

			case "5":
				request.getRequestDispatcher("view/gestor/gestor.jsp").forward(request, response);
				break;
			}
			
		}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Fallo metdo redirigir : " + e.getMessage());
            e.fillInStackTrace();
		}


	}
	


	private void ordenar(Tuple declas,HttpSession sesion) {
		// TODO Auto-generated method stub
		
		if(declas==null)
		{
			sesion.setAttribute("enviadasAut",   0);
			sesion.setAttribute("noEnviadasAut", 0);
			sesion.setAttribute("aprovadasAut",  0);
			sesion.setAttribute("rechasadasTAut",0);
			sesion.setAttribute("finalizadasAut",0);
			sesion.setAttribute("rechasadasGAut",0);
			sesion.setAttribute("gendhSedesAut", 0);
			sesion.setAttribute("trndhSedesAut", 0);
			sesion.setAttribute("gestordhSedesAut", 0);
			sesion.setAttribute("SedesAut", 0);		
		}else
		{
			sesion.setAttribute("enviadasAut", declas.get(0)==null ? 0 : declas.get(0));  
			sesion.setAttribute("noEnviadasAut", declas.get(1)==null ? 0 : declas.get(1));  
			sesion.setAttribute("aprovadasAut",  declas.get(2)==null ? 0 : declas.get(2));  
			sesion.setAttribute("rechasadasTAut",declas.get(3)==null ? 0 : declas.get(3));  
			sesion.setAttribute("finalizadasAut",declas.get(4)==null ? 0 : declas.get(4));  
			sesion.setAttribute("rechasadasGAut",declas.get(5)==null ? 0 : declas.get(5));  
			sesion.setAttribute("gendhSedesAut",declas.get(6)==null ? 0 : declas.get(6));   
			sesion.setAttribute("trndhSedesAut",declas.get(7)==null ? 0 : declas.get(7));   
			sesion.setAttribute("gestordhSedesAut",declas.get(8)==null ? 0 : declas.get(8));
			sesion.setAttribute("SedesAut",declas.get(9)==null ? 0 : declas.get(9));	    
	
		}
		
		
		
		
		
		
		
		
		
		
	}

	private void finalizaOrden(HttpSession sesion) {
		// TODO Auto-generated method stub
				// TODO Auto-generated method stub


					sesion.removeAttribute("dashEnviadas");
					sesion.removeAttribute("dashNoEnviadas");
					sesion.removeAttribute("dashAprovadas");
					sesion.removeAttribute("dashRechasadas");
					sesion.removeAttribute("dashFinalizadas");
					sesion.removeAttribute("dashRechaGes");
					sesion.removeAttribute("dashPrograma");			
				
	
		
	}

	protected void ingresar(HttpServletRequest request, HttpServletResponse response, String ruta)
			throws ServletException, IOException, Exception {
		try {
			
		
		HttpSession sesion = request.getSession();
		if (sesion.getAttribute("perfil") == null) {
			String user = request.getParameter("txtUser");
			String pass = request.getParameter("txtPass");
			if (user != null && pass != null) {
				Usuario usua = userDao.ingresar(user, pass);
				if (usua == null) {
					log.info("usuario no existe");
					request.setAttribute("msjClave", "Usuario o Contraseña incorreectos");
					dis = request.getRequestDispatcher("view/login.jsp");
					dis.forward(request, response);
				}
				log.info(usua.toString());
				List<Usuario_Perfil> lPerfiles = usua.getUsuaPer();
				log.info("Prueba login nuevo 1");
				Usuario_Perfil perfil1 = lPerfiles.get(0);
				log.info(perfil1.toString());
//				sesion.setAttribute("perfil", usua.getId_usr_perfil());
				int idP = perfil1.getId_perfil();
				log.info(idP+"");
//						Integer.parseInt(usua.getId_usr_perfil());
				if (idP == 1 || idP == 2) {
					log.info("cuenta Autoridad");
					Autoridad aut;
					aut = userDao.datosAutoridad(usua.getId_usr());
					sesion.setAttribute("usuario", aut.getAut_nombre());
					sesion.setAttribute("idUsuario", aut.getId_aut());
					sesion.setAttribute("correo", aut.getAut_nombre());
					sesion.setAttribute("nomPersona", aut.getAut_nombre()); //
					sesion.setAttribute("nom_comerc", aut.getAut_nombre()); //
					sesion.setAttribute("direccion", aut.getAut_direccion()); //
					sesion.setAttribute("apellido", aut.getAut_nombre());
					sesion.setAttribute("perfil", perfil1.getId_perfil());
					sesion.setAttribute("departamento", departamento); //
					sesion.setAttribute("telefono", aut.getAut_numero1()); //
					sesion.setAttribute("doc", aut.getAut_nombre()); //
					sesion.setAttribute("nom_epresa", aut.getAut_nombre());
					sesion.setAttribute("nSesion", usua.getNumero_sesion());
//					dash = dashDAO.cantResiduoDashboardAut(idUser);
					this.redirigir(request, response);
				} else {
					Usuario listUsu;
					log.info("passw " + pass + " / " + "user " + user);
					log.info("vamos a usuario dao");
					listUsu = userDao.LoginUsu(user, pass);
					
						usuario = listUsu;
						sede = listUsu.getSedeUsu();
						empre = listUsu.getSedeUsu().getEmpresaSed();
						lPerfilLog = listUsu.getUsuaPer();

					sesion.setAttribute("usuario", usuario.getUsr_nombre());
					sesion.setAttribute("nSesion", usuario.getNumero_sesion());
					sesion.setAttribute("idUsuario", usuario.getId_usr());
					sesion.setAttribute("correo", usuario.getUsr_email());
					sesion.setAttribute("nomPersona", usuario.getUsr_nom_persona()); //
					sesion.setAttribute("nom_comerc", sede.getSed_nombre()); //
					sesion.setAttribute("direccion", sede.getSed_direccion()); //
					sesion.setAttribute("apellido", usuario.getUsr_apellido());
//				sesion.setAttribute("departamento", sede.getDept_nombre()); //
					sesion.setAttribute("telefono", usuario.getUsr_telefono()); //
					sesion.setAttribute("doc", usuario.getUsr_email()); //
					sesion.setAttribute("idSede", sede.getSed_id()); // idSede
					sesion.setAttribute("nom_epresa", empre.getEmp_nombre_comercial());
					sesion.setAttribute("idEpresa", empre.getEmp_id());
					
					Usuario_Perfil per = lPerfilLog.get(0);
					log.info("" + per);
					sesion.setAttribute("perfil", per.getId_perfil());
					log.info(String.valueOf(sesion.getAttribute("perfil")));
					List<Usuario_Perfil> lUsP = usuario.getUsuaPer();
					log.info(lUsP+"");
//					for (Usuario_Perfil usPel : lUsP) {
//						usPel.getPerUsu().getPerfil();
//					}
					sesion.setAttribute("perfiles", usuario.getUsuaPer());
					log.info("" + sesion.getAttribute("perfiles"));
					log.info("" + sesion.getAttribute("perfil"));
					userDao.inPerfilSeleccionado(per.getId_perfil(), per.getId_usr());
					
					
					this.redirigir(request, response);
				}
			} else {

				request.setAttribute("msjClave", "Usuario o Contraseña incorreectos");
				dis = request.getRequestDispatcher("view/login.jsp");
				dis.forward(request, response);
			}
		} 
		else {
			ruta = "view/login.jsp";
			this.redirigir(request, response);
		}
		} catch (Exception e) {

			 System.out.println("Fallo metdo ingresar1 : " + e.getMessage());
	            e.fillInStackTrace();
		}

	}




	// Pantalla de principal autoridad
	protected void autoridad(HttpServletRequest request, HttpServletResponse response, String ruta)
			throws ServletException, IOException {

		Usuario us = new Usuario();
		us.setUsr_nombre(request.getParameter("txtUser"));

		String usuario = us.getUsr_nombre();
		System.out.println("usuario____:  " + us.getUsr_nombre());
		// request.setAttribute("msjClave", "Clave Incorrecta");
		// ${msjUsuario}

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
