package edu.school21.repositories;

import edu.school21.models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private final JdbcTemplate jdbcTemplate;

    final String UPDATE_QUERY = "UPDATE products SET name = ?, price = ? WHERE identifier = ?";
    final String FIND_ID_QUERY = "SELECT * FROM products WHERE identifier = ";
    final String FIND_ALL_QUERY = "SELECT * FROM products";
    final String SAVE_QUERY = "INSERT INTO products (name, price) VALUES (?, ?)";
    final String DELETE_QUERY = "DELETE FROM products WHERE identifier = ?";


    public ProductsRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class ProductMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            return (new Product(rs.getLong("identifier"),
                    rs.getString("name"),
                    rs.getInt("price")));
        }
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(FIND_ALL_QUERY, new ProductMapper());
    }

    @Override
    public Optional<Product> findById(Long id) {
        Product product = jdbcTemplate.queryForObject(FIND_ID_QUERY + id.intValue(), new ProductMapper());
        if (product == null) throw new RuntimeException("Wrong identifier");
        return Optional.of(product);
    }

    @Override
    public void update(Product product) {
        int res = jdbcTemplate.update(UPDATE_QUERY, product.getName(), product.getPrice(), product.getId().intValue());
        if (res == 0) save(product);
    }

    @Override
    public void save(Product product) {
        jdbcTemplate.update(SAVE_QUERY, product.getName(), product.getPrice());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_QUERY, id.intValue());
    }
}
