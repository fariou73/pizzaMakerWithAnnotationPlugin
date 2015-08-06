package simbirsoft;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Ingredients {

    private final List<Ingredient> ingredientsList;

    public Ingredients() {
        ingredientsList = new ArrayList<>();
    }

    public List<Ingredient> getIngredients() {
        return ingredientsList;
    }

    public void saveFromFile(String pathFromIngredientFile) {
        try (BufferedWriter myfile = new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream(pathFromIngredientFile), "UTF-8"));) {
            for (Ingredient myIngr : ingredientsList) {
                myfile.write(myIngr.getIngredientsName() + '@' + myIngr.getIngredientCount() + System.lineSeparator());
            }
        } catch (Exception e) {
            System.err.print(e.toString());
        }
    }

    public void loadFromFile(String pathFromIngredientFile) {
        String[] lines;
        String ingredientName;
        Integer ingredientCount;
        try (BufferedReader reader = new BufferedReader
                (new InputStreamReader(new FileInputStream(pathFromIngredientFile), "UTF-8"));) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines = line.split("\\@");
                ingredientName = lines[0];
                ingredientCount = Integer.parseInt(lines[1]);
                ingredientsList.add(new Ingredient(ingredientName, ingredientCount));
            }
        } catch (Exception e) {
            System.err.print(e.toString());
        }
    }

    public void add(Ingredient ingredient) {
        ingredientsList.add(ingredient);
    }

    public void clear() {
        ingredientsList.clear();
    }

    public void printAll() {
        int j = 0;
        for (Ingredient myIngredient : ingredientsList) {
            j++;
            System.out.println(j + ") " + myIngredient);
        }
    }
}
