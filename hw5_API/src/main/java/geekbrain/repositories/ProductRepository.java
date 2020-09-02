package geekbrain.repositories;

import geekbrain.entities.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Stateless
public class ProductRepository {

    public ProductRepository() {
    }

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @EJB
    private CategoryRepository categoryRepository;

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public void insert(Product product) {
        logger.info("Inserting new product");
        em.persist(product);
    }

    public void update(Product product) {
        em.merge(product);
    }

    public void delete(long id) {
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.remove(product);
        }
    }

    public Optional<Product> findById(long id) {
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
