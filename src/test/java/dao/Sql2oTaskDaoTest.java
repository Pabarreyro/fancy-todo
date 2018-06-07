package dao;

import models.Category;
import models.Task;
import org.junit.*;
import org.sql2o.*;

import static org.junit.Assert.*;

public class Sql2oTaskDaoTest {
    private Sql2oTaskDao taskDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        taskDao = new Sql2oTaskDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add_addsNewTaskCorrectly() {
        Task testTask = setupTask();
        int initialTaskId = testTask.getId();
        taskDao.add(testTask);
        assertNotEquals(initialTaskId, testTask.getId());
    }

    public Task setupTask(){
        return new Task("mow the lawn", 1);
    }
}