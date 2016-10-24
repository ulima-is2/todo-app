
package pe.edu.ulima.todoapp;

public class TodoRequest {
    private String todo;
    private long timestamp;

    public TodoRequest() {
    }

    public TodoRequest(String todo, long timestamp) {
        this.todo = todo;
        this.timestamp = timestamp;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    
}
