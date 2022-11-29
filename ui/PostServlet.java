package com.social.ProgettoFinaleSocial.ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.social.ProgettoFinaleSocial.businessLogic.BusinessLogic;
import com.social.ProgettoFinaleSocial.exceptions.NotPresentPostException;
import com.social.ProgettoFinaleSocial.exceptions.SameAuthorAndCommenterException;
import com.social.ProgettoFinaleSocial.model.Commento;
import com.social.ProgettoFinaleSocial.model.Post;
import com.social.ProgettoFinaleSocial.model.Utente;
import com.social.ProgettoFinaleSocial.utils.FinalConstants;
import com.social.ProgettoFinaleSocial.utils.WebUtility;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostServlet() {
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
			
			
			if (request.getParameter(FinalConstants.INPUT_DELETE) != null) {
				
				
			     List<Post> posts = BS.selectPostById(Integer.parseInt(request.getParameter(FinalConstants.INPUT_DELETE)));
				
			     BS.removePost(posts.get(0));
			     
			     
			  
			
			     
				response.sendRedirect("MyPostServlet");
				
			}
			

			if (request.getParameter(FinalConstants.INPUT_TESTOCOMMENTO) != null) {
				
				Commento comm = new Commento(request.getParameter(FinalConstants.INPUT_TESTOCOMMENTO), LocalDateTime.now());
				
				Utente u = (Utente) request.getSession().getAttribute(FinalConstants.INPUT_UTENTE);
				
				String idPost = (String) request.getParameter(FinalConstants.SIMPLE);
			
				BS.insertCommento(comm, u.getId(), Integer.parseInt(idPost) );
				
				List<Commento> commento = BS.selectCommentById((Integer.parseInt(idPost)));
			    List<Post> posts = BS.selectPostById(Integer.parseInt(idPost));
			    request.setAttribute(FinalConstants.INPUT_COMMENTI, commento);
				request.setAttribute(FinalConstants.INPUT_POSTS, posts);
				WebUtility.aggiungiImmagini(request, posts, getServletContext());
				request.getRequestDispatcher("/jsp/SelectedPost.jsp").forward(request, response);
				
			}
			
			String idPost =  request.getParameter(FinalConstants.MOSTRA_COMMENTI);
			
			if (idPost != null) {
			
		    List<Commento> commento = BS.selectCommentById(Integer.parseInt(idPost));
		    List<Post> posts = BS.selectPostById(Integer.parseInt(idPost));
		    request.setAttribute(FinalConstants.INPUT_COMMENTI, commento);
			request.setAttribute(FinalConstants.INPUT_POSTS, posts);
			WebUtility.aggiungiImmagini(request, posts, getServletContext());
			request.getRequestDispatcher("/jsp/SelectedPost.jsp").forward(request, response);
			}
		
			else {
				
			response.sendRedirect("HomeServlet");
				
			}

			//Utente utente = (Utente) request.getSession().getAttribute(FinalConstants.INPUT_UTENTE);

			//	List<Post> posts = BS.orderPostsByDateAndUser(utente.getId());	


			

		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NotPresentPostException e) {
			request.setAttribute(FinalConstants.MESSAGGIO1, e.getMessage());
			request.getRequestDispatcher("/jsp/SelectedPost.jsp").forward(request, response);
		}
			 catch (SameAuthorAndCommenterException e) {
					// TODO Auto-generated catch block
					request.setAttribute(FinalConstants.MESSAGGIO, e.getMessage());
					request.getRequestDispatcher("/jsp/SelectedPost.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
