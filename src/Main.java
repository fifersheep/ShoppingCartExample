/** Created by: Scott Laing
 ** Date: 11/12/13 @ 20:59 */

public class Main {
    public static void main(String[] args) {

        Checkout checkout = new Checkout();

        checkout.addItem("Biscuits", 2);
        checkout.addItem("Juice", 2);
        checkout.addItem("Meal", 1);
        checkout.addItem("Juice", 2);
        checkout.addItem("Meal", 1);
        checkout.removeItem("Meal", 2);
        checkout.addItem("Meal", 3);

        System.out.print(checkout.printReceipt());
    }
}
