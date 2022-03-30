package tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import business.AccessRecipes;
import objects.Recipe;
import persistence.RecipePersistence;
import persistence.hsqldb.RecipePersistenceHSQLDB;
import tests.utils.TestUtils;

public class AccessRecipesIT {
    private AccessRecipes accessRecipes;
    private File tempDB;


    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final RecipePersistence persistence = new RecipePersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessRecipes = new AccessRecipes(persistence);
    }

    @Test
    public void testListRecipes() {
        final Recipe recipe;

        recipe = accessRecipes.getSequential();
        assertNotNull("first sequential recipe should not be null", recipe);
        assertTrue(1 == (recipe.getRecipeID()));

        System.out.println("Finished test AccessRecipes");
    }

    @Test
    public void testGetRecipes() {
        final List<Recipe> recipes = accessRecipes.getRecipes();
        assertEquals(5, recipes.size());
    }

    @Test
    public void testDeleteRecipe() {
        final Recipe res = accessRecipes.getSequential();
        List<Recipe> recipes = accessRecipes.getRecipes();
        assertEquals(5, recipes.size());
        accessRecipes.deleteRecipe(res);
        recipes = accessRecipes.getRecipes();
        assertEquals(4, recipes.size());
    }

    @Test
    public void testInsertRecipe() {
        ArrayList<String> ingredientList = new ArrayList<>();
        ingredientList.add("Random ingred");

        ArrayList<String> categoryList = new ArrayList<>();
        categoryList.add("Random Category");

        final Recipe res = new Recipe(10, "Test Beef", "US", ingredientList, 5, 10, "Easy", "Description for recipe", "Instruction for recipe", "random link", categoryList);
        accessRecipes.insertRecipe(res);
        assertEquals(6, accessRecipes.getRecipes().size());
    }

    @Test
    public void testUpdateRecipe() {
        ArrayList<String> ingredientList = new ArrayList<>();
        ingredientList.add("Random ingred");

        ArrayList<String> categoryList = new ArrayList<>();
        categoryList.add("Random Category");

        final Recipe res = accessRecipes.getSequential();
        final Recipe res2 = new Recipe(res.getRecipeID(), "Test Beef", "US", ingredientList, 5, 10, "Easy", "Description for recipe", "Instruction for recipe", "random link", categoryList);
        accessRecipes.updateRecipe(res2);
        assertEquals(5, accessRecipes.getRecipes().size());
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}
