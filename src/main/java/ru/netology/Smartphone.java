package ru.netology;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Smartphone extends Product {
    private String manufacturer;

    public Smartphone(@NotNull String name, @NotNull String manufacturer, long cost) {
        super(name, cost);
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Smartphone)) return false;
        if (!super.equals(o)) return false;
        Smartphone that = (Smartphone) o;
        return getManufacturer().equals(that.getManufacturer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getManufacturer());
    }

    @Override
    public boolean matches(String search) {
        return super.matches(search) || getManufacturer().contains(search);
    }
}
