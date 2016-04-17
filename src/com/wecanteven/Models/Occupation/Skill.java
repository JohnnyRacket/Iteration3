package com.wecanteven.Models.Occupation;

/**
 * Created by simonnea on 4/16/16.
 */
public enum Skill {
    BIND_WOUNDS("Bind Wounds"),
    BARGAIN("Bargain"),
    OBSERVATION("Observation"),
    ONE_HANDED_WEAPON("One-handed Weapon"),
    TWO_HANDED_WEAPON("Two-handed Weapon"),
    BRAWLING("Brawling"),
    ENCHANTMENT("Enchantment"),
    BOON("Boon"),
    BANE("Bane"),
    STAFF("Staff Whackery"),
    PICK_POCKET("Stealing Shit"),
    DETECT_AND_REMOVE("Detect-N-Remove Traps"),
    CREEP("Creep"),
    RANGED_WEAPON("Ranged Weapon");

    private final String name;

    Skill(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public static Skill fromString(String skill) {
        if (skill != null) {
            for (Skill s : Skill.values()) {
                if (s.toString().equalsIgnoreCase(skill)) {
                    return s;
                }
            }
        }
        throw new IllegalArgumentException("That skill is not supported: " + skill);
    }
}
