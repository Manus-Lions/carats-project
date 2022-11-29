package com.social.ProgettoFinaleSocial.ui;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.social.ProgettoFinaleSocial.businessLogic.BusinessLogic;
import com.social.ProgettoFinaleSocial.exceptions.NotPresentPostException;
import com.social.ProgettoFinaleSocial.model.Commento;
import com.social.ProgettoFinaleSocial.model.Post;
import com.social.ProgettoFinaleSocial.model.Utente;
import com.social.ProgettoFinaleSocial.utils.FinalConstants;
import com.social.ProgettoFinaleSocial.utils.WebUtility;

/**
 * Servlet implementation class ModificaServlet
 */
@WebServlet("/ModificaServlet")
public class ModificaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaServlet() {
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
			
			Utente utente = (Utente) request.getSession().getAttribute(FinalConstants.INPUT_UTENTE);
			
			
			String titolo = request.getParameter(FinalConstants.INPUT_TITOLOPOST);
			String testo = request.getParameter(FinalConstants.INPUT_TESTOPOST);
			String id = request.getParameter(FinalConstants.INPUT_USR);
			
			System.out.println(titolo + testo + id);
			BS.updatePost(Integer.parseInt(id), testo, titolo, null);
			List<Commento> commento = BS.selectCommentById(Integer.parseInt(id));
			List<Post> posts = BS.selectPostById(Integer.parseInt(id));	
			WebUtility.aggiungiImmagini(request, posts, getServletContext());
			request.setAttribute(FinalConstants.INPUT_COMMENTI, commento);
				
			request.setAttribute(FinalConstants.INPUT_POSTS, posts);
			
			request.getRequestDispatcher("/jsp/SelectedPost.jsp").forward(request, response);
			
		} catch ( NotPresentPostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute(FinalConstants.MESSAGGIO, e.getMessage());
			request.getRequestDispatcher("/jsp/Esito.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
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
