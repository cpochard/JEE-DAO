package fr.ex.dao;

import java.util.List;

import fr.ex.access.AccessBD;

public abstract class AbstractDAO <T> {
	protected AccessBD abd = new AccessBD("jdbc:mysql://localhost:3306/chat", "root", "root");
	public abstract void save(T u);
	public abstract void delete(T u);
	public abstract void delete(long id);
	public abstract void update(T u);
	public abstract T getById(long id);
	public abstract List<T> getAll();
}
