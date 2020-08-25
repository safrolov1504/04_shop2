package geekbrain.Servlents;

import geekbrain.categories.CategoryRepository;
import geekbrain.products.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CategoryControllerServlet", urlPatterns = {"/categories"})
public class CategoryControllerServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductControllerServlet.class);

    private CategoryRepository categoryRepository;

    @Override
    public void init() throws ServletException {
        categoryRepository = (CategoryRepository) getServletContext().getAttribute("categoryRepository");
        if (categoryRepository == null) {
            throw new ServletException("Category repository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getPathInfo().equals("/")) {
            try {
                req.setAttribute("category", categoryRepository.findAll());
                getServletContext().getRequestDispatcher("/WEB-INF/categories.jsp").forward(req, resp);
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
        }

    }
}
