package com.wecanteven.AreaView.DynamicImages;


import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Alex on 2/21/2016.
 */
public class DynamicImageFactory {
    private static DynamicImageFactory ourInstance = new DynamicImageFactory();
    private static final String PATH = "resources/";
    public static DynamicImageFactory getInstance() {
        return ourInstance;
    }

    private DynamicImageFactory() {
    }

    public DynamicImage loadDynamicImage(String xmlPath) {
        try {
            File imageSpec = new File(PATH + xmlPath);
            DocumentBuilderFactory dbFactory = new DocumentBuilderFactoryImpl();
            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(imageSpec);
            doc.getDocumentElement().normalize();
            switch(doc.getDocumentElement().getNodeName()) {
                case "single_frame_animation":
                    return this.createSingleFrameAnimation(doc.getDocumentElement());
                default:
                    System.out.println("Could not find dynamic image for: " + doc.getDocumentElement().getNodeName());

            }

        } catch (Exception e ) {
            System.out.println("WUT");

            e.printStackTrace();
        }

        return null;
    }

    private DynamicImage createSingleFrameAnimation(Element root) {
        Element element = root;

        String rootPath = element.getElementsByTagName("rootPath").item(0).getTextContent();

        Image image = (new ImageIcon(PATH + rootPath + element.getElementsByTagName("fileName").item(0).getTextContent())).getImage();


        return new ConstantDynamicImage(
                Integer.parseInt(element.getElementsByTagName("x").item(0).getTextContent()),
                Integer.parseInt(element.getElementsByTagName("y").item(0).getTextContent()),
                image);
    }


}