package com.social.ProgettoFinaleSocial.businessLogic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.social.ProgettoFinaleSocial.dao.CommentoDAO;
import com.social.ProgettoFinaleSocial.dao.ImmagineDAO;
import com.social.ProgettoFinaleSocial.dao.LikeDAO;
import com.social.ProgettoFinaleSocial.dao.PostDAO;
import com.social.ProgettoFinaleSocial.dao.RuoloDAO;
import com.social.ProgettoFinaleSocial.dao.UtenteDAO;
import com.social.ProgettoFinaleSocial.exceptions.AlreadyExistentLikeException;
import com.social.ProgettoFinaleSocial.exceptions.AlreadyExistentPostException;
import com.social.ProgettoFinaleSocial.exceptions.AlreadyExistentRoleException;
import com.social.ProgettoFinaleSocial.exceptions.AlreadyExistentUserException;
import com.social.ProgettoFinaleSocial.exceptions.NotAvailableSimpleRoleException;
import com.social.ProgettoFinaleSocial.exceptions.NotAvailableUserException;
import com.social.ProgettoFinaleSocial.exceptions.NotPresentDateException;
import com.social.ProgettoFinaleSocial.exceptions.NotPresentIdException;
import com.social.ProgettoFinaleSocial.exceptions.NotPresentPostException;
import com.social.ProgettoFinaleSocial.exceptions.NotPresentTextException;
import com.social.ProgettoFinaleSocial.exceptions.NotPresentTitleException;
import com.social.ProgettoFinaleSocial.exceptions.NotPresentUserException;
import com.social.ProgettoFinaleSocial.exceptions.SameAuthorAndCommenterException;
import com.social.ProgettoFinaleSocial.exceptions.SameAuthorAndLikerException;
import com.social.ProgettoFinaleSocial.exceptions.TooManyPresentUserException;
import com.social.ProgettoFinaleSocial.model.*;
import com.social.ProgettoFinaleSocial.utils.FinalConstants;

public class BusinessLogic {

	private EntityManager manager;

	private UtenteDAO utenteDAO;
	private PostDAO postDAO;
	private RuoloDAO ruoloDAO;
	private LikeDAO likeDAO;
	private CommentoDAO commentoDAO;
	private ImmagineDAO immagineDAO;



	public BusinessLogic(EntityManager manager, UtenteDAO utenteDAO, PostDAO postDAO, RuoloDAO ruoloDAO,
			LikeDAO likeDAO, CommentoDAO commentoDAO, ImmagineDAO immagineDAO) {
		super();
		this.manager = manager;
		this.utenteDAO = utenteDAO;
		this.postDAO = postDAO;
		this.ruoloDAO = ruoloDAO;
		this.likeDAO = likeDAO;
		this.commentoDAO = commentoDAO;
		this.immagineDAO = immagineDAO;
	}

	//metodo d'inizializzazione per verificare presenza ruoli, utenti ecc., assicurarsi che ci siano i ruoli richiesti (semplice, admin, superuser)




	//Utente
	//scritto e testato


	//nella UI / Servlet creerò catch multipli: un catch che cattura l'AlreadyExistent e che riporta alla fase di registrazione
	// (e con e.getMessage nel quarto livello eredita il messaggio qui inserito in riga 67) 


	public List<Post> orderPostsByDate() {


		try {

			manager.getTransaction().begin();

			List<Post> posts =	postDAO.sortByDate();

			if (posts.size() != 0) {

				manager.getTransaction().commit();
				return posts;
			}


		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}
		return null;

	}
	
	public List<Post> selectPostById(int id) {


		try {

			manager.getTransaction().begin();
			manager.clear();
			List<Post> posts =	postDAO.searchByPostId(id);

			if (posts.size() != 0) {

				manager.getTransaction().commit();
				return posts;
			}


		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}
		return null;

	}

	public List<Post> orderPostsByDateAndUser(int id) throws Exception {

		try {

			manager.getTransaction().begin();

			List<Post> posts =	postDAO.sortByUserAndDate(id);

			if (posts.size() == 0) {

				throw new NotPresentPostException("Non hai inserito ancora alcun post");

			}

			manager.getTransaction().commit();
			return posts;

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;

		}


	}

