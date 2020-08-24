package geekbrain.listener;

import geekbrain.categories.Category;
import geekbrain.categories.CategoryRepository;
import geekbrain.products.Product;
import geekbrain.products.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class AppBootstrapListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(AppBootstrapListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Initializing application");

        ServletContext sc = sce.getServletContext();
        String jdbcConnectionString = sc.getInitParameter("jdbcConnectionString");
        String username = sc.getInitParameter("username");
        String password = sc.getInitParameter("password");

        try {
            Connection conn = DriverManager.getConnection(jdbcConnectionString, username, password);
            sc.setAttribute("connection", conn);

            ProductRepository productRepository = new ProductRepository(conn);
            sc.setAttribute("productRepository", productRepository);

//            CategoryRepository categoryRepository = new CategoryRepository(conn);
//            sc.setAttribute("categoryRepository", categoryRepository);

            if (productRepository.findAll().isEmpty()) {
                logger.info("No products in DB. Initializing.");

                productRepository.insert(new Product(-1L, "Apple Macbook pro 2015", "Apple profession laptop", new BigDecimal(3000)));
                productRepository.insert(new Product(-1L, "Apple Macbook air 2015", "Apple netbook", new BigDecimal(2000)));
                productRepository.insert(new Product(-1L, "Apple iPad", "Apple tablet", new BigDecimal(1000)));
            }

//            if(categoryRepository.findAll().isEmpty()){
//                logger.info("No category in DB. ");
//
//                categoryRepository.insert(new Category(-1L,"PC"));
//                categoryRepository.insert(new Category(-1L,"Goods"));
//            }

        } catch (SQLException ex) {
            logger.error("", ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
