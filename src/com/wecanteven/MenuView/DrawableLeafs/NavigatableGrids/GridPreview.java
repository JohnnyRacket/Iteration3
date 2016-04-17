package com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids;

import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.SelectableItem;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.SelectableMenuItemCommand;
import com.wecanteven.UtilityClasses.GameColor;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joshua Kegley on 4/17/2016.
 */
public class GridPreview extends SelectableItem {

    private int padding = 16;

    private Image gray = new ImageIcon("resources/Entities/Beans/Gray.png").getImage();
    private Image grayFeet = new ImageIcon("resources/Feet/Gray.png").getImage();


    private Image yellow = new ImageIcon("resources/Entities/Beans/Yellow.png").getImage();
    private Image yellowFeet = new ImageIcon("resources/Feet/Yellow.png").getImage();

    private Image green = new ImageIcon("resources/Entities/Beans/Green.png").getImage();
    private Image greenFeet = new ImageIcon("resources/Feet/Green.png").getImage();



    private Image pink = new ImageIcon("resources/Entities/Beans/Pink.png").getImage();
    private Image pinkFeet = new ImageIcon("resources/Feet/Pink.png").getImage();

    private Image blue = new ImageIcon("resources/Entities/Beans/Blue.png").getImage();
    private Image blueFeet = new ImageIcon("resources/Feet/Blue.png").getImage();

    private Image connery = new ImageIcon("resources/Face/Connery/South.png").getImage();
    private Image testFace = new ImageIcon("resources/Face/TestFace/South.png").getImage();

    private Image selectedBody;
    private Image selectedFace;


    public GridPreview(SelectableMenuItemCommand command){
        this.setCommand(command);
        selectedFace = null;
        selectedBody = gray;

        //TODO use name to derive the img location, ill look at the view to see how this is done
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        g2d.setColor(Color.WHITE);

        int imageSize = Math.min(windowHeight - 20 - padding,windowWidth - padding);

        g2d.drawImage(this.selectedBody, x + windowWidth/2 - (selectedBody.getWidth(null)*5)/2, y + (selectedBody.getHeight(null)*5)/9 - 10, selectedBody.getWidth(null)*5, selectedBody.getHeight(null)*5, null);
        if(this.selectedFace != null){
            g2d.drawImage(this.selectedFace, x + windowWidth/2 - imageSize/2, y + imageSize/4 - 10,imageSize, imageSize, null);
        }

    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public void setColor(GameColor color) {
        if(color == GameColor.GREEN){
            selectedBody = green;
        }else if(color == GameColor.PINK){
            selectedBody = pink;
        }else if(color == GameColor.BLUE){
            selectedBody = blue;
        }else if(color == GameColor.YELLOW) {
            selectedBody = yellow;
        }else {
            selectedBody = gray;
        }
    }
    public void setFace(String face) {
        if(face.equals("Connery")) {
            this.selectedFace = connery;
        }else if(face.equals("Test Face")){
            this.selectedFace = testFace;
        }
    }
}
