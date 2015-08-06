package simbirsoft;

import java.io.File;
import java.util.Scanner;

public class Main {
    private static final PizzaQueue PIZZA_QUEUE = new PizzaQueue();
    private static volatile Ingredients REAL_PIZZA_INGREDIENTS = new Ingredients();
    private static final Ingredients CUSTOMER_PIZZA_INGREDIENTS = new Ingredients();
    private static final String pathFromFile = "ingriki.txt";
    private static final PizzaSize clientPizzaSize = new PizzaSize();

    public static void main(String[] args) {
        if ((new File(pathFromFile)).exists()) {
            REAL_PIZZA_INGREDIENTS.loadFromFile(pathFromFile);
            PluginJarLoader pluginJarLoader = new PluginJarLoader(REAL_PIZZA_INGREDIENTS);
            while (true) {
                startWorkWithPizza();
            }
        } else {
            System.out.println("File With ingredients not found.");
        }
    }

    private static void addIngredient(Scanner scanner) {
        int ingredientCount;
        int ingredientId = -1;
        ingredientId = scanner.nextInt() - 1;
        if (ingredientId < REAL_PIZZA_INGREDIENTS.getIngredients().size() && ingredientId >= 0) {
            System.out.println("Enter the number of portion:");
            try {
                ingredientCount = new Scanner(System.in).nextInt();
                Ingredient _ingr = REAL_PIZZA_INGREDIENTS.getIngredients().get(ingredientId);
                if ((_ingr.getIngredientCount() - ingredientCount) >= 0 && ingredientCount != 0) {
                    CUSTOMER_PIZZA_INGREDIENTS.add(new Ingredient(_ingr.getIngredientsName(), ingredientCount));
                    REAL_PIZZA_INGREDIENTS.getIngredients().get(ingredientId).setIngredientCount(_ingr.getIngredientCount() - ingredientCount);
                    REAL_PIZZA_INGREDIENTS.saveFromFile(pathFromFile);
                } else {
                    System.out.println("Incorrect number");
                }
            } catch (Exception e) {
                System.err.println("You have written an incorrect number.");
            }
        }
    }

    private static void startWorkWithPizza() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("press 1 to show all ingredients");
        System.out.println("press 2 to print all sizes of pizza");
        System.out.println("press 3 to print all added ingredietns");
        System.out.println("press 4 to print current size of pizza");
        System.out.println("press 5 to enter sizes of pizza");
        System.out.println("press 6 to add ingredient in pizza");
        System.out.println("press 7 to add pizza in queue");
        System.out.println("press 8 to exit");
        switch (scanner.nextInt()) {
            case 1:
                REAL_PIZZA_INGREDIENTS.printAll();
                break;
            case 2:
                clientPizzaSize.printALL();
                break;
            case 3:
                CUSTOMER_PIZZA_INGREDIENTS.printAll();
                break;
            case 4:
                System.out.println(clientPizzaSize.getCurrentSize());
                break;
            case 5:
                String pizzaSize = new Scanner(System.in).nextLine().toUpperCase();
                if (clientPizzaSize.SIZES_OF_PIZZA.contains(pizzaSize)) {
                    clientPizzaSize.setCurrentSize(pizzaSize);
                }
                break;
            case 6:
                addIngredient(scanner);
                break;
            case 7:
                if (CUSTOMER_PIZZA_INGREDIENTS.getIngredients().size() != 0 && clientPizzaSize.getCurrentSize()!="") {
                    Pizza clientPizza = new Pizza(clientPizzaSize, CUSTOMER_PIZZA_INGREDIENTS);
                    PIZZA_QUEUE.add(clientPizza);
                    CUSTOMER_PIZZA_INGREDIENTS.clear();
                    clientPizzaSize.setCurrentSize("");
                } else {
                    System.out.println("Please add ingredient and enter size");
                }
                break;
            case 8:
                System.exit(0);
                break;
        }
    }
}






