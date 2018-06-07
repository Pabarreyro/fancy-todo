package dao;

import models.Category;
import models.Task;

import java.util.List;

public class CategoryDao {

    // CREATE
    void add(String name);

    // LIST
    List<Category> getAll();

    // READ
    Category findById(int id);
    List<Task> getAllTasksById(int categoryId);

    // UPDATE
    void update(int id, String name);

    // DELETE
    void deleteById(int id);
    void clearAll();

}
