package geekbrain.services;

import geekbrain.entities.ProductDao;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface ProductServiceInterface {
    void insert(ProductDao productDao);

    void update(ProductDao productDao);

    void delete(long id);

    Optional<ProductDao> findById(long id);

    List<ProductDao> findAll();
}
