package switchfully.com.eurder.itemgroup;

import switchfully.com.eurder.items.Item;

import java.time.LocalDate;


public class ItemGroup {
    private Item item;
    private int amount;
    private LocalDate shippingDate;


    public ItemGroup(Item item, int amount, LocalDate shippingDate) {
        this.item = item;
        this.amount = amount;
        this.shippingDate = shippingDate;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }


    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public double calculateTotalPriceOfTheGroup(){
        return this.item.getPrice()*this.amount;
    }





}
