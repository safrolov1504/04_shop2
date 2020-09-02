package geekbrain.entities;

import javax.persistence.*;
import java.util.List;

public class CategoryDao {
    private Long id;

    private String name;

    private List<Product> products;

    public CategoryDao() {
    }

    public CategoryDao(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDao(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        if(!category.getProducts().isEmpty()){
            this.products = category.getProducts();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


}
