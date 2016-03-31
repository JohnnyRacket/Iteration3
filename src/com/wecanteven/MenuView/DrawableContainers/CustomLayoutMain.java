package com.wecanteven.MenuView.DrawableContainers;

import com.wecanteven.MenuView.DrawableContainers.Decorators.HorizontalCenterContainer;
import com.wecanteven.MenuView.DrawableContainers.Decorators.VerticalCenterContainer;
import com.wecanteven.MenuView.DrawableContainers.LayoutComposites.ColumnatedCompositeContainer;
import com.wecanteven.MenuView.DrawableContainers.LayoutComposites.RowedCompositeContainer;
import com.wecanteven.MenuView.ProgressBars.AnimatedChangeProgressBar;
import com.wecanteven.MenuView.ScrollableMenus.ScrollableMenu;
import com.wecanteven.MenuView.ScrollableMenus.ScrollableMenuItem;
import com.wecanteven.MenuView.ScrollableMenus.NavigatableList;

import javax.swing.*;

/**
 * Created by John on 3/31/2016.
 */
public class CustomLayoutMain {
    public static void main(String[] args){
        JFrame window = new JFrame("Test Window");

        ScrollableMenu testMenu = new ScrollableMenu(300,300);
        ScrollableMenu testMenu2 = new ScrollableMenu(300,300);
        testMenu2.setList(new NavigatableList());
        NavigatableList testList = new NavigatableList();
        testList.addItem(new ScrollableMenuItem("test1", null));
        testMenu.setList(testList);
        VerticalCenterContainer container = new VerticalCenterContainer(testMenu);
        HorizontalCenterContainer container2 = new HorizontalCenterContainer(container);

        RowedCompositeContainer rows = new RowedCompositeContainer();
        rows.setHeight(300);
        rows.setWidth(480);

        ColumnatedCompositeContainer columns = new ColumnatedCompositeContainer();


        columns.addDrawable(container2);
        columns.addDrawable(container2);
        columns.addDrawable(container2);
        columns.setWidth(480);
        columns.setHeight(400);
        HorizontalCenterContainer testing =  new HorizontalCenterContainer(columns);
        VerticalCenterContainer testing2 = new VerticalCenterContainer(testing);

        rows.addDrawable(testing2);
        rows.addDrawable(null);
        rows.addDrawable(testing2);

        VerticalCenterContainer centerRows = new VerticalCenterContainer(rows);
        HorizontalCenterContainer centerRows2 = new HorizontalCenterContainer(centerRows);


        AnimatedChangeProgressBar progressBar = new AnimatedChangeProgressBar(300,50);
        progressBar.setY(50);
        progressBar.setBorderWidth(10);
        HorizontalCenterContainer barCenterer = new HorizontalCenterContainer(progressBar);




        //make adapter for swing to drawable
        SwingToDrawableAdapter adapter = new SwingToDrawableAdapter();
        //make viewManager
        ViewManager manager = new ViewManager();
        //set it to the adapter
        adapter.setAdaptee(manager);
        //make a swappableView
        SwappableView demoView = new SwappableView();
        //build up demo view with some drawables
        demoView.addDrawable(centerRows2);
        demoView.addDrawable(barCenterer);

        //add the demo view to the view manager
        manager.addView(demoView);


        //register the adapter to the jframe
        window.add(adapter);
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.repaint();

        try{
            Thread.sleep(2000);
        }catch (Exception e){}

        //the view manager is a stack so newer things will come up, as an example we can make a new view
        SwappableView newView = new SwappableView();
        newView.addDrawable(new VerticalCenterContainer( new AnimatedChangeProgressBar(500,200)));
        manager.addView(newView);
        window.repaint();
        try{
            Thread.sleep(2000);
        }catch (Exception e){}

        //then just pop the view off when youre done with it
        manager.popView();
        window.repaint();

    }
}
