package persistence;

import java.util.ArrayList;
import java.util.List;

import objects.GroceryList;

public interface GroceryListPersistence {

    GroceryList getGroceryList();

    String insertItem(String currentItem);

    String updateItem(String currentItem);

    void deleteItem(String currentItem);
}