	public List<Utente> orderUserByVagueNameorVagueSurname(String ricerca) throws Exception {

		try {



			ricerca =  ricerca.stripLeading().stripTrailing();			


			String[] ricerca1 = ricerca.split(" ");

			if (ricerca1.length == 1) {

				manager.getTransaction().begin();

				List<Utente> utenti = utenteDAO.findByNomeCognome(ricerca1[0]);

				if (utenti.size() == 0) {

					manager.getTransaction().commit();
					throw new NotPresentUserException("Nessun utente trovato");

				}

				manager.getTransaction().commit();
				return utenti;

			}

			else if (ricerca1.length == 2) {

				manager.getTransaction().begin();

				List<Utente> utenti = utenteDAO.findByNomeCognome(ricerca1[0], ricerca1[1]);

				if (utenti.size() == 0) {
					manager.getTransaction().commit();
					throw new NotPresentUserException("Nessun utente trovato");

				}


				manager.getTransaction().commit();
				return utenti;


			}





		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;

		}
		return null;


	}
	
	public List<Commento> selectCommentById(Integer id) {

		try {	

				manager.getTransaction().begin();

				List<Commento> commenti = commentoDAO.searchByPostId(id);

				if (commenti.size() == 0)

					manager.getTransaction().commit();
				else {
					manager.getTransaction().commit();
					return commenti;
				}
		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}
		return null;
	}



	public List<Post> orderPostsByUser(String ricerca) throws Exception {

		try {
			
			manager.getTransaction().begin();

			ricerca =  ricerca.stripLeading().stripTrailing();			


			String[] ricerca1 = ricerca.split(" ");

			if (ricerca1.length == 1) {

				List<Post> posts = postDAO.findByNomeCognome(ricerca1[0]);

				if (posts.size() == 0) {

					throw new NotPresentPostException("Nessun post trovato");

				}

				manager.getTransaction().commit();
				return posts;

			}

			else if (ricerca1.length == 2) {

				List<Post> posts = postDAO.findByNomeCognome(ricerca1[0], ricerca1[1]);

				if (posts.size() == 0) {
					
					throw new NotPresentPostException("Nessun post trovato");

				}


				manager.getTransaction().commit();
				return posts;
			}

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;

		}
		return null;
	}



	public boolean checkUtenteDB(String username, String password) throws Exception {



		try {

			manager.getTransaction().begin();


			//bisognerebbe controllare anche il ruolo simple (nello stesso modo seguente)
			List<Utente> utenti = utenteDAO.searchUtentiByUsernameAndPassword(username, password);


			if (utenti.size() != 1) {
				throw new NotAvailableUserException("L'utente non è registrato!");
			}

			else {

				manager.getTransaction().commit();

				return true;			

			}



		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}

	}

	public Utente insertUtenteSimple (Utente u) throws Exception {

		manager.getTransaction().begin();

		try {
			//bisognerebbe controllare anche il ruolo simple (nello stesso modo seguente)
			List<Ruolo> ruoli = ruoloDAO.searchByName(FinalConstants.SIMPLE);
			Ruolo ruolo = new Ruolo();


			if (ruoli.size() != 1) {
				throw new NotAvailableSimpleRoleException("Il ruolo 'simple' non è disponibile!");
			}


			ruolo = ruoli.get(0);

			//al quarto livello (non qui) controlliamo che l'utente abbia tutti i parametri necessari

			List<Utente> utenti = utenteDAO.searchUtentiByUsername(u.getUsername());

			if (utenti.size() != 0) {
				throw new AlreadyExistentUserException("Lo username " +  u.getUsername() + " già esiste!");
			}

			u.setRuolo(ruolo);
			utenteDAO.create(u);
			manager.getTransaction().commit();
			return u;

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}
	}

	public Utente insertUtenteAdmin (Utente u) throws Exception {

		manager.getTransaction().begin();

		try {
			//bisognerebbe controllare anche il ruolo simple (nello stesso modo seguente)
			List<Ruolo> ruoli = ruoloDAO.searchByName(FinalConstants.ADMIN);
			Ruolo ruolo = new Ruolo();


			if (ruoli.size() != 1) {
				throw new NotAvailableSimpleRoleException("Il ruolo 'Admin' non è disponibile!");
			}


			ruolo = ruoli.get(0);

			//al quarto livello (non qui) controlliamo che l'utente abbia tutti i parametri necessari

			List<Utente> utenti = utenteDAO.searchUtentiByUsername(u.getUsername());

			if (utenti.size() == 0) {
				//	throw new AlreadyExistentUserException("Lo username " +  u.getUsername() + " già esiste!");
				//	return utenti.get(0);
				u.setRuolo(ruolo);
				utenteDAO.create(u);
			}

			manager.getTransaction().commit();
			return u;

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}
	}

