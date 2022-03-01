import java.lang.reflect.Array;
import java.util.ArrayList;

/*
Comp 3350 Software Engineering
Group 8
February 28, 2022
Recipe.java

Public static member:
recipeList - Linked list of recipes.

Public static method:
getRecipeFromDatabase() - Get the recipes from the database.

Private member:
name - String name of the recipe.
ingredientList - Linked list of ingredients string.
nationality - The country name where the food originated.
prepTime - Integer the amount of time it takes to prep.
cookTime - Integer the amount of time it takes to cook.
cookingSkillLevel - Easy, Medium, or Hard cooking level.
description - String description of the recipe.
instructionList - Cooking instructions.
link - Youtube link.
vegetarian - String Yes or No if the food is vegetarian.
filterList - Linked list of string filter. Holiday like Christmas, New Year, to Seafood.

Public method:
Recipe() - Constructor to create an instance of recipe.
getter methods...

Future:
Image of the recipe.
*/

public class Recipe
{
    // Private member
    private String name;
    private String nationality;
    private ArrayList<String> ingredientList;
    private int prepTime;
    private int cookTime;
    private String cookingSkillLevel;
    private String description;
    private ArrayList<String> instructionList;
    private String link;
    private boolean vegetarian;
    private ArrayList<String> filterList;

    // Public method

    /* Recipe()
    Constructor to create an instance of recipe.
    */
    public Recipe(String name, String nationality, ArrayList<String> ingredientList,
                  int prepTime, int cookTime, String cookingSkillLevel, String description,
                  ArrayList<String> instructionList, String link, boolean vegetarian, ArrayList<String> filterList)
    {
        this.name = name;
        this.nationality = nationality;
        this.ingredientList = ingredientList;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.cookingSkillLevel = cookingSkillLevel;
        this.description = description;
        this.instructionList = instructionList;
        this.link = link;
        this.vegetarian = vegetarian;
        this.filterList = filterList;
    }

}
