package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.constants.Names;
import com.epam.rd.java.basic.practice7.entity.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class ParserDOM {
    private static final DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
    private final String inputXMLFile;
    private Flowers flowers;

    public ParserDOM(String inputXMLFile) {
        this.inputXMLFile = inputXMLFile;
    }

    public void parse(boolean validate) throws ParserConfigurationException, IOException, SAXException {
        DBF.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        DBF.setNamespaceAware(true);
        if (validate) {
            DBF.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
            DBF.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }
        DocumentBuilder documentBuilder = DBF.newDocumentBuilder();
        documentBuilder.setErrorHandler(new DefaultHandler() {
            @Override
            public void error(SAXParseException e) throws SAXException {
                throw e;
            }
        });
        Document document = documentBuilder.parse(inputXMLFile);
        Element root = document.getDocumentElement();
        flowers = new Flowers();
        NodeList flowersNodes = root.getElementsByTagName(Names.XML_FLOWER);
        for (int i = 0; i < flowersNodes.getLength(); i++) {
            Flower flower = getFlower(flowersNodes.item(i));
            flowers.getFlowerList().add(flower);
        }
    }

    public Flowers getFlowers() {
        return flowers;
    }

    private static Flower getFlower(Node flowerNode) {
        Flower flower = new Flower();
        Element flowerElement = (Element) flowerNode;

        Node nameNode = flowerElement.getElementsByTagName(Names.XML_NAME).item(0);
        flower.setName(nameNode.getTextContent());

        Node soilNode = flowerElement.getElementsByTagName(Names.XML_SOIL).item(0);
        flower.setSoil(Soil.fromValue(soilNode.getTextContent()));

        Node originNode = flowerElement.getElementsByTagName(Names.XML_ORIGIN).item(0);
        flower.setOrigin(originNode.getTextContent());

        Node multiplyingNode = flowerElement.getElementsByTagName(Names.XML_MULTIPLYING).item(0);
        flower.setMultiplying(Multiplying.fromValue(multiplyingNode.getTextContent()));

        Node visualParametersNode = flowerElement.getElementsByTagName(Names.XML_VISUAL_PARAMETERS).item(0);
        VisualParameters visualParameters = getVisualParameters(visualParametersNode);
        flower.setVisualParameters(visualParameters);

        Node growingTipsNode = flowerElement.getElementsByTagName(Names.XML_GROWING_TIPS).item(0);
        GrowingTips growingTips = getGrowingTips(growingTipsNode);
        flower.setGrowingTips(growingTips);
        return flower;
    }

    private static VisualParameters getVisualParameters(Node visualParametersNode) {
        VisualParameters visualParameters = new VisualParameters();
        Element element = (Element) visualParametersNode;

        Node stemColourNode = element.getElementsByTagName(Names.XML_STEM_COLOUR).item(0);
        visualParameters.setStemColour(stemColourNode.getTextContent());

        Node leafColourNode = element.getElementsByTagName(Names.XML_LEAF_COLOUR).item(0);
        visualParameters.setLeafColour(leafColourNode.getTextContent());

        Node aveLenFlowerNode = element.getElementsByTagName(Names.XML_AVE_LEN_FLOWER).item(0);
        AveLenFlower aveLenFlower = new AveLenFlower();
        aveLenFlower.setMeasure(aveLenFlowerNode.getAttributes().item(0).getTextContent());
        aveLenFlower.setAmount(Integer.parseInt(aveLenFlowerNode.getTextContent()));
        visualParameters.setAveLenFlower(aveLenFlower);
        return visualParameters;
    }

    private static GrowingTips getGrowingTips(Node growingTipsNode) {
        GrowingTips growingTips = new GrowingTips();
        Element element = (Element) growingTipsNode;

        Node tempretureNode = element.getElementsByTagName(Names.XML_TEMPRETURE).item(0);
        String measure = tempretureNode.getAttributes().item(0).getTextContent();
        Tempreture tempreture = new Tempreture();
        tempreture.setValue(Integer.parseInt(tempretureNode.getTextContent()));
        tempreture.setMeasure(measure);
        growingTips.setTempreture(tempreture);

        Node lightingNode = element.getElementsByTagName(Names.XML_LIGHTING).item(0)
                .getAttributes().getNamedItem(Names.XML_LIGHT_REQUIRING);
        growingTips.setLighting(new Lighting(LightRequiring.fromValue(lightingNode.getTextContent())));

        Node wateringNode = element.getElementsByTagName(Names.XML_WATERING).item(0);
        Watering watering = new Watering();
        watering.setMeasure(wateringNode.getAttributes().item(0).getTextContent());
        watering.setQuantity(Integer.parseInt(wateringNode.getTextContent()));
        growingTips.setWatering(watering);
        return growingTips;
    }

    public static void saveToXML(Flowers flowers, String xmlFileName) throws TransformerException, ParserConfigurationException {
        saveToXML(getDocument(flowers), xmlFileName);
    }

    private static void saveToXML(Document document, String xmlFileName) throws TransformerException {
        StreamResult result = new StreamResult(new File(xmlFileName));

        final TransformerFactory tf = TransformerFactory.newInstance();
        tf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING,true);
        javax.xml.transform.Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");

        t.transform(new DOMSource(document), result);
    }

    private static Document getDocument(Flowers flowers) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING,true);
        dbf.setNamespaceAware(true);

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        Element flowersElement = document.createElement(Names.XML_FLOWERS);
        document.appendChild(flowersElement);

        for (Flower flower : flowers.getFlowerList()) {
            Element fElement = document.createElement(Names.XML_FLOWER);
            flowersElement.appendChild(fElement);

            Element fnElement = document.createElement(Names.XML_NAME);
            fnElement.setTextContent(flower.getName());
            fElement.appendChild(fnElement);

            Element fsElement = document.createElement(Names.XML_SOIL);
            fsElement.setTextContent(flower.getSoil().value());
            fElement.appendChild(fsElement);

            Element foElement = document.createElement(Names.XML_ORIGIN);
            foElement.setTextContent(flower.getOrigin());
            fElement.appendChild(foElement);

            Element vpElement = document.createElement(Names.XML_VISUAL_PARAMETERS);
            fElement.appendChild(vpElement);

            Element scElement = document.createElement(Names.XML_STEM_COLOUR);
            scElement.setTextContent(flower.getVisualParameters().getStemColour());
            vpElement.appendChild(scElement);

            Element lcElement = document.createElement(Names.XML_LEAF_COLOUR);
            lcElement.setTextContent(flower.getVisualParameters().getLeafColour());
            vpElement.appendChild(lcElement);

            Element alfElement = document.createElement(Names.XML_AVE_LEN_FLOWER);
            alfElement.setAttribute(Names.XML_MEASURE, flower.getVisualParameters().getAveLenFlower().getMeasure());
            alfElement.setTextContent(String.valueOf(flower.getVisualParameters().getAveLenFlower().getAmount()));
            vpElement.appendChild(alfElement);

            Element gtElement = document.createElement(Names.XML_GROWING_TIPS);
            fElement.appendChild(gtElement);

            Element tElement = document.createElement(Names.XML_TEMPRETURE);
            tElement.setAttribute(Names.XML_MEASURE, flower.getGrowingTips().getTempreture().getMeasure());
            tElement.setTextContent(String.valueOf(flower.getGrowingTips().getTempreture().getValue()));
            gtElement.appendChild(tElement);

            Element lElement = document.createElement(Names.XML_LIGHTING);
            lElement.setAttribute(Names.XML_LIGHT_REQUIRING, flower.getGrowingTips().getLighting().getLightRequiring().value());
            gtElement.appendChild(lElement);

            Element wElement = document.createElement(Names.XML_WATERING);
            wElement.setAttribute(Names.XML_MEASURE, flower.getGrowingTips().getWatering().getMeasure());
            wElement.setTextContent(String.valueOf(flower.getGrowingTips().getWatering().getQuantity()));
            gtElement.appendChild(wElement);

            Element mElement = document.createElement(Names.XML_MULTIPLYING);
            mElement.setTextContent(flower.getMultiplying().value());
            fElement.appendChild(mElement);
        }
        return document;
    }
}
