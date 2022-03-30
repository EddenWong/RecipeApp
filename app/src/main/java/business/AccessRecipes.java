package business;

import java.util.Collections;
import java.util.List;

import application.Services;
import objects.Recipe;
import persistence.RecipePersistence;

public class AccessRecipes {
    private RecipePersistence recipePersistence;
    private List<Recipe> recipes;
    private Recipe recipe;
    private int currentRecipe;

    public AccessRecipes() {
        recipePersistence = Services.getRecipePersistence();
        recipes = null;
        recipe = null;
        currentRecipe = 0;
    }

    public AccessRecipes(final RecipePersistence recipePersistence) {
        this();
        this.recipePersistence = recipePersistence;
    }

    public List<Recipe> getRecipes()
    {
        recipes = recipePersistence.getRecipes();
        return Collections.unmodifiableList(recipes);
    }

    public Recipe getSequential()
    {
        if (recipes == null)
        {
            recipes = recipePersistence.getRecipes();
            currentRecipe = 0;
        }
        if (currentRecipe < recipes.size())
        {
            recipe = recipes.get(currentRecipe);
            currentRecipe++;
        }
        else
        {
            recipes = null;
            recipe = null;
            currentRecipe = 0;
        }
        return recipe;
    }

}
