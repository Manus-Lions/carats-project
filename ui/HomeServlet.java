package com.social.ProgettoFinaleSocial.ui;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.social.ProgettoFinaleSocial.businessLogic.BusinessLogic;
import com.social.ProgettoFinaleSocial.model.Commento;
import com.social.ProgettoFinaleSocial.model.Post;
import com.social.ProgettoFinaleSocial.utils.BlobConverter;
import com.social.ProgettoFinaleSocial.utils.FinalConstants;
import com.social.ProgettoFinaleSocial.utils.WebUtility;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
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


//			if (request.getAttribute(FinalConstants.INPUT_POSTS2) != null) {
//
//				List<Post> posts = (List<Post>) request.getAttribute(FinalConstants.INPUT_POSTS2);
//
//				List<Integer> listaId = new ArrayList<Integer>();
//
//				for(Post p : posts) {		
//					listaId.add(p.getId());
//				}
//
//				List<Commento> commentiCaricati = new ArrayList<Commento>();
//
//				for(Integer i: listaId) {
//
//					List<Commento> temp = BS.selectCommentById(i);
//					commentiCaricati.addAll(temp);	
//				}
//				
//				if (commentiCaricati != null) {
//					request.setAttribute(FinalConstants.INPUT_COMMENTI, commentiCaricati);
//				}
//
//
//				request.setAttribute(FinalConstants.INPUT_POSTS, posts);
//				WebUtility.aggiungiImmagini(request, posts, getServletContext());
//				request.getRequestDispatcher("/jsp/Home2.jsp").forward(request, response);
//
//
//			}

			List<Post> posts = BS.orderPostsByDate();

			List<Integer> listaId = new ArrayList<Integer>();

			for(Post p : posts) {		
				listaId.add(p.getId());
			}

			List<Commento> commentiCaricati = new ArrayList<Commento>();

			for(Integer i: listaId) {

				List<Commento> temp = BS.selectCommentById(i);
				if (temp != null) {
					commentiCaricati.addAll(temp);	
				}
			}
			request.setAttribute(FinalConstants.INPUT_COMMENTI, commentiCaricati);
			request.setAttribute(FinalConstants.INPUT_POSTS, posts);

			//Utente utente = (Utente) request.getSession().getAttribute(FinalConstants.INPUT_UTENTE);

			//	List<Post> posts = BS.orderPostsByDateAndUser(utente.getId());	


			WebUtility.aggiungiImmagini(request, posts, getServletContext());
			request.getRequestDispatcher("/jsp/Home2.jsp").forward(request, response);

		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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





