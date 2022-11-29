package com.social.ProgettoFinaleSocial.dao;

import java.util.List;

public interface DAOInterface<T> {

	public T create(T ref);
	
	public List<T> retrieve();
	
	public void update(T ref);
	
	public void delete(T ref);
	
}
