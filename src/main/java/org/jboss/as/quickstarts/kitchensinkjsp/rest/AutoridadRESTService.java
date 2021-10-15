package org.jboss.as.quickstarts.kitchensinkjsp.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import co.gov.ideam.sdstrp.controller.DeclaracionDAO;
import co.gov.ideam.sdstrp.controller.EmpresasDao;
import co.gov.ideam.sdstrp.controller.SedeDAO;
import co.gov.ideam.sdstrp.controller.UserDao;
import co.gov.ideam.sdstrp.util.BooleanTypeAdapter;
import co.gov.ideam.sdstrp.util.DeclarAdapter;
import co.gov.ideam.sdstrp.util.EmpresaAdapter;
import co.gov.ideam.sdstrp.util.SedeAdapter;
import co.gov.ideam.sdstrp.util.UsuaAdapter;

import co.gov.ideam.sdstrp.model.Declaracion;
import co.gov.ideam.sdstrp.model.Empresa;
import co.gov.ideam.sdstrp.model.Residuos;
import co.gov.ideam.sdstrp.model.Sede;
import co.gov.ideam.sdstrp.model.Usuario;
import co.gov.ideam.sdstrp.servlet.control;

/**
 * Servlet implementation class AutoridadRESTService
 */
@WebServlet( "/autoridadServlet" )
public class AutoridadRESTService extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoridadRESTService() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Inject
    private Logger log;
    
    @Inject
    private EntityManager em;
    
    @Inject
    private EmpresasDao empDao;
    
    @Inject
    private EmpresaAdapter empAdp;
    
    @Inject
    private SedeDAO sedDao;
    @Inject
	private SedeAdapter sedAdp;
    
    @Inject
    private DeclaracionDAO decDao;
    
    @Inject
    private DeclarAdapter decAdp;
    
    @Inject
    private UserDao usuDao;
    
    @Inject
    private UsuaAdapter usuAdp;
    
    private List<Empresa> emp;
    
    
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		response.setContentType("application/json");
		HttpSession sesion = request.getSession();
		String action = request.getParameter("action");
		
		switch (action) {
		case "autEmpresa":
			
		this.autEmpresa(request, response, sesion);
			break;
		case "autEmpresaUpt":
			
		this.autEmpresaUpt(request, response, sesion);
			break;
			
		case "autSedes":
			
			this.autSedes(request, response, sesion);
				break;
		case "autSedesUpd":
			
			this.autSedesUpd(request, response, sesion);
				break;	
			case "autSedesCrea":
			this.autSedesCrea(request, response, sesion);
				break;
		case "autUsu":
			
			this.autUsu(request, response, sesion);
				break;
		case "autUsuUpC":
			
			this.autUsuUpC(request, response, sesion);
				break;
		case "autUsDes":
			
			this.autUsuDes(request, response, sesion);
				break;	
		case "autHist":
			
			this.autHist(request, response, sesion);
				break;
			case "autHistData":
			
			this.autHistData(request, response, sesion);
				break;				
		case "outExcel":
			
			this.outExcel(request, response, sesion);
				break;
		}
	}



	private void autSedesCrea(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
		// TODO Auto-generated method stub
		
	}



	private void autHistData(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
		// TODO Auto-generated method stub
		int idAu = Integer.parseInt(String.valueOf(sesion.getAttribute("perfil")));
		int idDec = Integer.parseInt(request.getParameter("dec_id"));
		List<Declaracion> decId = decDao.verDeclaracionid(idAu,idDec);
		final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").registerTypeAdapter(Declaracion.class, new BooleanTypeAdapter()).create();
	    response.setContentType("application/json");
	    JsonElement lJDecAu = decAdp.crearId(decId);
	    JsonObject data = new JsonObject();
	    data.add("data", lJDecAu);
	    int total = decId.size();
	    data.addProperty("total", total);
		try {
			response.getWriter().write( gson.toJson(data));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void autHist(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
		// TODO Auto-generated method stub
		try {
			 
			int idAu = Integer.parseInt(String.valueOf(sesion.getAttribute("perfil")));
			int take = request.getParameter("take")!=null? Integer.parseInt(request.getParameter("take")):0;
			int skip = request.getParameter("skip")!=null?Integer.parseInt(request.getParameter("skip")):0;
			List<Declaracion> dec = decDao.listDeclaAuto(idAu,take,skip);
			int total = dec.size();
			final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").registerTypeAdapter(Declaracion.class, new BooleanTypeAdapter()).create();
		    response.setContentType("application/json");
		    JsonElement lJDecAu = decAdp.crear(dec);
		    JsonObject data = new JsonObject();
		    data.add("data", lJDecAu);
		    data.addProperty("total", total);
			response.getWriter().write( gson.toJson(data));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	public static String getBody(HttpServletRequest request) throws IOException {

	    String body = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    BufferedReader bufferedReader = null;

	    try {
	        InputStream inputStream = request.getInputStream();
	        if (inputStream != null) {
	            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	            char[] charBuffer = new char[128];
	            int bytesRead = -1;
	            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	                stringBuilder.append(charBuffer, 0, bytesRead);
	            }
	        } else {
	            stringBuilder.append("");
	        }
	    } catch (IOException ex) {
	        throw ex;
	    } finally {
	        if (bufferedReader != null) {
	            try {
	                bufferedReader.close();
	            } catch (IOException ex) {
	                throw ex;
	            }
	        }
	    }

	    body = stringBuilder.toString();
	    return body;
	}
	
	

	private void autUsuDes(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
		// TODO Auto-generated method stub
		try {
			int nestado;
			int id = Integer.parseInt(request.getParameter("id_usr"));
			int est = Integer.parseInt(request.getParameter("est"));
			Usuario us = em.find(Usuario.class, id);
			if(est==1)
			{
				nestado =0;
				us.setUsr_estado(0);
			}else {
				nestado = 1;
				us.setUsr_estado(1);}
			
			usuDao.updtUsuario(us);
			JsonObject res = new JsonObject();
			final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").registerTypeAdapter(Usuario.class, new BooleanTypeAdapter()).create();
			res.addProperty("success", true);
			res.addProperty("estado", nestado);
			response.getWriter().write( gson.toJson(res));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("Fallo lista de Declaraciones: " + e.getMessage());
			e.printStackTrace();
		}
		
		
	}

	private void autUsuUpC(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws Exception {
		// TODO Auto-generated method stub
		try {
			int id = Integer.parseInt(request.getParameter("id_usr"));
			Usuario us = em.find(Usuario.class, id);
			String clave = request.getParameter("contra");
			us.setUsr_clave(request.getParameter("contra"));
			us.setNumero_sesion(0);
			usuDao.updtUsuario(us);
			JsonObject res = new JsonObject();
			final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").registerTypeAdapter(Usuario.class, new BooleanTypeAdapter()).create();
			res.addProperty("success", true);
			response.getWriter().write( gson.toJson(res));
			
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Fallo lista de Declaraciones: " + e.getMessage());
		}
		
		
	}

	private void autSedesUpd(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
		// TODO Auto-generated method stub
		try {
			int id = Integer.parseInt(request.getParameter("sed_id"));
			Sede sed = em.find(Sede.class, id);
			sed.setSed_nombre(request.getParameter("sed_nombre"));
			sed.setSed_empresa(Integer.valueOf(request.getParameter("empresa[emp_id]")));
			sed.setSed_departamento(Integer.parseInt(request.getParameter("departamento[id_dept]")));
			sed.setSed_direccion(request.getParameter("sed_direccion"));
			sed.setSed_municipio(Integer.parseInt(request.getParameter("municipio[id_munic]"))) ;
			sed.setSed_telefono_nro(new BigInteger(request.getParameter("sed_telefono_n")));
			sed.setSed_telefono_ext(Integer.parseInt(request.getParameter("sed_telefono_ext")));
			JsonObject rest = new JsonObject();
			final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").registerTypeAdapter(Empresa.class, new BooleanTypeAdapter()).create();
		if(sedDao.sedeUpd(sed))
		{
			
			rest.addProperty("success", true);
			response.getWriter().write( gson.toJson(rest));
		}
		else
		{
			rest.addProperty("success", false);
			response.getWriter().write( gson.toJson(rest));
		}
		} catch (Exception e) {
			log.info("Fallo lista de Declaraciones: " + e.getMessage());
			// TODO: handle exception
		}
	}

	private void autEmpresaUpt(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws Exception {
		// TODO Auto-generated method stub
		int idEm = Integer.parseInt(String.valueOf(request.getParameter("emp_id")));
		String ciiu = request.getParameter("ciiupr[ciipr_id]");
		int departamento = Integer.parseInt(String.valueOf(request.getParameter("departamento[id_dept]")));
		int municipio = Integer.parseInt(String.valueOf(request.getParameter("municipio[id_munic]")));
		String razon_sos = request.getParameter("emp_razon_social");
		String direccion = request.getParameter("emp_direccion");
		String nombre_com= request.getParameter("emp_nombre_comercial");
		BigInteger doc = new BigInteger(request.getParameter("emp_numero_documento")) ;
		BigInteger tel = new BigInteger(request.getParameter("emp_telefono"));
		int ext = Integer.parseInt(String.valueOf(request.getParameter("emp_ext")));
		int cc_represen = Integer.parseInt(String.valueOf(request.getParameter("emp_cc_represen")));
		String email_represen = request.getParameter("emp_rep_email");
		String nombre_represen = request.getParameter("emp_rep_nombre");
		
		Empresa empUp = em.find(Empresa.class, idEm);
		
		empUp.setEmp_ciiu4(ciiu);
		empUp.setEmp_id_ubica(departamento);
		empUp.setEmp_municipio(municipio);
		empUp.setEmp_razon_social(razon_sos);
		empUp.setEmp_nombre_comercial(nombre_com);
		empUp.setEmp_direccion(direccion);
		empUp.setEmp_numero_documento(doc);
		empUp.setEmp_telefono(tel);
		empUp.setEmp_ext(ext);
		empUp.setEmp_cc_represen(cc_represen);
		empUp.setEmp_rep_email(email_represen);
		empUp.setEmp_rep_nombre(nombre_represen);
		
		emp = new ArrayList<Empresa>();
		emp.set(0, empUp);
		empDao.updEmp(empUp); 
		JsonArray lJsonEmp = empAdp.formatEmpres(emp);
		JsonObject rest = new JsonObject();
		final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").registerTypeAdapter(Empresa.class, new BooleanTypeAdapter()).create();
		rest.addProperty("success", true);
		rest.add("object",lJsonEmp.get(0));
		response.getWriter().write( gson.toJson(rest));
	}

	private void autUsu(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
		// TODO Auto-generated method stub
	    try {
			int idAut = Integer.parseInt(String.valueOf(sesion.getAttribute("idUsuario")));
			List<Usuario> usu = usuDao.usuAutoridad(idAut);
	    	final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").registerTypeAdapter(Usuario.class, new BooleanTypeAdapter()).create();
		    response.setContentType("application/json");
		    JsonArray lJsonUsu = usuAdp.formatUsu(usu);
			response.getWriter().write( gson.toJson(lJsonUsu));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void outExcel(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
		// TODO Auto-generated method stub
		String fileName = request.getParameter("fileName");
		response.setHeader("Content-Disposition", "attachment;filename="
                + fileName);
		
	}	
       


	private void autEmpresa(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
		// TODO Auto-generated method stub
	    try {
			int idAut = Integer.parseInt(String.valueOf(sesion.getAttribute("perfil")));
			List<Empresa> emp = empDao.empresasAutoridadJson(idAut);
	    	final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").registerTypeAdapter(Residuos.class, new BooleanTypeAdapter()).create();
		    response.setContentType("application/json");
		    JsonArray lJsonEmp = empAdp.formatEmpres(emp);
			response.getWriter().write( gson.toJson(lJsonEmp));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void autSedes(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
		// TODO Auto-generated method stub
	    try {
			int idAut = Integer.parseInt(String.valueOf(sesion.getAttribute("idUsuario")));
			List<Sede> sed = sedDao.sedesAutoJs(idAut);
	    	final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").registerTypeAdapter(Residuos.class, new BooleanTypeAdapter()).create();
		    response.setContentType("application/json");
		    JsonArray lJsonSed = sedAdp.formatSedes(sed);
			response.getWriter().write( gson.toJson(lJsonSed));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (Exception ex) {

			Logger.getLogger(control.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (Exception ex) {

			Logger.getLogger(control.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
