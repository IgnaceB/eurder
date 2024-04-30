package switchfully.com.eurder.itemgroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import switchfully.com.eurder.users.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemGroupRepository extends JpaRepository<ItemGroup, UUID> {

    List<ItemGroup> findByOrder_id(UUID orderId);

    List<ItemGroup> findByOrderUser(@Param("user") User user);
}
