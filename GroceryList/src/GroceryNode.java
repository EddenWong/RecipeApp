
/*
Comp 3350 Software Engineering
Group 8
February 28, 2022
GroceryNode.java

Private member:
next - Next linked ingredient.
ingredient - Ingredient this node represents

Public method:
GroceryNode() - Constructor to initialize the node.
getNext() - Returns a pointer to the next linked node.
setNext() - Sets the next linked node.
getIngredient() - Returns the ingredient this node represents
setIngredient() - Sets the ingredient for this node
*/

public class GroceryNode
{
    private String ingredient;
    private GroceryNode next;

    public GroceryNode()
    {
        ingredient = "New Ingredient";
        next = null;
    }

    public GroceryNode getNext()
    {
        return next;
    }

    public void setNext(GroceryNode newNext)
    {
        next = newNext;
    }

    public String getIngredient()
    {
        return ingredient;
    }

    public void setIngredient(String newIngredient)
    {
        ingredient = newIngredient;
    }
}
