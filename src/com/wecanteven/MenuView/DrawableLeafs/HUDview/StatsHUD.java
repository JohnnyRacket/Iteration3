package com.wecanteven.MenuView.DrawableLeafs.HUDview;

import com.wecanteven.MenuView.Drawable;
import com.wecanteven.MenuView.DrawableLeafs.ProgressBars.AnimatedChangeProgressBar;
import com.wecanteven.MenuView.DrawableLeafs.ProgressBars.CircularHealthBar;
import com.wecanteven.MenuView.DrawableLeafs.ProgressBars.HexagonProgressBar;
import com.wecanteven.Models.Stats.*;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Config;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
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
        stats.attach(this);
        healthBar = new CircularHealthBar(180,180);
        healthBar.setCurrentColor(new Color(40,250,80));
        healthBar.setDepletedColor(new Color(20,140,40));
        healthBar.setBorderSize(0);
        healthBar.setBorderColor(Config.GOLD);
        //healthBar.setBorderWidth(8);
        manaBar = new CircularHealthBar(180,180);
        manaBar.setCurrentColor(new Color(10,120,255));
        manaBar.setDepletedColor(new Color(10,70,175));
        manaBar.setBorderSize(0);
        manaBar.setBorderColor(Config.GOLD);

        expBar = new AnimatedChangeProgressBar(200,50);
        expBar.setCurrentColor(new Color(230,170,50));
        expBar.setDepletedColor(new Color(150,100,25));
        expBar.setBorderWidth(0);
        expBar.setWidth(320);
        expBar.setBorderColor(Config.GOLD);
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
        this.setWidth(800);
//
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);
//
//        Arc2D healthBorder = new Arc2D.Double(0, 0, 200, 200, 0,360, Arc2D.OPEN);
//        Arc2D manaBorder = new Arc2D.Double( 100, 0, 200, 200, 0, 360, Arc2D.OPEN);
//
//        Color[] colors = {new Color(.2f,.2f,.2f,1f), new Color(.2f,.2f,.2f,0f)};
//        RadialGradientPaint p =
//                new RadialGradientPaint(100,100,200, new float[]{.3f,.5f}, colors);
//        g2d.setPaint(p);
//        g2d.fill(healthBorder);
//        RadialGradientPaint pp =
//                new RadialGradientPaint(200,100,200, new float[]{.3f,.5f}, colors);
//        g2d.setPaint(pp);
//        g2d.fill(manaBorder);
        //expBar.setWidth(windowWidth - 480);



//        g2d.setColor(Config.GOLD);
//        g2d.fillPolygon(new int[]{0,0,100,300},new int[]{0,170,170,0},4);
//
//        g2d.fillPolygon(new int[]{windowWidth,windowWidth,windowWidth - 100,windowWidth - 300},new int[]{0,170,170,0},4);
//
//
//        //g2d.setPaint(texture);
//        g2d.setColor(Config.DARKGOLD);
//        g2d.fillPolygon(new int[]{0,0,97,290},new int[]{0,163,163,0},4);
//        g2d.fillPolygon(new int[]{windowWidth,windowWidth,windowWidth - 97, windowWidth - 290},new int[]{0,163,163,0},4);


        expBar.draw(g2d,x+240,0,windowWidth,windowHeight);
//
//        healthBar.draw(g2d,10,10,windowWidth,windowHeight);
//
//        manaBar.draw(g2d,110,10,windowWidth,windowHeight);
//
        RenderingHints rhreset = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_DEFAULT);

        rhreset.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_DEFAULT);
//
//        Arc2D healthArc = new Arc2D.Double(10, 10, 180, 180, 0,360, Arc2D.OPEN);
//        Arc2D manaArc = new Arc2D.Double( 110, 10, 180, 180, 0, 360, Arc2D.OPEN);
//
//        Area areaTest = new Area(healthArc);
//        Area areaTest2 = new Area(manaArc);
//
//        areaTest.intersect(areaTest2);
//        g2d.setColor(new Color(141, 107, 30));
//        g2d.fill(areaTest);
//
//        exp = 50;
//        Rectangle2D rect = new Rectangle2D.Double(110,10+ (180f - (float)exp*(1.8f)),100,180);
//        Area areaTest3 = new Area(rect);
//        areaTest.intersect(areaTest3);
//
//        g2d.setColor(new Color(230, 176, 49));
//        g2d.fill(areaTest);
        System.out.println(x);
        HexagonProgressBar hex = new HexagonProgressBar(150,70);
        HexagonProgressBar hex2 = new HexagonProgressBar(150,70);
        hex.draw(g2d,x,y,windowWidth,windowHeight);
        hex2.draw(g2d,x + 500,y,windowWidth, windowHeight);



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
        System.out.println("The stats hud was updated");
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }
}
