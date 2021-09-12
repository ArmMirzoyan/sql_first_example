package com.example.tomcattest.model;

import com.example.tomcattest.util.idgenerator.IdGenerator;
import com.example.tomcattest.util.idgenerator.Type;

public class GenerativeItem extends Item {

    public GenerativeItem() {
        this.setId(IdGenerator.getNext(Type.ITEM));
    }

    private double complexity = 1;

    public GenerativeItem(long id, int basePrice, String name) {
        super(id, basePrice, name);
    }

    public double getComplexity() {
        return complexity;
    }

    public void setComplexity(double complexity) {
        this.complexity = complexity;
    }

    @Override
    public int calculatePrice(Configuration configuration) {
        return (int) (getBasePrice() * complexity * configuration.getResolution().getCoefficient());
    }
}