	//Ruolo
	//scritto testato funziona
	//controllare se già esiste


	//se il ruolo già c'è, non fa nulla
	public void insertRuolo(Ruolo r) {

		manager.getTransaction().begin();

		try {

			List<Ruolo> ruoli = ruoloDAO.searchByName(r.getNome());

			if (ruoli.size() == 0) {
				ruoloDAO.create(r);
			}


			manager.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}	
	}


	public void insertImmagine(Immagine im) {

		manager.getTransaction().begin();

		try {


			immagineDAO.create(im);



			manager.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}	
	}


	//Post
	//scritto e testato
	//settare come paramentro l'id dell'autore per collegare, dopo opportuna query, utente e post in fase di inserimento



	public Post insertPost(Post p, Integer idUtente, Immagine im) throws Exception {

		manager.getTransaction().begin();

		try {

			if (p.getTesto().equals(null)) {

				throw new NotPresentTextException("Devi inserire del testo!");

			}

			if (p.getTitolo().equals(null)) {

				throw new NotPresentTitleException("Devi inserire il titolo !");

			}

			if (p.getData().equals(null)) {

				throw new NotPresentDateException("Data non presente!");

			}

			if (idUtente.equals(null)) {

				throw new NotPresentIdException("Id non presente (session inattiva?)!");
			}

			List<Utente> utentiDB = utenteDAO.searchUtentiByID(idUtente);
			if (utentiDB.size() != 1) {
				throw new RuntimeException("Errore di sistema.");
			}

			Utente utenteDB = utentiDB.get(0);
			p.setAutore(utenteDB);

			List<Post> posts = postDAO.searchByUtenteNameAndDate(p.getAutore().getNome(), p.getData());


			if (posts.size() != 0) {
				throw new AlreadyExistentPostException("Il post già esiste!");
			}

			if (im != null) {

				//	immagineDAO.create(im);
				p.setImmagine(im);
			}



			manager.clear();

			postDAO.create(p);
			manager.getTransaction().commit();
			return p;

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}	
	}

	public Post insertPost(Post p, Integer idUtente) throws Exception {

		manager.getTransaction().begin();

		try {

			if (p.getTesto().equals(null)) {

				throw new NotPresentTextException("Devi inserire del testo!");

			}

			if (p.getTitolo().equals(null)) {

				throw new NotPresentTitleException("Devi inserire il titolo !");

			}

			if (p.getData().equals(null)) {

				throw new NotPresentDateException("Data non presente!");

			}

			if (idUtente.equals(null)) {

				throw new NotPresentIdException("Id non presente (session inattiva?)!");
			}

			List<Utente> utentiDB = utenteDAO.searchUtentiByID(idUtente);
			if (utentiDB.size() != 1) {
				throw new RuntimeException("Errore di sistema.");
			}

			Utente utenteDB = utentiDB.get(0);
			p.setAutore(utenteDB);

			List<Post> posts = postDAO.searchByUtenteNameAndDate(p.getAutore().getNome(), p.getData());


			if (posts.size() != 0) {
				throw new AlreadyExistentPostException("Il post già esiste!");
			}



			manager.clear();

			if ( p.getImmagine() != null) {

				Immagine i = immagineDAO.create(p.getImmagine());
				p.setImmagine(i);

			}


			postDAO.create(p);
			manager.getTransaction().commit();
			return p;

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}	
	}

	//SCRITTO TESTATO E FUNZIONANTE
	//il metodo dovrebbe ricevere l'oggetto like da salvare, l'id del post e dell'utente. 
	//query per trovare il post, un'altra per trovare l'utente
	// ricorrere al metodo set invocandolo sull'utente e sul post ecc.

