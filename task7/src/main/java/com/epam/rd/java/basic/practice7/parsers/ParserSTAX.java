package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.constants.Names;
import com.epam.rd.java.basic.practice7.entity.*;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import javax.xml.transform.stream.StreamSource;
import java.util.Objects;

public class ParserSTAX {

    private static final XMLInputFactory XML_IF = XMLInputFactory.newInstance();
    private final String inputXMLFile;
    private Flowers flowers;
    private Flower flower;
    private AveLenFlower aveLenFlower;
    private Tempreture tempreture;
    private Watering watering;
    private GrowingTips growingTips;
    private VisualParameters visualParameters;

    public ParserSTAX(String inputXMLFile) {
        this.inputXMLFile = inputXMLFile;
    }

    public Flowers getFlowers() {
        return flowers;
    }

    public void parse() throws XMLStreamException {
        String currentElement = null;
        XML_IF.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        XML_IF.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
        XMLEventReader reader = XML_IF.createXMLEventReader(new StreamSource(inputXMLFile));
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
                continue;
            }
            if (event.isStartElement()) {
                currentElement = event.asStartElement().getName().getLocalPart();
                startElementMethod(currentElement);
            }
            if (event.isStartElement() && event.asStartElement().getAttributes().hasNext()) {
               eventWithAttributesProcess(event);
            }
            if (event.isCharacters()) {
                Characters characters = event.asCharacters();
                charsMethod(currentElement, characters);
            }
            if (event.isEndElement()) {
                eventAsEndElementProcess(event);
            }
        }
        reader.close();
    }

    private void eventWithAttributesProcess (XMLEvent event) {
        String data = event.asStartElement().getAttributes().next().toString();
        String currentElement = event.asStartElement().getName().getLocalPart();
        attributeMethod(currentElement, data);
    }

    private void eventAsEndElementProcess(XMLEvent event){
        String localName = event.asEndElement().getName().getLocalPart();
        if (Names.XML_FLOWER.equals(localName)) {
            flowers.getFlowerList().add(flower);
        }
    }

    private void startElementMethod(String currentElement) {
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
            Objects.requireNonNull(flower).setVisualParameters(visualParameters);
            return;
        }
        if (Names.XML_GROWING_TIPS.equals(currentElement)) {
            growingTips = new GrowingTips();
            Objects.requireNonNull(flower).setGrowingTips(growingTips);
        }
    }

    private void charsMethod(String currentElement, Characters characters) {
        if (Names.XML_NAME.equals(currentElement)) {
        Objects.requireNonNull(flower).setName(characters.getData());
        return;
        }
        if (Names.XML_SOIL.equals(currentElement)) {
            Objects.requireNonNull(flower).setSoil(Soil.fromValue(characters.getData()));
            return;
        }
        if (Names.XML_ORIGIN.equals(currentElement)) {
            Objects.requireNonNull(flower).setOrigin(characters.getData());
            return;
        }
        if (Names.XML_MULTIPLYING.equals(currentElement)) {
            Objects.requireNonNull(flower).setMultiplying(Multiplying.fromValue(characters.getData()));
            return;
        }
        if (Names.XML_STEM_COLOUR.equals(currentElement)) {
            Objects.requireNonNull(visualParameters).setStemColour(characters.getData());
            return;
        }
        if (Names.XML_LEAF_COLOUR.equals(currentElement)) {
            Objects.requireNonNull(visualParameters).setLeafColour(characters.getData());
            return;
        }
        if (Names.XML_AVE_LEN_FLOWER.equals(currentElement)) {
            aveLenFlower.setAmount(Integer.parseInt(characters.getData()));
            Objects.requireNonNull(visualParameters).setAveLenFlower(aveLenFlower);
            return;
        }
        if (Names.XML_TEMPRETURE.equals(currentElement)) {
            tempreture.setValue(Integer.parseInt(characters.getData()));
            Objects.requireNonNull(growingTips).setTempreture(tempreture);
            return;
        }
        if (Names.XML_WATERING.equals(currentElement)) {
            watering.setQuantity(Integer.parseInt(characters.getData()));
            Objects.requireNonNull(growingTips).setWatering(watering);
        }
    }

    private void attributeMethod (String currentElement, String data) {
        if (Names.XML_LIGHTING.equals(currentElement)) {
            String enumData = data.substring(data.indexOf('\'') + 1, data.lastIndexOf('\''));
            Objects.requireNonNull(growingTips).setLighting(new Lighting(LightRequiring.fromValue(enumData)));
            return;
        }
        if (Names.XML_AVE_LEN_FLOWER.equals(currentElement)) {
            aveLenFlower = new AveLenFlower();
            aveLenFlower.setMeasure(extractData(data));
            return;
        }
        if (Names.XML_TEMPRETURE.equals(currentElement)) {
            tempreture = new Tempreture();
            tempreture.setMeasure(extractData(data));
            return;
        }
        if (Names.XML_WATERING.equals(currentElement)) {
            watering = new Watering();
            watering.setMeasure(extractData(data));
        }
    }

    private static String extractData (String data) {
        return data.substring(data.indexOf('\'') + 1, data.lastIndexOf('\''));
    }
}
