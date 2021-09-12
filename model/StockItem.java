package com.example.tomcattest.model;

import com.example.tomcattest.util.idgenerator.IdGenerator;
import com.example.tomcattest.util.idgenerator.Type;

public class StockItem extends Item {

    public StockItem() {
        this.setId(IdGenerator.getNext(Type.ITEM));
    }

    public StockItem(long id, int basePrice, String name) {
        super(id, basePrice, name);
    }

    @Override
    public int calculatePrice(Configuration configuration) {
        return getBasePrice() * configuration.getResolution().getCoefficient();
    }
}
