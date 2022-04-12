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

    @Override
    public List<Ingredient> getIngredientList() {
        final List<Ingredient> ingredientList = new ArrayList<>();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM INGREDIENTS");
            while (rs.next()) {
                final Ingredient ingredient = new Ingredient(rs.getString("ingredientID"), rs.getString("name"), null, null, null);
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
    public ArrayList<Ingredient> getRecipeIngredients(String recipeID) {
        final ArrayList<Ingredient> ingredientList = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM RECIPEINGREDIENTS, INGREDIENTS WHERE INGREDIENTS.INGREDIENTID=RECIPEINGREDIENTS.INGREDIENTID AND RECIPEID = ?");
            st.setString(1, recipeID);
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final Ingredient ingredient = new Ingredient(rs.getString("ingredientID"), rs.getString("name"), rs.getString("quantity"), rs.getString("unit"), rs.getString("note"));
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
//        try (final Connection c = connection()) {
//            int recipeID = currentRecipe.getRecipeID();
//            PreparedStatement st = c.prepareStatement("INSERT INTO RECIPE VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
//            st.setInt(1, recipeID);
//            st.setString(2, currentRecipe.getName());
//            st.setString(3, currentRecipe.getNationality());
//            st.setInt(4, currentRecipe.getPrepTime());
//            st.setInt(5, currentRecipe.getCookTime());
//            st.setString(6, currentRecipe.getCookingSkillLevel());
//            st.setString(7, currentRecipe.getDescription());
//            st.setString(8, currentRecipe.getInstructions());
//            st.setString(9, currentRecipe.getLink());
//            st.executeUpdate();
//            st.close();
//
//            ArrayList<String> ingredientList = currentRecipe.getIngredientList();
//            ArrayList<String> categoryList = currentRecipe.getCategoryList();
//
//            insertSmallTables(c, recipeID, ingredientList, categoryList);
//
//            return currentRecipe;
//        } catch (final SQLException e) {
//            throw new PersistenceException(e);
//        }
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
