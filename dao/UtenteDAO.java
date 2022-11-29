package com.social.ProgettoFinaleSocial.dao;

import java.util.List;

import javax.persistence.EntityManager;


import com.social.ProgettoFinaleSocial.model.Utente;

public class UtenteDAO implements DAOInterface<Utente> {
	
	private EntityManager manager;
	
	
	public UtenteDAO(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public Utente create(Utente ref) {
		// TODO Auto-generated method stub
		manager.persist(ref);
		return ref;
	}

	@Override //retrieve è l'equivalente di  una select all
	public List<Utente> retrieve() {
		// TODO Auto-generated method stub
		return manager.createQuery("select s from Utente s", Utente.class).getResultList();
	}

	//SCRITTO E TESTATO (può risultare necessario ricorrere a manager.clear() prima dell'invocazione)
	public List<Utente> searchUtentiByNome(String s) {
		
		return manager.createQuery("select s from Utente s where s.nome = :parUtente", Utente.class).setParameter("parUtente", s).getResultList();
				
	}
	
	public List<Utente> searchUtentiByUsername(String s) {
		
		return manager.createQuery("select s from Utente s where s.username = :parUtente", Utente.class).setParameter("parUtente", s).getResultList();
				
	}
	
public List<Utente> searchUtentiByUsernameAndPassword(String username, String password) {
		
		return manager.createQuery("select s from Utente s where s.username = :parUtente AND s.password "
				+ "= :par2Utente", Utente.class).setParameter("parUtente", username).setParameter("par2Utente", password).getResultList();
				
	}
	
	
	//SCRITTO E TESTATO
	public List<Utente> searchUtentiByID(Integer id) {
		
		return manager.createQuery("select s from Utente s where s.id = :parUtente", Utente.class).setParameter("parUtente", id).getResultList();
				
	}
	
	
	@Override
	public void update(Utente ref) {
		// TODO Auto-generated method stub
		manager.merge(ref);		
	}

	@Override
	public void delete(Utente ref) {
		
		try {
		// TODO Auto-generated method stub
		manager.remove(ref);
		}
		
		catch (Exception e) {
			
			System.out.println("problema");
		}
		
	}
	
	
	//SCRITTO E TESTATO
	//restituisce i valori che contengono la stringa s (ma non all'inizio e alla fine del nome)
	
	//il cablaggio % dovrebbe essere richiesto a priori dalla UI
	public List<Utente> findByNomeCognome(String s) {
		return manager.createQuery("select s from Utente s where s.nome like :parNome OR s.cognome like :parNome OR s.nome = :par3Nome OR s.cognome = :par4Cognome", Utente.class)
				.setParameter("parNome", "%" + s + "%").setParameter("parNome", "%" + s + "%").setParameter("par3Nome", s ).setParameter("par4Cognome", s ).getResultList();
	}
	
	
	public List<Utente> findByNomeCognome(String nome, String cognome) {
		return manager.createQuery("select s from Utente s where s.nome like :parNome OR s.cognome like :parNome OR s.nome = :par3Nome OR s.cognome = :par4Cognome OR s.nome = :par5Nome OR s.cognome = :par6Cognome ", Utente.class)
				.setParameter("parNome", "%" + nome + "%").setParameter("parNome", "%" + cognome + "%").setParameter("par3Nome", nome ).setParameter("par4Cognome", cognome ).setParameter("par5Nome", cognome ).setParameter("par6Cognome", nome ).getResultList();
	}
	

}
