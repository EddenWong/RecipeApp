package presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.recipeapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import business.AccessRecipes;
import objects.Recipe;
import persistence.RecipePersistence;


public class RecipeListActivity extends AppCompatActivity {

    private AccessRecipes accessRecipes;
    private List<Recipe> recipeList;
    private ArrayAdapter<Recipe> recipeArrayAdapter;
    private int selectedRecipePosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        accessRecipes = new AccessRecipes(new RecipePersistenceStub());

        try
        {
            recipeList = new ArrayList<>();
            recipeList.addAll(accessRecipes.getRecipes());
            recipeArrayAdapter = new ArrayAdapter<Recipe>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, recipeList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(String.valueOf(recipeList.get(position).getName()));
                    text2.setText(recipeList.get(position).getCookingSkillLevel());

                    return view;
                }
            };

            final ListView listView = (ListView)findViewById(R.id.listRecipes);
            listView.setAdapter(recipeArrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Recipe item = (Recipe)adapterView.getItemAtPosition(position);

                    Intent recipeIntent = new Intent(RecipeListActivity.this,RecipeViewerActivity.class);
                    recipeIntent.putExtra("recipe",item);
                    startActivity(recipeIntent);

                }
            });

        }
        catch (final Exception e)
        {
            //Messages.fatalError(this, e.getMessage());
        }
    }
}


class RecipePersistenceStub implements RecipePersistence {
    private List<Recipe> recipes;

