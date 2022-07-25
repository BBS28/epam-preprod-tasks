package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.constants.Names;
import com.epam.rd.java.basic.practice7.entity.*;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class ParserSAX extends DefaultHandler {

    private static final SAXParserFactory SPF = SAXParserFactory.newInstance();
    private final String inputXMLFile;
    private Flowers flowers;
    private Flower flower;
    private GrowingTips growingTips;
    private VisualParameters visualParameters;
    private Tempreture tempreture;
    private AveLenFlower aveLenFlower;
    private Watering watering;
    private String currentElement;

    public ParserSAX(String inputXMLFile) {
        this.inputXMLFile = inputXMLFile;
    }

    public void parse(boolean validate) throws ParserConfigurationException, SAXException, IOException {

        SPF.setNamespaceAware(true);
        if (validate) {
            SPF.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
            SPF.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }

        SAXParser parser = SPF.newSAXParser();
        parser.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        parser.parse(inputXMLFile, this);
    }

    public Flowers getFlowers() {
        return flowers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = localName;
        String measure;
        if (Names.XML_FLOWERS.equals(currentElement)) {
            flowers = new Flowers();
            return;
        }
        if (Names.XML_FLOWER.equals(currentElement)) {
            flower = new Flower();
            return;
        }
        if (Names.XML_VISUAL_PARAMETERS.equals(currentElement)) {
            visualParameters = new VisualParameters();
            return;
        }
        if (Names.XML_GROWING_TIPS.equals(currentElement)) {
            growingTips = new GrowingTips();
        }
        if (Names.XML_LIGHTING.equals(qName)) {
            growingTips.setLighting(new Lighting(LightRequiring
                    .fromValue(attributes.getValue(Names.XML_LIGHT_REQUIRING))));
        }
        if (Names.XML_TEMPRETURE.equals(currentElement)) {
            measure = attributes.getValue(0);
            tempreture = new Tempreture();
            tempreture.setMeasure(measure);
        }
        if (Names.XML_AVE_LEN_FLOWER.equals(currentElement)) {
            measure = attributes.getValue(0);
            aveLenFlower = new AveLenFlower();
            aveLenFlower.setMeasure(measure);
        }
        if (Names.XML_WATERING.equals(currentElement)) {
            measure = attributes.getValue(0);
            watering = new Watering();
            watering.setMeasure(measure);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (Names.XML_FLOWER.equals(localName)) {
            flowers.getFlowerList().add(flower);
            return;
        }
        if (Names.XML_GROWING_TIPS.equals(localName)) {
            flower.setGrowingTips(growingTips);
            return;
        }
        if (Names.XML_VISUAL_PARAMETERS.equals(localName)) {
            flower.setVisualParameters(visualParameters);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String chars = new String(ch, start, length).trim();
        if (chars.isEmpty()) {
            return;
        }
        if (Names.XML_NAME.equals(currentElement)) {
            flower.setName(chars);
            return;
        }
        if (Names.XML_SOIL.equals(currentElement)) {
            flower.setSoil(Soil.fromValue(chars));
            return;
        }
        if (Names.XML_ORIGIN.equals(currentElement)) {
            flower.setOrigin(chars);
            return;
        }
        if (Names.XML_MULTIPLYING.equals(currentElement)) {
            flower.setMultiplying(Multiplying.fromValue(chars));
            return;
        }
        if (Names.XML_TEMPRETURE.equals(currentElement)) {
            tempreture.setValue(Integer.parseInt(chars));
            growingTips.setTempreture(tempreture);
            return;
        }

        if (Names.XML_WATERING.equals(currentElement)) {
            watering.setQuantity(Integer.parseInt(chars));
            growingTips.setWatering(watering);
            return;
        }
        if (Names.XML_STEM_COLOUR.equals(currentElement)) {
            visualParameters.setStemColour(chars);
            return;
        }
        if (Names.XML_LEAF_COLOUR.equals(currentElement)) {
            visualParameters.setLeafColour(chars);
            return;
        }
        if (Names.XML_AVE_LEN_FLOWER.equals(currentElement)) {
            aveLenFlower.setAmount(Integer.parseInt(chars));
            visualParameters.setAveLenFlower(aveLenFlower);
        }
    }

}
