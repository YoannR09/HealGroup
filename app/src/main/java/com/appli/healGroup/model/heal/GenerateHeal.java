package com.appli.healGroup.model.heal;

public class GenerateHeal {

    public Heal getHeal(int heal) {
        Heal healToReturn = null;
        switch (heal) {
            case 1 : {
                healToReturn = new SlowHeal();
                break;
            }
            case 2 : {
                healToReturn = new FastHeal();
                break;
            }
            case 3 : {
                healToReturn = new InstaHeal();
                break;
            }
        }
        return healToReturn;
    }
}
