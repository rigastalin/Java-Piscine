package edu.school21.repositories;

import edu.school21.models.Product;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class ProductsRepositoryJdbcImplTest {
    private ProductsRepositoryJdbcImpl productsRepositoryJdbc;
    private EmbeddedDatabase embeddedDatabase;
    private final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(new Product(0L, "tomato", 100),
            new Product(1L, "eggs", 10),
            new Product(2L, "potato", 35),
            new Product(3L, "parsley", 30),
            new Product(4L, "mango", 300),
            new Product(5L, "milk", 90),
            new Product(6L, "water", 15)
            );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = EXPECTED_FIND_ALL_PRODUCTS.get(1);
    final Product EXPECTED_UPDATED_PRODUCT = EXPECTED_FIND_ALL_PRODUCTS.get(3);
    final Product EXPECTED_SAVE_PRODUCT = new Product(6L, "water", 15);

    @BeforeEach
    void init() throws SQLException {
        embeddedDatabase = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).
                addScript("schema.sql").
                addScript("data.sql").build();
        productsRepositoryJdbc = new ProductsRepositoryJdbcImpl(embeddedDatabase);
    }

    @AfterEach
    void stop() {
        embeddedDatabase.shutdown();
    }

    @Test
    void findByIdTest() {
        Assertions.assertEquals(Optional.class, productsRepositoryJdbc.findById(1L).getClass());
    }

    @Test
    void findAllTest() {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepositoryJdbc.findAll());
    }

    @Test
    void findByIdExceptionTest() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            productsRepositoryJdbc.findById(332L);
        });
    }

    @Test
    void findByIdOptionalTest() {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productsRepositoryJdbc.findById(1L).get());
    }

    @Test
    void updateTest() {
        EXPECTED_UPDATED_PRODUCT.setName("NEW_NAME");
        productsRepositoryJdbc.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, productsRepositoryJdbc.findById(3L).get());
    }

    @Test
    void saveTest() {
        productsRepositoryJdbc.save(EXPECTED_SAVE_PRODUCT);
        Assertions.assertEquals(EXPECTED_SAVE_PRODUCT, productsRepositoryJdbc.findById(6L).get());
    }

    @Test
    void deleteTest() {
        productsRepositoryJdbc.delete(3L);
        Assertions.assertThrows(RuntimeException.class, () -> {
            productsRepositoryJdbc.findById(3L);
        });
    }
}
