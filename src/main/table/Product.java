package table;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by FromxSoul on 17.05.2016.
 */
@Entity
@Table(name = "product_m2m")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_brand")
    private String productBrand;

    @Column(name = "product_model")
    private String productModel;

    @Column(name = "product_mpn")
    private int productMPN;

    @ManyToMany(mappedBy = "products")
    private Set<User>users = new HashSet<User>();

    public Product() {

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public int getProductMPN() {
        return productMPN;
    }

    public void setProductMPN(int productMPN) {
        this.productMPN = productMPN;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
