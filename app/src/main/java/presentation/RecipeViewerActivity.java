package presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.recipeapp.R;

import java.util.ArrayList;
import java.util.Objects;

import business.AccessRecipes;
import objects.Ingredient;
import objects.Recipe;


public class RecipeViewerActivity extends AppCompatActivity {

    private Recipe myRecipe;
    private WebView mWebView;
    private AccessRecipes accessRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_viewer);

        accessRecipes = new AccessRecipes();

        //Get information on recipe
        if(getIntent().getExtras() != null) {
            myRecipe = (Recipe) getIntent().getSerializableExtra("recipe");
            Objects.requireNonNull(getSupportActionBar()).setTitle(myRecipe.getName());

            TextView skill = findViewById(R.id.recipeSkillLevel);
            TextView description = findViewById(R.id.recipeDescription);
            TextView ingredients = findViewById(R.id.recipeIngredients);
            TextView prepTime = findViewById(R.id.recipePrepTime);
            TextView cookTime = findViewById(R.id.recipeCookTime);
            TextView instructions = findViewById(R.id.recipeInstructions);
            TextView categories = findViewById(R.id.recipeCategories);

            skill.setText(myRecipe.getCookingSkillLevel());
            description.setText(myRecipe.getDescription());
            ingredients.setText(formatIngredients(myRecipe.getIngredientList()));
            prepTime.setText(myRecipe.getPrepTime() + " minutes");
            cookTime.setText(myRecipe.getCookTime() + " minutes");
            instructions.setText(formatInstructions(myRecipe.getInstructions()));
            categories.setText(myRecipe.getCategoryList().toString());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recipe_pop_up_menu, menu);
        return true;
    }

    private String formatIngredients(ArrayList<Ingredient> ingredientList)
    {
        String formattedIngredients = "No ingredients found";

        if(ingredientList != null) {
            formattedIngredients = "";
            for (int i = 0; i < ingredientList.size(); i++) {
                formattedIngredients += ingredientList.get(i).getQuantity() + " ";
                formattedIngredients += ingredientList.get(i).getUnit() + "\t\t";
                formattedIngredients += ingredientList.get(i).getIngredientName();
                if (ingredientList.get(i).getNote() != null) {
                    formattedIngredients += " (" + ingredientList.get(i).getNote() + ")";
                }

                if (i < ingredientList.size() - 1) {
                    formattedIngredients += "\n";
                }
            }
        }

        return formattedIngredients;
    }

    private String formatInstructions(String instructions)
    {
        String formattedInstructions = "No instructions found";

        if(instructions != null)
        {
            formattedInstructions = instructions.replace("$","\n\n");
        }

        return formattedInstructions;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.recipePrint:
                doPrint();
                break;
            case R.id.recipeBookmark:
                bookmark();
                break;
        }

        return true;
    }

    private void doPrint() {
        // Create a WebView object specifically for printing
        WebView webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                createWebPrintJob(view);
                mWebView = null;
            }
        });

        // Convert the recipe into a formatted html string:
        String htmlDocument = convertToHTML();
        webView.loadDataWithBaseURL(null, htmlDocument, "text/HTML", "UTF-8", null);

        // Keep a reference to WebView object until you pass the PrintDocumentAdapter
        // to the PrintManager
        mWebView = webView;

    }

    private void createWebPrintJob(WebView webView) {

        // Get a PrintManager instance
        PrintManager printManager = (PrintManager) RecipeViewerActivity.this
                .getSystemService(Context.PRINT_SERVICE);

        String jobName = myRecipe.getName() + " Document";

        // Get a print adapter instance
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter(jobName);

        // Create a print job with name and adapter instance
        PrintJob printJob = printManager.print(jobName, printAdapter,
                new PrintAttributes.Builder().build());

    }

    private String convertToHTML()
    {
        String HTMLConverted = "<html><body>";
        //Get Title
        HTMLConverted += "<h1>" + myRecipe.getName() + "</h1>";

        //Get skill level
        HTMLConverted += "<h2>Skill Level</h2>";
        HTMLConverted += "<p>" + myRecipe.getCookingSkillLevel() + "</p>";

        //Get description
        HTMLConverted += "<h2>Description</h2>";
        HTMLConverted += "<p>" + myRecipe.getDescription() + "</p>";

        //Get ingredients
        HTMLConverted += "<h2>Ingredients</h2>";
        HTMLConverted += "<p>" + formatIngredients(myRecipe.getIngredientList()).replace("\n","</br>") + "</p>";

        //Get prep time
        HTMLConverted += "<h3>Prep Time</h3>";
        HTMLConverted += "<p>" + myRecipe.getPrepTime() + "</p>";

        //Get cook time
        HTMLConverted += "<h3>Cook Time</h3>";
        HTMLConverted += "<p>" + myRecipe.getCookTime() + "minutes</p>";

        //Get instructions
        HTMLConverted += "<h2>Instructions</h2>";
        HTMLConverted += "<p>" + formatInstructions(myRecipe.getInstructions()) + "minutes</p>";

        //Get categories
        HTMLConverted += "<h2>Categories</h2>";
        HTMLConverted += "<p>" + myRecipe.getCategoryList() + "</p>";

        return HTMLConverted + "</body></html>";
    }

    private void bookmark()
    {
        //If it's not bookmarked, do it
        //If it is bookmarked, undo it
        //Construct new recipe and update the old one
        Recipe newRecipe;
        String oldId = myRecipe.getRecipeID();
        String oldName = myRecipe.getName();
        String oldNationality = myRecipe.getNationality();
        ArrayList<Ingredient> oldIngredients = myRecipe.getIngredientList();
        int oldPrep = myRecipe.getPrepTime();
        int oldCook = myRecipe.getCookTime();
        String oldSkill = myRecipe.getCookingSkillLevel();
        String oldDesc = myRecipe.getDescription();
        String oldInstruc = myRecipe.getInstructions();
        String oldLink = myRecipe.getLink();
        ArrayList<String> oldCategories = myRecipe.getCategoryList();

        if(oldCategories == null)
        {
            oldCategories = new ArrayList<>();
        }

        if(!myRecipe.getCategoryList().contains("Bookmarked")) {
            //Add bookmarked as category
            oldCategories.add("Bookmarked");
            newRecipe = new Recipe(oldId, oldName, oldNationality, oldIngredients, oldPrep, oldCook, oldSkill, oldDesc, oldInstruc, oldLink, oldCategories);
        }
        else
        {
            oldCategories.remove("Bookmarked");
            newRecipe = new Recipe(oldId, oldName, oldNationality, oldIngredients, oldPrep, oldCook, oldSkill, oldDesc, oldInstruc, oldLink, oldCategories);

        }
        //Update old recipe
        accessRecipes.updateRecipe(newRecipe);

        //Replace old category
        TextView categories = findViewById(R.id.recipeCategories);
        categories.setText(myRecipe.getCategoryList().toString());

    }
}


