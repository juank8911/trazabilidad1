package co.gov.ideam.sdstrp.servlet;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import co.gov.ideam.sdstrp.controller.ConsultaComboDAO;
import co.gov.ideam.sdstrp.util.BooleanTypeAdapter;
import co.gov.ideam.sdstrp.util.ComboAdapter;
import co.gov.ideam.sdstrp.util.EstMatAdapter;
import co.gov.ideam.sdstrp.util.GesUbicaAdapter;
import co.gov.ideam.sdstrp.util.ResiduosAdapter;
import co.gov.ideam.sdstrp.util.TResiduosAdapter;
import co.gov.ideam.sdstrp.util.TiGestAdapter;
import co.gov.ideam.sdstrp.util.TiManAdapter;

import co.gov.ideam.sdstrp.model.Ciiu4;
import co.gov.ideam.sdstrp.model.Departamento;
import co.gov.ideam.sdstrp.model.EstadoMateria;
import co.gov.ideam.sdstrp.model.GestionUbicacion;
import co.gov.ideam.sdstrp.model.Municipio;
import co.gov.ideam.sdstrp.model.Residuos;
import co.gov.ideam.sdstrp.model.TipoGestion;
import co.gov.ideam.sdstrp.model.TipoManejo;
import co.gov.ideam.sdstrp.model.TipoResiduos;

/**
 * Servlet implementation class CombosServlet
 */

@WebServlet(name = "combServlet", urlPatterns = { "/combServlet" })
public class CombosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CombosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Inject
    private Logger log;
    @Inject
    private ConsultaComboDAO combDAO;
    @Inject
    private ComboAdapter combAdp;
    
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession sesion = request.getSession();
		String action = request.getParameter("action");
		
		switch (action) {
		case "combTRes":
			
			this.tipResi(request,response);
			break;
		case "combTMat":
			
			this.estMat(request,response);

			break;
		case "combGUbica":
			
			this.combGubica(request,response);

			break;
		case "combTGestion":
			
			this.combTGes(request,response);

			break;
		case "combTMa":
			this.combTMa(request,response);
			break;
		case "combDepa":
			this.combDepa(request,response);
			break;
		case "combMuni":
			this.combMuni(request,response);
			break;
		case "combCiiu":
			this.combCiiu(request,response);
			break;
			
				
		default:
			break;
		}
		
	}

	private void combMuni(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String filter = request.getParameter("filter");
			int idDep = Integer.parseInt(request.getParameter("filter[filters][0][value]"));
			List<Municipio> muni = combDAO.verListaMuniDepa(idDep);
			final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").registerTypeAdapter(Municipio.class, new BooleanTypeAdapter()).create();
			response.setContentType("application/json");
		    JsonArray lJsonMuni = combAdp.formatMuni(muni);
			response.getWriter().write( gson.toJson(lJsonMuni));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void combCiiu(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	    try {
			List<Ciiu4> ciiu = combDAO.getListCiiu();
			final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").registerTypeAdapter(Ciiu4.class, new BooleanTypeAdapter()).create();
		    response.setContentType("application/json");
		    JsonArray lJsonCiiu = combAdp.formatCiiu(ciiu);
			response.getWriter().write( gson.toJson(lJsonCiiu));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void combDepa(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	    try {
			List<Departamento> depa = combDAO.getListaDepar();
			final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").registerTypeAdapter(Departamento.class, new BooleanTypeAdapter()).create();
		    response.setContentType("application/json");
		    JsonArray lJsonSed = combAdp.formatDep(depa);
			response.getWriter().write( gson.toJson(lJsonSed));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void combTMa(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		List<TipoManejo> tMan = combDAO.getListaTipoManejo();
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.registerTypeAdapter(TipoManejo.class, new TiManAdapter()).create();
		response.setContentType("application/json");
	    log.info(tMan+"");
	    log.info(gson.toJson(tMan)+"");
	    response.getWriter().write( gson.toJson(tMan));
		
		
	}

	private void combTGes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		List<TipoGestion> tGes = combDAO.getListaTipoGestion();
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.registerTypeAdapter(TipoGestion.class, new TiGestAdapter()).create();
		response.setContentType("application/json");
	    log.info(tGes+"");
	    log.info(gson.toJson(tGes)+"");
	    response.getWriter().write( gson.toJson(tGes));		
		
	}

	private void combGubica(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		List<GestionUbicacion> gUbica = combDAO.getListaGestionUbicacion();
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.registerTypeAdapter(GestionUbicacion.class, new GesUbicaAdapter()).create();
		response.setContentType("application/json");
	    log.info(gUbica+"");
	    log.info(gson.toJson(gUbica)+"");
	    response.getWriter().write( gson.toJson(gUbica));
		
	}

	private void estMat(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		List<EstadoMateria> eMat = combDAO.getListaEstadoMateria();
		GsonBuilder gsonBuilder = new GsonBuilder();
	    Gson gson = gsonBuilder.registerTypeAdapter(EstadoMateria.class, new EstMatAdapter()).create();
	    response.setContentType("application/json");
	    log.info(eMat+"");
	    log.info(gson.toJson(eMat)+"");
	    response.getWriter().write( gson.toJson(eMat));
		
	}

	private void tipResi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		List<TipoResiduos> tRes = combDAO.getListTiResiduos();
		GsonBuilder gsonBuilder = new GsonBuilder();
	    Gson gson = gsonBuilder.registerTypeAdapter(TipoResiduos.class, new TResiduosAdapter()).create();
	    response.setContentType("application/json");
	    log.info(tRes+"");
	    log.info(gson.toJson(tRes)+"");
	    response.getWriter().write( gson.toJson(tRes));
		
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
