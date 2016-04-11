package com.wecanteven.MenuView.DrawableLeafs.HUDview;

import com.wecanteven.MenuView.Drawable;
import com.wecanteven.MenuView.DrawableLeafs.ProgressBars.AnimatedChangeProgressBar;
import com.wecanteven.MenuView.DrawableLeafs.ProgressBars.CircularHealthBar;
import com.wecanteven.Models.Stats.*;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Config;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by John on 4/7/2016.
 */
public class StatsHUD extends Drawable implements Observer {

    private float health, mana, exp, maxHealth, maxMana, maxExp;
    private Stats stats;

    private Color bgColor = Color.GRAY;
    private CircularHealthBar healthBar, manaBar;
    private  AnimatedChangeProgressBar expBar;
    private BufferedImage stoneTexture;
    private TexturePaint texture;
    private Color borderColor = Config.DARKGREY;

    public StatsHUD(Stats stats){
        this.stats = stats;
        healthBar = new CircularHealthBar(180,180);
        healthBar.setCurrentColor(new Color(40,250,80));
        healthBar.setDepletedColor(new Color(20,140,40));
        healthBar.setBorderSize(14);
        healthBar.setBorderColor(borderColor);
        //healthBar.setBorderWidth(8);
        manaBar = new CircularHealthBar(180,180);
        manaBar.setCurrentColor(new Color(10,120,255));
        manaBar.setDepletedColor(new Color(10,70,175));
        manaBar.setBorderSize(14);
        manaBar.setBorderColor(borderColor);

        expBar = new AnimatedChangeProgressBar(200,50);
        expBar.setCurrentColor(new Color(230,170,50));
        expBar.setDepletedColor(new Color(150,100,25));
        expBar.setBorderWidth(14);
        expBar.setBorderColor(borderColor);
//        try {

//            stoneTexture = ImageIO.read(new File("resources/textures/stone.png"));
//        }catch(Exception e){
//            System.out.println("failed to load stone texture");
//        }
//        texture = new TexturePaint(stoneTexture, new Rectangle(0, 0, 90, 60));

        update();
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        int xOffset = x + 20;
        int yOffset = y + 20;


        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        expBar.setWidth(windowWidth - 480);
        expBar.draw(g2d,240,0,windowWidth,windowHeight);


        g2d.setColor(borderColor);
        g2d.fillPolygon(new int[]{0,0,300},new int[]{0,250,0},3);

        g2d.fillPolygon(new int[]{windowWidth,windowWidth,windowWidth - 300},new int[]{0,250,0},3);


        //g2d.setPaint(texture);
        g2d.setColor(Config.MEDIUMGREY);
        g2d.fillPolygon(new int[]{0,0,290},new int[]{0,240,0},3);
        g2d.fillPolygon(new int[]{windowWidth,windowWidth,windowWidth - 290},new int[]{0,240,0},3);


        healthBar.draw(g2d,20,20,windowWidth,windowHeight);

        manaBar.draw(g2d,windowWidth - manaBar.getWidth() - 20,20,windowWidth,windowHeight);

        RenderingHints rhreset = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_DEFAULT);

        rhreset.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_DEFAULT);

        g2d.setRenderingHints(rhreset);

    }

    @Override
    public void update() {
        health = stats.getHealth();
        maxHealth = stats.getMaxHealth();
        healthBar.setPercent((int)((health/maxHealth)*100f));
        
        mana = stats.getMana();
        maxMana = stats.getMaxMana();
        manaBar.setPercent((int)((mana/maxMana)*100f));

        exp = stats.getExperience();
        maxExp = 100; //TODO fix this
        expBar.setCurrentProgress((int)exp);
        expBar.setMaxProgress(100);
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }
}
