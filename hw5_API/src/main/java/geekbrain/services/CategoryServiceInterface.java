package geekbrain.services;

import geekbrain.entities.Category;
import geekbrain.entities.CategoryDao;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface CategoryServiceInterface {
    public List<CategoryDao> findAll();

    public Optional<CategoryDao> findByName(String name);

    public Optional<CategoryDao> findById(long id);

    public void insert(CategoryDao categoryDao);

    public void update(CategoryDao categoryDao);

    public void delete(long id);
}
