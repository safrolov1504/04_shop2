package geekbrain.controllers;

import geekbrain.entities.ProductDao;
import geekbrain.services.CategoryServiceInterface;
import geekbrain.services.ProductServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class ProductController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @EJB
    private ProductServiceInterface productService;

    @EJB
    private CategoryServiceInterface categoryService;

    private ProductDao product;

    public ProductDao getProduct() {
        return product;
    }

    public void setProduct(ProductDao product) {
        this.product = product;
    }

    public List<ProductDao> getAllProducts() {
        logger.info("Get all products form product Controller");
        return productService.findAll();
    }

    public String editProduct(ProductDao productDao) {
        this.product = productDao;
        return "/product.xhtml?faces-redirect=true";
    }

    public void deleteProduct(ProductDao productDao) {
        productService.delete(productDao.getId());
    }

    public String createProduct() {
        this.product = new ProductDao();
        return "/product.xhtml?faces-redirect=true";
    }

    public String saveProduct() {
        if (product.getId() != null) {
            productService.update(product);
        } else {
            productService.insert(product);
        }
        return "/products.xhtml?faces-redirect=true";
    }


}
