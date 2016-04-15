package com.wecanteven.SaveLoad.XMLProcessors;

import com.wecanteven.Models.Map.Aoe.*;
import com.wecanteven.UtilityClasses.Location;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public class AOEXMLProcessor extends XMLProcessor {
    private static void format(Map<String, String> attributes) {
        ArrayList<Attr> attr = new ArrayList<>();
        for (Map.Entry<String, String> e : attributes.entrySet()) {
            attr.add(sf.saveAttr(e.getKey(), e.getValue()));
        }

        sf.appendObjectToMostRecent(sf.createSaveElement("AreaOfEffect", attr));
    }

    private static Map<String, String> formatBaseAoe(AreaOfEffect aoe) {
        Map<String, String> attributes = new HashMap<>();

        attributes.put("type", aoe.getClass().getSimpleName());

        return attributes;
    }

    private static Map<String, String> formatBaseCooldownAoe(CoolDownAoE aoe) {
        Map<String, String> attrs = formatBaseAoe(aoe);

        attrs.put("cooldownticks", Integer.toString(aoe.getCoolDownTicks()));

        return attrs;
    }

    public static void formatAoe(AreaOfEffect aoe) {
        format(formatBaseAoe(aoe));
    }

    public static void formatCooldownAoe(CoolDownAoE aoe) { format(formatBaseCooldownAoe(aoe)); }

    public static void formatTeleportAoe(TeleportAoe aoe) {
        Map<String, String> attrs = formatBaseAoe(aoe);

        Location loc = aoe.getDestination();

        attrs.put("r", Integer.toString(loc.getR()));
        attrs.put("s", Integer.toString(loc.getS()));
        attrs.put("z", Integer.toString(loc.getZ()));

        format(attrs);
    }

    public static void formatHealingAreaOfEffect(HealingAreaOfEffect aoe) {
        Map<String, String> attrs = formatBaseAoe(aoe);

        int healPerTick = aoe.getHealPerTick();

        attrs.put("healpertick", Integer.toString(healPerTick));

        format(attrs);
    }

    public static void formatTakeDamageAreaOfEffect(TakeDamageAreaOfEffect aoe) {
        Map<String, String> attrs = formatBaseAoe(aoe);

        int healPerTick = aoe.getDamagePerTick();

        attrs.put("damagepertick", Integer.toString(healPerTick));

        format(attrs);
    }

    public static AreaOfEffect parseAoe(Element el) {
        String type = sf.getStrAttr(el, "type");

        switch (type.trim()) {
            case "HealingAreaOfEffect":
                return parseHealingAoe(el);
            case "InstaDeathAoe":
                return new InstaDeathAoe();
            case "LevelUpAoe":
                return parseCooldownAoe(el, type.trim());
            case "TakeDamageAreaOfEffect":
                return parseTakeDamageAoe(el);
            case "TeleportAoe":
                return parseTeleportAoe(el);
            default:
                throw new IllegalArgumentException("Unsupported Area of Effect");
        }
    }

    public static CoolDownAoE parseCooldownAoe(Element el, String type) {
        int cooldownticks = Integer.parseInt(sf.getStrAttr(el, "cooldownticks"));

        switch(type) {
            case "LevelUpAoe":
                return new LevelUpAoe(cooldownticks);
            default:
                throw new IllegalArgumentException("Unsupported Cooldown Area of Effect");
        }
    }

    public static TeleportAoe parseTeleportAoe(Element el) {
        int r = Integer.parseInt(sf.getStrAttr(el, "r"));
        int s = Integer.parseInt(sf.getStrAttr(el, "s"));
        int z = Integer.parseInt(sf.getStrAttr(el, "z"));

        return new TeleportAoe(new Location(r,s,z));
    }

    public static HealingAreaOfEffect parseHealingAoe(Element el) {
        int healPerTick = Integer.parseInt(sf.getStrAttr(el, "healpertick"));

        return new HealingAreaOfEffect(healPerTick);
    }

    public static TakeDamageAreaOfEffect parseTakeDamageAoe(Element el) {
        int dmgPerTick = Integer.parseInt(sf.getStrAttr(el, "damagepertick"));

        return new TakeDamageAreaOfEffect(dmgPerTick);
    }
}
