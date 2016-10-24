
package pe.edu.ulima.todoapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TodoDAO {
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/todoapp", "root", "");
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TodoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public void add(Todo todo, Connection conn) throws SQLException{
        String sql = "INSERT INTO todo (todo, timestamp) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setString(1, todo.getTodo());
        ps.setLong(2, todo.getTimestamp());
        
        ps.execute();
    }
}
