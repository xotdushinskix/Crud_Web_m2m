package table;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by nikita on 26.05.16.
 */
@Embeddable
public class UserProductsPK implements Serializable {

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
