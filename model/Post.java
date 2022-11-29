package com.social.ProgettoFinaleSocial.model;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titolo;
	private String testo;
	private LocalDateTime data;
	@OneToOne
	private Immagine immagine;
	@ManyToOne
	@JoinColumn (name = "utente_id")
	private Utente autore;
	@OneToMany (mappedBy = "post")
	private List<Commento> listaCommentiPost = new ArrayList<Commento>();
	@OneToMany (mappedBy = "post")
	private List<Likes> listaLikePost = new ArrayList<Likes>();
	
	
	
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Post(String titolo, String testo, LocalDateTime data, Utente autore) {
		super();
		this.titolo = titolo;
		this.testo = testo;
		this.data = data;
		this.autore = autore;
	}
	
	
	
	
	public Post(String titolo, String testo, LocalDateTime data) {
		super();
		this.titolo = titolo;
		this.testo = testo;
		this.data = data;
	}


	public Post(String titolo, String testo, LocalDateTime data, Immagine immagine, Utente autore) {
		super();
		this.titolo = titolo;
		this.testo = testo;
		this.data = data;
		this.immagine = immagine;
		this.autore = autore;
	}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitolo() {
		return titolo;
	}


	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}


	public String getTesto() {
		return testo;
	}


	public void setTesto(String testo) {
		this.testo = testo;
	}


	public LocalDateTime getData() {
		return data;
	}


	public void setData(LocalDateTime data) {
		this.data = data;
	}


	public Immagine getImmagine() {
		return immagine;
	}


	public void setImmagine(Immagine immagine) {
		this.immagine = immagine;
	}


	public Utente getAutore() {
		return autore;
	}


	public void setAutore(Utente autore) {
		this.autore = autore;
	}


	public List<Commento> getListaCommentiPost() {
		return listaCommentiPost;
	}


	public void setListaCommentiPost(List<Commento> listaCommentiPost) {
		this.listaCommentiPost = listaCommentiPost;
	}


	public List<Likes> getListaLikePost() {
		return listaLikePost;
	}


	public void setListaLikePost(List<Likes> listaLikePost) {
		this.listaLikePost = listaLikePost;
	}


	@Override
	public String toString() {
		return "Post [id=" + id + ", titolo=" + titolo + ", testo=" + testo + ", data=" + data + ", immagine="
				+ immagine + ", autore=" + autore + "]";
	}
	
	
	
	
	
	

}
