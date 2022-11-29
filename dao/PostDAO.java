package com.social.ProgettoFinaleSocial.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import com.social.ProgettoFinaleSocial.model.Post;
import com.social.ProgettoFinaleSocial.model.Utente;

public class PostDAO implements DAOInterface<Post> {
	
	private EntityManager manager;
	
	
	public PostDAO(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public Post create(Post ref) {
		// TODO Auto-generated method stub
		manager.persist(ref);
		return ref;
	}

	@Override
	public List<Post> retrieve() {
		// TODO Auto-generated method stub
		return manager.createQuery("select s from Post s", Post.class).getResultList();
	}
	
	
	
	 public List<Post> searchByPostId(Integer id) {	
			
			return manager.createQuery("select s from Post s where s.id = :parid", Post.class).setParameter("parid", id).getResultList();
					
			}
	
	
	
	//scritto e testato
    public List<Post> searchByUtenteId(Integer id) {	
	
	return manager.createQuery("select s from Post s where s.autore.id = :parid", Post.class).setParameter("parid", id).getResultList();
			
	}
    
    public List<Post> searchByTitolo(String titolo) {	
    	
    	return manager.createQuery("select s from Post s where  s.titolo = :parid", Post.class).setParameter("parid", titolo).getResultList();
    			
    	}

////qui dovrei ricorrere a un altro DAO: meglio metterlo dentro la BusinessLogic
    
    
    public List<Post> searchByUtenteName(String nomeAutore) {	//	
	
	return manager.createQuery("select s from Post s   "
			+ "where s.autore.nome = :parid", Post.class).setParameter("parid", nomeAutore).getResultList();
	//		
	}
    
    public List<Post> searchByUtenteNameAndDate(String nomeAutore, LocalDateTime time) {	//	
    	
    	return manager.createQuery("select s from Post s   "
    			+ "where s.autore.nome = :parid and s.data = :par2id", Post.class).setParameter("parid", nomeAutore).setParameter("par2id", time).getResultList();
    	//		
    	}

    public List<Post> sortByDate () {	//	
    	
    	return manager.createQuery("select s from Post s   "
    			+ "order by s.data DESC", Post.class).setFirstResult(0).setMaxResults(10).getResultList();
    			
    	}
    public List<Post> findByNomeCognome(String s) {
		return manager.createQuery("select s from Post s where s.autore.nome like :parNome OR s.autore.cognome like :parNome OR s.autore.nome = :par3Nome OR s.autore.cognome = :par4Cognome", Post.class)
				.setParameter("parNome", "%" + s + "%").setParameter("parNome", "%" + s + "%").setParameter("par3Nome", s ).setParameter("par4Cognome", s ).getResultList();
	}
	
	
	public List<Post> findByNomeCognome(String nome, String cognome) {
		return manager.createQuery("select s from Post  s where s.autore.nome like :parNome OR s.autore.cognome like :parNome OR s.autore.nome = :par3Nome OR s.autore.cognome = :par4Cognome OR s.autore.nome = :par5Nome OR s.autore.cognome = :par6Cognome ", Post.class)
				.setParameter("parNome", "%" + nome + "%").setParameter("parNome", "%" + cognome + "%").setParameter("par3Nome", nome ).setParameter("par4Cognome", cognome ).setParameter("par5Nome", cognome ).setParameter("par6Cognome", nome ).getResultList();
	}
	
    
    public List<Post> sortByUserAndDate(Integer id) {	
    	
    	return manager.createQuery("select s from Post s"
    			+ " where s.autore.id = :parid order "
    			+ "by s.data desc", Post.class).setParameter("parid", id).setFirstResult(0).setMaxResults(10).getResultList();
    			
    	}
        
    
    public List<Post> sortByNumberOfLikes () {	//	
    	
    	
    	
    	return manager.createQuery("select s from Post s   "
    			+ "order by s.listaLikePost DESC", Post.class).setFirstResult(0).setMaxResults(10).getResultList();
    			
    	}
        

	@Override
	public void update(Post ref) {
		// TODO Auto-generated method stub
		manager.merge(ref);		
	}

	@Override
	public void delete(Post ref) {
		// TODO Auto-generated method stub
		manager.remove(ref);
		
	}

}
