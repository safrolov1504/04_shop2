package geekbrain.services;

import geekbrain.entities.Category;
import geekbrain.entities.Product;
import geekbrain.entities.ProductDao;
import geekbrain.repositories.CategoryRepository;
import geekbrain.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class ProductService implements ProductServiceInterface {
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @TransactionAttribute
    @Override
    public void insert(ProductDao productDao) {
        Category category = categoryRepository.findById(productDao.getCategoryId())
                .orElse(null);
        Product product = new Product(productDao.getId(),
                productDao.getName(),
                productDao.getDescription(),
                productDao.getPrice(),
                category);
        productRepository.insert(product);
    }

    @TransactionAttribute
    @Override
    public void update(ProductDao productDao) {
        Category category = categoryRepository.findById(productDao.getCategoryId())
                .orElse(null);
        Product product = new Product(productDao.getId(),
                productDao.getName(),
                productDao.getDescription(),
                productDao.getPrice(),
                category);
        productRepository.update(product);
    }

    @TransactionAttribute
    @Override
    public void delete(long id) {
        productRepository.delete(id);
    }

    @TransactionAttribute
    @Override
    public Optional<ProductDao> findById(long id) {
        return productRepository.findById(id)
                .map(ProductDao::new);
    }

    @TransactionAttribute
    @Override
    public List<ProductDao> findAll() {
        return productRepository.findAll().stream()
                .map(ProductDao::new)
                .collect(Collectors.toList());
    }
}
