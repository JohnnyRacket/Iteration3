package com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids;

import com.sun.org.apache.bcel.internal.generic.BIPUSH;
import com.wecanteven.MenuView.Drawable;
import com.wecanteven.MenuView.DrawableContainers.Decorators.HorizontalCenterContainer;
import com.wecanteven.MenuView.DrawableContainers.Decorators.TitleBarDecorator;
import com.wecanteven.MenuView.DrawableContainers.Decorators.VerticalCenterContainer;
import com.wecanteven.MenuView.DrawableContainers.LayoutComposites.ColumnatedCompositeContainer;
import com.wecanteven.MenuView.SwappableView;
import com.wecanteven.UtilityClasses.Config;

import java.awt.*;

/**
 * Created by Joshua Kegley on 4/14/2016.
 */
public class BiNavigatableGridWithCenterTitle extends Drawable {

    private NavigatableGrid navigatableGrid1;
    private NavigatableGrid navigatableGrid2;
    private String title;
    private String sub1;
    private String sub2;
    private SwappableView swapView;

    public BiNavigatableGridWithCenterTitle(NavigatableGrid navigatableGrid1, NavigatableGrid navigatableGrid2, String title, String sub1, String sub2, Color bgColor, Color inactiveColor, Color activeColor) {
        this.navigatableGrid1 = navigatableGrid1;
        this.navigatableGrid2 = navigatableGrid2;
        this.title = title;
        this.sub1 = sub1;
        this.sub2 = sub2;
        this.swapView = new SwappableView();
    }

    public SwappableView createView() {
        this.swapView = new SwappableView();
        //add decorators to center the menu
        ColumnatedCompositeContainer columns  = new ColumnatedCompositeContainer();
        columns.setHeight(400);
        columns.setWidth(700);


        VerticalCenterContainer viewTitle =
                new VerticalCenterContainer(
                        new HorizontalCenterContainer(
                                new TitleBarDecorator(columns, title)
                        )
                );

        VerticalCenterContainer npcTradeTitle = new VerticalCenterContainer(
                        new HorizontalCenterContainer(
                                new TitleBarDecorator(
                                        navigatableGrid1,
                                        sub1
                                )
                        )
                );

        VerticalCenterContainer playerTradeTitle =
                new VerticalCenterContainer(
                        new HorizontalCenterContainer(
                                new TitleBarDecorator(
                                        navigatableGrid2, sub2
                                )
                        )
                );


        columns.addDrawable(playerTradeTitle);



        swapView.addDrawable(viewTitle);

        swapView.addNavigatable(navigatableGrid1);
        swapView.addNavigatable(navigatableGrid2);

        return null;
    }


    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {

    }
}
