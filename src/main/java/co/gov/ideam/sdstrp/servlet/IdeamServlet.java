package co.gov.ideam.sdstrp.servlet;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

/**
 * Servlet implementation class IdeamServlet
 */
@WebServlet("/IdeamServlet")
public class IdeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
    private UserDao usuDao;
    
    @Inject
    private UsuaAdapter usuAdp;
    
    @Inject
    private DeclaracionDAO decDao;
    
    @Inject
    private DeclarAdapter decAdp;
    

	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdeamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		response.setContentType("application/json");
		HttpSession sesion = request.getSession();
		String action = request.getParameter("action");
		
		switch (action) {
		case "IdeamEmpresa":
			
		this.ideamEmp(request, response, sesion);
			break;
		case "IdeamSede":
			
		this.IdeamSede(request, response, sesion);
			break;
		case "IdemUsu":
			
			this.IdemUsu(request, response, sesion);
				break;
		case "IdeHist":
			
			this.IdeHist(request, response, sesion);
				break;			

				
		case "outExcel":
			
			this.outExcel(request, response, sesion);
				break;
		}
	}
    
    
    
    
	private void IdeHist(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
		// TODO Auto-generated method stub
		try {
			 
			
			List<Declaracion> dec = decDao.listDeclaIdeam();
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


	private void IdemUsu(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
		// TODO Auto-generated method stub
	    try {
			List<Usuario> usu = usuDao.usuIdeam();
	    	final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").registerTypeAdapter(Usuario.class, new BooleanTypeAdapter()).create();
		    response.setContentType("application/json");
		    JsonArray lJsonUsu = usuAdp.formatUsu(usu);
		    JsonObject data = new JsonObject();
		    data.add("data", lJsonUsu);
		    data.addProperty("total", lJsonUsu.size());
			response.getWriter().write( gson.toJson(data));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void IdeamSede(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
		// TODO Auto-generated method stub
	    try {
			List<Sede> sed = sedDao.sedesIdeamJs();
	    	final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").registerTypeAdapter(Residuos.class, new BooleanTypeAdapter()).create();
		    response.setContentType("application/json");
		    JsonArray lJsonSed = sedAdp.formatSedes(sed);
		    JsonObject data = new JsonObject();
		    data.add("data", lJsonSed);
		    data.addProperty("total", lJsonSed.size());
			response.getWriter().write( gson.toJson(data));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void ideamEmp(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
		// TODO Auto-generated method stub
	    try {
			List<Empresa> emp = empDao.listEmpIdeam();
	    	final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").registerTypeAdapter(Residuos.class, new BooleanTypeAdapter()).create();
		    response.setContentType("application/json");
		    JsonArray lJsonEmp = empAdp.formatEmpres(emp);
		    JsonObject data = new JsonObject();
		    data.add("data", lJsonEmp);
		    data.addProperty("total", lJsonEmp.size());
			response.getWriter().write( gson.toJson(data));
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
