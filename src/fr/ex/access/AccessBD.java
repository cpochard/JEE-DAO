package fr.ex.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessBD {
	private String url;
	private String login;
	private String mdp;
	private Connection cnx;
	
	 /**
	  * Constructeur de AccessBD, il enregistre automatiquement le driver mysql
	  * Faire attention d'avoir la dépendance mysql dans pomxml !!!
	  * @param url : url de type jdbc exemple: jdbc.mysql://localhost:3306/chat
	  * @param login :  login de la BDD
	  * @param mdp : mot de passe de la BDD
	  */
	public AccessBD(String url, String login, String mdp) {
		this.url=url;
		this.login=login;
		this.mdp=mdp;
		
		try {
			//On charge/enregistre le driver(com.mysql.jdbc.Driver) dans le DriverManager
			//Chargement dynamique, revient à faire com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver()
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Le driver jdbc n'a pas pu être lancé, vérifier les dépendances");
		}
	}

	/**
	 * Création de l'unique connexion
	 * Moche d'avoir une seule connexion mais c'est pour l'exemple
	 * @return java.sql.Connection
	 */
	public Connection getConnection() {
        // si connexion est null ou fermée, on la réouvre
		//Donc création de la connexion vers la BDD, retourne un objet connection
        try {
            if(cnx==null || cnx.isClosed()) {
                cnx = DriverManager.getConnection(url, login, mdp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
			//Si pas de connexion, au lieu de juste afficher un message d'erreur, on lui dit d'arrêter le programme
            throw new RuntimeException("la création de la connexion n'a pas pu avoir lieu");
        }
        //Sinon on donne la connexion
        return cnx;
    }
	
	/**
	 * Juste pour la lecture (juste pour select) : Execute une requête SQL et retourne un résultat
	 * @param sql : une requête SQL exemple : "SELECT * from table"
	 * @return : ResultSet reponse
	 */
	public ResultSet executeSQL(String sql) {
		this.getConnection();
		
		PreparedStatement req = null;
		ResultSet rep=null;
		try {
			//Création de la requête
			req = cnx.prepareStatement(sql);
			//Exécution de la requête, donc récupération de la réponse
			rep = req.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return rep;
	}
	
	 /**
	  * Idem que ci dessus mais
	  * Pour tout ce qui est Mise a jour, ajout ou suppression
	  * @param sql
	  * @return
	  */
	public int updateSQL(String sql) {
		this.getConnection();
		PreparedStatement req = null;
		int rep=-1;
		try {
			//Création de la requête
			req = cnx.prepareStatement(sql);
			//Exécution de la requête, donc récupération de la réponse
			req.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return rep;
	}
	
	/**
	 * Fermeture de la seule connexion que nous avons
	 * Moche d'avoir une seule connexion mais c'est pour l'exemple
	 */
	public void close() {
		// fermeture de la connexion
        if(cnx!=null) {
            try {
                cnx.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
}
