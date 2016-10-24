package pe.edu.ulima.todoapp;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.edu.ulima.todoapp.model.Todo;
import pe.edu.ulima.todoapp.model.TodoDAO;

@WebServlet(name = "TodoResource", urlPatterns = {"/todo"})
public class TodoResource extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String req = getStringFromInputStream(request.getInputStream());
        
        Gson gson = new Gson();
        System.out.println(req);
        TodoRequest todoRequest = gson.fromJson(req, TodoRequest.class);
        
        TodoDAO todoDAO = new TodoDAO();
        Connection conn = todoDAO.getConnection();
        
        Todo todo = new Todo();
        todo.setTodo(todoRequest.getTodo());
        todo.setTimestamp(todoRequest.getTimestamp());
        
        TodoResponse todoResponse;
        try {
            todoDAO.add(todo, conn);
            todoResponse = new TodoResponse("OK");
        } catch (SQLException ex) {
            // Error en el guardado en bd
            todoResponse = new TodoResponse("Error guardando en la base de datos: " + ex.getMessage());
        }
        
        response.setContentType("application/json;charset=UTF-8");
        
        String resp = gson.toJson(todoResponse);
        response.getWriter().write(resp);
    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

}
