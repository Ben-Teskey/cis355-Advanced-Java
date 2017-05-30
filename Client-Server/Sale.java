import java.util.ArrayList;

/**
 * The following class stores all the items purchased in a purchase list and provides
 * methods to set and access this list
 */
public class Sale {

    private ArrayList<Item> purchaseList;

    Sale() {

        purchaseList = new ArrayList<Item>();

    }

    public ArrayList<Item> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(ArrayList<Item> purchaseList) {
        this.purchaseList = purchaseList;
    }

}
