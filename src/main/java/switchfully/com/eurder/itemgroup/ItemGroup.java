package switchfully.com.eurder.itemgroup;

import jakarta.persistence.*;
import switchfully.com.eurder.items.Item;
import switchfully.com.eurder.orders.Order;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "item_groups")
public class ItemGroup {
    @Id
    @Column(name = "id")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "item_price")
    private double itemPrice;

    @Column(name = "amount_ordered")
    private int amountOrdered;

    @Column(name = "shipping_date")
    private LocalDate shippingDate;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    public ItemGroup(UUID id, Item item, double itemPrice, int amountOrdered, LocalDate shippingDate, Order order) {
        this.id = id;
        this.item = item;
        this.itemPrice = itemPrice;
        this.amountOrdered = amountOrdered;
        this.shippingDate = shippingDate;
        this.order = order;
    }

    public ItemGroup() {
    }

    public Item getItem() {
        return item;
    }

    public UUID getId() {
        return id;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getAmountOrdered() {
        return amountOrdered;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public double calculateTotalPriceOfTheGroup(){
        return this.getItemPrice()*this.amountOrdered;
    }


    public String getItemName() {
        return this.item.getName();
    }

    public Order getOrder() {
        return this.order;
    }
}
