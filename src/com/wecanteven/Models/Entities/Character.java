package com.wecanteven.Models.Entities;

import com.wecanteven.Models.Abilities.Ability;
import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Factories.AbilityFactories.AbilityFactory;
import com.wecanteven.Models.Items.Takeable.*;
import com.wecanteven.Models.Items.Takeable.Equipable.*;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.Occupation.Occupation;
import com.wecanteven.Models.Occupation.Skill;
import com.wecanteven.Models.Occupation.Smasher;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Models.Storage.AbilityStorage.AbilityStorage;
import com.wecanteven.Models.Storage.ItemStorage.ItemStorage;
import com.wecanteven.Observers.Actionable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.GameColor;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.UtilityClasses.Sound;
import com.wecanteven.Visitors.EntityVisitor;


/**
 * Created by Brandon on 3/31/2016.
 */
public class Character extends Entity implements Actionable {
    private Occupation occupation;
    private ItemStorage itemStorage;
    private AbilityStorage abilityStorage;
    private int windUpTicks = 0, coolDownTicks = 0;

    private int availableSkillPoints = 0;

    public Character(ActionHandler actionHandler, Direction direction, GameColor color) {
        super(actionHandler, direction, color);
        occupation = new Smasher();
        this.itemStorage = new ItemStorage(this, 5);
        this.abilityStorage = new AbilityStorage();
        windUpTicks = 0;
        coolDownTicks = 0;
    }

    public Character(ActionHandler actionHandler, Direction direction, Occupation occupation, GameColor color) {
        super(actionHandler, direction, color);
        this.occupation = occupation;
        setStats(new Stats(this));
        this.itemStorage = new ItemStorage(this, 5);
        this.abilityStorage = new AbilityStorage();
        windUpTicks = 0;
        coolDownTicks = 0;
    }

    public Character(ActionHandler actionHandler, Direction direction, Occupation occupation, Stats stats, GameColor color) {
        super(actionHandler, direction, color);
        this.occupation = occupation;
        setStats(stats);
        this.itemStorage = new ItemStorage(this, 5);
        this.abilityStorage = new AbilityStorage();
        windUpTicks = 0;
        coolDownTicks = 0;
    }

    public Character(ActionHandler actionHandler, Direction direction, Occupation occupation, ItemStorage itemStorage, AbilityStorage abilityStorage, GameColor color) {
        super(actionHandler, direction, color);
        this.occupation = occupation;
        this.itemStorage = itemStorage;
        getItemStorage().setOwner(this);
        this.abilityStorage = abilityStorage;
        windUpTicks = 0;
        coolDownTicks = 0;
    }

    public void attack(Direction dir) {
        if(!isActive()){
            this.setDirection(dir);
            AbilityFactory factory = new AbilityFactory();
            Ability attack = factory.vendPickPocket(this);
            attack.cast();
        }
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

    public void mount(Mount mount) {
        System.out.println("character trying to mount");
    }

    /**
     *
     * Abilities
     *
     * */

    /**
     * Equip/Unequip an ability
     * */
    public void equipAbility(Ability ability) {
        abilityStorage.equipAbility(ability);
    }

    public void equipAbility(Ability ability, int slot) {
        abilityStorage.equipAbility(ability, slot);
    }

    public void unequipAbility(Ability ability) {
        abilityStorage.unequipAbility(ability);
    }

    public void unequipAbility(int slot) { abilityStorage.unequipAbility(slot); }

    /**
     * Add an ability
     * */

    public void addAbility(Ability ability) {
        abilityStorage.storeAbility(ability);
    }

    public void removeAbility(Ability ability) {
        abilityStorage.removeAbility(ability);
    }

    /**
     * Use an ability
     * */

    public void useAbility(Ability ability) {
        abilityStorage.useAbility(ability);
    }

    public void useAbility(int index) {
        abilityStorage.useAbility(index);
    }

    public AbilityStorage getAbilityStorage() {
        return this.abilityStorage;
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
        notifyObservers();
    }
    @Override
    protected void tickTicks(){
        if(calculateActiveStatus()){
            ModelTime.getInstance().registerAlertable(() -> {
                System.out.println("Stuck in an endless loop");
                System.out.println("Moving ticks "+getMovingTicks());
                System.out.println("WingUp ticks "+getWindUpTicks());
                System.out.println("CoolDown ticks "+getCoolDownTicks());
                deIncrementMovingTick();
                deIncrementWindUpTick();
                if(getWindUpTicks()==0){
                    deIncrementCoolDownTicks();
                }
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
    public boolean calculateActiveStatus(){
        if(getMovingTicks() <= 0 && getWindUpTicks() <= 0 && getCoolDownTicks() <= 0 && getTurningTicks() <= 0){
            setIsActive(false);
            System.out.println("The entity is no longer active");
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