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

    @Test
    public void getAll_returnsAllExistingTasks_2() {
        Task testTask= setupTask();
        Task testTask2= new Task("wash linens", 2);
        taskDao.add(testTask);
        taskDao.add(testTask2);
        assertEquals(2, taskDao.getAll().size());
    }

    @Test
    public void getAll_returnsZeroWhenNoTasksExist_0() {
        assertEquals(0, taskDao.getAll().size());
    }

    @Test
    public void findById_returnsCorrectTask() {
        Task testTask= setupTask();
        taskDao.add(testTask);
        int assignedId = testTask.getId();
        assertEquals(testTask, taskDao.findById(assignedId));
    }

    @Test
    public void update_setsNewValuesProperly() {
        Task testTask = setupTask();
        String originalDescription = testTask.getDescription();
        int originalCategoryId = testTask.getCategoryId();
        taskDao.add(testTask);
        int assignedId = testTask.getId();
        taskDao.update(assignedId, "trim the hedges", 2);
        Task updatedTask = taskDao.findById(assignedId);
        assertNotEquals(originalDescription, updatedTask.getDescription());
        assertNotEquals(originalCategoryId, updatedTask.getCategoryId());
        assertEquals(assignedId, updatedTask.getId());
    }

    @Test
    public void deleteById_removesCorrectTask() {
        Task testTask= setupTask();
        Task testTask2= new Task("wash linens", 2);
        taskDao.add(testTask);
        taskDao.add(testTask2);
        int assignedId = testTask.getId();
        taskDao.deleteById(assignedId);
        assertTrue(taskDao.getAll().contains(testTask2));
        assertFalse(taskDao.getAll().contains(testTask));
    }

    @Test
    public void clearAll_removesAllExistingTasks_0() {
        Task testTask= setupTask();
        Task testTask2= new Task("wash linens", 2);
        taskDao.add(testTask);
        taskDao.add(testTask2);
        taskDao.clearAll();
        assertEquals(0, taskDao.getAll().size());
    }

    public Task setupTask(){
        return new Task("mow the lawn", 1);
    }
}