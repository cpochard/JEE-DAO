package fr.ex;

import java.util.List;

import fr.ex.dao.UserDao;
import fr.ex.entity.User;

public class Run {

	public static void main(String[] args) {
		
		UserDao udao = new UserDao();
		
		User u = new User();
		u.setLogin("juju");
		u.setPassword("478");
		
		//udao.save(u);
		
		//udao.delete(2);
		
		//u.setId(13);
		//udao.delete(u);
		
		//System.out.println(udao.getById(14));
		
		u.setId(15);
		u.setLogin("dkjf");
		u.setPassword("jsdqdf");
		udao.update(u);
		
		}
}
		
	


