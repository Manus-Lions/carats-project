package com.social.ProgettoFinaleSocial.utils;


import com.social.ProgettoFinaleSocial.model.Post;
import com.social.ProgettoFinaleSocial.model.Utente;


public class Utility {		

	//SCRITTO, TESTATO E FUNZIONANTE
	public static boolean checkUsername(String username) {
		username = username.stripLeading().stripTrailing();

		if(username.isBlank() || username.isEmpty())
			return false;

		if(!username.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"))
			return false;


		return true;		        
	}

	//SCRITTO, TESTATO E FUNZIONANTE
	public static boolean checkPassword(String password) {
		if(password.isBlank() || password.isEmpty() || password.length()<8 || password.length()>20)
			return false;

		return true;	
	}
	//NON ANCORA SCRITTO
	public static boolean checkRuolo() {
		return false;	
	}

	//SCRITTO TESTATO E FUNZIONANTE
	public static String nomePrimaMaiuscola(String nome) {
		String nomeOutput = nome.substring(0, 1).toUpperCase() + nome.substring(1);
		return nomeOutput;
	}
	//SCRITTO TESTATO E FUNZIONANTE
	public static String cognomePrimaMaiuscola(String cognome) {
		String cognomeOutput = cognome.substring(0, 1).toUpperCase() + cognome.substring(1);
		return cognomeOutput;
	}
	//SCRITTO TESTATO E FUNZIONANTE
	public static boolean checkNomeCognome(String nome, String cognome) {
		nome = nome.stripLeading().stripTrailing();
		cognome = cognome.stripLeading().stripTrailing();
		if( nome.isEmpty() || cognome.isEmpty() || nome.isBlank() || cognome.isBlank())
			return false;


		for(int i = 0; i < nome.length(); i++) {
			char c = nome.charAt(i);
			if((c < 'A' ||  c > 'Z') && (c < 'a' ||  c > 'z') && c != ' ') {
				return false;
			}
		}
		for(int i = 0; i < cognome.length(); i++) {
			char c = cognome.charAt(i);
			if((c < 'A' ||  c > 'Z') && (c < 'a' ||  c > 'z') && c != ' ') {
				return false;
			}
		}
		return true;
	}
	//SCRITTO TESTATO E FUNZIONANTE
	public static boolean checkAutorePost(Utente u, Post p) {

		if(p.getAutore().getUsername().equals(u.getUsername()))
			return false;

		return true;
	}
	//SCRITTO TESTATO E FUNZIONANTE
	public static boolean checkTesto(String testo) {
		testo.stripLeading().stripTrailing();
		if(testo.isBlank() || testo.isEmpty())
			return false;

		return true;
	}
	//SCRITTO TESTATO CON VALORE 5 E FUNZIONANTE
	public static boolean checkTestoLunghezza(String testo) {
		testo = testo.stripLeading().stripTrailing();
		if(testo.length()>1999) 
			return false;

		return true;
	}

	public static boolean checkContenuto() {
		return false;
	}
	//SCRITTO TESTATO E FUNZIONANTE
	public static boolean checkTitolo(String titolo) {
		titolo.stripLeading().stripTrailing();
		if(titolo.isEmpty() || titolo.isBlank())
			return false;

		return true;
	}

	//SCRITTO TESTATO CON VALORE 3 E FUNZIONANTE
	public static boolean checkCommento(String commento) {
		commento.stripLeading().stripTrailing();
		if(commento.isEmpty()  || commento.length()>999 || commento.isBlank())
			return false;

		return true;
	}	

}