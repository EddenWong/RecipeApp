package presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.recipeapp.R;

import java.util.ArrayList;

import objects.Recipe;


public class RecipeViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_viewer);

        if(getIntent().getExtras() != null) {
            Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");

            TextView name = findViewById(R.id.recipeName);
            TextView skill = findViewById(R.id.recipeSkillLevel);
            TextView description = findViewById(R.id.recipeDescription);
            TextView ingredients = findViewById(R.id.recipeIngredients);
            TextView prepTime = findViewById(R.id.recipePrepTime);
            TextView cookTime = findViewById(R.id.recipeCookTime);
            TextView instructions = findViewById(R.id.recipeInstructions);
            TextView categories = findViewById(R.id.recipeCategories);

            name.setText(recipe.getName());
            skill.setText(recipe.getCookingSkillLevel());
            description.setText(recipe.getDescription() + "\n");
            ingredients.setText(formatIngredients(recipe.getIngredientList()));
            prepTime.setText("Preparation time: " + recipe.getPrepTime() + " minutes");
            cookTime.setText("Cooking time: " + recipe.getCookTime() + " minutes");
            instructions.setText("Instructions:\n" + recipe.getInstructions() + "\n");
            categories.setText(recipe.getCategoryList().toString());
        }
    }

    private String formatIngredients(ArrayList ingredientList)
    {
        String listToReturn = "Ingredients:\n";

        for(int i = 0; i < ingredientList.size(); i++) {
            listToReturn += ingredientList.get(i).toString() + "\n";
        }

        return listToReturn;
    }

}


