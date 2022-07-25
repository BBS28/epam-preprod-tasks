package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.entity.Flowers;
import com.epam.rd.java.basic.practice7.parsers.ParserDOM;
import com.epam.rd.java.basic.practice7.parsers.ParserSAX;
import com.epam.rd.java.basic.practice7.parsers.ParserSTAX;
import com.epam.rd.java.basic.practice7.util.Sorter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public final class Main {

    public static void main(final String[] args) throws IOException, SAXException, ParserConfigurationException, XMLStreamException, TransformerException {
        ParserDOM parserDOM = new ParserDOM(Constants.INPUT_XML);
        parserDOM.parse(true);
        Flowers flowers = parserDOM.getFlowers();
        System.out.println(flowers);
        ParserDOM.saveToXML(flowers, Constants.OUTPUT_DOM_XML);
        System.out.println("==================================================");
        ParserSAX parserSAX = new ParserSAX("input.xml");
        parserSAX.parse(true);
        flowers = parserSAX.getFlowers();
        Sorter.sortFlowersByName(flowers);
        System.out.println(flowers);
        ParserDOM.saveToXML(flowers, Constants.OUTPUT_SAX_XML);
        System.out.println("==================================================");
        ParserSTAX parserSTAX = new ParserSTAX("input.xml");
        parserSTAX.parse();
        flowers = parserSTAX.getFlowers();
        Sorter.sortFlowersByName(flowers);
        System.out.println(flowers);
        ParserDOM.saveToXML(flowers, Constants.OUTPUT_STAX_XML);


    }
}
