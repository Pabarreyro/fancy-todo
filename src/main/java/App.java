import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.CategoryDao;
import dao.Sql2oCategoryDao;
import dao.Sql2oTaskDao;
import dao.TaskDao;
import models.Category;
import models.Task;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {

    public static void main(String[] args) { //type “psvm + tab” to autocreate this
        staticFileLocation("/public");

        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        CategoryDao categoryDao = new Sql2oCategoryDao(sql2o);
        TaskDao taskDao = new Sql2oTaskDao(sql2o);
        Connection conn = sql2o.open();

        // get: display all categories and tasks therein
        get("/", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Category> categories = categoryDao.getAll();
            List<Task> tasks = taskDao.getAll();
            model.put("categories", categories);
            model.put("tasks", tasks);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        // get: display new category form (display all categories)
        get("/categories/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "category-form.hbs");
        }, new HandlebarsTemplateEngine());

        // post: submit category form (redirect to /)
        post("/categories", (req, res) -> {
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        // get: display single category by id, with all related tasks (display all categories)
        get("/categories/:id", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "category-details.hbs");
        }, new HandlebarsTemplateEngine());

        // get: display update form for single category by id (display all categories)
        get("/categories/:id/update", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "category-form.hbs");
        }, new HandlebarsTemplateEngine());

        // post: submit category update form (redirect to /categories/:id)
        post("/categories/:id/update", (req, res) -> {
            res.redirect("/categories/:id");
            return null;
        }, new HandlebarsTemplateEngine());

        // get: display new task form for single category by id (display all categories)
        // /categories/:id/tasks/new

        // post: submit new task to single category (redirect to /categories/:id)
        // /categories/:id/task

        // get: display new task form (display all categories)
        get("/tasks/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "task-form.hbs");
        }, new HandlebarsTemplateEngine());

        // post: submit new task form (redirect to /)
        post("/tasks", (req, res) -> {
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        // get: display task update form (display all categories)
        get("/tasks/:id/update", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "task-form.hbs");
        }, new HandlebarsTemplateEngine());

        // post: submit task update form (redirect to /categories/:id)
        post("/tasks/:id/update", (req, res) -> {
            res.redirect("/categories/:id");
            return null;
        }, new HandlebarsTemplateEngine());

        // post: delete category by id & all related tasks (redirect to /)
        // /categories/:id/delete
        post("/categories/:id/delete", (req, res) -> {
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        // post: delete all categories & tasks (redirect to /)
        post("/categories/delete", (req, res) -> {
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        // post: delete task by id (redirect to /categories/:id)
        post("/tasks/:id/delete", (req, res) -> {
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        // post: delete all tasks (redirect to /)
        post("/tasks/delete", (req, res) -> {
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
    }
}
