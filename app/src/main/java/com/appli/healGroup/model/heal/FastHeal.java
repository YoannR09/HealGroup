package com.appli.healGroup.model.heal;

public class FastHeal implements Heal {
    private int HEAL = 23;
    private int INCANT = 1000;
    private int COST = 5;


    @Override
    public int getHeal() {
        return this.HEAL;
    }

    @Override
    public int getIncant() {
        return this.INCANT;
    }

    @Override
    public int getCost() {
        return this.COST;
    }
}
