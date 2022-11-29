package com.social.ProgettoFinaleSocial.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.social.ProgettoFinaleSocial.model.Commento;
import com.social.ProgettoFinaleSocial.model.Utente;

public class CommentoDAO implements DAOInterface<Commento> {
	
	private EntityManager manager;
	
	
	public CommentoDAO(EntityManager manager) {
		
		super();
		this.manager = manager;
	}

	@Override
	public Commento create(Commento ref) {
		// TODO Auto-generated method stub
		manager.persist(ref);
		return ref;
	}

	@Override
	public List<Commento> retrieve() {
		// TODO Auto-generated method stub
		return manager.createQuery("select s from Commento s", Commento.class).getResultList();
	}

	@Override
	public void update(Commento ref) {
		// TODO Auto-generated method stub
		manager.merge(ref);		
	}

	@Override
	public void delete(Commento ref) {
		// TODO Auto-generated method stub
		manager.remove(ref);
		
	}

	//scritto e testato
	public List<Commento> searchByPostId(Integer id) {
		
		return manager.createQuery("select s from Commento s where s.post.id = :parCommento", Commento.class).setParameter("parCommento", id).getResultList();
		
	}
	
	
	
}
