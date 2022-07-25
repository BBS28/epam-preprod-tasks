package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.entity.Flowers;
import com.epam.rd.java.basic.practice7.parsers.ParserDOM;
import com.epam.rd.java.basic.practice7.parsers.ParserSAX;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ParserSAXTest {
    private static final String INPUT_XML_FILE = "input.xml";
    private static final String OUTPUT_XML_FILE = "test.dom.xml";

    @Test
    public void shouldParsValidXMLAndSaveToXML () {
        Flowers flowers = processSAX(INPUT_XML_FILE, true);
        Assert.assertNotNull(flowers);
        try {
            ParserDOM.saveToXML(flowers, OUTPUT_XML_FILE);
        } catch (TransformerException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(Files.exists(Paths.get(OUTPUT_XML_FILE)));
    }

    @Test
    public void shouldCCreateXMLWithoutValidate() {
        Flowers flowers =processSAX(INPUT_XML_FILE, false);
        Assert.assertNotNull(flowers);
        try {
            ParserDOM.saveToXML(flowers, OUTPUT_XML_FILE);
        } catch (TransformerException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(Files.exists(Paths.get(OUTPUT_XML_FILE)));
    }

    @After
    public void deleteTestFile() {
        try {
            Files.deleteIfExists(Paths.get(OUTPUT_XML_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Flowers processSAX (String inputXMLFile, boolean validate){
        ParserSAX parserSAX = new ParserSAX(inputXMLFile);
        try {
            parserSAX.parse(validate);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return parserSAX.getFlowers();
    }
}

