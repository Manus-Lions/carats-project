package com.social.ProgettoFinaleSocial.model;

import java.io.File;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Immagine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Blob blob1;
	private String nome;
	
	
	public Blob getBlob() {
		return blob1;
	}


	public void setBlob(Blob blob) {
		this.blob1 = blob;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	@OneToOne(mappedBy = "immagine")
	private Post post;
	
	
	
	
	public Immagine() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Immagine(Post post) {
		super();
	
		this.post = post;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}




	public Post getPost() {
		return post;
	}


	public void setPost(Post post) {
		this.post = post;
	}

	
	
//attenzione al loop con post
	@Override
	public String toString() {
		return "Immagine [id=" + id + ", post=" + post + "]";
	}

	
	
}
