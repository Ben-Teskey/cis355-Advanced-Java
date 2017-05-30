
/**
 * The following class handles storing all item data used to create this post system.
 * id, price, name are all directly read from the input file.
 * A quantity field was added to make counting items easier.
 */
public class Item {

    private char id;
    private double price;
    private String name;
    private int quantity;

    Item(char id, double price, String name, int quantity) {

        this.id = id;
        this.price = price;
        this.name = name;
        this.quantity = quantity;

    }

    public char getId() {
        return id;
    }

    public void setId(char id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
