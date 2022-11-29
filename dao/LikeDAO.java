package com.social.ProgettoFinaleSocial.dao;

import java.util.List;



import javax.persistence.EntityManager;
import javax.persistence.*;

import com.social.ProgettoFinaleSocial.model.Commento;
import com.social.ProgettoFinaleSocial.model.Likes;
import com.social.ProgettoFinaleSocial.model.Post;

public class LikeDAO implements DAOInterface<Likes> {
	
	private EntityManager manager;
	
	
	public LikeDAO(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public Likes create(Likes ref) {
		// TODO Auto-generated method stub
		manager.persist(ref);
		return ref;
	}

	@Override
	public List<Likes> retrieve() {
		// TODO Auto-generated method stub
		return manager.createQuery("select s from Likes s", Likes.class).getResultList();
	}

	@Override
	public void update(Likes ref) {
		// TODO Auto-generated method stub
		manager.merge(ref);		
	}

	@Override
	public void delete(Likes ref) {
		// TODO Auto-generated method stub
		manager.remove(ref);
		
	}
	
	//scritto e non testato
	public List<Likes> searchByPostId(Integer id) {
		
		return manager.createQuery("select s from Likes s where s.post.id = :parPost", Likes.class).setParameter("parPost", id).getResultList();
		
		
	}
	
	//scritto e non testato
    public List<Likes> searchByUtenteId(Integer id) {
		
		return manager.createQuery("select s from Likes s where s.utente_id = :parPost", Likes.class).setParameter("parPost", id).getResultList();
		
		
	}
    
    

}
