package com.social.ProgettoFinaleSocial.ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.social.ProgettoFinaleSocial.businessLogic.BusinessLogic;
import com.social.ProgettoFinaleSocial.exceptions.AlreadyExistentUserException;
import com.social.ProgettoFinaleSocial.exceptions.NotAvailableSimpleRoleException;
import com.social.ProgettoFinaleSocial.model.Utente;
import com.social.ProgettoFinaleSocial.utils.FinalConstants;
import com.social.ProgettoFinaleSocial.utils.Utility;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ricevo i parametri; richiamo la BS; rispondo. 

		try { 

			BusinessLogic BS = (BusinessLogic) getServletContext().getAttribute(FinalConstants.BUSINESSLOGIC);

			String usr =  request.getParameter(FinalConstants.INPUT_USR);
			String password = request.getParameter(FinalConstants.INPUT_PASS);
			String nome = request.getParameter(FinalConstants.INPUT_NAME);
			String cognome = request.getParameter(FinalConstants.INPUT_COGNOME);

			System.out.println("" + usr + password + nome + cognome);

			if (!Utility.checkUsername(usr)) {

				request.setAttribute(FinalConstants.MESSAGGIO, "Mail non valida!");
			

			}

			

			else if (!Utility.checkPassword(password)) {

				request.setAttribute(FinalConstants.MESSAGGIO, "Password non valida!");
			

			}

			
			else if (!Utility.checkNomeCognome(nome, cognome)) {

				request.setAttribute(FinalConstants.MESSAGGIO, "Nome o cognomi non validi!");
				

			}

			
			if (request.getAttribute(FinalConstants.MESSAGGIO) == null) {
				
				BS.insertUtenteSimple(new Utente (usr, password, nome, cognome));
			
			
				response.sendRedirect(request.getContextPath() + "/jsp/Login.jsp");
				
			}

			else {
				
				request.getRequestDispatcher("/jsp/Register.jsp").forward(request, response);
				
			}
			
		//	request.getRequestDispatcher("/jsp/Login.jsp").forward(request, response);
			

		} catch (AlreadyExistentUserException | NotAvailableSimpleRoleException e) {

			e.printStackTrace();
			request.setAttribute(FinalConstants.MESSAGGIO, e.getMessage());
			request.getRequestDispatcher("/jsp/Register.jsp").forward(request, response);
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
