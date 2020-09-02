package geekbrain.controllers;

import geekbrain.entities.CategoryDao;
import geekbrain.entities.Product;
import geekbrain.entities.ProductDao;
import geekbrain.services.CartServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductDao productDao;

    @EJB
    private CartServiceInterface cartService;

    public List<ProductDao> getAllProducts() {
        List<ProductDao> result = cartService.getAllProducts();
        logger.info("show all products from carController"+result.toString());
        return result;
    }

    public void add(ProductDao productDao) {
        logger.info("!!!!!!!!!!!!!add product to car "+ productDao.toString());
        cartService.add(productDao);
    }

    public void delete(ProductDao productDao){
        cartService.delete(productDao.getId());
    }
}
