package geekbrain.categories;

import geekbrain.products.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Named
public class CategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Inject
    private UserTransaction ut;

    public CategoryRepository() {
    }

    public List<Category> findAll() {
        return em.createQuery("from Category", Category.class)
                .getResultList();
    }

    public Optional<Category> findByName(String name) {
        Category category = em.createQuery("from Category c where c.name = :name", Category.class)
                .setParameter("name", name)
                .getSingleResult();
        if (category != null) {
            return Optional.of(category);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Category> findById(long id) {
        Category category = em.find(Category.class, id);
        if (category != null) {
            return Optional.of(category);
        }
        return Optional.empty();
    }

    @Transactional
    public void insert(Category category) {
        em.persist(category);
    }

    @Transactional
    public void update(Category category) {
        em.merge(category);
    }

    @Transactional
    public void delete(long id) {
        Category category = em.find(Category.class, id);
        if (category != null) {
            em.remove(category);
        }
    }
}
