package co.gov.ideam.sdstrp.servlet;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.Tuple;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import co.gov.ideam.sdstrp.controller.DeclaracionDAO;
import co.gov.ideam.sdstrp.util.BooleanTypeAdapter;
import co.gov.ideam.sdstrp.util.DeclaResiAdapter;
import co.gov.ideam.sdstrp.util.DeclarAdapter;
import co.gov.ideam.sdstrp.util.RepMesGenAdapter;

import co.gov.ideam.sdstrp.model.Declaracion;
import co.gov.ideam.sdstrp.model.DeclaracionResiduo;

/**
 * Servlet implementation class InformesServlet
 */

@WebServlet(name = "informesServlet", urlPatterns = { "/informesServlet" })
public class InformesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InformesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Inject
    private Logger log;
    
    @Inject
    private DeclaracionDAO decDao;
    
    @Inject
    private DeclarAdapter decADP;
    
    @Inject
    private DeclaResiAdapter deReADP;
    
    @Inject
    private RepMesGenAdapter reMesADP;
    
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		response.setContentType("application/json");
		HttpSession sesion = request.getSession();
		String action = request.getParameter("action");
		
		switch (action) {
		case "genRepMen":
			
		this.genRepMens(request, response, sesion);
			break;
		case "histLisGen":
			
			this.histListgen(request, response, sesion);
				break;
			case "salida":
			
			this.salidaGen(request, response, sesion);
				break;
		case "salidaDecRes":
			
			this.verDeclaResIdDec(request, response, sesion);
				break;
		default:
			break;
		case "outExcel":
			
			this.outExcel(request, response, sesion);
				break;
		}
	}

	private void outExcel(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
		// TODO Auto-generated method stub
		String fileName = request.getParameter("fileName");
		log.info(fileName);
		response.setHeader("Content-Disposition", "attachment;filename="
                + fileName);
		
	}
	
	private void salidaGen(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws IOException {
		// TODO Auto-generated method stub
		int idS = Integer.parseInt(String.valueOf(sesion.getAttribute("idSede")));
        int idP = Integer.parseInt(String.valueOf(sesion.getAttribute("perfil")));
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.registerTypeAdapter(Declaracion.class, new DeclarAdapter()).create();
		List<Declaracion> liDe =	decDao.SelectDeclHistorial(idS, idP);
		log.info(liDe.toString());
		JsonArray declaraciones = (JsonArray) decADP.crear(liDe);
		log.info(declaraciones.toString());
		JsonObject des = new JsonObject();
		des.addProperty("data", declaraciones.toString());
		log.info(des.toString());
		response.setContentType("application/json");
		response.getWriter().write( gson.toJson(des));
		
	}

	private void histListgen(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws IOException {
		// TODO Auto-generated method stub
		int idS = Integer.parseInt(String.valueOf(sesion.getAttribute("idSede")));
        int idP = Integer.parseInt(String.valueOf(sesion.getAttribute("perfil")));
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.registerTypeAdapter(Declaracion.class, new DeclarAdapter()).create();
		List<Declaracion> liDe =	decDao.SelectDeclHistorial(idS, idP);
		
		log.info(liDe.toString());
		for (Declaracion declaracion : liDe) {
			log.info(declaracion.getDecSedTran().getEmpresaSed().getEmp_nombre_comercial());
			log.info(declaracion.getDecSedGen().getEmpresaSed().getEmp_nombre_comercial());
			log.info(declaracion.getDecSedGes().getEmpresaSed().getEmp_nombre_comercial());
		}
		JsonArray declaraciones = (JsonArray) decADP.crear(liDe);
		log.info(declaraciones.toString());
		response.setContentType("application/json");
		response.getWriter().write( gson.toJson(declaraciones));
		
	}
	

	private void verDeclaResIdDec(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws IOException {
	
			int idD = Integer.parseInt(String.valueOf(request.getParameter("dec_id")));
			List<DeclaracionResiduo> deRe = decDao.DarDeclracionResiduoid(idD);
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.registerTypeAdapter(Boolean.class, new DeclaResiAdapter()).create();
			JsonArray deReId = (JsonArray) deReADP.formato(deRe);
			 response.setContentType("application/json");
			 response.getWriter().write( gson.toJson(deReId));
		}

	private void genRepMens(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws IOException {
		// TODO Auto-generated method stub
		final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").registerTypeAdapter(DeclaracionResiduo.class, new BooleanTypeAdapter()).create();
		Long val = 0l;
		int idSed = Integer.parseInt(String.valueOf(sesion.getAttribute("idSede")));
		log.info(idSed+"");
		decDao.reporteMes(idSed);
		List<Tuple> repM = decDao.getListRepoMesGene();
		JsonArray declaraciones = (JsonArray) reMesADP.repoMesAdap(repM);
		 log.info(declaraciones.toString());
		 response.setContentType("application/json");
		    response.getWriter().write( gson.toJson(declaraciones));
		

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
