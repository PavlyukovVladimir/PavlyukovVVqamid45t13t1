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
    void matches() {
        manager.add(new Book("one", "Athor", 10L));
        manager.add(new Smartphone("two", "Manufacturer", 10L));
        manager.add(new Smartphone("three", "Manfactrer", 10L));
        manager.add(new Product("four",10L));
        manager.add(new Book("five", "Author", 10L));
        manager.add(new Smartphone("usix", "Manfactrer", 10L));
        manager.add(new Book("useven", "Athor", 10L));
        manager.add(new Product("eight",10L));

        Product[] expected = new Product[]{
                new Smartphone("two", "Manufacturer", 10L),
                new Product("four",10L),
                new Book("five", "Author", 10L),
                new Smartphone("usix", "Manfactrer", 10L),
                new Book("useven", "Athor", 10L)};
        Product[] actual = manager.searchBy("u");

        assertArrayEquals(expected, actual);
    }
}