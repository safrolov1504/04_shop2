package geekbrain.controllers;


import geekbrain.entities.CategoryDao;
import geekbrain.services.CategoryServiceInterface;
import geekbrain.services.ProductServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServlet;
import java.util.List;

@SessionScoped
@Named
public class CategoryController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @EJB
    private ProductServiceInterface productService;

    @EJB
    private CategoryServiceInterface categoryService;

    private CategoryDao categoryDao;

    public List<CategoryDao> getAllCategories() {
        logger.info("Get all categories");
        return categoryService.findAll();
    }

    public void deleteCategory(CategoryDao categoryDao) {
        categoryService.delete(categoryDao.getId());
    }

    public String editCategory(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
        return "/category.xhtml?faces-redirect=true";
    }

    public String createCategory() {
        this.categoryDao = new CategoryDao();
        return "/category.xhtml?faces-redirect=true";
    }

    public String saveCategory() {
        if (categoryDao.getId() != null) {
            categoryService.update(categoryDao);
        } else {
            categoryService.insert(categoryDao);
        }
        return "/categories.xhtml?faces-redirect=true";
    }

    public CategoryDao getCategory() {
        return categoryDao;
    }

    public void setCategory(CategoryDao category) {
        this.categoryDao = category;
    }
}
