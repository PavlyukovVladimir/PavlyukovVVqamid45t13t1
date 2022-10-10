package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    private ProductRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ProductRepository();
    }

    @Test
    void saveOneNewProduct() {
        repository.save(new Product("one", 10L));
        Product[] expected = new Product[]{new Product("one", 10L)};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void saveOneNewBook() {
        repository.save(new Book("one", "Author", 10L));
        Product[] expected = new Product[]{new Book("one", "Author", 10L)};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void saveOneNewSmartphone() {
        repository.save(new Smartphone("one", "Manufacturer", 10L));
        Product[] expected = new Product[]{new Smartphone("one", "Manufacturer", 10L)};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void saveSmartphoneBookAndProduct() {
        repository.save(new Smartphone("one", "Manufacturer", 10L));
        repository.save(new Book("two", "Author", 11L));
        repository.save(new Product("one",  112L));
        Product[] expected = new Product[]{
                new Smartphone("one", "Manufacturer", 10L),
                new Book("two", "Author", 11L),
                new Product("one",  112L)};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void removeByIdMiddle() {
        repository.save(new Smartphone("one", "Manufacturer", 10L));
        repository.save(new Book("two", "Author", 11L));
        repository.save(new Product("one",  112L));

        repository.removeById(repository.findAll()[1].getId());

        Product[] expected = new Product[]{
                new Smartphone("one", "Manufacturer", 10L),
                new Product("one",  112L)};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void removeByIdBegin() {
        repository.save(new Smartphone("one", "Manufacturer", 10L));
        repository.save(new Book("two", "Author", 11L));
        repository.save(new Product("one",  112L));

        repository.removeById(repository.findAll()[0].getId());

        Product[] expected = new Product[]{
                new Book("two", "Author", 11L),
                new Product("one",  112L)};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void removeByIdEnd() {
        repository.save(new Smartphone("one", "Manufacturer", 10L));
        repository.save(new Book("two", "Author", 11L));
        repository.save(new Product("one",  112L));

        repository.removeById(repository.findAll()[2].getId());

        Product[] expected = new Product[]{
                new Smartphone("one", "Manufacturer", 10L),
                new Book("two", "Author", 11L)};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void removeByUnknownId() {
        repository.save(new Smartphone("one", "Manufacturer", 10L));
        repository.save(new Book("two", "Author", 11L));
        repository.save(new Product("one",  112L));

        Exception exception = assertThrows(NullPointerException.class, () -> {
            repository.removeById(100);
        });

        assertEquals("Объекта с id: " + 100 + " нет", exception.getMessage());
    }

    @Test
    void createByOtherRepo() {
        Product[] repository = new Product[]{
                new Smartphone("one", "Manufacturer", 10L),
                new Book("two", "Author", 11L),
                new Product("one",  112L)};

        this.repository = new ProductRepository(repository);

        Product[] expected = new Product[]{
                new Smartphone("one", "Manufacturer", 10L),
                new Book("two", "Author", 11L),
                new Product("one",  112L)};
        Product[] actual = this.repository.findAll();

        assertArrayEquals(expected, actual);
    }
}