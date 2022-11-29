package com.social.ProgettoFinaleSocial.model;

import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Commento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	private String testo;
	@ManyToOne
	@JoinColumn (name = "utente_id")
	private Utente commenter;
	@ManyToOne
	@JoinColumn (name = "post_id")
	private Post post;
	private LocalDateTime data;
	
	
	
	public Commento() {
		super();
		// TODO Auto-generated constructor stub
	}


//commenter e post mi sar
	public Commento( String testo, Utente commenter, Post post, LocalDateTime data) {
		super();
		this.testo = testo;
		this.commenter = commenter;
		this.post = post;
		this.data = data;
	}

	

	public Commento(String testo, LocalDateTime data) {
	super();
	this.testo = testo;
	this.data = data;
}


	public Integer getId() {
		return Id;
	}



	public void setId(Integer id) {
		Id = id;
	}



	public String getTesto() {
		return testo;
	}



	public void setTesto(String testo) {
		this.testo = testo;
	}



	public Utente getCommenter() {
		return commenter;
	}



	public void setCommenter(Utente commenter) {
		this.commenter = commenter;
	}



	public Post getPost() {
		return post;
	}



	public void setPost(Post post) {
		this.post = post;
	}



	public LocalDateTime getData() {
		return data;
	}



	public void setData(LocalDateTime data) {
		this.data = data;
	}



	@Override
	public String toString() {
		return "Commento [Id=" + Id + ", testo=" + testo + ", commenter=" + commenter + ", post=" + post + ", data="
				+ data + "]";
	}
	
	
	
	
	
	
	

}
