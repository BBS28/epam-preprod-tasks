package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.entity.Flowers;
import com.epam.rd.java.basic.practice7.parsers.ParserDOM;
import com.epam.rd.java.basic.practice7.parsers.ParserSTAX;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ParserSTAXTest {
    private static final String INPUT_XML_FILE = "input.xml";
    private static final String OUTPUT_XML_FILE = "test.dom.xml";

    @Test
    public void shouldParsValidXMLAndSaveToXML () {
        Flowers flowers = processSTAX(INPUT_XML_FILE);
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

    private Flowers processSTAX (String inputXMLFile){
        ParserSTAX parserSTAX = new ParserSTAX(inputXMLFile);
        try {
            parserSTAX.parse();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return parserSTAX.getFlowers();
    }
}
