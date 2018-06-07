package models;

import org.junit.*;

import static org.junit.Assert.*;

public class CategoryTest {

    @Test
    public void newCategory_instantiatesCorrectly_Name() throws Exception{
        Category testCategory = setupNewCategory();
        assertEquals("Yardwork", testCategory.getName());
    }

    public Category setupNewCategory() {
        return new Category("Yardwork");
    }

}