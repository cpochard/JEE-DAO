package fr.ex.dao;

import java.util.List;

public interface IDAO <T>{
	void save(T u);
	void delete(T u);
	void delete(long id);
	void update(T u);
	T getById(long id);
	List<T> getAll();
}