package dao;

import models.Category;
import models.Task;
import org.junit.*;
import org.sql2o.*;

import static org.junit.Assert.*;

public class Sql2oCategoryDaoTest {
    private Sql2oCategoryDao categoryDao;
    private Sql2oTaskDao taskDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        categoryDao = new Sql2oCategoryDao(sql2o);
        taskDao = new Sql2oTaskDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add_addsNewCategoryCorrectly() {
        Category testCategory = setupCategory();
        int initialId = testCategory.getId();
        categoryDao.add(testCategory);
        assertNotEquals(initialId, testCategory.getId());
    }

    @Test
    public void getAll_returnsAllExistingCategories_2() {
        Category testCategory = setupCategory();
        Category testCategory2 = new Category("Laundry");
        categoryDao.add(testCategory);
        categoryDao.add(testCategory2);
        assertEquals(2, categoryDao.getAll().size());
    }

    @Test
    public void getAll_returnsZeroWhenNoCategoriesExist_0() {
        assertEquals(0, categoryDao.getAll().size());
    }

    @Test
    public void getAllTasksById_returnsAllExistingTasks_2() {
        Category testCategory = setupCategory();
        categoryDao.add(testCategory);
        int assisgnedId = testCategory.getId();
        taskDao.add(new Task("mow the lawn", assisgnedId));
        taskDao.add(new Task("trim the hedges", assisgnedId));
        assertEquals(2, categoryDao.getAllTasksById(assisgnedId));
    }

    @Test
    public void findById_returnsCorrectCategory() {
        Category testCategory = setupCategory();
        categoryDao.add(testCategory);
        int assignedId = testCategory.getId();
        assertEquals(testCategory, categoryDao.findById(assignedId));
    }

    @Test
    public void update_setsNewValuesCorrectly() {
        Category testCategory = setupCategory();
        String originalName = testCategory.getName();
        categoryDao.add(testCategory);
        int assignedId = testCategory.getId();
        categoryDao.update(assignedId, "Gardening");
        Category updatedCategory = categoryDao.findById(assignedId);
        assertEquals(originalName, updatedCategory.getName());
    }

    @Test
    public void deleteById_removesCorrectCategory() {
        Category testCategory = setupCategory();
        Category testCategory2 = new Category("Laundry");
        categoryDao.add(testCategory);
        categoryDao.add(testCategory2);
        int assignedId = testCategory.getId();
        categoryDao.deleteById(assignedId);
        assertTrue(categoryDao.getAll().contains(testCategory2));
        assertFalse(categoryDao.getAll().contains(testCategory));
    }

    public Category setupCategory() {
        return new Category("Yardwork");
    }
}