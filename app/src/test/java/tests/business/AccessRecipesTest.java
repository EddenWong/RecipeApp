package tests.business;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import application.Main;
import business.AccessRecipes;
import objects.Recipe;
import persistence.hsqldb.RecipePersistenceHSQLDB;
import tests.persistence.RecipePersistenceStub;

public class AccessRecipesTest
{
	private AccessRecipes accessRecipes;

	@Before
    public void setUp() {
	    this.accessRecipes = new AccessRecipes(new RecipePersistenceStub());
//		this.accessRecipes = new AccessRecipes(new RecipePersistenceHSQLDB(Main.getDBPathName()));
    }

    @Test
	public void test1()
	{
		final Recipe recipe = accessRecipes.getSequential();
		assertNotNull(recipe);
		assertTrue(4 == (recipe.getRecipeID()));
	}
}