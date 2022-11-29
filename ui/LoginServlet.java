package com.social.ProgettoFinaleSocial.ui;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.social.ProgettoFinaleSocial.businessLogic.BusinessLogic;
import com.social.ProgettoFinaleSocial.dao.PostDAO;
import com.social.ProgettoFinaleSocial.dao.UtenteDAO;
import com.social.ProgettoFinaleSocial.exceptions.NotPresentUserException;
import com.social.ProgettoFinaleSocial.exceptions.TooManyPresentUserException;
import com.social.ProgettoFinaleSocial.model.Post;
import com.social.ProgettoFinaleSocial.model.Utente;
import com.social.ProgettoFinaleSocial.utils.FinalConstants;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			
			BusinessLogic BS = (BusinessLogic) getServletContext().getAttribute(FinalConstants.BUSINESSLOGIC);
			
			if(request.getSession().getAttribute(FinalConstants.INPUT_UTENTE) != null) {
				request.getSession().setAttribute(FinalConstants.INPUT_UTENTE, null);
				request.getRequestDispatcher("/jsp/Login.jsp").forward(request, response);
			}

			String usr = request.getParameter(FinalConstants.INPUT_USR);
			String password = request.getParameter(FinalConstants.INPUT_PASS);
			
			
		    Utente utente = BS.searchUtenteByUsernameAndPassword(usr, password);
			//usa una sola query che ritorni l'oggeto utente intero, così da salvarlo in sessione
			 
				//la rimozione di un utente (session..remove) corrisponderà al Logout
				
			 	request.getSession().setAttribute(FinalConstants.INPUT_UTENTE, utente);				
			
				
			//  request.getSession().setAttribute(FinalConstants.INPUT_POSTS, posts);
				
				
				//facciamo una sendRedirect verso la Home servlet E SOLO Lì CARICHIAMO I POST
				
				 
				response.sendRedirect("HomeServlet");
			//	request.getRequestDispatcher("/jsp/Home.jsp").forward(request, response);
			


		
		}
		
		catch ( NotPresentUserException |TooManyPresentUserException e)  {

			e.printStackTrace();

			request.setAttribute(FinalConstants.MESSAGGIO, e.getMessage());
			request.getRequestDispatcher("/jsp/Login.jsp").forward(request, response);

		}
		
		catch (Exception e) { 
			e.printStackTrace();
			
			request.setAttribute(FinalConstants.MESSAGGIO, "Errore imprevisto");
			request.getRequestDispatcher("/jsp/Esito.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
