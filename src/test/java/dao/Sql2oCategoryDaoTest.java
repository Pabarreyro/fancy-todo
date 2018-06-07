package dao;

import models.Category;
import org.junit.*;
import org.sql2o.*;

import static org.junit.Assert.*;

public class Sql2oCategoryDaoTest {
    private Sql2oCategoryDao categoryDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        categoryDao = new Sql2oCategoryDao(sql2o);
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

    public Category setupCategory() {
        return new Category("Yardwork");
    }
}