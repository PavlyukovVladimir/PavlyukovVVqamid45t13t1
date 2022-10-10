package ru.netology;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Objects;

public class Product {
    static private int count = 0;
    private final int id;
    private String name;

    private long cost = 0;  // 1 unit is 1 kopeck

    public Product(@NotNull String name, long cost) throws IllegalArgumentException {
        if (cost < 0) {
            throw new IllegalArgumentException("\"cost\" mast be not negative");
        }
        this.id = ++count;
        this.name = name;
        this.cost = cost;
    }

    public long getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getCost() == product.getCost() && getName().equals(product.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCost());
    }
}