    public RecipePersistenceStub() {
        this.recipes =new ArrayList<>();
        ArrayList<String> ingredientList = new ArrayList<>();
        ingredientList.add("1 tps of sugar");
        ingredientList.add("1 tps of salt");
        ingredientList.add("1 tps of flour");
        ingredientList.add("1 tbps of butter");

        ArrayList<String> categoryList = new ArrayList<>();
        categoryList.add("Vegetarian");

        ingredientList.add("Dessert");

        recipes.add(new Recipe(4, "Easy cake", "US", ingredientList, 5, 10, "Easy", "Too dawhkdhwakjwahdwiakjdhwaiwandwcunydiudyn dhiwaudan dhdwiud hdi dkjhg egfh kjh v ghb nv dvbn whftc n fhkejh vkjdh kdh kjfc kjh kjahwd jofi jfvjqpoi hu u uo  KDH KJH KDH KAJH kj hkdjh kjwahd  jdh kjwdh .,  dhwhdn kj  d 79aw  2 dh2817y edh3 2976 4easy KAJH kj hkdjh kjwahd  jdh kjwdh .,  dhwhdn kj  d 79aw  2 dh2817y edh3 2976 4easy KAJH kj hkdjh kjwahd  jdh kjwdh .,  dhwhdn kj  d 79aw  2 dh2817y edh3 2976 4easy KAJH kj hkdjh kjwahd  jdh kjwdh .,  dhwhdn kj  d 79aw  2 dh2817y edh3 2976 4easy KAJH kj hkdjh kjwahd  jdh kjwdh .,  dhwhdn kj  d 79aw  2 dh2817y edh3 2976 4easy KAJH kj hkdjh kjwahd  jdh kjwdh .,  dhwhdn kj  d 79aw  2 dh2817y edh3 2976 4easy KAJH kj hkdjh kjwahd  jdh kjwdh .,  dhwhdn kj  d 79aw  2 dh2817y edh3 2976 4easyKAJH kj hkdjh kjwahd  jdh kjwdh .,  dhwhdn kj  d 79aw  2 dh2817y edh3 2976 4easyKAJH kj hkdjh kjwahd  jdh kjwdh .,  dhwhdn kj  d 79aw  2 dh2817y edh3 2976 4easyKAJH kj hkdjh kjwahd  jdh kjwdh .,  dhwhdn kj  d 79aw  2 dh2817y edh3 2976 4easyKAJH kj hkdjh kjwahd  jdh kjwdh .,  dhwhdn kj  d 79aw  2 dh2817y edh3 2976 4easyKAJH kj hkdjh kjwahd  jdh kjwdh .,  dhwhdn kj  d 79aw  2 dh2817y edh3 2976 4easyKAJH kj hkdjh kjwahd  jdh kjwdh .,  dhwhdn kj  d 79aw  2 dh2817y edh3 2976 4easyKAJH kj hkdjh kjwahd  jdh kjwdh .,  dhwhdn kj  d 79aw  2 dh2817y edh3 2976 4easyKAJH kj hkdjh kjwahd  jdh kjwdh .,  dhwhdn kj  d 79aw  2 dh2817y edh3 2976 4easy", "Mix everything together. Eat it.", "MEME", categoryList));
        recipes.add(new Recipe(5, "Difficult cake", "US", ingredientList, 5, 20, "Hard", "Too hard", "Mix everything together. Don't eat it.", "LMAO", categoryList));
        recipes.add(new Recipe(6, "Difficult cake", "US", ingredientList, 5, 20, "Hard", "Too hard", "Mix everything together. Don't eat it.", "LMAO", categoryList));
        recipes.add(new Recipe(7, "Difficult cake", "US", ingredientList, 5, 20, "Hard", "Too hard", "Mix everything together. Don't eat it.", "LMAO", categoryList));
        recipes.add(new Recipe(8, "Difficult cake", "US", ingredientList, 5, 20, "Hard", "Too hard", "Mix everything together. Don't eat it.", "LMAO", categoryList));
        recipes.add(new Recipe(9, "Difficult cake", "US", ingredientList, 5, 20, "Hard", "Too hard", "Mix everything together. Don't eat it.", "LMAO", categoryList));
        recipes.add(new Recipe(10, "Difficult cake", "US", ingredientList, 5, 20, "Hard", "Too hard", "Mix everything together. Don't eat it.", "LMAO", categoryList));
        recipes.add(new Recipe(11, "Difficult cake", "US", ingredientList, 5, 20, "Hard", "Too hard", "Mix everything together. Don't eat it.", "LMAO", categoryList));
        recipes.add(new Recipe(12, "Difficult cake", "US", ingredientList, 5, 20, "Hard", "Too hard", "Mix everything together. Don't eat it.", "LMAO", categoryList));
        recipes.add(new Recipe(13, "Difficult cake", "US", ingredientList, 5, 20, "Hard", "Too hard", "Mix everything together. Don't eat it.", "LMAO", categoryList));
        recipes.add(new Recipe(4, "Easy cake", "US", ingredientList, 5, 10, "Easy", "Too easy", "Mix everything together. Eat it.", "MEME", categoryList));
        recipes.add(new Recipe(5, "Difficult cake", "US", ingredientList, 5, 20, "Hard", "Too hard", "Mix everything together. Don't eat it.", "LMAO", categoryList));
        recipes.add(new Recipe(6, "Difficult cake", "US", ingredientList, 5, 20, "Hard", "Too hard", "Mix everything together. Don't eat it.", "LMAO", categoryList));
        recipes.add(new Recipe(7, "Difficult cake", "US", ingredientList, 5, 20, "Hard", "Too hard", "Mix everything together. Don't eat it.", "LMAO", categoryList));
        recipes.add(new Recipe(8, "Difficult cake", "US", ingredientList, 5, 20, "Hard", "Too hard", "Mix everything together. Don't eat it.", "LMAO", categoryList));
        recipes.add(new Recipe(9, "Difficult cake", "US", ingredientList, 5, 20, "Hard", "Too hard", "Mix everything together. Don't eat it.", "LMAO", categoryList));
        recipes.add(new Recipe(10, "Difficult cake", "US", ingredientList, 5, 20, "Hard", "Too hard", "Mix everything together. Don't eat it.", "LMAO", categoryList));
        recipes.add(new Recipe(11, "Difficult cake", "US", ingredientList, 5, 20, "Hard", "Too hard", "Mix everything together. Don't eat it.", "LMAO", categoryList));
        recipes.add(new Recipe(12, "Difficult cake", "US", ingredientList, 5, 20, "Hard", "Too hard", "Mix everything together. Don't eat it.", "LMAO", categoryList));
        recipes.add(new Recipe(13, "Difficult cake", "US", ingredientList, 5, 20, "Hard", "Too hard", "Mix everything together. Don't eat it.", "LMAO", categoryList));

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
