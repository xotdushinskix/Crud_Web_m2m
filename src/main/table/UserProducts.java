package table;

import javax.persistence.*;

/**
 * Created by nikita on 26.05.16.
 */
@Entity
@Table(name = "user_products")
@AssociationOverrides({
        @AssociationOverride(name = "id.user", joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "id.product", joinColumns = @JoinColumn(name = "product_id"))
})
public class UserProducts {

    @EmbeddedId
    private UserProductsPK id = new UserProductsPK();

    @Column(name = "bought_quantity")
    private int boughtQuantity;

    public UserProductsPK getId() {
        return id;
    }

    public void setId(UserProductsPK id) {
        this.id = id;
    }

    public int getBoughtQuantity() {
        return boughtQuantity;
    }

    public void setBoughtQuantity(int boughtQuantity) {
        this.boughtQuantity = boughtQuantity;
    }

    public User getUser() {
        return getId().getUser();
    }

    public void setUser(User user) {
        getId().setUser(user);
    }

    public Product getProduct() {
        return getId().getProduct();
    }

    public void setProduct(Product product) {
        getId().setProduct(product);
    }
}
