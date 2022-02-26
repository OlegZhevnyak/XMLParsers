package com.it_academy.parsers.StAX;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import static com.it_academy.xml.services.StaxXmlServices.outputParsedByStaxJournal;
import static com.it_academy.xml.services.StaxXmlServices.setJournalWithXMLNodeValuesByStaxParser;

public class StaxParserRunner {

    private static final String XML_PATH = "journal.xml";

    public static void main(String[] args) throws XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader =
                factory.createXMLStreamReader(
                        ClassLoader.getSystemResourceAsStream(XML_PATH));
        setJournalWithXMLNodeValuesByStaxParser(reader);
        outputParsedByStaxJournal();
    }
}
