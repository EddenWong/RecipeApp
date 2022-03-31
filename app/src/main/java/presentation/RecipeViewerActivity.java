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

import objects.Recipe;


public class RecipeViewerActivity extends AppCompatActivity {

    private Recipe myRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_viewer);

        if(getIntent().getExtras() != null) {
            myRecipe = (Recipe) getIntent().getSerializableExtra("recipe");
            getSupportActionBar().setTitle(myRecipe.getName());

            TextView skill = findViewById(R.id.recipeSkillLevel);
            TextView description = findViewById(R.id.recipeDescription);
            TextView ingredients = findViewById(R.id.recipeIngredients);
            TextView prepTime = findViewById(R.id.recipePrepTime);
            TextView cookTime = findViewById(R.id.recipeCookTime);
            TextView instructions = findViewById(R.id.recipeInstructions);
            TextView categories = findViewById(R.id.recipeCategories);

            skill.setText(myRecipe.getCookingSkillLevel());
            description.setText(myRecipe.getDescription() + "\n");
            ingredients.setText(formatIngredients(myRecipe.getIngredientList()));
            prepTime.setText("Preparation time: " + myRecipe.getPrepTime() + " minutes");
            cookTime.setText("Cooking time: " + myRecipe.getCookTime() + " minutes");
            instructions.setText("Instructions:\n" + myRecipe.getInstructions() + "\n");
            categories.setText(myRecipe.getCategoryList().toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recipe_menu, menu);
        return true;
    }

    private String formatIngredients(ArrayList ingredientList)
    {
        String listToReturn = "Ingredients:\n";

        for(int i = 0; i < ingredientList.size(); i++) {
            listToReturn += ingredientList.get(i).toString() + "\n";
        }

        return listToReturn;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.printItemButton:
                doPrint();
                break;
        }

        return true;
    }
    private WebView mWebView;

    private void doPrint() {
        // Create a WebView object specifically for printing
        WebView webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //Log.i(TAG, "page finished loading " + url);
                createWebPrintJob(view);
                mWebView = null;
            }
        });

        // Generate an HTML document on the fly:
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

        // Save the job object for later status checking
        //printJobs.add(printJob);
    }

    private String convertToHTML()
    {
        String HTMLConverted = "<html><body>";
        //Get Title
        HTMLConverted += "<h1>" + myRecipe.getName() + "</h1>";

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
        HTMLConverted += "<p>" + myRecipe.getInstructions() + "minutes</p>";

        //Get categories
        HTMLConverted += "<h2>Categories</h2>";
        HTMLConverted += "<p>" + myRecipe.getCategoryList() + "</p>";

        return HTMLConverted + "</body></html>";
    }
}


