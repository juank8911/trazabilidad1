package co.gov.ideam.sdstrp.servlet;


import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


@WebServlet("/userser")
public class userServlet extends HttpServlet {

	  @Inject
	    private Logger log;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public userServlet() {
		
	}
	
	public void ingresar (String user, String pass) {
		
		log.info("si estamos adentro");
		
	}
	
	

}