	public void insertLikes (Likes l, Integer idUtente, Integer idPost) throws Exception {
		manager.getTransaction().begin();

		try {
			manager.clear();
			List<Utente> utentiDB = utenteDAO.searchUtentiByID(idUtente);
			if (utentiDB.size() != 1) {
				throw new RuntimeException("Errore di sistema.");
			}

			Utente utenteDB = utentiDB.get(0);
			

			List<Post> postsDB = postDAO.searchByPostId(idPost);
			if (postsDB.size() != 1) {
				throw new RuntimeException("Errore di sistema.");
			}

			Post postDB = postsDB.get(0);


			if (postDB.getAutore().equals(utenteDB)) {
				throw new SameAuthorAndLikerException("Non puoi inserire una reaction ai tuoi stessi post");
			}	



			List<Likes> likes = likeDAO.searchByPostId(postDB.getId());

			// si potrebbe fare l'override del metodo equals comparando gli id della list utenti. 
			// if(likes.contains(utenteDb)) 
			//throw exception..

			for (Likes l1 : likes) {

				if (l1.getLiker().equals(utenteDB))								
					throw new AlreadyExistentLikeException("Non puoi inserire piu' di una reaction dello stesso tipo!");
			}			

			l.setLiker(utenteDB);
			l.setPost(postDB);
			likeDAO.create(l);
			manager.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}


	}

	//Commento
	//scritto e testato


	public int searchForIDByUsernameAndPassword(String username, String password) {



		manager.getTransaction().begin();

		try {

			List<Utente> utenti =  utenteDAO.searchUtentiByUsernameAndPassword(username, password);

			if (utenti.size() != 1) {

				throw new RuntimeException("Errore mostruoso e incomprensibile..");

			}


			Utente utente = utenti.get(0);

			manager.getTransaction().commit();

			return utente.getId();




		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}
	}



	public void insertCommento (Commento c, Integer idUtente, Integer idPost) throws Exception {

		manager.getTransaction().begin();

		try {

			List<Utente> utentiDB = utenteDAO.searchUtentiByID(idUtente);
			if (utentiDB.size() != 1) {
				throw new RuntimeException("Errore di sistema.");
			}

			Utente utenteDB = utentiDB.get(0);

			List<Post> postsDB = postDAO.searchByPostId(idPost);
			if (postsDB.size() != 1) {
				throw new RuntimeException("Errore di sistema.");
			}

			Post postDB = postsDB.get(0);

			if (postDB.getAutore().equals(utenteDB)) {

				throw new SameAuthorAndCommenterException("Non puoi commentare i tuoi stessi post!");
			}		



			c.setCommenter(utenteDB);
			c.setPost(postDB);
			commentoDAO.create(c);
			manager.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}
	}


