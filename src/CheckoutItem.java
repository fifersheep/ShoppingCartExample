import java.text.DecimalFormat;

/** Created by: Scott Laing
 ** Date: 12/12/13 @ 14:10 **/

public class CheckoutItem extends Product {
    private int cost, savings, quantity;

    CheckoutItem (Product product, int quantity) {
        super(product.productName, product.standardPrice);
        this.quantity = quantity;
        this.offerPrice = product.offerPrice;
        this.offerQuantity = product.offerQuantity;
        calculateCostAndSavings();
    }

    /** Adding a specific quantity of this product **/
    public void add(int quantity) {
        this.quantity = this.quantity + quantity;
        calculateCostAndSavings();
    }

    /** Subtracting a specific quantity of this product **/
    public boolean subtract(int quantity) {
        if (this.quantity > quantity){
            this.quantity = this.quantity - quantity;
            calculateCostAndSavings();
            return true; // Confirm that items still remain
        } else {
            return false; // Indicate that none of these items now exist
        }
    }

    /** Calculate both cost and savings **/
    private void calculateCostAndSavings() {
        if (quantity >= offerQuantity && offerQuantity != 0) {
            int bundles = (quantity / offerQuantity);
            int remainder = (quantity % offerQuantity);
            cost = (bundles * offerPrice)           //add the cost of deal items
                    + (remainder * standardPrice); //add the cost of remaining non-deal items
        } else {
            cost = quantity * standardPrice;
        }

        savings = cost - (quantity * standardPrice);
    }

    /** Return string for the receipt **/
    public String getReceiptDetails() {
        //Represents currency in pounds
        float cost = (float) this.cost / 100;
        float savings = (float) this.savings / 100;
        DecimalFormat currencyFormat = new DecimalFormat("0.00");

        // Builder for printable details
        StringBuilder builder = new StringBuilder(productName + " x" + quantity + ": £" + currencyFormat.format(cost) + "\n");
        if (this.savings < 0) {
            builder.append("   SAVINGS: £")
                    .append(currencyFormat.format(savings))
                    .append("\n");
        }
        return builder.toString();
    }

    public int getCost() {
        return cost;
    }

    public int getSavings() {
        return savings;
    }
}
