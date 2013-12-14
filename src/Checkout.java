import java.text.DecimalFormat;
import java.util.*;

/** Created by: Scott Laing
 ** Date: 11/12/13 @ 16:27 */

public class Checkout {
    private List<CheckoutItem> basket;
    private Map<String, Product> availableProducts;
    private int totalCost;
    private int totalSavings;

    public Checkout() {
        basket = new ArrayList<CheckoutItem>();
        availableProducts = new HashMap<String, Product>();
        totalCost = 0;
        totalSavings = 0;
        getAvailableProducts();
    }

    /** Used to provide the example products **/
    private void getAvailableProducts(){
        availableProducts.put("Biscuits", new Product("Biscuits", 129));
        availableProducts.put("Juice", new Product("Juice", 53, 106, 3));
        availableProducts.put("Meal", new Product("Meal", 350, 500, 2));
    }

    /** Adds the selected item to the basket, with quantity **/
    public void addItem(String name, int quantity) {
        int index = -1;

        for (CheckoutItem item : basket) {
            if (item.productName.equalsIgnoreCase(name)) {
                index = basket.indexOf(item);
            }
        }

        // If the item does not exist in the basket
        if (index == -1){
            basket.add(new CheckoutItem(
                    availableProducts.get(name), quantity));
        // If the item already exists
        } else {
            CheckoutItem existingItem = basket.get(index);
            existingItem.add(quantity);
        }

        calculateTotal();
    }

    /** Removes either the selected quantity for an item rom the basket, or removed it altogether **/
    public void removeItem(String name, int quantity) {
        int index = -1;

        for (CheckoutItem item : basket) {
            if (item.productName.equalsIgnoreCase(name)) {
                index = basket.indexOf(item);
            }
        }

        if (index == -1){
            System.out.println("The item " + name + " could not be removed, as it did not exist");
        } else {
            CheckoutItem existingItem = basket.get(index);
            boolean anyItemsLeft = existingItem.subtract(quantity);
            if (!anyItemsLeft) {
                basket.remove(index);
            }
        }

        calculateTotal();
    }

    /** Calculates the total cost and savings of the basket **/
    public void calculateTotal() {
        int cost = 0;
        int savings = 0;

        for (CheckoutItem item : basket) {
            cost = cost + item.getCost();
            savings = savings + item.getSavings();
        }

        totalCost = cost;
        totalSavings = savings;
    }

    /** Prints the necessary details of the basket to the console **/
    public String printReceipt() {
        StringBuilder builder = new StringBuilder("SCOTT'S GROCERIES:\n--------------------\n");

        for (CheckoutItem item : basket) {
            builder.append(item.getReceiptDetails());
        }

        //Represents currency in pounds
        float cost = (float) totalCost / 100;
        float savings = (float) totalSavings / 100;
        DecimalFormat currencyFormat = new DecimalFormat("0.00");

        builder.append("--------------------\nTOTAL: £").append(currencyFormat.format(cost))
                .append("\n").append("SAVINGS: £").append(currencyFormat.format(savings)).append("\n");

        return builder.toString();
    }
}
