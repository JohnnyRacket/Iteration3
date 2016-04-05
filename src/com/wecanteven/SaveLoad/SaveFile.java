package com.wecanteven.SaveLoad;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public class SaveFile {

    private static final String PATH = "resources/Saves/";

    private String fileName;
    private Document doc;
    private Element root;
    public SaveFile(String fileName ) {
        this.doc = createDocumentFile();
        this.fileName = fileName;
        initialize();
    }

    public void initialize() {
        this.root = doc.createElement("SaveFile");
        Attr a = doc.createAttribute(fileName);
        a.setValue(fileName);
        root.setAttributeNode(a);
        doc.appendChild(root);
    }

    public Element createSaveElement(String elementName, ArrayList<Attr> attributes) {
        Element el = doc.createElement(elementName);
        for (Attr a:attributes) {
            el.setAttributeNode(a);
        }
        return el;
    }

    public void appendMap(Element map) {
        root.appendChild(map);
    }


    public void appendObjectTo(String parent, Element child) {
        NodeList nodes = root.getElementsByTagName(parent);
        Element pElement = (Element) nodes.item(nodes.getLength() - 1);
        pElement.appendChild(child);
    }

    public Document createDocumentFile() {
        Document doc;
        try
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();
        }
        catch (Exception e)
        {
            doc = null;
            System.out.println("Error converting file to Document");
            e.printStackTrace();
        }
        return doc;
    }

//Writes the Save File to an actual Save file.
    public void writeSaveFile() {
        TransformerFactory tFactory = TransformerFactory.newInstance();

        // Make the Transformer
        Transformer transformer = null;
        try {
            transformer = tFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }

        // Mark the document as a DOM (XML) source
        doc.normalizeDocument();
        DOMSource source = new DOMSource(doc);

        File file = getFileFromRes(fileName);

        // Say where we want the XML to go
        StreamResult result = new StreamResult(file);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,"yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        // Write the XML to file
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public File getFileFromRes(String fileName) {
        try {
            new FileOutputStream(PATH + fileName, false).close();
            return new File(PATH + fileName);
        } catch (IOException e) {
            System.out.println("Error creating/getting file");
            e.printStackTrace();
            return null;
        }
    }



    public Attr saveAttr(String attr, String value) {
        Attr a = doc.createAttribute(attr);
        a.setValue(value);
        return a;
    }

    public Attr saveAttr(String attr, int value) {
        Attr a = doc.createAttribute(attr);
        a.setValue(Integer.toString(value));
        return a;
    }


    public Document getDoc() {
        return doc;
    }

    public Element getRoot() {
        return root;
    }
}
