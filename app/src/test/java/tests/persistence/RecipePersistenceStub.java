package tests.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import objects.Recipe;
import persistence.RecipePersistence;

public class RecipePersistenceStub implements RecipePersistence {
    private List<Recipe> recipes;

    public RecipePersistenceStub() {
        this.recipes =new ArrayList<>();
        ArrayList<String> ingredientList = new ArrayList<String>();
        ingredientList.add("1 tps of sugar");
        ingredientList.add("1 tps of salt");
        ingredientList.add("1 tps of flour");
        ingredientList.add("1 tbps of butter");

        ArrayList<String> categoryList = new ArrayList<String>();
        ingredientList.add("Dessert");

        recipes.add(new Recipe(4, "Easy cake", "US", ingredientList, 5, 10, "Easy", "Too easy", "Mix everything together. Eat it.", "MEME", categoryList));
        recipes.add(new Recipe(5, "Difficult cake", "US", ingredientList, 5, 20, "Hard", "Too hard", "Mix everything together. Don't eat it.", "LMAO", categoryList));
    }

    @Override
    public List<Recipe> getRecipes() {
        return Collections.unmodifiableList(recipes);
    }

    @Override
    public List<Recipe> getRecipe(Recipe currentRecipe) {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(currentRecipe);
        return Collections.unmodifiableList(recipes);
    }

    public Recipe insertRecipe(Recipe currentRecipe) {
        recipes.add(currentRecipe);
        return currentRecipe;
    }

    public Recipe updateRecipe(Recipe currentRecipe) {
        int index;

        index = recipes.indexOf(currentRecipe);
        if (index >= 0)
        {
            recipes.set(index, currentRecipe);
        }
        return currentRecipe;
    }

    public void deleteRecipe(Recipe currentRecipe) {
        int index;

        index = recipes.indexOf(currentRecipe);
        if (index >= 0)
        {
            recipes.remove(index);
        }
    }
}