	//TESTATO E FUNZIONA
	public void initApp() throws Exception {

		//	manager.getTransaction().begin();

		try {			


			//			Utente utente1 = new Utente( "utente1", "password1", "nome1", "cognome1");
			//			Utente utente2 = new Utente( "utente2", "password2", "nome2", "cognome2");
			//			Utente utente3 = new Utente( "utente3", "password3", "nome3", "cognome3");
			//			Utente utente4 = new Utente( "utente4", "password4", "nome4", "cognome4");
			//			Utente utente5 = new Utente( "utente5", "password5", "nome5", "cognome5");
			//			Utente utente6 = new Utente( "utente6", "password6", "nome6", "cognome6");
			//			Utente utente7 = new Utente( "utente7", "password7", "nome7", "cognome7");
			//			Utente utente8 = new Utente( "utente8", "password8", "nome8", "cognome8");
			//			Utente utente9 = new Utente( "utente9", "password9", "nome9", "cognome9");
			//			Utente utente10 = new Utente( "utente10", "password10", "nome10", "cognome10");
			//			Utente utente11 = new Utente( "utente11", "password11", "nome11", "cognome11");
			//			Utente utente12 = new Utente( "utente12", "password12", "nome12", "cognome12");

			Utente admin1 = new Utente("admin1", "admin1", "admin1", "admin1");
			Ruolo adminRuolo = new Ruolo(FinalConstants.ADMIN);	

			insertRuolo(adminRuolo);
			insertRuolo(new Ruolo(FinalConstants.SIMPLE));
			insertUtenteAdmin(admin1);

			//			businessLogic.insertRuolo(simple);


			//nell'init si inserira solo l'admin, dopo aver creato i ruoli

			//			Utente utenteDB1 = businessLogic.insertUtente(utente1);
			//			businessLogic.insertUtente(utente2);
			//			businessLogic.insertUtente(utente3);
			//			businessLogic.insertUtente(utente4);
			//			businessLogic.insertUtente(utente5);





			//			admin1.setRuolo(adminRuolo);


			//Ruolo simple = new Ruolo(FinalConstants.SIMPLE);

			//conviene settare 
			//			utente1.setRuolo(simple);
			//			utente2.setRuolo(simple);
			//			utente3.setRuolo(simple);
			//			utente4.setRuolo(simple);
			//			
			//			utente5.setRuolo(simple);			
			//			utente6.setRuolo(simple);
			//			utente7.setRuolo(simple);
			//			utente8.setRuolo(simple);
			//		    
			//			utente9.setRuolo(simple);
			//			utente10.setRuolo(simple);
			//			utente11.setRuolo(simple);
			//			utente12.setRuolo(simple);




			//nella servlet il costruttore deve caricare l'utente che posta;
			//più correttamente, dovrebbe essere la business logic a collegare post e utente.
			//			Post post1 = new Post ("titolo1", "testo1", LocalDateTime.of(2019, 03, 14, 18, 55), utente1);
			//			Post post2 = new Post ("titolo2", "testo2", LocalDateTime.of(2019, 03, 14, 18, 50), utente2);
			//			Post post3 = new Post ("titolo3", "testo3", LocalDateTime.of(2019, 03, 14, 18, 50), utente1);
			//			Post post4 = new Post ("titolo4", "testo4", LocalDateTime.of(2019, 03, 14, 18, 50), utente4);
			//			Post post5 = new Post ("titolo1", "testo1", LocalDateTime.of(2019, 03, 14, 18, 52), utente1);
			//			Post post6 = new Post ("titolo1", "testo1", LocalDateTime.of(2019, 03, 14, 18, 50), utente3);
			//	Post errato = new Post ("titolo1", "testo1", LocalDateTime.of(2019, 03, 14, 18, 50), utente1);


			//			Likes like1 = new Likes(true);
			//			Likes like2 = new Likes(utente2, post2, true );
			//			Likes like3 = new Likes(utente3, post3, true );
			//			Likes like4 = new Likes(utente4, post1, false);
			//			Likes like5 = new Likes(utente5, post1, true );
			//	Likes like6 = new Likes(utente5, post1, false );

			//			Commento commento1 = new Commento("testo2", LocalDateTime.of(2019, 03, 14, 11, 55));
			//	Commento commento2 = new Commento("testo1", utente1, post1, LocalDateTime.of(2019, 03, 14, 11, 55));



			//		    Post postDB1 =	businessLogic.insertPost(post1);
			//			businessLogic.insertPost(post2);
			//			businessLogic.insertPost(post3);
			//			businessLogic.insertPost(post4);
			//			businessLogic.insertPost(post5);
			//			businessLogic.insertPost(post6);
			//		 	
			//			businessLogic.insertLikes(like1, utenteDB1.getId(), postDB1.getId());	
			//			businessLogic.insertLikes(like2);
			//			businessLogic.insertLikes(like3);
			//			businessLogic.insertLikes(like4);
			//			businessLogic.insertLikes(like5);
			//	businessLogic.insertLikes(like6);
			//			
			//			businessLogic.insertCommento(commento1, utenteDB1.getId(), postDB1.getId());
			//		businessLogic.insertCommento(commento2);
			//		ruoloDAO.create(adminRuolo);
			//		manager.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			//	manager.getTransaction().rollback();
			throw e;
		}	


	}



	// scritto e testato
	public void removeRuolo (Ruolo ruolo) {
		manager.getTransaction().begin();

		try {

			List<Ruolo> ruoli = ruoloDAO.retrieve();

			for (Ruolo r: ruoli) {
				if (r.getNome().equals(ruolo.getNome()))
					ruoloDAO.delete(r);
			}

			manager.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}	
	}

	//scritto e  testato
	public void removeUtente(Utente u) {
		manager.getTransaction().begin();

		try {

			List<Utente> utenti = utenteDAO.retrieve();

			for (Utente r: utenti) {
				if (r.getNome().equals(u.getNome()) && r.getUsername().equals(u.getUsername()))
					utenteDAO.delete(r);
			}
			manager.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}	
	}




