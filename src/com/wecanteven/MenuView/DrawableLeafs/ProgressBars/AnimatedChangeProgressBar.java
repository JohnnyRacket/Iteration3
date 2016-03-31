package com.wecanteven.MenuView.DrawableLeafs.ProgressBars;

import com.wecanteven.MenuView.Drawable;

import java.awt.*;

/**
 * Created by John on 3/11/2016.
 */
public class AnimatedChangeProgressBar extends Drawable {

    float maxProgress = 1;
    float currentProgress = 0;

    int progressBarWidth;
    int progressBarHeight;
    int borderWidth;

    int xOffset = 0;
    int yOffset = 0;

    float oldCurrent = -1;

    Color currentColor = Color.GREEN;
    Color depletedColor = Color.RED;
    Color borderColor = Color.BLACK;

    public AnimatedChangeProgressBar(int width, int height){
        this.progressBarWidth = width;
        this.progressBarHeight = height;
        this.setBorderWidth(width/10);
        //this.setPreferredSize(new Dimension(width, height));
    }



    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        //paint rectangle to draw border
        g2d.setColor(borderColor);
        g2d.fillRect(x,y,progressBarWidth,progressBarHeight);
        //now add depleted color
        g2d.setColor(depletedColor);
        g2d.fillRect(x + borderWidth/2, y + borderWidth/2, progressBarWidth - borderWidth, progressBarHeight-borderWidth);
        //now paint current stuff

        //calculate % to show
        if(this.oldCurrent > this.currentProgress + maxProgress/100f){
            this.oldCurrent -= maxProgress/100f;
            g2d.setColor(currentColor.darker());
            int percentage = (int)((oldCurrent/maxProgress) * progressBarWidth);
            if(percentage >= borderWidth) {
                g2d.fillRect(x + borderWidth / 2, y + borderWidth / 2, percentage - borderWidth, progressBarHeight - borderWidth);
            }else{
                g2d.fillRect(x + borderWidth / 2, y + borderWidth / 2, 0, progressBarHeight - borderWidth);
            }
            g2d.setColor(currentColor);
            int curPercentage = (int)((currentProgress/maxProgress) * progressBarWidth);
            if(curPercentage >= borderWidth) {
                g2d.fillRect(x + borderWidth / 2, y + borderWidth / 2, curPercentage - borderWidth, progressBarHeight - borderWidth);
            }else{
                g2d.fillRect(x + borderWidth / 2, y + borderWidth / 2, 0, progressBarHeight - borderWidth);
            }
        }else if(this.oldCurrent < this.currentProgress - maxProgress/100f){
            this.oldCurrent += maxProgress/100f;
            g2d.setColor(currentColor.darker());
            int curPercentage = (int)((currentProgress/maxProgress) * progressBarWidth);
            System.out.println(curPercentage);
            if(curPercentage >= borderWidth) {
                g2d.fillRect(x + borderWidth / 2, y + borderWidth / 2, curPercentage - borderWidth, progressBarHeight - borderWidth);
            }else{
                g2d.fillRect(x + borderWidth / 2, y + borderWidth / 2, 0, progressBarHeight - borderWidth);
            }
            g2d.setColor(currentColor);
            int percentage = (int)((oldCurrent/maxProgress) * progressBarWidth);
            if(percentage >= borderWidth) {
                g2d.fillRect(x + borderWidth / 2, y + borderWidth / 2, percentage - borderWidth, progressBarHeight - borderWidth);
            }else{
                g2d.fillRect(x + borderWidth / 2, y + borderWidth / 2, 0, progressBarHeight - borderWidth);
            }
        }else{
            g2d.setColor(currentColor);
            int percentage = (int)((oldCurrent/maxProgress) * progressBarWidth);
            if(percentage >= borderWidth) {
                g2d.fillRect(x + borderWidth / 2, y + borderWidth / 2, percentage - borderWidth, progressBarHeight - borderWidth);
            }else{
                g2d.fillRect(x + borderWidth / 2, y + borderWidth / 2, 0, progressBarHeight - borderWidth);

            }
        }

    }


    //////////////////////////////////////////////getters and setters//////////////////////////////////

    public int getMaxProgress() {
        return (int)maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = (float)maxProgress;
    }

    public int getCurrentProgress() {
        return(int)currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        if(this.oldCurrent == -1){
            this.oldCurrent = (float) currentProgress;
            this.currentProgress = (float) currentProgress;
        }else {
            this.oldCurrent = this.currentProgress;
            this.currentProgress = (float) currentProgress;
        }
    }
    @Override
    public int getWidth() {
        return progressBarWidth;
    }

    @Override
    public void setWidth(int progressBarWidth) {
        this.progressBarWidth = progressBarWidth;
    }
    @Override
    public int getHeight() {
        return progressBarHeight;
    }
    @Override
    public void setHeight(int progressBarHeight) {
        this.progressBarHeight = progressBarHeight;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public Color getDepletedColor() {
        return depletedColor;
    }

    public void setDepletedColor(Color depletedColor) {
        this.depletedColor = depletedColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    @Override
    public int getX() {
        return this.xOffset;
    }

    @Override
    public int getY() {
        return this.yOffset;
    }

    @Override
    public void setX(int x) {
        this.xOffset = x;
    }

    @Override
    public void setY(int y) {
        this.yOffset = y;
    }
}
