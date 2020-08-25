package geekbrain.Servlents;

import geekbrain.categories.Category;
import geekbrain.categories.CategoryRepository;
import geekbrain.products.Product;
import geekbrain.products.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@Named
public class CategoryController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoryRepository categoryRepository;

    private Category category;

    public List<Category> getAllCategories() {
        logger.info("Get all categories");
        return categoryRepository.findAll();
    }

    public void deleteCategory(Category category) {
        categoryRepository.delete(category.getId());
    }

    public String editCategory(Category category) {
        this.category = category;
        return "/category.xhtml?faces-redirect=true";
    }

    public String createCategory() {
        this.category = new Category();
        return "/category.xhtml?faces-redirect=true";
    }

    public String saveCategory() {
        if (category.getId() != null) {
            categoryRepository.update(category);
        } else {
            categoryRepository.insert(category);
        }
        return "/categories.xhtml?faces-redirect=true";
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
