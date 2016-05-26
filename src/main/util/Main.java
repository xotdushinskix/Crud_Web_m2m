package util;

import dao.ProductDao;
import dao.UserDao;
import fabric.Fabric;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import table.Product;
import table.User;
import table.UserProducts;

import java.sql.SQLException;
import java.util.List;
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


        User user = userDao.getUser(1);
//        List<UserProducts> userProductses = user.getUserProducts();
//        for (UserProducts userProducts : userProductses){
//            System.out.println(userProducts.getBoughtQuantity());
//
//        }
        Session session = sessionFactory.openSession();
        SQLQuery query = (SQLQuery) session.createQuery("hh");







//
////        User user = userDao.getUser(1);
////        Product product = productDao.getProduct(8);
////
////        user.getProducts().add(product);
////        product.getUsers().add(user);
////
////        userDao.editUser(user);
////        productDao.editProduct(product);
//
////        List<Product>products = productDao.getAllProducts();
////        for (Product product : products) {
////            System.out.println("Product Id " + product.getProductId() + " product brand " + product.getProductBrand() +
////                    " product model " + product.getProductModel() + " product mpn " + product.getProductMPN());
////        }
//
////        List<User>users = userDao.getAllUsers();
////        for (User user : users) {
////            System.out.println("user id = " + user.getUserId() +  "user first name" + user.getFirstName() +
////            " user last name " + user.getLastName() + " user shop experience " + user.getShopExperience());
////        }
//
////        user.getProducts().add(product);
////        product.getUsers().add(user);
////
////        userDao.editUser(user);
////        productDao.editProduct(product);
//
//





//        User user1 = new User();
//        user1.setFirstName("Adam");
//        user1.setLastName("Smith");
//        user1.setShopExperience(12);
//        user1.setLogin("adam.smith@gmail.com");
//        user1.setPassword("adamsmith");
//        userDao.addUser(user1);
//
//
//        User user2 = new User();
//        user2.setFirstName("Kevin");
//        user2.setLastName("Malek");
//        user2.setShopExperience(29);
//        user2.setLogin("kevin.malek@gmail.com");
//        user2.setPassword("kevinmalek");
//        userDao.addUser(user2);
//
//
//        User user3 = new User();
//        user3.setFirstName("Patrick");
//        user3.setLastName("MacKoy");
//        user3.setShopExperience(18);
//        user3.setLogin("patrick.mackoy@gmail.com");
//        user3.setPassword("patrickmackoy");
//        userDao.addUser(user3);
//
//
//        User user4 = new User();
//        user4.setFirstName("Willy");
//        user4.setLastName("Kennedy");
//        user4.setShopExperience(14);
//        user4.setLogin("willy.kennedy@gmail.com");
//        user4.setPassword("willykennedy");
//        userDao.addUser(user4);
//
//
//
//
//        Product product1 = new Product();
//        product1.setProductBrand("Lenovo");
//        product1.setProductModel("G700");
//        product1.setProductMPN(1221);
//        product1.setProductStock(120);
//        productDao.addProduct(product1);
//
//        Product product2 = new Product();
//        product2.setProductBrand("Sony");
//        product2.setProductModel("Experia");
//        product2.setProductMPN(2343);
//        product2.setProductStock(321);
//        productDao.addProduct(product2);
//
//        Product product3 = new Product();
//        product3.setProductBrand("Asus");
//        product3.setProductModel("Ace_b");
//        product3.setProductMPN(3454);
//        product3.setProductStock(543);
//        productDao.addProduct(product3);
//
//        Product product4 = new Product();
//        product4.setProductBrand("Apple");
//        product4.setProductModel("MacBook_Pro");
//        product4.setProductMPN(4565);
//        product4.setProductStock(867);
//        productDao.addProduct(product4);
//
//        Product product5 = new Product();
//        product5.setProductBrand("Phillips");
//        product5.setProductModel("MG_QA");
//        product5.setProductMPN(5676);
//        product5.setProductStock(1002);
//        productDao.addProduct(product5);
//
//        Product product6 = new Product();
//        product6.setProductBrand("LG");
//        product6.setProductModel("Razer_m");
//        product6.setProductMPN(6787);
//        product6.setProductStock(4332);
//        productDao.addProduct(product6);
//
//        Product product7 = new Product();
//        product7.setProductBrand("Logitech");
//        product7.setProductModel("Versus");
//        product7.setProductMPN(7898);
//        product7.setProductStock(5661);
//        productDao.addProduct(product7);
//
//        Product product8 = new Product();
//        product8.setProductBrand("Samsung");
//        product8.setProductModel("LX");
//        product8.setProductMPN(8909);
//        product8.setProductStock(129);
//        productDao.addProduct(product8);

//        User user = userDao.getUser(1);
//        System.out.println(user.getUserProducts());
//
//        List<UserProducts> userProducts = user.getUserProducts();
//        for (UserProducts userProducts1 : userProducts) {
//            System.out.println(userProduc);
//        }

    }

}
