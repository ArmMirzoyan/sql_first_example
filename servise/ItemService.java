package com.example.tomcattest.servise;

import com.example.tomcattest.model.Item;

import java.util.List;

public interface ItemService {
    void add(Item item);

    List<Item> getAll();

    void removeById(int id);

    Item getById(int id);

    void updateById(Item item);
}
