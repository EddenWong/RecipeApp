package tests.objects;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import java.util.ArrayList;

import objects.GroceryList;

public class GroceryListTest{

   @Test
   public void groceryTest1{

    GroceryList Grocery;

    System.out.println("\nStarting Grocery\n");

    ArrayList<String> groceryItems = new ArrayList<String>();
    groceryItems.add("sugar");
    groceryItems.add("salt");
    groceryItems.add("oil");

    assertNull(Grocery);
    Grocery = new GroceryList(groceryItems);
    //check if the object is not null after creation
    assertNotNull(Grocery);
    assertTrue(this.groceryItems.equals(Grocery.getGroceryList));
    
    ArrayList<String> groceryItems2 = new ArrayList<String>();
    groceryItems.add("pepper");
    groceryItems.add("spice");

    assertFalse(this.groceryItems2.equals(Grocery.getGroceryList));
    assertFalse(this.groceryItems2.equals(groceryItems));

    groceryItems2.removeAll(groceryItems2);
    groceryItems2.add("sugar");
    groceryItems2.add("salt");
    groceryItems2.add("oil");
    assertTrue(this.groceryItems2.equals(Grocery.getGroceryList));

    System.out.println("\nEnding Grocery Test\n");

   }

}
