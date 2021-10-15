/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.ideam.sdstrp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.persistence.Tuple;

import co.gov.ideam.sdstrp.controller.DashDao;

/**
 *
 * @author Islena
 */
@WebServlet(name = "controlIDEAM", urlPatterns = {"/controlIDEAM"})
public class controlIDEAM extends HttpServlet {

    private RequestDispatcher dis;
    
    @Inject
    private DashDao dasDao;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession();
        String ruta = "";
        String action = request.getParameter("action");
        switch (action) {

            case "ideam":
                ruta = "view/ideam/ideam.jsp";
                if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.ideam(request, response, ruta);
    			} else {

    				this.ordenar(sesion);
    	               this.ideam(request, response, ruta);
    			}
                break;
            case "ideamClave":
            	 ruta = "view/primero/UpdateClv.jsp";
                 if (sesion.getAttribute("perfil") == null) {
     				ruta = "view/login.jsp";
     	               this.ideam(request, response, ruta);
     			} else {
     	               this.ideam(request, response, ruta);
     			}
                break;
            case "ideamEmpresaLeer":
                ruta = "view/ideam/empresa/Read.jsp";
                if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.ideam(request, response, ruta);
    			} else {
    	               this.ideam(request, response, ruta);
    			}
                break;
            case "ideamEmpresaLista":
                ruta = "view/ideam/empresa/Read.jsp";
                if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.ideam(request, response, ruta);
    			} else {
    	               this.ideam(request, response, ruta);
    			}
                break;

            case "ideamSedeLee":
                ruta = "view/ideam/sede/Read.jsp";
                if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.ideam(request, response, ruta);
    			} else {
    	               this.ideam(request, response, ruta);
    			}
                break;
            case "ideamSedeLista":
                ruta = "view/ideam/sede/List.jsp";
                if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.ideam(request, response, ruta);
    			} else {
    	               this.ideam(request, response, ruta);
    			}
                break;
            case "ideamUsuLista":
                ruta = "view/ideam/usuario/List.jsp";
                if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.ideam(request, response, ruta);
    			} else {
    	               this.ideam(request, response, ruta);
    			}
                break;
            case "ideamDeclaLista":
                ruta = "view/ideam/declaracion/List.jsp";
                if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.ideam(request, response, ruta);
    			} else {
    	               this.ideam(request, response, ruta);
    			}
                break;
            case "ideamDeclaLeer":
                ruta = "view/ideam/declaracion/Read.jsp";
                if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.ideam(request, response, ruta);
    			} else {
    	               this.ideam(request, response, ruta);
    			}
                break;
            case "ideamIndiLista":
                ruta = "view/ideam/indicador/List.jsp";
                if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.ideam(request, response, ruta);
    			} else {
    	               this.ideam(request, response, ruta);
    			}
                break;
            case "ideamIndiCrea":
                ruta = "view/ideam/indicador/Create.jsp";
                if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.ideam(request, response, ruta);
    			} else {
    	               this.ideam(request, response, ruta);
    			}
                break;
            case "ideamSalidaLista":
                ruta = "view/ideam/salida/List.jsp";
                if (sesion.getAttribute("perfil") == null) {
    				ruta = "view/login.jsp";
    	               this.ideam(request, response, ruta);
    			} else {
    	               this.ideam(request, response, ruta);
    			}
                break;


            /*
                usuarioLista        
                declaraLista
                indiLista
                salidaList*/
        }
    }
    
	private void ordenar(HttpSession sesion) {
		// TODO Auto-generated method stub
			
		if(dasDao.getDeclarIdeam()==null)
		{
			sesion.setAttribute("enviadasIdm",  0) ;
			sesion.setAttribute("noEnviadasIdm",0) ;
			 sesion.setAttribute("aprovadasIdm",0) ;
		   sesion.setAttribute("rechasadasTIdm",0) ;
		   sesion.setAttribute("finalizadasIdm",0) ;
		   sesion.setAttribute("rechasadasGIdm",0) ;
			sesion.setAttribute("gendhSedesIdm",0) ;
			sesion.setAttribute("trndhSedesIdm",0) ;
		 sesion.setAttribute("gestordhSedesIdm",0) ;
		 sesion.setAttribute("gestordhSedesIdm",0) ;
		 sesion.setAttribute("SedesIdm",0) ;
			
		}
		else
		{
			Tuple declas =  dasDao.getDeclarIdeam();
			sesion.setAttribute("enviadasIdm",  declas.get(0)==null ? 0 :  declas.get(0));
			sesion.setAttribute("noEnviadasIdm",declas.get(1)==null ? 0 :  declas.get(1));
			 sesion.setAttribute("aprovadasIdm",declas.get(2)==null ? 0 :  declas.get(2));
		   sesion.setAttribute("rechasadasTIdm",declas.get(3)==null ? 0 :  declas.get(3));
		   sesion.setAttribute("finalizadasIdm",declas.get(4)==null ? 0 :  declas.get(4));
		   sesion.setAttribute("rechasadasGIdm",declas.get(5)==null ? 0 :  declas.get(5));
			sesion.setAttribute("gendhSedesIdm",declas.get(6)==null ? 0 :  declas.get(6));
			sesion.setAttribute("trndhSedesIdm",declas.get(7)==null ? 0 :  declas.get(7));
		 sesion.setAttribute("gestordhSedesIdm",declas.get(8)==null ? 0 :  declas.get(8));		 
		 sesion.setAttribute("SedesIdm",declas.get(9)==null ? 9 :  declas.get(9));
			
		}
		
		  
		
	}

    //Pantalla de ideam
    protected void ideam(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de ideam Cambio de clave
    protected void ideamClave(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de ideam Lista empresas asociadas 
    protected void ideamEmpresaLeer(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de ideam Lista empresas asociadas 
    protected void ideamEmpresaLista(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de ideam  sede asociadas 
    protected void ideamSedeLee(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de ideam  sede asociadas 
    protected void ideamSedeLista(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de ideam Lista sede asociadas 
    protected void ideamUsuLista(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de ideam Historico 
    protected void ideamDeclaLista(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de ideam Historico 
    protected void ideamDeclaLeer(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de ideam Indicadores 
    protected void ideamIndiLista(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de ideam Indicadores 
    protected void ideamIndiCrea(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    //Pantalla de ideam Salidas 
    protected void ideamSalidaLista(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
