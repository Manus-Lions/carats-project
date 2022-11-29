package com.social.ProgettoFinaleSocial.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.social.ProgettoFinaleSocial.model.Ruolo;
import com.social.ProgettoFinaleSocial.model.Utente;

public class RuoloDAO implements DAOInterface<Ruolo> {
	
	private EntityManager manager;
	
	
	public RuoloDAO(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public Ruolo create(Ruolo ref) {
		// TODO Auto-generated method stub
		manager.persist(ref);
		return ref;
	}

	@Override
	public List<Ruolo> retrieve() {
		// TODO Auto-generated method stub
		return manager.createQuery("select s from Ruolo s", Ruolo.class).getResultList();
	}
   
	//verifica se esiste già in DB il ruolo in questione (qualora intendiamo realizzare un UPDATE nel .xml).
	//scritto e testato
	public List<Ruolo>  searchByName(String nome) {
		
		return manager.createQuery("select r from Ruolo r where r.nome = :name", Ruolo.class).setParameter("name", nome).getResultList();
				
	}
	@Override
	public void update(Ruolo ref) {
		// TODO Auto-generated method stub
		manager.merge(ref);		
	}

	@Override
	public void delete(Ruolo ref) {
		// TODO Auto-generated method stub
		manager.remove(ref);
		
	}

}

