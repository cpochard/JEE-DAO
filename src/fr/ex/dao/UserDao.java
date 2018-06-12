package fr.ex.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.ex.access.AccessBD;
import fr.ex.entity.User;

public class UserDao extends AbstractDAO <User> {
	
	
	public UserDao() {
	}

	@Override
	public void save(User u) {
		 /*
		  * traitement pour la sauvegarde de l'utilisateur
		  */
		String sql = "INSERT INTO user (login, password)";
		sql+= " VALUE('"+u.getLogin() + "', '"+u.getPassword()+"')";
		
		System.out.println(sql);
		int res = abd.updateSQL(sql);
	}
	
	@Override
	public void delete (User u) {
		/*
		 * Suppression d'un utilisateur
		 */
		this.delete(u.getId());
	}
	
	@Override
	public void delete (long id) {
		/*
		 * Suppression d'un utilisateur
		 */
		String sql = "DELETE FROM user";
		sql+=" WHERE id="+id+" LIMIT 1";
		System.out.println(sql);
		int res=abd.updateSQL(sql);
	}
	
	@Override
	public void update (User u) {
		/*
		 * Mettre à jour un utilisateur
		 */
		String sql= "UPDATE user SET login ='"+u.getLogin()+"'";
		sql+= ", password='"+u.getPassword()+"'";
		sql+= " WHERE id="+u.getId();
		int res=abd.updateSQL(sql);
	}
	
	@Override
	public User getById (long id) {
		/*
		 * Récupération d'un utilisateur
		 */
		String sql="SELECT * FROM user WHERE id="+id;
		ResultSet res = abd.executeSQL(sql);
		User u = new User();
		try {
			res.next();
			u.setId(res.getLong("id"));
			u.setLogin(res.getString("login"));
			u.setPassword(res.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	@Override
	public List<User> getAll(){
		/*
		 * On récupère tous les utilisateurs
		 */
		List<User> users = new ArrayList<>();
		ResultSet res = abd.executeSQL("SELECT * FROM user");
		
		try {
			//Mapping des utilisateurs
			while (res.next()) {
				User u = new User ();
				u.setId(res.getLong("id"));
				u.setLogin(res.getString("login"));
				u.setPassword(res.getString("password"));
				users.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public User getByLoginPassword(String login, String password) {
		User u = null;
		
		String sql = "SELECT * FROM user";
		sql+= " WHERE login= '"+login+"'";
		sql+= " AND password='"+ password+"'";
		ResultSet res = abd.executeSQL(sql);
		
		try {
			if(res.next()) {
				u=new User();
				u.setId(res.getLong("id"));
				u.setLogin(res.getString("login"));
				u.setPassword(res.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return u;
	}
}
