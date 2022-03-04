import java.io.*;
import java.util.ArrayList;


public class Database {

//    private String csvFileName;

    // column number in recipe.csv file
    private static final int RECIPE_NAME = 0;
    private static final int RECIPE_NATIONALITY = 1;
    private static final int RECIPE_PREPTIME = 2;
    private static final int RECIPE_COOKTIME = 3;
    private static final int RECIPE_COOKINGSKILL = 4;
    private static final int RECIPE_DESCRIPTION = 5;
    private static final int RECIPE_LINK = 6;
    private static final int RECIPE_VEGETARIAN = 7;

    //Column number in Instructions.csv
    static final int INSTRUCTION = 1;

    //Column number in Ingredient.csv
    static final int INGREDIENT = 1;

    //Column number in Filters.csv
    static final int FILTER = 1;

//    // list of all recipes
//    private ArrayList<Recipe> recipeList;


    //our constructor
    public Database() {

        //csvFileName = "";  //initialize with expected file name

    }

    //we return arraylist of type Recipe object
    private ArrayList<Recipe> getAllRecipesFromCSV(){

        ArrayList<Recipe> RecipeAll = new ArrayList<>();

        try{
            File f1 = new File("./Database/Recipe.csv");
            InputStream recipeInfo = new FileInputStream(f1);
            BufferedReader readRecipe = new BufferedReader(new InputStreamReader(recipeInfo));

            File f2 = new File("./Database/Instructions.csv");
            InputStream instrucInfo = new FileInputStream(f2);
            BufferedReader readInstruction = new BufferedReader(new InputStreamReader(instrucInfo));
            String instructLine = readInstruction.readLine(); // Skip the first line, the header

            // Read Recipe data line by line
            String RecipeLine = readRecipe.readLine(); // Skip the first line, the header
            while ((RecipeLine = readRecipe.readLine()) != null) {

                String[] recipeData = RecipeLine.split(",");

                String name = recipeData[RECIPE_NAME];
                String nationality = recipeData[RECIPE_NATIONALITY];
                int prepTime = Integer.parseInt(recipeData[RECIPE_PREPTIME]);
                int cookTime = Integer.parseInt(recipeData[RECIPE_COOKTIME]);
                String cookingSkill = recipeData[RECIPE_COOKINGSKILL];
                String description = recipeData[RECIPE_DESCRIPTION];
                String link = recipeData[RECIPE_LINK];
                boolean vegetarian = Boolean.parseBoolean(recipeData[RECIPE_VEGETARIAN]);
                ArrayList<String> filters = new ArrayList<>();
                String instructions = ""; // string variable to store complete instructions for a recipe
                ArrayList<String> ingredients = new ArrayList<>(); // string variable to store a list of ingredients for
                // a recipe

                if ((instructLine = readInstruction.readLine()) != null) {
                    String[] instructionData = instructLine.split(",");
                    if (instructionData[RECIPE_NAME].equals(name)) {
                        instructions = instructionData[INSTRUCTION];
                    }
                }

                // Read ingredients
                File f3 = new File("./Database/Ingredients.csv");
                InputStream ingredientInfo = new FileInputStream(f3);
                BufferedReader readIngredient = new BufferedReader(new InputStreamReader(ingredientInfo));
                String ingredLine = readIngredient.readLine(); // Skip first header line
                while ((ingredLine = readIngredient.readLine()) != null) {

                    String[] ingredData = ingredLine.split(",");
                    // If the name matches, add to the Recipe object
                    if (ingredData[RECIPE_NAME].equals(name)) {
                        ingredients.add(ingredData[INGREDIENT]);
                    }
                }
                readIngredient.close();
                ingredientInfo.close();

                // Read filters
                File f4 = new File("./Database/Filters.csv");
                InputStream filterInfo = new FileInputStream(f4);
                BufferedReader readFilters = new BufferedReader(new InputStreamReader(filterInfo));

                String filterLine = readFilters.readLine(); // Skip the first line, the header
                while ((filterLine = readFilters.readLine()) != null) {

                    String[] filterData = filterLine.split(",");
                    // If the name matches, add to the Recipe object
                    if (filterData[RECIPE_NAME].equals(name)) {
                        filters.add(filterData[FILTER]);
                    }
                }
                readFilters.close();
                filterInfo.close();

                // creating the final recipe object to go into the recipe arraylist
                Recipe recipe = new Recipe(name, nationality, ingredients, prepTime, cookTime, cookingSkill,
                        description, instructions, link, vegetarian, filters);
//                System.out.println(name + "/" + nationality + "/" + ingredients + "/" + prepTime + "/" + cookTime + "/"
//                        + cookingSkill + "/" + description + "/" + instructions + "/" + link + "/" + vegetarian + "/"
//                        + filters + "\n");

                // add the object to our final recipe list
                RecipeAll.add(recipe);
            }
            readInstruction.close();
            instrucInfo.close();

            recipeInfo.close();
            readRecipe.close();

        }catch(Exception e){
            //handling exceptions
            e.printStackTrace();
        }
        return RecipeAll;
    }
}