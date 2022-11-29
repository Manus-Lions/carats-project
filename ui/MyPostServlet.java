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
import com.social.ProgettoFinaleSocial.model.Post;
import com.social.ProgettoFinaleSocial.model.Utente;
import com.social.ProgettoFinaleSocial.utils.FinalConstants;
import com.social.ProgettoFinaleSocial.utils.WebUtility;

/**
 * Servlet implementation class MyPost
 */
@WebServlet("/MyPostServlet")
public class MyPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPostServlet() {
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
			
			
				
			List<Post> posts = BS.orderPostsByDateAndUser(utente.getId());	
			WebUtility.aggiungiImmagini(request, posts, getServletContext());
				
			request.setAttribute(FinalConstants.INPUT_POSTS, posts);
			
			request.getRequestDispatcher("/jsp/Home2.jsp").forward(request, response);
			
		} catch ( NotPresentPostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute(FinalConstants.MESSAGGIO1, e.getMessage());
			request.getRequestDispatcher("/jsp/Home2.jsp").forward(request, response);
			
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
