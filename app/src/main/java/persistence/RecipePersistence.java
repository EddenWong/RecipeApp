package comp3350.recipeapp.persistence;

import java.util.List;

import comp3350.recipeapp.objects.Recipe;

public interface RecipePersistence {

    List<Recipe> getRecipes();

    Recipe insertRecipe(Recipe currentRecipe);

    Recipe updateRecipe(Recipe currentRecipe);

    void deleteRecipe(Recipe currentRecipe);
}
