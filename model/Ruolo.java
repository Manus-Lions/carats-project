package com.social.ProgettoFinaleSocial.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Ruolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@OneToMany(mappedBy = "ruolo")
	private  List<Utente> utenti = new ArrayList<>();
	
	//costruttori
	public Ruolo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ruolo(String nome) {
		super();
		this.nome = nome;
	}
	
	
	
	//getters setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Utente>  getUtenti() {
		return utenti;
	}

	public void setUtenti(List<Utente> utenti) {
		
		this.utenti= utenti;
	}

	@Override
	public String toString() {
		return "Ruolo [id=" + id + ", nome=" + nome + "]";
	}
	
	
	
	
	
	
	
	
	

}
