package com.social.ProgettoFinaleSocial.model;

import javax.persistence.*;

@Entity
public class Likes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn (name = "utente_id")
	private Utente liker;
	@ManyToOne
	@JoinColumn (name = "post_id")
	private Post post;
	private Boolean goodBad;
	
	
	
	public Likes() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Likes(Utente liker, Post post, Boolean goodBad) {
		super();
		this.liker = liker;
		this.post = post;
		this.goodBad = goodBad;
	}

	

	public Likes(Boolean goodBad) {
		super();
		this.goodBad = goodBad;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Utente getLiker() {
		return liker;
	}



	public void setLiker(Utente liker) {
		this.liker = liker;
	}



	public Post getPost() {
		return post;
	}



	public void setPost(Post post) {
		this.post = post;
	}



	public Boolean getGoodBad() {
		return goodBad;
	}



	public void setGoodBad(Boolean goodBad) {
		this.goodBad = goodBad;
	}



	@Override
	public String toString() {
		return "Like [id=" + id + ", liker=" + liker + ", post=" + post + ", goodBad=" + goodBad + "]";
	}
	
	
	
	
	

}
