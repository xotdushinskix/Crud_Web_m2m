package fabric;

import dao.ProductDao;
import dao.UserDao;
import dao_impl.ProductDaoImpl;
import dao_impl.UserDaoImpl;

/**
 * Created by FromxSoul on 17.05.2016.
 */
public class Fabric {

    private static UserDao userDao = null;
    private static ProductDao productDao = null;
    private static Fabric instance = null;

    public static synchronized Fabric getInstance() {
        if (instance == null) {
            instance = new Fabric();
        }
        return instance;
    }


    public static synchronized UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }


    public static synchronized ProductDao getProductDao() {
        if (productDao == null) {
            productDao = new ProductDaoImpl();
        }
        return productDao;
    }

}
