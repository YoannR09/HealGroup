package com.appli.healGroup.model.heal;

public class InstaHeal implements Heal{

    private int HEAL = 30;
    private int INCANT = 7000;
    private int COST = 2;


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
