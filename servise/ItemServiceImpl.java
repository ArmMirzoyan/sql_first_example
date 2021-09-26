package com.example.tomcattest.servise;

import com.example.tomcattest.model.Item;
import com.example.tomcattest.repository.ItemHibernateRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("itemServiceImpl")
public class ItemServiceImpl implements ItemService{

    private ItemHibernateRepository itemHibernateRepository;

    public ItemServiceImpl(ItemHibernateRepository itemHibernateRepository){
        this.itemHibernateRepository = itemHibernateRepository;
    }

    @Override
    public void add(Item item) {
        itemHibernateRepository.save(item);
    }

    @Override
    public List<Item> getAll() {
        return itemHibernateRepository.getAll();
    }

    @Override
    public void removeById(int id) {
        itemHibernateRepository.deleteById(id);
    }

    @Override
    public Item getById(int id) {
        return itemHibernateRepository.getById(id);
    }

    @Override
    public void updateById(Item item) {
        itemHibernateRepository.updateById(item);
    }

    @Override
    public void deleteById(int id) {
        itemHibernateRepository.deleteById(id);
    }
}
