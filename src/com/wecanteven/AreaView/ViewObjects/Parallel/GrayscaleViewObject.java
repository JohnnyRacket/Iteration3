package com.wecanteven.AreaView.ViewObjects.Parallel;

import com.wecanteven.AreaView.DynamicImages.DynamicImage;
import com.wecanteven.AreaView.DynamicImages.GrayscaleDynamicImage;
import com.wecanteven.AreaView.DynamicImages.SimpleDynamicImage;
import com.wecanteven.AreaView.Position;

/**
 * Created by Alex on 4/14/2016.
 */
public class GrayscaleViewObject extends ParallelViewObject {
    public GrayscaleViewObject(Position p) {
        super(p);
    }

    @Override
    protected DynamicImage convert(SimpleDynamicImage image) {
        return new GrayscaleDynamicImage(image);
    }
}
