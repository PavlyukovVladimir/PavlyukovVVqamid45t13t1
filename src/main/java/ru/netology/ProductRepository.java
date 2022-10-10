package ru.netology;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class ProductRepository {
    private Product[] repo;

    public ProductRepository(@NotNull Product[] repo) {
        this.repo = repo;
    }

    public ProductRepository() {
        repo = new Product[0];
    }

    public void save(@NotNull Product product) {
        Product[] tmp = Arrays.copyOf(repo, repo.length + 1);
        tmp[repo.length] = product;
        repo = tmp;
    }

    public Product[] findAll() {
        return repo.clone();
    }

    public void removeById(int itemId) throws NullPointerException {
        for (int i = 0; i < repo.length; i++) {
            if (repo[i].getId() == itemId) {
                remove(i);
                return;
            }
        }
        throw new NullPointerException("Объекта с id: " + itemId + " нет");
    }

    private void remove(int index) throws NullPointerException {
        Product[] tmp = new Product[repo.length - 1];
        int i = 0;
        for (; i < index; i++) {
            tmp[i] = repo[i];
        }
        i++;
        for (; i < repo.length; i++) {
            tmp[i - 1] = repo[i];
        }
        repo = tmp;
    }
}
