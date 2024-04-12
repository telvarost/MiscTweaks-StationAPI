package com.github.telvarost.misctweaks;

public enum ZombieDropEnum {
    FEATHER("Feathers"),
    RED_MUSHROOM("Red Mushrooms"),
    CYAN_DYE("Cyan Dye"),
    GREEN_DYE("Green Dye"),
    CLAY("Clay"),
    PAPER("Paper"),
    NOTHING("Nothing");

    final String stringValue;

    ZombieDropEnum() {
        this.stringValue = "Feathers";
    }

    ZombieDropEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}