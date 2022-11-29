package com.social.ProgettoFinaleSocial.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.social.ProgettoFinaleSocial.model.Immagine;

public class ImmagineDAO implements DAOInterface<Immagine> {
	
	private EntityManager manager;
	
	
	public ImmagineDAO(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public Immagine create(Immagine ref) {
		// TODO Auto-generated method stub
		manager.persist(ref);
		return ref;
	}

	@Override
	public List<Immagine> retrieve() {
		// TODO Auto-generated method stub
		return manager.createQuery("select s from Immagine s", Immagine.class).getResultList();
	}

	@Override
	public void update(Immagine ref) {
		// TODO Auto-generated method stub
		manager.merge(ref);		
	}

	@Override
	public void delete(Immagine ref) {
		// TODO Auto-generated method stub
		manager.remove(ref);
		
	}

}
