package geekbrain.products;

import geekbrain.categories.Category;
import geekbrain.categories.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

@ApplicationScoped
@Named
public class ProductRepository {

    public ProductRepository() {
    }

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @Inject
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void init() {
        logger.info("CategoryRepository init");

        if (categoryRepository.findAll().isEmpty()) {
            logger.info("No categories in DB. Initializing.");

            categoryRepository.insert(new Category(null, "Laptop"));
            categoryRepository.insert(new Category(null, "Tablet"));
            categoryRepository.insert(new Category(null, "Netbook"));
        }

        logger.info("ProductRepository init");

        if (this.findAll().isEmpty()) {
            logger.info("No products in DB. Initializing.");

            try {
                ut.begin();

                this.insert(new Product(null, "Apple Macbook pro 2015", "Apple profession laptop",
                        new BigDecimal(3000), categoryRepository.findByName("Laptop").get()));
                this.insert(new Product(null, "Apple Macbook air 2015", "Apple netbook",
                        new BigDecimal(2000), categoryRepository.findByName("Netbook").get()));
                this.insert(new Product(null, "Apple iPad", "Apple tablet",
                        new BigDecimal(1000), categoryRepository.findByName("Tablet").get()));

                ut.commit();
            } catch (Exception ex) {
                logger.error("", ex);
                try {
                    ut.rollback();
                } catch (SystemException e) {
                    logger.error("", e);
                }
            }
        }
    }

    @Transactional
    public void insert(Product product) {
        logger.info("Inserting new product");
        em.persist(product);
    }

    @Transactional
    public void update(Product product) {
        em.merge(product);
    }

    @Transactional
    public void delete(long id) {
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.remove(product);
        }
    }

    public Optional<Product> findById(long id) throws SQLException {
        Product product = em.find(Product.class, id);
        if (product != null) {
            return Optional.of(product);
        }
        return Optional.empty();
    }

    public List<Product> findAll() {
        return em.createQuery("from Product", Product.class).getResultList();
    }

}
