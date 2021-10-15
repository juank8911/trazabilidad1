package co.gov.ideam.sdstrp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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

import co.gov.ideam.sdstrp.controller.ResiduoDAO;
import co.gov.ideam.sdstrp.util.BooleanTypeAdapter;
import co.gov.ideam.sdstrp.util.ResiduosAdapter;

import co.gov.ideam.sdstrp.model.DeclaracionResiduo;
import co.gov.ideam.sdstrp.model.Residuos;


/**
 * Servlet implementation class ResiduosServlet
 */

@WebServlet(name = "residuosServlet", urlPatterns = { "/residuosServlet" })
public class ResiduosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResiduosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Inject 
    private ResiduoDAO resDAO;
    
    @Inject
    private Logger log;
    
    @Inject
    private ResiduosAdapter resAd;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		HttpSession sesion = request.getSession();
		String action = request.getParameter("action");
		
		switch (action) {
		case "residuosAll":
			this.listarResiduosAll(request,response,sesion);
			break;
		case "updateRes":
			this.updateRes(request, response,sesion);
			break;
			
		default:
			break;
		}
	}
    
    
    
    private void updateRes(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws Exception {
		// TODO Auto-generated method stub
    		int idr = Integer.parseInt(String.valueOf(request.getParameter("emp_id")));
			int id = Integer.parseInt(String.valueOf(request.getParameter("res_id")));
			Residuos resiUp = resDAO.findResiduo(id);
			resiUp.setRes_nombre(request.getParameter("res_nombre"));
			resiUp.setRes_tipo_residuo(request.getParameter("tre_nombre"));
			resiUp.setRes_estado_materia(request.getParameter("t_materia"));
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
			log.info(id+"");
	}



	private void listarResiduosAll(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) throws IOException {
		// TODO Auto-generated method stub 
		
		int idSede = Integer.parseInt(String.valueOf(sesion.getAttribute("idSede")));
		JsonArray jsArr = new JsonArray();
    	List<Residuos> resi = resDAO.getListaResiduos(idSede);
    	final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").registerTypeAdapter(Residuos.class, new BooleanTypeAdapter()).create();
	    response.setContentType("application/json");
	    log.info(resi+"");
	    if(!resi.isEmpty())
	    {
		    jsArr = resAd.adaResiduos(resi);
		    response.getWriter().write( gson.toJson(jsArr));
	    }
	    else {
	    	
	    	response.getWriter().write( gson.toJson(jsArr));
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
