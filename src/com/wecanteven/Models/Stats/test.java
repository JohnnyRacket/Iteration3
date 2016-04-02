package com.wecanteven.Models.Stats;
import com.wecanteven.Models.Entities.Character;
/**
 * Created by Brandon on 4/2/2016.
 */
public class test {
    public static void main(String[] args){
        Character avatar = new Character();
        System.out.println(avatar.getStats() + "\n\n\n");
        avatar.getStats().modifyStats(new StatsAddable(0,0,0,0,0,100,0,0,0));
        System.out.println(avatar.getStats());
    }
}
