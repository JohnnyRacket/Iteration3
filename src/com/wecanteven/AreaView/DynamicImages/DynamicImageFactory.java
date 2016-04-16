package com.wecanteven.AreaView.DynamicImages;


import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;

/**
 * Created by Alex on 2/21/2016.
 */
public class DynamicImageFactory {
    private static DynamicImageFactory ourInstance = new DynamicImageFactory();
    private static final String PATH = "resources/";
    public static DynamicImageFactory getInstance() {
        return ourInstance;
    }

    private final String NULL_PATH = "Null/null.xml";

    private DynamicImageFactory() {
    }

    public SimpleDynamicImage loadDynamicImage(String xmlPath) {

        Element element = getRootElement(xmlPath);
        switch(element.getNodeName()) {
            case "single_frame_animation":
                return this.createSingleFrameAnimation(element);
            default:

                return loadDynamicImage(NULL_PATH);

        }


    }

    public StartableDynamicImage loadActiveDynamicImage(String xmlPath) {
        Element element = getRootElement(xmlPath);
        return this.createStartableAnimation(element);
    }

    private Element getRootElement(String xmlPath) {
        try {
            File imageSpec = new File(PATH + xmlPath);
            DocumentBuilderFactory dbFactory = new DocumentBuilderFactoryImpl();
            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(imageSpec);
            doc.getDocumentElement().normalize();

            return doc.getDocumentElement();


        } catch (Exception e ) {

            e.printStackTrace();
            return getRootElement(NULL_PATH);
        }
    }

    private SimpleDynamicImage createSingleFrameAnimation(Element element) {
        String rootPath = element.getElementsByTagName("rootPath").item(0).getTextContent();

        String image = PATH + rootPath + element.getElementsByTagName("fileName").item(0).getTextContent();

        return new ConstantDynamicImage(
                -Integer.parseInt(element.getElementsByTagName("x").item(0).getTextContent()),
                -Integer.parseInt(element.getElementsByTagName("y").item(0).getTextContent()),
                image);

    }

    private Image createImage(String filePath) {
        return (new ImageIcon(PATH + filePath)).getImage();
    }

    private StartableDynamicImage createStartableAnimation(Element element) {
        String rootPath = element.getElementsByTagName("rootPath").item(0).getTextContent();

        NodeList nList = element.getElementsByTagName("fileName");
        String[] activeFilePaths = new String[nList.getLength()];
        for (int i=0; i<nList.getLength(); i++) {
            activeFilePaths[i] = PATH +rootPath + nList.item(i).getTextContent();
        }
        return new StartableDynamicImage(
                -Integer.parseInt(element.getElementsByTagName("x").item(0).getTextContent()),
                -Integer.parseInt(element.getElementsByTagName("y").item(0).getTextContent()),
                activeFilePaths);
    }


}