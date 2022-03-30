package presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.recipeapp.R;

import objects.Recipe;


public class RecipeViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_viewer);

        if(getIntent().getExtras() != null) {
            Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");

            TextView title = findViewById(R.id.recipeName);
            TextView description = findViewById(R.id.recipeDescription);
            TextView ingredients = findViewById(R.id.recipeIngredients);
            TextView instructions = findViewById(R.id.recipeInstructions);

            title.setText(recipe.getName());
            description.setText(recipe.getDescription());
            ingredients.setText("Ingredients:\n" + recipe.getIngredientList().toString());
            instructions.setText("Instructions:\n" + recipe.getInstructions());
        }
    }


}


