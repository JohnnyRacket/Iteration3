package com.wecanteven.AreaView;

import com.wecanteven.ViewEngine;

/**
 * Created by alexs on 3/29/2016.
 */
public class AVDriver {
    public static void main(String ... args) {
        ViewEngine viewEngine = new ViewEngine();
       // viewEngine.registerView(new AreaView());
        viewEngine.start();
    }
}
