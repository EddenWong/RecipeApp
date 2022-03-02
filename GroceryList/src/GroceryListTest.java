import static org.junit.jupiter.api.Assertions.*;

class GroceryListTest {

    @org.junit.jupiter.api.Test
    void addIngredientTest()
    {
        //Assert that a new grocery list is empty
        GroceryList addTest = new GroceryList();
        assert(addTest.getIngredient(0) == null);

        String newIngredient0 = "Flour";
        String newIngredient1 = "Sugar";
        String newIngredient2 = "Salt";

        //Assert that the added ingredient is correctly added
        addTest.addIngredient(newIngredient0);
        assert(addTest.getIngredient(0).equals(newIngredient0));

        //Assert that the new ingredient to the next index
        addTest.addIngredient(newIngredient1);
        assert(addTest.getIngredient(0).equals(newIngredient0));
        assert(addTest.getIngredient(1).equals(newIngredient1));

        //Assert that the new ingredient to the next index
        addTest.addIngredient(newIngredient2);
        assert(addTest.getIngredient(0).equals(newIngredient0));
        assert(addTest.getIngredient(1).equals(newIngredient1));
        assert(addTest.getIngredient(2).equals(newIngredient2));

        //Assert that null strings accepted
        addTest.addIngredient(null);
        assert(addTest.getIngredient(3) == null);

    }

    @org.junit.jupiter.api.Test
    void removeIngredient()
    {
        //Assert that a new grocery list is empty
        GroceryList removeTest = new GroceryList();
        assert(removeTest.getIngredient(0) == null);

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
        assert(removeTest.getIngredient(2) == (null));

        //Assert that the ingredient in index 0 was removed
        removeTest.removeIngredient(0);
        assert(removeTest.getIngredient(0).equals(newIngredient2));
        assert(removeTest.getIngredient(1) == null);
        assert(removeTest.getIngredient(2) == null);

        //Assert that all ingredients are removed
        removeTest.removeIngredient(0);
        assert(removeTest.getIngredient(0) == null);
        assert(removeTest.getIngredient(1) == null);
        assert(removeTest.getIngredient(2) == null);
    }
}