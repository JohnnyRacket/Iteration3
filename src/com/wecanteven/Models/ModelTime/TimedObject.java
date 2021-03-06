package com.wecanteven.Models.ModelTime;

/**
 * Created by John on 4/1/2016.
 */
public class TimedObject {

    private Alertable alertable;
    private int ticks;

    public TimedObject(Alertable alertable, int ticks){
        this.ticks = ticks;
        this.alertable = alertable;
    }

    public boolean decrement(){
        --ticks;
        if(ticks <= 0){
            //
            this.alertable.alert();
            return true;
        }else{
            return false;
        }
    }
}
