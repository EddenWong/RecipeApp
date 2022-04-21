package presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.recipeapp.R;

import java.util.ArrayList;
import java.util.List;

import business.AccessRecipes;
import objects.Ingredient;
import objects.Recipe;

public class RecipeAddActivity extends AppCompatActivity implements ReturnIngredientInterface {

    private ArrayList<Ingredient> ingredientList;
    private ArrayAdapter<Ingredient> ingredientArrayAdapter;
    private ArrayAdapter<String> categoryArrayAdapter;
    private AccessRecipes accessRecipes;

    private EditText name;
    private EditText description;
    private EditText skillLevel;
    private EditText prepTime;
    private EditText cookTime;
    private Spinner categories;
    private EditText instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_add);

        name = findViewById(R.id.recipeName);
        description = findViewById(R.id.recipeDesc);
        skillLevel = findViewById(R.id.recipeSkill);
        prepTime = findViewById(R.id.recipePrep);
        cookTime = findViewById(R.id.recipeCook);
        categories = findViewById(R.id.categories);

        instructions = findViewById(R.id.recipeInstructions);
        accessRecipes = new AccessRecipes();
        ingredientList = new ArrayList<>();
        String[] myCategories = new String[]{"Appetizer","Dessert","Entree","Soup"};
        categoryArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, myCategories);
        categories.setAdapter(categoryArrayAdapter);

        //Make an arrayadapter wrapper
        ingredientArrayAdapter = new ArrayAdapter<Ingredient>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, ingredientList);
        final ListView listView = findViewById(R.id.ingredientList);
        listView.setAdapter(ingredientArrayAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                ingredientList.remove(pos);
                //Ingredient item = (Ingredient)adapterView.getItemAtPosition(position);
                ingredientArrayAdapter.notifyDataSetChanged();
                setIngredientListHeight();


                return true;
            }
        });

    }

    public void buttonAddIngredientOnClick(View v)
    {
        IngredientFragment newFragment = new IngredientFragment();
        newFragment.show(getSupportFragmentManager(), "game");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipe_add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onSelectedData(Ingredient ing) {
        //Check if ingredient is already in recipe
        boolean alreadyIn = false;

        for(int i = 0; i < ingredientList.size(); i++)
        {
            if(ing.getIngredientName().equals(ingredientList.get(i).getIngredientName()))
            {
                Toast.makeText(this, "Ingredient already in list", Toast.LENGTH_SHORT).show();
                alreadyIn = true;
            }
        }

        if(!alreadyIn) {
            //get array adapter, add new item to it, reset listview
            ingredientList.add(ing);
            //ingredientArrayAdapter = new ArrayAdapter<Ingredient>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, ingredientList);
            ingredientArrayAdapter.notifyDataSetChanged();
            setIngredientListHeight();
        }
    }

    public void addRecipeButtonClick(MenuItem item)
    {
        //If name and instructions are not empty
        if(!TextUtils.isEmpty(name.getText().toString()) && !TextUtils.isEmpty(instructions.getText().toString()))
        {
            String formattedInstructions = instructions.getText().toString();
            formattedInstructions = formattedInstructions.replace("\n","$");

            ArrayList<String> newCategories = new ArrayList<>();
            newCategories.add(categories.getSelectedItem().toString());

            //Check if numbers are within range
            if(checkInt(prepTime.getText().toString()) && checkInt(cookTime.getText().toString())) {

                Recipe newRecipe = new Recipe(null, name.getText().toString(), null, ingredientList, Integer.parseInt(prepTime.getText().toString()), Integer.parseInt(cookTime.getText().toString()), skillLevel.getText().toString(), description.getText().toString(), instructions.getText().toString(), null, newCategories);
                accessRecipes.insertRecipe(newRecipe);
                finish();
            }
        }
    }

    private void setIngredientListHeight()
    {
        final ListView listView = findViewById(R.id.ingredientList);
        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) listView.getLayoutParams();
        lp.height = 500 + 500 * ingredientList.size();
        listView.setLayoutParams(lp);

    }

    private boolean checkInt(String s)
    {
        boolean returnVal = true;
        try
        {
            Integer.parseInt(s);
        }
        catch (NumberFormatException e)
        {
            Toast.makeText(this, "Time too long", Toast.LENGTH_SHORT).show();
            returnVal = false;
        }

        return returnVal;
    }
}