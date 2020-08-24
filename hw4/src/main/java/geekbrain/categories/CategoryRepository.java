package geekbrain.categories;

import geekbrain.products.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    private final Connection conn;

    public CategoryRepository(Connection conn) throws SQLException {
        this.conn = conn;
        createTableIfNotExists(conn);
    }

    public List<Category> findAll() throws SQLException {
        List<Category> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id, name from categories");

            while (rs.next()) {
                res.add(new Category(rs.getLong(1), rs.getString(2)));
            }
        }
        return res;
    }

    public void insert(Category category) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into categories(name) values (?);")) {
            stmt.setString(1, category.getName());
            stmt.execute();
        }
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table if not exists categories (\n" +
                    "    id int auto_increment primary key,\n" +
                    "    name varchar(25)\n"+
                    ");");
        }
    }
}
