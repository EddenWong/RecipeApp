
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
*/

public class GroceryList
{
    private GroceryNode groceryList;

    public GroceryList()
    {
        groceryList = null;
    }

    public void addIngredient(GroceryNode newIngredient)
    {
        //List empty
        if(groceryList == null)
        {
            groceryList = newIngredient;
        }
        else //List is not empty
        {
            GroceryNode current = groceryList;

            while (current.getNext() != null) {
                //Find end node (one without a next)
                current = current.getNext();
            }

            //Set next for end node

            current.setNext(newIngredient);

        }
    }

    public void removeIngredient(int toRemove)
    {
        //Make sure head is not null and index is at least 0
        if(groceryList != null && toRemove >= 0)
        {
            if(toRemove == 0)
            {
                groceryList = null;
            }
            else
            {
                int currentPosition = 0;
                GroceryNode currentNode = groceryList;
                GroceryNode previousNode = currentNode;
                for(int i = 0; i < toRemove; i++)
                {
                    if(currentNode != null)
                    {
                        previousNode = currentNode;
                        currentNode = currentNode.getNext();
                    }
                }

                //Make previous node skip over the current node to delete it
                if(currentNode != null)
                {
                    previousNode.setNext(currentNode.getNext());
                }
            }



        }
    }


}
