package com.social.ProgettoFinaleSocial.ui;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.social.ProgettoFinaleSocial.dao.*;
import com.social.ProgettoFinaleSocial.model.Likes;
import com.social.ProgettoFinaleSocial.model.Post;
import com.social.ProgettoFinaleSocial.model.Ruolo;
import com.social.ProgettoFinaleSocial.model.Utente;


public class Test {

	public static void main(String[] args) {

		EntityManager manager = null;

		try {

			
			
            String ricerca = " manuel";
			
			ricerca =  ricerca.stripLeading().stripTrailing();	
			
			System.out.println(ricerca);
			
			
			
			String[] ricerca1 = ricerca.split(" ");
			
			System.out.println(ricerca1.length);
			
			for (String s : ricerca1) {
				
				System.out.println(s.contains(" "));
				
				System.out.println(s);
				
			}
			
			
			
		//	String stringa = "Manuel Leoni"
			

//		RuoloDAO ruoloDao = new RuoloDAO(manager);
//      		UtenteDAO utenteDao = new UtenteDAO (manager);
//      		
//      		PostDAO postDao = new PostDAO (manager);
//      		
//      		LikeDAO likeDao = new LikeDAO (manager);
//      		
//      		
//      	
//      		List<Utente>  listaUtenti = utenteDao.findByNomeCognome("ian");
//      		
//      	 Post post1 = new Post ("ciao", "bello", LocalDateTime.of(2019, 03, 14, 18, 50), utente1);
//      		
      		
      		
      	//	utenteDao.create(utente1);
      	//	postDao.create(post1);
      		
      	//	Like like1 = new Like(utente1, post1, true);
      		
      	//	likeDao.create(like1);
//
		
//			Utente utente2 = new Utente("utente2", "password2", "Francesco", "Serverolo");		
//			Utente utente3 = new Utente("utente3", "password3", "Sigmund", "Freud");		
	//	Utente utente4 = new Utente("utente4", "password4", "Martina", "Pesce");
//			
//			Ruolo admin = new Ruolo ("admin");
//			
//			utente1.setRuolo(admin);	
//			utente1.setCognome("Verdi");
//			
//			utenteDao.create(utente1);
//			utenteDao.create(utente2);
//			utenteDao.create(utente3);
//			utenteDao.create(utente4);			
//			ruoloDao.create(admin);		
//			
//			manager.clear();
//      		List<Post> posts = postDao.searchByUtenteId(1);
//      		
	
//			//manager.clear();
//			//List<Utente>  listaUtenti2 = utenteDao.searchUtentiByID(2);
//			
//			
//			System.out.println(posts.toString());
//		//	System.out.println(listaUtenti2.toString());
//          //  System.out.println(ruoloDao.searchByName("admin").getNome());
//      		manager.clear();
//			manager.getTransaction().commit();
//			




		} catch (Exception e) {

//			e.printStackTrace();
//			manager.getTransaction().rollback();

		}

		System.exit(0);
	}
}

