package objects;

public class Ingredient {
    private String ingredientID;
    private String ingredientName;
    private String quantity;
    private String unit;
    private String note;

    public Ingredient(String ingredientID, String ingredientName, String quantity, String unit, String note) {
        this.ingredientID = ingredientID;
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unit = unit;
        this.note = note;
    }

    public String getIngredientID() {
        return this.ingredientID;
    }

    public String getIngredientName() {
        return this.ingredientName;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public String getUnit() {
        return this.unit;
    }

    public String getNote() {
        return this.note;
    }
}