	//



	public void removeLikes (Post p) {


		manager.getTransaction().begin();

		try {


			List<Post> post = postDAO.searchByPostId(p.getId());

			if (post.size() == 1) {

				List<Likes> likes = likeDAO.searchByPostId(post.get(0).getId());

				for (Likes t: likes) {

					likeDAO.delete(t);
				}


			}              

			else {

				throw new RuntimeException("Errore incredibile di sistema");

			}

			manager.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}	
	}

	public void removeCommentoList (Post p) {
		manager.getTransaction().begin();

		try {


			List<Post> post = postDAO.searchByPostId(p.getId());

			if (post.size() == 1) {      


				List<Commento> commenti = commentoDAO.searchByPostId(post.get(0).getId());

				for (Commento t: commenti) {

					commentoDAO.delete(t);
				}


			}              

			else {

				throw new RuntimeException("Errore incredibile di sistema");

			}

			manager.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}	

	}


	public void removePost (Post p) {
		manager.getTransaction().begin();

		try {


			List<Post> post = postDAO.searchByPostId(p.getId());

			if (post.size() == 1) {




				List<Likes> likes = likeDAO.searchByPostId(post.get(0).getId());

				for (Likes t: likes) {

					likeDAO.delete(t);
				}



				List<Commento> commenti = commentoDAO.searchByPostId(post.get(0).getId());

				for (Commento t: commenti) {

					commentoDAO.delete(t);
				}

				postDAO.delete(post.get(0));


			}              

			else {

				throw new RuntimeException("Errore incredibile di sistema");

			}

			manager.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}	
	}

	//scritto e testato
	public void removePostsByUtenteId (Integer id) {
		manager.getTransaction().begin();

		try {


			List<Post> p =  postDAO.searchByUtenteId(id);	

			for (Post pp : p) {
				postDAO.delete(pp);
			}		

			manager.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}	
	}

	//
	public void updatePost (Integer id) {

	}






	//PROVATO E TESTATO
	public void removeOwnComment(Commento c) {

		manager.getTransaction().begin();

		try {

			List <Commento> commenti = commentoDAO.searchByPostId(c.getPost().getId());

			for (Commento com: commenti) {

				if (com.getId().equals(c.getId())) 
					commentoDAO.delete(c);
			}



			manager.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}
	}


	//DA RIVEDERE CON LATO FRONT-END

	public void removeOwnLike () {

	}
	//		OPZIONALE NON FUNZIONA
	//		public void removeLikesList (Post p) {
	//
	//			manager.getTransaction().begin();
	//
	//			try {
	//				List <Likes> likes = likesDAO.retrieve();
	//				
	//				for(Likes c : likes) {
	//					System.out.println(c.getPost().getTitolo());
	//					System.out.println(c.getPost().getAutore());
	//					if(c.getPost().getTitolo().equals(p.getTitolo())
	//							&& c.getPost().getAutore().equals(p.getAutore())) {
	//					likesDAO.delete(c);
	//					}
	//				}
	//				manager.getTransaction().commit();
	//
	//			} catch (Exception e) {
	//				// TODO: handle exception
	//				manager.getTransaction().rollback();
	//				throw e;
	//			}
	//		}


	public int countLikes (Post p) {
		manager.getTransaction().begin();

		try {
			int counter = 0;
			List <Likes> likes = likeDAO.searchByPostId(p.getId());
			ArrayList<Likes> listaLikes = new ArrayList<>();

			for(Likes c : likes) {				 

				counter++;					

			}
			manager.getTransaction().commit();
			return counter;
		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}
	}







	public List<Likes> searchLikesByPost(Post p){
		manager.getTransaction().begin();

		try {
			List <Likes> likes = likeDAO.retrieve();
			ArrayList<Likes> listaLikes = new ArrayList<>();
			for(Likes c : likes) {
				System.out.println(c.getPost().getTitolo());
				System.out.println(c.getPost().getAutore());
				if(c.getPost().getTitolo().equals(p.getTitolo())
						&& c.getPost().getAutore().equals(p.getAutore())) {
					listaLikes.add(c);
				}
				return listaLikes;
			}
			manager.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}
		return null;
	}

