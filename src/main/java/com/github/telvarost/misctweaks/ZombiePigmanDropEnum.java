package com.github.telvarost.misctweaks;

public enum ZombiePigmanDropEnum {
    COOKED_PORKCHOP("Cooked Porkchops"),
    RAW_PORKCHOP("Raw Porkchops"),
    BROWN_MUSHROOM("Brown Mushrooms"),
    GOLD_SWORD("Gold Sword"),
    BONE_MEAL("Bone Meal"),
    BRICK("Brick"),
    GLOWSTONE_DUST("Glowstone Dust"),
    NOTHING("Nothing");

    final String stringValue;

    ZombiePigmanDropEnum() {
        this.stringValue = "Feathers";
    }

    ZombiePigmanDropEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}