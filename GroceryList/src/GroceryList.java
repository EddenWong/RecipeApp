
/*
Comp 3350 Software Engineering
Group 8
February 28, 2022
GroceryList.java

Private member:
groceryList - Linked list of the ingredients.

Public method:
GroceryList() - Constructor to initialize the linked list.
addIngredient() - Add the given string ingredient to the list.
removeIngredient() - Remove the given index of the ingredient from the list.
getIngredient() - Get the ingredient from a given index
*/

import java.util.LinkedList;

public class GroceryList
{
    private final LinkedList<String> groceryList;

    public GroceryList()
    {
        groceryList = new LinkedList<>();
    }

    public void addIngredient(String newIngredient)
    {
        groceryList.add(newIngredient);
    }

    public void removeIngredient(int index)
    {
        if(groceryList.size() > index && index >= 0)
        {
            groceryList.remove(index);
        }
    }

    public String getIngredient(int index) {
        String returnIngredient = null;
        if (groceryList.size() > index && index >= 0) {
            returnIngredient = groceryList.get(index);
        }

        return returnIngredient;
    }
}
