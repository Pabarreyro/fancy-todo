package dao;

import models.Category;
import models.Task;

import java.util.List;

public interface CategoryDao {

    // CREATE
    void add(Category category);

    // LIST
    List<Category> getAll();

    // READ
    Category findById(int id);
    List<Task> getAllTasksById(int categoryId);

    // UPDATE
    void update(int id, String name, int taskCount);

    // DELETE
    void deleteById(int id);
    void clearAll();

}
