import java.util.ArrayList;

/**
 * The following class stores the total sales in a sales list, and provides methods to set
 * and access this list.
 */
public class TotalSale {

    private ArrayList<Sale> saleList;

    TotalSale() {

        // Constructs a new list of sales
        saleList = new ArrayList<Sale>();

    }

    public ArrayList<Sale> getSaleList() {
        return saleList;
    }

    public void setSaleList(ArrayList<Sale> saleList) {
        this.saleList = saleList;
    }

}
