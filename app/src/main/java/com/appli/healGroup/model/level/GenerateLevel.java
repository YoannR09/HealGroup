package com.appli.healGroup.model.level;

public class GenerateLevel {

    public Level getLevel(String level) {
        Level levelToReturn = null;
        switch (Integer.parseInt(level)) {
            case 1 : {
                levelToReturn = new Level1();
                break;
            }
            case 2 : {
                levelToReturn = new Level2();
                break;
            }
        }
        return levelToReturn;
    }
}
