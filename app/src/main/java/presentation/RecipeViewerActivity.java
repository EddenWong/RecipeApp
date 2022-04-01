package presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.recipeapp.R;

public class RecipeViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_viewer);
    }
}