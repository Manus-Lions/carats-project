package com.social.ProgettoFinaleSocial.ui;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.social.ProgettoFinaleSocial.businessLogic.BusinessLogic;
import com.social.ProgettoFinaleSocial.dao.CommentoDAO;
import com.social.ProgettoFinaleSocial.dao.ImmagineDAO;
import com.social.ProgettoFinaleSocial.dao.LikeDAO;
import com.social.ProgettoFinaleSocial.dao.PostDAO;
import com.social.ProgettoFinaleSocial.dao.RuoloDAO;
import com.social.ProgettoFinaleSocial.dao.UtenteDAO;
import com.social.ProgettoFinaleSocial.model.Commento;
import com.social.ProgettoFinaleSocial.model.Likes;
import com.social.ProgettoFinaleSocial.model.Post;
import com.social.ProgettoFinaleSocial.model.Utente;
import com.social.ProgettoFinaleSocial.utils.FinalConstants;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet(value ="/InitServlet", loadOnStartup = 1)
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub

		try {

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("progettoFinale1");

			EntityManager manager = emf.createEntityManager();

			UtenteDAO utenteDao = new UtenteDAO(manager);
			RuoloDAO ruoloDao = new RuoloDAO(manager);
			PostDAO postDao = new PostDAO(manager);
			LikeDAO likeDao = new LikeDAO(manager);
			ImmagineDAO immagineDao = new ImmagineDAO(manager);
			CommentoDAO commentoDao = new CommentoDAO(manager);

			BusinessLogic businessLogic = new BusinessLogic(manager, utenteDao, postDao, ruoloDao, likeDao, commentoDao, immagineDao);

			getServletContext().setAttribute(FinalConstants.BUSINESSLOGIC, businessLogic);
			
			System.out.println("Inizio Test1");

			businessLogic.initApp();	 	


		} catch (Exception e) {

			e.printStackTrace();
			System.exit(0);
		}

	}
}
