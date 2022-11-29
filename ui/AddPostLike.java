package com.social.ProgettoFinaleSocial.ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.social.ProgettoFinaleSocial.businessLogic.BusinessLogic;
import com.social.ProgettoFinaleSocial.exceptions.AlreadyExistentLikeException;
import com.social.ProgettoFinaleSocial.exceptions.SameAuthorAndCommenterException;
import com.social.ProgettoFinaleSocial.model.Commento;
import com.social.ProgettoFinaleSocial.model.Post;
import com.social.ProgettoFinaleSocial.model.Utente;
import com.social.ProgettoFinaleSocial.utils.FinalConstants;
import com.social.ProgettoFinaleSocial.utils.WebUtility;

/**
 * Servlet implementation class AddPostLike
 */
@WebServlet("/AddPostLike")
public class AddPostLike extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddPostLike() {
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

			



			String paramLike = request.getParameter("likeBtn");
			String paramDislike = request.getParameter("dislikeBtn");

			if (paramLike != null) {

				String[] values = paramLike.split(",");
				List<Post> posts = BS.selectPostById(Integer.parseInt(values[0]));


				Utente u = (Utente) request.getSession().getAttribute(FinalConstants.INPUT_UTENTE);
				String b = values[1];

				BS.removeLikesFalse(Integer.parseInt(values[0]),  u.getId());
				
				BS.insertLikes(true,u.getId(), posts.get(0).getId());

				List<Post> posts1 = BS.selectPostById(Integer.parseInt(values[0]));
				List<Commento> commenti = BS.selectCommentById(posts1.get(0).getId());
				request.setAttribute(FinalConstants.INPUT_POSTS, posts1);
				request.setAttribute(FinalConstants.INPUT_COMMENTI, commenti);
				WebUtility.aggiungiImmagini(request, posts1, getServletContext());
				request.getRequestDispatcher("/jsp/SelectedPost.jsp").forward(request, response);
			}
			else if (paramDislike != null) {
				String[] values = paramDislike.split(",");
				List<Post> posts = BS.selectPostById(Integer.parseInt(values[0]));


				Utente u = (Utente) request.getSession().getAttribute(FinalConstants.INPUT_UTENTE);
				String b = values[1];

				BS.removeLikesTrue(Integer.parseInt(values[0]),  u.getId());
				BS.insertLikes(false,u.getId(), posts.get(0).getId());

				List<Post> posts1 = BS.selectPostById(Integer.parseInt(values[0]));
				List<Commento> commenti = BS.selectCommentById(posts1.get(0).getId());
				request.setAttribute(FinalConstants.INPUT_POSTS, posts1);
				request.setAttribute(FinalConstants.INPUT_COMMENTI, commenti);
				WebUtility.aggiungiImmagini(request, posts1, getServletContext());
				request.getRequestDispatcher("/jsp/SelectedPost.jsp").forward(request, response);
			}

			else
				response.sendRedirect("HomeServlet");



			//			if (request.getParameter(FinalConstants.INPUT_TESTOCOMMENTO) != null) {
			//				
			//				Commento comm = new Commento(request.getParameter(FinalConstants.INPUT_TESTOCOMMENTO), LocalDateTime.now());
			//				
			//				Utente u = (Utente) request.getSession().getAttribute(FinalConstants.INPUT_UTENTE);
			//				
			//				String idPost = (String) request.getParameter(FinalConstants.SIMPLE);
			//			
			//				BS.insertCommento(comm, u.getId(), Integer.parseInt(idPost) );
			//				
			//				List<Commento> commento = BS.selectCommentById((Integer.parseInt(idPost)));
			//			    List<Post> posts = BS.selectPostById(Integer.parseInt(idPost));
			//			    request.setAttribute(FinalConstants.INPUT_COMMENTI, commento);
			//				request.setAttribute(FinalConstants.INPUT_POSTS, posts);
			//				WebUtility.aggiungiImmagini(request, posts, getServletContext());
			//				request.getRequestDispatcher("/jsp/SelectedPost.jsp").forward(request, response);
			//				
			//			}
			//			
			//			String idPost =  request.getParameter(FinalConstants.MOSTRA_COMMENTI);
			//			
			//			if (idPost != null) {
			//			
			//		    List<Commento> commento = BS.selectCommentById(Integer.parseInt(idPost));
			//		    List<Post> posts = BS.selectPostById(Integer.parseInt(idPost));
			//		    request.setAttribute(FinalConstants.INPUT_COMMENTI, commento);
			//			request.setAttribute(FinalConstants.INPUT_POSTS, posts);
			//			WebUtility.aggiungiImmagini(request, posts, getServletContext());
			//			request.getRequestDispatcher("/jsp/SelectedPost.jsp").forward(request, response);
			//			}
			//		
			//			else {
			//				
			//			response.sendRedirect("HomeServlet");
			//				
			//			}

			//Utente utente = (Utente) request.getSession().getAttribute(FinalConstants.INPUT_UTENTE);

			//	List<Post> posts = BS.orderPostsByDateAndUser(utente.getId());	




		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SameAuthorAndCommenterException e) {
			// TODO Auto-generated catch block
			request.setAttribute(FinalConstants.MESSAGGIO, e.getMessage());
			response.sendRedirect("HomeServlet");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (AlreadyExistentLikeException e) {
			e.printStackTrace();
			request.setAttribute(FinalConstants.MESSAGGIO, e.getMessage());
			String paramLike = request.getParameter("likeBtn");
			String paramDislike = request.getParameter("dislikeBtn");
			
			if (paramLike != null || paramDislike != null) {
				if(paramLike != null) {
			String[] values = paramLike.split(",");
			List<Post> posts = BS.selectPostById(Integer.parseInt(values[0]));
			
			List<Commento> commenti = BS.selectCommentById(posts.get(0).getId());
			request.setAttribute(FinalConstants.INPUT_POSTS, posts);
			request.setAttribute(FinalConstants.INPUT_COMMENTI, commenti);
			try {
				WebUtility.aggiungiImmagini(request, posts, getServletContext());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				}
				else if (paramDislike != null) {
					String[] values = paramDislike.split(",");
					List<Post> posts = BS.selectPostById(Integer.parseInt(values[0]));
					
					List<Commento> commenti = BS.selectCommentById(posts.get(0).getId());
					request.setAttribute(FinalConstants.INPUT_POSTS, posts);
					request.setAttribute(FinalConstants.INPUT_COMMENTI, commenti);
					try {
						WebUtility.aggiungiImmagini(request, posts, getServletContext());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			request.getRequestDispatcher("/jsp/SelectedPost.jsp").forward(request, response);
			}

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
