package dao;

import models.Task;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oTaskDao implements TaskDao {
    private final Sql2o sql2o;

    public Sql2oTaskDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(Task task){
        String sql = "INSERT INTO tasks (description, categoryId) VALUES (:description, :categoryId)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(task)
                    .executeUpdate()
                    .getKey();
            task.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Task> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM tasks")
                    .executeAndFetch(Task.class);
        }
    }

    @Override
    public Task findById(int id) {
        String sql = "SELECT * FROM tasks WHERE id=:id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Task.class);
        }
    }

    @Override
    public void update(int id, String name, int categoryId) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }


}
