package switchfully.com.eurder.orders;

import jakarta.persistence.*;
import switchfully.com.eurder.itemgroup.ItemGroup;
import switchfully.com.eurder.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

    public Order(UUID id, User user) {
        this.id = id;
        this.user = user;
    }

    public Order() {

    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}
