package ru.netology;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Book extends Product {
    private String author;

    public Book(@NotNull String name, @NotNull String author, long cost) {
        super(name, cost);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return getAuthor().equals(book.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAuthor());
    }
}
