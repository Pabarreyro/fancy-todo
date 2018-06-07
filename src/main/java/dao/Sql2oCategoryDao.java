package dao;

import models.Category;
import models.Task;
import org.sql2o.*;

import java.util.List;

public class Sql2oCategoryDao implements CategoryDao {
    private final Sql2o sql2o;

    public Sql2oCategoryDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Category category) {
        String sql = "INSERT into categories (name) VALUES (:name)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true).bind(category).executeUpdate().getKey();
            category.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Category> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM categories")
                    .executeAndFetch(Category.class);
        }
    }

    @Override
    public Category findById(int id) {
        String sql = "SELECT * FROM categories WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Category.class);
        }
    }

    @Override
    public List<Task> getAllTasksById(int categoryId) {
        String sql = "SELECT * FROM tasks WHERE categoryId = :categoryId";
        try (Connection con = sql2o.open()) {
            return  con.createQuery(sql)
                    .addParameter("categoryId", categoryId)
                    .executeAndFetch(Task.class);
        }
    }

    @Override
    public void update(int id, String name) {
        String sql = "UPDATE categories SET name = :name where id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from categories WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from categories";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
