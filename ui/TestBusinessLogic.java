package com.social.ProgettoFinaleSocial.ui;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.social.ProgettoFinaleSocial.businessLogic.*;
import com.social.ProgettoFinaleSocial.dao.CommentoDAO;
import com.social.ProgettoFinaleSocial.dao.ImmagineDAO;
import com.social.ProgettoFinaleSocial.dao.LikeDAO;
import com.social.ProgettoFinaleSocial.dao.PostDAO;
import com.social.ProgettoFinaleSocial.dao.RuoloDAO;
import com.social.ProgettoFinaleSocial.dao.UtenteDAO;
import com.social.ProgettoFinaleSocial.model.Commento;
import com.social.ProgettoFinaleSocial.model.Likes;
import com.social.ProgettoFinaleSocial.model.Post;
import com.social.ProgettoFinaleSocial.model.Ruolo;
import com.social.ProgettoFinaleSocial.model.Utente;

public class TestBusinessLogic {
	
	
	public static void main (String[] args) {
		
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
 		
 	System.out.println("Inizio Test1");
 	
 	businessLogic.initApp();
 	
 	System.out.println("Fine test1");
 	
    Utente utente1 = new Utente( "utente9", "password1", "nome1", "cognome1");
    Utente utente2 = new Utente( "utente11", "password2", "nome1", "cognome1");
    Utente utente3 = new Utente( "utente13", "password3", "nome1", "cognome1");
 	utente1 = businessLogic.insertUtenteSimple(utente1);
 	utente2 = businessLogic.insertUtenteSimple(utente2);
 	utente3 = businessLogic.insertUtenteSimple(utente3);
 	 	
 	
  	Post post1 = new Post ("titolo9", "testo1", LocalDateTime.of(2019, 03, 14, 18, 54));
	Post post2 = new Post ("titolo8", "testo2", LocalDateTime.of(2019, 03, 14, 18, 53));
 	
 	post1 = businessLogic.insertPost(post1, utente1.getId());
 	post2 = businessLogic.insertPost(post2, utente2.getId());
 	
 	
 	System.out.println("Fine test2");
 	
 	Likes like1 = new Likes(true);

 	Likes like2 = new Likes(false);
 	
 	Likes like3 = new Likes(true);
 	
 	businessLogic.insertLikes(like1, utente1.getId(), post2.getId());
 	businessLogic.insertLikes(like2, utente2.getId(), post1.getId());
 	businessLogic.insertLikes(like3, utente3.getId(), post1.getId());
 	
 	System.out.println("fine test3");
 	
 	List<Post> posts =  businessLogic.orderPostsByDate();
 	System.out.println(posts);
 
 	
 	
 	
 	Commento commento1 = new Commento("testo1", LocalDateTime.of(2019, 03, 14, 11, 55));
 	
 	Commento commento2 = new Commento("testo2", LocalDateTime.of(2019, 03, 14, 11, 56));
 	
 	businessLogic.insertCommento(commento1, utente2.getId(), post1.getId());
 	businessLogic.insertCommento(commento2, utente3.getId(), post1.getId());
 	
	
 	System.out.println("Fine test4"); 	
 	
// 	businessLogic.removeLikes1(post1);
// 	
//	businessLogic.removeCommentoList(post1);
 	
//    businessLogic.removePost(post1);
 	
 	System.out.println("fine test5");
 	
 	
// 	businessLogic.removeOwnComment(commento2);
// 	businessLogic.removeOwnComment(commento1);
 	
 	System.out.println("fine test6");
 	
 	System.out.println(businessLogic.countLikes(post1));
 	

 	System.out.println("fine test7");
 	
 	
 	
 	
 	
 
// TEST1  insertRuolo e insertUtente e insertPost 
////nota bene:bisogna iniziare subito a settare all'utente un ruolo già esistente.
// 	
// 	Ruolo ruolo1 = new Ruolo ("admin"); 	
// 	businessLogic.insertRuolo(ruolo1);
// 	
// 	Utente utente1 = new Utente ("username1", "password1", "Mario", "Rossi");
// 	utente1.setRuolo(ruolo1); 	 	
// 	
// 	businessLogic.insertUtente(utente1); 	
 // 	Post post1 = new Post ("titolo1", "testo1", LocalDateTime.of(2019, 03, 14, 18, 50), utente1); 
// 	businessLogic.insertPost(post1);
// 	
// 	System.out.println("test1 completato");
 	
 //TEST2 removeRuolo, removeUtente, e removePost. funzionano  (alter Table su workbench)
// 	
// 	Ruolo ruolo1 = new Ruolo ("admin"); 	
// 	Utente utente1 = new Utente ("username1", "password1", "Rossi", "Rossi");
//	Post post1 = new Post ("titolo1", "testo1", LocalDateTime.of(2019, 03, 14, 18, 50), utente1);
//	manager.clear();
//	
//	businessLogic.removePostsByUtenteId(1);
//	businessLogic.removeUtente(utente1);	
//    businessLogic.removeRuolo(ruolo1);
 	
// 	List<Post> listaUtenti = postDao.searchByUtenteName("username1");
//    
 
// 	
// 	System.out.println("Test2 completato");

 	
// TEST3  removePostById, removeUtenteById
 	
 	
 // businessLogic.removePostsByUtenteId(3);
 	
  	
 	
 	
	System.exit(0);
 	
  
		} catch (Exception e) {
			
			e.printStackTrace();
			System.exit(0);
		}
 	
 	
	
	}
}
	

