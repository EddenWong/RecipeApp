package tests.business;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import application.Main;
import business.AccessGroceryList;
import business.AccessRecipes;
import objects.Recipe;
import persistence.hsqldb.RecipePersistenceHSQLDB;
import tests.persistence.GroceryPersistenceStub;
import tests.persistence.RecipePersistenceStub;

public class AccessGroceryListTest
{
    private AccessGroceryList accessGroceryList;

    @Before
    public void setUp() {
        this.accessGroceryList = new AccessGroceryList(new GroceryPersistenceStub());
//		this.accessRecipes = new AccessRecipes(new RecipePersistenceHSQLDB(Main.getDBPathName()));
//		this.accessRecipes = new AccessRecipes(new RecipePersistenceHSQLDB(new File("src/main/assets/db/SC.script").getAbsolutePath().replace(".script", "")));
//		this.accessRecipes = new AccessRecipes();
    }

    @Test
    public void test1()
    {
        final String item = accessGroceryList.getSequential();
        assertNotNull(item);
        assertTrue("Egg".equals(item));
//		assertTrue("The Best Classic Chilli".equals(recipe.getName()));
    }

    @Test
    public void test2()
    {
        final List<String> items = accessGroceryList.getGroceryList();
        assertNotNull(items);
        assertTrue(3 == (items.size()));
    }
}
