package util;

import dao.ProductDao;
import dao.UserDao;
import fabric.Fabric;
import org.hibernate.SessionFactory;
import table.Product;
import table.User;
import java.sql.SQLException;
import java.util.Set;

/**
 * Created by FromxSoul on 17.05.2016.
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Fabric fabric = Fabric.getInstance();
        UserDao userDao = fabric.getUserDao();
        ProductDao productDao = fabric.getProductDao();
        

//        User user1 = new User();
//        user1.setFirstName("Adam");
//        user1.setLastName("Smith");
//        user1.setShopExperience(12);
//        userDao.addUser(user1);
//
//
//        User user2 = new User();
//        user2.setFirstName("Kevin");
//        user2.setLastName("Malek");
//        user2.setShopExperience(29);
//        userDao.addUser(user2);
//
//
//        User user3 = new User();
//        user3.setFirstName("Patrick");
//        user3.setLastName("MacKoy");
//        user3.setShopExperience(18);
//        userDao.addUser(user3);
//
//
//        User user4 = new User();
//        user4.setFirstName("Willy");
//        user4.setLastName("Kennedy");
//        user4.setShopExperience(14);
//        userDao.addUser(user4);
//
//
//
//
//        Product product1 = new Product();
//        product1.setProductBrand("Lenovo");
//        product1.setProductModel("G700");
//        product1.setProductMPN(1221);
//        productDao.addProduct(product1);
//
//        Product product2 = new Product();
//        product2.setProductBrand("Sony");
//        product2.setProductModel("Experia");
//        product2.setProductMPN(2343);
//        productDao.addProduct(product2);
//
//        Product product3 = new Product();
//        product3.setProductBrand("Asus");
//        product3.setProductModel("Ace_b");
//        product3.setProductMPN(3454);
//        productDao.addProduct(product3);
//
//        Product product4 = new Product();
//        product4.setProductBrand("Apple");
//        product4.setProductModel("MacBook_Pro");
//        product4.setProductMPN(4565);
//        productDao.addProduct(product4);
//
//        Product product5 = new Product();
//        product5.setProductBrand("Phillips");
//        product5.setProductModel("MG_QA");
//        product5.setProductMPN(5676);
//        productDao.addProduct(product5);
//
//        Product product6 = new Product();
//        product6.setProductBrand("LG");
//        product6.setProductModel("Razer_m");
//        product6.setProductMPN(6787);
//        productDao.addProduct(product6);
//
//        Product product7 = new Product();
//        product7.setProductBrand("Logitech");
//        product7.setProductModel("Versus");
//        product7.setProductMPN(7898);
//        productDao.addProduct(product7);
//
//        Product product8 = new Product();
//        product8.setProductBrand("Samsung");
//        product8.setProductModel("LX");
//        product8.setProductMPN(8909);
//        productDao.addProduct(product8);

    }

}
