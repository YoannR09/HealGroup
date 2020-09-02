package com.appli.healGroup.model.level;

public class Level2 implements Level{
    public int BASICDMG = 25;
    public int SMALLDMG = 7;
    public int BIGDMG = 20;

    @Override
    public int getBigDmg() {
        return this.BIGDMG;
    }

    @Override
    public int getSmallDmg() {
        return this.SMALLDMG;
    }

    @Override
    public int getBasicDmg() {
        return this.BASICDMG;
    }
}
