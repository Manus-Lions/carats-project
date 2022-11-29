package com.social.ProgettoFinaleSocial.model;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

import com.social.ProgettoFinaleSocial.model.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
	private String nome;
	private String cognome;
	@OneToMany(mappedBy = "autore")
	private List<Post> listaPost = new ArrayList<Post>();
	@OneToMany(mappedBy = "liker")
	private List<Likes> listaLikeUtente = new ArrayList<Likes>();
	@OneToMany (mappedBy = "commenter")
	private List<Commento> listaCommentoUtente = new ArrayList<Commento>();
	@ManyToOne
	@JoinColumn (name = "ruolo_id")
	private Ruolo ruolo;
	
	//costruttori
	
	
	public Utente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Utente(String username, String password, String nome, String cognome) {
		super();
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.ruolo = new Ruolo("user"); 
	}
	

	
	//getters setters
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return the listaPost
	 */
	public List<Post> getListaPost() {
		return listaPost;
	}

	/**
	 * @param listaPost the listaPost to set
	 */
	public void setListaPost(List<Post> listaPost) {
		this.listaPost = listaPost;
	}

	/**
	 * @return the listaLikeUtente
	 */
	public List<Likes> getListaLikeUtente() {
		return listaLikeUtente;
	}

	/**
	 * @param listaLikeUtente the listaLikeUtente to set
	 */
	public void setListaLikeUtente(List<Likes> listaLikeUtente) {
		this.listaLikeUtente = listaLikeUtente;
	}

	/**
	 * @return the listaCommentoUtente
	 */
	public List<Commento> getListaCommentoUtente() {
		return listaCommentoUtente;
	}

	/**
	 * @param listaCommentoUtente the listaCommentoUtente to set
	 */
	public void setListaCommentoUtente(List<Commento> listaCommentoUtente) {
		this.listaCommentoUtente = listaCommentoUtente;
	}

	/**
	 * @return the ruolo
	 */
	public Ruolo getRuolo() {
		return ruolo;
	}

	/**
	 * @param ruolo the ruolo to set
	 */
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", username=" + username + ", password=" + password + ", nome=" + nome
				+ ", cognome=" + cognome + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