	public Utente searchUtenteByUsernameAndPassword(String username, String password) throws Exception {



		manager.getTransaction().begin();

		try {

			List<Utente> utenti =  utenteDAO.searchUtentiByUsernameAndPassword(username, password);


			if (utenti.size() == 0 ) {

				throw new NotPresentUserException("Password o username errati");

			}

			else if (utenti.size() > 1) {

				throw new TooManyPresentUserException("Ci sono più utenti nel database che corrispondono al tuo profilo");

			}


			Utente utente = utenti.get(0);

			manager.getTransaction().commit();

			return utente;



		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}


	}


//SCRITTO TESTATO E FUNZIONANTE
	//il metodo dovrebbe ricevere l'oggetto like da salvare, l'id del post e dell'utente. 
	//query per trovare il post, un'altra per trovare l'utente
	// ricorrere al metodo set invocandolo sull'utente e sul post ecc.

	public void insertLikes (boolean type, Integer idUtente, Integer idPost) throws Exception {
		manager.getTransaction().begin();

		try {
			Likes l = new Likes(type);
			List<Utente> utentiDB = utenteDAO.searchUtentiByID(idUtente);
			if (utentiDB.size() != 1) {
				throw new RuntimeException("Errore di sistema.");
			}

			Utente utenteDB = utentiDB.get(0);

			List<Post> postsDB = postDAO.searchByPostId(idPost);
			if (postsDB.size() != 1) {
				throw new RuntimeException("Errore di sistema.");
			}

			Post postDB = postsDB.get(0);


			if (postDB.getAutore().equals(utenteDB)) {
				throw new SameAuthorAndLikerException("Non puoi inserire una reaction ai tuoi stessi post");
			}	



			List<Likes> likes = likeDAO.searchByPostId(postDB.getId());

			// si potrebbe fare l'override del metodo equals comparando gli id della list utenti. 
			// if(likes.contains(utenteDb)) 
			//throw exception..

			for (Likes l1 : likes) {

				if (l1.getLiker().equals(utenteDB))								
					throw new AlreadyExistentLikeException("Non puoi inserire piu' di una reaction!");
			}			

			l.setLiker(utenteDB);
			l.setPost(postDB);
			likeDAO.create(l);
			manager.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}


	}
	
	
	public void removeLikesTrue (Integer postId, Integer likerId) {


		manager.getTransaction().begin();

		try {


			List<Post> post = postDAO.searchByPostId(postId);
			
			

			if (post.size() == 1) {

				List<Likes> likes = likeDAO.searchByPostId(post.get(0).getId());

				for (Likes t: likes) {
					if(t.getLiker().getId() == likerId && t.getGoodBad()) {
						likeDAO.delete(t);
					}
					
				}


			}              

			else {

				throw new RuntimeException("Errore incredibile di sistema");

			}

			manager.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}	
	}
	
	public void removeLikesFalse (Integer postId, Integer likerId) {


		manager.getTransaction().begin();

		try {


			List<Post> post = postDAO.searchByPostId(postId);

			if (post.size() == 1) {

				List<Likes> likes = likeDAO.searchByPostId(post.get(0).getId());

				for (Likes t: likes) {
					if(t.getLiker().getId() == likerId && !t.getGoodBad()) {
						likeDAO.delete(t);
					}
					
				}


			}              

			else {

				throw new RuntimeException("Errore incredibile di sistema");

			}

			manager.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			manager.getTransaction().rollback();
			throw e;
		}		
	}
	
	
public Post updatePost (Integer id, String testo, String titolo, Immagine immagine) {
		
		try {

			manager.getTransaction().begin();
			
			List<Post> p = postDAO.searchByPostId(id);
			
			if (p.size() != 0) {

				
				
				Post newPost = p.get(0);
			
				if (immagine != null) {
					
				newPost.setImmagine(immagine);
				
				}
				
				if (testo != null) {
					
					newPost.setTesto(testo);
					
					}
				
				if (titolo != null) {
					
					newPost.setTitolo(titolo);
					
					}
					
			
		
			
			manager.getTransaction().commit();
		 	return newPost;				
				
			
			}
			
		} catch (Exception e ) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
		return null;
		

	}
	
}








