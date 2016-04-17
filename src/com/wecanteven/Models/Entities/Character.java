package com.wecanteven.Models.Entities;

import com.wecanteven.Models.Abilities.Ability;
import com.wecanteven.Models.Abilities.AbilityFactory;
import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Items.Takeable.*;
import com.wecanteven.Models.Items.Takeable.Equipable.*;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.Occupation.Occupation;
import com.wecanteven.Models.Occupation.Skill;
import com.wecanteven.Models.Occupation.Smasher;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Models.Storage.ItemStorage.ItemStorage;
import com.wecanteven.Observers.Actionable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.GameColor;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.EntityVisitor;


/**
 * Created by Brandon on 3/31/2016.
 */
public class Character extends Entity implements Actionable {
    private Occupation occupation;
    private ItemStorage itemStorage, abilityItemStorage;
    private int windUpTicks, coolDownTicks;
    private int windUpTicks = 0, coolDownTicks = 0;
    private SimpleVOFactory factory;

    private int availableSkillPoints = 0;

    public Character(ActionHandler actionHandler, Direction direction, GameColor color) {
        super(actionHandler, direction, color);
        occupation = new Smasher();
        this.itemStorage = new ItemStorage(this, 5);
        windUpTicks = 0;
        coolDownTicks = 0;
    }

    public Character(ActionHandler actionHandler, Direction direction, Occupation occupation, GameColor color) {
        super(actionHandler, direction, color);
        this.occupation = occupation;
        setStats(new Stats(this));
        this.itemStorage = new ItemStorage(this, 5);
        windUpTicks = 0;
        coolDownTicks = 0;
    }
    public Character(ActionHandler actionHandler, Direction direction, Occupation occupation, Stats stats, GameColor color) {
        super(actionHandler, direction, color);
        this.occupation = occupation;
        setStats(stats);
        this.itemStorage = new ItemStorage(this, 5);
        windUpTicks = 0;
        coolDownTicks = 0;
    }

    public Character(ActionHandler actionHandler, Direction direction, Occupation occupation, ItemStorage itemStorage, GameColor color) {
        super(actionHandler, direction, color);
        this.occupation = occupation;
        this.itemStorage = itemStorage;
        getItemStorage().setOwner(this);
        windUpTicks = 0;
        coolDownTicks = 0;
    }

    public void attack(Direction dir) {
        if(!isActive()){
            this.setDirection(dir);
            AbilityFactory factory = new AbilityFactory();
            Ability attack = factory.vendPunchAttack(this);
            attack.cast();
        }
    }

    public void useAbility(int index) {
    }

    /**
     * Equipment
     */
    public void equipItem(EquipableItem item) {
        itemStorage.equip(item);
    }

    public void unequipItem(EquipableItem item) {
        itemStorage.unequip(item);
    }

    /**
     * Inventory
     */
    public void removeFromInventory(TakeableItem item) {
        itemStorage.removeItem(item);
        drop(item);
    }

    public void drop(TakeableItem item) {
        if (getActionHandler().drop(item, this.getLocation().add(getDirection().getCoords))) {
            itemStorage.removeItem(item);
        }
    }

    public void pickup(TakeableItem item) {
        itemStorage.addItem(item);
        if(itemStorage.hasItem(item)){
            System.out.println("Item was added");
        }
    }

    public void interact() {
        Location destination = getLocation().add(getDirection().getCoords);
        //getActionHandler().interact(this, destination);
    }

    public void interact(Character character) {
        //Probably pointless in Character

    }

    public void interact(Avatar avatar) {
        Location destination = getLocation().add(getDirection().getCoords);
        getActionHandler().interact(avatar, destination);
    }

    private boolean equipAbility(String id) {
        return false;
    }

    private boolean unequipAbility(String id) {
        return false;
    }

    public void mount(Mount mount) {
        System.out.println("character trying to mount");
    }

    /**
     * Consumption
     */
    public boolean consume(UseableItem item) {
        if (itemStorage.hasItem(item)) {
            item.use(this);
            itemStorage.removeItem(item);
            return true;
        }
        return false;
    }

    @Override
    public StatsAddable getLevelUpStats() {
        return occupation.getStatsAddable();
    }

    public Occupation getOccupation() {
        return occupation;
    }

    @Override
    public void accept(EntityVisitor v) {
        v.visitCharacter(this);
    }

    public ItemStorage getItemStorage() {
        return itemStorage;
    }

    public boolean buy(int value) {
        return itemStorage.buy(value);
    }

    public void addMoney(int value) {
        itemStorage.addMoney(new MoneyItem(value));
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "Character: " + getOccupation();
    }


    public void updateWindUpTicks(int ticks){
        setWindUpTicks(ticks);
        calculateActiveStatus();
        tickTicks();
        notifyObservers();
    }
    public void updateCoolDownTicks(int ticks){
        setCoolDownTicks(ticks);
        calculateActiveStatus();
        tickTicks();
        notifyObservers();
    }
    @Override
    protected void tickTicks(){
        if(calculateActiveStatus()){
            ModelTime.getInstance().registerAlertable(() -> {
                deIncrementMovingTick();
                deIncrementWindUpTick();
                deIncrementCoolDownTicks();
                deIncrementTurningTick();
                tickTicks();
            }, 1);
        }
    }

    public void setWindUpTicks(int ticks){
        windUpTicks = ticks;
    }
    public int getWindUpTicks(){
        return windUpTicks;
    }
    private void deIncrementWindUpTick(){
        if(getWindUpTicks()>0)
            windUpTicks--;
    }

    public  void setCoolDownTicks(int ticks){
        coolDownTicks = ticks;
    }
    public int getCoolDownTicks(){
        return coolDownTicks;
    }
    private void deIncrementCoolDownTicks(){
        if(getCoolDownTicks()>0)
            coolDownTicks--;
    }

    @Override
    protected boolean calculateActiveStatus(){
        if(getMovingTicks() <= 0 && getWindUpTicks() <= 0 && getCoolDownTicks() <= 0 && getTurningTicks() <= 0){
            setIsActive(false);
            return false;
        }
        else{
            setIsActive(true);
            return true;
        }
    }

    /**
     * Skill Thingses
     * */

    private void allocateAvailablePoints(int points) {
        if (points > 0) {
            this.availableSkillPoints += points;
        }
    }
    private void decrementAvailablePoints(int points) {
        if (points > 0) {
            this.availableSkillPoints -= points;
        }
    }
    public int getAvailablePoints() {
        return this.availableSkillPoints;
    }
    public void setAvailableSkillPoints(int points) {
        if (points >= 0) {
            this.availableSkillPoints = points;
        }
    }
    public boolean allocateSkillPoint(Skill skill, int points) {
        if (points > 0 && points <= getAvailablePoints()) {
            try {
                occupation.addSkillPoints(skill, points);
                decrementAvailablePoints(points);
                return true;
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }
    public int getSkillPoints(Skill skill) {
        return getOccupation().getSkillPoints(skill);
    }

    @Override
    public void levelUp() {
        super.levelUp();

        allocateAvailablePoints(3);
    }
}