import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class GroceryListTest {

    @org.junit.jupiter.api.Test
    void addIngredientTest()
    {
        //New grocery list is empty
        GroceryList addTest = new GroceryList();

        String newIngredient0 = "Flour";
        String newIngredient1 = "Sugar";
        String newIngredient2 = "Salt";

        //Assert that the added ingredient is correctly added
        addTest.addIngredient(newIngredient0);
        assert(addTest.getIngredient(0).equals(newIngredient0));
        assert (addTest.numIngredients() == 1);

        //Assert that the new ingredient to the next index
        addTest.addIngredient(newIngredient1);
        assert(addTest.getIngredient(0).equals(newIngredient0));
        assert(addTest.getIngredient(1).equals(newIngredient1));
        assert (addTest.numIngredients() == 2);

        //Assert that the new ingredient to the next index
        addTest.addIngredient(newIngredient2);
        assert(addTest.getIngredient(0).equals(newIngredient0));
        assert(addTest.getIngredient(1).equals(newIngredient1));
        assert(addTest.getIngredient(2).equals(newIngredient2));
        assert (addTest.numIngredients() == 3);

        //Assert that null strings rejected
        addTest.addIngredient(null);
        assert(addTest.numIngredients() == 3);
    }

    @org.junit.jupiter.api.Test()
    void removeIngredient()
    {
        //New grocery list is empty
        GroceryList removeTest = new GroceryList();

        //Add three ingredients
        String newIngredient0 = "Flour";
        String newIngredient1 = "Sugar";
        String newIngredient2 = "Salt";

        removeTest.addIngredient(newIngredient0);
        removeTest.addIngredient(newIngredient1);
        removeTest.addIngredient(newIngredient2);

        //Assert that the ingredient in index 1 was removed
        removeTest.removeIngredient(1);
        assert(removeTest.getIngredient(0).equals(newIngredient0));
        assert(removeTest.getIngredient(1).equals(newIngredient2));
        assert(removeTest.numIngredients() == 2);

        //Assert that the ingredient in index 0 was removed
        removeTest.removeIngredient(0);
        assert(removeTest.getIngredient(0).equals(newIngredient2));
        assert(removeTest.numIngredients() == 1);

        //Assert that all ingredients are removed
        removeTest.removeIngredient(0);
        assert(removeTest.numIngredients() == 0);

        //Assert that an IndexOutOfBoundsException is thrown when an improper index is given
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            removeTest.removeIngredient(-1);//Exception
        });
    }

    @org.junit.jupiter.api.Test()
    void getIngredient()
    {
        //New grocery list is empty
        GroceryList getTest = new GroceryList();

        //Assert that getting from the empty list throws an exception
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            getTest.getIngredient(-1);//Exception
        });

        //Add three ingredients
        String newIngredient0 = "Flour";
        String newIngredient1 = "Sugar";
        String newIngredient2 = "Salt";

        getTest.addIngredient(newIngredient0);
        getTest.addIngredient(newIngredient1);
        getTest.addIngredient(newIngredient2);

        //Assert that getting each one in order works
        assert(getTest.getIngredient(0).equals(newIngredient0));
        assert(getTest.getIngredient(1).equals(newIngredient1));
        assert(getTest.getIngredient(2).equals(newIngredient2));

        //Assert that an IndexOutOfBoundsException is thrown when an improper index is given
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            getTest.removeIngredient(-1);//Exception
        });
    }

}