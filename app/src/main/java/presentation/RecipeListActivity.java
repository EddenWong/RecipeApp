package presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipeapp.R;

import java.util.ArrayList;
import java.util.List;

import business.AccessRecipes;
import objects.Recipe;


public class RecipeListActivity extends AppCompatActivity {

    private AccessRecipes accessRecipes;
    private List<Recipe> recipeList;
    private List<String> recipeNames;
    private ArrayAdapter<Recipe> recipeArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        accessRecipes = new AccessRecipes();

        try
        {
            recipeList = new ArrayList<>();
            recipeList.addAll(accessRecipes.getRecipes());

            recipeNames = new ArrayList<>();
            for(int i = 0; i < recipeList.size(); i++)
            {
                recipeNames.add(recipeList.get(i).getName());
            }

            System.out.println(recipeNames);

            recipeArrayAdapter = new ArrayAdapter<Recipe>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, recipeList)
            {
              /* @Override
               public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(recipeList.get(position).getName());
                    text2.setText(recipeList.get(position).getCookingSkillLevel());

                    return view;
                }*/
            };

            final ListView listView = findViewById(R.id.listRecipes);
            listView.setAdapter(recipeArrayAdapter);

            listView.setOnItemClickListener((adapterView, view, position, l) -> {
                Recipe item = (Recipe)adapterView.getItemAtPosition(position);

                Intent recipeIntent = new Intent(RecipeListActivity.this,RecipeViewerActivity.class);
                recipeIntent.putExtra("recipe",item);
                startActivity(recipeIntent);

            });

        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate menu with items using MenuInflator
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipe_list_menu, menu);

        // Initialise menu item search bar
        // with id and take its object
        MenuItem searchViewItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);

        // attach setOnQueryTextListener
        // to search view defined above
        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {

                    @Override
                    public boolean onQueryTextSubmit(String query)
                    {
                        //If the list contains a recipe name containing the query, then filter it
                        if (recipeNames.contains(query)) {
                            recipeArrayAdapter.getFilter().filter(query);
                        }
                        else {
                            // Little pop up when nothing is found
                            Toast.makeText(RecipeListActivity.this,"Recipe Not found",Toast.LENGTH_LONG).show();
                        }
                        return false;
                    }

                    //Filters while the user is typing, not just when it's entered
                    @Override
                    public boolean onQueryTextChange(String newText)
                    {
                        recipeArrayAdapter.getFilter().filter(newText);
                        return false;
                    }
                });

        return super.onCreateOptionsMenu(menu);
    }

}