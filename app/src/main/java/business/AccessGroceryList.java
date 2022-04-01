package business;

import java.util.List;

import application.Services;
import objects.GroceryList;
import persistence.GroceryListPersistence;
import persistence.RecipePersistence;

public class AccessGroceryList {
    private GroceryListPersistence groceryListPersistence;
    private List<String> groceryList;
    private String item;
    private int currentItem;

    public AccessGroceryList() {
        groceryListPersistence = Services.getGroceryListPersistence();
        groceryList = null;
        item = null;
        currentItem = 0;
    }

    public AccessGroceryList(final GroceryListPersistence groceryListPersistence) {
        this();
        this.groceryListPersistence = groceryListPersistence;
    }


}
