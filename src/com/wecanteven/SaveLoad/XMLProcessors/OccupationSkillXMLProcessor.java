package com.wecanteven.SaveLoad.XMLProcessors;

import com.wecanteven.Models.Occupation.Occupation;
import com.wecanteven.Models.Occupation.Skill;
import com.wecanteven.UtilityClasses.Tuple;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by simonnea on 4/16/16.
 */
public class OccupationSkillXMLProcessor extends XMLProcessor {

    public static void formatSkillContainer(String parent) {
        sf.appendObjectTo(parent,sf.createSaveElement("Skills", null));
    }

    public static void formatSkill(Tuple<Skill, Integer> skillAmount) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("name", skillAmount.x.toString()));
        attr.add(sf.saveAttr("amount", skillAmount.y));
        sf.appendObjectTo("Skills", sf.createSaveElement("Skill",attr));
    }

    public static Occupation configureOccuationSkills(NodeList ele, Occupation occupation) {
        for (int i = 0; i < ele.getLength(); i++) {
            Element element = (Element)ele.item(i);
            Skill skill = Skill.fromString(sf.getStrAttr(element, "name"));
            int amount = Integer.parseInt(sf.getStrAttr(element, "amount"));

            if (amount > 0)
                occupation.addSkillPoints(skill, amount);
        }

        return occupation;
    }
}
