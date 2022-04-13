package persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import objects.Ingredient;
import objects.Recipe;
import persistence.IngredientPersistence;

public class IngredientPersistenceHSQLDB implements IngredientPersistence {
    private final String dbPath;

    public IngredientPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

//    private Ingredient createIngredient(ResultSet rs, ArrayList<String> ingredientsList, ArrayList<String> categoryList) throws SQLException {
//        final int recipeID = rs.getInt("recipeID");
//        final String recipeName = rs.getString("name");
//        final String recipeNationality = rs.getString("nationality");
//        final int prepTime = rs.getInt("preptime");
//        final int cookTime = rs.getInt("cooktime");
//        final String cookingSkillLevel = rs.getString("cookingskilllevel");
//        final String description = rs.getString("description");
//        final String instruction = rs.getString("instruction");
//        final String link = rs.getString("link");
//
//        return new Recipe(recipeID, recipeName, recipeNationality, ingredientsList, prepTime, cookTime, cookingSkillLevel, description, instruction, link, categoryList);
//    }

//    @Override
//    public List<Ingredient> getIngredientList() {
//        final List<Ingredient> ingredientList = new ArrayList<>();
//        try (final Connection c = connection()) {
//            final Statement st = c.createStatement();
//            final ResultSet rs = st.executeQuery("SELECT * FROM INGREDIENTS");
//            while (rs.next()) {
//                final Ingredient ingredient = new Ingredient(rs.getString("name"), null, null, null);
//                ingredientList.add(ingredient);
//            }
//            rs.close();
//            st.close();
//
//            return ingredientList;
//        } catch (final SQLException e) {
//            throw new PersistenceException(e);
//        }
//    }

    @Override
    public ArrayList<Ingredient> getRecipeIngredients(String recipeID) {
        final ArrayList<Ingredient> ingredientList = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM RECIPEINGREDIENTS WHERE RECIPEID = ?");
            st.setString(1, recipeID);
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final Ingredient ingredient = new Ingredient(rs.getString("ingredientName"), rs.getString("quantity"), rs.getString("unit"), rs.getString("note"));
                ingredientList.add(ingredient);
            }
            rs.close();
            st.close();

            return ingredientList;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Ingredient getIngredient() {
        return null;
    }

    @Override
    public Ingredient insertIngredient(Ingredient currentIngredient) {
        /* Insert ingredient into Ingredient table so that when we add a new ingredient that is not in the table using either the (future) insert button in grocery list
        or by modifying the recipe, or by adding a new recipe. Need to check if the ingredient already exist in the table.
        */
        return null;
    }

    @Override
    public Ingredient updateIngredient() {
        return null;
    }

    @Override
    public void deleteIngredient() {

    }
}
