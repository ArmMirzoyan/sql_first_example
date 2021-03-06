package com.example.tomcattest.repository;

import com.example.tomcattest.model.Item;
import com.example.tomcattest.model.StockItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ItemRepository {

    private static ItemRepository sInstance;

    private final List<Item> items = new ArrayList<>();

    {
        items.add(new StockItem(1, 100, "shun"));
        items.add(new StockItem(2, 500, "napo"));
    }

    public static ItemRepository getInstance() {
        if (sInstance == null) {
            sInstance = new ItemRepository();
        }

        return sInstance;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public long updateItem(Item item) {
        return items.stream()
                .filter((i) -> i.equals(item))
                .map((i) -> item)
                .count();
    }

    public void addItemAll(List<Item> items) {
        this.items.addAll(items);
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(items);
    }

    public List<Item> findItems(Predicate<Item> predicate) {
        return items.stream().filter(predicate).collect(Collectors.toList());
    }

    public Optional<Item> findItemById(long itemId) {
        return  items.stream()
                .filter((i) -> i.getId() == itemId)
                .findAny();
    }

    public boolean deleteById(int itemId) {
        return items.removeIf(i -> i.getId() == itemId);
    }

    private ItemRepository() {

    }
}
