package ru.netology;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class ProductManager {
    // добавьте необходимые поля, конструкторы и методы
    private ProductRepository repository;

    public ProductManager(@NotNull ProductRepository repository) {
        this.repository = repository;
    }

    public void add(Product product) {
        repository.save(product);
    }

    public Product[] searchBy(String text) {
        Product[] result = new Product[0]; // тут будем хранить подошедшие запросу продукты
        for (Product product : repository.findAll()) {
            if (product.matches(text)) {
                // "добавляем в конец" массива result продукт product
                Product[] tmp = Arrays.copyOf(result, result.length + 1);
                tmp[result.length] = product;
                result = tmp;
            }
        }
        return result;
    }
}
