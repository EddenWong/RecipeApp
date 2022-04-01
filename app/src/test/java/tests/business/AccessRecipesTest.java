package tests.business;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

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
//		this.accessRecipes = new AccessRecipes(new RecipePersistenceHSQLDB(new File("src/main/assets/db/SC.script").getAbsolutePath().replace(".script", "")));
//		this.accessRecipes = new AccessRecipes();
	}

	@Test
	public void test1()
	{
		final Recipe recipe = accessRecipes.getSequential();
		assertNotNull(recipe);
		assertTrue(4 == (recipe.getRecipeID()));
//		assertTrue("The Best Classic Chilli".equals(recipe.getName()));
	}

	@Test
	public void test2()
	{
		final List<Recipe> recipes = accessRecipes.getRecipes();
		assertNotNull(recipes);
		assertTrue(2 == (recipes.size()));
	}
}
