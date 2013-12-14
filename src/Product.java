/** Created by: Scott Laing
 ** Date: 10/12/13 @ 21:53 */

public class Product {

    protected String productName;
    protected int standardPrice, offerPrice, offerQuantity; //Currency used is pence

    /** Product with no deal **/
    Product (String productName, int standardPrice) {
        this.productName = productName;
        this.standardPrice = standardPrice;
    }

    /** Product with deal **/
    Product (String productName, int standardPrice, int offerPrice, int offerQuantity) {
        this.productName = productName;
        this.standardPrice = standardPrice;
        this.offerPrice = offerPrice;
        this.offerQuantity = offerQuantity;
    }
}
