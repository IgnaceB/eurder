package switchfully.com.eurder.itemgroup.DTO;

public class ItemGroupDTO {
    private String itemName;
    private int amountOrdered;
    private double totalPrice;


    public ItemGroupDTO(String itemName, int amountOrdered, double totalPrice) {
        this.itemName = itemName;
        this.amountOrdered = amountOrdered;
        this.totalPrice = totalPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public int getAmountOrdered() {
        return amountOrdered;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
