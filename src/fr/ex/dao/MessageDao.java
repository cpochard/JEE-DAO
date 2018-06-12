package fr.ex.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.ex.entity.Message;

public class MessageDao extends AbstractDAO<Message>{
   
   public MessageDao() {
       super();
   }
   
   /*
    * NE JAMAIS FAIRE DE CONCATENATION DANS UNE REQUETE
    * IL FAUT FAIRE UNE REQUÊTE PRÉPARÉE !!!!!
    */
   @Override
   public void save(Message m) {
       int result = -1;
       String sql = "INSERT INTO message (texte, date, user_id) ";
       sql += "VALUE('" + m.getText() +  "','" + m.getDate() + "'," + m.getUserID() + ")";

       result = abd.updateSQL(sql);
   }

   @Override
   public void delete(Message u) {
   }

   @Override
   public void delete(long id) {
   }

   @Override
   public void update(Message u) {
   }

   @Override
   public Message getById(long id) {
       return null;
   }

   @Override
   public List<Message> getAll() {
       List<Message> messages = new ArrayList<>();
       ResultSet res = abd.executeSQL("SELECT * FROM message");

       try {
           // mapping des messages
           while (res.next()) {
               Message m = new Message();
               m.setDate(res.getDate("date"));
               m.setId(res.getLong("id"));
               m.setText(res.getString("texte"));
               m.setUserID(res.getLong("user_id"));
               messages.add(m);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return messages;
   }

}
