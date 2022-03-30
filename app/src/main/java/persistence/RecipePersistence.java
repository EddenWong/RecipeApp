package persistence;

import java.util.List;

import objects.Recipe;

public interface RecipePersistence {

    List<Recipe> getRecipes();

    List<Recipe> getRecipe(Recipe currentRecipe);

    Recipe insertRecipe(Recipe currentRecipe);

    Recipe updateRecipe(Recipe currentRecipe);

    void deleteRecipe(Recipe currentRecipe);
}
