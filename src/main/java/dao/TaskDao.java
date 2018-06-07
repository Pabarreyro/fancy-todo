package dao;

import models.Task;

import java.util.List;

public class TaskDao {
    // CREATE
    void add(Task task);

    // LIST
    List<Task> getAll();

    // READ
    Task findById(int id);

    // UPDATE
    void update(int id, String name, int categoryId);

    // DELETE
    void deleteById(int id);
    void clearAll();
}
