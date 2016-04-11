package com.wecanteven.Models.Items.Takeable;

/**
 * Created by Joshua Kegley on 4/7/2016.
 */
public class MoneyItem extends TakeableItem {
    private int value;
    public MoneyItem(int value) {
        super("Money", value);
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
