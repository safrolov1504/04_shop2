package geekbrain.services;

import geekbrain.entities.ProductDao;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CartServiceInterface {

    void add(ProductDao productDao);

    List<ProductDao> getAllProducts();

    public void delete(long id);
}
