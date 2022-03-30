package tests.business;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

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
//	    this.accessRecipes = new AccessRecipes(new RecipePersistenceStub());
//		this.accessRecipes = new AccessRecipes(new RecipePersistenceHSQLDB(Main.getDBPathName()));
//		this.accessRecipes = new AccessRecipes(new RecipePersistenceHSQLDB(new File("src/main/assets/db/SC.script").getAbsolutePath().replace(".script", "")));
		this.accessRecipes = new AccessRecipes(new RecipePersistenceHSQLDB(Main.getDBPathName()));
	}

	@Test
	public void test1()
	{
		final Recipe recipe = accessRecipes.getSequential();
		assertNotNull(recipe);
		assertTrue(1 == (recipe.getRecipeID()));
	}
}
