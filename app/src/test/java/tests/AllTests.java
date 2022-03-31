package tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tests.business.AccessRecipesIT;
import tests.business.AccessRecipesTest;
import tests.objects.RecipeTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessRecipesIT.class,
        AccessRecipesTest.class,
        RecipeTest.class,
})
public class AllTests
{

}
