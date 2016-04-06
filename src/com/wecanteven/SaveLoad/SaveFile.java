package com.wecanteven.SaveLoad;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public class SaveFile {

    private static final String PATH = "resources/Saves/";

    private String fileName;
    private Document doc;
    private Element root;
    private File file;


    public SaveFile(String fileName ) {
        this.doc = createEmptyDOM();
        this.fileName = fileName;
        initializeDocumentHeaderFromFileName();
    }

    public SaveFile(File file) {
        this.file = file;
        this.doc = createDOMFromFile();
        initializeFileNameFromDocumentHeader(file);
    }



    //Initializes docu
    public void initializeDocumentHeaderFromFileName() {
        this.root = doc.createElement("SaveFile");
        Attr a = doc.createAttribute("FileName");
        a.setValue(fileName);
        root.setAttributeNode(a);
        doc.appendChild(root);
    }

    //Initializes a
    public void initializeFileNameFromDocumentHeader(File file) {
        doc.normalizeDocument();
        //--Setting the SaveFiles Root and FileName--//
        root = doc.getDocumentElement();
        fileName = root.getAttribute("FileName");
        //-------------------------------------------//
        System.out.println("Loading Save from File: " + fileName);


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

    //Creating the DOM object to edit
    public Document createEmptyDOM() {
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

    public Document createDOMFromFile() {
        DocumentBuilder builder;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return builder.parse(file);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserError");
        } catch (SAXException e) {
            System.out.println("SAXException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return null;
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

        file = getFileFromRes(fileName);

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

    public String getStrAttr(Element el, String key) {
        return el.getAttribute(key);
    }

    public int getIntAttr(Element el, String key) {
        return Integer.parseInt(el.getAttribute(key));
    }

    public Element getElemenetById(String id, int i) {
        NodeList nodes = root.getElementsByTagName(id);
        if(nodes.getLength() >= i) {
            Element e = (Element) nodes.item(i);
            return e;
        }
        return null;
    }

    public Element getElemenetById(Element el, String id, int i) {
        NodeList nodes = el.getElementsByTagName(id);

        if(nodes.getLength() >= i) {
            Element e = (Element) nodes.item(i);
            return e;
        }
        return null;
    }

    public Document getDoc() {
        return doc;
    }

    public Element getRoot() {
        return root;
    }


    public static void printDocument(Document doc) {
        try {
            printDocument(doc, System.out );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void printDocument(Document doc, OutputStream out) throws IOException, TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        transformer.transform(new DOMSource(doc),
                new StreamResult(new OutputStreamWriter(out, "UTF-8")));
    }
}
