package geekbrain.Servlents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "MainControllerServlet", urlPatterns = {"", "/"})
public class MainControllerServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductControllerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Index product page");

        if (req.getServletPath().equals("/")) {
                getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
        }
    }
}
