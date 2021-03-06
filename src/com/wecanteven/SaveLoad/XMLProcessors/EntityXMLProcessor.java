package com.wecanteven.SaveLoad.XMLProcessors;

import com.wecanteven.Controllers.AIControllers.AIController;
import com.wecanteven.Controllers.AIControllers.AITime;
import com.wecanteven.Controllers.AIControllers.ActionControllers.EnemyActionController;
import com.wecanteven.Controllers.AIControllers.SearchingControllers.EnemySearchingController;
import com.wecanteven.Models.Abilities.Ability;
import com.wecanteven.Models.Entities.*;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Factories.AbilityFactories.AbilityMap;
import com.wecanteven.Models.Interactions.*;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Occupation.*;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Models.Storage.AbilityStorage.AbilityStorage;
import com.wecanteven.SaveLoad.SaveFile;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.GameColor;
import com.wecanteven.UtilityClasses.Location;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sun.print.SunMinMaxPage;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public class EntityXMLProcessor extends XMLProcessor {

    public static void formatEntity(Entity e) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("Height", e.getHeight()));
        sf.appendObjectTo("Tile", sf.createSaveElement("Entity",attr));
        formatLocation(sf, e);

    }

    public static void parseEntity(Map map, Element el) {

    }

    public static void formatCharacter(Character e, String parent) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("Occupation", e.getOccupation().getClass().getSimpleName()));
        attr.add(sf.saveAttr("Height", e.getHeight()));
        attr.add(sf.saveAttr("Color", e.getColor().ordinal()));
        attr.add(sf.saveAttr("AvailableSkillPts", e.getAvailablePoints()));
        sf.appendObjectTo(parent, sf.createSaveElement("Character",attr));
        formatLocation(sf, e);
    }

    public static Character parseCharacter(Map map, Element el) {
        Character c =  new Character(map,
                parseDirection(sf.getElemenetById(el, "Direction", 0)),
                parseOccupation(sf.getStrAttr(el, "Occupation"), sf.getElementsById(el, "Skill")),
                StorageXMLProcessor.parseItemStorage(sf.getElemenetById(el, "ItemStorage", 0)),
                GameColor.values()[sf.getIntAttr(el, "Color")]
        );
        configureAbilityInventory(sf.getElementsById((Element)sf.getElementsById(el, "AbilityInventory").item(0), "Ability"), c);
        confgureAbilityEquipped(sf.getElementsById((Element)sf.getElementsById(el, "AbilityEquipped").item(0), "Ability"), c);
        parseStats(c, sf.getElemenetById(el, "Stats", 0));
        c.setAvailableSkillPoints(Integer.parseInt(sf.getStrAttr(el, "AvailableSkillPts")));
        map.add(c, parseLocation(sf.getElemenetById(el, "Location", 0)));
        return c;
    }

    public static void formatAvatar(Avatar avatar) {
        ArrayList<Attr> attr = new ArrayList<>();
        sf.appendObjectTo("Tile", sf.createSaveElement("Avatar", attr));
        formatCharacter(avatar.getCharacter(), "Avatar");
    }

    public static Avatar parseAvatar(Map map, Element el) {
        return new Avatar(parseCharacter(map, sf.getElemenetById(el, "Character", 0)), map);
    }

    public static void formatNPC(NPC npc, String parent) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("Occupation", npc.getOccupation().getClass().getSimpleName()));
        attr.add(sf.saveAttr("Height", npc.getHeight()));
        attr.add(sf.saveAttr("Color", npc.getColor().ordinal()));
        sf.appendObjectTo(parent, sf.createSaveElement("NPC",attr));
        formatLocation(sf, npc);
        formatInteraction(npc.getInteraction());

    }

    private static boolean isEnemy = false;
    public static NPC parseNPC(Map map, Element el) {
        NPC npc  = new NPC(map,
                parseDirection(sf.getElemenetById(el, "Direction", 0)),
                parseInteraction(sf.getElemenetById(el, "Interaction", 0)),
                parseOccupation(sf.getStrAttr(el, "Occupation"), sf.getElementsById(el, "Skill")),
                StorageXMLProcessor.parseItemStorage(sf.getElemenetById(el, "ItemStorage", 0)),
                GameColor.values()[sf.getIntAttr(el, "Color")] );

        configureAbilityInventory(sf.getElementsById((Element)sf.getElementsById(el, "AbilityInventory").item(0), "Ability"), npc);
        confgureAbilityEquipped(sf.getElementsById((Element)sf.getElementsById(el, "AbilityEquipped").item(0), "Ability"), npc);

        AIController controller = null;
        if (isEnemy) {
            EnemySearchingController esc = new EnemySearchingController(npc,map,2);
            EnemyActionController eac = new EnemyActionController(npc,map);
            controller = new AIController(esc,eac);
            npc.setController(controller);
        }

        map.add(npc, parseLocation(sf.getElemenetById(el, "Location", 0)));

        if (isEnemy) {
            AITime.getInstance().registerController(controller);
        }

        return npc;
    }

    public static void formatStats(Stats stats) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("lives", stats.getLives()));
        attr.add(sf.saveAttr("level", stats.getLevel()));
        attr.add(sf.saveAttr("strength", stats.getStrength()));
        attr.add(sf.saveAttr("agility", stats.getAgility()));
        attr.add(sf.saveAttr("intellect", stats.getIntellect()));
        attr.add(sf.saveAttr("hardiness", stats.getHardiness()));
        attr.add(sf.saveAttr("movement", stats.getMovement()));

        sf.appendObjectToMostRecent(sf.createSaveElement("Stats",attr));
    }

    public static void parseStats(Entity e, Element el) {
        e.getStats().initStats(e,
                sf.getIntAttr(el, "strength"),
                sf.getIntAttr(el, "agility"),
                sf.getIntAttr(el, "intellect"),
                sf.getIntAttr(el, "hardiness"),
                sf.getIntAttr(el, "movement"),
                sf.getIntAttr(el, "lives"),
                sf.getIntAttr(el, "level")
        );
    }

    public static void formatLocation(SaveFile save, Entity e) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(save.saveAttr("r", e.getLocation().getR()));
        attr.add(save.saveAttr("s", e.getLocation().getS()));
        attr.add(save.saveAttr("z", e.getLocation().getZ()));
        save.appendObjectToMostRecent(save.createSaveElement("Location",attr));
    }

    public static Location parseLocation(Element el){
        return new Location(sf.getIntAttr(el,"r"),sf.getIntAttr(el, "s"), sf.getIntAttr(el, "z"));
    }

    public static void formatDirection(Direction direction) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("enum", direction.ordinal()));
        sf.appendObjectToMostRecent(sf.createSaveElement("Direction",attr));

    }

    public static Direction parseDirection(Element el) {
        return Direction.values()[sf.getIntAttr(el, "enum")];
    }

    public static Occupation parseOccupation(String o, NodeList skills) {
        Occupation occupation;
        switch(o){
            case "Smasher":
                occupation =  new Smasher();
                break;
            case "Summoner":
                occupation = new Summoner();
                break;
            case "Sneak":
                occupation =  new Sneak();
                break;
            case "Pet":
                occupation = new Pet();
                break;
            case "Friendly":
                occupation = new Friendly();
                break;
            case "Enemy":
                occupation = new Enemy();
                isEnemy = true;
                break;
            default:
                occupation =  new Smasher();
        }

        return parseSkills(skills, occupation);
    }

    private static Occupation parseSkills(NodeList skills, Occupation occupation) {
        return OccupationSkillXMLProcessor.configureOccuationSkills(skills, occupation);
    }

    public static void formatInteraction(InteractionStrategy i) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("type", i.getClass().getSimpleName()));
        Element interactionSaveElement = sf.createSaveElement("Interaction",attr);

        if(i instanceof DialogInteractionStrategy){
            formatDialog(interactionSaveElement, ((DialogInteractionStrategy) i).getIterator());
        }else if(i instanceof QuestDialogInteractionStrategy) {
            Element startDialog = sf.createSaveElement("startDialog", new ArrayList<>());
            interactionSaveElement.appendChild(startDialog);
            formatDialog(startDialog, ((QuestDialogInteractionStrategy) i).getStartIterator());
            ArrayList<Attr> attr2 = new ArrayList<>();
            Element endDialog = sf.createSaveElement("endDialog", new ArrayList<>());
            interactionSaveElement.appendChild(endDialog);
            formatDialog(endDialog, ((QuestDialogInteractionStrategy) i).getEndIterator());

        }
        sf.appendObjectToMostRecent(interactionSaveElement);
    }

    public static InteractionStrategy parseInteraction(Element el) {
        if(el.getAttribute("type").equals("TradeInteractionStrategy")){
            return new TradeInteractionStrategy();
        }else if(el.getAttribute("type").equals("DialogInteractionStrategy")){
            return new DialogInteractionStrategy(parseDialog(el));
        }else {
            return new NoInteractionStrategy();
        }
    }

    public static void formatDialog(Element el, Iterator i) {
        while(i.hasNext()){
            Element dialogElement = sf.createSaveElement("Dialog",new ArrayList<>());
            sf.appendTextNode(dialogElement, (String)i.next());
            el.appendChild(dialogElement);
        }
    }

    public static ArrayList<String> parseDialog(Element el) {
        ArrayList<String> dialog = new ArrayList<>();
        NodeList dialogNode = sf.getElementsById(el, "Dialog");

        for(int i = 0; i < dialogNode.getLength(); ++i){
            dialog.add(((Element)dialogNode.item(i)).getTextContent());
        }

        return dialog;
    }

    public static void formatAbilities(Ability ability, String parent) {
        ArrayList<Attr> attr = new ArrayList<>();

        attr.add(sf.saveAttr("name", ability.getName()));

        sf.appendObjectTo(parent, sf.createSaveElement("Ability",attr));
    }

    public static void formatAbilityInventoryContainer(String parent) {
        sf.appendObjectTo(parent,sf.createSaveElement("AbilityInventory", null));
    }

    public static void formatAbilityEquipmentContainer(String parent) {
        sf.appendObjectTo(parent,sf.createSaveElement("AbilityEquipped", null));
    }

    public static void configureAbilityInventory(NodeList ele, Character character) {
        for (int i = 0; i < ele.getLength(); i++) {
            Element e = (Element) ele.item(i);

            Ability ability = AbilityMap.getInstance().getAbility(sf.getStrAttr(e, "name"), character);

            character.addAbility(ability);
        }
    }

    public static void confgureAbilityEquipped(NodeList ele, Character character) {
        for (int i = 0; i < ele.getLength(); i++) {
            Element e = (Element) ele.item(i);

            Ability ability = AbilityMap.getInstance().getAbility(sf.getStrAttr(e, "name"), character);

            character.addAbility(ability);
            character.equipAbility(ability);
        }
    }
}
