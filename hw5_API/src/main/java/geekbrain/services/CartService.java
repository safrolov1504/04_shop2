package geekbrain.services;

import geekbrain.controllers.ProductController;
import geekbrain.entities.ProductDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class CartService implements CartServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private List<ProductDao> productDaoList = new ArrayList<>();

    @EJB
    private ProductServiceInterface productService;

    @Override
    @TransactionAttribute
    public void add(ProductDao productDao) {
        logger.info("Service cart "+ productDao.toString());
        productDaoList.add(productDao);
        logger.info("Service cart "+ productDaoList.toString());
    }

    @Override
    @TransactionAttribute
    public List<ProductDao> getAllProducts() {
        logger.info("Service cart out!!!!"+ productDaoList.toString());
        return productDaoList;
    }

    @Override
    @TransactionAttribute
    public void delete(long id) {
        productDaoList.remove(productService.findById(id));
    }


}
