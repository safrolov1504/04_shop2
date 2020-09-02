package geekbrain.services;

import geekbrain.entities.Category;
import geekbrain.entities.CategoryDao;
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
public class CategoryService implements CategoryServiceInterface {

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    @TransactionAttribute
    public List<CategoryDao> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDao::new).collect(Collectors.toList());
    }

    @Override
    @TransactionAttribute
    public Optional<CategoryDao> findByName(String name) {
        return categoryRepository.findByName(name)
                .map(CategoryDao::new);
    }

    @Override
    @TransactionAttribute
    public Optional<CategoryDao> findById(long id) {
        return categoryRepository.findById(id)
                .map(CategoryDao::new);
    }

    @Override
    @TransactionAttribute
    public void insert(CategoryDao categoryDao) {
        Category category = new Category(categoryDao.getId(),
                categoryDao.getName());
        categoryRepository.insert(category);
    }

    @Override
    @TransactionAttribute
    public void update(CategoryDao categoryDao) {
        Category category = new Category(categoryDao.getId(),
                categoryDao.getName());
        categoryRepository.update(category);
    }

    @Override
    @TransactionAttribute
    public void delete(long id) {
        categoryRepository.delete(id);
    }
}
