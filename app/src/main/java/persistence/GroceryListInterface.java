package persistence;

import java.util.List;

import objects.GroceryList;

public interface GroceryListInterface {

    GroceryList getGroceryList();

    GroceryList getGroceryList(ArrayList<String> currentItemList);

    String insertItem(String currentItem);

    String updateItem(String currentItem);

    void deleteItem(String currentItem);
}
