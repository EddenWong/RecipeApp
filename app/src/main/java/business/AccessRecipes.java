package tests.business;

import java.util.List;

import objects.Recipe;
import persistence.RecipePersistence;

public class AccessRecipes {
    private RecipePersistence recipePersistence;
    private List<Recipe> recipes;
    private Recipe recipe;
    private int currentRecipe;

    public AccessRecipes() {
    }
}
