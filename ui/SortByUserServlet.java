package com.social.ProgettoFinaleSocial.ui;

import java.io.IOException;
import java.util.ArrayList;
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
import com.social.ProgettoFinaleSocial.utils.FinalConstants;
import com.social.ProgettoFinaleSocial.utils.WebUtility;

/**
 * Servlet implementation class SortByUserServlet
 */
@WebServlet("/SortByUserServlet")
public class SortByUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SortByUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		BusinessLogic BS = (BusinessLogic) getServletContext().getAttribute(FinalConstants.BUSINESSLOGIC);
		
		try {
			
			List<Post> post = BS.orderPostsByUser(request.getParameter(FinalConstants.CERCA_UTENTE));
			
			List<Integer> listaId = new ArrayList<Integer>();

			for(Post p : post) {		
				listaId.add(p.getId());
			}

			List<Commento> commentiCaricati = new ArrayList<Commento>();

			for(Integer i: listaId) {

				List<Commento> temp = BS.selectCommentById(i);
				if ( temp != null) {
				commentiCaricati.addAll(temp);	
				}
			}
			
			if (commentiCaricati != null) {
				request.setAttribute(FinalConstants.INPUT_COMMENTI, commentiCaricati);
			}


			request.setAttribute(FinalConstants.INPUT_POSTS, post);
			WebUtility.aggiungiImmagini(request, post, getServletContext());
			request.getRequestDispatcher("/jsp/Home2.jsp").forward(request, response);
			
			
		
			
			
			
		} catch (NotPresentPostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute(FinalConstants.MESSAGGIO, e.getMessage());
			request.getRequestDispatcher("/jsp/Esito.jsp").forward(request,response);
			
		} 
		
		
		catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute(FinalConstants.MESSAGGIO, "Errore imprevisto");
			request.getRequestDispatcher("/jsp/Esito.jsp").forward(request,response);
			
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
