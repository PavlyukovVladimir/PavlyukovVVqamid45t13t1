package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    private ProductManager manager;

    @BeforeEach
    void setUp() {
        manager = new ProductManager(new ProductRepository());
    }

    @Test
    void addOneNewProduct() {
        manager.add(new Product("one", 10L));

        Product[] expected = new Product[]{new Product("one", 10L)};
        Product[] actual = manager.searchBy("");

        assertArrayEquals(expected, actual);
    }

    @Test
    void addOneNewBook() {
        manager.add(new Book("one", "Author", 10L));

        Product[] expected = new Product[]{new Book("one", "Author", 10L)};
        Product[] actual = manager.searchBy("");

        assertArrayEquals(expected, actual);
    }

    @Test
    void addOneNewSmartphone() {
        manager.add(new Smartphone("one", "Manufacturer", 10L));

        Product[] expected = new Product[]{new Smartphone("one", "Manufacturer", 10L)};
        Product[] actual = manager.searchBy("");

        assertArrayEquals(expected, actual);
    }

    @Test
    void saveSmartphoneBookAndProduct() {
        manager.add(new Smartphone("one", "Manufacturer", 10L));
        manager.add(new Book("two", "Author", 11L));
        manager.add(new Product("one", 112L));

        Product[] expected = new Product[]{
                new Smartphone("one", "Manufacturer", 10L),
                new Book("two", "Author", 11L),
                new Product("one", 112L)};
        Product[] actual = manager.searchBy("");

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBy() {
        manager.add(new Smartphone("one", "Manufacturer", 10L));
        manager.add(new Book("two", "Author", 11L));
        manager.add(new Product("one", 112L));

        Product[] expected = new Product[]{
                new Smartphone("one", "Manufacturer", 10L),
                new Product("one", 112L)};
        Product[] actual = manager.searchBy("on");

        assertArrayEquals(expected, actual);
    }

    @Test
    void nothingSearch() {
        manager.add(new Smartphone("one", "Manufacturer", 10L));
        manager.add(new Book("two", "Author", 11L));
        manager.add(new Product("one", 112L));

        Product[] expected = new Product[0];
        Product[] actual = manager.searchBy("three");

        assertArrayEquals(expected, actual);
    }

    @Test
    void positiveMatches() {
        Product product = new Smartphone("one", "Manufacturer", 10L);
        boolean expected = true;
        boolean actual = manager.matches(product, "ne");

        assertEquals(expected, actual);
    }

    @Test
    void negativeMatches() {
        Product product = new Smartphone("one", "Manufacturer", 10L);
        boolean expected = false;
        boolean actual = manager.matches(product, "oe");

        assertEquals(expected, actual);
    }
}