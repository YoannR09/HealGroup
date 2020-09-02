package com.appli.healGroup.model.level;

import com.appli.healGroup.model.level.Level;

public class Level1 implements Level {

    public int SMALLDMG = 5;
    public int BASICDMG = 20;
    public int BIGDMG = 15;


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
        return BASICDMG;
    }
}
