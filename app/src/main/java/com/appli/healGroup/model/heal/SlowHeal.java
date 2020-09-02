package com.appli.healGroup.model.heal;

public class SlowHeal implements Heal {
    private int HEAL = 25;
    private int INCANT = 2000;
    private int COST = 3;


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
